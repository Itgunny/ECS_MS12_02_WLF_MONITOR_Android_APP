package taeha.wheelloader.fseries_monitor.main.b.key;

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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBKeyWorkLoadAccumulationFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView	textViewTitle;
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
		 TAG = "MainBKeyWorkLoadAccumulationFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_workload_accumulation, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ACCUMULATION;
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
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_workload_accumulation_line);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Weighing_System), 114));
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_accumulation_manual);
		radioManual.setText(getString(ParentActivity.getResources().getString(R.string.Manual), 26));
		ParentActivity.setMarqueeRadio(radioManual);
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_accumulation_auto);
		radioAuto.setText(getString(ParentActivity.getResources().getString(R.string.Automatic), 27));
		ParentActivity.setMarqueeRadio(radioAuto);
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
			CursurIndex = 1;
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

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD;
		ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment = new MainBKeyWorkLoadFragment();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment);
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