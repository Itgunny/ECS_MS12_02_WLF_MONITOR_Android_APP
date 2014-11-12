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

public class SpeedometerInitPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView textViewTitle;
	TextView textViewOK;
	TextView textViewCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Timer mPopupOffTimer = null;
	boolean bClickOKFlag;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public SpeedometerInitPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "SpeedometerInitPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_speedometerfreq_init, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(bClickOKFlag == false)
			return super.onTouchEvent(event);
		else
			return false;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		bClickOKFlag = false;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP;
		try {
			ParentActivity._MenuBaseFragment._SpeedometerFreqFragment.InitText();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_popup_speedometer_freq_init);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_speedometer_freq_init_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_speedometer_freq_init_cancel);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_speedometer_freq_init_title);

		textViewOK = (TextView)mRoot.findViewById(R.id.textView_popup_speedometer_freq_init_ok);
		textViewCancel = (TextView)mRoot.findViewById(R.id.textView_popup_speedometer_freq_init_cancel);
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
	public void ClickOK(){
		CAN1Comm.Set_SettingSelection_PGN61184_105(0);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_PGN61184_105(0xF);
		CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
		CAN1Comm.TxCANToMCU(105);
		CAN1Comm.Set_SettingSelection_PGN61184_105(15);
		StartPopupOffTimer();
		imgbtnOK.setVisibility(View.INVISIBLE);
		imgbtnCancel.setVisibility(View.INVISIBLE);
		textViewOK.setVisibility(View.INVISIBLE);
		textViewCancel.setVisibility(View.INVISIBLE);
		textViewTitle.setText(ParentActivity.getResources().getString(string.Waiting_for_initialization));
		bClickOKFlag = true;
		//this.dismiss();
	}	
	public void ClickCancel(){
		this.dismiss();
	}

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
		mPopupOffTimer.schedule(new PopupOffTimerClass(),2000);	
	}
	
	public void CancelPopupOffTimer(){
		if(mPopupOffTimer != null){
			mPopupOffTimer.cancel();
			mPopupOffTimer.purge();
			mPopupOffTimer = null;
		}
		
	}
}
