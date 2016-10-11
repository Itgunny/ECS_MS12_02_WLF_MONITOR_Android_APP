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
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
public class QuickCouplerPopupLocking2 extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	ImageButton imgbtnCancel;
	
	TextView	textViewTitle;
	TextFitTextView 	textViewCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Timer mQuickCouplerSendTimer = null;
	int	mQuickCouplerSend;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public QuickCouplerPopupLocking2(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		 TAG = "QuickCouplerPopupLocking2";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_key_quickcoupler_locking_2, null);
		this.addContentView(mRoot,  new LayoutParams(548,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		QuickCouplerPopupUnlocking3 quickCouplerPopupUnlocking3 = new QuickCouplerPopupUnlocking3(_context);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		Log.d(TAG,"show");
		InitResource();
		InitButtonListener();
		InitValuable();
		
		StartQuickCouplerSendTimer();
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_ON);	// Buzzer On
		// ++, 150314 bwk
		//ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2;
		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2;
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2;
		}
		// --, 150314 bwk		
		//ParentActivity.AttachmentStatus = CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_LOCK;
		//ParentActivity.SavePref();
		
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
		CancelQuickCouplerSendTimer();
		//ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
		try {
			if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
				if(QuickCouplerPopupUnlocking3.QuickCouplerOldScreenIndex == 1){
					if((ParentActivity.OldScreenIndex >= ParentActivity.SCREEN_STATE_MAIN_CAMERA_TOP
							&& ParentActivity.OldScreenIndex <= ParentActivity.SCREEN_STATE_MAIN_CAMERA_END)){
						ParentActivity.setScreenIndex();
					}
					ParentActivity.setScreenIndex();
				}else{
					ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
					ParentActivity._MainBBaseFragment._MainBKeyQuickCouplerFragment.CursurDisplay(1);
				}
			}else{
				if(QuickCouplerPopupUnlocking3.QuickCouplerOldScreenIndex == 1){
					if((ParentActivity.OldScreenIndex >= ParentActivity.SCREEN_STATE_MAIN_CAMERA_TOP
							&& ParentActivity.OldScreenIndex <= ParentActivity.SCREEN_STATE_MAIN_CAMERA_END)){
						ParentActivity.setScreenIndex();
					}
					ParentActivity.setScreenIndex();
				}else{
					ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER;
					ParentActivity._MainABaseFragment._MainAKeyQuickCouplerFragment.CursurDisplay(1);
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}		
		CAN1Comm.Set_QuickCouplerOperationStatus_3448_PGN65527(CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_OFF);
		CAN1Comm.TxCANToMCU(247);
		Log.d(TAG, "DATA_STATE_KEY_QUICKCOUPLER_OFF");
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_popup_key_quickcoupler_locking_2);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_locking_2_cancel);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_key_quickcoupler_locking_2_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Excuting_Locking_Attachment), 450));
		
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_key_quickcoupler_locking_2_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Complete), 351));
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
					if(mQuickCouplerSend==1)
					{
						try {
							CAN1Comm.Set_QuickCouplerOperationStatus_3448_PGN65527(CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_LOCK);
							CAN1Comm.TxCANToMCU(247);
							CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_ON);	// Buzzer On
							Log.d(TAG,"QuickCouplerSendTimerClass");
						} catch (NullPointerException e) {
							// TODO: handle exception
							Log.e(TAG,"NullPointerException");
						}
					}
					else
						Log.d(TAG,"QuickCouplerSendTimerClass_No!!!!");
				}
			});
			
		}
		
	}
	
	public void StartQuickCouplerSendTimer(){
		CancelQuickCouplerSendTimer();
		mQuickCouplerSend = 1;
		mQuickCouplerSendTimer = new Timer();
		mQuickCouplerSendTimer.schedule(new QuickCouplerSendTimerClass(),1,100);	
	}
	
	public void CancelQuickCouplerSendTimer(){
		mQuickCouplerSend = 0;
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
		QuickCouplerPopupUnlocking3.QuickCouplerOldScreenIndex = 0;
	}

}
