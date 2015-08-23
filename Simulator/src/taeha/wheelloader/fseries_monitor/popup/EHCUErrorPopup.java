package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
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
import taeha.wheelloader.fseries_monitor.main.R.color;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class EHCUErrorPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewData;
	TextView textViewOK;
	TextView textViewTitle;		// ++, --, 150209 bwk
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int JoystickSteeringEnableFailCondition;
	int Safety_CPU_Error;		// ++, --, 150209 bwk
	int	JoystickSteeringActiveStatus;
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
	
		EHCUErrInitDisplay(ParentActivity.OldJoystickSteeringEnableFailCondition);	// ++, --, 150210 bwk
		
		ParentActivity.bEHCUErrPopup = true;
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_EHCUERR_POPUP;
	}
	// ++, 150209 bwk
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(Safety_CPU_Error == 1 || (JoystickSteeringEnableFailCondition != 0xFFFF && ((JoystickSteeringEnableFailCondition & 0x0004)>>2) == 1))	
			return false;
		else
			return super.onTouchEvent(event);
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		if(Safety_CPU_Error == 1 || (JoystickSteeringEnableFailCondition != 0xFFFF && ((JoystickSteeringEnableFailCondition & 0x0004)>>2) == 1))	
			return false;
		else
			return super.dispatchKeyEvent(event);
	}
	// --, 150209 bwk
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.bEHCUErrPopup = false;
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
//		ParentActivity.OldJoystickSteeringEnableFailCondition = JoystickSteeringEnableFailCondition;	// ++, --, 150212 bwk
	}
	
	@Override
	public void InitValuable(){
		super.InitValuable();
		Safety_CPU_Error = 0;	// ++, --, 150210 bwk
		JoystickSteeringEnableFailCondition = CAN1Comm.Get_JoystickSteeringEnableFailCondition_2343_PGN65524();
		JoystickSteeringActiveStatus = CAN1Comm.Get_JoystickSteeringActiveStatusEHCU_186_PGN65517();
	}
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_ehcu_err_title);	// ++, --, 150209 bwk
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
		// ++, 150210 bwk
		if(Safety_CPU_Error == 0)
		{
			JoystickSteeringEnableFailCondition = CAN1Comm.Get_JoystickSteeringEnableFailCondition_2343_PGN65524();
			JoystickSteeringActiveStatus = CAN1Comm.Get_JoystickSteeringActiveStatusEHCU_186_PGN65517();
		}
		// --, 150210 bwk
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		// ++, 150210 bwk
		if(Safety_CPU_Error == 0)
			EHCUErrDisplay(JoystickSteeringEnableFailCondition,JoystickSteeringActiveStatus);
		// --, 150210 bwk
	}
	///////////////////////////////////////////////////////////////////////////////
	public void ClickOK(){
//		if(Safety_CPU_Error == 0 && (JoystickSteeringEnableFailCondition == 0xFFFF || ((JoystickSteeringEnableFailCondition & 0x0004)>>2) != 1))	// ++, --, 150209 bwk
		if(Safety_CPU_Error == 0 && (JoystickSteeringEnableFailCondition == 0 || JoystickSteeringEnableFailCondition == 0xFFFF || ((JoystickSteeringEnableFailCondition & 0x0004)>>2) != 1))	// ++, --, 150209 bwk
		{
			this.dismiss();
		}
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

	// ++, 150210 bwk	
	public void EHCUErrInitDisplay(int Data){
		String str = "";
		//str += ParentActivity.getResources().getString(string.Check_joystick_steering_enable_fail_condition);
		//Log.d(TAG,"Data : " + Integer.toHexString(Data));
		if(Data == 0xffff || Data == 0)
			return;
		
		if(((Data & 0x0004)>>2) == 1)
		{
			textViewTitle.setText(ParentActivity.getResources().getString(string.Initial_Para_Error_Found));
			str = "\n" + ParentActivity.getResources().getString(string.Please_Reset_Power);
			Safety_CPU_Error = 1;
			textViewOK.setVisibility(View.INVISIBLE);
			textViewData.setTextColor(ParentActivity.getResources().getColor(color.red));
			textViewData.setTextSize(35);
		}
		else
		{
			textViewTitle.setText(ParentActivity.getResources().getString(string.Check_joystick_steering_enable_fail_condition));
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
			textViewOK.setVisibility(View.VISIBLE);
		}
			
		textViewData.setText(str);
	}
	// --, 150210 bwk
	
	public void EHCUErrDisplay(int Data, int PopupOff){
		String str = "";
		//str += ParentActivity.getResources().getString(string.Check_joystick_steering_enable_fail_condition);
		// ++, 150210 bwk
		ParentActivity.OldJoystickSteeringEnableFailCondition = Data;
		Log.d(TAG, "EHCUErrDisplay PopupOff"+PopupOff);
		Log.d(TAG, "EHCUErrDisplay Data"+Data);

		if(JoystickSteeringEnableFailCondition == 0xFFFF || Data == 0xFFFF
				|| JoystickSteeringEnableFailCondition == 0 || Data == 0)
		{
			if(PopupOff == CAN1CommManager.DATA_STATE_LAMP_ON)
			{
				this.dismiss();
			}
			return;
		}
		
		
		if(((Data & 0x0004)>>2) == 1)
		{
			textViewTitle.setText(ParentActivity.getResources().getString(string.Initial_Para_Error_Found));
			str = "\n" + ParentActivity.getResources().getString(string.Please_Reset_Power);
			Safety_CPU_Error = 1;
			textViewOK.setVisibility(View.INVISIBLE);
			textViewData.setTextColor(ParentActivity.getResources().getColor(color.red));
			textViewData.setTextSize(35);
		}
		else if(PopupOff == CAN1CommManager.DATA_STATE_LAMP_ON)
//		else if(Data == 0x0000)
		{
			
			this.dismiss();
		}
		else
		// --, 150210 bwk
		{
			textViewTitle.setText(ParentActivity.getResources().getString(string.Check_joystick_steering_enable_fail_condition));
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
			textViewOK.setVisibility(View.VISIBLE);
		}
		
		textViewData.setText(str);
	}
}
