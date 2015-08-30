package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class CoolingFanReverseModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private  final int MAX_LEVEL	 	= 30;
	private  final int MIN_LEVEL 		= 3;
	
	private  final int TOTAL_STEP		= 90;
	private  final int STEP				= 10;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioAuto;
	RadioButton radioManual;
	
	RelativeLayout	layoutAuto;
	RelativeLayout	layoutManual;

	ImageButton imgbtnOK;
	TextView textViewExcute;
	
	ImageView imgViewCoolingFanIcon;
	ImageView imgViewExcuteBoader;
	
	TextView textViewIntervalData;
	TextView textViewIntervalMax;
	TextView textViewIntervalMin;
	TextView textViewOperationTimeData;
	TextView textViewOperationTimeMax;
	TextView textViewOperationTimeMin;
	
	SeekBar	seekbarInterval;
	SeekBar seekbarOperationTime;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CoolingFanReverse;
	int SelectMode;
	
	boolean ManualPress;
	int ReverseFan;
	
	int Interval;
	int OperationTime;
	
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
		 TAG = "CoolingFanAutoFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_coolingfanreversemode, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		setCoolingFanReverseRadio(CoolingFanReverse);
		
		if(CoolingFanReverse == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
			ClickAuto();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Cooling_Fan_Reverse_Mode));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CursurDisplay(CursurIndex);

	}
	@Override
	public void onDestroyView(){
		super.onDestroyView();
		
		// Manual Off
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfanreversemode_auto);
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfanreversemode_manual);
		
		layoutAuto = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_mode_coolingfan_Auto);
		layoutManual = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_mode_coolingfan_manual);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_coolingfanreversemode_low_ok);
		textViewExcute = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_manual_excute);
		
		imgViewExcuteBoader = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_coolingfan_manual_excute_boarder);
		imgViewCoolingFanIcon = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_coolingfan_manual);
		
		textViewIntervalData = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_auto_interval_time);
		textViewIntervalMax = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_auto_interval_max);
		textViewIntervalMin = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_auto_interval_min);
		
		textViewOperationTimeData = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_auto_time_time);
		textViewOperationTimeMax = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_auto_time_max);
		textViewOperationTimeMin = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_auto_time_min);
		
		seekbarInterval = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_mode_coolingfan_auto_interval);
		seekbarOperationTime = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_mode_coolingfan_auto_time);
		
		seekbarInterval.setMax(TOTAL_STEP);
		seekbarInterval.incrementProgressBy(1);
		
		seekbarOperationTime.setMax(TOTAL_STEP);
		seekbarOperationTime.incrementProgressBy(1);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		CoolingFanReverse = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		SelectMode = CoolingFanReverse;
		if(CoolingFanReverse == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
			CursurIndex = 1;
		else if(CoolingFanReverse == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
			CursurIndex = 2;
		else 
		{
			CoolingFanReverse = CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO;
			CursurIndex = 1;
		}

		textViewIntervalMax.setText(ParentActivity.GetSectoMinString(300,ParentActivity.getResources().getString(string.Hour),ParentActivity.getResources().getString(string.Min)));
		textViewIntervalMin.setText(ParentActivity.GetSectoMinString(30,ParentActivity.getResources().getString(string.Hour),ParentActivity.getResources().getString(string.Min)));
		textViewOperationTimeMax.setText(ParentActivity.GetSectoMinString(300,ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
		textViewOperationTimeMin.setText(ParentActivity.GetSectoMinString(30,ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SelectMode = CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO;
				setCoolingFanReverseRadio(SelectMode);
				ClickAuto();
			}
		});
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SelectMode = CAN1CommManager.DATA_STATE_REVERSEFAN_OFF;
				
				setCoolingFanReverseRadio(SelectMode);
				ClickManual();
			}
		});
		textViewExcute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Log.d(TAG,"imgbtnExcute.isPressed()="+imgbtnExcute.isPressed());
//				Log.d(TAG,"CursurIndex="+CursurIndex);
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickExcute();
			}
		});

		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		seekbarInterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				int progress;
				progress = seekBar.getProgress();
				Interval = SetSeekBarPositionbyProgress(seekBar, progress);
				IntervalTextDisplay(Interval);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				Interval = SetSeekBarPositionbyProgress(seekBar, progress);
				IntervalTextDisplay(Interval);
			}
		});
		seekbarOperationTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				int progress;
				progress = seekBar.getProgress();
				OperationTime = SetSeekBarPositionbyProgress(seekBar, progress);
				OperationTimeTextDisplay(OperationTime);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				OperationTime = SetSeekBarPositionbyProgress(seekBar, progress);
				OperationTimeTextDisplay(OperationTime);
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
		{
			CheckCoolingFanManualButton();
			ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
			ReverseFanDisplay(ReverseFan);
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickExcute(){
		CheckCoolingFanManualButton();
	}
	public void CheckCoolingFanManualButton(){
		if(textViewExcute.isPressed() == true){
//			Log.d(TAG, "Set_CoolingFanReverseManual_PGN61184_61(1)");
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(1);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = true;
		}
		
		if(ManualPress == true && textViewExcute.isPressed() == false){
//			Log.d(TAG, "Set_CoolingFanReverseManual_PGN61184_61(3)");
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = false;
		}
	}
	
	public void ClickAuto(){
		// Manual Off
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
		
		// Auto Mode
		Interval = CAN1Comm.Get_CoolingFanReverseIntervalTime_211_PGN65369();
		OperationTime = CAN1Comm.Get_CoolingFanReverseOperatingTime_212_PGN65369();
		
		if(Interval > MAX_LEVEL)
			Interval = MAX_LEVEL;
		else if(Interval < MIN_LEVEL)
			Interval = MIN_LEVEL;
		
		if(OperationTime > MAX_LEVEL)
			OperationTime = MAX_LEVEL;
		else if(OperationTime < MIN_LEVEL)
			OperationTime = MIN_LEVEL;
		
		IntervalTextDisplay(Interval);
		OperationTimeTextDisplay(OperationTime);
		SetSeekBarPositionbyData(seekbarInterval,Interval);
		SetSeekBarPositionbyData(seekbarOperationTime,OperationTime);
	}
	public void ClickManual(){
		CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_OFF);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
		CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(3);
	}
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);		
		
		if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
		{
			CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(OperationTime);
			CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(Interval);
			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(3);
		}
		else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
		{
			CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_OFF);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(3);
		}
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);		
	}
	/////////////////////////////////////////////////////////////////////
	public void ReverseFanDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_COOLINGFAN_OFF:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);

			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_FORWARD:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);

			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_REVERSE:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_green_popup);

			break;
		default:
			break;
		}
	}
	public void setCoolingFanReverseRadio(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			radioManual.setChecked(true);
			radioAuto.setChecked(false);
			ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);
			layoutAuto.setVisibility(View.INVISIBLE);
			layoutManual.setVisibility(View.VISIBLE);
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			layoutAuto.setVisibility(View.VISIBLE);
			layoutManual.setVisibility(View.INVISIBLE);
			break;
		default:
			layoutAuto.setVisibility(View.INVISIBLE);
			layoutManual.setVisibility(View.INVISIBLE);
			break;
		}
	}
	public void IntervalTextDisplay(int _data){
		int CalData;
		CalData = _data * 10;
		textViewIntervalData.setText(ParentActivity.GetSectoMinString(CalData, ParentActivity.getResources().getString(string.Hour),ParentActivity.getResources().getString(string.Min)));
	}
	public void OperationTimeTextDisplay(int _data){
		int CalData;
		CalData = _data * 10;
		textViewOperationTimeData.setText(ParentActivity.GetSectoMinString(CalData, ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
	}
	public void SetSeekBarPositionbyData(SeekBar _seekbar, int _data){
		int Progress;
		if(_data < 5){
			Progress = 0;
		}else if(_data < 8){
			Progress = 10;
		}else if(_data < 11){
			Progress = 20;
		}else if(_data < 14){
			Progress = 30;
		}else if(_data < 17){
			Progress = 40;
		}else if(_data < 20){
			Progress = 50;
		}else if(_data < 23){
			Progress = 60;
		}else if(_data < 26){
			Progress = 70;
		}else if(_data < 29){
			Progress = 80;
		}else{
			Progress = 90;
		}
		_seekbar.setProgress(Progress);
	}
	/////////////////////////////////////////////////////////////////////
	public int SetSeekBarPositionbyProgress(SeekBar _seekbar, int _progress){
		int Progress;
		int returnData;
		if(_progress < 5){
			Progress = 0;
			returnData = 3;
		}else if(_progress < 15){
			Progress = 10;
			returnData = 6;
		}else if(_progress < 25){
			Progress = 20;
			returnData = 9;
		}else if(_progress < 35){
			Progress = 30;
			returnData = 12;
		}else if(_progress < 45){
			Progress = 40;
			returnData = 15;
		}else if(_progress < 55){
			Progress = 50;
			returnData = 18;
		}else if(_progress < 65){
			Progress = 60;
			returnData = 21;
		}else if(_progress < 75){
			Progress = 70;
			returnData = 24;
		}else if(_progress < 85){
			Progress = 80;
			returnData = 27;
		}else{
			Progress = 90;
			returnData = 30;
		}

		_seekbar.setProgress(Progress);
		return returnData;
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void setCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
//		Log.d(TAG, "ClickRight() CursurIndex="+CursurIndex);
		if((SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF) && (textViewExcute.isPressed() == true))
			return;
		
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
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO){
				Interval -= 3;
				if(Interval < MIN_LEVEL)
					Interval = MIN_LEVEL;
				IntervalTextDisplay(Interval);
				SetSeekBarPositionbyData(seekbarInterval,Interval);
			}else {
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}
			break;
		case 4:
			OperationTime -= 3;
			if(OperationTime < MIN_LEVEL)
				OperationTime = MIN_LEVEL;
			OperationTimeTextDisplay(OperationTime);
			SetSeekBarPositionbyData(seekbarOperationTime,OperationTime);
			break;
		case 5:
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
				CursurIndex = 3;
			else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
				CursurIndex--;
			else
				CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
//		Log.d(TAG, "ClickRight() CursurIndex="+CursurIndex);
		if((SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF) && (textViewExcute.isPressed() == true))
			return;
		switch (CursurIndex) {
		case 3:
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO){
				Interval += 3;
				if(Interval > MAX_LEVEL)
					Interval = MAX_LEVEL;
				IntervalTextDisplay(Interval);
				SetSeekBarPositionbyData(seekbarInterval,Interval);
			}else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}
			break;
		case 4:
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
			{
				OperationTime += 3;
				if(OperationTime > MAX_LEVEL)
					OperationTime = MAX_LEVEL;
				OperationTimeTextDisplay(OperationTime);
				SetSeekBarPositionbyData(seekbarOperationTime,OperationTime);
			}
			break;
		case 1:
		case 2:
			if((SelectMode != CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO) && (SelectMode != CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
					&& (CursurIndex == 2))
				CursurIndex = 5;
			else
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
		if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
		{
			switch (CursurIndex) {
			case 3:
			case 4:
					CursurIndex = 1;
					CursurDisplay(CursurIndex);
				break;
			default:
				ClickOK();
				break;
			}
		}
		else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
		{
			switch (CursurIndex) {
			case 3:
					CursurIndex = 1;
					CursurDisplay(CursurIndex);
				break;
			default:
				ClickCancel();
				break;
			}
		}
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else{
				SelectMode = CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO;
				setCoolingFanReverseRadio(SelectMode);
			}
			break;
		case 2:
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else{
				SelectMode = CAN1CommManager.DATA_STATE_REVERSEFAN_OFF;
				setCoolingFanReverseRadio(SelectMode);
			}
			break;
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
				CursurIndex++;
			else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF)
				CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			ClickOK();
			break;
		default:

			break;
		}
	}
	public void CursurDisplay(int Index){
		try {
			radioAuto.setPressed(false);
			radioManual.setPressed(false);
			imgbtnOK.setPressed(false);
			
			if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO){
				seekbarOperationTime.setPressed(false);
				seekbarInterval.setPressed(false);
			}else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF){
				imgViewExcuteBoader.setVisibility(View.INVISIBLE);
				textViewExcute.setPressed(false);
			}
			switch (CursurIndex) {
				case 1:
					radioAuto.setPressed(true);
					break;
				case 2:
					radioManual.setPressed(true);
					break;
				case 3:
					if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
						seekbarInterval.setPressed(true);
					else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF){
						textViewExcute.setPressed(false);
						imgViewExcuteBoader.setVisibility(View.VISIBLE);
					}
					break;
				case 4:
					if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO)
						seekbarOperationTime.setPressed(true);
					else if(SelectMode == CAN1CommManager.DATA_STATE_REVERSEFAN_OFF){
						textViewExcute.setPressed(true);
						imgViewExcuteBoader.setVisibility(View.VISIBLE);
					}
					break;
				case 5:
					imgbtnOK.setPressed(true);
					break;
				default:
					break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException CursurDisplay"+CursurIndex+"SelectMode"+SelectMode);
		}
	
	}
	/////////////////////////////////////////////////////////////////////
	
}
