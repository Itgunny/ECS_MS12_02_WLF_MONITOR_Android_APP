package taeha.wheelloader.fseries_monitor.menu;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import customlist.userswitching.IconTextItemUserSwitching;
import customlist.userswitching.lock.IconTextItemUserSwitchingLock;
import customlist.userswitching.lock.IconTextListAdapterUserSwitchingLock;

public class SettingLockUserSwitchingFragment extends ParentFragment{

	
	public boolean LOCK_STATE_ENGINEMODE 			= false;
	public boolean LOCK_STATE_CCOMODE				= false;
	public boolean LOCK_STATE_SHIFTMODE				= false;
	public boolean LOCK_STATE_TCLOCKUP				= false;
	public boolean LOCK_STATE_RIDECONTROL			= false;
	public boolean LOCK_STATE_WEIGHINGSYSTEM			= false;
	public boolean LOCK_STATE_WEIGHINGDISPLAY		= false;
	public boolean LOCK_STATE_ERRORDETECTION			= false;
	public boolean LOCK_STATE_KICKDOWN				= false;
	public boolean LOCK_STATE_BUCKETPRIORITY			= false;
	public boolean LOCK_STATE_SOFTENDSTOP_BOOMUP		= false;
	public boolean LOCK_STATE_SOFTENDSTOP_BOOMDOWN 	= false;
	public boolean LOCK_STATE_SOFTENDSTOP_BUCKETIN	= false;
	public boolean LOCK_STATE_SOFTENDSTOP_BUCKETDUMP	= false;
	public boolean LOCK_STATE_BRIGHTNESS_MANUALAUTO	= false;
	public boolean LOCK_STATE_DISPLAYTYPE			= false;
	public boolean LOCK_STATE_UNIT_TYPE				= false;
	public boolean LOCK_STATE_UNIT_FUEL				= false;
	public boolean LOCK_STATE_UNIT_TEMP				= false;
	public boolean LOCK_STATE_UNIT_ODO				= false;
	public boolean LOCK_STATE_UNIT_WEIGHT			= false;
	public boolean LOCK_STATE_UNIT_PRESSURE			= false;
	public boolean LOCK_STATE_MACHINESTATUS_UPPER	= false;
	public boolean LOCK_STATE_MACHINESTATUS_LOWER	= false;
	public boolean LOCK_STATE_LANGUAGE				= false;
	public boolean LOCK_STATE_SOUNDOUTPUT			= false;
	public boolean LOCK_STATE_OPERATION_HISTORY		= false;	
	public boolean LOCK_STATE_FUEL_INFO				= false;	
	public boolean LOCK_STATE_BOOM_DETENT_MODE		= false;
	public boolean LOCK_STATE_BUCKET_DETENT_MODE		= false;
	public boolean LOCK_STATE_REVERSE_FAN_MODE		= false;
	public boolean LOCK_STATE_REVERSE_CAMERA			= false;
	public boolean LOCK_STATE_SET_CLOCK				= false;
	public boolean LOCK_STATE_ENGINE_AUTO_SHUTDOWN	= false;
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextFitTextView textViewOK;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	Handler HandleCursurDisplay;
	
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
	
	// ListView
	ListView listView;
	IconTextListAdapterUserSwitchingLock adapter;
	
	boolean CheckTCLockUp = false;
	boolean CheckEHCU = false;
	boolean CheckTM = false;
	boolean CheckBKCU = false;
	
	CheckBox checkBox;
	
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
		 TAG = "SettingLockUserSwitchingFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_lock_userswitching, null);
		ParentActivity.LoadLockStatePref();
		

		InitResource();
		InitValuables();
		InitButtonListener();
		
		GetDataFromNative();
		UserSwitchingListUpdate();
		
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_LOCKING;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(getString(ParentActivity.getResources().getString(string.Setting_Lock_List), 500));
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	////////////////////////////////////////////////
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_userswitching_lock_low_ok);
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_userswitching_lock_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15)); 
		
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_userswitching_lock);
		
		checkBox = (CheckBox)mRoot.findViewById(R.id.imageView_menu_body_userswitching_lock_icon);
		adapter = new IconTextListAdapterUserSwitchingLock(ParentActivity);
		adapter.clearItem();
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		listView.setAdapter(adapter);
		
		boolean BackgroundFlag = true;
		


		
		adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				getString(ParentActivity.getResources().getString(string.Engine_Mode), 240)
				, ""));
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
		{
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.ICCO_Mode), 205)
					, ""));
		}
		else
		{
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.CCO_Mode), 204)
					, ""));
		}
		adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
				null,
				getString(ParentActivity.getResources().getString(string.Shift_Mode), 206)
				, ""));
		
		if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
			CheckTCLockUp = false;
		}else{
			CheckTCLockUp = true;
			BackgroundFlag = false;
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.TC_Lock_Up), 210)
					, ""));
		}
		if(BackgroundFlag == false)
		{
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Ride_Control), 155)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_System), 114)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_Display), 170)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Error_Detection), 171)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Kick_Down), 207)
					, ""));
			BackgroundFlag = true;
		}
		else
		{
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Ride_Control), 155)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_System), 114)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Weighing_Display), 170)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Error_Detection), 171)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Kick_Down), 207)
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
				adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
						null,
						getString(ParentActivity.getResources().getString(string.Bucket_Priority), 211)
						, ""));
				adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
						null,
						getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Up), 236)
						, ""));
				BackgroundFlag = false;
				
				if(CheckTM == false)
				{
					adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Down), 237)
							, ""));
					adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_In), 238)
							, ""));
					adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_Dump), 239)
							, ""));
					BackgroundFlag = true;
				}
			}
			else
			{
				adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
						null,
						getString(ParentActivity.getResources().getString(string.Bucket_Priority), 211)
						, ""));
				adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
						null,
						getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Up), 236)
						, ""));
				BackgroundFlag = true;
				if(CheckTM == false)
				{
					adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Boom_Down), 237)
							, ""));
					adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_In), 238)
							, ""));
					adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
							null,
							getString(ParentActivity.getResources().getString(string.Soft_End_Stop), 214) + "/" + getString(ParentActivity.getResources().getString(string.Bucket_Dump), 239)
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
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Brightness_Setting), 412)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Display_Style), 416)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Unit_Setting), 413)
					, ""));
			/*adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Volume), 468)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Temp), 39)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Distance), 471)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Weight), 41)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Pressure), 42)
					, ""));*/
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Language), 422)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Operation_History), 254)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Fuel_Consumption_History), 313)
					, ""));		
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Boom_Detent_Mode), 149)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Bucket_Detent_Mode), 150)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null, 
					getString(ParentActivity.getResources().getString(string.Cooling_Fan_Reverse_Mode), 217)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null, 
					getString(ParentActivity.getResources().getString(string.Camera_Setting), 216)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null, 
					getString(ParentActivity.getResources().getString(string.Clock_Setting), 413)
					, ""));	
		}else{
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Brightness_Setting), 412)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Display_Style), 416)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Unit_Setting), 414)
					, ""));
			/*adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Volume), 468)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Temp), 39)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Distance), 471)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Weight), 41)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Pressure), 42)
					, ""));*/
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Machine_Monitoring), 253)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Language), 422)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Operation_History), 254)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Fuel_Consumption_History), 313)
					, ""));		
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null,
					getString(ParentActivity.getResources().getString(string.Boom_Detent_Mode), 149)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null,
					getString(ParentActivity.getResources().getString(string.Bucket_Detent_Mode), 150)
					, ""));	
			adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null, 
					getString(ParentActivity.getResources().getString(string.Cooling_Fan_Reverse_Mode), 217)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
					null, 
					getString(ParentActivity.getResources().getString(string.Camera_Setting), 216)
					, ""));
			adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
					null, 
					getString(ParentActivity.getResources().getString(string.Clock_Setting), 413)
					, ""));
		}
		if(CAN1Comm.Get_CheckBKCUComm() == 1)
		{
			CheckBKCU = true;
			if(BackgroundFlag == false){
				adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),
						null, 
						getString(ParentActivity.getResources().getString(string.Engine_Auto_Shutdown), 215)
						, ""));
			}else {
				adapter.addItem(new IconTextItemUserSwitchingLock(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),
						null, 
						getString(ParentActivity.getResources().getString(string.Engine_Auto_Shutdown), 215)
						, ""));
			}
		}else {
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
				//Home.STATE_UNIT_FUEL				        = nIndex++;
				//Home.STATE_UNIT_TEMP				        = nIndex++;
				//Home.STATE_UNIT_ODO					    = nIndex++;
				//Home.STATE_UNIT_WEIGHT				    = nIndex++;
				//Home.STATE_UNIT_PRESSURE			      	= nIndex++;
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
				//Home.STATE_UNIT_FUEL				        = nIndex++;
				//Home.STATE_UNIT_TEMP				        = nIndex++;
				//Home.STATE_UNIT_ODO					    = nIndex++;
				//Home.STATE_UNIT_WEIGHT				    = nIndex++;
				//Home.STATE_UNIT_PRESSURE			      	= nIndex++;
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
			//Home.STATE_UNIT_FUEL				        = nIndex++;
			//Home.STATE_UNIT_TEMP				        = nIndex++;
			//Home.STATE_UNIT_ODO					    = nIndex++;
			//Home.STATE_UNIT_WEIGHT				    = nIndex++;
			//Home.STATE_UNIT_PRESSURE			      	= nIndex++;
			Home.STATE_MACHINESTATUS_UPPER		  	= nIndex++;;
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
				//Home.STATE_UNIT_FUEL				        = nIndex++;
				//Home.STATE_UNIT_TEMP				        = nIndex++;
				//Home.STATE_UNIT_ODO					    = nIndex++;
				//Home.STATE_UNIT_WEIGHT				    = nIndex++;
				//Home.STATE_UNIT_PRESSURE			      	= nIndex++;
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
				//Home.STATE_UNIT_FUEL				        = nIndex++;
				//Home.STATE_UNIT_TEMP				        = nIndex++;
				//Home.STATE_UNIT_ODO					    = nIndex++;
				//Home.STATE_UNIT_WEIGHT				    = nIndex++;
				//Home.STATE_UNIT_PRESSURE			      	= nIndex++;
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
			//Home.STATE_UNIT_FUEL				        = nIndex++;
			//Home.STATE_UNIT_TEMP				        = nIndex++;
			//Home.STATE_UNIT_ODO					    = nIndex++;
			//Home.STATE_UNIT_WEIGHT				    = nIndex++;
			//Home.STATE_UNIT_PRESSURE			      	= nIndex++;
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
				ClickOK();
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				
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
		//UnitFuel = ParentActivity.UnitFuel;
		//UnitTemp = ParentActivity.UnitTemp;
		//UnitOdo = ParentActivity.UnitOdo;
		//UnitWeight = ParentActivity.UnitWeight;
		//UnitPressure = ParentActivity.UnitPressure;
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
		
				
		LOCK_STATE_ENGINEMODE	= adapter.isChecked(Home.STATE_ENGINEMODE);
		LOCK_STATE_CCOMODE 	= adapter.isChecked(Home.STATE_CCOMODE);
		LOCK_STATE_SHIFTMODE 	= adapter.isChecked(Home.STATE_SHIFTMODE);
		if(CheckTCLockUp && CheckEHCU){
			LOCK_STATE_TCLOCKUP	= adapter.isChecked(Home.STATE_TCLOCKUP);
			LOCK_STATE_RIDECONTROL 	= adapter.isChecked(Home.STATE_RIDECONTROL);
			LOCK_STATE_WEIGHINGSYSTEM 	= adapter.isChecked(Home.STATE_WEIGHINGSYSTEM);
			LOCK_STATE_WEIGHINGDISPLAY 	= adapter.isChecked(Home.STATE_WEIGHINGDISPLAY);
			LOCK_STATE_ERRORDETECTION 	= adapter.isChecked(Home.STATE_ERRORDETECTION);
			LOCK_STATE_KICKDOWN 	= adapter.isChecked(Home.STATE_KICKDOWN);
			LOCK_STATE_BUCKETPRIORITY 	= adapter.isChecked(Home.STATE_BUCKETPRIORITY);
			LOCK_STATE_SOFTENDSTOP_BOOMUP 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BOOMUP);
			if(CheckTM == false){
				LOCK_STATE_SOFTENDSTOP_BOOMDOWN 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BOOMDOWN);
				LOCK_STATE_SOFTENDSTOP_BUCKETIN 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BUCKETIN);
				LOCK_STATE_SOFTENDSTOP_BUCKETDUMP 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BUCKETDUMP);
				LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= adapter.isChecked(Home.STATE_BRIGHTNESS_MANUALAUTO);
				LOCK_STATE_DISPLAYTYPE 	= adapter.isChecked(Home.STATE_DISPLAYTYPE);
				LOCK_STATE_UNIT_TYPE 	= adapter.isChecked(Home.STATE_UNIT_TYPE);
				//LOCK_STATE_UNIT_FUEL 	= adapter.isChecked(Home.STATE_UNIT_FUEL);
				//LOCK_STATE_UNIT_TEMP 	= adapter.isChecked(Home.STATE_UNIT_TEMP);
				//LOCK_STATE_UNIT_ODO 	= adapter.isChecked(Home.STATE_UNIT_ODO);
				//LOCK_STATE_UNIT_WEIGHT 	= adapter.isChecked(Home.STATE_UNIT_WEIGHT);
				//LOCK_STATE_UNIT_PRESSURE 	= adapter.isChecked(Home.STATE_UNIT_PRESSURE);
				LOCK_STATE_MACHINESTATUS_UPPER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_UPPER);
				LOCK_STATE_MACHINESTATUS_LOWER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_LOWER);
				LOCK_STATE_LANGUAGE 	= adapter.isChecked(Home.STATE_LANGUAGE);
				//LOCK_STATE_SOUNDOUTPUT 	= adapter.isChecked(Home.STATE_SOUNDOUTPUT);
				LOCK_STATE_OPERATION_HISTORY 	= adapter.isChecked(Home.STATE_OPERATION_HISTORY);
				LOCK_STATE_FUEL_INFO 	= adapter.isChecked(Home.STATE_FUEL_INFO);
				LOCK_STATE_BOOM_DETENT_MODE 	= adapter.isChecked(Home.STATE_BOOM_DETENT_MODE);
				LOCK_STATE_BUCKET_DETENT_MODE 	= adapter.isChecked(Home.STATE_BUCKET_DETENT_MODE);
				LOCK_STATE_REVERSE_FAN_MODE 	= adapter.isChecked(Home.STATE_REVERSE_FAN_MODE);
				LOCK_STATE_REVERSE_CAMERA 	= adapter.isChecked(Home.STATE_REVERSE_CAMERA);
				LOCK_STATE_SET_CLOCK 	= adapter.isChecked(Home.STATE_SET_CLOCK);
			} else {
				LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= adapter.isChecked(Home.STATE_BRIGHTNESS_MANUALAUTO);
				LOCK_STATE_DISPLAYTYPE 	= adapter.isChecked(Home.STATE_DISPLAYTYPE);
				LOCK_STATE_UNIT_TYPE 	= adapter.isChecked(Home.STATE_UNIT_TYPE);
				//LOCK_STATE_UNIT_FUEL 	= adapter.isChecked(Home.STATE_UNIT_FUEL);
				//LOCK_STATE_UNIT_TEMP 	= adapter.isChecked(Home.STATE_UNIT_TEMP);
				//LOCK_STATE_UNIT_ODO 	= adapter.isChecked(Home.STATE_UNIT_ODO);
				//LOCK_STATE_UNIT_WEIGHT 	= adapter.isChecked(Home.STATE_UNIT_WEIGHT);
				//LOCK_STATE_UNIT_PRESSURE 	= adapter.isChecked(Home.STATE_UNIT_PRESSURE);
				LOCK_STATE_MACHINESTATUS_UPPER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_UPPER);
				LOCK_STATE_MACHINESTATUS_LOWER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_LOWER);
				LOCK_STATE_LANGUAGE 	= adapter.isChecked(Home.STATE_LANGUAGE);
				//LOCK_STATE_SOUNDOUTPUT 	= adapter.isChecked(Home.STATE_SOUNDOUTPUT);
				LOCK_STATE_OPERATION_HISTORY 	= adapter.isChecked(Home.STATE_OPERATION_HISTORY);
				LOCK_STATE_FUEL_INFO 	= adapter.isChecked(Home.STATE_FUEL_INFO);
				LOCK_STATE_BOOM_DETENT_MODE 	= adapter.isChecked(Home.STATE_BOOM_DETENT_MODE);
				LOCK_STATE_BUCKET_DETENT_MODE 	= adapter.isChecked(Home.STATE_BUCKET_DETENT_MODE);
				LOCK_STATE_REVERSE_FAN_MODE 	= adapter.isChecked(Home.STATE_REVERSE_FAN_MODE);
				LOCK_STATE_REVERSE_CAMERA 	= adapter.isChecked(Home.STATE_REVERSE_CAMERA);
				LOCK_STATE_SET_CLOCK 	= adapter.isChecked(Home.STATE_SET_CLOCK);				
			}
		} else if(CheckTCLockUp) {
			LOCK_STATE_TCLOCKUP	= adapter.isChecked(Home.STATE_TCLOCKUP);
			LOCK_STATE_RIDECONTROL 	= adapter.isChecked(Home.STATE_RIDECONTROL);
			LOCK_STATE_WEIGHINGSYSTEM 	= adapter.isChecked(Home.STATE_WEIGHINGSYSTEM);
			LOCK_STATE_WEIGHINGDISPLAY 	= adapter.isChecked(Home.STATE_WEIGHINGDISPLAY);
			LOCK_STATE_ERRORDETECTION 	= adapter.isChecked(Home.STATE_ERRORDETECTION);
			LOCK_STATE_KICKDOWN 	= adapter.isChecked(Home.STATE_KICKDOWN);
			LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= adapter.isChecked(Home.STATE_BRIGHTNESS_MANUALAUTO);
			LOCK_STATE_DISPLAYTYPE 	= adapter.isChecked(Home.STATE_DISPLAYTYPE);
			LOCK_STATE_UNIT_TYPE 	= adapter.isChecked(Home.STATE_UNIT_TYPE);
			//LOCK_STATE_UNIT_FUEL 	= adapter.isChecked(Home.STATE_UNIT_FUEL);
			//LOCK_STATE_UNIT_TEMP 	= adapter.isChecked(Home.STATE_UNIT_TEMP);
			//LOCK_STATE_UNIT_ODO 	= adapter.isChecked(Home.STATE_UNIT_ODO);
			//LOCK_STATE_UNIT_WEIGHT 	= adapter.isChecked(Home.STATE_UNIT_WEIGHT);
			//LOCK_STATE_UNIT_PRESSURE 	= adapter.isChecked(Home.STATE_UNIT_PRESSURE);
			LOCK_STATE_MACHINESTATUS_UPPER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_UPPER);
			LOCK_STATE_MACHINESTATUS_LOWER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_LOWER);
			LOCK_STATE_LANGUAGE 	= adapter.isChecked(Home.STATE_LANGUAGE);
			//LOCK_STATE_SOUNDOUTPUT 	= adapter.isChecked(Home.STATE_SOUNDOUTPUT);
			LOCK_STATE_OPERATION_HISTORY 	= adapter.isChecked(Home.STATE_OPERATION_HISTORY);
			LOCK_STATE_FUEL_INFO 	= adapter.isChecked(Home.STATE_FUEL_INFO);
			LOCK_STATE_BOOM_DETENT_MODE 	= adapter.isChecked(Home.STATE_BOOM_DETENT_MODE);
			LOCK_STATE_BUCKET_DETENT_MODE 	= adapter.isChecked(Home.STATE_BUCKET_DETENT_MODE);
			LOCK_STATE_REVERSE_FAN_MODE 	= adapter.isChecked(Home.STATE_REVERSE_FAN_MODE);
			LOCK_STATE_REVERSE_CAMERA 	= adapter.isChecked(Home.STATE_REVERSE_CAMERA);
			LOCK_STATE_SET_CLOCK 	= adapter.isChecked(Home.STATE_SET_CLOCK);	
		} else if(CheckEHCU) {
			LOCK_STATE_RIDECONTROL 	= adapter.isChecked(Home.STATE_RIDECONTROL);
			LOCK_STATE_WEIGHINGSYSTEM 	= adapter.isChecked(Home.STATE_WEIGHINGSYSTEM);
			LOCK_STATE_WEIGHINGDISPLAY 	= adapter.isChecked(Home.STATE_WEIGHINGDISPLAY);
			LOCK_STATE_ERRORDETECTION 	= adapter.isChecked(Home.STATE_ERRORDETECTION);
			LOCK_STATE_KICKDOWN 	= adapter.isChecked(Home.STATE_KICKDOWN);
			LOCK_STATE_BUCKETPRIORITY 	= adapter.isChecked(Home.STATE_BUCKETPRIORITY);
			LOCK_STATE_SOFTENDSTOP_BOOMUP 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BOOMUP);
			if(CheckTM == false){
				LOCK_STATE_SOFTENDSTOP_BOOMDOWN 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BOOMDOWN);
				LOCK_STATE_SOFTENDSTOP_BUCKETIN 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BUCKETIN);
				LOCK_STATE_SOFTENDSTOP_BUCKETDUMP 	= adapter.isChecked(Home.STATE_SOFTENDSTOP_BUCKETDUMP);
				LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= adapter.isChecked(Home.STATE_BRIGHTNESS_MANUALAUTO);
				LOCK_STATE_DISPLAYTYPE 	= adapter.isChecked(Home.STATE_DISPLAYTYPE);
				LOCK_STATE_UNIT_TYPE 	= adapter.isChecked(Home.STATE_UNIT_TYPE);
				//LOCK_STATE_UNIT_FUEL 	= adapter.isChecked(Home.STATE_UNIT_FUEL);
				//LOCK_STATE_UNIT_TEMP 	= adapter.isChecked(Home.STATE_UNIT_TEMP);
				//LOCK_STATE_UNIT_ODO 	= adapter.isChecked(Home.STATE_UNIT_ODO);
				//LOCK_STATE_UNIT_WEIGHT 	= adapter.isChecked(Home.STATE_UNIT_WEIGHT);
				//LOCK_STATE_UNIT_PRESSURE 	= adapter.isChecked(Home.STATE_UNIT_PRESSURE);
				LOCK_STATE_MACHINESTATUS_UPPER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_UPPER);
				LOCK_STATE_MACHINESTATUS_LOWER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_LOWER);
				LOCK_STATE_LANGUAGE 	= adapter.isChecked(Home.STATE_LANGUAGE);
				//LOCK_STATE_SOUNDOUTPUT 	= adapter.isChecked(Home.STATE_SOUNDOUTPUT);
				LOCK_STATE_OPERATION_HISTORY 	= adapter.isChecked(Home.STATE_OPERATION_HISTORY);
				LOCK_STATE_FUEL_INFO 	= adapter.isChecked(Home.STATE_FUEL_INFO);
				LOCK_STATE_BOOM_DETENT_MODE 	= adapter.isChecked(Home.STATE_BOOM_DETENT_MODE);
				LOCK_STATE_BUCKET_DETENT_MODE 	= adapter.isChecked(Home.STATE_BUCKET_DETENT_MODE);
				LOCK_STATE_REVERSE_FAN_MODE 	= adapter.isChecked(Home.STATE_REVERSE_FAN_MODE);
				LOCK_STATE_REVERSE_CAMERA 	= adapter.isChecked(Home.STATE_REVERSE_CAMERA);
				LOCK_STATE_SET_CLOCK 	= adapter.isChecked(Home.STATE_SET_CLOCK);
			} else {
				LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= adapter.isChecked(Home.STATE_BRIGHTNESS_MANUALAUTO);
				LOCK_STATE_DISPLAYTYPE 	= adapter.isChecked(Home.STATE_DISPLAYTYPE);
				LOCK_STATE_UNIT_TYPE 	= adapter.isChecked(Home.STATE_UNIT_TYPE);
				//LOCK_STATE_UNIT_FUEL 	= adapter.isChecked(Home.STATE_UNIT_FUEL);
				//LOCK_STATE_UNIT_TEMP 	= adapter.isChecked(Home.STATE_UNIT_TEMP);
				//LOCK_STATE_UNIT_ODO 	= adapter.isChecked(Home.STATE_UNIT_ODO);
				//LOCK_STATE_UNIT_WEIGHT 	= adapter.isChecked(Home.STATE_UNIT_WEIGHT);
				//LOCK_STATE_UNIT_PRESSURE 	= adapter.isChecked(Home.STATE_UNIT_PRESSURE);
				LOCK_STATE_MACHINESTATUS_UPPER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_UPPER);
				LOCK_STATE_MACHINESTATUS_LOWER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_LOWER);
				LOCK_STATE_LANGUAGE 	= adapter.isChecked(Home.STATE_LANGUAGE);
				//LOCK_STATE_SOUNDOUTPUT 	= adapter.isChecked(Home.STATE_SOUNDOUTPUT);
				LOCK_STATE_OPERATION_HISTORY 	= adapter.isChecked(Home.STATE_OPERATION_HISTORY);
				LOCK_STATE_FUEL_INFO 	= adapter.isChecked(Home.STATE_FUEL_INFO);
				LOCK_STATE_BOOM_DETENT_MODE 	= adapter.isChecked(Home.STATE_BOOM_DETENT_MODE);
				LOCK_STATE_BUCKET_DETENT_MODE 	= adapter.isChecked(Home.STATE_BUCKET_DETENT_MODE);
				LOCK_STATE_REVERSE_FAN_MODE 	= adapter.isChecked(Home.STATE_REVERSE_FAN_MODE);
				LOCK_STATE_REVERSE_CAMERA 	= adapter.isChecked(Home.STATE_REVERSE_CAMERA);
				LOCK_STATE_SET_CLOCK 	= adapter.isChecked(Home.STATE_SET_CLOCK);	
			}
		} else {
			LOCK_STATE_RIDECONTROL 	= adapter.isChecked(Home.STATE_RIDECONTROL);
			LOCK_STATE_WEIGHINGSYSTEM 	= adapter.isChecked(Home.STATE_WEIGHINGSYSTEM);
			LOCK_STATE_WEIGHINGDISPLAY 	= adapter.isChecked(Home.STATE_WEIGHINGDISPLAY);
			LOCK_STATE_ERRORDETECTION 	= adapter.isChecked(Home.STATE_ERRORDETECTION);
			LOCK_STATE_KICKDOWN 	= adapter.isChecked(Home.STATE_KICKDOWN);
			LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= adapter.isChecked(Home.STATE_BRIGHTNESS_MANUALAUTO);
			LOCK_STATE_DISPLAYTYPE 	= adapter.isChecked(Home.STATE_DISPLAYTYPE);
			LOCK_STATE_UNIT_TYPE 	= adapter.isChecked(Home.STATE_UNIT_TYPE);
			//LOCK_STATE_UNIT_FUEL 	= adapter.isChecked(Home.STATE_UNIT_FUEL);
			//LOCK_STATE_UNIT_TEMP 	= adapter.isChecked(Home.STATE_UNIT_TEMP);
			//LOCK_STATE_UNIT_ODO 	= adapter.isChecked(Home.STATE_UNIT_ODO);
			//LOCK_STATE_UNIT_WEIGHT 	= adapter.isChecked(Home.STATE_UNIT_WEIGHT);
			//LOCK_STATE_UNIT_PRESSURE 	= adapter.isChecked(Home.STATE_UNIT_PRESSURE);
			LOCK_STATE_MACHINESTATUS_UPPER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_UPPER);
			LOCK_STATE_MACHINESTATUS_LOWER 	= adapter.isChecked(Home.STATE_MACHINESTATUS_LOWER);
			LOCK_STATE_LANGUAGE 	= adapter.isChecked(Home.STATE_LANGUAGE);
			//LOCK_STATE_SOUNDOUTPUT 	= adapter.isChecked(Home.STATE_SOUNDOUTPUT);
			LOCK_STATE_OPERATION_HISTORY 	= adapter.isChecked(Home.STATE_OPERATION_HISTORY);
			LOCK_STATE_FUEL_INFO 	= adapter.isChecked(Home.STATE_FUEL_INFO);
			LOCK_STATE_BOOM_DETENT_MODE 	= adapter.isChecked(Home.STATE_BOOM_DETENT_MODE);
			LOCK_STATE_BUCKET_DETENT_MODE 	= adapter.isChecked(Home.STATE_BUCKET_DETENT_MODE);
			LOCK_STATE_REVERSE_FAN_MODE 	= adapter.isChecked(Home.STATE_REVERSE_FAN_MODE);
			LOCK_STATE_REVERSE_CAMERA 	= adapter.isChecked(Home.STATE_REVERSE_CAMERA);
			LOCK_STATE_SET_CLOCK 	= adapter.isChecked(Home.STATE_SET_CLOCK);	
		}
		if(CheckBKCU == true){
			LOCK_STATE_ENGINE_AUTO_SHUTDOWN 	= adapter.isChecked(Home.STATE_ENGINE_AUTO_SHUTDOWN);
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
	}
	/////////////////////////////////////////////////////////////////////
	
	public void UserSwitchingListUpdate(){
		EngineModeDisplay(EngineMode);
		CCOModeDisplay(CCOMode);
		ShiftModeDisplay(ShiftMode);
		if(CheckTCLockUp)
			TCLockUpDisplay(TCLockUp);
		RideControlDisplay(RideControl);
		WeighingSystemDisplay(WeighingSystem);
		WeighingDisplayDisplay(WeighingDisplay);
		ErrorDetectionDisplay(ErrorDetection);
		KickDownDisplay(KickDown);
		if(CheckEHCU)
		{
			BucketPriorityDisplay(BucketPriority);
			SoftEndStopBoomUpDisplay(SoftEndStopBoomUp);
			if(CheckTM == false)
			{
				SoftEndStopBoomDownDisplay(SoftEndStopBoomDown);
				SoftEndStopBucketInDisplay(SoftEndStopBucketIn);
				SoftEndStopBucketDumpDisplay(SoftEndStopBucketDump);
			}
		}
		BrightnessDisplay(BrightnessManualAuto);
		UnitTypeDisplay(UnitType);
		//UnitFuelDisplay(UnitFuel);
		//UnitTempDisplay(UnitTemp);
		//UnitOdoDisplay(UnitOdo);
		//UnitWeightDisplay(UnitWeight);
		//UnitPressureDisplay(UnitPressure);
		DisplayTypeDisplay(DisplayType);
		MachineStatusUpperDisplay(MachineStatusUpper);
		MachineStatusLowerDisplay(MachineStatusLower);
		LanguageDisplay(Language);
		//SoundOutputDisplay(SoundOutput);
		HourmeterDisplay(HourmeterDisplay);
		FuelDisplay(FuelDisplay);
		BoomDetentModeDisplay(BoomDetentMode);
		BucketDetentModeDisplay(BucketDetentMode);
		ReverseFanModeDisplay(ReverseFanMode);
		ReverseCameraModeDisplay(ReverseCamera);
		SetClockDisplay(SetClock);
		if(CheckBKCU){
			EngineAutoShutdownDisplay(EngineAutoShutdownStatus);
		}
	}
	public void EngineModeDisplay(int SettingData){
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
		
		
		if(Home.LOCK_STATE_ENGINEMODE == true){
			adapter.UpdateCheckBox(Home.STATE_ENGINEMODE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_ENGINEMODE, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void CCOModeDisplay(int SettingData){
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
		}
		if(Home.LOCK_STATE_CCOMODE == true){
			adapter.UpdateCheckBox(Home.STATE_CCOMODE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_CCOMODE, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void ShiftModeDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_SHIFTMODE== true){
			adapter.UpdateCheckBox(Home.STATE_SHIFTMODE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SHIFTMODE, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void TCLockUpDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_TCLOCKUP == true){
			adapter.UpdateCheckBox(Home.STATE_TCLOCKUP, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_TCLOCKUP, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void RideControlDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_RIDECONTROL == true){
			adapter.UpdateCheckBox(Home.STATE_RIDECONTROL, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_RIDECONTROL, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void WeighingSystemDisplay(int SettingData){
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
		if(Home.LOCK_STATE_WEIGHINGSYSTEM == true){
			adapter.UpdateCheckBox(Home.STATE_WEIGHINGSYSTEM, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_WEIGHINGSYSTEM, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void WeighingDisplayDisplay(int SettingData){
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
		if(Home.LOCK_STATE_WEIGHINGDISPLAY == true){
			adapter.UpdateCheckBox(Home.STATE_WEIGHINGDISPLAY, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_WEIGHINGDISPLAY, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void ErrorDetectionDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_ERRORDETECTION == true){
			adapter.UpdateCheckBox(Home.STATE_ERRORDETECTION, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_ERRORDETECTION, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void KickDownDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_KICKDOWN == true){
			adapter.UpdateCheckBox(Home.STATE_KICKDOWN, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_KICKDOWN, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void BucketPriorityDisplay(int SettingData){
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
		if(Home.LOCK_STATE_BUCKETPRIORITY == true){
			adapter.UpdateCheckBox(Home.STATE_BUCKETPRIORITY, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_BUCKETPRIORITY, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void SoftEndStopBoomUpDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_SOFTENDSTOP_BOOMUP == true){
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BOOMUP, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BOOMUP, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void SoftEndStopBoomDownDisplay(int SettingData){
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

		
		if(Home.LOCK_STATE_SOFTENDSTOP_BOOMDOWN == true){
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BOOMDOWN, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BOOMDOWN, false);
		}
		adapter.notifyDataSetChanged();
	}	
	
	public void SoftEndStopBucketInDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_SOFTENDSTOP_BUCKETIN == true){
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BUCKETIN, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BUCKETIN, false);
		}
		adapter.notifyDataSetChanged();
	}	
	
	public void SoftEndStopBucketDumpDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_SOFTENDSTOP_BUCKETDUMP == true){
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BUCKETDUMP, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SOFTENDSTOP_BUCKETDUMP, false);
		}
		adapter.notifyDataSetChanged();
	}	
	
	public void BrightnessDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_BRIGHTNESS_MANUALAUTO == true){
			adapter.UpdateCheckBox(Home.STATE_BRIGHTNESS_MANUALAUTO, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_BRIGHTNESS_MANUALAUTO, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitTypeDisplay(int SettingData){
		switch (SettingData) {
		case Home.UNIT_TYPE_CUSTOM:
			adapter.UpdateSecond(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.CUSTOM), 463));
			break;
		case Home.UNIT_TYPE_US:
			adapter.UpdateSecond(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.USTon), 462));
			break;
		case Home.UNIT_TYPE_METRIC:
			adapter.UpdateSecond(Home.STATE_UNIT_TYPE, getString(ParentActivity.getResources().getString(string.METRIC), 461));
			break;
		default:
			break;
		}
		if(Home.LOCK_STATE_UNIT_TYPE == true){
			adapter.UpdateCheckBox(Home.STATE_UNIT_TYPE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_UNIT_TYPE, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitFuelDisplay(int SettingData){
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
		if(Home.LOCK_STATE_UNIT_FUEL == true){
			adapter.UpdateCheckBox(Home.STATE_UNIT_FUEL, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_UNIT_FUEL, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitTempDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_UNIT_TEMP == true){
			adapter.UpdateCheckBox(Home.STATE_UNIT_TEMP, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_UNIT_TEMP, false);
		}
		adapter.notifyDataSetChanged();
	}
	
	public void UnitOdoDisplay(int SettingData){
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
		if(Home.LOCK_STATE_UNIT_ODO == true){
			adapter.UpdateCheckBox(Home.STATE_UNIT_ODO, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_UNIT_ODO, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void UnitWeightDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_UNIT_WEIGHT == true){
			adapter.UpdateCheckBox(Home.STATE_UNIT_WEIGHT, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_UNIT_WEIGHT, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void UnitPressureDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_UNIT_PRESSURE == true){
			adapter.UpdateCheckBox(Home.STATE_UNIT_PRESSURE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_UNIT_PRESSURE, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void DisplayTypeDisplay(int SettingData){
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
		if(Home.LOCK_STATE_DISPLAYTYPE == true){
			adapter.UpdateCheckBox(Home.STATE_DISPLAYTYPE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_DISPLAYTYPE, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void MachineStatusUpperDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_MACHINESTATUS_UPPER == true){
			adapter.UpdateCheckBox(Home.STATE_MACHINESTATUS_UPPER, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_MACHINESTATUS_UPPER, false);
		}
		adapter.notifyDataSetChanged();
	}	
	public void MachineStatusLowerDisplay(int SettingData){
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
		
		
		if(Home.LOCK_STATE_MACHINESTATUS_LOWER == true){
			adapter.UpdateCheckBox(Home.STATE_MACHINESTATUS_LOWER, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_MACHINESTATUS_LOWER, false);
		}
		adapter.notifyDataSetChanged();
	}
	public void LanguageDisplay(int SettingData){
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
			case Home.STATE_DISPLAY_LANGUAGE_FINNISH:
				adapter.UpdateSecond(Home.STATE_LANGUAGE, getString(ParentActivity.getResources().getString(string.Finnish), 444));
				break;
			// --, 150225 bwk
		}
		
		if(Home.LOCK_STATE_LANGUAGE == true){
			adapter.UpdateCheckBox(Home.STATE_LANGUAGE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_LANGUAGE, false);
		}
		adapter.notifyDataSetChanged();
		// --, 150213 bwk
	}
	/*
	public void SoundOutputDisplay(int SettingData){
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
		if(Home.LOCK_STATE_SOUNDOUTPUT == true){
			adapter.UpdateCheckBox(Home.STATE_SOUNDOUTPUT, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SOUNDOUTPUT, false);
		}
		adapter.notifyDataSetChanged();
	}*/
	public void HourmeterDisplay(int SettingData){
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
		
		if(Home.LOCK_STATE_OPERATION_HISTORY == true){
			adapter.UpdateCheckBox(Home.STATE_OPERATION_HISTORY, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_OPERATION_HISTORY, false);
		}
		adapter.notifyDataSetChanged();
	}
	
	public void FuelDisplay(int SettingData){
		
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
		
		if(Home.LOCK_STATE_FUEL_INFO == true){
			adapter.UpdateCheckBox(Home.STATE_FUEL_INFO, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_FUEL_INFO, false);
		}
		adapter.notifyDataSetChanged();		
	}
	
	void BoomDetentModeDisplay(int SettingData){
		
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
		if(Home.LOCK_STATE_BOOM_DETENT_MODE == true){
			adapter.UpdateCheckBox(Home.STATE_BOOM_DETENT_MODE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_BOOM_DETENT_MODE, false);
		}
		adapter.notifyDataSetChanged();		
	}
	
	void BucketDetentModeDisplay(int SettingData){
		
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
		if(Home.LOCK_STATE_BUCKET_DETENT_MODE == true){
			adapter.UpdateCheckBox(Home.STATE_BUCKET_DETENT_MODE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_BUCKET_DETENT_MODE, false);
		}
		adapter.notifyDataSetChanged();		
	}
	void ReverseFanModeDisplay(int SettingData){
		
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
		if(Home.LOCK_STATE_REVERSE_FAN_MODE == true){
			adapter.UpdateCheckBox(Home.STATE_REVERSE_FAN_MODE, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_REVERSE_FAN_MODE, false);
		}
		adapter.notifyDataSetChanged();		
	}
	void ReverseCameraModeDisplay(int SettingData){
		if(Home.LOCK_STATE_REVERSE_CAMERA == true){
			adapter.UpdateCheckBox(Home.STATE_REVERSE_CAMERA, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_REVERSE_CAMERA, false);
		}
		adapter.notifyDataSetChanged();		
	}
	
	void SetClockDisplay(int SettingData){
		if(Home.LOCK_STATE_SET_CLOCK == true){
			adapter.UpdateCheckBox(Home.STATE_SET_CLOCK, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_SET_CLOCK, false);
		}
		adapter.notifyDataSetChanged();		
	}
	void EngineAutoShutdownDisplay(int SettingData){
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
		if(Home.LOCK_STATE_ENGINE_AUTO_SHUTDOWN == true){
			adapter.UpdateCheckBox(Home.STATE_ENGINE_AUTO_SHUTDOWN, true);
		}else {
			adapter.UpdateCheckBox(Home.STATE_ENGINE_AUTO_SHUTDOWN, false);
		}
		adapter.notifyDataSetChanged();		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_USERSWITCHING_LOCKING;
		
		ParentActivity.LockUserSwitching = Home.STATE_USERSWITCHING_LOCK;
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity._MenuBaseFragment.showUserSwitching();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_USERSWITCHING_TOP);
		
		Home.LOCK_STATE_ENGINEMODE 	= LOCK_STATE_ENGINEMODE;
		Home.LOCK_STATE_CCOMODE 	= LOCK_STATE_CCOMODE;
		Home.LOCK_STATE_SHIFTMODE 	= LOCK_STATE_SHIFTMODE;
		Home.LOCK_STATE_TCLOCKUP 	= LOCK_STATE_TCLOCKUP;
		Home.LOCK_STATE_RIDECONTROL = LOCK_STATE_RIDECONTROL;
		Home.LOCK_STATE_WEIGHINGSYSTEM 	= LOCK_STATE_WEIGHINGSYSTEM;
		Home.LOCK_STATE_WEIGHINGDISPLAY 	= LOCK_STATE_WEIGHINGDISPLAY;
		Home.LOCK_STATE_ERRORDETECTION 	= LOCK_STATE_ERRORDETECTION;
		Home.LOCK_STATE_KICKDOWN 	= LOCK_STATE_KICKDOWN;
		Home.LOCK_STATE_BUCKETPRIORITY 	= LOCK_STATE_BUCKETPRIORITY;
		Home.LOCK_STATE_SOFTENDSTOP_BOOMUP 	= LOCK_STATE_SOFTENDSTOP_BOOMUP;
		Home.LOCK_STATE_SOFTENDSTOP_BOOMDOWN 	= LOCK_STATE_SOFTENDSTOP_BOOMDOWN;
		Home.LOCK_STATE_SOFTENDSTOP_BUCKETIN 	= LOCK_STATE_SOFTENDSTOP_BUCKETIN;
		Home.LOCK_STATE_SOFTENDSTOP_BUCKETDUMP 	= LOCK_STATE_SOFTENDSTOP_BUCKETDUMP;
		Home.LOCK_STATE_BRIGHTNESS_MANUALAUTO 	= LOCK_STATE_BRIGHTNESS_MANUALAUTO;
		Home.LOCK_STATE_DISPLAYTYPE 	= LOCK_STATE_DISPLAYTYPE;
		Home.LOCK_STATE_UNIT_TYPE 	= LOCK_STATE_UNIT_TYPE;
		//Home.LOCK_STATE_UNIT_FUEL 	= LOCK_STATE_UNIT_FUEL;
		//Home.LOCK_STATE_UNIT_TEMP 	= LOCK_STATE_UNIT_TEMP;
		//Home.LOCK_STATE_UNIT_ODO 	= LOCK_STATE_UNIT_ODO;
		//Home.LOCK_STATE_UNIT_WEIGHT 	= LOCK_STATE_UNIT_WEIGHT;
		//Home.LOCK_STATE_UNIT_PRESSURE 	= LOCK_STATE_UNIT_PRESSURE;
		Home.LOCK_STATE_MACHINESTATUS_UPPER 	= LOCK_STATE_MACHINESTATUS_UPPER;
		Home.LOCK_STATE_MACHINESTATUS_LOWER 	= LOCK_STATE_MACHINESTATUS_LOWER;
		Home.LOCK_STATE_LANGUAGE 	= LOCK_STATE_LANGUAGE;
		//Home.LOCK_STATE_SOUNDOUTPUT 	= LOCK_STATE_SOUNDOUTPUT;
		Home.LOCK_STATE_OPERATION_HISTORY 	= LOCK_STATE_OPERATION_HISTORY;
		Home.LOCK_STATE_FUEL_INFO 	= LOCK_STATE_FUEL_INFO;
		Home.LOCK_STATE_BOOM_DETENT_MODE 	= LOCK_STATE_BOOM_DETENT_MODE;
		Home.LOCK_STATE_BUCKET_DETENT_MODE 	= LOCK_STATE_BUCKET_DETENT_MODE;
		Home.LOCK_STATE_REVERSE_FAN_MODE 	= LOCK_STATE_REVERSE_FAN_MODE;
		Home.LOCK_STATE_REVERSE_CAMERA 	= LOCK_STATE_REVERSE_CAMERA;
		Home.LOCK_STATE_SET_CLOCK 	= LOCK_STATE_SET_CLOCK;
		Home.LOCK_STATE_ENGINE_AUTO_SHUTDOWN 	= LOCK_STATE_ENGINE_AUTO_SHUTDOWN;
		
		ParentActivity.showUserSwitchingLockingPopup();
		ParentActivity.SaveLockStatePref();
	}
	public void ClickCancel() {
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showUserSwitching();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_USERSWITCHING_TOP);
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			break;
		case 2:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(false);
			break;
		case 2:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////
}
