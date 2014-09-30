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

public class MainBKeyWorkLoadAccumulationFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBKeyWorkLoadAccumulationFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAuto;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingSystemMode;
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
		mRoot = inflater.inflate(R.layout.key_main_b_workload_accumulation, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ACCUMULATION;
		ErrorDetectDisplay(WeighingSystemMode);
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_accumulation_manual);
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_accumulation_auto);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		WeighingSystemMode = CAN1Comm.Get_WeightAccumulationMode_PGN65450();
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
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAuto();
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
	public void ErrorDetectDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			radioManual.setChecked(true);
			radioAuto.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			break;
		
		default:
			break;
		}
		
	}
	public void ClickManual(){
		CAN1Comm.Set_WeightAccumulationMode_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL);
		CAN1Comm.TxCANToMCU(62);
		showWorkLoadAnimation();
	}
	public void ClickAuto(){
		CAN1Comm.Set_WeightAccumulationMode_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
		showWorkLoadAnimation();
	}
	
	public void showWorkLoadAnimation(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD;
		ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment = new MainBKeyWorkLoadFragment();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment);
	}
	
}