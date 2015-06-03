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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking2.QuickCouplerSendTimerClass;

public class QuickCouplerPopupUnlocking2 extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	ImageButton imgbtnCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Timer mQuickCouplerSendTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public QuickCouplerPopupUnlocking2(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "QuickCouplerPopupUnlocking2";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_key_quickcoupler_unlocking_2, null);
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
		
		StartQuickCouplerSendTimer();
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_ON);	// Buzzer On
		// ++, 150314 bwk
		//ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2;
		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2;
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2;
		}
		// --, 150314 bwk			
		ParentActivity.AttachmentStatus = CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_UNLOCK;
		ParentActivity.SavePref();
		
		CAN1Comm.SetFNFlag(false);
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
		// ++, 150407 bwk
		// ++, 150314 bwk
		//ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		/*
		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER;
		}
		//
		 */
		// --, 150314 bwk
		try {
			if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
				ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
				ParentActivity._MainBBaseFragment._MainBKeyQuickCouplerFragment.CursurDisplay(2);
			}else{
				ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER;
				ParentActivity._MainABaseFragment._MainAKeyQuickCouplerFragment.CursurDisplay(2);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
		//
		
		CancelQuickCouplerSendTimer();
		CAN1Comm.Set_QuickCouplerOperationStatus_3448_PGN65527(CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_OFF);
		CAN1Comm.TxCANToMCU(247);
		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_popup_key_quickcoupler_unlocking_2);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_unlocking_2_cancel);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
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
	//Timer////////////////////////////////////////////////////////////////////////
	public class QuickCouplerSendTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						CAN1Comm.Set_QuickCouplerOperationStatus_3448_PGN65527(CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_UNLOCK);
						CAN1Comm.TxCANToMCU(247);
						CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_ON);	// Buzzer On
						Log.d(TAG,"QuickCouplerSendTimerClass");
					} catch (NullPointerException e) {
						// TODO: handle exception
						Log.e(TAG,"NullPointerException");
					}
					
					
				}
			});
			
		}
		
	}
	
	public void StartQuickCouplerSendTimer(){
		CancelQuickCouplerSendTimer();
		mQuickCouplerSendTimer = new Timer();
		mQuickCouplerSendTimer.schedule(new QuickCouplerSendTimerClass(),1,100);	
	}
	
	public void CancelQuickCouplerSendTimer(){
		if(mQuickCouplerSendTimer != null){
			mQuickCouplerSendTimer.cancel();
			mQuickCouplerSendTimer.purge();
			mQuickCouplerSendTimer = null;
		}
		
	}
	///////////////////////////////////////////////////////////////////////////////

	public void ClickCancel(){
		Log.d(TAG,"ClickCancel");
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_OFF);	// Buzzer Off
		CAN1Comm.SetFNFlag(true);
		this.dismiss();
	}

}
