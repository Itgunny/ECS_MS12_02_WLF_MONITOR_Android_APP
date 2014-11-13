package taeha.wheelloader.fseries_monitor.menu.monitoring;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EHCUIOInfoBoomLeverFloatFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;

	TextView textviewJoystickStrokeBoomUp;
	TextView textviewJoystickStrokeBoomDown;
	
	
	TextView textviewEPPRCurrentBoomUp;
	TextView textviewEPPRCurrentBoomDown;
	
	TextView textViewBoomLeverFloatingPosition;
	ImageButton imgbtnBoomLeverFloatingPositionUp;
	ImageButton imgbtnBoomLeverFloatingPositionDown;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int JoystickStrokeBoomPosition;
	int JoystickStrokeBucketPosition;
	

	int JoystickStrokeBoomStatus;
	
	
	int EPPRCurrentBoomUp;
	int EPPRCurrentBoomDown;

	int BoomLeverFloatingPosition;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 TAG = "EHCUIOInfoBoomLeverFloatFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_ehcuioinfo_boomleverfloat, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_EHCUINFO_BOOMLEVERFLOAT;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Boom_Lever_Floating_Position_Adjust));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_low_ok);

		textviewJoystickStrokeBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_joystickstroke_data);
		textviewJoystickStrokeBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_joystickstroke_data);
	
		
		textviewEPPRCurrentBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_epprcurrent_data);
		textviewEPPRCurrentBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_epprcurrent_data);
		
		textViewBoomLeverFloatingPosition = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuinfo_boomleverfloat_data);
		
		imgbtnBoomLeverFloatingPositionUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_monitoring_ehcuinfo_boomleverfloat_plus);
		imgbtnBoomLeverFloatingPositionDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_monitoring_ehcuinfo_boomleverfloat_minus);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		imgbtnBoomLeverFloatingPositionUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPlus();
			}
		});
		imgbtnBoomLeverFloatingPositionDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMinus();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		 JoystickStrokeBoomStatus = CAN1Comm.Get_BoomJoystickPositionStatus_2310_PGN65515();
		
		 
		 JoystickStrokeBoomPosition = CAN1Comm.Get_BoomJoystickPosition_2311_PGN65515();
		 JoystickStrokeBucketPosition = CAN1Comm.Get_BucketJoystickPosition_2313_PGN65515();
		
	  
		 EPPRCurrentBoomUp = CAN1Comm.Get_BoomUpEPPRValveCurrent_2304_PGN65517();
		 EPPRCurrentBoomDown = CAN1Comm.Get_BoomDownEPPRValveCurrent_2305_PGN65517();
		
		BoomLeverFloatingPosition = CAN1Comm.Get_BoomLeverFloatingPosition_2336_PGN65515();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		JoystickStrokeUpDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomUp);
		JoystickStrokeDownDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomDown);

		
		EPPRCurrentDisplay(EPPRCurrentBoomUp,textviewEPPRCurrentBoomUp);
		EPPRCurrentDisplay(EPPRCurrentBoomDown,textviewEPPRCurrentBoomDown);

		BoomLeverFloatingPositionDisplay(BoomLeverFloatingPosition);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyEHCUIOInfo();
	}
	public void ClickPlus(){
		CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(1);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(3);
	}
	public void ClickMinus(){
		CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(0);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(3);
	}

	/////////////////////////////////////////////////////////////////////
	public void JoystickStrokeUpDisplay(int position, int status, TextView textview){
		if(position > CAN1CommManager.DATA_STATE_JOYSTICKPOSITION_MAX){
			position  = 0;
		}
		if(status == CAN1CommManager.DATA_STATE_JOYSTICKPOSITION_UP){
			String str;
			str = ParentActivity.GetJoystickPositionString(position);
			textview.setText(str);
		}else{
			textview.setText("0");
		}
	}
	public void JoystickStrokeDownDisplay(int position, int status, TextView textview){
		if(position > CAN1CommManager.DATA_STATE_JOYSTICKPOSITION_MAX){
			position  = 0;
		}
		if(status == CAN1CommManager.DATA_STATE_JOYSTICKPOSITION_DOWN){
			String str;
			str = ParentActivity.GetJoystickPositionString(position);
			textview.setText(str);
		}else{
			textview.setText("0");
		}
	}
	
	public void EPPRCurrentDisplay(int current, TextView textview){
		String str;
		str = ParentActivity.GetEPPRCurrent(current);
		textview.setText(str);
		
	}
	public void BoomLeverFloatingPositionDisplay(int boom_lever_floating_posistion){
		if(boom_lever_floating_posistion < 0)
			boom_lever_floating_posistion = 0;
		else if(boom_lever_floating_posistion > 100)
			boom_lever_floating_posistion = 100;
		
		textViewBoomLeverFloatingPosition.setText(Integer.toString(boom_lever_floating_posistion));
	}
	/////////////////////////////////////////////////////////////////////
	
}
