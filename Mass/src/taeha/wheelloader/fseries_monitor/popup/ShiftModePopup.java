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
import taeha.wheelloader.fseries_monitor.main.R.string;

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
	int ShiftMode;
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
	public void InitValuable(){
		super.InitValuable();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
		ShiftModeDisplay(ShiftMode);
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
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
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
		this.dismiss();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickManual();
			break;
		case 2:
			ClickAL();
			break;
		case 3:
			ClickAN();
			break;
		case 4:
			ClickAH();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewManual.setPressed(true);
			textViewAL.setPressed(false);
			textViewAN.setPressed(false);
			textViewAH.setPressed(false);
			break;
		case 2:
			textViewManual.setPressed(false);
			textViewAL.setPressed(true);
			textViewAN.setPressed(false);
			textViewAH.setPressed(false);
			break;
		case 3:
			textViewManual.setPressed(false);
			textViewAL.setPressed(false);
			textViewAN.setPressed(true);
			textViewAH.setPressed(false);
			break;
		case 4:
			textViewManual.setPressed(false);
			textViewAL.setPressed(false);
			textViewAN.setPressed(false);
			textViewAH.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void ShiftModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
}
