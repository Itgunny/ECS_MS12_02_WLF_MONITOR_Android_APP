package taeha.wheelloader.fseries_monitor.main.b.key;

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

public class MainBKeyWorkLightFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBKeyWorkLightFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioFront;
	RadioButton radioRear;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WorkLamp;
	int RearWorkLamp;
	int WorkLampStatus;
	int SelectWorkLampStatus;
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
		mRoot = inflater.inflate(R.layout.key_main_b_worklight, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLIGHT;
		SelectWorkLampStatus = WorkLightLampDisplay(WorkLamp,RearWorkLamp);
		ClickHardKey();
		return mRoot;
	}

	////////////////////////////////////////////////


	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_worklight_off);
		radioFront = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_worklight_front);
		radioRear = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_worklight_rear);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		WorkLamp = CAN1Comm.Get_WorkLampOperationStatus_3435_PGN65527();
		RearWorkLamp = CAN1Comm.Get_RearWorkLampOperationStatus_3446_PGN65527();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
			}
		});
		radioFront.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickFront();
			}
		});
		radioRear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRear();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		WorkLamp = CAN1Comm.Get_WorkLampOperationStatus_3435_PGN65527();
		RearWorkLamp = CAN1Comm.Get_RearWorkLampOperationStatus_3446_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		WorkLightLampDisplay(WorkLamp,RearWorkLamp);
	}
	/////////////////////////////////////////////////////////////////////	
	public int WorkLightLampDisplay(int _worklamp, int _rearworklamp){
		if(_worklamp == 0 && _rearworklamp == 0){
			WorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
			radioOff.setChecked(true);
			radioFront.setChecked(false);
			radioRear.setChecked(false);
		}else if(_worklamp == 1 && _rearworklamp == 0){
			WorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1;
			radioOff.setChecked(false);
			radioFront.setChecked(true);
			radioRear.setChecked(false);
		}else if(_worklamp == 1 && _rearworklamp == 1){
			WorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2;
			radioOff.setChecked(false);
			radioFront.setChecked(false);
			radioRear.setChecked(true);
		}else{
			WorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
			radioOff.setChecked(true);
			radioFront.setChecked(false);
			radioRear.setChecked(false);
		}
		return WorkLampStatus;
	}
	
	public void ClickOff(){
		CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
		CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickFront(){
		CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
		CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickRear(){
		CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
		CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	
	public void ClickHardKey(){
		switch (SelectWorkLampStatus) {
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2:
		default:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		}

	}
	
}