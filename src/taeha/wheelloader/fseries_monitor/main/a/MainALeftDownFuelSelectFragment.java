package taeha.wheelloader.fseries_monitor.main.a;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainALeftDownFuelSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioCurrentFuel;
	RadioButton radioAverageFuel;
	RadioButton radioLastestConsumed;
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
		TAG = "MainALeftDownFuelSelectFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.left_main_a_fuel_select, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_LEFTDOWN_FUEL;
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
		FuelDisplay(ParentActivity.FuelIndex);
	}	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioCurrentFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_a_fuel_select_currentfuelrate);
		radioAverageFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_a_fuel_select_averagefuelrate);
		radioLastestConsumed = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_a_fuel_select_latestfuelconsumed);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioCurrentFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCurrentFuelRate();
			}
		});
		radioAverageFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAverageFuelRate();
			}
		});
		radioLastestConsumed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLastestFuelConsumed();
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
	public void FuelDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_CURRENT_FUEL_RATE:
			radioCurrentFuel.setChecked(true);
			radioAverageFuel.setChecked(false);
			radioLastestConsumed.setChecked(false);			
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			radioCurrentFuel.setChecked(false);
			radioAverageFuel.setChecked(true);
			radioLastestConsumed.setChecked(false);			
			break;
		case CAN1CommManager.DATA_STATE_LASTEST_FUEL_CONSUMED:
			radioCurrentFuel.setChecked(false);
			radioAverageFuel.setChecked(false);
			radioLastestConsumed.setChecked(true);			
			break;
		default:
			break;
		}
		
	}
	
	public void ClickCurrentFuelRate(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_CURRENT_FUEL_RATE;
		ParentActivity._MainABaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void ClickAverageFuelRate(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE;
		ParentActivity._MainABaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void ClickLastestFuelConsumed(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_LASTEST_FUEL_CONSUMED;
		ParentActivity._MainABaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("FuelIndex", ParentActivity.FuelIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
}