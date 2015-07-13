package taeha.wheelloader.fseries_monitor.main.a;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainALeftUpMachineStatusSelectFragment3 extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	
	private  final String TAB = "         ";
	
	private  final int SELECT_UPPER = 0;
	private  final int SELECT_LOWER = 1;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioHYDTemp;
	RadioButton radioTMOilTemp;
	RadioButton radioBatteryVoltage;
	RadioButton radioWeighingSystem;
	RadioButton radioCoolantTemp;
	RadioButton radioFrontAxleTemp;	
	RadioButton	radioRearAxleTemp;
	
	TextView textViewOK;
	
	ImageButton imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectFlag;	
	int CursurIndex;
	int InitialCursur = 0;
	Handler HandleCursurDisplay;
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
		TAG = "MainALeftUpMachineStatusSelectFragment3";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.left_main_a_machinestatus_select3, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.StartBackHomeTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS3;
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){
			radioHYDTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_hydtemp);
			radioHYDTemp.setPadding(90, 0, 0, 0);
			radioTMOilTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_tmoiltemp);
			radioTMOilTemp.setPadding(90, 0, 0, 0);
			radioBatteryVoltage = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_battery);
			radioBatteryVoltage.setPadding(90, 0, 0, 0);
			radioWeighingSystem = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_weighing);
			radioWeighingSystem.setPadding(90, 0, 0, 0);
			radioCoolantTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_coolanttemp);
			radioCoolantTemp.setPadding(90, 0, 0, 0);
			radioFrontAxleTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_frontaxletemp);
			radioFrontAxleTemp.setPadding(90, 0, 0, 0);
			radioRearAxleTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_rearaxletemp);
			radioRearAxleTemp.setPadding(90, 0, 0, 0);
		}else {
			radioHYDTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_hydtemp);
			radioTMOilTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_tmoiltemp);
			radioBatteryVoltage = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_battery);
			radioWeighingSystem = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_weighing);
			radioCoolantTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_coolanttemp);
			radioFrontAxleTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_frontaxletemp);	
			radioRearAxleTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_a_machinestatus_select_rearaxletemp);
		}
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_leftup_main_a_machinestatus_select_ok);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_leftup_main_a_machinestatus_select_ok);
		 
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		SelectFlag = SELECT_UPPER;
		InitialCursur = 0;
		switch (ParentActivity.MachineStatusUpperIndex) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			CursurIndex = 1;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			CursurIndex = 2;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			CursurIndex = 3;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			CursurIndex = 4;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			CursurIndex = 5;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			CursurIndex = 6;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			CursurIndex = 7;
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
		default:
			CursurIndex = 1;
			break;
		}
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioHYDTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickHYD();
			}
		});
		radioTMOilTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTMOil();
			}
		});
		radioBatteryVoltage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBattery();
			}
		});
		radioWeighingSystem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickWeighing();
			}
		});
		radioCoolantTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCoolant();
			}
		});
		radioFrontAxleTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickFrontAxleTemp();
			}
		});
		radioRearAxleTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickRearAxleTemp();
			}
		});
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
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
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
	}
	/////////////////////////////////////////////////////////////////////	
	public void MachineStatusDisplay(int Upper, int Lower){
	
		radioHYDTemp.setChecked(false);
		radioTMOilTemp.setChecked(false);
		radioBatteryVoltage.setChecked(false);
		radioWeighingSystem.setChecked(false);
		radioCoolantTemp.setChecked(false);
		radioFrontAxleTemp.setChecked(false);
		radioRearAxleTemp.setChecked(false);
		
		switch (Upper) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			radioHYDTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			radioTMOilTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			radioBatteryVoltage.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			radioWeighingSystem.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			radioCoolantTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			radioFrontAxleTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			radioRearAxleTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			
			break;
		default:
			break;
		}
		switch (Lower) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			radioHYDTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			radioTMOilTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			radioBatteryVoltage.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			radioWeighingSystem.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			radioCoolantTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			radioFrontAxleTemp.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			radioRearAxleTemp.setChecked(true);
			break;
		default:
			break;
		}
	}
	
	public void ClickRadio(int ID){
		if(ParentActivity.MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING){
			if(ParentActivity.MachineStatusUpperIndex == ID){
				ParentActivity.MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
				return;
			}
			if(ParentActivity.MachineStatusLowerIndex == ID){
				ParentActivity.MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
				return;
			}
			if(ParentActivity.MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT){
				ParentActivity.MachineStatusUpperIndex = ID;
				return;
			}
			if(ParentActivity.MachineStatusLowerIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT){
				ParentActivity.MachineStatusLowerIndex = ID;
				return;
			}
				
			if(SelectFlag == SELECT_UPPER){
				ParentActivity.MachineStatusUpperIndex = ID;
				SelectFlag = SELECT_LOWER;
			}else{
				ParentActivity.MachineStatusLowerIndex = ID;
				SelectFlag = SELECT_UPPER;
			}
		}else{
			ParentActivity.MachineStatusUpperIndex = ID;
			ParentActivity.MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
		}
	}
	public void ClickHYD(){
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickTMOil(){
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickBattery(){
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickWeighing(){
		SelectFlag = SELECT_UPPER;
		if(ParentActivity.MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING){
			ParentActivity.MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
			ParentActivity.MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
		}else{
			ParentActivity.MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING;
			ParentActivity.MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING;
		}
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickCoolant(){
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickFrontAxleTemp(){
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickRearAxleTemp(){
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
		return;
		else
		ParentActivity.StartAnimationRunningTimer();
		ParentActivity.SavePref();
		ParentActivity._MainABaseFragment.showLeftUptoDefaultScreenAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 8;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
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
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);

			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		Log.d(TAG,"ClickEnter");
		switch (CursurIndex) {
		case 1:
			ClickHYD();
			break;
		case 2:
			ClickTMOil();
			break;
		case 3:
			ClickBattery();
			break;
		case 4:
			ClickWeighing();
			break;
		case 5:
			ClickCoolant();
			break;
		case 6:
			ClickFrontAxleTemp();
			break;
		case 7:
			ClickRearAxleTemp();
			break;
		case 8:
			ClickOK();
			break;
		default:

			break;
		}
	}
	public void CursurDisplay(int Index){
		radioHYDTemp.setPressed(false);
		radioTMOilTemp.setPressed(false);
		radioBatteryVoltage.setPressed(false);
		radioWeighingSystem.setPressed(false);
		radioCoolantTemp.setPressed(false);		
		radioFrontAxleTemp.setPressed(false);
		radioRearAxleTemp.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioHYDTemp.setPressed(true);
			break;
		case 2:
			radioTMOilTemp.setPressed(true);
			break;
		case 3:
			radioBatteryVoltage.setPressed(true);
			break;
		case 4:
			radioWeighingSystem.setPressed(true);
			break;
		case 5:
			radioCoolantTemp.setPressed(true);
			break;			
		case 6:
			radioFrontAxleTemp.setPressed(true);
			break;
		case 7:
			radioRearAxleTemp.setPressed(true);
			break;
		case 8:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
		if(InitialCursur == 0)
			InitialCursur = 1;
		else
			ParentActivity.StartBackHomeTimer();

	}
}