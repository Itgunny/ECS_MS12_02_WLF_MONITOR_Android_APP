package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;

public class CalibrationEHCUPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	TextView	textViewOK;
	
	TextView textViewTitle;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public CalibrationEHCUPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "CalibrationEHCUPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_anglecalibration_result, null);
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
		
		//Log.d(TAG, "ScreenIndex"+Integer.toHexString(ParentActivity.ScreenIndex));
		if(ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP)
		{
			ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP;
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_POPUP;
		}
		else if(ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP)
		{
			ParentActivity.TempScreenIndex = ParentActivity.OldScreenIndex;
			ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP;
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_POPUP;
		}
			
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
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP)
		{
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP;
			try {
				ParentActivity._MenuBaseFragment._AngleCalibration.CursurDisplay(1);
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"NullPointerException dismiss");
			}
		}else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP)
		{
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP;
			ParentActivity.OldScreenIndex = ParentActivity.TempScreenIndex;
			try {
				ParentActivity._MenuBaseFragment._PressureCalibration.CursurDisplay(1);
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"NullPointerException dismiss");
			}
		}		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_anglecalibration_result_ok);
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_popup_anglecalibration_result_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_anglecalibration_result_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.You_should_turn_OFF_Soft_end_stop_before_start_calibration), 252));
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
		this.dismiss();
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
