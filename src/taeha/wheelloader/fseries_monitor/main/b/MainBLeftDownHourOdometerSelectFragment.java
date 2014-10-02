package taeha.wheelloader.fseries_monitor.main.b;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBLeftDownHourOdometerSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioTotalHour;
	RadioButton radioLatestHour;
	RadioButton radioTotalOdo;
	RadioButton radioLatestOdo;
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
		TAG = "MainBLeftDownHourOdometerSelectFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftdown_main_b_hourodometer_select, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_LEFTDOWN_HOURODOMETER;
		return mRoot;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		SavePref();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		HourOdometerDisplay(ParentActivity.HourOdometerIndex);
	}	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioTotalHour = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_totalhourmeter);
		radioLatestHour = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_latesthourmeter);
		radioTotalOdo = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_totalodometer);
		radioLatestOdo = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_latestodometer);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioTotalHour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalHourmeter();
			}
		});
		radioLatestHour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLatestHourmeter();
			}
		});
		radioTotalOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalOdometer();
			}
		});
		radioLatestOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLatestOdometer();
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
	public void HourOdometerDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_HOURMETER_TOTAL:
			radioTotalHour.setChecked(true);
			radioLatestHour.setChecked(false);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			radioTotalHour.setChecked(false);
			radioLatestHour.setChecked(true);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			radioTotalHour.setChecked(false);
			radioLatestHour.setChecked(false);
			radioTotalOdo.setChecked(true);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			radioTotalHour.setChecked(false);
			radioLatestHour.setChecked(false);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(true);

			break;
		default:
			break;
		}
		
	}
	public void ClickTotalHourmeter(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_HOURMETER_TOTAL;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	public void ClickLatestHourmeter(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_HOURMETER_LATEST;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	public void ClickTotalOdometer(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_ODOMETER_TOTAL;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	public void ClickLatestOdometer(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_ODOMETER_LATEST;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("HourOdometerIndex", ParentActivity.HourOdometerIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
}