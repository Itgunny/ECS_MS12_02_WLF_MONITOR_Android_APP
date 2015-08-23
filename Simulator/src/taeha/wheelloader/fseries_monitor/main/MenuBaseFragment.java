package taeha.wheelloader.fseries_monitor.main;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuInterTitleFragment;
import taeha.wheelloader.fseries_monitor.menu.MenuListLeftFragment;
import taeha.wheelloader.fseries_monitor.menu.MenuListTitleFragment;
import taeha.wheelloader.fseries_monitor.menu.UserSwitching;
import taeha.wheelloader.fseries_monitor.menu.management.ChangeASPhoneNumberFragment;
import taeha.wheelloader.fseries_monitor.menu.management.EHCUIOInfoBoomLeverFloatFragment;
import taeha.wheelloader.fseries_monitor.menu.management.EHCUIOInfoFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityESLFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityListFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityPasswordChangeFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityPasswordFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecuritySmartKeyFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceChangeCycleFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceDetailFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MenuManagementFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuListFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuPasswordFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSensorMonitoringFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSensorMonitoringHiddenFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSpeedLimitFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuWeighingCompensationFragment;
import taeha.wheelloader.fseries_monitor.menu.management.SoftwareUpdatePasswordFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.AngleCalibration;
import taeha.wheelloader.fseries_monitor.menu.mode.CalibrationFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.CoolingFanReverseModeFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.DetentFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineAutoShutdownFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineAutoShutdownPWFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineDelayShutdownFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineSpeedFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.MaxFlowFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.MenuModeFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.PressureCalibration;
import taeha.wheelloader.fseries_monitor.menu.mode.SoftStopFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.SpeedometerFreqFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.WiperFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.WorkLoadFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FaultHistoryActiveFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FaultHistoryFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FaultHistoryLoggedFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FaultHistoryLoggedPasswordFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryDailyRecordFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryGeneralRecordFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryHourlyRecordFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryMenuFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryModeRecordFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.MachineMonitoringFragment1;
import taeha.wheelloader.fseries_monitor.menu.monitoring.MachineMonitoringFragment2;
import taeha.wheelloader.fseries_monitor.menu.monitoring.MachineMonitoringFragment3;
import taeha.wheelloader.fseries_monitor.menu.monitoring.MenuMonitoringFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.OperationHistoryFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoBKCUFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoClusterFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoECMFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoEHCUFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoMCUFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoMonitorFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoRMCUFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.VersionInfoTCUFragment;
import taeha.wheelloader.fseries_monitor.menu.multimedia.MenuMultimediaFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.BrightnessFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.CameraSettingFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.ClockFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.DisplayTypeFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.DisplayTypeListFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.LanguageListFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.MenuPreferenceFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.SoundOutputSettingFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.UnitFragment;
import android.R.integer;
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

public class MenuBaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	AbsoluteLayout LayoutInter;
	AbsoluteLayout LayoutList;

	FrameLayout framelayoutInterTitle;
	FrameLayout framelayoutInterBody;
	FrameLayout framelayoutListTitle;
	FrameLayout framelayoutListBody;
	FrameLayout framelayoutListLeft;

	ImageView imgViewtitleBG;
	//////////////////////////////////////////////////

	//VALUABLE////////////////////////////////////////
	public int FirstScreenIndex;
	//////////////////////////////////////////////////

	//Fragment////////////////////////////////////////
	public MenuListLeftFragment 	_MenuListLeftFragment;
	public MenuListTitleFragment 	_MenuListTitleFragment;
	public MenuInterTitleFragment 	_MenuInterTitleFragment;

	public MenuModeFragment			_MenuModeFragment;
	public MenuMonitoringFragment 	_MenuMonitoringFragment;
	public MenuManagementFragment 	_MenuManagementFragment;
	public MenuPreferenceFragment 	_MenuPreferenceFragment;
	public MenuMultimediaFragment 	_MenuMultimediaFragment;

	//Mode
	public EngineSpeedFragment 				_EngineSpeedFragment;
	public WorkLoadFragment 				_WorkLoadFragment;
	public DetentFragment					_DetentFragment;
	public MaxFlowFragment 					_MaxFlowFragment;
	public SoftStopFragment					_SoftStopFragment;
	public EngineAutoShutdownFragment		_EngineAutoShutdownFragment;
	public EngineDelayShutdownFragment		_EngineDelayShutdownFragment;
	public CameraSettingFragment 			_CameraSettingFragment;
	public CoolingFanReverseModeFragment	_CoolingFanReverseModeFragment;
	public SpeedometerFreqFragment			_SpeedometerFreqFragment;
	public WiperFragment					_WiperFragment;
	public EngineAutoShutdownPWFragment		_EngineAutoShutdownPWFragment;
	//Monitoring
	public MachineMonitoringFragment1			_MachineMonitoringFragment1;
	public MachineMonitoringFragment2			_MachineMonitoringFragment2;
	public MachineMonitoringFragment3			_MachineMonitoringFragment3;
	public OperationHistoryFragment				_OperationHistoryFragment;
	public FaultHistoryFragment					_FaultHistoryFragment;
	public EHCUIOInfoFragment					_EHCUIOInfoFragment;
	public EHCUIOInfoBoomLeverFloatFragment		_EHCUIOInfoBoomLeverFloatFragment;
	public VersionInfoFragment					_VersionInfoFragment;
	public VersionInfoMonitorFragment			_VersionInfoMonitorFragment;
	public VersionInfoMCUFragment				_VersionInfoMCUFragment;
	public VersionInfoClusterFragment			_VersionInfoClusterFragment;
	public VersionInfoEHCUFragment				_VersionInfoEHCUFragment;
	public VersionInfoBKCUFragment				_VersionInfoBKCUFragment;
	public VersionInfoRMCUFragment				_VersionInfoRMCUFragment;
	public VersionInfoTCUFragment				_VersionInfoTCUFragment;
	public VersionInfoECMFragment				_VersionInfoECMFragment;
	public FaultHistoryActiveFragment			_FaultHistoryActiveFragment;
	public FaultHistoryLoggedFragment			_FaultHistoryLoggedFragment;
	public FaultHistoryLoggedPasswordFragment	_FaultHistoryLoggedPasswordFragment;
	// ++, 150405 bwk
	public FuelHistoryMenuFragment				_FuelHistoryMenuFragment;
	public FuelHistoryGeneralRecordFragment		_FuelHistoryGeneralRecordFragment;
	public FuelHistoryHourlyRecordFragment		_FuelHistoryHourlyRecordFragment;
	public FuelHistoryDailyRecordFragment		_FuelHistoryDailyRecordFragment;
	public FuelHistoryModeRecordFragment		_FuelHistoryModeRecordFragment;
	// --, 150405 bwk

	//Management
	public MachineSecurityPasswordFragment				_MachineSecurityPasswordFragment;
	public MachineSecurityListFragment					_MachineSecurityListFragment;
	public MachineSecurityESLFragment					_MachineSecurityESLFragment;
	public MachineSecurityPasswordChangeFragment		_MachineSecurityPasswordChangeFragment;
	public MachineSecuritySmartKeyFragment				_MachineSecuritySmartKeyFragment;
	public CalibrationFragment							_CalibrationFragment;
	public AngleCalibration								_AngleCalibration;
	public PressureCalibration							_PressureCalibration;
	public ChangeASPhoneNumberFragment					_ChangeASPhoneNumberFragment;
	public ServiceMenuPasswordFragment					_ServiceMenuPasswordFragment;
	public ServiceMenuListFragment						_ServiceMenuListFragment;
	public ServiceMenuSpeedLimitFragment				_ServiceMenuSpeedLimitFragment;
	public ServiceMenuWeighingCompensationFragment		_ServiceMenuWeighingCompensationFragment;
	public ServiceMenuSensorMonitoringFragment			_ServiceMenuSensorMonitoringFragment;
	public ServiceMenuSensorMonitoringHiddenFragment	_ServiceMenuSensorMonitoringHiddenFragment;
	public MaintenanceFragment							_MaintenanceFragment;
	public MaintenanceDetailFragment					_MaintenanceDetailFragment;
	public MaintenanceChangeCycleFragment				_MaintenanceChangeCycleFragment;
	public SoftwareUpdatePasswordFragment				_SoftwareUpdatePasswordFragment;	// ++, --, 150323 bwk

	//Preference
	public BrightnessFragment						_BrightnessFragment;
	public ClockFragment							_ClockFragment;
	public UnitFragment								_UnitFragment;
	public DisplayTypeListFragment					_DisplayTypeListFragment;
	public LanguageListFragment						_LanguageListFragment;	// ++, --, 150206 bwk
	public DisplayTypeFragment						_DisplayTypeFragment;	// ++, --, 150309 bwk
	public SoundOutputSettingFragment				_SoundOutputSettingFragment;	// ++, --, 150324 bwk

	//UserSwitching
	public UserSwitching							_UserSwitching;
	//////////////////////////////////////////////////

	//ANIMATION///////////////////////////////////////
	ChangeFragmentAnimation ListLeftAnimation;
	ChangeFragmentAnimation ListTitleAnimation;
	ChangeFragmentAnimation ListBodyAnimation;
	ChangeFragmentAnimation InterTitleAnimation;
	ChangeFragmentAnimation InterBodyAnimation;
	///////////////////////////////////////////////////

	//TEST////////////////////////////////////////////

	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MenuBaseFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.screen_menu, null);
		InitResource();
		InitFragment();
		InitValuables();
		InitButtonListener();

		ClearCursurIndex();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_TOP;

		CheckFirstScreen(FirstScreenIndex);

		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		DestroyFragment();
		//	Runtime.getRuntime().gc();
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutInter = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_menu_inter);
		LayoutList = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_menu_list);

		framelayoutInterTitle = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_inter_title);
		framelayoutInterBody = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_inter_body);
		framelayoutListTitle = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_list_title);
		framelayoutListBody = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_list_body);
		framelayoutListLeft = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_list_left);

		imgViewtitleBG = (ImageView)mRoot.findViewById(R.id.imageView_menu_title_bg);
	}

	protected void InitFragment(){
		_MenuListLeftFragment = new MenuListLeftFragment();
		_MenuListTitleFragment = new MenuListTitleFragment();
		_MenuInterTitleFragment = new MenuInterTitleFragment();
		_MenuModeFragment = new MenuModeFragment();
		_MenuMonitoringFragment = new MenuMonitoringFragment();
		_MenuManagementFragment = new MenuManagementFragment();
		_MenuPreferenceFragment = new MenuPreferenceFragment();
		_MenuMultimediaFragment = new MenuMultimediaFragment();
		_EngineSpeedFragment = new EngineSpeedFragment();
		_WorkLoadFragment = new WorkLoadFragment();
		_DetentFragment = new DetentFragment();
		_MaxFlowFragment = new MaxFlowFragment();
		_SoftStopFragment = new SoftStopFragment();
		_EngineAutoShutdownFragment = new EngineAutoShutdownFragment();
		_EngineDelayShutdownFragment = new EngineDelayShutdownFragment();
		_CameraSettingFragment = new CameraSettingFragment();
		_CoolingFanReverseModeFragment = new CoolingFanReverseModeFragment();
		_SpeedometerFreqFragment = new SpeedometerFreqFragment();
		_WiperFragment = new WiperFragment();
		_EngineAutoShutdownPWFragment = new EngineAutoShutdownPWFragment();

		_MachineMonitoringFragment1 = new MachineMonitoringFragment1();
		_MachineMonitoringFragment2 = new MachineMonitoringFragment2();
		_MachineMonitoringFragment3 = new MachineMonitoringFragment3();
		_OperationHistoryFragment = new OperationHistoryFragment();
		_FaultHistoryFragment = new FaultHistoryFragment();
		_EHCUIOInfoFragment = new EHCUIOInfoFragment();
		_EHCUIOInfoBoomLeverFloatFragment = new EHCUIOInfoBoomLeverFloatFragment();
		_VersionInfoFragment = new VersionInfoFragment();
		_VersionInfoMonitorFragment = new VersionInfoMonitorFragment();
		_VersionInfoMCUFragment = new VersionInfoMCUFragment();
		_VersionInfoClusterFragment = new VersionInfoClusterFragment();
		_VersionInfoEHCUFragment = new VersionInfoEHCUFragment();
		_VersionInfoBKCUFragment = new VersionInfoBKCUFragment();
		_VersionInfoRMCUFragment = new VersionInfoRMCUFragment();
		_VersionInfoTCUFragment = new VersionInfoTCUFragment();
		_VersionInfoECMFragment = new VersionInfoECMFragment();
		_FaultHistoryActiveFragment = new FaultHistoryActiveFragment();
		_FaultHistoryLoggedFragment = new FaultHistoryLoggedFragment();
		_FaultHistoryLoggedPasswordFragment = new FaultHistoryLoggedPasswordFragment();
		_FuelHistoryMenuFragment = new FuelHistoryMenuFragment();
		_FuelHistoryGeneralRecordFragment = new FuelHistoryGeneralRecordFragment();
		_FuelHistoryHourlyRecordFragment = new FuelHistoryHourlyRecordFragment();
		_FuelHistoryDailyRecordFragment = new FuelHistoryDailyRecordFragment();
		_FuelHistoryModeRecordFragment = new FuelHistoryModeRecordFragment();
		_MachineSecurityPasswordFragment = new MachineSecurityPasswordFragment();
		_MachineSecurityListFragment = new MachineSecurityListFragment();
		_MachineSecurityESLFragment = new MachineSecurityESLFragment();
		_MachineSecurityPasswordChangeFragment = new MachineSecurityPasswordChangeFragment();
		_MachineSecuritySmartKeyFragment = new MachineSecuritySmartKeyFragment();
		_CalibrationFragment = new CalibrationFragment();
		_AngleCalibration = new AngleCalibration();
		_PressureCalibration = new PressureCalibration();
		_ChangeASPhoneNumberFragment = new ChangeASPhoneNumberFragment();
		_ServiceMenuPasswordFragment = new ServiceMenuPasswordFragment();
		_ServiceMenuListFragment = new ServiceMenuListFragment();
		_ServiceMenuSpeedLimitFragment = new ServiceMenuSpeedLimitFragment();
		_ServiceMenuWeighingCompensationFragment = new ServiceMenuWeighingCompensationFragment();
		_ServiceMenuSensorMonitoringFragment = new ServiceMenuSensorMonitoringFragment();
		_ServiceMenuSensorMonitoringHiddenFragment = new ServiceMenuSensorMonitoringHiddenFragment();
		_MaintenanceFragment = new MaintenanceFragment();
		_MaintenanceDetailFragment = new MaintenanceDetailFragment();
		_MaintenanceChangeCycleFragment = new MaintenanceChangeCycleFragment();
		_SoftwareUpdatePasswordFragment = new SoftwareUpdatePasswordFragment();	// ++, --, 150323 bwk 
		_BrightnessFragment = new BrightnessFragment();
		_ClockFragment = new ClockFragment();
		_UnitFragment = new UnitFragment();
		_DisplayTypeListFragment = new DisplayTypeListFragment();
		_UserSwitching = new UserSwitching();
		_LanguageListFragment = new LanguageListFragment();	// ++, --, 150206 bwk
		_DisplayTypeFragment = new DisplayTypeFragment();	// ++, --, 150309 bwk
		_SoundOutputSettingFragment = new SoundOutputSettingFragment();	// ++, --, 150324 bwk
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		ListLeftAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutListLeft, R.id.FrameLayout_menu_list_left, null);
		ListTitleAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutListTitle, R.id.FrameLayout_menu_list_title, null);
		ListBodyAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutListBody, R.id.FrameLayout_menu_list_body, null);
		InterTitleAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutInterTitle, R.id.FrameLayout_menu_inter_title, null);
		InterBodyAnimation = new ChangeFragmentAnimation(ParentActivity, framelayoutInterBody, R.id.FrameLayout_menu_inter_body, null);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

	}

	public void DestroyFragment(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

		transaction.detach(_MenuListLeftFragment);
		transaction.detach(_MenuListTitleFragment);
		transaction.detach(_MenuInterTitleFragment);		
		transaction.detach(_MenuModeFragment);
		transaction.detach(_MenuMonitoringFragment);
		transaction.detach(_MenuManagementFragment);
		transaction.detach(_MenuPreferenceFragment);
		transaction.detach(_MenuMultimediaFragment);
		transaction.detach(_EngineSpeedFragment);
		transaction.detach(_WorkLoadFragment);
		transaction.detach(_DetentFragment);
		transaction.detach(_MaxFlowFragment);
		transaction.detach(_SoftStopFragment);		
		transaction.detach(_EngineAutoShutdownFragment);
		transaction.detach(_EngineDelayShutdownFragment);
		transaction.detach(_CameraSettingFragment);
		transaction.detach(_CoolingFanReverseModeFragment);
		transaction.detach(_SpeedometerFreqFragment);
		transaction.detach(_WiperFragment);
		transaction.detach(_EngineAutoShutdownPWFragment);
		transaction.detach(_MachineMonitoringFragment1);
		transaction.detach(_MachineMonitoringFragment2);
		transaction.detach(_MachineMonitoringFragment3);
		transaction.detach(_OperationHistoryFragment);
		transaction.detach(_FaultHistoryFragment);
		transaction.detach(_EHCUIOInfoFragment);	
		transaction.detach(_EHCUIOInfoBoomLeverFloatFragment);	
		transaction.detach(_VersionInfoFragment);
		transaction.detach(_VersionInfoMonitorFragment);
		transaction.detach(_VersionInfoMCUFragment);
		transaction.detach(_VersionInfoClusterFragment);
		transaction.detach(_VersionInfoEHCUFragment);
		transaction.detach(_VersionInfoBKCUFragment);
		transaction.detach(_VersionInfoRMCUFragment);
		transaction.detach(_VersionInfoTCUFragment);
		transaction.detach(_VersionInfoECMFragment);
		transaction.detach(_FaultHistoryActiveFragment);
		transaction.detach(_FaultHistoryLoggedFragment);
		transaction.detach(_FaultHistoryLoggedPasswordFragment);
		transaction.detach(_FuelHistoryMenuFragment);
		transaction.detach(_FuelHistoryGeneralRecordFragment);
		transaction.detach(_FuelHistoryHourlyRecordFragment);
		transaction.detach(_FuelHistoryDailyRecordFragment);
		transaction.detach(_FuelHistoryModeRecordFragment);
		transaction.detach(_MachineSecurityPasswordFragment);
		transaction.detach(_MachineSecurityListFragment);
		transaction.detach(_MachineSecurityESLFragment);
		transaction.detach(_MachineSecurityPasswordChangeFragment);
		transaction.detach(_MachineSecuritySmartKeyFragment);
		transaction.detach(_CalibrationFragment);
		transaction.detach(_AngleCalibration);
		transaction.detach(_PressureCalibration);
		transaction.detach(_ChangeASPhoneNumberFragment);
		transaction.detach(_ServiceMenuPasswordFragment);
		transaction.detach(_ServiceMenuListFragment);
		transaction.detach(_ServiceMenuSpeedLimitFragment);
		transaction.detach(_ServiceMenuWeighingCompensationFragment);
		transaction.detach(_ServiceMenuSensorMonitoringFragment);
		transaction.detach(_ServiceMenuSensorMonitoringHiddenFragment);
		transaction.detach(_MaintenanceFragment);
		transaction.detach(_MaintenanceDetailFragment);
		transaction.detach(_MaintenanceChangeCycleFragment);
		transaction.detach(_SoftwareUpdatePasswordFragment);	// ++, 150323 bwk
		transaction.detach(_BrightnessFragment);
		transaction.detach(_ClockFragment);
		transaction.detach(_UnitFragment);
		transaction.detach(_DisplayTypeListFragment);
		transaction.detach(_UserSwitching);
		transaction.detach(_LanguageListFragment);	// ++, --, 150206 bwk
		transaction.detach(_DisplayTypeFragment);	// ++, --, 150309 bwk
		transaction.detach(_SoundOutputSettingFragment);	// ++, --, 150324 bwk

		transaction.commit();	

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
	public void setFirstScreenIndex(int Index){
		FirstScreenIndex = Index;
	}
	public void CheckFirstScreen(int Index){
		if(Index == Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyClockAnimation();
			setFirstScreenIndex(0);
		}else if(Index == Home.SCREEN_STATE_MENU_USERSWITCHING_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyUserSwitchingAnimation();
			setFirstScreenIndex(0);
		}else if(Index == Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyPressureCalibrationAnimation();
			setFirstScreenIndex(0);
		}else if(Index == Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyWiperAnimation();
			setFirstScreenIndex(0);
		}
		else if(Index == Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyActiveFaultAnimation();
			setFirstScreenIndex(0);
		}
		else if(Index == Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyMaintenanceAnimation();
			setFirstScreenIndex(0);
		}
		// ++, 150210 bwk
		else if(Index == Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyLanguageAnimation();
			setFirstScreenIndex(0);
		}
		// --, 150210 bwk
		else{
			ListLeftAnimation.StartAppearAnimation(_MenuListLeftFragment);
			ListTitleAnimation.StartAppearAnimation(_MenuListTitleFragment);
			ListBodyAnimation.StartAppearAnimation(_MenuModeFragment);
			_MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP);
		}
	}
	/////////////////////////////////////////////////////////////////////
	//Show Fragment//////////////////////////////////////////////////////
	public void showListLeft(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuListLeftFragment);
		transaction.replace(R.id.FrameLayout_menu_list_left, _MenuListLeftFragment);
		transaction.commit();
	}
	public void showListTitle(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuListTitleFragment);
		transaction.replace(R.id.FrameLayout_menu_list_title, _MenuListTitleFragment);
		transaction.commit();
	}
	public void showInterTitle(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuInterTitleFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_title, _MenuInterTitleFragment);
		transaction.commit();
	}

	// List
	public void showBodyMode(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuModeFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MenuModeFragment);
		transaction.commit();
	}
	public void showBodyMonitoring(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuMonitoringFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MenuMonitoringFragment);
		transaction.commit();
	}
	public void showBodyManagement(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuManagementFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MenuManagementFragment);
		transaction.commit();
	}
	public void showBodyPreference(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuPreferenceFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MenuPreferenceFragment);
		transaction.commit();
	}
	public void showBodyMultimedia(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuMultimediaFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MenuMultimediaFragment);
		transaction.commit();
	}
	public void showBodyFaultHistory(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FaultHistoryFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _FaultHistoryFragment);
		transaction.commit();
	}

	public void showBodyMachineSecurityPassword(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineSecurityPasswordFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MachineSecurityPasswordFragment);
		transaction.commit();
	}
	public void showBodyMachineSecurityList(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineSecurityListFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MachineSecurityListFragment);
		transaction.commit();
	}
	public void showBodyMachineSecurityPWChange(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineSecurityPasswordChangeFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _MachineSecurityPasswordChangeFragment);
		transaction.commit();
	}
	public void showBodyCalibration(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_CalibrationFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _CalibrationFragment);
		transaction.commit();
	}
	public void showBodyServiceMenuPassword(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuPasswordFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _ServiceMenuPasswordFragment);
		transaction.commit();
	}
	// ++, 150323 bwk
	public void showBodySoftwareUpdatePassword(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_SoftwareUpdatePasswordFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _SoftwareUpdatePasswordFragment);
		transaction.commit();
	}
	// --, 150323 bwk
	public void showBodyServiceMenuList(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuListFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _ServiceMenuListFragment);
		transaction.commit();
	}
	public void showBodyDisplayTypeLangList(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_DisplayTypeListFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _DisplayTypeListFragment);
		transaction.commit();
	}
	public void showBodyFuelHistoryList(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FuelHistoryMenuFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _FuelHistoryMenuFragment);
		transaction.commit();
	}

	// Inter
	public void showBodyEngineSpeed(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_EngineSpeedFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _EngineSpeedFragment);
		transaction.commit();

	}
	public void showBodyWorkLoad(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_WorkLoadFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _WorkLoadFragment);
		transaction.commit();

	}
	public void showBodyDetent(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_DetentFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _DetentFragment);
		transaction.commit();

	}
	public void showBodyMaxFlow(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MaxFlowFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MaxFlowFragment);
		transaction.commit();

	}
	public void showBodySoftStop(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MaxFlowFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _SoftStopFragment);
		transaction.commit();

	}	
	public void showBodyEngineAutoShutdown(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_EngineAutoShutdownFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _EngineAutoShutdownFragment);
		transaction.commit();

	}
	public void showBodyEngineDelayShutdown(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_EngineDelayShutdownFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _EngineDelayShutdownFragment);
		transaction.commit();

	}
	public void showBodyCameraSetting(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_CameraSettingFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _CameraSettingFragment);
		transaction.commit();

	}
	public void showBodyCoolingFanReverse(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_CoolingFanReverseModeFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _CoolingFanReverseModeFragment);
		transaction.commit();

	}
	public void showBodySpeedometerFreq(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_SpeedometerFreqFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _SpeedometerFreqFragment);
		transaction.commit();

	}
	public void showBodyWiper(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_SpeedometerFreqFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _WiperFragment);
		transaction.commit();

	}
	public void showBodyEngineAutoShutdownPW(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_EngineAutoShutdownPWFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _EngineAutoShutdownPWFragment);
		transaction.commit();

	}

	public void showBodyMachineMonitoring1(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineMonitoringFragment1);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MachineMonitoringFragment1);
		transaction.commit();

	}
	public void showBodyMachineMonitoring2(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineMonitoringFragment2);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MachineMonitoringFragment2);
		transaction.commit();

	}
	public void showBodyMachineMonitoring3(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineMonitoringFragment3);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MachineMonitoringFragment3);
		transaction.commit();

	}
	public void showBodyOperationHistory(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_OperationHistoryFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _OperationHistoryFragment);
		transaction.commit();

	}

	public void showBodyEHCUIOInfo(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_EHCUIOInfoFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _EHCUIOInfoFragment);
		transaction.commit();

	}	
	public void showBodyBoomLeverFloatInfo(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_EHCUIOInfoBoomLeverFloatFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _EHCUIOInfoBoomLeverFloatFragment);
		transaction.commit();

	}	
	public void showBodyVersionInfo(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoFragment);
		transaction.commit();

	}	
	public void showBodyVersionInfoMonitor(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoMonitorFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoMonitorFragment);
		transaction.commit();

	}	
	public void showBodyVersionInfoMCU(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoMCUFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoMCUFragment);
		transaction.commit();

	}
	public void showBodyVersionInfoCluster(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoClusterFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoClusterFragment);
		transaction.commit();

	}
	public void showBodyVersionInfoEHCU(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoEHCUFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoEHCUFragment);
		transaction.commit();

	}
	public void showBodyVersionInfoBKCU(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoBKCUFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoBKCUFragment);
		transaction.commit();

	}
	public void showBodyVersionInfoRMCU(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoRMCUFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoRMCUFragment);
		transaction.commit();

	}
	public void showBodyVersionInfoTCU(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoTCUFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoTCUFragment);
		transaction.commit();

	}
	public void showBodyVersionInfoECM(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_VersionInfoECMFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _VersionInfoECMFragment);
		transaction.commit();

	}
	public void showBodyActiveFault(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FaultHistoryActiveFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FaultHistoryActiveFragment);
		transaction.commit();

	}
	public void showBodyLoggedFault(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FaultHistoryLoggedFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FaultHistoryLoggedFragment);
		transaction.commit();

	}	
	public void showBodyLoggedFaultPassword(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FaultHistoryLoggedPasswordFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FaultHistoryLoggedPasswordFragment);
		transaction.commit();
	}
	public void showBodyESL(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineSecurityESLFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MachineSecurityESLFragment);
		transaction.commit();

	}
	public void showBodySmartKey(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineSecuritySmartKeyFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MachineSecuritySmartKeyFragment);
		transaction.commit();

	}
	public void showBodyAngleCalibration(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_AngleCalibration);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _AngleCalibration);
		transaction.commit();

	}
	public void showBodyPressureCalibration(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_PressureCalibration);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _PressureCalibration);
		transaction.commit();

	}
	public void showBodyChangeASPhoneNumber(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ChangeASPhoneNumberFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _ChangeASPhoneNumberFragment);
		transaction.commit();

	}
	public void showBodySpeedLimit(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuSpeedLimitFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _ServiceMenuSpeedLimitFragment);
		transaction.commit();

	}
	public void showBodyWeighingCompensation(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuWeighingCompensationFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _ServiceMenuWeighingCompensationFragment);
		transaction.commit();

	}
	public void showBodySensorMonitoring(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuWeighingCompensationFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _ServiceMenuSensorMonitoringFragment);
		transaction.commit();

	}
	public void showBodySensorMonitoringHidden(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuWeighingCompensationFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _ServiceMenuSensorMonitoringHiddenFragment);
		transaction.commit();

	}
	public void showBodyMaintenance(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MaintenanceFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MaintenanceFragment);
		transaction.commit();

	}
	public void showBodyMaintenanceDetail(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MaintenanceDetailFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MaintenanceDetailFragment);
		transaction.commit();

	}
	public void showBodyMaintenanceChangeCycle(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MaintenanceChangeCycleFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MaintenanceChangeCycleFragment);
		transaction.commit();

	}
	public void showBodyBrightness(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_BrightnessFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _BrightnessFragment);
		transaction.commit();

	}
	public void showBodyClock(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ClockFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _ClockFragment);
		transaction.commit();

	}
	public void showBodyUnit(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_UnitFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _UnitFragment);
		transaction.commit();

	}
	public void showUserSwitching(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_UserSwitching);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _UserSwitching);
		transaction.commit();

	}
	public void showBodyFuelHistoryGeneralRecord(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FuelHistoryGeneralRecordFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FuelHistoryGeneralRecordFragment);
		transaction.commit();

	}
	public void showBodyFuelHistoryHourlyRecord(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FuelHistoryHourlyRecordFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FuelHistoryHourlyRecordFragment);
		transaction.commit();

	}
	public void showBodyFuelHistoryDailyRecord(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FuelHistoryDailyRecordFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FuelHistoryDailyRecordFragment);
		transaction.commit();

	}
	public void showBodyFuelHistoryModeRecord(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_FuelHistoryModeRecordFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _FuelHistoryModeRecordFragment);
		transaction.commit();

	}

	/////////////////////////////////////////////////////////////////////
	//Show Fragment Animation////////////////////////////////////////////
	public void showListAnimation(){
		InterTitleAnimation.StartDisappearAnimation();
		InterBodyAnimation.StartDisappearAnimation();

		ListLeftAnimation.StartChangeAnimation(_MenuListLeftFragment);
		ListTitleAnimation.StartChangeAnimation(_MenuListTitleFragment);
	}
	public void showInterAnimation(){
		ListLeftAnimation.StartDisappearAnimation();
		ListTitleAnimation.StartDisappearAnimation();
		ListBodyAnimation.StartDisappearAnimation();

		InterTitleAnimation.StartChangeAnimation(_MenuInterTitleFragment);
	}
	// List
	public void showBodyModeAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_MenuModeFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_TOP);
	}
	public void showBodyMonitoringAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_MenuMonitoringFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
	}
	public void showBodyManagementAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_MenuManagementFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
	}
	public void showBodyPreferenceAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_MenuPreferenceFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
	}
	public void showBodyMultimediaAnimation(){

	}
	public void showFaultHistoryAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_FaultHistoryFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
	}
	public void showMachineSecurityListAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_MachineSecurityListFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
	}
	// ++, 150409 cjg
//	public void showCalibrationAnimation(){
//		DestroyFragment();
//		InitFragment();
//		showListAnimation();
//		ListBodyAnimation.StartChangeAnimation(_CalibrationFragment);
//		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
//	}
	// --, 150409 cjg
	public void showServiceMenuListAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_ServiceMenuListFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
	}
	public void showDisplayTypeLangAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_DisplayTypeListFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
	}
	public void showFuelHistoryMenuListAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_FuelHistoryMenuFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
	}

	// Inter
	public void showBodyEngineSpeedAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_EngineSpeedFragment);
	}
	public void showBodyWorkLoadAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_WorkLoadFragment);
	}
	public void showBodyDetentAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_DetentFragment);
	}
	public void showBodyMaxFlowAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MaxFlowFragment);
	}
	public void showBodySoftStopAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_SoftStopFragment);
	}
	public void showBodyEngineAutoShutdownAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_EngineAutoShutdownFragment);
	}
	public void showBodyEngineDelayShutdownAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_EngineDelayShutdownFragment);
	}
	public void showBodyCameraSettingAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_CameraSettingFragment);
	}
	public void showBodyCoolingFanReverseModeAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_CoolingFanReverseModeFragment);
	}
	public void showBodySpeedometerFreqAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_SpeedometerFreqFragment);
	}
	public void showBodyWiperAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_WiperFragment);
	}
	public void showBodyEngineAutoShutdownPWAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_EngineAutoShutdownPWFragment);
	}
	public void showBodyMachineMonitoringAnimation1(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MachineMonitoringFragment1);
	}
	public void showBodyMachineMonitoringAnimation2(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MachineMonitoringFragment2);
	}
	public void showBodyMachineMonitoringAnimation3(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MachineMonitoringFragment3);
	}
	public void showBodyOperationHistoryAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_OperationHistoryFragment);
	}
	public void showBodyEHCUIOInfoAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_EHCUIOInfoFragment);
	}
	public void showBodyBoomLeverFloatAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_EHCUIOInfoBoomLeverFloatFragment);
	}
	public void showBodyVersionInfoAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoFragment);
	}
	public void showBodyVersionInfoMonitorAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoMonitorFragment);
	}
	public void showBodyVersionInfoMCUAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoMCUFragment);
	}
	public void showBodyVersionInfoClusterAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoClusterFragment);
	}
	public void showBodyVersionInfoEHCUAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoEHCUFragment);
	}
	public void showBodyVersionInfoBKCUAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoBKCUFragment);
	}
	public void showBodyVersionInfoRMCUAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoRMCUFragment);
	}
	public void showBodyVersionInfoTCUAniamtion(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoTCUFragment);
	}
	public void showBodyVersionInfoECMAniamtion(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoECMFragment);
	}
	public void showBodyActiveFaultAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FaultHistoryActiveFragment);
	}
	public void showBodyLoggedFaultAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FaultHistoryLoggedFragment);
	}
	public void showLoggedFaultPasswordAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FaultHistoryLoggedPasswordFragment);
	}
	public void showBodyESLAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MachineSecurityESLFragment);
	}
	public void showBodySmartKeyAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MachineSecuritySmartKeyFragment);
	}
	public void showBodyAngleCalibrationAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_AngleCalibration);
	}
	public void showBodyPressureCalibrationAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_PressureCalibration);
	}
	public void showBodyChangeASPhoneNumberAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_ChangeASPhoneNumberFragment);
	}
	public void showBodySpeedLimitAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_ServiceMenuSpeedLimitFragment);
	}
	public void showBodyWeighingCompensationAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_ServiceMenuWeighingCompensationFragment);
	}
	public void showBodySensorMonitoringAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_ServiceMenuSensorMonitoringFragment);
	}
	public void showBodySensorMonitoringHiddenAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_ServiceMenuSensorMonitoringHiddenFragment);
	}
	public void showBodyMaintenanceAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MaintenanceFragment);
	}
	public void showBodyMaintenanceDetailAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MaintenanceDetailFragment);
	}
	public void showBodyMaintenanceChangeCycleAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MaintenanceChangeCycleFragment);
	}
	public void showBodyBrightnessAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_BrightnessFragment);
	}
	public void showBodyClockAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_ClockFragment);
	}
	public void showBodyUnitAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_UnitFragment);
	}
	public void showBodyUserSwitchingAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_UserSwitching);
	}
	// ++, 150206 bwk
	public void showBodyLanguageAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_LanguageListFragment);
	}
	// --, 150206 bwk
	// ++, 150309 bwk
	public void showBodyDisplayTypeAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_DisplayTypeFragment);
	}
	// --, 150309 bwk
	// ++, 150324 bwk
	public void showBodySoundOutputSettingAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_SoundOutputSettingFragment);
	}
	// --, 150324 bwk
	
	public void showBodyFuelHistoryGeneralRecordAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FuelHistoryGeneralRecordFragment);
	}
	public void showBodyFuelHistoryHourlyRecordAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FuelHistoryHourlyRecordFragment);
	}
	public void showBodyFuelHistoryDailyRecordAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FuelHistoryDailyRecordFragment);
	}
	public void showBodyFuelHistoryModeRecordAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_FuelHistoryModeRecordFragment);
	}	
	/////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////
	public void ClearCursurIndex(){
		_MenuModeFragment.CursurIndex = 1;
		_MenuModeFragment._MenuModeTabFragment.CursurIndex = 1;
		_MenuModeFragment._MenuModeEngTMFragment.CursurIndex = 1;
		_MenuModeFragment._MenuModeHYDFragment.CursurIndex = 1;
		_MenuModeFragment._MenuModeETCFragment.CursurIndex = 1;
	}
	/////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(final int key){
		Log.d(TAG,"KeyButtonClick : 0x" + Integer.toHexString(key));

		// TODO Auto-generated method stub
		switch (key) {
		case CAN1CommManager.MAINLIGHT:			// Num1
			ClickNum1();
			break;
		case CAN1CommManager.WORKLIGHT:			// Num2
			ClickNum2();
			break;
		case CAN1CommManager.AUTO_GREASE:		// Num3
			ClickNum3();
			break;
		case CAN1CommManager.QUICK_COUPLER:		// Num4
			ClickNum4();
			break;
		case CAN1CommManager.RIDE_CONTROL:		// Num5
			ClickNum5();
			break;
		case CAN1CommManager.WORK_LOAD:

			break;
		case CAN1CommManager.BEACON_LAMP:		// Num6
			ClickNum6();
			break;
		case CAN1CommManager.REAR_WIPER:		// Num7
			ClickNum7();
			break;
		case CAN1CommManager.MIRROR_HEAT:		// Num8
			ClickNum8();
			break;
		case CAN1CommManager.AUTOPOSITION:		// Num9
			ClickNum9();
			break;
		case CAN1CommManager.FINEMODULATION:	// Num0
			ClickNum0();
			break;
		case CAN1CommManager.LEFT:
			ClickKeyButtonLeft();
			break;
		case CAN1CommManager.RIGHT:
			ClickKeyButtonRight();
			break;
		case CAN1CommManager.ESC:
			ClickKeyButtonESC();
			break;
		case CAN1CommManager.ENTER:
			ClickKeyButtonEnter();
			break;
		case CAN1CommManager.LONG_LEFT_RIGHT:
			ClickKeyButtonLongLeftRight();
			break;
		case CAN1CommManager.LONG_8_9_0:
			ClickKeyButtonLong890();
			break;
		case CAN1CommManager.LONG_8_0:
			ClickKeyButtonLong80();
			break;
		case CAN1CommManager.LONG_HIDDEN_5KEY:
			ClickKeyButtonLongHiddenVersion();
			break;
		case CAN1CommManager.MENU:
			// ++, 150209 bwk
			//_MenuListTitleFragment.ClickHome();
			ClickKeyButtonHome();
			// --, 150209 bwk
			break;
		case CAN1CommManager.FN:
			Log.d(TAG,"Click FN");
			break;
		case CAN1CommManager.CAMERA:
			Log.d(TAG, "Click CAMERA");
			ParentActivity.ExcuteCamActivitybyKey();
			break;
		default:
			break;
		}
	}
	public void ClickKeyButtonLeft(){
		Log.d(TAG,"Click Left");
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP:
			_MenuModeFragment._MenuModeEngTMFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
			_MenuModeFragment._EngineSettingFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_MODE:
			ParentActivity._EngineModePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_WARMINGUP:
			ParentActivity._EngineWarmingUpPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_SPEED:
			_EngineSpeedFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN:
			ParentActivity._KickDownPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE:
			ParentActivity._CCoModePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ICCOMODE:
			ParentActivity._ICCOModePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE:
			ParentActivity._ShiftModePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP:
			ParentActivity._TCLockUpPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_TOP:
			_MenuModeFragment._MenuModeHYDFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP:
			_WorkLoadFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_INIT:
			ParentActivity._WorkLoadInitPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_DETENT:
			_DetentFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY:
			ParentActivity._BucketPriorityPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_MAXFLOW:
			_MaxFlowFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_TOP:
			_SoftStopFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_INIT:
			ParentActivity._SoftStopInitPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_TOP:
			_MenuModeFragment._MenuModeETCFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP:
			//_CalibrationFragment.ClickLeft();
			_MenuModeFragment._CalibrationFragment.ClickLeft(); 
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP:
			_AngleCalibration.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_RESULT:
			ParentActivity._AngleCalibrationResultPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP:
			_PressureCalibration.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_RESULT:
			ParentActivity._PressureCalibrationResultPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_BRAKEPEDAL_TOP:
			ParentActivity._BrakePedalCalibrationPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT:
			ParentActivity._SpeedometerInitPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP:
			_CoolingFanReverseModeFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP:
			_WiperFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP:
			_EngineAutoShutdownFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CAMERASETTING_TOP:
			_CameraSettingFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_TOP:
			_MenuMonitoringFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_1:
			_MachineMonitoringFragment1.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_2:
			_MachineMonitoringFragment2.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_3:
			_MachineMonitoringFragment3.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP:
			_OperationHistoryFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_INIT:
			ParentActivity._OperationHistoryInitPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP:
			_FaultHistoryFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP:
			_FaultHistoryActiveFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP:
			_FaultHistoryLoggedFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_DELETE:
			ParentActivity._LoggedFaultDeletePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP:
			_VersionInfoFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU:
			_VersionInfoTCUFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM:
			_VersionInfoECMFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR:
			_VersionInfoMonitorFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MCU:
			_VersionInfoMCUFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER:
			_VersionInfoClusterFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU:
			_VersionInfoRMCUFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_EHCU:
			_VersionInfoEHCUFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU:
			_VersionInfoBKCUFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_TOP:
			_FuelHistoryMenuFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD:
			_FuelHistoryGeneralRecordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD:
			_FuelHistoryHourlyRecordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD:
			_FuelHistoryDailyRecordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD:
			_FuelHistoryModeRecordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_INITIAL:
			ParentActivity._FuelInitalPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP:
			_EHCUIOInfoFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_BOOMLEVERFLOAT:
			_EHCUIOInfoBoomLeverFloatFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_TOP:
			_MenuManagementFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP:
			_MachineSecurityListFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL:
			_MachineSecurityESLFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY:
			_MachineSecuritySmartKeyFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP:
			_MaintenanceFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_TOP:
			_MaintenanceDetailFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_REPLACE:
			ParentActivity._MaintReplacePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP:
			_ServiceMenuListFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP:
			_ServiceMenuSensorMonitoringFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_POPUP:
			ParentActivity._FanSelectModePopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN:
			_ServiceMenuSensorMonitoringHiddenFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP:
			_ServiceMenuSpeedLimitFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickLeft();
			break;
			// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickLeft();	
			break;
			// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_TOP:
			_MenuPreferenceFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP:
			_BrightnessFragment._BrightnessManualFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP:
			_BrightnessFragment._BrightnessAutoFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_UNIT_TOP:
			_UnitFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP:
			_DisplayTypeListFragment.ClickLeft();
			break;
			// ++, 150309 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TYPE:
			_DisplayTypeFragment.ClickLeft();
			break;
			// --, 150309 bwk
			// ++, 150206 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE:
			_LanguageListFragment.ClickLeft();
			break;
			// --, 150206 bwk;
		case Home.SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP:
			ParentActivity._SoundOutputPopup.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
			_MenuMultimediaFragment.ClickLeft();
			break;
		case Home.SCREEN_STATE_MENU_USERSWITCHING_TOP:
			_UserSwitching.ClickLeft();
			break;

		default:
			break;
		}
	}
	public void ClickKeyButtonRight(){
		Log.d(TAG,"Click Right");
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP:
			_MenuModeFragment._MenuModeEngTMFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
			_MenuModeFragment._EngineSettingFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_MODE:
			ParentActivity._EngineModePopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_WARMINGUP:
			ParentActivity._EngineWarmingUpPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_SPEED:
			_EngineSpeedFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN:
			ParentActivity._KickDownPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE:
			ParentActivity._CCoModePopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ICCOMODE:
			ParentActivity._ICCOModePopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE:
			ParentActivity._ShiftModePopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP:
			ParentActivity._TCLockUpPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_TOP:
			_MenuModeFragment._MenuModeHYDFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP:
			_WorkLoadFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_INIT:
			ParentActivity._WorkLoadInitPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_DETENT:
			_DetentFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY:
			ParentActivity._BucketPriorityPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_MAXFLOW:
			_MaxFlowFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_TOP:
			_SoftStopFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_INIT:
			ParentActivity._SoftStopInitPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_TOP:
			_MenuModeFragment._MenuModeETCFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP:
			//_CalibrationFragment.ClickRight();
			_MenuModeFragment._CalibrationFragment.ClickRight(); 
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP:
			_AngleCalibration.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_RESULT:
			ParentActivity._AngleCalibrationResultPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP:
			_PressureCalibration.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_RESULT:
			ParentActivity._PressureCalibrationResultPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_BRAKEPEDAL_TOP:
			ParentActivity._BrakePedalCalibrationPopup.ClickRight();
			break;
			
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT:
			ParentActivity._SpeedometerInitPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP:
			_CoolingFanReverseModeFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP:
			_WiperFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP:
			_EngineAutoShutdownFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CAMERASETTING_TOP:
			_CameraSettingFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_TOP:
			_MenuMonitoringFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_1:
			_MachineMonitoringFragment1.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_2:
			_MachineMonitoringFragment2.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_3:
			_MachineMonitoringFragment3.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP:
			_OperationHistoryFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_INIT:
			ParentActivity._OperationHistoryInitPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP:
			_FaultHistoryFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP:
			_FaultHistoryActiveFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP:
			_FaultHistoryLoggedFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_DELETE:
			ParentActivity._LoggedFaultDeletePopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP:
			_VersionInfoFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU:
			_VersionInfoTCUFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM:
			_VersionInfoECMFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR:
			_VersionInfoMonitorFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MCU:
			_VersionInfoMCUFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER:
			_VersionInfoClusterFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU:
			_VersionInfoRMCUFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_EHCU:
			_VersionInfoEHCUFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU:
			_VersionInfoBKCUFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_TOP:
			_FuelHistoryMenuFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD:
			_FuelHistoryGeneralRecordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD:
			_FuelHistoryHourlyRecordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD:
			_FuelHistoryDailyRecordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD:
			_FuelHistoryModeRecordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_INITIAL:
			ParentActivity._FuelInitalPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP:
			_EHCUIOInfoFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_BOOMLEVERFLOAT:
			_EHCUIOInfoBoomLeverFloatFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_TOP:
			_MenuManagementFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP:
			_MachineSecurityListFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL:
			_MachineSecurityESLFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY:
			_MachineSecuritySmartKeyFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP:
			_MaintenanceFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_TOP:
			_MaintenanceDetailFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_REPLACE:
			ParentActivity._MaintReplacePopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP:
			_ServiceMenuListFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP:
			_ServiceMenuSensorMonitoringFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_POPUP:
			ParentActivity._FanSelectModePopup.ClickRight();
			break;			
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN:
			_ServiceMenuSensorMonitoringHiddenFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP:
			_ServiceMenuSpeedLimitFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickRight();
			break;
			// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickRight();	
			break;
			// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_TOP:
			_MenuPreferenceFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP:
			_BrightnessFragment._BrightnessManualFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP:
			_BrightnessFragment._BrightnessAutoFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_UNIT_TOP:
			_UnitFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP:
			_DisplayTypeListFragment.ClickRight();
			break;
			// ++, 150309 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TYPE:
			_DisplayTypeFragment.ClickRight();
			break;
			// --, 150309 bwk
			// ++, 150206 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE:
			_LanguageListFragment.ClickRight();
			break;
			// --, 150206 bwk;			
		case Home.SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP:
			ParentActivity._SoundOutputPopup.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
			_MenuMultimediaFragment.ClickRight();
			break;
		case Home.SCREEN_STATE_MENU_USERSWITCHING_TOP:
			_UserSwitching.ClickRight();
			break;
		default:
			break;
		}
	}
	public void ClickKeyButtonESC(){
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP:
			_MenuModeFragment._MenuModeEngTMFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
			_MenuModeFragment._EngineSettingFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_MODE:
			ParentActivity._EngineModePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_WARMINGUP:
			ParentActivity._EngineWarmingUpPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_SPEED:
			_EngineSpeedFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN:
			ParentActivity._KickDownPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE:
			ParentActivity._CCoModePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ICCOMODE:
			ParentActivity._ICCOModePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE:
			ParentActivity._ShiftModePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP:
			ParentActivity._TCLockUpPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_TOP:
			_MenuModeFragment._MenuModeHYDFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP:
			_WorkLoadFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_INIT:
			ParentActivity._WorkLoadInitPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_DETENT:
			_DetentFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY:
			ParentActivity._BucketPriorityPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_MAXFLOW:
			_MaxFlowFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_TOP:
			_SoftStopFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_INIT:
			ParentActivity._SoftStopInitPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_TOP:
			_MenuModeFragment._MenuModeETCFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP:
			// ++, 150409 cjg
			//_CalibrationFragment.ClickESC();
			_MenuModeFragment._CalibrationFragment.ClickESC(); 
			// --, 150409 cjg
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP:
			_AngleCalibration.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_RESULT:
			ParentActivity._AngleCalibrationResultPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_POPUP:
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_POPUP:
			ParentActivity._CalibrationEHCUPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP:
			_PressureCalibration.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_RESULT:
			ParentActivity._PressureCalibrationResultPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_BRAKEPEDAL_TOP:
			ParentActivity._BrakePedalCalibrationPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT:
			ParentActivity._SpeedometerInitPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP:
			_CoolingFanReverseModeFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP:
			_WiperFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP:
			_EngineAutoShutdownFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CAMERASETTING_TOP:
			_CameraSettingFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_TOP:
			_MenuMonitoringFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_1:
			_MachineMonitoringFragment1.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_2:
			_MachineMonitoringFragment2.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_3:
			_MachineMonitoringFragment3.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP:
			_OperationHistoryFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_INIT:
			ParentActivity._OperationHistoryInitPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP:
			_FaultHistoryFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP:
			_FaultHistoryActiveFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP:
			_FaultHistoryLoggedFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_DELETE:
			ParentActivity._LoggedFaultDeletePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP:
			_VersionInfoFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU:
			_VersionInfoTCUFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM:
			_VersionInfoECMFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR:
			_VersionInfoMonitorFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MCU:
			_VersionInfoMCUFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER:
			_VersionInfoClusterFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU:
			_VersionInfoRMCUFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_EHCU:
			_VersionInfoEHCUFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU:
			_VersionInfoBKCUFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_TOP:
			_FuelHistoryMenuFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD:
			_FuelHistoryGeneralRecordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD:
			_FuelHistoryHourlyRecordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD:
			_FuelHistoryDailyRecordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD:
			_FuelHistoryModeRecordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_INITIAL:
			ParentActivity._FuelInitalPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP:
			_EHCUIOInfoFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_BOOMLEVERFLOAT:
			_EHCUIOInfoBoomLeverFloatFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_TOP:
			_MenuManagementFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP:
			_MachineSecurityListFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL:
			_MachineSecurityESLFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY:
			_MachineSecuritySmartKeyFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP:
			_MaintenanceFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_TOP:
			_MaintenanceDetailFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_REPLACE:
			ParentActivity._MaintReplacePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP:
			_ServiceMenuListFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP:
			_ServiceMenuSensorMonitoringFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_POPUP:
			ParentActivity._FanSelectModePopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN:
			_ServiceMenuSensorMonitoringHiddenFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP:
			_ServiceMenuSpeedLimitFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickESC();	
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_POPUP:
			ParentActivity._SoftwareUpdateErrorPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_TOP:
			_MenuPreferenceFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP:
			_BrightnessFragment._BrightnessManualFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP:
			_BrightnessFragment._BrightnessAutoFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_UNIT_TOP:
			_UnitFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP:
			_DisplayTypeListFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TYPE:
			_DisplayTypeFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE:
			_LanguageListFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE_POPUP:
			ParentActivity._LanguageChangePopup.ClickOK();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP:
			ParentActivity._SoundOutputPopup.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
			_MenuMultimediaFragment.ClickESC();
			break;
		case Home.SCREEN_STATE_MENU_USERSWITCHING_TOP:
			_UserSwitching.ClickESC();
			break;
		default:
			break;
		}
	}
	public void ClickKeyButtonEnter(){
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP:
			_MenuModeFragment._MenuModeEngTMFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
			_MenuModeFragment._EngineSettingFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_MODE:
			ParentActivity._EngineModePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_WARMINGUP:
			ParentActivity._EngineWarmingUpPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_SPEED:
			_EngineSpeedFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN:
			ParentActivity._KickDownPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE:
			ParentActivity._CCoModePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ICCOMODE:
			ParentActivity._ICCOModePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE:
			ParentActivity._ShiftModePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP:
			ParentActivity._TCLockUpPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_TOP:
			_MenuModeFragment._MenuModeHYDFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP:
			_WorkLoadFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_INIT:
			ParentActivity._WorkLoadInitPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_DETENT:
			_DetentFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY:
			ParentActivity._BucketPriorityPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_MAXFLOW:
			_MaxFlowFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_TOP:
			_SoftStopFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_INIT:
			ParentActivity._SoftStopInitPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_TOP:
			_MenuModeFragment._MenuModeETCFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP:
			//_CalibrationFragment.ClickEnter();
			_MenuModeFragment._CalibrationFragment.ClickEnter(); 
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP:
			_AngleCalibration.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_RESULT:
			ParentActivity._AngleCalibrationResultPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_POPUP:
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_POPUP:
			ParentActivity._CalibrationEHCUPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP:
			_PressureCalibration.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_RESULT:
			ParentActivity._PressureCalibrationResultPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_BRAKEPEDAL_TOP:
			ParentActivity._BrakePedalCalibrationPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT:
			ParentActivity._SpeedometerInitPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP:
			_CoolingFanReverseModeFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP:
			_WiperFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP:
			_EngineAutoShutdownFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CAMERASETTING_TOP:
			_CameraSettingFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_TOP:
			_MenuMonitoringFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_1:
			_MachineMonitoringFragment1.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_2:
			_MachineMonitoringFragment2.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_3:
			_MachineMonitoringFragment3.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP:
			_OperationHistoryFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_INIT:
			ParentActivity._OperationHistoryInitPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP:
			_FaultHistoryFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP:
			_FaultHistoryActiveFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP:
			_FaultHistoryLoggedFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_DELETE:
			ParentActivity._LoggedFaultDeletePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP:
			_VersionInfoFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU:
			_VersionInfoTCUFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM:
			_VersionInfoECMFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR:
			_VersionInfoMonitorFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MCU:
			_VersionInfoMCUFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER:
			_VersionInfoClusterFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU:
			_VersionInfoRMCUFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_EHCU:
			_VersionInfoEHCUFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU:
			_VersionInfoBKCUFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_TOP:
			_FuelHistoryMenuFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD:
			_FuelHistoryGeneralRecordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD:
			_FuelHistoryHourlyRecordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD:
			_FuelHistoryDailyRecordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD:
			_FuelHistoryModeRecordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_INITIAL:
			ParentActivity._FuelInitalPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP:
			_EHCUIOInfoFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_BOOMLEVERFLOAT:
			_EHCUIOInfoBoomLeverFloatFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_TOP:
			_MenuManagementFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP:
			_MachineSecurityListFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL:
			_MachineSecurityESLFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY:
			_MachineSecuritySmartKeyFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP:
			_MaintenanceFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_TOP:
			_MaintenanceDetailFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_REPLACE:
			ParentActivity._MaintReplacePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP:
			_ServiceMenuListFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP:
			_ServiceMenuSensorMonitoringFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_POPUP:
			ParentActivity._FanSelectModePopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN:
			_ServiceMenuSensorMonitoringHiddenFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP:
			_ServiceMenuSpeedLimitFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickEnter();
			break;
			// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickEnter();	
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_POPUP:
			ParentActivity._SoftwareUpdateErrorPopup.ClickEnter();
			break;
			// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_PREFERENCE_TOP:
			_MenuPreferenceFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP:
			_BrightnessFragment._BrightnessManualFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP:
			_BrightnessFragment._BrightnessAutoFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_UNIT_TOP:
			_UnitFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP:
			_DisplayTypeListFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TYPE:
			_DisplayTypeFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE:
			_LanguageListFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE_POPUP:
			ParentActivity._LanguageChangePopup.ClickOK();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP:
			ParentActivity._SoundOutputPopup.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
			_MenuMultimediaFragment.ClickEnter();
			break;
		case Home.SCREEN_STATE_MENU_USERSWITCHING_TOP:
			_UserSwitching.ClickEnter();
			break;
		default:
			break;
		}
	}
	public void ClickKeyButtonLongLeftRight(){
		switch (ParentActivity.ScreenIndex) {
	//		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
	//			_ServiceMenuPasswordFragment.showServicePasswordNextScreen();
	//			break;
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
				_SoftwareUpdatePasswordFragment.showServicePasswordNextScreen();
				break;
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP:
				showBodyBoomLeverFloatInfo();
				break;
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP:
				showBodySensorMonitoringHidden();
				break;
			default:
				break;
		}
	}

	public void ClickKeyButtonLong890(){
		switch (ParentActivity.ScreenIndex) {
			case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
				_MenuMultimediaFragment.ExcuteSettings();
				break;
			default:
				break;
		}

	}
	public void ClickKeyButtonLong80(){
		switch (ParentActivity.ScreenIndex) {
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM:
				_VersionInfoECMFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU:
				_VersionInfoTCUFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR:
				_VersionInfoMonitorFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MCU:
				_VersionInfoMCUFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER:
				_VersionInfoClusterFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU:
				_VersionInfoRMCUFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_EHCU:
				_VersionInfoEHCUFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU:
				_VersionInfoBKCUFragment.ShowManufactureDay(true);
				break;
			case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
				_MenuMultimediaFragment.ExcuteFileManaget();
				break;
			default:
				break;
		}
	}
	public void ClickKeyButtonLongHiddenVersion(){
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER:
			_VersionInfoClusterFragment.ShowMonitorHiddenVersion();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR:
			_VersionInfoMonitorFragment.ShowMonitorHiddenVersion();
			break;
		default:
			break;
	}		
	}

	// ++, 150209 bwk
	public void ClickKeyButtonHome(){
		switch (ParentActivity.ScreenIndex) {
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP:
			_FaultHistoryActiveFragment.ClickHome();
			break;
		default:
			// ++, 150210 bwk
			// ++, 150309 bwk
			//if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_TOP || ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
				ParentActivity.showMainScreen();
				// --, 150309 bwk						
				ParentActivity.OldScreenIndex = 0;
			}
			else
				// --, 150210 bwk
				_MenuListTitleFragment.ClickHome();
			break;
		}
	}
	// --, 150209 bwk

	public void ClickNum1(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum1();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum1();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum1();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum1();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum1();
			break;
		// --, 150407 bwk
		// ++, 150331 bwk
		default:
			ParentActivity.ClickMainLightHardKey();
			break;
		// --, 150331 bwk
		}
	}

	public void ClickNum2(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum2();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum2();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum2();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum2();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum2();
			break;
		// --, 150407 bwk
		// ++, 150331 bwk
		default:
			ParentActivity.ClickWorkLightHardKey();
			break;
		// --, 150331 bwk
		}
	}

	public void ClickNum3(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum3();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum3();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum3();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum3();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum3();
			break;
		// --, 150407 bwk

		}
	}

	public void ClickNum4(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum4();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum4();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum4();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum4();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum4();
			break;
		// --, 150407 bwk

		}
	}

	public void ClickNum5(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum5();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum5();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum5();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum5();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum5();
			break;
		// --, 150407 bwk

		}
	}

	public void ClickNum6(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum6();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum6();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum6();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum6();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum6();
			break;
		// --, 150407 bwk
			
		// ++, 150331 bwk
		default:
			ParentActivity.ClickBeaconLampHardKey();
			break;
		// --, 150331 bwk
		}
	}

	public void ClickNum7(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum7();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum7();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum7();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum7();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum7();
			break;
		// --, 150407 bwk			
		// ++, 150331 bwk
		default:
			ParentActivity.ClickRearWiperHardKey();
			break;
		// --, 150331 bwk
		}
	}

	public void ClickNum8(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum8();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum8();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum8();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum8();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum8();
			break;
		// --, 150407 bwk
		}
	}

	public void ClickNum9(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum9();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum9();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum9();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum9();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum9();
			break;
		// --, 150407 bwk
		}
	}

	public void ClickNum0(){
		switch (ParentActivity.ScreenIndex) {

		case Home.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP:
			_SpeedometerFreqFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW:
			_EngineAutoShutdownPWFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
			_FaultHistoryLoggedPasswordFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			_MachineSecurityPasswordFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
			_MachineSecurityPasswordChangeFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE:
			_MaintenanceChangeCycleFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.ClickNum0();
			break;
		// ++, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:
			_SoftwareUpdatePasswordFragment.ClickNum0();	
			break;
		// --, 150323 bwk
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP:
			_ServiceMenuWeighingCompensationFragment.ClickNum0();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP:
			_ChangeASPhoneNumberFragment.ClickNum0();
			break;
		// ++, 150407 bwk 
		case Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP:
			_ClockFragment.ClickNum0();
			break;
		// --, 150407 bwk
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////
}
