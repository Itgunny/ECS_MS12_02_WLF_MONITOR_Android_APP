package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
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
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnCancel;

	TextView textViewTitle;
	TextView textViewStart;
	TextView textViewText1;
	TextView textViewText2;
	TextView textViewText3;
	
	ImageView imgViewStep1;
	ImageView imgViewStep2;
	
	ProgressBar progressBoomPressure;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	// Timer	
	private Timer mCheckTimer = null;
	
	private int StatusCNT;
	
	int Order;
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Boom_Pressure_Calibration));
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
		CAN1Comm.TxCANToMCU(201);
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		CAN1Comm.Set_RequestAEB_PGN61184_201(3);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
	}
	////////////////////////////////////////////////
	
	


	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_calibration_pressure_low_cancel);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_title);
		textViewStart = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_start);
		
		textViewText1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_1);
		textViewText2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_2);
		textViewText3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_pressure_main_step_3);
	
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
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BoomPressureProgressDisplay(StatusCNT);
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showCalibrationAnimation();
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
		textViewTitle.setText(ParentActivity.getResources().getString(string.Second_Calibration));
		textViewText2.setText(ParentActivity.getResources().getString(string.Set_P_mode_step_on_pedal_max_range_press_Start_button_lift_the_boom_with_the_hydraulic_control_in_detent_position));
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
	}
	public void BoomPressureProgressDisplay(int progress){
		progressBoomPressure.setProgress(progress);
	}
	public void StartButtonOnOff(boolean flag){
		if(flag == true){
			textViewStart.setClickable(true);
			textViewStart.setAlpha((float)1.0);
		}else{
			textViewStart.setClickable(false);
			textViewStart.setAlpha((float)0.5);
		}
		
	}
	/////////////////////////////////////////////////////////////////////

	public void CheckBoomPressureProgressStatus(int StatusCnt){
		int Result;
		Result = CAN1Comm.Get_BoomPressureCalibrationStatus_1908_PGN61184_202();
		Log.d(TAG,"Result : " + Integer.toString(Result));
		//if((StatusCnt >= 25) && (StatusCnt <= 90)) // 5 Sec ~ 15 Sec
		if((StatusCnt >= 25)) // 5 Sec ~ 
		{
			if( (Result == 2) ||  (Result == 4) || (Result == 11) || (Result == 12) )
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
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(ParentActivity.getResources().getString(string.First_boom_pressure_calibration_completed));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"SUCCESS");
					break;
				case SUCCESS_CAL2:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(ParentActivity.getResources().getString(string.Calibration_completed));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(true);
					ParentActivity.showPressureCalibrationResult();
					break;
				case TIMER_OVERFLOW_ERROR:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(ParentActivity.getResources().getString(string.Time_Out));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"TIMER_OVERFLOW_ERROR");
					break;
				case NOSENSOR_ERROR:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(ParentActivity.getResources().getString(string.Sensor_Error));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"NOSENSOR_ERROR");
					break;
				case BOOMUP_SPEED_ERROR:
					ParentActivity._PressureCalibrationResultPopup.setTextTitle(ParentActivity.getResources().getString(string.Boom_Up_Speed_Error));
					ParentActivity._PressureCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showPressureCalibrationResult();
					Log.d(TAG,"Boom_Up_Speed_Error");
					break;
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
}
