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

public class MainBLeftDownFuelSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	// ++, 150317 bwk
	/*
	RadioButton radioLatestHour;
	RadioButton radioCurrentFuel;
	RadioButton radioTotalOdo;
	RadioButton radioLatestOdo;
	*/
	RadioButton radioCurrentFuel;
	RadioButton radioAverageFuel;
	RadioButton radioLastestConsumed;
	// --, 150317 bwk
	
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
		TAG = "MainBLeftDownFuelSelectFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftdown_main_b_fuel_select, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_LEFTDOWN_FUEL;
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
		// ++, 150317 bwk
		//HourOdometerDisplay(ParentActivity.HourOdometerIndex);
		FuelDisplay(ParentActivity.FuelIndex);
		// --, 150317 bwk
	}	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		// ++, 150317 bwk
		/*
		radioLatestHour = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_latesthourmeter);
		radioCurrentFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_currentfuelrate);
		radioTotalOdo = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_totalodometer);
		radioLatestOdo = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_hourodometer_select_latestodometer);
		*/
		radioCurrentFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_fuel_select_currentfuelrate);
		radioAverageFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_fuel_select_averagefuelrate);
		radioLastestConsumed = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_fuel_select_latestfuelconsumed);
		// --, 150317 bwk
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		// ++, 150317 bwk
		/*
		radioLatestHour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLatestHourmeter();
			}
		});
		radioCurrentFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCurrentFuelRate();
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
		*/
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
		// --, 150317 bwk
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
	// ++, 150317 bwk
	/*
	public void HourOdometerDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			radioLatestHour.setChecked(true);
			radioCurrentFuel.setChecked(false);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_FUELRATE_CURRENT:
			radioLatestHour.setChecked(false);
			radioCurrentFuel.setChecked(true);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			radioLatestHour.setChecked(false);
			radioCurrentFuel.setChecked(false);
			radioTotalOdo.setChecked(true);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			radioLatestHour.setChecked(false);
			radioCurrentFuel.setChecked(false);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(true);

			break;
		default:
			break;
		}
		
	}
	*/
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
		case CAN1CommManager.DATA_STATE_LATEST_FUEL_CONSUMED:
			radioCurrentFuel.setChecked(false);
			radioAverageFuel.setChecked(false);
			radioLastestConsumed.setChecked(true);			
			break;
		default:
			break;
		}
		
	}
	
	/*
	public void ClickLatestHourmeter(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_HOURMETER_LATEST;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	public void ClickCurrentFuelRate(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_FUELRATE_CURRENT;
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
	*/
	public void ClickCurrentFuelRate(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_CURRENT_FUEL_RATE;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void ClickAverageFuelRate(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void ClickLastestFuelConsumed(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_LATEST_FUEL_CONSUMED;
		ParentActivity._MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	/*
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("HourOdometerIndex", ParentActivity.HourOdometerIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	*/
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("FuelIndex", ParentActivity.FuelIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	// --, 150317 bwk
}