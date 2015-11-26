package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	TextFitTextView	textViewOK;
	TextFitTextView	textViewCancel;
	
	
	ImageButton imgbtnTimeStartLeft;
	ImageButton imgbtnTimeStartRight;
	ImageButton imgbtnTimeEndLeft;
	ImageButton imgbtnTimeEndRight;
	
	TextFitTextView textViewTimeStart;
	TextFitTextView textViewTimeEnd;
	
	SeekBar seekbarDayLevel;
	SeekBar seekbarNightLevel;
	
	TextFitTextView textViewTimeTitle;
	TextFitTextView textViewDayTimeTitle;
	TextFitTextView textViewNightTimeTitle;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	public int BrightnessManualAuto;
	public int BrightnessAutoDayLevel;
	public int BrightnessAutoNightLevel;
	public int BrightnessAutoStartTime;
	public int BrightnessAutoEndTime;
	
	int CursurIndex = 1;
	
	Handler HandleCursurDisplay;
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
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_brightness_auto_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_brightness_auto_low_cancel);
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
		
		imgbtnTimeStartLeft = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_start_minus);
		imgbtnTimeStartRight = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_start_plus);
		imgbtnTimeEndLeft = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_end_minus);
		imgbtnTimeEndRight = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_brightness_auto_time_end_plus);
		
		textViewTimeStart = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_time_start_time);
		textViewTimeEnd = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_time_end_time);
		
		seekbarDayLevel = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_preference_brightness_auto_day);
		seekbarNightLevel = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_preference_brightness_auto_night);
		
		
		seekbarDayLevel.setMax(Home.BRIGHTNESS_MAX);
		seekbarDayLevel.incrementProgressBy(1);
		seekbarNightLevel.setMax(Home.BRIGHTNESS_MAX);
		seekbarNightLevel.incrementProgressBy(1);
		
		textViewTimeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_time_title);
		textViewTimeTitle.setText(getString(ParentActivity.getResources().getString(R.string.Time_settings_for_daytime), 417));
		textViewDayTimeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_day_title);
		textViewDayTimeTitle.setText(getString(ParentActivity.getResources().getString(R.string.Daytime), 418));
		textViewNightTimeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_brightness_auto_night_title);
		textViewNightTimeTitle.setText(getString(ParentActivity.getResources().getString(R.string.Nighttime), 419));
		
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
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		imgbtnTimeStartLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTimeStartLeft();
			}
		});
		imgbtnTimeStartRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTimeStartRight();
			}
		});
		imgbtnTimeEndLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTimeEndLeft();
			}
		});
		imgbtnTimeEndRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTimeEndRight();
			}
		});
		
		seekbarDayLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
	public void setCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
		case 2:
			ParentActivity._MenuBaseFragment._BrightnessFragment.ClickLeft();
			break;
		case 3:
			CursurIndex = 10;
			CursurDisplay(CursurIndex);
			break;
		case 4:
		case 5:
		case 6:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			BrightnessAutoDayLevel -= 1;
			if(BrightnessAutoDayLevel < Home.BRIGHTNESS_MIN){
				BrightnessAutoDayLevel = Home.BRIGHTNESS_MIN;
			}
			seekbarDayLevel.setProgress(BrightnessAutoDayLevel);
			break;
		case 8:
			BrightnessAutoNightLevel -= 1;
			if(BrightnessAutoNightLevel < Home.BRIGHTNESS_MIN){
				BrightnessAutoNightLevel = Home.BRIGHTNESS_MIN;
			}
			seekbarNightLevel.setProgress(BrightnessAutoNightLevel);
			break;
		case 9:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 10:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
		case 2:
			ParentActivity._MenuBaseFragment._BrightnessFragment.ClickRight();
			break;
		case 3:
		case 4:
		case 5:
		case 6:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			BrightnessAutoDayLevel += 1;
			if(BrightnessAutoDayLevel > Home.BRIGHTNESS_MAX){
				BrightnessAutoDayLevel = Home.BRIGHTNESS_MAX;
			}
			seekbarDayLevel.setProgress(BrightnessAutoDayLevel);
			break;
		case 8:
			BrightnessAutoNightLevel += 1;
			if(BrightnessAutoNightLevel > Home.BRIGHTNESS_MAX){
				BrightnessAutoNightLevel = Home.BRIGHTNESS_MAX;
			}
			seekbarNightLevel.setProgress(BrightnessAutoNightLevel);
			break;
		case 9:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			
			break;
		case 10:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			
			break;
		default:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 1:
		case 2:
			ClickCancel();
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			ParentActivity._MenuBaseFragment._BrightnessFragment.setCursurIndex(2);
			ParentActivity._MenuBaseFragment._BrightnessFragment.CursurDisplay(2);
			break;
		default:
			break;
		}
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
		case 2:
			ParentActivity._MenuBaseFragment._BrightnessFragment.ClickEnter();
			break;
		case 3:
			ClickTimeStartLeft();
			break;
		case 4:
			ClickTimeStartRight();
			break;
		case 5:
			ClickTimeEndLeft();
			break;
		case 6:
			ClickTimeEndRight();
			break;
		case 7:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 9:
			ClickCancel();
			break;
		case 10:
			ClickOK();
			break;
		default:

			break;
		}
	}
	public void CursurDisplay(int Index){
		try {
			imgbtnCancel.setPressed(false);
			imgbtnOK.setPressed(false);
			imgbtnTimeStartLeft.setPressed(false);
			imgbtnTimeStartRight.setPressed(false);
			imgbtnTimeEndLeft.setPressed(false);
			imgbtnTimeEndRight.setPressed(false);
			seekbarDayLevel.setPressed(false);
			seekbarNightLevel.setPressed(false);
			switch (CursurIndex) {
				case 3:
					imgbtnTimeStartLeft.setPressed(true);
					break;
				case 4:
					imgbtnTimeStartRight.setPressed(true);
					break;
				case 5:
					imgbtnTimeEndLeft.setPressed(true);
					break;
				case 6:
					imgbtnTimeEndRight.setPressed(true);
					break;
				case 7:
					seekbarDayLevel.setPressed(true);
					break;
				case 8:
					seekbarNightLevel.setPressed(true);
					break;
				case 9:
					imgbtnCancel.setPressed(true);
					break;
				case 10:
					imgbtnOK.setPressed(true);
					break;
				default:
					break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException CursurDisplay");
		}
	
	}
	/////////////////////////////////////////////////////////////////////
	public String getString(String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			Log.d(TAG, "Android");
			return str;
		}else {
			Log.d(TAG, "Excel");
			return ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex);	
		}
	}
	
}
