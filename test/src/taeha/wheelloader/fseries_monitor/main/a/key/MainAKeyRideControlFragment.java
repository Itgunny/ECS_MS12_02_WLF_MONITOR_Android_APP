package taeha.wheelloader.fseries_monitor.main.a.key;

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

public class MainAKeyRideControlFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOnAlways;
	RadioButton radioOnConditional;
	
	ImageButton imgbtnOK;
	
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int RideControl;
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
		TAG = "MainAKeyRideControlFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_ridecontrol, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL;
		RideControlDisplay(RideControl);
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_ridecontrol_off);
		radioOnAlways = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_ridecontrol_on_always);
		radioOnConditional = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_ridecontrol_on_conditional);

		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_ridecontrol_low_ok);

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_ridecontrol_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_ridecontrol_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
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
		radioOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOnAlways();
			}
		});
		radioOnConditional.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOnConditional();
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
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RideControlDisplay(RideControl);
		KeyBGDisplay(RideControl);
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
	public void RideControlDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF:
			radioOff.setChecked(true);
			radioOnAlways.setChecked(false);
			radioOnConditional.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL:
			radioOff.setChecked(false);
			radioOnAlways.setChecked(true);
			radioOnConditional.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_AUTO:
			radioOff.setChecked(false);
			radioOnAlways.setChecked(false);
			radioOnConditional.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ClickOff(){
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF);	// Off
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOnAlways(){
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL);	// Off
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();	
	}
	public void ClickOnConditional(){
		ParentActivity._MainABaseFragment.showRideControlSpeedAnimation();
	}
	public void ClickOK(){
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();	
	}
	
	
}