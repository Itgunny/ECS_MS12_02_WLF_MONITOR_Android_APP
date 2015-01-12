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
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup.PopupOffTimerClass;

public class PressureCalibrationResultPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;

	
	TextView textViewTitle;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	String strTitle;
	boolean bExitPage;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public PressureCalibrationResultPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "PressureCalibrationResultPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_anglecalibration_result, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		bExitPage = false;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_RESULT;
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
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_TOP;
		try {
			ParentActivity._MenuBaseFragment._PressureCalibration.CursurDisplay(1);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_anglecalibration_result_ok);

	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_anglecalibration_result_title);

		textViewTitle.setText(strTitle);
		
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
		Log.d(TAG,"bExitPage : " + Boolean.toString(bExitPage));
		if(bExitPage == true)
			ParentActivity._MenuBaseFragment.showCalibrationAnimation();
		this.dismiss();
	}	
	////////////////////////////////////////////////////////////////////////////////
	public void setTextTitle(String str){
		
		strTitle = str;
	}
	public void setExitFlag(boolean flag){
		bExitPage = flag;
	}
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		
	}
	public void ClickRight(){
		
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		ClickOK();
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(true);
	}
}
