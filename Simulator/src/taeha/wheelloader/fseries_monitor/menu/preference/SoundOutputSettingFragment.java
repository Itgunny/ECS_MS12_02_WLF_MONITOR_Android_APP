package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
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
import android.widget.SeekBar;

public class SoundOutputSettingFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

	RadioButton radioInternal;
	RadioButton radioExternal;

	SeekBar		seekbarLevel;


	RelativeLayout layoutTime;
	//////////////////////////////////////////////////

	//VALUABLE////////////////////////////////////////
	int SoundState;
	int InternalSoundLevel;

	int CursurIndex;

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
		TAG = "MachineSecurityESLFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_soundoutput, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_CHANGE;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Sound_Output_Setting));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SoundOuputDisplay(SoundState);
		InternalSPKDisplayOnOff(SoundState);
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_soundoutput_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_soundoutput_low_cancel);

		radioInternal = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_soundoutput_internal_speaker);
		radioExternal = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_soundoutput_external_aux);

		layoutTime = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_soundoutput_internal_speaker);

		seekbarLevel = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_preference_soundoutput_internal);

		seekbarLevel.setMax(Home.INTERNAL_SPK_MAX);
		seekbarLevel.incrementProgressBy(1);		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();

		SoundState = ParentActivity.SoundState;
		InternalSoundLevel = ParentActivity.InternalSoundLevel;
		CursurFirstDisplay(SoundState);
		SoundOuputDisplay(SoundState);
		InternalSPKDisplayOnOff(SoundState);
		seekbarLevel.setProgress(InternalSoundLevel);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		radioInternal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickInternalSPK();
			}
		});
		radioExternal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickExternalAUX();
			}
		});
		seekbarLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
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
				InternalSoundLevel = seekBar.getProgress();
				CAN1Comm.setVolume(InternalSoundLevel);
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

		ParentActivity.SoundState = SoundState;
//		ParentActivity.InternalSoundLevel = InternalSoundLevel;
//		CAN1Comm.setVolume(InternalSoundLevel);
		ParentActivity.SavePref();
		try {
			CAN1Comm.LineOutfromJNI(SoundState);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG,"Load Library Error");
		}			

	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		CAN1Comm.setVolume(ParentActivity.InternalSoundLevel);

		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
	}
	public void ClickInternalSPK(){
		SoundState = Home.STATE_INTERNAL_SPK;
		SoundOuputDisplay(SoundState);
		InternalSPKDisplayOnOff(SoundState);
	}
	public void ClickExternalAUX(){
		SoundState = Home.STATE_EXTERNAL_AUX;
		SoundOuputDisplay(SoundState);
		InternalSPKDisplayOnOff(SoundState);
	}
	/////////////////////////////////////////////////////////////////////
	public void InternalSPKDisplayOnOff(int _outputmode){
		switch (_outputmode) {
		case Home.STATE_INTERNAL_SPK:
			layoutTime.setAlpha((float)1);
			seekbarLevel.setClickable(true);
			break;
		case Home.STATE_EXTERNAL_AUX:
			layoutTime.setAlpha((float)0.2);
			seekbarLevel.setClickable(false);
			break;
		default:
			break;
		}
	}
	public void SoundOuputDisplay(int _outputmode){
		switch (_outputmode) {
		case Home.STATE_INTERNAL_SPK:
			radioInternal.setChecked(true);
			radioExternal.setChecked(false);
			break;
		case Home.STATE_EXTERNAL_AUX:
			radioInternal.setChecked(false);
			radioExternal.setChecked(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			InternalSoundLevel -= 1;
			if(InternalSoundLevel < Home.INTERNAL_SPK_MIN){
				InternalSoundLevel = Home.INTERNAL_SPK_MIN;
			}
			seekbarLevel.setProgress(InternalSoundLevel);
			break;
		case 4:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 5:
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
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			InternalSoundLevel += 1;
			if(InternalSoundLevel > Home.INTERNAL_SPK_MAX){
				InternalSoundLevel = Home.INTERNAL_SPK_MAX;
			}
			seekbarLevel.setProgress(InternalSoundLevel);
			CursurDisplay(CursurIndex);

			break;
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 4:
		case 5:
			ClickCancel();
			break;
		default:
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickInternalSPK();
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			ClickExternalAUX();
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			ClickCancel();
			break;
		case 5:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case Home.STATE_INTERNAL_SPK:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case Home.STATE_EXTERNAL_AUX:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		radioInternal.setPressed(false);
		radioExternal.setPressed(false);
		seekbarLevel.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioInternal.setPressed(true);
			break;
		case 2:
			radioExternal.setPressed(true);
			break;
		case 3:
			seekbarLevel.setPressed(true);
			break;
		case 4:
			imgbtnCancel.setPressed(true);
			break;
		case 5:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;

		}
		/////////////////////////////////////////////////////////////////////

	}
}