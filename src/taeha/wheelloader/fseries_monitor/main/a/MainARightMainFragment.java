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
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainARightMainFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewModeTitle;
	TextView textViewModeData;	
	TextView textViewHourOdoTitle;
	TextView textViewHourOdoData;
	TextView textViewHourOdoUnit;
	
	TextView textViewCCOModeTitle;
	TextView textViewCCOModeData;
	TextView textViewShiftModeTitle;
	TextView textViewShiftModeData;
	TextView textViewTCLockUpTitle;
	TextView textViewTCLockUpData;
	
	ImageButton imgbtnMode;
	ImageButton imgbtnHourOdo;
	ImageButton imgbtnCCOMode;
	ImageButton imgbtnShiftMode;
	ImageButton imgbtnTCLockUp;
	
	RelativeLayout layoutTCLockUp;
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
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	TextViewXAxisFlipAnimation EngineModeDataAnimation;
	TextViewXAxisFlipAnimation HourOdometerTitleAnimation;	
	//TextViewXAxisFlipAnimation TMCCOModeTitleDataAnimation;	// ++, --, 150305 HHI 변경 요청
	TextViewXAxisFlipAnimation TMCCOModeDataAnimation;
	TextViewXAxisFlipAnimation TMShiftModeDataAnimation;
	TextViewXAxisFlipAnimation TMTCLockUpDataAnimation;
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
		textViewModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_a_engine_mode_title);
		textViewModeData = (TextView)mRoot.findViewById(R.id.textView_rightup_main_a_engine_mode_data);
		textViewHourOdoTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_title);
		textViewHourOdoData = (TextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_data);
		textViewHourOdoUnit = (TextView)mRoot.findViewById(R.id.textView_rightup_main_a_hourodometer_unit);
		
		imgbtnMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_a_engine_mode);
		imgbtnHourOdo = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_a_hourodometer);
		textViewCCOModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_ccomode_title);
		textViewCCOModeData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_ccomode_data);
		
		textViewShiftModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_shiftmode_title);
		textViewShiftModeData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_shiftmode_data);
		
		textViewTCLockUpTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_tclockup_title);
		textViewTCLockUpData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_a_tm_tclockup_data);
		
		imgbtnCCOMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_ccomode);
		imgbtnShiftMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_shiftmode);
		imgbtnTCLockUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_a_tm_tclockup);
		
		layoutTCLockUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_a_tm_tclockup);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		EngineModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		HourOdometerTitleAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		//TMCCOModeTitleDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);	// ++, --, 150305 HHI 변경 요청
		TMCCOModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		TMShiftModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		TMTCLockUpDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnHourOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickHourOdo();
			}
		});
		imgbtnMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickMode();
			}
		});
		
		imgbtnCCOMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickCCOMode();
			}
		});
		imgbtnShiftMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickShiftMode();
			}
		});
		imgbtnTCLockUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
		
		TMCCOModeTitleDisplay(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()));
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
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.POWER));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.STANDARD));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.ECONO));
				break;
			default:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.POWER));
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
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.LATEST_HOURMETER));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.TOTAL_ODOMETER));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.LATEST_ODOMETER));
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
			textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.Hr));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			textViewHourOdoData.setText(ParentActivity.GetOdometerStrng(TotalOdo,ParentActivity.UnitOdo));
			if(ParentActivity.UnitOdo == ParentActivity.UNIT_ODO_MILE){
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.mile));
			}else{
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.km));
			}
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			textViewHourOdoData.setText(ParentActivity.GetOdometerStrng(LatestOdo,ParentActivity.UnitOdo));
			if(ParentActivity.UnitOdo == ParentActivity.UNIT_ODO_MILE){
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.mile));
			}else{
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.km));
			}
			break;

		default:
			break;
		}
		
	}	
	
	public void TCLockUpShow(){
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			layoutTCLockUp.setVisibility(View.GONE);
			imgbtnTCLockUp.setClickable(false);
		}else if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
			layoutTCLockUp.setVisibility(View.GONE);
			imgbtnTCLockUp.setClickable(false);
		}else{
			layoutTCLockUp.setVisibility(View.VISIBLE);
			imgbtnTCLockUp.setClickable(true);
		}
	}
	public void TMCCOModeTitleDisplay(int Model){
		// ++, 150305 bwk
		//if(Model == CheckModel.MODEL_980){
		//	TMCCOModeTitleDataAnimation.FlipAnimation(textViewCCOModeTitle,getResources().getString(string.ICCO_MODE));
		//}else{
		//	TMCCOModeTitleDataAnimation.FlipAnimation(textViewCCOModeTitle,getResources().getString(string.CCO_MODE));
		//}
		if(Model == CheckModel.MODEL_980){
			textViewCCOModeTitle.setText(getResources().getString(string.ICCO_MODE));
		}else{
			textViewCCOModeTitle.setText(getResources().getString(string.CCO_MODE));
		}
		// --, 150305 bwk
		
	}
	public void TMCCOModeDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.OFF));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.L));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.M));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.H));
				break;
			default:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.OFF));
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
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.MANUAL));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.AL));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.AN));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.AH));
				break;
			default:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.MANUAL));
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
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.OFF));
				break;
			case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.ON));
				break;
			default:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.OFF));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMTCLockUpDisplay");
		}
	}
	
	public void ClickHourOdo(){
		ShowSelectAnimation(0);
	}
	public void ClickMode(){
		ShowSelectAnimation(1);
	}
	
	public void ClickCCOMode(){
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
			ShowSelectAnimation(2);
		else
			ShowSelectAnimation(2);
	}
	public void ClickShiftMode(){
		ShowSelectAnimation(3);
	}
	public void ClickTCLockUp(){
		ShowSelectAnimation(4);
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
			ParentActivity._MainABaseFragment._MainARightDownTMCCOModeFragment = new MainARightDownTMCCOModeFragment();
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftRightAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterTMFragment);
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
}
