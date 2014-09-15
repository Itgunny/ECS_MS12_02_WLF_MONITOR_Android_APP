package taeha.wheelloader.fseries_monitor.main;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.R.string;
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

public class MainBBaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBBaseFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	AbsoluteLayout LayoutBody;
	
	FrameLayout framelayoutCenter;
	FrameLayout framelayoutLeftUp;
	FrameLayout framelayoutLeftDown;
	FrameLayout framelayoutRightUp;
	FrameLayout framelayoutRightDown;
	FrameLayout framelayoutIndicator;
	FrameLayout framelayoutMenuBar;
	FrameLayout framelayoutVirtualKey;
	FrameLayout framelayoutKeyTitle;
	FrameLayout framelayoutKeyBody;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	public MainBCenterFragment _MainBCenterFragment;
	public MainBCenterEngineFragment _MainBCenterEngineFragment; 
	public MainBCenterTMFragment _MainBCenterTMFragment;
	public MainBCenterHourOdometerFragment _MainBCenterHourOdometerFragment;
	public MainBCenterMachineStatusFragment _MainBCenterMachineStatusFragment;
	public MainBCenterQuickFragment _MainBCenterQuickFragment;
	
	public MainBIndicatorFragment _MainBIndicatorFragment;
	
	public MainBRightUpEngineFragment _MainBRightUpEngineFragment;
	public MainBRightUpEngineModeFragment _MainBRightUpEngineModeFragment;
	public MainBRightUpEngineWarmingUpFragment _MainBRightUpEngineWarmingUpFragment;
	public MainBRightUpQuickFragment _MainBRightUpQuickFragment;
	
	public MainBRightDownTMFragment _MainBRightDownTMFragment;
	public MainBRightDownTMCCOModeFragment _MainBRightDownTMCCOModeFragment;
	public MainBRightDownTMShiftModeFragment _MainBRightDownTMShiftModeFragment;
	public MainBRightDownTMTCLockUpFragment _MainBRightDownTMTCLockUpFragment;
	public MainBRightDownQuickFragment _MainBRightDownQuickFragment;
	
	public MainBLeftDownHourOdometerFragment _MainBLeftDownHourOdoFragment;
	public MainBLeftDownHourOdometerSelectFragment _MainBLeftDownHourOdometerSelectFragment;
	public MainBLeftDownQuickFragment _MainBLeftDownQuickFragment;
	
	public MainBLeftUpMachineStatusFragment _MainBLeftUpMachineStatusFragment;
	public MainBLeftUpMachineStatusSelectFragment _MainBLeftUpMachineStatusSelectFragment;
	public MainBLeftUpQuickFragment _MainBLeftUpQuickFragment;
	
	public MainBUpperMenuBarFragment _MainBUpperMenuBarFragment;
	
	public MainBVirtualKeyFragment _MainBVirtualKeyFragment;
	
	public MainBKeyTitleFragment _MainBKeyTitleFragment;
	
	public MainBKeyMainLightFragment _MainBKeyMainLightFragment;
	public MainBKeyWorkLightFragment _MainBKeyWorkLightFragment;
	public MainBKeyAutoGreaseFragment _MainBKeyAutoGreaseFragment;
	public MainBKeyQuickCouplerFragment _MainBKeyQuickCouplerFragment;
	public MainBKeyRideControlFragment _MainBKeyRideControlFragment;
	public MainBKeyWorkLoadFragment _MainBKeyWorkLoadFragment;
	public MainBKeyBeaconLampFragment _MainBKeyBeaconLampFragment;
	public MainBKeyRearWiperFragment _MainBKeyRearWiperFragment;
	public MainBKeyMirrorHeatFragment _MainBKeyMirrorHeatFragment;
	public MainBKeyDetentFragment _MainBKeyDetentFragment;
	public MainBKeyFineModulationFragment _MainBKeyFineModulationFragment;
	public MainBKeyWorkLoadAccumulationFragment _MainBKeyWorkLoadAccumulationFragment;
	public MainBKeyWorkLoadDisplayFragment _MainBKeyWorkLoadDisplayFragment;
	public MainBKeyWorkLoadErrorDetectionFragment _MainBKeyWorkLoadErrorDetectionFragment;
	public MainBKeyRideControlSpeedFragment _MainBKeyRideControlSpeedFragment;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	public MainBodyShiftAnimation _MainBodyShiftAnimation;
	
	public ChangeFragmentAnimation CenterAnimation;
	public ChangeFragmentAnimation RightUpChangeAnimation;
	public ChangeFragmentAnimation RightDownChangeAnimation;
	public ChangeFragmentAnimation LeftUpChangeAnimation;
	public ChangeFragmentAnimation LeftDownChangeAnimation;
	public ChangeFragmentAnimation IndicatorChangeAnimation;
	public ChangeFragmentAnimation VirtualKeyChangeAnimation;
	public ChangeFragmentAnimation KeyTitleChangeAnimation;
	public ChangeFragmentAnimation KeyBodyChangeAnimation;
	
	public LeftRightShiftAnimation _RightUpShiftAnimation;
	public LeftRightShiftAnimation _RightDownShiftAnimation;
	public LeftRightShiftAnimation _LeftUpShiftAnimation;
	public LeftRightShiftAnimation _LeftDownShiftAnimation;
	public LeftRightShiftAnimation _KeyTitleShiftAnimation;
	public LeftRightShiftAnimation _KeyBodyShiftAnimation;
	
	public DisappearAnimation _CenterDisappearAnimation; 
	public DisappearAnimation _RightUpDisappearAnimation;
	public DisappearAnimation _RightDownDisappearAnimation;
	public DisappearAnimation _LeftUpDisappearAnimation;
	public DisappearAnimation _LeftDownDisappearAnimation;
	public DisappearAnimation _KeyTitleDisappearAnimation;
	public DisappearAnimation _KeyBodyDisappearAnimation;
	
	public AppearAnimation _CenterAppearAnimation;
	public AppearAnimation _RightUpAppearAnimation;
	public AppearAnimation _RightDownAppearAnimation;
	public AppearAnimation _LeftUpAppearAnimation;
	public AppearAnimation _LeftDownAppearAnimation;
	public AppearAnimation _KeyTitleAppearAnimation;
	public AppearAnimation _KeyBodyAppearAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.screen_main_b, null);
		InitResource();
		InitFragment();
		InitValuables();
		
		InitButtonListener();
		
		showCenter();
		showIndicator();
		showRightUpEngine();
		showRightDownTM();
		showLeftDownHourOdo();
		showLeftUpMachineStatus();
		showUpperMenuBar();
		showVirtualKey();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutBody = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_b_body);
		
		framelayoutCenter = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_center);
		framelayoutLeftUp = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_leftup);
		framelayoutLeftDown = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_leftdown);
		framelayoutRightUp = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_rightup);
		framelayoutRightDown = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_rightdown);
		framelayoutIndicator = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_indicator);
		framelayoutMenuBar = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_menubar);
		framelayoutVirtualKey = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_virtualkey);
		framelayoutKeyTitle = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_key_title);
		framelayoutKeyBody = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_b_key_body);
		
		framelayoutKeyTitle.setVisibility(View.INVISIBLE);
		framelayoutKeyBody.setVisibility(View.INVISIBLE);
	}
	
	protected void InitFragment(){
		_MainBCenterFragment = new MainBCenterFragment();
		_MainBCenterEngineFragment = new MainBCenterEngineFragment();
		_MainBCenterTMFragment = new MainBCenterTMFragment();
		_MainBCenterHourOdometerFragment = new MainBCenterHourOdometerFragment();
		_MainBCenterMachineStatusFragment = new MainBCenterMachineStatusFragment();
		_MainBCenterQuickFragment = new MainBCenterQuickFragment();
		
		_MainBIndicatorFragment = new MainBIndicatorFragment();
		
		_MainBRightUpEngineFragment = new MainBRightUpEngineFragment();
		_MainBRightUpEngineModeFragment = new MainBRightUpEngineModeFragment();
		_MainBRightUpEngineWarmingUpFragment = new MainBRightUpEngineWarmingUpFragment();
		_MainBRightUpQuickFragment = new MainBRightUpQuickFragment();
		
		_MainBRightDownTMFragment = new MainBRightDownTMFragment();
		_MainBRightDownTMCCOModeFragment = new MainBRightDownTMCCOModeFragment();
		_MainBRightDownTMShiftModeFragment = new MainBRightDownTMShiftModeFragment();
		_MainBRightDownTMTCLockUpFragment = new MainBRightDownTMTCLockUpFragment();
		_MainBRightDownQuickFragment = new MainBRightDownQuickFragment();
		
		_MainBLeftDownHourOdoFragment = new MainBLeftDownHourOdometerFragment();
		_MainBLeftDownHourOdometerSelectFragment = new MainBLeftDownHourOdometerSelectFragment();
		_MainBLeftDownQuickFragment = new MainBLeftDownQuickFragment();
		
		_MainBLeftUpMachineStatusFragment = new MainBLeftUpMachineStatusFragment();
		_MainBLeftUpMachineStatusSelectFragment = new MainBLeftUpMachineStatusSelectFragment();
		_MainBLeftUpQuickFragment = new MainBLeftUpQuickFragment();
		
		_MainBUpperMenuBarFragment = new MainBUpperMenuBarFragment();
		
		_MainBVirtualKeyFragment = new MainBVirtualKeyFragment();
		
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		
		_MainBKeyMainLightFragment = new MainBKeyMainLightFragment();
		_MainBKeyWorkLightFragment = new MainBKeyWorkLightFragment();
		_MainBKeyAutoGreaseFragment = new MainBKeyAutoGreaseFragment();
		_MainBKeyQuickCouplerFragment = new MainBKeyQuickCouplerFragment();
		_MainBKeyRideControlFragment = new MainBKeyRideControlFragment();
		_MainBKeyWorkLoadFragment = new MainBKeyWorkLoadFragment();
		_MainBKeyBeaconLampFragment = new MainBKeyBeaconLampFragment();
		_MainBKeyRearWiperFragment = new MainBKeyRearWiperFragment();
		_MainBKeyMirrorHeatFragment = new MainBKeyMirrorHeatFragment();
		_MainBKeyDetentFragment = new MainBKeyDetentFragment();
		_MainBKeyFineModulationFragment = new MainBKeyFineModulationFragment();
		_MainBKeyWorkLoadAccumulationFragment = new MainBKeyWorkLoadAccumulationFragment();
		_MainBKeyWorkLoadDisplayFragment = new MainBKeyWorkLoadDisplayFragment();
		_MainBKeyWorkLoadErrorDetectionFragment = new MainBKeyWorkLoadErrorDetectionFragment();
		_MainBKeyRideControlSpeedFragment = new MainBKeyRideControlSpeedFragment();
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		_MainBodyShiftAnimation = new MainBodyShiftAnimation(ParentActivity, LayoutBody);
		CenterAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutCenter, R.id.FrameLayout_screen_main_b_center,_MainBCenterFragment);
		RightUpChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutRightUp, R.id.FrameLayout_screen_main_b_rightup,_MainBRightUpEngineFragment);
		RightDownChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutRightDown, R.id.FrameLayout_screen_main_b_rightdown,_MainBRightDownTMFragment);
		LeftUpChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutLeftUp, R.id.FrameLayout_screen_main_b_leftup,_MainBLeftUpMachineStatusFragment);
		LeftDownChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutLeftDown, R.id.FrameLayout_screen_main_b_leftdown,_MainBLeftDownHourOdoFragment);
		IndicatorChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutIndicator, R.id.FrameLayout_screen_main_b_indicator,_MainBIndicatorFragment);
		VirtualKeyChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutVirtualKey, R.id.FrameLayout_screen_main_b_virtualkey,_MainBVirtualKeyFragment);
		KeyTitleChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutKeyTitle, R.id.FrameLayout_screen_main_b_key_title,null);
		KeyBodyChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutKeyBody, R.id.FrameLayout_screen_main_b_key_body,null);
		
		_RightUpShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutRightUp,600,-400);
		_RightDownShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutRightDown,600,400);
		_LeftUpShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutLeftUp,-600,-400);
		_LeftDownShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutLeftDown,-600,400);
		_KeyTitleShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutKeyTitle,-400,0);
		_KeyBodyShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutKeyBody,1800,0);
		
		_CenterDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutCenter);
		_RightUpDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutRightUp);
		_RightDownDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutRightDown);
		_LeftUpDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutLeftUp);
		_LeftDownDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutLeftDown);
		_KeyTitleDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutKeyTitle);
		_KeyBodyDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutKeyBody);
		
		_CenterAppearAnimation = new AppearAnimation(ParentActivity, framelayoutCenter);
		_RightUpAppearAnimation = new AppearAnimation(ParentActivity, framelayoutRightUp);
		_RightDownAppearAnimation = new AppearAnimation(ParentActivity, framelayoutRightDown);
		_LeftUpAppearAnimation = new AppearAnimation(ParentActivity, framelayoutLeftUp);
		_LeftDownAppearAnimation = new AppearAnimation(ParentActivity, framelayoutLeftDown);
		_KeyTitleAppearAnimation = new AppearAnimation(ParentActivity, framelayoutKeyTitle);
		_KeyBodyAppearAnimation = new AppearAnimation(ParentActivity, framelayoutKeyBody);
		
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
	/////////////////////////////////////////////////////////////////////	
	
	//Show Fragment//////////////////////////////////////////////////////
	// Center
	public void showCenter(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_center, _MainBCenterFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterEngine(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_center, _MainBCenterEngineFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterTM(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_center, _MainBCenterTMFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterHourOdometer(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_center, _MainBCenterHourOdometerFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterMachineStatus(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_center, _MainBCenterMachineStatusFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_center, _MainBCenterQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	
	// Indicator
	public void showIndicator(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_indicator, _MainBIndicatorFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Right Up
	public void showRightUpEngine(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightup, _MainBRightUpEngineFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightUpEngineMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightup, _MainBRightUpEngineModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightUpEngineWarmingUp(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightup, _MainBRightUpEngineWarmingUpFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightUpQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightup, _MainBRightUpQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Right Down
	public void showRightDownTM(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightdown, _MainBRightDownTMFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightDownTMCCOMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightdown, _MainBRightDownTMCCOModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightDownTMShiftMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightdown, _MainBRightDownTMShiftModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightDownTMTCLockUp(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightdown, _MainBRightDownTMTCLockUpFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}	
	public void showRightDownQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_rightdown, _MainBRightDownQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}	
	// Left Down
	public void showLeftDownHourOdo(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_leftdown, _MainBLeftDownHourOdoFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showLeftDownHourOdoSelect(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_leftdown, _MainBLeftDownHourOdometerSelectFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showLeftDownQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_leftdown, _MainBLeftDownQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	// Left Up
	public void showLeftUpMachineStatus(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_leftup, _MainBLeftUpMachineStatusFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showLeftUpMachineStatusSelect(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_leftup, _MainBLeftUpMachineStatusSelectFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showLeftUpQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_leftup, _MainBLeftUpQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Upper
	public void showUpperMenuBar(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_menubar, _MainBUpperMenuBarFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// VirtualKey
	public void showVirtualKey(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_virtualkey, _MainBVirtualKeyFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Key Title
	public void showKeyTitle(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_title, _MainBKeyTitleFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Key Body
	public void showKeyMainLight(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyMainLightFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLight(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyWorkLightFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyAutoGrease(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyAutoGreaseFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyQuickCoupler(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyQuickCouplerFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyRideControl(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyRideControlFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoad(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyWorkLoadFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyBeaconLamp(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyBeaconLampFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyRearWiper(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyRearWiperFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyMirrorHeat(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyMirrorHeatFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyDetent(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyDetentFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyFineModulation(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyFineModulationFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoadAccumulation(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyWorkLoadAccumulationFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoadDisplay(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyWorkLoadDisplayFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoadErrorDetection(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyWorkLoadErrorDetectionFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyRideControlSpeed(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_b_key_body, _MainBKeyRideControlSpeedFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	public void showDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		CenterAnimation.StartChangeAnimation(_MainBCenterFragment);
		RightUpChangeAnimation.StartChangeAnimation(_MainBRightUpEngineFragment);
		RightDownChangeAnimation.StartChangeAnimation(_MainBRightDownTMFragment);
		LeftUpChangeAnimation.StartChangeAnimation(_MainBLeftUpMachineStatusFragment);
		LeftDownChangeAnimation.StartChangeAnimation(_MainBLeftDownHourOdoFragment);
		VirtualKeyChangeAnimation.StartChangeAnimation(_MainBVirtualKeyFragment);
		
		_RightUpShiftAnimation.StartShiftAnimation();
		_RightDownShiftAnimation.StartShiftAnimation();
		_LeftUpShiftAnimation.StartShiftAnimation();
		_LeftDownShiftAnimation.StartShiftAnimation();
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();
		
		_MainBIndicatorFragment.EnableVirtualKey();
	}
	
	public void showQuickScreenAnimation(){
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
	
		CenterAnimation.StartChangeAnimation(_MainBCenterQuickFragment);
		RightUpChangeAnimation.StartChangeAnimation(_MainBRightUpQuickFragment);
		RightDownChangeAnimation.StartChangeAnimation(_MainBRightDownQuickFragment);
		LeftUpChangeAnimation.StartChangeAnimation(_MainBLeftUpQuickFragment);
		LeftDownChangeAnimation.StartChangeAnimation(_MainBLeftDownQuickFragment);
		
		
		_RightUpShiftAnimation.StartShiftAnimation();
		_RightDownShiftAnimation.StartShiftAnimation();
		_LeftUpShiftAnimation.StartShiftAnimation();
		_LeftDownShiftAnimation.StartShiftAnimation();
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();
		
		_MainBIndicatorFragment.EnableVirtualKey();
	}
	
	public void showKeyScreenAnimation(){
		
		_CenterDisappearAnimation.StartAnimation();
		_RightUpDisappearAnimation.StartAnimation();
		_RightDownDisappearAnimation.StartAnimation();
		_LeftUpDisappearAnimation.StartAnimation();
		_LeftDownDisappearAnimation.StartAnimation();
		
		_KeyTitleAppearAnimation.StartAnimation();
		_KeyBodyAppearAnimation.StartAnimation();
		
		_KeyTitleShiftAnimation.StartShiftAnimation();
		_KeyBodyShiftAnimation.StartShiftAnimation();
		
		VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		_MainBIndicatorFragment.DisableVirtualKey();
		Log.d(TAG,"showKeyScreenAnimation");
		
	}
	public void showMainLightAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_mainlight));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Main_Light));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyMainLightFragment);
	}
	public void showWorkLightAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_worklight));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Light));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLightFragment);
	}
	public void showAutoGreaseAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_auto_grease));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Auto_Grease));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyAutoGreaseFragment);
	}
	public void showQuickCouplerAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_quick));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Quick_Coupler));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyQuickCouplerFragment);
	}
	public void showRideControlAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_ridecontrol));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Ride_Control));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyRideControlFragment);
	}
	public void showWorkLoadAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadFragment);
	}
	public void showBeaconLampAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_beacon));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Beacon_Lamp));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyBeaconLampFragment);
	}
	public void showRearWiperAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_rearwiper));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Rear_Wiper));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyRearWiperFragment);
	}
	public void showMirrorHeatAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_mirror));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Mirror_Heat));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyMirrorHeatFragment);
	}
	public void showDetentAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_autoposition));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Detent_Setting));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyDetentFragment);
	}
	public void showFineModulationAnimation(){
		showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_fine));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Fine_Modulation));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyFineModulationFragment);
	}
	public void showWorkLoadAccumulationAnimation(){
	//	showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadAccumulationFragment);
	}
	public void showWorkLoadDisplayAnimation(){
	//	showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadDisplayFragment);
	}
	public void showWorkLoadErrorDetectionAnimation(){
	//	showKeyScreenAnimation();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadErrorDetectionFragment);
	}
	public void showRideControlSpeedAnimation(){
		//	showKeyScreenAnimation();
			_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_ridecontrol));
			_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Ride_Control));
		//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
			KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyRideControlSpeedFragment);
		}
	
	/////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(final int key){
		Log.d(TAG,"KeyButtonClick : 0x" + Integer.toHexString(key));
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (key) {
				case CAN1CommManager.MAINLIGHT:

					showMainLightAnimation();
					break;
				case CAN1CommManager.WORKLIGHT:

					showWorkLightAnimation();
					break;
				case CAN1CommManager.AUTO_GREASE:

					showAutoGreaseAnimation();
					break;
				case CAN1CommManager.QUICK_COUPLER:

					showQuickCouplerAnimation();
					break;
				case CAN1CommManager.RIDE_CONTROL:

					showRideControlAnimation();
					break;
				case CAN1CommManager.WORK_LOAD:

					showWorkLoadAnimation();
					break;
				case CAN1CommManager.BEACON_LAMP:

					showBeaconLampAnimation();
					break;
				case CAN1CommManager.REAR_WIPER:

					showRearWiperAnimation();
					break;
				case CAN1CommManager.MIRROR_HEAT:

					showMirrorHeatAnimation();
					break;
				case CAN1CommManager.AUTOPOSITION:

					showDetentAnimation();
					break;
				case CAN1CommManager.FINEMODULATION:

					showFineModulationAnimation();
					break;
				default:
					break;
				}
				
			}
		});
	}
}
