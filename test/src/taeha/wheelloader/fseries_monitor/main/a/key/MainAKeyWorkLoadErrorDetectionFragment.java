package taeha.wheelloader.fseries_monitor.main.a.key;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.b.MainBKeyTitleFragment;

public class MainAKeyWorkLoadErrorDetectionFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		TAG = "MainAKeyWorkLoadErrorDetectionFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_workload_errordetect, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ERRORDETECT;
		ErrorDetectDisplay(ParentActivity.WeighingErrorDetect);
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		ParentActivity.SavePref();
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_errordetect_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_errordetect_on);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
			}
		});
		radioOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOn();
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
	public void ErrorDetectDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			radioOff.setChecked(true);
			radioOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			radioOff.setChecked(false);
			radioOn.setChecked(true);
			break;
		
		default:
			break;
		}
		
	}
	public void ClickOff(){
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF;
		showWorkLoadAnimation();
	}
	public void ClickOn(){
		ParentActivity.WeighingErrorDetect = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		showWorkLoadAnimation();
	}
	
	public void showWorkLoadAnimation(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment = new MainAKeyWorkLoadFragment();
		ParentActivity._MainABaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment);
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("WeighingErrorDetect", ParentActivity.WeighingErrorDetect);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	
	
}