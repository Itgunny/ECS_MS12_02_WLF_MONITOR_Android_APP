﻿package taeha.wheelloader.fseries_monitor.main.b;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.TextFitTextViewAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBRightUpEngineFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewModeTitle;
	TextFitTextView textViewModeData;
	// ++, 150316 bwk
	//TextView textViewWarmingUpTitle;
	//TextView textViewWarmingUpData;
	TextFitTextView textViewHourOdoTitle;
	TextFitTextView textViewHourOdoData;
	TextFitTextView textViewHourOdoUnit;
	// --, 150316 bwk
	
	ImageButton imgbtnMode;
	// ++, 150316 bwk
	//ImageButton imgbtnWarmingUp;
	ImageButton imgbtnHourOdo;
	// --, 150316 bwk
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
	// ++, 150316 bwk
	//int EngineWarmingUp;
	int TotalOdometer;
	int LatestOdometer;
	int LatestHourmeter;
	// --, 150316 bwk
	boolean ClickFlag;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	TextFitTextViewAxisFlipAnimation EngineModeDataAnimation;
	// ++, 150316 bwk
	//TextViewXAxisFlipAnimation EngineWarmingUpDataAnimation;
	TextFitTextViewAxisFlipAnimation HourOdometerTitleAnimation;
	// --, 150316 bwk
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBRightUpEngineFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightup_main_b_engine, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_mode_title);
		textViewModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.ENGINE_MODE), 85) + " ");
		
		textViewModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_mode_data);
		// ++, 150316 bwk
		//textViewWarmingUpTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_warmingup_title);
		//textViewWarmingUpData = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_warmingup_data);
		
		textViewHourOdoTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_b_hourodometer_title);

		//textViewHourOdoTitle.setText(getString(ParentActivity.getResources().getString(R.string.LATEST_HOURMETER), 90));
		textViewHourOdoData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_b_hourodometer_data);
		textViewHourOdoUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_b_hourodometer_unit);
		textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(R.string.Hr), 7));
		// --, 150316 bwk
		
		imgbtnMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_b_engine_mode);
		// ++, 150316 bwk
		//imgbtnWarmingUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_b_engine_warmingup);
		imgbtnHourOdo = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_b_hourodometer);
		// --, 150316 bwk

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		EngineModeDataAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);
		// ++, 150317 bwk
		//EngineWarmingUpDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		HourOdometerTitleAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);
		// --, 150317 bwk
		
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
				
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 6;
				if(ClickFlag == true)
					ClickMode();
			}
		});
		// ++, 150317 bwk
		/*imgbtnWarmingUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickWarmingUp();
			}
		});*/
		imgbtnHourOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 5;
				if(ClickFlag == true)
					ClickHourOdo();
			}
		});
		// --, 150317 bwk
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		// ++, 150317 bwk
		//EngineWarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
		TotalOdometer = CAN1Comm.Get_TotalVehicleDistance_601_PGN65389();
		LatestOdometer = CAN1Comm.Get_TripDistance_600_PGN65389();
		
		LatestHourmeter = CAN1Comm.Get_TripTime_849_PGN65344();
		// --, 150317 bwk
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		EngineModeDisplay(EngineMode);
		// ++, 150317 bwk
		//EngineWarmingUpDisplay(EngineWarmingUp);
		HourOdometerTitleDisplay(ParentActivity.HourOdometerIndex);
		HourOdometerDataDisplay(ParentActivity.HourOdometerIndex,LatestHourmeter,TotalOdometer,LatestOdometer);
		// --, 150317 bwk
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void EngineModeDisplay(int _EngineMode){
		try {
			switch (_EngineMode) {
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getString(getResources().getString(string.POWER), 96));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getString(getResources().getString(string.STANDARD), 95));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getString(getResources().getString(string.ECONO), 94));
				break;
			default:
//				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.POWER));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
		
	}
	
	// ++, 150317 bwk
	/*
	public void EngineWarmingUpDisplay(int _EngineWarmingUp){
		try {
			switch (_EngineWarmingUp) {
			case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
				EngineWarmingUpDataAnimation.FlipAnimation(textViewWarmingUpData,getResources().getString(string.OFF));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
				EngineWarmingUpDataAnimation.FlipAnimation(textViewWarmingUpData,getResources().getString(string.ON));
				break;
			default:
				break;

			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
		
	}
	*/
	public void HourOdometerTitleDisplay(int _Index){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
//			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,"");
//			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getString(getResources().getString(string.LATEST_HOURMETER), 90) + " ");
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getString(getResources().getString(string.TOTAL_ODOMETER), 92) + " ");
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getString(getResources().getString(string.LATEST_ODOMETER), 93) + " ");
			break;

		default:
			break;
		}
	}
	public void HourOdometerDataDisplay(int _Index, int LatestHour, int TotalOdo, int LatestOdo){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
//			textViewHourOdoData.setText("");
//			textViewHourOdoUnit.setText("");
//			break;
			
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			textViewHourOdoData.setText(ParentActivity.GetHourmeterString(LatestHour));
			textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(string.Hr), 7));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			textViewHourOdoData.setText(ParentActivity.GetOdometerStrng(TotalOdo,ParentActivity.UnitOdo));
			if(ParentActivity.UnitOdo == ParentActivity.UNIT_ODO_MILE){
				textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(string.mile), 38));
			}else{
				textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(string.km), 37));
			}
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			textViewHourOdoData.setText(ParentActivity.GetOdometerStrng(LatestOdo,ParentActivity.UnitOdo));
			if(ParentActivity.UnitOdo == ParentActivity.UNIT_ODO_MILE){
				textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(string.mile), 38));
			}else{
				textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(string.km), 37));
			}
			break;

		default:
			break;
		}
		
	}
	// --, 150317 bwk
	
	public void ClickMode(){
		if(Home.LOCK_STATE_ENGINEMODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainBBaseFragment._MainBCenterEngineFragment = new MainBCenterEngineFragment();
		ParentActivity._MainBBaseFragment._MainBRightUpEngineModeFragment = new MainBRightUpEngineModeFragment();
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpEngineModeFragment);
		
		//ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		

		}
	}
	
	// ++, 150317 bwk
	/*
	public void ClickWarmingUp(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterEngineFragment = new MainBCenterEngineFragment();
		ParentActivity._MainBBaseFragment._MainBRightUpEngineWarmingUpFragment = new MainBRightUpEngineWarmingUpFragment();
		ParentActivity._MainBBaseFragment._MainBRightUpHourOdometerSelectFragment = new MainBRightUpHourOdometerSelectFragment();
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpEngineWarmingUpFragment);
		
		//ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
	}
	*/
	public void ClickHourOdo(){
		if(Home.LOCK_STATE_OPERATION_HISTORY == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterEngineFragment = new MainBCenterEngineFragment();
		ParentActivity._MainBBaseFragment._MainBRightUpHourOdometerSelectFragment = new MainBRightUpHourOdometerSelectFragment();
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpHourOdometerSelectFragment);
		
		ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
	}
	}
	// --, 150317 bwk
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMode.setClickable(ClickFlag);
		// ++, 150317 bwk
		//imgbtnWarmingUp.setClickable(ClickFlag);
		imgbtnHourOdo.setClickable(ClickFlag);
		// --, 150317 bwk
	}
	public void CursurDisplayDetail(int index){
		imgbtnHourOdo.setBackgroundResource(R.drawable._selector_rightup_main_b_hourodometer_btn1);
		imgbtnMode.setBackgroundResource(R.drawable._selector_rightup_main_b_engine_mode_btn1);
		switch(index){
			case 5:
				imgbtnHourOdo.setBackgroundResource(R.drawable.main_default_engine01_selected02);
				break;
			case 6:
				imgbtnMode.setBackgroundResource(R.drawable.main_default_engine02_selected02);
				break;
		}
	}
}
