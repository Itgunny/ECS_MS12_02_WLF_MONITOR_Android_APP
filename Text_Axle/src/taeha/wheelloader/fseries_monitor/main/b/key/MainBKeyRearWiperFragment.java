package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBKeyRearWiperFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioIntermittent;
	RadioButton radioLow;
	
	TextView textViewWasher;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WiperSpeedState;
	int WiperWasherState;
	
	int SelectWiperSpeedState;
	
	int SendCount;
	
//	int initState;	// ++, 150211 bwk
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
		TAG = "MainBKeyRearWiperFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_rearwiper, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_REARWIPER;
		RearWiperSpeedDisplay(WiperSpeedState);
		ClickHardKey();
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
		CAN1Comm.Set_RearWiperWasherOperationStatus_3452_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_WASHER_OFF);
		CAN1Comm.TxCANToMCU(247);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_rearwiper_off);
		radioIntermittent = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_rearwiper_intermittent);
		radioLow = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_rearwiper_low);
		
		textViewWasher = (TextView)mRoot.findViewById(R.id.textView_key_main_b_rearwiper_washer);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		WiperSpeedState = CAN1Comm.Get_RearWiperOperationStatus_3451_PGN65527();
		WiperWasherState = CAN1Comm.Get_RearWiperWasherOperationStatus_3452_PGN65527();
		
		SelectWiperSpeedState = WiperSpeedState;		// ++, --, 150331 bwk
		
		SendCount = 0;
//		initState = 0;		// ++, 150210 bwk
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
		radioIntermittent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickIntermittent();
			}
		});
		radioLow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickLow();
			}
		});
		textViewWasher.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG,"textViewWasher Click");
				CAN1Comm.Set_RearWiperWasherOperationStatus_3452_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_WASHER_OFF);
				CAN1Comm.TxCANToMCU(247);
				CAN1Comm.Set_RearWiperWasherOperationStatus_3452_PGN65527(3);
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		WiperSpeedState = CAN1Comm.Get_RearWiperOperationStatus_3451_PGN65527();
		WiperWasherState = CAN1Comm.Get_RearWiperWasherOperationStatus_3452_PGN65527();
		
		SelectWiperSpeedState = WiperSpeedState;
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RearWiperSpeedDisplay(WiperSpeedState);
		RearWiperWasherDisplay(WiperWasherState);
		CheckRearWiperWasher();
	}
	/////////////////////////////////////////////////////////////////////	
	public void RearWiperSpeedDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF:
			radioOff.setChecked(true);
			radioIntermittent.setChecked(false);
			radioLow.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT:
			radioOff.setChecked(false);
			radioIntermittent.setChecked(true);
			radioLow.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW:
			radioOff.setChecked(false);
			radioIntermittent.setChecked(false);
			radioLow.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void RearWiperWasherDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_WASHER_OFF:
			textViewWasher.setBackgroundResource(R.drawable._selector_key_rearwiper_washer_btn1);
			textViewWasher.setTextColor(ParentActivity.getResources().getColor(R.color.white));
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_WASHER_ON:
			textViewWasher.setBackgroundResource(R.drawable.main_key_washer_btn_selected);
			textViewWasher.setTextColor(ParentActivity.getResources().getColor(R.color.black));
			break;
		default:
			break;
		}
	}
	public void CheckRearWiperWasher(){
		if(SendCount > 5){
			if(textViewWasher.isPressed() == true 	// Washer Button Push
			|| (CAN1Comm.Get_RX_RES_KEY_LongFlag() == 1 && CAN1Comm.Get_RX_RES_KEY_RearWiper() == 1 )){	// RearWiper Long Key	
				CAN1Comm.Set_RearWiperWasherOperationStatus_3452_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_WASHER_ON);
				CAN1Comm.TxCANToMCU(247);
				// ++, 150211 bwk
				// LongŰ�� ������ ���� �� ������ �ٲ��� �ʴٴ� ���̹Ƿ� �Ѵܰ� �������� ����.
//				if(initState>=2)
//					ReturnStatusWiperSpeedState();
				// --, 150211 bwk
				Log.d(TAG,"Washer On");
			}else{
				CAN1Comm.Set_RearWiperWasherOperationStatus_3452_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_WASHER_OFF);
				CAN1Comm.TxCANToMCU(247);
				Log.d(TAG,"Washer Off");
			}
			SendCount = 0;
		}
		SendCount++;
	}
	// ++, 150211 bwk
	public void ReturnStatusWiperSpeedState(){
		switch (SelectWiperSpeedState) {
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF:
		default:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			CursurIndex = 3;
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			CursurIndex = 1;
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			CursurIndex = 2;
			break;
		}		
		CursurDisplay(CursurIndex);
	}
	// --, 150211 bwk
	public void ClickHardKey(){
		
		switch (SelectWiperSpeedState) {
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF:
		default:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			CursurIndex = 2;
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			CursurIndex = 3;
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			CursurIndex = 1;
			break;
		}
		CursurDisplay(CursurIndex);
//		initState++;	// ++, 150211 bwk
	}
	
	public void ClickOff(){
		CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
	}
	public void ClickIntermittent(){
		CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
	}
	public void ClickLow(){
		CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
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
			ClickIntermittent();
			break;
		case 3:
			ClickLow();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioOff.setPressed(false);
		radioIntermittent.setPressed(false);
		radioLow.setPressed(false);
		textViewWasher.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioOff.setPressed(true);
			break;
		case 2:
			radioIntermittent.setPressed(true);
			break;
		case 3:
			radioLow.setPressed(true);
			break;
		default:
			break;
		}
	}
}