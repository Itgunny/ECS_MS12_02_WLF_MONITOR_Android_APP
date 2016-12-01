package taeha.wheelloader.fseries_monitor.menu.monitoring;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LanguageDB;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import customlist.versiondetail.IconTextItemVersion;
import customlist.versiondetail.IconTextListAdapterVersion;

public class VersionInfoMonitorFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	protected static final int STATE_FIRMWARE_VERSION			= 1;
	protected static final int STATE_SERIALNUMBER				= 2;
	protected static final int STATE_MAKER						= 3;
	/////////////////////////////////////////////////////////////////////
	TextView textViewHardwareData;
	RelativeLayout layoutHardware;
	/////////////////////VALUABLE////////////////////////////////////////
	
	int FirmwareVersionHigh;
	int FirmwareVersionLow;
	int FirmwareVersionSubHigh;
	int FirmwareVersionSubLow;
	int FirmwareVersionHidden;
	int HWVersion;
	boolean HiddenVersionFlag;
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 TAG = "VersionInfoDetailFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_version_monitor, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ShowManufactureDay(ManufactureDayDisplayFlag);
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.Monitor), 318, 268);
		return mRoot;
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_version_monitor_low_ok);

		textViewModelData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_model_data);
		textViewDateData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_date_data);
		textViewHardwareData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_monitor_hw_data);
		
		layoutModel = (RelativeLayout)mRoot.findViewById(R.id.Relativelayout_menu_body_monitoring_version_monitor_model);
		layoutDate = (RelativeLayout)mRoot.findViewById(R.id.Relativelayout_menu_body_monitoring_version_monitor_date);
		layoutHardware = (RelativeLayout)mRoot.findViewById(R.id.Relativelayout_menu_body_monitoring_version_monitor_hw);
		
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_monitoring_version_monitor);
		
		adapter = new IconTextListAdapterVersion(ParentActivity);
		adapter.clearItem();

	}
	@Override
	protected void InitValuables(){
		super.InitValuables();

//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				ParentActivity.getResources().getString(string.Firmware_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				getString(ParentActivity.getResources().getString(string.Program_Version), 277) ,"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				getString(ParentActivity.getResources().getString(string.Firmware_Version), 289), "" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				getString(ParentActivity.getResources().getString(string.Serial_Number), 278),"" , ""));
		
		
	
		listView.setAdapter(adapter);
		
		HiddenVersionFlag = false;

	}
	@Override
	public void ShowHiddenPage(){
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				getString(ParentActivity.getResources().getString(string.Manufacturer), 279),"" , ""));
	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = GetMonitorComponentCode();
		ComponentBasicInformation = ParentActivity.GetMonitorComponentBasicInfo();
		FirmwareVersionHigh = CAN1Comm.Get_FirmwareVersionHigh();
		FirmwareVersionLow = CAN1Comm.Get_FirmwareVersionLow();
		FirmwareVersionSubHigh = CAN1Comm.Get_FirmwareVersionSubHigh();
		FirmwareVersionSubLow = CAN1Comm.Get_FirmwareVersionSubLow();
		FirmwareVersionHidden = CAN1Comm.Get_FirmwareVersionHidden();
		HWVersion = CAN1Comm.Get_HWVersion();
		if(ManufactureDayDisplayFlag == true)
			ManufacturerCode = GetMonitorManufacturerCode();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ModelDisplay(ComponentBasicInformation);
		ManufactureDayDisplay(ComponentBasicInformation);
		HardwareDisplay(HWVersion);
		SerialNumberDisplay(ComponentBasicInformation);
		if(HiddenVersionFlag == false)
		{
			if(ManufactureDayDisplayFlag == true)
				ApplicationHiddenVersionDisplay(ParentActivity.VERSION_HIGH,ParentActivity.VERSION_LOW
						,ParentActivity.VERSION_SUB_HIGH,ParentActivity.VERSION_SUB_LOW);
			else
				ApplicationVersionDisplay(ParentActivity.VERSION_HIGH,ParentActivity.VERSION_LOW
						,ParentActivity.VERSION_SUB_HIGH);
		}
		else
			ApplicationVersionDisplayTaehaHidden(ParentActivity.VERSION_HIGH,ParentActivity.VERSION_LOW
					,ParentActivity.VERSION_SUB_HIGH,ParentActivity.VERSION_SUB_LOW, ParentActivity.VERSION_TAEHA);
			
		if(HiddenVersionFlag == false)
		{
			if(ManufactureDayDisplayFlag == true)
				FirmwareHiddenVersionDisplay(FirmwareVersionHigh,FirmwareVersionLow,FirmwareVersionSubHigh,FirmwareVersionSubLow);
			else
				FirmwareVersionDisplay(FirmwareVersionHigh,FirmwareVersionLow,FirmwareVersionSubHigh);
		}
		else
			FirmwareVersionDisplayTaehaHidden(FirmwareVersionHigh,FirmwareVersionLow,FirmwareVersionSubHigh,FirmwareVersionSubLow,FirmwareVersionHidden);
		
		if(ManufactureDayDisplayFlag == true)
			ManufacturerDisplay(ManufacturerCode);
		
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
	public void SerialNumberDisplay(byte[] BasicInfo)throws NullPointerException{
		boolean DataCheckFlag = true;
		String strSerial;
		int Index = 0;
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
			}
			else{
				
				break;
			}
		}
		
		char[] Serial;
		Serial = new char [Index];
		int[] Temp;
		Temp = new int[Index];
		
		for(int i = 0; i < Index; i++){
			Serial[i] = (char) BasicInfo[i+4];
			Temp[i] = (int)(BasicInfo[i+4] & 0xFF);
			if(Temp[i] > 254){
				DataCheckFlag = false;
			}
		}
		strSerial = new String(Serial,0,Serial.length);
		if(DataCheckFlag == false){
			adapter.UpdateSecond(STATE_SERIALNUMBER, "-");
		}else{
			adapter.UpdateSecond(STATE_SERIALNUMBER, strSerial);
		}
		
	}
	public void ApplicationVersionDisplay(int VersionHigh, int VersionLow, int VersionSubHigh){
		adapter.UpdateSecond(STATE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow)
				+ "." + Integer.toHexString(VersionSubHigh));
	}
	public void ApplicationHiddenVersionDisplay(int VersionHigh, int VersionLow, int VersionSubHigh, int VersionSubLow){
		adapter.UpdateSecond(STATE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow)
				+ "." + Integer.toHexString(VersionSubHigh) + "." + Integer.toHexString(VersionSubLow));
	}
	
	public void ApplicationVersionDisplayTaehaHidden(int VersionHigh, int VersionLow, int VersionSubHigh, int VersionSubLow, int VersionTaeha){
		if(LanguageDB.LanguageVersion1 == null){
			adapter.UpdateSecond(STATE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow)
					+ "." + Integer.toHexString(VersionSubHigh) + "." + Integer.toHexString(VersionSubLow) + Integer.toHexString(VersionTaeha)
					+ "(Language Ver : Android)");
			Log.d(TAG, "Android");
		}else {
			adapter.UpdateSecond(STATE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow)
					+ "." + Integer.toHexString(VersionSubHigh) + "." + Integer.toHexString(VersionSubLow) + Integer.toHexString(VersionTaeha)
					+ "(Language Ver : " + LanguageDB.LanguageVersion1 + "." + LanguageDB.LanguageVersion2 + "." + LanguageDB.LanguageVersion3 + ")");
			Log.d(TAG, "Excel");
		}

	}	
	public void FirmwareVersionDisplay(int VersionHigh, int VersionLow, int SubVersionHigh){
		adapter.UpdateSecond(STATE_FIRMWARE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow) 
				+ "." + Integer.toHexString(SubVersionHigh));
	}
	public void FirmwareHiddenVersionDisplay(int VersionHigh, int VersionLow, int SubVersionHigh, int SubVersionLow){
		adapter.UpdateSecond(STATE_FIRMWARE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow) 
				+ "." + Integer.toHexString(SubVersionHigh) + "." + Integer.toHexString(SubVersionLow));
	}
	public void FirmwareVersionDisplayTaehaHidden(int VersionHigh, int VersionLow, int VersionSubHigh, int VersionSubLow, int VersionHidden){
		adapter.UpdateSecond(STATE_FIRMWARE_VERSION, Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow)
				+ "." + Integer.toHexString(VersionSubHigh) + "." + Integer.toHexString(VersionSubLow) + Integer.toHexString(VersionHidden));
	}
	public void HardwareDisplay(int _data){
		if(_data > 357 && _data < 387){		// 10k 372
			textViewHardwareData.setText("RevB.02.01");
		}else if(_data > 390 && _data < 420){	// 9.1k 405
			textViewHardwareData.setText("RevD.02.01");
		}else if(_data > 499 && _data < 551){	// 6.8k 525
			textViewHardwareData.setText("RevD.03.01");
		}else if(_data > 596 && _data < 626){	// 5.7k 611
			textViewHardwareData.setText("RevD.04.01");
		}else if(_data > 688 && _data < 740){	// 4.7k	718
			textViewHardwareData.setText("RevF.01.01");
		}else if(_data > 805 && _data < 865){	// 3.9k	835
			textViewHardwareData.setText("RevF.03.01");
		}else if(_data > 865 && _data < 920){	// 3.6k	890
			textViewHardwareData.setText("RevF.04.01");
		}else if(_data > 1082 && _data < 1132){ // 2.7K 1107
			textViewHardwareData.setText("RevH.01.01");
		}
		else{
			Log.d(TAG,"HardwareDisplay:"+_data);
			textViewHardwareData.setText("-");
		}
	}
	////////////////////////////////////////////////
	public void ManufacturerDisplay(int Code){
		switch (Code) {
		case STATE_MANUFACTURERCODE_TAEHA:
			adapter.UpdateSecond(STATE_MAKER, "Taeha");
			break;
		case STATE_MANUFACTURERCODE_FREEMS:
			adapter.UpdateSecond(STATE_MAKER, "FreeMs Corp.");
			break;
		case STATE_MANUFACTURERCODE_KYUNGWOO:
			adapter.UpdateSecond(STATE_MAKER, "KYUNGWOO");
			break;
		case STATE_MANUFACTURERCODE_DONHWAN:
			adapter.UpdateSecond(STATE_MAKER, "DongHwan");
			break;
		case STATE_MANUFACTURERCODE_CONTINENTAL:
			adapter.UpdateSecond(STATE_MAKER, "Continental");
			break;
		case STATE_MANUFACTURERCODE_ZF:
			adapter.UpdateSecond(STATE_MAKER, "ZF");
			break;
		case STATE_MANUFACTURERCODE_SAUNERDANFOSS:
			adapter.UpdateSecond(STATE_MAKER, "Danfoss Power Solution");
			break;
		case STATE_MANUFACTURERCODE_FIND:
			adapter.UpdateSecond(STATE_MAKER, "FIND");
			break;
		case STATE_MANUFACTURERCODE_GIMIS:
			adapter.UpdateSecond(STATE_MAKER, "GIMIS");
			break;
		case STATE_MANUFACTURERCODE_HMC:
			adapter.UpdateSecond(STATE_MAKER, "HMC");
			break;
		case STATE_MANUFACTURERCODE_CUMMINS:
			adapter.UpdateSecond(STATE_MAKER, "Cummins");
			break;
		case STATE_MANUFACTURERCODE_MITSUBISHI:
			adapter.UpdateSecond(STATE_MAKER, "Mitsubishi");
			break;
		case STATE_MANUFACTURERCODE_YANMAR:
			adapter.UpdateSecond(STATE_MAKER, "Yanmar");
			break;
		case STATE_MANUFACTURERCODE_PERKINS:
			adapter.UpdateSecond(STATE_MAKER, "Perkins");
			break;
		case STATE_MANUFACTURERCODE_SCANIA:
			adapter.UpdateSecond(STATE_MAKER, "Scania");
			break;
		case 0xFF:
			adapter.UpdateSecond(STATE_MAKER, "-");
			break;
		default:
			adapter.UpdateSecond(STATE_MAKER, "-");
			break;
		}
	}
	@Override
	public void ShowManufactureDay(final boolean flag){
		if(ManufactureDayDisplayFlag == flag && flag == true)
			return;
		
		ManufactureDayDisplayFlag = flag;
		
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(flag == true){
					layoutModel.setVisibility(View.VISIBLE);
					layoutDate.setVisibility(View.VISIBLE);
					layoutHardware.setVisibility(View.VISIBLE);
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)listView.getLayoutParams();
					params.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
					listView.setLayoutParams(params); //causes layout update
					ShowHiddenPage();
				}
					
				else{
					layoutModel.setVisibility(View.INVISIBLE);
					layoutDate.setVisibility(View.INVISIBLE);
					layoutHardware.setVisibility(View.INVISIBLE);
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)listView.getLayoutParams();
					params.addRule(RelativeLayout.ALIGN_PARENT_TOP,1);
					listView.setLayoutParams(params); //causes layout update
				}
			}
		});
	}
	
	public void ShowMonitorHiddenVersion(){
		HiddenVersionFlag = true;
		ShowManufactureDay(true);
	}	
}
