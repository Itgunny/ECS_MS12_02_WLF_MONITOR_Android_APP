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

public class VersionInfoMonitorFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	protected static final int STATE_FIRMWARE_VERSION				 	= 3;
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	
	int FirmwareVersionHigh;
	int FirmwareVersionLow;
	int FirmwareVersionSubHigh;
	int FirmwareVersionSubLow;
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoMonitorFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Version_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.Monitor));
		return mRoot;
	}
	@Override
	protected void InitValuables(){
		super.InitValuables();

		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Firmware_Version),"" , ""));
		
		
	
		listView.setAdapter(adapter);

	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = GetMonitorComponentCode();
		ManufacturerCode = GetMonitorManufacturerCode();
		ComponentBasicInformation = ParentActivity.GetMonitorComponentBasicInfo();
		
		FirmwareVersionHigh = CAN1Comm.Get_FirmwareVersionHigh();
		FirmwareVersionLow = CAN1Comm.Get_FirmwareVersionLow();
		FirmwareVersionSubHigh = CAN1Comm.Get_FirmwareVersionSubHigh();
		FirmwareVersionSubLow = CAN1Comm.Get_FirmwareVersionSubLow();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ModelDisplay(ComponentBasicInformation);
		ManufactureDayDisplay(ComponentBasicInformation);
		SerialNumberDisplay(ComponentBasicInformation);
		ManufacturerDisplay(ManufacturerCode);
		ApplicationVersionDisplay(ParentActivity.VERSION_HIGH,ParentActivity.VERSION_LOW
								,ParentActivity.VERSION_SUB_HIGH,ParentActivity.VERSION_SUB_LOW);
		FirmwareVersionDisplay(FirmwareVersionHigh,FirmwareVersionLow,FirmwareVersionSubHigh,FirmwareVersionSubLow);
		adapter.notifyDataSetChanged();
	}
	////////////////////////////////////////////////
	public int GetMonitorComponentCode(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("CID", 0);
		return SharePref.getInt("ComponentCode_Monitor", 0xFF);
	}
	
	public int GetMonitorManufacturerCode(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("CID", 0);
		return SharePref.getInt("ManufacturerCode_Monitor", 0xFF);
	}
	////////////////////////////////////////////////
	public void ApplicationVersionDisplay(int VersionHigh, int VersionLow, int VersionSubHigh, int VersionSubLow){
		adapter.UpdateSecond(STATE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow)
				+ "." + Integer.toHexString(VersionSubHigh) + "." + Integer.toHexString(VersionSubLow));
	}
	public void FirmwareVersionDisplay(int VersionHigh, int VersionLow, int SubVersionHigh, int SubVersionLow){
		adapter.UpdateSecond(STATE_FIRMWARE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow) 
				+ "." + Integer.toHexString(SubVersionHigh) + "." + Integer.toHexString(SubVersionLow));
	}
	////////////////////////////////////////////////
}
