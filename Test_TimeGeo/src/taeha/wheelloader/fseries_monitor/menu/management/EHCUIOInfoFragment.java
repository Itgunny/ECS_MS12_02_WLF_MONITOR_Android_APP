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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
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
import android.widget.TextView;

public class EHCUIOInfoFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextFitTextView	textViewOK;

	TextFitTextView textviewOperation;
	TextFitTextView textviewJoystickStroke;
	TextFitTextView textviewEPPRCurrent;
	
	TextFitTextView textviewBoomUpTitle;
	TextFitTextView textviewBoomDownTitle;
	TextFitTextView textviewBucketInTitle;
	TextFitTextView textviewBucketDumpTitle;
	TextFitTextView textviewAuxUpTitle;
	TextFitTextView textviewAuxDownTitle;
	
	TextFitTextView textviewJoystickStrokeBoomUp;
	TextFitTextView textviewJoystickStrokeBoomDown;
	TextFitTextView textviewJoystickStrokeBucketIn;
	TextFitTextView textviewJoystickStrokeBucketDump;
	TextFitTextView textviewJoystickStrokeAuxUp;
	TextFitTextView textviewJoystickStrokeAuxDown;
	
	TextFitTextView textviewJoystickStrokeBoomUpUnit;
	TextFitTextView textviewJoystickStrokeBoomDownUnit;
	TextFitTextView textviewJoystickStrokeBucketInUnit;
	TextFitTextView textviewJoystickStrokeBucketDumpUnit;
	TextFitTextView textviewJoystickStrokeAuxUpUnit;
	TextFitTextView textviewJoystickStrokeAuxDownUnit;
	
	TextFitTextView textviewEPPRCurrentBoomUp;
	TextFitTextView textviewEPPRCurrentBoomDown;
	TextFitTextView textviewEPPRCurrentBucketIn;
	TextFitTextView textviewEPPRCurrentBucketDump;
	TextFitTextView textviewEPPRCurrentAuxUp;
	TextFitTextView textviewEPPRCurrentAuxDown;
	
	TextFitTextView textviewEPPRCurrentBoomUpUnit;
	TextFitTextView textviewEPPRCurrentBoomDownUnit;
	TextFitTextView textviewEPPRCurrentBucketInUnit;
	TextFitTextView textviewEPPRCurrentBucketDumpUnit;
	TextFitTextView textviewEPPRCurrentAuxUpUnit;
	TextFitTextView textviewEPPRCurrentAuxDownUnit;
	
	TextFitTextView textviewBoomAngleTitle;
	TextFitTextView textviewBoomAngle;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int JoystickStrokeBoomPosition;
	int JoystickStrokeBucketPosition;
	int JoystickStrokeAuxPosition;

	int JoystickStrokeBoomStatus;
	int JoystickStrokeBucketStatus;
	int JoystickStrokeAuxStatus;
	
	int EPPRCurrentBoomUp;
	int EPPRCurrentBoomDown;
	int EPPRCurrentBucketIn;
	int EPPRCurrentBucketDump;
	int EPPRCurrentAuxUp;
	int EPPRCurrentAuxDown;
	
	int BoomAngle;
	
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
		 TAG = "EHCUIOInfoFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_ehcuioinfo, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.EHCU_IO_Information), 256);
		
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_ehcuioinfo_low_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));

		textviewOperation = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_operation_title);
		textviewOperation.setText(getString(ParentActivity.getResources().getString(R.string.Operation), 258));
		textviewJoystickStroke = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_joystickstroke_title);
		textviewJoystickStroke.setText(getString(ParentActivity.getResources().getString(R.string.Joystick_Stroke), 259));
		textviewEPPRCurrent  = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_epprcurrent_title);
		textviewEPPRCurrent.setText(getString(ParentActivity.getResources().getString(R.string.EPPR_Current), 260));
		
		textviewBoomUpTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_operation);
		textviewBoomUpTitle.setText(getString(ParentActivity.getResources().getString(R.string.Boom_Up), 236));
		textviewBoomDownTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_operation);
		textviewBoomDownTitle.setText(getString(ParentActivity.getResources().getString(R.string.Boom_Down), 237));
		textviewBucketInTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_operation);
		textviewBucketInTitle.setText(getString(ParentActivity.getResources().getString(R.string.Bucket_In), 238));
		textviewBucketDumpTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_operation);
		textviewBucketDumpTitle.setText(getString(ParentActivity.getResources().getString(R.string.Bucket_Dump), 239));
		textviewAuxUpTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_operation);
		textviewAuxUpTitle.setText(getString(ParentActivity.getResources().getString(R.string.Aux_Up), 261));
		textviewAuxDownTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_operation);
		textviewAuxDownTitle.setText(getString(ParentActivity.getResources().getString(R.string.Aux_Down), 262));
		
		textviewJoystickStrokeBoomUp = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_joystickstroke_data);
		textviewJoystickStrokeBoomDown = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_joystickstroke_data);
		textviewJoystickStrokeBucketIn = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_joystickstroke_data);
		textviewJoystickStrokeBucketDump = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_joystickstroke_data);
		textviewJoystickStrokeAuxUp = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_joystickstroke_data);
		textviewJoystickStrokeAuxDown = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_joystickstroke_data);
		
		textviewJoystickStrokeBoomUpUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_joystickstroke_unit);
		textviewJoystickStrokeBoomUpUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		textviewJoystickStrokeBoomDownUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_joystickstroke_unit);
		textviewJoystickStrokeBoomDownUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		textviewJoystickStrokeBucketInUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_joystickstroke_unit);
		textviewJoystickStrokeBucketInUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		textviewJoystickStrokeBucketDumpUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_joystickstroke_unit);
		textviewJoystickStrokeBucketDumpUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		textviewJoystickStrokeAuxUpUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_joystickstroke_unit);
		textviewJoystickStrokeAuxUpUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		textviewJoystickStrokeAuxDownUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_joystickstroke_unit);
		textviewJoystickStrokeAuxDownUnit.setText(getString(ParentActivity.getResources().getString(R.string.Percent), 68));
		
		textviewEPPRCurrentBoomUp = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_epprcurrent_data);
		textviewEPPRCurrentBoomDown = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_epprcurrent_data);
		textviewEPPRCurrentBucketIn = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_epprcurrent_data);
		textviewEPPRCurrentBucketDump = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_epprcurrent_data);
		textviewEPPRCurrentAuxUp = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_epprcurrent_data);
		textviewEPPRCurrentAuxDown = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_epprcurrent_data);
		
		textviewEPPRCurrentBoomUpUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_epprcurrent_unit);
		textviewEPPRCurrentBoomUpUnit.setText(getString(ParentActivity.getResources().getString(R.string.mA), 69));
		textviewEPPRCurrentBoomDownUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_epprcurrent_unit);
		textviewEPPRCurrentBoomDownUnit.setText(getString(ParentActivity.getResources().getString(R.string.mA), 69));
		textviewEPPRCurrentBucketInUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_epprcurrent_unit);
		textviewEPPRCurrentBucketInUnit.setText(getString(ParentActivity.getResources().getString(R.string.mA), 69));
		textviewEPPRCurrentBucketDumpUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_epprcurrent_unit);
		textviewEPPRCurrentBucketDumpUnit.setText(getString(ParentActivity.getResources().getString(R.string.mA), 69));
		textviewEPPRCurrentAuxUpUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_epprcurrent_unit);
		textviewEPPRCurrentAuxUpUnit.setText(getString(ParentActivity.getResources().getString(R.string.mA), 69));
		textviewEPPRCurrentAuxDownUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_epprcurrent_unit);
		textviewEPPRCurrentAuxDownUnit.setText(getString(ParentActivity.getResources().getString(R.string.mA), 69));
		
		textviewBoomAngleTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_low_boom_title);
		textviewBoomAngleTitle.setText(getString(ParentActivity.getResources().getString(R.string.Boom), 28));
		textviewBoomAngle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_low_boom_data);
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
				ClickOK();
			}
		});
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		 JoystickStrokeBoomStatus = CAN1Comm.Get_BoomJoystickPositionStatus_2310_PGN65515();
		 JoystickStrokeBucketStatus = CAN1Comm.Get_BucketJoystickPositionStatus_2312_PGN65515();
		 JoystickStrokeAuxStatus = CAN1Comm.Get_AuxJoystickPositionStatus_2314_PGN65515();
		 
		 JoystickStrokeBoomPosition = CAN1Comm.Get_BoomJoystickPosition_2311_PGN65515();
		 JoystickStrokeBucketPosition = CAN1Comm.Get_BucketJoystickPosition_2313_PGN65515();
		 JoystickStrokeAuxPosition= CAN1Comm.Get_AuxJoystickPosition_2315_PGN65515();
	  
		 EPPRCurrentBoomUp = CAN1Comm.Get_BoomUpEPPRValveCurrent_2304_PGN65517();
		 EPPRCurrentBoomDown = CAN1Comm.Get_BoomDownEPPRValveCurrent_2305_PGN65517();
		 EPPRCurrentBucketIn = CAN1Comm.Get_BucketInEPPRValveCurrent_2306_PGN65517();
		 EPPRCurrentBucketDump = CAN1Comm.Get_BucketOutEPPRValveCurrent_2307_PGN65517();
		 EPPRCurrentAuxUp = CAN1Comm.Get_AUX1EPPRValveCurrent_2308_PGN65517();
		 EPPRCurrentAuxDown = CAN1Comm.Get_AUX2EPPRValveCurrent_2309_PGN65517();
		 
		 BoomAngle = CAN1Comm.Get_BoomLinkAngle_1920_PGN65395();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		JoystickStrokeUpDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomUp);
		JoystickStrokeDownDisplay(JoystickStrokeBoomPosition,JoystickStrokeBoomStatus,textviewJoystickStrokeBoomDown);
		JoystickStrokeUpDisplay(JoystickStrokeBucketPosition,JoystickStrokeBucketStatus,textviewJoystickStrokeBucketIn);
		JoystickStrokeDownDisplay(JoystickStrokeBucketPosition,JoystickStrokeBucketStatus,textviewJoystickStrokeBucketDump);
		JoystickStrokeUpDisplay(JoystickStrokeAuxPosition,JoystickStrokeAuxStatus,textviewJoystickStrokeAuxUp);
		JoystickStrokeDownDisplay(JoystickStrokeAuxPosition,JoystickStrokeAuxStatus,textviewJoystickStrokeAuxDown);
		
		EPPRCurrentDisplay(EPPRCurrentBoomUp,textviewEPPRCurrentBoomUp);
		EPPRCurrentDisplay(EPPRCurrentBoomDown,textviewEPPRCurrentBoomDown);
		EPPRCurrentDisplay(EPPRCurrentBucketIn,textviewEPPRCurrentBucketIn);
		EPPRCurrentDisplay(EPPRCurrentBucketDump,textviewEPPRCurrentBucketDump);
		EPPRCurrentDisplay(EPPRCurrentAuxUp,textviewEPPRCurrentAuxUp);
		EPPRCurrentDisplay(EPPRCurrentAuxDown,textviewEPPRCurrentAuxDown);
		
		BoomBucketAngleCurrAngle(BoomAngle);
	}
	/////////////////////////////////////////////////////////////////////
//	public void AngleDisplay(int Angle, int AngleDot){
//		textviewBoomAngle.setText(Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "им");
//	}
	public void AngleDisplay(int Sign, int Angle, int AngleDot){
		if(Sign == 1)
			textviewBoomAngle.setText("-" + Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "им");
		else
			textviewBoomAngle.setText(Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "им");
	}
	public void BoomBucketAngleCurrAngle(int _BoomAngle){
		int BoomAngleDot = 0;
		int BoomSign = 0;

		if( _BoomAngle == 0xffff )
			_BoomAngle = 1800;

		if( _BoomAngle == 1800 )		// 0
		{
			_BoomAngle = 0;
			BoomAngleDot = 0;
		}
		else if( _BoomAngle > 1800 )	// +
		{
			_BoomAngle -= 1800;
			BoomAngleDot = _BoomAngle % 10;
			_BoomAngle /= 10;	
		}
		else						// -
		{
			_BoomAngle = 1800 - _BoomAngle;
			BoomAngleDot = _BoomAngle % 10;
			_BoomAngle /= 10;
			//_BoomAngle *= -1;
			BoomSign = 1;
		}
		
		AngleDisplay(BoomSign,_BoomAngle,BoomAngleDot);

	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		// ++, 150325 bwk
//		ParentActivity._MenuBaseFragment.showBodyMonitoringAnimation();
//		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
		// --, 150325 bwk
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
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){

	}
	public void ClickRight(){	

	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		ClickOK();
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			break;
		
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
