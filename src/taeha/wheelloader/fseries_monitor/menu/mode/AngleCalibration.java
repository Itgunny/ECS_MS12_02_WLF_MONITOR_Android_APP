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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.menu.mode.PressureCalibration.EnableButtonTimerClass;
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
import android.widget.TextView;

public class AngleCalibration extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int STATE_FAIL 	= 0;
	private static final int STATE_SUCCESS 	= 1;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnCancel;
	
	TextFitTextView textViewTitle;
	TextFitTextView textViewAngle;
	TextFitTextView textViewAngleTitle;
	TextView textViewAngleColon;
	TextFitTextView textViewNext;
	
	TextFitTextView textViewCancel;
	
	ImageView imgViewStep1;
	ImageView imgViewStep2;
	ImageView imgViewStep3;
	ImageView imgViewStep4;
	ImageView imgViewStep5;
	ImageView imgViewIcon;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int AngleError_Flag;
	int AngleError_Flag1;
	int m_BoomAngleCaliStep;
	int BoomPos_volt;
	boolean PopupFlag;
	
	int CursurIndex;
	Handler HandleCursurDisplay;
	
	private Timer	mEnableButtonTimer = null;	
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
		 TAG = "AngleCalibration";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_calibration_angle, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Boom_Bucket_Angle_Calibration), 342);
		
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
		
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_calibration_angle_low_cancel);
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_angle_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Cancel), 16));
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_angle_main_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Lower_the_boom_at_min_height), 345));
		textViewAngle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_angle_main_boom_data);
		textViewAngleTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_angle_main_boom_title);
		textViewAngleTitle.setText(getString(ParentActivity.getResources().getString(string.Boom), 28));
		textViewAngleColon = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_angle_main_boom_colon);
		textViewNext = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_angle_next);
		textViewNext.setText(getString(ParentActivity.getResources().getString(string.Next), 350));
		
	
		imgViewStep1 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_angle_step_1);
		imgViewStep2 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_angle_step_2);
		imgViewStep3 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_angle_step_3);
		imgViewStep4 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_angle_step_4);
		imgViewStep5 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_angle_step_5);
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_angle_main_icon);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		AngleError_Flag = 0;
		AngleError_Flag1 = 0;
		m_BoomAngleCaliStep = 1;
		BoomPos_volt = 0;
		PopupFlag = false;
		CursurIndex = 1;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		textViewNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNext();
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
		BoomBucketAngleCurrAngle();
		
		if(PopupFlag == false)
			ReplyBoomAngleCali();
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		// ++, 150409 cjg
//		ParentActivity._MenuBaseFragment.showCalibrationAnimation();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP);
		// --, 150409 cjg
		
	}
	public void ClickNext(){
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(m_BoomAngleCaliStep);
		CAN1Comm.TxCANToMCU(201);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		if(m_BoomAngleCaliStep == 1)
		{
			BoomPos_volt = CAN1Comm.Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395();
			Log.d(TAG,"BoomPos_volt : " + Integer.toString(BoomPos_volt));
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void SetStep1Display(){
		imgViewIcon.setImageResource(R.drawable.menu_management_boom_angle_img_01);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep3.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep4.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep5.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Lower_the_boom_at_min_height), 345));
		
		textViewAngle.setVisibility(View.VISIBLE);
		textViewAngleTitle.setVisibility(View.VISIBLE);
		textViewAngleColon.setVisibility(View.VISIBLE);
	}
	public void SetStep2Display(){
		imgViewIcon.setImageResource(R.drawable.menu_management_boom_angle_img_02);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		imgViewStep3.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep4.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep5.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Lift_up_the_boom_at_max_height), 346));
		
		textViewAngle.setVisibility(View.VISIBLE);
		textViewAngleTitle.setVisibility(View.VISIBLE);
		textViewAngleColon.setVisibility(View.VISIBLE);
	}
	public void SetStep3Display(){
		imgViewIcon.setImageResource(R.drawable.menu_management_boom_angle_img_03);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep3.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		imgViewStep4.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep5.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Lift_the_boom_to__5_0), 347));
		
		textViewAngle.setVisibility(View.VISIBLE);
		textViewAngleTitle.setVisibility(View.VISIBLE);
		textViewAngleColon.setVisibility(View.VISIBLE);
	}
	public void SetStep4Display(){
		imgViewIcon.setImageResource(R.drawable.menu_management_boom_angle_img_04);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep3.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep4.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		imgViewStep5.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Max_retraction_of_bucket_cylinder), 348));
		
		textViewAngle.setVisibility(View.VISIBLE);
		textViewAngleTitle.setVisibility(View.VISIBLE);
		textViewAngleColon.setVisibility(View.VISIBLE);
	}
	public void SetStep5Display(){
		imgViewIcon.setImageResource(R.drawable.menu_management_boom_angle_img_05);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep3.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep4.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep5.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Max_extension_of_bucket_cylinder), 349));
		
		textViewAngle.setVisibility(View.VISIBLE);
		textViewAngleTitle.setVisibility(View.VISIBLE);
		textViewAngleColon.setVisibility(View.VISIBLE);
	}
//	public void AngleDisplay(int Angle, int AngleDot){
//		textViewAngle.setText(Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "��");
//	}
	public void AngleDisplay(int Sign, int Angle, int AngleDot){
		if(Sign == 1)
			textViewAngle.setText("-" + Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "º");
		else
			textViewAngle.setText(Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "º");
	}
	public void CalStepDisplay(final int Step){
			
			ParentActivity.runOnUiThread(new Runnable() {
	
				@Override
				public void run() {
					// TODO Auto-generated method stub
					switch (Step) {
					case 1:
						SetStep1Display();
						break;
					case 2:
						SetStep2Display();
						break;
					case 3:
						SetStep3Display();
						break;
					case 4:
						SetStep4Display();
						break;
					case 5:
						SetStep5Display();
						break;
					}
				}
	
			});
			
		}
	/////////////////////////////////////////////////////////////////////


	public void BoomBucketAngleCurrAngle(){

		int	 BoomAngle = CAN1Comm.Get_BoomLinkAngle_1920_PGN65395();
		int	 BoomAngleDot;
		int	 BoomAngleVolt = CAN1Comm.Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395();
		int  BoomVolt_to_Angle;
		int	 BoomSign=0;

		if( BoomAngle == 0xffff )
			BoomAngle = 1800;
		if(AngleError_Flag1 == 1)
		{
			if(m_BoomAngleCaliStep<3)
			{
				BoomVolt_to_Angle = (int)(38.298 * (BoomAngleVolt-BoomPos_volt) ) + 1350;
				BoomAngle = BoomVolt_to_Angle;
			}
			else
			{
				AngleError_Flag1=0;
			}
			
			if( BoomAngle == 1800 )		// 0
			{
				BoomAngle = 0;
				BoomAngleDot = 0;
			}
			else if( BoomAngle > 1800 )	// +
			{
				BoomAngle -= 1800;
				BoomAngleDot = BoomAngle % 10;
				BoomAngle /= 10;	
			}
			else						// -
			{
				BoomAngle = 1800 - BoomAngle;
				BoomAngleDot = BoomAngle % 10;
				BoomAngle /= 10;
				//BoomAngle *= -1;
				BoomSign = 1;
			}
		}
		else
		{
			if( BoomAngle == 1800 )		// 0
			{
				BoomAngle = 0;
				BoomAngleDot = 0;

				AngleError_Flag = 0;
			}
			else if( BoomAngle > 1800 )	// +
			{
				BoomAngle -= 1800;

				if (BoomAngle > 900)
				{
					AngleError_Flag = 1;
					AngleError_Flag1 =1;
					return;		
				}
				else
				{
					AngleError_Flag = 0;
					BoomAngleDot = BoomAngle % 10;
					BoomAngle /= 10;
				}	
			}
			else						// -
			{
				BoomAngle = 1800 - BoomAngle;

				if (BoomAngle > 900)
				{
					AngleError_Flag = 1;
					AngleError_Flag1=1;
					return;
				}
				else
				{
					AngleError_Flag = 0;
					BoomAngleDot = BoomAngle % 10;
					BoomAngle /= 10;
					//BoomAngle *= -1;
					BoomSign = 1;
				}	
			}
		}
		
		AngleDisplay(BoomSign,BoomAngle,BoomAngleDot);

	}

	public void ReplyBoomAngleCali(){
		ParentActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int Reply = CAN1Comm.Get_AngleSensorCalibrationStatus_1909_PGN61184_202();
				
				//Log.d(TAG,"Reply : " + Integer.toString(Reply));
				if(AngleError_Flag == 1)		// Error Popup
				{
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
					ParentActivity._AngleCalibrationResultPopup.setTextTitle(getString(ParentActivity.getResources().getString(string.Calibration_failed_Machine_Restart), 378));
					ParentActivity._AngleCalibrationResultPopup.setExitFlag(false);
					ParentActivity.showAngleCalibrationResult();
					PopupFlag = true;
				}
				else
				{
					if(Reply == m_BoomAngleCaliStep)
					{
						m_BoomAngleCaliStep++;
						CalStepDisplay(m_BoomAngleCaliStep);
					}
					
					if((Reply == 5) || (Reply == 11) || (Reply == 12))	// Error Popup
					{
						Log.d(TAG,"ReplyBoomAngleCali Reply : " + Integer.toString(Reply));
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
						switch (Reply) {
						case 5:
							ParentActivity._AngleCalibrationResultPopup.setTextTitle(getString(ParentActivity.getResources().getString(string.Calibration_completed), 379));
							ParentActivity._AngleCalibrationResultPopup.setExitFlag(true);
							ParentActivity.showAngleCalibrationResult();
							PopupFlag = true;
							break;
						case 11:
							ParentActivity._AngleCalibrationResultPopup.setTextTitle(getString(ParentActivity.getResources().getString(string.Calibration_failed), 380));
							ParentActivity._AngleCalibrationResultPopup.setExitFlag(false);
							ParentActivity.showAngleCalibrationResult();
							PopupFlag = true;
							break;
						case 12:
							ParentActivity._AngleCalibrationResultPopup.setTextTitle(getString(ParentActivity.getResources().getString(string.Sensor_Error), 381));
							ParentActivity._AngleCalibrationResultPopup.setExitFlag(false);
							ParentActivity.showAngleCalibrationResult();
							PopupFlag = true;
							break;

						default:
							break;
						}
					}
				}
			}

		});
		
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
			CursurIndex--;
			CursurDisplay(CursurIndex);
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
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
			ClickNext();
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
			textViewNext.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			textViewNext.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
