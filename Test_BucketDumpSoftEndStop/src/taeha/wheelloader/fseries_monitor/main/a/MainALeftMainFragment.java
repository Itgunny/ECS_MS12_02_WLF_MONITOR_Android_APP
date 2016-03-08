package taeha.wheelloader.fseries_monitor.main.a;

import taeha.wheelloader.fseries_monitor.animation.BarAnimation;
import taeha.wheelloader.fseries_monitor.animation.ImageViewYAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.animation.TextFitTextViewAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LongPressChecker;
import taeha.wheelloader.fseries_monitor.main.LongPressChecker.OnLongPressListener;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.color;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import actionpopup.ActionItem;
import actionpopup.QuickAction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainALeftMainFragment extends ParentFragment{
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
	TextFitTextView textViewFuelTitle;
	TextFitTextView textViewFuelData;
	TextFitTextView textViewFuelUnit;	
	
	ImageView imgViewNormalUpperIcon;
	ImageView imgViewNormalLowerIcon;
	ImageView imgViewWeighingUpperIcon;
	ImageView imgViewWeighingLowerIcon;
	ImageView imgViewEcoIcon;
	ImageView imgViewEcoBG;
	ImageView imgViewEcoBar;	
	
//	ImageButton imgbtnMachineStatus;
	ImageButton imgbtnMachineStatus1;
	ImageButton imgbtnMachineStatus2;
	ImageButton imgbtnFuel;

	private QuickAction popupIndicator;
	private ActionItem actionitemIndicator;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	int HYD;
	int Battery;
	int TMOil;
	int Coolant;
	// ++, 150329 bwk
	int FrontAxleTemp;
	int RearAxleTemp;
	// --, 150329 bwk	
	int HYDHighWarning;
	int BatteryLowWarning;
	int TMOilHighWarning;
	int CoolantHighWarning;
	// ++, 150329 bwk
	int FrontAxleTempHighWarning;
	int RearAxleTempHighWarning;
	// --, 150329 bwk		
	
	int WeightInfoDataCurrent;
	int WeightInfoDataDay1;
	int WeightInfoDataToday;
	int WeightInfoDataTotalA;
	int WeightInfoDataTotalB;
	int WeightInfoDataTotalC;
	
	int WeightInfoCurrentWeighingResult;
		
	int WeighingDisplayMode;

	int WeighingSystemError_BoomLiftSpeed;
	int WeighingSystemError_BucketFullIn;
	int WeighingSystemError_HydraulicOilTemperature;
	
	int AverageFuelRate;
	int LastestConsumed;
	
	int EcoGaugeLevel;
	int EcoGaugeStatus;	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	ImageViewYAxisFlipAnimation UpperStatusIconAnimation;
	ImageViewYAxisFlipAnimation LowerStatusIconAnimation;
	ImageViewYAxisFlipAnimation WeighingUpperStatusIconAnimation;
	ImageViewYAxisFlipAnimation WeighingLowerStatusIconAnimation;
	
	TextFitTextViewAxisFlipAnimation	FuelTitleAnimation;
	
	public BarAnimation			_EcoGaugeAnimation;
	public LongPressChecker     mLongPressChecker;	// ++, --, 150406 cjg
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainALeftMainFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.left_main_a, null);
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
		
		LayoutNormalUpper = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_a_machinestatus_normal_upper);
		LayoutNormalLower = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_a_machinestatus_normal_lower);
		LayoutWeighingUpper = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_a_machinestatus_weighing_upper);
		LayoutWeighingLower = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_a_machinestatus_weighing_lower);
		
		textViewNormalUpperData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_data);
		textViewNormalUpperUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_unit);
		textViewNormalLowerData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_data);
		textViewNormalLowerUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_unit);
		textViewWeighingUpperData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_data);
		textViewWeighingUpperUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_unit);
		textViewWeighingLowerData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_data);
		textViewWeighingLowerUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_unit);
		textViewFuelTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_leftdown_fuel_title);
		textViewFuelData = (TextFitTextView)mRoot.findViewById(R.id.textView_leftdown_fuel_data);
		textViewFuelUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_leftdown_fuel_unit);		
		
		imgViewNormalUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_upper_icon);
		imgViewNormalLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_lower_icon);
		imgViewWeighingUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_upper_icon);
		imgViewWeighingLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_lower_icon);
		imgViewEcoIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_eco_title);
		imgViewEcoBG = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_eco_bg);
		imgViewEcoBar = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_eco_bar);
		
//		imgbtnMachineStatus = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_a_machinestatus);
		imgbtnMachineStatus1 = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_a_machinestatus1);
		imgbtnMachineStatus2 = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_a_machinestatus2);
		imgbtnFuel = (ImageButton)mRoot.findViewById(R.id.imageButton_leftdown_main_a_fuel);		

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		UpperStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		LowerStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		WeighingUpperStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		WeighingLowerStatusIconAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		
		FuelTitleAnimation = new TextFitTextViewAxisFlipAnimation(ParentActivity);

		popupIndicator = new QuickAction(ParentActivity, QuickAction.VERTICAL);
		actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning), 139), getResources().getDrawable(R.drawable.main_default_monitoring_icon_fullin));
		popupIndicator.addActionItem(actionitemIndicator);		
		
		EcoGaugeLevel = CAN1Comm.Get_EcoGaugeLevel_1304_PGN65390();
		EcoGaugeStatus = CAN1Comm.Get_EcoGaugeStatus_1305_PGN65390();
		
		if (EcoGaugeLevel > 100) {
			EcoGaugeLevel = 100;
		} else if (EcoGaugeLevel < 0) {
			EcoGaugeLevel = 0;
		}
		
		FrontAxleTempHighWarning = CAN1Comm.Get_Front_Axle_Oil_Temp_Warning_580_PGN65449();
		RearAxleTempHighWarning = CAN1Comm.Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449();
		
		CursurDisplayDetail(ParentActivity._MainABaseFragment.CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
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
//		imgViewWeighingUpperIcon.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			  	if(ClickFlag == true)
//			  		ClickWeighingError(v);
//			}
//		});
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
				ParentActivity._MainABaseFragment.CursurIndex = 1;
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
				ParentActivity._MainABaseFragment.CursurIndex = 2;
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

		// ++, 150406 cjg
//		imgbtnFuel.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(ClickFlag == true)
//					ClickFuel();
//			}
//		});
		imgbtnFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 3;
				if(mLongPressChecker.getLongPressed() == false){
					if(ClickFlag == true)
						ClickFuel();
				}
			}
		});
		imgbtnFuel.setOnTouchListener( new OnTouchListener() {
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
				if(mLongPressChecker.mTargetView == imgbtnFuel)
				{
					imgbtnFuel.setBackgroundResource(R.drawable.main_a_default_fuel_n);
					ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
					if(ParentActivity.FuelIndex == CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE)
						ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE_INFO_CLEAR);
					else if(ParentActivity.FuelIndex == CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED)
						ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED_CLEAR);
					ParentActivity.showFuelInitalPopup();
	//				if(ParentActivity.FuelIndex == CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE)
	//					CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE_INFO_CLEAR);
	//				else if(ParentActivity.FuelIndex == CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED)
	//					CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED_CLEAR);
	//				CAN1Comm.TxCANToMCU(31);
	//				CAN1Comm.Set_OperationHistory_1101_PGN61184_31(0xFF);
				}
				else if(mLongPressChecker.mTargetView == imgbtnMachineStatus1 || mLongPressChecker.mTargetView == imgbtnMachineStatus2)
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
		// --, 150406 cjg
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		HYD = CAN1Comm.Get_HydraulicOilTemperature_101_PGN65431();
		Battery = CAN1Comm.Get_BatteryVoltage_705_PGN65431();
		Coolant = CAN1Comm.Get_EngineCoolantTemperature_304_PGN65431();
		TMOil = CAN1Comm.Get_TransmissionOilTemperature_536_PGN65431();
		// ++, 150329 bwk
		FrontAxleTemp = CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449();
		RearAxleTemp = CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449();
		// --, 150329 bwk
		HYDHighWarning = CAN1Comm.Get_HydraulicOilTemperatureHigh_102_PGN65427();
		BatteryLowWarning = CAN1Comm.Get_BatteryVoltageLow_706_PGN65427();
		CoolantHighWarning = CAN1Comm.Get_EngineCoolantTemperatureHigh_305_PGN65427();
		TMOilHighWarning = CAN1Comm.Get_TransmissionOilTemperatureHigh_537_PGN65427();
		// ++, 150329 bwk
		FrontAxleTempHighWarning = CAN1Comm.Get_Front_Axle_Oil_Temp_Warning_580_PGN65449();
		RearAxleTempHighWarning = CAN1Comm.Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449();
		// --, 150329 bwk
		
		WeightInfoDataDay1 = CAN1Comm.Get_ADayBeforeWeight_1916_PGN65452();
		WeightInfoDataToday = CAN1Comm.Get_TodayWeight_1915_PGN65450();
		WeightInfoDataTotalA = CAN1Comm.Get_TotalWorkAWeight_1912_PGN65451();
		WeightInfoDataTotalB = CAN1Comm.Get_TotalWorkBWeight_1913_PGN65451();
		WeightInfoDataTotalC = CAN1Comm.Get_TotalWorkCWeight_1914_PGN65452();
		WeightInfoDataCurrent = CAN1Comm.Get_CurrentWeight_1911_PGN65450();

		WeighingDisplayMode = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
		WeightInfoCurrentWeighingResult = CAN1Comm.Get_CurrentWeighingResult_1919_PGN65450();

		WeighingSystemError_BoomLiftSpeed = CAN1Comm.Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450();
		WeighingSystemError_BucketFullIn = CAN1Comm.Get_WeighingSystemError_BucketFullIn_1943_PGN65450();
		WeighingSystemError_HydraulicOilTemperature = CAN1Comm.Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450();
		
		AverageFuelRate = CAN1Comm.Get_AverageFuelRate_333_PGN65390();
		LastestConsumed = CAN1Comm.Get_ADaysFuelUsed_1405_PGN65390();
		
		EcoGaugeLevel = CAN1Comm.Get_EcoGaugeLevel_1304_PGN65390();
		EcoGaugeStatus = CAN1Comm.Get_EcoGaugeStatus_1305_PGN65390();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		try {
			UpperLowerDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
			FuelTitleDisplay(ParentActivity.FuelIndex);
			FuelDataDisplay(ParentActivity.FuelIndex,AverageFuelRate,LastestConsumed);
			EcoGaugeDisplay(EcoGaugeLevel,EcoGaugeStatus);
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
				LayoutNormalUpper.setVisibility(View.GONE);
				LayoutWeighingUpper.setVisibility(View.GONE);
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
				LayoutNormalUpper.setVisibility(View.GONE);
				LayoutWeighingUpper.setVisibility(View.GONE);
			}
			break;
		// --, 150327 bwk
			
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			LayoutNormalUpper.setVisibility(View.GONE);	
			LayoutWeighingUpper.setVisibility(View.VISIBLE);
			WeighingUpperDisplay(WeighingSystemError_BoomLiftSpeed,WeighingSystemError_BucketFullIn,WeighingSystemError_HydraulicOilTemperature,
					WeighingDisplayMode,WeightInfoDataCurrent,WeightInfoDataDay1,WeightInfoDataToday,WeightInfoDataTotalA,WeightInfoDataTotalB,WeightInfoDataTotalC,
					ParentActivity.UnitWeight);
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
	// ++, 150329 bwk
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
	// --, 150329 bwk
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
		textUnit.setText(getString(ParentActivity.getResources().getString(string.V), 10));
	}
	// ++, 150329 bwk
	public void AxleDisplay(TextView textData, TextView textUnit, int Data, int Unit){
		textData.setText(ParentActivity.GetTempAxle(Data,Unit));
		if(Unit == Home.UNIT_TEMP_F){
			textUnit.setText(getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textUnit.setText(getString(ParentActivity.getResources().getString(string.C), 8));
		}
	}
	// --, 150329 bwk

	public void WeighingUpperDisplay(int BoomLiftSpeedError, int BucketFullInError, int HydOilTempError, int DisplayIndex, int Current, int Day1, int Today, int TotalA, int TotalB, int TotalC, int Unit){
		if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_LB){
			textViewWeighingUpperUnit.setText(getString(ParentActivity.getResources().getString(string.lb), 12));
			textViewWeighingLowerUnit.setText(getString(ParentActivity.getResources().getString(string.lb), 12));
		}else if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_US_TON){
			textViewWeighingUpperUnit.setText(getString(ParentActivity.getResources().getString(string.USTon), 467));
			textViewWeighingLowerUnit.setText(getString(ParentActivity.getResources().getString(string.USTon), 467));
		}else{
			textViewWeighingUpperUnit.setText(getString(ParentActivity.getResources().getString(string.ton), 11));
			textViewWeighingLowerUnit.setText(getString(ParentActivity.getResources().getString(string.ton), 11));
		}

		if(HydOilTempError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
		
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_hyd_blue);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.white));
		}
		else if(BoomLiftSpeedError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& BucketFullInError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_sudden_fullin);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		}else if(BoomLiftSpeedError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
				&& ParentActivity.WeighingErrorDetect == CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON){
			WeighingUpperStatusIconAnimation.FlipAnimation(imgViewWeighingUpperIcon,R.drawable.main_default_monitoring_icon_sudden);
			textViewWeighingUpperData.setText(ParentActivity.GetWeighit(WeightInfoDataCurrent, ParentActivity.UnitWeight));
			textViewWeighingUpperData.setTextColor(ParentActivity.getResources().getColor(color.red));
		}else if(BucketFullInError == CAN1CommManager.DATA_STATE_WEIGHTING_SYSGEM_ERROR
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
			textViewWeighingUpperUnit.setText(getString(ParentActivity.getResources().getString(string.lb), 12));
			textViewWeighingLowerUnit.setText(getString(ParentActivity.getResources().getString(string.lb), 12));
		}else if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_US_TON){
			textViewWeighingUpperUnit.setText(getString(ParentActivity.getResources().getString(string.USTon), 467));
			textViewWeighingLowerUnit.setText(getString(ParentActivity.getResources().getString(string.USTon), 467));
		}else{
			textViewWeighingUpperUnit.setText(getString(ParentActivity.getResources().getString(string.ton), 11));
			textViewWeighingLowerUnit.setText(getString(ParentActivity.getResources().getString(string.ton), 11));
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
				ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
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
				ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
				ParentActivity._WorkLoadWeighingInitPopup2.setMode(DisplayIndex);
				ParentActivity.showWorkLoadWeighingInit2();
				break;
			default:
				ClickMachineStatus();
				break;
			}			
		}
	}

	public void FuelTitleDisplay(int _Index){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
//			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,"");
//			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getString(getResources().getString(string.AVERAGE_FUEL_RATE), 91));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getString(getResources().getString(string.A_DAYS_FUEL_USED), 146));
			break;
		default:
			break;
		}
	}
	public void FuelDataDisplay(int _Index, int AverageFuel, int LatestConsumed){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
//			textViewFuelData.setText("");
//			textViewFuelUnit.setText("");
//			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			textViewFuelData.setText(ParentActivity.GetFuelRateString(AverageFuel, ParentActivity.UnitFuel));
			if(ParentActivity.UnitFuel == Home.UNIT_FUEL_GAL)
				textViewFuelUnit.setText(getString(ParentActivity.getResources().getString(string.gal_h), 465));
			else
				textViewFuelUnit.setText(getString(ParentActivity.getResources().getString(string.l_h), 33));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			textViewFuelData.setText(ParentActivity.GetFuelRateString(LatestConsumed, ParentActivity.UnitFuel));
			if(ParentActivity.UnitFuel == Home.UNIT_FUEL_GAL)
				textViewFuelUnit.setText(getString(ParentActivity.getResources().getString(R.string.gal), 466));
			else
				textViewFuelUnit.setText(getString(ParentActivity.getResources().getString(R.string.l), 81));
			break;
		default:
			break;
		}
		
	}
	public void EcoGaugeDisplay(int _level, int _status) {
		float Scale = 0;
		switch (_status) {
//			case CAN1CommManager.DATA_STATE_ECO_GREEN:
//			default:
//				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_all);
//				break;
//			//case CAN1CommManager.DATA_STATE_ECO_YELLOW:
//			//	imgViewEcoBar.setImageResource(R.drawable.main_center_eco_yellow_all);
//			//	break;
//			//case CAN1CommManager.DATA_STATE_ECO_RED:
//			//	imgViewEcoBar.setImageResource(R.drawable.main_center_eco_red_all);
//			//	break;
//			// ++, 150402 bwk
////			case CAN1CommManager.DATA_STATE_ECO_WHITE:
////				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_white_all);
////				break;
//			// --, 150402 bwk
			case CAN1CommManager.DATA_STATE_ECO_WHITE:
				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_all_white);
				break;
			default:
				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_all_green);
				break;
		}
		
		
		if(_level == 0xFF){
			_level = 0;
		}
		else if (_level > 100) {
			_level = 100;
		} else if (_level < 0) {
			_level = 0;
		}
		Scale = (float) ((float) _level / 100.0);

		imgViewEcoBar.setLayoutParams(new RelativeLayout.LayoutParams((int)(ParentActivity.getResources().getDrawable(R.drawable.main_center_eco_all).getIntrinsicWidth() * Scale), 
				ParentActivity.getResources().getDrawable(R.drawable.main_center_eco_all).getIntrinsicHeight()));
	}
	
	public void ClickMachineStatus(){
		ShowSelectAnimation(0);
	}

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
				// ++, 150330 bwk
//				ShowSelectAnimation(0, ParentActivity._MainABaseFragment._MainACenterMachineStatusFragment,
//						ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment);
				ClickMachineStatus();
				// --, 150330 bwk
			}
		}else{
			// ++, 150330 bwk
//			ShowSelectAnimation(0, ParentActivity._MainABaseFragment._MainACenterMachineStatusFragment,
//					ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment);
			ClickMachineStatus();
			// --, 150330 bwk
		}
	}
	public void ClickFuel(){
		ShowSelectAnimation(1);
	}

	public void ShowSelectAnimation(int index){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		if(index == 0){
			ParentActivity._MainABaseFragment._MainACenterMachineStatusFragment = new MainACenterMachineStatusFragment();
			if(CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449() != 0xFF)
				ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment3 = new MainALeftUpMachineStatusSelectFragment3();
			else if(CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449() != 0xFF)
				ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment2 = new MainALeftUpMachineStatusSelectFragment2();
			else
				ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment1 = new MainALeftUpMachineStatusSelectFragment1();
			
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftLeftAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterMachineStatusFragment);
			if(CAN1Comm.Get_Rear_Axle_Oil_Temperature_578_PGN65449() != 0xFF)
				ParentActivity._MainABaseFragment.LeftChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment3);
			else if(CAN1Comm.Get_Front_Axle_Oil_Temperature_577_PGN65449() != 0xFF)
				ParentActivity._MainABaseFragment.LeftChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment2);
			else
				ParentActivity._MainABaseFragment.LeftChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainALeftUpMachineStatusSelectFragment1);

		}else{
			ParentActivity._MainABaseFragment._MainACenterFuelFragment = new MainACenterFuelFragment();
			ParentActivity._MainABaseFragment._MainALeftDownFuelSelectFragment = new MainALeftDownFuelSelectFragment();
			
			ParentActivity._MainABaseFragment._MainBodyShiftAnimation.StartShiftLeftAnimation();
			ParentActivity._MainABaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainACenterFuelFragment);
			ParentActivity._MainABaseFragment.LeftChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainALeftDownFuelSelectFragment);
		}

		ParentActivity._MainABaseFragment._RightDisappearAnimation.StartAnimation();
		ParentActivity._MainABaseFragment._RightBGDisappearAnimation.StartAnimation();
		ParentActivity._MainABaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainABaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainABaseFragment._KeyBodyDisappearAnimation.StartAnimation();
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
//		imgbtnMachineStatus.setClickable(ClickFlag);
		imgbtnMachineStatus1.setClickable(ClickFlag);
		imgbtnMachineStatus2.setClickable(ClickFlag);
		imgbtnFuel.setClickable(ClickFlag);
	}

	public void showLampPopup(int Index){
		popupIndicator.removeAllActionItem();
		switch (Index) {
		case DATA_STATE_CURRENT_WEIHGING_RESULT_HYDRAULICOILTEMP:
			actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Hydraulic_Oil_Temp_Low_Warning), 141));
			popupIndicator.addActionItem(actionitemIndicator);
			break;
		case DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN:
			actionitemIndicator = new ActionItem(0, getString(ParentActivity.getResources().getString(R.string.Sudden_Change_Error_Warning), 140)+"\n"+getString(ParentActivity.getResources().getString(R.string.Bucket_Full_In_Error_Warning), 139));
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
	
	public void CursurDisplayDetail(int index){
		imgbtnMachineStatus1.setBackgroundResource(R.drawable._selector_leftup_btn1);
		imgbtnMachineStatus2.setBackgroundResource(R.drawable._selector_leftup_btn2);
		imgbtnFuel.setBackgroundResource(R.drawable._selector_leftdown_main_a_fuel_btn);
		switch(index){
			case 1:
				imgbtnMachineStatus1.setBackgroundResource(R.drawable.main_b_default_monitoring_01_s_02);
				break;
			case 2:
				imgbtnMachineStatus2.setBackgroundResource(R.drawable.main_b_default_monitoring_02_s_02);
				break;
			case 3:
				imgbtnFuel.setBackgroundResource(R.drawable.main_b_default_fuelinfo_s_02);
				break;
		}
	}
}