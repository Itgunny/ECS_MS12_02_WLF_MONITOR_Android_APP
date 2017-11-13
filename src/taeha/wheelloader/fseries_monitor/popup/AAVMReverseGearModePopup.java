package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class AAVMReverseGearModePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewOn;
	TextFitTextView textViewOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int reverseGearUseAAVM;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public AAVMReverseGearModePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "AAVMReverseGearModePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_aavmreversegearmode, null);
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_PREFERENCE_AAVMSETTING_TOP;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_PREFERENCE_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		reverseGearUseAAVM = ParentActivity.CameraReverseMode;
		AAVMReverseDisplay(reverseGearUseAAVM);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_aavmreverse_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.AAVM_Reverse_mode), 508));
		textViewOn = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_aavmreverse_on);
		textViewOn.setText(getString(ParentActivity.getResources().getString(R.string.On), 19));
		textViewOff = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_aavmreverse_off);
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
		ParentActivity.CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON;
		this.dismiss();
	}	
	public void ClickOff(){
		ParentActivity.CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF;
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
			ClickOn();
			break;
		case 2:
			ClickOff();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewOn.setPressed(true);
			textViewOff.setPressed(false);
			break;
		case 2:
			textViewOn.setPressed(false);
			textViewOff.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void AAVMReverseDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;

		default:
			break;
		}
	}
}
