package taeha.wheelloader.fseries_monitor.popup;


import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;

public class SeatBeltAlarmOnOffPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewSeatBeltWarning;
	
	
	ImageButton imgbtnOn;
	ImageButton imgbtnOff;
	
	TextFitTextView textViewOn;
	TextFitTextView textViewOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SeatBeltAlarm;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public SeatBeltAlarmOnOffPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "SeatBeltAlarmOnOffPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_main_sbr_alarm, null);
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_SEATBELT_ALARM_POPUP; 
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}	
	@Override
	public void InitValuable(){
		super.InitValuable();
		SeatBeltAlarm = ParentActivity.SeatBeltAlarm;
		DisplaySBRAlarm(SeatBeltAlarm);
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		if(ParentActivity.SeatBeltPopupUsed == true) {
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP;	
		} else {
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_SEATBELT_POPUP;
		}
		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewSeatBeltWarning = (TextView)findViewById(R.id.textView_popup_main_sbr_alarm_title);
		textViewSeatBeltWarning.setText(getString(ParentActivity.getResources().getString(string.Seatbelt_Alarm_Warning), 512));
		
		imgbtnOn = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_main_sbr_alarm_on);
		imgbtnOff = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_main_sbr_alarm_off);
		
		textViewOn = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_main_sbr_alarm_on);
		textViewOn.setText(getString(ParentActivity.getResources().getString(R.string.On), 19));
		
		textViewOff = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_main_sbr_alarm_off);
		textViewOff.setText(getString(ParentActivity.getResources().getString(R.string.Off), 20));
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOn();
			}


		});
		
		imgbtnOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
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
	
	
	private void ClickOn() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyManagementAnimation();
		//ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
		
		
		ParentActivity.SeatBeltAlarm = Home.STATE_SBR_ALARM_ON;
		ParentActivity.SavePref();
		this.dismiss();
	}
	private void ClickOff() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyManagementAnimation();
		//ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
		
		ParentActivity.SeatBeltAlarm = Home.STATE_SBR_ALARM_OFF;
		ParentActivity.SavePref();
		this.dismiss();
	}
	public void ClickCancel(){
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch(CursurIndex) {
		case 1:
			ClickOn();
			break;
		case 2:
			ClickOff();
			break;
		default:
			break;
			
		}
	}
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
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOn.setPressed(true);
			imgbtnOff.setPressed(false);
			break;
		case 2:
			imgbtnOff.setPressed(true);
			imgbtnOn.setPressed(false);
			break;
		default:
			break;
		}
	}
	public void DisplaySBRAlarm(int data) {
		switch(data) {
		case Home.STATE_SBR_ALARM_ON:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case Home.STATE_SBR_ALARM_OFF:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////

}
