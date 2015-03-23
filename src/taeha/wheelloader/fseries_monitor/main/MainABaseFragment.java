package taeha.wheelloader.fseries_monitor.main;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterAEBFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterBrakePedalCalibrationFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterEngineFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterFuelFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterMachineStatusFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterQuickFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainACenterTMFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainAIndicatorFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainAKeyTitleFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainALeftDownFuelSelectFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainALeftQuickFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainALeftMainFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainALeftUpMachineStatusSelectFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightDownTMCCOModeFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightMainFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightDownTMICCOModeFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightDownTMShiftModeFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightDownTMTCLockUpFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightQuickFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightUpEngineModeFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainARightUpHourOdometerSelectFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainAUpperMenuBarFragment;
import taeha.wheelloader.fseries_monitor.main.a.MainAVirtualKeyFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyAutoGreaseFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyBeaconLampFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyDetentFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyFineModulationFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyMainLightFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyMirrorHeatFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyRearWiperFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyRideControlFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyRideControlSpeedFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyWorkLightFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyQuickCouplerFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyWorkLoadAccumulationFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyWorkLoadDisplayFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyWorkLoadErrorDetectionFragment;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyWorkLoadFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainABaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	AbsoluteLayout LayoutBody;
	AbsoluteLayout LayoutTop;
	AbsoluteLayout LayoutKey;
	
	FrameLayout framelayoutCenter;
	FrameLayout framelayoutLeft;
	FrameLayout framelayoutRight;
	FrameLayout framelayoutIndicator;
	FrameLayout framelayoutUpperMenuBar;
	FrameLayout framelayoutVirtualKey;
	FrameLayout framelayoutKeyTitle;
	FrameLayout framelayoutKeyBody;
	
	ImageView imgViewKeyTitleBG;
	ImageView imgViewKeyBodyBG;
	ImageView imgViewCenterBG;
	ImageView imgViewRightBG;
	ImageView imgViewLeftBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BrakepedalReq;
	int AEBReq;	
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	public MainACenterFragment 						_MainACenterFragment;
	public MainACenterEngineFragment 				_MainACenterEngineFragment; 
	public MainACenterTMFragment 					_MainACenterTMFragment;
	public MainACenterFuelFragment 					_MainACenterFuelFragment;
	public MainACenterMachineStatusFragment 		_MainACenterMachineStatusFragment;
	public MainACenterQuickFragment 				_MainACenterQuickFragment;
	public MainACenterBrakePedalCalibrationFragment	_MainACenterBrakePedalCalibrationFragment;
	public MainACenterAEBFragment					_MainACenterAEBFragment;
	
	public MainAIndicatorFragment 					_MainAIndicatorFragment;
	
	public MainARightMainFragment 					_MainARightMainFragment;
	public MainARightUpEngineModeFragment 			_MainARightUpEngineModeFragment;
	public MainARightUpHourOdometerSelectFragment 	_MainARightUpHourOdometerSelectFragment;
	public MainARightDownTMCCOModeFragment 			_MainARightDownTMCCOModeFragment;
	public MainARightDownTMICCOModeFragment 		_MainARightDownTMICCOModeFragment;
	public MainARightDownTMShiftModeFragment 		_MainARightDownTMShiftModeFragment;
	public MainARightDownTMTCLockUpFragment 		_MainARightDownTMTCLockUpFragment;
	public MainARightQuickFragment					_MainARightQuickFragment;
	
	public MainALeftMainFragment 					_MainALeftMainFragment;
	public MainALeftUpMachineStatusSelectFragment 	_MainALeftUpMachineStatusSelectFragment;
	public MainALeftDownFuelSelectFragment 			_MainALeftDownFuelSelectFragment;
	public MainALeftQuickFragment					_MainALeftQuickFragment;
	
	public MainAUpperMenuBarFragment 				_MainAUpperMenuBarFragment;
	
	public MainAVirtualKeyFragment 					_MainAVirtualKeyFragment;
	
	public MainAKeyTitleFragment 					_MainAKeyTitleFragment;
	
	public MainAKeyMainLightFragment 				_MainAKeyMainLightFragment;
	public MainAKeyWorkLightFragment 				_MainAKeyWorkLightFragment;
	public MainAKeyAutoGreaseFragment 				_MainAKeyAutoGreaseFragment;
	public MainAKeyQuickCouplerFragment 			_MainAKeyQuickCouplerFragment;
	public MainAKeyRideControlFragment 				_MainAKeyRideControlFragment;
	public MainAKeyWorkLoadFragment 				_MainAKeyWorkLoadFragment;
	public MainAKeyBeaconLampFragment 				_MainAKeyBeaconLampFragment;
	public MainAKeyRearWiperFragment 				_MainAKeyRearWiperFragment;
	public MainAKeyMirrorHeatFragment 				_MainAKeyMirrorHeatFragment;
	public MainAKeyDetentFragment 					_MainAKeyDetentFragment;
	public MainAKeyFineModulationFragment 			_MainAKeyFineModulationFragment;
	public MainAKeyWorkLoadAccumulationFragment 	_MainAKeyWorkLoadAccumulationFragment;
	public MainAKeyWorkLoadDisplayFragment 			_MainAKeyWorkLoadDisplayFragment;
	public MainAKeyWorkLoadErrorDetectionFragment 	_MainAKeyWorkLoadErrorDetectionFragment;
	public MainAKeyRideControlSpeedFragment 		_MainAKeyRideControlSpeedFragment;
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	public MainBodyShiftAnimation _MainBodyShiftAnimation;
	public MainBodyShiftAnimation _MainAVirtualKeyShiftAnimation;
	
	public ChangeFragmentAnimation CenterAnimation;
	public ChangeFragmentAnimation RightChangeAnimation;
	public ChangeFragmentAnimation LeftChangeAnimation;
	public ChangeFragmentAnimation IndicatorChangeAnimation;
	public ChangeFragmentAnimation VirtualKeyChangeAnimation;
	public ChangeFragmentAnimation KeyTitleChangeAnimation;
	public ChangeFragmentAnimation KeyBodyChangeAnimation;
	
	public LeftRightShiftAnimation _RightShiftAnimation;
	public LeftRightShiftAnimation _LeftShiftAnimation;
	public LeftRightShiftAnimation _KeyTitleShiftAnimation;
	public LeftRightShiftAnimation _KeyBodyShiftAnimation;
	public LeftRightShiftAnimation _RightCenterShiftAnimation;
	public LeftRightShiftAnimation _LeftCenterShiftAnimation;
	public LeftRightShiftAnimation _KeyTitleBGShiftAnimation;
	public LeftRightShiftAnimation _KeyBodyBGShiftAnimation;
	public LeftRightShiftAnimation _RightBGShiftAnimation;
	public LeftRightShiftAnimation _LeftBGShiftAnimation;
	
	public DisappearAnimation _CenterDisappearAnimation; 
	public DisappearAnimation _RightDisappearAnimation;
	public DisappearAnimation _LeftDisappearAnimation;
	public DisappearAnimation _KeyTitleDisappearAnimation;
	public DisappearAnimation _KeyBodyDisappearAnimation;
	public DisappearAnimation _KeyTitleBGDisappearAnimation;
	public DisappearAnimation _KeyBodyBGDisappearAnimation;
	public DisappearAnimation _BodyDisappearAnimation;
	public DisappearAnimation _KeyDisappearAnimation;
	public DisappearAnimation _RightBGDisappearAnimation;
	public DisappearAnimation _LeftBGDisappearAnimation;
	public DisappearAnimation _CenterBGDisappearAnimation;
	public DisappearAnimation _VirtualKeyDisappearAnimation;
	
	public AppearAnimation _CenterAppearAnimation;
	public AppearAnimation _RightAppearAnimation;
	public AppearAnimation _LeftAppearAnimation;
	public AppearAnimation _KeyTitleAppearAnimation;
	public AppearAnimation _KeyBodyAppearAnimation;
	public AppearAnimation _KeyTitleBGAppearAnimation;
	public AppearAnimation _KeyBodyBGAppearAnimation;
	public AppearAnimation _BodyAppearAnimation;
	public AppearAnimation _KeyAppearAnimation;
	public AppearAnimation _RightBGAppearAnimation;
	public AppearAnimation _LeftBGAppearAnimation;
	public AppearAnimation _CenterBGAppearAnimation;
	public AppearAnimation _VirtualKeyAppearAnimation;	
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
		
		showCenter();
		
		
		showIndicator();
		showRightMain();
		showLeftMain();
		showUpperMenuBar();		
		
		VirtualKeyChangeAnimation.StartDisappearAnimation();
		if(BrakepedalReq == 1){
			CenterAnimation.StartChangeAnimation(_MainACenterBrakePedalCalibrationFragment);
		}else if (AEBReq == 1){
			CenterAnimation.StartChangeAnimation(_MainACenterAEBFragment);
		}		
	
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		Log.d(TAG, "StartSeatBeltTimer");
		ParentActivity.StartSeatBeltTimer();
		Log.d(TAG, "onCreateView OK");
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
		LayoutBody = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_a_body);
		LayoutTop = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_a);
		LayoutKey = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_a_key);
		
		framelayoutCenter = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_center);
		framelayoutLeft = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_left);
		framelayoutRight = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_right);
		framelayoutIndicator = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_indicator);
		framelayoutUpperMenuBar = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_menubar);
		framelayoutVirtualKey = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_virtualkey);
		framelayoutKeyTitle = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_key_title);
		framelayoutKeyBody = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_screen_main_a_key_body);
		
		imgViewKeyTitleBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_a_key_title_bg);
		imgViewKeyBodyBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_a_key_body_bg);
		
		imgViewCenterBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_a_center_bg);
		imgViewRightBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_a_right_bg);
		imgViewLeftBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_a_left_bg);
		
		framelayoutKeyTitle.setVisibility(View.INVISIBLE);
		framelayoutKeyBody.setVisibility(View.INVISIBLE);		
	}
	
	protected void InitFragment(){
		_MainACenterFragment = new MainACenterFragment();
		_MainACenterEngineFragment = new MainACenterEngineFragment();
		_MainACenterTMFragment = new MainACenterTMFragment();
		_MainACenterFuelFragment = new MainACenterFuelFragment();
		_MainACenterMachineStatusFragment = new MainACenterMachineStatusFragment();
		_MainACenterQuickFragment = new MainACenterQuickFragment();
		_MainACenterBrakePedalCalibrationFragment = new MainACenterBrakePedalCalibrationFragment();
		_MainACenterAEBFragment = new MainACenterAEBFragment();
		
		_MainAIndicatorFragment = new MainAIndicatorFragment();
		
		_MainARightQuickFragment = new MainARightQuickFragment();
		
		_MainALeftQuickFragment = new MainALeftQuickFragment();
		
		_MainARightMainFragment = new MainARightMainFragment();
		_MainARightUpEngineModeFragment = new MainARightUpEngineModeFragment();
		_MainARightUpHourOdometerSelectFragment = new MainARightUpHourOdometerSelectFragment();
		
		_MainARightDownTMCCOModeFragment = new MainARightDownTMCCOModeFragment();
		_MainARightDownTMICCOModeFragment = new MainARightDownTMICCOModeFragment();
		_MainARightDownTMShiftModeFragment = new MainARightDownTMShiftModeFragment();
		_MainARightDownTMTCLockUpFragment = new MainARightDownTMTCLockUpFragment();
		
		_MainALeftDownFuelSelectFragment = new MainALeftDownFuelSelectFragment();
		
		_MainALeftMainFragment = new MainALeftMainFragment();
		_MainALeftUpMachineStatusSelectFragment = new MainALeftUpMachineStatusSelectFragment();
		
		_MainAUpperMenuBarFragment = new MainAUpperMenuBarFragment();
		
		_MainAVirtualKeyFragment = new MainAVirtualKeyFragment();
		
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		
		_MainAKeyMainLightFragment = new MainAKeyMainLightFragment();
		_MainAKeyWorkLightFragment = new MainAKeyWorkLightFragment();
		_MainAKeyAutoGreaseFragment = new MainAKeyAutoGreaseFragment();
		_MainAKeyQuickCouplerFragment = new MainAKeyQuickCouplerFragment();
		_MainAKeyRideControlFragment = new MainAKeyRideControlFragment();
		_MainAKeyWorkLoadFragment = new MainAKeyWorkLoadFragment();
		_MainAKeyBeaconLampFragment = new MainAKeyBeaconLampFragment();
		_MainAKeyRearWiperFragment = new MainAKeyRearWiperFragment();
		_MainAKeyMirrorHeatFragment = new MainAKeyMirrorHeatFragment();
		_MainAKeyDetentFragment = new MainAKeyDetentFragment();
		_MainAKeyFineModulationFragment = new MainAKeyFineModulationFragment();
		_MainAKeyWorkLoadAccumulationFragment = new MainAKeyWorkLoadAccumulationFragment();
		_MainAKeyWorkLoadDisplayFragment = new MainAKeyWorkLoadDisplayFragment();
		_MainAKeyWorkLoadErrorDetectionFragment = new MainAKeyWorkLoadErrorDetectionFragment();
		_MainAKeyRideControlSpeedFragment = new MainAKeyRideControlSpeedFragment();		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		BrakepedalReq = CAN1Comm.Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201();
		AEBReq = CAN1Comm.Get_RequestAEB_PGN61184_201();	
		
		_MainBodyShiftAnimation = new MainBodyShiftAnimation(ParentActivity, LayoutBody);
		_MainAVirtualKeyShiftAnimation = new MainBodyShiftAnimation(ParentActivity, framelayoutVirtualKey);
		CenterAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutCenter, R.id.FrameLayout_screen_main_a_center,_MainACenterFragment);
		RightChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutRight, R.id.FrameLayout_screen_main_a_right,_MainARightMainFragment);
		LeftChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutLeft, R.id.FrameLayout_screen_main_a_left,_MainALeftMainFragment);
		IndicatorChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutIndicator, R.id.FrameLayout_screen_main_a_indicator,_MainAIndicatorFragment);
		VirtualKeyChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutVirtualKey, R.id.FrameLayout_screen_main_a_virtualkey,_MainAVirtualKeyFragment);
		KeyTitleChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutKeyTitle, R.id.FrameLayout_screen_main_a_key_title,null);
		KeyBodyChangeAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutKeyBody, R.id.FrameLayout_screen_main_a_key_body,null);
		
		_RightShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutRight,600,0);
		_LeftShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutLeft,-600,0);
		_RightCenterShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutCenter, -400, 0);
		_LeftCenterShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutCenter, 400, 0);
		_KeyTitleShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutKeyTitle,-400,0);
		_KeyBodyShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutKeyBody,1800,0);
		_KeyTitleBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewKeyTitleBG,-400,0);
		_KeyBodyBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewKeyBodyBG,1800,0);
		_RightBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewRightBG,600,0);
		_LeftBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewLeftBG,-600,0);

		_CenterDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutCenter);
		_RightDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutRight);
		_LeftDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutLeft);
		_KeyTitleDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutKeyTitle);
		_KeyBodyDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutKeyBody);
		_KeyTitleBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewKeyTitleBG);
		_KeyBodyBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewKeyBodyBG);
		_BodyDisappearAnimation = new DisappearAnimation(ParentActivity, LayoutBody);
		_KeyDisappearAnimation = new DisappearAnimation(ParentActivity, LayoutKey);
		_RightBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewRightBG);
		_LeftBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewLeftBG);
		_CenterBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewCenterBG);
		_VirtualKeyDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutVirtualKey);
		
		_CenterAppearAnimation = new AppearAnimation(ParentActivity, framelayoutCenter);
		_RightAppearAnimation = new AppearAnimation(ParentActivity, framelayoutRight);
		_LeftAppearAnimation = new AppearAnimation(ParentActivity, framelayoutLeft);
		_KeyTitleAppearAnimation = new AppearAnimation(ParentActivity, framelayoutKeyTitle);
		_KeyBodyAppearAnimation = new AppearAnimation(ParentActivity, framelayoutKeyBody);
		_KeyTitleBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewKeyTitleBG);
		_KeyBodyBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewKeyBodyBG);
		_BodyAppearAnimation = new AppearAnimation(ParentActivity, LayoutBody);
		_KeyAppearAnimation = new AppearAnimation(ParentActivity, LayoutKey);
		_RightBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewRightBG);
		_LeftBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewLeftBG);
		_CenterBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewCenterBG);
		_VirtualKeyAppearAnimation = new AppearAnimation(ParentActivity, framelayoutVirtualKey);


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
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.detach(_MainAKeyTitleFragment);
		transaction.detach(_MainAKeyMainLightFragment);
		transaction.detach(_MainAKeyWorkLightFragment);
		transaction.detach(_MainAKeyAutoGreaseFragment);
		transaction.detach(_MainAKeyQuickCouplerFragment);
		transaction.detach(_MainAKeyRideControlFragment);
		transaction.detach(_MainAKeyWorkLoadFragment);
		transaction.detach(_MainAKeyBeaconLampFragment);
		transaction.detach(_MainAKeyRearWiperFragment);
		transaction.detach(_MainAKeyMirrorHeatFragment);
		transaction.detach(_MainAKeyDetentFragment);
		transaction.detach(_MainAKeyFineModulationFragment);
		transaction.detach(_MainAKeyWorkLoadAccumulationFragment);
		transaction.detach(_MainAKeyWorkLoadDisplayFragment);
		transaction.detach(_MainAKeyWorkLoadErrorDetectionFragment);
		transaction.detach(_MainAKeyRideControlSpeedFragment);

		transaction.commit();			
	}
	public void DestroyBodyFragment(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.detach(_MainACenterFragment);
		transaction.detach(_MainACenterEngineFragment);
		transaction.detach(_MainACenterTMFragment);
		transaction.detach(_MainACenterFuelFragment);
		transaction.detach(_MainACenterMachineStatusFragment);
		transaction.detach(_MainACenterQuickFragment);
		transaction.detach(_MainACenterBrakePedalCalibrationFragment);
		transaction.detach(_MainACenterAEBFragment);
		transaction.detach(_MainAIndicatorFragment);
		transaction.detach(_MainARightMainFragment);
		transaction.detach(_MainARightUpEngineModeFragment);
		transaction.detach(_MainARightUpHourOdometerSelectFragment);
		transaction.detach(_MainARightDownTMCCOModeFragment);
		transaction.detach(_MainARightDownTMICCOModeFragment);
		transaction.detach(_MainARightDownTMShiftModeFragment);
		transaction.detach(_MainARightDownTMTCLockUpFragment);
		transaction.detach(_MainARightQuickFragment);
		transaction.detach(_MainALeftMainFragment);
		transaction.detach(_MainALeftUpMachineStatusSelectFragment);
		transaction.detach(_MainALeftDownFuelSelectFragment);
		transaction.detach(_MainALeftQuickFragment);
		transaction.detach(_MainAUpperMenuBarFragment);
		transaction.detach(_MainAVirtualKeyFragment);
		transaction.commit();
	}
	public void DestroyFragment(){
		DestroyKeyFragment();
		DestroyBodyFragment();
		_MainACenterFragment 	 = null;
		_MainACenterEngineFragment 	 = null;
		_MainACenterTMFragment 	 = null;
		_MainACenterFuelFragment 	 = null;
		_MainACenterMachineStatusFragment 	 = null;
		_MainACenterQuickFragment 	 = null;
		_MainACenterBrakePedalCalibrationFragment = null;
		_MainACenterAEBFragment = null;
			
		_MainAIndicatorFragment 	 = null;
			
		_MainARightMainFragment 	 = null;
		_MainARightUpEngineModeFragment 	 = null;
		_MainARightUpHourOdometerSelectFragment 	 = null;
			
		_MainARightDownTMCCOModeFragment 	 = null;
		_MainARightDownTMICCOModeFragment 	 = null;
		_MainARightDownTMShiftModeFragment 	 = null;
		_MainARightDownTMTCLockUpFragment 	 = null;
		_MainARightQuickFragment	= null;
			
		_MainALeftMainFragment 	 = null;
		_MainALeftUpMachineStatusSelectFragment 	 = null;
		_MainALeftDownFuelSelectFragment 	 = null;
		_MainALeftQuickFragment	= null;
			
		_MainAUpperMenuBarFragment 	 = null;
			
		_MainAVirtualKeyFragment 	 = null;
		
		_MainAKeyTitleFragment 	 = null;
		
		_MainAKeyMainLightFragment 	 = null;
		_MainAKeyWorkLightFragment 	 = null;
		_MainAKeyAutoGreaseFragment 	 = null;
		_MainAKeyQuickCouplerFragment 	 = null;
		_MainAKeyRideControlFragment 	 = null;
		_MainAKeyWorkLoadFragment 	 = null;
		_MainAKeyBeaconLampFragment 	 = null;
		_MainAKeyRearWiperFragment 	 = null;
		_MainAKeyMirrorHeatFragment 	 = null;
		_MainAKeyDetentFragment 	 = null;
		_MainAKeyFineModulationFragment 	 = null;
		_MainAKeyWorkLoadAccumulationFragment 	 = null;
		_MainAKeyWorkLoadDisplayFragment 	 = null;
		_MainAKeyWorkLoadErrorDetectionFragment 	 = null;
		_MainAKeyRideControlSpeedFragment 	 = null;	
	}
	public void DestroyAnimation(){
		_MainBodyShiftAnimation.CancelShiftLayoutTimer();
		_MainAVirtualKeyShiftAnimation.CancelShiftLayoutTimer();
		_MainBodyShiftAnimation 	 = null;
		_MainAVirtualKeyShiftAnimation = null;
		CenterAnimation 	 = null;
		RightChangeAnimation 	 = null;
		LeftChangeAnimation 	 = null;
		IndicatorChangeAnimation 	 = null;
		VirtualKeyChangeAnimation 	 = null;
		KeyTitleChangeAnimation 	 = null;
		KeyBodyChangeAnimation 	 = null;
			
		_RightShiftAnimation 	 = null;
		_LeftShiftAnimation 	 = null;
		_RightCenterShiftAnimation 	 = null;
		_LeftCenterShiftAnimation 	 = null;
		_KeyTitleShiftAnimation 	 = null;
		_KeyBodyShiftAnimation 	 = null;
		_KeyTitleBGShiftAnimation 	 = null;
		_KeyBodyBGShiftAnimation 	 = null;
		_RightBGShiftAnimation 	 = null;
		_LeftBGShiftAnimation 	 = null;
			
		_CenterDisappearAnimation 	 = null;
		_RightDisappearAnimation 	 = null;
		_LeftDisappearAnimation 	 = null;
		_KeyTitleDisappearAnimation 	 = null;
		_KeyBodyDisappearAnimation 	 = null;
		_KeyTitleBGDisappearAnimation 	 = null;
		_KeyBodyBGDisappearAnimation 	 = null;
		_BodyDisappearAnimation 	 = null;
		_KeyDisappearAnimation 	 = null;
		_RightBGDisappearAnimation 	 = null;
		_LeftBGDisappearAnimation 	 = null;
		_CenterBGDisappearAnimation 	 = null;
		_VirtualKeyDisappearAnimation 	 = null;
			
		_CenterAppearAnimation 	 = null;
		_RightAppearAnimation 	 = null;
		_LeftAppearAnimation 	 = null;
		_KeyTitleAppearAnimation 	 = null;
		_KeyBodyAppearAnimation 	 = null;
		_KeyTitleBGAppearAnimation 	 = null;
		_KeyBodyBGAppearAnimation 	 = null;
		_BodyAppearAnimation 	 = null;
		_KeyAppearAnimation 	 = null;
		_RightBGAppearAnimation 	 = null;
		_LeftBGAppearAnimation 	 = null;
		_CenterBGAppearAnimation 	 = null;
		_VirtualKeyAppearAnimation 	 = null;	

	}
	/////////////////////////////////////////////////////////////////////	
	
	/////////////////////////////////////////////////////////////////////
	
	//Show Fragment//////////////////////////////////////////////////////
	// Center
	public void showCenter(){
		Log.d(TAG, "showCenter");
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterEngine(){
		Log.d(TAG, "showCenterEngine");
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterEngineFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterTM(){
		Log.d(TAG, "showCenterTM");
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterTMFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterHourOdometer(){
		Log.d(TAG, "showCenterHourOdometer");
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterFuelFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterMachineStatus(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterMachineStatusFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterBrakePedalCalibration(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterBrakePedalCalibrationFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showCenterAEB(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_center, _MainACenterAEBFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Indicator
	public void showIndicator(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_indicator, _MainAIndicatorFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Right
	public void showRightMain(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightMainFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
		
	}
	public void showRightQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Right Up
	public void showRightUpEngineMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightUpEngineModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightUpHourOdoSelect(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightUpHourOdometerSelectFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Right Down
	public void showRightDownTMCCOMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightDownTMCCOModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightDownTMICCOMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightDownTMICCOModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightDownTMShiftMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightDownTMShiftModeFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showRightDownTMTCLockUp(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_right, _MainARightDownTMTCLockUpFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}	
	
	// Left
	public void showLeftMain(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_left, _MainALeftMainFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showLeftQuick(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_left, _MainALeftQuickFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();		
	}
	public void showLeftDownHourOdoSelect(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_left, _MainALeftDownFuelSelectFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showLeftUpMachineStatusSelect(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_left, _MainALeftUpMachineStatusSelectFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Upper
	public void showUpperMenuBar(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_menubar, _MainAUpperMenuBarFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// VirtualKey
	public void showVirtualKey(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_virtualkey, _MainAVirtualKeyFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Key Title
	public void showKeyTitle(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_title, _MainAKeyTitleFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	
	// Key Body
	public void showKeyMainLight(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyMainLightFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLight(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyWorkLightFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyAutoGrease(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyAutoGreaseFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyQuickCoupler(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyQuickCouplerFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyRideControl(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyRideControlFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoad(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyWorkLoadFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyBeaconLamp(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyBeaconLampFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyRearWiper(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyRearWiperFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyMirrorHeat(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyMirrorHeatFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyDetent(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyDetentFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyFineModulation(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyFineModulationFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoadAccumulation(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyWorkLoadAccumulationFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoadDisplay(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyWorkLoadDisplayFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyWorkLoadErrorDetection(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyWorkLoadErrorDetectionFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}
	public void showKeyRideControlSpeed(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_screen_main_a_key_body, _MainAKeyRideControlSpeedFragment);
		//transaction.addToBackStack("Main_Left");
		transaction.commit();
	}	
	/////////////////////////////////////////////////////////////////////
	public void showDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
		RightChangeAnimation.StartChangeAnimation(_MainARightMainFragment);
		LeftChangeAnimation.StartChangeAnimation(_MainALeftMainFragment);
		
		
		_RightShiftAnimation.StartShiftAnimation();
		_LeftShiftAnimation.StartShiftAnimation();
		_RightBGShiftAnimation.StartShiftAnimation();
		_LeftBGShiftAnimation.StartShiftAnimation();
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();
		VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		IndicatorChangeAnimation.StartChangeAnimation(_MainAIndicatorFragment);
	
		
		
	}
	
	public void showRightUptoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
		RightChangeAnimation.StartChangeAnimation(_MainARightMainFragment);
		
		
		_RightAppearAnimation.StartAnimation();
		_LeftAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightBGAppearAnimation.StartAnimation();
		_LeftBGAppearAnimation.StartAnimation();
	
		
		_RightShiftAnimation.StartShiftAnimation();
		_LeftShiftAnimation.StartShiftAnimation();
		_RightBGShiftAnimation.StartShiftAnimation();
		_LeftBGShiftAnimation.StartShiftAnimation();
	}
	public void showRightDowntoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
		RightChangeAnimation.StartChangeAnimation(_MainARightMainFragment);
		
		
		_RightAppearAnimation.StartAnimation();
		_LeftAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightBGAppearAnimation.StartAnimation();
		_LeftBGAppearAnimation.StartAnimation();
		
		
		_RightShiftAnimation.StartShiftAnimation();
		_LeftShiftAnimation.StartShiftAnimation();
		
		_RightBGShiftAnimation.StartShiftAnimation();
		_LeftBGShiftAnimation.StartShiftAnimation();
	}
	public void showLeftDowntoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
		LeftChangeAnimation.StartChangeAnimation(_MainALeftMainFragment);
		
		
		_RightAppearAnimation.StartAnimation();
		_LeftAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightBGAppearAnimation.StartAnimation();
		_LeftBGAppearAnimation.StartAnimation();
		
		_RightShiftAnimation.StartShiftAnimation();
		_LeftShiftAnimation.StartShiftAnimation();
		
		_RightBGShiftAnimation.StartShiftAnimation();
		_LeftBGShiftAnimation.StartShiftAnimation();
	}
	public void showLeftUptoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
		LeftChangeAnimation.StartChangeAnimation(_MainALeftMainFragment);
		
		
		_RightAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightBGAppearAnimation.StartAnimation();
	
		
		_RightShiftAnimation.StartShiftAnimation();
		_RightBGShiftAnimation.StartShiftAnimation();
	}
	public void showCalibrationtoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
	}
	public void showKeytoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		
		_BodyAppearAnimation.StartAnimation();
		_KeyDisappearAnimation.StartAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterFragment);
		RightChangeAnimation.StartChangeAnimation(_MainARightMainFragment);
		LeftChangeAnimation.StartChangeAnimation(_MainALeftMainFragment);
		
		
		_RightShiftAnimation.StartShiftAnimation();
		_LeftShiftAnimation.StartShiftAnimation();
		_RightBGShiftAnimation.StartShiftAnimation();
		_LeftBGShiftAnimation.StartShiftAnimation();
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();

	
		
	}
	public void showQuickScreenAnimation(){
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainACenterQuickFragment);
		RightChangeAnimation.StartChangeAnimation(_MainARightQuickFragment);
		LeftChangeAnimation.StartChangeAnimation(_MainALeftQuickFragment);
		
		_RightShiftAnimation.StartShiftAnimation();
		_RightBGShiftAnimation.StartShiftAnimation();
		_LeftShiftAnimation.StartShiftAnimation();
		_LeftBGShiftAnimation.StartShiftAnimation();
		
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	
	public void showVirtualKeytoQuickScreenAnimation(){
		VirtualKeyChangeAnimation.StartDisappearAnimation();
	}
	
	public void showVritualKeyScreenAnimation(){
		_MainAVirtualKeyShiftAnimation.StartShiftZeroAnimation();
		VirtualKeyChangeAnimation.StartChangeAnimation(_MainAVirtualKeyFragment);
	}
	
	public void showKeyScreenAnimation(){
		if((ParentActivity.ScreenIndex < ParentActivity.SCREEN_STATE_MAIN_A_KEY_TOP) || (ParentActivity.ScreenIndex > ParentActivity.SCREEN_STATE_MAIN_A_KEY_END)){
			CenterAnimation.StartChangeAnimation(_MainACenterQuickFragment);
			_BodyDisappearAnimation.StartAnimation();
			_KeyAppearAnimation.StartAnimation();
			_KeyTitleShiftAnimation.StartShiftAnimation();
			_KeyBodyShiftAnimation.StartShiftAnimation();
			_KeyTitleBGAppearAnimation.StartAnimation();
			_KeyBodyBGAppearAnimation.StartAnimation();
			_KeyTitleBGShiftAnimation.StartShiftAnimation();
			_KeyBodyBGShiftAnimation.StartShiftAnimation();
			VirtualKeyChangeAnimation.StartDisappearAnimation();
		
			
		}else{

		}
		
		Log.d(TAG,"showKeyScreenAnimation");
		
	}
	public void showMainLightAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_MAINLIGHT;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyMainLightFragment = new MainAKeyMainLightFragment();
		
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_mainlight));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Main_Light));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyMainLightFragment);
	}
	public void showWorkLightAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLIGHT;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyWorkLightFragment = new MainAKeyWorkLightFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_worklight));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Light));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyWorkLightFragment);
	}
	public void showAutoGreaseAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_AUTOGREASE;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyAutoGreaseFragment = new MainAKeyAutoGreaseFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_auto_grease));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Auto_Grease));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyAutoGreaseFragment);
	}
	public void showQuickCouplerAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyQuickCouplerFragment = new MainAKeyQuickCouplerFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_quick));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Quick_Coupler));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyQuickCouplerFragment);
	}
	public void showRideControlAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyRideControlFragment = new MainAKeyRideControlFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_ridecontrol));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Ride_Control));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyRideControlFragment);
	}
	public void showWorkLoadAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyWorkLoadFragment = new MainAKeyWorkLoadFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyWorkLoadFragment);
	}
	public void showBeaconLampAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_BEACONLAMP;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyBeaconLampFragment = new MainAKeyBeaconLampFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_beacon));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Beacon_Lamp));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyBeaconLampFragment);
	}
	public void showRearWiperAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_REARWIPER;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyRearWiperFragment = new MainAKeyRearWiperFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_rearwiper));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Rear_Wiper));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyRearWiperFragment);
	}
	public void showMirrorHeatAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_MIRRORHEAT;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyMirrorHeatFragment = new MainAKeyMirrorHeatFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_mirror));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Mirror_Heat));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyMirrorHeatFragment);
	}
	public void showDetentAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_DETENT;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyDetentFragment = new MainAKeyDetentFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_autoposition));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Detent_Setting));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyDetentFragment);
	}
	public void showFineModulationAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_FINEMODULATION;
		_MainAKeyTitleFragment = new MainAKeyTitleFragment();
		_MainAKeyFineModulationFragment = new MainAKeyFineModulationFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_fine));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Fine_Modulation));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyFineModulationFragment);
	}
	public void showWorkLoadAccumulationAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ACCUMULATION;
		_MainAKeyWorkLoadAccumulationFragment = new MainAKeyWorkLoadAccumulationFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyWorkLoadAccumulationFragment);
	}
	public void showWorkLoadDisplayAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_DISPLAY;
		_MainAKeyWorkLoadDisplayFragment = new MainAKeyWorkLoadDisplayFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyWorkLoadDisplayFragment);
	}
	public void showWorkLoadErrorDetectionAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ERRORDETECT;
		_MainAKeyWorkLoadErrorDetectionFragment = new MainAKeyWorkLoadErrorDetectionFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyWorkLoadErrorDetectionFragment);
	}
	public void showRideControlSpeedAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL_SPEED;
		_MainAKeyRideControlSpeedFragment = new MainAKeyRideControlSpeedFragment();
		_MainAKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_ridecontrol));
		_MainAKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Ride_Control));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainAKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainAKeyRideControlSpeedFragment);
	}

	/////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(final int key){
		Log.d(TAG,"KeyButtonClick : 0x" + Integer.toHexString(key));
		
		// TODO Auto-generated method stub
		switch (key) {
		case CAN1CommManager.MAINLIGHT:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_MAINLIGHT)
				showMainLightAnimation();
			else
				_MainAKeyMainLightFragment.ClickHardKey();
			break;
		case CAN1CommManager.WORKLIGHT:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLIGHT)
				showWorkLightAnimation();
			else
				_MainAKeyWorkLightFragment.ClickHardKey();
			break;
		case CAN1CommManager.AUTO_GREASE:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_AUTOGREASE)
				showAutoGreaseAnimation();
			else
				_MainAKeyAutoGreaseFragment.ClickHardKey();
			break;
		case CAN1CommManager.QUICK_COUPLER:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			
			showQuickCouplerAnimation();
			break;
		case CAN1CommManager.RIDE_CONTROL:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			showRideControlAnimation();
			break;
		case CAN1CommManager.WORK_LOAD:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			showWorkLoadAnimation();
			break;
		case CAN1CommManager.BEACON_LAMP:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_BEACONLAMP)
				showBeaconLampAnimation();
			else
				_MainAKeyBeaconLampFragment.ClickHardKey();
			break;
		case CAN1CommManager.REAR_WIPER:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_REARWIPER)
				showRearWiperAnimation();
			else
				_MainAKeyRearWiperFragment.ClickHardKey();
			break;
		case CAN1CommManager.MIRROR_HEAT:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_MIRRORHEAT)
				showMirrorHeatAnimation();
			else
				_MainAKeyMirrorHeatFragment.ClickHardKey();
			break;
		case CAN1CommManager.AUTOPOSITION:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			showDetentAnimation();
			break;
		case CAN1CommManager.FINEMODULATION:
			if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
				
			}else{
				if(ParentActivity.AnimationRunningFlag == true)
					return;
				if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_A_KEY_FINEMODULATION)
					showFineModulationAnimation();
				else
					_MainAKeyFineModulationFragment.ClickHardKey();
			}

			break;
		case CAN1CommManager.CAMERA:
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			if(CAN1Comm.CameraOnFlag ==  CAN1CommManager.STATE_CAMERA_OFF){
				ParentActivity.ExcuteCamActivitybyKey();
			}else{
				ParentActivity.ExitCam();
			}
			break;
		case CAN1CommManager.LEFT:
			
			break;
		case CAN1CommManager.RIGHT:
			
			break;
		case CAN1CommManager.ESC:
			ClickKeyButtonESC();
			break;
		case CAN1CommManager.ENTER:
 			ClickKeyButtonEnter();	
			break;
		case CAN1CommManager.MENU:
			Log.d(TAG,"Click MENU");
			_MainAUpperMenuBarFragment.ClickMenu();
			break;
		case CAN1CommManager.FN:
			Log.d(TAG,"Click FN");
			break;
		case CAN1CommManager.LONG_LEFT_RIGHT_ENTER:
			ClickKeyButtonLongLeftRightEnter();
			break;
		default:
			break;
		}
				

	}
	
	public void ClickKeyButtonLongLeftRightEnter(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE);
	}
	
	public void ClickKeyButtonESC(){
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MAIN_A_RIGHTUP_ENGINE_MODE:
		case Home.SCREEN_STATE_MAIN_A_RIGHTUP_HOURODMETER:
			showRightUptoDefaultScreenAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_RIGHTDOWN_CCOMODE:
		case Home.SCREEN_STATE_MAIN_A_RIGHTDOWN_SHIFTMODE:
		case Home.SCREEN_STATE_MAIN_A_RIGHTDOWN_TCLOCKUP:
			showRightDowntoDefaultScreenAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS:
			showLeftUptoDefaultScreenAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_LEFTDOWN_FUEL:
			showLeftDowntoDefaultScreenAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_QUICK_TOP:
			showDefaultScreenAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_KEY_MAINLIGHT:
		case Home.SCREEN_STATE_MAIN_A_KEY_WORKLIGHT:
		case Home.SCREEN_STATE_MAIN_A_KEY_AUTOGREASE:
		case Home.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER:
		case Home.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL:
		case Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD:
		case Home.SCREEN_STATE_MAIN_A_KEY_BEACONLAMP:
		case Home.SCREEN_STATE_MAIN_A_KEY_REARWIPER:
		case Home.SCREEN_STATE_MAIN_A_KEY_MIRRORHEAT:
		case Home.SCREEN_STATE_MAIN_A_KEY_DETENT:
		case Home.SCREEN_STATE_MAIN_A_KEY_FINEMODULATION:
			showKeytoDefaultScreenAnimation();
			break;	
			
		case Home.SCREEN_STATE_MAIN_A_BRKAEPEDALCALIBRATION_TOP:
		case Home.SCREEN_STATE_MAIN_A_AEB_TOP:
			showCalibrationtoDefaultScreenAnimation();
			break;	
			
		case Home.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL_SPEED:
			_MainAKeyRideControlSpeedFragment.ClickCancel();
			break;
		case Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ACCUMULATION:
			_MainAKeyWorkLoadAccumulationFragment.showWorkLoadAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_DISPLAY:
			_MainAKeyWorkLoadDisplayFragment.showWorkLoadAnimation();
			break;
		case Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ERRORDETECT:
			_MainAKeyWorkLoadErrorDetectionFragment.showWorkLoadAnimation();
			break;
		default:
			break;
		}
	}

	public void ClickKeyButtonEnter(){
		_MainALeftMainFragment.ClickEnter();
	}

}
