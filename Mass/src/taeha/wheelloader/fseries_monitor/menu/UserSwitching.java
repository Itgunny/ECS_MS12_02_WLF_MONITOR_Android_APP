package taeha.wheelloader.fseries_monitor.menu;

import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;
import customlist.userswitching.IconTextItemUserSwitching;
import customlist.userswitching.IconTextListAdapterUserSwitching;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LanguageClass;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserSwitching extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int STATE_ENGINEMODE 				= 0;
	//private static final int STATE_WARMINGUP				= 1;	// ++, --, 150403 bwk 항목 삭제
	private static final int STATE_CCOMODE					= 1;
	private static final int STATE_SHIFTMODE				= 2;
	private static final int STATE_TCLOCKUP					= 3;
	private static final int STATE_RIDECONTROL				= 4;
	private static final int STATE_WEIGHINGSYSTEM			= 5;
	private static final int STATE_WEIGHINGDISPLAY			= 6;
	private static final int STATE_ERRORDETECTION			= 7;
	private static final int STATE_KICKDOWN					= 8;
	private static final int STATE_BUCKETPRIORITY			= 9;
	private static final int STATE_SOFTENDSTOP_BOOMUP		= 10;
	private static final int STATE_SOFTENDSTOP_BOOMDOWN		= 11;
	private static final int STATE_SOFTENDSTOP_BUCKETIN		= 12;
	private static final int STATE_SOFTENDSTOP_BUCKETDUMP	= 13;
	private static final int STATE_BRIGHTNESS_MANUALAUTO	= 14;	// ++, --, 150407 bwk 수동 밝기 -> 수동/자동으로 변경
	private static final int STATE_DISPLAYTYPE				= 15;
	private static final int STATE_UNIT_TEMP				= 16;
	private static final int STATE_UNIT_ODO					= 17;
	private static final int STATE_UNIT_WEIGHT				= 18;
	private static final int STATE_UNIT_PRESSURE			= 19;
	private static final int STATE_MACHINESTATUS_UPPER		= 20;
	private static final int STATE_MACHINESTATUS_LOWER		= 21;
	private static final int STATE_LANGUAGE					= 22;
	private static final int STATE_SOUNDOUTPUT				= 23;
	private static final int STATE_OPERATION_HISTORY		= 24;	// ++, --, 150403 bwk 명칭 변경 STATE_HOURMETER -> STATE_OPERATION_HISTORY
	private static final int STATE_FUEL_INFO				= 25;	// ++, --, 150403 bwk 항목 추가
	private static final int STATE_BOOM_DETENT_MODE			= 26;
	private static final int STATE_BUCKET_DETENT_MODE		= 27;	
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
	
	RelativeLayout layoutSave;	// ++, --, 150407 bwk
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	public int EngineMode;
	//public int WarmingUp;
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
	// ++, 150407 bwk
	//public int Brightness;
	public int BrightnessManualAuto;    
	public int BrightnessManualLevel;
	public int BrightnessAutoDayLevel;
	public int BrightnessAutoNightLevel;
	public int BrightnessAutoStartTime;
	public int BrightnessAutoEndTime;
	// --, 150407 bwk
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
	public int FuelDisplay;

	public int BoomDetentMode;
	public int BucketDetentMode;
	
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
		
		// ++, 150212 bwk
		switch(SelectIndex)
		{
			case 1:
			default:
				ClickDefault();
				break;
			case 2:
				ClickUser1();
				break;
			case 3:
				ClickUser2();
				break;
			case 4:
				ClickUser3();
				break;
			case 5:
				ClickUser4();
				break;
		}
		// --, 150212 bwk
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
		
		layoutSave = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_userswitching_low_save);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	
		listView.setAdapter(adapter);

		// ++, 150212 bwk
		/*
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		
		SelectIndex = 1;
		 */
		SelectIndex = ParentActivity.UserIndex;
		// --, 150212 bwk
		UserSwitchingRadioDisplay(SelectIndex);
		
		CursurIndex = SelectIndex;
		CursurDisplay(CursurIndex);
		
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Engine_Mode)
				, ""
				, ""));
//		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
//				null,
//				ParentActivity.getResources().getString(string.Warming_Up)
//				, ""
//				, ""));
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
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Fuel_Consumption_History)
				, ""
				, ""));		
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				ParentActivity.getResources().getString(string.Boom_Detent_Mode)
				, ""
				, ""));
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
				null,
				ParentActivity.getResources().getString(string.Bucket_Detent_Mode)
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
//		WarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
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
		// ++, 150407 bwk
		//Brightness = ParentActivity.ManualLevel;		
		BrightnessManualAuto = ParentActivity.BrightnessManualAuto;    
		BrightnessManualLevel = ParentActivity.BrightnessManualLevel;
		BrightnessAutoDayLevel = ParentActivity.BrightnessAutoDayLevel;
		BrightnessAutoNightLevel = ParentActivity.BrightnessAutoNightLevel;
		BrightnessAutoStartTime = ParentActivity.BrightnessAutoStartTime;
		BrightnessAutoEndTime = ParentActivity.BrightnessAutoEndTime;
		// --, 150407 bwk
		DisplayType = ParentActivity.DisplayType;
		UnitTemp = ParentActivity.UnitTemp;
		UnitOdo = ParentActivity.UnitOdo;
		UnitWeight = ParentActivity.UnitWeight;
		UnitPressure = ParentActivity.UnitPressure;
		MachineStatusUpper = ParentActivity.MachineStatusUpperIndex;
		MachineStatusLower = ParentActivity.MachineStatusLowerIndex;
		Language = ParentActivity.LanguageIndex;		// ++, --, 150212 bwk
		SoundOutput = ParentActivity.SoundState;
		HourmeterDisplay = ParentActivity.HourOdometerIndex;
		FuelDisplay = ParentActivity.FuelIndex;
		
		BoomDetentMode = CAN1Comm.Get_BoomDetentMode_223_PGN61184_124();
		BucketDetentMode = CAN1Comm.Get_BucketDetentMode_224_PGN61184_124();
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
			layoutSave.setAlpha((float)0.3);
			imgbtnSave.setClickable(false);
			radioDefault.setChecked(true);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		case 2:
			layoutSave.setAlpha((float)1.0);
			imgbtnSave.setClickable(true);
			radioDefault.setChecked(false);
			radioUser1.setChecked(true);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		case 3:
			layoutSave.setAlpha((float)1.0);
			imgbtnSave.setClickable(true);
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(true);
			radioUser3.setChecked(false);
			radioUser4.setChecked(false);
			break;
		case 4:
			layoutSave.setAlpha((float)1.0);
			imgbtnSave.setClickable(true);
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(true);
			radioUser4.setChecked(false);
			break;
		case 5:
			layoutSave.setAlpha((float)1.0);
			imgbtnSave.setClickable(true);
			radioDefault.setChecked(false);
			radioUser1.setChecked(false);
			radioUser2.setChecked(false);
			radioUser3.setChecked(false);
			radioUser4.setChecked(true);
			break;

		default:
			layoutSave.setAlpha((float)1.0);
			imgbtnSave.setClickable(true);
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
		//WarmingUpDisplay(WarmingUp,_userdata.WarmingUp);	// ++, --, 150402 bwk
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
		// ++, 150407 bwk
		//BrightnessDisplay(Brightness,_userdata.Brightness);
		BrightnessDisplay(BrightnessManualAuto,_userdata.BrightnessManualAuto);
		// --, 150407 bwk
		UnitTempDisplay(UnitTemp,_userdata.UnitTemp);
		UnitOdoDisplay(UnitOdo,_userdata.UnitOdo);
		UnitWeightDisplay(UnitWeight,_userdata.UnitWeight);
		UnitPressureDisplay(UnitPressure,_userdata.UnitPressure);
		DisplayTypeDisplay(DisplayType,_userdata.DisplayType);
		MachineStatusUpperDisplay(MachineStatusUpper,_userdata.MachineStatusUpper);
		MachineStatusLowerDisplay(MachineStatusLower,_userdata.MachineStatusLower);
		LanguageDisplay(Language, _userdata.Language);	// ++, --, 150213 bwk
		SoundOutputDisplay(SoundOutput,_userdata.SoundOutput);
		HourmeterDisplay(HourmeterDisplay,_userdata.HourmeterDisplay);
		FuelDisplay(FuelDisplay,_userdata.FuelDisplay);		// ++, --, 150403 bwk
		BoomDetentModeDisplay(BoomDetentMode, _userdata.BoomDetentMode);
		BucketDetentModeDisplay(BucketDetentMode, _userdata.BucketDetentMode);
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
	// ++, 150402 bwk 사양 삭제
//	public void WarmingUpDisplay(int SettingData, int LoadingData){
//		switch (SettingData) {
//		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
//			adapter.UpdateSecond(STATE_WARMINGUP, ParentActivity.getResources().getString(string.OFF));
//			break;
//		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
//			adapter.UpdateSecond(STATE_WARMINGUP, ParentActivity.getResources().getString(string.ON));
//			break;
//		default:
//			break;
//		}
//		switch (LoadingData) {
//		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
//			adapter.UpdateThird(STATE_WARMINGUP, ParentActivity.getResources().getString(string.OFF));
//			break;
//		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
//			adapter.UpdateThird(STATE_WARMINGUP, ParentActivity.getResources().getString(string.ON));
//			break;
//		default:
//			break;
//		}
//		
//		if(SettingData != LoadingData){
//			adapter.UpdateIcon(STATE_WARMINGUP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
//		}else{
//			adapter.UpdateIcon(STATE_WARMINGUP, null);
//		}
//		adapter.notifyDataSetChanged();
//	}
	// --, 150402 bwk
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
		// ++, 150407 bwk
		//SettingData+=1;
		//LoadingData+=1;
		//adapter.UpdateSecond(STATE_BRIGHTNESS_MANUALAUTO, Integer.toString(SettingData));
		//adapter.UpdateThird(STATE_BRIGHTNESS, Integer.toString(LoadingData));
//		
//		if(SettingData != LoadingData){
//			adapter.UpdateIcon(STATE_BRIGHTNESS, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
//		}else{
//			adapter.UpdateIcon(STATE_BRIGHTNESS, null);
//		}
//		adapter.notifyDataSetChanged();
		switch (SettingData) {
		case Home.BRIGHTNESS_MANUAL:
			adapter.UpdateSecond(STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getString(string.Manual));
			break;
		case Home.BRIGHTNESS_AUTO:
			adapter.UpdateSecond(STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getString(string.Automatic));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.BRIGHTNESS_MANUAL:
			adapter.UpdateThird(STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getString(string.Manual));
			break;
		case Home.BRIGHTNESS_AUTO:
			adapter.UpdateThird(STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getString(string.Automatic));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_BRIGHTNESS_MANUALAUTO, null);
		}
		adapter.notifyDataSetChanged();
		// --, 150407 bwk
		
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
		// ++, 150212 bwk
		switch (SettingData) {
			case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Korean));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.English));
				break;
			// ++, 150225 bwk
			case Home.STATE_DISPLAY_LANGUAGE_GERMAN:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.German));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_FRENCH:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.French));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SPANISH:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Spanish));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_PORTUGUE:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Portuguese));
				break;
//			case Home.STATE_DISPLAY_LANGUAGE_CHINESE:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Chinese));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_RUSIAN:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Russian));
//				break;
			case Home.STATE_DISPLAY_LANGUAGE_ITALIAN:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Italian));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_NEDERLAND:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Dutch));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SWEDISH:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Swedish));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_TURKISH:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Turkish));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Slovakian));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ESTONIAN:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Estonian));
				break;
//			case Home.STATE_DISPLAY_LANGUAGE_THAILAND:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Thai));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_HINDI:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Hindi));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_MONGOL:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Mongolian));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_ARABIC:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Arabic));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_FARSI:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Persian));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_INDONESIAN:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Indonesian));
//				break;
			case Home.STATE_DISPLAY_LANGUAGE_FINNISH:
				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Finnish));
				break;
			// --, 150225 bwk
		}
		switch (LoadingData) {
		case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
			adapter.UpdateThird(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Korean));
			break;
		case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
			adapter.UpdateThird(STATE_LANGUAGE, ParentActivity.getResources().getString(string.English));
			break;
		}
		// --, 150212 bwk
		
		// ++, 150213 bwk
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_LANGUAGE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_LANGUAGE, null);
		}
		adapter.notifyDataSetChanged();
		// --, 150213 bwk
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
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
			adapter.UpdateSecond(STATE_OPERATION_HISTORY, "-");
			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			adapter.UpdateSecond(STATE_OPERATION_HISTORY, ParentActivity.getResources().getString(string.Latest_Hourmeter));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			adapter.UpdateSecond(STATE_OPERATION_HISTORY, ParentActivity.getResources().getString(string.Total_Odometer));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			adapter.UpdateSecond(STATE_OPERATION_HISTORY, ParentActivity.getResources().getString(string.Latest_Odometer));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
			adapter.UpdateThird(STATE_OPERATION_HISTORY, "-");
			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			adapter.UpdateThird(STATE_OPERATION_HISTORY, ParentActivity.getResources().getString(string.Latest_Hourmeter));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			adapter.UpdateThird(STATE_OPERATION_HISTORY, ParentActivity.getResources().getString(string.Total_Odometer));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			adapter.UpdateThird(STATE_OPERATION_HISTORY, ParentActivity.getResources().getString(string.Latest_Odometer));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_OPERATION_HISTORY, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_OPERATION_HISTORY, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void FuelDisplay(int SettingData, int LoadingData){
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
			adapter.UpdateSecond(STATE_FUEL_INFO, "-");
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			adapter.UpdateSecond(STATE_FUEL_INFO, ParentActivity.getResources().getString(string.Average_Fuel_Rate));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			adapter.UpdateSecond(STATE_FUEL_INFO, ParentActivity.getResources().getString(string.A_Days_Fuel_Used));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
			adapter.UpdateThird(STATE_FUEL_INFO, "-");
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			adapter.UpdateThird(STATE_FUEL_INFO, ParentActivity.getResources().getString(string.Average_Fuel_Rate));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			adapter.UpdateThird(STATE_FUEL_INFO, ParentActivity.getResources().getString(string.A_Days_Fuel_Used));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_FUEL_INFO, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_FUEL_INFO, null);
		}
		adapter.notifyDataSetChanged();		
	}
	/////////////////////////////////////////////////////////////////////
	void BoomDetentModeDisplay(int SettingData, int LoadingData){
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF:
			adapter.UpdateSecond(STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN:
			adapter.UpdateSecond(STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF:
			adapter.UpdateThird(STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN:
			adapter.UpdateThird(STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_BOOM_DETENT_MODE, null);
		}
		adapter.notifyDataSetChanged();		
	}
	/////////////////////////////////////////////////////////////////////
	void BucketDetentModeDisplay(int SettingData, int LoadingData){
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF:
			adapter.UpdateSecond(STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN:
			adapter.UpdateSecond(STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF:
			adapter.UpdateThird(STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN:
			adapter.UpdateThird(STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(STATE_BUCKET_DETENT_MODE, null);
		}
		adapter.notifyDataSetChanged();		
	}
	/////////////////////////////////////////////////////////////////////

	public void ClickOK(){
//		ParentActivity.UserIndex = SelectIndex;		// ++, --, 150212 bwk
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		// ++, 150309 bwk
		//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
//		ParentActivity.showMainScreen();
		if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_A){
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_TOP;
			ParentActivity._MainBBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MAIN_B_QUICK_TOP);
		}else{
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment);
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_TOP;
			ParentActivity._MainABaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MAIN_A_QUICK_TOP);
		}
		// --, 150309 bwk
//		ParentActivity.OldScreenIndex = 0;
	}
	public void ClickSave(){
		UserData _userdata;
		_userdata = new UserData();
		
		_userdata.EngineMode = EngineMode;
		//_userdata.WarmingUp = WarmingUp;
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
		// ++, 150407 bwk
		//_userdata.Brightness = Brightness;
		_userdata.BrightnessManualAuto = BrightnessManualAuto;
		_userdata.BrightnessManualLevel = BrightnessManualLevel;
		_userdata.BrightnessAutoDayLevel = BrightnessAutoDayLevel;
		_userdata.BrightnessAutoNightLevel = BrightnessAutoNightLevel;
		_userdata.BrightnessAutoStartTime = BrightnessAutoStartTime;
		_userdata.BrightnessAutoEndTime = BrightnessAutoEndTime;
		// --, 150407 bwk
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
		_userdata.FuelDisplay = FuelDisplay;
		_userdata.BoomDetentMode = BoomDetentMode;
		_userdata.BucketDetentMode = BucketDetentMode;
		
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
		ParentActivity.UserIndex = SelectIndex;
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
		//CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(_userdata.WarmingUp);
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
		
		
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(_userdata.BoomDetentMode);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(7);

		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(_userdata.BucketDetentMode);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
		
		ParentActivity.WeighingErrorDetect = _userdata.ErrorDetection;

		// ++, 150407 bwk
//		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(_userdata.Brightness + 1);
//		CAN1Comm.TxCANToMCU(109);
//		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(15);
//		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD,_userdata.Brightness + 1);
//		
//		ParentActivity.BrihgtnessCurrent = _userdata.Brightness;
//		ParentActivity.BrightnessManualLevel = _userdata.Brightness;
//		ParentActivity.BrightnessManualAuto = Home.BRIGHTNESS_MANUAL;
		ParentActivity.BrightnessManualAuto = _userdata.BrightnessManualAuto;
		if(_userdata.BrightnessManualAuto == Home.BRIGHTNESS_MANUAL)
		{
//			CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(_userdata.Brightness + 1);
//			CAN1Comm.TxCANToMCU(109);
//			CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(15);
//			CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD,_userdata.Brightness + 1);			
			CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(BrightnessManualLevel + 1);
			CAN1Comm.TxCANToMCU(109);
			CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, ParentActivity.BrightnessManualLevel + 1);
			ParentActivity.BrihgtnessCurrent = _userdata.BrightnessManualLevel;
		}
		ParentActivity.BrightnessManualLevel = _userdata.BrightnessManualLevel;
		ParentActivity.BrightnessAutoDayLevel = _userdata.BrightnessAutoDayLevel;
		ParentActivity.BrightnessAutoNightLevel = _userdata.BrightnessAutoNightLevel;
		ParentActivity.BrightnessAutoStartTime = _userdata.BrightnessAutoStartTime;
		ParentActivity.BrightnessAutoEndTime = _userdata.BrightnessAutoEndTime;
		// --, 150407 bwk
		
		ParentActivity.UnitOdo = _userdata.UnitOdo;
		ParentActivity.UnitTemp = _userdata.UnitTemp;
		ParentActivity.UnitWeight = _userdata.UnitWeight;
		ParentActivity.UnitPressure = _userdata.UnitPressure;

		ParentActivity.MachineStatusUpperIndex = _userdata.MachineStatusUpper;
		ParentActivity.MachineStatusLowerIndex = _userdata.MachineStatusLower;

		ParentActivity.SoundState = _userdata.SoundOutput;

		ParentActivity.HourOdometerIndex = _userdata.HourmeterDisplay;
		ParentActivity.FuelIndex = _userdata.FuelDisplay;
		
		// ++, 150213 bwk
		ParentActivity.LanguageIndex = _userdata.Language;
		ParentActivity.setLanguage();
		// --, 150213 bwk
		
		ParentActivity.DisplayType = _userdata.DisplayType;
		ParentActivity.SavePref();
		
		ParentActivity.SoundState = _userdata.SoundOutput;
		ParentActivity.SavePref();
		try {
			CAN1Comm.LineOutfromJNI(_userdata.SoundOutput);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG,"Load Library Error");
		}			
		
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
