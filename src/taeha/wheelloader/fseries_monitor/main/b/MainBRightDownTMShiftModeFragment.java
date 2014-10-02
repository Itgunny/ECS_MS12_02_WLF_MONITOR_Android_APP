package taeha.wheelloader.fseries_monitor.main.b;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBRightDownTMShiftModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAL;
	RadioButton radioAN;
	RadioButton radioAH;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int ShiftMode;
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
		TAG = "MainBRightDownTMShiftModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_b_tmshiftmode, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_RIGHTDOWN_SHIFTMODE;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TMShfitModeDisplay(ShiftMode);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_b_tmshiftmode_manual);
		radioAL = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_b_tmshiftmode_al);
		radioAN = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_b_tmshiftmode_an);
		radioAH = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_b_tmshiftmode_ah);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickManual();
			}
		});
		radioAL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAL();
			}
		});
		radioAN.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAN();
			}
		});
		radioAH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAH();
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
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void TMShfitModeDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
			radioManual.setChecked(true);
			radioAL.setChecked(false);
			radioAN.setChecked(false);
			radioAH.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			radioManual.setChecked(false);
			radioAL.setChecked(true);
			radioAN.setChecked(false);
			radioAH.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			radioManual.setChecked(false);
			radioAL.setChecked(false);
			radioAN.setChecked(true);
			radioAH.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			radioManual.setChecked(false);
			radioAL.setChecked(false);
			radioAN.setChecked(false);
			radioAH.setChecked(true);

			break;
		default:
			break;
		}

	}
	
	public void ClickManual(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickAL(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickAN(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickAH(){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
	}
}