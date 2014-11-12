package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class BrightnessManualFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int TOTAL_STEP = 8;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	SeekBar		seekbarLevel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BrightnessManualAuto;
	int BrightnessManualLevel;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
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
		 TAG = "BrightnessManualFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_brightness_manual, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP;
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_brightness_manual_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_brightness_manual_low_cancel);
		seekbarLevel = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_preference_brightness_manual);
		
		seekbarLevel.setMax(Home.BRIGHTNESS_MAX);
		seekbarLevel.incrementProgressBy(1);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		BrightnessManualLevel = ParentActivity.BrightnessManualLevel;
		BrightnessManualAuto = Home.BRIGHTNESS_MANUAL;
		
		seekbarLevel.setProgress(BrightnessManualLevel);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		seekbarLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				BrightnessManualLevel = seekBar.getProgress();
				CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(BrightnessManualLevel + 1);
				CAN1Comm.TxCANToMCU(109);
				CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, BrightnessManualLevel + 1);
				
				ParentActivity.BrihgtnessCurrent = BrightnessManualLevel;
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
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
		
		ParentActivity.BrightnessManualLevel = BrightnessManualLevel;
		ParentActivity.BrightnessManualAuto = BrightnessManualAuto;
		SavePref();
		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(BrightnessManualLevel + 1);
		CAN1Comm.TxCANToMCU(109);
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, ParentActivity.BrightnessManualLevel + 1);
		ParentActivity.BrihgtnessCurrent = BrightnessManualLevel;
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);

		
		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(ParentActivity.BrightnessManualLevel + 1);
		CAN1Comm.TxCANToMCU(109);
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, ParentActivity.BrightnessManualLevel + 1);
		ParentActivity.BrihgtnessCurrent = ParentActivity.BrightnessManualLevel;
	}
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("BrightnessManualLevel", BrightnessManualLevel);
		edit.putInt("BrightnessManualAuto", BrightnessManualAuto);
		
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	
}
