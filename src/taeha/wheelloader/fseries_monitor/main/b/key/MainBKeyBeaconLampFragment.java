package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBKeyBeaconLampFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BeaconLamp;
	int SelectBeaconLamp;
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
		TAG = "MainBKeyBeaconLampFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_beaconlamp, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_BEACONLAMP;
		
		BeaconLampDisplay(BeaconLamp);
		ClickHardKey();
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_beaconlamp_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_beaconlamp_on);

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_beaconlamp_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_beaconlamp_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		BeaconLamp = CAN1Comm.Get_BeaconLampOperationStatus_3444_PGN65527();
		SelectBeaconLamp = BeaconLamp;
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
		BeaconLamp = CAN1Comm.Get_BeaconLampOperationStatus_3444_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BeaconLampDisplay(BeaconLamp);
		KeyBGDisplay(BeaconLamp);
	}
	/////////////////////////////////////////////////////////////////////	
	public void KeyBGDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			layoutAvailable.setVisibility(View.INVISIBLE);
			layoutNotAvailable.setVisibility(View.VISIBLE);
			break;
		default:
			layoutAvailable.setVisibility(View.VISIBLE);
			layoutNotAvailable.setVisibility(View.INVISIBLE);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void BeaconLampDisplay(int Data){
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
		
		switch (SelectBeaconLamp) {
		case CAN1CommManager.DATA_STATE_OFF:
		default:
			SelectBeaconLamp = CAN1CommManager.DATA_STATE_ON;
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(CAN1CommManager.DATA_STATE_ON);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(3);
			break;
		case CAN1CommManager.DATA_STATE_ON:
			SelectBeaconLamp = CAN1CommManager.DATA_STATE_OFF;
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(3);
			break;
		}

		
	}
	public void ClickOff(){
		CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(CAN1CommManager.DATA_STATE_OFF);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(3);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOn(){
		CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(CAN1CommManager.DATA_STATE_ON);
		CAN1Comm.TxCANToMCU(247);
		CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(3);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	
}