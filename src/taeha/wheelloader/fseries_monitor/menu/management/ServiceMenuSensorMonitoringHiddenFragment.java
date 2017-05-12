package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.RadioButtonTextView;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;



public class ServiceMenuSensorMonitoringHiddenFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int ADJUST_MODE = 1;
	private static final int FAN_SPEED_MAX_MODE = 2;
	private static final int FAN_REVERSE_MODE = 3;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextFitTextView	textViewOK;
	
	ImageButton imgbtnUp;
	ImageButton imgbtnDown;
	ImageButton imgBtnControlUp;
	ImageButton imgBtnControlDown;
	
	ImageView imgViewCoolingFanIcon;
	
	RadioButtonTextView radioAuto;
	RadioButtonTextView radioManual;
	
	CheckBox checkMaxControl;
	
	// TextView
	TextFitTextView textViewEpprValue;
	TextFitTextView textViewExcute;
	TextFitTextView textViewMaxControl;
	
	// TextView
	//TextFitTextView textViewControlEpprTitle;
	TextFitTextView textViewControlEpprData;
	
	// ListView
	ListView listView;
	// ListItem
	IconTextListAdapter adapter;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectMode;
	int nFanRpm;
	int nEpprCurrent;
	int nBreakeFailureWarningPS;
	int nBrakePriorityPS;
	int CoolingFanReverse;
	int ReverseFan;
	int MaxControlFanSpeed;
	boolean EpprActive = false;
	boolean ManualPress;
	
	
	int CursurIndex;
	Handler HandleCursurDisplay;
	
	private Timer	mEnableButtonTimer = null;	
	
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
		 TAG = "ServiceMenuSensorMonitoringHiddenFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_service_sensormonitoring_hidden, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		StartEnableButtonTimer();
		
		CAN1Comm.Set_TestMode_PGN61184_61(0);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
		
		setCoolingFanReverseRadio(CoolingFanReverse);
		SetMaxControlFanSpeed(MaxControlFanSpeed);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Fan_EPPR_Current_Adjust), 386);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		CAN1Comm.Set_TestMode_PGN61184_61(0);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_TestMode_PGN61184_61(3);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_sensormonitoring_hidden_low_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
		
		imgbtnUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_plus);
		imgbtnDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_minus);

		imgBtnControlUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_maxcontrol_plus);
		imgBtnControlDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_maxcontrol_minus);
		
		textViewEpprValue = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_data);

		radioAuto = (RadioButtonTextView)mRoot.findViewById(R.id.radioButton_menu_body_management_service_sensormonitoring_hidden_auto);
		radioAuto.setText(getString(ParentActivity.getResources().getString(string.Automatic), 27));
		radioManual = (RadioButtonTextView)mRoot.findViewById(R.id.radioButton_menu_body_management_service_sensormonitoring_hidden_manual);
		radioManual.setText(getString(ParentActivity.getResources().getString(string.Manual), 26));
		
		textViewExcute = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_excute);
		textViewExcute.setText(getString(ParentActivity.getResources().getString(string.Excute), 249));
		
		textViewMaxControl = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_low_maxcontrol);
	
		// TextView
		textViewControlEpprData = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_maxcontrol_mode);
		textViewControlEpprData.setText("Max Mode : ");
		
		
		imgViewCoolingFanIcon = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_service_sensormonitoring_hidden_coolingfan_manual);

		checkMaxControl = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_management_service_sensormonitoring_hidden_maxcontrol_check);
		
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_management_service_sensormonitoring_hidden);
		adapter = new IconTextListAdapter(ParentActivity);
		adapter.clearItem();
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		nFanRpm = 0;
		nEpprCurrent = 0;
		EpprActive = false;
		ManualPress = false;
		listView.setAdapter(adapter);
		SelectMode = ADJUST_MODE;
		
		CoolingFanReverse = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		MaxControlFanSpeed = CAN1Comm.Get_FanSpeedMaxControlMode_210_PGN65369();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPlus();
			}
		});
		imgbtnDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickMinus();
			}
		});
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				CoolingFanReverse = CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO;
				setCoolingFanReverseRadio(CoolingFanReverse);
				ClickAuto();
			}
		});
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				CoolingFanReverse = CAN1CommManager.DATA_STATE_REVERSEFAN_OFF;
				setCoolingFanReverseRadio(CoolingFanReverse);
				ClickManual();
			}
		});
		textViewExcute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CheckCoolingFanManualButton();
			}
		});		
		checkMaxControl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetMaxControlFanSpeed(MaxControlFanSpeed);
				ClickMaxControl();
			}
		});		
		imgBtnControlDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				clickControlMinus();
			}
		});
		imgBtnControlUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				clickControlPlus();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		nFanRpm = CAN1Comm.Get_CoolingFanSpeed_318_PGN65369();
		nEpprCurrent = CAN1Comm.Get_CoolingFanValveCurrent_146_PGN65369();	
		nBreakeFailureWarningPS = CAN1Comm.Get_BrakeOilPressure_503_PGN65354();	
		nBrakePriorityPS = CAN1Comm.Get_BrakeOilChargingPriorityPressure_557_PGN65354();
		ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
		MaxControlFanSpeed = CAN1Comm.Get_FanSpeedMaxControlMode_210_PGN65369();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		if(SelectMode == FAN_REVERSE_MODE)
			CheckCoolingFanManualButton();
		StatusListDisplay(nFanRpm,nEpprCurrent,nBreakeFailureWarningPS,nBrakePriorityPS);
		ReverseFanDisplay(ReverseFan);
		SetMaxControlFanSpeed(MaxControlFanSpeed);
	}
	/////////////////////////////////////////////////////////////////////
	public void SetSelectMode(int index){
		SelectMode = index;
		int rpm = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		int AlternatorVoltage = CAN1Comm.Get_AlternatorVoltage_707_PGN65360();
		
		imgbtnDown.setClickable(false);
		imgbtnUp.setClickable(false);
		radioAuto.setClickable(false);
		radioManual.setClickable(false);
		textViewExcute.setClickable(false);
		checkMaxControl.setClickable(false);
		textViewMaxControl.setClickable(false);
		//textViewControlEpprTitle.setClickable(false);
		textViewControlEpprData.setClickable(false);
		imgBtnControlUp.setClickable(false);
		imgBtnControlDown.setClickable(false);

		imgbtnDown.setAlpha((float)0.5);
		imgbtnUp.setAlpha((float)0.5);
		radioAuto.setAlpha((float)0.5);
		radioManual.setAlpha((float)0.5);
		textViewExcute.setAlpha((float)0.5);
		checkMaxControl.setAlpha((float)0.5);
		textViewMaxControl.setAlpha((float)0.5);		
		//textViewControlEpprTitle.setAlpha((float)0.5);
		textViewControlEpprData.setAlpha((float)0.5);
		imgBtnControlUp.setAlpha((float)0.5);
		imgBtnControlDown.setAlpha((float)0.5);	
		
		switch(index){
			case ADJUST_MODE:
			default:
				imgbtnDown.setClickable(true);
				imgbtnUp.setClickable(true);
				imgbtnDown.setAlpha((float)1);
				imgbtnUp.setAlpha((float)1);
				CursurIndex = 1;
				break;
			case FAN_SPEED_MAX_MODE:
				if((rpm >= 500 && rpm < 8031) && (AlternatorVoltage > 255 && AlternatorVoltage <= 360))
				{
					if(checkMaxControl.isChecked() == true){
						//textViewControlEpprTitle.setClickable(true);
						textViewControlEpprData.setClickable(true);
						imgBtnControlUp.setClickable(true);
						imgBtnControlDown.setClickable(true);
						checkMaxControl.setClickable(true);
						//textViewControlEpprTitle.setAlpha((float)1);
						textViewControlEpprData.setAlpha((float)1);
						imgBtnControlUp.setAlpha((float)1);
						imgBtnControlDown.setAlpha((float)1);
						checkMaxControl.setAlpha((float)1);
					}else {
						//textViewControlEpprTitle.setClickable(true);
						textViewControlEpprData.setClickable(true);
						imgBtnControlUp.setClickable(false);
						imgBtnControlDown.setClickable(false);
						checkMaxControl.setClickable(true);
					//	textViewControlEpprTitle.setAlpha((float)1);
						textViewControlEpprData.setAlpha((float)1);
						imgBtnControlUp.setAlpha((float)0.5);
						imgBtnControlDown.setAlpha((float)0.5);
						checkMaxControl.setAlpha((float)1);
					}
				} else {
					checkMaxControl.setClickable(false);
					textViewMaxControl.setClickable(false);
					textViewControlEpprData.setClickable(false);
					imgBtnControlUp.setClickable(false);
					imgBtnControlDown.setClickable(false);
		
					checkMaxControl.setAlpha((float)0.5);
					textViewMaxControl.setAlpha((float)0.5);	
					textViewControlEpprData.setAlpha((float)0.5);
					imgBtnControlUp.setAlpha((float)0.5);
					imgBtnControlDown.setAlpha((float)0.5);
				}
				CursurIndex = 5;
				break;
			case FAN_REVERSE_MODE:
				radioAuto.setClickable(true);
				radioManual.setClickable(true);
				textViewExcute.setClickable(true);
				radioAuto.setAlpha((float)1);
				radioManual.setAlpha((float)1);
				textViewExcute.setAlpha((float)1);
				CursurIndex = 3;
				break;
		}
		CursurDisplay(CursurIndex);
		
		if(checkMaxControl.isChecked() == true){
			checkMaxControl.setChecked(false);
			CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
		}
	}
	public void CheckCoolingFanManualButton(){
		if(textViewExcute.isPressed() == true){
//			Log.d(TAG, "Set_CoolingFanReverseManual_PGN61184_61(1)");
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(1);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = true;
			
		}
		
		if(ManualPress == true && textViewExcute.isPressed() == false){
//			Log.d(TAG, "Set_CoolingFanReverseManual_PGN61184_61(3)");
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = false;
		}
	}
	public void setCoolingFanReverseRadio(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			radioManual.setChecked(true);
			radioAuto.setChecked(false);
			ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);
			if(SelectMode == FAN_REVERSE_MODE){
				textViewExcute.setClickable(true);
				textViewExcute.setAlpha((float)1);
			}
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			if(SelectMode == FAN_REVERSE_MODE){
				textViewExcute.setClickable(false);
				textViewExcute.setAlpha((float)0.5);
			}
			break;
		}
	}
	public void ReverseFanDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_COOLINGFAN_OFF:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);
			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_FORWARD:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);
			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_REVERSE:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_green_popup);
			break;
		default:
			break;
		}
	}
	public void SetMaxControlFanSpeed(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_OFF:
			textViewControlEpprData.setText(" Max Mode: " + getString(ParentActivity.getResources().getString(R.string.OFF), 98));
			imgBtnControlUp.setClickable(false);
			imgBtnControlDown.setClickable(false);
			imgBtnControlUp.setAlpha((float)0.5);
			imgBtnControlDown.setAlpha((float)0.5);
			break;
		case CAN1CommManager.DATA_STATE_ON:
			textViewControlEpprData.setText(" Max Mode: " + getString(ParentActivity.getResources().getString(R.string.ON), 97));
			imgBtnControlUp.setClickable(true);
			imgBtnControlDown.setClickable(true);
			imgBtnControlUp.setAlpha((float)1);
			imgBtnControlDown.setAlpha((float)1);
			break;
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			textViewControlEpprData.setText(" Not Available");
			break;
		default:
			break;
		}		
	}
	/////////////////////////////////////////////////////////////////////	
	public void clickControlPlus(){
		if(checkMaxControl.isChecked() == true)
		{
			CAN1Comm.Set_CoolingFanMaxSpeedCurrentAdjust_PGN61184_61(1);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanMaxSpeedCurrentAdjust_PGN61184_61(3);
			Log.d(TAG, "increase");
		}
	}
	public void clickControlMinus(){
		if(checkMaxControl.isChecked() == true)
		{
			CAN1Comm.Set_CoolingFanMaxSpeedCurrentAdjust_PGN61184_61(0);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanMaxSpeedCurrentAdjust_PGN61184_61(3);
			Log.d(TAG, "decrease");
		}
	}
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySensorMonitoring();
	}
	public void ClickPlus(){
		int EpprCurrent = CAN1Comm.Get_CoolingFanValveCurrent_146_PGN65369();
		EpprActive = true;
		if(EpprCurrent <= 95){
			EpprCurrent += 5;
		}
		else{
			EpprCurrent = 100;
		}
		CAN1Comm.Set_TestMode_PGN61184_61(1);
		CAN1Comm.Set_CoolingFanValveCurrent_146_PGN61184_61(EpprCurrent);
		CAN1Comm.TxCANToMCU(61);
		
		CAN1Comm.Set_TestMode_PGN61184_61(3);
		Log.d(TAG,"RightKeyClick, EpprCurrent : " + Integer.toString(EpprCurrent));
	}
	public void ClickMinus(){
		int EpprCurrent = CAN1Comm.Get_CoolingFanValveCurrent_146_PGN65369();
		EpprActive = true;
		if(EpprCurrent >= 5){
			EpprCurrent -= 5;
		}
		else{
			EpprCurrent = 0;
		}
		CAN1Comm.Set_TestMode_PGN61184_61(1);
		CAN1Comm.Set_CoolingFanValveCurrent_146_PGN61184_61(EpprCurrent);
		CAN1Comm.TxCANToMCU(61);
		
		CAN1Comm.Set_TestMode_PGN61184_61(3);
		Log.d(TAG,"LeftKeyClick, EpprCurrent : " + Integer.toString(EpprCurrent));
	}
	public void ClickAuto(){
		// Manual Off
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
		
		CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(3);
	}
	public void ClickManual(){
		CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_OFF);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(3);
	}
	public void ClickMaxControl(){
		if(checkMaxControl.isChecked() == true){
			CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_ON);
		}else{
			CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
		}
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
	}
	/////////////////////////////////////////////////////////////////////

	public void StatusListDisplay(int FanRpm, int EpprCurrent, int BreakeFailureWarningPS, int BrakePriorityPS){
		int tempBrakePriorityPS;
		int tempBreakeFailureWarningPS;
		boolean BackgroundFlag;
		int IntegerValue, Point;
		
		if(FanRpm == 10000)
			FanRpm = 0;
		else if (FanRpm < 10000)
			FanRpm = 0;
		else if (FanRpm > 10000)
			FanRpm = FanRpm - 10000;
		adapter.clearItem();
		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line)
				,"Fan rpm", Integer.toString(FanRpm), getString(ParentActivity.getResources().getString(R.string.rpm), 34)));
//		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line)
//				, "Engine Cooling Fan Valve Current", Integer.toString(EpprCurrent), "%"));		// ++, --, 150407 bwk HHI ��û���� ���������� mA���� %�� ����
		textViewEpprValue.setText(Integer.toString(EpprCurrent));
		
		BackgroundFlag = true;
		
		if(BrakePriorityPS != 0xffff){
			tempBrakePriorityPS = PressureConvert(BrakePriorityPS,ParentActivity.UnitPressure);
			IntegerValue = tempBrakePriorityPS / 10;
			Point = tempBrakePriorityPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Priority PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Priority PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
		}
		
		if(BreakeFailureWarningPS != 0xffff){
			tempBreakeFailureWarningPS = PressureConvert(BreakeFailureWarningPS,ParentActivity.UnitPressure);
			IntegerValue = tempBreakeFailureWarningPS / 10;
			Point = tempBreakeFailureWarningPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake failure warning PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake failure warning PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
		
		}
		adapter.notifyDataSetChanged();
						
	}
	
	/////////////////////////////////////////////////////////////////////
	public int PressureConvert(int pressure, int Unit){
		int tmp = pressure;
		
		switch (Unit) {
		case Home.UNIT_PRESSURE_MPA:
			tmp = (int)(tmp/10);
			break;
		case Home.UNIT_PRESSURE_KGF:
			tmp = (int)((float)tmp * 1.01972);
			break;
		case Home.UNIT_PRESSURE_PSI:
			tmp = (int)((float)tmp * 14.5);
			break;
		}
		return tmp;
	}
	
	public String GetUnit(int Unit){
		String Result;
	
		switch (Unit) {
		case 0:
			Result = getString(ParentActivity.getResources().getString(string.bar), 43);
			break;
		case 1:
			Result = getString(ParentActivity.getResources().getString(string.Mpa), 44);
			break;
		case 2:
			Result = getString(ParentActivity.getResources().getString(string.kgf_cm), 45);
			break;
		case 3:
			Result = getString(ParentActivity.getResources().getString(string.Psi), 46);
			break;

		default:
			Result = getString(ParentActivity.getResources().getString(string.bar), 43);
			break;
		}
		return Result;
	}
	/////////////////////////////////////////////////////////////////////
	public class EnableButtonTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ParentActivity.AnimationRunningFlag == false)
					{
						CancelEnableButtonTimer();
						CAN1Comm.Set_TestMode_PGN61184_61(0);
						CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
						CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
						CAN1Comm.TxCANToMCU(61);
						CAN1Comm.Set_TestMode_PGN61184_61(3);
						CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
						CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
						ParentActivity.showFanSelectModePopup();
						
					}
				}
			});

		}

	}

	public void StartEnableButtonTimer(){
		CancelEnableButtonTimer();
		mEnableButtonTimer = new Timer();
		mEnableButtonTimer.schedule(new EnableButtonTimerClass(),1,50);	
	}

	public void CancelEnableButtonTimer(){
		if(mEnableButtonTimer != null){
			mEnableButtonTimer.cancel();
			mEnableButtonTimer.purge();
			mEnableButtonTimer = null;
		}

	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch(SelectMode){
			case ADJUST_MODE:
				switch (CursurIndex) {
				case 1:
					CursurIndex = 8;
					break;
				case 2:
				default:
					CursurIndex = 1;
					break;
				case 8:
					CursurIndex = 2;
					break;
				}
				CursurDisplay(CursurIndex);
				break;
			case FAN_SPEED_MAX_MODE:
				switch (CursurIndex) {
				case 5:
				default:
					CursurIndex = 8;
					break;
				case 6:
						CursurIndex = 5;
					break;
				case 7:
					CursurIndex = 6;
					break;
				case 8:
					if(checkMaxControl.isChecked() == true){
						CursurIndex = 7;
					}else {
						CursurIndex = 5;
					}
					break;
				}
				CursurDisplay(CursurIndex);
				break;
			case FAN_REVERSE_MODE:
				switch (CursurIndex) {
				case 3:
					CursurIndex = 8;
					break;
				case 4:
				default:
					CursurIndex = 3;
					break;
				case 8:
					CursurIndex = 4;
					break;
				}
				CursurDisplay(CursurIndex);
				break;
		}
	}
	public void ClickRight(){
		switch(SelectMode){
		case ADJUST_MODE:
			switch (CursurIndex) {
			case 1:
				CursurIndex = 2;
				break;
			case 2:
				CursurIndex = 8;
				break;
			case 8:
			default:
				CursurIndex = 1;
				break;
			}
			CursurDisplay(CursurIndex);
			break;
		case FAN_SPEED_MAX_MODE:
			switch (CursurIndex) {
			case 5:
				if(checkMaxControl.isChecked() == true){
				CursurIndex = 6;
				}else {
					CursurIndex = 8;
				}
				break;
			case 6:
				CursurIndex = 7;
				break;
			case 7:
				CursurIndex = 8;
				break;
			default:
				CursurIndex = 5;
				break;
			}
			CursurDisplay(CursurIndex);
			break;
		case FAN_REVERSE_MODE:
			switch (CursurIndex) {
			case 3:
				CursurIndex = 4;
				break;
			case 4:
				CursurIndex = 8;
				break;
			case 8:
			default:
				CursurIndex = 3;
				break;
			}
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		StartEnableButtonTimer();
	}
	public void ClickEnter(){
		int rpm = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		int AlternatorVoltage = CAN1Comm.Get_AlternatorVoltage_707_PGN65360();
		Log.d(TAG, "rpm="+rpm+"AlternatorVoltage"+AlternatorVoltage);
		switch (CursurIndex) {
		case 1:
			ClickMinus();
			break;
		case 2:
			ClickPlus();
			break;
		case 3:
			CoolingFanReverse = CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO;
			setCoolingFanReverseRadio(CoolingFanReverse);
			ClickAuto();
			break;			
		case 4:
			CoolingFanReverse = CAN1CommManager.DATA_STATE_REVERSEFAN_OFF;
			setCoolingFanReverseRadio(CoolingFanReverse);
			ClickManual();
			break;			
		
		case 5:
			if((rpm >= 500 && rpm < 8031) && (AlternatorVoltage > 255 && AlternatorVoltage <= 360))
			{
			if(checkMaxControl.isChecked() == true){
				checkMaxControl.setChecked(false);
			}else{
				checkMaxControl.setChecked(true);
			}
			ClickMaxControl();
			} 
			break;
		case 6:
			if((rpm >= 500 && rpm < 8031) && (AlternatorVoltage > 255 && AlternatorVoltage <= 360))
			{
				if(checkMaxControl.isChecked() == true){
					clickControlMinus();
				}
			}
			break;
		case 7:
			if((rpm >= 500 && rpm < 8031) && (AlternatorVoltage > 255 && AlternatorVoltage <= 360))
			{
				if(checkMaxControl.isChecked() == true){
					clickControlPlus();
				}
			}
			break;
		case 8:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnDown.setPressed(false);
		imgbtnUp.setPressed(false);
		imgbtnOK.setPressed(false);
		radioAuto.setPressed(false);
		radioManual.setPressed(false);
		checkMaxControl.setPressed(false);
		imgBtnControlUp.setPressed(false);
		imgBtnControlDown.setPressed(false);
		
		switch (Index) {
		case 1:
			imgbtnDown.setPressed(true);
			break;
		case 2:
			imgbtnUp.setPressed(true);
			break;
		case 3:
			radioAuto.setPressed(true);
			break;
		case 4:
			radioManual.setPressed(true);
			break;
		case 5:
			checkMaxControl.setPressed(true);
			break;
		case 6:
			imgBtnControlDown.setPressed(true);
			break;
		case 7:
			imgBtnControlUp.setPressed(true);
			break;
		case 8:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
}
