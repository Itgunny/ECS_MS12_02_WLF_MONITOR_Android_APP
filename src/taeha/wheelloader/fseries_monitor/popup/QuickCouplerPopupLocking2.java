package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;

public class QuickCouplerPopupLocking2 extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "QuickCouplerPopupLocking2";
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
	public QuickCouplerPopupLocking2(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_key_quickcoupler_locking_2, null);
		this.addContentView(mRoot,  new LayoutParams(500,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
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
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		CancelQuickCouplerSendTimer();
		CAN1Comm.Set_QuickCouplerOperationStatus_3448_PGN65527(CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_OFF);
		CAN1Comm.TxCANToMCU(247);
		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_popup_key_quickcoupler_locking_2);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_locking_2_cancel);
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
						CAN1Comm.Set_QuickCouplerOperationStatus_3448_PGN65527(CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_LOCK);
						CAN1Comm.TxCANToMCU(247);
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
		this.dismiss();
	}

}
