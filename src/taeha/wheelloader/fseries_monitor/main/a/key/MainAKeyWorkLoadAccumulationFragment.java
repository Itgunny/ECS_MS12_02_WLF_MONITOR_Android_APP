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
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainAKeyWorkLoadAccumulationFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAuto;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingSystemMode;
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
		 TAG = "MainAKeyWorkLoadAccumulationFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_workload_accumulation, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ACCUMULATION;
		ErrorDetectDisplay(WeighingSystemMode);
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
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_accumulation_manual);
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_accumulation_auto);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		WeighingSystemMode = CAN1Comm.Get_WeighingSystemAccumulationMode_1941_PGN65450();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickManual();
			}
		});
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
			CursurIndex = 1;
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			CursurIndex = 2;
			break;
		
		default:
			break;
		}
		CursurDisplay(CursurIndex);
	}
	public void ClickManual(){
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL);
		CAN1Comm.TxCANToMCU(62);
		showWorkLoadAnimation();
	}
	public void ClickAuto(){
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		CAN1Comm.TxCANToMCU(62);
		showWorkLoadAnimation();
	}
	
	public void showWorkLoadAnimation(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ACCUMULATION;
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment = new MainAKeyWorkLoadFragment();
		ParentActivity._MainABaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment);
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
			ClickManual();
			break;
		case 2:
			ClickAuto();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioManual.setPressed(false);
		radioAuto.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioManual.setPressed(true);
			break;
		case 2:
			radioAuto.setPressed(true);
			break;
		default:
			break;
		}
	}

	
}