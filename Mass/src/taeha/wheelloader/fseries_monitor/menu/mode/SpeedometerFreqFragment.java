package taeha.wheelloader.fseries_monitor.menu.mode;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SpeedometerFreqFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;
	TextFitTextView textViewOK;
	TextFitTextView textViewCancel;
	TextFitTextView textViewDefault;

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
	ImageButton imgbtnNext;
	
	RadioButton radioNum10;
	RadioButton radioNum1;
	RadioButton radioNumUnder1;
	RadioButton radioNumUnder01;
	RadioGroup radioGroupNum;
	
	TextFitTextView textViewSpeedUnit; 
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SpeedometerFreq;
	int SpeedometerFreq_Num10;
	int SpeedometerFreq_Num1;
	int SpeedometerFreq_Num_Under1;
	int SpeedometerFreq_Num_Under01;
	
	
	Handler HandleCursurDisplay;
	int CursurIndex;
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
		 TAG = "SpeedometerFreqFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_speedometerfreq, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		DisableHalfNumButton();
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Speedometer_Setting), 321);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
		
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_default);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
		textViewDefault = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_low_default);
		textViewDefault.setText(getString(ParentActivity.getResources().getString(R.string.Default), 25));
		
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_0);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_speedometerfreq_num_back);
		imgbtnNext = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_speedometerfreq_num_next);
		
		radioNum10 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_10);
		radioNum1 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_1);
		radioNumUnder1 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_under_1);
		radioNumUnder01 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_under_01);
		
		radioGroupNum = (RadioGroup)mRoot.findViewById(R.id.radioGroup_menu_body_mode_speedometerfreq_data);
		textViewSpeedUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_data_unit);
		textViewSpeedUnit.setText(getString(ParentActivity.getResources().getString(R.string.rpm_km_h), 251));

	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CAN1Comm.Set_SettingSelection_PGN61184_105(0xF);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(0xF);
		CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
		CAN1Comm.TxCANToMCU(105);
		CAN1Comm.Set_SettingSelection_PGN61184_105(15);
		
		InitText();
		
		CursurIndex = 5;
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
		imgbtnDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickDefault();
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
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				
				ClickNumBack();
			}
		});	
		imgbtnNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				
				ClickNumNext();
			}
		});	

		radioNum10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioNum10();
			}
		});	
		radioNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioNum1();
			}
		});	
		radioNumUnder1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioUnderNum1();
			}
		});	
		radioNumUnder01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioUnderNum01();
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
		CursurIndex = 15;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		
		CAN1Comm.Set_SettingSelection_PGN61184_105(1);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(SpeedometerFreq);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(0xF);
		CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
		CAN1Comm.TxCANToMCU(105);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
		CAN1Comm.Set_SettingSelection_PGN61184_105(15);
		Log.d(TAG,"SetSpeedMeterFreq : " + Integer.toString(SpeedometerFreq));
	}
	public void ClickCancel(){
		CursurIndex = 14;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
	}
	public void ClickDefault(){
		CursurIndex = 13;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		ParentActivity.showSpeedometerInit();
	}
	public void ClickNum1(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10)
			return;
		
		CursurIndex = 1;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(1);
	}
	public void ClickNum2(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10)
			return;
		CursurIndex = 2;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(2);
	}
	public void ClickNum3(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10)
			return;
		CursurIndex = 3;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(3);
	}
	public void ClickNum4(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10)
			return;
		CursurIndex = 4;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(4);
	}
	public void ClickNum5(){
		CursurIndex = 5;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(5);
	}
	public void ClickNum6(){
		CursurIndex = 6;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(6);
	}
	public void ClickNum7(){
		CursurIndex = 7;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(7);
	}
	public void ClickNum8(){
		CursurIndex = 8;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(8);
	}
	public void ClickNum9(){
		CursurIndex = 9;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(9);
	}
	public void ClickNum0(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10)
			return;
		CursurIndex = 11;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNumber(0);
	}
	public void ClickNumBack(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10)
			return;
		CursurIndex = 10;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setBack();
	}
	public void ClickNumNext(){
		CursurIndex = 12;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		setNext();
	}
	public void ClickRadioNum10(){
		DisableHalfNumButton();
	}
	public void ClickRadioNum1(){
		EnableAllNumButton();
	}
	public void ClickRadioUnderNum1(){
		EnableAllNumButton();
	}
	public void ClickRadioUnderNum01(){
		EnableAllNumButton();
	}
	/////////////////////////////////////////////////////////////////////
	public void EnableAllNumButton(){
		imgbtnBack.setClickable(true);
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(true);
		textViewNum3.setClickable(true);
		textViewNum4.setClickable(true);
		textViewNum0.setClickable(true);
		
		imgbtnBack.setAlpha((float)1.0);
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)1.0);
		textViewNum3.setAlpha((float)1.0);
		textViewNum4.setAlpha((float)1.0);
		textViewNum0.setAlpha((float)1.0);
	}
	public void DisableHalfNumButton(){
		imgbtnBack.setClickable(false);
		textViewNum1.setClickable(false);
		textViewNum2.setClickable(false);
		textViewNum3.setClickable(false);
		textViewNum4.setClickable(false);
		textViewNum0.setClickable(false);
		
		imgbtnBack.setAlpha((float)0.5);
		textViewNum1.setAlpha((float)0.5);
		textViewNum2.setAlpha((float)0.5);
		textViewNum3.setAlpha((float)0.5);
		textViewNum4.setAlpha((float)0.5);
		textViewNum0.setAlpha((float)0.5);
	}
	public void setNumber(int num){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (Focus) {
		case R.id.radio_menu_body_mode_speedometerfreq_data_10:
			SpeedometerFreq_Num10 = num;
			radioNum10.setText(Integer.toString(SpeedometerFreq_Num10));
			radioNum1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_1:
			SpeedometerFreq_Num1 = num;
			radioNum1.setText(Integer.toString(SpeedometerFreq_Num1));
			radioNumUnder1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_1:
			SpeedometerFreq_Num_Under1 = num;
			radioNumUnder1.setText(Integer.toString(SpeedometerFreq_Num_Under1));
			radioNumUnder01.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_01:
			SpeedometerFreq_Num_Under01 = num;
			radioNumUnder01.setText(Integer.toString(SpeedometerFreq_Num_Under01));
			radioNum10.setChecked(true);
			DisableHalfNumButton();
			break;
		default:
			
			break;
		}
		SpeedometerFreq = SpeedometerFreq_Num10 * 1000
				+ SpeedometerFreq_Num1 * 100
				+ SpeedometerFreq_Num_Under1 * 10
				+ SpeedometerFreq_Num_Under01;
	}
	public void setNext(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (Focus) {
		case R.id.radio_menu_body_mode_speedometerfreq_data_10:
			radioNum1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_1:
			radioNumUnder1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_1:
			radioNumUnder01.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_01:
			radioNum10.setChecked(true);
			DisableHalfNumButton();
			break;

		default:
			
			break;
		}
		SpeedometerFreq = SpeedometerFreq_Num10 * 1000
				+ SpeedometerFreq_Num1 * 100
				+ SpeedometerFreq_Num_Under1 * 10
				+ SpeedometerFreq_Num_Under01;
	}
	public void setBack(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (Focus) {
		case R.id.radio_menu_body_mode_speedometerfreq_data_10:
			radioNumUnder01.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_1:
			radioNum10.setChecked(true);
			DisableHalfNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_1:
			radioNum1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_01:
			radioNumUnder1.setChecked(true);
			EnableAllNumButton();
			break;

		default:
			
			break;
		}
		SpeedometerFreq = SpeedometerFreq_Num10 * 1000
				+ SpeedometerFreq_Num1 * 100
				+ SpeedometerFreq_Num_Under1 * 10
				+ SpeedometerFreq_Num_Under01;
	}
	public void InitText(){
		
		SpeedometerFreq = CAN1Comm.Get_SpeedometerFrequency_534_PGN61184_106();
		
		if(SpeedometerFreq == 0xFFFF)
			SpeedometerFreq = 0;
		
		SpeedometerFreq_Num10 = SpeedometerFreq / 1000;
		SpeedometerFreq_Num1 = (SpeedometerFreq % 1000) / 100;
		SpeedometerFreq_Num_Under1 = (SpeedometerFreq % 100) / 10;
		SpeedometerFreq_Num_Under01 = SpeedometerFreq % 10;
		
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				radioNum10.setText(Integer.toString(SpeedometerFreq_Num10));
				radioNum1.setText(Integer.toString(SpeedometerFreq_Num1));
				radioNumUnder1.setText(Integer.toString(SpeedometerFreq_Num_Under1));
				radioNumUnder01.setText(Integer.toString(SpeedometerFreq_Num_Under01));
			}
		});
		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (CursurIndex) {
		case 1:
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 15;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 3:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 15;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 4:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 15;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 5:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 15;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 6:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 9:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 10:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 12:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 13:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 14:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 15:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
		
	}
	public void ClickRight(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (CursurIndex) {
		case 1:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 2:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 3:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 9:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 12;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 10:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 12:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 13:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 14:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 15:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (CursurIndex) {
		case 1:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				ClickNum1();
			}
			break;
		case 2:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				ClickNum2();
			}
			break;
		case 3:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				ClickNum3();
			}
			break;
		case 4:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				ClickNum4();
			}
			break;
		case 5:
			ClickNum5();
			break;
		case 6:
			ClickNum6();
			break;
		case 7:
			ClickNum7();
			break;
		case 8:
			ClickNum8();
			break;
		case 9:
			ClickNum9();
			break;
		case 10:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}
			// ++, 150210 bwk
			else if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_1){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
				setBack();
			// --, 150210 bwk
			}else{
				// ++, 150210 bwk
				//ClickNumNext();
				ClickNumBack();
				// --, 150210 bwk
			}
			break;
		case 11:
			if(Focus == R.id.radio_menu_body_mode_speedometerfreq_data_10){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				ClickNum0();
			}
			break;
		case 12:
			ClickNumNext();
			break;
		case 13:
			ClickDefault();
			break;
		case 14:
			ClickCancel();
			break;
		case 15:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		imgbtnDefault.setPressed(false);

		textViewNum1.setPressed(false);
		textViewNum2.setPressed(false);
		textViewNum3.setPressed(false);
		textViewNum4.setPressed(false);
		textViewNum5.setPressed(false);
		textViewNum6.setPressed(false);
		textViewNum7.setPressed(false);
		textViewNum8.setPressed(false);
		textViewNum9.setPressed(false);
		textViewNum0.setPressed(false);
		imgbtnBack.setPressed(false);
		imgbtnNext.setPressed(false);

		switch (Index) {
		case 1:
			textViewNum1.setPressed(true);
			break;
		case 2:
			textViewNum2.setPressed(true);
			break;
		case 3:
			textViewNum3.setPressed(true);
			break;
		case 4:
			textViewNum4.setPressed(true);
			break;
		case 5:
			textViewNum5.setPressed(true);
			break;
		case 6:
			textViewNum6.setPressed(true);
			break;
		case 7:
			textViewNum7.setPressed(true);
			break;
		case 8:
			textViewNum8.setPressed(true);
			break;
		case 9:
			textViewNum9.setPressed(true);
			break;
		case 10:
			imgbtnBack.setPressed(true);
			break;
		case 11:
			textViewNum0.setPressed(true);
			break;
		case 12:
			imgbtnNext.setPressed(true);
			break;
		case 13:
			imgbtnDefault.setPressed(true);
			break;
		case 14:
			imgbtnCancel.setPressed(true);
			break;
		case 15:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
	/////////////////////////////////////////////////////////////////////
}
