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
import taeha.wheelloader.fseries_monitor.main.R.string;

public class EHCUErrorPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewData;
	TextView textViewOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int JoystickSteeringEnableFailCondition;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public EHCUErrorPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "EHCUErrorPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_ehcu_err, null);
		this.addContentView(mRoot,  new LayoutParams(448,348));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.bEHCUErrPopup = true;
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_EHCUERR_POPUP;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.bEHCUErrPopup = false;
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		JoystickSteeringEnableFailCondition = CAN1Comm.Get_JoystickSteeringEnableFailCondition_2343_PGN65524();
		EHCUErrDisplay(JoystickSteeringEnableFailCondition);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewData = (TextView)mRoot.findViewById(R.id.textView_popup_ehcu_err_data);
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_popup_ehcu_err_ok);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
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
		this.dismiss();
	}	
	
	public void ClickLeft(){
		
	}
	public void ClickRight(){
		
	}
	public void ClickESC(){
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOK();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewOK.setPressed(true);
			break;

		default:
			break;
		}
	}
	public void EHCUErrDisplay(int Data){
		String str = "";
		//str += ParentActivity.getResources().getString(string.Check_joystick_steering_enable_fail_condition);
		Log.d(TAG,"Data : " + Integer.toHexString(Data));
		if(((Data & 0x000F)) != 0){
			str += "\n" + ParentActivity.getResources().getString(string.Check_EHCU_Fault_Code);
		}
		if(((Data & 0x0100) >> 8)== 1){
			str += "\n" + ParentActivity.getResources().getString(string.Check_Steering_Joystick_Position);
		}
		if(((Data & 0x0200) >> 9) == 1){
			str += "\n" + ParentActivity.getResources().getString(string.Check_Seat_Switch_On);
		}
		if(((Data & 0x0400) >> 10) == 1){
			str += "\n" + ParentActivity.getResources().getString(string.Check_Armrest_Swtich_On);
		}
		if(((Data & 0x0800) >> 11) == 1){
			str += "\n" + ParentActivity.getResources().getString(string.Check_Engine_Run);
		}
		if(((Data & 0x1000) >> 12) == 1){
			str += "\n" + ParentActivity.getResources().getString(string.Check_Machine_Stop);
		}	
		if(((Data & 0x2000) >> 13) == 1){
			str += "\n" + ParentActivity.getResources().getString(string.Check_TM_Control_Selection);
		}		
		
		textViewData.setText(str);
	}
}
