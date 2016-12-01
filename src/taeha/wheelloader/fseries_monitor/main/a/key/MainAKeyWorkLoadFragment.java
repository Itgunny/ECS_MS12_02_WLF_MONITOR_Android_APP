package taeha.wheelloader.fseries_monitor.main.a.key;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainAKeyWorkLoadFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnAccumulation;
	ImageButton imgbtnDisplay;
	ImageButton imgbtnErrorDetect;
	
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;
	
	TextFitTextView textViewOK;
	TextFitTextView textViewCancel;
	TextFitTextView textViewDefault;
	
	
	TextFitTextView textviewWeighingSystemModeTitle;
	TextFitTextView textViewWeighingDisplayTitle;
	TextFitTextView textViewErrorDetectTitle;
	
	TextFitTextView textviewWeighingSystemModeData;
	TextFitTextView textViewWeighingDisplayData;
	TextFitTextView textViewErrorDetectData;
	
	TextFitTextView textViewPressureCalibration;
	TextFitTextView textViewInitialization;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingSystemMode;
	int WeighingDisplayMode;
	
	int CursurIndex;
	Handler HandleCursurDisplay;
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
		 TAG = "MainAKeyWorkLoadFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_workload, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
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
		
		imgbtnAccumulation = (ImageButton)mRoot.findViewById(R.id.imageButton_key_main_a_workload_accumulation);
		imgbtnDisplay = (ImageButton)mRoot.findViewById(R.id.imageButton_key_main_a_workload_display);
		imgbtnErrorDetect = (ImageButton)mRoot.findViewById(R.id.imageButton_key_main_a_workload_errordetect);

		
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_low_default);
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Cancel), 16));
		textViewDefault = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_low_default);
		textViewDefault.setText(getString(ParentActivity.getResources().getString(string.Default), 25));
		
		textviewWeighingSystemModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_accumulation_title);
		textviewWeighingSystemModeTitle.setText(getString(ParentActivity.getResources().getString(string.Weighing_System_Mode), 169));
		textViewWeighingDisplayTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_display_title);
		textViewWeighingDisplayTitle.setText(getString(ParentActivity.getResources().getString(string.Weighing_Display), 170));
		textViewErrorDetectTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_errordetect_title);
		textViewErrorDetectTitle.setText(getString(ParentActivity.getResources().getString(string.Error_Detection), 171));
		
		textviewWeighingSystemModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_accumulation_data);
		textViewWeighingDisplayData = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_display_data);
		textViewErrorDetectData = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_errordetect_data);
		
		textViewPressureCalibration = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_calibration);
		textViewPressureCalibration.setText(getString(ParentActivity.getResources().getString(string.Boom_Pressure_Calibration), 172));
		textViewInitialization = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_a_workload_initialization);
		textViewInitialization.setText(getString(ParentActivity.getResources().getString(string.Initialization), 30));
		
		float fontsize;
		if(ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_KOREAN)
			fontsize = 22;
		else if(ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_ENGLISH
				|| ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_SWEDISH)
			fontsize = 20;
		else if(ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_TURKISH
				|| ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_SPANISH
				|| ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_GERMAN)
			fontsize = 15;
		else if(ParentActivity.LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_ITALIAN)
			fontsize = 13;
		else
			fontsize = 18;
		
		textViewPressureCalibration.setTextSize(fontsize);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		switch(ParentActivity.OldScreenIndex)
		{
			case Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_DISPLAY:
				CursurIndex = 2;
				break;
			case Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ERRORDETECT:
				CursurIndex = 3;
				break;
			case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP:
				CursurIndex = 4;
				break;
			default:
				CursurIndex = 1;
				break;
					
		}
		ParentActivity.OldScreenIndex = 0;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnAccumulation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAccumulation();
			}
		});
		imgbtnDisplay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDisplay();
			}
		});
		imgbtnErrorDetect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickErrorDetect();
			}
		});
		
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
				CursurIndex = 6;
			}
		});
		textViewPressureCalibration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCalibration();
				CursurIndex = 4;
			}
		});
		textViewInitialization.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickInitial();
				CursurIndex = 5;
			}
		});
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		WeighingSystemMode = CAN1Comm.Get_WeighingSystemAccumulationMode_1941_PGN65450();
		WeighingDisplayMode = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		WeighingSystemModeDisplay(WeighingSystemMode);
		WeighingDisplayDisplay(WeighingDisplayMode);
		ErrorDetectionDisplay(ParentActivity.WeighingErrorDetect);
	}
	/////////////////////////////////////////////////////////////////////	
	public void WeighingSystemModeDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			textviewWeighingSystemModeData.setText(getString(ParentActivity.getResources().getString(R.string.Manual), 26));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			textviewWeighingSystemModeData.setText(getString(ParentActivity.getResources().getString(R.string.Automatic), 27));
			break;
		default:
			break;
		}
	}
	public void WeighingDisplayDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			textViewWeighingDisplayData.setText(getString(ParentActivity.getResources().getString(R.string.Daily), 173));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			textViewWeighingDisplayData.setText(getString(ParentActivity.getResources().getString(R.string.Total_A), 174));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			textViewWeighingDisplayData.setText(getString(ParentActivity.getResources().getString(R.string.Total_B), 175));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			textViewWeighingDisplayData.setText(getString(ParentActivity.getResources().getString(R.string.Total_C), 176));
			break;
			
		default:
			break;
		}
	}
	public void ErrorDetectionDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			textViewErrorDetectData.setText(getString(ParentActivity.getResources().getString(R.string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			textViewErrorDetectData.setText(getString(ParentActivity.getResources().getString(R.string.On), 19));
			break;
		default:
			break;
		}
	}
	
	
	public void ClickAccumulation(){
		ParentActivity._MainABaseFragment.showWorkLoadAccumulationAnimation();
	}
	public void ClickDisplay(){
		ParentActivity._MainABaseFragment.showWorkLoadDisplayAnimation();
	}
	public void ClickErrorDetect(){
		ParentActivity._MainABaseFragment.showWorkLoadErrorDetectionAnimation();
	}
	public void ClickOK(){
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickCancel(){
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickDefault(){
		// ++, 150210 bwk
		/*
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
	
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayMode);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
	
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		SavePref();
		*/
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		ParentActivity.showWorkLoadInit();
		// --, 150210 bwk
	}
	public void ClickCalibration(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP);
	}
	public void ClickInitial(){
		ParentActivity.OldScreenIndex = ParentActivity.ScreenIndex;
		ParentActivity.showWorkLoadWeighingInit1();
	}
	// ++, 150210 bwk
	public void SetDefault(){
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
	
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayMode);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
	
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		SavePref();
	}
	// --, 150210 bwk
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("WeighingErrorDetect", ParentActivity.WeighingErrorDetect);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
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
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
			case 1:
				ClickAccumulation();
				break;
			case 2:
				ClickDisplay();
				break;
			case 3:
				ClickErrorDetect();
				break;
			case 4:
				ClickCalibration();
				break;
			case 5:
				ClickInitial();
				break;
			case 6:
				ClickDefault();
				break;
			case 7:
				ClickOK();
				break;
			default:
				break;
		}
	}
	
	public void CursurDisplay(int Index){
		imgbtnAccumulation.setPressed(false);
		imgbtnDisplay.setPressed(false);
		imgbtnErrorDetect.setPressed(false);
		textViewPressureCalibration.setPressed(false);
		textViewInitialization.setPressed(false);
		imgbtnDefault.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (CursurIndex) {
			case 1:
				imgbtnAccumulation.setPressed(true);
				break;
			case 2:
				imgbtnDisplay.setPressed(true);
				break;
			case 3:
				imgbtnErrorDetect.setPressed(true);
				break;
			case 4:
				textViewPressureCalibration.setPressed(true);
				break;
			case 5:
				textViewInitialization.setPressed(true);
				break;
			case 6:
				imgbtnDefault.setPressed(true);
				break;		
			case 7:
				imgbtnOK.setPressed(true);
				break;						
			default:
				break;
		}
	}

}