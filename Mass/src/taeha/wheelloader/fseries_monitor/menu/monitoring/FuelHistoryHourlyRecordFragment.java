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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryModeRecordFragment.SendCommandTimerClass;

public class FuelHistoryHourlyRecordFragment  extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView[]	imgViewHour;
	TextView[]	textViewHour;
	
	ImageButton imgbtnInital;
	TextFitTextView	textViewInital;
	
	ImageButton	imgbtnOK;
	TextFitTextView	textViewOK;
	
	TextFitTextView	textViewGraphUnit;
	TextView	textViewUnit;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int[] HourFuelUsed;
	
	int CursurIndex;		
	Handler HandleCursurDisplay;
	
	private Timer mSendCommandTimer = null;
	public boolean bCursurIndex;
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
		 TAG = "FuelHistoryHourlyRecordFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_fuelhistory_hourlyrecord, null);
		InitValuables();
		InitResource();
		InitButtonListener();
		SetUnit();
		
		StartSendCommandTimer();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Hourly_Record), 315);
		
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
		imgViewHour[0] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value1);
		imgViewHour[1] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value2);
		imgViewHour[2] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value3);
		imgViewHour[3] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value4);
		imgViewHour[4] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value5);
		imgViewHour[5] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value6);
		imgViewHour[6] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value7);
		imgViewHour[7] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value8);
		imgViewHour[8] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value9);
		imgViewHour[9] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value10);
		imgViewHour[10] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value11);
		imgViewHour[11] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_value12);
		
		textViewHour[0] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_1);
		textViewHour[1] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_2);
		textViewHour[2] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_3);
		textViewHour[3] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_4);
		textViewHour[4] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_5);
		textViewHour[5] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_6);
		textViewHour[6] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_7);
		textViewHour[7] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_8);
		textViewHour[8] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_9);
		textViewHour[9] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_10);
		textViewHour[10] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_11);
		textViewHour[11] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_text_12);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_hourlyrecord_low_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		imgbtnInital = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_hourlyrecord_low_initialization);
		textViewInital = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_low_initialization);
		textViewInital.setText(getString(ParentActivity.getResources().getString(R.string.Initialization), 30));
		
		textViewGraphUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_unit);
		textViewGraphUnit.setText(getString(ParentActivity.getResources().getString(R.string.l_h), 33));
		
		
		textViewUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_hourlyrecord_graph_unit);
	}
	
	@Override
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		HourFuelUsed = new int[12];
		imgViewHour = new ImageView[12];
		textViewHour = new TextView[12];
		
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
		if(CAN1Comm.Get_OperationHistoryType_1101_PGN61184_33() == CAN1CommManager.DATA_STATE_HOURLY_FUEL_RATE_INFO)
		{
			HourFuelUsed[0] = CAN1Comm.Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[1] = CAN1Comm.Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[2] = CAN1Comm.Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[3] = CAN1Comm.Get_4th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[4] = CAN1Comm.Get_5th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[5] = CAN1Comm.Get_6th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[6] = CAN1Comm.Get_7th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			HourFuelUsed[7] = CAN1Comm.Get_8th_HourAverageFuelRate_333_PGN61184_33();
			HourFuelUsed[8] = CAN1Comm.Get_9th_HourAverageFuelRate_333_PGN61184_33();
			HourFuelUsed[9] = CAN1Comm.Get_10th_HourAverageFuelRate_333_PGN61184_33();
			HourFuelUsed[10] = CAN1Comm.Get_11th_HourAverageFuelRate_333_PGN61184_33();
			HourFuelUsed[11] = CAN1Comm.Get_12th_HourAverageFuelRate_333_PGN61184_33();
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		try {
			DisplayHourlyGraph(HourFuelUsed);
			DisplayHourlyText(HourFuelUsed);
			
			if(bCursurIndex == false && ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD)
				CursurDisplay(CursurIndex);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void SetUnit(){
		if(ParentActivity.UnitFuel == Home.UNIT_FUEL_GAL)
			textViewGraphUnit.setText(ParentActivity.getResources().getString(string.gal_h));
		else
			textViewGraphUnit.setText(ParentActivity.getResources().getString(string.l_h));
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
					if(ParentActivity.ScreenIndex == ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD)	
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
		CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_HOURLY_FUEL_RATE_INFO);
		CAN1Comm.TxCANToMCU(31);
	}
	
	public void DisplayHourlyGraph(int[] _FuelUsed){
		float Scale = 0;
		
		for(int i=0;i<12;i++)
		{
			if(_FuelUsed[i] == 0xFFFF || _FuelUsed[i] < 0)
				_FuelUsed[i] = 0;
			Scale = (float) ((float) _FuelUsed[i] / 1000.0);

			RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams)imgViewHour[i].getLayoutParams();

			if(_FuelUsed[i] > 1000 && _FuelUsed[i] != 0xFFFF)
				Params.height = (ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_power).getIntrinsicHeight());
			else
				Params.height = (int) ((ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_power).getIntrinsicHeight()) * Scale);
			
			imgViewHour[i].setLayoutParams(Params);
		}
	}
	
	public void DisplayHourlyText(int[] _FuelUsed){
		float Scale = 0;
		for(int i=0;i<12;i++)
		{
			if(_FuelUsed[i] == 0xFFFF || _FuelUsed[i] < 0)
				_FuelUsed[i] = 0;
			Scale = (float) ((float) _FuelUsed[i] / 1000.0);
			textViewHour[i].setText(ParentActivity.GetFuelRateString(_FuelUsed[i], ParentActivity.UnitFuel));
			RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams)textViewHour[i].getLayoutParams(); 
			if(_FuelUsed[i] >= 1000)
			{
				Params.topMargin = 13;
				textViewHour[i].setTextColor(ParentActivity.getResources().getColor(color.black));
			}
			else
			{
				Params.topMargin = (ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_empty).getIntrinsicHeight())
						- (int) ((ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_empty).getIntrinsicHeight()) * Scale) - 5;
				textViewHour[i].setTextColor(ParentActivity.getResources().getColor(color.white));
			}
			
			textViewHour[i].setLayoutParams(Params);
		}
	}	
	/////////////////////////////////////////////////////////////////////	
	public void ClickInitial(){
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD;
		ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_HOURLY_FUEL_RATE_INFO_CLEAR);
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
