package taeha.wheelloader.fseries_monitor.menu.management;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import taeha.wheelloader.fseries_monitor.main.DataProvider;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class ServiceMenuPasswordFragment extends PasswordFragment{
	
	// ++, 150403 cjg
	public static String monitorSerialNumber;
	protected byte[] ComponentBasicInformation;
	// --, 150403 cjg
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "ServiceMenuPasswordFragment";
		Log.d(TAG, "onCreateView");		
		
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Service_Menu), 323);
		SetTextIndicatorTitle(2);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyServiceMenuList();
	}
	
	// ++, 150323 bwk
	public void showHWTestScreen() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"taeha.wheelloader.fserieshwtest");
		
		if(intent != null){
			ParentActivity.CancelCommErrStopTimer();
			CAN1Comm.Callback_StopCommService();
			CAN1Comm.CloseComport();
			startActivity(intent);		
		}
	}	
	// --, 150323 bwk
	@Override
	public void EnterClick(){
		if(ErrCount >= MAX_ERR_CNT){
			return;
		}
		if(DataBufIndex < 5)
		{
			SetTextIndicatorTitle(4);
			PasswordIndicatorDisplay();
			
			StartIndicatorTitleTimer(1000);
		}
		else
		{
			
			SetTextIndicatorTitle(5);
			CAN1Comm.Set_MessageType_PGN61184_21(21);
			CAN1Comm.TxCANToMCU(21);
			
			CheckHWTest();
		}
		
		
	}
	public void CheckHWTest(){
		Log.d(TAG,"CheckHWTest Result");
		
		// ++, 150323 bwk
		int TempNumData[]={0x30,0x33,0x31,0x34,0x34,0x35,0x31,0x32,0x32,0x37};
		int TempResult = 1;
		for(int i=0;i<DataBufIndex;i++)
		{
			if(NumDataBuf[i] != TempNumData[i])
			{
				TempResult = 0;
				break;
			}
		}
		if(TempResult == 1)
		{
			CancelPasswordCheckTimer();
			CancelTimeOutTimer();
			
			showHWTestScreen();
			Log.d(TAG,"H/W Test");
			
			return;
		}
		
		StartSeedCheckTimer(1,100);
		StartTimeOutTimer(3000);
	}
	

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		DataBufIndex = 0;
		PasswordIndicatorDisplay();
		
		SetTextIndicatorTitle(3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CancelPasswordCheckTimer();
		SetTextIndicatorTitle(2);
	}
	// ++, 150216 bwk 
	@Override
	public void CheckPassword(){
		int Result;
		Result = CAN1Comm.Get_PasswordCertificationResult_956_PGN61184_24();
		Log.d(TAG,"CheckPassword Result : " + Integer.toString(Result));
		
		// ++, 150323 bwk
//		int TempNumData[]={0x30,0x33,0x31,0x34,0x34,0x35,0x31,0x32,0x32,0x37};
//		int TempResult = 1;
//		for(int i=0;i<DataBufIndex;i++)
//		{
//			if(tempNumDataBuf[i] != TempNumData[i])
//			{
//				TempResult = 0;
//				break;
//			}
//		}
//		if(TempResult == 1)
//		{
//			CancelPasswordCheckTimer();
//			CancelTimeOutTimer();
//			
//			showHWTestScreen();
//			Log.d(TAG,"H/W Test");
//			
//			return;
//		}
		// --, 150323 bwk
		//if(Result == 1)	// UserPassword
		//	Result = 0;
		
		
		switch (Result) {
		case 0:			// Not OK
		case 1:			// UserPassword	OK		
		case 13:		// TimeOut
			ErrCount++;
			DataBufIndex = 0;
			PasswordIndicatorDisplay();
			if(ErrCount >= MAX_ERR_CNT)
			{
				CancelPasswordCheckTimer();
				CancelTimeOutTimer();
				SetTextIndicatorTitle(13);
				return;
			}
			
			SetTextIndicatorTitle(3);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CancelPasswordCheckTimer();
			CancelTimeOutTimer();
			SetTextIndicatorTitle(2);		// ++, --, 150216 bwk 1(사용자 비밀번호 )-> 2(서비스 비밀번호 )

			break;
		case 2:	// Service Password OK
		
			CancelPasswordCheckTimer();
			CancelTimeOutTimer();
			
			if(Result == 2)
			{
				showServicePasswordNextScreen();
			}
			else if(Result == 1)
			{
				showUserPasswordNextScreen();
			}
			Log.d(TAG,"Password OK : " + Integer.toString(Result));
			break;
		
		case 5:	// MLC Password OK
		case 10:	// Master Password OK
		case 15:
		default:
			break;
		}
	}
	// --, 150216 bwk
	
	// ++, 150403 cjg
	@Override
	protected void GetDataFromNative() {
		ComponentBasicInformation = ParentActivity.GetMonitorComponentBasicInfo();
		String serialNumber = getSerialNumber(ComponentBasicInformation);
		DataProvider.setAuthkey(serialNumber);
		
	}
	
	public String getSerialNumber(byte[] BasicInfo)throws NullPointerException{
		boolean DataCheckFlag = true;
		String strSerial;
		int Index = 0;
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
			}
			else{
				
				break;
			}
		}
		
		char[] Serial;
		Serial = new char [Index];
		int[] Temp;
		Temp = new int[Index];
		
		for(int i = 0; i < Index; i++){
			Serial[i] = (char) BasicInfo[i+4];
			Temp[i] = (int)(BasicInfo[i+4] & 0xFF);
			if(Temp[i] > 254){
				DataCheckFlag = false;
			}
		}
		strSerial = new String(Serial,0,Serial.length);
		if(DataCheckFlag == false){
			return "-";
		}else{
			return strSerial;
		}
		
	}
	// --, 150403 cjg	
}
