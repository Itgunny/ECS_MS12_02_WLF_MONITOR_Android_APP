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

public class BrightnessAutoFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int TOTAL_STEP = 8;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	ImageButton imgbtnTimeStartLeft;
	ImageButton imgbtnTimeStartRight;
	ImageButton imgbtnTimeEndLeft;
	ImageButton imgbtnTimeEndRight;
	
	TextView textViewTimeStart;
	TextView textViewTimeEnd;
	
	SeekBar seekbarDayLevel;
	SeekBar seekbarNightLevel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	public int BrightnessManualAuto;
	public int BrightnessAutoDayLevel;
	public int BrightnessAutoNightLevel;
	public int BrightnessAutoStartTime;
	public int BrightnessAutoEndTime;
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
		 TAG = "BrightnessAutoFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_brightness_auto, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP;
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_brightness_auto_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_brightness_auto_low_cancel);
		
		imgbtnTimeStartLeft = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_start_minus);
		imgbtnTimeStartRight = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_start_plus);
		imgbtnTimeEndLeft = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_end_minus);
		imgbtnTimeEndRight = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_end_plus);
		
		textViewTimeStart = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_time_start_time);
		textViewTimeEnd = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_time_end_time);
		
		seekbarDayLevel = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_preference_brightness_auto_day);
		seekbarNightLevel = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_preference_brightness_auto_night);
		
		
		seekbarDayLevel.setMax(Home.BRIGHTNESS_MAX);
		seekbarDayLevel.incrementProgressBy(1);
		seekbarNightLevel.setMax(Home.BRIGHTNESS_MAX);
		seekbarNightLevel.incrementProgressBy(1);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		BrightnessManualAuto = Home.BRIGHTNESS_AUTO;
		BrightnessAutoDayLevel = ParentActivity.BrightnessAutoDayLevel;
		BrightnessAutoNightLevel = ParentActivity.BrightnessAutoNightLevel;
		BrightnessAutoStartTime = ParentActivity.BrightnessAutoStartTime;
		BrightnessAutoEndTime = ParentActivity.BrightnessAutoEndTime;
		
		seekbarDayLevel.setProgress(BrightnessAutoDayLevel);
		seekbarNightLevel.setProgress(BrightnessAutoNightLevel);
		
		textViewTimeStart.setText(Integer.toString(BrightnessAutoStartTime));
		textViewTimeEnd.setText(Integer.toString(BrightnessAutoEndTime));
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
		imgbtnTimeStartLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTimeStartLeft();
			}
		});
		imgbtnTimeStartRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTimeStartRight();
			}
		});
		imgbtnTimeEndLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTimeEndLeft();
			}
		});
		imgbtnTimeEndRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTimeEndRight();
			}
		});
		
		seekbarDayLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
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
		
				BrightnessAutoDayLevel = seekBar.getProgress();
				CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(BrightnessAutoDayLevel + 1);
				CAN1Comm.TxCANToMCU(109);
				CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, BrightnessAutoDayLevel + 1);
				
				ParentActivity.BrihgtnessCurrent = BrightnessAutoDayLevel;
			}
		});
		seekbarNightLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
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
				BrightnessAutoNightLevel = seekBar.getProgress();
				CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(BrightnessAutoNightLevel + 1);
				CAN1Comm.TxCANToMCU(109);
				CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, BrightnessAutoNightLevel + 1);
				
				ParentActivity.BrihgtnessCurrent = BrightnessAutoNightLevel;
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
		
		
		ParentActivity.BrightnessManualAuto = BrightnessManualAuto;
		ParentActivity.BrightnessAutoDayLevel = BrightnessAutoDayLevel;
		ParentActivity.BrightnessAutoNightLevel = BrightnessAutoNightLevel;
		ParentActivity.BrightnessAutoStartTime = BrightnessAutoStartTime;
		ParentActivity.BrightnessAutoEndTime = BrightnessAutoEndTime;

		SavePref();
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
	public void ClickTimeStartLeft(){
		if(BrightnessAutoStartTime > 24){
			BrightnessAutoStartTime = 23;
		}
			
		if(BrightnessAutoStartTime > 0){
			BrightnessAutoStartTime--;
		}
		textViewTimeStart.setText(Integer.toString(BrightnessAutoStartTime));
	}
	public void ClickTimeStartRight(){
		if(BrightnessAutoStartTime > 24){
			BrightnessAutoStartTime = 23;
		}
		
		if((BrightnessAutoStartTime + 1) < BrightnessAutoEndTime){
			BrightnessAutoStartTime++;
		}
		textViewTimeStart.setText(Integer.toString(BrightnessAutoStartTime));
	}
	public void ClickTimeEndLeft(){
		if(BrightnessAutoEndTime > 24){
			BrightnessAutoEndTime = 23;
		}
		if(BrightnessAutoEndTime > (BrightnessAutoStartTime + 1)){
			BrightnessAutoEndTime--;
		}
		textViewTimeEnd.setText(Integer.toString(BrightnessAutoEndTime));
	}
	public void ClickTimeEndRight(){
		if(BrightnessAutoEndTime > 24){
			BrightnessAutoEndTime = 23;
		}
		if(BrightnessAutoEndTime < 24){
			BrightnessAutoEndTime++;
		}
		textViewTimeEnd.setText(Integer.toString(BrightnessAutoEndTime));
	}
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("BrightnessManualAuto", BrightnessManualAuto);
		edit.putInt("BrightnessAutoDayLevel", BrightnessAutoDayLevel);
		edit.putInt("BrightnessAutoNightLevel", BrightnessAutoNightLevel);
		edit.putInt("BrightnessAutoStartTime", BrightnessAutoStartTime);
		edit.putInt("BrightnessAutoEndTime", BrightnessAutoEndTime);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	
}
