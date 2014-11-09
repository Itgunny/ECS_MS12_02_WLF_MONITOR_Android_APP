package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

public class ShiftModePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewManual;
	TextView textViewAL;
	TextView textViewAN;
	TextView textViewAH;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public ShiftModePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "ShiftModePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_shiftmode, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE;

	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewManual = (TextView)mRoot.findViewById(R.id.textView_popup_shiftmode_Manual);
		textViewAL = (TextView)mRoot.findViewById(R.id.textView_popup_shiftmode_AL);
		textViewAN = (TextView)mRoot.findViewById(R.id.textView_popup_shiftmode_AN);
		textViewAH = (TextView)mRoot.findViewById(R.id.textView_popup_shiftmode_AH);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickManual();
			}
		});
		textViewAL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAL();
			}
		});
		textViewAN.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAN();
			}
		});
		textViewAH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAH();
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
	public void ClickManual(){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickAL(){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickAN(){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickAH(){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	

}
