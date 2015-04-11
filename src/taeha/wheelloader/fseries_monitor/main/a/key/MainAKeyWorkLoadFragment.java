package taeha.wheelloader.fseries_monitor.main.a.key;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainAKeyWorkLoadFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnAccumulation;
	ImageButton imgbtnDisplay;
	ImageButton imgbtnErrorDetect;
	
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;
	
	TextView textviewWeighingSystemModeData;
	TextView textViewWeighingDisplayData;
	TextView textViewErrorDetectData;
	
	TextView textViewPressureCalibration;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingSystemMode;
	int WeighingDisplayMode;
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
		 TAG = "MainAKeyWorkLoadFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_workload, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnAccumulation = (ImageButton)mRoot.findViewById(R.id.imageButton_key_main_a_workload_accumulation);
		imgbtnDisplay = (ImageButton)mRoot.findViewById(R.id.imageButton_key_main_a_workload_display);
		imgbtnErrorDetect = (ImageButton)mRoot.findViewById(R.id.imageButton_key_main_a_workload_errordetect);

		
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_low_default);
		
		textviewWeighingSystemModeData = (TextView)mRoot.findViewById(R.id.textView_key_main_a_workload_accumulation_data);
		textViewWeighingDisplayData = (TextView)mRoot.findViewById(R.id.textView_key_main_a_workload_display_data);
		textViewErrorDetectData = (TextView)mRoot.findViewById(R.id.textView_key_main_a_workload_errordetect_data);
		
		textViewPressureCalibration = (TextView)mRoot.findViewById(R.id.textView_key_main_a_workload_calibration);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnAccumulation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAccumulation();
			}
		});
		imgbtnDisplay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDisplay();
			}
		});
		imgbtnErrorDetect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickErrorDetect();
			}
		});
		
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
		textViewPressureCalibration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCalibration();
			}
		});
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		WeighingSystemMode = CAN1Comm.Get_WeighingSystemAccumulationMode_1941_PGN65450();
		WeighingDisplayMode = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		WeighingSystemModeDisplay(WeighingSystemMode);
		WeighingDisplayDisplay(WeighingDisplayMode);
		ErrorDetectionDisplay(ParentActivity.WeighingErrorDetect);
	}
	/////////////////////////////////////////////////////////////////////	
	public void WeighingSystemModeDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			textviewWeighingSystemModeData.setText(ParentActivity.getResources().getString(R.string.Manual));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			textviewWeighingSystemModeData.setText(ParentActivity.getResources().getString(R.string.Automatic));
			break;
		default:
			break;
		}
	}
	public void WeighingDisplayDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			textViewWeighingDisplayData.setText(ParentActivity.getResources().getString(R.string.Daily));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			textViewWeighingDisplayData.setText(ParentActivity.getResources().getString(R.string.Total_A));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			textViewWeighingDisplayData.setText(ParentActivity.getResources().getString(R.string.Total_B));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			textViewWeighingDisplayData.setText(ParentActivity.getResources().getString(R.string.Total_C));
			break;
			
		default:
			break;
		}
	}
	public void ErrorDetectionDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			textViewErrorDetectData.setText(ParentActivity.getResources().getString(R.string.Off));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			textViewErrorDetectData.setText(ParentActivity.getResources().getString(R.string.On));
			break;
		default:
			break;
		}
	}
	
	
	public void ClickAccumulation(){
		ParentActivity._MainABaseFragment.showWorkLoadAccumulationAnimation();
	}
	public void ClickDisplay(){
		ParentActivity._MainABaseFragment.showWorkLoadDisplayAnimation();
	}
	public void ClickErrorDetect(){
		ParentActivity._MainABaseFragment.showWorkLoadErrorDetectionAnimation();
	}
	public void ClickOK(){
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickCancel(){
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickDefault(){
		// ++, 150210 bwk
		/*
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
	
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayMode);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
	
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		SavePref();
		*/
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		ParentActivity.showWorkLoadInit();
		// --, 150210 bwk
	}
	public void ClickCalibration(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP);
	}
	
	// ++, 150210 bwk
	public void SetDefault(){
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
	
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayMode);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
	
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		SavePref();
	}
	// --, 150210 bwk
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("WeighingErrorDetect", ParentActivity.WeighingErrorDetect);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	
}