package taeha.wheelloader.fseries_monitor.main.b;

import actionpopup.ActionItem;
import actionpopup.QuickAction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.ImageViewYAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.color;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainBLeftUpMachineStatusFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// ++, 150207 bwk
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_HYDRAULICOILTEMP			= 1;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN	= 2;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING				= 3;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN				= 4;
	// --, 150207 bwk
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutNormalUpper;
	RelativeLayout LayoutNormalLower;
	RelativeLayout LayoutWeighingUpper;
	RelativeLayout LayoutWeighingLower;
	
	TextView textViewNormalUpperData;
	TextView textViewNormalUpperUnit;
	TextView textViewNormalLowerData;
	TextView textViewNormalLowerUnit;
	TextView textViewWeighingUpperData;
	TextView textViewWeighingUpperUnit;
	TextView textViewWeighingLowerData;
	TextView textViewWeighingLowerUnit;
	
	ImageView imgViewNormalUpperIcon;
	ImageView imgViewNormalLowerIcon;
	ImageView imgViewWeighingUpperIcon;
	ImageView imgViewWeighingLowerIcon;
	
	ImageButton imgbtnMachineStatus;

	// ++, 150205 bwk
	private QuickAction popupIndicator;
	private ActionItem actionitemIndicator;
	// --, 150205 bwk
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	int HYD;
	int Battery;
	int TMOil;
	int Coolant;
	
	int HYDHighWarning;
	int BatteryLowWarning;
	int TMOilHighWarning;
	int CoolantHighWarning;
	
	int WeightInfoDataCurrent;
	int WeightInfoDataDay1;
	int WeightInfoDataToday;
	int WeightInfoDataTotalA;
	int WeightInfoDataTotalB;
	int WeightInfoDataTotalC;
	
	int WeightInfoCurrentWeighingResult;
		
	int WeighingDisplayMode;
	
	// ++, 150207 bwk
	int WeighingSystemError_BoomLiftSpeed;
	int WeighingSystemError_BucketFullIn;
	int WeighingSystemError_HydraulicOilTemperature;
	// --, 150207 bwk
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	ImageViewYAxisFlipAnimation UpperStatusIconAnimation;
	ImageViewYAxisFlipAnimation LowerStatusIconAnimation;
	ImageViewYAxisFlipAnimation WeighingUpperStatusIconAnimation;
	ImageViewYAxisFlipAnimation WeighingLowerStatusIconAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBLeftUpMachineStatusFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftup_main_b_machinestatus, null);
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
		
		LayoutNormalUpper = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_normal_upper);
		LayoutNormalLower = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_normal_lower);
		LayoutWeighingUpper = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_weighing_upper);
		LayoutWeighingLower = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_weighing_lower);
		
		textViewNormalUpperData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_data);
		textViewNormalUpperUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_unit);
		textViewNormalLowerData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_data);
		textViewNormalLowerUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_unit);
		textViewWeighingUpperData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_data);
		textViewWeighingUpperUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_unit);
		textViewWeighingLowerData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_data);
		textViewWeighingLowerUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_unit);
		
		imgViewNormalUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_upper_icon);
		imgViewNormalLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_lower_icon);
		imgViewWeighingUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_upper_icon);
		imgViewWeighingLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_lower_icon);
		
		imgbtnMachineStatus = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_b_machinestatus);

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		UpperStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		LowerStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		WeighingUpperStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		WeighingLowerStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		
		// ++, 150205 bwk
		popupIndicator = new QuickAction(ParentActivity, QuickAction.VERTICAL);
		actionitemIndicator = new ActionItem(0, ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning), getResources().getDrawable(R.drawable.main_default_monitoring_icon_fullin));
		popupIndicator.addActionItem(actionitemIndicator);		
		// --, 150205 bwk
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		// ++, 150207 bwk
		imgViewWeighingUpperIcon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  	if(ClickFlag == true)
			  		ClickWeighingError(v);
			}
		});
		// --, 150207 bwk
		imgbtnMachineStatus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  	if(ClickFlag == true)
			  		ClickMachineStatus();
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
		
		WeightInfoDataDay1 = CAN1Comm.Get_ADayBeforeWeight_1916_PGN65452();
		WeightInfoDataToday = CAN1Comm.Get_TodayWeight_1915_PGN65450();
		WeightInfoDataTotalA = CAN1Comm.Get_TotalWorkAWeight_1912_PGN65451();
		WeightInfoDataTotalB = CAN1Comm.Get_TotalWorkBWeight_1913_PGN65451();
		WeightInfoDataTotalC = CAN1Comm.Get_TotalWorkCWeight_1914_PGN65452();
		WeightInfoDataCurrent = CAN1Comm.Get_CurrentWeight_1911_PGN65450();

		WeighingDisplayMode = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
		WeightInfoCurrentWeighingResult = CAN1Comm.Get_CurrentWeighingResult_1919_PGN65450();
		
		// ++, 150207 bwk
		WeighingSystemError_BoomLiftSpeed = CAN1Comm.Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450();
		WeighingSystemError_BucketFullIn = CAN1Comm.Get_WeighingSystemError_BucketFullIn_1943_PGN65450();
		WeighingSystemError_HydraulicOilTemperature = CAN1Comm.Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450();
		// --, 150207 bwk		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		try {
			UpperLowerDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void UpperLowerDisplay(int Upper, int Lower){
		if(Upper == CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT){
			LayoutNormalUpper.setVisibility(View.GONE);
			LayoutWeighingUpper.setVisibility(View.GONE);
		}
		if(Lower == CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT){
			LayoutNormalLower.setVisibility(View.GONE);
			LayoutWeighingLower.setVisibility(View.GONE);
		}
		
		switch (Upper) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			LayoutNormalUpper.setVisibility(View.VISIBLE);
			LayoutWeighingUpper.setVisibility(View.GONE);
			UpperHYDDisplay(imgViewNormalUpperIcon,textViewNormalUpperData,textViewNormalUpperUnit,HYD,ParentActivity.UnitTemp);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			LayoutNormalUpper.setVisibility(View.VISIBLE);
			LayoutWeighingUpper.setVisibility(View.GONE);
			UpperBatteryDisplay(imgViewNormalUpperIcon,textViewNormalUpperData,textViewNormalUpperUnit,Battery);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			LayoutNormalUpper.setVisibility(View.VISIBLE);
			LayoutWeighingUpper.setVisibility(View.GONE);
			UpperTMOilDisplay(imgViewNormalUpperIcon,textViewNormalUpperData,textViewNormalUpperUnit,TMOil,ParentActivity.UnitTemp);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			LayoutNormalUpper.setVisibility(View.VISIBLE);
			LayoutWeighingUpper.setVisibility(View.GONE);
			UpperCoolantDisplay(imgViewNormalUpperIcon,textViewNormalUpperData,textViewNormalUpperUnit,Coolant,ParentActivity.UnitTemp);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			LayoutNormalUpper.setVisibility(View.GONE);
			LayoutWeighingUpper.setVisibility(View.VISIBLE);
			// ++, 150207 bwk
			/*
			WeighingUpperDisplay(WeightInfoCurrentWeighingResult,WeighingDisplayMode,WeightInfoDataCurrent,
					WeightInfoDataDay1,WeightInfoDataToday,WeightInfoDataTotalA,WeightInfoDataTotalB,WeightInfoDataTotalC,ParentActivity.UnitWeight);
			*/
			WeighingUpperDisplay(WeighingSystemError_BoomLiftSpeed,WeighingSystemError_BucketFullIn,WeighingSystemError_HydraulicOilTemperature,
					WeighingDisplayMode,WeightInfoDataCurrent,WeightInfoDataDay1,WeightInfoDataToday,WeightInfoDataTotalA,WeightInfoDataTotalB,WeightInfoDataTotalC,
					ParentActivity.UnitWeight);
			// --, 150207 bwk
			break;

		default:
			break;
		}
		switch (Lower) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			LayoutNormalLower.setVisibility(View.VISIBLE);
			LayoutWeighingLower.setVisibility(View.GONE);
			LowerHYDDisplay(imgViewNormalLowerIcon,textViewNormalLowerData,textViewNormalLowerUnit,HYD,ParentActivity.UnitTemp);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			LayoutNormalLower.setVisibility(View.VISIBLE);
			LayoutWeighingLower.setVisibility(View.GONE);
			LowerBatteryDisplay(imgViewNormalLowerIcon,textViewNormalLowerData,textViewNormalLowerUnit,Battery);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			LayoutNormalLower.setVisibility(View.VISIBLE);
			LayoutWeighingLower.setVisibility(View.GONE);
			LowerTMOilDisplay(imgViewNormalLowerIcon,textViewNormalLowerData,textViewNormalLowerUnit,TMOil,ParentActivity.UnitTemp);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			LayoutNormalLower.setVisibility(View.VISIBLE);
			LayoutWeighingLower.setVisibility(View.GONE);
			LowerCoolantDisplay(imgViewNormalLowerIcon,textViewNormalLowerData,textViewNormalLowerUnit,Coolant,ParentActivity.UnitTemp);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			LayoutNormalLower.setVisibility(View.GONE);
			LayoutWeighingLower.setVisibility(View.VISIBLE);
			WeighingLowerDisplay(WeighingDisplayMode,WeightInfoDataCurrent,
					WeightInfoDataDay1,WeightInfoDataToday,WeightInfoDataTotalA,WeightInfoDataTotalB,WeightInfoDataTotalC,ParentActivity.UnitWeight);
			break;

		default:
			break;
		}
		
		
	}
	public void UpperHYDDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(HYDHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_hyd_red);
		}else{
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_hyd);
		}
		HYDDisplay(textData,textUnit,Data,Unit);
	}
	public void LowerHYDDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(HYDHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_hyd_red);
		}else{
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_hyd);
		}
		HYDDisplay(textData,textUnit,Data,Unit);
	}
	public void UpperTMOilDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(TMOilHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_tm_red);
		}else{
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_tm);
		}
		TMOilDisplay(textData,textUnit,Data,Unit);
	}
	public void LowerTMOilDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(TMOilHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_tm_red);
		}else{
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_tm);
		}
		TMOilDisplay(textData,textUnit,Data,Unit);
	}
	public void UpperCoolantDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(CoolantHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_cool_red);
		}else{
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_cool);
		}
		CoolantDisplay(textData,textUnit,Data,Unit);
	}
	public void LowerCoolantDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(CoolantHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_cool_red);
		}else{
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_cool);
		}
		CoolantDisplay(textData,textUnit,Data,Unit);
	}
	public void UpperBatteryDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data){
		if(BatteryLowWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_bart_red);
		}else{
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_batt);
		}
		BatteryDisplay(textData,textUnit,Data);
	}
	public void LowerBatteryDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data){
		if(BatteryLowWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_bart_red);
		}else{
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_batt);
		}
		BatteryDisplay(textData,textUnit,Data);
	}
	public void HYDDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTemp(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(ParentActivity.getResources().getString(string.F));
		}else{
			textUnit.setText(ParentActivity.getResources().getString(string.C));
		}
	}
	public void TMOilDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTemp(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(ParentActivity.getResources().getString(string.F));
		}else{
			textUnit.setText(ParentActivity.getResources().getString(string.C));
		}
	}
	public void CoolantDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTemp(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(ParentActivity.getResources().getString(string.F));
		}else{
			textUnit.setText(ParentActivity.getResources().getString(string.C));
		}
	}
	public void BatteryDisplay(TextView textData, TextView textUnit, int Data){
		textData.setText(ParentActivity.GetBattery(Data));
		textUnit.setText(ParentActivity.getResources().getString(string.V));
	}

	// ++, 150207 bwk
	//public void WeighingUpperDisplay(int CurrentWeighingResult, int DisplayIndex, int Current, int Day1, int Today, int TotalA, int TotalB, int TotalC, int Unit){
	public void WeighingUpperDisplay(int BoomLiftSpeedError, int BucketFullInError, int HydOilTempError, int DisplayIndex, int Current, int Day1, int Today, int TotalA, int TotalB, int TotalC, int Unit){
	// --, 150207 bwk
		if(ParentActivity.UnitWeight == ParentActivity.UNIT_WEIGHT_LB){
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.lb));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.lb));
		}else{
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.ton));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.ton));
		}
		
		// ++, 150207 bwk
		if(HydOilTempError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_hyd_blue);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.white));
		}
		//if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN 
		else if(BoomLiftSpeedError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& BucketFullInError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
		// --, 150207 bwk
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_sudden_fullin);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		// ++, 150207 bwk
		//}else if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING
		}else if(BoomLiftSpeedError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
		// --, 150207 bwk
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_sudden);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		// ++, 150207 bwk
		// }else if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN
		}else if(BucketFullInError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
		// --, 150207 bwk
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_fullin);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		}else{
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.white));
			switch (DisplayIndex) {
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
				WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_d1);
				textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataDay1, ParentActivity.UnitWeight));

				break;
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
				WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_work);
				textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			
				break;
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
				WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_work);
				textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			
				break;
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
				WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_work);
				textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			
				break;

			default:
				break;
			}
		}
	}
	public void WeighingLowerDisplay(int DisplayIndex, int Current, int Day1, int Today, int TotalA, int TotalB, int TotalC, int Unit){
		if(ParentActivity.UnitWeight == ParentActivity.UNIT_WEIGHT_LB){
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.lb));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.lb));
		}else{
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.ton));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.ton));
		}
		
		switch (DisplayIndex) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			WeighingLowerStatusIconAnimation.FlipAnimation(imgViewWeighingLowerIcon,R.drawable.main_default_monitoring_icon_today);
			textViewWeighingLowerData.setText(ParentActivity.GetWeighit(WeightInfoDataToday, ParentActivity.UnitWeight));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			WeighingLowerStatusIconAnimation.FlipAnimation(imgViewWeighingLowerIcon,R.drawable.main_default_monitoring_icon_total_a);
			textViewWeighingLowerData.setText(ParentActivity.GetWeighit(WeightInfoDataTotalA, ParentActivity.UnitWeight));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			WeighingLowerStatusIconAnimation.FlipAnimation(imgViewWeighingLowerIcon,R.drawable.main_default_monitoring_icon_total_b);
			textViewWeighingLowerData.setText(ParentActivity.GetWeighit(WeightInfoDataTotalB, ParentActivity.UnitWeight));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			WeighingLowerStatusIconAnimation.FlipAnimation(imgViewWeighingLowerIcon,R.drawable.main_default_monitoring_icon_total_c);
			textViewWeighingLowerData.setText(ParentActivity.GetWeighit(WeightInfoDataTotalC, ParentActivity.UnitWeight));
			break;

		default:
			break;
		}	
	}
	
	public void ClickMachineStatus(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		// ++, 150207 bwk
		/*
		if(WeightInfoCurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			CAN1Comm.Set_RequestReweighing_PGN61184_62(1);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
		}else if(WeightInfoCurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			CAN1Comm.Set_RequestReweighing_PGN61184_62(1);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
		}else if(WeightInfoCurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			CAN1Comm.Set_RequestReweighing_PGN61184_62(1);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
		}else
		 */
		
		// --, 150207 bwk
		{
			ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment = new MainBCenterMachineStatusFragment();
			ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment = new MainBLeftUpMachineStatusSelectFragment();
			ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftUpAnimation();
			ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment);
			ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment);
			
			//ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
			
			ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		}
	}
	
	// ++, 150207 bwk
	public void ClickWeighingError(View v){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		if(ParentActivity.MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING
			|| ParentActivity.MachineStatusLowerIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING)
		{
			if(WeighingSystemError_HydraulicOilTemperature == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
				showLampPopup(DATA_STATE_CURRENT_WEIHGING_RESULT_HYDRAULICOILTEMP);
				popupIndicator.show(v);
			}
			else if(WeighingSystemError_BoomLiftSpeed == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& WeighingSystemError_BucketFullIn == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
				showLampPopup(DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN);
				popupIndicator.show(v);
			}else if(WeighingSystemError_BoomLiftSpeed == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
				showLampPopup(DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING);
				popupIndicator.show(v);
			}else if(WeighingSystemError_BucketFullIn == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
				showLampPopup(DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN);
				popupIndicator.show(v);
			}else{
				ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment = new MainBCenterMachineStatusFragment();
				ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment = new MainBLeftUpMachineStatusSelectFragment();
				ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftUpAnimation();
				ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment);
				ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment);
				
				//ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
				
				ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
				ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
			}
		}else{
			ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment = new MainBCenterMachineStatusFragment();
			ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment = new MainBLeftUpMachineStatusSelectFragment();
			ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftUpAnimation();
			ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment);
			ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment);
			
			//ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
			
			ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
			ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		}
	}
	// --, 150207 bwk
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMachineStatus.setClickable(ClickFlag);
	}

	// ++, 150207 bwk
	public void showLampPopup(int Index){
		popupIndicator.removeAllActionItem();
		switch (Index) {
		case DATA_STATE_CURRENT_WEIHGING_RESULT_HYDRAULICOILTEMP:
			actionitemIndicator = new ActionItem(0, ParentActivity.getResources().getString(R.string.Hydraulic_Oil_Temp_Low_Warning));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN:
			actionitemIndicator = new ActionItem(0, ParentActivity.getResources().getString(R.string.SuddenChange_BucketFullIn_Error_Warning));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING:
			actionitemIndicator = new ActionItem(0, ParentActivity.getResources().getString(R.string.Sudden_Change_Error_Warning));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN:
			actionitemIndicator = new ActionItem(0, ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		default:
			break;
		}
	}
	
	public void ClickEnter(){
		if(ParentActivity.MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING
				|| ParentActivity.MachineStatusLowerIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING)
		{
			if((WeighingSystemError_HydraulicOilTemperature == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON)
				||(WeighingSystemError_BoomLiftSpeed == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& WeighingSystemError_BucketFullIn == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON)
				||(WeighingSystemError_BoomLiftSpeed == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
					&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON)
				||(WeighingSystemError_BucketFullIn == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
						&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON)){
				CAN1Comm.Set_RequestReweighing_PGN61184_62(1);
				CAN1Comm.TxCANToMCU(62);
				CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
			}
		}
	}
	// --, 150207 bwk
}