package taeha.wheelloader.fseries_monitor.menu.mode;

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
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuModeETCFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CoolingFanReverse;
	int EngineAutoShutdownStatus;
	int EngineAutoShutdownTime;
	
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
		 TAG = "MenuModeETCFragment";
		Log.d(TAG, "onCreateView");

		InitList();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_TOP;
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		setClickableList4(true);
		setClickableList5(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.Speedometer_Freq_Setting));
		setListTitle2(ParentActivity.getResources().getString(string.Cooling_Fan_Reverse_Mode));
		setListTitle3(ParentActivity.getResources().getString(string.Wiper_Level_Setting));
		setListTitle4(ParentActivity.getResources().getString(string.Engine_Auto_Shutdown));
		setListTitle5(ParentActivity.getResources().getString(string.Camera_Setting));
	}
	
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		CoolingFanReverse = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		EngineAutoShutdownTime = CAN1Comm.Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122();
		EngineAutoShutdownStatus = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN61184_122();		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		CoolingFanReverseDisplay(CoolingFanReverse);
		EngineAutoShutdownDisplay(EngineAutoShutdownStatus, EngineAutoShutdownTime);
	}
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodySpeedometerFreqAnimation();
		
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyCoolingFanAnimation();
		
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyEngineAutoShutdownAnimation();
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity._MenuBaseFragment.showBodyCameraSettingAnimation();
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity._MenuBaseFragment.showBodyEngineDelayShutdownAnimation();
	}



	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		
		
	}
	public void ClickRight(){
		
		
	}
	public void ClickESC(){
		
	}
	public void ClickEnter(){
		
		
	}

	/////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			setListFocus(1);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP:
			setListFocus(2);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP:
			setListFocus(3);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP:
			setListFocus(4);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_TOP:
			setListFocus(5);
			break;
		default:
			setListFocus(0);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void CoolingFanReverseDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			setListData2(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL:
			setListData2(ParentActivity.getResources().getString(string.Manual));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			setListData2(ParentActivity.getResources().getString(string.Automatic));
			break;

		default:
			break;
		}
	}
	public void EngineAutoShutdownDisplay(int status, int time){
		switch (status) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			setListData4(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			setListData4(ParentActivity.GetSectoMinString(time) + ParentActivity.getResources().getString(string.Min));
			break;

		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
