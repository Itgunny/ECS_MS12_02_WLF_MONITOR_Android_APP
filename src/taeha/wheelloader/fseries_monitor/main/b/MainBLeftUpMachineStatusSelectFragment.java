package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
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

public class MainBLeftUpMachineStatusSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBLeftUpMachineStatusSelectFragment";
	
	private static final String TAB = "         ";
	
	private static final int SELECT_UPPER = 0;
	private static final int SELECT_LOWER = 1;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioHYDTemp;
	RadioButton radioTMOilTemp;
	RadioButton radioBatteryVoltage;
	RadioButton radioWeighingSystem;
	RadioButton radioCoolantTemp;
	
	TextView textViewOK;
	
	ImageView imgViewHYDTemp;
	ImageView imgViewTMOilTemp;
	ImageView imgViewBatteryVoltage;
	ImageView imgViewWeighingSystem;
	ImageView imgViewCoolantTemp;
	
	ImageButton imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectFlag;
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
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftup_main_b_machinestatus_select, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS;
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
		
		radioHYDTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_hydtemp);
		radioTMOilTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_tmoiltemp);
		radioBatteryVoltage = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_battery);
		radioWeighingSystem = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_weighing);
		radioCoolantTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_coolanttemp);
		
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_leftup_main_b_machinestatus_select_ok);
		
		imgViewHYDTemp = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_hydtemp);
		imgViewTMOilTemp = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_tmoiltemp);
		imgViewBatteryVoltage = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_battery);
		imgViewWeighingSystem = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_weighing);
		imgViewCoolantTemp = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_coolanttemp);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_leftup_main_b_machinestatus_select_ok);
		
		radioHYDTemp.setText(TAB + ParentActivity.getResources().getString(string.HYD_Temp));
		radioTMOilTemp.setText(TAB + ParentActivity.getResources().getString(string.TM_Oil_Temp));
		radioBatteryVoltage.setText(TAB + ParentActivity.getResources().getString(string.Battery_Volt));
		radioWeighingSystem.setText(TAB + ParentActivity.getResources().getString(string.Weighing_System));
		radioCoolantTemp.setText(TAB + ParentActivity.getResources().getString(string.Coolant_Temp));
		
		 
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		SelectFlag = SELECT_UPPER;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioHYDTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHYD();
			}
		});
		radioTMOilTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTMOil();
			}
		});
		radioBatteryVoltage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBattery();
			}
		});
		radioWeighingSystem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeighing();
			}
		});
		radioCoolantTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCoolant();
			}
		});
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
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickTMOil(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickBattery(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickWeighing(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
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
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ClickRadio(CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT);
		MachineStatusDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
		ParentActivity.SavePref();
	}
	public void ClickOK(){
		ParentActivity._MainBBaseFragment.showLeftUptoDefaultScreenAnimation();
	}
	
}