package taeha.wheelloader.fseries_monitor.menu.monitoring;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
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

public class VersionInfoFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;

	TextView textViewTCU;
	TextView textViewECM;
	TextView textViewMonitor;
	TextView textViewMCU;
	TextView textViewCluster;
	TextView textViewRMCU;
	TextView textViewEHCU;
	
	TextView textViewMonitorVersion;
	TextView textViewMCUVersion;
	TextView textViewClusterVersion;
	TextView textViewRMCUVersion;
	TextView textViewEHCUVersion;
	
	TextView textViewModel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	byte[] ComponentBasicInformation_RMCU;
	byte[] ComponentBasicInformation_MCU;
	byte[] ComponentBasicInformation_Cluster;
	byte[] ComponentBasicInformation_Monitor;
	
	byte[] ComponentBasicInformation_EHCU;
	byte[] SoftwareIdentification_ECM;
	byte[] SoftwareIdentification_TCU;
	
	int ProgramSubVersionRMCU;
	int ProgramSubVersionMCU;
	int ProgramSubVersionCluster;
	int ProgramSubVersionMonitor;
	int ProgramSubVersionEHCU;
	int ProgramSubVersionECM;
	int ProgramSubVersionTCU;
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
		
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_VERSION);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Version_Information));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_version_low_ok);

		
		textViewTCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_tcu);
		textViewECM = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_ecm);
		textViewMonitor = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_title);
		textViewMCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_mcu_title);
		textViewCluster = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_cluster_title);
		textViewRMCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_rmcu_title);
		textViewEHCU = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_ehcu_title);
		
		textViewMonitorVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_data);
		textViewMCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_mcu_data);
		textViewClusterVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_cluster_data);
		textViewRMCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_rmcu_data);
		textViewEHCUVersion = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_ehcu_data);
		
		textViewModel = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_model_data);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		ComponentBasicInformation_RMCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_MCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_Cluster = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_Monitor = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		ComponentBasicInformation_EHCU = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		SoftwareIdentification_ECM = new byte[CAN1CommManager.LENGTH_SOFTWAREIDENTIFICATION_ECM];
		SoftwareIdentification_TCU = new byte[CAN1CommManager.LENGTH_SOFTWAREIDENTIFICATION_TCU];
		for(int i = 0; i < CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION; i++){
			ComponentBasicInformation_RMCU[i] = (byte) 0;
			ComponentBasicInformation_MCU[i] = (byte) 0;
			ComponentBasicInformation_Cluster[i] = (byte) 0;
			ComponentBasicInformation_Monitor[i] = (byte) 0;
			ComponentBasicInformation_EHCU[i] = (byte) 0;
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
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		textViewTCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTCU();
			}
		});
		textViewECM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickECM();
			}
		});
		textViewMonitor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMonitor();
			}
		});
		textViewMCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMCU();
			}
		});
		textViewCluster.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCluster();
			}
		});
		textViewRMCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRMCU();
			}
		});
		textViewEHCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEHCU();
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
		SoftwareIdentification_ECM = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_ECM();
		SoftwareIdentification_TCU = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU();
		
		ProgramSubVersionRMCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_RMCU);
		ProgramSubVersionMCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_MCU);
		ProgramSubVersionCluster = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_Cluster);
		ProgramSubVersionMonitor = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_Monitor);
		ProgramSubVersionEHCU = ParentActivity.FindProgramSubInfo(ComponentBasicInformation_EHCU);
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
	}
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
	}
	public void ClickECM(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoECM();
	}
	public void ClickMonitor(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoMonitor();
	}
	public void ClickMCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoMCU();
	}
	public void ClickCluster(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoCluster();
	}
	public void ClickRMCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoRMCU();
	}
	public void ClickEHCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoEHCU();
	}
	/////////////////////////////////////////////////////////////////////
	public void ModelDisplay(byte[] _data){
		textViewModel.setText(ParentActivity.GetModelNameString(_data));
	}
	public void MonitorVersionDisplay(int _versionhigh, int _versionlow, int _subhigh, int _sublow){
		textViewMonitorVersion.setText(ParentActivity.GetVersionString(_versionhigh, _versionlow, _subhigh, _sublow));
	}
	public void VersionDisplay(byte[] _data, int _subinfo, TextView textview){
		textview.setText(ParentActivity.GetVersionString(_data, _subinfo));
	}
	/////////////////////////////////////////////////////////////////////
	
}
