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

public class VersionInfoAAVMFragment extends VersionInfoDetailFragment{
	/////////////////CONSTANT////////////////////////////////////////////

	
	/////////////////////////////////////////////////////////////////////
	/////////////////////VALUABLE////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////	
	
	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		TAG = "VersionInfoAAVMFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_AAVM;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.AAVM), 318, 505);
		return mRoot;
	}
	@Override
	protected void InitValuables(){
		super.InitValuables();
		
//		SoftwareIdentification = new byte[LENGTH_SOFTWAREIDENTIFICATION];
//		ComponentCode = 0;
//		ManufacturerCode = 0;
//		for(int i = 0; i < LENGTH_SOFTWAREIDENTIFICATION; i++){
//			SoftwareIdentification[i] = 0;
//		}

		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				getString(ParentActivity.getResources().getString(string.Program_Version), 277) ,"" , ""));
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),null,
				getString(ParentActivity.getResources().getString(string.Serial_Number), 278),"" , ""));
		listView.setAdapter(adapter);

	}
	@Override
	public void ShowHiddenPage(){
		adapter.addItem(new IconTextItemVersion(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),null,
				getString(ParentActivity.getResources().getString(string.Manufacturer), 279),"" , ""));
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_AAVO();
		ComponentBasicInformation = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_AAVO();
		ProgramSubVersion = ParentActivity.FindProgramSubInfo(ComponentBasicInformation);
		if(ManufactureDayDisplayFlag == true){
			ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_AAVO();
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ModelDisplay(ComponentBasicInformation);
		ManufactureDayDisplay(ComponentBasicInformation);
		if(ManufactureDayDisplayFlag == true)
			VersionDisplayHidden(ComponentBasicInformation,ProgramSubVersion);
		else
			VersionDisplay(ComponentBasicInformation,ProgramSubVersion);
		SerialNumberDisplay(ComponentBasicInformation);
		if(ManufactureDayDisplayFlag == true)
			ManufacturerDisplay(ManufacturerCode);
	
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
	////////////////////////////////////////////////
	////////////////////////////////////////////////
}
