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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
public class EngineModePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewEcono;
	TextFitTextView textViewSTD;
	TextFitTextView textViewPWR;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public EngineModePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "EngineModePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_enginemode, null);
		this.addContentView(mRoot,  new LayoutParams(448,348));
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_MODE;

	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		EngineModeDisplay(EngineMode);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_enginemode_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Engine_Mode), 240));
		textViewEcono = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_enginemode_econo);
		textViewEcono.setText(getString(ParentActivity.getResources().getString(R.string.Econo), 241));
		textViewSTD = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_enginemode_std);
		textViewSTD.setText(getString(ParentActivity.getResources().getString(R.string.Standard), 242));
		textViewPWR = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_enginemode_pwr);
		textViewPWR.setText(getString(ParentActivity.getResources().getString(R.string.Power), 243));
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewEcono.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEcono();
			}
		});
		textViewSTD.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSTD();
			}
		});
		textViewPWR.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPWR();
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
	public void ClickEcono(){
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO);
		CAN1Comm.TxCANToMCU(101);
		this.dismiss();
	}	
	public void ClickSTD(){
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_STD);
		CAN1Comm.TxCANToMCU(101);
		this.dismiss();
	}	
	public void ClickPWR(){
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR);
		CAN1Comm.TxCANToMCU(101);
		this.dismiss();
	}	
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
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
		case 3:
			ClickPWR();
			break;
		case 2:
			ClickSTD();
			break;
		case 1:
			ClickEcono();
			break;
		default:
			break;
		}
	}	
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewEcono.setPressed(true);
			textViewSTD.setPressed(false);
			textViewPWR.setPressed(false);
			break;
		case 2:
			textViewEcono.setPressed(false);
			textViewSTD.setPressed(true);
			textViewPWR.setPressed(false);
			break;
		case 3:
			textViewEcono.setPressed(false);
			textViewSTD.setPressed(false);
			textViewPWR.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void EngineModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	
	
	
}
