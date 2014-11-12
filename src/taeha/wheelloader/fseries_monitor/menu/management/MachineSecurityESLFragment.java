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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.ESL_System_Setting));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_esl_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_esl_low_cancel);
	
		radioDisable = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_use_on_disalbe);
		radioOnAlways = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_use_on_always);
		radioOnAfterTime = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_use_on_time);
		
		radio5Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_5min);
		radio10Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_10min);
		radio20Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_20min);
		radio30Min = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_30min);
		radio1Hour = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_1hour);
		radio2Hour = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_2hour);
		radio4Hour = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_4hour);
		radio1Day = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_1day);
		radio2Day = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_esl_time_2day);
		
		layoutTime = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_esl_time);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		ESLMode = CAN1Comm.Get_ESLMode_820_PGN65348();
		ESLInterval = CAN1Comm.Get_ESLInterval_825_PGN65348();
		
		ESLTimeDisplayOnOff(ESLMode);
		ESLModeDisplay(ESLMode);
		ESLTimeDisplay(ESLInterval);
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
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		radioDisable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDisable();
			}
		});
		radioOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOnAlways();
			}
		});
		radioOnAfterTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOnAfterTime();
			}
		});
		
		
		
		radio5Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click5Min();
			}
		});
		radio10Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click10Min();
			}
		});
		radio20Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click20Min();
			}
		});
		radio30Min.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click30Min();
			}
		});
		radio1Hour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click1Hour();
			}
		});
		radio2Hour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click2Hour();
			}
		});
		radio4Hour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click4Hour();
			}
		});
		radio1Day.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click1Day();
			}
		});
		radio2Day.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
		ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();

		if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL){
			CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_INTERVAL_SETTING);
			CAN1Comm.Set_ESLMode_820_PGN61184_23(ESLMode);
			CAN1Comm.Set_ESLInterval_825_PGN61184_23(ESLInterval);
			CAN1Comm.TxCANToMCU(23);
			CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
			CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
			CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
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
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();
	}
	public void ClickDisable(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE;
		ESLTimeDisplayOnOff(ESLMode);
	}
	public void ClickOnAlways(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS;
		ESLTimeDisplayOnOff(ESLMode);
	}
	public void ClickOnAfterTime(){
		ESLMode = CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL;
		ESLTimeDisplayOnOff(ESLMode);
	}
	public void Click5Min(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click10Min(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click20Min(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click30Min(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click1Hour(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click2Hour(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click4Hour(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click1Day(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY;
		ESLTimeDisplay(ESLInterval);
	}
	public void Click2Day(){
		ESLInterval = CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY;
		ESLTimeDisplay(ESLInterval);
	}
	/////////////////////////////////////////////////////////////////////
	public void ESLTimeDisplayOnOff(int _eslmode){
		switch (_eslmode) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			layoutTime.setVisibility(View.INVISIBLE);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			layoutTime.setVisibility(View.INVISIBLE);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			layoutTime.setVisibility(View.VISIBLE);
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
	
}
