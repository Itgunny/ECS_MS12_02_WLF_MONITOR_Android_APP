package taeha.wheelloader.fseries_monitor.menu;

import java.util.Timer;
import java.util.TimerTask;

import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;
import customlist.userswitching.IconTextItemUserSwitching;
import customlist.userswitching.IconTextListAdapterUserSwitching;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LanguageClass;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.UserData;
import taeha.wheelloader.fseries_monitor.menu.management.ServiceMenuSensorMonitoringHiddenFragment.EnableButtonTimerClass;
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
	/*
	private static final int STATE_ENGINEMODE 				= 0;
	//private static final int STATE_WARMINGUP				= 1;	// ++, --, 150403 bwk �׸� ����
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
	private static final int STATE_BRIGHTNESS_MANUALAUTO	= 14;	// ++, --, 150407 bwk ���� ��� -> ����/�ڵ����� ����
	private static final int STATE_DISPLAYTYPE				= 15;
	private static final int STATE_UNIT_TEMP				= 16;
	private static final int STATE_UNIT_ODO					= 17;
	private static final int STATE_UNIT_WEIGHT				= 18;
	private static final int STATE_UNIT_PRESSURE			= 19;
	private static final int STATE_MACHINESTATUS_UPPER		= 20;
	private static final int STATE_MACHINESTATUS_LOWER		= 21;
	private static final int STATE_LANGUAGE					= 22;
	private static final int STATE_SOUNDOUTPUT				= 23;
	private static final int STATE_OPERATION_HISTORY		= 24;	// ++, --, 150403 bwk ��Ī ���� STATE_HOURMETER -> STATE_OPERATION_HISTORY
	private static final int STATE_FUEL_INFO				= 25;	// ++, --, 150403 bwk �׸� �߰�
	private static final int STATE_BOOM_DETENT_MODE			= 26;
	private static final int STATE_BUCKET_DETENT_MODE		= 27;	
	*/
	
//	private int STATE_ENGINEMODE 				= 0;
//	private int STATE_CCOMODE					= 1;
//	private int STATE_SHIFTMODE					= 2;
//	private int STATE_TCLOCKUP					= 3;
//	private int STATE_RIDECONTROL				= 4;
//	private int STATE_WEIGHINGSYSTEM			= 5;
//	private int STATE_WEIGHINGDISPLAY			= 6;
//	private int STATE_ERRORDETECTION			= 7;
//	private int STATE_KICKDOWN					= 8;
//	private int STATE_BUCKETPRIORITY			= 9;
//	private int STATE_SOFTENDSTOP_BOOMUP		= 10;
//	private int STATE_SOFTENDSTOP_BOOMDOWN		= 11;
//	private int STATE_SOFTENDSTOP_BUCKETIN		= 12;
//	private int STATE_SOFTENDSTOP_BUCKETDUMP	= 13;
//	private int STATE_BRIGHTNESS_MANUALAUTO		= 14;	// ++, --, 150407 bwk ���� ��� -> ����/�ڵ����� ����
//	private int STATE_DISPLAYTYPE				= 15;
//	private int STATE_UNIT_TYPE					= 16;
//	private int STATE_UNIT_FUEL					= 17;
//	private int STATE_UNIT_TEMP					= 18;
//	private int STATE_UNIT_ODO					= 19;
//	private int STATE_UNIT_WEIGHT				= 20;
//	private int STATE_UNIT_PRESSURE				= 21;
//	private int STATE_MACHINESTATUS_UPPER		= 22;
//	private int STATE_MACHINESTATUS_LOWER		= 23;
//	private int STATE_LANGUAGE					= 24;
//	private int STATE_SOUNDOUTPUT				= 25;
//	private int STATE_OPERATION_HISTORY			= 26;	// ++, --, 150403 bwk ��Ī ���� STATE_HOURMETER -> STATE_OPERATION_HISTORY
//	private int STATE_FUEL_INFO					= 27;	// ++, --, 150403 bwk �׸� �߰�
//	private int STATE_BOOM_DETENT_MODE			= 28;
//	private int STATE_BUCKET_DETENT_MODE		= 29;
//	private int STATE_REVERSE_FAN_MODE			= 30;
//	private int STATE_REVERSE_CAMERA			= 31;
//	private int STATE_SET_CLOCK					= 32;
//	private int STATE_ENGINE_AUTO_SHUTDOWN		= 33;
	
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
	public int UnitType;
	public int UnitFuel;
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
	//
	public int ReverseFanMode;
	public int ReverseCamera;
	public int SetClock;
	public int EngineAutoShutdownStatus;
	
	
	int CursurIndex;
	Handler HandleCursurDisplay;
	
	int SelectIndex;
	
	boolean CheckTCLockUp = false;
	boolean CheckEHCU = false;
	boolean CheckTM = false;
	boolean CheckBKCU = false;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	private Timer	mEnableButtonTimer = null;	
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
		
		
		StartEnableButtonTimer();
		
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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.User_Switching), 116);
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
		
		boolean BackgroundFlag = true;

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
		
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				null,
				getString(ParentActivity.getResources().getString(string.Engine_Mode), 240)
				, ""
				, ""));
//		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
//				null,
//				ParentActivity.getResources().getString(string.Warming_Up)
//				, ""
//				, ""));
		//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
		{
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.ICCO_Mode), 205)
					, ""
					, ""));
		}
		else
		{
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.CCO_Mode), 204)
					, ""
					, ""));
		}
		adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				null,
				getString(ParentActivity.getResources().getString(string.Shift_Mode), 206)
				, ""
				, ""));
		/*if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			CheckTCLockUp = false;
		}else*/ if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
			CheckTCLockUp = false;
		}else{
			CheckTCLockUp = true;
			BackgroundFlag = false;
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.TC_Lock_Up), 210)
					, ""
					, ""));
		}
			
		if(BackgroundFlag == false)
		{
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Ride_Control), 155)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_System), 114)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_Display), 170)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Error_Detection), 171)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Kick_Down), 207)
					, ""
					, ""));
			BackgroundFlag = true;
		}
		else
		{
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Ride_Control), 155)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_System), 114)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_Display), 170)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Error_Detection), 171)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Kick_Down), 207)
					, ""
					, ""));	
			BackgroundFlag = false;
		}
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() == CAN1CommManager.STATE_COMPONENTCODE_EHCU)
		{
			CheckEHCU = true;
			
			String strModelOption = ParentActivity._CheckModel.GetMCUModelOption(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330());
			if(strModelOption.equals("TM"))
				CheckTM = true;
			else
				CheckTM = false;
				
			
			if(BackgroundFlag == false)
			{
				adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
						null,
						null,
						getString(ParentActivity.getResources().getString(string.Bucket_Priority), 211)
						, ""
						, ""));
				adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
						null,
						null,
						getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Up), 236)
						, ""
						, ""));
				BackgroundFlag = false;
				
				if(CheckTM == false)
				{
					adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
							null,
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Down), 237)
							, ""
							, ""));
					adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
							null,
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_In), 238)
							, ""
							, ""));
					adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
							null,
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_Dump), 239)
							, ""
							, ""));
					BackgroundFlag = true;
				}
			}
			else
			{
				adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
						null,
						null,
						getString(ParentActivity.getResources().getString(string.Bucket_Priority), 211)
						, ""
						, ""));
				adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
						null,
						null,
						getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Up), 236)
						, ""
						, ""));
				BackgroundFlag = true;
				if(CheckTM == false)
				{
					adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
							null,
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Down), 237)
							, ""
							, ""));
					adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
							null,
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_In), 238)
							, ""
							, ""));
					adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
							null,
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_Dump), 239)
							, ""
							, ""));
					BackgroundFlag = false;
				}
				
			}
		}
		else
		{
			CheckEHCU = false;
			CheckTM = false;
		}
		
		if(BackgroundFlag == false)
		{
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Brightness_Setting), 412)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Display_Style), 416)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Unit_Setting), 414)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Volume), 468)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Temp), 39)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Distance), 471)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Weight), 41)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Pressure), 42)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Language), 422)
					, ""
					, ""));
			/* adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Sound_Output_Setting), 415)
					, ""
					, "")); */
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Operation_History), 254)
					, ""
					, ""));		
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Fuel_Consumption_History), 313)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Boom_Detent_Mode), 149)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Bucket_Detent_Mode), 150)
					, ""
					, ""));		
			adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null, 
					null, 
					getString(ParentActivity.getResources().getString(string.Cooling_Fan_Reverse_Mode), 217)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null, 
					null, 
					getString(ParentActivity.getResources().getString(string.Camera_Setting), 216)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null, 
					null, 
					getString(ParentActivity.getResources().getString(string.Clock_Setting), 413)
					, ""
					, ""));
		}
		else
		{
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Brightness_Setting), 412)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Display_Style), 416)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Unit_Setting), 414)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Volume), 468)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Temp), 39)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Distance), 471)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Weight), 41)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Pressure), 42)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Language), 422)
					, ""
					, ""));
			/*(adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Sound_Output_Setting), 415)
					, ""
					, ""));*/
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Operation_History), 254)
					, ""
					, ""));		
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Fuel_Consumption_History), 313)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Boom_Detent_Mode), 149)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					null,
					getString(ParentActivity.getResources().getString(string.Bucket_Detent_Mode), 150)
					, ""
					, ""));				
			adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null, 
					null, 
					getString(ParentActivity.getResources().getString(string.Cooling_Fan_Reverse_Mode), 217)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null, 
					null, 
					getString(ParentActivity.getResources().getString(string.Camera_Setting), 216)
					, ""
					, ""));
			adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null, 
					null, 
					getString(ParentActivity.getResources().getString(string.Clock_Setting), 413)
					, ""
					, ""));
		}
		if(CAN1Comm.Get_CheckBKCUComm() == 1)
		{
			Log.d(TAG, "CheckBKCU");
			
			Log.d(TAG, "get Check BKCU : " + CAN1Comm.Get_ComponentCode_1699_PGN65330_BKCU());
			CheckBKCU = true;
			if(BackgroundFlag == false){
				adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
						null, 
						null, 
						getString(ParentActivity.getResources().getString(string.Engine_Auto_Shutdown), 215)
						, ""
						, ""));
			}else {
				adapter.addItem(new IconTextItemUserSwitching(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
						null, 
						null, 
						getString(ParentActivity.getResources().getString(string.Engine_Auto_Shutdown), 215)
						, ""
						, ""));
			}
			
		}else {
			Log.d(TAG, "No CheckBKCU");
			CheckBKCU = false;
		}
		
		int nIndex = 0;
		Home.STATE_ENGINEMODE 				= nIndex++;
		Home.STATE_CCOMODE					= nIndex++;
		Home.STATE_SHIFTMODE					= nIndex++;
		if(CheckTCLockUp && CheckEHCU){
			Home.STATE_TCLOCKUP					    = nIndex++;
			Home.STATE_RIDECONTROL				    = nIndex++;
			Home.STATE_WEIGHINGSYSTEM			    = nIndex++;
			Home.STATE_WEIGHINGDISPLAY			    = nIndex++;
			Home.STATE_ERRORDETECTION			    = nIndex++;
			Home.STATE_KICKDOWN					    = nIndex++;
			Home.STATE_BUCKETPRIORITY			    = nIndex++;
			Home.STATE_SOFTENDSTOP_BOOMUP		  	= nIndex++;
			if(CheckTM == false)
			{
				Home.STATE_SOFTENDSTOP_BOOMDOWN			= nIndex++;
				Home.STATE_SOFTENDSTOP_BUCKETIN			= nIndex++;
				Home.STATE_SOFTENDSTOP_BUCKETDUMP		= nIndex++;
				Home.STATE_BRIGHTNESS_MANUALAUTO	  		= nIndex++;	
				Home.STATE_DISPLAYTYPE				    = nIndex++;
				Home.STATE_UNIT_TYPE						= nIndex++;
				Home.STATE_UNIT_FUEL				        = nIndex++;
				Home.STATE_UNIT_TEMP				        = nIndex++;
				Home.STATE_UNIT_ODO					    = nIndex++;
				Home.STATE_UNIT_WEIGHT				    = nIndex++;
				Home.STATE_UNIT_PRESSURE			      	= nIndex++;
				Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;
				Home.STATE_MACHINESTATUS_LOWER		  	= nIndex++;
				Home.STATE_LANGUAGE					    = nIndex++;
				//Home.STATE_SOUNDOUTPUT				    = nIndex++;
				Home.STATE_OPERATION_HISTORY		    	= nIndex++;
				Home.STATE_FUEL_INFO				        = nIndex++;
				Home.STATE_BOOM_DETENT_MODE			  	= nIndex++;
				Home.STATE_BUCKET_DETENT_MODE		  	= nIndex++;
				Home.STATE_REVERSE_FAN_MODE				= nIndex++;
				Home.STATE_REVERSE_CAMERA				= nIndex++;
				Home.STATE_SET_CLOCK						= nIndex++;
			}
			else
			{
				Home.STATE_BRIGHTNESS_MANUALAUTO	  		= nIndex++;	
				Home.STATE_DISPLAYTYPE				    = nIndex++;
				Home.STATE_UNIT_TYPE						= nIndex++;
				Home.STATE_UNIT_FUEL				        = nIndex++;
				Home.STATE_UNIT_TEMP				        = nIndex++;
				Home.STATE_UNIT_ODO					    = nIndex++;
				Home.STATE_UNIT_WEIGHT				    = nIndex++;
				Home.STATE_UNIT_PRESSURE			      	= nIndex++;
				Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;
				Home.STATE_MACHINESTATUS_LOWER		  	= nIndex++;
				Home.STATE_LANGUAGE					    = nIndex++;
				//Home.STATE_SOUNDOUTPUT				    = nIndex++;
				Home.STATE_OPERATION_HISTORY		    	= nIndex++;
				Home.STATE_FUEL_INFO				        = nIndex++;
				Home.STATE_BOOM_DETENT_MODE			  	= nIndex++;
				Home.STATE_BUCKET_DETENT_MODE		  	= nIndex++;
				Home.STATE_REVERSE_FAN_MODE				= nIndex++;
				Home.STATE_REVERSE_CAMERA				= nIndex++;
				Home.STATE_SET_CLOCK						= nIndex++;
			}
		}else if(CheckTCLockUp){
			Home.STATE_TCLOCKUP						= nIndex++;
			Home.STATE_RIDECONTROL				    = nIndex++;
			Home.STATE_WEIGHINGSYSTEM			    = nIndex++;
			Home.STATE_WEIGHINGDISPLAY			    = nIndex++;
			Home.STATE_ERRORDETECTION			    = nIndex++;
			Home.STATE_KICKDOWN					    = nIndex++;
			Home.STATE_BRIGHTNESS_MANUALAUTO	  		= nIndex++;
			Home.STATE_DISPLAYTYPE				    = nIndex++;
			Home.STATE_UNIT_TYPE						= nIndex++;
			Home.STATE_UNIT_FUEL				        = nIndex++;
			Home.STATE_UNIT_TEMP				        = nIndex++;
			Home.STATE_UNIT_ODO					    = nIndex++;
			Home.STATE_UNIT_WEIGHT				    = nIndex++;
			Home.STATE_UNIT_PRESSURE			      	= nIndex++;
			Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;
			Home.STATE_MACHINESTATUS_LOWER		  	= nIndex++;
			Home.STATE_LANGUAGE					    = nIndex++;
			//Home.STATE_SOUNDOUTPUT				    = nIndex++;
			Home.STATE_OPERATION_HISTORY		    	= nIndex++;
			Home.STATE_FUEL_INFO				        = nIndex++;
			Home.STATE_BOOM_DETENT_MODE			  	= nIndex++;
			Home.STATE_BUCKET_DETENT_MODE		  	= nIndex++;
			Home.STATE_REVERSE_FAN_MODE				= nIndex++;
			Home.STATE_REVERSE_CAMERA				= nIndex++;
			Home.STATE_SET_CLOCK						= nIndex++;
		}
		else if(CheckEHCU)
		{
			Home.STATE_RIDECONTROL				    = nIndex++;
			Home.STATE_WEIGHINGSYSTEM			    = nIndex++;
			Home.STATE_WEIGHINGDISPLAY			    = nIndex++;
			Home.STATE_ERRORDETECTION			    = nIndex++;
			Home.STATE_KICKDOWN					    = nIndex++;
			Home.STATE_BUCKETPRIORITY			    = nIndex++;
			Home.STATE_SOFTENDSTOP_BOOMUP		  	= nIndex++;
			if(CheckTM == false)
			{
				Home.STATE_SOFTENDSTOP_BOOMDOWN			= nIndex++;
				Home.STATE_SOFTENDSTOP_BUCKETIN			= nIndex++;
				Home.STATE_SOFTENDSTOP_BUCKETDUMP		= nIndex++;
				Home.STATE_BRIGHTNESS_MANUALAUTO	  		= nIndex++;	
				Home.STATE_DISPLAYTYPE				    = nIndex++;
				Home.STATE_UNIT_TYPE						= nIndex++;
				Home.STATE_UNIT_FUEL				        = nIndex++;
				Home.STATE_UNIT_TEMP				        = nIndex++;
				Home.STATE_UNIT_ODO					    = nIndex++;
				Home.STATE_UNIT_WEIGHT				    = nIndex++;
				Home.STATE_UNIT_PRESSURE			      	= nIndex++;
				Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;
				Home.STATE_MACHINESTATUS_LOWER		  	= nIndex++;
				Home.STATE_LANGUAGE					    = nIndex++;
				//Home.STATE_SOUNDOUTPUT				    = nIndex++;
				Home.STATE_OPERATION_HISTORY		    	= nIndex++;
				Home.STATE_FUEL_INFO				        = nIndex++;
				Home.STATE_BOOM_DETENT_MODE			  	= nIndex++;
				Home.STATE_BUCKET_DETENT_MODE		  	= nIndex++;
				Home.STATE_REVERSE_FAN_MODE				= nIndex++;
				Home.STATE_REVERSE_CAMERA				= nIndex++;
				Home.STATE_SET_CLOCK						= nIndex++;
			}
			else
			{
				Home.STATE_BRIGHTNESS_MANUALAUTO	  		= nIndex++;	
				Home.STATE_DISPLAYTYPE				    = nIndex++;
				Home.STATE_UNIT_TYPE						= nIndex++;
				Home.STATE_UNIT_FUEL				        = nIndex++;
				Home.STATE_UNIT_TEMP				        = nIndex++;
				Home.STATE_UNIT_ODO					    = nIndex++;
				Home.STATE_UNIT_WEIGHT				    = nIndex++;
				Home.STATE_UNIT_PRESSURE			      	= nIndex++;
				Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;
				Home.STATE_MACHINESTATUS_LOWER		  	= nIndex++;
				Home.STATE_LANGUAGE					    = nIndex++;
				//Home.STATE_SOUNDOUTPUT				    = nIndex++;
				Home.STATE_OPERATION_HISTORY		    	= nIndex++;
				Home.STATE_FUEL_INFO				        = nIndex++;
				Home.STATE_BOOM_DETENT_MODE			  	= nIndex++;
				Home.STATE_BUCKET_DETENT_MODE		  	= nIndex++;
				Home.STATE_REVERSE_FAN_MODE				= nIndex++;
				Home.STATE_REVERSE_CAMERA				= nIndex++;
				Home.STATE_SET_CLOCK						= nIndex++;
			}
		}
		else{
			Home.STATE_RIDECONTROL				    = nIndex++;
			Home.STATE_WEIGHINGSYSTEM			    = nIndex++;
			Home.STATE_WEIGHINGDISPLAY			    = nIndex++;
			Home.STATE_ERRORDETECTION			    = nIndex++;
			Home.STATE_KICKDOWN					    = nIndex++;
			Home.STATE_BRIGHTNESS_MANUALAUTO	  		= nIndex++;
			Home.STATE_DISPLAYTYPE				    = nIndex++;
			Home.STATE_UNIT_TYPE						= nIndex++;
			Home.STATE_UNIT_FUEL				        = nIndex++;
			Home.STATE_UNIT_TEMP				        = nIndex++;
			Home.STATE_UNIT_ODO					    = nIndex++;
			Home.STATE_UNIT_WEIGHT				    = nIndex++;
			Home.STATE_UNIT_PRESSURE			      	= nIndex++;
			Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;
			Home.STATE_MACHINESTATUS_LOWER		  	= nIndex++;
			Home.STATE_LANGUAGE					    = nIndex++;
			//Home.STATE_SOUNDOUTPUT				    = nIndex++;
			Home.STATE_OPERATION_HISTORY		    	= nIndex++;
			Home.STATE_FUEL_INFO				        = nIndex++;
			Home.STATE_BOOM_DETENT_MODE			  	= nIndex++;
			Home.STATE_BUCKET_DETENT_MODE		  	= nIndex++;
			Home.STATE_REVERSE_FAN_MODE				= nIndex++;
			Home.STATE_REVERSE_CAMERA				= nIndex++;
			Home.STATE_SET_CLOCK						= nIndex++;
		}
		if(CheckBKCU == true){
			Home.STATE_ENGINE_AUTO_SHUTDOWN = nIndex++;
		}
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
		if(CheckTCLockUp)
			TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434();
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
		WeighingSystem = CAN1Comm.Get_WeighingSystemAccumulationMode_1941_PGN65450();
		WeighingDisplay = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
		ErrorDetection = ParentActivity.WeighingErrorDetect;
		KickDown = CAN1Comm.Get_KickDownShiftMode_547_PGN65434();
		if(CheckEHCU)
		{
			BucketPriority = CAN1Comm.Get_BucketPriorityOperation_2301_PGN65517();
			SoftEndStopBoomUp = CAN1Comm.Get_SoftStopBoomUp_2337_PGN65524();
			if(CheckTM == false)
			{
				SoftEndStopBoomDown = CAN1Comm.Get_SoftStopBoomDown_2338_PGN65524();
				SoftEndStopBucketIn = CAN1Comm.Get_SoftStopBucketIn_2339_PGN65524();
				SoftEndStopBucketDump = CAN1Comm.Get_SoftStopBucketOut_2340_PGN65524();
			}
		}
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
		UnitType = ParentActivity.UnitType;
		UnitFuel = ParentActivity.UnitFuel;
		UnitTemp = ParentActivity.UnitTemp;
		UnitOdo = ParentActivity.UnitOdo;
		UnitWeight = ParentActivity.UnitWeight;
		UnitPressure = ParentActivity.UnitPressure;
		MachineStatusUpper = ParentActivity.MachineStatusUpperIndex;
		MachineStatusLower = ParentActivity.MachineStatusLowerIndex;
		Language = ParentActivity.LanguageIndex;		// ++, --, 150212 bwk
		//SoundOutput = ParentActivity.SoundState;
		HourmeterDisplay = ParentActivity.HourOdometerIndex;
		FuelDisplay = ParentActivity.FuelIndex;
		
		BoomDetentMode = CAN1Comm.Get_BoomDetentMode_223_PGN61184_124();
		BucketDetentMode = CAN1Comm.Get_BucketDetentMode_224_PGN61184_124();
		
		ReverseFanMode = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		
		ReverseCamera = ParentActivity.CameraReverseMode;
		
		EngineAutoShutdownStatus = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN61184_122();
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
		if(CheckTCLockUp)
			TCLockUpDisplay(TCLockUp,_userdata.TCLockUp);
		RideControlDisplay(RideControl,_userdata.RideControl);
		WeighingSystemDisplay(WeighingSystem,_userdata.WeighingSystem);
		WeighingDisplayDisplay(WeighingDisplay,_userdata.WeighingDisplay);
		ErrorDetectionDisplay(ErrorDetection,_userdata.ErrorDetection);
		KickDownDisplay(KickDown,_userdata.KickDown);
		if(CheckEHCU)
		{
			BucketPriorityDisplay(BucketPriority,_userdata.BucketPriority);
			SoftEndStopBoomUpDisplay(SoftEndStopBoomUp,_userdata.SoftEndStopBoomUp);
			if(CheckTM == false)
			{
				SoftEndStopBoomDownDisplay(SoftEndStopBoomDown,_userdata.SoftEndStopBoomDown);
				SoftEndStopBucketInDisplay(SoftEndStopBucketIn,_userdata.SoftEndStopBucketIn);
				SoftEndStopBucketDumpDisplay(SoftEndStopBucketDump,_userdata.SoftEndStopBucketDump);
			}
		}
		// ++, 150407 bwk
		//BrightnessDisplay(Brightness,_userdata.Brightness);
		BrightnessDisplay(BrightnessManualAuto,_userdata.BrightnessManualAuto);
		// --, 150407 bwk
		UnitTypeDisplay(UnitType, _userdata.UnitType);
		UnitFuelDisplay(UnitFuel,_userdata.UnitFuel);
		UnitTempDisplay(UnitTemp,_userdata.UnitTemp);
		UnitOdoDisplay(UnitOdo,_userdata.UnitOdo);
		UnitWeightDisplay(UnitWeight,_userdata.UnitWeight);
		UnitPressureDisplay(UnitPressure,_userdata.UnitPressure);
		DisplayTypeDisplay(DisplayType,_userdata.DisplayType);
		MachineStatusUpperDisplay(MachineStatusUpper,_userdata.MachineStatusUpper);
		MachineStatusLowerDisplay(MachineStatusLower,_userdata.MachineStatusLower);
		LanguageDisplay(Language, _userdata.Language);	// ++, --, 150213 bwk
		//SoundOutputDisplay(SoundOutput,_userdata.SoundOutput);
		HourmeterDisplay(HourmeterDisplay,_userdata.HourmeterDisplay);
		FuelDisplay(FuelDisplay,_userdata.FuelDisplay);		// ++, --, 150403 bwk
		BoomDetentModeDisplay(BoomDetentMode, _userdata.BoomDetentMode);
		BucketDetentModeDisplay(BucketDetentMode, _userdata.BucketDetentMode);
		ReverseFanModeDisplay(ReverseFanMode, _userdata.ReverseFanMode);
		ReverseCameraModeDisplay(ReverseCamera, _userdata.ReverseCameraMode);
		SetClockDisplay(SetClock, _userdata.SetClock);
		if(CheckBKCU){
			EngineAutoShutdownDisplay(EngineAutoShutdownStatus, _userdata.EngineAutoShutdown);
		}
	}
	public void EngineModeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			adapter.UpdateSecond(Home.STATE_ENGINEMODE, getString(ParentActivity.getResources().getString(string.POWER), 96));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			adapter.UpdateSecond(Home.STATE_ENGINEMODE, getString(ParentActivity.getResources().getString(string.STANDARD), 95));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			adapter.UpdateSecond(Home.STATE_ENGINEMODE, getString(ParentActivity.getResources().getString(string.ECONO), 94));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			adapter.UpdateThird(Home.STATE_ENGINEMODE, getString(ParentActivity.getResources().getString(string.POWER), 96));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			adapter.UpdateThird(Home.STATE_ENGINEMODE, getString(ParentActivity.getResources().getString(string.STANDARD), 95));
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			adapter.UpdateThird(Home.STATE_ENGINEMODE, getString(ParentActivity.getResources().getString(string.ECONO), 94));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_ENGINEMODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_ENGINEMODE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_ENGINEMODE == true){
				adapter.UpdateLockIcon(Home.STATE_ENGINEMODE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_ENGINEMODE, null);
			}
		}else {
			adapter.UpdateLockIcon(Home.STATE_ENGINEMODE, null);
		}
		adapter.notifyDataSetChanged();
	}
	// ++, 150402 bwk ��� ����
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
		//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
		{
			switch (SettingData) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				adapter.UpdateSecond(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				adapter.UpdateSecond(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.ON), 97));
				break;
			default:
				break;
			}
			switch (LoadingData) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				adapter.UpdateThird(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				adapter.UpdateThird(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.ON), 97));
				break;
			default:
				break;
			}			
		}
		else{
			switch (SettingData) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				adapter.UpdateSecond(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
				adapter.UpdateSecond(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.L), 99));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
				adapter.UpdateSecond(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.M), 100));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				adapter.UpdateSecond(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.H), 101));
				break;
			default:
				break;
			}
			switch (LoadingData) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				adapter.UpdateThird(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
				adapter.UpdateThird(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.L), 99));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
				adapter.UpdateThird(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.M), 100));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				adapter.UpdateThird(Home.STATE_CCOMODE, getString(ParentActivity.getResources().getString(string.H), 101));
				break;
			default:
				break;
			}
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_CCOMODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_CCOMODE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_CCOMODE == true){
				adapter.UpdateLockIcon(Home.STATE_CCOMODE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_CCOMODE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_CCOMODE, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void ShiftModeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
			adapter.UpdateSecond(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.MANUAL), 102));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			adapter.UpdateSecond(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.AL), 103));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			adapter.UpdateSecond(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.AN), 104));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			adapter.UpdateSecond(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.AH), 105));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			adapter.UpdateThird(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.MANUAL), 102));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			adapter.UpdateThird(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.AL), 103));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			adapter.UpdateThird(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.AN), 104));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			adapter.UpdateThird(Home.STATE_SHIFTMODE, getString(ParentActivity.getResources().getString(string.AH), 105));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_SHIFTMODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_SHIFTMODE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SHIFTMODE  == true){
				adapter.UpdateLockIcon(Home.STATE_SHIFTMODE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_SHIFTMODE, null);
			}
		}else {
			adapter.UpdateLockIcon(Home.STATE_SHIFTMODE, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void TCLockUpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			adapter.UpdateSecond(Home.STATE_TCLOCKUP, getString(ParentActivity.getResources().getString(string.OFF), 98));
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			adapter.UpdateSecond(Home.STATE_TCLOCKUP, getString(ParentActivity.getResources().getString(string.ON), 97));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			adapter.UpdateThird(Home.STATE_TCLOCKUP, getString(ParentActivity.getResources().getString(string.OFF), 98));
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			adapter.UpdateThird(Home.STATE_TCLOCKUP, getString(ParentActivity.getResources().getString(string.ON), 97));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_TCLOCKUP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_TCLOCKUP, null);
		}
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_TCLOCKUP == true){
				adapter.UpdateLockIcon(Home.STATE_TCLOCKUP, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_TCLOCKUP, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_TCLOCKUP, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void RideControlDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF:
			adapter.UpdateSecond(Home.STATE_RIDECONTROL, getString(ParentActivity.getResources().getString(string.OFF), 98));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL:
			adapter.UpdateSecond(Home.STATE_RIDECONTROL, getString(ParentActivity.getResources().getString(string.On_Always), 23));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_AUTO:
			adapter.UpdateSecond(Home.STATE_RIDECONTROL, getString(ParentActivity.getResources().getString(string.On_Conditional_Speed), 168));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF:
			adapter.UpdateThird(Home.STATE_RIDECONTROL, getString(ParentActivity.getResources().getString(string.OFF), 98));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL:
			adapter.UpdateThird(Home.STATE_RIDECONTROL, getString(ParentActivity.getResources().getString(string.On_Always), 23));
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_AUTO:
			adapter.UpdateThird(Home.STATE_RIDECONTROL, getString(ParentActivity.getResources().getString(string.On_Conditional_Speed), 168));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_RIDECONTROL, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_RIDECONTROL, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_RIDECONTROL == true){
				adapter.UpdateLockIcon(Home.STATE_RIDECONTROL, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_RIDECONTROL, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_RIDECONTROL, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void WeighingSystemDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			adapter.UpdateSecond(Home.STATE_WEIGHINGSYSTEM, getString(ParentActivity.getResources().getString(string.Manual), 26));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			adapter.UpdateSecond(Home.STATE_WEIGHINGSYSTEM, getString(ParentActivity.getResources().getString(string.Automatic), 27));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL:
			adapter.UpdateThird(Home.STATE_WEIGHINGSYSTEM, getString(ParentActivity.getResources().getString(string.Manual), 26));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO:
			adapter.UpdateThird(Home.STATE_WEIGHINGSYSTEM, getString(ParentActivity.getResources().getString(string.Automatic), 27));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_WEIGHINGSYSTEM, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_WEIGHINGSYSTEM == true){
				adapter.UpdateLockIcon(Home.STATE_WEIGHINGSYSTEM, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_WEIGHINGSYSTEM, null);
			}
		}else {
			adapter.UpdateLockIcon(Home.STATE_WEIGHINGSYSTEM, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void WeighingDisplayDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			adapter.UpdateSecond(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Daily), 173));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			adapter.UpdateSecond(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Total_A), 174));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			adapter.UpdateSecond(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Total_B), 175));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			adapter.UpdateSecond(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Total_C), 176));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			adapter.UpdateThird(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Daily), 173));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			adapter.UpdateThird(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Total_A), 174));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			adapter.UpdateThird(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Total_B), 175));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			adapter.UpdateThird(Home.STATE_WEIGHINGDISPLAY, getString(ParentActivity.getResources().getString(string.Total_C), 176));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_WEIGHINGDISPLAY, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_WEIGHINGDISPLAY == true){
				adapter.UpdateLockIcon(Home.STATE_WEIGHINGDISPLAY, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_WEIGHINGDISPLAY, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_WEIGHINGDISPLAY, null);
		}
		adapter.notifyDataSetChanged();
	}
	public void ErrorDetectionDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			adapter.UpdateSecond(Home.STATE_ERRORDETECTION, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			adapter.UpdateSecond(Home.STATE_ERRORDETECTION, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF:
			adapter.UpdateThird(Home.STATE_ERRORDETECTION, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON:
			adapter.UpdateThird(Home.STATE_ERRORDETECTION, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_ERRORDETECTION, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_ERRORDETECTION, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_ERRORDETECTION == true){
				adapter.UpdateLockIcon(Home.STATE_ERRORDETECTION, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_ERRORDETECTION, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_ERRORDETECTION, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void KickDownDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			adapter.UpdateSecond(Home.STATE_KICKDOWN, getString(ParentActivity.getResources().getString(string.MODE1_DOWN_UP), 221));
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			adapter.UpdateSecond(Home.STATE_KICKDOWN, getString(ParentActivity.getResources().getString(string.MODE2_DOWN_ONLY), 222));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			adapter.UpdateThird(Home.STATE_KICKDOWN, getString(ParentActivity.getResources().getString(string.MODE1_DOWN_UP), 221));
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			adapter.UpdateThird(Home.STATE_KICKDOWN, getString(ParentActivity.getResources().getString(string.MODE2_DOWN_ONLY), 222));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_KICKDOWN, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_KICKDOWN, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_KICKDOWN == true){
				adapter.UpdateLockIcon(Home.STATE_KICKDOWN, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_KICKDOWN, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_KICKDOWN, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void BucketPriorityDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF:
			adapter.UpdateSecond(Home.STATE_BUCKETPRIORITY, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_ON:
			adapter.UpdateSecond(Home.STATE_BUCKETPRIORITY, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF:
			adapter.UpdateThird(Home.STATE_BUCKETPRIORITY, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_ON:
			adapter.UpdateThird(Home.STATE_BUCKETPRIORITY, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_BUCKETPRIORITY, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_BUCKETPRIORITY, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_BUCKETPRIORITY == true){
				adapter.UpdateLockIcon(Home.STATE_BUCKETPRIORITY, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_BUCKETPRIORITY, null);
			}
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBoomUpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BOOMUP, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BOOMUP, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BOOMUP, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BOOMUP, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BOOMUP, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SOFTENDSTOP_BOOMUP == true){
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BOOMUP, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BOOMUP, null);
			}
		}else {
			adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BOOMUP, null);
		}
		
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBoomDownDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BOOMDOWN, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BOOMDOWN, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BOOMDOWN, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BOOMDOWN, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BOOMDOWN, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SOFTENDSTOP_BOOMDOWN == true){
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BOOMDOWN, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BOOMDOWN, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BOOMDOWN, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBucketInDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BUCKETIN, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BUCKETIN, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BUCKETIN, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BUCKETIN, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BUCKETIN, null);
		}
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SOFTENDSTOP_BUCKETIN == true){
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BUCKETIN, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BUCKETIN, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BUCKETIN, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBucketDumpDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BUCKETDUMP, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON:
			adapter.UpdateSecond(Home.STATE_SOFTENDSTOP_BUCKETDUMP, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BUCKETDUMP, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON:
			adapter.UpdateThird(Home.STATE_SOFTENDSTOP_BUCKETDUMP, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_SOFTENDSTOP_BUCKETDUMP, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SOFTENDSTOP_BUCKETDUMP == true){
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BUCKETDUMP, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BUCKETDUMP, null);
			}
		}else {
			adapter.UpdateLockIcon(Home.STATE_SOFTENDSTOP_BUCKETDUMP, null);
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
			adapter.UpdateSecond(Home.STATE_BRIGHTNESS_MANUALAUTO, getString(ParentActivity.getResources().getString(string.Manual), 26));
			break;
		case Home.BRIGHTNESS_AUTO:
			adapter.UpdateSecond(Home.STATE_BRIGHTNESS_MANUALAUTO, getString(ParentActivity.getResources().getString(string.Automatic), 27));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.BRIGHTNESS_MANUAL:
			adapter.UpdateThird(Home.STATE_BRIGHTNESS_MANUALAUTO, getString(ParentActivity.getResources().getString(string.Manual), 26));
			break;
		case Home.BRIGHTNESS_AUTO:
			adapter.UpdateThird(Home.STATE_BRIGHTNESS_MANUALAUTO, getString(ParentActivity.getResources().getString(string.Automatic), 27));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_BRIGHTNESS_MANUALAUTO, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_BRIGHTNESS_MANUALAUTO == true){
				adapter.UpdateLockIcon(Home.STATE_BRIGHTNESS_MANUALAUTO, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_BRIGHTNESS_MANUALAUTO, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_BRIGHTNESS_MANUALAUTO, null);
		}
		adapter.notifyDataSetChanged();
		// --, 150407 bwk
		
	}	
	public void UnitTypeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_TYPE_CUSTOM:
			adapter.UpdateSecond(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.CUSTOM), 463));
			break;
		case Home.UNIT_TYPE_US:
			adapter.UpdateSecond(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.US), 462));
			break;
		case Home.UNIT_TYPE_METRIC:
			adapter.UpdateSecond(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.METRIC), 461));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_TYPE_CUSTOM:
			adapter.UpdateThird(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.CUSTOM), 463));
			break;
		case Home.UNIT_TYPE_US:
			adapter.UpdateThird(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.US), 462));
			break;
		case Home.UNIT_TYPE_METRIC:
			adapter.UpdateThird(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.METRIC), 461));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_UNIT_TYPE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_UNIT_TYPE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_UNIT_TYPE == true){
				adapter.UpdateLockIcon(Home.STATE_UNIT_TYPE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_UNIT_TYPE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_UNIT_TYPE, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitFuelDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_FUEL_L:
			adapter.UpdateSecond(Home.STATE_UNIT_FUEL, getString(ParentActivity.getResources().getString(string.l), 81));
			break;
		case Home.UNIT_FUEL_GAL:
			adapter.UpdateSecond(Home.STATE_UNIT_FUEL, getString(ParentActivity.getResources().getString(string.gal), 466));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_FUEL_L:
			adapter.UpdateThird(Home.STATE_UNIT_FUEL, getString(ParentActivity.getResources().getString(string.l), 81));
			break;
		case Home.UNIT_FUEL_GAL:
			adapter.UpdateThird(Home.STATE_UNIT_FUEL, getString(ParentActivity.getResources().getString(string.gal), 466));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_UNIT_FUEL, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_UNIT_FUEL, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_UNIT_TYPE == true){
				adapter.UpdateLockIcon(Home.STATE_UNIT_FUEL, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_UNIT_FUEL, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_UNIT_FUEL, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitTempDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_TEMP_C:
			adapter.UpdateSecond(Home.STATE_UNIT_TEMP, getString(ParentActivity.getResources().getString(string.C), 8));
			break;
		case Home.UNIT_TEMP_F:
			adapter.UpdateSecond(Home.STATE_UNIT_TEMP, getString(ParentActivity.getResources().getString(string.F), 9));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_TEMP_C:
			adapter.UpdateThird(Home.STATE_UNIT_TEMP, getString(ParentActivity.getResources().getString(string.C), 8));
			break;
		case Home.UNIT_TEMP_F:
			adapter.UpdateThird(Home.STATE_UNIT_TEMP, getString(ParentActivity.getResources().getString(string.F), 9));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_UNIT_TEMP, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_UNIT_TEMP, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_UNIT_TYPE == true){
				adapter.UpdateLockIcon(Home.STATE_UNIT_TEMP, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_UNIT_TEMP, null);
			}
		}else{
			adapter.UpdateLockIcon(Home.STATE_UNIT_TEMP, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitOdoDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_ODO_KM:
			adapter.UpdateSecond(Home.STATE_UNIT_ODO, getString(ParentActivity.getResources().getString(string.km), 37));
			break;
		case Home.UNIT_ODO_MILE:
			adapter.UpdateSecond(Home.STATE_UNIT_ODO, getString(ParentActivity.getResources().getString(string.mile), 38));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_ODO_KM:
			adapter.UpdateThird(Home.STATE_UNIT_ODO, getString(ParentActivity.getResources().getString(string.km), 37));
			break;
		case Home.UNIT_ODO_MILE:
			adapter.UpdateThird(Home.STATE_UNIT_ODO, getString(ParentActivity.getResources().getString(string.mile), 38));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_UNIT_ODO, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_UNIT_ODO, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_UNIT_TYPE == true){
				adapter.UpdateLockIcon(Home.STATE_UNIT_ODO, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_UNIT_ODO, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_UNIT_ODO, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitWeightDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_WEIGHT_TON:
			adapter.UpdateSecond(Home.STATE_UNIT_WEIGHT, getString(ParentActivity.getResources().getString(string.ton), 11));
			break;
		case Home.UNIT_WEIGHT_LB:
			adapter.UpdateSecond(Home.STATE_UNIT_WEIGHT, getString(ParentActivity.getResources().getString(string.lb), 12));
			break;
		case Home.UNIT_WEIGHT_US_TON:
			adapter.UpdateSecond(Home.STATE_UNIT_WEIGHT, getString(ParentActivity.getResources().getString(string.USTon), 467));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_WEIGHT_TON:
			adapter.UpdateThird(Home.STATE_UNIT_WEIGHT, getString(ParentActivity.getResources().getString(string.ton), 11));
			break;
		case Home.UNIT_WEIGHT_LB:
			adapter.UpdateThird(Home.STATE_UNIT_WEIGHT, getString(ParentActivity.getResources().getString(string.lb), 12));
			break;
		case Home.UNIT_WEIGHT_US_TON:
			adapter.UpdateThird(Home.STATE_UNIT_WEIGHT, getString(ParentActivity.getResources().getString(string.USTon), 467));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_UNIT_WEIGHT, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_UNIT_WEIGHT, null);
		}
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_UNIT_TYPE == true){
				adapter.UpdateLockIcon(Home.STATE_UNIT_WEIGHT, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_UNIT_WEIGHT, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_UNIT_WEIGHT, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitPressureDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.UNIT_PRESSURE_BAR:
			adapter.UpdateSecond(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.bar), 43));
			break;
		case Home.UNIT_PRESSURE_MPA:
			adapter.UpdateSecond(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.Mpa), 44));
			break;
		case Home.UNIT_PRESSURE_KGF:
			adapter.UpdateSecond(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.kgf_cm), 45));
			break;
		case Home.UNIT_PRESSURE_PSI:
			adapter.UpdateSecond(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.Psi), 46));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.UNIT_PRESSURE_BAR:
			adapter.UpdateThird(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.bar), 43));
			break;
		case Home.UNIT_PRESSURE_MPA:
			adapter.UpdateThird(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.Mpa), 44));
			break;
		case Home.UNIT_PRESSURE_KGF:
			adapter.UpdateThird(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.kgf_cm), 45));
			break;
		case Home.UNIT_PRESSURE_PSI:
			adapter.UpdateThird(Home.STATE_UNIT_PRESSURE, getString(ParentActivity.getResources().getString(string.Psi), 46));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_UNIT_PRESSURE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_UNIT_PRESSURE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_UNIT_TYPE == true){
				adapter.UpdateLockIcon(Home.STATE_UNIT_PRESSURE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_UNIT_PRESSURE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_UNIT_PRESSURE, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void DisplayTypeDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.DISPLAY_TYPE_A:
			adapter.UpdateSecond(Home.STATE_DISPLAYTYPE, getString(ParentActivity.getResources().getString(string.Type_A), 423));
			break;
		case Home.DISPLAY_TYPE_B:
			adapter.UpdateSecond(Home.STATE_DISPLAYTYPE, getString(ParentActivity.getResources().getString(string.Type_B), 424));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.DISPLAY_TYPE_A:
			adapter.UpdateThird(Home.STATE_DISPLAYTYPE, getString(ParentActivity.getResources().getString(string.Type_A), 423));
			break;
		case Home.DISPLAY_TYPE_B:
			adapter.UpdateThird(Home.STATE_DISPLAYTYPE, getString(ParentActivity.getResources().getString(string.Type_B), 424));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_DISPLAYTYPE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_DISPLAYTYPE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_DISPLAYTYPE == true){
				adapter.UpdateLockIcon(Home.STATE_DISPLAYTYPE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_DISPLAYTYPE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_DISPLAYTYPE, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void MachineStatusUpperDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.HYD_Temp), 111));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Battery_Volt), 113));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Coolant_Temp), 115));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.TM_Oil_Temp), 112));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Weighing_System), 114));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Front_Axle_Temp), 144));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Rear_Axle_Temp), 145));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.HYD_Temp), 111));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Battery_Volt), 113));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Coolant_Temp), 115));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.TM_Oil_Temp), 112));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Weighing_System), 114));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Front_Axle_Temp), 144));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_UPPER, getString(ParentActivity.getResources().getString(string.Rear_Axle_Temp), 145));
			break;
		default:
			break;
		}		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_MACHINESTATUS_UPPER, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_MACHINESTATUS_UPPER == true){
				adapter.UpdateLockIcon(Home.STATE_MACHINESTATUS_UPPER, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_MACHINESTATUS_UPPER, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_MACHINESTATUS_UPPER, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void MachineStatusLowerDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.HYD_Temp), 111));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Battery_Volt), 113));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Coolant_Temp), 115));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.TM_Oil_Temp), 112));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Weighing_System), 114));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Front_Axle_Temp), 144));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			adapter.UpdateSecond(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Rear_Axle_Temp), 145));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, "-");
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.HYD_Temp), 111));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Battery_Volt), 113));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Coolant_Temp), 115));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.TM_Oil_Temp), 112));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Weighing_System), 114));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Front_Axle_Temp), 144));
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
			adapter.UpdateThird(Home.STATE_MACHINESTATUS_LOWER, getString(ParentActivity.getResources().getString(string.Rear_Axle_Temp), 145));
			break;
		default:
			break;
		}		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_MACHINESTATUS_LOWER, null);
		}
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_MACHINESTATUS_LOWER == true){
				adapter.UpdateLockIcon(Home.STATE_MACHINESTATUS_LOWER, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_MACHINESTATUS_LOWER, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_MACHINESTATUS_LOWER, null);
		}
		adapter.notifyDataSetChanged();
	}	
	
	public void LanguageDisplay(int SettingData, int LoadingData){
		// ++, 150212 bwk
		switch (SettingData) {
			case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Korean), 432));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.English), 433));
				break;
			// ++, 150225 bwk
			case Home.STATE_DISPLAY_LANGUAGE_GERMAN:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.German), 434));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_FRENCH:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.French), 435));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SPANISH:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Spanish), 436));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_PORTUGUE:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Portuguese), 437));
				break;
//			case Home.STATE_DISPLAY_LANGUAGE_CHINESE:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Chinese));
//				break;
//			case Home.STATE_DISPLAY_LANGUAGE_RUSIAN:
//				adapter.UpdateSecond(STATE_LANGUAGE, ParentActivity.getResources().getString(string.Russian));
//				break;
			case Home.STATE_DISPLAY_LANGUAGE_ITALIAN:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Italian), 438));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_NEDERLAND:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Dutch), 439));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SWEDISH:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Swedish), 440));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_TURKISH:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Turkish), 441));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Slovakian), 442));
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ESTONIAN:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Estonian), 443));
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
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Finnish), 444));
				break;
			// --, 150225 bwk
		}
		switch (LoadingData) {
		case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
			adapter.UpdateThird(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Korean), 432));
			break;
		case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
			adapter.UpdateThird(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.English), 433));
			break;
		}
		// --, 150212 bwk
		
		// ++, 150213 bwk
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_LANGUAGE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_LANGUAGE, null);
		}
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_LANGUAGE == true){
				adapter.UpdateLockIcon(Home.STATE_LANGUAGE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_LANGUAGE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_LANGUAGE, null);
		}
		adapter.notifyDataSetChanged();
		// --, 150213 bwk
	}
	
	/*public void SoundOutputDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case Home.STATE_INTERNAL_SPK:
			adapter.UpdateSecond(Home.STATE_SOUNDOUTPUT, getString(ParentActivity.getResources().getString(string.Internal_Speaker), 420));
			break;
		case Home.STATE_EXTERNAL_AUX:
			adapter.UpdateSecond(Home.STATE_SOUNDOUTPUT, getString(ParentActivity.getResources().getString(string.External_Aux), 421));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case Home.STATE_INTERNAL_SPK:
			adapter.UpdateThird(Home.STATE_SOUNDOUTPUT, getString(ParentActivity.getResources().getString(string.Internal_Speaker), 420));
			break;
		case Home.STATE_EXTERNAL_AUX:
			adapter.UpdateThird(Home.STATE_SOUNDOUTPUT, getString(ParentActivity.getResources().getString(string.External_Aux), 421));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_SOUNDOUTPUT, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_SOUNDOUTPUT, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SOUNDOUTPUT == true){
				adapter.UpdateLockIcon(Home.STATE_SOUNDOUTPUT, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_SOUNDOUTPUT, null);
			}
		}else {
			adapter.UpdateLockIcon(Home.STATE_SOUNDOUTPUT, null);
		}
		adapter.notifyDataSetChanged();
	}	*/
	public void HourmeterDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
			adapter.UpdateSecond(Home.STATE_OPERATION_HISTORY, "-");
			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			adapter.UpdateSecond(Home.STATE_OPERATION_HISTORY, getString(ParentActivity.getResources().getString(string.Latest_Hourmeter), 107));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			adapter.UpdateSecond(Home.STATE_OPERATION_HISTORY, getString(ParentActivity.getResources().getString(string.Total_Odometer), 109));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			adapter.UpdateSecond(Home.STATE_OPERATION_HISTORY, getString(ParentActivity.getResources().getString(string.Latest_Odometer), 110));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_OPERATION_NOSELECT:
			adapter.UpdateThird(Home.STATE_OPERATION_HISTORY, "-");
			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			adapter.UpdateThird(Home.STATE_OPERATION_HISTORY, getString(ParentActivity.getResources().getString(string.Latest_Hourmeter), 107));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			adapter.UpdateThird(Home.STATE_OPERATION_HISTORY, getString(ParentActivity.getResources().getString(string.Total_Odometer), 109));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			adapter.UpdateThird(Home.STATE_OPERATION_HISTORY, getString(ParentActivity.getResources().getString(string.Latest_Odometer), 110));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_OPERATION_HISTORY, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_OPERATION_HISTORY, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_OPERATION_HISTORY == true){
				adapter.UpdateLockIcon(Home.STATE_OPERATION_HISTORY, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_OPERATION_HISTORY, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_OPERATION_HISTORY, null);
		}
		adapter.notifyDataSetChanged();
	}	
	public void FuelDisplay(int SettingData, int LoadingData){
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
			adapter.UpdateSecond(Home.STATE_FUEL_INFO, "-");
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			adapter.UpdateSecond(Home.STATE_FUEL_INFO, getString(ParentActivity.getResources().getString(string.Average_Fuel_Rate), 108));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			adapter.UpdateSecond(Home.STATE_FUEL_INFO, getString(ParentActivity.getResources().getString(string.A_Days_Fuel_Used), 147));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
			adapter.UpdateThird(Home.STATE_FUEL_INFO, "-");
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			adapter.UpdateThird(Home.STATE_FUEL_INFO, getString(ParentActivity.getResources().getString(string.Average_Fuel_Rate), 108));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			adapter.UpdateThird(Home.STATE_FUEL_INFO, getString(ParentActivity.getResources().getString(string.A_Days_Fuel_Used), 147));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_FUEL_INFO, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_FUEL_INFO, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_FUEL_INFO == true){
				adapter.UpdateLockIcon(Home.STATE_FUEL_INFO, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {
				adapter.UpdateLockIcon(Home.STATE_FUEL_INFO, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_FUEL_INFO, null);
		}
		adapter.notifyDataSetChanged();		
	}
	/////////////////////////////////////////////////////////////////////
	void BoomDetentModeDisplay(int SettingData, int LoadingData){
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF:
			adapter.UpdateSecond(Home.STATE_BOOM_DETENT_MODE, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN:
			adapter.UpdateSecond(Home.STATE_BOOM_DETENT_MODE, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF:
			adapter.UpdateThird(Home.STATE_BOOM_DETENT_MODE, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN:
			adapter.UpdateThird(Home.STATE_BOOM_DETENT_MODE, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_BOOM_DETENT_MODE, null);
		}
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
				if(Home.LOCK_STATE_BOOM_DETENT_MODE == true){
					adapter.UpdateLockIcon(Home.STATE_BOOM_DETENT_MODE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
				}else {
					adapter.UpdateLockIcon(Home.STATE_BOOM_DETENT_MODE, null);
				}
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_BOOM_DETENT_MODE, null);
		}
		adapter.notifyDataSetChanged();		
	}
	/////////////////////////////////////////////////////////////////////
	void BucketDetentModeDisplay(int SettingData, int LoadingData){
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF:
			adapter.UpdateSecond(Home.STATE_BUCKET_DETENT_MODE, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN:
			adapter.UpdateSecond(Home.STATE_BUCKET_DETENT_MODE, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF:
			adapter.UpdateThird(Home.STATE_BUCKET_DETENT_MODE, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN:
			adapter.UpdateThird(Home.STATE_BUCKET_DETENT_MODE, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_BUCKET_DETENT_MODE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_BUCKET_DETENT_MODE == true){
				adapter.UpdateLockIcon(Home.STATE_BUCKET_DETENT_MODE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {	
				adapter.UpdateLockIcon(Home.STATE_BUCKET_DETENT_MODE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_BUCKET_DETENT_MODE, null);
		}
		adapter.notifyDataSetChanged();		
	}
	
	void ReverseFanModeDisplay(int SettingData, int LoadingData){
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			adapter.UpdateSecond(Home.STATE_REVERSE_FAN_MODE, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL:
			adapter.UpdateSecond(Home.STATE_REVERSE_FAN_MODE, getString(ParentActivity.getResources().getString(string.Manual), 26));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			adapter.UpdateSecond(Home.STATE_REVERSE_FAN_MODE, getString(ParentActivity.getResources().getString(string.Automatic), 27));
			break;

		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			adapter.UpdateThird(Home.STATE_REVERSE_FAN_MODE, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL:
			adapter.UpdateThird(Home.STATE_REVERSE_FAN_MODE, getString(ParentActivity.getResources().getString(string.Manual), 26));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			adapter.UpdateThird(Home.STATE_REVERSE_FAN_MODE, getString(ParentActivity.getResources().getString(string.Automatic), 27));
			break;

		default:
			break;
		}
		
		if(SettingData != LoadingData){
			adapter.UpdateIcon(Home.STATE_REVERSE_FAN_MODE, ParentActivity.getResources().getDrawable(R.drawable.main_quick_user_x));
		}else{
			adapter.UpdateIcon(Home.STATE_REVERSE_FAN_MODE, null);
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_REVERSE_FAN_MODE == true){
				adapter.UpdateLockIcon(Home.STATE_REVERSE_FAN_MODE, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {	
				adapter.UpdateLockIcon(Home.STATE_REVERSE_FAN_MODE, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_REVERSE_FAN_MODE, null);
		}
		adapter.notifyDataSetChanged();		
	}
	void ReverseCameraModeDisplay(int SettingData, int LoadingData){
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_REVERSE_CAMERA == true){
				adapter.UpdateLockIcon(Home.STATE_REVERSE_CAMERA, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {	
				adapter.UpdateLockIcon(Home.STATE_REVERSE_CAMERA, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_REVERSE_CAMERA, null);
		}
		adapter.notifyDataSetChanged();		
	}
	
	void SetClockDisplay(int SettingData, int LoadingData){
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_SET_CLOCK == true){
				adapter.UpdateLockIcon(Home.STATE_SET_CLOCK, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {	
				adapter.UpdateLockIcon(Home.STATE_SET_CLOCK, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_SET_CLOCK, null);
		}
		adapter.notifyDataSetChanged();		
	}
	
	void EngineAutoShutdownDisplay(int SettingData, int LoadingData){
		
		
		switch (SettingData) {
		case CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF:
			adapter.UpdateSecond(Home.STATE_ENGINE_AUTO_SHUTDOWN, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON:
			adapter.UpdateSecond(Home.STATE_ENGINE_AUTO_SHUTDOWN, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		switch (LoadingData) {
		case CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF:
			adapter.UpdateThird(Home.STATE_ENGINE_AUTO_SHUTDOWN, getString(ParentActivity.getResources().getString(string.Off), 20));
			break;
		case CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON:
			adapter.UpdateThird(Home.STATE_ENGINE_AUTO_SHUTDOWN, getString(ParentActivity.getResources().getString(string.On), 19));
			break;
		default:
			break;
		}
		
		if(ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_LOCK){
			if(Home.LOCK_STATE_ENGINE_AUTO_SHUTDOWN == true){
				adapter.UpdateLockIcon(Home.STATE_ENGINE_AUTO_SHUTDOWN, ParentActivity.getResources().getDrawable(R.drawable.locking_userswitching));
			}else {	
				adapter.UpdateLockIcon(Home.STATE_ENGINE_AUTO_SHUTDOWN, null);
			}
		} else {
			adapter.UpdateLockIcon(Home.STATE_ENGINE_AUTO_SHUTDOWN, null);
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
		_userdata.UnitType = UnitType;
		_userdata.UnitFuel = UnitFuel;
		_userdata.UnitTemp = UnitTemp;
		_userdata.UnitOdo = UnitOdo;
		_userdata.UnitWeight = UnitWeight;
		_userdata.UnitPressure = UnitPressure;
		_userdata.MachineStatusUpper = MachineStatusUpper;
		_userdata.MachineStatusLower = MachineStatusLower;
		_userdata.Language = Language;
		//_userdata.SoundOutput = SoundOutput;
		_userdata.HourmeterDisplay = HourmeterDisplay;
		_userdata.FuelDisplay = FuelDisplay;
		_userdata.BoomDetentMode = BoomDetentMode;
		_userdata.BucketDetentMode = BucketDetentMode;
		_userdata.ReverseFanMode = ReverseFanMode;
		_userdata.ReverseCameraMode = ReverseCamera;
		
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
		

		if(Home.LOCK_STATE_ENGINEMODE  == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(_userdata.EngineMode);
		CAN1Comm.TxCANToMCU(101);
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(15);
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(3);
		}

		//CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(_userdata.WarmingUp);
		
		if(Home.LOCK_STATE_CCOMODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(_userdata.CCOMode);
		}
		if(Home.LOCK_STATE_SHIFTMODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(_userdata.ShiftMode);
		}

		if(Home.LOCK_STATE_TCLOCKUP == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		if(CheckTCLockUp)
			CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(_userdata.TCLockUp);
		else
			CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(3);
		}

		if(Home.LOCK_STATE_KICKDOWN == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_KickDownShiftMode_547_PGN61184_104(_userdata.KickDown);
		CAN1Comm.TxCANToMCU(104);
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(15);
		CAN1Comm.Set_TransmisstionShiftMode_543_PGN61184_104(15);
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(3);
		CAN1Comm.Set_KickDownShiftMode_547_PGN61184_104(3);
		}
		
		if(Home.LOCK_STATE_RIDECONTROL == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(_userdata.RideControl);
		CAN1Comm.TxCANToMCU(247);
		}
		
		if(Home.LOCK_STATE_WEIGHINGSYSTEM == false  || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(_userdata.WeighingSystem);
		}

		if(Home.LOCK_STATE_WEIGHINGDISPLAY == false  || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(_userdata.WeighingDisplay);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(3);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
		}
		

		if(CheckEHCU)
		{
			if(Home.LOCK_STATE_BUCKETPRIORITY == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
			CAN1Comm.Set_BucketPriorityOperation_2301_PGN61184_203(_userdata.BucketPriority);
			}
			if(Home.LOCK_STATE_SOFTENDSTOP_BOOMUP == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
			CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(_userdata.SoftEndStopBoomUp);
			}
			if(CheckTM == false)
			{
				if(Home.LOCK_STATE_SOFTENDSTOP_BOOMDOWN == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){	
				CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(_userdata.SoftEndStopBoomDown);
				}
				if(Home.LOCK_STATE_SOFTENDSTOP_BUCKETIN == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
				CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(_userdata.SoftEndStopBucketIn);
				}
				if(Home.LOCK_STATE_SOFTENDSTOP_BUCKETDUMP == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
				CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(_userdata.SoftEndStopBucketDump);
			}
			}
			else
			{
				CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
				CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
				CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
			}
			CAN1Comm.TxCANToMCU(203);
		}
		CAN1Comm.Set_BucketPriorityOperation_2301_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		
		if(Home.LOCK_STATE_UNIT_ODO == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_SpeedmeterUnitChange_PGN65327(_userdata.UnitOdo);
		CAN1Comm.TxCANToMCU(47);
		}
		
		if(Home.LOCK_STATE_BOOM_DETENT_MODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(_userdata.BoomDetentMode);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(7);
		}

		if(Home.LOCK_STATE_BUCKET_DETENT_MODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(_userdata.BucketDetentMode);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
		}
		
		if(Home.LOCK_STATE_ERRORDETECTION == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.WeighingErrorDetect = _userdata.ErrorDetection;
		}

		// ++, 150407 bwk
//		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(_userdata.Brightness + 1);
//		CAN1Comm.TxCANToMCU(109);
//		CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(15);
//		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD,_userdata.Brightness + 1);
//		
//		ParentActivity.BrihgtnessCurrent = _userdata.Brightness;
//		ParentActivity.BrightnessManualLevel = _userdata.Brightness;
//		ParentActivity.BrightnessManualAuto = Home.BRIGHTNESS_MANUAL;

		if(Home.LOCK_STATE_BRIGHTNESS_MANUALAUTO == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
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
		} 



		// --, 150407 bwk
		
		if(Home.LOCK_STATE_UNIT_TYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.UnitType = Home.UNIT_TYPE_CUSTOM;
		}
		
		if(Home.LOCK_STATE_UNIT_TYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.UnitFuel = _userdata.UnitFuel;
		}
		if(Home.LOCK_STATE_UNIT_TYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.UnitOdo = _userdata.UnitOdo;
		}
		if(Home.LOCK_STATE_UNIT_TYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.UnitTemp = _userdata.UnitTemp;
		}
		if(Home.LOCK_STATE_UNIT_TYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.UnitWeight = _userdata.UnitWeight;
		}
		if(Home.LOCK_STATE_UNIT_TYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.UnitPressure = _userdata.UnitPressure;
		}
		if(Home.LOCK_STATE_MACHINESTATUS_UPPER == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.MachineStatusUpperIndex = _userdata.MachineStatusUpper;
		}
		if(Home.LOCK_STATE_MACHINESTATUS_LOWER == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.MachineStatusLowerIndex = _userdata.MachineStatusLower;
		}

		if(Home.LOCK_STATE_OPERATION_HISTORY == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.HourOdometerIndex = _userdata.HourmeterDisplay;
		}
		if(Home.LOCK_STATE_FUEL_INFO == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.FuelIndex = _userdata.FuelDisplay;
		}
		
		// ++, 150213 bwk
		if(Home.LOCK_STATE_LANGUAGE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.LanguageIndex = _userdata.Language;
		ParentActivity.setLanguage();
		ParentActivity.ResetPopup(); 		// ++, --, 150305 bwk
		}
		
		// --, 150213 bwk
		if(Home.LOCK_STATE_DISPLAYTYPE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.DisplayType = _userdata.DisplayType;
		}
		
		/*if(Home.LOCK_STATE_SOUNDOUTPUT == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
		ParentActivity.SoundState = _userdata.SoundOutput;
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
		}*/
		
		if(Home.LOCK_STATE_REVERSE_FAN_MODE == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
			ParentActivity.CoolingReverseFanStatus = _userdata.ReverseFanMode;
			CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_OFF);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(0xFF);
			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(3);
		}
		
		if(Home.LOCK_STATE_REVERSE_CAMERA == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
			ParentActivity.CameraReverseMode = _userdata.ReverseCameraMode;
		}
		if(Home.LOCK_STATE_ENGINE_AUTO_SHUTDOWN  == false || ParentActivity.LockUserSwitching == Home.STATE_USERSWITCHING_UNLOCK){
			ParentActivity.EngineAutoShutdownStatus = _userdata.EngineAutoShutdown;
		}
		
		
		
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
						if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_USERSWITCHING_LOCKING){
							ParentActivity.showUserSwitchingLockingPopup();
						}
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
}
