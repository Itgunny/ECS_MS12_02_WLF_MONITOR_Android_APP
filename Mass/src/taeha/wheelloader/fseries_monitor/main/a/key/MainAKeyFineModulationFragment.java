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
	int CursurIndex;
	int OldOption;
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
		TAG = "MainAKeyFineModulationFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_finemodulation, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_FINEMODULATION;
		FineModulationDisplay(FineModulation);
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
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_finemodulation_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_finemodulation_on);

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_finemodulation_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_finemodulation_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		OldOption = -1;
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
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOff();
			}
		});
		radioOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
		if(OldOption != Data)
		{
			OldOption = Data;
			switch (Data){
				case CAN1CommManager.STATE_COMPONENTCODE_EHCU:
					layoutAvailable.setVisibility(View.VISIBLE);
					layoutNotAvailable.setVisibility(View.INVISIBLE);
					break;
				default:
					ParentActivity.StartBackHomeTimer();
					layoutAvailable.setVisibility(View.INVISIBLE);
					layoutNotAvailable.setVisibility(View.VISIBLE);
					break;
			}

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
			CursurIndex = 2;
			break;
		case CAN1CommManager.DATA_STATE_ON:
			SelectFineModulation = CAN1CommManager.DATA_STATE_OFF;
			CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_FlowFineModulationOperation_2302_PGN61184_203(3);
			CursurIndex = 1;
			break;
		}
		
		CursurDisplay(CursurIndex);
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
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
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
			ClickOn();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioOff.setPressed(false);
		radioOn.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioOff.setPressed(true);
			break;
		case 2:
			radioOn.setPressed(true);
			break;
		default:
			break;
		}
		ParentActivity.StartBackHomeTimer();
	}

	
}