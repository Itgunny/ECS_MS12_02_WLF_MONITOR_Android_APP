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
import taeha.wheelloader.fseries_monitor.main.R.string;
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
	ImageButton	imgbtnCancel;
	
	TextView	textViewTime;
	TextView 	textViewMax;
	TextView 	textViewMin;
	
	RadioButton radioOnOnce;
	RadioButton radioOnAlways;
	RadioButton radioOff;
	
	SeekBar seekbarTime;
	
	RelativeLayout layoutTime;
	RelativeLayout layoutESL;
	
	ImageView imgViewLine;
	
	TextView textViewESL;
	
	ImageView imgViewESL;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineAutoShutdownTime;
	int EngineAutoShutdownStatus;
	int EngineAutoShutdownType;
	
	int ESLInterval;
	int ESLMode;
	
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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Engine_Auto_Shutdown));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_engineautoshutdown_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_engineautoshutdown_low_cancel);

		textViewTime = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_time);
		textViewMax = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_max);
		textViewMin = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_min);

		radioOnOnce = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_engineautoshutdown_use_on_once);
		radioOnAlways = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_engineautoshutdown_use_on_always);
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_engineautoshutdown_use_off);
	
		seekbarTime = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_mode_engineautoshutdown);
		
		layoutTime = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_mode_engineautoshutdown_time);
		layoutESL = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_mode_engineautoshutdown_esl);
		
		imgViewLine = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_engineautoshutdown_line2);
		
		textViewESL = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_esl_time);
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
			EngineAutoShutdownStatus = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN65428();
			EngineAutoShutdownType = CAN1Comm.Get_AutomaticEngineShutdownType_PGN61184_122();
			ESLMode = CAN1Comm.Get_ESLMode_820_PGN65348();
			ESLInterval = CAN1Comm.Get_ESLInterval_825_PGN65348();
		}
	
		
		
		AutoShutdownStatusDisplay(EngineAutoShutdownStatus,EngineAutoShutdownType);
		TimeTextDisplay(EngineAutoShutdownTime);
		SetSeekBarPositionbyData(seekbarTime,EngineAutoShutdownTime);
		ESLSystemDisplay(ESLMode,ESLInterval);
		
		textViewMax.setText(ParentActivity.GetSectoMinString(2400,ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
		textViewMin.setText(ParentActivity.GetSectoMinString(120,ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
		
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
		radioOnOnce.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOnOnce();
			}
		});
		radioOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOnAlways();
			}
		});
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
			}
		});
		textViewESL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickESL();
			}
		});
		seekbarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int progress;
				progress = seekBar.getProgress();
				EngineAutoShutdownTime = SetSeekBarPositionbyProgress(seekBar, progress);
				TimeTextDisplay(EngineAutoShutdownTime);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
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
	}
	public void ClickOnAlways(){
		LayoutOnOff(true);
		EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON;
		EngineAutoShutdownType = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON_ALWAYS;
	}
	public void ClickOff(){
		LayoutOnOff(false);
		EngineAutoShutdownStatus = CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF;
		EngineAutoShutdownType = 3;
	}
	public void ClickESL(){
		ParentActivity.EngineAutoShutdownESLFlag = true;
		ParentActivity.EngineAutoShutdownTime = EngineAutoShutdownTime;
		ParentActivity.EngineAutoShutdownStatus = EngineAutoShutdownStatus;
		ParentActivity.EngineAutoShutdownType = EngineAutoShutdownType;
		ParentActivity._MenuBaseFragment.showBodyEngineAutoShutdownPWFrame();
	}
	/////////////////////////////////////////////////////////////////////
	public void LayoutOnOff(boolean flag){
		if(flag == true){
			layoutTime.setVisibility(View.VISIBLE);
			layoutESL.setVisibility(View.VISIBLE);
			imgViewLine.setVisibility(View.VISIBLE);
			Log.d(TAG,"LayoutOn");
		}else{
			layoutTime.setVisibility(View.INVISIBLE);
			layoutESL.setVisibility(View.INVISIBLE);
			imgViewLine.setVisibility(View.INVISIBLE);
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
		textViewTime.setText(ParentActivity.GetSectoMinString(CalData, ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
	}
	public void ESLSystemDisplay(int mode, int interval){
		String str = "";
		switch (mode) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			str = ParentActivity.getResources().getString(string.Disable);
			imgViewESL.setImageResource(R.drawable.btn_check_off);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			str = ParentActivity.getResources().getString(string.On_Always);
			imgViewESL.setImageResource(R.drawable.btn_check_on);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			imgViewESL.setImageResource(R.drawable.btn_check_on);
			switch (interval) {
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN:
				str = "5" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN:
				str = "10" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN:
				str = "20" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN:
				str = "30" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR:
				str = "1" + ParentActivity.getResources().getString(string.Hour);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR:
				str = "2" + ParentActivity.getResources().getString(string.Hour);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR:
				str = "4" + ParentActivity.getResources().getString(string.Hour);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY:
				str = "1" + ParentActivity.getResources().getString(string.Day);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY:
				str = "2" + ParentActivity.getResources().getString(string.Day);
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
	
}
