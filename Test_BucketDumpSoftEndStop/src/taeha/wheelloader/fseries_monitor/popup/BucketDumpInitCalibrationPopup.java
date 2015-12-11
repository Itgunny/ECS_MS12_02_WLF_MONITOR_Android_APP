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

public class BucketDumpInitCalibrationPopup extends ParentPopup{
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
	public BucketDumpInitCalibrationPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "BucketDumpInitCalibrationPopup";
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
		if(ParentActivity.ScreenIndex == Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_TOP)
		{
			ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_TOP;
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_POPUP;
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
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_TOP)
		{
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_TOP;
			try {
				ParentActivity._MenuBaseFragment._BucketDumpCalibration.CursurDisplay(1);
			} catch (NullPointerException e) {
				// TODO: handle 
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
		textViewTitle.setText("Soft End Stop Bucket Dump 기능을 켜주세요.");
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
