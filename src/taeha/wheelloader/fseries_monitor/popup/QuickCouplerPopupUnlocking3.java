package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking2.QuickCouplerSendTimerClass;

public class QuickCouplerPopupUnlocking3 extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnLocking;
	ImageButton imgbtnOK;
	
	CheckBox checkShow;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public QuickCouplerPopupUnlocking3(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "QuickCouplerPopupUnlocking3";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_key_quickcoupler_unlocking_3, null);
		this.addContentView(mRoot,  new LayoutParams(548,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
	
		//return super.onTouchEvent(event);
		return false;
	
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		//return super.dispatchKeyEvent(event);
		return false;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		imgbtnLocking = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_unlocking_3_lock);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_unlocking_3_ok);
		
		checkShow = (CheckBox)mRoot.findViewById(R.id.checkBox_popup_key_quickcoupler_unlocking_3);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnLocking.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLocking();
			}
		});
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
	
	///////////////////////////////////////////////////////////////////////////////

	public void ClickOK(){
		if(checkShow.isChecked() == true){
			ParentActivity.AttachmentStatus = CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_LOCK;
		}else{
			ParentActivity.AttachmentStatus = CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_UNLOCK;
		}
		ParentActivity.SavePref();
		this.dismiss();
	}

	public void ClickLocking(){
		ParentActivity.OldScreenIndex = ParentActivity.ScreenIndex;
		ParentActivity.showQuickCouplerPopupLocking2();
		this.dismiss();
	}
}
