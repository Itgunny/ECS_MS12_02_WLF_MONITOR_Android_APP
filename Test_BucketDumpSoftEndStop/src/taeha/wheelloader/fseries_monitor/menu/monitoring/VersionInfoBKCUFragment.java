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

public class VersionInfoBKCUFragment extends VersionInfoDetailFragment{
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
		TAG = "VersionInfoBKCUFragment";
		Log.d(TAG, "onCreateView");
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Information)
				+ " - " + ParentActivity.getResources().getString(R.string.BKCU), 318, 273);
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
				getString(ParentActivity.getResources().getString(string.Program_Version), 277),"" , ""));
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
		ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_BKCU();
		if(ManufactureDayDisplayFlag == true)
			ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_BKCU();
		ComponentBasicInformation = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_BKCU();
		ProgramSubVersion = ParentActivity.FindProgramSubInfo(ComponentBasicInformation);
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
	public String getString(String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			Log.d(TAG, "Android");
			return str;
		}else {
			Log.d(TAG, "Excel");
			return ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex);	
		}
	}
	////////////////////////////////////////////////
	
	////////////////////////////////////////////////
}
