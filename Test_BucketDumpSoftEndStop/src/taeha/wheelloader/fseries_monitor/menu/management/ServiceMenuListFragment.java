package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.color;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuBodyList_ParentFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ServiceMenuListFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int nSpeedLimit;
	int SpeedLimitStatus;
	static int CursurIndex = 1;
	int nInputMachineSerial=0;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "ServiceMenuListFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Service_Menu), 323);
		CursurDisplay(CursurIndex);
		MachineSerialDisplay();
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		nSpeedLimit = CAN1Comm.Get_VehicleSpeedLimit_572_PGN61184_106();
		SpeedLimitStatus = CAN1Comm.Get_VehicleSpeedLimitMode_575_PGN65434();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		SpeedLimitDisplay(SpeedLimitStatus,nSpeedLimit,ParentActivity.UnitOdo);
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		int Hourmeter = CAN1Comm.Get_Hourmeter_1601_PGN65433()/3600;
		nInputMachineSerial = 0;
		
		Log.d(TAG, "CAN1Comm.Get_TripTime_849_PGN65344()"+CAN1Comm.Get_TripTime_849_PGN65344());
		Log.d(TAG, "Hourmeter="+Hourmeter);
		
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.Sensor_Monitoring), 359);
		setListTitle2(ParentActivity.getResources().getString(string.Speed_Limit_Setting), 360);
		setListTitle3(ParentActivity.getResources().getString(string.Weighing_System_Compensation),361);
		
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
			setClickableList4(true);
			setListTitle4(ParentActivity.getResources().getString(string.Machine_Serial_Number), 82);
			
			setClickableList5(true);
			setListTitle5(ParentActivity.getResources().getString(string.Soft_End_Stop_Calibration), 472);
			
			if(Hourmeter <= 20)
				nInputMachineSerial = 4;
			else
				nInputMachineSerial = 1;
		}else{
			setClickableList4(true);
			setListTitle4(ParentActivity.getResources().getString(string.EHCU_IO_Information), 256);

			setClickableList5(true);
			setListTitle5(ParentActivity.getResources().getString(string.Machine_Serial_Number), 82);	
			
			setClickableList6(true);
			setListTitle6(ParentActivity.getResources().getString(string.Soft_End_Stop_Calibration), 472);
			
			if(Hourmeter < 20)
				nInputMachineSerial = 5;
			else
				nInputMachineSerial = 2;
		}
		
		if((nInputMachineSerial == 1) || (nInputMachineSerial == 2))
			setListColor(nInputMachineSerial+3, ParentActivity.getResources().getColor(color.main_title_gray));
		
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySensorMonitoringAnimation();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySpeedLimitAnimation();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyWeighingCompensationAnimation();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		// ++, 150325 bwk
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		if(nInputMachineSerial == 4)
		{
			ParentActivity._MenuBaseFragment.showBodyChangeMachineSerialAnimation();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);		
		}
		else if(nInputMachineSerial != 1)
		{
			ParentActivity._MenuBaseFragment.showBodyEHCUIOInfoAnimation();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);			
		}
		// --, 150325 bwk
		// ++, 150323 bwk
		// 관리 기능 하위로 위치 이동
//		Intent intent;
//		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
//				"taeha.wheelloader.update");
//		
//		if(intent != null){
//			CAN1Comm.Callback_StopCommService();
//			CAN1Comm.CloseComport();
//			startActivity(intent);		
//		}
//		CursurIndex = 4;
//		CursurDisplay(CursurIndex);
		// --, 150323 bwk
		
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
			ParentActivity._MenuBaseFragment.showBodyServiceSoftAndStopCalibrationMenuList();
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
		}else {
			if(nInputMachineSerial == 5)
			{
				ParentActivity._MenuBaseFragment.showBodyChangeMachineSerialAnimation();
				CursurIndex = 5;
				CursurDisplay(CursurIndex);		 
			}
		}
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		ParentActivity._MenuBaseFragment.showBodyServiceSoftAndStopCalibrationMenuList();
		CursurIndex = 6;
		CursurDisplay(CursurIndex);
	}
	
	/////////////////////////////////////////////////////////////////////	
	public void SpeedLimitDisplay(int _status, int _speed, int _unit){
		switch (_status) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			setListData2(ParentActivity.getResources().getString(string.Off), 98);
		
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			if(_speed != 0xFF){
				String strSpeed;
				strSpeed = ParentActivity.GetRideControlSpeed(_speed, _unit);
				
				if(_unit == Home.UNIT_ODO_MILE){
					setListData2(strSpeed + getString(ParentActivity.getResources().getString(R.string.mph), 32));
				}else{
					setListData2(strSpeed + getString(ParentActivity.getResources().getString(R.string.km_h), 31));
				}
			}
			break;
		default:
			break;
		}
	}
	public void SpeedDisplay(int _data, int _unit){

	}
	public void MachineSerialDisplay(){
		if(ParentActivity.MachineSerialNumber != 0xffffff)
		{
			switch(nInputMachineSerial)
			{
				case 1:	case 4:
					setListData4(Integer.toString(ParentActivity.MachineSerialNumber));
					break;
				case 2: case 5:
					setListData5(Integer.toString(ParentActivity.MachineSerialNumber));
					break;
			}
		}
	}	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	// 장비 호기 수 추가되면 Left, Right 다시 구현 해야 함!!!
	public void ClickLeft(){
		switch (CursurIndex) {
		case 0:
			
			break;
		case 1:
			if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
				CursurIndex = 5;
			}else{
				CursurIndex = 6;
			}
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex--;
			CursurDisplay(CursurIndex);
		default:
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 0:
			
			break;
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
				CursurIndex = 1;
			}else{
				CursurIndex++;
			}
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
		default:
			break;
		}
	}
	public void ClickESC(){
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickBack();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			imgbtnList[0].callOnClick();
			break;
		case 2:
			imgbtnList[1].callOnClick();
			break;
		case 3:
			imgbtnList[2].callOnClick();
			break;
		case 4:
			imgbtnList[3].callOnClick();
			break;
		case 5:
			imgbtnList[4].callOnClick();
			break;
		case 6:
			imgbtnList[5].callOnClick();
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		try {
			switch (Index) {
			case 1:
				setListFocus(1);
				break;
			case 2:
				setListFocus(2);
				break;
			case 3:
				setListFocus(3);
				break;
			case 4:
				setListFocus(4);
				break;
			case 5:
				setListFocus(5);
				break;
			case 6:
				setListFocus(5);
				break;
			default:
				setListFocus(0);
				break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException CursurDisplay");
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
