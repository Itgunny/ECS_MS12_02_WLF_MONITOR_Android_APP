package taeha.wheelloader.fseries_monitor.main.b;

import actionpopup.ActionItem;
import actionpopup.QuickAction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.ImageViewYAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LongPressChecker;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.LongPressChecker.OnLongPressListener;
import taeha.wheelloader.fseries_monitor.main.R.color;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.a.MainALeftUpMachineStatusSelectFragment1;
import taeha.wheelloader.fseries_monitor.main.a.MainALeftUpMachineStatusSelectFragment2;

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
	
	TextFitTextView textViewNormalUpperData;
	TextFitTextView textViewNormalUpperUnit;
	TextFitTextView textViewNormalLowerData;
	TextFitTextView textViewNormalLowerUnit;
	TextFitTextView textViewWeighingUpperData;
	TextFitTextView textViewWeighingUpperUnit;
	TextFitTextView textViewWeighingLowerData;
	TextFitTextView textViewWeighingLowerUnit;
	
	ImageView imgViewNormalUpperIcon;
	ImageView imgViewNormalLowerIcon;
	ImageView imgViewWeighingUpperIcon;
	ImageView imgViewWeighingLowerIcon;
	
//	ImageButton imgbtnMachineStatus;
	ImageButton imgbtnMachineStatus1;
	ImageButton imgbtnMachineStatus2;

	// ++, 150212 bwk
	private QuickAction popupIndicator;
	private ActionItem actionitemIndicator;
	// --, 150212 bwk
	
	public LongPressChecker     mLongPressChecker;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	int HYD;
	int Battery;
	int TMOil;
	int Coolant;
	// ++, 150327 bwk
	int FrontAxleTemp;
	int RearAxleTemp;
	// --, 150327 bwk
	
	int HYDHighWarning;
	int BatteryLowWarning;
	int TMOilHighWarning;
	int CoolantHighWarning;
	// ++, 150327 bwk
	int FrontAxleTempHighWarning;
	int RearAxleTempHighWarning;
	// --, 150327 bwk	
	
	int WeightInfoDataCurrent;
	int WeightInfoDataDay1;
	int WeightInfoDataToday;
	int WeightInfoDataTotalA;
	int WeightInfoDataTotalB;
	int WeightInfoDataTotalC;
	
	int WeightInfoCurrentWeighingResult;
		
	int WeighingDisplayMode;

	// ++, 150212 bwk
	int WeighingSystemError_BoomLiftSpeed;
	int WeighingSystemError_BucketFullIn;
	int WeighingSystemError_HydraulicOilTemperature;
	// --, 150212 bwk
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
		
		textViewNormalUpperData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_data);
		textViewNormalUpperUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_unit);
		textViewNormalLowerData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_data);
		textViewNormalLowerUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_unit);
		textViewWeighingUpperData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_data);
		textViewWeighingUpperUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_unit);
		textViewWeighingLowerData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_data);
		textViewWeighingLowerUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_unit);
		
		imgViewNormalUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_upper_icon);
		imgViewNormalLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_lower_icon);
		imgViewWeighingUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_upper_icon);
		imgViewWeighingLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_lower_icon);
		
//		imgbtnMachineStatus = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_b_machinestatus);
		imgbtnMachineStatus1 = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_b_machinestatus1);
		imgbtnMachineStatus2 = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_b_machinestatus2);

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		UpperStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		LowerStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		WeighingUpperStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		WeighingLowerStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);

		FrontAxleTempHighWarning = CAN1Comm.Get_Front_Axle_Oil_Temp_Warning_580_PGN65449();
		RearAxleTempHighWarning = CAN1Comm.Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449();
		
		// ++, 150212 bwk
		popupIndicator = new QuickAction(ParentActivity, QuickAction.VERTICAL);
		actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning), 139), getResources().getDrawable(R.drawable.main_default_monitoring_icon_fullin));
		popupIndicator.addActionItem(actionitemIndicator);		
		// --, 150212 bwk
		
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
//		imgViewWeighingUpperIcon.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			  	if(ClickFlag == true)
//			  		ClickWeighingError(v);
//			}
//		});
		imgViewWeighingUpperIcon.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					//Log.d(TAG, "x : " + event.getX() + "y : " + event.getY());
					if(ClickFlag == true)
				  		ClickWeighingError(v, event.getX());
				}
				return false;
			}
		});
		
//		imgbtnMachineStatus.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			  	if(ClickFlag == true)
//			  		ClickMachineStatus();
//			}
//		});
		imgbtnMachineStatus1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 1;
				if(mLongPressChecker.getLongPressed() == false){
					if(ClickFlag == true)
						ClickMachineStatus();
				}
			}
		});
		imgbtnMachineStatus1.setOnTouchListener( new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mLongPressChecker.deliverMotionEvent(v, event);
				return false;
			}
		});

		imgbtnMachineStatus2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 2;
				if(mLongPressChecker.getLongPressed() == false){
					if(ClickFlag == true)
						ClickMachineStatus();
				}
			}
		});		
		imgbtnMachineStatus2.setOnTouchListener( new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mLongPressChecker.deliverMotionEvent(v, event);
				return false;
			}
		});

		mLongPressChecker = new LongPressChecker(ParentActivity);
		mLongPressChecker.setOnLongPressListener( new OnLongPressListener() {
			@Override
			public void onLongPressed() {
//				Log.d(TAG, "Long Pressed");
				if(mLongPressChecker.mTargetView == imgbtnMachineStatus1 || mLongPressChecker.mTargetView == imgbtnMachineStatus2)
				{
					if(ParentActivity.MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING
							|| ParentActivity.MachineStatusLowerIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING)
						ClickLongKeyWeight(mLongPressChecker.mTargetView, WeighingDisplayMode);
					else
						if(ClickFlag == true)
							ClickMachineStatus();
				}
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
		// ++, 150327 bwk
		FrontAxleTemp = CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449();
		RearAxleTemp = CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449();
		// --, 150327 bwk
		HYDHighWarning = CAN1Comm.Get_HydraulicOilTemperatureHigh_102_PGN65427();
		BatteryLowWarning = CAN1Comm.Get_BatteryVoltageLow_706_PGN65427();
		CoolantHighWarning = CAN1Comm.Get_EngineCoolantTemperatureHigh_305_PGN65427();
		TMOilHighWarning = CAN1Comm.Get_TransmissionOilTemperatureHigh_537_PGN65427();
		// ++, 150327 bwk
		FrontAxleTempHighWarning = CAN1Comm.Get_Front_Axle_Oil_Temp_Warning_580_PGN65449();
		RearAxleTempHighWarning = CAN1Comm.Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449();
		// --, 150327 bwk
		
		WeightInfoDataDay1 = CAN1Comm.Get_ADayBeforeWeight_1916_PGN65452();
		WeightInfoDataToday = CAN1Comm.Get_TodayWeight_1915_PGN65450();
		WeightInfoDataTotalA = CAN1Comm.Get_TotalWorkAWeight_1912_PGN65451();
		WeightInfoDataTotalB = CAN1Comm.Get_TotalWorkBWeight_1913_PGN65451();
		WeightInfoDataTotalC = CAN1Comm.Get_TotalWorkCWeight_1914_PGN65452();
		WeightInfoDataCurrent = CAN1Comm.Get_CurrentWeight_1911_PGN65450();

		WeighingDisplayMode = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
		WeightInfoCurrentWeighingResult = CAN1Comm.Get_CurrentWeighingResult_1919_PGN65450();

		// ++, 150212 bwk
		WeighingSystemError_BoomLiftSpeed = CAN1Comm.Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450();
		WeighingSystemError_BucketFullIn = CAN1Comm.Get_WeighingSystemError_BucketFullIn_1943_PGN65450();
		WeighingSystemError_HydraulicOilTemperature = CAN1Comm.Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450();
		// --, 150212 bwk		
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
		// ++, 150327 bwk
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			if(CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449() != 0xFF)
			{
				LayoutNormalUpper.setVisibility(View.VISIBLE);
				LayoutWeighingUpper.setVisibility(View.GONE);
				UpperFrontAxleDisplay(imgViewNormalUpperIcon,textViewNormalUpperData,textViewNormalUpperUnit,FrontAxleTemp,ParentActivity.UnitTemp);
			}
			else
			{
				ParentActivity.MachineStatusUpperIndex= CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
				LayoutNormalLower.setVisibility(View.GONE);
				LayoutWeighingLower.setVisibility(View.GONE);
			}			
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			if(CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449() != 0xFF)
			{
				LayoutNormalUpper.setVisibility(View.VISIBLE);
				LayoutWeighingUpper.setVisibility(View.GONE);
				UpperRearAxleDisplay(imgViewNormalUpperIcon,textViewNormalUpperData,textViewNormalUpperUnit,RearAxleTemp,ParentActivity.UnitTemp);
			}
			else
			{
				ParentActivity.MachineStatusUpperIndex= CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
				LayoutNormalLower.setVisibility(View.GONE);
				LayoutWeighingLower.setVisibility(View.GONE);
			}
			break;
		// --, 150327 bwk
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			LayoutNormalUpper.setVisibility(View.GONE);
			LayoutWeighingUpper.setVisibility(View.VISIBLE);
			// ++, 150212 bwk
			/*
			WeighingUpperDisplay(WeightInfoCurrentWeighingResult,WeighingDisplayMode,WeightInfoDataCurrent,
					WeightInfoDataDay1,WeightInfoDataToday,WeightInfoDataTotalA,WeightInfoDataTotalB,WeightInfoDataTotalC,ParentActivity.UnitWeight);
			*/
			WeighingUpperDisplay(WeighingSystemError_BoomLiftSpeed,WeighingSystemError_BucketFullIn,WeighingSystemError_HydraulicOilTemperature,
					WeighingDisplayMode,WeightInfoDataCurrent,WeightInfoDataDay1,WeightInfoDataToday,WeightInfoDataTotalA,WeightInfoDataTotalB,WeightInfoDataTotalC,
					ParentActivity.UnitWeight);
			// --, 150212 bwk
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
		// ++, 150329 bwk
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			if(CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449() != 0xFF)
			{
				LayoutNormalLower.setVisibility(View.VISIBLE);
				LayoutWeighingLower.setVisibility(View.GONE);
				LowerFrontAxleDisplay(imgViewNormalLowerIcon,textViewNormalLowerData,textViewNormalLowerUnit,FrontAxleTemp,ParentActivity.UnitTemp);
			}
			else
			{
				ParentActivity.MachineStatusLowerIndex= CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
				LayoutNormalLower.setVisibility(View.GONE);
				LayoutWeighingLower.setVisibility(View.GONE);
			}
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			if(CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449() != 0xFF)
			{
				LayoutNormalLower.setVisibility(View.VISIBLE);
				LayoutWeighingLower.setVisibility(View.GONE);
				LowerRearAxleDisplay(imgViewNormalLowerIcon,textViewNormalLowerData,textViewNormalLowerUnit,RearAxleTemp,ParentActivity.UnitTemp);
			}
			else
			{
				ParentActivity.MachineStatusLowerIndex= CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
				LayoutNormalLower.setVisibility(View.GONE);
				LayoutWeighingLower.setVisibility(View.GONE);
			}
			break;
		// --, 150329 bwk			
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
	// ++, 150327 bwk
	public void UpperFrontAxleDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(FrontAxleTempHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_front_axl_red);
		}else{
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_front_axl);
		}
		AxleDisplay(textData,textUnit,Data,Unit);
	}
	public void LowerFrontAxleDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(FrontAxleTempHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_front_axl_red);
		}else{
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_front_axl);
		}
		AxleDisplay(textData,textUnit,Data,Unit);
	}
	public void UpperRearAxleDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(RearAxleTempHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_rear_axle_red);
		}else{
			UpperStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_rear_axle);
		}
		AxleDisplay(textData,textUnit,Data,Unit);
	}
	public void LowerRearAxleDisplay(ImageView imgIcon, TextView textData, TextView textUnit, int Data, int Unit){
		if(RearAxleTempHighWarning == CAN1CommManager.DATA_STATE_LAMP_ON){
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_rear_axle_red);
		}else{
			LowerStatusIconAnimation.FlipAnimation(imgIcon, R.drawable.main_default_monitoring_icon_rear_axle);
		}
		AxleDisplay(textData,textUnit,Data,Unit);
	}
	// --, 150327 bwk
	public void HYDDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTemp(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
	}
	public void TMOilDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTemp(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
	}
	public void CoolantDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTemp(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
	}
	public void BatteryDisplay(TextView textData, TextView textUnit, int Data){
		textData.setText(ParentActivity.GetBattery(Data));
		textUnit.setText(ParentActivity.getResources().getString(string.V));
	}
	// ++, 150327 bwk
	public void AxleDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTempAxle(Data,Unit));
		if(Unit == ParentActivity.UNIT_TEMP_F){
			textUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
	}
	// --, 150327 bwk

	// ++, 150212 bwk
	//public void WeighingUpperDisplay(int CurrentWeighingResult, int DisplayIndex, int Current, int Day1, int Today, int TotalA, int TotalB, int TotalC, int Unit){
	public void WeighingUpperDisplay(int BoomLiftSpeedError, int BucketFullInError, int HydOilTempError, int DisplayIndex, int Current, int Day1, int Today, int TotalA, int TotalB, int TotalC, int Unit){
	// --, 150212 bwk
		if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_LB){
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.lb));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.lb));
		}else if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_US_TON){
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.USTon));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.USTon));
		}else{
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.ton));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.ton));
		}

		// ++, 150212 bwk
		//if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_HYDTEMPLOW
		if(HydOilTempError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
		
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_hyd_blue);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.white));
		}
		//else if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN 
		else if(BoomLiftSpeedError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& BucketFullInError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
		// --, 150212 bwk
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_sudden_fullin);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		// ++, 150212 bwk
		//}else if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING
		}else if(BoomLiftSpeedError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
		// --, 150212 bwk
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_sudden);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		// ++, 150212 bwk
		//}else if(CurrentWeighingResult == CAN1CommManager.DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN
		}else if(BucketFullInError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
		// --, 150212 bwk
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
		if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_LB){
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.lb));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.lb));
		}else if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_US_TON){
			textViewWeighingUpperUnit.setText(ParentActivity.getResources().getString(string.USTon));
			textViewWeighingLowerUnit.setText(ParentActivity.getResources().getString(string.USTon));
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
	
	public void ClickLongKeyWeight(View v, int DisplayIndex){
		if(v == imgbtnMachineStatus1)
		{
			switch (DisplayIndex) {
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
				ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_B_TOP;
				ParentActivity._WorkLoadWeighingInitPopup2.setMode(3);
				ParentActivity.showWorkLoadWeighingInit2();
				break;
			default:
				ClickMachineStatus();
				break;
			}			
		
		}
		else if(v == imgbtnMachineStatus2)
		{
			switch (DisplayIndex) {
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
				ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_B_TOP;
				ParentActivity._WorkLoadWeighingInitPopup2.setMode(DisplayIndex);
				ParentActivity.showWorkLoadWeighingInit2();
				break;
			default:
				ClickMachineStatus();
				break;
			}			
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////
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
			showMachineStatusFragment();
		}
	}

	// ++, 150212 bwk
	public void ClickWeighingError(View v, float X){
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
				if(X < 60)
					showLampPopup(DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN);
				else
					showLampPopup(DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING);
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
				showMachineStatusFragment();
			}
		}else{
			showMachineStatusFragment();
		}
	}
	// --, 150212 bwk
	public void showMachineStatusFragment(){
		ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment = new MainBCenterMachineStatusFragment();
		if(CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449() != 0xFF)
			ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment3 = new MainBLeftUpMachineStatusSelectFragment3();
		else if(CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449() != 0xFF)
			ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment2 = new MainBLeftUpMachineStatusSelectFragment2();
		else
			ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment1 = new MainBLeftUpMachineStatusSelectFragment1();
		
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment);
		if(CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449() != 0xFF)
			ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment3);
		else if(CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449() != 0xFF)
			ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment2);
		else
			ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment1);
		
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
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
//		imgbtnMachineStatus.setClickable(ClickFlag);
		imgbtnMachineStatus1.setClickable(ClickFlag);
		imgbtnMachineStatus2.setClickable(ClickFlag);
	}

	// ++, 150212 bwk
	public void showLampPopup(int Index){
		popupIndicator.removeAllActionItem();
		switch (Index) {
		case DATA_STATE_CURRENT_WEIHGING_RESULT_HYDRAULICOILTEMP:
			actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Hydraulic_Oil_Temp_Low_Warning), 141));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN:
			actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Sudden_Change_Error_Warning), 140)+"\n"+
								getString(ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning), 139));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING:
			actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Sudden_Change_Error_Warning), 140));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN:
			actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning), 139));
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
	// --, 150212 bwk
	public void CursurDisplayDetail(int index){
		imgbtnMachineStatus1.setBackgroundResource(R.drawable._selector_leftup_main_b_machinestatus_up_btn);
		imgbtnMachineStatus2.setBackgroundResource(R.drawable._selector_leftup_main_b_machinestatus_down_btn);
		switch(index){
			case 1:
				imgbtnMachineStatus1.setBackgroundResource(R.drawable.main_default_monitoring_01_selected02);
				break;
			case 2:
				imgbtnMachineStatus2.setBackgroundResource(R.drawable.main_default_monitoring_02_selected02);
				break;
		}
	}
}