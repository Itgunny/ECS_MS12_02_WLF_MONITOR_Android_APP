package taeha.wheelloader.fseries_monitor.main.b;

import java.util.Timer;
import java.util.TimerTask;

import android.content.SharedPreferences;
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
import taeha.wheelloader.fseries_monitor.main.a.MainARightUpHourOdometerSelectFragment.EnableButtonTimerClass;

public class MainBRightUpHourOdometerSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioLatestHour;
	RadioButton radioTotalOdo;
	RadioButton radioLatestOdo;
	
	RelativeLayout LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
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
		TAG = "MainBRightUpHourOdometerSelectFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightup_main_b_hourodometer_select, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		EnableRadioButton(false);
		StartEnableButtonTimer();
		ParentActivity.StartBackHomeTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_RIGHTUP_HOURODMETER;
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
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		SavePref();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		HourOdometerDisplay(ParentActivity.HourOdometerIndex);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioLatestHour = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_hourodometer_select_latesthourmeter);
		radioTotalOdo = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_hourodometer_select_totalodometer);
		radioLatestOdo = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_hourodometer_select_latestodometer);
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightup_main_b_hourodomterhourodo);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioLatestHour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickLatestHourmeter();
			}
		});
		radioTotalOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTotalOdometer();
			}
		});
		radioLatestOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickLatestOdometer();
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
	public void HourOdometerDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
//			radioLatestHour.setChecked(false);
//			radioTotalOdo.setChecked(false);
//			radioLatestOdo.setChecked(false);
//			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			radioLatestHour.setChecked(true);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			radioLatestHour.setChecked(false);
			radioTotalOdo.setChecked(true);
			radioLatestOdo.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			radioLatestHour.setChecked(false);
			radioTotalOdo.setChecked(false);
			radioLatestOdo.setChecked(true);

			break;
		default:
			break;
		}
		CursurIndex = Data;
		CursurDisplay(CursurIndex);		
		
	}
	public void ClickLatestHourmeter(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_HOURMETER_LATEST;
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickTotalOdometer(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_ODOMETER_TOTAL;
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickLatestOdometer(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.HourOdometerIndex = CAN1CommManager.DATA_STATE_ODOMETER_LATEST;
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("HourOdometerIndex", ParentActivity.HourOdometerIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
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
			ClickLatestHourmeter();
			break;
		case 2:
			ClickTotalOdometer();
			break;
		case 3:
			ClickLatestOdometer();
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

		radioLatestHour.setClickable(bEnable);
		radioTotalOdo.setClickable(bEnable);
		radioLatestOdo.setClickable(bEnable);
	}
	public void CursurDisplay(int Index){
		radioLatestHour.setPressed(false);
		radioTotalOdo.setPressed(false);
		radioLatestOdo.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioLatestHour.setPressed(true);
			break;
		case 2:
			radioTotalOdo.setPressed(true);
			break;
		case 3:
			radioLatestOdo.setPressed(true);
			break;
		default:
			break;
		}
	}
}
