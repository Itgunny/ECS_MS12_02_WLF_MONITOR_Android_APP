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

public class MainAKeyFineModulationFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;	
	
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int FineModulation;
	int SelectFineModulation;
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
		TAG = "MainAKeyFineModulationFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_finemodulation, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_FINEMODULATION;
		FineModulationDisplay(FineModulation);
		ClickHardKey();
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_finemodulation_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_finemodulation_on);

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_finemodulation_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_finemodulation_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		FineModulation = CAN1Comm.Get_FlowFineModulationOperation_2302_PGN65517();
		SelectFineModulation = FineModulation;
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
		FineModulation = CAN1Comm.Get_FlowFineModulationOperation_2302_PGN65517();;
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		EHCUDisplay(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU());
		FineModulationDisplay(FineModulation);
	}
	/////////////////////////////////////////////////////////////////////	
	public void EHCUDisplay(int Data){
		switch (Data){
		case CAN1CommManager.STATE_COMPONENTCODE_EHCU:
			layoutAvailable.setVisibility(View.VISIBLE);
			layoutNotAvailable.setVisibility(View.INVISIBLE);
			break;
		default:
			layoutAvailable.setVisibility(View.INVISIBLE);
			layoutNotAvailable.setVisibility(View.VISIBLE);
			break;
		}
	}
	public void FineModulationDisplay(int Data){
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
		
		switch (SelectFineModulation) {
		case CAN1CommManager.DATA_STATE_OFF:
		default:
			SelectFineModulation = CAN1CommManager.DATA_STATE_ON;
			CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(CAN1CommManager.DATA_STATE_ON);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(3);
			break;
		case CAN1CommManager.DATA_STATE_ON:
			SelectFineModulation = CAN1CommManager.DATA_STATE_OFF;
			CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(3);
			break;
		}
		
	}
	public void ClickOff(){
		CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(CAN1CommManager.DATA_STATE_OFF);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(3);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOn(){
		CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(CAN1CommManager.DATA_STATE_ON);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(3);
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	
}