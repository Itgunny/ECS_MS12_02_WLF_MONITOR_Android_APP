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
import android.widget.ImageView;

public class MainBBaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	AbsoluteLayout LayoutBody;
	AbsoluteLayout LayoutTop;
	AbsoluteLayout LayoutKey;
	
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
	
	ImageView imgViewKeyTitleBG;
	ImageView imgViewKeyBodyBG;
	ImageView imgViewCenterBG;
	ImageView imgViewRightUpBG;
	ImageView imgViewRightDownBG;
	ImageView imgViewLeftUp;
	ImageView imgViewLeftDown;
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
	public LeftRightShiftAnimation _KeyTitleBGShiftAnimation;
	public LeftRightShiftAnimation _KeyBodyBGShiftAnimation;
	public LeftRightShiftAnimation _RightUpBGShiftAnimation;
	public LeftRightShiftAnimation _RightDownBGShiftAnimation;
	public LeftRightShiftAnimation _LeftUpBGShiftAnimation;
	public LeftRightShiftAnimation _LeftDownBGShiftAnimation;
	
	public DisappearAnimation _CenterDisappearAnimation; 
	public DisappearAnimation _RightUpDisappearAnimation;
	public DisappearAnimation _RightDownDisappearAnimation;
	public DisappearAnimation _LeftUpDisappearAnimation;
	public DisappearAnimation _LeftDownDisappearAnimation;
	public DisappearAnimation _KeyTitleDisappearAnimation;
	public DisappearAnimation _KeyBodyDisappearAnimation;
	public DisappearAnimation _KeyTitleBGDisappearAnimation;
	public DisappearAnimation _KeyBodyBGDisappearAnimation;
	public DisappearAnimation _BodyDisappearAnimation;
	public DisappearAnimation _KeyDisappearAnimation;
	public DisappearAnimation _RightUpBGDisappearAnimation;
	public DisappearAnimation _RightDownBGDisappearAnimation;
	public DisappearAnimation _LeftUpBGDisappearAnimation;
	public DisappearAnimation _LeftDownBGDisappearAnimation;
	public DisappearAnimation _CenterBGDisappearAnimation;
	public DisappearAnimation _VirtualKeyDisappearAnimation;
	
	public AppearAnimation _CenterAppearAnimation;
	public AppearAnimation _RightUpAppearAnimation;
	public AppearAnimation _RightDownAppearAnimation;
	public AppearAnimation _LeftUpAppearAnimation;
	public AppearAnimation _LeftDownAppearAnimation;
	public AppearAnimation _KeyTitleAppearAnimation;
	public AppearAnimation _KeyBodyAppearAnimation;
	public AppearAnimation _KeyTitleBGAppearAnimation;
	public AppearAnimation _KeyBodyBGAppearAnimation;
	public AppearAnimation _BodyAppearAnimation;
	public AppearAnimation _KeyAppearAnimation;
	public AppearAnimation _RightUpBGAppearAnimation;
	public AppearAnimation _RightDownBGAppearAnimation;
	public AppearAnimation _LeftUpBGAppearAnimation;
	public AppearAnimation _LeftDownBGAppearAnimation;
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
		TAG = "MainBBaseFragment";
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

//		_BodyAppearAnimation.StartAnimation();
//		_RightUpShiftAnimation.StartShiftAnimation();
//		_RightDownShiftAnimation.StartShiftAnimation();
//		_LeftUpShiftAnimation.StartShiftAnimation();
//		_LeftDownShiftAnimation.StartShiftAnimation();
//		_RightUpBGShiftAnimation.StartShiftAnimation();
//		_RightDownBGShiftAnimation.StartShiftAnimation();
//		_LeftUpBGShiftAnimation.StartShiftAnimation();
//		_LeftDownBGShiftAnimation.StartShiftAnimation();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
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
		LayoutBody = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_b_body);
		LayoutTop = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_b);
		LayoutKey = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_screen_main_b_key);
		
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
		
		imgViewKeyTitleBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_key_title_bg);
		imgViewKeyBodyBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_key_body_bg);
		
		imgViewCenterBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_center_bg);
		imgViewRightUpBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_rightup_bg);
		imgViewRightDownBG = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_rightdown_bg);
		imgViewLeftUp = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_leftup_bg);
		imgViewLeftDown = (ImageView)mRoot.findViewById(R.id.imageView_screen_main_b_leftdown_bg);
		
		
		ImageView imgViewKeyTitleBG;
		ImageView imgViewKeyBodyBG;
		
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
		_KeyTitleBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewKeyTitleBG,-400,0);
		_KeyBodyBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewKeyBodyBG,1800,0);
		_RightUpBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewRightUpBG,600,-400);
		_RightDownBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewRightDownBG,600,400);
		_LeftUpBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewLeftUp,-600,-400);
		_LeftDownBGShiftAnimation = new LeftRightShiftAnimation(ParentActivity, imgViewLeftDown,-600,400);
		_RightUpShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutRightUp,600,-400);
		_RightDownShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutRightDown,600,400);
		_LeftUpShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutLeftUp,-600,-400);
		_LeftDownShiftAnimation = new LeftRightShiftAnimation(ParentActivity, framelayoutLeftDown,-600,400);

		_CenterDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutCenter);
		_RightUpDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutRightUp);
		_RightDownDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutRightDown);
		_LeftUpDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutLeftUp);
		_LeftDownDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutLeftDown);
		_KeyTitleDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutKeyTitle);
		_KeyBodyDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutKeyBody);
		_KeyTitleBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewKeyTitleBG);
		_KeyBodyBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewKeyBodyBG);
		_BodyDisappearAnimation = new DisappearAnimation(ParentActivity, LayoutBody);
		_KeyDisappearAnimation = new DisappearAnimation(ParentActivity, LayoutKey);
		_RightUpBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewRightUpBG);
		_RightDownBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewRightDownBG);
		_LeftUpBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewLeftUp);
		_LeftDownBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewLeftDown);
		_CenterBGDisappearAnimation = new DisappearAnimation(ParentActivity, imgViewCenterBG);
		_VirtualKeyDisappearAnimation = new DisappearAnimation(ParentActivity, framelayoutVirtualKey);
		
		_CenterAppearAnimation = new AppearAnimation(ParentActivity, framelayoutCenter);
		_RightUpAppearAnimation = new AppearAnimation(ParentActivity, framelayoutRightUp);
		_RightDownAppearAnimation = new AppearAnimation(ParentActivity, framelayoutRightDown);
		_LeftUpAppearAnimation = new AppearAnimation(ParentActivity, framelayoutLeftUp);
		_LeftDownAppearAnimation = new AppearAnimation(ParentActivity, framelayoutLeftDown);
		_KeyTitleAppearAnimation = new AppearAnimation(ParentActivity, framelayoutKeyTitle);
		_KeyBodyAppearAnimation = new AppearAnimation(ParentActivity, framelayoutKeyBody);
		_KeyTitleBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewKeyTitleBG);
		_KeyBodyBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewKeyBodyBG);
		_BodyAppearAnimation = new AppearAnimation(ParentActivity, LayoutBody);
		_KeyAppearAnimation = new AppearAnimation(ParentActivity, LayoutKey);
		_RightUpBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewRightUpBG);
		_RightDownBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewRightDownBG);
		_LeftUpBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewLeftUp);
		_LeftDownBGAppearAnimation = new AppearAnimation(ParentActivity, imgViewLeftDown);
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
		transaction.detach(_MainBKeyTitleFragment);
		transaction.detach(_MainBKeyMainLightFragment);
		transaction.detach(_MainBKeyWorkLightFragment);
		transaction.detach(_MainBKeyAutoGreaseFragment);
		transaction.detach(_MainBKeyQuickCouplerFragment);
		transaction.detach(_MainBKeyRideControlFragment);
		transaction.detach(_MainBKeyWorkLoadFragment);
		transaction.detach(_MainBKeyBeaconLampFragment);
		transaction.detach(_MainBKeyRearWiperFragment);
		transaction.detach(_MainBKeyMirrorHeatFragment);
		transaction.detach(_MainBKeyDetentFragment);
		transaction.detach(_MainBKeyFineModulationFragment);
		transaction.detach(_MainBKeyWorkLoadAccumulationFragment);
		transaction.detach(_MainBKeyWorkLoadDisplayFragment);
		transaction.detach(_MainBKeyWorkLoadErrorDetectionFragment);
		transaction.detach(_MainBKeyRideControlSpeedFragment);

		transaction.commit();		
	}
	public void DestroyBodyFragment(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.detach(_MainBCenterFragment);
		transaction.detach(_MainBCenterEngineFragment);
		transaction.detach(_MainBCenterTMFragment);
		transaction.detach(_MainBCenterHourOdometerFragment);
		transaction.detach(_MainBCenterMachineStatusFragment);
		transaction.detach(_MainBCenterQuickFragment);
		transaction.detach(_MainBIndicatorFragment);
		transaction.detach(_MainBRightUpEngineFragment);
		transaction.detach(_MainBRightUpEngineModeFragment);
		transaction.detach(_MainBRightUpEngineWarmingUpFragment);
		transaction.detach(_MainBRightUpQuickFragment);
		transaction.detach(_MainBRightDownTMFragment);
		transaction.detach(_MainBRightDownTMCCOModeFragment);
		transaction.detach(_MainBRightDownTMShiftModeFragment);
		transaction.detach(_MainBRightDownTMTCLockUpFragment);
		transaction.detach(_MainBRightDownQuickFragment);
		transaction.detach(_MainBLeftDownHourOdoFragment);
		transaction.detach(_MainBLeftDownHourOdometerSelectFragment);
		transaction.detach(_MainBLeftDownQuickFragment);
		transaction.detach(_MainBLeftUpMachineStatusFragment);
		transaction.detach(_MainBLeftUpMachineStatusSelectFragment);
		transaction.detach(_MainBLeftUpQuickFragment);
		transaction.detach(_MainBUpperMenuBarFragment);
		transaction.detach(_MainBVirtualKeyFragment);
		transaction.commit();
	}
	public void DestroyFragment(){
		DestroyKeyFragment();
		DestroyBodyFragment();
		_MainBCenterFragment 	 = null;
		_MainBCenterEngineFragment 	 = null;
		_MainBCenterTMFragment 	 = null;
		_MainBCenterHourOdometerFragment 	 = null;
		_MainBCenterMachineStatusFragment 	 = null;
		_MainBCenterQuickFragment 	 = null;
			
		_MainBIndicatorFragment 	 = null;
			
		_MainBRightUpEngineFragment 	 = null;
		_MainBRightUpEngineModeFragment 	 = null;
		_MainBRightUpEngineWarmingUpFragment 	 = null;
		_MainBRightUpQuickFragment 	 = null;
			
		_MainBRightDownTMFragment 	 = null;
		_MainBRightDownTMCCOModeFragment 	 = null;
		_MainBRightDownTMShiftModeFragment 	 = null;
		_MainBRightDownTMTCLockUpFragment 	 = null;
		_MainBRightDownQuickFragment 	 = null;
			
		_MainBLeftDownHourOdoFragment 	 = null;
		_MainBLeftDownHourOdometerSelectFragment 	 = null;
		_MainBLeftDownQuickFragment 	 = null;
			
		_MainBLeftUpMachineStatusFragment 	 = null;
		_MainBLeftUpMachineStatusSelectFragment 	 = null;
		_MainBLeftUpQuickFragment 	 = null;
			
		_MainBUpperMenuBarFragment 	 = null;
			
		_MainBVirtualKeyFragment 	 = null;
		
		
		_MainBKeyTitleFragment 	 = null;
		
		_MainBKeyMainLightFragment 	 = null;
		_MainBKeyWorkLightFragment 	 = null;
		_MainBKeyAutoGreaseFragment 	 = null;
		_MainBKeyQuickCouplerFragment 	 = null;
		_MainBKeyRideControlFragment 	 = null;
		_MainBKeyWorkLoadFragment 	 = null;
		_MainBKeyBeaconLampFragment 	 = null;
		_MainBKeyRearWiperFragment 	 = null;
		_MainBKeyMirrorHeatFragment 	 = null;
		_MainBKeyDetentFragment 	 = null;
		_MainBKeyFineModulationFragment 	 = null;
		_MainBKeyWorkLoadAccumulationFragment 	 = null;
		_MainBKeyWorkLoadDisplayFragment 	 = null;
		_MainBKeyWorkLoadErrorDetectionFragment 	 = null;
		_MainBKeyRideControlSpeedFragment 	 = null;
	}
	public void DestroyAnimation(){
		_MainBodyShiftAnimation.CancelShiftLayoutTimer();
		_MainBodyShiftAnimation 	 = null;
		CenterAnimation 	 = null;
		RightUpChangeAnimation 	 = null;
		RightDownChangeAnimation 	 = null;
		LeftUpChangeAnimation 	 = null;
		LeftDownChangeAnimation 	 = null;
		IndicatorChangeAnimation 	 = null;
		VirtualKeyChangeAnimation 	 = null;
		KeyTitleChangeAnimation 	 = null;
		KeyBodyChangeAnimation 	 = null;
			
		_RightUpShiftAnimation 	 = null;
		_RightDownShiftAnimation 	 = null;
		_LeftUpShiftAnimation 	 = null;
		_LeftDownShiftAnimation 	 = null;
		_KeyTitleShiftAnimation 	 = null;
		_KeyBodyShiftAnimation 	 = null;
		_KeyTitleBGShiftAnimation 	 = null;
		_KeyBodyBGShiftAnimation 	 = null;
		_RightUpBGShiftAnimation 	 = null;
		_RightDownBGShiftAnimation 	 = null;
		_LeftUpBGShiftAnimation 	 = null;
		_LeftDownBGShiftAnimation 	 = null;
		_RightUpShiftAnimation 	 = null;
		_RightDownShiftAnimation 	 = null;
		_LeftUpShiftAnimation 	 = null;
		_LeftDownShiftAnimation 	 = null;
			
		_CenterDisappearAnimation 	 = null;
		_RightUpDisappearAnimation 	 = null;
		_RightDownDisappearAnimation 	 = null;
		_LeftUpDisappearAnimation 	 = null;
		_LeftDownDisappearAnimation 	 = null;
		_KeyTitleDisappearAnimation 	 = null;
		_KeyBodyDisappearAnimation 	 = null;
		_KeyTitleBGDisappearAnimation 	 = null;
		_KeyBodyBGDisappearAnimation 	 = null;
		_BodyDisappearAnimation 	 = null;
		_KeyDisappearAnimation 	 = null;
		_RightUpBGDisappearAnimation 	 = null;
		_RightDownBGDisappearAnimation 	 = null;
		_LeftUpBGDisappearAnimation 	 = null;
		_LeftDownBGDisappearAnimation 	 = null;
		_CenterBGDisappearAnimation 	 = null;
		_VirtualKeyDisappearAnimation 	 = null;
			
		_CenterAppearAnimation 	 = null;
		_RightUpAppearAnimation 	 = null;
		_RightDownAppearAnimation 	 = null;
		_LeftUpAppearAnimation 	 = null;
		_LeftDownAppearAnimation 	 = null;
		_KeyTitleAppearAnimation 	 = null;
		_KeyBodyAppearAnimation 	 = null;
		_KeyTitleBGAppearAnimation 	 = null;
		_KeyBodyBGAppearAnimation 	 = null;
		_BodyAppearAnimation 	 = null;
		_KeyAppearAnimation 	 = null;
		_RightUpBGAppearAnimation 	 = null;
		_RightDownBGAppearAnimation 	 = null;
		_LeftUpBGAppearAnimation 	 = null;
		_LeftDownBGAppearAnimation 	 = null;
		_CenterBGAppearAnimation 	 = null;
		_VirtualKeyAppearAnimation 	 = null;

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
		_RightUpBGShiftAnimation.StartShiftAnimation();
		_RightDownBGShiftAnimation.StartShiftAnimation();
		_LeftUpBGShiftAnimation.StartShiftAnimation();
		_LeftDownBGShiftAnimation.StartShiftAnimation();
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();

		
		_MainBIndicatorFragment.EnableVirtualKey();
		
		
	}
	
	public void showRightUptoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainBCenterFragment);
		RightUpChangeAnimation.StartChangeAnimation(_MainBRightUpEngineFragment);
		
		
		_RightDownAppearAnimation.StartAnimation();
		_LeftUpAppearAnimation.StartAnimation();
		_LeftDownAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightDownBGAppearAnimation.StartAnimation();
		_LeftUpBGAppearAnimation.StartAnimation();
		_LeftDownBGAppearAnimation.StartAnimation();
		
		_VirtualKeyAppearAnimation.StartAnimation();
		
		
		_RightDownShiftAnimation.StartShiftAnimation();
		_LeftUpShiftAnimation.StartShiftAnimation();
		_LeftDownShiftAnimation.StartShiftAnimation();
		_RightDownBGShiftAnimation.StartShiftAnimation();
		_LeftUpBGShiftAnimation.StartShiftAnimation();
		_LeftDownBGShiftAnimation.StartShiftAnimation();
				
			
		_MainBIndicatorFragment.EnableVirtualKey();
		
		
	}
	public void showRightDowntoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainBCenterFragment);
		RightDownChangeAnimation.StartChangeAnimation(_MainBRightDownTMFragment);
		
		
		_RightUpAppearAnimation.StartAnimation();
		_LeftUpAppearAnimation.StartAnimation();
		_LeftDownAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightUpBGAppearAnimation.StartAnimation();
		_LeftUpBGAppearAnimation.StartAnimation();
		_LeftDownBGAppearAnimation.StartAnimation();
		_VirtualKeyAppearAnimation.StartAnimation();
		
		_RightUpShiftAnimation.StartShiftAnimation();
		_LeftUpShiftAnimation.StartShiftAnimation();
		_LeftDownShiftAnimation.StartShiftAnimation();
		
		_RightUpBGShiftAnimation.StartShiftAnimation();
		_LeftUpBGShiftAnimation.StartShiftAnimation();
		_LeftDownBGShiftAnimation.StartShiftAnimation();
				
			
		_MainBIndicatorFragment.EnableVirtualKey();
		
		
	}
	
	public void showLeftDowntoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainBCenterFragment);
		LeftDownChangeAnimation.StartChangeAnimation(_MainBLeftDownHourOdoFragment);
		
		
		_RightUpAppearAnimation.StartAnimation();
		_LeftUpAppearAnimation.StartAnimation();
		_RightDownAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightUpBGAppearAnimation.StartAnimation();
		_LeftUpBGAppearAnimation.StartAnimation();
		_RightDownBGAppearAnimation.StartAnimation();
		
		_VirtualKeyAppearAnimation.StartAnimation();
		
		_RightUpShiftAnimation.StartShiftAnimation();
		_LeftUpShiftAnimation.StartShiftAnimation();
		_RightDownShiftAnimation.StartShiftAnimation();
		
		_RightUpBGShiftAnimation.StartShiftAnimation();
		_LeftUpBGShiftAnimation.StartShiftAnimation();
		_RightDownBGShiftAnimation.StartShiftAnimation();
				
			
		_MainBIndicatorFragment.EnableVirtualKey();
		
		
	}
	public void showLeftUptoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		
		CenterAnimation.StartChangeAnimation(_MainBCenterFragment);
		LeftUpChangeAnimation.StartChangeAnimation(_MainBLeftUpMachineStatusFragment);
		
		
		_RightUpAppearAnimation.StartAnimation();
		_LeftDownAppearAnimation.StartAnimation();
		_RightDownAppearAnimation.StartAnimation();
		
		_CenterBGAppearAnimation.StartAnimation();
		_RightUpBGAppearAnimation.StartAnimation();
		_LeftDownBGAppearAnimation.StartAnimation();
		_RightDownBGAppearAnimation.StartAnimation();
		
		_VirtualKeyAppearAnimation.StartAnimation();
		
		_RightUpShiftAnimation.StartShiftAnimation();
		_LeftDownShiftAnimation.StartShiftAnimation();
		_RightDownShiftAnimation.StartShiftAnimation();
		
		_RightUpBGShiftAnimation.StartShiftAnimation();
		_LeftDownBGShiftAnimation.StartShiftAnimation();
		_RightDownBGShiftAnimation.StartShiftAnimation();
				
			
		_MainBIndicatorFragment.EnableVirtualKey();
		
		
	}
	
	public void showKeytoDefaultScreenAnimation(){
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		_MainBodyShiftAnimation.StartShiftZeroAnimation();
		
		_BodyAppearAnimation.StartAnimation();
		_KeyDisappearAnimation.StartAnimation();
		
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
		_RightUpBGShiftAnimation.StartShiftAnimation();
		_RightDownBGShiftAnimation.StartShiftAnimation();
		_LeftUpBGShiftAnimation.StartShiftAnimation();
		_LeftDownBGShiftAnimation.StartShiftAnimation();
		
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
		_RightUpBGShiftAnimation.StartShiftAnimation();
		_RightDownBGShiftAnimation.StartShiftAnimation();
		_LeftUpBGShiftAnimation.StartShiftAnimation();
		_LeftDownBGShiftAnimation.StartShiftAnimation();
		
		KeyTitleChangeAnimation.StartDisappearAnimation();
		KeyBodyChangeAnimation.StartDisappearAnimation();
		
		_MainBIndicatorFragment.EnableVirtualKey();
	}
	
	public void showKeyScreenAnimation(){
		
		if((ParentActivity.ScreenIndex < ParentActivity.SCREEN_STATE_MAIN_B_KEY_TOP) || (ParentActivity.ScreenIndex > ParentActivity.SCREEN_STATE_MAIN_B_KEY_END)){
			_BodyDisappearAnimation.StartAnimation();
			_KeyAppearAnimation.StartAnimation();
			_KeyTitleShiftAnimation.StartShiftAnimation();
			_KeyBodyShiftAnimation.StartShiftAnimation();
			_KeyTitleBGAppearAnimation.StartAnimation();
			_KeyBodyBGAppearAnimation.StartAnimation();
			_KeyTitleBGShiftAnimation.StartShiftAnimation();
			_KeyBodyBGShiftAnimation.StartShiftAnimation();
			VirtualKeyChangeAnimation.StartDisappearAnimation();
			
			_MainBIndicatorFragment.DisableVirtualKey();
		}else{

		}
		
		
		
		Log.d(TAG,"showKeyScreenAnimation");
		
	}
	public void showMainLightAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_MAINLIGHT;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyMainLightFragment = new MainBKeyMainLightFragment();
		
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_mainlight));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Main_Light));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyMainLightFragment);
	}
	public void showWorkLightAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLIGHT;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyWorkLightFragment = new MainBKeyWorkLightFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_worklight));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Light));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLightFragment);
	}
	public void showAutoGreaseAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_AUTOGREASE;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyAutoGreaseFragment = new MainBKeyAutoGreaseFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_auto_grease));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Auto_Grease));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyAutoGreaseFragment);
	}
	public void showQuickCouplerAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyQuickCouplerFragment = new MainBKeyQuickCouplerFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_quick));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Quick_Coupler));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyQuickCouplerFragment);
	}
	public void showRideControlAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyRideControlFragment = new MainBKeyRideControlFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_ridecontrol));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Ride_Control));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyRideControlFragment);
	}
	public void showWorkLoadAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyWorkLoadFragment = new MainBKeyWorkLoadFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadFragment);
	}
	public void showBeaconLampAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_BEACONLAMP;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyBeaconLampFragment = new MainBKeyBeaconLampFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_beacon));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Beacon_Lamp));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyBeaconLampFragment);
	}
	public void showRearWiperAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_REARWIPER;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyRearWiperFragment = new MainBKeyRearWiperFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_rearwiper));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Rear_Wiper));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyRearWiperFragment);
	}
	public void showMirrorHeatAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_MIRRORHEAT;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyMirrorHeatFragment = new MainBKeyMirrorHeatFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_mirror));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Mirror_Heat));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyMirrorHeatFragment);
	}
	public void showDetentAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_DETENT;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyDetentFragment = new MainBKeyDetentFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_autoposition));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Detent_Setting));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyDetentFragment);
	}
	public void showFineModulationAnimation(){
		ParentActivity.StartAnimationRunningTimer();
		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_FINEMODULATION;
		_MainBKeyTitleFragment = new MainBKeyTitleFragment();
		_MainBKeyFineModulationFragment = new MainBKeyFineModulationFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_fine));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Fine_Modulation));
		KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyFineModulationFragment);
	}
	public void showWorkLoadAccumulationAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ACCUMULATION;
		_MainBKeyWorkLoadAccumulationFragment = new MainBKeyWorkLoadAccumulationFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadAccumulationFragment);
	}
	public void showWorkLoadDisplayAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_DISPLAY;
		_MainBKeyWorkLoadDisplayFragment = new MainBKeyWorkLoadDisplayFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadDisplayFragment);
	}
	public void showWorkLoadErrorDetectionAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ERRORDETECT;
		_MainBKeyWorkLoadErrorDetectionFragment = new MainBKeyWorkLoadErrorDetectionFragment();
		_MainBKeyTitleFragment.setTitleIcon(ParentActivity.getResources().getDrawable(R.drawable.main_key_title_workmode));
		_MainBKeyTitleFragment.setTitleText(ParentActivity.getResources().getString(string.Work_Load));
	//	KeyTitleChangeAnimation.StartChangeAnimation(_MainBKeyTitleFragment);
		KeyBodyChangeAnimation.StartChangeAnimation(_MainBKeyWorkLoadErrorDetectionFragment);
	}
	public void showRideControlSpeedAnimation(){
		ParentActivity.StartAnimationRunningTimer();
//		showKeyScreenAnimation();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED;
		_MainBKeyRideControlSpeedFragment = new MainBKeyRideControlSpeedFragment();
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
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_MAINLIGHT)
						showMainLightAnimation();
					else
						_MainBKeyMainLightFragment.ClickHardKey();
					break;
				case CAN1CommManager.WORKLIGHT:
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLIGHT)
						showWorkLightAnimation();
					else
						_MainBKeyWorkLightFragment.ClickHardKey();
					break;
				case CAN1CommManager.AUTO_GREASE:
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_AUTOGREASE)
						showAutoGreaseAnimation();
					else
						_MainBKeyAutoGreaseFragment.ClickHardKey();
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
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_BEACONLAMP)
						showBeaconLampAnimation();
					else
						_MainBKeyBeaconLampFragment.ClickHardKey();
					break;
				case CAN1CommManager.REAR_WIPER:
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_REARWIPER)
						showRearWiperAnimation();
					else
						_MainBKeyRearWiperFragment.ClickHardKey();
					break;
				case CAN1CommManager.MIRROR_HEAT:
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_MIRRORHEAT)
						showMirrorHeatAnimation();
					else
						_MainBKeyMirrorHeatFragment.ClickHardKey();
					break;
				case CAN1CommManager.AUTOPOSITION:
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					showDetentAnimation();
					break;
				case CAN1CommManager.FINEMODULATION:
					if(ParentActivity.AnimationRunningFlag == true)
						return;
					if(ParentActivity.ScreenIndex != ParentActivity.SCREEN_STATE_MAIN_B_KEY_FINEMODULATION)
						showFineModulationAnimation();
					else
						_MainBKeyFineModulationFragment.ClickHardKey();
					break;
				default:
					break;
				}
				
			}
		});
	}
}
