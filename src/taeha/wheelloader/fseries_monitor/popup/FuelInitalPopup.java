package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home.SeatBeltTimerClass;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup.PopupOffTimerClass;

public class FuelInitalPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView textViewTitle;
	TextFitTextView textViewOK;
	TextFitTextView textViewCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectMode;
	
	private Timer mPopupOffTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public FuelInitalPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "FuelInitalPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_fuelinfo_inital, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		setTitle(SelectMode);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_INITIAL;
	}
	
	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_TOP || ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP)
		{
			
		}
		else
		{
			try {
				// Crash 뜨는 현상으로 flag로 변경
				switch (SelectMode) {
				case CAN1CommManager.DATA_STATE_HOURLY_FUEL_RATE_INFO_CLEAR:
					ParentActivity._MenuBaseFragment._FuelHistoryHourlyRecordFragment.CursurDisplay(2);
					break;
				case CAN1CommManager.DATA_STATE_DAILY_FUEL_RATE_INFO_CLEAR:
					ParentActivity._MenuBaseFragment._FuelHistoryDailyRecordFragment.CursurDisplay(2);
					break;
				case CAN1CommManager.DATA_STATE_MODE_FUEL_RATE_INFO_CLEAR:
					ParentActivity._MenuBaseFragment._FuelHistoryModeRecordFragment.CursurDisplay(2);
					break;
				case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE_INFO_CLEAR:
					ParentActivity._MenuBaseFragment._FuelHistoryGeneralRecordFragment.CursurDisplay(1);
					break;
				case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED_CLEAR:
					ParentActivity._MenuBaseFragment._FuelHistoryGeneralRecordFragment.CursurDisplay(2);
					break;
				default:
					break;		
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"NullPointerException dismiss");
			}		
		}
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_fuelinfo_inital_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_fuelinfo_inital_cancel);
	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_fuelinfo_inital_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Initialize_), 51));

		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_fuelinfo_inital_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_fuelinfo_inital_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
		
		imgbtnOK.setVisibility(View.VISIBLE);
		imgbtnCancel.setVisibility(View.VISIBLE);
		textViewOK.setVisibility(View.VISIBLE);
		textViewCancel.setVisibility(View.VISIBLE);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
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
	///////////////////////////////////////////////////////////////////////////////
	public void ClickOK(){
		CAN1Comm.Set_OperationHistory_1101_PGN61184_31(SelectMode);
		CAN1Comm.TxCANToMCU(31);
		
		StartPopupOffTimer();
		imgbtnOK.setVisibility(View.INVISIBLE);
		imgbtnCancel.setVisibility(View.INVISIBLE);
		textViewOK.setVisibility(View.INVISIBLE);
		textViewCancel.setVisibility(View.INVISIBLE);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Waiting_for_initialization), 52));
	}	
	public void ClickCancel(){
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////

	public void setMode(int Index){
		SelectMode = Index;
		
	}
	public void setTitle(int Index){
		setMode(Index);
		
		switch (Index) {
		case CAN1CommManager.DATA_STATE_HOURLY_FUEL_RATE_INFO_CLEAR:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "\r\n(" +getString(ParentActivity.getResources().getString(string.Hourly_Record), 315) +")");
			break;
		case CAN1CommManager.DATA_STATE_DAILY_FUEL_RATE_INFO_CLEAR:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "\r\n(" +getString(ParentActivity.getResources().getString(string.Daily_Record), 316) +")");
			break;
		case CAN1CommManager.DATA_STATE_MODE_FUEL_RATE_INFO_CLEAR:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51)  
					+ "\r\n(" +getString(ParentActivity.getResources().getString(string.Mode_Record), 317) +")");
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE_INFO_CLEAR:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "\r\n(" +getString(ParentActivity.getResources().getString(string.Average_Fuel_Rate), 108) +")");
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED_CLEAR:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "\r\n(" +getString(ParentActivity.getResources().getString(string.A_Days_Fuel_Used), 147) +")");
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOK();
			break;
		case 2:
			ClickCancel();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			imgbtnOK.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	public class PopupOffTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			switch(ParentActivity.OldScreenIndex)
			{
				case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD:
					ParentActivity._MenuBaseFragment._FuelHistoryHourlyRecordFragment.ReqestFuelData();
					break;
				case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD:
					ParentActivity._MenuBaseFragment._FuelHistoryDailyRecordFragment.ReqestFuelData();
					break;
				case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD:
					ParentActivity._MenuBaseFragment._FuelHistoryModeRecordFragment.ReqestFuelData();
					break;
			}
			ClickCancel();
		}
				
	}
	
	public void StartPopupOffTimer(){
		CancelPopupOffTimer();
		mPopupOffTimer = new Timer();
		mPopupOffTimer.schedule(new PopupOffTimerClass(),1000);	
	}
	
	public void CancelPopupOffTimer(){
		if(mPopupOffTimer != null){
			mPopupOffTimer.cancel();
			mPopupOffTimer.purge();
			mPopupOffTimer = null;
		}
		
	}
}
