package taeha.wheelloader.fseries_monitor.menu.management;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class SoftwareUpdatePasswordFragment extends PasswordFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "SoftwareUpdatePasswordFragment";
		Log.d(TAG, "onCreateView");		
		
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Software_Update), 274);
		SetTextIndicatorTitle(1);
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
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"taeha.wheelloader.update");
		
		if(intent != null){
			ParentActivity.CancelCommErrStopTimer();
			CAN1Comm.Callback_StopCommService();
			CAN1Comm.CloseComport();
			startActivity(intent);		
		}	
	}
	

	@Override
	public void showUserPasswordNextScreen() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"taeha.wheelloader.update");
		
		if(intent != null){
			ParentActivity.CancelCommErrStopTimer();
			CAN1Comm.Callback_StopCommService();
			CAN1Comm.CloseComport();
			startActivity(intent);		
		}	
	}

	@Override
	public void CheckPassword(){
		int Result;
		Result = CAN1Comm.Get_PasswordCertificationResult_956_PGN61184_24();
		Log.d(TAG,"CheckPassword Result : " + Integer.toString(Result));

		//if(Result == 1)	// UserPassword
		//	Result = 0;
		int getBKCUComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_BKCU();
		
		switch (Result) {
		case 0:			// Not OK
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
			SetTextIndicatorTitle(1);		// ++, --, 150216 bwk 1(사용자 비밀번호 )-> 2(서비스 비밀번호 )

			break;
		case 1:			// UserPassword	OK		
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
}
