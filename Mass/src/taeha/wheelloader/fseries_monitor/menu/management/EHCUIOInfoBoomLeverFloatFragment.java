package taeha.wheelloader.fseries_monitor.menu.management;

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
import android.os.Handler;
import android.os.Message;
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
	TextView textviewOK;
	
	TextView textviewOperation;
	TextView textviewJoystickStroke;
	TextView textviewEPPRCurrent;
	
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
	
	int CursurIndex;
	
	Handler HandleCursurDisplay;
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
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_BOOMLEVERFLOAT;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Fingertip_Calibration), 457);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_low_ok);
		textviewOK = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_low_ok);
		textviewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		textviewOperation = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_operation_title);
		textviewOperation.setText(getString(ParentActivity.getResources().getString(R.string.Operation), 258));
		textviewJoystickStroke = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_joystickstroke_title);
		textviewJoystickStroke.setText(getString(ParentActivity.getResources().getString(R.string.Joystick_Stroke), 259));
		textviewEPPRCurrent = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_epprcurrent_title);
		textviewEPPRCurrent.setText(getString(ParentActivity.getResources().getString(R.string.EPPR_Current), 260));
		
		textviewTitleBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_operation);
		textviewTitleBoomUp.setText(getString(ParentActivity.getResources().getString(R.string.Boom_Up), 236));
		textviewTitleBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_operation);
		textviewTitleBoomDown.setText(getString(ParentActivity.getResources().getString(R.string.Boom_Down), 237));

		textviewJoystickStrokeBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_joystickstroke_data);
		textviewJoystickStrokeBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_joystickstroke_data);
	
		
		textviewEPPRCurrentBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomup_epprcurrent_data);
		textviewEPPRCurrentBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown_epprcurrent_data);
		
		textViewBoomLeverFloatingPosition = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuinfo_boomleverfloat_data);
		textViewBoomLeverFloatingPositionUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuinfo_boomleverfloat_unit);
		textViewBoomLeverFloatingPositionUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		
		imgbtnBoomLeverFloatingPositionUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_monitoring_ehcuinfo_boomleverfloat_plus);
		imgbtnBoomLeverFloatingPositionDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_monitoring_ehcuinfo_boomleverfloat_minus);
		
		radioBoomLever = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomlever);
		radioBoomLever.setText(getString(ParentActivity.getResources().getString(R.string.Boom_Lever_Floating_Position_Adjust), 456));
		radioBoomDown = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_boomdown);
		radioBoomDown.setText(getString(ParentActivity.getResources().getString(R.string.Boom_Down_Response_Adjust), 458));
		radioBucketDump = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_ehcuioinfo_boomleverfloat_bucketdump);
		radioBucketDump.setText(getString(ParentActivity.getResources().getString(R.string.Bucket_Dump_Response_Adjust), 459));
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnBoomLeverFloatingPositionUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPlus();
			}
		});
		imgbtnBoomLeverFloatingPositionDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickMinus();
			}
		});
		radioBoomLever.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBoomLever();
			}
		});
		radioBoomDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBoomDown();
			}
		});
		radioBucketDump.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
			textviewTitleBoomUp.setText(getString(ParentActivity.getResources().getString(string.Boom_Up), 236));
			break;
		case STATE_BOOMDOWN:
			JoystickStrokeUpDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomUp);
			EPPRCurrentDisplay(EPPRCurrentBoomUp,textviewEPPRCurrentBoomUp);
			textviewTitleBoomUp.setText(getString(ParentActivity.getResources().getString(string.Boom_Up), 236));
			break;
		case STATE_BUCKETDUMP:
			JoystickStrokeUpDisplay(JoystickStrokeBucketPosition,JoystickStrokeBucketStatus,textviewJoystickStrokeBoomUp);
			EPPRCurrentDisplay(EPPRCurrentBucketIn,textviewEPPRCurrentBoomUp);
			textviewTitleBoomUp.setText(getString(ParentActivity.getResources().getString(string.Bucket_In), 238));
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
			textviewTitleBoomDown.setText(getString(ParentActivity.getResources().getString(string.Boom_Down), 237));
			break;
		case STATE_BOOMDOWN:
			JoystickStrokeDownDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomDown);
			EPPRCurrentDisplay(EPPRCurrentBoomDown,textviewEPPRCurrentBoomDown);
			textviewTitleBoomDown.setText(getString(ParentActivity.getResources().getString(string.Boom_Down), 237));
			break;
		case STATE_BUCKETDUMP:
			JoystickStrokeDownDisplay(JoystickStrokeBucketPosition,JoystickStrokeBucketStatus,textviewJoystickStrokeBoomDown);
			EPPRCurrentDisplay(EPPRCurrentBucketDump,textviewEPPRCurrentBoomDown);
			textviewTitleBoomDown.setText(getString(ParentActivity.getResources().getString(string.Bucket_Dump), 239));
			break;
		default:
			break;
		}
	}
	public void AdjustDisplay(int Index){
		switch (Index) {
		case STATE_BOOMLEVER:
			BoomLeverFloatingPositionDisplay(BoomLeverFloatingPosition);
			textViewBoomLeverFloatingPositionUnit.setText(getString(ParentActivity.getResources().getString(string.Percent), 68));
			break;
		case STATE_BOOMDOWN:
			EPPRCurrentDisplay(BoomDownEPPRValveMaxCurrent,textViewBoomLeverFloatingPosition);
			textViewBoomLeverFloatingPositionUnit.setText(getString(ParentActivity.getResources().getString(string.mA), 69) );
			break;
		case STATE_BUCKETDUMP:
			EPPRCurrentDisplay(BucketOutEPPRValveMaxCurrent,textViewBoomLeverFloatingPosition);
			textViewBoomLeverFloatingPositionUnit.setText(getString(ParentActivity.getResources().getString(string.mA), 69));
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
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
		case 4:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
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
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
			ClickOK();
			break;
		case 4:
		case 5:
		case 6:
			if(SelectIndex == STATE_BOOMLEVER){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}else if(SelectIndex == STATE_BOOMDOWN){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(SelectIndex == STATE_BUCKETDUMP){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}
		
			break;
		default:
			
			break;
		}
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickBoomLever();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			ClickBoomDown();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			ClickBucketDump();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			ClickMinus();
			break;
		case 5:
			ClickPlus();
			break;
		case 6:
			ClickOK();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnBoomLeverFloatingPositionUp.setPressed(false);
		imgbtnBoomLeverFloatingPositionDown.setPressed(false);
		radioBoomLever.setPressed(false);
		radioBoomDown.setPressed(false);
		radioBucketDump.setPressed(false);


		switch (CursurIndex) {
			case 1:
				radioBoomLever.setPressed(true);
				break;
			case 2:
				radioBoomDown.setPressed(true);
				break;
			case 3:
				radioBucketDump.setPressed(true);
				break;
			case 4:
				imgbtnBoomLeverFloatingPositionDown.setPressed(true);
				break;
			case 5:
				imgbtnBoomLeverFloatingPositionUp.setPressed(true);
				break;
			case 6:
				imgbtnOK.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
