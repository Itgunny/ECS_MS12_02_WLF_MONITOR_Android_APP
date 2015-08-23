package taeha.wheelloader.fseries_monitor.menu.monitoring;

import customlist.sensormonitoring.IconTextItem;
import customlist.versiondetail.IconTextItemVersion;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.R.integer;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class VersionInfoClusterFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	protected static final int STATE_HWVERSION		 				= 3;
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	int HWVersion;
	int HWVersionSub;
	boolean HiddenVersionFlag;
	int AppVersion;
	int AppVersionSub;
	int AppVersionHidden;
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoClusterFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.Cluster));
		return mRoot;
	}
	@Override
	protected void InitValuables(){
		super.InitValuables();

//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));

		listView.setAdapter(adapter);

		HiddenVersionFlag = false;

	}
	@Override
	public void ShowHiddenPage(){
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Hardware),"" , ""));
	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_CLUSTER();
		if(ManufactureDayDisplayFlag == true)
		{
			ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_CLUSTER();
			HWVersion = CAN1Comm.Get_Dashboard_Hardware_Version_989_PGN65445();
			HWVersionSub = CAN1Comm.Get_HW_Vers_Sub_PGN65445();
		}
		ComponentBasicInformation = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_CLUSTER();
		ProgramSubVersion = ParentActivity.FindProgramSubInfo(ComponentBasicInformation);
		
		if(HiddenVersionFlag == true)
		{
			AppVersion = CAN1Comm.Get_Dashboard_Program_Version_988_PGN65445();
			AppVersionSub = CAN1Comm.Get_Dashboard_Program_Version2_PGN65445();
			AppVersionHidden = CAN1Comm.Get_Dashboard_Program_Version_H_PGN65445();
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ModelDisplay(ComponentBasicInformation);
		ManufactureDayDisplay(ComponentBasicInformation);
		if(HiddenVersionFlag == false)
		{
			if(ManufactureDayDisplayFlag == true)
				VersionDisplayHidden(ComponentBasicInformation,ProgramSubVersion);
			else
				VersionDisplay(ComponentBasicInformation,ProgramSubVersion);
		}
		else
			VersionDisplayTaehaHidden(AppVersion,AppVersionSub, AppVersionHidden);
		SerialNumberDisplay(ComponentBasicInformation);
		if(ManufactureDayDisplayFlag == true)
		{
			ManufacturerDisplay(ManufacturerCode);
			HardwareDisplay(HWVersion, HWVersionSub);
		}

		adapter.notifyDataSetChanged();
	}
	////////////////////////////////////////////////
	public void HardwareDisplay(int _HWVersion, int _HWVersionSub){
		String strVersion = null;
		
		
		if(_HWVersion == 0xFF || _HWVersionSub == 0xFF)
		{
			adapter.UpdateSecond(STATE_HWVERSION, "-");
			Log.d(TAG, "HWVersion"+strVersion);
		}
		else
		{
			adapter.UpdateSecond(STATE_HWVERSION, strVersion.format("Rev%X.%02X.%02X", (_HWVersion & 0xF0)>>4, (_HWVersion & 0x0F),_HWVersionSub));
		}
	}
	////////////////////////////////////////////////
	public void ShowMonitorHiddenVersion(){
		HiddenVersionFlag = true;
		ShowManufactureDay(true);
	}	
	////////////////////////////////////////////////
}
