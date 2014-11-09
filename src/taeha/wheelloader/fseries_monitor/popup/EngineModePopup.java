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

public class EngineModePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewEcono;
	TextView textViewSTD;
	TextView textViewPWR;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewEcono = (TextView)mRoot.findViewById(R.id.textView_popup_enginemode_econo);
		textViewSTD = (TextView)mRoot.findViewById(R.id.textView_popup_enginemode_std);
		textViewPWR = (TextView)mRoot.findViewById(R.id.textView_popup_enginemode_pwr);
		

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

}
