package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;

public class AxleTempWarningPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Timer mPopupOffTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public AxleTempWarningPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "AxleTempWarningPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_main_axle_warning, null);
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
		
		if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_A)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS_POPUP; 
		else 
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS_POPUP; 
		
		StartPopupOffTimer();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		ClickCancel();
	
		return false;
	}	
	@Override
	public void InitValuable(){
		super.InitValuable();
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		
		super.dismiss();
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
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
		//Log.d(TAG,"AxleClickCancel:ScreenIndex="+Integer.toHexString(ParentActivity.ScreenIndex)+"OldScreenIndex="+Integer.toHexString(ParentActivity.OldScreenIndex));
		
		 if(((ParentActivity.ScreenIndex & Home.SCREEN_STATE_FILTER) == Home.SCREEN_STATE_MAIN_A_TOP)
				 ||((ParentActivity.ScreenIndex & Home.SCREEN_STATE_FILTER) == Home.SCREEN_STATE_MAIN_B_TOP))
			ParentActivity.setScreenIndex();
		
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		ClickCancel();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	public class PopupOffTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ClickCancel();
		}
				
	}
	
	public void StartPopupOffTimer(){
		CancelPopupOffTimer();
		mPopupOffTimer = new Timer();
		mPopupOffTimer.schedule(new PopupOffTimerClass(),5000);	
	}
	
	public void CancelPopupOffTimer(){
		if(mPopupOffTimer != null){
			mPopupOffTimer.cancel();
			mPopupOffTimer.purge();
			mPopupOffTimer = null;
		}
		
	}
}
