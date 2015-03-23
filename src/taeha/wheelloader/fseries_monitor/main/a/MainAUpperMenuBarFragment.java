package taeha.wheelloader.fseries_monitor.main.a;

import java.util.Timer;
import java.util.TimerTask;

import actionpopup.ActionItem;
import actionpopup.QuickAction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.MainBBaseFragment;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainAUpperMenuBarFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	// Upper
	TextView textViewTimeAM;
	TextView textViewTimePM;
	TextView textViewTimeHour;
	TextView textViewTimeColon;
	TextView textViewTimeMin;
	
	ImageButton imgbtnTime;
	
	// Lower
	ImageView imgViewBuzzerNormal;
	ImageView imgViewBuzzer070Left;
	ImageView imgViewBuzzer070Right;
	
	ImageView imgViewWiperIcon;
	ImageView imgViewWiperLevel1;
	ImageView imgViewWiperLevel2;
	ImageView imgViewWiperLevel3;
	ImageView imgViewWiperLevel4;
	
	ImageView imgViewCamera;
	
	ImageView imgViewMenu;
	
	ImageButton imgbtnBuzzer;
	ImageButton imgbtnWiper;
	ImageButton imgbtnCamera;
	ImageButton imgbtnMenu;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	// Timer
	private Timer mClockColonTimer = null;
	private Timer mBuzzerStopTimer = null;
	
	boolean ColonFlag;
	
	int ClockHour;
	int ClockMin;
	
	int Warning;
	int CommErrCount;
	
	int WiperStatus;
	int WiperLevel;	
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
		TAG = "MainAUpperMenuBarFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.upper_main_a_menubar, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		CancelClockColonTimer();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StartClockColonTimer();
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTimeAM = (TextView)mRoot.findViewById(R.id.textView_upper_main_a_menubar_time_am);
		textViewTimePM = (TextView)mRoot.findViewById(R.id.textView_upper_main_a_menubar_time_pm);
		textViewTimeHour = (TextView)mRoot.findViewById(R.id.textView_upper_main_a_menubar_time_hour);
		textViewTimeColon = (TextView)mRoot.findViewById(R.id.textView_upper_main_a_menubar_time_colon);
		textViewTimeMin = (TextView)mRoot.findViewById(R.id.textView_upper_main_a_menubar_time_min);
		
		imgbtnTime = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_a_menubar_time);
		
		imgViewBuzzerNormal = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_buzzer_normal);
		imgViewBuzzer070Left = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_buzzer_070_left);
		imgViewBuzzer070Right = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_buzzer_070_right);
		
		imgViewWiperIcon = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_wiper_icon);
		imgViewWiperLevel1 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_wiper_level_1);
		imgViewWiperLevel2 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_wiper_level_2);
		imgViewWiperLevel3 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_wiper_level_3);
		imgViewWiperLevel4 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_wiper_level_4);
		
		imgViewCamera = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_camera);
		
		imgViewMenu = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_a_menubar_menu);
		
		imgbtnBuzzer = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_a_menubar_buzzer);
		imgbtnWiper = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_a_menubar_wiper);
		imgbtnCamera = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_a_menubar_camera);
		imgbtnMenu = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_a_menubar_menu);		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		ColonFlag = true;
		
	
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTime();
			}
		});
		imgbtnBuzzer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBuzzer();
			}
		});
		imgbtnWiper.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWiper();
			}
		});
		imgbtnCamera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCamera();
			}
		});
		imgbtnMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMenu();
			}
		});		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ClockHour = CAN1Comm.Get_RTColock_Hour();
		ClockMin = CAN1Comm.Get_RTColock_Min();
		
		Warning = CAN1Comm.BuzzerStatus;
		CommErrCount = CAN1Comm.Get_CommErrCnt();
		
		WiperStatus = CAN1Comm.Get_WiperOperationStatus_717_PGN65433();
		WiperLevel = CAN1Comm.Get_WiperSpeedLevel_718_PGN65433();		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		MainBtnDisplay();
		ClockDisplay(ClockHour, ClockMin);
		WarningDisplay(Warning,CommErrCount);
		WiperDisplay(WiperStatus,WiperLevel);
	}
	/////////////////////////////////////////////////////////////////////
	//Timer//////////////////////////////////////////////////////////////
	public class ClockColonTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ColonFlag == true){
						textViewTimeColon.setVisibility(View.INVISIBLE);
						ColonFlag = false;
					}else{
						textViewTimeColon.setVisibility(View.VISIBLE);
						ColonFlag = true;
					}
					
				}
			});
			
		}
		
	}
	
	public void StartClockColonTimer(){
		CancelClockColonTimer();
		mClockColonTimer = new Timer();
		mClockColonTimer.schedule(new ClockColonTimerClass(),1,500);	
	}
	
	public void CancelClockColonTimer(){
		if(mClockColonTimer != null){
			mClockColonTimer.cancel();
			mClockColonTimer.purge();
			mClockColonTimer = null;
		}
		
	}
	
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
	/////////////////////////////////////////////////////////////////////
	public void MainBtnDisplay(){
		try {
			if(ParentActivity.ScreenIndex == ParentActivity.SCREEN_STATE_MAIN_A_TOP)
			{
				imgbtnBuzzer.setBackgroundResource(R.drawable._selector_upper_main_a_buzzer_btn1);
				imgbtnWiper.setBackgroundResource(R.drawable._selector_lower_main_a_menubar_btn1);
				imgbtnCamera.setBackgroundResource(R.drawable._selector_lower_main_a_menubar_btn1);
				imgbtnMenu.setBackgroundResource(R.drawable._selector_lower_main_a_menubar_btn1);		
			}
			else
			{
				imgbtnBuzzer.setBackgroundResource(R.drawable._selector_upper_main_a_buzzer_btn2);
				imgbtnWiper.setBackgroundResource(R.drawable._selector_lower_main_a_menubar_btn2);
				imgbtnCamera.setBackgroundResource(R.drawable._selector_lower_main_a_menubar_btn2);
				imgbtnMenu.setBackgroundResource(R.drawable._selector_lower_main_a_menubar_btn2);		
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
			
	}
	public void ClockDisplay(int Hour, int Min){
		if(ParentActivity.GetAMPM(Hour) == ParentActivity.CLOCK_AM){
			textViewTimeAM.setVisibility(View.VISIBLE);
			textViewTimePM.setVisibility(View.INVISIBLE);
		}else{
			textViewTimeAM.setVisibility(View.INVISIBLE);
			textViewTimePM.setVisibility(View.VISIBLE);
		}
		textViewTimeHour.setText(ParentActivity.GetHour(Hour));
		textViewTimeMin.setText(ParentActivity.GetMin(Min));
	}

	public void ClickTime(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP);
	}

	public void WarningDisplay(int Data, int CommErr){
		if(CommErr >= 1000){
			imgViewBuzzer070Left.setVisibility(View.VISIBLE);
			imgViewBuzzer070Right.setVisibility(View.VISIBLE);
			imgViewBuzzerNormal.setVisibility(View.INVISIBLE);
			imgViewBuzzer070Right.setImageResource(R.drawable.main_a_fault_070);
			switch (Data) {
			case CAN1CommManager.BUZZER_OFF:
			default:
				imgViewBuzzer070Left.setImageResource(R.drawable.main_a_menubar_icon_buzzer_on);
				break;
			case CAN1CommManager.BUZZER_ON:
				imgViewBuzzer070Left.setImageResource(R.drawable.main_a_menubar_icon_buzzer_on_red);
				break;
			case CAN1CommManager.BUZZER_STOP:
				imgViewBuzzer070Left.setImageResource(R.drawable.main_a_menubar_icon_buzzer_on);
				break;
			}
		}else{
			imgViewBuzzer070Left.setVisibility(View.INVISIBLE);
			imgViewBuzzer070Right.setVisibility(View.INVISIBLE);
			imgViewBuzzerNormal.setVisibility(View.VISIBLE);
			switch (Data) {
			case CAN1CommManager.BUZZER_OFF:
			default:
				imgViewBuzzerNormal.setImageResource(R.drawable.main_a_menubar_icon_buzzer_on);
				break;
			case CAN1CommManager.BUZZER_ON:
				imgViewBuzzerNormal.setImageResource(R.drawable.main_a_menubar_icon_buzzer_on_red);
				break;
			case CAN1CommManager.BUZZER_STOP:
				imgViewBuzzerNormal.setImageResource(R.drawable.main_a_menubar_icon_buzzer_on);
				break;

			}
		}
		
	}	
	
	public void WiperDisplay(int Status, int Level){
		if(Status == CAN1CommManager.DATA_STATE_WIPER_OFF){
			imgViewWiperIcon.setImageResource(R.drawable.main_menubar_icon_wiper_off);
			imgViewWiperLevel1.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
			imgViewWiperLevel2.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
			imgViewWiperLevel3.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
			imgViewWiperLevel4.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
			
			imgViewWiperIcon.setAlpha((float)0.1);
			imgViewWiperLevel1.setAlpha((float)0.1);
			imgViewWiperLevel2.setAlpha((float)0.1);
			imgViewWiperLevel3.setAlpha((float)0.1);
			imgViewWiperLevel4.setAlpha((float)0.1);
			
			setWiperButtonEnable(false);
		}else{
			if(Level == 1){
				imgViewWiperIcon.setImageResource(R.drawable.main_menubar_icon_wiper_on);
				imgViewWiperLevel1.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel2.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
				imgViewWiperLevel3.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
				imgViewWiperLevel4.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
				
				imgViewWiperIcon.setAlpha((float)1);
				imgViewWiperLevel1.setAlpha((float)1);
				imgViewWiperLevel2.setAlpha((float)0.1);
				imgViewWiperLevel3.setAlpha((float)0.1);
				imgViewWiperLevel4.setAlpha((float)0.1);
			}
			else if(Level == 2){
				imgViewWiperIcon.setImageResource(R.drawable.main_menubar_icon_wiper_on);
				imgViewWiperLevel1.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel2.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel3.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
				imgViewWiperLevel4.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
				
				imgViewWiperIcon.setAlpha((float)1);
				imgViewWiperLevel1.setAlpha((float)1);
				imgViewWiperLevel2.setAlpha((float)1);
				imgViewWiperLevel3.setAlpha((float)0.1);
				imgViewWiperLevel4.setAlpha((float)0.1);
			}
			else if(Level == 3){
				imgViewWiperIcon.setImageResource(R.drawable.main_menubar_icon_wiper_on);
				imgViewWiperLevel1.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel2.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel3.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel4.setImageResource(R.drawable.main_menubar_icon_wiper_step_off);
				
				imgViewWiperIcon.setAlpha((float)1);
				imgViewWiperLevel1.setAlpha((float)1);
				imgViewWiperLevel2.setAlpha((float)1);
				imgViewWiperLevel3.setAlpha((float)1);
				imgViewWiperLevel4.setAlpha((float)0.1);
			}
			else if(Level == 4){
				imgViewWiperIcon.setImageResource(R.drawable.main_menubar_icon_wiper_on);
				imgViewWiperLevel1.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel2.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel3.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				imgViewWiperLevel4.setImageResource(R.drawable.main_menubar_icon_wiper_step_on);
				
				imgViewWiperIcon.setAlpha((float)1);
				imgViewWiperLevel1.setAlpha((float)1);
				imgViewWiperLevel2.setAlpha((float)1);
				imgViewWiperLevel3.setAlpha((float)1);
				imgViewWiperLevel4.setAlpha((float)1);
			}
			setWiperButtonEnable(true);
		}
	}
	
	public void ClickBuzzer(){
		if(CAN1Comm.BuzzerStatus == CAN1Comm.BUZZER_ON){
			CAN1Comm.Set_RequestBuzzerStop_PGN65327(1);
			CAN1Comm.TxCANToMCU(47);
			CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_OFF);	// Buzzer Off
			CAN1Comm.BuzzerStatus = CAN1Comm.BUZZER_STOP;
			ParentActivity.BuzzerStopCount = 0;
			StartBuzzerStopTimer();
		}
	}
	public void ClickWiper(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP);
	}
	public void ClickCamera(){
		ParentActivity.ExcuteCamActivitybyKey();
	}
	public void ClickMenu(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
	}
	///////////////////////////////////////////////////////////
	public void setWiperButtonEnable(boolean flag){
		imgbtnWiper.setClickable(flag);
	}
	///////////////////////////////////////////////////////////
}	