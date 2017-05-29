package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.a.key.MainAKeyRideControlSpeedFragment;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class RideControlWarningPopup extends ParentPopup {


	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewRideControlWarning;
	
	ImageButton imgbtnOK;
	TextView textViewOK;
	//////////////////////////////////////////////////

	//VALUABLE////////////////////////////////////////
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public RideControlWarningPopup(Context _context) {
		super(_context);
		
		TAG = "RideControlWarningPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_ridecontrol_always_warning, null);
		this.addContentView(mRoot, new LayoutParams(448, 288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		// TODO Auto-generated constructor stub
	}

	@Override 
	public void show() {
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_KEY_RIDECONTROL_POPUP;

		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}
	
	@Override
	public void dismiss() {
		
		
		super.dismiss();
	}
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_main_key_ridecontrol_warning_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_main_key_ridecontrol_warning_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
		
		textViewRideControlWarning = (TextView)findViewById(R.id.textView_popup_main_key_ridecontrol_warning_title);
		textViewRideControlWarning.setText(getString(ParentActivity.getResources().getString(R.string.Ride_Control_Warning), 504));
	}

	@Override	
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				ClickOk();
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
	

	public void ClickESC() {
		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED;
			ParentActivity._MainBBaseFragment._MainBKeyRideControlSpeedFragment.CursurDisplay(3);
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL_SPEED;
			ParentActivity._MainABaseFragment._MainAKeyRideControlSpeedFragment.CursurDisplay(3);
		}
		this.dismiss();
	}
	public void ClickEnter() {
		ClickOk();
	}
	public void ClickOk() {
		

		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED;
			ParentActivity._MainBBaseFragment._MainBKeyRideControlSpeedFragment.CursurDisplay(3);
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL_SPEED;
			ParentActivity._MainABaseFragment._MainAKeyRideControlSpeedFragment.CursurDisplay(3);
		}
		this.dismiss();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	
	public void KeyButtonClick(final int key){
		switch (key) {
		case CAN1CommManager.ESC:
			ClickESC();
			break;
		case CAN1CommManager.ENTER:
			ClickEnter();
			break;
		default:
			break;
		}
	}
}
