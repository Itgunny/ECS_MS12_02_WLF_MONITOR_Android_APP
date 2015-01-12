package taeha.wheelloader.fseries_monitor.main;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.BarAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterAEBFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterBrakePedalCalibrationFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterEngineFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterHourOdometerFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterMachineStatusFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterQuickFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBCenterTMFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBIndicatorFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBKeyTitleFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBLeftDownHourOdometerFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBLeftDownHourOdometerSelectFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBLeftDownQuickFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBLeftUpMachineStatusFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBLeftUpMachineStatusSelectFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBLeftUpQuickFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightDownQuickFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightDownTMCCOModeFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightDownTMFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightDownTMICCOModeFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightDownTMShiftModeFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightDownTMTCLockUpFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightUpEngineFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightUpEngineModeFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightUpEngineWarmingUpFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBRightUpQuickFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBUpperMenuBarFragment;
import taeha.wheelloader.fseries_monitor.main.b.MainBVirtualKeyFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyAutoGreaseFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyBeaconLampFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyDetentFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyFineModulationFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyMainLightFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyMirrorHeatFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyRearWiperFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyRideControlFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyRideControlSpeedFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyWorkLightFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyQuickCouplerFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyWorkLoadAccumulationFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyWorkLoadDisplayFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyWorkLoadErrorDetectionFragment;
import taeha.wheelloader.fseries_monitor.main.b.key.MainBKeyWorkLoadFragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainABaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	
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
		TAG = "MainABaseFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.screen_main_a, null);
		InitResource();
		InitFragment();
		InitValuables();
		
		InitButtonListener();
	
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		ParentActivity.StartSeatBeltTimer();
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		DestroyFragment();
		DestroyAnimation();
	//	Runtime.getRuntime().gc();
	}
	////////////////////////////////////////////////

	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
	}
	
	protected void InitFragment(){
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		

	}
	
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	public void DestroyKeyFragment(){
	
	}
	public void DestroyBodyFragment(){
		
	}
	public void DestroyFragment(){
	
	}
	public void DestroyAnimation(){
	

	}
	/////////////////////////////////////////////////////////////////////	
	
	/////////////////////////////////////////////////////////////////////
	
	//Show Fragment//////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
}
