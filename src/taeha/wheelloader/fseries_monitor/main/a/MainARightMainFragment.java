package taeha.wheelloader.fseries_monitor.main.a;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.TextFitTextViewAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainARightMainFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewModeTitle;
	TextFitTextView textViewModeData;	
	TextFitTextView textViewHourOdoTitle;
	TextFitTextView textViewHourOdoData;
	TextFitTextView textViewHourOdoUnit;
	
	TextFitTextView textViewCCOModeTitle;
	TextFitTextView textViewCCOModeData;
	TextFitTextView textViewShiftModeTitle;
	TextFitTextView textViewShiftModeData;
	TextFitTextView textViewTCLockUpTitle;
	TextFitTextView textViewTCLockUpData;
	
	ImageButton imgbtnMode;
	ImageButton imgbtnHourOdo;
	ImageButton imgbtnCCOMode;
	ImageButton imgbtnShiftMode;
	ImageButton imgbtnTCLockUp;
	
	RelativeLayout layoutTCLockUp;
	RelativeLayout layoutRightB;
	RelativeLayout layoutRightBNonTCLockUp;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;

	int TotalOdometer;
	int LatestOdometer;
	int LatestHourmeter;
	int EngineMode;
	
	int CCOMode;
	int ShiftMode;
	int TCLockUp;
	
	boolean OptionTCLockUp;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	TextFitTextViewAxisFlipAnimation EngineModeDataAnimation;
	TextFitTextViewAxisFlipAnimation HourOdometerTitleAnimation;	
	//TextViewXAxisFlipAnimation TMCCOModeTitleDataAnimation;	// ++, --, 150305 HHI ���� ��û
	TextFitTextViewAxisFlipAnimation TMCCOModeDataAnimation;
	TextFitTextViewAxisFlipAnimation TMShiftModeDataAnimation;
	TextFitTextViewAxisFlipAnimation TMTCLockUpDataAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainARightMainFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.right_main_a, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}
	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		layoutRightB = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_right_main_a);
		layoutRightBNonTCLockUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_right_main_a_non_tclockup);
		
		InitResource_TCLockUp();
	}
	public void InitResource_nonTCLockUp(){
		textViewModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_engine_mode_title_non_tclockup);
		textViewModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.ENGINE_MODE), 85));
		
		textViewModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_engine_mode_data_non_tclockup);
		
		textViewHourOdoTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_title_non_tclockup);
		//textViewHourOdoTitle.setText(getString(ParentActivity.getResources().getString(R.string.LATEST_HOURMETER), 90));
		textViewHourOdoData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_data_non_tclockup);
		textViewHourOdoUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_unit_non_tclockup);
		//textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(R.string.Hr), 7));
		
		imgbtnMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_a_engine_mode_non_tclockup);
		imgbtnHourOdo = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_a_hourodometer_non_tclockup);
		textViewCCOModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_ccomode_title_non_tclockup);
		textViewCCOModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.CCO_MODE), 86));
		textViewCCOModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_ccomode_data_non_tclockup);
		
		textViewShiftModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_shiftmode_title_non_tclockup);
		textViewShiftModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.SHIFT_MODE), 88));
		textViewShiftModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_shiftmode_data_non_tclockup);
		
		imgbtnCCOMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_ccomode_non_tclockup);
		imgbtnShiftMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_shiftmode_non_tclockup);
	}
	public void InitResource_TCLockUp(){
		textViewModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_engine_mode_title);
		textViewModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.ENGINE_MODE), 85));
		textViewModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_engine_mode_data);
		
		textViewHourOdoTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_title);
		//textViewHourOdoTitle.setText(getString(ParentActivity.getResources().getString(R.string.LATEST_HOURMETER), 90));
		textViewHourOdoData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_data);
		textViewHourOdoUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_unit);
		//textViewHourOdoUnit.setText(getString(ParentActivity.getResources().getString(R.string.Hr), 7));
		
		imgbtnMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_a_engine_mode);
		imgbtnHourOdo = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_a_hourodometer);
		textViewCCOModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_ccomode_title);
		textViewCCOModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.CCO_MODE), 86));
		textViewCCOModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_ccomode_data);
		
		textViewShiftModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_shiftmode_title);
		textViewShiftModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.SHIFT_MODE), 88));
		textViewShiftModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_shiftmode_data);
		
		textViewTCLockUpTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_tclockup_title);
		textViewTCLockUpTitle.setText(getString(ParentActivity.getResources().getString(R.string.TC_LOCK_UP), 89));
		textViewTCLockUpData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_tclockup_data);
		
		imgbtnCCOMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_ccomode);
		imgbtnShiftMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_shiftmode);
		imgbtnTCLockUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_tclockup);
		
		layoutTCLockUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_a_tm_tclockup);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
//		EngineModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
//		HourOdometerTitleAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
//		//TMCCOModeTitleDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);	// ++, --, 150305 HHI ���� ��û
//		TMCCOModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
//		TMShiftModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
//		TMTCLockUpDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);		
		InitAnimation();
		
		OptionTCLockUp = true;
		CursurDisplayDetail(ParentActivity._MainABaseFragment.CursurIndex);
	}
	public void InitAnimation(){
		EngineModeDataAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);
		HourOdometerTitleAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);
		//TMCCOModeTitleDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);	// ++, --, 150305 HHI ���� ��û
		TMCCOModeDataAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);
		TMShiftModeDataAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);
		TMTCLockUpDataAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);		
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnHourOdo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 5;
				if(ClickFlag == true)
					ClickHourOdo();
			}
		});
		imgbtnMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 6;
				if(ClickFlag == true)
					ClickMode();
			}
		});
		
		imgbtnCCOMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 7;
				if(ClickFlag == true)
					ClickCCOMode();
			}
		});
		imgbtnShiftMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 8;
				if(ClickFlag == true)
					ClickShiftMode();
			}
		});
		imgbtnTCLockUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 9;
				if(ClickFlag == true)
					ClickTCLockUp();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		TotalOdometer = CAN1Comm.Get_TotalVehicleDistance_601_PGN65389();
		LatestOdometer = CAN1Comm.Get_TripDistance_600_PGN65389();
		LatestHourmeter = CAN1Comm.Get_TripTime_849_PGN65344();
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
		TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		HourOdometerTitleDisplay(ParentActivity.HourOdometerIndex);
		HourOdometerDataDisplay(ParentActivity.HourOdometerIndex,LatestHourmeter,TotalOdometer,LatestOdometer);
		EngineModeDisplay(EngineMode);
		
		//TMCCOModeTitleDisplay(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()));
		TMCCOModeTitleDisplay(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()));
		TMCCOModeDisplay(CCOMode);
		TMShiftModeDisplay(ShiftMode);
		TMTCLockUpDisplay(TCLockUp);
		TCLockUpShow();
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
	
	public void HourOdometerTitleDisplay(int _Index){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
//			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,"");
//			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getString(getResources().getString(string.LATEST_HOURMETER), 90));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getString(getResources().getString(string.TOTAL_ODOMETER), 92));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getString(getResources().getString(string.LATEST_ODOMETER), 93));
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
	
	public void TCLockUpShow(){
		if(OptionTCLockUp == true)
		{
			/*if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
				InitResource_nonTCLockUp();
				InitButtonListener();
				InitAnimation();
				layoutTCLockUp.setVisibility(View.GONE);
				imgbtnTCLockUp.setClickable(false);
				layoutRightB.setVisibility(View.GONE);
				layoutRightBNonTCLockUp.setVisibility(View.VISIBLE);
				OptionTCLockUp = false;
			}else */if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
				InitResource_nonTCLockUp();
				InitButtonListener();
				InitAnimation();
				layoutTCLockUp.setVisibility(View.GONE);
				imgbtnTCLockUp.setClickable(false);
				layoutRightB.setVisibility(View.GONE);
				layoutRightBNonTCLockUp.setVisibility(View.VISIBLE);
				OptionTCLockUp = false;
			}
		}else if(OptionTCLockUp == false){
			/*if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
					|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			}else*/ if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
			}else{
				InitResource_TCLockUp();
				InitButtonListener();
				InitAnimation();
				layoutRightB.setVisibility(View.VISIBLE);
				layoutRightBNonTCLockUp.setVisibility(View.GONE);
				layoutTCLockUp.setVisibility(View.VISIBLE);
				imgbtnTCLockUp.setClickable(false);
				OptionTCLockUp = true;
			}
		}
	}
	public void TMCCOModeTitleDisplay(int Model){
		// ++, 150305 bwk
		//if(Model == CheckModel.MODEL_980){
		//	TMCCOModeTitleDataAnimation.FlipAnimation(textViewCCOModeTitle,getResources().getString(string.ICCO_MODE));
		//}else{
		//	TMCCOModeTitleDataAnimation.FlipAnimation(textViewCCOModeTitle,getResources().getString(string.CCO_MODE));
		//}
		//if(Model == CheckModel.MODEL_980){
		if(Model == 980){
			textViewCCOModeTitle.setText(getString(getResources().getString(string.ICCO_MODE), 87));
		}else{
			textViewCCOModeTitle.setText(getString(getResources().getString(string.CCO_MODE), 86));
		}
		// --, 150305 bwk
		
	}
	public void TMCCOModeDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.L), 99));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.M), 100));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
				if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
					TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.ON), 97));
				else
					TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.H), 101));
				break;
			default:
//				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.OFF));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMCCOModeDisplay");
		}
	}
	public void TMShiftModeDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.MANUAL), 102));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.AL), 103));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.AN), 104));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.AH), 105));
				break;
			default:
//				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.MANUAL));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMShiftModeDisplay");
		}
	}
	public void TMTCLockUpDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getString(getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getString(getResources().getString(string.ON), 97));
				break;
			default:
//				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.OFF));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMTCLockUpDisplay");
		}
	}
	
	public void ClickHourOdo(){
		if(Home.LOCK_STATE_OPERATION_HISTORY == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ShowSelectAnimation(0);
	}
	}
	public void ClickMode(){
		if(Home.LOCK_STATE_ENGINEMODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ShowSelectAnimation(1);
	}
	}
	
	public void ClickCCOMode(){
		if(Home.LOCK_STATE_CCOMODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ShowSelectAnimation(2);
	}
	}
	public void ClickShiftMode(){
		if(Home.LOCK_STATE_SHIFTMODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ShowSelectAnimation(3);
	}
	}
	public void ClickTCLockUp(){
		if(Home.LOCK_STATE_TCLOCKUP == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ShowSelectAnimation(4);
	}
	}
	
	public void ShowSelectAnimation(int index){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		if(index == 0){
			ParentActivity._MainABaseFragment._MainACenterEngineFragment = new MainACenterEngineFragment();
			ParentActivity._MainABaseFragment._MainARightUpHourOdometerSelectFragment = new MainARightUpHourOdometerSelectFragment();
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftRightAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterEngineFragment);
			ParentActivity._MainABaseFragment.RightChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainARightUpHourOdometerSelectFragment);
		}else if(index == 1){
			ParentActivity._MainABaseFragment._MainACenterEngineFragment = new MainACenterEngineFragment();
			ParentActivity._MainABaseFragment._MainARightUpEngineModeFragment = new MainARightUpEngineModeFragment();
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftRightAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterEngineFragment);
			ParentActivity._MainABaseFragment.RightChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainARightUpEngineModeFragment);
		}else if(index == 2){
			ParentActivity._MainABaseFragment._MainACenterTMFragment = new MainACenterTMFragment();
			//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
			if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
				ParentActivity._MainABaseFragment._MainARightDownTMICCOModeFragment = new MainARightDownTMICCOModeFragment();
			else
				ParentActivity._MainABaseFragment._MainARightDownTMCCOModeFragment = new MainARightDownTMCCOModeFragment();
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftRightAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterTMFragment);
			//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
			if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
				ParentActivity._MainABaseFragment.RightChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainARightDownTMICCOModeFragment);
			else
				ParentActivity._MainABaseFragment.RightChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainARightDownTMCCOModeFragment);
		}else if(index == 3){
			ParentActivity._MainABaseFragment._MainACenterTMFragment = new MainACenterTMFragment();
			ParentActivity._MainABaseFragment._MainARightDownTMShiftModeFragment = new MainARightDownTMShiftModeFragment();
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftRightAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterTMFragment);
			ParentActivity._MainABaseFragment.RightChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainARightDownTMShiftModeFragment);
		}else if(index == 4){
			ParentActivity._MainABaseFragment._MainACenterTMFragment = new MainACenterTMFragment();
			ParentActivity._MainABaseFragment._MainARightDownTMTCLockUpFragment = new MainARightDownTMTCLockUpFragment();
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftRightAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterTMFragment);
			ParentActivity._MainABaseFragment.RightChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainARightDownTMTCLockUpFragment);
		}
		
		ParentActivity._MainABaseFragment._LeftDisappearAnimation.StartAnimation();
		ParentActivity._MainABaseFragment._LeftBGDisappearAnimation.StartAnimation();
		ParentActivity._MainABaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainABaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainABaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMode.setClickable(ClickFlag);
		imgbtnHourOdo.setClickable(ClickFlag);
		
		imgbtnCCOMode.setClickable(ClickFlag);
		imgbtnShiftMode.setClickable(ClickFlag);
		imgbtnTCLockUp.setClickable(ClickFlag);
	}

	public void CursurDisplayDetail(int index){
		if(OptionTCLockUp == true){
			imgbtnHourOdo.setBackgroundResource(R.drawable._selector_rightup_main_a_hourodometer_btn1);
			imgbtnMode.setBackgroundResource(R.drawable._selector_right_main_a_middle_btn1);
			imgbtnCCOMode.setBackgroundResource(R.drawable._selector_right_main_a_middle_btn1);
			imgbtnShiftMode.setBackgroundResource(R.drawable._selector_right_main_a_middle_btn1);
			imgbtnTCLockUp.setBackgroundResource(R.drawable._selector_rightdown_main_a_tm_tclockup_btn1);

			switch(index){
				case 5:
					imgbtnHourOdo.setBackgroundResource(R.drawable.main_b_default_enginemode_s_02);
					break;
				case 6:
					imgbtnMode.setBackgroundResource(R.drawable.main_b_default_warmingup_s_02);
					break;
				case 7:
					imgbtnCCOMode.setBackgroundResource(R.drawable.main_b_default_cco_s_02);
					break;
				case 8:
					imgbtnShiftMode.setBackgroundResource(R.drawable.main_b_default_shift_s_02);
					break;
				case 9:
					imgbtnTCLockUp.setBackgroundResource(R.drawable.main_b_default_tclockup_s_02);
					break;
			}
		
		}
		else{
			imgbtnHourOdo.setBackgroundResource(R.drawable._selector_rightup_main_a_hourodometer_btn2);
			imgbtnMode.setBackgroundResource(R.drawable._selector_right_main_a_middle_btn2);
			imgbtnCCOMode.setBackgroundResource(R.drawable._selector_right_main_a_middle_btn3);
			imgbtnShiftMode.setBackgroundResource(R.drawable._selector_rightdown_main_a_tm_shiftmode_btn1);
			
			switch(index){
				case 5:
					imgbtnHourOdo.setBackgroundResource(R.drawable.main_b_default_no_tclockup_01_odometer_s_02);
					break;
				case 6:
					imgbtnMode.setBackgroundResource(R.drawable.main_b_default_no_tclockup_02_enginemode_s_02);
					break;
				case 7:
					imgbtnCCOMode.setBackgroundResource(R.drawable.main_b_default_no_tclockup_03_cco_s_02);
					break;
				case 8:
					imgbtnShiftMode.setBackgroundResource(R.drawable.main_b_default_no_tclockup_04_shift_s_02);
					break;
			}

		}
	}
}
