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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EngineSettingFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
	int EngineWarmingUp;
	int EngineRPM;
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
		 TAG = "EngineSettingFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Engine_Setting));
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	//	ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		EngineWarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
		EngineRPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		EngineModeDisplay(EngineMode);
		EngineWarmingUpDisplay(EngineWarmingUp);
		EngineRPMDisplay(EngineRPM);
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);

		setListTitle1(ParentActivity.getResources().getString(string.Engine_Mode));
		setListTitle2(ParentActivity.getResources().getString(string.Warming_Up));
		setListTitle3(ParentActivity.getResources().getString(string.Engine_Speed));
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		ParentActivity.showEngineMode();
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		ParentActivity.showEngineWarmingUp();
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyEngineSpeedAnimation();
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		
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
	public void EngineModeDisplay(int data){
	
		switch (data) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			setListData1(ParentActivity.getResources().getString(string.Power));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			setListData1(getResources().getString(string.Standard));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			setListData1(getResources().getString(string.Econo));
			break;
		default:
			break;
		}
		
	}
	public void EngineWarmingUpDisplay(int data){
		
		switch (data) {
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
			setListData2(getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
			setListData2(getResources().getString(string.On));
			break;
		default:
			break;

		}
	} 
	public void EngineRPMDisplay(int data){
		if(data > 8031)	// Operational Range : 0 to 8,031
			data = 0;
		setListData3(Integer.toString(data));
	}	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
