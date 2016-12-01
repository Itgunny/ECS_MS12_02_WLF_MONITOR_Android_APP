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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MachineSecurityESLFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	TextFitTextView 	textViewOK;
	TextFitTextView		textViewCancel;

	RadioButton radioDisable;
	RadioButton radioOnAlways;
	RadioButton radioOnAfterTime;
	
	RadioButton radio5Min;
	RadioButton radio10Min;
	RadioButton radio20Min;
	RadioButton radio30Min;
	RadioButton radio1Hour;
	RadioButton radio2Hour;
	RadioButton radio4Hour;
	RadioButton radio1Day;
	RadioButton radio2Day;
	
	RelativeLayout layoutTime;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int ESLInterval;
	int ESLMode;
	
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
		 TAG = "MachineSecurityESLFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_esl, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.ESL_System_Setting), 324);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ESLTimeDisplayOnOff(ESLMode);
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_esl_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_esl_low_cancel);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_esl_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_esl_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
	
		radioDisable = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_use_on_disalbe);
		radioDisable.setText(getString(ParentActivity.getResources().getString(R.string.Disable), 21));
		ParentActivity.setMarqueeRadio(radioDisable);
		radioOnAlways = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_use_on_always);
		radioOnAlways.setText(getString(ParentActivity.getResources().getString(R.string.On_Always), 23));
		ParentActivity.setMarqueeRadio(radioOnAlways);
		radioOnAfterTime = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_use_on_time);
		radioOnAfterTime.setText(getString(ParentActivity.getResources().getString(R.string.On_After_Specific_Time), 327));
		ParentActivity.setMarqueeRadio(radioOnAfterTime);
		
		radio5Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_5min);
		radio5Min.setText(getString(ParentActivity.getResources().getString(R.string._5Min), 328));
		ParentActivity.setMarqueeRadio(radio5Min);
		radio10Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_10min);
		radio10Min.setText(getString(ParentActivity.getResources().getString(R.string._10Min), 329));
		ParentActivity.setMarqueeRadio(radio10Min);
		radio20Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_20min);
		radio20Min.setText(getString(ParentActivity.getResources().getString(R.string._20Min), 330));
		ParentActivity.setMarqueeRadio(radio20Min);
		radio30Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_30min);
		radio30Min.setText(getString(ParentActivity.getResources().getString(R.string._30Min), 331));
		ParentActivity.setMarqueeRadio(radio30Min);
		radio1Hour = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_1hour);
		radio1Hour.setText(getString(ParentActivity.getResources().getString(R.string._1Hour), 332));
		ParentActivity.setMarqueeRadio(radio1Hour);
		radio2Hour = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_2hour);
		radio2Hour.setText(getString(ParentActivity.getResources().getString(R.string._2Hour), 333));
		ParentActivity.setMarqueeRadio(radio2Hour);
		radio4Hour = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_4hour);
		radio4Hour.setText(getString(ParentActivity.getResources().getString(R.string._4Hour), 334));
		ParentActivity.setMarqueeRadio(radio4Hour);
		radio1Day = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_1day);
		radio1Day.setText(getString(ParentActivity.getResources().getString(R.string._1Day), 335));
		ParentActivity.setMarqueeRadio(radio1Day);
		radio2Day = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_2day);
		radio2Day.setText(getString(ParentActivity.getResources().getString(R.string._2Day), 336));
		ParentActivity.setMarqueeRadio(radio2Day);
		
		layoutTime = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_esl_time);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		ESLMode = CAN1Comm.Get_ESLMode_820_PGN65348();
		ESLInterval = CAN1Comm.Get_ESLInterval_825_PGN65348();
		
		Log.d(TAG,"ESLMode : " + Integer.toString(ESLMode));
		
		ESLModeDisplay(ESLMode);
		ESLTimeDisplay(ESLInterval);
		CursurFirstDisplay(ESLMode);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 14;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		radioDisable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDisable();
			}
		});
		radioOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnAlways();
			}
		});
		radioOnAfterTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnAfterTime();
			}
		});
		
		
		
		radio5Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click5Min();
			}
		});
		radio10Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click10Min();
			}
		});
		radio20Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click20Min();
			}
		});
		radio30Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click30Min();
			}
		});
		radio1Hour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click1Hour();
			}
		});
		radio2Hour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click2Hour();
			}
		});
		radio4Hour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click4Hour();
			}
		});
		radio1Day.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click1Day();
			}
		});
		radio2Day.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				Click2Day();
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
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
			
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW){
			ParentActivity.OldScreenIndex = 0;
			ParentActivity._MenuBaseFragment.showBodyEngineAutoShutdown();
			ParentActivity.EngineAutoShutdownESLSetFlag = true;
			ParentActivity.ESLInterval = ESLInterval;
			ParentActivity.ESLMode = ESLMode;
		}else{
			ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();
			ParentActivity.EngineAutoShutdownESLSetFlag = false;
			if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL){
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_INTERVAL_SETTING);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(ESLMode);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(ESLInterval);
				CAN1Comm.TxCANToMCU(23);
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
				Log.d(TAG,"DATA_STATE_ESL_INTERVAL_SETTING");
			}
			else{
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_SETTING);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(ESLMode);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
				CAN1Comm.TxCANToMCU(23);
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
			}
		}
		
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW){
			ParentActivity.OldScreenIndex = 0;
			ParentActivity._MenuBaseFragment.showBodyEngineAutoShutdown();
		}else{
			ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();
		}
		ParentActivity.EngineAutoShutdownESLSetFlag =false;
	}
	public void ClickDisable(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE;
		ESLTimeDisplayOnOff(ESLMode);
		ESLModeDisplay(ESLMode);
	}
	public void ClickOnAlways(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS;
		ESLTimeDisplayOnOff(ESLMode);
		ESLModeDisplay(ESLMode);
	}
	public void ClickOnAfterTime(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLTimeDisplayOnOff(ESLMode);
		ESLModeDisplay(ESLMode);
	}
	public void Click5Min(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click10Min(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click20Min(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click30Min(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click1Hour(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click2Hour(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click4Hour(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click1Day(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click2Day(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY;
		ESLTimeDisplay(ESLInterval);
	}
	/////////////////////////////////////////////////////////////////////
	public void ESLTimeDisplayOnOff(int _eslmode){
		switch (_eslmode) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			layoutTime.setAlpha((float)0.2);
			radio5Min.setClickable(false);
			radio10Min.setClickable(false);
			radio20Min.setClickable(false);
			radio30Min.setClickable(false);
			radio1Hour.setClickable(false);
			radio2Hour.setClickable(false);
			radio4Hour.setClickable(false);
			radio1Day.setClickable(false);
			radio2Day.setClickable(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			layoutTime.setAlpha((float)0.2);
			radio5Min.setClickable(false);
			radio10Min.setClickable(false);
			radio20Min.setClickable(false);
			radio30Min.setClickable(false);
			radio1Hour.setClickable(false);
			radio2Hour.setClickable(false);
			radio4Hour.setClickable(false);
			radio1Day.setClickable(false);
			radio2Day.setClickable(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			layoutTime.setAlpha((float)1);
			radio5Min.setClickable(true);
			radio10Min.setClickable(true);
			radio20Min.setClickable(true);
			radio30Min.setClickable(true);
			radio1Hour.setClickable(true);
			radio2Hour.setClickable(true);
			radio4Hour.setClickable(true);
			radio1Day.setClickable(true);
			radio2Day.setClickable(true);
			break;
		default:
			break;
		}
	}
	public void ESLModeDisplay(int _eslmode){
		switch (_eslmode) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			radioDisable.setChecked(true);
			radioOnAlways.setChecked(false);
			radioOnAfterTime.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			radioDisable.setChecked(false);
			radioOnAlways.setChecked(true);
			radioOnAfterTime.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			radioDisable.setChecked(false);
			radioOnAlways.setChecked(false);
			radioOnAfterTime.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ESLTimeDisplay(int _time){
		switch (_time) {
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN:
			radio5Min.setChecked(true);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN:
			radio5Min.setChecked(false);
			radio10Min.setChecked(true);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(true);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(true);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(true);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(true);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(true);
			radio1Day.setChecked(false);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(true);
			radio2Day.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY:
			radio5Min.setChecked(false);
			radio10Min.setChecked(false);
			radio20Min.setChecked(false);
			radio30Min.setChecked(false);
			radio1Hour.setChecked(false);
			radio2Hour.setChecked(false);
			radio4Hour.setChecked(false);
			radio1Day.setChecked(false);
			radio2Day.setChecked(true);
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
			CursurIndex = 14;
			CursurDisplay(CursurIndex);
			break;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
			if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE
			|| ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS){
				CursurIndex = 14;
				CursurDisplay(CursurIndex);
			}else if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL){
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 14:
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
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE
			|| ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS){
				CursurIndex = 14;
				CursurDisplay(CursurIndex);
			}else if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL){
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 13:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 14:
			if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE
			|| ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS){
				CursurIndex = 13;
				CursurDisplay(CursurIndex);
			}else if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL){
				CursurIndex = 4;
				CursurDisplay(CursurIndex);
			}
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
		
			ClickCancel();
			break;
			
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
			CursurFirstDisplay(ESLMode);
			break;
		default:
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickDisable();
			CursurIndex = 14;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			ClickOnAlways();
			CursurIndex = 14;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			ClickOnAfterTime();
			switch (ESLInterval) {
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN:
				CursurIndex = 4;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN:
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN:
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN:
				CursurIndex = 7;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR:
				CursurIndex = 8;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR:
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR:
				CursurIndex = 10;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY:
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY:
				CursurIndex = 12;
				CursurDisplay(CursurIndex);
				break;
			default:
				CursurIndex = 4;
		        HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				break;
			}
			
			break;
		case 4:
			Click5Min();
			CursurIndex = 14;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			Click10Min();
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			Click20Min();
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			Click30Min();
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			Click1Hour();
			CursurIndex = 8;
			CursurDisplay(CursurIndex);
			break;
		case 9:
			Click2Hour();
			CursurIndex = 9;
			CursurDisplay(CursurIndex);
			break;
		case 10:
			Click4Hour();
			CursurIndex = 10;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			Click1Day();
			CursurIndex = 11;
			CursurDisplay(CursurIndex);
			break;
		case 12:
			Click2Day();
			CursurIndex = 12;
			CursurDisplay(CursurIndex);
			break;
		case 13:
			ClickCancel();
			break;
		case 14:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		radioDisable.setPressed(false);
		radioOnAlways.setPressed(false);
		radioOnAfterTime.setPressed(false);
		radio5Min.setPressed(false);
		radio10Min.setPressed(false);
		radio20Min.setPressed(false);
		radio30Min.setPressed(false);
		radio1Hour.setPressed(false);
		radio2Hour.setPressed(false);
		radio4Hour.setPressed(false);
		radio1Day.setPressed(false);
		radio2Day.setPressed(false);

		switch (CursurIndex) {
			case 1:
				radioDisable.setPressed(true);
				break;
			case 2:
				radioOnAlways.setPressed(true);
				break;
			case 3:
				radioOnAfterTime.setPressed(true);
				break;
			case 4:
				radio5Min.setPressed(true);
				break;
			case 5:
				radio10Min.setPressed(true);
				break;
			case 6:
				radio20Min.setPressed(true);
				break;
			case 7:
				radio30Min.setPressed(true);
				break;
			case 8:
				radio1Hour.setPressed(true);
				break;
			case 9:
				radio2Hour.setPressed(true);
				break;
			case 10:
				radio4Hour.setPressed(true);
				break;
			case 11:
				radio1Day.setPressed(true);
				break;
			case 12:
				radio2Day.setPressed(true);
				break;
			case 13:
				imgbtnCancel.setPressed(true);
				break;
			case 14:
				imgbtnOK.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
