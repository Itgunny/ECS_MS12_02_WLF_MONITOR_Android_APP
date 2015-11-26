package taeha.wheelloader.fseries_monitor.main.b;


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
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.a.MainARightUpEngineModeFragment.EnableButtonTimerClass;

public class MainBRightUpEngineModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView	textViewTitle;
	RadioButton radioPower;
	RadioButton radioStandard;
	RadioButton radioEcono;
	
	RelativeLayout LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
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
		TAG = "MainBRightUpEngineModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightup_main_b_enginemode, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		EnableRadioButton(false);
		StartEnableButtonTimer();
		ParentActivity.StartBackHomeTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_MODE;
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
		EngineModeDisplay(EngineMode);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_enginemode_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.ENGINE_MODE), 85));
		ParentActivity.setMarqueeText(textViewTitle);
		radioPower = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginemode_power);
		radioPower.setText(getString(ParentActivity.getResources().getString(R.string.POWER), 96));
		ParentActivity.setMarqueeText(radioPower);
		radioStandard = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginemode_standard);
		radioStandard.setText(getString(ParentActivity.getResources().getString(R.string.STANDARD), 95));
		ParentActivity.setMarqueeText(radioStandard);
		radioEcono = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginemode_econo);
		radioEcono.setText(getString(ParentActivity.getResources().getString(R.string.ECONO), 94));
		ParentActivity.setMarqueeText(radioEcono);
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightup_main_b_engine_mode_top);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioPower.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPower();
			}
		});
		radioStandard.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickStandard();
			}
		});
		radioEcono.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickEcono();
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
	public void EngineModeDisplay(int _EngineMode){
		switch (_EngineMode) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			CursurIndex = 3;
			radioPower.setChecked(true);
			radioStandard.setChecked(false);
			radioEcono.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			CursurIndex = 2;
			radioPower.setChecked(false);
			radioStandard.setChecked(true);
			radioEcono.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			CursurIndex = 1;
			radioPower.setChecked(false);
			radioStandard.setChecked(false);
			radioEcono.setChecked(true);
			break;
		default:
			break;
		}
		CursurDisplay(CursurIndex);		
	}
	public void ClickPower(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickStandard(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_STD);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickEcono(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
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
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
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
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
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
			ClickEcono();
			break;
		case 2:
			ClickStandard();
			break;
		case 3:
			ClickPower();
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

		radioPower.setClickable(bEnable);
		radioStandard.setClickable(bEnable);
		radioEcono.setClickable(bEnable);
	}

	public void CursurDisplay(int Index){
		radioPower.setPressed(false);
		radioStandard.setPressed(false);
		radioEcono.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioEcono.setPressed(true);
			break;
		case 2:
			radioStandard.setPressed(true);
			break;
		case 3:
			radioPower.setPressed(true);
			break;
		default:
			break;
		}
	}
}