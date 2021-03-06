package taeha.wheelloader.fseries_monitor.menu.monitoring;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.R.color;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MachineMonitoringFragment1 extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextFitTextView 	textViewOK;
	

	TextFitTextView 	textViewHYDTitle;
	TextFitTextView 	textViewBattTitle;
	TextFitTextView 	textViewCoolantTitle;
	TextFitTextView 	textViewTMOilTitle;
	
	TextFitTextView 	textViewHYD;
	TextFitTextView 	textViewBatt;
	TextFitTextView 	textViewCoolant;
	TextFitTextView 	textViewTMOil;
	
	TextFitTextView 	textViewHYDUnit;
	TextFitTextView 	textViewBattUnit;
	TextFitTextView 	textViewCoolantUnit;
	TextFitTextView 	textViewTMOilUnit;
	
	RelativeLayout	layoutHYD;
	RelativeLayout	layoutBatt;
	RelativeLayout	layoutCoolant;
	RelativeLayout	layoutTMOil;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int HYD;
	int Battery;
	int TMOil;
	int Coolant;
	
	int HYDHighWarning;
	int BatteryLowWarning;
	int TMOilHighWarning;
	int CoolantHighWarning;
	
	int CursurIndex;
	Handler HandleCursurDisplay;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 TAG = "MachineMonitoringFragment1";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_machinemonitoring1, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_1;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Monitoring), 253);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_machinemonitoring_low_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		textViewHYDTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_hyd_title);
		textViewHYDTitle.setText(getString(ParentActivity.getResources().getString(R.string.HYD_Temp), 111));
		textViewBattTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_battery_title);
		textViewBattTitle.setText(getString(ParentActivity.getResources().getString(R.string.Battery_Volt), 113));
		textViewCoolantTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_coolant_title);
		textViewCoolantTitle.setText(getString(ParentActivity.getResources().getString(R.string.Coolant_Temp), 115));
		textViewTMOilTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_tmoil_title);
		textViewTMOilTitle.setText(getString(ParentActivity.getResources().getString(R.string.TM_Oil_Temp), 112));
		
		textViewHYD = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_hyd_data);
		textViewBatt = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_battery_data);
		textViewCoolant = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_coolant_data);
		textViewTMOil = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_tmoil_data);
		
		textViewHYDUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_hyd_unit);
		textViewHYDUnit.setText(getString(ParentActivity.getResources().getString(R.string.C), 8));
		textViewBattUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_battery_unit);
		textViewBattUnit.setText(getString(ParentActivity.getResources().getString(R.string.V), 10));
		textViewCoolantUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_coolant_unit);
		textViewCoolantUnit.setText(getString(ParentActivity.getResources().getString(R.string.C), 8));
		textViewTMOilUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_machinemonitoring_tmoil_unit);
		textViewTMOilUnit.setText(getString(ParentActivity.getResources().getString(R.string.C), 8));
		
		layoutHYD = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_machinemonitoring_hyd);
		layoutBatt = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_machinemonitoring_battery);
		layoutCoolant = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_machinemonitoring_coolant);
		layoutTMOil = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_machinemonitoring_tmoil);
		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		HYD = CAN1Comm.Get_HydraulicOilTemperature_101_PGN65431();
		Battery = CAN1Comm.Get_BatteryVoltage_705_PGN65431();
		Coolant = CAN1Comm.Get_EngineCoolantTemperature_304_PGN65431();
		TMOil = CAN1Comm.Get_TransmissionOilTemperature_536_PGN65431();
		HYDHighWarning = CAN1Comm.Get_HydraulicOilTemperatureHigh_102_PGN65427();
		BatteryLowWarning = CAN1Comm.Get_BatteryVoltageLow_706_PGN65427();
		CoolantHighWarning = CAN1Comm.Get_EngineCoolantTemperatureHigh_305_PGN65427();
		TMOilHighWarning = CAN1Comm.Get_TransmissionOilTemperatureHigh_537_PGN65427();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		HYDDisplay(HYD,HYDHighWarning,ParentActivity.UnitTemp);
		BatteryDisplay(Battery, BatteryLowWarning);
		CoolantDisplay(Coolant, CoolantHighWarning, ParentActivity.UnitTemp);
		TMOilDisplay(TMOil, TMOilHighWarning, ParentActivity.UnitTemp);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMonitoringAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
	}

	/////////////////////////////////////////////////////////////////////
	public void HYDDisplay(int _data, int _status, int _unit){
		textViewHYD.setText(ParentActivity.GetTemp(_data,_unit));
		if(_unit == ParentActivity.UNIT_TEMP_F){
			textViewHYDUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textViewHYDUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
		
		if(_status == CAN1CommManager.DATA_STATE_LAMP_ON){
			textViewHYD.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			textViewHYDTitle.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			layoutHYD.setBackgroundResource(R.drawable.menu_information_monitoring_block_warning);
		}else{
			textViewHYD.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			textViewHYDTitle.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			layoutHYD.setBackgroundResource(R.drawable.menu_information_monitoring_block);
		}
	}
	public void BatteryDisplay(int _data, int _status){
		textViewBatt.setText(ParentActivity.GetBattery(_data));
		textViewBattUnit.setText(getString(ParentActivity.getResources().getString(string.V), 10));
		
		if(_status == CAN1CommManager.DATA_STATE_LAMP_ON){
			textViewBatt.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			textViewBattTitle.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			layoutBatt.setBackgroundResource(R.drawable.menu_information_monitoring_block_warning);
		}else{
			textViewBatt.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			textViewBattTitle.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			layoutBatt.setBackgroundResource(R.drawable.menu_information_monitoring_block);
		}
	}
	public void CoolantDisplay(int _data, int _status, int _unit){
		textViewCoolant.setText(ParentActivity.GetTemp(_data,_unit));
		if(_unit == ParentActivity.UNIT_TEMP_F){
			textViewCoolantUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textViewCoolantUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
		
		if(_status == CAN1CommManager.DATA_STATE_LAMP_ON){
			textViewCoolant.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			textViewCoolantTitle.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			layoutCoolant.setBackgroundResource(R.drawable.menu_information_monitoring_block_warning);
		}else{
			textViewCoolant.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			textViewCoolantTitle.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			layoutCoolant.setBackgroundResource(R.drawable.menu_information_monitoring_block);
		}
	}
	public void TMOilDisplay(int _data, int _status, int _unit){
		textViewTMOil.setText(ParentActivity.GetTemp(_data,_unit));
		if(_unit == ParentActivity.UNIT_TEMP_F){
			textViewTMOilUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textViewTMOilUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
		
		if(_status == CAN1CommManager.DATA_STATE_LAMP_ON){
			textViewTMOil.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			textViewTMOilTitle.setTextColor(ParentActivity.getResources().getColor(R.color.red));
			layoutTMOil.setBackgroundResource(R.drawable.menu_information_monitoring_block_warning);
		}else{
			textViewTMOil.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			textViewTMOilTitle.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			layoutTMOil.setBackgroundResource(R.drawable.menu_information_monitoring_block);
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){

	}
	public void ClickRight(){	

	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		ClickOK();
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			break;
		
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
