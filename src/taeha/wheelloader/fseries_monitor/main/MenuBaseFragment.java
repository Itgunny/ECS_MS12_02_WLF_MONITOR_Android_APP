package taeha.wheelloader.fseries_monitor.main;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuInterTitleFragment;
import taeha.wheelloader.fseries_monitor.menu.MenuListLeftFragment;
import taeha.wheelloader.fseries_monitor.menu.MenuListTitleFragment;
import taeha.wheelloader.fseries_monitor.menu.management.AngleCalibration;
import taeha.wheelloader.fseries_monitor.menu.management.CalibrationFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ChangeASPhoneNumberFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityESLFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityListFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityPasswordChangeFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecurityPasswordFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MachineSecuritySmartKeyFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceChangeCycleFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceDetailFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MenuManagementFragment;
import taeha.wheelloader.fseries_monitor.menu.management.PressureCalibration;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuListFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuPasswordFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSensorMonitoringFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSensorMonitoringHiddenFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSpeedLimitFragment;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuWeighingCompensationFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.CameraSettingFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.CoolingFanFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.DetentFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineAutoShutdownFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineAutoShutdownPWFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineDelayShutdownFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.EngineSpeedFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.MaxFlowFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.MenuModeFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.SoftStopFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.SpeedometerFreqFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.WiperFragment;
import taeha.wheelloader.fseries_monitor.menu.mode.WorkLoadFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.EHCUIOInfoBoomLeverFloatFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.EHCUIOInfoFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FaultHistoryFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.MachineMonitoringFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.MenuMonitoringFragment;
import taeha.wheelloader.fseries_monitor.menu.monitoring.OperationHistoryFragment;
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
import taeha.wheelloader.fseries_monitor.menu.preference.ClockFragment;
import taeha.wheelloader.fseries_monitor.menu.preference.MenuPreferenceFragment;
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
	public EngineSpeedFragment 			_EngineSpeedFragment;
	public WorkLoadFragment 			_WorkLoadFragment;
	public DetentFragment				_DetentFragment;
	public MaxFlowFragment 				_MaxFlowFragment;
	public SoftStopFragment				_SoftStopFragment;
	public EngineAutoShutdownFragment	_EngineAutoShutdownFragment;
	public EngineDelayShutdownFragment	_EngineDelayShutdownFragment;
	public CameraSettingFragment 		_CameraSettingFragment;
	public CoolingFanFragment			_CoolingFanFragment;
	public SpeedometerFreqFragment		_SpeedometerFreqFragment;
	public WiperFragment				_WiperFragment;
	public EngineAutoShutdownPWFragment	_EngineAutoShutdownPWFragment;
	//Monitoring
	public MachineMonitoringFragment			_MachineMonitoringFragment;
	public OperationHistoryFragment				_OperationHistoryFragment;
	public FaultHistoryFragment					_FaultHistoryFragment;
	public EHCUIOInfoFragment					_EHCUIOInfoFragment;
	public EHCUIOInfoBoomLeverFloatFragment		_EHCUIOInfoBoomLeverFloatFragment;
	public VersionInfoFragment					_VersionInfoFragment;
	public VersionInfoMonitorFragment			_VersionInfoMonitorFragment;
	public VersionInfoMCUFragment				_VersionInfoMCUFragment;
	public VersionInfoClusterFragment			_VersionInfoClusterFragment;
	public VersionInfoEHCUFragment				_VersionInfoEHCUFragment;
	public VersionInfoRMCUFragment				_VersionInfoRMCUFragment;
	public VersionInfoTCUFragment				_VersionInfoTCUFragment;
	public VersionInfoECMFragment				_VersionInfoECMFragment;
	
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
	
	//Preference
	public BrightnessFragment						_BrightnessFragment;
	public ClockFragment							_ClockFragment;
	public UnitFragment								_UnitFragment;
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

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_TOP;
		
		if(FirstScreenIndex == Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyClockAnimation();
			FirstScreenIndex = 0;
		}else if(FirstScreenIndex == Home.SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyPressureCalibrationAnimation();
			FirstScreenIndex = 0;
		}else if(FirstScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP){
			framelayoutListTitle.setVisibility(View.INVISIBLE);
			framelayoutListBody.setVisibility(View.INVISIBLE);
			framelayoutListLeft.setVisibility(View.INVISIBLE);
			showBodyWiperAnimation();
			FirstScreenIndex = 0;
		}
		else{
			ListLeftAnimation.StartAppearAnimation(_MenuListLeftFragment);
			ListTitleAnimation.StartAppearAnimation(_MenuListTitleFragment);
			ListBodyAnimation.StartAppearAnimation(_MenuModeFragment);
			_MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP);
		}
	
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
		_CoolingFanFragment = new CoolingFanFragment();
		_SpeedometerFreqFragment = new SpeedometerFreqFragment();
		_WiperFragment = new WiperFragment();
		_EngineAutoShutdownPWFragment = new EngineAutoShutdownPWFragment();
		
		_MachineMonitoringFragment = new MachineMonitoringFragment();
		_OperationHistoryFragment = new OperationHistoryFragment();
		_FaultHistoryFragment = new FaultHistoryFragment();
		_EHCUIOInfoFragment = new EHCUIOInfoFragment();
		_EHCUIOInfoBoomLeverFloatFragment = new EHCUIOInfoBoomLeverFloatFragment();
		_VersionInfoFragment = new VersionInfoFragment();
		_VersionInfoMonitorFragment = new VersionInfoMonitorFragment();
		_VersionInfoMCUFragment = new VersionInfoMCUFragment();
		_VersionInfoClusterFragment = new VersionInfoClusterFragment();
		_VersionInfoEHCUFragment = new VersionInfoEHCUFragment();
		_VersionInfoRMCUFragment = new VersionInfoRMCUFragment();
		_VersionInfoTCUFragment = new VersionInfoTCUFragment();
		_VersionInfoECMFragment = new VersionInfoECMFragment();
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
		_BrightnessFragment = new BrightnessFragment();
		_ClockFragment = new ClockFragment();
		_UnitFragment = new UnitFragment();
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
		transaction.detach(_CoolingFanFragment);
		transaction.detach(_SpeedometerFreqFragment);
		transaction.detach(_WiperFragment);
		transaction.detach(_EngineAutoShutdownPWFragment);
		transaction.detach(_MachineMonitoringFragment);
		transaction.detach(_OperationHistoryFragment);
		transaction.detach(_FaultHistoryFragment);
		transaction.detach(_EHCUIOInfoFragment);	
		transaction.detach(_EHCUIOInfoBoomLeverFloatFragment);	
		transaction.detach(_VersionInfoFragment);
		transaction.detach(_VersionInfoMonitorFragment);
		transaction.detach(_VersionInfoMCUFragment);
		transaction.detach(_VersionInfoClusterFragment);
		transaction.detach(_VersionInfoEHCUFragment);
		transaction.detach(_VersionInfoRMCUFragment);
		transaction.detach(_VersionInfoTCUFragment);
		transaction.detach(_VersionInfoECMFragment);
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
		transaction.detach(_BrightnessFragment);
		transaction.detach(_ClockFragment);
		transaction.detach(_UnitFragment);
		
		
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
	public void showBodyServiceMenuList(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_ServiceMenuListFragment);
		transaction.replace(R.id.FrameLayout_menu_list_body, _ServiceMenuListFragment);
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
	public void showBodyCoolingFan(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_CoolingFanFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _CoolingFanFragment);
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
	
	public void showBodyMachineMonitoring(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MachineMonitoringFragment);
		transaction.replace(R.id.FrameLayout_menu_inter_body, _MachineMonitoringFragment);
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
	public void showMachineSecurityListAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_MachineSecurityListFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
	}
	public void showCalibrationAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_CalibrationFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
	}
	public void showServiceMenuListAnimation(){
		DestroyFragment();
		InitFragment();
		showListAnimation();
		ListBodyAnimation.StartChangeAnimation(_ServiceMenuListFragment);
		_MenuListLeftFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
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
	public void showBodyCoolingFanAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_CoolingFanFragment);
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
	public void showBodyMachineMonitoringAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_MachineMonitoringFragment);
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
	public void showBodyVersionInfoRMCUAnimation(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoRMCUFragment);
	}
	public void _VersionInfoTCUFragment(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoTCUFragment);
	}
	public void _VersionInfoECMFragment(){
		showInterAnimation();
		InterBodyAnimation.StartChangeAnimation(_VersionInfoECMFragment);
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
	
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(final int key){
		Log.d(TAG,"KeyButtonClick : 0x" + Integer.toHexString(key));
		
		// TODO Auto-generated method stub
		switch (key) {
		case CAN1CommManager.MAINLIGHT:
			
			break;
		case CAN1CommManager.WORKLIGHT:
			
			break;
		case CAN1CommManager.AUTO_GREASE:
		
			break;
		case CAN1CommManager.QUICK_COUPLER:
		
			break;
		case CAN1CommManager.RIDE_CONTROL:
			
			break;
		case CAN1CommManager.WORK_LOAD:
		
			break;
		case CAN1CommManager.BEACON_LAMP:
			
			break;
		case CAN1CommManager.REAR_WIPER:
			
			break;
		case CAN1CommManager.MIRROR_HEAT:
			
			break;
		case CAN1CommManager.AUTOPOSITION:
		
			break;
		case CAN1CommManager.FINEMODULATION:
			
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
		default:
			break;
		}
	}
	public void ClickKeyButtonLeft(){
		if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MODE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MODE_END)){
			_MenuModeFragment.ClickLeft();
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MONITORING_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MONITORING_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MANAGEMENT_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MANAGEMENT_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_PREFERENCE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_PREFERENCE_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MULTIMEDIA_END)){
			
		}else{
			
		}
	}
	public void ClickKeyButtonRight(){
		if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MODE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MODE_END)){
			_MenuModeFragment.ClickRight();
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MONITORING_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MONITORING_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MANAGEMENT_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MANAGEMENT_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_PREFERENCE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_PREFERENCE_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MULTIMEDIA_END)){
			
		}else{
		
		}
	}
	public void ClickKeyButtonESC(){
		Log.d(TAG,"ClickKeyButtonESC");
		if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MODE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MODE_END)){
			_MenuModeFragment.ClickESC();
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MONITORING_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MONITORING_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MANAGEMENT_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MANAGEMENT_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_PREFERENCE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_PREFERENCE_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MULTIMEDIA_END)){
			
		}else{
			
		}
	}
	public void ClickKeyButtonEnter(){
		if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MODE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MODE_END)){
			_MenuModeFragment.ClickEnter();
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MONITORING_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MONITORING_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MANAGEMENT_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MANAGEMENT_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_PREFERENCE_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_PREFERENCE_END)){
			
		}else if((ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP) && (ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MENU_MULTIMEDIA_END)){
			
		}else{
			
		}
	}
	public void ClickKeyButtonLongLeftRight(){
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
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			_ServiceMenuPasswordFragment.showServicePasswordNextScreen();
			break;
		case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
			_MenuMultimediaFragment.ExcuteFileManaget();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_EHCUINFO_TOP:
			showBodyBoomLeverFloatInfo();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP:
			showBodySensorMonitoringHidden();
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////
}
