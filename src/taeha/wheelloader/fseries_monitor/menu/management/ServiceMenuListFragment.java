package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
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
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Service_Menu));
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
		SppedLimitDisplay(SpeedLimitStatus,nSpeedLimit,ParentActivity.UnitOdo);
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		setClickableList4(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.Sensor_Monitoring));
		setListTitle2(ParentActivity.getResources().getString(string.Speed_Limit_Setting));
		setListTitle3(ParentActivity.getResources().getString(string.Weighing_System_Compensation));
		setListTitle4(ParentActivity.getResources().getString(string.Software_Update));
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
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySpeedLimitAnimation();
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyWeighingCompensationAnimation();
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"taeha.wheelloader.update");
		
		if(intent != null){
			CAN1Comm.Callback_StopCommService();
			CAN1Comm.CloseComport();
			startActivity(intent);		
		}
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		
	}
	
	/////////////////////////////////////////////////////////////////////	
	public void SppedLimitDisplay(int _status, int _speed, int _unit){
		switch (_status) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			setListData2(ParentActivity.getResources().getString(string.Off));
		
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			if(_speed != 0xFF){
				String strSpeed;
				strSpeed = ParentActivity.GetRideControlSpeed(_speed, _unit);
				
				if(_unit == Home.UNIT_ODO_MILE){
					setListData2(strSpeed + ParentActivity.getResources().getString(R.string.mph));
				}else{
					
					setListData2(strSpeed + ParentActivity.getResources().getString(R.string.km_h));
				}
			}
			break;
		default:
			break;
		}
	}
	public void SpeedDisplay(int _data, int _unit){

	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
