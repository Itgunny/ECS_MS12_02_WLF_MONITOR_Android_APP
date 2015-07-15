package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
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
	private static final int NumofItem = 20;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	
	ImageButton imgbtnUp;
	ImageButton imgbtnDown;
	
	ImageView imgViewCoolingFanIcon;
	
	RadioButton radioAuto;
	RadioButton radioManual;
	
	CheckBox checkMaxControl;
	
	// TextView
	TextView textViewEpprValue;
	TextView textViewExcute;
	TextView textViewMaxControl;
	
	// ListView
	ListView listView;
	// ListItem
	IconTextListAdapter adapter;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
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
		
		CAN1Comm.Set_TestMode_PGN61184_61(0);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
		
		setCoolingFanReverseRadio(CoolingFanReverse);
		SetMaxControlFanSpeed(MaxControlFanSpeed);
		
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Fan_EPPR_Current_Adjust));
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
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
		CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(3);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_sensormonitoring_hidden_low_ok);
		
		imgbtnUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_plus);
		imgbtnDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_minus);
		textViewEpprValue = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_data);

		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_sensormonitoring_hidden_auto);
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_sensormonitoring_hidden_manual);
	
		textViewExcute = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_excute);
		textViewMaxControl = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_low_maxcontrol);
		
		imgViewCoolingFanIcon = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_service_sensormonitoring_hidden_coolingfan_manual);

		checkMaxControl = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_management_service_sensormonitoring_hidden_low_maxcontrol);
		
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
		
		CoolingFanReverse = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		MaxControlFanSpeed = CAN1Comm.Get_FanSpeedMaxControlMode_210_PGN65369();
		
		CursurIndex = 1;
		
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
				ClickMaxControl();
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
		CheckCoolingFanManualButton();
		StatusListDisplay(nFanRpm,nEpprCurrent,nBreakeFailureWarningPS,nBrakePriorityPS);
		ReverseFanDisplay(ReverseFan);
		SetMaxControlFanSpeed(MaxControlFanSpeed);
	}
	/////////////////////////////////////////////////////////////////////
	public void CheckCoolingFanManualButton(){
		if(textViewExcute.isPressed() == true){
//			Log.d(TAG, "Set_CoolingFanReverseManual_PGN61184_61(1)");
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(1);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			if(ManualPress == false)
			{
				checkMaxControl.setClickable(false);
				checkMaxControl.setAlpha((float)0.5);
				textViewMaxControl.setClickable(false);
				textViewMaxControl.setAlpha((float)0.5);
			}
			ManualPress = true;
			
		}
		
		if(ManualPress == true && textViewExcute.isPressed() == false){
//			Log.d(TAG, "Set_CoolingFanReverseManual_PGN61184_61(3)");
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			
			checkMaxControl.setClickable(true);
			checkMaxControl.setAlpha((float)1);
			textViewMaxControl.setClickable(true);
			textViewMaxControl.setAlpha((float)1);
			
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
			checkMaxControl.setClickable(true);
			checkMaxControl.setAlpha((float)1);
			textViewExcute.setClickable(true);
			textViewExcute.setAlpha((float)1);
			textViewMaxControl.setClickable(true);
			textViewMaxControl.setAlpha((float)1);
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			textViewExcute.setClickable(false);
			textViewExcute.setAlpha((float)0.5);
			checkMaxControl.setClickable(false);
			checkMaxControl.setAlpha((float)0.5);
			textViewMaxControl.setClickable(false);
			textViewMaxControl.setAlpha((float)0.5);
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
			textViewMaxControl.setText(" : " + ParentActivity.getResources().getString(R.string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_ON:
			textViewMaxControl.setText(" : " + ParentActivity.getResources().getString(R.string.ON));
			break;
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			textViewMaxControl.setText(" : Not Available");
			break;
		default:
			break;
		}		
	}
	/////////////////////////////////////////////////////////////////////	

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
			textViewExcute.setClickable(false);
			textViewExcute.setAlpha((float)0.5);			
		}else{
			CAN1Comm.Set_FanSpeedMaxControlMode_210_PGN61184_61(CAN1CommManager.DATA_STATE_OFF);
			textViewExcute.setClickable(true);
			textViewExcute.setAlpha((float)1);			
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
				,"Fan rpm", Integer.toString(FanRpm), "rpm"));
//		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line)
//				, "Engine Cooling Fan Valve Current", Integer.toString(EpprCurrent), "%"));		// ++, --, 150407 bwk HHI 요청으로 전류단위를 mA에서 %로 변경
		adapter.notifyDataSetChanged();
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
			Result = ParentActivity.getResources().getString(string.bar);
			break;
		case 1:
			Result = ParentActivity.getResources().getString(string.Mpa);
			break;
		case 2:
			Result = ParentActivity.getResources().getString(string.kgf_cm);
			break;
		case 3:
			Result = ParentActivity.getResources().getString(string.Psi);
			break;

		default:
			Result = ParentActivity.getResources().getString(string.bar);
			break;
		}
		return Result;
	}
	
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			if((CoolingFanReverse == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF) && (textViewExcute.isPressed() == false))
				CursurIndex--;
			else
				CursurIndex = 4;;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
		
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 5:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			if((CoolingFanReverse == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF) && (textViewExcute.isPressed() == false))
				CursurIndex++;
			else
				CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
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
			if(checkMaxControl.isChecked() == true){
				checkMaxControl.setChecked(false);
			}else{
				checkMaxControl.setChecked(true);
			}
			ClickMaxControl();
			break;
		case 6:
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
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
}
