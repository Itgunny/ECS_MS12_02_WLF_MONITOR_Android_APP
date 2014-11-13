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

public class VersionInfoTCUFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	private static final int LENGTH_SOFTWAREIDENTIFICATION = 67;
	
	private static final int STATE_EST37A_PART_NUMBER 		= 0;
	private static final int STATE_HARDWARE_PART_NUMBER		= 1;
	private static final int STATE_SOFTWARE_VERSION			= 2;
	private static final int STATE_HARDWARE_SERIAL_NUMBER	= 3;
	private static final int STATE_CUSTOMER_SERIAL_NUMBER	= 4;
	private static final int STATE_MAKER					= 5;
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	int Est37APartNumberIndex;
	int HardwarePartNumberIndex;
	int SoftwareVersionIndex;
	int HardwareSerialNumberIndex;
	int CutomerSerialNumberIndex;
	int LastIndex;
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoTCUFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Version_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.TCU));
		return mRoot;
	}
	@Override
	protected void InitValuables(){
		super.InitValuables();
		
		SoftwareIdentification = new byte[LENGTH_SOFTWAREIDENTIFICATION];
		ComponentCode = 0;
		ManufacturerCode = 0;
		for(int i = 0; i < LENGTH_SOFTWAREIDENTIFICATION; i++){
			SoftwareIdentification[i] = 0;
		}

		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.EST37A_Part_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Hardware_Part_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Software_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Hardware_Serial_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Customer_Serial_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));

		listView.setAdapter(adapter);

	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_TCU();
		ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_TCU();
		SoftwareIdentification = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU();	
		ProgramSubVersion = ParentActivity.FindProgramSubInfo(ComponentBasicInformation);
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ManufacturerDisplay(ManufacturerCode);
		
		HardwarePartNumberIndex = SoftwareIDDisplay(SoftwareIdentification,1,12,STATE_EST37A_PART_NUMBER);
		SoftwareVersionIndex = SoftwareIDDisplay(SoftwareIdentification,HardwarePartNumberIndex,12,STATE_HARDWARE_PART_NUMBER);
		HardwareSerialNumberIndex = SoftwareIDDisplay(SoftwareIdentification,SoftwareVersionIndex,12,STATE_SOFTWARE_VERSION);
		CutomerSerialNumberIndex = SoftwareIDDisplay(SoftwareIdentification,HardwareSerialNumberIndex,9,STATE_HARDWARE_SERIAL_NUMBER);
		LastIndex = SoftwareIDDisplay(SoftwareIdentification,CutomerSerialNumberIndex,16,STATE_CUSTOMER_SERIAL_NUMBER);
	
		adapter.notifyDataSetChanged();
	}
	////////////////////////////////////////////////

	////////////////////////////////////////////////
	
	////////////////////////////////////////////////
}
