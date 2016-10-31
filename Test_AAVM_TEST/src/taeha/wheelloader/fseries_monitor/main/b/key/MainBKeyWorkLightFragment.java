package taeha.wheelloader.fseries_monitor.main.b.key;

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

public class MainBKeyWorkLightFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
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
	int CursurIndex;
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
		 TAG = "MainBKeyWorkLightFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_worklight, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLIGHT;
		SelectWorkLampStatus = WorkLightLampDisplay(WorkLamp,RearWorkLamp);
		ClickHardKey();
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	////////////////////////////////////////////////


	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_worklight_off);
		radioOff.setText(getString(ParentActivity.getResources().getString(R.string.Off), 20));
		ParentActivity.setMarqueeRadio(radioOff);
		radioFront = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_worklight_front);
		radioFront.setText(getString(ParentActivity.getResources().getString(R.string.Front), 164));
		ParentActivity.setMarqueeRadio(radioFront);
		radioRear = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_worklight_rear);
		
		// ++, 150210 bwk
		radioRear.setText(getString(ParentActivity.getResources().getString(string.Front), 164)
				+ " + " +getString(ParentActivity.getResources().getString(string.Rear), 165));
		// --, 150210 bwk
		ParentActivity.setMarqueeRadio(radioRear);
		
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
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOff();
			}
		});
		radioFront.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickFront();
			}
		});
		radioRear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
			CursurIndex = 2;
			break;
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			CursurIndex = 3;
			break;
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2:
		default:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(247);
			CursurIndex = 1;
			break;
		}
		CursurDisplay(CursurIndex);		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOff();
			break;
		case 2:
			ClickFront();
			break;
		case 3:
			ClickRear();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioOff.setPressed(false);
		radioFront.setPressed(false);
		radioRear.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioOff.setPressed(true);
			break;
		case 2:
			radioFront.setPressed(true);
			break;
		case 3:
			radioRear.setPressed(true);
			break;
		default:
			break;
		}
		ParentActivity.StartBackHomeTimer();
	}
	
}