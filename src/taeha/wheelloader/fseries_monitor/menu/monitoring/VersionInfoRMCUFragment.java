package taeha.wheelloader.fseries_monitor.menu.monitoring;

import customlist.sensormonitoring.IconTextItem;
import customlist.versiondetail.IconTextItemVersion;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VersionInfoRMCUFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	protected static final int STATE_SERIALNUMBER					 		= 0;
	protected static final int STATE_MAKER					 				= 1;
	protected static final int STATE_VERSION					 			= 2;
	private static final int STATE_NETWORKTYPE					 			= 3;
	private static final int STATE_NUMOFDATA					 			= 4;
	private static final int STATE_BACKUPBATTERYVOLT					 	= 5;
	private static final int STATE_COMMSTATUS					 			= 6;
	private static final int STATE_NETWORKSERVICE					 		= 7;
	private static final int STATE_COMMANTENNA					 			= 8;
	private static final int STATE_GPSANTENNA					 			= 9;
	private static final int STATE_POSITIONUPDATE				 			= 10;
	private static final int STATE_GUARD					 				= 11;
	private static final int STATE_LOCKLEVEL					 			= 12;
	
	private static final int STATE_DISABLE						 			= 0;
	private static final int STATE_ENABLE						 			= 1;
	private static final int STATE_NOTAVAILABLE					 			= 3;
	
	private static final int STATE_RMCUNETWORKTYPE_NETWORK1_GSM				= 0;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK1_CDMA			= 1;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK1_3G				= 2;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK1_WIFI			= 3;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK1_ORBCOMM			= 4;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK12_3G_ORBCOMM		= 20;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK12_ORBCOMM_3G 	= 21;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK12_3G_WIFI 		= 22;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK12_WIFI_3G 		= 23;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK12_2G_3G 			= 24;
	private static final int STATE_RMCUNETWORKTYPE_NETWORK12_3G_2G 			= 25;
	private static final int STATE_RMCUNETWORKTYPE_NOTAVAILABLE 			= 255;
	
	private static final int STATE_MACHINEPOSITIONSTATUS_INSIDE	 			= 0;
	private static final int STATE_MACHINEPOSITIONSTATUS_OUTSIDE 			= 1;
	private static final int STATE_MACHINEPOSITIONSTATUS_NOTAVAILABLE		= 3;
	
	private static final int STATE_GPSANTENNA_NORMAL						= 0;
	private static final int STATE_GPSANTENNA_ABNORMAL						= 1;
	private static final int STATE_GPSANTENNA_NOTAVAILABLE					= 3;
	
	private static final int STATE_NETWORKSERVICESTATUS_LOCALSERVICE			= 0;
	private static final int STATE_NETWORKSERVICESTATUS_ROAMINGSERVICE			= 1;
	private static final int STATE_NETWORKSERVICESTATUS_SERVICENOTAVAILABLE		= 2;
	private static final int STATE_NETWORKSERVICESTATUS_NOTAUTHORIZEDSERVICE	= 3;
	private static final int STATE_NETWORKSERVICESTATUS_NOTAVAILABLE			= 255;
	
	private static final int STATE_NETWORKANTENNASTATUS_OFF					= 0;
	private static final int STATE_NETWORKANTENNASTATUS_MAIN_ON				= 1;
	private static final int STATE_NETWORKANTENNASTATUS_EMERGENCY_ON		= 2;
	private static final int STATE_NETWORKANTENNASTATUS_NOTAVAILABLE		= 255;
	
	private static final int STATE_LOCKLEVEL_UNLOCK							= 0;
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	int RMCUNetworkType;
	int RMCUBackupBatteryVolt;
	int RMCUPowerSource;
	int RMCUBoxOpeningStatus;
	int NetworkCommStatus1;
	int PositionUpdateStatus;
	int MachinePositionStatus;
	int GPSAntennaConnectionAlarmStatus;
	int NetworkTransceiverStatus1;
	int NetworkServiceStatus1;
	int NetworkAntennaStatus1;
	int RMCUData;
	
	int LockLevel;
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoRMCUFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.RMCU));
		return mRoot;
	}
	@Override
	protected void InitValuables(){
		super.InitValuables();
		
		RMCUNetworkType = STATE_RMCUNETWORKTYPE_NOTAVAILABLE;
		RMCUBackupBatteryVolt = 0;
		RMCUPowerSource = 0;
		RMCUBoxOpeningStatus = 0;
		NetworkCommStatus1 = STATE_NOTAVAILABLE;
		PositionUpdateStatus = 0;
		MachinePositionStatus = STATE_MACHINEPOSITIONSTATUS_NOTAVAILABLE;
		GPSAntennaConnectionAlarmStatus = STATE_GPSANTENNA_NOTAVAILABLE;
		NetworkTransceiverStatus1 = 0;
		NetworkServiceStatus1 = STATE_NETWORKSERVICESTATUS_NOTAVAILABLE;
		NetworkAntennaStatus1 = STATE_NETWORKANTENNASTATUS_NOTAVAILABLE;
		RMCUData = 0;
		
		LockLevel = STATE_LOCKLEVEL_UNLOCK;

//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Network_Type),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Number_of_Messages_to_Transmit),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Back_up_Battery_Voltage),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Network_Communication_Status),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Network_Service),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Communication_Antenna),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.GPS_Antenna),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Position_Update),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Outside_of_Boundary),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Lock_Level),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
		
		
		listView.setAdapter(adapter);

	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU();
//		ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_RMCU();
		ComponentBasicInformation = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_RMCU();
//		RMCUNetworkType = CAN1Comm.Get_RMCUNetworkType_1621_PGN65329();
//		RMCUBackupBatteryVolt = CAN1Comm.Get_RMCUBackupBatteryVoltage_1590_PGN65329();
//		RMCUPowerSource = CAN1Comm.Get_RMCUPowerSource_1594_PGN65329();
//		RMCUBoxOpeningStatus = CAN1Comm.Get_RMCUBoxOpeningStatus_PGN65329();
//		NetworkCommStatus1 = CAN1Comm.Get_NetworkCommunicationStatus1_1622_PGN65329();
//		PositionUpdateStatus = CAN1Comm.Get_PositionUpdateStatus_852_PGN65329();
//		MachinePositionStatus = CAN1Comm.Get_MachinePositionStatus_1593_PGN65329();
//		GPSAntennaConnectionAlarmStatus = CAN1Comm.Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329();
//		NetworkTransceiverStatus1 = CAN1Comm.Get_NetworkTransceiverStatus1_1623_PGN65329();
//		NetworkServiceStatus1 = CAN1Comm.Get_NetworkServiceStatus1_1624_PGN65329();
//		NetworkAntennaStatus1 = CAN1Comm.Get_NetworkAntennaStatus1_1625_PGN65329();
//		RMCUData = CAN1Comm.Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329();
//		LockLevel = CAN1Comm.Get_LockLevel_823_PGN65348();
	
		ProgramSubVersion = ParentActivity.FindProgramSubInfo(ComponentBasicInformation);
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ManufactureDayDisplay(ComponentBasicInformation);
		ModelDisplay(ComponentBasicInformation);
		VersionDisplay(ComponentBasicInformation, ProgramSubVersion);
		SerialNumberDisplay(ComponentBasicInformation);
//		ManufacturerDisplay(ManufacturerCode);
//		NetworkTypeDisplay(RMCUNetworkType);
//		NumofDataDisplay(RMCUData);
//		BackupBatteryVoltDisplay(RMCUBackupBatteryVolt);
//		CommStatusDisplay(NetworkCommStatus1);
//		NetworkServiceDisplay(NetworkServiceStatus1);
//		NetworkAntennaDisplay(NetworkAntennaStatus1);
//		GPSAntennaDisplay(GPSAntennaConnectionAlarmStatus);
//		PositionUpdateDisplay(PositionUpdateStatus);
//		GuardDisplay(MachinePositionStatus);
//		LockLevelDisplay(LockLevel);
	
		adapter.notifyDataSetChanged();
	}
	////////////////////////////////////////////////
	public void NetworkTypeDisplay(int NetworkType){
		switch (NetworkType) {
		case STATE_RMCUNETWORKTYPE_NETWORK1_GSM:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "2G (GSM)");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK1_CDMA:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "2G (CDMA)");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK1_3G:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "3G");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK1_WIFI:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "WiFi");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK1_ORBCOMM:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Orbcomm");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK12_3G_ORBCOMM:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Network 1 : 3G & Network 2 : Orbcomm");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK12_ORBCOMM_3G:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Network 1 : Orbcomm & Network 2 : 3G");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK12_3G_WIFI:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Network 1 : 3G & Network 2 : WiFi");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK12_WIFI_3G:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Network 1 : WiFi & Network 2 : 3G");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK12_2G_3G:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Network 1 : 2G & Network 2 : 3G");
			break;
		case STATE_RMCUNETWORKTYPE_NETWORK12_3G_2G:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "Network 1 : 3G & Network 2 : 2G");
			break;			
		case STATE_RMCUNETWORKTYPE_NOTAVAILABLE:
			adapter.UpdateSecond(STATE_NETWORKTYPE, "-");
			break;
		default:
			break;
		}
	}
	
	
	
	public void NumofDataDisplay(int NumofData){
		if(NumofData > 250){
			adapter.UpdateSecond(STATE_NUMOFDATA, "-");
		}else{
			adapter.UpdateSecond(STATE_NUMOFDATA, Integer.toString(NumofData));
		}
		
	}
	
	public void BackupBatteryVoltDisplay(int BatteryVolt){
		float fBattery;
		fBattery = (float)(BatteryVolt / 10.0);
		
		if(BatteryVolt > 250){
			adapter.UpdateSecond(STATE_BACKUPBATTERYVOLT, "-");
		}else{
			adapter.UpdateSecond(STATE_BACKUPBATTERYVOLT, Float.toString(fBattery) + " V");
		}
	
		
	}

	public void CommStatusDisplay(int CommStatus){
		switch (CommStatus) {
		case STATE_DISABLE:
			adapter.UpdateSecond(STATE_COMMSTATUS, "Disable");
			break;
		case STATE_ENABLE:
			adapter.UpdateSecond(STATE_COMMSTATUS, "Enable");
			break;
		case STATE_NOTAVAILABLE:
			adapter.UpdateSecond(STATE_COMMSTATUS, "-");
			break;
		default:
			break;
		}
	}
	
	public void NetworkServiceDisplay(int NetworkService){
		switch (NetworkService) {
		case STATE_NETWORKSERVICESTATUS_LOCALSERVICE:
			adapter.UpdateSecond(STATE_NETWORKSERVICE, "Local");
			break;
		case STATE_NETWORKSERVICESTATUS_ROAMINGSERVICE:
			adapter.UpdateSecond(STATE_NETWORKSERVICE, "Roaming");
			break;
		case STATE_NETWORKSERVICESTATUS_SERVICENOTAVAILABLE:
			adapter.UpdateSecond(STATE_NETWORKSERVICE, "Service Not Available");
			break;
		case STATE_NETWORKSERVICESTATUS_NOTAUTHORIZEDSERVICE:
			adapter.UpdateSecond(STATE_NETWORKSERVICE, "Not Authorized to Operate on Service");
			break;
		case STATE_NETWORKSERVICESTATUS_NOTAVAILABLE:
			adapter.UpdateSecond(STATE_NETWORKSERVICE, "-");
			break;
		default:
			break;
		}
	}
	
	public void NetworkAntennaDisplay(int Antenna){
		switch (Antenna) {
		case STATE_NETWORKANTENNASTATUS_OFF:
			adapter.UpdateSecond(STATE_COMMANTENNA, "Off");
			break;
		case STATE_NETWORKANTENNASTATUS_MAIN_ON:
			adapter.UpdateSecond(STATE_COMMANTENNA, "Main On");
			break;
		case STATE_NETWORKANTENNASTATUS_EMERGENCY_ON:
			adapter.UpdateSecond(STATE_COMMANTENNA, "Emergency On");
			break;
		case STATE_NETWORKANTENNASTATUS_NOTAVAILABLE:
			adapter.UpdateSecond(STATE_COMMANTENNA, "-");
			break;
		default:
			break;
		}
	}
	
	public void GPSAntennaDisplay(int Antenna){
		switch (Antenna) {
		case STATE_GPSANTENNA_NORMAL:
			adapter.UpdateSecond(STATE_GPSANTENNA, "Normal");
			break;
		case STATE_GPSANTENNA_ABNORMAL:
			adapter.UpdateSecond(STATE_GPSANTENNA, "Abnormal");
			break;
		case STATE_GPSANTENNA_NOTAVAILABLE:
			adapter.UpdateSecond(STATE_GPSANTENNA, "-");
			break;
		default:
			break;
		}
	}
	
	public void PositionUpdateDisplay(int PositionUpdate){
		switch (PositionUpdate) {
		case 0:
			adapter.UpdateSecond(STATE_POSITIONUPDATE, "Not OK");
			break;
		case 1:
			adapter.UpdateSecond(STATE_POSITIONUPDATE, "OK");
			break;
		case 3:
			adapter.UpdateSecond(STATE_POSITIONUPDATE, "-");
			break;
		default:
			break;
		}
	}
	
	public void GuardDisplay(int Guard){
		switch (Guard) {
		case STATE_MACHINEPOSITIONSTATUS_INSIDE:
			adapter.UpdateSecond(STATE_GUARD, "Inside of boundary");
			break;
		case STATE_MACHINEPOSITIONSTATUS_OUTSIDE:
			adapter.UpdateSecond(STATE_GUARD, "Outside of boundary");
			break;
		case STATE_MACHINEPOSITIONSTATUS_NOTAVAILABLE:
			adapter.UpdateSecond(STATE_GUARD, "-");
			break;
		default:
			break;
		}
	}
	
	public void LockLevelDisplay(int LockLevel){
		switch (LockLevel) {
		case STATE_LOCKLEVEL_UNLOCK:
			adapter.UpdateSecond(STATE_LOCKLEVEL, "Unlock");
			break;
			
		case 1:
			adapter.UpdateSecond(STATE_LOCKLEVEL, "Level 1");
			break;
			
		case 2:
			adapter.UpdateSecond(STATE_LOCKLEVEL, "Level 2");
			break;

		default:
			adapter.UpdateSecond(STATE_LOCKLEVEL, "-");
			break;
		}
	}
	////////////////////////////////////////////////
	
	////////////////////////////////////////////////
}
