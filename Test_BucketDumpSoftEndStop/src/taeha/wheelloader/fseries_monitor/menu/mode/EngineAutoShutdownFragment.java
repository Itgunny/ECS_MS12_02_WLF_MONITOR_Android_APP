package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
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
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class EngineAutoShutdownFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private  final int MAX_LEVEL	 	= 40;
	private  final int MIN_LEVEL 		= 2;
	
	private  final int TOTAL_STEP		= 38;
	private  final int STEP				= 1;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextFitTextView textViewOK;
	ImageButton	imgbtnCancel;
	TextFitTextView textViewCancel;
	
	TextFitTextView	textViewTime;
	TextFitTextView textViewMax;
	TextFitTextView textViewMin;
	
	RadioButton radioOnOnce;
	RadioButton radioOnAlways;
	RadioButton radioOff;
	
	SeekBar seekbarTime;
	
	RelativeLayout layoutTime;
	RelativeLayout layoutESL;
	
	ImageView imgViewLine;
	
	TextFitTextView textViewESLTitle;
	TextFitTextView textViewESL;
	
	ImageView imgViewESL;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineAutoShutdownTime;
	int EngineAutoShutdownStatus;
	int EngineAutoShutdownType;
	
	int ESLInterval;
	int ESLMode;
	
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
		 TAG = "EngineAutoShutdownFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_engineautoshutdown, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Engine_Auto_Shutdown), 215);
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
		CursurFirstDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
	}
	////////////////////////////////////////////////
	
	

	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_engineautoshutdown_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_engineautoshutdown_low_cancel);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Cancel), 16));

		textViewTime = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_time);
		textViewMax = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_max);
		textViewMin = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_min);

		radioOnOnce = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_engineautoshutdown_use_on_once);
		radioOnOnce.setText(getString(ParentActivity.getResources().getString(string.On_Once), 24));
		ParentActivity.setMarqueeRadio(radioOnOnce);
		radioOnAlways = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_engineautoshutdown_use_on_always);
		radioOnAlways.setText(getString(ParentActivity.getResources().getString(string.On_Always), 23));
		ParentActivity.setMarqueeRadio(radioOnAlways);
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_engineautoshutdown_use_off);
		radioOff.setText(getString(ParentActivity.getResources().getString(string.Disable), 21));
		ParentActivity.setMarqueeRadio(radioOff);
	
		seekbarTime = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_mode_engineautoshutdown);
		
		layoutTime = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_mode_engineautoshutdown_time);
		layoutESL = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_mode_engineautoshutdown_esl);
		
		imgViewLine = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_engineautoshutdown_line2);
		
		textViewESLTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_esl_title);
		textViewESLTitle.setText(getString(ParentActivity.getResources().getString(string.ESL_System_Setting), 324));
		
		textViewESL = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_esl_time);
		imgViewESL = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_engineautoshutdown_esl_check);
		
		seekbarTime.setMax(TOTAL_STEP);
		seekbarTime.incrementProgressBy(1);
		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		Log.d(TAG,"InitValuables");
		if(ParentActivity.EngineAutoShutdownESLFlag == true){
			EngineAutoShutdownTime = ParentActivity.EngineAutoShutdownTime;
			EngineAutoShutdownStatus = ParentActivity.EngineAutoShutdownStatus;
			EngineAutoShutdownType = ParentActivity.EngineAutoShutdownType;
			ParentActivity.EngineAutoShutdownESLFlag = false;
			
			if(ParentActivity.EngineAutoShutdownESLSetFlag == true){
				
				ESLMode = ParentActivity.ESLMode;
				ESLInterval = ParentActivity.ESLInterval;
			}else{
				ESLMode = CAN1Comm.Get_ESLMode_820_PGN65348();
				ESLInterval = CAN1Comm.Get_ESLInterval_825_PGN65348();
			}
			
		}else{
			EngineAutoShutdownTime = CAN1Comm.Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122();
			EngineAutoShutdownStatus = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN61184_122();
			EngineAutoShutdownType = CAN1Comm.Get_AutomaticEngineShutdownType_PGN61184_122();
			ESLMode = CAN1Comm.Get_ESLMode_820_PGN65348();
			ESLInterval = CAN1Comm.Get_ESLInterval_825_PGN65348();
		}
		
		// ++, 150212 bwk
		// 받은 값이 2분 이하 이거나 40분 이상일 경우 강제로 맞춤
		if(EngineAutoShutdownTime < 12)
			EngineAutoShutdownTime = 12;
		else if(EngineAutoShutdownTime > 240)
			EngineAutoShutdownTime = 240;
		// --, 150212 bwk
		
		AutoShutdownStatusDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
		TimeTextDisplay(EngineAutoShutdownTime);
		SetSeekBarPositionbyData(seekbarTime,EngineAutoShutdownTime);
		ESLSystemDisplay(ESLMode,ESLInterval);
		
		textViewMax.setText(ParentActivity.GetSectoMinString(2400, getString(ParentActivity.getResources().getString(string.Min), 48)
				,getString(ParentActivity.getResources().getString(string.Sec), 50)));
		textViewMin.setText(ParentActivity.GetSectoMinString(120,  getString(ParentActivity.getResources().getString(string.Min), 48)
				,getString(ParentActivity.getResources().getString(string.Sec), 50)));
		
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		radioOnOnce.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnOnce();
				
			}
		});
		radioOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnAlways();
			}
		});
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOff();
			}
		});
		textViewESL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickESL();
				
			}
		});
		seekbarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int progress;
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				progress = seekBar.getProgress();
				EngineAutoShutdownTime = SetSeekBarPositionbyProgress(seekBar, progress);
				TimeTextDisplay(EngineAutoShutdownTime);
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Log.d(TAG,"onStartTrackingTouch");
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				EngineAutoShutdownTime = SetSeekBarPositionbyProgress(seekBar, progress);
				TimeTextDisplay(EngineAutoShutdownTime);
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
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		
		CAN1Comm.Set_AutomaticEngineShutdown_363_PGN61184_121(EngineAutoShutdownStatus);
		CAN1Comm.Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(EngineAutoShutdownType);
		CAN1Comm.Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(EngineAutoShutdownTime);
		CAN1Comm.TxCANToMCU(121);
		
		if(ParentActivity.EngineAutoShutdownESLSetFlag == true){
			if(ESLMode == CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL){
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_INTERVAL_SETTING);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(ESLMode);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(ESLInterval);
				CAN1Comm.TxCANToMCU(23);
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
			}
			else{
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_SETTING);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(ESLMode);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
				CAN1Comm.TxCANToMCU(23);
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
			}
		}
		
		
		
		ParentActivity.EngineAutoShutdownESLFlag = false;
		ParentActivity.EngineAutoShutdownESLSetFlag =false; 

	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		ParentActivity.EngineAutoShutdownESLFlag = false;
		ParentActivity.EngineAutoShutdownESLSetFlag =false;
	}
	public void ClickOnOnce(){
		LayoutOnOff(true);
		EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON;
		EngineAutoShutdownType = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ONCE;
		AutoShutdownStatusDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
	}
	public void ClickOnAlways(){
		LayoutOnOff(true);
		EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON;
		EngineAutoShutdownType = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ALWAYS;
		AutoShutdownStatusDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
	}
	public void ClickOff(){
		LayoutOnOff(false);
		EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF;
		EngineAutoShutdownType = 3;
		AutoShutdownStatusDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
	}
	public void ClickESL(){
		ParentActivity.EngineAutoShutdownESLFlag = true;
		ParentActivity.EngineAutoShutdownTime = EngineAutoShutdownTime;
		ParentActivity.EngineAutoShutdownStatus = EngineAutoShutdownStatus;
		ParentActivity.EngineAutoShutdownType = EngineAutoShutdownType;
		ParentActivity._MenuBaseFragment.showBodyEngineAutoShutdownPW();
	}
	/////////////////////////////////////////////////////////////////////
	public void LayoutOnOff(boolean flag){
		if(flag == true){
//			layoutTime.setVisibility(View.VISIBLE);
//			layoutESL.setVisibility(View.VISIBLE);
//			imgViewLine.setVisibility(View.VISIBLE);
			layoutTime.setAlpha((float)1);
			layoutESL.setAlpha((float)1);
			imgViewLine.setAlpha((float)1);
			seekbarTime.setEnabled(true);
			textViewESL.setClickable(true);
			Log.d(TAG,"LayoutOn");
		}else{
//			layoutTime.setVisibility(View.INVISIBLE);
//			layoutESL.setVisibility(View.INVISIBLE);
//			imgViewLine.setVisibility(View.INVISIBLE);
			layoutTime.setAlpha((float)0.2);
			layoutESL.setAlpha((float)0.2);
			imgViewLine.setAlpha((float)0.2);
			seekbarTime.setEnabled(false);
			textViewESL.setClickable(false);
			Log.d(TAG,"LayoutOff");
		}
	}
	public void SetSeekBarPositionbyData(SeekBar _seekbar, int _data){
		int Progress;
		if(_data < 15){
			Progress = 0;
		}else if(_data >=15 && _data <= 237){
			Progress = (_data - 12) / 6; 
		}else{
			Progress = 38;
		}
		_seekbar.setProgress(Progress);
	}
	public int SetSeekBarPositionbyProgress(SeekBar _seekbar, int _progress){
		int Progress;
		int returnData;
		
		Progress = _progress;
		returnData = (Progress + 2) * 6;
			
		//_seekbar.setProgress(Progress);
		return returnData;
	}
	/////////////////////////////////////////////////////////////////////
	public void AutoShutdownStatusDisplay(int status, int type){
		if(status == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF){
			LayoutOnOff(false);
			radioOnOnce.setChecked(false);
			radioOnAlways.setChecked(false);
			radioOff.setChecked(true);
			EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF;
		}else if(status == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON){
			
			EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON;
			if(type == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ONCE){
				LayoutOnOff(true);
				radioOnOnce.setChecked(true);
				radioOnAlways.setChecked(false);
				radioOff.setChecked(false);
				EngineAutoShutdownType = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ONCE;
			}else if(type == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ALWAYS){
				LayoutOnOff(true);
				radioOnOnce.setChecked(false);
				radioOnAlways.setChecked(true);
				radioOff.setChecked(false);
				EngineAutoShutdownType = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ALWAYS;
			}
		}
		
	}
	public void TimeTextDisplay(int _data){
		int CalData;
		CalData = _data * 10;
		textViewTime.setText(ParentActivity.GetSectoMinString(CalData, getString(ParentActivity.getResources().getString(string.Min), 48),
				getString(ParentActivity.getResources().getString(string.Sec), 50)));
	}
	public void ESLSystemDisplay(int mode, int interval){
		String str = "";
		switch (mode) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			str = getString(ParentActivity.getResources().getString(string.Disable), 21);
			
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			str = getString(ParentActivity.getResources().getString(string.On_Always), 23);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			switch (interval) {
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN:
				str = "5" + getString(ParentActivity.getResources().getString(string.Min), 48);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN:
				str = "10" + getString(ParentActivity.getResources().getString(string.Min), 48);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN:
				str = "20" + getString(ParentActivity.getResources().getString(string.Min), 48);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN:
				str = "30" + getString(ParentActivity.getResources().getString(string.Min), 48);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR:
				str = "1" + getString(ParentActivity.getResources().getString(string.Hour), 47); 
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR:
				str = "2" + getString(ParentActivity.getResources().getString(string.Hour), 47);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR:
				str = "4" + getString(ParentActivity.getResources().getString(string.Hour), 47);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY:
				str = "1" + getString(ParentActivity.getResources().getString(string.Day), 49);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY:
				str = "2" + getString(ParentActivity.getResources().getString(string.Day), 49);
				break;

			default:
				break;
			}
			break;
		default:
			break;
		}
		textViewESL.setText(str);
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			EngineAutoShutdownTime -= 6;
			if(EngineAutoShutdownTime < MIN_LEVEL*6){
				// ++, 150212 bwk
				//EngineAutoShutdownTime = MIN_LEVEL;
				EngineAutoShutdownTime = MIN_LEVEL*6;
				// --, 150212 bwk
			}
			TimeTextDisplay(EngineAutoShutdownTime);
			SetSeekBarPositionbyData(seekbarTime,EngineAutoShutdownTime);
			break;
		case 5:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			if(EngineAutoShutdownStatus == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF){
				CursurIndex = 7;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 7:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			
			break;
		case 4:
			EngineAutoShutdownTime += 6;
			if(EngineAutoShutdownTime > MAX_LEVEL*6){
				// ++, 150212 bwk
				//EngineAutoShutdownTime = MAX_LEVEL;
				EngineAutoShutdownTime = MAX_LEVEL*6;
				// --, 150212 bwk
			}
			TimeTextDisplay(EngineAutoShutdownTime);
			SetSeekBarPositionbyData(seekbarTime,EngineAutoShutdownTime);
			break;
		case 5:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			if(EngineAutoShutdownStatus == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF){
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex = 4;
				CursurDisplay(CursurIndex);
			}
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
		case 3:
		
			ClickCancel();
			break;
			
		case 4:
		case 5:
		case 6:
		case 7:
			CursurFirstDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
			break;
		default:
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOnOnce();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			ClickOnAlways();
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			ClickOff();
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			ClickESL();
			break;
		case 6:
			ClickCancel();
			break;
		case 7:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurFirstDisplay(int status, int type){
		if(status == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF){
			LayoutOnOff(false);
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
		}else if(status == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON){
			LayoutOnOff(true);
			if(type == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ONCE){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}else if(type == CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ALWAYS){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		radioOnOnce.setPressed(false);
		radioOnAlways.setPressed(false);
		radioOff.setPressed(false);
		seekbarTime.setPressed(false);
		textViewESL.setPressed(false);

		switch (CursurIndex) {
			case 1:
				radioOnOnce.setPressed(true);
				break;
			case 2:
				radioOnAlways.setPressed(true);
				break;
			case 3:
				radioOff.setPressed(true);
				break;
			case 4:
				seekbarTime.setPressed(true);
				break;
			case 5:
				textViewESL.setPressed(true);
				break;
			case 6:
				imgbtnCancel.setPressed(true);
				break;
			case 7:
				imgbtnOK.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
