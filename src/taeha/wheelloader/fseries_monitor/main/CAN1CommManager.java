package taeha.wheelloader.fseries_monitor.main;

import java.io.FileDescriptor;

import android.R.bool;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class CAN1CommManager extends ICAN1CommManager.Stub{		// ttySAC1(Linux), UART1(Exynos), UART2(STM32) 
	
	private static final String TAG = "CAN1CommManager";
	static final RemoteCallbackList<ICAN1CommManagerCallback> callbacks = new RemoteCallbackList<ICAN1CommManagerCallback>();
	private int ReqPopup = 0xFFFF;
	// Singleton Pattern
	//////////////////////Init////////////////////////////////////
	private CommService service;
	private static CAN1CommManager instance = null;
	private static ShareDataClass Datainstance = null;
	
	/////////////////////CMD//////////////////////////////////////
	
	public static int CMD_KEY		= 0x00;
	public static int CMD_LCD		= 0x01;
	public static int CMD_BUZ		= 0x02;
	public static int CMD_RTC		= 0x03;
	public static int CMD_CAM		= 0x04;
	public static int CMD_VERSION	= 0x05;
	public static int CMD_DOWN		= 0x06;
	public static int CMD_SMK		= 0x07;
	public static int CMD_LAMP		= 0x08;
	public static int CMD_STARTCAN	= 0x09;
	public static int CMD_EEPROMTEST	= 0x0C;
	public static int CMD_FLASHTEST		= 0x0D;
	
	
	public static int RES_KEY		= 0x80;
	public static int RES_LCD		= 0x81;
	public static int RES_BUZ		= 0x82;
	public static int RES_RTC		= 0x83;
	public static int RES_CAM		= 0x84;
	public static int RES_VERSION	= 0x85;
	public static int RES_DOWN		= 0x86;
	public static int RES_SMK		= 0x87;
	public static int RES_LAMP		= 0x88;
	public static int RES_STARTCAN	= 0x89;
	public static int RES_EEPROMTEST	= 0x8C;
	public static int RES_FLASHTEST		= 0x8D;
	
	///////////////////////Key/////////////////////////////////
	public static final int OFF 				= 0x00000000;
	public static final int MENU 				= 0x00000001;
	public static final int ESC 				= 0x00000002;
	public static final int LEFT 				= 0x00000004;
	public static final int RIGHT 				= 0x00000008;
	public static final int ENTER 				= 0x00000010;
	public static final int CAMERA 				= 0x00000020;
	
	public static final int MAINLIGHT 			= 0x00000100;
	public static final int WORKLIGHT 			= 0x00000200;
	public static final int AUTO_GREASE 		= 0x00000400;
	public static final int QUICK_COUPLER 		= 0x00000800;
	public static final int RIDE_CONTROL 		= 0x00001000;
	public static final int WORK_LOAD 			= 0x00002000;
	
	public static final int BEACON_LAMP 		= 0x00010000;
	public static final int REAR_WIPER 			= 0x00020000;
	public static final int MIRROR_HEAT 		= 0x00040000;
	public static final int AUTOPOSITION 		= 0x00080000;
	public static final int FINEMODULATION		= 0x00100000;
	public static final int FN		 			= 0x00200000;


	public static final int MENU_LEFT 			= 0X00000005;
	public static final int MENU_RIGHT 			= 0X00000009;
	public static final int MENU_ENTER 			= 0X00000011;
	public static final int LEFT_RIGHT 			= 0X0000000C;
	public static final int LEFT_RIGHT_ENTER 	= 0X0000001C;

	public static final int LONG_MENU 			= 0x01000001;
	public static final int LONG_ESC 			= 0x01000002;
	public static final int LONG_LEFT 			= 0X01000004;
	public static final int LONG_RIGHT 			= 0X01000008;
	public static final int LONG_ENTER 			= 0x01000010;
	
	public static final int LONG_MENU_LEFT 		= 0x01000005;
	public static final int LONG_MENU_RIGHT 	= 0x01000009;
	public static final int LONG_MENU_ENTER 	= 0x01000011;	
	public static final int LONG_LEFT_RIGHT 	= 0X0100000C;
	public static final int LONG_LEFT_RIGHT_ENTER 		= 0x0100001C;

	
	public static final int POWER_OFF 			= 0x000000F5;
	///////////////////////////////////////////////////////////////
	
	////////////////////CAN Data SPN///////////////////////////////	
	public static final int DATA_STATE_LAMP_OFF		 		= 0;
	public static final int DATA_STATE_LAMP_ON		 		= 1;
	
	public static final int DATA_STATE_OFF		 			= 0;
	public static final int DATA_STATE_ON		 			= 1;
	
	public static final int DATA_STATE_ECO_GREEN = 0;
	public static final int DATA_STATE_ECO_YELLOW = 1;
	public static final int DATA_STATE_ECO_RED = 2;
	public static final int DATA_STATE_ECO_WHITE = 3;
	
	public static final int DATA_STATE_ENGINE_MODE_PWR 		= 0;
	public static final int DATA_STATE_ENGINE_MODE_STD 		= 1;
	public static final int DATA_STATE_ENGINE_MODE_ECONO 	= 2;

	public static final int DATA_STATE_ENGINE_WARMINGUP_OFF = 0;
	public static final int DATA_STATE_ENGINE_WARMINGUP_ON 	= 1;
	
	public static final int DATA_STATE_TM_CLUTCHCUTOFF_OFF		 = 0;
	public static final int DATA_STATE_TM_CLUTCHCUTOFF_L		 = 1;
	public static final int DATA_STATE_TM_CLUTCHCUTOFF_M		 = 2;
	public static final int DATA_STATE_TM_CLUTCHCUTOFF_H		 = 3;
  
 	public static final int DATA_STATE_TM_SHIFTMODE_MANUAL		 = 0;
	public static final int DATA_STATE_TM_SHIFTMODE_AL			 = 1;
	public static final int DATA_STATE_TM_SHIFTMODE_AN			 = 2;
	public static final int DATA_STATE_TM_SHIFTMODE_AH			 = 3; 
  
  	public static final int DATA_STATE_TM_LOCKUPCLUTCH_OFF		 = 0;
	public static final int DATA_STATE_TM_LOCKUPCLUTCH_ON		 = 1;
	
	public static final int DATA_STATE_HOURMETER_LATEST 		= 0;
	public static final int DATA_STATE_FUELRATE_CURRENT 		= 1;
	public static final int DATA_STATE_ODOMETER_TOTAL 			= 2;
	public static final int DATA_STATE_ODOMETER_LATEST 			= 3;
	
	// ++, 150317 bwk
	public static final int DATA_STATE_CURRENT_FUEL_RATE		= 0;
	public static final int DATA_STATE_AVERAGE_FUEL_RATE		= 1;
	public static final int DATA_STATE_LATEST_FUEL_CONSUMED		= 2;
	// --, 150317 bwk
	
	public static final int DATA_STATE_MACHINESTATUS_NOSELECT	= 0;
	public static final int DATA_STATE_MACHINESTATUS_HYD		= 1;
	public static final int DATA_STATE_MACHINESTATUS_BATTERY	= 2;
	public static final int DATA_STATE_MACHINESTATUS_COOLANT	= 3;
	public static final int DATA_STATE_MACHINESTATUS_TMOIL		= 4;
	public static final int DATA_STATE_MACHINESTATUS_WEIGHING	= 5;
	// ++, 150326 bwk
	public static final int DATA_STATE_MACHINESTATUS_FRONTAXLE	= 6;
	public static final int DATA_STATE_MACHINESTATUS_REARAXLE	= 7;
	// --, 150326 bwk
	
	
	public static final int DATA_STATE_WEIGHINGDISPLAY_TOTAL_A	= 0;
	public static final int DATA_STATE_WEIGHINGDISPLAY_TOTAL_B	= 1;
	public static final int DATA_STATE_WEIGHINGDISPLAY_TOTAL_C	= 2;
	public static final int DATA_STATE_WEIGHINGDISPLAY_DAILY	= 3;
	
	public static final int DATA_STATE_WEIGHING_ERRORDETECT_OFF	= 0;
	public static final int DATA_STATE_WEIGHING_ERRORDETECT_ON	= 1;
	
	public static final int DATA_STATE_WEIGHING_ACCUMULATION_MANUAL	= 1;
	public static final int DATA_STATE_WEIGHING_ACCUMULATION_AUTO	= 3;
	
	public static final int DATA_STATE_RIDECONTROL_OFF			= 0;
	public static final int DATA_STATE_RIDECONTROL_ON			= 1;
	public static final int DATA_STATE_RIDECONTROL_AUTO			= 2;
	
	public static final int DATA_STATE_COOLINGFAN_OFF			= 0;
	public static final int DATA_STATE_COOLINGFAN_FORWARD		= 1;
	public static final int DATA_STATE_COOLINGFAN_REVERSE		= 2;
	
	public static final int DATA_STATE_KEY_MAINLIGHT_OFF		= 0;
	public static final int DATA_STATE_KEY_MAINLIGHT_LV1		= 1;
	public static final int DATA_STATE_KEY_MAINLIGHT_LV2		= 2;
	
	public static final int DATA_STATE_KEY_WORKLIGHT_OFF		= 0;
	public static final int DATA_STATE_KEY_WORKLIGHT_LV1		= 1;
	public static final int DATA_STATE_KEY_WORKLIGHT_LV2		= 2;
	
	public static final int DATA_STATE_KEY_QUICKCOUPLER_OFF			= 2;
	public static final int DATA_STATE_KEY_QUICKCOUPLER_LOCK		= 1;
	public static final int DATA_STATE_KEY_QUICKCOUPLER_UNLOCK		= 0;
	
	public static final int DATA_STATE_KEY_RIDECONTROL_OFF		= 0;
	public static final int DATA_STATE_KEY_RIDECONTROL_MANUAL	= 1;
	public static final int DATA_STATE_KEY_RIDECONTROL_AUTO		= 2;
	
	public static final int DATA_STATE_KEY_REARWIPER_SPEED_OFF		= 0;
	public static final int DATA_STATE_KEY_REARWIPER_SPEED_INT		= 1;
	public static final int DATA_STATE_KEY_REARWIPER_SPEED_LOW		= 2;
	
	public static final int DATA_STATE_KEY_REARWIPER_WASHER_OFF		= 0;
	public static final int DATA_STATE_KEY_REARWIPER_WASHER_ON		= 1;
	
	public static final int DATA_STATE_KEY_DETENT_BOOM_OFF			= 0;
	public static final int DATA_STATE_KEY_DETENT_BOOM_UP			= 1;
	public static final int DATA_STATE_KEY_DETENT_BOOM_DOWN			= 2;
	public static final int DATA_STATE_KEY_DETENT_BOOM_UPDOWN		= 3;
	
	public static final int DATA_STATE_KEY_DETENT_BUCKET_OFF		= 0;
	public static final int DATA_STATE_KEY_DETENT_BUCKET_IN			= 1;
	public static final int DATA_STATE_KEY_DETENT_BUCKET_OUT		= 2;
	public static final int DATA_STATE_KEY_DETENT_BUCKET_INOUT		= 3;
	
	public static final int DATA_STATE_KEY_DETENT_BOOM_POS			= 0;
	public static final int DATA_STATE_KEY_DETENT_BUCKET_POS		= 2;
	
	public static final int DATA_STATE_ENGINERPM_DOWN				= 0;
	public static final int DATA_STATE_ENGINERPM_UP					= 1;
	
	public static final int DATA_STATE_KICKDOWN_UPDOWN				= 0;
	public static final int DATA_STATE_KICKDOWN_DOWNONLY			= 1;
	
	public static final int DATA_STATE_BUCKETPRIORITY_OFF			= 0;
	public static final int DATA_STATE_BUCKETPRIORITY_ON			= 1;
		
	public static final int DATA_STATE_SOFTSTOP_BOOMUP_OFF			= 0;
	public static final int DATA_STATE_SOFTSTOP_BOOMUP_ON			= 1;
	
	public static final int DATA_STATE_SOFTSTOP_BOOMDOWN_OFF		= 0;
	public static final int DATA_STATE_SOFTSTOP_BOOMDOWN_ON			= 1;
	
	public static final int DATA_STATE_SOFTSTOP_BUCKETIN_OFF		= 0;
	public static final int DATA_STATE_SOFTSTOP_BUCKETIN_ON			= 1;
	
	public static final int DATA_STATE_SOFTSTOP_BUCKETOUT_OFF		= 0;
	public static final int DATA_STATE_SOFTSTOP_BUCKETOUT_ON		= 1;
	
	public static final int DATA_STATE_REVERSEFAN_OFF				= 0;
	public static final int DATA_STATE_REVERSEFAN_MANUAL			= 1;
	public static final int DATA_STATE_REVERSEFAN_AUTO				= 2;
	
	public static final int DATA_STATE_WIPER_OFF					= 0;
	public static final int DATA_STATE_WIPER_ON						= 1;
	
	public static final int DATA_STATE_WIPER_LEVEL1					= 1;
	public static final int DATA_STATE_WIPER_LEVEL2					= 2;
	public static final int DATA_STATE_WIPER_LEVEL3					= 3;
	public static final int DATA_STATE_WIPER_LEVEL4					= 4;
	
	public static final int DATA_STATE_AUTOSHUTDOWN_OFF				= 0;
	public static final int DATA_STATE_AUTOSHUTDOWN_ON				= 1;
	
	public static final int DATA_STATE_AUTOSHUTDOWN_ON_ONCE			= 0;
	public static final int DATA_STATE_AUTOSHUTDOWN_ON_ALWAYS		= 1;
	
	public static final int DATA_STATE_CAMERA_REVERSE_OFF			= 0;
	public static final int DATA_STATE_CAMERA_REVERSE_ON			= 1;
	
	public static final int DATA_STATE_JOYSTICKPOSITION_NEUTRAL		= 0;
	public static final int DATA_STATE_JOYSTICKPOSITION_UP			= 1;
	public static final int DATA_STATE_JOYSTICKPOSITION_DOWN		= 2;
			
	public static final int DATA_STATE_JOYSTICKPOSITION_MAX			= 1000;
	public static final int DATA_STATE_EPPRCURRENT_MAX				= 950;
	
	public static final int DATA_STATE_TRIPDATA_RESET_TIME			= 0;
	public static final int DATA_STATE_TRIPDATA_RESET_DISTANCE		= 1;
	
	public static final int DATA_STATE_PASSWORD_IDENTIFICATION_REQUEST	=	0x0;
	public static final int DATA_STATE_ESL_MODE_SETTING					=	0x1;
	public static final int DATA_STATE_ESL_INTERVAL_SETTING				=	0x2;
	public static final int DATA_STATE_ESL_MODE_INTERVAL_SETTING		=	0x3;
	public static final int DATA_STATE_TEMPORARY_PASSWORD_IDENTIFICATION_REQUEST	=	0x8;
	public static final int DATA_STATE_PASSWORD_CHANGE_COMMAND			=	0xA;
	public static final int DATA_STATE_LOGIN							=	0xD;
	
	public static final int DATA_STATE_PW_CHANGE_NOT_CHANGE 			= 0;
	public static final int DATA_STATE_PW_CHANGE_CHANGE_OK 				= 1;
	
	public static final int DATA_STATE_PW_CHANGE_OLD 					= 0;
	public static final int DATA_STATE_PW_CHANGE_NEW 					= 1;
	public static final int DATA_STATE_PW_CHANGE_CONFIRM 				= 2;
	
	public static final int DATA_STATE_ESL_MODE_DISABLE				= 0;
	public static final int DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS	= 1;
	public static final int DATA_STATE_ESL_MODE_ENABLE_INTERVAL		= 2;
	
	public static final int DATA_STATE_ESL_INTERVAL_5MIN			= 5;
	public static final int DATA_STATE_ESL_INTERVAL_10MIN			= 10;
	public static final int DATA_STATE_ESL_INTERVAL_20MIN			= 20;
	public static final int DATA_STATE_ESL_INTERVAL_30MIN			= 30;
	
	public static final int DATA_STATE_ESL_INTERVAL_1HR				= 101;
	public static final int DATA_STATE_ESL_INTERVAL_2HR				= 102;
	public static final int DATA_STATE_ESL_INTERVAL_4HR				= 104;
	
	public static final int DATA_STATE_ESL_INTERVAL_1DAY			= 201;
	public static final int DATA_STATE_ESL_INTERVAL_2DAY			= 202;
	
	public static final int DATA_STATE_SMARTKEY_USE_OFF 			= 0;
	public static final int DATA_STATE_SMARTKEY_USE_ON 				= 1;
	
	public static final int DATA_STATE_1ST_TAG_REG_SUCCESS			= 0x01;
	public static final int DATA_STATE_2ND_TAG_REG_SUCCESS 			= 0x02;
	public static final int DATA_STATE_TAG_NO_REALIZE			 	= 0x03;
	public static final int DATA_STATE_FAIL						 	= 0x04;
	public static final int DATA_STATE_TAG_ALREADY_REG			 	= 0x05;
	public static final int DATA_STATE_TAG_ELIMINATION_SUCESS	 	= 0x06;
	public static final int DATA_STATE_3RD_TAG_REG_SUCCESS 			= 0x07;
	public static final int DATA_STATE_4TH_TAG_REG_SUCCESS 			= 0x08;
	public static final int DATA_STATE_5TH_TAG_REG_SUCCESS 			= 0x09;
	
	
	public static final int DATA_INDEX_TAG_REQ_SUCCESS	 			= 0x00;
	public static final int DATA_INDEX_TAG_REGISTRATION			 	= 0x01;
	public static final int DATA_INDEX_TAG_ELIMINATION	 			= 0x02;
	public static final int DATA_INDEX_TAG_USE_SAVE		 			= 0x03;
	public static final int DATA_INDEX_TAG_USE_LOAD		 			= 0x04;
	
	public static final int DATA_INDEX_SELECTGEAR_DIR_N 	= 0x00000;
	public static final int DATA_INDEX_SELECTGEAR_DIR_F 	= 0x00001;
	public static final int DATA_INDEX_SELECTGEAR_DIR_R 	= 0x00002;
	public static final int DATA_INDEX_SELECTGEAR_DIR_REQ_N	= 0x00003;
	
	public static final int	DATA_STATE_WEIGHT_OFFSET_SETTING_CALL		= 0;
	public static final int	DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET	= 0;
	public static final int	DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1	= 1;
	public static final int	DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2	= 2;
	public static final int	DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3	= 3;
	
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_NOTWORK					= 0;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_OK						= 1;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_CANCELOK					= 2;
	// ++, 150212 bwk
	/*
	
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING				= 11;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BUCKETFULLIN				= 12;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_BUCKETFULLIN	= 13;
	public static final int	DATA_STATE_CURRENT_WEIHGING_RESULT_BOOMLIFTING_HYDTEMPLOW	= 14;
	*/
	public static final int DATA_STATE_WEIGHTING_SYSGEM_NORMAL = 0;
	public static final int DATA_STATE_WEIGHTING_SYSGEM_ERROR = 1;
	// --, 150212 bwk
	
	public static final int	DATA_STATE_EHCUERR_STEERINGPROPRTIONALVALVE					= 0x01;
	public static final int	DATA_STATE_EHCUERR_STEERINGJOYSTICK							= 0x02;
	public static final int	DATA_STATE_EHCUERR_SAFETYCPU								= 0x04;
	public static final int	DATA_STATE_EHCUERR_JOYSTICKSTEERINGSETTING					= 0x08;
	
	public static final int	DATA_STATE_EHCUERR_STEERINGJOYSTICKINNOTCENTERED			= 0x01;	
	public static final int	DATA_STATE_EHCUERR_SEATSWITCHOFF							= 0x02;
	public static final int	DATA_STATE_EHCUERR_ARMRESTSWITCHOFF							= 0x04;
	public static final int	DATA_STATE_EHCUERR_ENGINEISNOTSTARTED						= 0x08;
	public static final int	DATA_STATE_EHCUERR_MACHINEISNOTSTATIONARY					= 0x10;
	public static final int	DATA_STATE_EHCUERR_TCUPENDING								= 0x20;
	
	public static final int COMMAND_MAINTENANCE_ITEM_LIST_REQUEST						= 0;
	public static final int MAINTETNANCE_INFORMATION_REQUEST							= 1;
	public static final int REPLACEMENT_CONFIRMAION										= 2;
	public static final int MAINTENANCE_INTERVAL_CHANGE									= 3;
	public static final int MAINTENANCE_HISTORY_REQUEST									= 4;
	public static final int MAINTENANCE_ITEM_LIST_INFORMATION_REQUEST					= 5;
	public static final int MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_REQUEST					= 6;
	public static final int MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_INFORMATION_REQUEST		= 7;
	
	public static final int RESERVED 										= 0;
	public static final int ENGINE_OIL 										= 1;
	public static final int TRAVEL_REDUCTION_GEAR_CASE						= 2;
	public static final int SWING_REDUCTION_GEAR_CASE						= 3;
	public static final int HYDRAULIC_OIL									= 4;
	public static final int PILOT_LINE_FILTER_ELEMENT						= 5;
	public static final int HYDRAULIC_TANK_DRAIN_FILTER_CARTRIDGE			= 6;
	public static final int HYDRAULIC_OIL_RETURN_FILTER						= 7;
	public static final int ENGINE_OIL_FILTER								= 8;
	public static final int FUEL_FILTER_ELEMENT								= 9;
	public static final int PRE_FILTER										= 10;
	public static final int HYDRAULIC_TANK_AIR_BREATHER_ELEMENT				= 11;
	public static final int AIR_CLEANER_ELEMENT								= 12;
	public static final int RADIATOR_COOLANT								= 13;
	public static final int SWING_BEARING_GEAR_PINION_GREASE				= 14;
	public static final int TRANSMISSION_OIL								= 15;
	public static final int TRANSMISSION_OIL_FILTER							= 16;
	public static final int FRONT_AXLE_DIFFERENTIAL_GEAR_OIL				= 17;
	public static final int REAR_AXLE_DIFFERENTIAL_GEAR_OIL					= 18;
	public static final int AXLE_PLANETARY_GEAR_OIL							= 19;
	public static final int BRAKE_COOLING_OIL								= 20;
	public static final int HYDRAULIC_OIL_STRAINER							= 21;
	public static final int CRANKCASE_VENTILATION_FILTER					= 22;
	public static final int AIR_CON_HEATER_OUTER_FILTER						= 23;
	
	public static final int SEND_COMMAND_FLAG_INFO							= 0;
	public static final int SEND_COMMAND_FLAG_ALRAM							= 1;
	public static final int SEND_COMMAND_FLAG_HISTORY						= 1;
	
	public static final int LENGTH_COMPONENTBASICINFORMATION	= 37;
	public static final int LENGTH_SOFTWAREIDENTIFICATION_ECM	= 48;
	public static final int LENGTH_SOFTWAREIDENTIFICATION_TCU	= 67;
	public static final int LENGTH_SOFTWAREIDENTIFICATION_ACU	= 6;
	///////////////////////////////////////////////////////////////
	////////////////Camera Status//////////////////////////////////
	public static final int STATE_CAMERA_OFF 			= 0;
	public static final int STATE_CAMERA_MANUAL 		= 1;
	public static final int STATE_CAMERA_REVERSEGEAR 	= 2;
	public int CameraOnFlag = STATE_CAMERA_OFF;
	///////////////////////////////////////////////////////////////
	////////////////////Shutdown State/////////////////////////////
	public static final int SHUTDOWN_MODE_OFF				= 0x00;
	public static final int SHUTDOWN_MODE_DELAY				= 0x01;
	public static final int SHUTDOWN_MODE_AUTO				= 0x02;
	
	public static final int DELAYSHUTDOWN_MODE_OFF			= 0x00;
	public static final int DELAYSHUTDOWN_MODE_ON			= 0x01;
	public static final int DELAYSHUTDOWN_MODE_CONTINUOUS	= 0x02;
	
	public static final int AUTOSHUTDOWN_MODE_OFF			= 0x00;
	public static final int AUTOSHUTDOWN_MODE_ON			= 0x01;
	public static final int AUTOSHUTDOWN_MODE_CONTINUOUS	= 0x02;
	///////////////////////////////////////////////////////////////
	////////////////////Buzzer State/////////////////////////////
	public static final int BUZZER_OFF				= 0x00;
	public static final int BUZZER_ON				= 0x01;
	public static final int BUZZER_STOP				= 0x02;
	///////////////////////////////////////////////////////////////
	/////////////////Buzzer Status/////////////////////////////////
	public int BuzzerStatus = 0;
	///////////////////////////////////////////////////////////////
	// ++, 150326 bwk
	/////////////////Camera Status/////////////////////////////////
	public boolean CameraCurrentOnOff = false;	// ++, --, 150326 cjg
	///////////////////////////////////////////////////////////////	
	// --, 150326 bwk
	public static CAN1CommManager getInstance(){
		Log.v(TAG,"getInstance1");
		return instance;
	}
	protected static CAN1CommManager getInstance(CommService service){
		Log.v(TAG,"getInstance2");
		if(instance == null){
			instance = new CAN1CommManager(service);
		}
		if(Datainstance == null){
			Datainstance = new ShareDataClass();
		}
		return instance;
	}
	private CAN1CommManager(CommService _service){
		Log.v(TAG,"CAN1CommManager");
		this.service = _service;
	}
	public static ShareDataClass getDataInstance(){
		return Datainstance;
	}
	///////////////////////////////////////////////////////////
	
	//////////////////////Register Callback////////////////////
	public boolean unregisterCallback(ICAN1CommManagerCallback callback) throws RemoteException{
		boolean flag = false;
		
		if(callback != null){
			flag = callbacks.unregister(callback);
		}
		return flag;
	}
	
	public boolean registerCallback(ICAN1CommManagerCallback callback) throws RemoteException{
		boolean flag = false;
		
		if(callback != null){
			flag = callbacks.register(callback);
		}
		return flag;
	}
	

	
	public void SetScreenTopFlag(boolean flag){
		service.SetScreenTopFlag(flag);
	}
	public boolean GetScreenTopFlag(){
		return service.GetScreenTopFlag();
	}
	public void SetFNFlag(boolean flag){
		service.SetFNFlag(flag);
	}
	public boolean GetFNFlag(){
		return service.GetFNFlag();
	}

	// ++, 150211 bwk
	public void SetPlayerFlag(boolean flag){
		service.SetPlayerFlag(flag);
	}
	public boolean GetPlayerFlag(){
		return service.GetPlayerFlag();
	}
	public boolean GetrpmFlag(){
		return service.GetrpmFlag();
	}
	
	public void ChangeMediatoHome(){
		service.ChangeMediatoHome();
	}
	public void ChangeHometoMedia(){
		service.ChangeHometoMedia();
	}
	
	public void StartEnding(){
		service.BackKeyEvent();
		//Callback_KeyButton(POWER_OFF);
	}
	// --, 150211 bwk
	
	// ++, 150319 bwk
	public void ClickFN(){
		service.ClickFN();
	}
	// --, 150319 bwk
	
	// ++, 150323 bwk
	public void SetMultimediaFlag(boolean flag){
		service.SetMultimediaFlag(flag);
	}
	public void SetMiracastFlag(boolean flag){
		service.SetMiracastFlag(flag);
	}	
	
	public void setVolume(int _Volume){
		service.setVolume(_Volume);
	}
	// --, 150323 bwk
	///////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////
	public void OpenComport(){
		service.InitComport();
	}
	public void CloseComport(){
		service.CloseComport();
	}
	//////////////////////////////////////////////////////////
	
	/////////// Main Popup ///////////////////////////////////
	public void SetReqPopup(int Req){
		ReqPopup = Req;
	}
	public int GetReqPopup(){
		return ReqPopup;
	}
	//////////////////////////////////////////////////////////
	
	public int LineOutfromJNI(int spk){
		return service.LineOutfromJNI_Native(spk);
	}
	
	public int[] Get_TcuErr(){
		return service.Get_TcuErr_FromNative();
	}
	public int[] Get_EcuErr(){
		return service.Get_EcuErr_FromNative();
	}
	public int[] Get_McuErr(){
		return service.Get_McuErr_FromNative();
	}
	
	public int[] Get_TcuErr_Logged(){
		return service.Get_TcuErr_Logged_FromNative();
	}
	public int[] Get_EcuErr_Logged(){
		return service.Get_EcuErr_Logged_FromNative();
	}
	public int[] Get_EHCUErr_Logged(){
		return service.Get_EHCUErr_Logged_FromNative();
	}
	public int[] Get_McuErr_Logged(){
		return service.Get_McuErr_Logged_FromNative();
	}
	
	public int Get_EHCUSingleOrMulti(){
		return service.Get_EHCUSingleOrMulti_FromNative();
	}
	public int Get_EHCUTotalError(){
		return service.Get_EHCUTotalError_FromNative();
	}
	public int Get_EHCUTotalPacket(){
		return service.Get_EHCUTotalPacket_FromNative();
	}
	public int Get_EHCUSingleErrorData(){
		return service.Get_EHCUSingleErrorData_FromNative();
	}
	public int Get_EHCUErrorData(int Data){
		return service.Get_EHCUErrorData_FromNative(Data);
	}

	public int Get_RecvESL_Flag() {
		return service.Get_RecvESL_Flag_FromNative();
	}

	public int Get_RecvSMK_Flag() {
		return service.Get_RecvSMK_Flag_FromNative();
	}
	
	public int Get_SmkAuthResult() {
		return service.Get_SmkAuthResult_FromNative();
	}

	public int Get_SmkMsgResult() {
		return service.Get_SmkMsgResult_FromNative();
	}

	public int Get_SmkRegTagCount() {
		return service.Get_SmkRegTagCount_FromNative();
	}
	public int Get_RTColock_Year(){
		return service.Get_RTColock_Year();
	}
	public int Get_RTColock_Month(){
		return service.Get_RTColock_Month();
	}
	public int Get_RTColock_Date(){
		return service.Get_RTColock_Date();
	}
	public int Get_RTColock_Sec(){
		return service.Get_RTColock_Sec();
	}
	public int Get_RTColock_Hour(){
		return service.Get_RTColock_Hour_FromNative();
	}
	public int Get_RTColock_Min(){
		return service.Get_RTColock_Min_FromNative();
	}
	public int GET_Buzzer(){
		return service.GET_Buzzer_FromNative();
	}
	
	
	
	//////////////// Sharing Data ////////////////////////////
	public static class ShareDataClass{
		
		public static final int STATE_HOURMETER_TOTAL	= 0;
		public static final int STATE_HOURMETER_LATEST	= 1;
		public static final int STATE_ODOMETER_TOTAL	= 2;
		public static final int STATE_ODOMETER_LATEST	= 3;
		
		public static final int STATE_INFO_HYD			= 0;
		public static final int STATE_INFO_BATT			= 1;
		public static final int STATE_INFO_COOL			= 2;
		public static final int STATE_INFO_OIL			= 3;
		public static final int STATE_INFO_BUCKET		= 4;
		public static final int STATE_INFO_NOSELECT		= 0xFF;
		
		public int HourmeterFlag;
		public int InfoUpperFlag;
		public int InfoLowerFlag;
		
		public int MirrorHeat;
		public int MirrorHeatPeriod;
		public int MirrorHeatTime;
		
		public int KeyState;
		
		public ShareDataClass(){
			HourmeterFlag = STATE_HOURMETER_TOTAL;
			InfoUpperFlag = STATE_INFO_NOSELECT;
			InfoLowerFlag = STATE_INFO_NOSELECT;
			
			MirrorHeat = 0;		// 0 : off, 1 : on, 2 : auto
			MirrorHeatPeriod = 10;	// 10 ~ 30
			MirrorHeatTime = 1;		// 1 ~ 5;
			KeyState = 0xFFFF;
		} 
	}
	//////////////////////////////////////////////////////////
	public int TxUpdate(byte[] Data, int size){
		return service.Write_UART3(Data, size);
	}

	public int TxCANToMCU(int PS) {
		// TODO Auto-generated method stub
		Log.v(TAG,"TxCommandToMCU");
		return service.UART1_TxComm(PS);
	}

	public int TxCMDToMCU(int CMD, int DAT1, int DAT2, int DAT3, int DAT4, int DAT5, int DAT6, int DAT7, int DAT8) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, DAT3, DAT4, DAT5, DAT6, DAT7, DAT8);
	}
	public int TxCMDToMCU(int CMD, int DAT1, int DAT2, int DAT3, int DAT4, int DAT5, int DAT6, int DAT7) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, DAT3, DAT4, DAT5, DAT6, DAT7, 0xFF);
	}
	public int TxCMDToMCU(int CMD, int DAT1, int DAT2, int DAT3, int DAT4, int DAT5, int DAT6) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, DAT3, DAT4, DAT5, DAT6, 0xFF, 0xFF);
	}
	public int TxCMDToMCU(int CMD, int DAT1, int DAT2, int DAT3, int DAT4, int DAT5) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, DAT3, DAT4, DAT5, 0xFF, 0xFF, 0xFF);
	}
	public int TxCMDToMCU(int CMD, int DAT1, int DAT2, int DAT3, int DAT4) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, DAT3, DAT4, 0xFF, 0xFF, 0xFF, 0xFF);
	}
	public int TxCMDToMCU(int CMD, int DAT1, int DAT2, int DAT3) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, DAT3, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF);
	}
	public int TxCMDToMCU(int CMD, int DAT1, int DAT2) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, DAT1, DAT2, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF);
	}
	public int TxCMDToMCU(int CMD, int DAT1) {
		// TODO Auto-generated method stub
		if(CMD == CMD_BUZ){
			BuzzerStatus = DAT1;
		}
		return service.UART3_TxComm(CMD, DAT1, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF);
	}
	public int TxCMDToMCU(int CMD) {
		// TODO Auto-generated method stub
		return service.UART3_TxComm(CMD, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF);
	}
	///////////////////////////////////////////////////////////
	
	
	
	
	/////////////////////////NEW CAN2/////////////////////////
	public int UART1_Tx(int PF, int PS, int Flag){ return service.UART1_Tx(PF, PS, Flag);};
	//////RX_DTC_INFORMATION_REQUEST_61184_11///////
//	public int Get_MessageType_PGN61184_11(){ return service.Get_MessageType_PGN61184_11();}
//	public int Get_DTCInformationRequest_1515_PGN61184_11(){ return service.Get_DTCInformationRequest_1515_PGN61184_11();}
//	public int Get_DTCType_1510_PGN61184_11(){ return service.Get_DTCType_1510_PGN61184_11();}
//	public int Get_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(){ return service.Get_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11();}
	//////RX_MAINTENANCE_REQUSET_61184_12///////
//	public int Get_MessageType_PGN61184_12(){ return service.Get_MessageType_PGN61184_12();}
//	public int Get_MaintenanceCommant_1097_PGN61184_12(){ return service.Get_MaintenanceCommant_1097_PGN61184_12();}
	public int Get_MaintenanceItem_1098_PGN61184_12(){ return service.Get_MaintenanceItem_1098_PGN61184_12();}
//	public int Get_MaintenanceInterval_1091_PGN61184_12(){ return service.Get_MaintenanceInterval_1091_PGN61184_12();}
	//////RX_MAINTENANCE_ITEM_LIST_61184_13///////
	public int Get_MessageType_PGN61184_13(){ return service.Get_MessageType_PGN61184_13();}
	public int Get_TotalNumberofMaintenanceItems_1100_PGN61184_13(){ return service.Get_TotalNumberofMaintenanceItems_1100_PGN61184_13();}
	public byte[] Get_MaintenanceItem_1098_PGN61184_13(){ return service.Get_MaintenanceItem_1098_PGN61184_13();}
	//////RX_MAINTENANCE_INFORMATION_61184_14///////
	public int Get_MessageType_PGN61184_14(){ return service.Get_MessageType_PGN61184_14();}
	public int Get_MaintenanceItem_1098_PGN61184_14(){ return service.Get_MaintenanceItem_1098_PGN61184_14();}
	public int Get_MaintenanceAlarmLamp_1099_PGN61184_14(){ return service.Get_MaintenanceAlarmLamp_1099_PGN61184_14();}
	public int Get_MaintenanceEvent_PGN61184_14(){ return service.Get_MaintenanceEvent_PGN61184_14();}
	public int Get_MaintenanceInterval_1091_PGN61184_14(){ return service.Get_MaintenanceInterval_1091_PGN61184_14();}
	public int Get_MaintenanceTotalCount_1092_PGN61184_14(){ return service.Get_MaintenanceTotalCount_1092_PGN61184_14();}
	public int Get_HourmeterattheLastMaintenance_1093_PGN61184_14(){ return service.Get_HourmeterattheLastMaintenance_1093_PGN61184_14();}
	//////RX_MAINTENANCE_HISTORY_61184_15///////
	public int Get_MessageType_PGN61184_15(){ return service.Get_MessageType_PGN61184_15();}
	public int Get_MaintenanceItem_1098_PGN61184_15(){ return service.Get_MaintenanceItem_1098_PGN61184_15();}
	public int Get_HourmeterattheLastMaintenance_1093_PGN61184_15(){ return service.Get_HourmeterattheLastMaintenance_1093_PGN61184_15();}
	public short[] Get_MaintenanceHistory_1094_PGN61184_15(){ return service.Get_MaintenanceHistory_1094_PGN61184_15();}
	//////RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
	public int Get_MessageType_PGN61184_16(){ return service.Get_MessageType_PGN61184_16();}
	public int Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16(){ return service.Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16();}
	public byte[] Get_MaintenanceItem_PGN61184_16(){ return service.Get_MaintenanceItem_PGN61184_16();}
	//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
//	public int Get_MessageType_PGN61184_21(){ return service.Get_MessageType_PGN61184_21();}
	//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
	public int Get_MessageType_PGN61184_22(){ return service.Get_MessageType_PGN61184_22();}
	public byte[] Get_HCEAntiTheftRandomNumber_1632_PGN61184_22(){ return service.Get_HCEAntiTheftRandomNumber_1632_PGN61184_22();}
	public int Get_RecvSeedFlag_PGN61184_22(){ return service.Get_RecvSeedFlag_PGN61184_22();}
	//////RX_HCE_ANTI_THEFT_REQUEST_61184_23///////
//	public int Get_MessageType_PGN61184_23(){ return service.Get_MessageType_PGN61184_23();}
//	public int Get_HCEAntiTheftCommand_1633_PGN61184_23(){ return service.Get_HCEAntiTheftCommand_1633_PGN61184_23();}
//	public int Get_ESLMode_820_PGN61184_23(){ return service.Get_ESLMode_820_PGN61184_23();}
//	public int Get_ESLInterval_825_PGN61184_23(){ return service.Get_ESLInterval_825_PGN61184_23();}
//	public byte[] Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(){ return service.Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23();}
	//////RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
	public int Get_MessageType_PGN61184_24(){ return service.Get_MessageType_PGN61184_24();}
	public int Get_PasswordCertificationResult_956_PGN61184_24(){ return service.Get_PasswordCertificationResult_956_PGN61184_24();}
	public int Get_RecvPasswordResultFlag_PGN61184_24(){ return service.Get_RecvPasswordResultFlag_PGN61184_24();}
	//////RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
	public int Get_MessageType_PGN61184_25(){ return service.Get_MessageType_PGN61184_25();}
	public int Get_PasswordChangeResult_958_PGN61184_25(){ return service.Get_PasswordChangeResult_958_PGN61184_25();}
	public byte[] Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25(){ return service.Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25();}
	public int Get_RecvPasswordChangeResultFlag_PGN61184_25(){ return service.Get_RecvPasswordChangeResultFlag_PGN61184_25();}
	//////RX_COOLING_FAN_SETTING_61184_61///////
//	public int Get_MessageType_PGN61184_61(){ return service.Get_MessageType_PGN61184_61();}
//	public int Get_TestMode_PGN61184_61(){ return service.Get_TestMode_PGN61184_61();}
//	public int Get_CoolingFanReverseMode_182_PGN61184_61(){ return service.Get_CoolingFanReverseMode_182_PGN61184_61();}
//	public int Get_CoolingFanValveCurrent_146_PGN61184_61(){ return service.Get_CoolingFanValveCurrent_146_PGN61184_61();}
//	public int Get_CoolingFanReverseIntervalTime_211_PGN61184_61(){ return service.Get_CoolingFanReverseIntervalTime_211_PGN61184_61();}
//	public int Get_CoolingFanReverseOperatingTime_212_PGN61184_61(){ return service.Get_CoolingFanReverseOperatingTime_212_PGN61184_61();}
	//////RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
//	public int Get_MessageType_PGN61184_62(){ return service.Get_MessageType_PGN61184_62();}
//	public int Get_RequestReweighing_PGN61184_62(){ return service.Get_RequestReweighing_PGN61184_62();}
//	public int Get_RequestTotalWorkWeightReset_PGN61184_62(){ return service.Get_RequestTotalWorkWeightReset_PGN61184_62();}
//	public int Get_WeightOffsetSelection_PGN61184_62(){ return service.Get_WeightOffsetSelection_PGN61184_62();}
//	public int Get_WeightOffsetSetting_PGN61184_62(){ return service.Get_WeightOffsetSetting_PGN61184_62();}
//	public int Get_WeightOffset_1922_PGN61184_62(){ return service.Get_WeightOffset_1922_PGN61184_62();}
//	public int Get_WeighingDisplayMode1_1910_PGN61184_62(){ return service.Get_WeighingDisplayMode1_1910_PGN61184_62();}
	//////RX_WEIGHT_OFFSET_61184_63///////
	public int Get_MessageType_PGN61184_63(){ return service.Get_MessageType_PGN61184_63();}
	public int Get_WeightOffsetSelectionStatus_PGN61184_63(){ return service.Get_WeightOffsetSelectionStatus_PGN61184_63();}
	public int Get_WeightOffsetWorkTool1_1922_PGN61184_63(){ return service.Get_WeightOffsetWorkTool1_1922_PGN61184_63();}
	public int Get_WeightOffsetWorkTool2_1922_PGN61184_63(){ return service.Get_WeightOffsetWorkTool2_1922_PGN61184_63();}
	public int Get_WeightOffsetWorkTool3_1922_PGN61184_63(){ return service.Get_WeightOffsetWorkTool3_1922_PGN61184_63();}
	//////PARALLEL_LIFT_OPERATION_STATUS_61184_65///////
	public int Get_MessageType_PGN61184_65(){ return service.Get_MessageType_PGN61184_65();}
	public int Get_ParallelLiftTargetAngleSettingErrorStatus_1924_PGN61184_65(){ return service.Get_ParallelLiftTargetAngleSettingErrorStatus_1924_PGN61184_65();}
	public int Get_ParallelLiftInhibitedStatus_1925_PGN61184_65(){ return service.Get_ParallelLiftInhibitedStatus_1925_PGN61184_65();}
	public int Get_ParallelLiftOverloadStatus_1926_PGN61184_65(){ return service.Get_ParallelLiftOverloadStatus_1926_PGN61184_65();}
	//////RX_MACHINE_MODE_SETTING_61184_101///////
//	public int Get_MessageType_PGN61184_101(){ return service.Get_MessageType_PGN61184_101();}
//	public int Get_EngineAlternateLowIdleSwitch_348_PGN61184_101(){ return service.Get_EngineAlternateLowIdleSwitch_348_PGN61184_101();}
//	public int Get_EnginePowerMode_347_PGN61184_101(){ return service.Get_EnginePowerMode_347_PGN61184_101();}
	//////RX_TRAVEL_MODE_SETTING_61184_104///////
//	public int Get_MessageType_PGN61184_104(){ return service.Get_MessageType_PGN61184_104();}
//	public int Get_TransmisstionShiftMode_543_PGN61184_104(){ return service.Get_TransmisstionShiftMode_543_PGN61184_104();}
//	public int Get_ClutchCutoffMode_544_PGN61184_104(){ return service.Get_ClutchCutoffMode_544_PGN61184_104();}
//	public int Get_KickDownShiftMode_547_PGN61184_104(){ return service.Get_KickDownShiftMode_547_PGN61184_104();}
//	public int Get_TransmissionTCLockupEngaged_568_PGN61184_104(){ return service.Get_TransmissionTCLockupEngaged_568_PGN61184_104();}
//	public int Get_VehicleSpeedLimitMode_575_PGN61184_104(){ return service.Get_VehicleSpeedLimitMode_575_PGN61184_104();}
	//////RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
//	public int Get_MessageType_PGN61184_105(){ return service.Get_MessageType_PGN61184_105();}
//	public int Get_SettingSelection_PGN61184_105(){ return service.Get_SettingSelection_PGN61184_105();}
//	public int Get_SpeedometerFrequency_534_PGN61184_105(){ return service.Get_SpeedometerFrequency_534_PGN61184_105();}
//	public int Get_AutoRideControlOperationSpeedForward_574_PGN61184_105(){ return service.Get_AutoRideControlOperationSpeedForward_PGN61184_105();}
//	public int Get_AutoRideControlOperationSpeedBackward_576_PGN61184_105(){ return service.Get_AutoRideControlOperationSpeedBackward_576_PGN61184_105();}
//	public int Get_VehicleSpeedLimit_572_PGN61184_105(){ return service.Get_VehicleSpeedLimit_572_PGN61184_105();}
	//////RX_TRAVEL_CONTROL_VALUE_61184_106///////
	public int Get_MessageType_PGN61184_106(){ return service.Get_MessageType_PGN61184_106();}
	public int Get_SpeedometerFrequency_534_PGN61184_106(){ return service.Get_SpeedometerFrequency_534_PGN61184_106();}
	public int Get_AutoRideControlOperationSpeedForward_574_PGN61184_106(){ return service.Get_AutoRideControlOperationSpeedForward_574_PGN61184_106();}
	public int Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106(){ return service.Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106();}
	public int Get_VehicleSpeedLimit_572_PGN61184_106(){ return service.Get_VehicleSpeedLimit_572_PGN61184_106();}
	//////RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
//	public int Get_MessageType_PGN61184_109(){ return service.Get_MessageType_PGN61184_109();}
//	public int Get_BacklightIlluminationLevel_719_PGN61184_109(){ return service.Get_BacklightIlluminationLevel_719_PGN61184_109();}
//	public int Get_WiperSpeedLevel_718_PGN61184_109(){ return service.Get_WiperSpeedLevel_718_PGN61184_109();}
//	public int Get_Clock_819_PGN61184_109(){ return service.Get_Clock_819_PGN61184_109();}
//	public int Get_RequestEngineLowIdleSpeed_PGN61184_109(){ return service.Get_RequestEngineLowIdleSpeed_PGN61184_109();}
//	public int Get_RequestTripDataReset_PGN61184_109(){ return service.Get_RequestTripDataReset_PGN61184_109();}
	//////RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
//	public int Get_MessageType_PGN61184_121(){ return service.Get_MessageType_PGN61184_121();}
//	public int Get_AutomaticEngineShutdown_363_PGN61184_121(){ return service.Get_AutomaticEngineShutdown_363_PGN61184_121();}
//	public int Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(){ return service.Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121();}
//	public int Get_EngineShutdownCotrolByte_PGN61184_121(){ return service.Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121();}
//	public int Get_DelayedEngineShutdown_365_PGN61184_121(){ return service.Get_DelayedEngineShutdown_365_PGN61184_121();}
//	public int Get_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121(){ return service.Get_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121();}
	//////RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
	public int Get_MessageType_PGN61184_122(){ return service.Get_MessageType_PGN61184_122();}
	public int Get_AutomaticEngineShutdown_363_PGN61184_122(){ return service.Get_AutomaticEngineShutdown_363_PGN61184_122();}
	public int Get_AutomaticEngineShutdownType_PGN61184_122(){ return service.Get_AutomaticEngineShutdownType_PGN61184_122();}
	public int Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122(){ return service.Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122();}
	public int Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122(){ return service.Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122();}
	public int Get_SettingTimeforDelayedEngineShutdown_366_PGN61184_122(){ return service.Get_SettingTimeforDelayedEngineShutdown_366_PGN61184_122();}
	public int Get_RemainingTimeforDelayedEngineShutdown_PGN61184_122(){ return service.Get_RemainingTimeforDelayedEngineShutdown_PGN61184_122();}
	//////RX_DETENT_MODE_SETTING_61184_123///////
//	public int Get_MessageType_PGN61184_123(){ return service.Get_MessageType_PGN61184_123();}
//	public int Get_BoomDetentMode_223_PGN61184_123(){ return service.Get_BoomDetentMode_223_PGN61184_123();}
//	public int Get_BucketDetentMode_224_PGN61184_123(){ return service.Get_BucketDetentMode_224_PGN61184_123();}
//	public int Get_RequestDetentReleasePositionSetting_PGN61184_123(){ return service.Get_RequestDetentReleasePositionSetting_PGN61184_123();}
	//////RX_DETENT_MODE_STATUS_61184_124///////
	public int Get_MessageType_PGN61184_124(){ return service.Get_MessageType_PGN61184_124();}
	public int Get_BoomDetentMode_223_PGN61184_124(){ return service.Get_BoomDetentMode_223_PGN61184_124();}
	public int Get_BucketDetentMode_224_PGN61184_124(){ return service.Get_BucketDetentMode_224_PGN61184_124();}
	public int Get_DetentReleasePositionSettingStatus_PGN61184_124(){ return service.Get_DetentReleasePositionSettingStatus_PGN61184_124();}
	//////RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
	public int Get_MessageType_PGN61184_129(){ return service.Get_MessageType_PGN61184_129();}
	public int Get_EngineShutdownCommand_3472_PGN61184_129(){ return service.Get_EngineShutdownCommand_3472_PGN61184_129();}
	//////RX_AS_PHONE_NUMBER_SETTING_61184_151///////
	public int Get_MessageType_PGN61184_151(){ return service.Get_MessageType_PGN61184_151();}
	public byte[] Get_ASPhoneNumber_PGN61184_151(){ return service.Get_ASPhoneNumber_PGN61184_151();}
	//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
//	public int Get_MessageType_PGN61184_201(){ return service.Get_MessageType_PGN61184_201();}
//	public int Get_RequestBoomPressureCalibration_PGN61184_201(){ return service.Get_RequestBoomPressureCalibration_PGN61184_201();}
//	public int Get_RequestBoomBucketAngleSensorCalibration_PGN61184_201(){ return service.Get_RequestBoomBucketAngleSensorCalibration_PGN61184_201();}
	public int Get_RequestAEB_PGN61184_201(){ return service.Get_RequestAEB_PGN61184_201();}
	public int Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201(){ return service.Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201();}
	//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
	public int Get_MessageType_PGN61184_202(){ return service.Get_MessageType_PGN61184_202();}
	public int Get_BoomPressureCalibrationStatus_1908_PGN61184_202(){ return service.Get_BoomPressureCalibrationStatus_1908_PGN61184_202();}
	public int Get_AngleSensorCalibrationStatus_1909_PGN61184_202(){ return service.Get_AngleSensorCalibrationStatus_1909_PGN61184_202();}
	public int Get_AEBStatusInformation_MainCode_562_PGN61184_202(){ return service.Get_AEBStatusInformation_MainCode_562_PGN61184_202();}
	public int Get_AEBCycleNumber_540_PGN61184_202(){ return service.Get_AEBCycleNumber_540_PGN61184_202();}
	public int Get_AEBStatusInformation_SubCode_563_PGN61184_202(){ return service.Get_AEBStatusInformation_SubCode_563_PGN61184_202();}
	public int Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202(){ return service.Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202();}
	public int Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202(){ return service.Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202();}
	//////RX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
//	public int Get_MessageType_PGN61184_203(){ return service.Get_MessageType_PGN61184_203();}
//	public int Get_BucketPriorityOperation_2301_PGN61184_203(){ return service.Get_BucketPriorityOperation_2301_PGN61184_203();}
//	public int Get_FlowFineModulationOperation_2302_PGN61184_203(){ return service.Get_FlowFineModulationOperation_2302_PGN61184_203();}
//	public int Get_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(){ return service.Get_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203();}
//	public int Get_BoomLeverFloatingPosition_PGN61184_203(){ return service.Get_BoomLeverFloatingPosition_PGN61184_203();}
	//////RX_MONIOTR_STATUS_65327///////
	public int Get_RequestBuzzerStop_PGN65327(){ return service.Get_RequestBuzzerStop_PGN65327();}
	public int Get_SpeedmeterUnitChange_PGN65327(){ return service.Get_SpeedmeterUnitChange_PGN65327();}
	//////RX_RMCU_STATUS_65329///////
	public int Get_RMCUNetworkType_1621_PGN65329(){ return service.Get_RMCUNetworkType_1621_PGN65329();}
	public int Get_RMCUBackupBatteryVoltage_1590_PGN65329(){ return service.Get_RMCUBackupBatteryVoltage_1590_PGN65329();}
	public int Get_RMCUPowerSource_1594_PGN65329(){ return service.Get_RMCUPowerSource_1594_PGN65329();}
	public int Get_RMCUBoxOpeningStatus_PGN65329(){ return service.Get_RMCUBoxOpeningStatus_PGN65329();}
	public int Get_NetworkCommunicationStatus1_1622_PGN65329(){ return service.Get_NetworkCommunicationStatus1_1622_PGN65329();}
	public int Get_PositionUpdateStatus_852_PGN65329(){ return service.Get_PositionUpdateStatus_852_PGN65329();}
	public int Get_MachinePositionStatus_1593_PGN65329(){ return service.Get_MachinePositionStatus_1593_PGN65329();}
	public int Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329(){ return service.Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329();}
	public int Get_NetworkTransceiverStatus1_1623_PGN65329(){ return service.Get_NetworkTransceiverStatus1_1623_PGN65329();}
	public int Get_NetworkServiceStatus1_1624_PGN65329(){ return service.Get_NetworkServiceStatus1_1624_PGN65329();}
	public int Get_NetworkAntennaStatus1_1625_PGN65329(){ return service.Get_NetworkAntennaStatus1_1625_PGN65329();}
	public int Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329(){ return service.Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329();}
	//////RX_COMPONENT_IDENTIFICATION_65330///////
	public int Get_ComponentCode_1699_PGN65330(){ return service.Get_ComponentCode_1699_PGN65330();}
	public int Get_ManufacturerCode_1700_PGN65330(){ return service.Get_ManufacturerCode_1700_PGN65330();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330(){ return service.Get_ComponentBasicInformation_1698_PGN65330();}
	//////RX_COMPONENT_IDENTIFICATION_CLUSTER_65330///////
	public int Get_ComponentCode_1699_PGN65330_CLUSTER(){ return service.Get_ComponentCode_1699_PGN65330_CLUSTER();}
	public int Get_ManufacturerCode_1700_PGN65330_CLUSTER(){ return service.Get_ManufacturerCode_1700_PGN65330_CLUSTER();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_CLUSTER(){ return service.Get_ComponentBasicInformation_1698_PGN65330_CLUSTER();}
	//////RX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
	public int Get_ComponentCode_1699_PGN65330_MONITOR(){ return service.Get_ComponentCode_1699_PGN65330_MONITOR();}
	public int Get_ManufacturerCode_1700_PGN65330_MONITOR(){ return service.Get_ManufacturerCode_1700_PGN65330_MONITOR();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_MONITOR(){ return service.Get_ComponentBasicInformation_1698_PGN65330_MONITOR();}
	//////RX_COMPONENT_IDENTIFICATION_EHCU_65330///////
	public int Get_ComponentCode_1699_PGN65330_EHCU(){ return service.Get_ComponentCode_1699_PGN65330_EHCU();}
	public int Get_ManufacturerCode_1700_PGN65330_EHCU(){ return service.Get_ManufacturerCode_1700_PGN65330_EHCU();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_EHCU(){ return service.Get_ComponentBasicInformation_1698_PGN65330_EHCU();}
	//////RX_COMPONENT_IDENTIFICATION_RMCU_65330///////
	public int Get_ComponentCode_1699_PGN65330_RMCU(){ return service.Get_ComponentCode_1699_PGN65330_RMCU();}
	public int Get_ManufacturerCode_1700_PGN65330_RMCU(){ return service.Get_ManufacturerCode_1700_PGN65330_RMCU();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_RMCU(){ return service.Get_ComponentBasicInformation_1698_PGN65330_RMCU();}
	//////RX_COMPONENT_IDENTIFICATION_ECM_65330///////
	public int Get_ComponentCode_1699_PGN65330_ECM(){ return service.Get_ComponentCode_1699_PGN65330_ECM();}
	public int Get_ManufacturerCode_1700_PGN65330_ECM(){ return service.Get_ManufacturerCode_1700_PGN65330_ECM();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_ECM(){ return service.Get_ComponentBasicInformation_1698_PGN65330_ECM();}
	//////RX_COMPONENT_IDENTIFICATION_TCU_65330///////
	public int Get_ComponentCode_1699_PGN65330_TCU(){ return service.Get_ComponentCode_1699_PGN65330_TCU();}
	public int Get_ManufacturerCode_1700_PGN65330_TCU(){ return service.Get_ManufacturerCode_1700_PGN65330_TCU();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_TCU(){ return service.Get_ComponentBasicInformation_1698_PGN65330_TCU();}
	//////RX_COMPONENT_IDENTIFICATION_ACU_65330///////
	public int Get_ComponentCode_1699_PGN65330_ACU(){ return service.Get_ComponentCode_1699_PGN65330_ACU();}
	public int Get_ManufacturerCode_1700_PGN65330_ACU(){ return service.Get_ManufacturerCode_1700_PGN65330_ACU();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_ACU(){ return service.Get_ComponentBasicInformation_1698_PGN65330_ACU();}
	  //////RX_COMPONENT_IDENTIFICATION_BKCU_65330///////
	public int Get_ComponentCode_1699_PGN65330_BKCU(){ return service.Get_ComponentCode_1699_PGN65330_BKCU();}
	public int Get_ManufacturerCode_1700_PGN65330_BKCU(){ return service.Get_ManufacturerCode_1700_PGN65330_BKCU();}
	public byte[] Get_ComponentBasicInformation_1698_PGN65330_BKCU(){ return service.Get_ComponentBasicInformation_1698_PGN65330_BKCU();}
	//////RX_TRIP_TIME_INFORMATION_65344///////
	public int Get_TripTime_849_PGN65344(){ return service.Get_TripTime_849_PGN65344();}
	//////RX_MACHINE_SECURITY_STATUS_65348///////
	public int Get_ESLMode_820_PGN65348(){ return service.Get_ESLMode_820_PGN65348();}
	public int Get_LockMode_822_PGN65348(){ return service.Get_LockMode_822_PGN65348();}
	public int Get_LockLevel_823_PGN65348(){ return service.Get_LockLevel_823_PGN65348();}
	public int Get_LockDemander_824_PGN65348(){ return service.Get_LockDemander_824_PGN65348();}
	public int Get_EngineStartingPermission_821_PGN65348(){ return service.Get_EngineStartingPermission_821_PGN65348();}
	public int Get_PasswordIdentificationBlockedState_1631_PGN65348(){ return service.Get_PasswordIdentificationBlockedState_1631_PGN65348();}
	public int Get_ESLInterval_825_PGN65348(){ return service.Get_ESLInterval_825_PGN65348();}
	//////RX_MACHINE_MODE_STATUS_65350///////
	public int Get_EngineAlternateLowIdelSwitch_348_PGN65350(){ return service.Get_EngineAlternateLowIdelSwitch_348_PGN65350();}
	public int Get_EnginePowerMode_347_PGN65350(){ return service.Get_EnginePowerMode_347_PGN65350();}
	//////RX_HYDRAULIC_PRESSURE4_65354///////
	public int Get_BrakeOilPressure_503_PGN65354(){ return service.Get_BrakeOilPressure_503_PGN65354();}
	public int Get_BrakeOilChargingPriorityPressure_557_PGN65354(){ return service.Get_BrakeOilChargingPriorityPressure_557_PGN65354();}
	//////RX_HYDRAULIC_PRESSURE6_65356///////
	public int Get_BoomCylinderHeadPressure_204_PGN65356(){ return service.Get_BoomCylinderHeadPressure_204_PGN65356();}
	public int Get_BoomCylinderRodPressure_205_PGN65356(){ return service.Get_BoomCylinderRodPressure_205_PGN65356();}
	//////RX_HYDRAULIC_PRESSURE7_65357///////
	public int Get_SteeringMainPumpPressure_202_PGN65357(){ return service.Get_SteeringMainPumpPressure_202_PGN65357();}
	public int Get_EmergencySteeringPumpPressure_203_PGN65357(){ return service.Get_EmergencySteeringPumpPressure_203_PGN65357();}
	public int Get_ParkingOilPressure_507_PGN65357(){ return service.Get_ParkingOilPressure_507_PGN65357();}
	public int Get_DifferentialLockPressure_558_PGN65357(){ return service.Get_DifferentialLockPressure_558_PGN65357();}
	//////RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
	public int Get_AlternatorVoltage_707_PGN65360(){ return service.Get_AlternatorVoltage_707_PGN65360();}
	//////RX_RELAY_BUZZER_STATUS_65364///////
	public int Get_AntiRestartRelay_327_PGN65364(){ return service.Get_AntiRestartRelay_327_PGN65364();}
	public int Get_FuelWarmerRelay_325_PGN65364(){ return service.Get_FuelWarmerRelay_325_PGN65364();}
	public int Get_EnginePreheatRelay_322_PGN65364(){ return service.Get_EnginePreheatRelay_322_PGN65364();}
	public int Get_EngineStopRelay_345_PGN65364(){ return service.Get_EngineStopRelay_345_PGN65364();}
	public int Get_WorkingCutoffRelay_164_PGN65364(){ return service.Get_WorkingCutoffRelay_164_PGN65364();}
	public int Get_TravelingCutoffRelay_517_PGN65364(){ return service.Get_TravelingCutoffRelay_517_PGN65364();}
	public int Get_ParkingRelay_514_PGN65364(){ return service.Get_ParkingRelay_514_PGN65364();}
	public int Get_WiperRelay_727_PGN65364(){ return service.Get_WiperRelay_727_PGN65364();}
	public int Get_EmergencySteeringPumpRelay_187_PGN65364(){ return service.Get_EmergencySteeringPumpRelay_187_PGN65364();}
	public int Get_TravelAlarmBuzzer_722_PGN65364(){ return service.Get_TravelAlarmBuzzer_722_PGN65364();}
	public int Get_Buzzer_723_PGN65364(){ return service.Get_Buzzer_723_PGN65364();}
	public int Get_FuelCutoffRelay_324_PGN65364(){ return service.Get_FuelCutoffRelay_324_PGN65364();}
	//////RX_SOLENOID_STATUS_65365///////
	public int Get_BoomUpLeverDetentSolenoid_172_PGN65365(){ return service.Get_BoomUpLeverDetentSolenoid_172_PGN65365();}
	public int Get_BoomDownLeverDetentSolenoid_173_PGN65365(){ return service.Get_BoomDownLeverDetentSolenoid_173_PGN65365();}
	public int Get_BucketLeverDetentSolenoid_174_PGN65365(){ return service.Get_BucketLeverDetentSolenoid_174_PGN65365();}
	//////RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
	public int Get_AcceleratorPedalPositionVoltage1_710_PGN65368(){ return service.Get_AcceleratorPedalPositionVoltage1_710_PGN65368();}
	public int Get_BrakePedalPositionVoltage_573_PGN65368(){ return service.Get_BrakePedalPositionVoltage_573_PGN65368();}
	public int Get_AcceleratorPedalPosition1_339_PGN65368(){ return service.Get_AcceleratorPedalPosition1_339_PGN65368();}
	public int Get_AcceleratorPedalPositionVoltage2_710_PGN65368(){ return service.Get_AcceleratorPedalPositionVoltage2_710_PGN65368();}
	//////RX_COOLING_FAN_STATUS_65369///////
	public int Get_CoolingFanReverseMode_182_PGN65369(){ return service.Get_CoolingFanReverseMode_182_PGN65369();}
	public int Get_CoolingFanReverseSolenoid_181_PGN65369(){ return service.Get_CoolingFanReverseSolenoid_181_PGN65369();}
	public int Get_CoolingFanReverseSwitchManual_740_PGN65369(){ return service.Get_CoolingFanReverseSwitchManual_740_PGN65369();}
	public int Get_CoolingFanReverseSwitchAuto_741_PGN65369(){ return service.Get_CoolingFanReverseSwitchAuto_741_PGN65369();}
	public int Get_CoolingFanValveCurrent_146_PGN65369(){ return service.Get_CoolingFanValveCurrent_146_PGN65369();}
	public int Get_CoolingFanSpeed_318_PGN65369(){ return service.Get_CoolingFanSpeed_318_PGN65369();}
	public int Get_CoolingFanReverseIntervalTime_211_PGN65369(){ return service.Get_CoolingFanReverseIntervalTime_211_PGN65369();}
	public int Get_CoolingFanReverseOperatingTime_212_PGN65369(){ return service.Get_CoolingFanReverseOperatingTime_212_PGN65369();}
	//////RX_ENGINE_STATUS2_65370///////
	public int Get_EngineFuelRate_331_PGN65370(){ return service.Get_EngineFuelRate_331_PGN65370();}
	public int Get_EnginePercentLoadatCurrentSpeed_334_PGN65370(){ return service.Get_EnginePercentLoadatCurrentSpeed_334_PGN65370();}
	public int Get_EngineActualPercentTorque_335_PGN65370(){ return service.Get_EngineActualPercentTorque_335_PGN65370();}
	//////RX_ENGINE_STATUS1_65371///////
	public int Get_EngineOperatingCondition_336_PGN65371(){ return service.Get_EngineOperatingCondition_336_PGN65371();}
	public int Get_EngineIntakeManifold1Temperatue_329_PGN65371(){ return service.Get_EngineIntakeManifold1Temperatue_329_PGN65371();}
	public int Get_EngineFuelTemperature_330_PGN65371(){ return service.Get_EngineFuelTemperature_330_PGN65371();}
	public int Get_EngineOilPressure_311_PGN65371(){ return service.Get_EngineOilPressure_311_PGN65371();}
	public int Get_BarometricPressure_328_PGN65371(){ return service.Get_BarometricPressure_328_PGN65371();}
	public int Get_EngineAirIntakePressure_337_PGN65371(){ return service.Get_EngineAirIntakePressure_337_PGN65371();}
	public int Get_DEFTankLevel_362_PGN65371(){ return service.Get_DEFTankLevel_362_PGN65371();}
	//////RX_AIR_CONDITIONER_STATUS_65373///////	
	public int Get_FATCSettingTemperatureCelsius_3408_PGN65373(){ return service.Get_FATCSettingTemperatureCelsius_3408_PGN65373();}
	public int Get_FATCSettingTemperatureFahrenheit_3409_PGN65373(){ return service.Get_FATCSettingTemperatureFahrenheit_3409_PGN65373();}
	public int Get_Ambienttemperaturesensoropen_PGN65373(){ return service.Get_Ambienttemperaturesensoropen_PGN65373();}
	public int Get_Ambienttemperaturesensorshort_PGN65373(){ return service.Get_Ambienttemperaturesensorshort_PGN65373();}
	public int Get_Incabtemperaturesensoropen_PGN65373(){ return service.Get_Incabtemperaturesensoropen_PGN65373();}
	public int Get_Incabtemperaturesensorshort_PGN65373(){ return service.Get_Incabtemperaturesensorshort_PGN65373();}
	public int Get_Evaptemperaturesensoropen_PGN65373(){ return service.Get_Evaptemperaturesensoropen_PGN65373();}
	public int Get_Evaptemperaturesensorshort_PGN65373(){ return service.Get_Evaptemperaturesensorshort_PGN65373();}
	public int Get_Mode1actuatoropenshort_PGN65373(){ return service.Get_Mode1actuatoropenshort_PGN65373();}
	public int Get_Mode1actuatordrivecircuitmalfunction_PGN65373(){ return service.Get_Mode1actuatordrivecircuitmalfunction_PGN65373();}
	public int Get_Intakeactuatoropenshort_PGN65373(){ return service.Get_Intakeactuatoropenshort_PGN65373();}
	public int Get_Intakeactuatordrivecircuitmalfunction_PGN65373(){ return service.Get_Intakeactuatordrivecircuitmalfunction_PGN65373();}
	public int Get_Temperatureactuatoropenshort_PGN65373(){ return service.Get_Temperatureactuatoropenshort_PGN65373();}
	public int Get_Temperatureactuatordrivecircuitmalfunction_PGN65373(){ return service.Get_Temperatureactuatordrivecircuitmalfunction_PGN65373();}
	public int Get_Ducttemperaturesensoropen_PGN65373(){ return service.Get_Ducttemperaturesensoropen_PGN65373();}
	public int Get_Ducttemperaturesensorshort_PGN65373(){ return service.Get_Ducttemperaturesensorshort_PGN65373();}
	public int Get_WaterValveSensorError_PGN65373(){ return service.Get_WaterValveSensorError_PGN65373();}
	public int Get_GPSCircuitError_PGN65373(){ return service.Get_GPSCircuitError_PGN65373();}
	//////RX_VEHICLE_DISTANCE_65389///////
	public int Get_TripDistance_600_PGN65389(){ return service.Get_TripDistance_600_PGN65389();}
	public int Get_TotalVehicleDistance_601_PGN65389(){ return service.Get_TotalVehicleDistance_601_PGN65389();}
	///////RX_FUEL_INFORMATION_ECO_GAUGE_65390////////////////
	public int Get_FuelLevel_302_PGN65390(){ return service.Get_FuelLevel_302_PGN65390();}
	public int Get_EcoGaugeLevel_1304_PGN65390(){ return service.Get_EcoGaugeLevel_1304_PGN65390();}
	public int Get_EcoGaugeStatus_1305_PGN65390(){ return service.Get_EcoGaugeStatus_1305_PGN65390();}
	public int Get_AverageFuelRate_PGN65390(){ return service.Get_AverageFuelRate_PGN65390();}
	public int Get_CurrentFuelRate_PGN65390(){ return service.Get_CurrentFuelRate_PGN65390();}
	//////RX_CYLINDER_ANGLE_STROKE1_65395///////
	public int Get_BoomLinkAngle_1920_PGN65395(){ return service.Get_BoomLinkAngle_1920_PGN65395();}
	public int Get_BellCrankAngle_1921_PGN65395(){ return service.Get_BellCrankAngle_1921_PGN65395();}
	public int Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395(){ return service.Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395();}
	public int Get_BellCrankAngleSensorSignalVoltage_729_PGN65395(){ return service.Get_BellCrankAngleSensorSignalVoltage_729_PGN65395();}
	public int Get_BucketCylinderStroke_1930_PGN65395(){ return service.Get_BucketCylinderStroke_1930_PGN65395();}
	//////RX_CYLINDER_ANGLE_STROKE2_65396///////
	public int Get_BucketAngle_1931_PGN65396(){ return service.Get_BucketAngle_1931_PGN65396();}
	//////RX_AS_PHONE_NUMBER_65425///////
//	public byte[] Get_ASPhoneNumber_1095_PGN65425(){ return service.Get_ASPhoneNumber_1095_PGN65425();}
	//////RX_WARNING_LAMP_65427///////
	public int Get_FuelLevelLow_303_PGN65427(){ return service.Get_FuelLevelLow_303_PGN65427();}
	public int Get_HydraulicOilTemperatureHigh_102_PGN65427(){ return service.Get_HydraulicOilTemperatureHigh_102_PGN65427();}
	public int Get_BatteryVoltageLow_706_PGN65427(){ return service.Get_BatteryVoltageLow_706_PGN65427();}
	public int Get_Overload_104_PGN65427(){ return service.Get_Overload_104_PGN65427();}
	public int Get_AirCleanerClog_317_PGN65427(){ return service.Get_AirCleanerClog_317_PGN65427();}
	public int Get_EngineCheckLamp_320_PGN65427(){ return service.Get_EngineCheckLamp_320_PGN65427();}
	public int Get_TransmissionOilPressureLow_502_PGN65427(){ return service.Get_TransmissionOilPressureLow_502_PGN65427();}
	public int Get_BrakeOilPressureLow_504_PGN65427(){ return service.Get_BrakeOilPressureLow_504_PGN65427();}
	public int Get_EngineCoolantTemperatureHigh_305_PGN65427(){ return service.Get_EngineCoolantTemperatureHigh_305_PGN65427();}
	public int Get_EngineCoolantLevelLow_307_PGN65427(){ return service.Get_EngineCoolantLevelLow_307_PGN65427();}
	public int Get_EngineOilPressureLow_313_PGN65427(){ return service.Get_EngineOilPressureLow_313_PGN65427();}
	public int Get_EngineOilFilterClog_315_PGN65427(){ return service.Get_EngineOilFilterClog_315_PGN65427();}
	public int Get_TransmissionOilTemperatureHigh_537_PGN65427(){ return service.Get_TransmissionOilTemperatureHigh_537_PGN65427();}
	public int Get_TransmissionCheck_538_PGN65427(){ return service.Get_TransmissionCheck_538_PGN65427();}
	public int Get_SteeringMainPumpPressureLow_184_PGN65427(){ return service.Get_SteeringMainPumpPressureLow_184_PGN65427();}
	public int Get_EmergencySteeringActive_185_PGN65427(){ return service.Get_EmergencySteeringActive_185_PGN65427();}
	public int Get_WarningSymbolLamp_709_PGN65427(){ return service.Get_WarningSymbolLamp_709_PGN65427();}
	public int Get_WaterInFuelIndicator_360_PGN65427(){ return service.Get_WaterInFuelIndicator_360_PGN65427();}
	public int Get_DTCAlarmLamp_1509_PGN65427(){ return service.Get_DTCAlarmLamp_1509_PGN65427();}
	public int Get_EngineStopLamp_319_PGN65427(){ return service.Get_EngineStopLamp_319_PGN65427();}
	public int Get_DPFLampCommand_352_PGN65427(){ return service.Get_DPFLampCommand_352_PGN65427();}
	public int Get_DEFLowLamp_358_PGN65427(){ return service.Get_DEFLowLamp_358_PGN65427();}
	public int Get_ClutchSlip_569_PGN65427(){ return service.Get_ClutchSlip_569_PGN65427();}
	public int Get_BrakeOilLevelLow_566_PGN65427(){ return service.Get_BrakeOilLevelLow_566_PGN65427();}
	public int Get_BrakeOilTemperatureHigh_567_PGN65427(){ return service.Get_BrakeOilTemperatureHigh_567_PGN65427();}
	public int Get_EmissionSystemFailLamp_357_PGN65427(){ return service.Get_EmissionSystemFailLamp_357_PGN65427();}
	//////RX_INDICATOR_LAMP_65428///////
	public int Get_PowerMaxStatus_802_PGN65428(){ return service.Get_PowerMaxStatus_802_PGN65428();}
	public int Get_DecelerationStatus_803_PGN65428(){ return service.Get_DecelerationStatus_803_PGN65428();}
	public int Get_WarmingUpStatus_804_PGN65428(){ return service.Get_WarmingUpStatus_804_PGN65428();}
	public int Get_EnginePreheatStatus_323_PGN65428(){ return service.Get_EnginePreheatStatus_323_PGN65428();}
	public int Get_FuelWarmerActiveStatus_326_PGN65428(){ return service.Get_FuelWarmerActiveStatus_326_PGN65428();}
	public int Get_CoolingFandrivingStatus_180_PGN65428(){ return service.Get_CoolingFandrivingStatus_180_PGN65428();}
	public int Get_CruiseStatus_519_PGN65428(){ return service.Get_CruiseStatus_519_PGN65428();}
	public int Get_ParkingStatus_508_PGN65428(){ return service.Get_ParkingStatus_508_PGN65428();}
	public int Get_ClutchCutoffStatus_545_PGN65428(){ return service.Get_ClutchCutoffStatus_545_PGN65428();}
	public int Get_FNRJoystickActiveStatus_546_PGN65428(){ return service.Get_FNRJoystickActiveStatus_546_PGN65428();}
	public int Get_RideControlStatus_550_PGN65428(){ return service.Get_RideControlStatus_550_PGN65428();}
	public int Get_DifferentialLockStatus_559_PGN65428(){ return service.Get_DifferentialLockStatus_559_PGN65428();}
	public int Get_JoystickSteeringActiveStatus_186_PGN65428(){ return service.Get_JoystickSteeringActiveStatus_186_PGN65428();}
	public int Get_InchingStatus_549_PGN65428(){ return service.Get_InchingStatus_549_PGN65428();}
	public int Get_RamLockStatus_520_PGN65428(){ return service.Get_RamLockStatus_520_PGN65428();}
	public int Get_WorkingBrakeStatus_506_PGN65428(){ return service.Get_WorkingBrakeStatus_506_PGN65428();}
	public int Get_MaintenanceAlarmLamp_1099_PGN65428(){ return service.Get_MaintenanceAlarmLamp_1099_PGN65428();}
	public int Get_OperatorAbsenceStatus_832_PGN65428(){ return service.Get_OperatorAbsenceStatus_832_PGN65428();}
	public int Get_MirrorHeaterStatus_724_PGN65428(){ return service.Get_MirrorHeaterStatus_724_PGN65428();}
	public int Get_HighBeamStatus_725_PGN65428(){ return service.Get_HighBeamStatus_725_PGN65428();}
	public int Get_TravelAlarmSwitch_721_PGN65428(){ return service.Get_TravelAlarmSwitch_721_PGN65428();}
	public int Get_SwingBoomSwitch_726_PGN65428(){ return service.Get_SwingBoomSwitch_726_PGN65428();}
	public int Get_ParkingWorkingTravelStatus_510_PGN65428(){ return service.Get_ParkingWorkingTravelStatus_510_PGN65428();}
	public int Get_TransmissionTCLockupEngaged_556_PGN65428(){ return service.Get_TransmissionTCLockupEngaged_556_PGN65428();}
	public int Get_ExhaustSystemHighTemperatureLampCommand_353_PGN65428(){ return service.Get_ExhaustSystemHighTemperatureLampCommand_353_PGN65428();}
	public int Get_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428(){ return service.Get_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428();}
	public int Get_AutomaticEngineShutdown_363_PGN65428(){ return service.Get_AutomaticEngineShutdown_363_PGN65428();}
	public int Get_DelayedEngineShutdown_365_PGN65428(){ return service.Get_DelayedEngineShutdown_365_PGN65428();}
	public int Get_AutoGreaseGreenLEDStatus_3453_PGN65428(){ return service.Get_AutoGreaseGreenLEDStatus_3453_PGN65428();}
	public int Get_AutoGreaseRedLEDStatus_3453_PGN65428(){ return service.Get_AutoGreaseRedLEDStatus_3453_PGN65428();}
	
	//////RX_GAUGE_65431///////
	public int Get_FuelLevel_301_PGN65431(){ return service.Get_FuelLevel_301_PGN65431();}
	public int Get_EngineCoolantTemperature_304_PGN65431(){ return service.Get_EngineCoolantTemperature_304_PGN65431();}
	public int Get_HydraulicOilTemperature_101_PGN65431(){ return service.Get_HydraulicOilTemperature_101_PGN65431();}
	public int Get_TransmissionOilTemperature_536_PGN65431(){ return service.Get_TransmissionOilTemperature_536_PGN65431();}
	public int Get_EngineSpeed_310_PGN65431(){ return service.Get_EngineSpeed_310_PGN65431();}
	public int Get_BatteryVoltage_705_PGN65431(){ return service.Get_BatteryVoltage_705_PGN65431();}
	//////RX_HOURMETER_CLOCK_WIPER_65433///////
	public int Get_Hourmeter_1601_PGN65433(){ return service.Get_Hourmeter_1601_PGN65433();}
	public int Get_Clock_819_PGN65433(){ return service.Get_Clock_819_PGN65433();}
	public int Get_HourmeterActiveStatus_703_PGN65433(){ return service.Get_HourmeterActiveStatus_703_PGN65433();}
	public int Get_WiperOperationStatus_717_PGN65433(){ return service.Get_WiperOperationStatus_717_PGN65433();}
	public int Get_WiperSpeedLevel_718_PGN65433(){ return service.Get_WiperSpeedLevel_718_PGN65433();}
	public int Get_BacklightIlluminationLevel_719_PGN65433(){ return service.Get_BacklightIlluminationLevel_719_PGN65433();}
	public int Get_IlluminationStatus_737_PGN65433(){ return service.Get_IlluminationStatus_737_PGN65433();}
	//////RX_MACHINE_TRAVEL_STATUS_65434///////
	public int Get_WheelBasedVehicleSpeed_532_PGN65434(){ return service.Get_WheelBasedVehicleSpeed_532_PGN65434();}
	public int Get_SelectGear_541_PGN65434(){ return service.Get_SelectGear_541_PGN65434();}
	public int Get_ActualGear_542_PGN65434(){ return service.Get_ActualGear_542_PGN65434();}
	public int Get_TransmissionShiftMode_543_PGN65434(){ return service.Get_TransmissionShiftMode_543_PGN65434();}
	public int Get_ClutchCutoffMode_544_PGN65434(){ return service.Get_ClutchCutoffMode_544_PGN65434();}
	public int Get_KickDownShiftMode_547_PGN65434(){ return service.Get_KickDownShiftMode_547_PGN65434();}
	public int Get_TransmissionTCLockupEngaged_568_PGN65434(){ return service.Get_TransmissionTCLockupEngaged_568_PGN65434();}
	public int Get_DifferentialLockMode_570_PGN65434(){ return service.Get_DifferentialLockMode_570_PGN65434();}
	public int Get_VehicleSpeedLimitMode_575_PGN65434(){ return service.Get_VehicleSpeedLimitMode_575_PGN65434();}
	//////RX_DTC_INFORMATION_TYPE1_65438///////
	public int Get_DTCType_1510_PGN65438(){ return service.Get_DTCType_1510_PGN65438();}
	public int Get_NumberofDTCinThisPacket_1514_PGN65438(){ return service.Get_NumberofDTCinThisPacket_1514_PGN65438();}
	public int Get_TotalNumberofDTC_PGN65438(){ return service.Get_TotalNumberofDTC_PGN65438();}
	public int Get_TotalNumberofDTCInformationPacket_1512_PGN65438(){ return service.Get_TotalNumberofDTCInformationPacket_1512_PGN65438();}
	public int Get_SequenceNumberofDTCInformationPacket_1513_PGN65438(){ return service.Get_SequenceNumberofDTCInformationPacket_1513_PGN65438();}
	public byte[] Get_DTC_1_PGN65438(){ return service.Get_DTC_1_PGN65438();}
	public byte[] Get_DTC_2_PGN65438(){ return service.Get_DTC_2_PGN65438();}
	public byte[] Get_DTC_3_PGN65438(){ return service.Get_DTC_3_PGN65438();}
	public byte[] Get_DTC_4_PGN65438(){ return service.Get_DTC_4_PGN65438();}
	public byte[] Get_DTC_5_PGN65438(){ return service.Get_DTC_5_PGN65438();}
	//////RX_WEIGHING_SYSTEM_STATUS_65450///////
	public int Get_WeighingSystemAccumulationMode_1941_PGN65450(){ return service.Get_WeighingSystemAccumulationMode_1941_PGN65450();}
	public int Get_WeighingSystemBuzzer_1907_PGN65450(){ return service.Get_WeighingSystemBuzzer_1907_PGN65450();}
	public int Get_CurrentWeighingResult_1919_PGN65450(){ return service.Get_CurrentWeighingResult_1919_PGN65450();}
	public int Get_WeighingDisplayMode1_1910_PGN65450(){ return service.Get_WeighingDisplayMode1_1910_PGN65450();}
	// ++, 150212 bwk
	public int Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450(){ return service.Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450();}
	public int Get_WeighingSystemError_BucketFullIn_1943_PGN65450(){ return service.Get_WeighingSystemError_BucketFullIn_1943_PGN65450();}
	public int Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450(){ return service.Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450();}
	// --, 150212 bwk
	public int Get_CurrentWeight_1911_PGN65450(){ return service.Get_CurrentWeight_1911_PGN65450();}
	public int Get_TodayWeight_1915_PGN65450(){ return service.Get_TodayWeight_1915_PGN65450();}
	//////RX_WEIGHING_SYSTEM_DATA1_65451///////
	public int Get_TotalWorkAWeight_1912_PGN65451(){ return service.Get_TotalWorkAWeight_1912_PGN65451();}
	public int Get_TotalWorkBWeight_1913_PGN65451(){ return service.Get_TotalWorkBWeight_1913_PGN65451();}
	//////RX_WEIGHING_SYSTEM_DATA2_65452///////
	public int Get_TotalWorkCWeight_1914_PGN65452(){ return service.Get_TotalWorkCWeight_1914_PGN65452();}
	public int Get_ADayBeforeWeight_1916_PGN65452(){ return service.Get_ADayBeforeWeight_1916_PGN65452();}
	//////RX_BKCU_STATUS_65514///////
	public int Get_ButtonKeyPosition_3471_PGN65514(){ return service.Get_ButtonKeyPosition_3471_PGN65514();}
	//////RX_JOYSTICK_POSITION_STATUS_65515///////
	public int Get_BoomJoystickPositionStatus_2310_PGN65515(){ return service.Get_BoomJoystickPositionStatus_2310_PGN65515();}
	public int Get_BoomJoystickPosition_2311_PGN65515(){ return service.Get_BoomJoystickPosition_2311_PGN65515();}
	public int Get_BucketJoystickPositionStatus_2312_PGN65515(){ return service.Get_BucketJoystickPositionStatus_2312_PGN65515();}
	public int Get_BucketJoystickPosition_2313_PGN65515(){ return service.Get_BucketJoystickPosition_2313_PGN65515();}
	public int Get_AuxJoystickPositionStatus_2314_PGN65515(){ return service.Get_AuxJoystickPositionStatus_2314_PGN65515();}
	public int Get_AuxJoystickPosition_2315_PGN65515(){ return service.Get_AuxJoystickPosition_2315_PGN65515();}
	public int Get_BoomLeverFloatingPosition_2336_PGN65515(){ return service.Get_BoomLeverFloatingPosition_2336_PGN65515();}
	//////RX_WHEEL_LOADER_EHCU_STATUS_65517///////
	public int Get_BucketPriorityOperation_2301_PGN65517(){ return service.Get_BucketPriorityOperation_2301_PGN65517();}
	public int Get_FlowFineModulationOperation_2302_PGN65517(){ return service.Get_FlowFineModulationOperation_2302_PGN65517();}
	public int Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517(){ return service.Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517();}
	public int Get_AttachmentPilotCutoffStatus_225_PGN65517(){ return service.Get_AttachmentPilotCutoffStatus_225_PGN65517();}
	public int Get_FloatMode_2316_PGN65517(){ return service.Get_FloatMode_2316_PGN65517();}
	public int Get_JoystickSteeringActiveStatusEHCU_186_PGN65517(){ return service.Get_JoystickSteeringActiveStatusEHCU_186_PGN65517();}
	public int Get_BoomUpEPPRValveCurrent_2304_PGN65517(){ return service.Get_BoomUpEPPRValveCurrent_2304_PGN65517();}
	public int Get_BoomDownEPPRValveCurrent_2305_PGN65517(){ return service.Get_BoomDownEPPRValveCurrent_2305_PGN65517();}
	public int Get_BucketInEPPRValveCurrent_2306_PGN65517(){ return service.Get_BucketInEPPRValveCurrent_2306_PGN65517();}
	public int Get_BucketOutEPPRValveCurrent_2307_PGN65517(){ return service.Get_BucketOutEPPRValveCurrent_2307_PGN65517();}
	public int Get_AUX1EPPRValveCurrent_2308_PGN65517(){ return service.Get_AUX1EPPRValveCurrent_2308_PGN65517();}
	public int Get_AUX2EPPRValveCurrent_2309_PGN65517(){ return service.Get_AUX2EPPRValveCurrent_2309_PGN65517();}
	//////RX_WHEEL_LOADER_EHCU_STATUS2_65524///////
	public int Get_SoftStopBoomUp_2337_PGN65524(){ return service.Get_SoftStopBoomUp_2337_PGN65524();}
	public int Get_SoftStopBoomDown_2338_PGN65524(){ return service.Get_SoftStopBoomDown_2338_PGN65524();}
	public int Get_SoftStopBucketIn_2339_PGN65524(){ return service.Get_SoftStopBucketIn_2339_PGN65524();}
	public int Get_SoftStopBucketOut_2340_PGN65524(){ return service.Get_SoftStopBucketOut_2340_PGN65524();}
	public int Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524(){ return service.Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524();}
	public int Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524(){ return service.Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524();}
	public int Get_JoystickSteeringEnableFailCondition_2343_PGN65524(){ return service.Get_JoystickSteeringEnableFailCondition_2343_PGN65524();}
	//////RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
	public int Get_TravelAlarmOperationStatus_3431_PGN65527(){ return service.Get_TravelAlarmOperationStatus_3431_PGN65527();}
	public int Get_WasherOperationStatus_3432_PGN65527(){ return service.Get_WasherOperationStatus_3432_PGN65527();}
	public int Get_wiperoperationstatus_3433_PGN65527(){ return service.Get_wiperoperationstatus_3433_PGN65527();}
	public int Get_IntWiperOperationStatus_3434_PGN65527(){ return service.Get_IntWiperOperationStatus_3434_PGN65527();}
	public int Get_WorkLampOperationStatus_3435_PGN65527(){ return service.Get_WorkLampOperationStatus_3435_PGN65527();}
	public int Get_HeadLampOperationStatus_3436_PGN65527(){ return service.Get_HeadLampOperationStatus_3436_PGN65527();}
	public int Get_CabinLampOperationStatus_3437_PGN65527(){ return service.Get_CabinLampOperationStatus_3437_PGN65527();}
	public int Get_IlluminationOperationStatus_3438_PGN65527(){ return service.Get_IlluminationOperationStatus_3438_PGN65527();}
	public int Get_OutriggerFrontRHOperationStatus_3439_PGN65527(){ return service.Get_OutriggerFrontRHOperationStatus_3439_PGN65527();}
	public int Get_OutriggerFrontLHOperationStatus_3440_PGN65527(){ return service.Get_OutriggerFrontLHOperationStatus_3440_PGN65527();}
	public int Get_OutriggerRearRHOperationStatus_3441_PGN65527(){ return service.Get_OutriggerRearRHOperationStatus_3441_PGN65527();}
	public int Get_OutriggerRearLHOperationStatus_3442_PGN65527(){ return service.Get_OutriggerRearLHOperationStatus_3442_PGN65527();}
	public int Get_OverloadSwitchOperationStatus_3443_PGN65527(){ return service.Get_OverloadSwitchOperationStatus_3443_PGN65527();}
	public int Get_BeaconLampOperationStatus_3444_PGN65527(){ return service.Get_BeaconLampOperationStatus_3444_PGN65527();}
	public int Get_ForwardTravelAlarmOperationStatus_3445_PGN65527(){ return service.Get_ForwardTravelAlarmOperationStatus_3445_PGN65527();}
	public int Get_RearWorkLampOperationStatus_3446_PGN65527(){ return service.Get_RearWorkLampOperationStatus_3446_PGN65527();}
	public int Get_RideControlOperationStatus_3447_PGN65527(){ return service.Get_RideControlOperationStatus_3447_PGN65527();}
	public int Get_QuickCouplerOperationStatus_3448_PGN65527(){ return service.Get_QuickCouplerOperationStatus_3448_PGN65527();}
	public int Get_AutoGreaseOperationStatus_3449_PGN65527(){ return service.Get_AutoGreaseOperationStatus_3449_PGN65527();}
	public int Get_MirrorHeatOperationStatus_3450_PGN65527(){ return service.Get_MirrorHeatOperationStatus_3450_PGN65527();}
	public int Get_RearWiperOperationStatus_3451_PGN65527(){ return service.Get_RearWiperOperationStatus_3451_PGN65527();}
	public int Get_RearWiperWasherOperationStatus_3452_PGN65527(){ return service.Get_RearWiperWasherOperationStatus_3452_PGN65527();}
		
//////TX_DTC_INFORMATION_REQUEST_61184_11///////
	public void Set_MessageType_PGN61184_11(int Data){ service.Set_MessageType_PGN61184_11(Data);}
	public void Set_DTCInformationRequest_1515_PGN61184_11(int Data){ service.Set_DTCInformationRequest_1515_PGN61184_11(Data);}
	public void Set_DTCType_1510_PGN61184_11(int Data){ service.Set_DTCType_1510_PGN61184_11(Data);}
	public void Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(int Data){ service.Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(Data);}
	//////TX_MAINTENANCE_REQUSET_61184_12///////
	public void Set_MessageType_PGN61184_12(int Data){ service.Set_MessageType_PGN61184_12(Data);}
	public void Set_MaintenanceCommant_1097_PGN61184_12(int Data){ service.Set_MaintenanceCommant_1097_PGN61184_12(Data);}
	public void Set_MaintenanceItem_1098_PGN61184_12(int Data){ service.Set_MaintenanceItem_1098_PGN61184_12(Data);}
	public void Set_MaintenanceInterval_1091_PGN61184_12(int Data){ service.Set_MaintenanceInterval_1091_PGN61184_12(Data);}
	//////TX_MAINTENANCE_ITEM_LIST_61184_13///////
//	public void Set_MessageType_PGN61184_13(int Data){ service.Set_MessageType_PGN61184_13(Data);}
//	public void Set_TotalNumberofMaintenanceItems_1100_PGN61184_13(int Data){ service.Set_TotalNumberofMaintenanceItems_1100_PGN61184_13(Data);}
//	public void Set_MaintenanceItem_1098_PGN61184_13(byte[] Data){ service.Set_MaintenanceItem_1098_PGN61184_13(Data);}
	//////TX_MAINTENANCE_INFORMATION_61184_14///////
//	public void Set_MessageType_PGN61184_14(int Data){ service.Set_MessageType_PGN61184_14(Data);}
//	public void Set_MaintenanceItem_1098_PGN61184_14(int Data){ service.Set_MaintenanceItem_1098_PGN61184_14(Data);}
//	public void Set_MaintenanceAlarmLamp_1099_PGN61184_14(int Data){ service.Set_MaintenanceAlarmLamp_1099_PGN61184_14(Data);}
//	public void Set_MaintenanceEvent_PGN61184_14(int Data){ service.Set_MaintenanceEvent_PGN61184_14(Data);}
//	public void Set_MaintenanceInterval_1091_PGN61184_14(int Data){ service.Set_MaintenanceInterval_1091_PGN61184_14(Data);}
//	public void Set_MaintenanceTotalCount_1092_PGN61184_14(int Data){ service.Set_MaintenanceTotalCount_1092_PGN61184_14(Data);}
//	public void Set_HourmeterattheLastMaintenance_1093_PGN61184_14(int Data){ service.Set_HourmeterattheLastMaintenance_1093_PGN61184_14(Data);}
	//////TX_MAINTENANCE_HISTORY_61184_15///////
//	public void Set_MessageType_PGN61184_15(int Data){ service.Set_MessageType_PGN61184_15(Data);}
//	public void Set_MaintenanceItem_1098_PGN61184_15(int Data){ service.Set_MaintenanceItem_1098_PGN61184_15(Data);}
//	public void Set_HourmeterattheLastMaintenance_1093_PGN61184_15(int Data){ service.Set_HourmeterattheLastMaintenance_1093_PGN61184_15(Data);}
//	public void Set_MaintenanceHistory_1094_PGN61184_15(byte[] Data){ service.Set_MaintenanceHistory_1094_PGN61184_15(Data);}
	//////TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
//	public void Set_MessageType_PGN61184_16(int Data){ service.Set_MessageType_PGN61184_16(Data);}
//	public void Set_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16(int Data){ service.Set_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16(Data);}
//	public void Set_MaintenanceItem_PGN61184_16(byte[] Data){ service.Set_MaintenanceItem_PGN61184_16(Data);}
	//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
	public void Set_MessageType_PGN61184_21(int Data){ service.Set_MessageType_PGN61184_21(Data);}
	//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
//	public void Set_MessageType_PGN61184_22(int Data){ service.Set_MessageType_PGN61184_22(Data);}
//	public void Set_HCEAntiTheftRandomNumber_1632_PGN61184_22(byte[] Data){ service.Set_HCEAntiTheftRandomNumber_1632_PGN61184_22(Data);}
	//////TX_HCE_ANTI_THEFT_REQUEST_61184_23///////
	public void Set_MessageType_PGN61184_23(int Data){ service.Set_MessageType_PGN61184_23(Data);}
	public void Set_HCEAntiTheftCommand_1633_PGN61184_23(int Data){ service.Set_HCEAntiTheftCommand_1633_PGN61184_23(Data);}
	public void Set_ESLMode_820_PGN61184_23(int Data){ service.Set_ESLMode_820_PGN61184_23(Data);}
	public void Set_ESLInterval_825_PGN61184_23(int Data){ service.Set_ESLInterval_825_PGN61184_23(Data);}
	public void Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(byte[] Data){ service.Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(Data);}
	//////TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
//	public void Set_MessageType_PGN61184_24(int Data){ service.Set_MessageType_PGN61184_24(Data);}
//	public void Set_PasswordCertificationResult_956_PGN61184_24(int Data){ service.Set_PasswordCertificationResult_956_PGN61184_24(Data);}
	//////TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
//	public void Set_MessageType_PGN61184_25(int Data){ service.Set_MessageType_PGN61184_25(Data);}
//	public void Set_PasswordChangeResult_958_PGN61184_25(int Data){ service.Set_PasswordChangeResult_958_PGN61184_25(Data);}
//	public void Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25(byte[] Data){ service.Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25(Data);}
	//////TX_COOLING_FAN_SETTING_61184_61///////
	public void Set_MessageType_PGN61184_61(int Data){ service.Set_MessageType_PGN61184_61(Data);}
	public void Set_TestMode_PGN61184_61(int Data){ service.Set_TestMode_PGN61184_61(Data);}
	public void Set_CoolingFanReverseMode_182_PGN61184_61(int Data){ service.Set_CoolingFanReverseMode_182_PGN61184_61(Data);}
	public void Set_CoolingFanReverseManual_PGN61184_61(int Data){ service.Set_CoolingFanReverseManual_PGN61184_61(Data);}
	public void Set_CoolingFanValveCurrent_146_PGN61184_61(int Data){ service.Set_CoolingFanValveCurrent_146_PGN61184_61(Data);}
	public void Set_CoolingFanReverseIntervalTime_211_PGN61184_61(int Data){ service.Set_CoolingFanReverseIntervalTime_211_PGN61184_61(Data);}
	public void Set_CoolingFanReverseOperatingTime_212_PGN61184_61(int Data){ service.Set_CoolingFanReverseOperatingTime_212_PGN61184_61(Data);}
	//////TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
	public void Set_MessageType_PGN61184_62(int Data){ service.Set_MessageType_PGN61184_62(Data);}
	public void Set_WeighingSystemAccumulationMode_1941_PGN61184_62(int Data){ service.Set_WeighingSystemAccumulationMode_1941_PGN61184_62(Data);}
	public void Set_RequestReweighing_PGN61184_62(int Data){ service.Set_RequestReweighing_PGN61184_62(Data);}
	public void Set_RequestTotalWorkWeightReset_PGN61184_62(int Data){ service.Set_RequestTotalWorkWeightReset_PGN61184_62(Data);}
	public void Set_WeightOffsetSelection_PGN61184_62(int Data){ service.Set_WeightOffsetSelection_PGN61184_62(Data);}
	public void Set_WeightOffsetSetting_PGN61184_62(int Data){ service.Set_WeightOffsetSetting_PGN61184_62(Data);}
	public void Set_WeightOffset_1922_PGN61184_62(int Data){ service.Set_WeightOffset_1922_PGN61184_62(Data);}
	public void Set_WeighingDisplayMode1_1910_PGN61184_62(int Data){ service.Set_WeighingDisplayMode1_1910_PGN61184_62(Data);}
	//////TX_WEIGHT_OFFSET_61184_63///////
//	public void Set_MessageType_PGN61184_63(int Data){ service.Set_MessageType_PGN61184_63(Data);}
//	public void Set_WeightOffsetSelectionStatus_PGN61184_63(int Data){ service.Set_WeightOffsetSelectionStatus_PGN61184_63(Data);}
//	public void Set_WeightOffsetWorkTool1_1922_PGN61184_63(int Data){ service.Set_WeightOffsetWorkTool1_1922_PGN61184_63(Data);}
//	public void Set_WeightOffsetWorkTool2_1922_PGN61184_63(int Data){ service.Set_WeightOffsetWorkTool2_1922_PGN61184_63(Data);}
//	public void Set_WeightOffsetWorkTool3_1922_PGN61184_63(int Data){ service.Set_WeightOffsetWorkTool3_1922_PGN61184_63(Data);}
	//////TX_MACHINE_MODE_SETTING_61184_101///////
	public void Set_MessageType_PGN61184_101(int Data){ service.Set_MessageType_PGN61184_101(Data);}
	public void Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(int Data){ service.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(Data);}
	public void Set_EnginePowerMode_347_PGN61184_101(int Data){ service.Set_EnginePowerMode_347_PGN61184_101(Data);}
	//////TX_TRAVEL_MODE_SETTING_61184_104///////
	public void Set_MessageType_PGN61184_104(int Data){ service.Set_MessageType_PGN61184_104(Data);}
	public void Set_TransmisstionShiftMode_543_PGN61184_104(int Data){ service.Set_TransmisstionShiftMode_543_PGN61184_104(Data);}
	public void Set_ClutchCutoffMode_544_PGN61184_104(int Data){ service.Set_ClutchCutoffMode_544_PGN61184_104(Data);}
	public void Set_KickDownShiftMode_547_PGN61184_104(int Data){ service.Set_KickDownShiftMode_547_PGN61184_104(Data);}
	public void Set_TransmissionTCLockupEngaged_568_PGN61184_104(int Data){ service.Set_TransmissionTCLockupEngaged_568_PGN61184_104(Data);}
	public void Set_VehicleSpeedLimitMode_575_PGN61184_104(int Data){ service.Set_VehicleSpeedLimitMode_575_PGN61184_104(Data);}
	//////TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
	public void Set_MessageType_PGN61184_105(int Data){ service.Set_MessageType_PGN61184_105(Data);}
	public void Set_SettingSelection_PGN61184_105(int Data){ service.Set_SettingSelection_PGN61184_105(Data);}
	public void Set_SpeedometerFrequency_534_PGN61184_105(int Data){ service.Set_SpeedometerFrequency_534_PGN61184_105(Data);}
	public void Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(int Data){ service.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(Data);}
	public void Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(int Data){ service.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(Data);}
	public void Set_VehicleSpeedLimit_572_PGN61184_105(int Data){ service.Set_VehicleSpeedLimit_572_PGN61184_105(Data);}
	//////TX_TRAVEL_CONTROL_VALUE_61184_106///////
//	public void Set_MessageType_PGN61184_106(int Data){ service.Set_MessageType_PGN61184_106(Data);}
//	public void Set_SpeedometerFrequency_534_PGN61184_106(int Data){ service.Set_SpeedometerFrequency_534_PGN61184_106(Data);}
//	public void Set_AutoRideControlOperationSpeedForward_PGN61184_574_106(int Data){ service.Set_AutoRideControlOperationSpeedForward_PGN61184_106(Data);}
//	public void Set_AutoRideControlOperationSpeedBackward_576_PGN61184_106(int Data){ service.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_106(Data);}
//	public void Set_VehicleSpeedLimit_572_PGN61184_106(int Data){ service.Set_VehicleSpeedLimit_572_PGN61184_106(Data);}
	//////TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
	public void Set_MessageType_PGN61184_109(int Data){ service.Set_MessageType_PGN61184_109(Data);}
	public void Set_BacklightIlluminationLevel_719_PGN61184_109(int Data){ service.Set_BacklightIlluminationLevel_719_PGN61184_109(Data);}
	public void Set_WiperSpeedLevel_718_PGN61184_109(int Data){ service.Set_WiperSpeedLevel_718_PGN61184_109(Data);}
	public void Set_Clock_819_PGN61184_109(int Data){ service.Set_Clock_819_PGN61184_109(Data);}
	public void Set_RequestEngineLowIdleSpeed_PGN61184_109(int Data){ service.Set_RequestEngineLowIdleSpeed_PGN61184_109(Data);}
	public void Set_RequestTripDataReset_PGN61184_109(int Data){ service.Set_RequestTripDataReset_PGN61184_109(Data);}
	//////TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
	public void Set_MessageType_PGN61184_121(int Data){ service.Set_MessageType_PGN61184_121(Data);}
	public void Set_AutomaticEngineShutdown_363_PGN61184_121(int Data){ service.Set_AutomaticEngineShutdown_363_PGN61184_121(Data);}
	public void Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(int Data){ service.Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(Data);}
	public void Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(int Data){ service.Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(Data);}
	public void Set_EngineShutdownCotrolByte_PGN61184_121(int Data){ service.Set_EngineShutdownCotrolByte_PGN61184_121(Data);}
	public void Set_DelayedEngineShutdown_365_PGN61184_121(int Data){ service.Set_DelayedEngineShutdown_365_PGN61184_121(Data);}
	public void Set_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121(int Data){ service.Set_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121(Data);}
	//////TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
//	public void Set_MessageType_PGN61184_122(int Data){ service.Set_MessageType_PGN61184_122(Data);}
//	public void Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122(int Data){ service.Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122(Data);}
//	public void Set_RemainingTimeforAutomaticEngineShutdown_PGN61184_122(int Data){ service.Set_RemainingTimeforAutomaticEngineShutdown_PGN61184_122(Data);}
//	public void Set_SettingTimeforDelayedEngineShutdown_366_PGN61184_122(int Data){ service.Set_SettingTimeforDelayedEngineShutdown_366_PGN61184_122(Data);}
//	public void Set_RemainingTimeforDelayedEngineShutdown_PGN61184_122(int Data){ service.Set_RemainingTimeforDelayedEngineShutdown_PGN61184_122(Data);}
	//////TX_DETENT_MODE_SETTING_61184_123///////
	public void Set_MessageType_PGN61184_123(int Data){ service.Set_MessageType_PGN61184_123(Data);}
	public void Set_BoomDetentMode_223_PGN61184_123(int Data){ service.Set_BoomDetentMode_223_PGN61184_123(Data);}
	public void Set_BucketDetentMode_224_PGN61184_123(int Data){ service.Set_BucketDetentMode_224_PGN61184_123(Data);}
	public void Set_RequestDetentReleasePositionSetting_PGN61184_123(int Data){ service.Set_RequestDetentReleasePositionSetting_PGN61184_123(Data);}
	//////TX_DETENT_MODE_STATUS_61184_124///////
//	public void Set_MessageType_PGN61184_124(int Data){ service.Set_MessageType_PGN61184_124(Data);}
//	public void Set_BoomDetentMode_223_PGN61184_124(int Data){ service.Set_BoomDetentMode_223_PGN61184_124(Data);}
//	public void Set_BucketDetentMode_224_PGN61184_124(int Data){ service.Set_BucketDetentMode_224_PGN61184_124(Data);}
//	public void Set_DetentReleasePositionSettingStatus_PGN61184_124(int Data){ service.Set_DetentReleasePositionSettingStatus_PGN61184_124(Data);}
	//////TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
//	public void Set_MessageType_PGN61184_129(int Data){ service.Set_MessageType_PGN61184_129(Data);}
//	public void Set_EngineShutdownCommand_3472_PGN61184_129(int Data){ service.Set_EngineShutdownCommand_3472_PGN61184_129(Data);}
	//////TX_AS_PHONE_NUMBER_SETTING_61184_151///////
	public void Set_MessageType_PGN61184_151(int Data){ service.Set_MessageType_PGN61184_151(Data);}
//	public void Set_ASPhoneNumber_PGN61184_151(byte[] Data){ service.Set_ASPhoneNumber_PGN61184_151(Data);}
	//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
	public void Set_MessageType_PGN61184_201(int Data){ service.Set_MessageType_PGN61184_201(Data);}
	public void Set_RequestBoomPressureCalibration_PGN61184_201(int Data){ service.Set_RequestBoomPressureCalibration_PGN61184_201(Data);}
	public void Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(int Data){ service.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(Data);}
	public void Set_RequestAEB_PGN61184_201(int Data){ service.Set_RequestAEB_PGN61184_201(Data);}
	public void Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(int Data){ service.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(Data);}
	//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
//	public void Set_MessageType_PGN61184_202(int Data){ service.Set_MessageType_PGN61184_202(Data);}
//	public void Set_BoomPressureCalibrationStatus_1908_PGN61184_202(int Data){ service.Set_BoomPressureCalibrationStatus_1908_PGN61184_202(Data);}
//	public void Set_AngleSensorCalibrationStatus_1909_PGN61184_202(int Data){ service.Set_AngleSensorCalibrationStatus_1909_PGN61184_202(Data);}
//	public void Set_AEBStatusInformation_MainCode_562_PGN61184_202(int Data){ service.Set_AEBStatusInformation_MainCode_562_PGN61184_202(Data);}
//	public void Set_AEBCycleNumber_540_PGN61184_202(int Data){ service.Set_AEBCycleNumber_540_PGN61184_202(Data);}
//	public void Set_AEBStatusInformation_SubCode_563_PGN61184_202(int Data){ service.Set_AEBStatusInformation_SubCode_563_PGN61184_202(Data);}
//	public void Set_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202(int Data){ service.Set_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202(Data);}
//	public void Set_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202(int Data){ service.Set_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202(Data);}
	//////TX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
	public void Set_MessageType_PGN61184_203(int Data){ service.Set_MessageType_PGN61184_203(Data);}
	public void Set_BucketPriorityOperation_2301_PGN61184_203(int Data){ service.Set_BucketPriorityOperation_2301_PGN61184_203(Data);}
	public void Set_FlowFineModulationOperation_2302_PGN61184_203(int Data){ service.Set_FlowFineModulationOperation_2302_PGN61184_203(Data);}
	public void Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(int Data){ service.Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(Data);}
	public void Set_BoomLeverFloatingPosition_PGN61184_203(int Data){ service.Set_BoomLeverFloatingPosition_PGN61184_203(Data);}
	public void Set_SoftStopBoomUp_2337_PGN61184_203(int Data){ service.Set_SoftStopBoomUp_2337_PGN61184_203(Data);}
	public void Set_SoftStopBoomDown_2338_PGN61184_203(int Data){ service.Set_SoftStopBoomDown_2338_PGN61184_203(Data);}
	public void Set_SoftStopBucketIn_2339_PGN61184_203(int Data){ service.Set_SoftStopBucketIn_2339_PGN61184_203(Data);}
	public void Set_SoftStopBucketOut_2340_PGN61184_203(int Data){ service.Set_SoftStopBucketOut_2340_PGN61184_203(Data);}
	public void Set_BoomDownSpeedAdjust_PGN61184_203(int Data){ service.Set_BoomDownSpeedAdjust_PGN61184_203(Data);}
	public void Set_BucketOutSpeedAdjust_PGN61184_203(int Data){ service.Set_BucketOutSpeedAdjust_PGN61184_203(Data);}
	//////TX_MONIOTR_STATUS_65327///////
	public void Set_RequestBuzzerStop_PGN65327(int Data){ service.Set_RequestBuzzerStop_PGN65327(Data);}
	public void Set_SpeedmeterUnitChange_PGN65327(int Data){ service.Set_SpeedmeterUnitChange_PGN65327(Data);}
	public void Set_MonitorScreenNumber_836_PGN65327(int Data){ service.Set_MonitorScreenNumber_836_PGN65327(Data);}
	//////TX_RMCU_STATUS_65329///////
//	public void Set_RMCUNetworkType_1621_PGN65329(int Data){ service.Set_RMCUNetworkType_1621_PGN65329(Data);}
//	public void Set_RMCUBackupBatteryVoltage_1590_PGN65329(int Data){ service.Set_RMCUBackupBatteryVoltage_1590_PGN65329(Data);}
//	public void Set_RMCUPowerSource_1594_PGN65329(int Data){ service.Set_RMCUPowerSource_1594_PGN65329(Data);}
//	public void Set_RMCUBoxOpeningStatus_PGN65329(int Data){ service.Set_RMCUBoxOpeningStatus_PGN65329(Data);}
//	public void Set_NetworkCommunicationStatus1_1622_PGN65329(int Data){ service.Set_NetworkCommunicationStatus1_1622_PGN65329(Data);}
//	public void Set_PositionUpdateStatus_852_PGN65329(int Data){ service.Set_PositionUpdateStatus_852_PGN65329(Data);}
//	public void Set_MachinePositionStatus_1593_PGN65329(int Data){ service.Set_MachinePositionStatus_1593_PGN65329(Data);}
//	public void Set_GPSAntennaConnectionAlarmStatus_1595_PGN65329(int Data){ service.Set_GPSAntennaConnectionAlarmStatus_1595_PGN65329(Data);}
//	public void Set_NetworkTransceiverStatus1_1623_PGN65329(int Data){ service.Set_NetworkTransceiverStatus1_1623_PGN65329(Data);}
//	public void Set_NetworkServiceStatus1_1624_PGN65329(int Data){ service.Set_NetworkServiceStatus1_1624_PGN65329(Data);}
//	public void Set_NetworkAntennaStatus1_1625_PGN65329(int Data){ service.Set_NetworkAntennaStatus1_1625_PGN65329(Data);}
//	public void Set_RMCUData_NumberofMessagestoTransmit_855_PGN65329(int Data){ service.Set_RMCUData_NumberofMessagestoTransmit_855_PGN65329(Data);}
//	//////TX_COMPONENT_IDENTIFICATION_65330///////
//	public void Set_ComponentCode_1699_PGN65330(int Data){ service.Set_ComponentCode_1699_PGN65330(Data);}
//	public void Set_ManufacturerCode_1700_PGN65330(int Data){ service.Set_ManufacturerCode_1700_PGN65330(Data);}
//	public void Set_ComponentBasicInformation_1698_PGN65330(byte[] Data){ service.Set_ComponentBasicInformation_1698_PGN65330(Data);}
//	//////TX_COMPONENT_IDENTIFICATION_ECM_65330///////
//	public void Set_ComponentCode_1699_PGN65330_ECM(int Data){ service.Set_ComponentCode_1699_PGN65330_ECM(Data);}
//	public void Set_ManufacturerCode_1700_PGN65330_ECM(int Data){ service.Set_ManufacturerCode_1700_PGN65330_ECM(Data);}
//	public void Set_ComponentBasicInformation_1698_PGN65330_ECM(byte[] Data){ service.Set_ComponentBasicInformation_1698_PGN65330_ECM(Data);}
//	//////TX_COMPONENT_IDENTIFICATION_TCU_65330///////
//	public void Set_ComponentCode_1699_PGN65330_TCU(int Data){ service.Set_ComponentCode_1699_PGN65330_TCU(Data);}
//	public void Set_ManufacturerCode_1700_PGN65330_TCU(int Data){ service.Set_ManufacturerCode_1700_PGN65330_TCU(Data);}
//	public void Set_ComponentBasicInformation_1698_PGN65330_TCU(byte[] Data){ service.Set_ComponentBasicInformation_1698_PGN65330_TCU(Data);}
//	//////TX_TRIP_TIME_INFORMATION_65344///////
	//////TX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
	public void Set_ComponentCode_1699_PGN65330_MONITOR(int Data){ service.Set_ComponentCode_1699_PGN65330_MONITOR(Data);}
	public void Set_ManufacturerCode_1700_PGN65330_MONITOR(int Data){ service.Set_ManufacturerCode_1700_PGN65330_MONITOR(Data);}
	public void Set_ComponentBasicInformation_1698_PGN65330_MONITOR(byte[] Data){ service.Set_ComponentBasicInformation_1698_PGN65330_MONITOR(Data);}
//	public void Set_TripTime_849_PGN65344(int Data){ service.Set_TripTime_849_PGN65344(Data);}
//	//////TX_MACHINE_SECURITY_STATUS_65348///////
//	public void Set_ESLMode_820_PGN65348(int Data){ service.Set_ESLMode_820_PGN65348(Data);}
//	public void Set_LockMode_822_PGN65348(int Data){ service.Set_LockMode_822_PGN65348(Data);}
//	public void Set_LockLevel_823_PGN65348(int Data){ service.Set_LockLevel_823_PGN65348(Data);}
//	public void Set_LockDemander_824_PGN65348(int Data){ service.Set_LockDemander_824_PGN65348(Data);}
//	public void Set_EngineStartingPermission_821_PGN65348(int Data){ service.Set_EngineStartingPermission_821_PGN65348(Data);}
//	public void Set_PasswordIdentificationBlockedState_1631_PGN65348(int Data){ service.Set_PasswordIdentificationBlockedState_1631_PGN65348(Data);}
//	public void Set_ESLInterval_825_PGN65348(int Data){ service.Set_ESLInterval_825_PGN65348(Data);}
//	//////TX_MACHINE_MODE_STATUS_65350///////
//	public void Set_EngineAlternateLowIdelSwitch_348_PGN65350(int Data){ service.Set_EngineAlternateLowIdelSwitch_348_PGN65350(Data);}
//	public void Set_EnginePowerMode_347_PGN65350(int Data){ service.Set_EnginePowerMode_347_PGN65350(Data);}
//	//////TX_HYDRAULIC_PRESSURE4_65354///////
//	public void Set_BrakeOilPressure_503_PGN65354(int Data){ service.Set_BrakeOilPressure_503_PGN65354(Data);}
//	public void Set_BrakeOilChargingPriorityPressure_557_PGN65354(int Data){ service.Set_BrakeOilChargingPriorityPressure_557_PGN65354(Data);}
//	//////TX_HYDRAULIC_PRESSURE6_65356///////
//	public void Set_BoomCylinderHeadPressure_204_PGN65356(int Data){ service.Set_BoomCylinderHeadPressure_204_PGN65356(Data);}
//	public void Set_BoomCylinderRodPressure_205_PGN65356(int Data){ service.Set_BoomCylinderRodPressure_205_PGN65356(Data);}
//	//////TX_HYDRAULIC_PRESSURE7_65357///////
//	public void Set_SteeringMainPumpPressure_202_PGN65357(int Data){ service.Set_SteeringMainPumpPressure_202_PGN65357(Data);}
//	public void Set_EmergencySteeringPumpPressure_203_PGN65357(int Data){ service.Set_EmergencySteeringPumpPressure_203_PGN65357(Data);}
//	public void Set_ParkingOilPressure_507_PGN65357(int Data){ service.Set_ParkingOilPressure_507_PGN65357(Data);}
//	public void Set_DifferentialLockPressure_558_PGN65357(int Data){ service.Set_DifferentialLockPressure_558_PGN65357(Data);}
//	//////TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
//	public void Set_AlternatorVoltage_707_PGN65360(int Data){ service.Set_AlternatorVoltage_707_PGN65360(Data);}
//	//////TX_RELAY_BUZZER_STATUS_65364///////
//	public void Set_AntiRestartRelay_327_PGN65364(int Data){ service.Set_AntiRestartRelay_327_PGN65364(Data);}
//	public void Set_FuelWarmerRelay_325_PGN65364(int Data){ service.Set_FuelWarmerRelay_325_PGN65364(Data);}
//	public void Set_EnginePreheatRelay_322_PGN65364(int Data){ service.Set_EnginePreheatRelay_322_PGN65364(Data);}
//	public void Set_EngineStopRelay_345_PGN65364(int Data){ service.Set_EngineStopRelay_345_PGN65364(Data);}
//	public void Set_WorkingCutoffRelay_164_PGN65364(int Data){ service.Set_WorkingCutoffRelay_164_PGN65364(Data);}
//	public void Set_TravelingCutoffRelay_517_PGN65364(int Data){ service.Set_TravelingCutoffRelay_517_PGN65364(Data);}
//	public void Set_ParkingRelay_514_PGN65364(int Data){ service.Set_ParkingRelay_514_PGN65364(Data);}
//	public void Set_WiperRelay_727_PGN65364(int Data){ service.Set_WiperRelay_727_PGN65364(Data);}
//	public void Set_EmergencySteeringPumpRelay_187_PGN65364(int Data){ service.Set_EmergencySteeringPumpRelay_187_PGN65364(Data);}
//	public void Set_TravelAlarmBuzzer_722_PGN65364(int Data){ service.Set_TravelAlarmBuzzer_722_PGN65364(Data);}
//	public void Set_Buzzer_723_PGN65364(int Data){ service.Set_Buzzer_723_PGN65364(Data);}
//	public void Set_FuelCutoffRelay_324_PGN65364(int Data){ service.Set_FuelCutoffRelay_324_PGN65364(Data);}
//	//////TX_SOLENOID_STATUS_65365///////
//	public void Set_BoomUpLeverDetentSolenoid_172_PGN65365(int Data){ service.Set_BoomUpLeverDetentSolenoid_172_PGN65365(Data);}
//	public void Set_BoomDownLeverDetentSolenoid_173_PGN65365(int Data){ service.Set_BoomDownLeverDetentSolenoid_173_PGN65365(Data);}
//	public void Set_BucketLeverDetentSolenoid_174_PGN65365(int Data){ service.Set_BucketLeverDetentSolenoid_174_PGN65365(Data);}
//	//////TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
//	public void Set_AcceleratorPedalPositionVoltage1_710_PGN65368(int Data){ service.Set_AcceleratorPedalPositionVoltage1_710_PGN65368(Data);}
//	public void Set_BrakePedalPositionVoltage_573_PGN65368(int Data){ service.Set_BrakePedalPositionVoltage_573_PGN65368(Data);}
//	public void Set_AcceleratorPedalPosition1_339_PGN65368(int Data){ service.Set_AcceleratorPedalPosition1_339_PGN65368(Data);}
//	public void Set_AcceleratorPedalPositionVoltage2_710_PGN65368(int Data){ service.Set_AcceleratorPedalPositionVoltage2_710_PGN65368(Data);}
//	//////TX_COOLING_FAN_STATUS_65369///////
//	public void Set_CoolingFanReverseMode_182_PGN65369(int Data){ service.Set_CoolingFanReverseMode_182_PGN65369(Data);}
//	public void Set_CoolingFanReverseSolenoid_181_PGN65369(int Data){ service.Set_CoolingFanReverseSolenoid_181_PGN65369(Data);}
//	public void Set_CoolingFanReverseSwitchManual_740_PGN65369(int Data){ service.Set_CoolingFanReverseSwitchManual_740_PGN65369(Data);}
//	public void Set_CoolingFanReverseSwitchAuto_741_PGN65369(int Data){ service.Set_CoolingFanReverseSwitchAuto_741_PGN65369(Data);}
//	public void Set_CoolingFanValveCurrent_146_PGN65369(int Data){ service.Set_CoolingFanValveCurrent_146_PGN65369(Data);}
//	public void Set_CoolingFanSpeed_318_PGN65369(int Data){ service.Set_CoolingFanSpeed_318_PGN65369(Data);}
//	public void Set_CoolingFanReverseIntervalTime_211_PGN65369(int Data){ service.Set_CoolingFanReverseIntervalTime_211_PGN65369(Data);}
//	public void Set_CoolingFanReverseOperatingTime_212_PGN65369(int Data){ service.Set_CoolingFanReverseOperatingTime_212_PGN65369(Data);}
//	//////TX_ENGINE_STATUS1_65371///////
//	public void Set_EngineOperatingCondition_336_PGN65371(int Data){ service.Set_EngineOperatingCondition_336_PGN65371(Data);}
//	public void Set_DEFTankLevel_362_PGN65371(int Data){ service.Set_DEFTankLevel_362_PGN65371(Data);}
//	//////TX_VEHICLE_DISTANCE_65389///////
//	public void Set_TripDistance_600_PGN65389(int Data){ service.Set_TripDistance_600_PGN65389(Data);}
//	public void Set_TotalVehicleDistance_601_PGN65389(int Data){ service.Set_TotalVehicleDistance_601_PGN65389(Data);}
//	//////TX_CYLINDER_ANGLE_STROKE1_65395///////
//	public void Set_BoomLinkAngle_1920_PGN65395(int Data){ service.Set_BoomLinkAngle_1920_PGN65395(Data);}
//	public void Set_BellCrankAngle_1921_PGN65395(int Data){ service.Set_BellCrankAngle_1921_PGN65395(Data);}
//	public void Set_BoomLinkAngleSensorSignalVoltage_728_PGN65395(int Data){ service.Set_BoomLinkAngleSensorSignalVoltage_728_PGN65395(Data);}
//	public void Set_BellCrankAngleSensorSignalVoltage_729_PGN65395(int Data){ service.Set_BellCrankAngleSensorSignalVoltage_729_PGN65395(Data);}
//	public void Set_BucketCylinderStroke_1930_PGN65395(int Data){ service.Set_BucketCylinderStroke_1930_PGN65395(Data);}
	//////TX_AS_PHONE_NUMBER_65425///////
	public void Set_ASPhoneNumber_1095_PGN65425(byte[] Data){ service.Set_ASPhoneNumber_1095_PGN65425(Data);}
//	//////TX_WARNING_LAMP_65427///////
//	public void Set_FuelLevelLow_303_PGN65427(int Data){ service.Set_FuelLevelLow_303_PGN65427(Data);}
//	public void Set_HydraulicOilTemperatureHigh_102_PGN65427(int Data){ service.Set_HydraulicOilTemperatureHigh_102_PGN65427(Data);}
//	public void Set_BatteryVoltageLow_706_PGN65427(int Data){ service.Set_BatteryVoltageLow_706_PGN65427(Data);}
//	public void Set_Overload_104_PGN65427(int Data){ service.Set_Overload_104_PGN65427(Data);}
//	public void Set_AirCleanerClog_317_PGN65427(int Data){ service.Set_AirCleanerClog_317_PGN65427(Data);}
//	public void Set_EngineCheckLamp_320_PGN65427(int Data){ service.Set_EngineCheckLamp_320_PGN65427(Data);}
//	public void Set_TransmissionOilPressureLow_502_PGN65427(int Data){ service.Set_TransmissionOilPressureLow_502_PGN65427(Data);}
//	public void Set_BrakeOilPressureLow_504_PGN65427(int Data){ service.Set_BrakeOilPressureLow_504_PGN65427(Data);}
//	public void Set_EngineCoolantTemperatureHigh_305_PGN65427(int Data){ service.Set_EngineCoolantTemperatureHigh_305_PGN65427(Data);}
//	public void Set_EngineCoolantLevelLow_307_PGN65427(int Data){ service.Set_EngineCoolantLevelLow_307_PGN65427(Data);}
//	public void Set_EngineOilPressureLow_313_PGN65427(int Data){ service.Set_EngineOilPressureLow_313_PGN65427(Data);}
//	public void Set_EngineOilFilterClog_315_PGN65427(int Data){ service.Set_EngineOilFilterClog_315_PGN65427(Data);}
//	public void Set_TransmissionOilTemperatureHigh_537_PGN65427(int Data){ service.Set_TransmissionOilTemperatureHigh_537_PGN65427(Data);}
//	public void Set_TransmissionCheck_538_PGN65427(int Data){ service.Set_TransmissionCheck_538_PGN65427(Data);}
//	public void Set_SteeringMainPumpPressureLow_184_PGN65427(int Data){ service.Set_SteeringMainPumpPressureLow_184_PGN65427(Data);}
//	public void Set_EmergencySteeringActive_185_PGN65427(int Data){ service.Set_EmergencySteeringActive_185_PGN65427(Data);}
//	public void Set_WarningSymbolLamp_709_PGN65427(int Data){ service.Set_WarningSymbolLamp_709_PGN65427(Data);}
//	public void Set_WaterInFuelIndicator_360_PGN65427(int Data){ service.Set_WaterInFuelIndicator_360_PGN65427(Data);}
//	public void Set_DTCAlarmLamp_1509_PGN65427(int Data){ service.Set_DTCAlarmLamp_1509_PGN65427(Data);}
//	public void Set_EngineStopLamp_319_PGN65427(int Data){ service.Set_EngineStopLamp_319_PGN65427(Data);}
//	public void Set_DPFLampCommand_352_PGN65427(int Data){ service.Set_DPFLampCommand_352_PGN65427(Data);}
//	public void Set_DEFLowLamp_358_PGN65427(int Data){ service.Set_DEFLowLamp_358_PGN65427(Data);}
//	public void Set_ClutchSlip_569_PGN65427(int Data){ service.Set_ClutchSlip_569_PGN65427(Data);}
//	public void Set_BrakeOilLevelLow_566_PGN65427(int Data){ service.Set_BrakeOilLevelLow_566_PGN65427(Data);}
//	public void Set_BrakeOilTemperatureHigh_567_PGN65427(int Data){ service.Set_BrakeOilTemperatureHigh_567_PGN65427(Data);}
//	public void Set_EmissionSystemFailLamp_357_PGN65427(int Data){ service.Set_EmissionSystemFailLamp_357_PGN65427(Data);}
//	//////TX_INDICATOR_LAMP_65428///////
//	public void Set_PowerMaxStatus_802_PGN65428(int Data){ service.Set_PowerMaxStatus_802_PGN65428(Data);}
//	public void Set_DecelerationStatus_803_PGN65428(int Data){ service.Set_DecelerationStatus_803_PGN65428(Data);}
//	public void Set_WarmingUpStatus_804_PGN65428(int Data){ service.Set_WarmingUpStatus_804_PGN65428(Data);}
//	public void Set_EnginePreheatStatus_323_PGN65428(int Data){ service.Set_EnginePreheatStatus_323_PGN65428(Data);}
//	public void Set_FuelWarmerActiveStatus_326_PGN65428(int Data){ service.Set_FuelWarmerActiveStatus_326_PGN65428(Data);}
//	public void Set_CoolingFandrivingStatus_180_PGN65428(int Data){ service.Set_CoolingFandrivingStatus_180_PGN65428(Data);}
//	public void Set_CruiseStatus_519_PGN65428(int Data){ service.Set_CruiseStatus_519_PGN65428(Data);}
//	public void Set_ParkingStatus_508_PGN65428(int Data){ service.Set_ParkingStatus_508_PGN65428(Data);}
//	public void Set_ClutchCutoffStatus_545_PGN65428(int Data){ service.Set_ClutchCutoffStatus_545_PGN65428(Data);}
//	public void Set_FNRJoystickActiveStatus_546_PGN65428(int Data){ service.Set_FNRJoystickActiveStatus_546_PGN65428(Data);}
//	public void Set_RideControlStatus_550_PGN65428(int Data){ service.Set_RideControlStatus_550_PGN65428(Data);}
//	public void Set_DifferentialLockStatus_559_PGN65428(int Data){ service.Set_DifferentialLockStatus_559_PGN65428(Data);}
//	public void Set_JoystickSteeringActiveStatus_186_PGN65428(int Data){ service.Set_JoystickSteeringActiveStatus_186_PGN65428(Data);}
//	public void Set_InchingStatus_549_PGN65428(int Data){ service.Set_InchingStatus_549_PGN65428(Data);}
//	public void Set_RamLockStatus_520_PGN65428(int Data){ service.Set_RamLockStatus_520_PGN65428(Data);}
//	public void Set_WorkingBrakeStatus_506_PGN65428(int Data){ service.Set_WorkingBrakeStatus_506_PGN65428(Data);}
//	public void Set_MaintenanceAlarmLamp_1099_PGN65428(int Data){ service.Set_MaintenanceAlarmLamp_1099_PGN65428(Data);}
//	public void Set_OperatorAbsenceStatus_832_PGN65428(int Data){ service.Set_OperatorAbsenceStatus_832_PGN65428(Data);}
//	public void Set_MirrorHeaterStatus_724_PGN65428(int Data){ service.Set_MirrorHeaterStatus_724_PGN65428(Data);}
//	public void Set_HighBeamStatus_725_PGN65428(int Data){ service.Set_HighBeamStatus_725_PGN65428(Data);}
//	public void Set_TravelAlarmSwitch_721_PGN65428(int Data){ service.Set_TravelAlarmSwitch_721_PGN65428(Data);}
//	public void Set_SwingBoomSwitch_726_PGN65428(int Data){ service.Set_SwingBoomSwitch_726_PGN65428(Data);}
//	public void Set_ParkingWorkingTravelStatus_510_PGN65428(int Data){ service.Set_ParkingWorkingTravelStatus_510_PGN65428(Data);}
//	public void Set_TransmissionTCLockupEngaged_556_PGN65428(int Data){ service.Set_TransmissionTCLockupEngaged_556_PGN65428(Data);}
//	public void Set_ExhaustSystemHighTemperatureLampCommand_353_PGN65428(int Data){ service.Set_ExhaustSystemHighTemperatureLampCommand_353_PGN65428(Data);}
//	public void Set_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428(int Data){ service.Set_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428(Data);}
//	public void Set_AutomaticEngineShutdown_363_PGN65428(int Data){ service.Set_AutomaticEngineShutdown_363_PGN65428(Data);}
//	public void Set_DelayedEngineShutdown_365_PGN65428(int Data){ service.Set_DelayedEngineShutdown_365_PGN65428(Data);}
//	//////TX_GAUGE_65431///////
//	public void Set_FuelLevel_301_PGN65431(int Data){ service.Set_FuelLevel_301_PGN65431(Data);}
//	public void Set_EngineCoolantTemperature_304_PGN65431(int Data){ service.Set_EngineCoolantTemperature_304_PGN65431(Data);}
//	public void Set_HydraulicOilTemperature_101_PGN65431(int Data){ service.Set_HydraulicOilTemperature_101_PGN65431(Data);}
//	public void Set_TransmissionOilTemperature_536_PGN65431(int Data){ service.Set_TransmissionOilTemperature_536_PGN65431(Data);}
//	public void Set_EngineSpeed_310_PGN65431(int Data){ service.Set_EngineSpeed_310_PGN65431(Data);}
//	public void Set_BatteryVoltage_705_PGN65431(int Data){ service.Set_BatteryVoltage_705_PGN65431(Data);}
//	//////TX_HOURMETER_CLOCK_WIPER_65433///////
//	public void Set_Hourmeter_1601_PGN65433(int Data){ service.Set_Hourmeter_1601_PGN65433(Data);}
//	public void Set_Clock_819_PGN65433(int Data){ service.Set_Clock_819_PGN65433(Data);}
//	public void Set_HourmeterActiveStatus_703_PGN65433(int Data){ service.Set_HourmeterActiveStatus_703_PGN65433(Data);}
//	public void Set_WiperOperationStatus_717_PGN65433(int Data){ service.Set_WiperOperationStatus_717_PGN65433(Data);}
//	public void Set_WiperSpeedLevel_718_PGN65433(int Data){ service.Set_WiperSpeedLevel_718_PGN65433(Data);}
//	public void Set_BacklightIlluminationLevel_719_PGN65433(int Data){ service.Set_BacklightIlluminationLevel_719_PGN65433(Data);}
//	public void Set_IlluminationStatus_737_PGN65433(int Data){ service.Set_IlluminationStatus_737_PGN65433(Data);}
//	//////TX_MACHINE_TRAVEL_STATUS_65434///////
//	public void Set_WheelBasedVehicleSpeed_532_PGN65434(int Data){ service.Set_WheelBasedVehicleSpeed_532_PGN65434(Data);}
//	public void Set_SelectGear_541_PGN65434(int Data){ service.Set_SelectGear_541_PGN65434(Data);}
//	public void Set_ActualGear_542_PGN65434(int Data){ service.Set_ActualGear_542_PGN65434(Data);}
//	public void Set_TransmissionShiftMode_543_PGN65434(int Data){ service.Set_TransmissionShiftMode_543_PGN65434(Data);}
//	public void Set_ClutchCutoffMode_544_PGN65434(int Data){ service.Set_ClutchCutoffMode_544_PGN65434(Data);}
//	public void Set_KickDownShiftMode_547_PGN65434(int Data){ service.Set_KickDownShiftMode_547_PGN65434(Data);}
//	public void Set_TransmissionTCLockupEngaged_568_PGN65434(int Data){ service.Set_TransmissionTCLockupEngaged_568_PGN65434(Data);}
//	public void Set_DifferentialLockMode_570_PGN65434(int Data){ service.Set_DifferentialLockMode_570_PGN65434(Data);}
//	public void Set_VehicleSpeedLimitMode_575_PGN65434(int Data){ service.Set_VehicleSpeedLimitMode_575_PGN65434(Data);}
//	//////TX_DTC_INFORMATION_TYPE1_65438///////
//	public void Set_DTCType_1510_PGN65438(int Data){ service.Set_DTCType_1510_PGN65438(Data);}
//	public void Set_NumberofDTCinThisPacket_1514_PGN65438(int Data){ service.Set_NumberofDTCinThisPacket_1514_PGN65438(Data);}
//	public void Set_TotalNumberofDTC_PGN65438(int Data){ service.Set_TotalNumberofDTC_PGN65438(Data);}
//	public void Set_TotalNumberofDTCInformationPacket_1512_PGN65438(int Data){ service.Set_TotalNumberofDTCInformationPacket_1512_PGN65438(Data);}
//	public void Set_SequenceNumberofDTCInformationPacket_1513_PGN65438(int Data){ service.Set_SequenceNumberofDTCInformationPacket_1513_PGN65438(Data);}
//	public void Set_DTC_1_PGN65438(byte[] Data){ service.Set_DTC_1_PGN65438(Data);}
//	public void Set_DTC_2_PGN65438(byte[] Data){ service.Set_DTC_2_PGN65438(Data);}
//	public void Set_DTC_3_PGN65438(byte[] Data){ service.Set_DTC_3_PGN65438(Data);}
//	public void Set_DTC_4_PGN65438(byte[] Data){ service.Set_DTC_4_PGN65438(Data);}
//	public void Set_DTC_5_PGN65438(byte[] Data){ service.Set_DTC_5_PGN65438(Data);}
//	//////TX_WEIGHING_SYSTEM_STATUS_65450///////
//	public void Set_WeighingSystemAccumulationMode_1941_PGN65450(int Data){ service.Set_WeighingSystemAccumulationMode_1941_PGN65450(Data);}
//	public void Set_WeighingSystemBuzzer_1907_PGN65450(int Data){ service.Set_WeighingSystemBuzzer_1907_PGN65450(Data);}
//	public void Set_CurrentWeighingResult_1919_PGN65450(int Data){ service.Set_CurrentWeighingResult_1919_PGN65450(Data);}
//	public void Set_WeighingDisplayMode1_1910_PGN65450_PGN65450(int Data){ service.Set_WeighingDisplayMode1_1910_PGN65450_PGN65450(Data);}	
//	public void Set_CurrentWeight_1911_PGN65450(int Data){ service.Set_CurrentWeight_1911_PGN65450(Data);}
//	public void Set_TodayWeight_1915_PGN65450(int Data){ service.Set_TodayWeight_1915_PGN65450(Data);}
//	//////TX_WEIGHING_SYSTEM_DATA1_65451///////
//	public void Set_TotalWorkAWeight_1912_PGN65451(int Data){ service.Set_TotalWorkAWeight_1912_PGN65451(Data);}
//	public void Set_TotalWorkBWeight_1913_PGN65451(int Data){ service.Set_TotalWorkBWeight_1913_PGN65451(Data);}
//	//////TX_WEIGHING_SYSTEM_DATA2_65452///////
//	public void Set_TotalWorkCWeight_1914_PGN65452(int Data){ service.Set_TotalWorkCWeight_1914_PGN65452(Data);}
//	public void Set_ADayBeforeWeight_1916_PGN65452(int Data){ service.Set_ADayBeforeWeight_1916_PGN65452(Data);}
//	//////TX_JOYSTICK_POSITION_STATUS_65515///////
//	public void Set_BoomJoystickPositionStatus_2310_PGN65515(int Data){ service.Set_BoomJoystickPositionStatus_2310_PGN65515(Data);}
//	public void Set_BoomJoystickPosition_2311_PGN65515(int Data){ service.Set_BoomJoystickPosition_2311_PGN65515(Data);}
//	public void Set_BucketJoystickPositionStatus_2312_PGN65515(int Data){ service.Set_BucketJoystickPositionStatus_2312_PGN65515(Data);}
//	public void Set_BucketJoystickPosition_2313_PGN65515(int Data){ service.Set_BucketJoystickPosition_2313_PGN65515(Data);}
//	public void Set_AuxJoystickPositionStatus_2314_PGN65515(int Data){ service.Set_AuxJoystickPositionStatus_2314_PGN65515(Data);}
//	public void Set_AuxJoystickPosition_2315_PGN65515(int Data){ service.Set_AuxJoystickPosition_2315_PGN65515(Data);}
//	public void Set_BoomLeverFloatingPosition_2336_PGN65515(int Data){ service.Set_BoomLeverFloatingPosition_2336_PGN65515(Data);}
//	//////TX_WHEEL_LOADER_EHCU_STATUS_65517///////
//	public void Set_BucketPriorityOperation_2301_PGN65517(int Data){ service.Set_BucketPriorityOperation_2301_PGN65517(Data);}
//	public void Set_FlowFineModulationOperation_2302_PGN65517(int Data){ service.Set_FlowFineModulationOperation_2302_PGN65517(Data);}
//	public void Set_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517(int Data){ service.Set_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517(Data);}
//	public void Set_AttachmentPilotCutoffStatus_225_PGN65517(int Data){ service.Set_AttachmentPilotCutoffStatus_225_PGN65517(Data);}
//	public void Set_FloatMode_2316_PGN65517(int Data){ service.Set_FloatMode_2316_PGN65517(Data);}
//	public void Set_JoystickSteeringActiveStatusEHCU_186_PGN65517(int Data){ service.Set_JoystickSteeringActiveStatusEHCU_186_PGN65517(Data);}
//	public void Set_BoomUpEPPRValveCurrent_2304_PGN65517(int Data){ service.Set_BoomUpEPPRValveCurrent_2304_PGN65517(Data);}
//	public void Set_BoomDownEPPRValveCurrent_2305_PGN65517(int Data){ service.Set_BoomDownEPPRValveCurrent_2305_PGN65517(Data);}
//	public void Set_BucketInEPPRValveCurrent_2306_PGN65517(int Data){ service.Set_BucketInEPPRValveCurrent_2306_PGN65517(Data);}
//	public void Set_BucketOutEPPRValveCurrent_2307_PGN65517(int Data){ service.Set_BucketOutEPPRValveCurrent_2307_PGN65517(Data);}
//	public void Set_AUX1EPPRValveCurrent_2308_PGN65517(int Data){ service.Set_AUX1EPPRValveCurrent_2308_PGN65517(Data);}
//	public void Set_AUX2EPPRValveCurrent_2309_PGN65517(int Data){ service.Set_AUX2EPPRValveCurrent_2309_PGN65517(Data);}
	//////TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
	public void Set_TravelAlarmOperationStatus_3431_PGN65527(int Data){ service.Set_TravelAlarmOperationStatus_3431_PGN65527(Data);}
	public void Set_WasherOperationStatus_3432_PGN65527(int Data){ service.Set_WasherOperationStatus_3432_PGN65527(Data);}
	public void Set_wiperoperationstatus_3433_PGN65527(int Data){ service.Set_wiperoperationstatus_3433_PGN65527(Data);}
	public void Set_IntWiperOperationStatus_3434_PGN65527(int Data){ service.Set_IntWiperOperationStatus_3434_PGN65527(Data);}
	public void Set_WorkLampOperationStatus_3435_PGN65527(int Data){ service.Set_WorkLampOperationStatus_3435_PGN65527(Data);}
	public void Set_HeadLampOperationStatus_3436_PGN65527(int Data){ service.Set_HeadLampOperationStatus_3436_PGN65527(Data);}
	public void Set_CabinLampOperationStatus_3437_PGN65527(int Data){ service.Set_CabinLampOperationStatus_3437_PGN65527(Data);}
	public void Set_IlluminationOperationStatus_3438_PGN65527(int Data){ service.Set_IlluminationOperationStatus_3438_PGN65527(Data);}
	public void Set_OutriggerFrontRHOperationStatus_3439_PGN65527(int Data){ service.Set_OutriggerFrontRHOperationStatus_3439_PGN65527(Data);}
	public void Set_OutriggerFrontLHOperationStatus_3440_PGN65527(int Data){ service.Set_OutriggerFrontLHOperationStatus_3440_PGN65527(Data);}
	public void Set_OutriggerRearRHOperationStatus_3441_PGN65527(int Data){ service.Set_OutriggerRearRHOperationStatus_3441_PGN65527(Data);}
	public void Set_OutriggerRearLHOperationStatus_3442_PGN65527(int Data){ service.Set_OutriggerRearLHOperationStatus_3442_PGN65527(Data);}
	public void Set_OverloadSwitchOperationStatus_3443_PGN65527(int Data){ service.Set_OverloadSwitchOperationStatus_3443_PGN65527(Data);}
	public void Set_BeaconLampOperationStatus_3444_PGN65527(int Data){ service.Set_BeaconLampOperationStatus_3444_PGN65527(Data);}
	public void Set_ForwardTravelAlarmOperationStatus_3445_PGN65527(int Data){ service.Set_ForwardTravelAlarmOperationStatus_3445_PGN65527(Data);}
	public void Set_RearWorkLampOperationStatus_3446_PGN65527(int Data){ service.Set_RearWorkLampOperationStatus_3446_PGN65527(Data);}
	public void Set_RideControlOperationStatus_3447_PGN65527(int Data){ service.Set_RideControlOperationStatus_3447_PGN65527(Data);}
	public void Set_QuickCouplerOperationStatus_3448_PGN65527(int Data){ service.Set_QuickCouplerOperationStatus_3448_PGN65527(Data);}
	public void Set_AutoGreaseOperationStatus_3449_PGN65527(int Data){ service.Set_AutoGreaseOperationStatus_3449_PGN65527(Data);}
	public void Set_MirrorHeatOperationStatus_3450_PGN65527(int Data){ service.Set_MirrorHeatOperationStatus_3450_PGN65527(Data);}
	public void Set_RearWiperOperationStatus_3451_PGN65527(int Data){ service.Set_RearWiperOperationStatus_3451_PGN65527(Data);}
	public void Set_RearWiperWasherOperationStatus_3452_PGN65527(int Data){ service.Set_RearWiperWasherOperationStatus_3452_PGN65527(Data);}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	public int Get_FirmwareVersionHigh(){ return service.Get_FirmwareVersionHigh();}
	public int Get_FirmwareVersionLow(){ return service.Get_FirmwareVersionLow();}
	public int Get_FirmwareVersionSubHigh(){ return service.Get_FirmwareVersionSubHigh();}
	public int Get_FirmwareVersionSubLow(){ return service.Get_FirmwareVersionSubLow();}
	public int Get_HWVersion(){ return service.Get_HWVersion();}
	public int Get_CommErrCnt(){ return service.Get_CommErrCnt();}
	public int Get_CheckBKCUComm(){ return service.Get_CheckBKCUComm();}
	
	public void Set_CommErrCnt(int Data){ service.Set_CommErrCnt(Data);}
	
	public int GET_RecvResDownFlag(){ return service.GET_RecvResDownFlag();}
	public int GET_RX_RES_DOWN_UpdateResponse(){ return service.GET_RX_RES_DOWN_UpdateResponse();}
	
	public int Get_RX_RES_KEY_LongFlag(){ return service.Get_RX_RES_KEY_LongFlag();}
	public int Get_RX_RES_KEY_Menu(){ return service.Get_RX_RES_KEY_Menu();}
	public int Get_RX_RES_KEY_ESC(){ return service.Get_RX_RES_KEY_ESC();}
	public int Get_RX_RES_KEY_Left(){ return service.Get_RX_RES_KEY_Left();}
	public int Get_RX_RES_KEY_Right(){ return service.Get_RX_RES_KEY_Right();}
	public int Get_RX_RES_KEY_Enter(){ return service.Get_RX_RES_KEY_Enter();}
	public int Get_RX_RES_KEY_CAM(){ return service.Get_RX_RES_KEY_CAM();}
	public int Get_RX_RES_KEY_MainLight(){ return service.Get_RX_RES_KEY_MainLight();}
	public int Get_RX_RES_KEY_WorkLight(){ return service.Get_RX_RES_KEY_WorkLight();}
	public int Get_RX_RES_KEY_AutoGrease(){ return service.Get_RX_RES_KEY_AutoGrease();}
	public int Get_RX_RES_KEY_QuickCouupler(){ return service.Get_RX_RES_KEY_QuickCouupler();}
	public int Get_RX_RES_KEY_RideControl(){ return service.Get_RX_RES_KEY_RideControl();}
	public int Get_RX_RES_KEY_WorkLoad(){ return service.Get_RX_RES_KEY_WorkLoad();}
	public int Get_RX_RES_KEY_BeaconLamp(){ return service.Get_RX_RES_KEY_BeaconLamp();}
	public int Get_RX_RES_KEY_RearWiper(){ return service.Get_RX_RES_KEY_RearWiper();}
	public int Get_RX_RES_KEY_MirrorHeat(){ return service.Get_RX_RES_KEY_MirrorHeat();}
	public int Get_RX_RES_KEY_DetentSetting(){ return service.Get_RX_RES_KEY_DetentSetting();}
	public int Get_RX_RES_KEY_FineModulation(){ return service.Get_RX_RES_KEY_FineModulation();}
	public int Get_RX_RES_KEY_FN(){ return service.Get_RX_RES_KEY_FN();}

	public int Get_gErr_Mcu_TotalPacket(){ return service.Get_gErr_Mcu_TotalPacket();}
	public int Get_gErr_Ecu_TotalPacket(){ return service.Get_gErr_Ecu_TotalPacket();}
	public int Get_gErr_Tcu_TotalPacket(){ return service.Get_gErr_Tcu_TotalPacket();}
	public int Get_gErr_Mcu_TotalPacket_Logged(){ return service.Get_gErr_Mcu_TotalPacket_Logged();}
	public int Get_gErr_Ecu_TotalPacket_Logged(){ return service.Get_gErr_Ecu_TotalPacket_Logged();}
	public int Get_gErr_Tcu_TotalPacket_Logged(){ return service.Get_gErr_Tcu_TotalPacket_Logged();}
	public int Get_gErr_EHCU_TotalPacket_Logged(){ return service.Get_gErr_EHCU_TotalPacket_Logged();}
	public int Get_gErr_Mcu_Total(){ return service.Get_gErr_Mcu_Total();}
	public int Get_gErr_Ecu_Total(){ return service.Get_gErr_Ecu_Total();}
	public int Get_gErr_Tcu_Total(){ return service.Get_gErr_Tcu_Total();}
	public int Get_gErr_Mcu_Total_Logged(){ return service.Get_gErr_Mcu_Total_Logged();}
	public int Get_gErr_Ecu_Total_Logged(){ return service.Get_gErr_Ecu_Total_Logged();}
	public int Get_gErr_Tcu_Total_Logged(){ return service.Get_gErr_Tcu_Total_Logged();}
	public int Get_gErr_EHCU_Total_Logged(){ return service.Get_gErr_EHCU_Total_Logged();}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////
	// CALLBACK METHOD
	///////////////////////////////////////////////////////////////////
	
	@Override
	public void Callback_KeyButton(int Data) {
		// TODO Auto-generated method stub
		int N = callbacks.beginBroadcast();
		Log.d(TAG,"Callback " + Integer.toString(N));
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).KeyButtonCallBack(Data);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		callbacks.finishBroadcast();
	}
	
	@Override
	public void Callback_EEPRomTest(int Data) {
		// TODO Auto-generated method stub
		int N = callbacks.beginBroadcast();
		Log.d(TAG,"Callback " + Integer.toString(N));
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).EEPRomCallBack(Data);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		callbacks.finishBroadcast();
	}
	
	@Override
	public void Callback_FlashTest(int Data) {
		// TODO Auto-generated method stub
		int N = callbacks.beginBroadcast();
		Log.d(TAG,"Callback " + Integer.toString(N));
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).FlashCallBack(Data);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		callbacks.finishBroadcast();
	}
	
	public static void CallbackFunc(){
		int N = callbacks.beginBroadcast();
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).CallbackFunc(1);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void Callback_CID() {
		// TODO Auto-generated method stub
		int N = callbacks.beginBroadcast();
		Log.d(TAG,"Callback " + Integer.toString(N));
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).CIDCallBack();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		callbacks.finishBroadcast();
	}
	
	@Override
	public void Callback_AS() {
		// TODO Auto-generated method stub
		int N = callbacks.beginBroadcast();
		Log.d(TAG,"Callback " + Integer.toString(N));
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).ASCallBack();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		callbacks.finishBroadcast();
	}
	
	@Override
	public void Callback_StopCommService() {
		// TODO Auto-generated method stub
		int N = callbacks.beginBroadcast();
		Log.d(TAG,"Callback " + Integer.toString(N));
		for(int i = 0; i < N; i++){
			try {
				callbacks.getBroadcastItem(i).StopCommServiceCallBack();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		callbacks.finishBroadcast();
	}

}
