package taeha.wheelloader.fseries_monitor.menu;

import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;
import customlist.userswitching.IconTextItemUserSwitching;
import customlist.userswitching.IconTextListAdapterUserSwitching;
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
import taeha.wheelloader.fseries_monitor.main.UserData;
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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class UserSwitching extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int STATE_ENGINEMODE 				= 0;
	private static final int STATE_WARMINGUP				= 1;
	private static final int STATE_CCOMODE					= 2;
	private static final int STATE_SHIFTMODE				= 3;
	private static final int STATE_TCLOCKUP					= 4;
	private static final int STATE_RIDECONTROL				= 5;
	private static final int STATE_WEIGHINGSYSTEM			= 6;
	private static final int STATE_WEIGHINGDISPLAY			= 7;
	private static final int STATE_ERRORDETECTION			= 8;
	private static final int STATE_KICKDOWN					= 9;
	private static final int STATE_BUCKETPRIORITY			= 10;
	private static final int STATE_SOFTENDSTOP_BOOMUP		= 11;
	private static final int STATE_SOFTENDSTOP_BOOMDOWN		= 12;
	private static final int STATE_SOFTENDSTOP_BUCKETIN		= 13;
	private static final int STATE_SOFTENDSTOP_BUCKETDUMP	= 14;
	private static final int STATE_BRIGHTNESS				= 15;
	private static final int STATE_DISPLAYTYPE				= 16;
	private static final int STATE_UNIT_TEMP				= 17;
	private static final int STATE_UNIT_ODO					= 18;
	private static final int STATE_UNIT_WEIGHT				= 19;
	private static final int STATE_UNIT_PRESSURE			= 20;
	private static final int STATE_MACHINESTATUS_UPPER		= 21;
	private static final int STATE_MACHINESTATUS_LOWER		= 22;
	private static final int STATE_LANGUAGE					= 23;
	private static final int STATE_SOUNDOUTPUT				= 24;
	private static final int STATE_HOURMETER				= 25;
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnSave;
	ImageButton imgbtnApply;
	
	RadioButton radioDefault;
	RadioButton radioUser1;
	RadioButton radioUser2;
	RadioButton radioUser3;
	RadioButton radioUser4;
	
	// ListView
	ListView listView;
	// ListItem
	IconTextListAdapterUserSwitching adapter;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	public int EngineMode;
	public int WarmingUp;
	public int CCOMode;
	public int ShiftMode;
	public int TCLockUp;
	public int RideControl;
	public int WeighingSystem;
	public int WeighingDisplay;
	public int ErrorDetection;
	public int KickDown;
	public int BucketPriority;
	public int SoftEndStopBoomUp;
	public int SoftEndStopBoomDown;
	public int SoftEndStopBucketIn;
	public int SoftEndStopBucketDump;
	public int Brightness;
	public int DisplayType;
	public int UnitTemp;
	public int UnitOdo;
	public int UnitWeight;
	public int UnitPressure;
	public int MachineStatusUpper;
	public int MachineStatusLower;
	public int Language;
	public int SoundOutput;
	public int HourmeterDisplay;
	
	int CursurIndex;
	Handler HandleCursurDisplay;
	
	int SelectIndex;
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
		 TAG = "UserSwitching";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_userswitching, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.User_Switching));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		
		
		
		
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_userswitching_low_ok);
		imgbtnSave = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_userswitching_low_save);
		imgbtnApply = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_userswitching_low_apply);
		
		radioDefault = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_userswitching_default);
		radioUser1 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_userswitching_user1);
		radioUser2 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_userswitching_user2);
		radioUser3 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_userswitching_user3);
		radioUser4 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_userswitching_user4);
		
	
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_userswitching);
		adapter = new IconTextListAdapterUserSwitching(ParentActivity);
		adapter.clearItem();
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	
		listView.setAdapter(adapter);

		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		
		SelectIndex = 1;
		UserSwitchingRadioDisplay(SelectIndex);
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Engine_Mode)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Warming_Up)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.CCO_Mode)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Shift_Mode)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.TC_Lock_Up)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Ride_Control)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Weighing_System)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Weighing_Display)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Error_Detection)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Kick_Down)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Bucket_Priority)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Soft_End_Stop) + "/" + ParentActivity.getResources().getString(string.Boom_Up)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Soft_End_Stop) + "/" + ParentActivity.getResources().getString(string.Boom_Down)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Soft_End_Stop) + "/" + ParentActivity.getResources().getString(string.Bucket_In)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Soft_End_Stop) + "/" + ParentActivity.getResources().getString(string.Bucket_Dump)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Brightness_Setting)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Display_Style)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Temp)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Speed)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Weight)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Pressure)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Machine_Monitoring)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Machine_Monitoring)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Language)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Sound_Output_Setting)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Operation_History)
				, ""
				, ""));
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickSave();
			}
		});
		imgbtnApply.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickApply();
			}
		});
		radioDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDefault();
			}
		});
		radioUser1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickUser1();
			}
		});
		radioUser2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickUser2();
			}
		});
		radioUser3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickUser3();
			}
		});
		radioUser4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickUser4();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		WarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
		TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434();
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
		WeighingSystem = CAN1Comm.Get_WeighingSystemAccumulationMode_1941_PGN65450();
		WeighingDisplay = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
		ErrorDetection = ParentActivity.WeighingErrorDetect;
		KickDown = CAN1Comm.Get_KickDownShiftMode_547_PGN65434();
		BucketPriority = CAN1Comm.Get_BucketPriorityOperation_2301_PGN65517();
		SoftEndStopBoomUp = CAN1Comm.Get_SoftStopBoomUp_2337_PGN65524();
		SoftEndStopBoomDown = CAN1Comm.Get_SoftStopBoomDown_2338_PGN65524();
		SoftEndStopBucketIn = CAN1Comm.Get_SoftStopBucketIn_2339_PGN65524();
		SoftEndStopBucketDump = CAN1Comm.Get_SoftStopBucketOut_2340_PGN65524();
		Brightness = ParentActivity.BrightnessManualLevel;
		DisplayType = ParentActivity.DisplayType;
		UnitTemp = ParentActivity.UnitTemp;
		UnitOdo = ParentActivity.UnitOdo;
		UnitWeight = ParentActivity.UnitWeight;
		UnitPressure = ParentActivity.UnitPressure;
		MachineStatusUpper = ParentActivity.MachineStatusUpperIndex;
		MachineStatusLower = ParentActivity.MachineStatusLowerIndex;
		//Language = ParentActivity.;
		SoundOutput = ParentActivity.SoundState;
		HourmeterDisplay = ParentActivity.HourOdometerIndex;

	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		UserSwitchingListUpdate(SelectIndex);
	}
	
	/////////////////////////////////////////////////////////////////////
	public void UserSwitchingRadioDisplay(int Index){
		
		switch (Index) {
		case 1:
			radioDefault.setChecked(true);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		case 2:
			radioDefault.setChecked(false);
			radioUser1.setChecked(true);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		case 3:
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(true);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		case 4:
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(true);
			radioUser4.setChecked(false);
			break;
		case 5:
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(true);
			break;

		default:
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		}
	}
	public void UserSwitchingListUpdate(int Index){
		UserData _userdata;
		_userdata = new UserData();
		switch (Index) {
		case 1:
			_userdata = ParentActivity.UserDataDefault;
			break;
		case 2:
			_userdata = ParentActivity.UserDataUser1;
			break;
		case 3:
			_userdata = ParentActivity.UserDataUser2;
			break;
		case 4:
			_userdata = ParentActivity.UserDataUser3;
			break;
		case 5:
			_userdata = ParentActivity.UserDataUser4;
			break;
			
		default:
			
			break;
		}
		EngineModeDisplay(EngineMode,_userdata.EngineMode);
		WarmingUpDisplay(WarmingUp,_userdata.WarmingUp);
		CCOModeDisplay(CCOMode,_userdata.CCOMode);
		ShiftModeDisplay(ShiftMode,_userdata.ShiftMode);
		TCLockUpDisplay(TCLockUp,_userdata.TCLockUp);
		RideControlDisplay(RideControl,_userdata.RideControl);
		WeighingSystemDisplay(WeighingSystem,_userdata.WeighingSystem);
		WeighingDisplayDisplay(WeighingDisplay,_userdata.WeighingDisplay);
		ErrorDetectionDisplay(ErrorDetection,_userdata.ErrorDetection);
		KickDownDisplay(KickDown,_userdata.KickDown);
		BucketPriorityDisplay(BucketPriority,_userdata.BucketPriority);
		SoftEndStopBoomUpDisplay(SoftEndStopBoomUp,_userdata.SoftEndStopBoomUp);
		SoftEndStopBoomDownDisplay(SoftEndStopBoomDown,_userdata.SoftEndStopBoomDown);
		SoftEndStopBucketInDisplay(SoftEndStopBucketIn,_userdata.SoftEndStopBucketIn);
		SoftEndStopBucketDumpDisplay(SoftEndStopBucketDump,_userdata.SoftEndStopBucketDump);
		BrightnessDisplay(Brightness,_userdata.Brightness);
		UnitTempDisplay(UnitTemp,_userdata.UnitTemp);
		UnitOdoDisplay(UnitOdo,_userdata.UnitOdo);
		UnitWeightDisplay(UnitWeight,_userdata.UnitWeight);
		UnitPressureDisplay(UnitPressure,_userdata.UnitPressure);
		DisplayTypeDisplay(DisplayType,_userdata.DisplayType);
		MachineStatusUpperDisplay(MachineStatusUpper,_userdata.MachineStatusUpper);
		MachineStatusLowerDisplay(MachineStatusLower,_userdata.MachineStatusLower);
		SoundOutputDisplay(SoundOutput,_userdata.SoundOutput);
		HourmeterDisplay(HourmeterDisplay,_userdata.HourmeterDisplay);
	}
	public void EngineModeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			adapter.UpdateSecond(STATE_ENGINEMODE, ParentActivity.getResources().getString(string.POWER));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			adapter.UpdateSecond(STATE_ENGINEMODE, ParentActivity.getResources().getString(string.STANDARD));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			adapter.UpdateSecond(STATE_ENGINEMODE, ParentActivity.getResources().getString(string.ECONO));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			adapter.UpdateThird(STATE_ENGINEMODE, ParentActivity.getResources().getString(string.POWER));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			adapter.UpdateThird(STATE_ENGINEMODE, ParentActivity.getResources().getString(string.STANDARD));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			adapter.UpdateThird(STATE_ENGINEMODE, ParentActivity.getResources().getString(string.ECONO));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_ENGINEMODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_ENGINEMODE, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void WarmingUpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
			adapter.UpdateSecond(STATE_WARMINGUP, ParentActivity.getResources().getString(string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
			adapter.UpdateSecond(STATE_WARMINGUP, ParentActivity.getResources().getString(string.ON));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
			adapter.UpdateThird(STATE_WARMINGUP, ParentActivity.getResources().getString(string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
			adapter.UpdateThird(STATE_WARMINGUP, ParentActivity.getResources().getString(string.ON));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_WARMINGUP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_WARMINGUP, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void CCOModeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			adapter.UpdateSecond(STATE_CCOMODE, ParentActivity.getResources().getString(string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
			adapter.UpdateSecond(STATE_CCOMODE, ParentActivity.getResources().getString(string.L));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
			adapter.UpdateSecond(STATE_CCOMODE, ParentActivity.getResources().getString(string.M));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			adapter.UpdateSecond(STATE_CCOMODE, ParentActivity.getResources().getString(string.H));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			adapter.UpdateThird(STATE_CCOMODE, ParentActivity.getResources().getString(string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
			adapter.UpdateThird(STATE_CCOMODE, ParentActivity.getResources().getString(string.L));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
			adapter.UpdateThird(STATE_CCOMODE, ParentActivity.getResources().getString(string.M));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			adapter.UpdateThird(STATE_CCOMODE, ParentActivity.getResources().getString(string.H));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_CCOMODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_CCOMODE, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void ShiftModeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
			adapter.UpdateSecond(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.MANUAL));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			adapter.UpdateSecond(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.AL));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			adapter.UpdateSecond(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.AN));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			adapter.UpdateSecond(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.AH));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			adapter.UpdateThird(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.MANUAL));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			adapter.UpdateThird(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.AL));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			adapter.UpdateThird(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.AN));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			adapter.UpdateThird(STATE_SHIFTMODE, ParentActivity.getResources().getString(string.AH));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_SHIFTMODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_SHIFTMODE, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void TCLockUpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			adapter.UpdateSecond(STATE_TCLOCKUP, ParentActivity.getResources().getString(string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			adapter.UpdateSecond(STATE_TCLOCKUP, ParentActivity.getResources().getString(string.ON));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			adapter.UpdateThird(STATE_TCLOCKUP, ParentActivity.getResources().getString(string.OFF));
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			adapter.UpdateThird(STATE_TCLOCKUP, ParentActivity.getResources().getString(string.ON));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_TCLOCKUP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_TCLOCKUP, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void RideControlDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF:
			adapter.UpdateSecond(STATE_RIDECONTROL, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL:
			adapter.UpdateSecond(STATE_RIDECONTROL, ParentActivity.getResources().getString(string.On_Always));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_AUTO:
			adapter.UpdateSecond(STATE_RIDECONTROL, ParentActivity.getResources().getString(string.On_Conditional_Speed));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF:
			adapter.UpdateThird(STATE_RIDECONTROL, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL:
			adapter.UpdateThird(STATE_RIDECONTROL, ParentActivity.getResources().getString(string.On_Always));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_AUTO:
			adapter.UpdateThird(STATE_RIDECONTROL, ParentActivity.getResources().getString(string.On_Conditional_Speed));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_RIDECONTROL, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_RIDECONTROL, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void WeighingSystemDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			adapter.UpdateSecond(STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getString(string.Manual));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			adapter.UpdateSecond(STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getString(string.Automatic));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			adapter.UpdateThird(STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getString(string.Manual));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			adapter.UpdateThird(STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getString(string.Automatic));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_WEIGHINGSYSTEM, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void WeighingDisplayDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			adapter.UpdateSecond(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Daily));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			adapter.UpdateSecond(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Total_A));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			adapter.UpdateSecond(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Total_B));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			adapter.UpdateSecond(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Total_C));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			adapter.UpdateThird(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Daily));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			adapter.UpdateThird(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Total_A));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			adapter.UpdateThird(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Total_B));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			adapter.UpdateThird(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getString(string.Total_C));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_WEIGHINGDISPLAY, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void ErrorDetectionDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			adapter.UpdateSecond(STATE_ERRORDETECTION, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			adapter.UpdateSecond(STATE_ERRORDETECTION, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			adapter.UpdateThird(STATE_ERRORDETECTION, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			adapter.UpdateThird(STATE_ERRORDETECTION, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_ERRORDETECTION, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_ERRORDETECTION, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void KickDownDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			adapter.UpdateSecond(STATE_KICKDOWN, ParentActivity.getResources().getString(string.MODE1_DOWN_UP));
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			adapter.UpdateSecond(STATE_KICKDOWN, ParentActivity.getResources().getString(string.MODE2_DOWN_ONLY));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			adapter.UpdateThird(STATE_KICKDOWN, ParentActivity.getResources().getString(string.MODE1_DOWN_UP));
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			adapter.UpdateThird(STATE_KICKDOWN, ParentActivity.getResources().getString(string.MODE2_DOWN_ONLY));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_KICKDOWN, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_KICKDOWN, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void BucketPriorityDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF:
			adapter.UpdateSecond(STATE_BUCKETPRIORITY, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_ON:
			adapter.UpdateSecond(STATE_BUCKETPRIORITY, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF:
			adapter.UpdateThird(STATE_BUCKETPRIORITY, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_ON:
			adapter.UpdateThird(STATE_BUCKETPRIORITY, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_BUCKETPRIORITY, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_BUCKETPRIORITY, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBoomUpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BOOMUP, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBoomDownDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BOOMDOWN, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBucketInDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BUCKETIN, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBucketDumpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON:
			adapter.UpdateSecond(STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON:
			adapter.UpdateThird(STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_SOFTENDSTOP_BUCKETDUMP, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void BrightnessDisplay(int SettingData, int LoadingData){
		SettingData += 1;
		LoadingData += 1;
		adapter.UpdateSecond(STATE_BRIGHTNESS, Integer.toString(SettingData));
		adapter.UpdateThird(STATE_BRIGHTNESS, Integer.toString(LoadingData));
		
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_BRIGHTNESS, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_BRIGHTNESS, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitTempDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_TEMP_C:
			adapter.UpdateSecond(STATE_UNIT_TEMP, ParentActivity.getResources().getString(string.C));
			break;
		case Home.UNIT_TEMP_F:
			adapter.UpdateSecond(STATE_UNIT_TEMP, ParentActivity.getResources().getString(string.F));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_TEMP_C:
			adapter.UpdateThird(STATE_UNIT_TEMP, ParentActivity.getResources().getString(string.C));
			break;
		case Home.UNIT_TEMP_F:
			adapter.UpdateThird(STATE_UNIT_TEMP, ParentActivity.getResources().getString(string.F));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_UNIT_TEMP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_UNIT_TEMP, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitOdoDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_ODO_KM:
			adapter.UpdateSecond(STATE_UNIT_ODO, ParentActivity.getResources().getString(string.km));
			break;
		case Home.UNIT_ODO_MILE:
			adapter.UpdateSecond(STATE_UNIT_ODO, ParentActivity.getResources().getString(string.mile));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_ODO_KM:
			adapter.UpdateThird(STATE_UNIT_ODO, ParentActivity.getResources().getString(string.km));
			break;
		case Home.UNIT_ODO_MILE:
			adapter.UpdateThird(STATE_UNIT_ODO, ParentActivity.getResources().getString(string.mile));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_UNIT_ODO, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_UNIT_ODO, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitWeightDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_WEIGHT_TON:
			adapter.UpdateSecond(STATE_UNIT_WEIGHT, ParentActivity.getResources().getString(string.ton));
			break;
		case Home.UNIT_WEIGHT_LB:
			adapter.UpdateSecond(STATE_UNIT_WEIGHT, ParentActivity.getResources().getString(string.lb));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_WEIGHT_TON:
			adapter.UpdateThird(STATE_UNIT_WEIGHT, ParentActivity.getResources().getString(string.ton));
			break;
		case Home.UNIT_WEIGHT_LB:
			adapter.UpdateThird(STATE_UNIT_WEIGHT, ParentActivity.getResources().getString(string.lb));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_UNIT_WEIGHT, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_UNIT_WEIGHT, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitPressureDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_PRESSURE_BAR:
			adapter.UpdateSecond(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.bar));
			break;
		case Home.UNIT_PRESSURE_MPA:
			adapter.UpdateSecond(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.Mpa));
			break;
		case Home.UNIT_PRESSURE_KGF:
			adapter.UpdateSecond(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.kgf_cm));
			break;
		case Home.UNIT_PRESSURE_PSI:
			adapter.UpdateSecond(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.Psi));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_PRESSURE_BAR:
			adapter.UpdateThird(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.bar));
			break;
		case Home.UNIT_PRESSURE_MPA:
			adapter.UpdateThird(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.Mpa));
			break;
		case Home.UNIT_PRESSURE_KGF:
			adapter.UpdateThird(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.kgf_cm));
			break;
		case Home.UNIT_PRESSURE_PSI:
			adapter.UpdateThird(STATE_UNIT_PRESSURE, ParentActivity.getResources().getString(string.Psi));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_UNIT_PRESSURE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_UNIT_PRESSURE, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void DisplayTypeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.DISPLAY_TYPE_A:
			adapter.UpdateSecond(STATE_DISPLAYTYPE, ParentActivity.getResources().getString(string.Type_A));
			break;
		case Home.DISPLAY_TYPE_B:
			adapter.UpdateSecond(STATE_DISPLAYTYPE, ParentActivity.getResources().getString(string.Type_B));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_WEIGHT_TON:
			adapter.UpdateThird(STATE_DISPLAYTYPE, ParentActivity.getResources().getString(string.Type_A));
			break;
		case Home.UNIT_WEIGHT_LB:
			adapter.UpdateThird(STATE_DISPLAYTYPE, ParentActivity.getResources().getString(string.Type_B));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_DISPLAYTYPE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_DISPLAYTYPE, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void MachineStatusUpperDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateSecond(STATE_MACHINESTATUS_UPPER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateSecond(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.HYD_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateSecond(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.Battery_Volt));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateSecond(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.Coolant_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateSecond(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.TM_Oil_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateSecond(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.Weighing_System));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateThird(STATE_MACHINESTATUS_UPPER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateThird(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.HYD_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateThird(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.Battery_Volt));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateThird(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.Coolant_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateThird(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.TM_Oil_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateThird(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getString(string.Weighing_System));
			break;
		default:
			break;
		}		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_MACHINESTATUS_UPPER, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void MachineStatusLowerDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateSecond(STATE_MACHINESTATUS_LOWER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateSecond(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.HYD_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateSecond(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.Battery_Volt));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateSecond(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.Coolant_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateSecond(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.TM_Oil_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateSecond(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.Weighing_System));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateThird(STATE_MACHINESTATUS_LOWER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateThird(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.HYD_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateThird(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.Battery_Volt));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateThird(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.Coolant_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateThird(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.TM_Oil_Temp));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateThird(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getString(string.Weighing_System));
			break;
		default:
			break;
		}		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_MACHINESTATUS_LOWER, null);
		}
		adapter.notifyDataSetChanged();
	}	
	
	public void LanguageDisplay(int SettingData, int LoadingData){
		
	}
	public void SoundOutputDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.STATE_INTERNAL_SPK:
			adapter.UpdateSecond(STATE_SOUNDOUTPUT, ParentActivity.getResources().getString(string.Internal_Speaker));
			break;
		case Home.STATE_EXTERNAL_AUX:
			adapter.UpdateSecond(STATE_SOUNDOUTPUT, ParentActivity.getResources().getString(string.External_Aux));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.STATE_INTERNAL_SPK:
			adapter.UpdateThird(STATE_SOUNDOUTPUT, ParentActivity.getResources().getString(string.Internal_Speaker));
			break;
		case Home.STATE_EXTERNAL_AUX:
			adapter.UpdateThird(STATE_SOUNDOUTPUT, ParentActivity.getResources().getString(string.External_Aux));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_SOUNDOUTPUT, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_SOUNDOUTPUT, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void HourmeterDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			adapter.UpdateSecond(STATE_HOURMETER, ParentActivity.getResources().getString(string.Latest_Hourmeter));
			break;
		case CAN1CommManager.DATA_STATE_FUELRATE_CURRENT:
			adapter.UpdateSecond(STATE_HOURMETER, ParentActivity.getResources().getString(string.Current_Fuel_Rate));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			adapter.UpdateSecond(STATE_HOURMETER, ParentActivity.getResources().getString(string.Total_Odometer));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			adapter.UpdateSecond(STATE_HOURMETER, ParentActivity.getResources().getString(string.Latest_Odometer));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			adapter.UpdateThird(STATE_HOURMETER, ParentActivity.getResources().getString(string.Latest_Hourmeter));
			break;
		case CAN1CommManager.DATA_STATE_FUELRATE_CURRENT:
			adapter.UpdateThird(STATE_HOURMETER, ParentActivity.getResources().getString(string.Current_Fuel_Rate));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			adapter.UpdateThird(STATE_HOURMETER, ParentActivity.getResources().getString(string.Total_Odometer));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			adapter.UpdateThird(STATE_HOURMETER, ParentActivity.getResources().getString(string.Latest_Odometer));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_HOURMETER, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_HOURMETER, null);
		}
		adapter.notifyDataSetChanged();
	}	
	/////////////////////////////////////////////////////////////////////	

	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
		ParentActivity.OldScreenIndex = 0;
	}
	public void ClickSave(){
		UserData _userdata;
		_userdata = new UserData();
		
		_userdata.EngineMode = EngineMode;
		_userdata.WarmingUp = WarmingUp;
		_userdata.CCOMode = CCOMode;
		_userdata.ShiftMode = ShiftMode;
		_userdata.TCLockUp = TCLockUp;
		_userdata.RideControl = RideControl;
		_userdata.WeighingSystem = WeighingSystem;
		_userdata.WeighingDisplay = WeighingDisplay;
		_userdata.ErrorDetection = ErrorDetection;
		_userdata.KickDown = KickDown;
		_userdata.BucketPriority = BucketPriority;
		_userdata.SoftEndStopBoomUp = SoftEndStopBoomUp;
		_userdata.SoftEndStopBoomDown = SoftEndStopBoomDown;
		_userdata.SoftEndStopBucketIn = SoftEndStopBucketIn;
		_userdata.SoftEndStopBucketDump = SoftEndStopBucketDump;
		_userdata.Brightness = Brightness;
		_userdata.DisplayType = DisplayType;
		_userdata.UnitTemp = UnitTemp;
		_userdata.UnitOdo = UnitOdo;
		_userdata.UnitWeight = UnitWeight;
		_userdata.UnitPressure = UnitPressure;
		_userdata.MachineStatusUpper = MachineStatusUpper;
		_userdata.MachineStatusLower = MachineStatusLower;
		_userdata.Language = Language;
		_userdata.SoundOutput = SoundOutput;
		_userdata.HourmeterDisplay = HourmeterDisplay;

		
		switch (SelectIndex) {
		case 1:
			break;
		case 2:
			ParentActivity.UserDataUser1 = _userdata;
			ParentActivity.SaveUserData(1, ParentActivity.UserDataUser1);
			break;
		case 3:
			ParentActivity.UserDataUser2 = _userdata;
			ParentActivity.SaveUserData(2, ParentActivity.UserDataUser2);
			break;
		case 4:
			ParentActivity.UserDataUser3 = _userdata;
			ParentActivity.SaveUserData(3, ParentActivity.UserDataUser3);
			break;
		case 5:
			ParentActivity.UserDataUser4 = _userdata;
			ParentActivity.SaveUserData(4, ParentActivity.UserDataUser4);
			break;
			
		default:
			
			break;
		}
		
	}
	public void ClickApply(){
		UserData _userdata;
		_userdata = new UserData();
		switch (SelectIndex) {
		case 1:
			_userdata = ParentActivity.UserDataDefault;
			break;
		case 2:
			_userdata = ParentActivity.UserDataUser1;
			break;
		case 3:
			_userdata = ParentActivity.UserDataUser2;
			break;
		case 4:
			_userdata = ParentActivity.UserDataUser3;
			break;
		case 5:
			_userdata = ParentActivity.UserDataUser4;
			break;
			
		default:
			
			break;
		}
		
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(_userdata.EngineMode);
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(_userdata.WarmingUp);
		CAN1Comm.TxCANToMCU(101);
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(15);
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(3);
		
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(_userdata.CCOMode);
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(_userdata.ShiftMode);
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(_userdata.TCLockUp);
		CAN1Comm.Set_KickDownShiftMode_547_PGN61184_104(_userdata.KickDown);
		CAN1Comm.TxCANToMCU(104);
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(15);
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(15);
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(3);
		CAN1Comm.Set_KickDownShiftMode_547_PGN61184_104(3);
		
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(_userdata.RideControl);
		CAN1Comm.TxCANToMCU(247);
		
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(_userdata.WeighingSystem);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(_userdata.WeighingDisplay);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(3);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
		

		CAN1Comm.Set_BucketPriorityOperation_2301_PGN61184_203(_userdata.BucketPriority);
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(_userdata.SoftEndStopBoomUp);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(_userdata.SoftEndStopBoomDown);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(_userdata.SoftEndStopBucketIn);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(_userdata.SoftEndStopBucketDump);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_BucketPriorityOperation_2301_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		
		CAN1Comm.Set_SpeedmeterUnitChange_PGN65327(_userdata.UnitOdo);
		CAN1Comm.TxCANToMCU(47);

		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(_userdata.Brightness + 1);
		CAN1Comm.TxCANToMCU(109);
		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(15);
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD,_userdata.Brightness + 1);
		
		ParentActivity.WeighingErrorDetect = _userdata.ErrorDetection;
		
		ParentActivity.BrihgtnessCurrent = _userdata.Brightness;
		ParentActivity.BrightnessManualLevel = _userdata.Brightness;
		ParentActivity.BrightnessManualAuto = Home.BRIGHTNESS_MANUAL;
		
		ParentActivity.UnitOdo = _userdata.UnitOdo;
		ParentActivity.UnitTemp = _userdata.UnitTemp;
		ParentActivity.UnitWeight = _userdata.UnitWeight;
		ParentActivity.UnitPressure = _userdata.UnitPressure;

		ParentActivity.MachineStatusUpperIndex = _userdata.MachineStatusUpper;
		ParentActivity.MachineStatusLowerIndex = _userdata.MachineStatusLower;

		ParentActivity.SoundState = _userdata.SoundOutput;

		ParentActivity.HourOdometerIndex = _userdata.HourmeterDisplay;
		ParentActivity.SavePref();
	}
	public void ClickDefault(){
		SelectIndex = 1;
		UserSwitchingRadioDisplay(SelectIndex);
	}
	public void ClickUser1(){
		SelectIndex = 2;
		UserSwitchingRadioDisplay(SelectIndex);
	}
	public void ClickUser2(){
		SelectIndex = 3;
		UserSwitchingRadioDisplay(SelectIndex);
	}
	public void ClickUser3(){
		SelectIndex = 4;
		UserSwitchingRadioDisplay(SelectIndex);
	}
	public void ClickUser4(){
		SelectIndex = 5;
		UserSwitchingRadioDisplay(SelectIndex);
	}
	/////////////////////////////////////////////////////////////////////
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
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 8;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 8:
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
			CursurIndex++;
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
		case 6:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex = 6;
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
		case 3:
		case 4:
		case 5:
			ClickOK();
			break;
		case 6:
			CursurIndex = SelectIndex;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex = SelectIndex;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex = SelectIndex;
			CursurDisplay(CursurIndex);
			break;
		default:
			ClickOK();
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			ClickDefault();
			break;
		case 2:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			ClickUser1();
			break;
		case 3:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			ClickUser2();
			break;
		case 4:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			ClickUser3();
			break;
		case 5:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			ClickUser4();
			break;
		case 6:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			ClickApply();
			break;
		case 7:
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			ClickSave();
			break;
		case 8:
			ClickOK();
			break;
		default:
			ClickOK();
			break;
		}
	}
	public void CursurDisplay(int Index){
		
		imgbtnOK.setPressed(false);
		imgbtnSave.setPressed(false);
		imgbtnApply.setPressed(false);
		
		radioDefault.setPressed(false);
		radioUser1.setPressed(false);
		radioUser2.setPressed(false);
		radioUser3.setPressed(false);
		radioUser4.setPressed(false);
		
		switch (Index) {
		case 1:
			radioDefault.setPressed(true);
			break;
		case 2:
			radioUser1.setPressed(true);
			break;
		case 3:
			radioUser2.setPressed(true);
			break;
		case 4:
			radioUser3.setPressed(true);
			break;
		case 5:
			radioUser4.setPressed(true);
			break;
		case 6:
			imgbtnApply.setPressed(true);
			break;
		case 7:
			imgbtnSave.setPressed(true);
			break;
		case 8:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}