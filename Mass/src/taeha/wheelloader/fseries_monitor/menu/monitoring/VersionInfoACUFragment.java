package taeha.wheelloader.fseries_monitor.menu.monitoring;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import customlist.versiondetail.IconTextItemVersion;

public class VersionInfoACUFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////
	private static final int LENGTH_SOFTWAREIDENTIFICATION = 6;
	
	private static final int STATE_MAKER 					= 0;
	private static final int STATE_SOFTWARE_VERSION			= 1;
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoACUFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ACU;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.FATC_System_Controller), 318, 472);
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

		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				getString(ParentActivity.getResources().getString(string.Manufacturer), 279) ,"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				getString(ParentActivity.getResources().getString(string.Program_Version), 277),"" , ""));
		listView.setAdapter(adapter);

	}
	@Override
	public void ShowHiddenPage(){
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				getString(ParentActivity.getResources().getString(string.EST37A_Part_Number), 290),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				getString(ParentActivity.getResources().getString(string.Hardware_Part_Number), 291), "" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
//				getString(ParentActivity.getResources().getString(string.Hardware_Serial_Number), 292),"" , ""));
//		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
//				getString(ParentActivity.getResources().getString(string.Customer_Serial_Number), 293), "" , ""));
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU();
		ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ACU();
		SoftwareIdentification = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_ACU();	
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ManufacturerDisplay(ManufacturerCode);
		VersionDisplay(SoftwareIdentification);
	
		adapter.notifyDataSetChanged();
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
	public void VersionDisplay(byte[] _data){
		int VersionHigh, VersionLow;
		String strVer;
		VersionHigh = ((_data[3] & 0xF0) >> 4);
		VersionLow = (_data[3] & 0x0F);

		if(VersionLow > 10 && VersionHigh >= 15){
			strVer = "";
		}else{
			strVer = Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow);
		}
		
		adapter.UpdateSecond(STATE_SOFTWARE_VERSION, strVer);
	}
	////////////////////////////////////////////////
	////////////////////////////////////////////////
}
