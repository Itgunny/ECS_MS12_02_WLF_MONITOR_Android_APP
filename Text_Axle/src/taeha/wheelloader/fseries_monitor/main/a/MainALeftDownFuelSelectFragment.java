package taeha.wheelloader.fseries_monitor.main.a;

import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainALeftDownFuelSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioAverageFuel;
	RadioButton radioADaysFuelUsed;
	
	RelativeLayout LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CursurIndex;
	Handler HandleCursurDisplay;
	
	Timer	mEnableButtonTimer = null;
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

		EnableRadioButton(false);
		StartEnableButtonTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_LEFTDOWN_FUEL;
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
		radioAverageFuel = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_a_fuel_select_averagefuelrate);
		radioADaysFuelUsed = (RadioButton)mRoot.findViewById(R.id.radioButton_leftdown_main_a_fuel_select_adaysfuelused);
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftdown_main_a_fuelselect);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();	
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
		radioADaysFuelUsed.setOnClickListener(new View.OnClickListener() {
			
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
//			radioAverageFuel.setChecked(false);
//			radioADaysFuelUsed.setChecked(false);	
//			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			radioAverageFuel.setChecked(true);
			radioADaysFuelUsed.setChecked(false);			
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			radioAverageFuel.setChecked(false);
			radioADaysFuelUsed.setChecked(true);			
			break;
		default:
			break;
		}
		CursurIndex = Data;
		CursurDisplay(CursurIndex);	
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
		ParentActivity.FuelIndex = CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED;
		ParentActivity._MainABaseFragment.showLeftDowntoDefaultScreenAnimation();
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("FuelIndex", ParentActivity.FuelIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
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
						EnableRadioButton(true);
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
	public void EnableRadioButton(boolean bEnable){
		float alpha;
		if(bEnable == true)
			alpha = (float)1;
		else
			alpha = (float)0;
		
		LayoutBG.setAlpha(alpha);

		radioAverageFuel.setClickable(bEnable);
		radioADaysFuelUsed.setClickable(bEnable);
	}

	public void CursurDisplay(int Index){
		radioAverageFuel.setPressed(false);
		radioADaysFuelUsed.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioAverageFuel.setPressed(true);
			break;
		case 2:
			radioADaysFuelUsed.setPressed(true);
			break;
		default:
			break;
		}
	}
}