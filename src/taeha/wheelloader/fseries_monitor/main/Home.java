package taeha.wheelloader.fseries_monitor.main;

import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.popup.AngleCalibrationResultPopup;
import taeha.wheelloader.fseries_monitor.popup.BrakePedalCalibrationPopup;
import taeha.wheelloader.fseries_monitor.popup.BucketPriorityPopup;
import taeha.wheelloader.fseries_monitor.popup.CCOModePopup;
import taeha.wheelloader.fseries_monitor.popup.EngineAutoShutdownCountPopup;
import taeha.wheelloader.fseries_monitor.popup.EngineModePopup;
import taeha.wheelloader.fseries_monitor.popup.EngineWarmingUpPopup;
import taeha.wheelloader.fseries_monitor.popup.KickDownPopup;
import taeha.wheelloader.fseries_monitor.popup.LoggedFaultDeletePopup;
import taeha.wheelloader.fseries_monitor.popup.MaintReplacePopup;
import taeha.wheelloader.fseries_monitor.popup.OperationHistoryInitPopup;
import taeha.wheelloader.fseries_monitor.popup.PressureCalibrationResultPopup;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking2;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking2;
import taeha.wheelloader.fseries_monitor.popup.ShiftModePopup;
import taeha.wheelloader.fseries_monitor.popup.SoundOutputPopup;
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup;
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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Home extends Activity {
	//CONSTANT//////////////////////////////////////////
	//Version/////////////////////////////////////////////////////////////////////////////
	//
	public static final int VERSION_HIGH 		= 1;
	public static final int VERSION_LOW 		= 0;
	public static final int VERSION_SUB_HIGH 	= 0;
	public static final int VERSION_SUB_LOW 	= 0;
	//
	//////////////////////////////////////////////////////////////////////////////////////
	
	// TAG
	private  final String TAG = "Home";
	


	public  static final int SCREEN_STATE_MAIN_B_TOP 										= 0x10000000;
	
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
	public  static final int SCREEN_STATE_MAIN_B_BRKAEPEDALCALIBRATION_TOP					= 0x17000000;
	public  static final int SCREEN_STATE_MAIN_B_BRKAEPEDALCALIBRATION_END					= 0x17FFFFFF;
	public  static final int SCREEN_STATE_MAIN_B_AEB_TOP									= 0x18000000;
	public  static final int SCREEN_STATE_MAIN_B_AEB_END									= 0x18FFFFFF;
	public  static final int SCREEN_STATE_MAIN_B_END 										= 0x1FFFFFFF;
	
	public  static final int SCREEN_STATE_MENU_TOP 											= 0x20000000;
	
	public  static final int SCREEN_STATE_MENU_MODE_TOP										= 0x21000000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_TOP							= 0x21100000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP				= 0x21110000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_MODE				= 0x21111000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_WARMINGUP		= 0x21112000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_SPEED			= 0x21113000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_END				= 0x21111FFF;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN						= 0x21120000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE						= 0x21130000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE						= 0x21140000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP						= 0x21150000;
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
	public  static final int SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP							= 0x21310000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT							= 0x21311000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_FREQ_END							= 0x2131FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP						= 0x21320000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_OFF						= 0x21321000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_AUTO						= 0x21322000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL					= 0x21323000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_END						= 0x2132FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP					= 0x21330000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW						= 0x21331000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_END					= 0x2133FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP							= 0x21340000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_WIPER_END							= 0x2134FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_TOP					= 0x21350000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_END					= 0x2135FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_DELAYSHUTDOWN_TOP					= 0x21360000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_DELAYSHUTDOWN_END					= 0x2136FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_END									= 0x213FFFFF;
	
	
	public  static final int SCREEN_STATE_MENU_MODE_END										= 0x21FFFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_TOP								= 0x22000000;
	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING					= 0x22100000;
	public  static final int SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP				= 0x22200000;
	public  static final int SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_INIT				= 0x22210000;
	public  static final int SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_END				= 0x222FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP					= 0x22300000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP			= 0x22310000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_END			= 0x2231FFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP			= 0x22320000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW			= 0x22321000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_DELETE		= 0x22322000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_END			= 0x2232FFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_END					= 0x223FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP					= 0x22400000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TCU					= 0x22410000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ECM					= 0x22420000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MONITOR				= 0x22430000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_MCU					= 0x22440000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_CLUSTER				= 0x22450000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_RMCU					= 0x22460000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_EHCU					= 0x22470000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_END					= 0x224FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_TOP						= 0x22500000;
	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_BOOMLEVERFLOAT			= 0x22510000;
	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_END						= 0x225FFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_END								= 0x22FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_TOP								= 0x23000000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP				= 0x23100000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW				= 0x23110000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_ESL				= 0x23120000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE			= 0x23130000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY			= 0x23140000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_END				= 0x231FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP					= 0x23200000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_TOP			= 0x23210000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_REPLACE		= 0x23211000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_CHANGECYCLE	= 0x23212000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_END			= 0x2321FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_END					= 0x232FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_TOP					= 0x23300000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_TOP				= 0x23310000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_RESULT			= 0x23311000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_END				= 0x2331FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_TOP			= 0x23320000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_RESULT		= 0x23321000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_END			= 0x2332FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_BRAKEPEDAL_TOP		= 0x23330000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_BRAKEPEDAL_END		= 0x2333FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_AEB_TOP				= 0x23340000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_AEB_END				= 0x2334FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_END					= 0x233FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP						= 0x23500000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW						= 0x23510000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP		= 0x23520000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN	= 0x23521000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_END		= 0x2352FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP			= 0x23530000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_END			= 0x2353FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP	= 0x23540000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_END	= 0x2354FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SOFTWAREUPATE_TOP			= 0x23550000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SOFTWAREUPATE_END			= 0x2355FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_END						= 0x235FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP						= 0x23600000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_END						= 0x236FFFFF;
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
	
	public  static final int SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP						= 0x30000000;
	
	public  static final int SCREEN_STATE_MAIN_ESL_CHECK_TOP								= 0x40000000;
	public  static final int SCREEN_STATE_MAIN_ESL_PASSWORD									= 0x41000000;
	public  static final int SCREEN_STATE_MAIN_ESL_CHECK_END								= 0x4FFFFFFF;
	
	public  static final int UNIT_ODO_KM 			= 0;
	public  static final int UNIT_ODO_MILE 			= 1;
	
	public  static final int UNIT_TEMP_F 			= 1;
	public  static final int UNIT_TEMP_C 			= 0;
	
	public  static final int UNIT_WEIGHT_TON 		= 0;
	public  static final int UNIT_WEIGHT_LB 			= 1;
	
	public  static final int UNIT_PRESSURE_BAR 		= 0;
	public  static final int UNIT_PRESSURE_MPA 		= 1;
	public  static final int UNIT_PRESSURE_KGF 		= 2;
	public  static final int UNIT_PRESSURE_PSI 		= 3;
	
	public  static final int CLOCK_AM		 		= 0;
	public  static final int CLOCK_PM		 		= 1;
	
	public 	static final int BRIGHTNESS_MANUAL		= 0;
	public 	static final int BRIGHTNESS_AUTO		= 1;
	
	public 	static final int BRIGHTNESS_MIN			= 0;
	public 	static final int BRIGHTNESS_MAX			= 7;
	
	public static final int STATE_INTERNAL_SPK			= 0;
	public static final int STATE_EXTERNAL_AUX	 		= 1;
	
	public static final int MAX_AS_LENGTH = 21;
	////////////////////////////////////////////////////
	
	//Resource//////////////////////////////////////////
	FrameLayout framelayoutMain;
	ImageView imgViewCameraScreen;
	////////////////////////////////////////////////////
	//Valuable//////////////////////////////////////////
	public int ScreenIndex;
	public int OldScreenIndex;
	
	// Unit
	public int UnitOdo;
	public int UnitTemp;
	public int UnitWeight;
	public int UnitPressure;
	
	// HourOdometer Index
	public int HourOdometerIndex;
	
	// MachineStatus Index
	public int MachineStatusUpperIndex;
	public int MachineStatusLowerIndex;
	
	// Weighing Display Index
	
	// Camera
	public int ActiveCameraNum;
	public int CameraOrder1;
	public int CameraOrder2;
	public int CameraOrder3;
	public int CameraOrder4;
	public int CameraReverseMode;
	int SelectGear;
	int SelectGearRange;
	int SelectGearDirection;
	int GearIndex;
	int CameraReverseOnCount;
	int CameraReverseOffCount;
	
	// Brightness
	public int BrightnessManualAuto;
	public int BrightnessManualLevel;
	public int BrightnessAutoDayLevel;
	public int BrightnessAutoNightLevel;
	public int BrightnessAutoStartTime;
	public int BrightnessAutoEndTime;
	public int BrihgtnessCurrent;
	
	// Smart Key
	public int SmartKeyUse;
	
	// Sound Output
	public int SoundState;
	
	// SeatBelt
	public int SeatBelt;
	
	// Weighing
	public int WeighingDisplayIndex;
	public int WeighingErrorDetect;
	
	// Buzzer
	int Buzzer;
	public boolean BuzzerOnFlag;
	
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
	EngineModePopup _EngineModePopup;
	EngineWarmingUpPopup	_EngineWarmingUpPopup;
	SpeedometerInitPopup	_SpeedometerInitPopup;
	OperationHistoryInitPopup	_OperationHistoryInitPopup;
	public AngleCalibrationResultPopup		_AngleCalibrationResultPopup;
	public PressureCalibrationResultPopup	_PressureCalibrationResultPopup;
	SoundOutputPopup						_SoundOutputPopup;
	BrakePedalCalibrationPopup				_BrakePedalCalibrationPopup;
	EngineAutoShutdownCountPopup			_EngineAutoShutdownCountPopup;
	MaintReplacePopup						_MaintReplacePopup;
	public LoggedFaultDeletePopup					_LoggedFaultDeletePopup;
	
	// Timer
	private Timer mSeatBeltTimer = null;
	private Timer mAutoGreaseStopTimer = null;
	private Timer mAnimationRunningTimer = null;
	private Timer mMirrorHeatPreHeatTimer = null;
	private Timer mMirrorHeatTimer = null;
	private Timer mSendCommandTimer = null;
	private Timer mCommErrStopTimer = null;
	
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
	
	int nSendCommandTimerIndex;
	
	// EngineAutoShutdown Data
	public int EngineAutoShutdownTime;
	public int EngineAutoShutdownStatus;
	public int EngineAutoShutdownType;
	public boolean EngineAutoShutdownESLFlag;
	public int ESLInterval;
	public int ESLMode;
	public boolean EngineAutoShutdownESLSetFlag;
	
	// Engine Auto Shutdown
	int EngineAutoShutdownRemainingTime;
	int EngineAutoShutdownMode;
	
	// AS
	public String strASNumDash;
	public String strASNum;
	
	// Model
	public CheckModel _CheckModel;
	////////////////////////////////////////////////////
	
	//Fragment//////////////////////////////////////////
	public  MainBBaseFragment 	_MainBBaseFragment;
	public  MenuBaseFragment 	_MenuBaseFragment;
	public 	ESLCheckFragment	_ESLCheckFragment;
	public 	ESLPasswordFragment	_ESLPasswordFragment;
	////////////////////////////////////////////////////
	
	//Animation/////////////////////////////////////////
	public ChangeFragmentAnimation _MainChangeAnimation;
	////////////////////////////////////////////////////

	//Lift Cycle Function///////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreateView");
		InitResource();
		InitFragment();
		InitPopup();
		InitValuable();
		InitAnimation();
		InitButtonListener();
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
		Log.d(TAG, "onResume");
		try {
			StartCommService();
			threadRead = new Thread(new ReadThread(this));
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
		imgViewCameraScreen = (ImageView)findViewById(R.id.imageView_main_camerascreen);
		imgViewCameraScreen.setClickable(false);
	}
	public void InitValuable(){
		ScreenIndex = 0;
		SeatBelt = CAN1CommManager.DATA_STATE_LAMP_ON;
		AnimationRunningFlag = false;
		MirrorHeatPreHeatFlag = false;
		MirrorHeatCount = 0;
		nSendCommandTimerIndex = 0;
		BuzzerOnFlag = false;
		EngineAutoShutdownESLFlag = false;
		EngineAutoShutdownESLSetFlag = false;
		_MainChangeAnimation = new ChangeFragmentAnimation(this, framelayoutMain, R.id.FrameLayout_main, null);
		_CheckModel = new CheckModel();
	}
	public void InitFragment(){
		_MainBBaseFragment = new MainBBaseFragment();
		_MenuBaseFragment = new MenuBaseFragment();
		_ESLCheckFragment = new ESLCheckFragment();
		_ESLPasswordFragment = new ESLPasswordFragment();
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
		_EngineModePopup = new EngineModePopup(this);
		_EngineWarmingUpPopup = new EngineWarmingUpPopup(this);
		_SpeedometerInitPopup = new SpeedometerInitPopup(this);
		_OperationHistoryInitPopup = new OperationHistoryInitPopup(this);
		_AngleCalibrationResultPopup = new AngleCalibrationResultPopup(this);
		_PressureCalibrationResultPopup = new PressureCalibrationResultPopup(this);
		_SoundOutputPopup = new SoundOutputPopup(this);
		_BrakePedalCalibrationPopup = new BrakePedalCalibrationPopup(this);
		_EngineAutoShutdownCountPopup = new EngineAutoShutdownCountPopup(this);
		_MaintReplacePopup = new MaintReplacePopup(this);
		_LoggedFaultDeletePopup= new LoggedFaultDeletePopup(this);
				
	}
	public void InitAnimation(){
		
	}
	public void InitButtonListener(){	
		
		imgViewCameraScreen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ExitCam();
			}
		});
		
	}
	public void ExcuteCamActivitybyKey(){

		CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_MANUAL;
		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_CAM, CameraOrder1);
	}
	public void ExcuteCamActivitybyReverseGear(){
	
		CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_REVERSEGEAR;
	}
	public boolean ExitCam(){
		try {
			if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL){
				CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_OFF;
				CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_CAM, 0xFF);
				imgViewCameraScreen.setClickable(false);
				
				return true;
			}else if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_REVERSEGEAR)
				return false;
			else 
				return true;
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		return true;
	}
	public void SaveASPhoneNumber(){
		byte[] ASNum;
		int Index = 0;
		String strASNum = "";
		ASNum = new byte[MAX_AS_LENGTH];
		for(int i = 0; i < MAX_AS_LENGTH; i++){
			ASNum[i] = (byte)0xFF;
		}
		
		ASNum = CAN1Comm.Get_ASPhoneNumber_PGN61184_151();
		CAN1Comm.Set_ASPhoneNumber_1095_PGN65425(ASNum);
		CAN1Comm.TxCANToMCU(145);
		
		for(int i = 0; i < MAX_AS_LENGTH; i++){
			if(ASNum[i] == 0x2A)
				break;
			else
				Index++;
		}

		
		if(Index > 0)
		{
			for(int i = 0; i < Index; i++){
				if(ASNum[i] == 0x23){
					strASNum += "#";
				}
				else{
					strASNum += Integer.toString(ASNum[i]-0x30);
				}
				
			}
		}
		
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();

		edit.putString("strASNum", strASNum);
		
		edit.commit();
		Log.d(TAG,"SaveASPhoneNumber : " + strASNum);
	}
	
	public void SaveCID(int _componentcode, int _manufacturecode, byte[] _componentbasicinformation){
		String strBasicInfo;
		
		strBasicInfo = new String(_componentbasicinformation,0,_componentbasicinformation.length);
		
		SharedPreferences SharePref = getSharedPreferences("CID", 0);
		SharedPreferences.Editor edit = SharePref.edit();

		edit.putInt("ComponentCode_Monitor", _componentcode);
		edit.putInt("ManufacturerCode_Monitor", _manufacturecode);
		edit.putString("ComponentBasicInformation_Monitor", strBasicInfo);
		
		edit.commit();
		Log.d(TAG,"SaveCID");
		Log.d(TAG,"length : " + Integer.toString(_componentbasicinformation.length));
	}
	public void SavePref(){
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("UnitOdo", UnitOdo);
		edit.putInt("UnitTemp", UnitTemp);
		edit.putInt("UnitWeight", UnitWeight);
		edit.putInt("UnitPressure", UnitPressure);
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
		UnitTemp = SharePref.getInt("UnitTemp", UNIT_TEMP_C);
		UnitWeight = SharePref.getInt("UnitWeight", UNIT_WEIGHT_TON);
		UnitPressure = SharePref.getInt("UnitPressure", UNIT_PRESSURE_BAR);
		HourOdometerIndex = SharePref.getInt("HourOdometerIndex", CAN1CommManager.DATA_STATE_HOURMETER_LATEST);
		MachineStatusUpperIndex = SharePref.getInt("MachineStatusUpperIndex", CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT);
		MachineStatusLowerIndex = SharePref.getInt("MachineStatusLowerIndex", CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT);
		WeighingDisplayIndex = SharePref.getInt("WeighingDisplayIndex", CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A);
		WeighingErrorDetect = SharePref.getInt("WeighingErrorDetect", CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF);
		
		ActiveCameraNum = SharePref.getInt("ActiveCameraNum", 4);
		CameraOrder1 = SharePref.getInt("CameraOrder1", 0);
		CameraOrder2 = SharePref.getInt("CameraOrder2", 1);
		CameraOrder3 = SharePref.getInt("CameraOrder3", 2);
		CameraOrder4 = SharePref.getInt("CameraOrder4", 3);
		CameraReverseMode = SharePref.getInt("CameraReverseMode", CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF);
		
		BrightnessManualAuto = SharePref.getInt("BrightnessManualAuto", BRIGHTNESS_MANUAL);
		BrightnessManualLevel = SharePref.getInt("BrightnessManualLevel", BRIGHTNESS_MAX);
		BrightnessAutoDayLevel = SharePref.getInt("BrightnessAutoDayLevel", BRIGHTNESS_MAX);
		BrightnessAutoNightLevel = SharePref.getInt("BrightnessAutoNightLevel", BRIGHTNESS_MAX);
		BrightnessAutoStartTime = SharePref.getInt("BrightnessAutoStartTime", 8);
		BrightnessAutoEndTime = SharePref.getInt("BrightnessAutoEndTime", 18);
		SoundState = SharePref.getInt("SoundState", STATE_INTERNAL_SPK);
		
		SmartKeyUse = SharePref.getInt("SmartKeyUse", CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF);
		
		strASNumDash = SharePref.getString("strASNumDash", "");
		strASNum = SharePref.getString("strASNum", "");
		Log.d(TAG,"LoadPref");
	}
	public void setSoundOutput(int _soundstate) {
		try {
			CAN1Comm.LineOutfromJNI(_soundstate);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG, "NullPointerException");
		} catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG, "Load Library Error");
		}
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
	//ESL Check Screen Fragment
	public void showESLCheckFragment(){
		_ESLCheckFragment = new ESLCheckFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _ESLCheckFragment);
		transaction.commit();
	}
	//ESL Check Screen Fragment
	public void showESLPasswordFragment(){
		_ESLPasswordFragment = new ESLPasswordFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _ESLPasswordFragment);
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
		//	_MainChangeAnimation.StartChangeAnimation(_MainBBaseFragment);
		//	_MainChangeAnimation.StartChangeAnimation(_MenuBaseFragment);
		//	_MainChangeAnimation.StartChangeAnimation(_ESLCheckFragment);
			showESLCheckFragment();
			StartSendCommandTimer();
			StartCommErrStopTimer();
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
			
			SaveCID(ComponentCode,ManufacturerCode,ComponentBasicInformation);
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
					if(activityRef.get().ScreenIndex == Home.SCREEN_STATE_MAIN_ESL_CHECK_TOP
					||	activityRef.get().ScreenIndex == Home.SCREEN_STATE_MAIN_ESL_PASSWORD){
						
					}else{
						activityRef.get().GetDataFromNative();
						activityRef.get().UpdateUI();
					}
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
		RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		Buzzer = CAN1Comm.Get_Buzzer_723_PGN65364();
		SelectGear = CAN1Comm.Get_SelectGear_541_PGN65434();
		SelectGearRange = SelectGear & 0x0F;
		SelectGearDirection = ((SelectGear & 0x30) >> 4);
		
		EngineAutoShutdownRemainingTime = CAN1Comm.Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122();
		EngineAutoShutdownMode = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN61184_122();

		
		CheckMirrorHeatPreHeat(PreHeat,RPM);
		SetBackLight();
	}
	public void UpdateUI() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				CameraDisplay();
				CheckBuzzer();
				CheckEngineAutoShutdown();
			}
		});
	
	}
	public void CheckBuzzer(){
		if(CAN1Comm.Get_CommErrCnt() < 1000){
			if(Buzzer == CAN1Comm.BUZZER_ON){
				BuzzerOnFlag = true;
				if(CAN1Comm.BuzzerStatus == CAN1Comm.BUZZER_OFF || CAN1Comm.BuzzerStatus == CAN1Comm.BUZZER_STOP){
					CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_ON);	// Buzzer On
				}
			}else if(Buzzer == CAN1Comm.BUZZER_OFF){
				if(CAN1Comm.BuzzerStatus == CAN1Comm.BUZZER_ON){
					CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_OFF);	// Buzzer Off
					CAN1Comm.BuzzerStatus = CAN1Comm.BUZZER_STOP;
				}
			}			
		}
	}
	public void CameraDisplay(){
		if(GearIndex == CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON){
			if(SelectGearDirection == CAN1CommManager.DATA_INDEX_SELECTGEAR_DIR_R){	
				CameraReverseOffCount = 0; 
				if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_OFF
				|| CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL){
					CameraReverseOnCount++;
					
				}
				else{
					CameraReverseOnCount = 0;
				}
				if(CameraReverseOnCount >= 5){
					CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_REVERSEGEAR;
					CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_CAM, CameraOrder1);
					imgViewCameraScreen.setClickable(false);
					CameraReverseOnCount = 0;
				}
					
			}else{
				CameraReverseOnCount = 0;
				if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_REVERSEGEAR){
					CameraReverseOffCount++;
					
				}else{
					CameraReverseOffCount = 0;
				}
				
				if(CameraReverseOffCount >= 5){
					CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_OFF;
					CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_CAM, 0xFF);
					imgViewCameraScreen.setClickable(false);
				}
			}
		}
		if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_REVERSEGEAR
		|| CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL){
			imgViewCameraScreen.setClickable(true);
		}else{
			imgViewCameraScreen.setClickable(false);
		}
	}
	/////////////////////////////////////////////////////
	//Backlight//////////////////////////////////////////
	public void SetBackLight(){
		if(ScreenIndex >= SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_TOP && ScreenIndex <= SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_END)
			return;
		
		int BackLight;
		if(BrightnessManualAuto == BRIGHTNESS_AUTO)
		{
			BackLight = CheckAutoBacklight();
		}
		else
		{
			BackLight = BrightnessManualLevel + 1;
		}
		if(BackLight != BrihgtnessCurrent){
			Log.d(TAG,"BackLight : " + Integer.toString(BackLight));
			CAN1Comm.Set_BacklightIlluminationLevel_719_PGN61184_109(BackLight);
			CAN1Comm.TxCANToMCU(109);
			
			CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_LCD, BackLight);
			BrihgtnessCurrent = BackLight;
		}
	}
	
	public int CheckAutoBacklight(){

		int CurrHour;
		int BackLight;

		CurrHour = CAN1Comm.Get_RTColock_Hour();
				
		if((BrightnessAutoStartTime <= CurrHour) && (CurrHour <= BrightnessAutoEndTime))
		{
			BackLight = BrightnessAutoDayLevel + 1;
		}
		else
		{
			BackLight = BrightnessAutoNightLevel + 1;
		}
		
		return BackLight;
	}
	
	public void CheckEngineAutoShutdown(){
		if(ScreenIndex != SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP
			&& EngineAutoShutdownMode == 1
			&& EngineAutoShutdownRemainingTime <= 60){
			
			showEngineAutoShutdownCount();
			OldScreenIndex = ScreenIndex;
			
		}else if(ScreenIndex == SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP){
			if( EngineAutoShutdownMode == 0
					|| EngineAutoShutdownRemainingTime > 60){
				
				if(HomeDialog != null){
					HomeDialog.dismiss();
					HomeDialog = null;
				}
			}
		}
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
	public void showEngineMode(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _EngineModePopup;
		HomeDialog.show();
	}
	public void showEngineWarmingUp(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _EngineWarmingUpPopup;
		HomeDialog.show();
	}
	public void showSpeedometerInit(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		_SpeedometerInitPopup = new SpeedometerInitPopup(this);
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _SpeedometerInitPopup;
		HomeDialog.show();
	}
	public void showOperationHistoryInit(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		_OperationHistoryInitPopup = new OperationHistoryInitPopup(this);
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _OperationHistoryInitPopup;
		HomeDialog.show();
	}
	public void showAngleCalibrationResult(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _AngleCalibrationResultPopup;
		HomeDialog.show();
	}
	public void showPressureCalibrationResult(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _PressureCalibrationResultPopup;
		HomeDialog.show();
	}
	public void showSoundOutput(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _SoundOutputPopup;
		HomeDialog.show();
	}
	public void showBrkaePedalCalibration(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _BrakePedalCalibrationPopup;
		HomeDialog.show();
	}
	public void showEngineAutoShutdownCount(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _EngineAutoShutdownCountPopup;
		HomeDialog.show();
	}
	public void showMaintReplace(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _MaintReplacePopup;
		HomeDialog.show();
	}
	public void showLoggedFaultDelete(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _LoggedFaultDeletePopup;
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
	
	
	public void StartSendCommandTimer(){
		CancelSendCommandTimer();
		mSendCommandTimer = new Timer();
		mSendCommandTimer.schedule(new SendCommandTimerClass(),500,500);
	}
	
	public void CancelSendCommandTimer(){
		if(mSendCommandTimer != null){
			mSendCommandTimer.cancel();
			mSendCommandTimer.purge();
			mSendCommandTimer = null;
		}
		
	}
	
	public class SendCommandTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			Log.d(TAG,"mSendCommandTimer");	
						
			try {
				if(nSendCommandTimerIndex == 0){
					setSoundOutput(SoundState);
					CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.COMMAND_MAINTENANCE_ITEM_LIST_REQUEST);
					CAN1Comm.TxCANToMCU(12);
					
				}
				else if(nSendCommandTimerIndex == 1){
					CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_REQUEST);
					CAN1Comm.TxCANToMCU(12);						
				}
				else if(nSendCommandTimerIndex == 2){
					CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(7);
					CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
					CAN1Comm.TxCANToMCU(123);							
				}
				else if(nSendCommandTimerIndex == 3){
					CAN1Comm.Set_SettingSelection_PGN61184_105(0xF);
					CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
					CAN1Comm.Set_AutoRideControlOperationSpeedForward_PGN61184_105(0xF);
					CAN1Comm.Set_AutoRideControlOperationSpeedBackward_PGN61184_105(0xF);
					CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
					CAN1Comm.TxCANToMCU(105);
					CAN1Comm.Set_SettingSelection_PGN61184_105(15);
				}
				
				else if (nSendCommandTimerIndex == 4){
					CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(0); //STATE_WEIGHT_OFFSET_SETTING_CALL
					CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayIndex);
					CAN1Comm.Set_SuddenChangeError_PGN61184_62(CAN1Comm.Get_SuddenChangeError_PGN65450());
    				CAN1Comm.Set_BucketFullInError_PGN61184_62(CAN1Comm.Get_BucketFullInError_PGN65450());
					CAN1Comm.TxCANToMCU(62);
					CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(3);
					CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
				}	
				else if (nSendCommandTimerIndex == 5){
					CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_VERSION,1);
				}	
				else if(nSendCommandTimerIndex == 6){
					CAN1Comm.Set_AutomaticEngineShutdown_363_PGN61184_121(3);
					CAN1Comm.Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(3);
					CAN1Comm.Set_EngineShutdownCotrolByte_PGN61184_121(0xF);
					CAN1Comm.Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(0xFF);
					CAN1Comm.TxCANToMCU(121);
				}
				else {
					CancelSendCommandTimer();
				}
				nSendCommandTimerIndex++;
			} catch (RuntimeException e) {
				// TODO: handle exception
				Log.e(TAG,"RuntimeExeption1, nSendCommandTimerIndex : " + Integer.toString(nSendCommandTimerIndex));
				nSendCommandTimerIndex = 0;
			}
			
		}
		
	}
	
	
	public class CommErrStopTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			int CommErrCount;
			CommErrCount = CAN1Comm.Get_CommErrCnt();
			CommErrCount++;
			if(CommErrCount == 1000){
				CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_BUZ, CAN1Comm.BUZZER_ON);	// Buzzer On
				//IndicatorFragment.WarningDisplay(1);	
			}
			if(CommErrCount >= 1000){
				CommErrCount = 1001;	
			}
			
			CAN1Comm.Set_CommErrCnt(CommErrCount);
		}
		
	}
	
	public void StartCommErrStopTimer(){
		CancelCommErrStopTimer();
		mCommErrStopTimer = new Timer();
		mCommErrStopTimer.schedule(new CommErrStopTimerClass(),1,10);	
	}
	
	public void CancelCommErrStopTimer(){
		if(mCommErrStopTimer != null){
			mCommErrStopTimer.cancel();
			mCommErrStopTimer.purge();
			mCommErrStopTimer = null;
		}
	}
	
	
	/////////////////////////////////////////////////////
	//Version////////////////////////////////////////////
	public byte[] GetMonitorComponentBasicInfo()throws NullPointerException{
		String str;
		byte[] componetbasicinfo;
		componetbasicinfo = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];
		for(int i = 0; i < CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION; i++){
			componetbasicinfo[i] = (byte) 0xFF;
		}
		SharedPreferences SharePref = getSharedPreferences("CID", 0);

		str = SharePref.getString("ComponentBasicInformation_Monitor", "");

		byte[] Temp;
		Temp = new byte[str.length()];
		
		Temp = str.getBytes();
		
		for(int i = 0; i < str.length(); i++){
			componetbasicinfo[i] = Temp[i];
		}
		return componetbasicinfo; 
	}
	public int FindProgramSubInfo(byte[] BasicInfo)throws NullPointerException{
		int SubVersion = 0xFF;
		
		int Index = 4;
		int Index2 = 0;
		////////////// Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
			}
			else{
				break;
			}
		}
		/////////////////////////////////////////////
		
		//////////// Find Model Name/////////////////
		for(int i = Index + 1; i < CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index2++;
			}
			else{
				
				SubVersion = BasicInfo[Index+Index2+2];
				break;
			}
		}
		/////////////////////////////////////////////
		
		
		return SubVersion;
	}
	public String GetModelNameString(byte[] BasicInfo)throws NullPointerException{
		String strModel;
		int Index = 4;
		int Index2 = 0;
		boolean bAsterisk = false;
		////////////// Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}
		
		
		/////////////////////////////////////////////
		
		//////////// Find Model Name/////////////////
		for(int i = Index + 1; i < CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index2++;
				bAsterisk = false;
			}
			else{
				bAsterisk = true;
				break;
			}
		}
		/////////////////////////////////////////////
		char[] Model;
		Model = new char [Index2];
		int[] Temp;
		Temp = new int[Index2];
		
		if(bAsterisk == false){
			strModel = "";
		}else{
			for(int i = 0; i < Index2; i++){
				Model[i] = (char)BasicInfo[i+Index+1];
			}
			strModel = new String(Model,0,Model.length);
			
		}
		return strModel;
	}
	public String GetProgramVersion(byte[] BasicInfo)throws NullPointerException{
		int nVersion;
		int VersionHigh, VersionLow;
		float fVersion;
		String strVer;
		nVersion = (BasicInfo[3] & 0xFF);
		VersionHigh = ((nVersion & 0xF0) >> 4);
		VersionLow = (nVersion & 0x0F);

		if(VersionLow > 10 && VersionHigh >= 15){
			strVer = "";
		}else{
			strVer = Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow);
		}

		return strVer;
	}
	public String GetVersionString(byte[] BasicInfo, int SubInfo)throws NullPointerException{
		String strVersion = GetProgramVersion(BasicInfo);
		int SubVersionHigh;
		int SubVersionLow;
		
		SubVersionHigh = ((SubInfo & 0xF0) >> 4);
		SubVersionLow = (SubInfo & 0x0F);
		
		if(0 <= SubVersionHigh && SubVersionHigh <= 14){
			strVersion = strVersion + "." + Integer.toHexString(SubVersionHigh);
		}
		
		if(0 <= SubVersionLow && SubVersionLow <= 14){
			strVersion = strVersion + "." + Integer.toHexString(SubVersionLow);
		}
		
		return strVersion;
	}
	public String GetVersionString(int VersionHigh, int VersionLow, int VersionSubHigh, int VersionSubLow)throws NullPointerException{
		String strVersion;
		strVersion = Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow) 
				+ "." +Integer.toString(VersionSubHigh)  + "." + Integer.toString(VersionSubLow);
		return strVersion;
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
	
	public String GetSectoMinString(int _Sec){
		String strMin;
		long long_Sec;
		int Min;
		int Sec;
		
		long_Sec = _Sec & 0xFFFFFFFFL;
		if(long_Sec == 0xFFFFFFFFL){
			long_Sec = 0;
		}
		Min = (int) (long_Sec / 60);
		Sec = (int) ((long_Sec % 60) / 6);
		strMin = GetNumberString(Min) + "." + Integer.toString(Sec);
		return strMin;
	}

	public String GetSectoMinString(int _Sec, String Unit1, String Unit2){
		String strMin;
		long long_Sec;
		int Min;
		int Sec;
		
		long_Sec = _Sec & 0xFFFFFFFFL;
		if(long_Sec == 0xFFFFFFFFL){
			long_Sec = 0;
		}
		Min = (int) (long_Sec / 60);
		Sec = (int) ((long_Sec % 60));
		if(Min == 0 && Sec != 0){
			strMin = Integer.toString(Sec) + Unit2;
		}else if(Min != 0 && Sec == 0){
			strMin = GetNumberString(Min) + Unit1;
		}
		else{
			strMin = GetNumberString(Min) + Unit1 +  " " + Integer.toString(Sec) + Unit2;
		}
		
		return strMin;
	}
	
	public String GetEPPRCurrent(int current){
		String strCurrent;
		long long_Current;

		
		long_Current = current & 0xFFFFFFFFL;
		if(long_Current == 0xFFFFFFFFL){
			long_Current = 0;
		}
		
		long_Current *= 5;
		
		if(long_Current > CAN1CommManager.DATA_STATE_EPPRCURRENT_MAX)
			long_Current = 0;
		
		strCurrent = Long.toString(long_Current);
		
		return strCurrent;
	}
	
	public String GetJoystickPositionString(int position){
		String strPosition;
		long long_Position;
		int n100;
		int n10;
		int n1;
		int Under1;
		
		long_Position = position & 0xFFFFFFFFL;
		if(long_Position == 0xFFFFFFFFL){
			long_Position = 0;
		}
		
		n100 = (int) ((long_Position  / 1000) % 10);
		n10 = (int) ((long_Position  / 100) % 10);
		n1 = (int) ((long_Position  / 10) % 10);
		Under1 = (int) ((long_Position  / 1) % 10);
		
		if(n100 != 0){
			strPosition = Integer.toString(n100) + Integer.toString(n10) + Integer.toString(n1) + "." + Integer.toString(Under1);
		}else if(n10 != 0){
			strPosition = Integer.toString(n10) + Integer.toString(n1) + "." + Integer.toString(Under1);
		}else {
			strPosition = Integer.toString(n1) + "." + Integer.toString(Under1);
		}
		
		return strPosition;
	}
	
	public String GetASPhoneNum(byte[] as){
		String strAS = "";
		if(as.length == 0){
	
		}
		else if(as.length <= 4){
			for(int i = 0; i < as.length; i++){
				strAS += Integer.toString(as[i]);
			}
			
		}
		else if(as.length <= 8){
			for(int i = 0; i < 4; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 4; i < as.length; i++){
				strAS += Integer.toString(as[i]);
			}
		}
		else if(as.length <= 12){
			for(int i = 0; i < 4; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 4; i < 8; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 8; i < as.length; i++){
				strAS += Integer.toString(as[i]);
			}
		}
		else if(as.length <= 16){
			for(int i = 0; i < 4; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 4; i < 8; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 8; i < 12; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 12; i < as.length; i++){
				strAS += Integer.toString(as[i]);
			}
		}
		else if(as.length <= 20){
			for(int i = 0; i < 4; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 4; i < 8; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 8; i < 12; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 12; i < 16; i++){
				strAS += Integer.toString(as[i]);
			}
			strAS += "-";
			for(int i = 16; i < as.length; i++){
				strAS += Integer.toString(as[i]);
			}
		}
		return strAS;
	}
	/////////////////////////////////////////////////////
}
