package taeha.wheelloader.fseries_monitor.menu.monitoring;

import java.util.Timer;
import java.util.TimerTask;

import android.R.color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceDetailFragment.SendCommandTimerClass;

public class FuelHistoryDailyRecordFragment  extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView[]	imgViewDay;
	TextView[]	textViewDay;
	
	TextView	textViewUnit;
	
	ImageButton imgbtnInital;
	ImageButton	imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int[] DayFuelUsed;
	
	int CursurIndex;		
	Handler HandleCursurDisplay;
	
	private Timer mSendCommandTimer = null;
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
		 TAG = "FuelHistoryDailyRecordFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_fuelhistory_dailyrecord, null);
		InitValuables();
		InitResource();
		InitButtonListener();
		StartSendCommandTimer();
		SetUnit();
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Daily_Record));
		
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
		imgViewDay[0] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value1);
		imgViewDay[1] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value2);
		imgViewDay[2] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value3);
		imgViewDay[3] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value4);
		imgViewDay[4] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value5);
		imgViewDay[5] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value6);
		imgViewDay[6] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_dailyrecord_graph_value7);
		
		textViewDay[0] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_1);
		textViewDay[1] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_2);
		textViewDay[2] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_3);
		textViewDay[3] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_4);
		textViewDay[4] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_5);
		textViewDay[5] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_6);
		textViewDay[6] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_text_7);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_dailyrecord_low_ok);
		imgbtnInital = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_dailyrecord_low_initialization);
		
		textViewUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_dailyrecord_graph_unit);
	}
	
	@Override
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		DayFuelUsed = new int[7];
		imgViewDay = new ImageView[7];
		textViewDay = new TextView[7];
		
		bCursurIndex = true;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnInital.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickInitial();
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
	
	public void onDestroyView()
	{
		super.onDestroyView();
		CancelSendCommandTimer();
		CAN1Comm.Set_OperationHistory_1101_PGN61184_31(0xFF);
		CAN1Comm.TxCANToMCU(31);
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		if(CAN1Comm.Get_OperationHistoryType_1101_PGN61184_33() == CAN1CommManager.DATA_STATE_DAILY_FUEL_RATE_INFO)
		{
			DayFuelUsed[0] = CAN1Comm.Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			DayFuelUsed[1] = CAN1Comm.Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			DayFuelUsed[2] = CAN1Comm.Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			DayFuelUsed[3] = CAN1Comm.Get_4th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			DayFuelUsed[4] = CAN1Comm.Get_5th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			DayFuelUsed[5] = CAN1Comm.Get_6th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			DayFuelUsed[6] = CAN1Comm.Get_7th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		try {
			DisplayDailyGraph(DayFuelUsed);
			DisplayDailyText(DayFuelUsed);
			
			if(bCursurIndex == false && ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD)
				CursurDisplay(CursurIndex);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}		
	}
	/////////////////////////////////////////////////////////////////////
	public void SetUnit(){
		if(ParentActivity.UnitFuel == Home.UNIT_FUEL_GAL)
			textViewUnit.setText(ParentActivity.getResources().getString(string.gal));
		else
			textViewUnit.setText(ParentActivity.getResources().getString(string.l));
			
	}
	/////////////////////////////////////////////////////////////////////	
	public class SendCommandTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ParentActivity.ScreenIndex == ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD)	
						ReqestFuelData();
				}
			});
		}
	}
	
	public void StartSendCommandTimer(){
		CancelSendCommandTimer();
		mSendCommandTimer = new Timer();
		mSendCommandTimer.schedule(new SendCommandTimerClass(),1000,3000);	
	}
	
	public void CancelSendCommandTimer(){
		if(mSendCommandTimer != null){
			mSendCommandTimer.cancel();
			mSendCommandTimer.purge();
			mSendCommandTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ReqestFuelData(){
		CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_DAILY_FUEL_RATE_INFO);
		CAN1Comm.TxCANToMCU(31);
	}
	
	public void DisplayDailyGraph(int[] _FuelUsed){
		float Scale = 0;
		
		for(int i=0;i<7;i++)
		{
			if(_FuelUsed[i] == 0xFFFF || _FuelUsed[i] < 0)
				_FuelUsed[i] = 0;
			Scale = (float) ((float) _FuelUsed[i] / 12000.0);

			if(_FuelUsed[i] >= 12000 && _FuelUsed[i] != 0xFFFF)
				imgViewDay[i].setLayoutParams(new RelativeLayout.LayoutParams(ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_power_02).getIntrinsicWidth(), 
						(ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_power_02).getIntrinsicHeight())));
			else
				imgViewDay[i].setLayoutParams(new RelativeLayout.LayoutParams(ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_power_02).getIntrinsicWidth(), 
						(int) (ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_power_02).getIntrinsicHeight() * Scale)));
		}
	}
	
	public void DisplayDailyText(int[] _FuelUsed){
		float Scale = 0;
		for(int i=0;i<7;i++)
		{
			if(_FuelUsed[i] == 0xFFFF || _FuelUsed[i] < 0)
				_FuelUsed[i] = 0;
			Scale = (float) ((float) _FuelUsed[i] / 12000.0);
			textViewDay[i].setText(ParentActivity.GetFuelRateString(_FuelUsed[i],ParentActivity.UnitFuel));
			RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams)textViewDay[i].getLayoutParams(); 
			if(_FuelUsed[i] >= 12000)
			{
				Params.topMargin = 13;
				textViewDay[i].setTextColor(ParentActivity.getResources().getColor(color.black));
			}
			else
			{
				Params.topMargin = (ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_empty_02).getIntrinsicHeight())
						- (int) ((ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_empty_02).getIntrinsicHeight()) * Scale) - 5;
				textViewDay[i].setTextColor(ParentActivity.getResources().getColor(color.white));
			}
			
			textViewDay[i].setLayoutParams(Params);			
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickInitial(){
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD;
		ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_DAILY_FUEL_RATE_INFO_CLEAR);
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
		default:
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
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 2:		
			ClickInitial();
			break;
		default:
			ClickOK();
			break;
		}
	}
	public void CursurDisplay(int Index){
		bCursurIndex = true;
		imgbtnInital.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (Index) {
		case 2:
			imgbtnInital.setPressed(true);
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
