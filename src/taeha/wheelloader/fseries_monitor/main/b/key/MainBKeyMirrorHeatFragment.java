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

public class MainBKeyMirrorHeatFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBKeyMirrorHeatFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int MirrorHeat;
	int SelectMirrorHeat;
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
		mRoot = inflater.inflate(R.layout.key_main_b_mirrorheat, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_MIRRORHEAT;
		MirrorHeatDisplay(MirrorHeat);
		ClickHardKey();
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_mirrorheat_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_mirrorheat_on);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		MirrorHeat = CAN1Comm.Get_MirrorHeatOperationStatus_3450_PGN65527();
		SelectMirrorHeat = MirrorHeat;
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
		radioOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOn();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		MirrorHeat = CAN1Comm.Get_MirrorHeatOperationStatus_3450_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		MirrorHeatDisplay(MirrorHeat);
	}
	/////////////////////////////////////////////////////////////////////	
	public void MirrorHeatDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_OFF:
			radioOff.setChecked(true);
			radioOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ON:
			radioOff.setChecked(false);
			radioOn.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ClickHardKey(){
		
		switch (SelectMirrorHeat) {
		case CAN1CommManager.DATA_STATE_OFF:
		default:
			SelectMirrorHeat = CAN1CommManager.DATA_STATE_ON;
			CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_ON);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
			ParentActivity.StartMirrorHeatTimer();
			break;
		case CAN1CommManager.DATA_STATE_ON:
			SelectMirrorHeat = CAN1CommManager.DATA_STATE_OFF;
			CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
			ParentActivity.CancelMirrorHeatTimer();
			break;
		}

		
	}
	public void ClickOff(){
		CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_OFF);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
		ParentActivity.CancelMirrorHeatTimer();
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOn(){
		CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_ON);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
		ParentActivity.StartMirrorHeatTimer();
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	
	
}