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
	
//	protected static final int STATE_ECM_PART_NUMBER_CUMMINS				= 0;
//	protected static final int STATE_ECM_SERIAL_NUMBER_CUMMINS				= 1;
//	protected static final int STATE_SOFTWARE_DATA_DATE_STAMP_CUMMINS		= 2;
//	protected static final int STATE_CALIBRATION_VERSION_NUMBER_CUMMINS		= 3;
//	protected static final int STATE_ECM_IDENTIFIER_CUMMINS					= 4;
//	protected static final int STATE_PRODUCT_ID_CUMMINS						= 5;
//	protected static final int STATE_MAKER_CUMMINS							= 6;
	protected static final int STATE_MAKER_CUMMINS							= 0;
	protected static final int STATE_CALIBRATION_VERSION_NUMBER_CUMMINS		= 1;
	protected static final int STATE_ECM_PART_NUMBER_CUMMINS				= 2;
	protected static final int STATE_ECM_SERIAL_NUMBER_CUMMINS				= 3;
	protected static final int STATE_SOFTWARE_DATA_DATE_STAMP_CUMMINS		= 4;
	protected static final int STATE_ECM_IDENTIFIER_CUMMINS					= 5;
	protected static final int STATE_PRODUCT_ID_CUMMINS						= 6;

	
	protected static final int STATE_ECU_PART_NUMBER_SCANIA					= 0;
	protected static final int STATE_ECU_SERIAL_NUMBER_SCANIA				= 1;
	protected static final int STATE_ECU_LOCATION_SCANIA					= 2;
	protected static final int STATE_ECU_TYPE_SCANIA						= 3;
	protected static final int STATE_ECU_MANUFACTURER_NAMESCANIA			= 4;
	//protected static final int STATE_MAKER_SCANIA							= 5;
	
//	protected static final int STATE_ECU_CALIBRATION_VERSION_NUMBER			= 0;
//	protected static final int STATE_ECU_CALIBRATION_ID						= 1;
//	protected static final int STATE_MAKER_SCANIA							= 2;
	protected static final int STATE_MAKER_SCANIA							= 0;
	protected static final int STATE_ECU_CALIBRATION_VERSION_NUMBER			= 1;
	protected static final int STATE_ECU_CALIBRATION_ID						= 2;
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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
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
			
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.ECM_Part_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.ECM_Serial_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.Software_Data_Date_Stamp),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.ECM_Identifier),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.Product_ID),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
			if(ManufactureDayDisplayFlag == true){
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.ECM_Part_Number),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
						ParentActivity.getResources().getString(string.ECM_Serial_Number),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.Software_Data_Date_Stamp),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
						ParentActivity.getResources().getString(string.ECM_Identifier),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.Product_ID),"" , ""));
			}
			
			if(ManufactureDayDisplayFlag == true){
				ECMSerialNumberIndex = SoftwareIDDisplay(SoftwareIdentification,1,8,STATE_ECM_PART_NUMBER_CUMMINS);
				SoftwareDataDateStampIndex = SoftwareIDDisplay(SoftwareIdentification,ECMSerialNumberIndex,8,STATE_ECM_SERIAL_NUMBER_CUMMINS);
				CalibrationVersionNumberIndex = SoftwareIDDisplay(SoftwareIdentification,SoftwareDataDateStampIndex,12,STATE_SOFTWARE_DATA_DATE_STAMP_CUMMINS);
				ECMIdentifierIndex = SoftwareIDDisplay(SoftwareIdentification,CalibrationVersionNumberIndex,8,STATE_CALIBRATION_VERSION_NUMBER_CUMMINS);
				ProductIDIndex = SoftwareIDDisplay(SoftwareIdentification,ECMIdentifierIndex,2,STATE_ECM_IDENTIFIER_CUMMINS);
				LastIndex = SoftwareIDDisplay(SoftwareIdentification,ProductIDIndex,3,STATE_PRODUCT_ID_CUMMINS);
			}
			else
				SoftwareIDDisplay(SoftwareIdentification,32,8,STATE_CALIBRATION_VERSION_NUMBER_CUMMINS);

			adapter.notifyDataSetChanged();		
			
			ManufacturerDisplay(STATE_MANUFACTURERCODE_CUMMINS);
			break;
		case STATE_MANUFACTURERCODE_SCANIA:

			
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.Calibration_ID),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
			if(ManufactureDayDisplayFlag == true){
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.Calibration_ID),"" , ""));
			}
	
			ScaniaECMDisplay(SoftwareIdentification);
			adapter.notifyDataSetChanged();		
			
			ManufacturerDisplay(STATE_MANUFACTURERCODE_SCANIA);
			break;
			
		default:
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.ECM_Part_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.ECM_Serial_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.Software_Data_Date_Stamp),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.ECM_Identifier),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//					ParentActivity.getResources().getString(string.Product_ID),"" , ""));
//			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
					ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
			adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
					ParentActivity.getResources().getString(string.Calibration_Version_Number),"" , ""));
			if(ManufactureDayDisplayFlag == true){
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.ECM_Part_Number),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
						ParentActivity.getResources().getString(string.ECM_Serial_Number),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.Software_Data_Date_Stamp),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
						ParentActivity.getResources().getString(string.ECM_Identifier),"" , ""));
				adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
						ParentActivity.getResources().getString(string.Product_ID),"" , ""));
			}
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
		
		if(ManufactureDayDisplayFlag == true)
		{
			char[] cString;
			String strCaliID;
			cString = new char[16];
			for(int i = 0; i < 16; i++){
				cString[i] = (char) _softwareid[i+4];
			}
			strCaliID = new String(cString,0,cString.length);
			adapter.UpdateSecond(STATE_ECU_CALIBRATION_ID,strCaliID);
		}
	}
	
	public void ManufacturerDisplay(int Code){
		int Index = 0;
		if(Code == STATE_MANUFACTURERCODE_CUMMINS){
			Index = STATE_MAKER_CUMMINS;
		}else if(Code == STATE_MANUFACTURERCODE_SCANIA){
			Index = STATE_MAKER_SCANIA;
		}
		else{
			Index = STATE_MAKER_CUMMINS;
		}
		switch (Code) {
		case STATE_MANUFACTURERCODE_TAEHA:
			adapter.UpdateSecond(Index, "Taeha");
			break;
		case STATE_MANUFACTURERCODE_FREEMS:
			adapter.UpdateSecond(Index, "FreeMs Corp.");
			break;
		case STATE_MANUFACTURERCODE_KYUNGWOO:
			adapter.UpdateSecond(Index, "KYUNGWOO");
			break;
		case STATE_MANUFACTURERCODE_DONHWAN:
			adapter.UpdateSecond(Index, "DongHwan");
			break;
		case STATE_MANUFACTURERCODE_CONTINENTAL:
			adapter.UpdateSecond(Index, "Continental");
			break;
		case STATE_MANUFACTURERCODE_ZF:
			adapter.UpdateSecond(Index, "ZF");
			break;
		case STATE_MANUFACTURERCODE_SAUNERDANFOSS:
			adapter.UpdateSecond(Index, "Danfoss Power Solution");
			break;
		case STATE_MANUFACTURERCODE_FIND:
			adapter.UpdateSecond(Index, "FIND");
			break;
		case STATE_MANUFACTURERCODE_GIMIS:
			adapter.UpdateSecond(Index, "GIMIS");
			break;
		case STATE_MANUFACTURERCODE_HMC:
			adapter.UpdateSecond(Index, "HMC");
			break;
		case STATE_MANUFACTURERCODE_CUMMINS:
			adapter.UpdateSecond(Index, "Cummins");
			break;
		case STATE_MANUFACTURERCODE_MITSUBISHI:
			adapter.UpdateSecond(Index, "Mitsubishi");
			break;
		case STATE_MANUFACTURERCODE_YANMAR:
			adapter.UpdateSecond(Index, "Yanmar");
			break;
		case STATE_MANUFACTURERCODE_PERKINS:
			adapter.UpdateSecond(Index, "Perkins");
			break;
		case STATE_MANUFACTURERCODE_SCANIA:
			adapter.UpdateSecond(Index, "Scania");
			break;
		case 0xFF:
			adapter.UpdateSecond(Index, "-");
			break;
		default:
			adapter.UpdateSecond(Index, "-");
			break;
		}
	}
	////////////////////////////////////////////////
	
	////////////////////////////////////////////////
}
