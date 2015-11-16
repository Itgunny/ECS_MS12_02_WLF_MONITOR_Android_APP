package taeha.wheelloader.fseries_monitor.menu.monitoring;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryDailyRecordFragment.SendCommandTimerClass;

public class FuelHistoryGeneralRecordFragment  extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView	textViewAvgData;
	TextView	textViewDaysData;
	TextView	textViewAvgInital;
	TextView	textViewDaysInital;
	
	TextView	textViewAvgUnit;
	TextView	textViewDayUnit;
	
	ImageButton	imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int AverageFuelRate;
	int LatestFuelConsumed;
	int CursurIndex;		
	Handler HandleCursurDisplay;	
	public boolean bCursurIndex = true;
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
		 TAG = "FuelHistoryGeneralRecordFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_fuelhistory_generalrecord, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		SetUnit();
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.General_Record));
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);		
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewAvgData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_generalrecord_average_data);
		textViewDaysData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_generalrecord_a_days_fuel_used_data);

		textViewAvgInital = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_generalrecord_average_init);
		textViewDaysInital = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_generalrecord_a_days_fuel_used_init);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_generalrecord_low_ok);
		
		textViewAvgUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_generalrecord_average_unit);
		textViewDayUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_generalrecord_a_days_fuel_used_unit);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewAvgInital.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAverageInitial();
			}
		});
		textViewDaysInital.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDaysInitial();
			}
		});		
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AverageFuelRate = CAN1Comm.Get_AverageFuelRate_333_PGN65390();
		LatestFuelConsumed = CAN1Comm.Get_ADaysFuelUsed_1405_PGN65390();	
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		DisplayFuelData(AverageFuelRate, LatestFuelConsumed);
		
		if(bCursurIndex == false && ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD)
			CursurDisplay(CursurIndex);
	}
	/////////////////////////////////////////////////////////////////////
	public void SetUnit(){
		if(ParentActivity.UnitFuel == Home.UNIT_FUEL_GAL){
			textViewAvgUnit.setText(ParentActivity.getResources().getString(string.gal_h));
			textViewDayUnit.setText(ParentActivity.getResources().getString(string.gal));
		}
		else{
			textViewAvgUnit.setText(ParentActivity.getResources().getString(string.l_h));
			textViewDayUnit.setText(ParentActivity.getResources().getString(string.l));
		}
	
	}
	/////////////////////////////////////////////////////////////////////	
	public void DisplayFuelData(int _AverageFuelRate, int _LatestFuelConsumed){
		textViewAvgData.setText(ParentActivity.GetFuelRateString(_AverageFuelRate,ParentActivity.UnitFuel));
		textViewDaysData.setText(ParentActivity.GetFuelRateString(_LatestFuelConsumed,ParentActivity.UnitFuel));
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickAverageInitial(){
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD;
		ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE_INFO_CLEAR);
		ParentActivity.showFuelInitalPopup();
	}
	public void ClickDaysInitial(){
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD;
		ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED_CLEAR);
		ParentActivity.showFuelInitalPopup();
	}
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showFuelHistoryMenuListAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
			case 1:
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
				break;
			case 2:
			case 3:
				CursurIndex--;
				CursurDisplay(CursurIndex);
				break;
		}		
	}
	public void ClickRight(){	
		switch (CursurIndex) {
			case 1:
			case 2:
				CursurIndex++;
				CursurDisplay(CursurIndex);
				break;
			case 3:
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
				break;
		}		
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickAverageInitial();
			break;
		case 2:
			ClickDaysInitial();
			break;
		case 3:
			ClickOK();
			break;
	}		
		
	}
	public void CursurDisplay(int Index){
		bCursurIndex = true;
		textViewAvgInital.setPressed(false);
		textViewDaysInital.setPressed(false);
		imgbtnOK.setPressed(false);
		
		switch (Index) {
		case 1:
			textViewAvgInital.setPressed(true);
			break;
		case 2:
			textViewDaysInital.setPressed(true);
			break;
		case 3:
			imgbtnOK.setPressed(true);
			break;
		
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////

}
