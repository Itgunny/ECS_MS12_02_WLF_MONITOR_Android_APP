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

	TextView textviewJoystickStrokeBoomUp;
	TextView textviewJoystickStrokeBoomDown;
	TextView textviewJoystickStrokeBucketIn;
	TextView textviewJoystickStrokeBucketDump;
	TextView textviewJoystickStrokeAuxUp;
	TextView textviewJoystickStrokeAuxDown;
	
	TextView textviewEPPRCurrentBoomUp;
	TextView textviewEPPRCurrentBoomDown;
	TextView textviewEPPRCurrentBucketIn;
	TextView textviewEPPRCurrentBucketDump;
	TextView textviewEPPRCurrentAuxUp;
	TextView textviewEPPRCurrentAuxDown;
	
	TextView textviewBoomAngle;
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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.EHCU_IO_Information));
		
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

		textviewJoystickStrokeBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_joystickstroke_data);
		textviewJoystickStrokeBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_joystickstroke_data);
		textviewJoystickStrokeBucketIn = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_joystickstroke_data);
		textviewJoystickStrokeBucketDump = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_joystickstroke_data);
		textviewJoystickStrokeAuxUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_joystickstroke_data);
		textviewJoystickStrokeAuxDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_joystickstroke_data);
		
		textviewEPPRCurrentBoomUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomup_epprcurrent_data);
		textviewEPPRCurrentBoomDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_boomdown_epprcurrent_data);
		textviewEPPRCurrentBucketIn = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketin_epprcurrent_data);
		textviewEPPRCurrentBucketDump = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_bucketout_epprcurrent_data);
		textviewEPPRCurrentAuxUp = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxup_epprcurrent_data);
		textviewEPPRCurrentAuxDown = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_auxdown_epprcurrent_data);
		
		textviewBoomAngle = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_ehcuioinfo_low_boom_data);
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
	public void AngleDisplay(int Angle, int AngleDot){
		textviewBoomAngle.setText(Integer.toString(Angle) + "." + Integer.toString(AngleDot) + "им");
	}
	
	public void BoomBucketAngleCurrAngle(int _BoomAngle){
		int BoomAngleDot = 0;

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
			_BoomAngle *= -1;
		}
		
		AngleDisplay(_BoomAngle,BoomAngleDot);

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
