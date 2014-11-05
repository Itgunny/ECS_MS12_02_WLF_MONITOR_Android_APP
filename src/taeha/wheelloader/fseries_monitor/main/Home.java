package taeha.wheelloader.fseries_monitor.main;

import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.popup.BucketPriorityPopup;
import taeha.wheelloader.fseries_monitor.popup.CCOModePopup;
import taeha.wheelloader.fseries_monitor.popup.KickDownPopup;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking2;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking2;
import taeha.wheelloader.fseries_monitor.popup.ShiftModePopup;
import taeha.wheelloader.fseries_monitor.popup.TCLockUpPopup;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class Home extends Activity {
	//CONSTANT//////////////////////////////////////////
	// TAG
	private  final String TAG = "Home";
	


	public  static final int SCREEN_STATE_MAIN_B_TOP 											= 0x10000000;
	
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_TOP								= 0x11000000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_MODE						= 0x11100000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_WARMINGUP					= 0x11200000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_END								= 0x11FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_TOP								= 0x12000000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_CCOMODE							= 0x12100000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_SHIFTMODE						= 0x12200000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_TCLOCKUP							= 0x12300000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_END								= 0x12FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_TOP									= 0x13000000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS						= 0x13100000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_END									= 0x13FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_TOP								= 0x14000000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_HOURODOMETER						= 0x14100000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_END								= 0x14FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_QUICK_TOP									= 0x15000000;
	public  static final int SCREEN_STATE_MAIN_B_QUICK_END									= 0x15FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_KEY_TOP									= 0x16000000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_MAINLIGHT								= 0x16100000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLIGHT								= 0x16200000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_AUTOGREASE								= 0x16300000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER							= 0x16400000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING1			= 0x16410000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2			= 0x16420000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING1			= 0x16430000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2			= 0x16440000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_RIDECONTROL							= 0x16500000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED						= 0x16510000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD								= 0x16600000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ACCUMULATION					= 0x16610000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_DISPLAY						= 0x16620000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ERRORDETECT					= 0x16630000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_BEACONLAMP								= 0x16700000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_REARWIPER								= 0x16800000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_MIRRORHEAT								= 0x16900000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_DETENT									= 0x16A00000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_FINEMODULATION							= 0x16B00000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_FN										= 0x16C00000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_END									= 0x16FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_END 										= 0x1FFFFFFF;
	
	public  static final int SCREEN_STATE_MENU_TOP 											= 0x20000000;
	
	public  static final int SCREEN_STATE_MENU_MODE_TOP										= 0x21000000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_TOP							= 0x21100000;
	
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESPEED					= 0x21110000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP						= 0x21120000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE						= 0x21130000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE						= 0x21140000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN						= 0x21150000;
	
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_END							= 0x211FFFFF;
	
	public  static final int SCREEN_STATE_MENU_MODE_HYD_TOP									= 0x21200000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP						= 0x21210000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_END						= 0x2121FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_DETENT								= 0x21220000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY						= 0x21230000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_MAXFLOW								= 0x21240000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP							= 0x21250000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_END									= 0x212FFFFF;
	
	public  static final int SCREEN_STATE_MENU_MODE_ETC_TOP									= 0x21300000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP					= 0x21310000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_END					= 0x2131FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_DELAYSHUTDOWN_TOP					= 0x21320000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_DELAYSHUTDOWN_END					= 0x2132FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_TOP					= 0x21330000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_END					= 0x2133FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP						= 0x21340000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL					= 0x21341000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_AUTO						= 0x21342000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_END						= 0x2134FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_END									= 0x213FFFFF;
	
	
	public  static final int SCREEN_STATE_MENU_MODE_END										= 0x21FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_MONITORING_TOP								= 0x22000000;
	

	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING					= 0x22000000;
	public  static final int SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP				= 0x22100000;
	public  static final int SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_END				= 0x221FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_WEIGHINGINFO_TOP					= 0x22200000;
	public  static final int SCREEN_STATE_MENU_MONITORING_WEIGHINGINFO_END					= 0x222FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP					= 0x22300000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_END					= 0x223FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_TOP						= 0x22400000;
	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_END						= 0x224FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP					= 0x22500000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_END					= 0x225FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_END								= 0x22FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_TOP								= 0x23000000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP				= 0x23100000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW				= 0x23110000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL				= 0x23120000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE			= 0x23130000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY			= 0x23140000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_END				= 0x231FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP					= 0x23200000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_END					= 0x232FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_TOP					= 0x23300000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_TOP				= 0x23310000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_END				= 0x2331FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_TOP			= 0x23320000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_END			= 0x2332FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_BRAKEPEDAL_TOP		= 0x23330000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_BRAKEPEDAL_END		= 0x2333FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_AEB_TOP				= 0x23340000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_AEB_END				= 0x2334FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_END					= 0x233FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SPEEDOMETERFREQ_TOP				= 0x23400000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SPEEDOMETERFREQ_END				= 0x234FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP						= 0x23500000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_END						= 0x235FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP						= 0x23600000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW						= 0x23610000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP		= 0x23620000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_END		= 0x2362FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP			= 0x23630000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_END			= 0x2363FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP	= 0x23640000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_END	= 0x2364FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_END						= 0x236FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_END								= 0x23FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_PREFERENCE_TOP								= 0x24000000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_TOP					= 0x24100000;	
	public  static final int SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP				= 0x24110000;	
	public  static final int SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_END				= 0x2411FFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP				= 0x24120000;	
	public  static final int SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_END				= 0x2412FFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_END					= 0x241FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP							= 0x24200000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_CLOCK_END							= 0x242FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_UNIT_TOP							= 0x24300000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_UNIT_END							= 0x243FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP					= 0x24400000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_END					= 0x244FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_WIRELESS_TOP						= 0x24500000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_WIRELESS_END						= 0x245FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPE_TOP					= 0x24600000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPE_END					= 0x246FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_END								= 0x24FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_MULTIMEDIA_TOP								= 0x25000000;
	public  static final int SCREEN_STATE_MENU_MULTIMEDIA_END								= 0x25FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_END 											= 0x2FFFFFFF;
	
	public  final int UNIT_ODO_KM 			= 0;
	public  final int UNIT_ODO_MILE 			= 1;
	
	public  final int UNIT_TEMP_F 			= 1;
	public  final int UNIT_TEMP_C 			= 0;
	
	public  final int UNIT_WEIGHT_TON 		= 0;
	public  final int UNIT_WEIGHT_LB 			= 1;
	
	public  final int UNIT_PRESSURE_BAR 		= 0;
	public  final int UNIT_PRESSURE_MPA 		= 1;
	public  final int UNIT_PRESSURE_KGF 		= 2;
	public  final int UNIT_PRESSURE_PSI 		= 3;
	
	public  final int CLOCK_AM		 		= 0;
	public  final int CLOCK_PM		 		= 1;
	////////////////////////////////////////////////////
	
	//Resource//////////////////////////////////////////
	FrameLayout framelayoutMain;
	////////////////////////////////////////////////////
	//Valuable//////////////////////////////////////////
	public int ScreenIndex;
	
	// Unit
	public int UnitOdo;
	public int UnitTemp;
	public int UnitWeight;
	
	// HourOdometer Index
	public int HourOdometerIndex;
	
	// MachineStatus Index
	public int MachineStatusUpperIndex;
	public int MachineStatusLowerIndex;
	
	// Weighing Display Index
	
	
	// SeatBelt
	public int SeatBelt;
	
	// Weighing
	public int WeighingDisplayIndex;
	public int WeighingErrorDetect;
	
	// CAN1CommManager
	private CAN1CommManager CAN1Comm = null;	
	
	// Thread
	private Thread threadRead = null;
	
	// Dialog
	Dialog HomeDialog;
	QuickCouplerPopupLocking1 _QuickCouplerPopupLocking1;
	QuickCouplerPopupUnlocking1 _QuickCouplerPopupUnlocking1;
	QuickCouplerPopupLocking2 _QuickCouplerPopupLocking2;
	QuickCouplerPopupUnlocking2 _QuickCouplerPopupUnlocking2;
	CCOModePopup	_CCoModePopup;
	ShiftModePopup _ShiftModePopup;
	KickDownPopup _KickDownPopup;
	TCLockUpPopup _TCLockUpPopup;
	BucketPriorityPopup _BucketPriorityPopup;
	
	// Timer
	private Timer mSeatBeltTimer = null;
	private Timer mAutoGreaseStopTimer = null;
	private Timer mAnimationRunningTimer = null;
	private Timer mMirrorHeatPreHeatTimer = null;
	private Timer mMirrorHeatTimer = null;
	
	
	// Flag
	public boolean AnimationRunningFlag;
	
	// Data
	int PreHeat;
	int RPM;
	
	// MirrorHeat
	boolean MirrorHeatPreHeatFlag;
	int MirrorHeatCount;
	boolean MirrorHeatTimerFlag;
	
	// Handler
	Handler HandleKeyButton;
	////////////////////////////////////////////////////
	
	//Fragment//////////////////////////////////////////
	public  MainBBaseFragment _MainBBaseFragment;
	public  MenuBaseFragment _MenuBaseFragment;
	////////////////////////////////////////////////////
	
	//Animation/////////////////////////////////////////
	public ChangeFragmentAnimation _MainChangeAnimation;
	////////////////////////////////////////////////////

	//Lift Cycle Function///////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		InitResource();
		InitFragment();
		InitPopup();
		InitValuable();
		InitAnimation();
		LoadPref();
		
		HandleKeyButton = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				KeyButtonClick(msg.what);
				Log.d(TAG, "Key : 0x" + Integer.toHexString(msg.what));
			}
		};
		
		StartSeatBeltTimer();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	/////////////////////////////////////////////////////


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StartCommService();
		threadRead = new Thread(new ReadThread(this));
		try {
			CAN1Comm.SetScreenTopFlag(true);
		} catch (RuntimeException e) {
			Log.e(TAG,"CAN1Comm Instance Error");
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG,"onPause");
		try {
			CAN1Comm.SetScreenTopFlag(false);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		
	}
	//Initialization/////////////////////////////////////
	public void InitResource(){
		framelayoutMain = (FrameLayout) findViewById(R.id.FrameLayout_main);
	}
	public void InitValuable(){
		ScreenIndex = SCREEN_STATE_MAIN_B_TOP;
		SeatBelt = CAN1CommManager.DATA_STATE_LAMP_ON;
		AnimationRunningFlag = false;
		MirrorHeatPreHeatFlag = false;
		MirrorHeatCount = 0;
		
		_MainChangeAnimation = new ChangeFragmentAnimation(this, framelayoutMain, R.id.FrameLayout_main, null);
	}
	public void InitFragment(){
		_MainBBaseFragment = new MainBBaseFragment();
		_MenuBaseFragment = new MenuBaseFragment();
	}
	public void InitPopup(){
		HomeDialog = null;
		_QuickCouplerPopupLocking1 = new QuickCouplerPopupLocking1(this);
		_QuickCouplerPopupUnlocking1 = new QuickCouplerPopupUnlocking1(this);
		_QuickCouplerPopupLocking2 = new QuickCouplerPopupLocking2(this);
		_QuickCouplerPopupUnlocking2 = new QuickCouplerPopupUnlocking2(this);
		_CCoModePopup = new CCOModePopup(this);
		_ShiftModePopup = new ShiftModePopup(this);
		_KickDownPopup = new KickDownPopup(this);
		_TCLockUpPopup = new TCLockUpPopup(this);
		_BucketPriorityPopup = new BucketPriorityPopup(this);
		
	}
	public void InitAnimation(){
		
	}
	public void SavePref(){
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("UnitOdo", UnitOdo);
		edit.putInt("UnitTemp", UnitTemp);
		edit.putInt("UnitWeight", UnitWeight);
		edit.putInt("HourOdometerIndex", HourOdometerIndex);
		edit.putInt("MachineStatusUpperIndex", MachineStatusUpperIndex);
		edit.putInt("MachineStatusLowerIndex", MachineStatusLowerIndex);
		edit.putInt("WeighingDisplayIndex", WeighingDisplayIndex);
		edit.putInt("WeighingErrorDetect", WeighingErrorDetect);
		
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	public void LoadPref(){
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		UnitOdo = SharePref.getInt("UnitOdo", UNIT_ODO_KM);
		UnitOdo = SharePref.getInt("UnitTemp", UNIT_TEMP_C);
		UnitOdo = SharePref.getInt("UnitWeight", UNIT_WEIGHT_TON);
		HourOdometerIndex = SharePref.getInt("HourOdometerIndex", CAN1CommManager.DATA_STATE_HOURMETER_LATEST);
		MachineStatusUpperIndex = SharePref.getInt("MachineStatusUpperIndex", CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT);
		MachineStatusLowerIndex = SharePref.getInt("MachineStatusLowerIndex", CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT);
		WeighingDisplayIndex = SharePref.getInt("WeighingDisplayIndex", CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A);
		WeighingErrorDetect = SharePref.getInt("WeighingErrorDetect", CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF);
		
		Log.d(TAG,"LoadPref");
	}
	/////////////////////////////////////////////////////
	
	//Main Screen Fragment///////////////////////////////
	public void showMainBFragment(){
		_MainBBaseFragment = new MainBBaseFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _MainBBaseFragment);
		transaction.commit();
		
	}
	//Menu Screen Fragment
	public void showMenuFragment(){
		_MenuBaseFragment = new MenuBaseFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _MenuBaseFragment);
		transaction.commit();
	}
	
	/////////////////////////////////////////////////////
	//Communication//////////////////////////////////////
	// Communication Service Start
	private void StartCommService() {
		Log.v(TAG,"Start Comm Service");
		Intent intent = new Intent(Home.this,CommService.class);
		// Loacal Service
		startService(intent);
		// Remote Service
		bindService(new Intent(CommService.class.getName()),serConn,Context.BIND_AUTO_CREATE);
	}
	
	// Communication Service Stop
	private void stopCommService(){
		Log.v(TAG,"Stop Comm Service");
		unbindService(serConn);
		if(stopService(new Intent(Home.this,CommService.class))){
			Log.v(TAG,"stopService was successful");
		}
		else{
			Log.v(TAG,"stopService was unsuccessful");
		}
		try {
			CAN1Comm.unregisterCallback(mCallback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadRead.interrupt();		
	}
	
	// Service Connection
	private ServiceConnection serConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.v(TAG,"onServiceDisconnected() called");
			
			StartCommService();
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			boolean Success;
			Log.v(TAG,"onServiceConnected() called");
			CAN1Comm = CAN1CommManager.getInstance();

			try {
				Success = CAN1Comm.registerCallback(mCallback);
				Log.d(TAG,"CallBack Success : " + Boolean.toString(Success));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			threadRead.start();
			CAN1Comm.SetScreenTopFlag(true);
			
			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_STARTCAN);
			
			//showMainBFragment();
			_MainChangeAnimation.StartChangeAnimation(_MainBBaseFragment);
			
		}
	};
	
	
	// Service Callback
	ICAN1CommManagerCallback mCallback = new ICAN1CommManagerCallback.Stub() {
		
		@Override
		public void KeyButtonCallBack(int Data) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG,"KeyButton Callback : 0x" + Integer.toHexString(Data));
			if(CAN1Comm.GetScreenTopFlag() == true){
				try {
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException");
				}
			}
				
		}
		
		@Override
		public void CallbackFunc(int Data) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG,"Callback");
		}

		@Override
		public void CIDCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "CIDCallBack");
			int ComponentCode;
			int ManufacturerCode;
			byte[] ComponentBasicInformation;
			
			ComponentBasicInformation = new byte[37];
			
			ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_MONITOR();
			ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_MONITOR();
			ComponentBasicInformation = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_MONITOR();
			
		//	SaveCID(ComponentCode,ManufacturerCode,ComponentBasicInformation);
		}

		@Override
		public void ASCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "ASCallBack");
		//	SaveASPhoneNumber();
		}

		@Override
		public void StopCommServiceCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "StopCommServiceCallBack");
			stopCommService();
		}

	};
	// Thread Class
	public  class ReadThread implements Runnable {
		private WeakReference<Home> activityRef = null;
		public Message msg = null;
		public ReadThread(Home activity){
			this.activityRef = new WeakReference<Home>(activity);
			msg = new Message();
		}

		
		@Override
		public void run() {
			try{
				while(!activityRef.get().threadRead.currentThread().isInterrupted())
				{
					activityRef.get().GetDataFromNative();
					activityRef.get().UpdateUI();
					Thread.sleep(200);
				}
			}
			catch(InterruptedException ie){
				Log.e(TAG,"InterruptedException");
			}		
			catch(RuntimeException ee){
				Log.e(TAG,"RuntimeException");
			}
		}
	}
	
	public void GetDataFromNative(){
		PreHeat = CAN1Comm.Get_MirrorHeaterStatus_724_PGN65428();
		RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();;
		CheckMirrorHeatPreHeat(PreHeat,RPM);
	}
	public void UpdateUI() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			
			}
		});
	
	}
	
	/////////////////////////////////////////////////////
	
	//Key Button/////////////////////////////////////////
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		//Log.d(TAG,"dispatchKeyEvent");
		if(event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			{
				Log.d(TAG,"KEYCODE_BACK");
//				if(HomeDialog != null){
//					Log.d(TAG,"Dialog Kill");
//					HomeDialog.dismiss();
//					HomeDialog = null;
//				}else{
//					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(CAN1CommManager.ESC));
//				}
				HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(CAN1CommManager.ESC));
			}
			if(event.getKeyCode() == KeyEvent.KEYCODE_MENU)
			{			
				Log.d(TAG,"KEYCODE_MENU");
			}
		}
		//return super.dispatchKeyEvent(event);
		return true;
	}
	
	public void KeyButtonClick(final int Data){
		Log.d(TAG,"KeyButtonClick");
		// TODO Auto-generated method stub

		if((ScreenIndex & SCREEN_STATE_MAIN_B_TOP) == SCREEN_STATE_MAIN_B_TOP){
			Log.d(TAG,"Click Main Key");
			_MainBBaseFragment.KeyButtonClick(Data);
		}else if((ScreenIndex & SCREEN_STATE_MENU_TOP) == SCREEN_STATE_MENU_TOP){
			Log.d(TAG,"Click Menu Key");
			_MenuBaseFragment.KeyButtonClick(Data);
		}

	}
	/////////////////////////////////////////////////////
	//Popup//////////////////////////////////////////////
	public void showQuickCouplerPopupLocking1(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupLocking1;
		HomeDialog.show();
	}
	public void showQuickCouplerPopupUnlocking1(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupUnlocking1;
		HomeDialog.show();
	}
	public void showQuickCouplerPopupLocking2(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupLocking2;
		HomeDialog.show();
	}
	public void showQuickCouplerPopupUnlocking2(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupUnlocking2;
		HomeDialog.show();
	}
	public void showCCoMode(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _CCoModePopup;
		HomeDialog.show();
	}
	public void showShiftMode(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _ShiftModePopup;
		HomeDialog.show();
	}
	public void showKickDown(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _KickDownPopup;
		HomeDialog.show();
	}
	public void showTCLockUp(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _TCLockUpPopup;
		HomeDialog.show();
	}
	public void showBucketPriority(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _BucketPriorityPopup;
		HomeDialog.show();
	}
	
	
	/////////////////////////////////////////////////////
	//Timer//////////////////////////////////////////////
	public class SeatBeltTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					SeatBelt = CAN1CommManager.DATA_STATE_LAMP_OFF;
				}
			});
			
		}
		
	}
	
	public void StartSeatBeltTimer(){
		CancelSeatBeltTimer();
		mSeatBeltTimer = new Timer();
		mSeatBeltTimer.schedule(new SeatBeltTimerClass(),5000);	
	}
	
	public void CancelSeatBeltTimer(){
		if(mSeatBeltTimer != null){
			mSeatBeltTimer.cancel();
			mSeatBeltTimer.purge();
			mSeatBeltTimer = null;
		}
		
	}
	
	public class AutoGreaseStopTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(CAN1CommManager.DATA_STATE_OFF);
					CAN1Comm.TxCANToMCU(247);
					CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(3);
				}
			});
			
		}
		
	}
	
	public void StartAutoGreaseStopTimer(){
		CancelAutoGreaseStopTimer();
		mAutoGreaseStopTimer = new Timer();
		mAutoGreaseStopTimer.schedule(new AutoGreaseStopTimerClass(),60000);	
	}
	
	public void CancelAutoGreaseStopTimer(){
		if(mAutoGreaseStopTimer != null){
			mAutoGreaseStopTimer.cancel();
			mAutoGreaseStopTimer.purge();
			mAutoGreaseStopTimer = null;
		}
		
	}
	
	public class AnimationRunningTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					AnimationRunningFlag = false;
					Log.d(TAG,"Animation Timer Finish");
				}
			});
			
		}
		
	}
	
	public void StartAnimationRunningTimer(){
		Log.d(TAG,"Animation Timer Start");
		AnimationRunningFlag = true;
		CancelAnimationRunningTimer();
		mAnimationRunningTimer = new Timer();
		mAnimationRunningTimer.schedule(new AnimationRunningTimerClass(),500);	
	}
	public void StartAnimationRunningTimer(int Time){
		Log.d(TAG,"Animation Timer Start");
		AnimationRunningFlag = true;
		CancelAnimationRunningTimer();
		mAnimationRunningTimer = new Timer();
		mAnimationRunningTimer.schedule(new AnimationRunningTimerClass(),Time);	
	}
	public void CancelAnimationRunningTimer(){
		if(mAnimationRunningTimer != null){
			mAnimationRunningTimer.cancel();
			mAnimationRunningTimer.purge();
			mAnimationRunningTimer = null;
		}
		
	}
	
	
	
			// Mirror Heat
	public class MirrorHeatTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(MirrorHeatCount < 60*15){
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_ON);
						CAN1Comm.TxCANToMCU(247);
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
						Log.d(TAG,"MirrorHeat On");
					}else if(MirrorHeatCount <60 * 15 + 5){
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_OFF);
						CAN1Comm.TxCANToMCU(247);
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
						Log.d(TAG,"MirrorHeat Off");
					}else{
						MirrorHeatCount = 0;
						CancelMirrorHeatTimer();
						Log.d(TAG,"MirrorHeat Timer timer");
					}
					if(MirrorHeatTimerFlag == false){
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_OFF);
						CAN1Comm.TxCANToMCU(247);
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
					}
					MirrorHeatCount++;
					Log.d(TAG,"MirrorHeat timer");
				}
			});
			
		}
		
	}
	
	public void StartMirrorHeatTimer(){
		Log.d(TAG,"MirrorHeat Timer Start");
		MirrorHeatCount = 0;
		MirrorHeatTimerFlag = true;
		CancelMirrorHeatTimer();
		mMirrorHeatTimer = new Timer();
		mMirrorHeatTimer.schedule(new MirrorHeatTimerClass(),1,1000);	
	}
	
	public void CancelMirrorHeatTimer(){
	
		if(mMirrorHeatTimer != null){
			MirrorHeatTimerFlag = false;
			mMirrorHeatTimer.cancel();
			mMirrorHeatTimer.purge();
			mMirrorHeatTimer = null;
		}
		
	}
	
	public class MirrorHeatPreHeatTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					StartMirrorHeatTimer();
					Log.d(TAG,"MirrorHeatPreHeat timer");
				}
			});
			
		}
		
	}
	
	public void StartMirrorHeatPreHeatTimer(){
		Log.d(TAG,"MirrorHeatPreHeat Timer Start");
		CancelMirrorHeatPreHeatTimer();
		mMirrorHeatPreHeatTimer = new Timer();
		mMirrorHeatPreHeatTimer.schedule(new MirrorHeatPreHeatTimerClass(),60000);	
	}
	
	public void CancelMirrorHeatPreHeatTimer(){
		if(mMirrorHeatPreHeatTimer != null){
			mMirrorHeatPreHeatTimer.cancel();
			mMirrorHeatPreHeatTimer.purge();
			mMirrorHeatPreHeatTimer = null;
		}
		
	}
	public void CheckMirrorHeatPreHeat(int _PreHeat, int _RPM){
		if(MirrorHeatPreHeatFlag == false){
			if(_RPM > 500 && _PreHeat == CAN1CommManager.DATA_STATE_ON){
				MirrorHeatPreHeatFlag = true;
				StartMirrorHeatPreHeatTimer();
			}
		}
	}
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////
	//Calculate//////////////////////////////////////////
	public String GetNumberString(long _Number){
		String strNumber;
		strNumber = NumberFormat.getNumberInstance(Locale.US).format(_Number);
		return strNumber;
	}
	public String GetFuelRateString(int _FuelRate){
		String strFuelRate;
		long long_Fuel;
		int nFuel;
		int nFuel_Under;
		
		long_Fuel = _FuelRate & 0xFFFFFFFFL;
		if(long_Fuel > 0xFB00L){
			long_Fuel = 0;
		}
		
		long_Fuel *= 500;
		
		nFuel = (int)(long_Fuel / 10000);
		nFuel_Under = (int)((long_Fuel % 10000) / 1000);
		
		strFuelRate = Integer.toString(nFuel) + "." + Integer.toString(nFuel_Under);
		return strFuelRate;
		
	}
	public String GetHourmeterString(int _Hourmeter){
		String strHourmeter;
		long long_Hour;
		int Hour;
		int Min;
		
		long_Hour = _Hourmeter & 0xFFFFFFFFL;
		if(long_Hour == 0xFFFFFFFFL){
			long_Hour = 0;
		}
		Hour = (int) (long_Hour / 3600);
		Min = (int) (((long_Hour % 3600) / 60) / 6);
		strHourmeter = GetNumberString(Hour) + "." + Integer.toString(Min);
		return strHourmeter;
	}
	public String GetOdometerStrng(int _Odometer, int Unit){
		String strOdometer = "";
		long long_Odo;
		long nOdo;
		long nOdo_Under;
		
		long_Odo = (_Odometer  & 0xFFFFFFFFL);
		if(long_Odo == 0xFFFFFFFFL){
			long_Odo = 0;
		}
		long_Odo *= 125;
		if(Unit == UNIT_ODO_MILE){
			long_Odo *= 62137;
			nOdo =  (long_Odo / 100000000);
			nOdo_Under = ( (long_Odo % 100000000) / 10000000);
		}else{
			nOdo = (int) (long_Odo / 1000);
			nOdo_Under = ((int) (long_Odo % 1000) / 100);
		}
		
		strOdometer = GetNumberString(nOdo) + "." + Long.toString(nOdo_Under);
		return strOdometer;
	}
	public String GetWeighit(int _Weighit, int Unit){
		String strWeight = "";
		long long_Weight;
		long nWeight;
		long nWeight_Under;
		
		long_Weight = (_Weighit  & 0xFFFFFFFFL);
		if(long_Weight == 0xFFFFFFFFL){
			long_Weight = 0;
		}
		if(Unit == UNIT_WEIGHT_LB){
			long_Weight *= 220462;
			nWeight =  (long_Weight / 1000);
			nWeight_Under = ( (long_Weight % 1000) / 100);
		}else{
			nWeight =  (long_Weight / 10);
			nWeight_Under =  (long_Weight % 10);
		}
				
		strWeight = GetNumberString(nWeight) + "." + Long.toString(nWeight_Under);
		return strWeight;
	}
	public String GetBattery(int _Battery){
		String strBattery = "";
		long long_Battery;
		int nBattery;
		int nBattery_Under;
		
		long_Battery = (_Battery  & 0xFFFFFFFFL);
		if(long_Battery > 500){
			long_Battery = 0;
		}
		
		nBattery = (int) (long_Battery / 10);
		nBattery_Under = (int) (long_Battery % 10);
		strBattery = GetNumberString(nBattery) + "." + Integer.toString(nBattery_Under);
		return strBattery;
	}
	public String GetTemp(int _Temp, int Unit){
		String strTemp;
		int nTemp;
		
		nTemp = GetTemp(_Temp);
		if(Unit == UNIT_TEMP_F){
			nTemp *= 18;
			nTemp = nTemp / 10;
			nTemp += 32;
			strTemp = GetNumberString(nTemp);
		}else{
			strTemp = GetNumberString(nTemp);
		}
		
		return strTemp;
		
	}
	public int GetTemp(int _Temp){
		long long_Temp;
		int nTemp;
		
		long_Temp = (_Temp  & 0xFFFFFFFFL);
		if(long_Temp >= 0xFFL){
			long_Temp = 0;
		}
		nTemp = (int) (long_Temp - 40);
		
		return nTemp;
	}
	public String GetHour(int _Hour){
		String strHour = "";
		int AbsHour;
		AbsHour = Math.abs(_Hour);
		
		if(AbsHour > 12){
			AbsHour = AbsHour - 12;
		}
		
		if(AbsHour == 0){
			AbsHour = 12;
		}
		if(AbsHour < 10)
			strHour = "0" + Integer.toString(AbsHour);
		else
			strHour = Integer.toString(AbsHour);
		
		return strHour;
	}
	public String GetMin(int _Min){
		String strMin = "";
		int AbsMin;
		
		AbsMin = Math.abs(_Min);
		if(AbsMin >= 60){
			AbsMin = 0;
		}
		
		if(AbsMin < 10)
			strMin = "0" + Integer.toString(AbsMin);
		else
			strMin = Integer.toString(AbsMin);
	
		
		return strMin;
	}
	public int GetAMPM(int _Hour){
		int AbsHour;
		int AMPM;
		
		AbsHour = Math.abs(_Hour);
		if(AbsHour >= 12){
			AMPM = CLOCK_PM;
		}else{
			AMPM = CLOCK_AM;
		}
		
		return AMPM;
	}
	public String GetRideControlSpeed(int _Speed, int Unit){
		String strSpeed = "";
		long long_Speed;
		long nSpeed;
		
		
		long_Speed = (_Speed  & 0xFFFFFFFFL);
		if(long_Speed == 0xFFFFFFFFL){
			long_Speed = 0;
		}
		if(Unit == UNIT_ODO_MILE){
			long_Speed *= 62137;
			nSpeed =  (long_Speed / 100000);
			
		}else{
			nSpeed =  long_Speed;
			
		}
				
		strSpeed = GetNumberString(nSpeed);
		return strSpeed;
	}
	/////////////////////////////////////////////////////
}
