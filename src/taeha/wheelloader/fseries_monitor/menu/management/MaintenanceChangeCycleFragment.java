package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.TextView;

public class MaintenanceChangeCycleFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

	TextView textViewNum1;
	TextView textViewNum2;
	TextView textViewNum3;
	TextView textViewNum4;
	TextView textViewNum5;
	TextView textViewNum6;
	TextView textViewNum7;
	TextView textViewNum8;
	TextView textViewNum9;
	TextView textViewNum0;
	
	ImageButton imgbtnBack;
	ImageButton imgbtnEnter;
	
	TextView textViewTime;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int MaintenanceItem;
	int Interval;
	
	int IntervalSetting;
	
	boolean bFirstClickFlag;
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
		 TAG = "MaintenanceChangeCycleFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_maint_changeinterval, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE;
		TitleDisplay(MaintenanceItem);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_maint_changecycle_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_maint_changecycle_low_cancel);
	
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_num_0);
		
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_changecycle_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_changecycle_num_enter);
	
		textViewTime = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_changecycle_text_data);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		MaintenanceItem = CAN1Comm.Get_MaintenanceItem_1098_PGN61184_12();
		Interval = CAN1Comm.Get_MaintenanceInterval_1091_PGN61184_14();
		IntervalSetting = Interval * 50;
		if(Interval > 250){
			textViewTime.setText("");
		}else{
			IntervalDisplay(IntervalSetting);
		}
		
		bFirstClickFlag = true;
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
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		textViewNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum1();
			}
		});
		textViewNum2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum2();
			}
		});
		textViewNum3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum3();
			}
		});
		textViewNum4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum4();
			}
		});
		textViewNum5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum5();
			}
		});
		textViewNum6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum6();
			}
		});
		textViewNum7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum7();
			}
		});
		textViewNum8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum8();
			}
		});
		textViewNum9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum9();
			}
		});
		textViewNum0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum0();
			}
		});
		imgbtnEnter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEnter();
			}
		});
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBack();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMaintenanceDetail();		
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_INTERVAL_CHANGE);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItem);
		CAN1Comm.Set_MaintenanceInterval_1091_PGN61184_12(IntervalSetting/50);
		CAN1Comm.TxCANToMCU(12);
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(15);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(0xFF);
		CAN1Comm.Set_MaintenanceInterval_1091_PGN61184_12(0xFF);
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMaintenanceDetail();	
	}
	public void ClickNum1(){
		IntervalSetting = CalData(1,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}
	public void ClickNum2(){
		IntervalSetting = CalData(2,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum3(){
		IntervalSetting = CalData(3,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum4(){
		IntervalSetting = CalData(4,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum5(){
		IntervalSetting = CalData(5,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum6(){
		IntervalSetting = CalData(6,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum7(){
		IntervalSetting = CalData(7,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum8(){
		IntervalSetting = CalData(8,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum9(){
		IntervalSetting = CalData(9,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickNum0(){
		IntervalSetting = CalData(0,IntervalSetting);
		IntervalDisplay(IntervalSetting);
	}

	public void ClickBack(){
		IntervalSetting = 0;
		IntervalDisplay(IntervalSetting);
	}
	public void ClickEnter(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMaintenanceDetail();		
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_INTERVAL_CHANGE);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItem);
		CAN1Comm.Set_MaintenanceInterval_1091_PGN61184_12(IntervalSetting/50);
		CAN1Comm.TxCANToMCU(12);
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(15);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(0xFF);
		CAN1Comm.Set_MaintenanceInterval_1091_PGN61184_12(0xFF);
		
		Log.d(TAG,"IntervalSetting : " + Integer.toString(IntervalSetting));
	}
	/////////////////////////////////////////////////////////////////////
	public void TitleDisplay(int _MaintItem){
		switch (_MaintItem) {
		case	CAN1CommManager.ENGINE_OIL	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Engine_oil));
			break;
		case	CAN1CommManager.TRAVEL_REDUCTION_GEAR_CASE	:	
			
			break;
		case	CAN1CommManager.SWING_REDUCTION_GEAR_CASE	:	
			
			break;
		case	CAN1CommManager.HYDRAULIC_OIL	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Hydraulic_oil));
			break;
		case	CAN1CommManager.PILOT_LINE_FILTER_ELEMENT	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Pilot_line_filter_element));
			break;
		case	CAN1CommManager.HYDRAULIC_TANK_DRAIN_FILTER_CARTRIDGE	:	
			
			break;
		case	CAN1CommManager.HYDRAULIC_OIL_RETURN_FILTER	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Hydraulic_oil_return_filter));
			break;
		case	CAN1CommManager.ENGINE_OIL_FILTER	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Engine_oil_filter));
			break;
		case	CAN1CommManager.FUEL_FILTER_ELEMENT	:	
			
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Fuel_filter_element));
			break;
		case	CAN1CommManager.PRE_FILTER	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Fuel_Pre_filter));
			break;
		case	CAN1CommManager.HYDRAULIC_TANK_AIR_BREATHER_ELEMENT	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Hydraulic_Tank_Drain_Filter_Cartridge));
			break;
		case	CAN1CommManager.AIR_CLEANER_ELEMENT	:	
		
			break;
		case	CAN1CommManager.RADIATOR_COOLANT	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Radiator_engine_coolant));
			break;
		case	CAN1CommManager.SWING_BEARING_GEAR_PINION_GREASE	:	
			
			break;
		case	CAN1CommManager.TRANSMISSION_OIL	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Transmission_oil));
			break;
		case	CAN1CommManager.TRANSMISSION_OIL_FILTER	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Transmission_oil_filter));
			break;
		case	CAN1CommManager.FRONT_AXLE_DIFFERENTIAL_GEAR_OIL	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Front_axle_oil));
			break;
		case	CAN1CommManager.REAR_AXLE_DIFFERENTIAL_GEAR_OIL	:	
		
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Rear_axle_oil));
			break;
		case	CAN1CommManager.AXLE_PLANETARY_GEAR_OIL	:	
			
			break;
		case	CAN1CommManager.BRAKE_COOLING_OIL	:	
		
			break;
		case	CAN1CommManager.HYDRAULIC_OIL_STRAINER	:	
		
			break;
		case	CAN1CommManager.CRANKCASE_VENTILATION_FILTER	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Crankcase_Ventilation_Filter));
			break;
		case	CAN1CommManager.AIR_CON_HEATER_OUTER_FILTER	:	
			
			ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(string.Aircon_Heater_Outer_Filter));
			break;
		default:
			break;
		}
	}
	public void IntervalDisplay(int _data){
		textViewTime.setText(Integer.toString(_data));
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public int CalData(int _input, int _result){
		if(bFirstClickFlag == true){
			bFirstClickFlag = false;
			_result = 0;
		}
		if(_result == 0){
			_result = _input;
		}else{
			_result = _result * 10 + _input;
		}
		if(_result > 12500){
			_result = 12500;
		}
		return _result;
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
	
}
