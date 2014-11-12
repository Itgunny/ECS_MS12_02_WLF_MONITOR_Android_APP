package taeha.wheelloader.fseries_monitor.menu.mode;

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
import android.content.SharedPreferences;
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
import android.widget.RadioButton;
import android.widget.TextView;

public class WorkLoadFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;
	
	TextView textViewCalibration;
	TextView textViewInitialization;
	
	RadioButton radioWeighingModeManual;
	RadioButton radioWeighingModeAuto;
	
	RadioButton radioWeighingDaily;
	RadioButton radioWeighingTotalA;
	RadioButton radioWeighingTotalB;
	RadioButton radioWeighingTotalC;
	
	RadioButton radioErrorDetectionOn;
	RadioButton radioErrorDetectionOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingSystemModeIndex;
	int WeighingDisplayIndex;
	int WeighingErrorDetect;
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
		 TAG = "WorkLoadFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_workload, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Work_Load));
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		SavePref();
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_workload_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_workload_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_workload_low_default);

		textViewCalibration = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_workload_calibration);
		textViewInitialization = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_workload_initialization);
		
		radioWeighingModeManual = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_accumulation_manual);
		radioWeighingModeAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_accumulation_auto);
		
		radioWeighingDaily = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_display_daily);
		radioWeighingTotalA = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_display_totala);
		radioWeighingTotalB = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_display_totalb);
		radioWeighingTotalC = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_display_totalc);
		
		radioErrorDetectionOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_errordetect_on);
		radioErrorDetectionOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_workload_errordetect_off);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		WeighingSystemModeIndex = CAN1Comm.Get_WeightAccumulationMode_PGN65450();
		WeighingDisplayIndex = ParentActivity.WeighingDisplayIndex;
		WeighingErrorDetect = ParentActivity.WeighingErrorDetect;
		
		WeighingSystemModeDisplay(WeighingSystemModeIndex);
		WeighingSystemDisplayDisplay(WeighingDisplayIndex);
		ErrorDetectionDisplay(WeighingErrorDetect);
		InitButtonDisplay(WeighingDisplayIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
			}
		});
		textViewCalibration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCalibration();
			}
		});
		textViewInitialization.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickInitialization();
			}
		});
		radioWeighingModeManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighingSystemModeManual();
			}
		});
		radioWeighingModeAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighingSystemModeAuto();
			}
		});
		radioWeighingDaily.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighingSystemDisplayDaily();
			}
		});
		radioWeighingTotalA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighingSystemDisplayTotalA();
			}
		});
		radioWeighingTotalB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighingSystemDisplayTotalB();
			}
		});
		radioWeighingTotalC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighingSystemDisplayTotalC();
			}
		});
		radioErrorDetectionOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickErrorDetectionOn();
			}
		});
		radioErrorDetectionOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickErrorDetectionOff();
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
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		

		ParentActivity.WeighingDisplayIndex = WeighingDisplayIndex;
		ParentActivity.WeighingErrorDetect = WeighingErrorDetect;
		CAN1Comm.Set_WeightAccumulationMode_PGN61184_62(WeighingSystemModeIndex);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayIndex);
		CAN1Comm.Set_SuddenChangeError_PGN61184_62(CAN1Comm.Get_SuddenChangeError_PGN65450());
		CAN1Comm.Set_BucketFullInError_PGN61184_62(CAN1Comm.Get_BucketFullInError_PGN65450());
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
		
		SavePref();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickDefault(){
		CAN1Comm.Set_WeightAccumulationMode_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
	
		ParentActivity.WeighingDisplayIndex = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(ParentActivity.WeighingDisplayIndex);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
	
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		SavePref();
		
		ClickWeighingSystemModeAuto();
		ClickWeighingSystemDisplayTotalA();
		ClickErrorDetectionOn();
	}
	public void ClickCalibration(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyPressureCalibration();	
	}
	public void ClickInitialization(){
		CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(ParentActivity.WeighingDisplayIndex);
		CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
		CAN1Comm.Set_SuddenChangeError_PGN61184_62(CAN1Comm.Get_SuddenChangeError_PGN65450());
		CAN1Comm.Set_BucketFullInError_PGN61184_62(CAN1Comm.Get_BucketFullInError_PGN65450());
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
	}
	
	public void ClickWeighingSystemModeManual(){
		radioWeighingModeManual.setChecked(true);
		radioWeighingModeAuto.setChecked(false);
		WeighingSystemModeIndex = CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL;
	}
	public void ClickWeighingSystemModeAuto(){
		radioWeighingModeManual.setChecked(false);
		radioWeighingModeAuto.setChecked(true);
		WeighingSystemModeIndex = CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO;
	}
	
	public void ClickWeighingSystemDisplayDaily(){
		radioWeighingDaily.setChecked(true);
		radioWeighingTotalA.setChecked(false);
		radioWeighingTotalB.setChecked(false);
		radioWeighingTotalC.setChecked(false);
		WeighingDisplayIndex = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY;
		InitButtonDisplay(WeighingDisplayIndex);
	}
	public void ClickWeighingSystemDisplayTotalA(){
		radioWeighingDaily.setChecked(false);
		radioWeighingTotalA.setChecked(true);
		radioWeighingTotalB.setChecked(false);
		radioWeighingTotalC.setChecked(false);
		WeighingDisplayIndex = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		InitButtonDisplay(WeighingDisplayIndex);
	}
	public void ClickWeighingSystemDisplayTotalB(){
		radioWeighingDaily.setChecked(false);
		radioWeighingTotalA.setChecked(false);
		radioWeighingTotalB.setChecked(true);
		radioWeighingTotalC.setChecked(false);
		WeighingDisplayIndex = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B;
		InitButtonDisplay(WeighingDisplayIndex);
	}
	public void ClickWeighingSystemDisplayTotalC(){
		radioWeighingDaily.setChecked(false);
		radioWeighingTotalA.setChecked(false);
		radioWeighingTotalB.setChecked(false);
		radioWeighingTotalC.setChecked(true);
		WeighingDisplayIndex = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C;
		InitButtonDisplay(WeighingDisplayIndex);
	}
	
	public void ClickErrorDetectionOn(){
		radioErrorDetectionOn.setChecked(true);
		radioErrorDetectionOff.setChecked(false);
		WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
	}
	public void ClickErrorDetectionOff(){
		radioErrorDetectionOn.setChecked(false);
		radioErrorDetectionOff.setChecked(true);
		WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF;
	}
	/////////////////////////////////////////////////////////////////////
	public void WeighingSystemModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			radioWeighingModeManual.setChecked(true);
			radioWeighingModeAuto.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			radioWeighingModeManual.setChecked(false);
			radioWeighingModeAuto.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void WeighingSystemDisplayDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			radioWeighingDaily.setChecked(true);
			radioWeighingTotalA.setChecked(false);
			radioWeighingTotalB.setChecked(false);
			radioWeighingTotalC.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			radioWeighingDaily.setChecked(false);
			radioWeighingTotalA.setChecked(true);
			radioWeighingTotalB.setChecked(false);
			radioWeighingTotalC.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			radioWeighingDaily.setChecked(false);
			radioWeighingTotalA.setChecked(false);
			radioWeighingTotalB.setChecked(true);
			radioWeighingTotalC.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			radioWeighingDaily.setChecked(false);
			radioWeighingTotalA.setChecked(false);
			radioWeighingTotalB.setChecked(false);
			radioWeighingTotalC.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ErrorDetectionDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			radioErrorDetectionOn.setChecked(false);
			radioErrorDetectionOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			radioErrorDetectionOn.setChecked(true);
			radioErrorDetectionOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	public void InitButtonDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			textViewInitialization.setText(ParentActivity.getResources().getString(string.Daily) + " "
					 + ParentActivity.getResources().getString(string.Initialization));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			textViewInitialization.setText(ParentActivity.getResources().getString(string.Total_A) + " "
					 + ParentActivity.getResources().getString(string.Initialization));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			textViewInitialization.setText(ParentActivity.getResources().getString(string.Total_B) + " "
					 + ParentActivity.getResources().getString(string.Initialization));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			textViewInitialization.setText(ParentActivity.getResources().getString(string.Total_C) + " "
					 + ParentActivity.getResources().getString(string.Initialization));
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("WeighingDisplayIndex", ParentActivity.WeighingDisplayIndex);
		edit.putInt("WeighingErrorDetect", ParentActivity.WeighingErrorDetect);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	
}
