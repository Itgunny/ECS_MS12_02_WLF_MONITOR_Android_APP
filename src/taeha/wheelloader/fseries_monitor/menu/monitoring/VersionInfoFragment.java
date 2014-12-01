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
	
	Handler HandleCursurDisplay;
	int CursurIndex;
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
				CursurIndex = 8;
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
	public void ClickMonitor(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoMonitor();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(3);
	}
	public void ClickMCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoMCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(4);
	}
	public void ClickCluster(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoCluster();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(5);
	}
	public void ClickRMCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoRMCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(6);
	}
	public void ClickEHCU(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyVersionInfoEHCU();
		ParentActivity._MenuBaseFragment._VersionInfoFragment.setFirstCursurIndex(7);
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
	/////////////////////////////////////////////////////////////////////
	public void setFirstCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 8;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			CursurIndex--;
			CursurDisplay(CursurIndex);
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
		case 5:
		case 6:
		case 7:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 8:
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
		
		textViewMonitorVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewMCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewClusterVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewRMCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));
		textViewEHCUVersion.setTextColor(ParentActivity.getResources().getColor(R.color.white));		
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
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
