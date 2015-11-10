package taeha.wheelloader.fseries_monitor.menu.monitoring;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.monitoring.FuelHistoryDailyRecordFragment.SendCommandTimerClass;
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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class FuelHistoryModeRecordFragment  extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView[]	imgViewMode;
	TextView[]	textViewMode;
	TextView	textViewUnit;
	
	ImageButton imgbtnInital;
	ImageButton	imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int[] ModeFuelUsed;
	
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
		 TAG = "FuelHistoryModeRecordFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_fuelhistory_moderecord, null);
		InitValuables();
		InitResource();
		InitButtonListener();
		SetUnit();
		
		StartSendCommandTimer();
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Mode_Record));
		
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
		super.InitValuables();
		
		imgViewMode[0] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_moderecord_graph_value1);
		imgViewMode[1] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_moderecord_graph_value2);
		imgViewMode[2] = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_monitoring_fuelhistory_moderecord_graph_value3);
		
		textViewMode[0] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_moderecord_graph_text_1);
		textViewMode[1] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_moderecord_graph_text_2);
		textViewMode[2] = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_moderecord_graph_text_3);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_moderecord_low_ok);
		imgbtnInital = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fuelhistory_moderecord_low_initialization);
		
		textViewUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fuelhistory_moderecord_graph_unit);
	}
	
	@Override
	protected void InitValuables() {
		// TODO Auto-generated method stub
		ModeFuelUsed = new int[3];
		imgViewMode = new ImageView[3];
		textViewMode = new TextView[3];
		
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
		if(CAN1Comm.Get_OperationHistoryType_1101_PGN61184_33() == CAN1CommManager.DATA_STATE_MODE_FUEL_RATE_INFO)
		{
			ModeFuelUsed[0] = CAN1Comm.Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			ModeFuelUsed[1] = CAN1Comm.Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
			ModeFuelUsed[2] = CAN1Comm.Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		try {
			DisplayModeGraph(ModeFuelUsed);
			DisplayModeText(ModeFuelUsed);
			
			if(bCursurIndex == false && ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD)
				CursurDisplay(CursurIndex);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
				
	}
	/////////////////////////////////////////////////////////////////////
	public void SetUnit(){
		/*if(ParentActivity.UnitFuel == Home.UNIT_FUEL_GAL){
			textViewUnit.setText(ParentActivity.getResources().getString(string.gal_h));
		}
		else*/{
			textViewUnit.setText(ParentActivity.getResources().getString(string.l_h));
		}
	
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
					if(ParentActivity.ScreenIndex == ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD)	
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
		CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_MODE_FUEL_RATE_INFO);
		CAN1Comm.TxCANToMCU(31);
	}
	
	public void DisplayModeGraph(int[] _FuelUsed){
		float Scale = 0;
		int graph_id = R.drawable.fuel_graph_power_02;
		
		for(int i=0;i<3;i++)
		{
			if(_FuelUsed[i] == 0xFFFF || _FuelUsed[i] < 0)
				_FuelUsed[i] = 0;
			Scale = (float) ((float) _FuelUsed[i] / 1000.0);
			
			switch(i)
			{
				case 0: graph_id = R.drawable.fuel_graph_power_02;		break;
				case 1: graph_id = R.drawable.fuel_graph_standard_02;	break;
				case 2: graph_id = R.drawable.fuel_graph_econo_02;		break;
			}

			if(_FuelUsed[i] > 1000 && _FuelUsed[i] != 0xFFFF)
				imgViewMode[i].setLayoutParams(new RelativeLayout.LayoutParams(ParentActivity.getResources().getDrawable(graph_id).getIntrinsicWidth(), 
						(ParentActivity.getResources().getDrawable(graph_id).getIntrinsicHeight())));
			else
				imgViewMode[i].setLayoutParams(new RelativeLayout.LayoutParams(ParentActivity.getResources().getDrawable(graph_id).getIntrinsicWidth(), 
						(int) (ParentActivity.getResources().getDrawable(graph_id).getIntrinsicHeight() * Scale)));
		}
		
	}
	
	public void DisplayModeText(int[] _FuelUsed){
		float Scale = 0;
		for(int i=0;i<3;i++)
		{
			if(_FuelUsed[i] == 0xFFFF || _FuelUsed[i] < 0)
				_FuelUsed[i] = 0;
			Scale = (float) ((float) _FuelUsed[i] / 1000.0);
			textViewMode[i].setText(ParentActivity.GetFuelRateString(_FuelUsed[i], ParentActivity.UnitFuel));
			RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams)textViewMode[i].getLayoutParams(); 
			if(_FuelUsed[i] >= 1000)
			{
				Params.topMargin = 13;
				textViewMode[i].setTextColor(ParentActivity.getResources().getColor(color.black));
			}
			else
			{
				Params.topMargin = (ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_empty_02).getIntrinsicHeight())
						- (int) ((ParentActivity.getResources().getDrawable(R.drawable.fuel_graph_empty_02).getIntrinsicHeight()) * Scale) - 5;
				textViewMode[i].setTextColor(ParentActivity.getResources().getColor(color.white));
			}
			
			textViewMode[i].setLayoutParams(Params);
			
		}
	}	
	/////////////////////////////////////////////////////////////////////	
	public void ClickInitial(){
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD;
		ParentActivity._FuelInitalPopup.setMode(CAN1CommManager.DATA_STATE_MODE_FUEL_RATE_INFO_CLEAR);
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
