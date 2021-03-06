package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;

public class BucketDumpCalibrationPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;

	
	TextView textViewTitle;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	String strTitle;
	boolean bExitPage;
	int Retry;
	//////////////////////////////////////////////////
	public BucketDumpCalibrationPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "BucketDumpCalibrationPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_anglecalibration_result, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		bExitPage = false;
		Retry = 0;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_RESULT;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_TOP;
		if(Retry == 2 || Retry == 5){
			CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(0);
			CAN1Comm.TxCANToMCU(201);
			CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);			
			Retry = 0;
		}
		try {
			ParentActivity._MenuBaseFragment._BucketDumpCalibration.CursurDisplay(1);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
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
	public void ClickOK(){
		Log.d(TAG,"bExitPage : " + Boolean.toString(bExitPage));
		if(bExitPage == true)
		{
			// ++, 150409 cjg
//			ParentActivity._MenuBaseFragment.showCalibrationAnimation();
			ParentActivity._MenuBaseFragment.showServiceMenuSoftAndStopCalbrationMenuListAnimation();
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SOFTANDSTOP_CAL_TOP);
			// --, 150409 cjg
		}
		this.dismiss();
	}	
	public void setTextTitle(String str){
		strTitle = str;
	}
	public void setExitFlag(boolean flag){
		bExitPage = flag;
	}
	public void setRetry(int retry){
		Retry = retry;
	}
	///////////////////////////////////////////////////
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
