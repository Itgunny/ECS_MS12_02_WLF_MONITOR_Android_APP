package taeha.wheelloader.fseries_monitor.main.b;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	RadioButton radioAverageFuel;
	RadioButton radioLastestConsumed;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CursurIndex;
	Handler HandleCursurDisplay;
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
		radioAverageFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_fuel_select_averagefuelrate);
		radioLastestConsumed = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_b_fuel_select_latestfuelconsumed);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);				
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioAverageFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAverageFuelRate();
			}
		});
		radioLastestConsumed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
			radioAverageFuel.setChecked(false);
			radioLastestConsumed.setChecked(false);			
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			radioAverageFuel.setChecked(true);
			radioLastestConsumed.setChecked(false);			
			break;
		case CAN1CommManager.DATA_STATE_LATEST_FUEL_CONSUMED:
			radioAverageFuel.setChecked(false);
			radioLastestConsumed.setChecked(true);			
			break;
		default:
			break;
		}
		
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
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("FuelIndex", ParentActivity.FuelIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
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
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickAverageFuelRate();
			break;
		case 2:
			ClickLastestFuelConsumed();
			break;
		default:

			break;
		}
	}
	public void CursurDisplay(int Index){
		radioAverageFuel.setPressed(false);
		radioLastestConsumed.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioAverageFuel.setPressed(true);
			break;
		case 2:
			radioLastestConsumed.setPressed(true);
			break;
		default:
			break;
		}
	}
}