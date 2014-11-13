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

public class VersionInfoECMFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	private static final int LENGTH_SOFTWAREIDENTIFICATION = 200;
	
	protected static final int STATE_ECM_PART_NUMBER_CUMMINS				= 0;
	protected static final int STATE_ECM_SERIAL_NUMBER_CUMMINS				= 1;
	protected static final int STATE_SOFTWARE_DATA_DATE_STAMP_CUMMINS		= 2;
	protected static final int STATE_CALIBRATION_VERSION_NUMBER_CUMMINS		= 3;
	protected static final int STATE_ECM_IDENTIFIER_CUMMINS					= 4;
	protected static final int STATE_PRODUCT_ID_CUMMINS						= 5;
	protected static final int STATE_MAKER_CUMMINS							= 6;
	
	protected static final int STATE_ECU_PART_NUMBER_SCANIA					= 0;
	protected static final int STATE_ECU_SERIAL_NUMBER_SCANIA				= 1;
	protected static final int STATE_ECU_LOCATION_SCANIA					= 2;
	protected static final int STATE_ECU_TYPE_SCANIA						= 3;
	protected static final int STATE_ECU_MANUFACTURER_NAMESCANIA			= 4;
	//protected static final int STATE_MAKER_SCANIA							= 5;
	
	protected static final int STATE_ECU_CALIBRATION_VERSION_NUMBER			= 0;
	protected static final int STATE_ECU_CALIBRATION_ID						= 1;
	protected static final int STATE_MAKER_SCANIA							= 2;
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	int ECMPartNumberIndex;
	int ECMSerialNumberIndex;
	int SoftwareDataDateStampIndex;
	int CalibrationVersionNumberIndex;
	int ECMIdentifierIndex;
	int ProductIDIndex;
	int LastIndex;
	
	int ECUPartNumberIndex;
	int ECUSerialNumberIndex;
	int ECULocationIndex;
	int ECUTypeIndex;
	int ECUManufacturerNameIndex;
	
	int ECUCalibrationVersionNumberIndex;
	int ECUCalibrationIDIndex;
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoECMFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Version_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.ECM));
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

		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_ECM();
		ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM();
		SoftwareIdentification = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_ECM();
		
		textViewModelData.setText("");

		listView.setAdapter(adapter);

	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_ECM();
		ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM();
		SoftwareIdentification = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_ECM();	
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ECMDisplay();
	}
	////////////////////////////////////////////////
	public void ECMDisplay(){
		
		adapter.clearItem();
		switch (ManufacturerCode) {
		case STATE_MANUFACTURERCODE_CUMMINS:
			
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.ECM_Part_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.ECM_Serial_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Software_Data_Date_Stamp),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Product_ID),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
	
			ECMSerialNumberIndex = SoftwareIDDisplay(SoftwareIdentification,1,8,STATE_ECM_PART_NUMBER_CUMMINS);
			SoftwareDataDateStampIndex = SoftwareIDDisplay(SoftwareIdentification,ECMSerialNumberIndex,8,STATE_ECM_SERIAL_NUMBER_CUMMINS);
			CalibrationVersionNumberIndex = SoftwareIDDisplay(SoftwareIdentification,SoftwareDataDateStampIndex,12,STATE_SOFTWARE_DATA_DATE_STAMP_CUMMINS);
			ECMIdentifierIndex = SoftwareIDDisplay(SoftwareIdentification,CalibrationVersionNumberIndex,8,STATE_CALIBRATION_VERSION_NUMBER_CUMMINS);
			ProductIDIndex = SoftwareIDDisplay(SoftwareIdentification,ECMIdentifierIndex,2,STATE_ECM_IDENTIFIER_CUMMINS);
			LastIndex = SoftwareIDDisplay(SoftwareIdentification,ProductIDIndex,3,STATE_PRODUCT_ID_CUMMINS);
			adapter.notifyDataSetChanged();		
			
			ManufacturerDisplay(STATE_MANUFACTURERCODE_CUMMINS);
			break;
		case STATE_MANUFACTURERCODE_SCANIA:

			
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Calibration_ID),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
	
			ScaniaECMDisplay(SoftwareIdentification);
			adapter.notifyDataSetChanged();		
			
			ManufacturerDisplay(STATE_MANUFACTURERCODE_SCANIA);
			break;
			
		default:
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.ECM_Part_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.ECM_Serial_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Software_Data_Date_Stamp),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Product_ID),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.notifyDataSetChanged();		
			
			break;
		}	
	}

	public void ScaniaECMDisplay(byte[] _softwareid){
		String strCaliVersionNum;
		int nCalVersionNum;
		
		nCalVersionNum = (int)(_softwareid[0] & 0xFF) + ((int)(_softwareid[1] & 0xFF) << 8)  + ((int)(_softwareid[2] & 0xFF) << 16) + ((int)(_softwareid[3] & 0xFF) << 24);

		strCaliVersionNum = "0x" + Integer.toHexString(nCalVersionNum); 
		adapter.UpdateSecond(STATE_ECU_CALIBRATION_VERSION_NUMBER,strCaliVersionNum);
		
		
		char[] cString;
		String strCaliID;
		cString = new char[16];
		for(int i = 0; i < 16; i++){
			cString[i] = (char) _softwareid[i+4];
		}
		strCaliID = new String(cString,0,cString.length);
		adapter.UpdateSecond(STATE_ECU_CALIBRATION_ID,strCaliID);
	}
	////////////////////////////////////////////////
	
	////////////////////////////////////////////////
}
