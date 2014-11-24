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
import taeha.wheelloader.fseries_monitor.main.R.string;
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
import android.widget.RadioButton;
import android.widget.TextView;

public class EHCUIOInfoBoomLeverFloatFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int STATE_BOOMLEVER	= 0;
	private static final int STATE_BOOMDOWN		= 1;
	private static final int STATE_BUCKETDUMP	= 2;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	
	TextView textviewTitleBoomUp;
	TextView textviewTitleBoomDown;

	TextView textviewJoystickStrokeBoomUp;
	TextView textviewJoystickStrokeBoomDown;
	
	
	TextView textviewEPPRCurrentBoomUp;
	TextView textviewEPPRCurrentBoomDown;
	
	TextView textViewBoomLeverFloatingPosition;
	TextView textViewBoomLeverFloatingPositionUnit;
	ImageButton imgbtnBoomLeverFloatingPositionUp;
	ImageButton imgbtnBoomLeverFloatingPositionDown;
	
	RadioButton radioBoomLever;
	RadioButton radioBoomDown;
	RadioButton radioBucketDump;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int JoystickStrokeBoomPosition;
	int JoystickStrokeBucketPosition;
	

	int JoystickStrokeBoomStatus;
	int JoystickStrokeBucketStatus;
	
	int EPPRCurrentBoomUp;
	int EPPRCurrentBoomDown;
	int EPPRCurrentBucketIn;
	int EPPRCurrentBucketDump;

	int BoomLeverFloatingPosition;
	
	int BoomDownEPPRValveMaxCurrent;
	int BucketOutEPPRValveMaxCurrent;
	
	int SelectIndex;
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
		
		ClickBoomLever();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_EHCUINFO_BOOMLEVERFLOAT;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Fingertip_Calibration));
		return mRoot;
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_low_ok);
		

		
		textviewTitleBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_operation);
		textviewTitleBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_operation);

		textviewJoystickStrokeBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_joystickstroke_data);
		textviewJoystickStrokeBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_joystickstroke_data);
	
		
		textviewEPPRCurrentBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_epprcurrent_data);
		textviewEPPRCurrentBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_epprcurrent_data);
		
		textViewBoomLeverFloatingPosition = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuinfo_boomleverfloat_data);
		textViewBoomLeverFloatingPositionUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuinfo_boomleverfloat_unit);
		
		
		imgbtnBoomLeverFloatingPositionUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_monitoring_ehcuinfo_boomleverfloat_plus);
		imgbtnBoomLeverFloatingPositionDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_monitoring_ehcuinfo_boomleverfloat_minus);
		
		radioBoomLever = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomlever);
		radioBoomDown = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown);
		radioBucketDump = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_bucketdump);
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
		radioBoomLever.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomLever();
			}
		});
		radioBoomDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomDown();
			}
		});
		radioBucketDump.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketDump();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		 JoystickStrokeBoomStatus = CAN1Comm.Get_BoomJoystickPositionStatus_2310_PGN65515();
		 JoystickStrokeBucketStatus = CAN1Comm.Get_BucketJoystickPositionStatus_2312_PGN65515();
		 
		 JoystickStrokeBoomPosition = CAN1Comm.Get_BoomJoystickPosition_2311_PGN65515();
		 JoystickStrokeBucketPosition = CAN1Comm.Get_BucketJoystickPosition_2313_PGN65515();
		
	  
		 EPPRCurrentBoomUp = CAN1Comm.Get_BoomUpEPPRValveCurrent_2304_PGN65517();
		 EPPRCurrentBoomDown = CAN1Comm.Get_BoomDownEPPRValveCurrent_2305_PGN65517();
		 EPPRCurrentBucketIn = CAN1Comm.Get_BucketInEPPRValveCurrent_2306_PGN65517();
		 EPPRCurrentBucketDump = CAN1Comm.Get_BucketOutEPPRValveCurrent_2307_PGN65517();
		
		 BoomLeverFloatingPosition = CAN1Comm.Get_BoomLeverFloatingPosition_2336_PGN65515();
		
		 BoomDownEPPRValveMaxCurrent = CAN1Comm.Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524();
		 BucketOutEPPRValveMaxCurrent = CAN1Comm.Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BoomUpDisplay(SelectIndex);
		BoomDownDisplay(SelectIndex);
		AdjustDisplay(SelectIndex);	
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
		switch (SelectIndex) {
		case STATE_BOOMLEVER:
			CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(1);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(3);
			break;
		case STATE_BOOMDOWN:
			CAN1Comm.Set_BoomDownSpeedAdjust_PGN61184_203(1);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_BoomDownSpeedAdjust_PGN61184_203(3);
			break;
		case STATE_BUCKETDUMP:
			CAN1Comm.Set_BucketOutSpeedAdjust_PGN61184_203(1);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_BucketOutSpeedAdjust_PGN61184_203(3);
			break;
		default:
			break;
		}
		
	}
	public void ClickMinus(){
		switch (SelectIndex) {
		case STATE_BOOMLEVER:
			CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(0);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_BoomLeverFloatingPosition_PGN61184_203(3);
			break;
		case STATE_BOOMDOWN:
			CAN1Comm.Set_BoomDownSpeedAdjust_PGN61184_203(0);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_BoomDownSpeedAdjust_PGN61184_203(3);
			break;
		case STATE_BUCKETDUMP:
			CAN1Comm.Set_BucketOutSpeedAdjust_PGN61184_203(0);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_BucketOutSpeedAdjust_PGN61184_203(3);
			break;
		default:
			break;
		}
		
	}
	public void ClickBoomLever(){
		SelectIndex = STATE_BOOMLEVER;
		radioBoomLever.setChecked(true);
		radioBoomDown.setChecked(false);
		radioBucketDump.setChecked(false);
	}
	public void ClickBoomDown(){
		SelectIndex = STATE_BOOMDOWN;
		radioBoomLever.setChecked(false);
		radioBoomDown.setChecked(true);
		radioBucketDump.setChecked(false);
	}
	public void ClickBucketDump(){
		SelectIndex = STATE_BUCKETDUMP;
		radioBoomLever.setChecked(false);
		radioBoomDown.setChecked(false);
		radioBucketDump.setChecked(true);
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
	public void BoomUpDisplay(int Index){
		switch (Index) {
		case STATE_BOOMLEVER:
			JoystickStrokeUpDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomUp);
			EPPRCurrentDisplay(EPPRCurrentBoomUp,textviewEPPRCurrentBoomUp);
			textviewTitleBoomUp.setText(ParentActivity.getResources().getString(string.Boom_Up));
			break;
		case STATE_BOOMDOWN:
			JoystickStrokeUpDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomUp);
			EPPRCurrentDisplay(EPPRCurrentBoomUp,textviewEPPRCurrentBoomUp);
			textviewTitleBoomUp.setText(ParentActivity.getResources().getString(string.Boom_Up));
			break;
		case STATE_BUCKETDUMP:
			JoystickStrokeUpDisplay(JoystickStrokeBucketPosition,JoystickStrokeBucketStatus,textviewJoystickStrokeBoomUp);
			EPPRCurrentDisplay(EPPRCurrentBucketIn,textviewEPPRCurrentBoomUp);
			textviewTitleBoomUp.setText(ParentActivity.getResources().getString(string.Bucket_In));
			break;
		default:
			break;
		}
	}
	public void BoomDownDisplay(int Index){
		switch (Index) {
		case STATE_BOOMLEVER:
			JoystickStrokeDownDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomDown);
			EPPRCurrentDisplay(EPPRCurrentBoomDown,textviewEPPRCurrentBoomDown);
			textviewTitleBoomDown.setText(ParentActivity.getResources().getString(string.Boom_Down));
			break;
		case STATE_BOOMDOWN:
			JoystickStrokeDownDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomDown);
			EPPRCurrentDisplay(EPPRCurrentBoomDown,textviewEPPRCurrentBoomDown);
			textviewTitleBoomDown.setText(ParentActivity.getResources().getString(string.Boom_Down));
			break;
		case STATE_BUCKETDUMP:
			JoystickStrokeDownDisplay(JoystickStrokeBucketPosition,JoystickStrokeBucketStatus,textviewJoystickStrokeBoomDown);
			EPPRCurrentDisplay(EPPRCurrentBucketDump,textviewEPPRCurrentBoomDown);
			textviewTitleBoomDown.setText(ParentActivity.getResources().getString(string.Bucket_Dump));
			break;
		default:
			break;
		}
	}
	public void AdjustDisplay(int Index){
		switch (Index) {
		case STATE_BOOMLEVER:
			BoomLeverFloatingPositionDisplay(BoomLeverFloatingPosition);
			textViewBoomLeverFloatingPositionUnit.setText(ParentActivity.getResources().getString(string.Percent));
			break;
		case STATE_BOOMDOWN:
			EPPRCurrentDisplay(BoomDownEPPRValveMaxCurrent,textViewBoomLeverFloatingPosition);
			textViewBoomLeverFloatingPositionUnit.setText(ParentActivity.getResources().getString(string.mA));
			break;
		case STATE_BUCKETDUMP:
			EPPRCurrentDisplay(BucketOutEPPRValveMaxCurrent,textViewBoomLeverFloatingPosition);
			textViewBoomLeverFloatingPositionUnit.setText(ParentActivity.getResources().getString(string.mA));
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
