package taeha.wheelloader.fseries_monitor.menu.mode;

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
import taeha.wheelloader.fseries_monitor.menu.preference.DisplayTypeFragment.EnableButtonTimerClass;
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
import android.widget.ProgressBar;
import android.widget.TextView;

public class PressureCalibration extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int NORMAL	 				= 0;
	private static final int SUCCESS	 			= 2;
	private static final int SUCCESS_CAL2 			= 4;
	private static final int TIMER_OVERFLOW_ERROR	= 11;
	private static final int NOSENSOR_ERROR	 		= 12;
	private static final int BOOMUP_SPEED_ERROR		= 13;
	private static final int LOW_HYDRAULIC_OIL_ERROR = 14;	// ++, --, 150204 bwk
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnCancel;
	TextFitTextView textViewCancel;

	TextFitTextView textViewTitle;
	TextFitTextView textViewStart;
	TextView textViewText1;
	TextView textViewText2;
	TextView textViewText3;
	
	TextView textViewOrder4;
	TextView textViewText4;
	
	TextFitTextView textViewHYDTitle;
	TextFitTextView textViewHYDData;		// ++, --, 150204 bwk
	
	TextView textViewWarning;
	
	ImageView imgViewStep1;
	ImageView imgViewStep2;
	
	ProgressBar progressBoomPressure;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int HYD;		// ++, --, 150204 bwk
	
	// Timer	
	private Timer mCheckTimer = null;
	private Timer	mEnableButtonTimer = null;
	
	
	private int StatusCNT;
	
	int Order;	
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
		 TAG = "PressureCalibration";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_calibration_pressure, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Boom_Pressure_Calibration), 172);
		
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
			StartEnableButtonTimer();
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		CancelCheckTimer();
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestAEB_PGN61184_201(0);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(0);
		CAN1Comm.TxCANToMCU(201);
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		CAN1Comm.Set_RequestAEB_PGN61184_201(3);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
	}
	////////////////////////////////////////////////
	
	


	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_calibration_pressure_low_cancel);
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.First_Calibration), 353));
		
		textViewStart = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_start);
		textViewStart.setText(getString(ParentActivity.getResources().getString(R.string.Start), 352));
		
		textViewText1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_1);
		textViewText1.setText(getString(ParentActivity.getResources().getString(R.string.Roll_in_the_bucket_at_max_range_lower_the_boom_at_min_height), 355));
		
		textViewText2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_2);
		textViewText2.setText(getString(ParentActivity.getResources().getString(R.string.Press_Start_button_lift_the_boom_with_the_hydraulic_control_in_detent_position), 356));
		
		textViewText3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_3);
		textViewText3.setText(getString(ParentActivity.getResources().getString(R.string.Boom_lifting_will_be_stopped_automatically_at_a_certain_position_and_calibration_is_completed), 357));
		
		textViewOrder4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_order_4);
		textViewText4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_4);
		textViewText4.setText(getString(ParentActivity.getResources().getString(R.string.Engine_Mode_Set_P_Mode_For_Calibration), 473));
		
		textViewHYDTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_HYD_Temp);
		textViewHYDTitle.setText(getString(ParentActivity.getResources().getString(R.string.HYD_Temp), 111));
		
		textViewHYDData = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_HYD_Temp_value);	// ++, --, 150204 bwk
		
		textViewWarning = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_warning);
		textViewWarning.setText(getString(ParentActivity.getResources().getString(R.string.Pressure_Calibration_Warning), 384));
	
		imgViewStep1 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_pressure_step_1);
		imgViewStep2 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_pressure_step_2);
		
		progressBoomPressure = (ProgressBar)mRoot.findViewById(R.id.progressBar_menu_body_management_calibration_pressure);
		progressBoomPressure.setMax(115);	// 23Sec.
		progressBoomPressure.setProgress(0);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		StatusCNT = 0;
		Order = 0;
		CursurIndex = 1;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		textViewStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickStart();
			}
		});
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		HYD = CAN1Comm.Get_HydraulicOilTemperature_101_PGN65431();	// ++, --, 150204 bwk
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		HYDDisplay(textViewHYDData,HYD,ParentActivity.UnitTemp);		// ++, --, 150204 bwk
		BoomPressureProgressDisplay(StatusCNT);
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		// ++, 150210 bwk
		/*
		ParentActivity._MenuBaseFragment.showCalibrationAnimation();
		*/
		// ++, 150309 bwk
		//if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_TOP || ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP;
			ParentActivity.showMaintoKey(CAN1CommManager.WORK_LOAD);
		// --, 150309 bwk		
			//ParentActivity._MainBBaseFragment.showWorkLoadAnimation();
		}
		
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP)
		{
			ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP;
			ParentActivity._MenuBaseFragment.showBodyWorkLoad();
		}	
		else
		{
			// ++, 150409 cjg
//			ParentActivity._MenuBaseFragment.showCalibrationAnimation();
			ParentActivity._MenuBaseFragment.showBodyModeAnimation();
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP);
			// --, 150409 cjg
		}
	}
	public void ClickStart(){
		if(Order == 0){
			CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(1);
			CAN1Comm.TxCANToMCU(201);
			CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
			StatusCNT = 0;
			CancelCheckTimer();
			StartCheckTimer();
		}else if(Order == 1){
			CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(2);
			CAN1Comm.TxCANToMCU(201);
			CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
			progressBoomPressure.setMax(90);	// 18Sec.
			StatusCNT = 0;
			CancelCheckTimer();
			StartCheckTimer();
		}
		StartButtonOnOff(false);
	}
	/////////////////////////////////////////////////////////////////////
	public void Order2Display(){
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Second_Calibration), 354));
		textViewText2.setText(getString(
				ParentActivity.getResources().getString(string.Set_P_mode_step_on_pedal_max_range_press_Start_button_lift_the_boom_with_the_hydraulic_control_in_detent_position), 358));
		textViewOrder4.setVisibility(View.VISIBLE);
		textViewText4.setVisibility(View.VISIBLE);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		StatusCNT = 0;
	}
	public void BoomPressureProgressDisplay(int progress){
		progressBoomPressure.setProgress(progress);
	}
	public void StartButtonOnOff(boolean flag){
		if(flag == true){
			textViewStart.setClickable(true);
			textViewStart.setAlpha((float)1.0);
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
		}else{
			textViewStart.setClickable(false);
			textViewStart.setAlpha((float)0.5);
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	// ++, 150204 bwk
	public void HYDDisplay(TextView textData, int Data, int Unit){
		if(Unit == Home.UNIT_TEMP_F){
			textData.setText(": " + ParentActivity.GetTemp(Data,Unit) + " " + getString(ParentActivity.getResources().getString(string.F), 9));
		}else{
			textData.setText(": " + ParentActivity.GetTemp(Data,Unit) + " " + getString(ParentActivity.getResources().getString(string.C), 8));
		}
	}
	// --, 150204 bwk
	
	public void CheckBoomPressureProgressStatus(int StatusCnt){
		int Result;
		Result = CAN1Comm.Get_BoomPressureCalibrationStatus_1908_PGN61184_202();
		Log.d(TAG,"Result : " + Integer.toString(Result)+" StatusCnt:"+Integer.toString(StatusCnt));
		//if((StatusCnt >= 25) && (StatusCnt <= 90)) // 5 Sec ~ 15 Sec
		if((StatusCnt >= 25)) // 5 Sec ~ 
		{
			if( (Result == 2) ||  (Result == 4) || (Result == 11) || (Result == 12) || (Result == 13) || (Result == 14))	// ++, --, 150204 bwk : Result = 14 추가
			{
				CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(0);
				CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(0);
				CAN1Comm.Set_RequestAEB_PGN61184_201(0);
				CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(0);
				CAN1Comm.TxCANToMCU(201);
				CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
				CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
				CAN1Comm.Set_RequestAEB_PGN61184_201(3);
				CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
				if(Order == 0){
					StatusCNT = 115;
				}else if(Order == 1){
					StatusCNT = 90;
				}
				switch (Result) {
				case NORMAL:
					
					break;
				case SUCCESS:
					Order = 1;
					Order2Display();
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(
							getString(ParentActivity.getResources().getString(string.First_boom_pressure_calibration_completed), 362));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"SUCCESS");
					break;
				case SUCCESS_CAL2:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(
							getString(ParentActivity.getResources().getString(string.Calibration_completed), 379));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(true);
					ParentActivity.showPressureCalibrationResult();
					break;
				case TIMER_OVERFLOW_ERROR:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(
							getString(ParentActivity.getResources().getString(string.Time_Out), 382));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"TIMER_OVERFLOW_ERROR");
					break;
				case NOSENSOR_ERROR:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(
							getString(ParentActivity.getResources().getString(string.Sensor_Error), 381));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"NOSENSOR_ERROR");
					break;
				case BOOMUP_SPEED_ERROR:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(
							getString(ParentActivity.getResources().getString(string.Boom_Up_Speed_Error), 383));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"Boom_Up_Speed_Error");
					break;
				// ++, 150204 bwk
				case LOW_HYDRAULIC_OIL_ERROR:
					if(Order == 0){
						Order = 1;
						Order2Display();
					// ++, 160317 bwk
					//ParentActivity._PressureCalibrationResultPopup.setTextTitle(
					//		getString(ParentActivity.getResources().getString(string.Hydraulic_Oil_Temp_Error), 385));
					// ++, --, 160323 bwk 다국어 숫자 잘못되어 있는거 수정함!!!!!
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(
							getString(ParentActivity.getResources().getString(string.First_boom_pressure_calibration_completed), 362));
					// --, 160317 bwk
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					}else {
						ParentActivity._PressureCalibrationResultPopup.setTextTitle(
								getString(ParentActivity.getResources().getString(string.Calibration_completed), 379));
						ParentActivity._PressureCalibrationResultPopup.setExitFlag(true);
						ParentActivity.showPressureCalibrationResult();
					}
					Log.d(TAG,"Low_Hydraulic_Oil_Error");
					break;
				// --, 150204 bwk
				}
				StartButtonOnOff(true);
				CancelCheckTimer();
			}
		}
	}
	public class CheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					StatusCNT++;
					CheckBoomPressureProgressStatus(StatusCNT);
					UpdateUI();
				}
			});
			
		}
		
	}
	
	public void StartCheckTimer(){
		CancelCheckTimer();
		mCheckTimer = new Timer();
		mCheckTimer.schedule(new CheckTimerClass(),0,200);	
	}
	
	public void CancelCheckTimer(){
		if(mCheckTimer != null){
			mCheckTimer.cancel();
			mCheckTimer.purge();
			mCheckTimer = null;
		}
		
	}	
	/////////////////////////////////////////////////////////////////////
	public class EnableButtonTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ParentActivity.AnimationRunningFlag == false)
					{
						CancelEnableButtonTimer();
						
						ParentActivity.showCalibrationEHCUPopup();
					}
				}
			});

		}

	}

	public void StartEnableButtonTimer(){
		CancelEnableButtonTimer();
		mEnableButtonTimer = new Timer();
		mEnableButtonTimer.schedule(new EnableButtonTimerClass(),1,50);	
	}

	public void CancelEnableButtonTimer(){
		if(mEnableButtonTimer != null){
			mEnableButtonTimer.cancel();
			mEnableButtonTimer.purge();
			mEnableButtonTimer = null;
		}

	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			if(textViewStart.isClickable() == true){
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		default:
			break;
		}
		
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			if(textViewStart.isClickable() == true){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickStart();
			break;
		case 2:
			ClickCancel();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewStart.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			textViewStart.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
}
