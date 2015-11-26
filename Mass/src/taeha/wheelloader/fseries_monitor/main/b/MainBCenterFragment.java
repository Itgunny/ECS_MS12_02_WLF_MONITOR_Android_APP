package taeha.wheelloader.fseries_monitor.main.b;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.BarAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.b.MainBUpperMenuBarFragment.BuzzerStopTimerClass;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBCenterFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewRPMData;
	TextFitTextView textViewRPMDataUnit;
	
	ImageView imgViewEcoIcon;
	ImageView imgViewEcoBG;
	ImageView imgViewEcoBar;
	ImageView imgViewSmkIcon;	// ++, --, 150326 bwk
	
	
	ImageButton imgbtnOption;
	
	RelativeLayout layoutEcoBar;
	
	RelativeLayout layoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	protected int RPM;
	
	// Eco Gauge
	private int EcoGaugeLevel;
	private int EcoGaugeStatus;
	
	int Maint;
	int FaultCode;
	
	private Timer mBuzzerStopTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	public BarAnimation				_EcoGaugeAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBCenterFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_b, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
//		Log.d(TAG,"10ScreenIndex="+Integer.toHexString(ParentActivity.ScreenIndex));
		
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG,"onResume");
		ClickFlag = true;
		setClickEnable(ClickFlag);
//		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
//		Log.d(TAG,"11ScreenIndex="+Integer.toHexString(ParentActivity.ScreenIndex));
		ParentActivity.CheckAttachmentUnlock();
		
		if(ParentActivity.AxleWarningFlag != true)
		{
			Log.d(TAG, "AxleInit ");
			ParentActivity.FrontAxleWarningFlag = false;
			ParentActivity.RearAxleWarningFlag = false;
		}	
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause");
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewRPMData = (TextFitTextView)mRoot.findViewById(R.id.textView_center_main_b_rpm);
		textViewRPMDataUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_center_main_b_rpm_unit);
		textViewRPMDataUnit.setText(getString(ParentActivity.getResources().getString(R.string.rpm), 34));
		imgViewEcoIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_eco_title);
		imgViewEcoBG = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_eco_bg);
		imgViewEcoBar = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_eco_bar);
		imgViewSmkIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_smkicon);		// ++, --, 150326 bwk

		
		imgbtnOption = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_b_option);
		
		layoutEcoBar = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_b_icon_eco_bar);
		
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_b_bg);

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		EcoGaugeLevel = CAN1Comm.Get_EcoGaugeLevel_1304_PGN65390();
		EcoGaugeStatus = CAN1Comm.Get_EcoGaugeStatus_1305_PGN65390();
		
		if (EcoGaugeLevel > 100) {
			EcoGaugeLevel = 100;
		} else if (EcoGaugeLevel < 0) {
			EcoGaugeLevel = 0;
		}
		
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
		
	//	_EcoGaugeAnimation = new BarAnimation(ParentActivity, imgViewEcoBar,EcoGaugeLevel);
		
	}
	boolean Temp = false;
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickBG();
				
			
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		EcoGaugeLevel = CAN1Comm.Get_EcoGaugeLevel_1304_PGN65390();
		EcoGaugeStatus = CAN1Comm.Get_EcoGaugeStatus_1305_PGN65390();
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RPMDisplay(RPM);
		EcoGaugeDisplay(EcoGaugeLevel,EcoGaugeStatus);
		IconDisplay(FaultCode,Maint);
		SmkIconDisplay(ParentActivity.SmartIconDisplay);	// ++, --, 150326 bwk
	}
	/////////////////////////////////////////////////////////////////////
	public void RPMDisplay(int Data){
		if(Data == 0xFFFF)
			Data = 0;
		else if(Data > 9999)
			Data = 9999;
		try {
			textViewRPMData.setText(Integer.toString(Data));
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
	}
	//--------------------------------------------------------------------------
	public class BuzzerStopTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			CAN1Comm.Set_RequestBuzzerStop_PGN65327(0);
			CAN1Comm.TxCANToMCU(47);
			
		}
		
	}
	public void StartBuzzerStopTimer(){
		CancelBuzzerStopTimer();
		mBuzzerStopTimer = new Timer();
		mBuzzerStopTimer.schedule(new BuzzerStopTimerClass(),1000);	
	}
	
	public void CancelBuzzerStopTimer(){
		if(mBuzzerStopTimer != null){
			mBuzzerStopTimer.cancel();
			mBuzzerStopTimer.purge();
			mBuzzerStopTimer = null;
		}
	}
	public void ClickBuzzer(){
		if(CAN1Comm.BuzzerStatus == CAN1CommManager.BUZZER_ON){
			CAN1Comm.Set_RequestBuzzerStop_PGN65327(1);
			CAN1Comm.TxCANToMCU(47);
			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_BUZ, CAN1CommManager.BUZZER_OFF);	// Buzzer Off
			CAN1Comm.BuzzerStatus = CAN1CommManager.BUZZER_STOP;
			ParentActivity.BuzzerStopCount = 0;
			StartBuzzerStopTimer();
		}
	}
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ClickBuzzer();
		ParentActivity._MainBBaseFragment.showQuickScreenAnimation();
		Log.d(TAG,"ClickOption");
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnOption.setClickable(ClickFlag);
	}
	//////////////////////////////////////////////////////////////////////
	public void EcoGaugeDisplay(int _level, int _status) {
		float Scale = 0;
		switch (_status) {
//			case CAN1CommManager.DATA_STATE_ECO_GREEN:
//			default:
//				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_all);
//				break;
//			//case CAN1CommManager.DATA_STATE_ECO_YELLOW:
//			//	imgViewEcoBar.setImageResource(R.drawable.main_center_eco_yellow_all);
//			//	break;
//			//case CAN1CommManager.DATA_STATE_ECO_RED:
//			//	imgViewEcoBar.setImageResource(R.drawable.main_center_eco_red_all);
//			//	break;
//			// ++, 150402 bwk
////			case CAN1CommManager.DATA_STATE_ECO_WHITE:
////				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_white_all);
////				break;
//			// --, 150402 bwk
			case CAN1CommManager.DATA_STATE_ECO_WHITE:
				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_all_white);
				break;
			default:
				imgViewEcoBar.setImageResource(R.drawable.main_center_eco_all_green);
				break;
		}
		
		if(_level == 0xFF){
			_level = 0;
		}
		else if (_level > 100) {
			_level = 100;
		} else if (_level < 0) {
			_level = 0;
		}
		Scale = (float) ((float) _level / 100.0);

		imgViewEcoBar.setLayoutParams(new RelativeLayout.LayoutParams((int)(ParentActivity.getResources().getDrawable(R.drawable.main_center_eco_all).getIntrinsicWidth() * Scale), 
				ParentActivity.getResources().getDrawable(R.drawable.main_center_eco_all).getIntrinsicHeight()));
	}
	public void IconDisplay(int _fault, int _maint){
		if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON && _maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_faultmaint_btn);
		}else if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_fault_btn);
		}else if(_maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_maint_btn);
		}else{
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_quick_btn);
		}
	}
	//////////////////////////////////////////////////////////////////////
	// ++, 150326 bwk
	public void SmkIconDisplay(boolean flag){
		try{
			if(flag == false)
				imgViewSmkIcon.setVisibility(View.INVISIBLE);
			else
				imgViewSmkIcon.setVisibility(View.VISIBLE);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
	}
	// --, 150326 bwk	
	public void CursurDisplayDetail(int index){
		layoutBG.setBackgroundResource(R.drawable.main_bg_center);
		switch(index){
			case 4:
				layoutBG.setBackgroundResource(R.drawable.main_bg_center_s_02);
				break;
		}		
	}
}
