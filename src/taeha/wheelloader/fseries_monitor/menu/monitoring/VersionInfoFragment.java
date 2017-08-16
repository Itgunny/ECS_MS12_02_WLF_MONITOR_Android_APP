package taeha.wheelloader.fseries_monitor.menu.monitoring;

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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class VersionInfoFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int OPTION_RMCU = 0;
	private static final int OPTION_EHCU = 1;
	private static final int OPTION_BKCU = 2;
	private static final int OPTION_FATC = 3;
	private static final int OPTION_AAVM = 4;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextView	textViewOK;

	TextView textViewTCU;
	TextView textViewECM;

	
	TextView textViewMonitor;
	TextView textViewMCU;
	TextView textViewCluster;
	TextView textViewRMCU;
	TextView textViewEHCU;
	TextView textViewBKCU;
	TextView textViewACU;
	TextView textViewAAVM;
	
	TextView textViewMonitorVersion;
	TextView textViewMCUVersion;
	TextView textViewClusterVersion;
	TextView textViewRMCUVersion;
	TextView textViewEHCUVersion;
	TextView textViewBKCUVersion;
	TextView textViewACUVersion;
	TextView textViewAAVMVersion;
	
	TextView textViewModelTitle;
	TextView textViewModel;
	
	RelativeLayout	layoutEHCU;		// ++, --, 150211 bwk;
	RelativeLayout	layoutBKCU;
	RelativeLayout	layoutRMCU;
	RelativeLayout 	layoutFATC;
	RelativeLayout  layoutAAVM;
	
	RelativeLayout.LayoutParams lFirstParam;
	RelativeLayout.LayoutParams lSecondParam;
	RelativeLayout.LayoutParams lThirdParam;
	RelativeLayout.LayoutParams lForthParam;
	RelativeLayout.LayoutParams lFifthParam;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	byte[] ComponentBasicInformation_EHCU;
	byte[] ComponentBasicInformation_RMCU;
	byte[] ComponentBasicInformation_MCU;
	byte[] ComponentBasicInformation_Cluster;
	byte[] ComponentBasicInformation_Monitor;
	byte[] ComponentBasicInformation_BKCU;
	byte[] ComponentBasicInfomation_ACU;
	byte[] ComponentBasicInfomation_AAVM;
	
	
	
	
	byte[] SoftwareIdentification_ECM;
	byte[] SoftwareIdentification_TCU;
	
	int ProgramSubVersionRMCU;
	int ProgramSubVersionMCU;
	int ProgramSubVersionCluster;
	int ProgramSubVersionMonitor;
	int ProgramSubVersionEHCU;
	int ProgramSubVersionECM;
	int ProgramSubVersionTCU;
	int ProgramSubVersionACU;
	int ProgramSubVersionBKCU;
	int ProgramSubVersionAAVM;
	
	Handler HandleCursurDisplay;
	int CursurIndex;
	int OptionFlag[];
	int OptionCount;
	
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
		 TAG = "VersionInfoFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_version, null);


		
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_VERSION);
		
		
		setOptionFlag();
		
		setOptionLayout(OPTION_RMCU, layoutRMCU);
		setOptionLayout(OPTION_EHCU, layoutEHCU);
		setOptionLayout(OPTION_BKCU, layoutBKCU);
		setOptionLayout(OPTION_FATC, layoutFATC);
		setOptionLayout(OPTION_AAVM, layoutAAVM);
		
		CheckACU();
		CheckAAVM();
		CheckEHCU();	// ++, --, 150211 bwk
		CheckBKCU();
		CheckRMCU();
		
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information), 318);
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_version_low_ok);
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		textViewTCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_tcu);
		textViewTCU.setText(getString(ParentActivity.getResources().getString(R.string.TCU), 266));
		textViewECM = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_ecm);
		textViewECM.setText(getString(ParentActivity.getResources().getString(R.string.ECM), 267));

		
		textViewMonitor = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_title);
		textViewMonitor.setText(getString(ParentActivity.getResources().getString(R.string.Monitor), 268));
		textViewMCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_mcu_title);
		textViewMCU.setText(getString(ParentActivity.getResources().getString(R.string.MCU), 269));
		textViewCluster = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_cluster_title);
		textViewCluster.setText(getString(ParentActivity.getResources().getString(R.string.Cluster), 270));
		textViewRMCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_rmcu_title);
		textViewRMCU.setText(getString(ParentActivity.getResources().getString(R.string.RMCU), 271));
		textViewEHCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_ehcu_title);
		textViewEHCU.setText(getString(ParentActivity.getResources().getString(R.string.EHCU), 272));
		textViewBKCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_bkcu_title);
		textViewBKCU.setText(getString(ParentActivity.getResources().getString(R.string.BKCU), 273));
		textViewACU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_fatc_title);
		textViewACU.setText(getString(ParentActivity.getResources().getString(R.string.FATC), 455));
		textViewAAVM = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_aavm_title);
		textViewAAVM.setText(getString(ParentActivity.getResources().getString(R.string.AAVM), 505));
		
		
		textViewMonitorVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_data);
		textViewMCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_mcu_data);
		textViewClusterVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_cluster_data);
		textViewRMCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_rmcu_data);
		textViewEHCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_ehcu_data);
		textViewBKCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_bkcu_data);
		textViewACUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_fatc_data);
		textViewAAVMVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_aavm_data);
		
		
		textViewModelTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_model_title);
		textViewModelTitle.setText(getString(ParentActivity.getResources().getString(string.Model), 265));
		textViewModel = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_model_data);
		
		layoutEHCU = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_version_ehcu);	// ++, --, 150211 bwk
		layoutBKCU = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_version_bkcu);
		layoutRMCU = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_version_rmcu);
		layoutFATC = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_version_fatc);
		layoutAAVM = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_version_aavm);

		lFirstParam = (RelativeLayout.LayoutParams) layoutRMCU.getLayoutParams();
		lSecondParam = (RelativeLayout.LayoutParams) layoutEHCU.getLayoutParams();
		lThirdParam = (RelativeLayout.LayoutParams) layoutBKCU.getLayoutParams();
		lForthParam = (RelativeLayout.LayoutParams) layoutFATC.getLayoutParams();
		lFifthParam = (RelativeLayout.LayoutParams) layoutAAVM.getLayoutParams();
		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		OptionCount = 0;
		ComponentBasicInformation_RMCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_MCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_Cluster = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_Monitor = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_BKCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_EHCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInfomation_ACU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInfomation_AAVM = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		
		SoftwareIdentification_ECM = new byte[CAN1CommManager.LENGTH_SOFTWAREIDENTIFICATION_ECM];
		SoftwareIdentification_TCU = new byte[CAN1CommManager.LENGTH_SOFTWAREIDENTIFICATION_TCU];
		
		for(int i = 0; i < CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION; i++){
			ComponentBasicInformation_RMCU[i] = (byte) 0;
			ComponentBasicInformation_MCU[i] = (byte) 0;
			ComponentBasicInformation_Cluster[i] = (byte) 0;
			ComponentBasicInformation_Monitor[i] = (byte) 0;
			ComponentBasicInformation_EHCU[i] = (byte) 0;
			ComponentBasicInformation_BKCU[i] = (byte) 0;
			ComponentBasicInfomation_ACU[i] = (byte) 0;
			ComponentBasicInfomation_AAVM[i] = (byte) 0;
		}
		
		for(int i = 0; i < CAN1CommManager.LENGTH_SOFTWAREIDENTIFICATION_ECM; i++){
			SoftwareIdentification_ECM[i] = (byte) 0;
		}
		
		for(int i = 0; i < CAN1CommManager.LENGTH_SOFTWAREIDENTIFICATION_TCU; i++){
			SoftwareIdentification_TCU[i] = (byte) 0;
		}
		
		ProgramSubVersionRMCU = 0xFF;
		ProgramSubVersionMCU = 0xFF;
		ProgramSubVersionCluster = 0xFF;
		ProgramSubVersionMonitor = 0xFF;
		ProgramSubVersionEHCU = 0xFF;
		ProgramSubVersionECM = 0xFF;
		ProgramSubVersionTCU = 0xFF;
		ProgramSubVersionBKCU = 0xFF;
		ProgramSubVersionAAVM = 0xFF;
		ProgramSubVersionACU = 0xFF;
		
		
		OptionFlag = new int[5];
		
		for(int i = 0; i < 5; i++){
			OptionFlag[i] = 0;
		}
		//CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
				
			}
		});
		textViewTCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTCU();
			}
		});
		textViewECM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickECM();
			}
		});
		textViewMonitor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickMonitor();
			}
		});
		textViewMCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickMCU();
			}
		});
		textViewCluster.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCluster();
			}
		});
		textViewRMCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickRMCU();
			}
		});
		textViewEHCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickEHCU();
			}
		});
		textViewBKCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBKCU();
			}
		});		
		textViewACU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickACU();
			}
		});
		textViewAAVM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVM();
				
			}
		});	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentBasicInformation_RMCU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_RMCU();
		ComponentBasicInformation_MCU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330();
		ComponentBasicInformation_Cluster = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_CLUSTER();
		ComponentBasicInformation_Monitor = ParentActivity.GetMonitorComponentBasicInfo();
		ComponentBasicInformation_EHCU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_EHCU();
		ComponentBasicInformation_BKCU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_BKCU();
		ComponentBasicInfomation_ACU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_ACU();
		ComponentBasicInfomation_AAVM = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_AAVO();
		
		SoftwareIdentification_ECM = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_ECM();
		SoftwareIdentification_TCU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU();
	
		
		ProgramSubVersionRMCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_RMCU);
		ProgramSubVersionMCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_MCU);
		ProgramSubVersionCluster = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_Cluster);
		ProgramSubVersionMonitor = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_Monitor);
		ProgramSubVersionEHCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_EHCU);
		ProgramSubVersionBKCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_BKCU);
		ProgramSubVersionAAVM = ParentActivity.FindProgramSubInfo(ComponentBasicInfomation_AAVM);
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ModelDisplay(ComponentBasicInformation_MCU);
		MonitorVersionDisplay(ParentActivity.VERSION_HIGH,ParentActivity.VERSION_LOW,ParentActivity.VERSION_SUB_HIGH,ParentActivity.VERSION_SUB_LOW);
		VersionDisplay(ComponentBasicInformation_MCU,ProgramSubVersionMCU,textViewMCUVersion);
		VersionDisplay(ComponentBasicInformation_RMCU,ProgramSubVersionRMCU,textViewRMCUVersion);
		VersionDisplay(ComponentBasicInformation_Cluster,ProgramSubVersionCluster,textViewClusterVersion);
		VersionDisplay(ComponentBasicInformation_EHCU,ProgramSubVersionEHCU,textViewEHCUVersion);
		VersionDisplay(ComponentBasicInformation_BKCU,ProgramSubVersionBKCU,textViewBKCUVersion);
		VersionDisplay(ComponentBasicInfomation_AAVM,ProgramSubVersionAAVM,textViewAAVMVersion);
		VersionDisplay(ComponentBasicInfomation_ACU,textViewACUVersion);
	}
	/////////////////////////////////////////////////////////////////////	
	// ++, 150211 bwk
	public void CheckACU(){
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() != CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER){
			setClickableACU(false);
		}else{
			setClickableACU(true);
		}		
	}
	public void CheckAAVM(){
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() != CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER){
			setClickableAAVM(false);
		}else{
			setClickableAAVM(true);
		}
	}
	public void CheckEHCU(){
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
			setClickableEHCU(false);
		}else{
			setClickableEHCU(true);
		}
	}
	public void CheckBKCU(){
		if(CAN1Comm.Get_CheckBKCUComm() == 1){
			setClickableBKCU(true);
		}else{
			setClickableBKCU(false);
		}
		
	}
	public void CheckRMCU(){
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() != CAN1CommManager.STATE_COMPONENTCODE_RMCU){
			setClickableRMCU(false);
		}else{
			setClickableRMCU(true);
		}
	}
	
	public void setClickableACU(boolean flag){
		if(flag == true){
			layoutFATC.setVisibility(View.VISIBLE);
		}else{
			layoutFATC.setVisibility(View.INVISIBLE);
		}
	}
	
	public void setClickableAAVM(boolean flag){
		if(flag == true){
			layoutAAVM.setVisibility(View.VISIBLE);
		}else{
			layoutAAVM.setVisibility(View.INVISIBLE);
		}
	}
	public void setClickableEHCU(boolean flag){
		if(flag == true){
			layoutEHCU.setVisibility(View.VISIBLE);
		}else{
			layoutEHCU.setVisibility(View.INVISIBLE);
		}
	}
	
	public void setClickableBKCU(boolean flag){
		if(flag == true){
			layoutBKCU.setVisibility(View.VISIBLE);
		}else{
			layoutBKCU.setVisibility(View.INVISIBLE);
		}
	}	
	public void setClickableRMCU(boolean flag){
		if(flag == true){
			layoutRMCU.setVisibility(View.VISIBLE);
		}else{
			layoutRMCU.setVisibility(View.INVISIBLE);
		}
	}	
	// --, 150211 bwk
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMonitoringAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
	}
	public void ClickTCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoTCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(1);
	}
	public void ClickECM(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoECM();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(2);
	}
	public void ClickACU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoACU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(3);
	}
	public void ClickAAVM(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoAAVM();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(4);
	}
	public void ClickMonitor(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoMonitor();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(5);
	}
	public void ClickMCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoMCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(6);
	}
	public void ClickCluster(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoCluster();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(7);
	}
	public void ClickRMCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoRMCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(8);
	}
	public void ClickEHCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoEHCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(9);
	}
	public void ClickBKCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoBKCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(10);
	}
	
	/////////////////////////////////////////////////////////////////////
	public void ModelDisplay(byte[] _data){
		if(ParentActivity.MachineSerialNumber != 0xffffff)
			textViewModel.setText(ParentActivity.GetModelNameString(_data)+" # "+ParentActivity.MachineSerialNumber);
		else
			textViewModel.setText(ParentActivity.GetModelNameString(_data));
	}
	public void MonitorVersionDisplay(int _versionhigh, int _versionlow, int _subhigh, int _sublow){
		//textViewMonitorVersion.setText(ParentActivity.GetVersionString(_versionhigh, _versionlow, _subhigh, _sublow));
		textViewMonitorVersion.setText(ParentActivity.GetVersionString(_versionhigh, _versionlow, _subhigh));
	}
	public void VersionDisplay(byte[] _data, int _subinfo, TextView textview){
		textview.setText(ParentActivity.GetVersionString(_data, _subinfo));
	}
	public void BKCUVersionDisplay(byte[] _data,TextView textview){
		int UpperVersion;
		int LowerVersion;
		UpperVersion = (_data[3] & 0xF0) >> 4;
		LowerVersion = _data[3] & 0x0F;
		textview.setText(Integer.toString(UpperVersion) + "." + Integer.toString(LowerVersion));
	}
	public void VersionDisplay(byte[] _data, TextView textView){
		int VersionHigh, VersionLow;
		String strVer;
		VersionHigh = ((_data[3] & 0xF0) >> 4);
		VersionLow = (_data[3] & 0x0F);

		if(VersionLow > 10 && VersionHigh >= 15){
			strVer = "";
		}else{
			strVer = Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow);
		}
		
		textView.setText(strVer);
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void setFirstCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 11;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
				CursurIndex--;
			CursurDisplay(CursurIndex);
			break;		
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
			setOptionCursurIndex(CursurIndex, false);
			break;			
		default:
			break;
		}
		
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 10:
				CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			setOptionCursurIndex(CursurIndex, true);
			break;
		case 11:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickTCU();
			break;
		case 2:
			ClickECM();
			break;
		case 3:
			ClickMonitor();
			break;
		case 4:
			ClickMCU();
			break;
		case 5:
			ClickCluster();
			break;
		case 6:
			ClickRMCU();
			break;
		case 7:
			ClickEHCU();
			break;
		case 8:
			ClickBKCU();
			break;
		case 9:
			ClickACU();
			break;
		case 10:
			ClickAAVM();
			break;
		case 11:
			ClickOK();
			break;
		default:
			break;
		}
	}

	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		textViewTCU.setPressed(false);
		textViewECM.setPressed(false);
		
		textViewMonitor.setPressed(false);
		textViewMCU.setPressed(false);
		textViewCluster.setPressed(false);
		textViewRMCU.setPressed(false);
		textViewEHCU.setPressed(false);
		textViewBKCU.setPressed(false);
		textViewACU.setPressed(false);
		textViewAAVM.setPressed(false);
		
		textViewMonitorVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewMCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewClusterVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewRMCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewEHCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));	
		textViewBKCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewACUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewAAVMVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		
		switch (Index) {
		case 1:
			textViewTCU.setPressed(true);
			break;
		case 2:
			textViewECM.setPressed(true);
			break;
		case 3:
			textViewMonitor.setPressed(true);
			textViewMonitorVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;
		case 4:
			textViewMCU.setPressed(true);
			textViewMCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;
		case 5:
			textViewCluster.setPressed(true);
			textViewClusterVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;
		case 6:
			textViewRMCU.setPressed(true);
			textViewRMCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;
		case 7:
			textViewEHCU.setPressed(true);
			textViewEHCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));	
			break;
		case 8:
			textViewBKCU.setPressed(true);
			textViewBKCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));	
			break;
		case 9:
			textViewACU.setPressed(true);
			textViewACUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;			
		case 10:
			textViewAAVM.setPressed(true);
			textViewAAVMVersion.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;
		case 11:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
	public void setOptionFlag(){
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU){
			OptionFlag[0] = 1;
		}
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU){
			OptionFlag[1] = 1;
		}
		if(CAN1Comm.Get_CheckBKCUComm() == 1){
			OptionFlag[2] = 1;
		}
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER){
			OptionFlag[3] = 1;
		}
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER){
			OptionFlag[4] = 1;
		}
	}
	public void setOptionLayout(int optionNumber, RelativeLayout layout){
		

		
		Log.d(TAG, "Option Number : " + optionNumber);
		if(OptionFlag[optionNumber] == 1){
			switch(OptionCount){
			case 0:
				layout.setLayoutParams(lFirstParam);
				OptionCount++;
				Log.d(TAG, "First Layout Option Flag : " + OptionFlag[optionNumber] + " Option Count : " + OptionCount);
				break;
			case 1:
				layout.setLayoutParams(lSecondParam);
				Log.d(TAG, "Second Layout Option Flag : " + OptionFlag[optionNumber] + " Option Count : " + OptionCount);
				OptionCount++;
				break;
			case 2:
				layout.setLayoutParams(lThirdParam);
				Log.d(TAG, "Third Layout Option Flag : " + OptionFlag[optionNumber] + " Option Count : " + OptionCount);
				OptionCount++;
				break;
			case 3:
				layout.setLayoutParams(lForthParam);
				Log.d(TAG, "Forth Layout Option Flag : " + OptionFlag[optionNumber] + " Option Count : " + OptionCount);
				OptionCount++;
				break;
			case 4:
				layout.setLayoutParams(lFifthParam);
				Log.d(TAG, "Fifth Layout Option Flag : " + OptionFlag[optionNumber] + " Option Count : " + OptionCount);
				OptionCount++;
				break;
			default:
				break;
			}
			
			
			
		}
		
		
		
	}
	/////////////////////////////////////////////////////////////////////
	void setOptionCursurIndex(int cursurIndex, boolean flag){
		if(flag == true){
			switch(cursurIndex){
			case 5:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU)
					CursurIndex = 6;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
					CursurIndex = 7;
				else if(CAN1Comm.Get_CheckBKCUComm() == 1)
					CursurIndex = 8;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 9;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER)
					CursurIndex = 10;
				else
					CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			case 6:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
					CursurIndex = 7;
				else if(CAN1Comm.Get_CheckBKCUComm() == 1)
					CursurIndex = 8;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 9;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER)
					CursurIndex = 10;
				else
					CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			case 7:
				if(CAN1Comm.Get_CheckBKCUComm() == 1)
					CursurIndex = 8;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 9;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER)
					CursurIndex = 10;
				else
					CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			case 8:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 9;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER)
					CursurIndex = 10;
				else
					CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			case 9:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 10;
				else
					CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			default:
				break;
			}
		}else {
			switch(cursurIndex){
			case 7:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU)
					CursurIndex = 6;
				else
					CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			case 8:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU)
					CursurIndex = 6;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
					CursurIndex = 7;
				else
					CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			case 9:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU)
					CursurIndex = 6;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
					CursurIndex = 7;
				else if(CAN1Comm.Get_CheckBKCUComm() == 1)
					CursurIndex = 8;
				else
					CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			case 10:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU)
					CursurIndex = 6;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
					CursurIndex = 7;
				else if(CAN1Comm.Get_CheckBKCUComm() == 1)
					CursurIndex = 8;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 9;
				else
					CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			case 11:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() == CAN1CommManager.STATE_COMPONENTCODE_RMCU)
					CursurIndex = 6;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
					CursurIndex = 7;
				else if(CAN1Comm.Get_CheckBKCUComm() == 1)
					CursurIndex = 8;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() == CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
					CursurIndex = 9;
				else if(CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO() == CAN1CommManager.STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER)
					CursurIndex = 10;
				else
					CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			default:
				break;
			}
		}
		
	}
}
