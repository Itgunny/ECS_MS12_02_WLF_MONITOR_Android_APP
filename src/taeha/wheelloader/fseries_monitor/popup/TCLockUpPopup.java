package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;

public class TCLockUpPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewOn;
	TextView textViewOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public TCLockUpPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "TCLockUpPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_tclockup, null);
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_TOP;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewOn = (TextView)mRoot.findViewById(R.id.textView_popup_tclockup_on);
		textViewOff = (TextView)mRoot.findViewById(R.id.textView_popup_tclockup_off);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOn();
			}
		});
		textViewOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
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
	public void ClickOn(){
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickOff(){
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
}
