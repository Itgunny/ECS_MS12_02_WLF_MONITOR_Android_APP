package taeha.wheelloader.fseries_monitor.main.a.key;

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

public class MainAKeyMainLightFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioPositionLamp;
	RadioButton radioHeadLamp;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int HeadLamp;
	int Illumination;
	int MainLampStatus;
	int SelectMainLampStatus;
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
		TAG = "MainAKeyMainLightFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_mainlight, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_MAINLIGHT;
		SelectMainLampStatus = MainLightLampDisplay(HeadLamp,Illumination);
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
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_mainlight_off);
		radioPositionLamp = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_mainlight_positionlamp);
		radioHeadLamp = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_mainlight_headlamp);
		
		// ++, 150210 bwk
		radioHeadLamp.setText(ParentActivity.getResources().getString(string.Position_Lamp)
				+ " + " +ParentActivity.getResources().getString(string.Head_Lamp));
		// --, 150210 bwk
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		HeadLamp = CAN1Comm.Get_HeadLampOperationStatus_3436_PGN65527();
		Illumination = CAN1Comm.Get_IlluminationOperationStatus_3438_PGN65527();
		
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
		radioPositionLamp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPositionLamp();
			}
		});
		radioHeadLamp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickHeadLamp();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		HeadLamp = CAN1Comm.Get_HeadLampOperationStatus_3436_PGN65527();
		Illumination = CAN1Comm.Get_IlluminationOperationStatus_3438_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		MainLightLampDisplay(HeadLamp,Illumination);
	}
	/////////////////////////////////////////////////////////////////////	
	public int MainLightLampDisplay(int _headlamp, int _illumination){
		if(_headlamp == 0 && _illumination == 0){
			MainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF;
			radioOff.setChecked(true);
			radioPositionLamp.setChecked(false);
			radioHeadLamp.setChecked(false);
		}else if(_headlamp == 0 && _illumination == 1){
			MainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV1;
			radioOff.setChecked(false);
			radioPositionLamp.setChecked(true);
			radioHeadLamp.setChecked(false);
		}else if(_headlamp == 1 && _illumination == 1){
			MainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV2;
			radioOff.setChecked(false);
			radioPositionLamp.setChecked(false);
			radioHeadLamp.setChecked(true);
		}else{
			MainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF;
			radioOff.setChecked(true);
			radioPositionLamp.setChecked(false);
			radioHeadLamp.setChecked(false);
		}		
		
		return MainLampStatus;
	}
	public void ClickOff(){
		CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
		CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickPositionLamp(){
		CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
		CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickHeadLamp(){
		CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
		CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickHardKey(){
		switch (SelectMainLampStatus) {
		case CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF:
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV1;
			CursurIndex = 2;
			CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV1:
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV2;
			CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			CursurIndex = 3;
			break;
		case CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV2:
		default:
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF;
			CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
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
			ClickPositionLamp();
			break;
		case 3:
			ClickHeadLamp();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioOff.setPressed(false);
		radioPositionLamp.setPressed(false);
		radioHeadLamp.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioOff.setPressed(true);
			break;
		case 2:
			radioPositionLamp.setPressed(true);
			break;
		case 3:
			radioHeadLamp.setPressed(true);
			break;
		default:
			break;
		}
		
		ParentActivity.StartBackHomeTimer();
	}

}