package taeha.wheelloader.fseries_monitor.main.a;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainARightDownTMShiftModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAL;
	RadioButton radioAN;
	RadioButton radioAH;
	
	RelativeLayout LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int ShiftMode;
	
	int CursurIndex;
	Handler HandleCursurDisplay;
	
	Timer	mEnableButtonTimer = null;
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
		TAG = "MainARightDownTMShiftModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_a_tmshiftmode, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		EnableRadioButton(false);
		StartEnableButtonTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_RIGHTDOWN_SHIFTMODE;
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};		
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
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmshiftmode_manual);
		radioAL = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmshiftmode_al);
		radioAN = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmshiftmode_an);
		radioAH = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmshiftmode_ah);
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_a_tmshiftmode);
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
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickManual();
			}
		});
		radioAL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAL();
			}
		});
		radioAN.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAN();
			}
		});
		radioAH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
		CursurIndex = Data+1;
		CursurDisplay(CursurIndex);	

	}
	
	public void ClickManual(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickAL(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickAN(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickAH(){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public class EnableButtonTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ParentActivity.AnimationRunningFlag == false)
					{
						CancelEnableButtonTimer();
						EnableRadioButton(true);
					}
				}
			});
			
		}
		
	}
	
	public void StartEnableButtonTimer(){
		CancelEnableButtonTimer();
		mEnableButtonTimer = new Timer();
		mEnableButtonTimer.schedule(new EnableButtonTimerClass(),1,50);	
	}
	
	public void CancelEnableButtonTimer(){
		if(mEnableButtonTimer != null){
			mEnableButtonTimer.cancel();
			mEnableButtonTimer.purge();
			mEnableButtonTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
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
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
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
			ClickAL();
			break;
		case 3:
			ClickAN();
			break;
		case 4:
			ClickAH();
			break;
		default:

			break;
		}
	}

	public void EnableRadioButton(boolean bEnable){
		float alpha;
		if(bEnable == true)
			alpha = (float)1;
		else
			alpha = (float)0;
		
		LayoutBG.setAlpha(alpha);
		
		radioManual.setClickable(bEnable);
		radioAL.setClickable(bEnable);
		radioAN.setClickable(bEnable);
		radioAH.setClickable(bEnable);
	}
		
	public void CursurDisplay(int Index){
		radioManual.setPressed(false);
		radioAL.setPressed(false);
		radioAN.setPressed(false);
		radioAH.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioManual.setPressed(true);
			break;
		case 2:
			radioAL.setPressed(true);
			break;
		case 3:
			radioAN.setPressed(true);
			break;
		case 4:
			radioAH.setPressed(true);
			break;
		default:
			break;
		}
	}

}