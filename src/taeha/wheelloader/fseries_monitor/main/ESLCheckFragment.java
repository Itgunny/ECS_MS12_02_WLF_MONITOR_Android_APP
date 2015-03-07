package taeha.wheelloader.fseries_monitor.main;


import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;

public class ESLCheckFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView imgViewSMK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	Timer mESLCheckTimer = null;
	Timer mTimeOutTimer = null;
	Timer mSMKCheckTimer = null;
	Timer mSeedCheckTimer = null;
	Timer mPasswordCheckTimer = null;
	
	
	int SmartKeyUse;
	int SmartKeyAuth;
	int RecESLFlag;
	int RecSMKFlag;
	int RecPWFlag;
	int TimeoutIndex;
	
	// Handler
	Handler HandleSMKImgChange;
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
		TAG = "ESLCheckFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.main_esl_check, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CheckSMK();
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_SMK,CAN1CommManager.DATA_INDEX_TAG_REQ_SUCCESS);
		StartSMKCheckTimer();
		StartTimeOutTimer();

		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_ESL_CHECK_TOP;
		
		HandleSMKImgChange = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					imgViewSMK.setBackgroundResource(R.drawable.intro_smk_green);
					break;
				case 1:
					showESLScreen();
					break;
				case 2:
					showMainScreen();
					break;
				default:
					break;
				}
			}
		};

		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d(TAG,"onDestroy()");
		CancelESLCheckTimer();
		CancelPasswordCheckTimer();
		CancelSeedCheckTimer();
		CancelSMKCheckTimer();
		CancelTimeOutTimer();
		
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		imgViewSMK = (ImageView)mRoot.findViewById(R.id.imageView_main_esl_check);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		RecESLFlag = 0;
		TimeoutIndex = 0;
		SmartKeyUse = ParentActivity.SmartKeyUse;

	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
	
	}
	/////////////////////////////////////////////////////////////////////
	public void CheckSMK(){
		if(SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON){
			imgViewSMK.setVisibility(View.VISIBLE);
		}else{
			imgViewSMK.setVisibility(View.INVISIBLE);
		}
	}
	// ESL
	public void ESLCheck(){
		int EngineStartPermission, LockLevel;
		
		EngineStartPermission = CAN1Comm.Get_EngineStartingPermission_821_PGN65348();
		LockLevel = CAN1Comm.Get_LockLevel_823_PGN65348();
		Log.d(TAG,"EngineStartPermission : " + Integer.toString(EngineStartPermission));
		Log.d(TAG,"LockLevel : " + Integer.toString(LockLevel));
		if(EngineStartPermission == 0 || LockLevel == 1)
		{
		//	showESLScreen();
			HandleSMKImgChange.sendMessage(HandleSMKImgChange.obtainMessage(1));
		}
		else
		{
		//	showMainScreen();
			HandleSMKImgChange.sendMessage(HandleSMKImgChange.obtainMessage(2));
			
		}
		Log.d(TAG,"EngineStartPermission : " + Integer.toString(EngineStartPermission)
				+ "  LockLevel : " + Integer.toString(LockLevel));
				
	}
	public void CheckPassword(){
		int Result;
		Result = CAN1Comm.Get_PasswordCertificationResult_956_PGN61184_24();
		Log.d(TAG,"CheckPassword Result : " + Integer.toString(Result));	
		//if(Result == 1)	// UserPassword
		//	Result = 0;
		
		switch (Result) {
		case 0:			// Not OK
		case 13:		// TimeOut
			//showESLScreen();
			HandleSMKImgChange.sendMessage(HandleSMKImgChange.obtainMessage(1));

			break;
		case 1:	// UserPassword	OK
		case 2:	// Service Password OK
			//showMainScreen();
			HandleSMKImgChange.sendMessage(HandleSMKImgChange.obtainMessage(2));
			
			Log.d(TAG,"Password OK : " + Integer.toString(Result));
			break;
		
		case 5:	// MLC Password OK
		case 10:	// Master Password OK
		case 15:
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////

	public void showESLScreen(){
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._ESLPasswordFragment);
	}
	public void showMainScreen(){
	//	if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_A){
	//		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment);
	//	}else{
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
	//	}
	}
	/////////////////////////////////////////////////////////////////////
	public void StartESLCheckTimer(){
		TimeoutIndex = 1;
		CancelESLCheckTimer();
		mESLCheckTimer = new Timer();
		mESLCheckTimer.schedule(new ESLCheckTimerClass(),1,100);	
	}
	
	public void CancelESLCheckTimer(){
		if(mESLCheckTimer != null){
			mESLCheckTimer.cancel();
			mESLCheckTimer.purge();
			mESLCheckTimer = null;
		}
	}
	
	
	public class ESLCheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG,"mESLCheckTimerTask");	
			RecESLFlag = CAN1Comm.Get_RecvESL_Flag();
			if(RecESLFlag == 1){
				CancelESLCheckTimer();
				CancelSMKCheckTimer();
				CancelTimeOutTimer();
				ESLCheck();
			}
		}
	}
	
	public void StartSMKCheckTimer(){
		TimeoutIndex = 0;
		CancelSMKCheckTimer();
		mSMKCheckTimer = new Timer();
		mSMKCheckTimer.schedule(new SMKCheckTimerClass(),1,100);
		
	}
	
	public void CancelSMKCheckTimer(){
		if(mSMKCheckTimer != null){
			mSMKCheckTimer.cancel();
			mSMKCheckTimer.purge();
			mSMKCheckTimer = null;
		}
	}
	
	
	public class SMKCheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG,"mSMKCheckTimerTask");	
			
			////////////////////////////스마트 키////////////////////////////////////////////////////////			
				
			if (SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON) {
				RecSMKFlag = CAN1Comm.Get_RecvSMK_Flag();

				if (RecSMKFlag != 0) {
					SmartKeyAuth = CAN1Comm.Get_SmkAuthResult();
					if (SmartKeyAuth == 0xA5) { // Success
						//HandleSMKImgChange.sendMessage(HandleSMKImgChange.obtainMessage(0));	// ++, --, 150306 bwk
						CancelSMKCheckTimer();
						StartESLCheckTimer();
						//CancelSMKCheckTimer();	// ++, --, 150306 bwk
					} else if (SmartKeyAuth == 0xFE) { // Fail
						CancelSMKCheckTimer();
						StartESLCheckTimer();
					} else {
						CancelSMKCheckTimer();
						StartESLCheckTimer();
					}
					Log.d(TAG,"SmartKeyAuth : 0x" + Integer.toHexString(SmartKeyAuth));
				} else {
					
				}
			} else {
				
				StartESLCheckTimer();
				CancelSMKCheckTimer();
			}
			//
			//////////////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	
	
	public void StartTimeOutTimer(){
		CancelTimeOutTimer();
		mTimeOutTimer = new Timer();
		mTimeOutTimer.schedule(new TimeOutTimerClass(),3000);	
	}
	
	public void CancelTimeOutTimer(){
		if(mTimeOutTimer != null){
			mTimeOutTimer.cancel();
			mTimeOutTimer.purge();
			mTimeOutTimer = null;
		}
	}
	
	
	public class TimeOutTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG,"mTimeOutTimerTask");	
			CancelESLCheckTimer();
			CancelSMKCheckTimer();
			CancelTimeOutTimer();
			CancelSeedCheckTimer();
			CancelPasswordCheckTimer();
			if(TimeoutIndex == 0){
			//	showESLScreen();
				HandleSMKImgChange.sendMessage(HandleSMKImgChange.obtainMessage(1));
			}else if(TimeoutIndex == 1){
				ESLCheck();
			}
			
			
			
		}
		
	}
	
	public void StartPasswordCheckTimer(int Delay, int Interval){
		CancelPasswordCheckTimer();
		mPasswordCheckTimer = new Timer();
		mPasswordCheckTimer.schedule(new PasswordCheckTimerClass(),Delay,Interval);	
		
	}
	
	public void CancelPasswordCheckTimer(){
		if(mPasswordCheckTimer != null){
			mPasswordCheckTimer.cancel();
			mPasswordCheckTimer.purge();
			mPasswordCheckTimer = null;
		}
	}
	
	public class PasswordCheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(CAN1Comm.Get_RecvPasswordResultFlag_PGN61184_24() == 1){
				CheckPassword();
			}
				
		}
		
	}
	
	
	public void StartSeedCheckTimer(int Delay, int Interval){
		CancelSeedCheckTimer();
		mSeedCheckTimer = new Timer();
		mSeedCheckTimer.schedule(new SeedCheckTimerClass(),Delay,Interval);	
		
	}
	
	public void CancelSeedCheckTimer(){
		if(mSeedCheckTimer != null){
			mSeedCheckTimer.cancel();
			mSeedCheckTimer.purge();
			mSeedCheckTimer = null;
		}
	}
	
	public class SeedCheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG,"SeedCheckTimerClass");
			if(CAN1Comm.Get_RecvSeedFlag_PGN61184_22() == 1)
			{
				Log.d(TAG,"GET SEED");
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_TEMPORARY_PASSWORD_IDENTIFICATION_REQUEST);
				CAN1Comm.TxCANToMCU(23);
				
				CancelSeedCheckTimer();
				StartPasswordCheckTimer(1,100);
			}

		}
		
	}
	/////////////////////////////////////////////////////////////////////
}