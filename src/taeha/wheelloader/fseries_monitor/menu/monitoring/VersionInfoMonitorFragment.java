package taeha.wheelloader.fseries_monitor.menu.monitoring;

import customlist.sensormonitoring.IconTextItem;
import customlist.versiondetail.IconTextItemVersion;
import customlist.versiondetail.IconTextListAdapterVersion;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
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

public class VersionInfoMonitorFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	protected static final int STATE_FIRMWARE_VERSION				 	= 3;
	/////////////////////////////////////////////////////////////////////
	TextView textViewHardwareData;
	RelativeLayout layoutHardware;
	/////////////////////VALUABLE////////////////////////////////////////
	
	int FirmwareVersionHigh;
	int FirmwareVersionLow;
	int FirmwareVersionSubHigh;
	int FirmwareVersionSubLow;
	int HWVersion;
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Version_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.Monitor));
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

		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Serial_Number),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				ParentActivity.getResources().getString(string.Manufacturer),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				ParentActivity.getResources().getString(string.Program_Version),"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
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
		HWVersion = CAN1Comm.Get_HWVersion();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ModelDisplay(ComponentBasicInformation);
		ManufactureDayDisplay(ComponentBasicInformation);
		HardwareDisplay(HWVersion);
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
	public void HardwareDisplay(int _data){
		if(_data > 357 && _data < 387){		// 10k
			textViewHardwareData.setText("RevB.02.01");
		}else if(_data > 390 && _data < 420){	// 9.1k
			textViewHardwareData.setText("RevD.02.01");
		}else if(_data > 499 && _data < 551){	// 6.8k
			textViewHardwareData.setText("RevD.03.01");
		}else if(_data > 596 && _data < 626){	// 5.7k
			textViewHardwareData.setText("RevD.04.01");
		}		
		else{
			textViewHardwareData.setText("-");
		}
	}
	////////////////////////////////////////////////
	@Override
	public void ShowManufactureDay(final boolean flag){
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
}
