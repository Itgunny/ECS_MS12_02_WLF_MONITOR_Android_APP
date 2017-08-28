package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

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

public class SeatBeltWarningPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewSeatBeltWarning;
	
	ImageButton imgbtnOK;
	TextFitTextView textViewOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public SeatBeltWarningPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "SeatBeltWarningPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_main_seatbelt_warning, null);
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_SEATBELT_POPUP; 
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		//ClickCancel();
	
		return false;
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
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewSeatBeltWarning = (TextView)findViewById(R.id.textView_popup_main_seatbelt_warning_title);
		textViewSeatBeltWarning.setText(getString(ParentActivity.getResources().getString(string.Seatbelt_Warning_Popup), 148));
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_main_seatbelt_warning_ok);

		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_main_seatbelt_warning_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEnter();
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
	public void ClickCancel(){
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_SEATBELT_POPUP)
		{
			Log.d(TAG,"Crash!!!AxleClickCancel:ScreenIndex="+Integer.toHexString(ParentActivity.ScreenIndex)+"OldScreenIndex="+Integer.toHexString(ParentActivity.OldScreenIndex));
		}
		else
		{
			 ParentActivity.setScreenIndex();
		}
		
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(final int key){
		switch (key) {
		case CAN1CommManager.ESC:
			ClickESC();
			break;
		case CAN1CommManager.ENTER:
			ClickEnter();
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		ClickCancel();
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
			imgbtnOK.setPressed(true);
			break;
		case 2:
			imgbtnOK.setPressed(false);
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////

}
