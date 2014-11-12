package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
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
import android.widget.RadioGroup;
import android.widget.TextView;

public class SpeedometerFreqFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;

	TextView textViewNum1;
	TextView textViewNum2;
	TextView textViewNum3;
	TextView textViewNum4;
	TextView textViewNum5;
	TextView textViewNum6;
	TextView textViewNum7;
	TextView textViewNum8;
	TextView textViewNum9;
	TextView textViewNum0;
	
	ImageButton imgbtnBack;
	TextView textViewNext;
	
	RadioButton radioNum10;
	RadioButton radioNum1;
	RadioButton radioNumUnder1;
	RadioButton radioNumUnder01;
	RadioGroup radioGroupNum;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SpeedometerFreq;
	int SpeedometerFreq_Num10;
	int SpeedometerFreq_Num1;
	int SpeedometerFreq_Num_Under1;
	int SpeedometerFreq_Num_Under01;
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
		 TAG = "SpeedometerFreqFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_speedometerfreq, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		DisableHalfNumButton();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Speedometer_Freq_Setting));
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
	}
	////////////////////////////////////////////////
	
	

	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_default);
		
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_0);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_speedometerfreq_num_back);
		textViewNext = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_next);
		
		radioNum10 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_10);
		radioNum1 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_1);
		radioNumUnder1 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_under_1);
		radioNumUnder01 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_mode_speedometerfreq_data_under_01);
		
		radioGroupNum = (RadioGroup)mRoot.findViewById(R.id.radioGroup_menu_body_mode_speedometerfreq_data);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CAN1Comm.Set_SettingSelection_PGN61184_105(0xF);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_PGN61184_105(0xF);
		CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
		CAN1Comm.TxCANToMCU(105);
		CAN1Comm.Set_SettingSelection_PGN61184_105(15);
		
		InitText();
		
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
		imgbtnDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDefault();
			}
		});	
		textViewNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum1();
			}
		});	
		textViewNum2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum2();
			}
		});	
		textViewNum3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum3();
			}
		});	
		textViewNum4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum4();
			}
		});	
		textViewNum5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum5();
			}
		});	
		textViewNum6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum6();
			}
		});	
		textViewNum7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum7();
			}
		});	
		textViewNum8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum8();
			}
		});	
		textViewNum9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum9();
			}
		});	
		textViewNum0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum0();
			}
		});	
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNumBack();
			}
		});	
		textViewNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNumNext();
			}
		});	

		radioNum10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioNum10();
			}
		});	
		radioNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioNum1();
			}
		});	
		radioNumUnder1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioUnderNum1();
			}
		});	
		radioNumUnder01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRadioUnderNum01();
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
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);

		
		CAN1Comm.Set_SettingSelection_PGN61184_105(1);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(SpeedometerFreq);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_PGN61184_105(0xF);
		CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
		CAN1Comm.TxCANToMCU(105);
		CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
		CAN1Comm.Set_SettingSelection_PGN61184_105(15);
		Log.d(TAG,"SetSpeedMeterFreq : " + Integer.toString(SpeedometerFreq));
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
	}
	public void ClickDefault(){
		ParentActivity.showSpeedometerInit();
	}
	public void ClickNum1(){
		setNumber(1);
	}
	public void ClickNum2(){
		setNumber(2);
	}
	public void ClickNum3(){
		setNumber(3);
	}
	public void ClickNum4(){
		setNumber(4);
	}
	public void ClickNum5(){
		setNumber(5);
	}
	public void ClickNum6(){
		setNumber(6);
	}
	public void ClickNum7(){
		setNumber(7);
	}
	public void ClickNum8(){
		setNumber(8);
	}
	public void ClickNum9(){
		setNumber(9);
	}
	public void ClickNum0(){
		setNumber(0);
	}
	public void ClickNumBack(){
		setNumber(0);
	}
	public void ClickNumNext(){
		setNext();
	}
	public void ClickRadioNum10(){
		DisableHalfNumButton();
	}
	public void ClickRadioNum1(){
		EnableAllNumButton();
	}
	public void ClickRadioUnderNum1(){
		EnableAllNumButton();
	}
	public void ClickRadioUnderNum01(){
		EnableAllNumButton();
	}
	/////////////////////////////////////////////////////////////////////
	public void EnableAllNumButton(){
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(true);
		textViewNum3.setClickable(true);
		textViewNum4.setClickable(true);
		textViewNum0.setClickable(true);
		
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)1.0);
		textViewNum3.setAlpha((float)1.0);
		textViewNum4.setAlpha((float)1.0);
		textViewNum0.setAlpha((float)1.0);
	}
	public void DisableHalfNumButton(){
		textViewNum1.setClickable(false);
		textViewNum2.setClickable(false);
		textViewNum3.setClickable(false);
		textViewNum4.setClickable(false);
		textViewNum0.setClickable(false);
		
		textViewNum1.setAlpha((float)0.5);
		textViewNum2.setAlpha((float)0.5);
		textViewNum3.setAlpha((float)0.5);
		textViewNum4.setAlpha((float)0.5);
		textViewNum0.setAlpha((float)0.5);
	}
	public void setNumber(int num){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (Focus) {
		case R.id.radio_menu_body_mode_speedometerfreq_data_10:
			SpeedometerFreq_Num10 = num;
			radioNum10.setText(Integer.toString(SpeedometerFreq_Num10));
			radioNum1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_1:
			SpeedometerFreq_Num1 = num;
			radioNum1.setText(Integer.toString(SpeedometerFreq_Num1));
			radioNumUnder1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_1:
			SpeedometerFreq_Num_Under1 = num;
			radioNumUnder1.setText(Integer.toString(SpeedometerFreq_Num_Under1));
			radioNumUnder01.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_01:
			SpeedometerFreq_Num_Under01 = num;
			radioNumUnder01.setText(Integer.toString(SpeedometerFreq_Num_Under01));
			radioNum10.setChecked(true);
			DisableHalfNumButton();
			break;
		default:
			
			break;
		}
		SpeedometerFreq = SpeedometerFreq_Num10 * 1000
				+ SpeedometerFreq_Num1 * 100
				+ SpeedometerFreq_Num_Under1 * 10
				+ SpeedometerFreq_Num_Under01;
	}
	public void setNext(){
		int Focus = radioGroupNum.getCheckedRadioButtonId();
		switch (Focus) {
		case R.id.radio_menu_body_mode_speedometerfreq_data_10:
			radioNum1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_1:
			radioNumUnder1.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_1:
			radioNumUnder01.setChecked(true);
			EnableAllNumButton();
			break;
		case R.id.radio_menu_body_mode_speedometerfreq_data_under_01:
			radioNum10.setChecked(true);
			DisableHalfNumButton();
			break;

		default:
			
			break;
		}
		SpeedometerFreq = SpeedometerFreq_Num10 * 1000
				+ SpeedometerFreq_Num1 * 100
				+ SpeedometerFreq_Num_Under1 * 10
				+ SpeedometerFreq_Num_Under01;
	}
	
	public void InitText(){
		
		SpeedometerFreq = CAN1Comm.Get_SpeedometerFrequency_534_PGN61184_106();
		
		SpeedometerFreq_Num10 = SpeedometerFreq / 1000;
		SpeedometerFreq_Num1 = (SpeedometerFreq % 1000) / 100;
		SpeedometerFreq_Num_Under1 = (SpeedometerFreq % 100) / 10;
		SpeedometerFreq_Num_Under01 = SpeedometerFreq % 10;
		
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				radioNum10.setText(Integer.toString(SpeedometerFreq_Num10));
				radioNum1.setText(Integer.toString(SpeedometerFreq_Num1));
				radioNumUnder1.setText(Integer.toString(SpeedometerFreq_Num_Under1));
				radioNumUnder01.setText(Integer.toString(SpeedometerFreq_Num_Under01));
			}
		});
		
	}
	/////////////////////////////////////////////////////////////////////
	
}
