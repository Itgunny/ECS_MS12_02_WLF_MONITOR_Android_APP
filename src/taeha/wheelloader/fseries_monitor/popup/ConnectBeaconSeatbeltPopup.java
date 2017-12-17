package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class ConnectBeaconSeatbeltPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewOn;
	TextFitTextView textViewOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SeatBeltBeaconConnect;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public ConnectBeaconSeatbeltPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "ConnectBeaconSeatbeltPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_seatbelt_beacon_connect, null);
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CONNECT_BEACON_SEATBELT_POPUP;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		SeatBeltBeaconConnect = ParentActivity.SeatBeltBeaconConnect; 
		SeatBeltBeaconDisplay(SeatBeltBeaconConnect);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_seatbelt_beacon_title);
		textViewTitle.setText("SeatBelt-Beacon Reminder");
		
		textViewOn = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_seatbelt_beacon_on);
		textViewOn.setText(getString(ParentActivity.getResources().getString(string.On), 19));
		
		textViewOff = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_seatbelt_beacon_off);
		textViewOff.setText(getString(ParentActivity.getResources().getString(string.Off), 20));
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOn();
			}
		});
		textViewOff.setOnClickListener(new View.OnClickListener() {
			
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
	public void ClickOn(){
		ParentActivity.SeatBeltBeaconConnect = CAN1CommManager.DATA_STATE_CONNECT_SEATBELT_BEACON_CONNECTED_ON;
		ParentActivity.SavePref();
		this.dismiss();
	}	
	public void ClickOff(){
		ParentActivity.SeatBeltBeaconConnect = CAN1CommManager.DATA_STATE_CONNECT_SEATBELT_BEACON_CONNECTED_OFF;
		ParentActivity.SavePref();
		this.dismiss();
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
	public void ClickESC(){
		this.dismiss();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOff();
			break;
		case 2:
			ClickOn();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewOn.setPressed(false);
			textViewOff.setPressed(true);
			break;
		case 2:
			textViewOn.setPressed(true);
			textViewOff.setPressed(false);
			break;
		default:
			break;
		}
	}
	public void SeatBeltBeaconDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_CONNECT_SEATBELT_BEACON_CONNECTED_OFF:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_CONNECT_SEATBELT_BEACON_CONNECTED_ON:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
}
