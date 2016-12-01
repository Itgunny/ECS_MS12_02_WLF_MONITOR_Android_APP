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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.Home.SeatBeltTimerClass;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class SpeedometerInitPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextFitTextView textViewTitle;
	TextFitTextView textViewOK;
	TextFitTextView textViewCancel;
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
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		
		imgbtnOK.setVisibility(View.VISIBLE);
		imgbtnCancel.setVisibility(View.VISIBLE);
		textViewOK.setVisibility(View.VISIBLE);
		textViewCancel.setVisibility(View.VISIBLE);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51));
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP;
		try {
			ParentActivity._MenuBaseFragment._SpeedometerFreqFragment.InitText();
			ParentActivity._MenuBaseFragment._SpeedometerFreqFragment.CursurDisplay(13);
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
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_speedometer_freq_init_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51));
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_speedometer_freq_init_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));

		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_speedometer_freq_init_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Cancel), 16));
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
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(0xF);
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
		mPopupOffTimer.schedule(new PopupOffTimerClass(),1000);	
	}
	
	public void CancelPopupOffTimer(){
		if(mPopupOffTimer != null){
			mPopupOffTimer.cancel();
			mPopupOffTimer.purge();
			mPopupOffTimer = null;
		}
		
	}
	//////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOK();
			break;
		case 2:
			ClickCancel();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			imgbtnOK.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}
}
