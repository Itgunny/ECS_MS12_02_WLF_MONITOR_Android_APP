package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.TextView;

public class SelectSeatbeltOrEntertainmentLockPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewEntertaiment;
	TextFitTextView textViewSbrAlarm;
	TextFitTextView textViewOK;
	
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public SelectSeatbeltOrEntertainmentLockPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "SelectSeatbeltOrEntertainmentLockPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_select_seatbelt_or_entertainment, null);
		this.addContentView(mRoot,  new LayoutParams(448,365));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_SELECT_SEATBELT_OR_ENTER_POPUP;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		
	}
	@Override
	public void InitValuable(){
		super.InitValuable();

		
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_select_seatbelt_or_enter_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Select_Menu), 510));
		
		textViewEntertaiment = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_select_seatbelt_or_enter_enter);
		textViewEntertaiment.setText(getString(ParentActivity.getResources().getString(R.string.Entertainment_limit), 503));
		
		
		textViewSbrAlarm = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_select_seatbelt_or_enter_seatbelt);
		textViewSbrAlarm.setText(getString(ParentActivity.getResources().getString(R.string.SBR_Alarm), 511));
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_select_seatbelt_or_enter_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		


	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewEntertaiment.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickEntertainmentLimit();
			}


		});
		textViewSbrAlarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSBRAlarm();
			}


		});
		
		textViewOK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
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
	
	private void ClickEntertainmentLimit() {
		// TODO Auto-generated method stub
		this.dismiss();
		ParentActivity.showEntertainmentLockPopup();
	}
	
	private void ClickSBRAlarm() {
		// TODO Auto-generated method stub
		this.dismiss();
		ParentActivity.showSeatBeltAlarmOnOffPopup();
	}
	
	public void ClickOK(){
		this.dismiss();
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
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
			ClickEntertainmentLimit();
			break;
		case 2:
			ClickSBRAlarm();
			break;
		case 3:
			ClickOK();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewEntertaiment.setPressed(true);
			textViewSbrAlarm.setPressed(false);
			textViewOK.setPressed(false);
			break;
			
		case 2:
			textViewEntertaiment.setPressed(false);
			textViewSbrAlarm.setPressed(true);
			textViewOK.setPressed(false);
			break;
			
		case 3:
			textViewEntertaiment.setPressed(false);
			textViewSbrAlarm.setPressed(false);
			textViewOK.setPressed(true);
			break;
		default:
			break;
		}
	}

}
