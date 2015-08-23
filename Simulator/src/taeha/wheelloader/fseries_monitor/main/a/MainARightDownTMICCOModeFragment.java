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

public class MainARightDownTMICCOModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioH;
	
	RelativeLayout LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CCOMode;
	
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
		TAG = "MainARightDownTMICCOModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_a_tmiccomode, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		EnableRadioButton(false);
		StartEnableButtonTimer();
		ParentActivity.StartBackHomeTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_RIGHTDOWN_CCOMODE;
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
		Log.d(TAG,"onResume");
		TMCCOModeDisplay(CCOMode);	
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmiccomode_off);
		radioH = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmiccomode_h);
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_a_tmiccomode);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		Log.d(TAG,"InitValuables");
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		
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
		radioH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickH();
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
	public void TMCCOModeDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			CursurIndex = 1;
			radioOff.setChecked(true);
			radioH.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			CursurIndex = 2;
			radioOff.setChecked(false);
			radioH.setChecked(true);

			break;
		default:
			break;
		}
		CursurDisplay(CursurIndex);	

	}

	
	public void ClickOff(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();

	}
	public void ClickH(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H);
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
		ParentActivity.StartBackHomeTimer();
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
		ParentActivity.StartBackHomeTimer();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOff();
			break;
		case 2:
			ClickH();
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

		radioOff.setClickable(bEnable);
		radioH.setClickable(bEnable);
	}
		
	public void CursurDisplay(int Index){
		radioOff.setPressed(false);
		radioH.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioOff.setPressed(true);
			break;
		case 2:
			radioH.setPressed(true);
			break;
		default:
			break;
		}
	}
}