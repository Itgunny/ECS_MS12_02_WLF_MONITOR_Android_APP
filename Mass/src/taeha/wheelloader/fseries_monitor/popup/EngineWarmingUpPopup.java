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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class EngineWarmingUpPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewOn;
	TextFitTextView textViewOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineWarmingUp;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public EngineWarmingUpPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "EngineWarmingUpPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_warmingup, null);
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_WARMINGUP;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		EngineWarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
		WarmingUpDisplay(EngineWarmingUp);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_warmingup_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Warming_Up), 452));
		textViewOn = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_warmingup_on);
		textViewOn.setText(getString(ParentActivity.getResources().getString(R.string.On), 19));
		textViewOff = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_warmingup_off);
		textViewOff.setText(getString(ParentActivity.getResources().getString(R.string.Off), 20));
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
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON);
		CAN1Comm.TxCANToMCU(101);
		this.dismiss();
	}	
	public void ClickOff(){
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF);
		CAN1Comm.TxCANToMCU(101);
		this.dismiss();
	}
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
		this.dismiss();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOff();
			break;
		case 2:
			ClickOn();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewOn.setPressed(false);
			textViewOff.setPressed(true);
			break;
		case 2:
			textViewOn.setPressed(true);
			textViewOff.setPressed(false);
			break;
		default:
			break;
		}
	}
	public void WarmingUpDisplay(int data){
	
		switch (data) {
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;

		}
	}
}
