
static const char *TAG = "serial_port";
//#define DEBUG_READ

JavaVM *glpVM = NULL;
int fd_UART1;
int fd_UART3;

pthread_t p_thread[5];
int thr_id[5];

jclass jObject;


#define LOGI(fmt, args...) __android_log_print(ANDROID_LOG_INFO,  TAG, fmt, ##args)
#define LOGD(fmt, args...) __android_log_print(ANDROID_LOG_DEBUG, TAG, fmt, ##args)
#define LOGE(fmt, args...) __android_log_print(ANDROID_LOG_ERROR, TAG, fmt, ##args)

////////////////////Define//////////////////////////
#define		RINGBUFF_SIZE				4096


#define		QBUFF_SIZE					17
#define 	UART1_RXPACKET_SIZE 		17
#define		UART1_PARSING_SIZE			32
#define 	UART3_RXPACKET_SIZE 		11
#define		UART3_PARSING_SIZE			1
#define		UART1_TXPACKET_SIZE			14
#define		UART3_TXPACKET_SIZE			11

#define		CMD_RECV_SIZE				17
#define		MAX_RECV_SIZE				(1024*2)			//	2048
#define		DAT_RECV_SIZE				17


#define		SERIAL_RX_STX				0x02
#define		SERIAL_RX_ID				0xF5
#define		SERIAL_RX_DATA_LEN			0x08
#define		SERIAL_RX_ETX				0x03
#define		SERIAL_RX_EOT				0x04
#define		SERIAL_RX_ACK				0x06
#define		SERIAL_RX_NAK				0x15


#define		SERIAL_RX_SAVE_DATA1		0xE1
#define		SERIAL_RX_SAVE_DATA2		0xE2
#define		SERIAL_RX_SAVE_DATA3		0xE3

//#define		KEYCMD						0x01
//#define		LCDCMD						0x02
//#define		BUZCMD						0x03
//#define		LAMPCMD						0x04
//#define		RTCCMD						0x05
//#define		CAMCMD						0x06
//#define		VERSIONHIGHCMD				0x07
//#define		VERSIONLOWCMD				0x08
//#define		VERSIONSUBCMD				0x09
//
//#define		KeyValue					0x4B
//#define		LCDValue					0x4C
//#define		BuzValue					0x42
//#define 	LampValue					0x4d
//#define		RTCValue					0x80
//#define		CAMValue					0x4e
//#define		VersionHighValue			0x50
//#define		VersionLowValue				0x51
//#define		VersionSubValue				0x52

#define		KeyCMD			0x00
#define		LCDCMD			0x01
#define		BuzCMD			0x02
#define		RTCCMD			0x03
#define		CAMCMD			0x04
#define		VersionCMD		0x05
#define		DOWNCMD			0x06
#define		SMKCMD			0x07
#define		LampCMD			0x08
#define		StartCANCMD		0x09
#define		EEPROMTESTCMD 	0x0C
#define		FLASHTESTCMD	0x0D


#define		KeyRES			0x80
#define		LCDRES			0x81
#define		BuzRES			0x82
#define		RTCRES			0x83
#define		CAMRES			0x84
#define		VersionRES		0x85
#define		DOWNRES			0x86
#define		SMKRES			0x87
#define		LampRES			0x88
#define		StartCANRES		0x89
#define		EEPROMTESTRES 	0x8C
#define		FLASHTESTRES	0x8D

#define 	BUZZERDAT_ON          		0x11    	//	Buzz On  Data
#define 	BUZZERDAT_OFF          		0x10    	//	Buzz Off Data

#define 	SA_MCU						0x47
#define		SA_MONITOR					0x28
#define		SA_CLUSTER					0x17
#define		SA_RMCU						0x4A
#define		SA_EHCU						0xE4
#define		SA_TCU						0x03
#define		SA_ECM						0x00
#define		SA_CID						0xF4
#define		SA_ACU						0x19
#define		SA_BKCU						0x34
////////////////////////////////////////////////////
#define		ERR_BUF_SIZE				400
#define		MULTI_BUF_SIZE				400

#define 	TIMER1_INTERVAL				50
#define 	KEYBUTTON_CNT_MAX			5
//////////////////Variable//////////////////////////
unsigned char		UART1_TxBuff[RINGBUFF_SIZE][UART1_TXPACKET_SIZE];
unsigned int		TxRingBuffHead;
unsigned int		TxRingBuffTail;

unsigned char		UART1_RxBuff[RINGBUFF_SIZE][UART1_TXPACKET_SIZE];
unsigned int		RxRingBuffHead;
unsigned int		RxRingBuffTail;

pthread_mutex_t mutex_UART1_tx = PTHREAD_MUTEX_INITIALIZER;
jboolean				MCU_RTSFlag;
jboolean				MCU_CTSFlag;


CAN_RX_PACKET*		UART1_Rx_Data;

unsigned char 		UART1_RxTmpOne[MAX_RECV_SIZE][UART1_RXPACKET_SIZE];
unsigned char 		UART1_RxTmpTwo[MAX_RECV_SIZE][UART1_RXPACKET_SIZE];
unsigned char		cUART1_RxData[UART1_RXPACKET_SIZE];
unsigned char		MultiPacket_ACK_MCU[8];
unsigned char		MultiPacket_ACK_RMCU[8];
int					UART1ReadFlag = 0;
unsigned char 		bReadRunningFlag_UART1;
//unsigned char		bWriteRunningFlag_UART1 = 0;
unsigned char		bWriteRunningFlag_UART1 = 0;
int					dwUART1ReadCnt = 0;
int					UART1_RxInx = 0;
unsigned char		bParsingFlag_UART1 = 0;
unsigned char		bAckFlag_MCU = 0;
unsigned char		bAckFlag_RMCU = 0;
unsigned char		nCTSFlag_MCU = 0;
unsigned char		nCTSFlag_RMCU = 0;

unsigned int		nRTSDataLength = 0;
unsigned char		RTSData[1024];
pthread_mutex_t mutex_UART1 = PTHREAD_MUTEX_INITIALIZER;




CAN_RX_PACKET*		UART3_Rx_Data;
unsigned char		UART3_ReadBuff[2048];
unsigned char 		UART3_RxTmpOne[MAX_RECV_SIZE][UART3_RXPACKET_SIZE];
unsigned char 		UART3_RxTmpTwo[MAX_RECV_SIZE][UART3_RXPACKET_SIZE];
unsigned char		cUART3_RxData[UART1_RXPACKET_SIZE];
int					UART3ReadFlag = 0;
unsigned char 		bReadRunningFlag_UART3;
unsigned char		bParsingRunningFlag_UART3 = 0;
int					dwUART3ReadCnt = 0;
int					UART3_RxInx = 0;
unsigned char		bParsingFlag_UART3 = 0;

unsigned short		nPGN;
unsigned char		nTotalPacketNum;
unsigned short		nEHCUPGN;
unsigned short		nEHCUPGN06;
unsigned char		nEHCUTotalPacketNum_E;
unsigned char		nEHCUTotalPacketNum;
unsigned char		nEHCUTotalPacketNum06;
unsigned char		nEHCUTotalError;
unsigned char		nTempEHCUTotalError;
unsigned char		ehcu_s_or_m;
unsigned char 		RecvScuError;
unsigned short		nRMCUPGN;
unsigned char		nRMCUTotalPacketNum;
unsigned short		nClusterPGN;
unsigned char 		nClusterTotalPacketNum;
unsigned short		nCIDPGN;
unsigned char 		nCIDTotalPacketNum;
unsigned short		nECMPGN;
unsigned char 		nECMTotalPacketNum;
unsigned short		nTCUPGN;
unsigned char 		nTCUTotalPacketNum;

unsigned char		gRecvMulti[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_EHCU[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_EHCU_06[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_RMCU[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_Cluster[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_ECM[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_TCU[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_CID[MULTI_BUF_SIZE];
unsigned char		gRecvMulti_BKCU[MULTI_BUF_SIZE];
unsigned char		gErr_EHCU[ERR_BUF_SIZE];

unsigned int	 	gErr_Tcu[ERR_BUF_SIZE];
unsigned int 		gErr_Ecu[ERR_BUF_SIZE];
unsigned int 		gErr_Mcu[ERR_BUF_SIZE];

unsigned int	 	gErr_Tcu_Logged[ERR_BUF_SIZE];
unsigned int 		gErr_Ecu_Logged[ERR_BUF_SIZE];
unsigned int 		gErr_Mcu_Logged[ERR_BUF_SIZE];
unsigned int 		gErr_EHCU_Logged[ERR_BUF_SIZE];

unsigned int		gErr_Mcu_TotalPacket;
unsigned int		gErr_Ecu_TotalPacket;
unsigned int		gErr_Tcu_TotalPacket;
unsigned int		gErr_EHCU_TotalPacket;
unsigned int		gErr_Mcu_TotalPacket_Logged;
unsigned int		gErr_Ecu_TotalPacket_Logged;
unsigned int		gErr_Tcu_TotalPacket_Logged;
unsigned int		gErr_EHCU_TotalPacket_Logged;

unsigned int		gErr_Mcu_Total;
unsigned int		gErr_Ecu_Total;
unsigned int		gErr_Tcu_Total;
unsigned int		gErr_EHCU_Total;
unsigned int		gErr_Mcu_Total_Logged;
unsigned int		gErr_Ecu_Total_Logged;
unsigned int		gErr_Tcu_Total_Logged;
unsigned int		gErr_EHCU_Total_Logged;

unsigned char		gRecvSingle_250[8];
unsigned char		gRecvMulti_250[21];
unsigned char		gRecvPassWord;
unsigned char 		gRecvESL;
unsigned char 		gRecvSMK;


unsigned int		CommErrCnt;

unsigned int		KeyButtonCount;

unsigned char		CheckBKCUComm;

pthread_mutex_t mutex_UART3 = PTHREAD_MUTEX_INITIALIZER;

////////////////////////////////////////////////////

/////////////////////// CAN Rx Data/////////////////

RTC								rx_rtc_data;
TCU_ERR							rx_tcu_err;
WARNING_INDICATOR_LAMP			rx_warning_lamp;
VEHICLE_TRAVEL					rx_vehi_travel;
GAUGE							rx_gauge;
HOURMETER_WIPER					rx_hr_wiper;
VEHICLE_DISTANCE				rx_vehicle_distance;
VEHICLE_STATUS1					rx_vehicle_status1;
VEHICLE_STATUS2					rx_vehicle_status2;
WHEGHING_SYSTEM_STATUS			rx_weighing_system_status;
WEIGHING_SYSTEM_DATA1			rx_weighing_system_data1;
WEIGHING_SYSTEM_DATA2			rx_weighing_system_data2;
WEIGHING_SYSTEM_DATA3			rx_weighing_system_data3;
AEB_INFORMATION					rx_aeb_info;
MACHINE_SECURITY_STATUS			rx_mach_security_stat;
MACHINE_SECURITY_STATUS_SINGLE	rx_mach_security_stat_s;
MAINTENANCE_INFORMATION2		rx_maintenance_info;
MAINTENANCE_INFORMATION3		rx_maintenance_history;
VEHICLE_INFORMATION				rx_machine_version;
WHEEL_LOADER_DASHBOARD			rx_cluster_version;
MACH_STATE						rx_machine_state;
SMK_RESULT						rx_smk_result;
EHCU_STATUS						rx_ehcu_status;
EHCU_STATUS2					rx_ehcu_status2;
JOYSTICK_POSITION_STATUS		rx_Joystick_Position_Status;
CYLINDER						rx_cylinder_angle;
Indicator_Lamp					rx_indicator_lamp;
Machine_Mode_Status				rx_Machine_Mode_Status;
MACHINE_TRAVEL_SETTING			rx_Machine_Travel_Setting;
MACHINE_SETTING_STATUS			rx_Machine_Setting_Status;
COOLING_FAN_STATUS				rx_Cooling_Fan_Status;
WARNING_LAMP					rx_Warning_Lamp;
RMCU_STATUS						rx_RMCU_Status;
COMPONENT_IDENTIFICATION		rx_Component_Identification_MCU;
COMPONENT_IDENTIFICATION		rx_Component_Identification_Cluster;
COMPONENT_IDENTIFICATION		rx_Component_Identification_RMCU;
COMPONENT_IDENTIFICATION		rx_Component_Identification_EHCU;
COMPONENT_IDENTIFICATION		rx_Component_Identification_Monitor;
COMPONENT_IDENTIFICATION_ECM	rx_Component_Identification_ECM;
COMPONENT_IDENTIFICATION_TCU	rx_Component_Identification_TCU;
ACCELERATOR_BRAKEPEDAL_STATUS	rx_AccBrakepedalStatus;
ENGINE_SHUTDOWN_MODE_SETTING	rx_Engine_Shutdown_Mode_Setting;
ENGINE_SHUTDOWN_MODE_STATUS		rx_Engine_Shutdown_Mode_Status;
WEIGHING_SYSTEM_MODE			rx_Weihing_System_Mode;


PGN_ACK							rx_hcepgn_ack;
COMM							flag_comm;
SINGLE_PACKET_PHONE_NUM			rx_single_phone_num;
MULTI_PACKET_PHONE_NUM			rx_multi_phone_num;
Realy_Control					rx_Realy_Control;

// Error
ERROR_MULTI						rx_error_code;
SCU_ERROR						rx_scu_error;

EHCU_ERROR_SINGLE				rx_error_ehcu_s;
EHCU_ERROR_MULTI				rx_error_ehcu_m;
WEIGHING_SYSTEM_ERROR			rx_Weighing_System_Error;

unsigned char					m_Buzzer_Previous;
unsigned char					m_Buzzer_Present;
jboolean						m_RecvSmkMsgResult;

//	CAN SAVE DATA
SAVE_DATA1						rx_save_data1;
SAVE_DATA2						rx_save_data2;
SAVE_DATA3						rx_save_data3;

// CMD Data
CMD_DATA						rx_CMD_Data;



////////////////////////////////////////////////////
/////////////////////// CAN TX Data/////////////////

HCEPGN_REQUEST						tx_request_hcepgn;
HCESPN_DATA_REQUEST					tx_request_hcespn_data;

ESL_PASSWORD_SETTING				tx_esl_password69;
ESL_PASSWORD_SETTING_MULTI			tx_esl_password_multi;
AS_PHONE_NUM_MULTI					tx_as_phone_num_multi;
MONITOR_REQUEST_REPLY				tx_monitor_request;
MONITOR_SETTING						tx_monitor_setting;
WHEEL_LOADER_MONITOR				tx_monitor_status;
WEIGHING_SYSTEM_MODE2				tx_weighing_sys_mode2;
RTC									tx_rtc;
EHCU								tx_ehcu_setting;
Realy_Control						tx_Realy_Control;

MACHINE_MODE_SETTING				tx_Machine_Mode_Setting;
MACHINE_TRAVEL_SETTING				tx_Machine_Travel_Setting;
MACHINE_ACCESSORY_SETTING_REQUEST 	tx_Machine_Accessory_Setting_Req;
COOLING_FAN_SETTING					tx_Cooling_Fan_Setting;
ENGINE_SHUTDOWN_MODE_SETTING		tx_Engine_Shutdown_Mode_Setting;
WEIGHING_SYSTEM_MODE				tx_Weihing_System_Mode;
DETENT_MODE_SETTING					tx_Detent_Mode_Setting;

////////////////////////////////////////////////////
#define NEW_CAN2
#ifdef NEW_CAN2
/////////////////////////////////////////////////////
unsigned char		nPF;
unsigned char		nPS;
unsigned char		nTotalPacketNum;
unsigned char		nMultiPacketMessageType;

unsigned char		nEHCUPF;
unsigned char		nEHCUPS;
unsigned char		nEHCUTotalPacketNum;
unsigned char		nEHCU06PF;
unsigned char		nEHCU06PS;
unsigned char		nEHCU06TotalPacketNum;

unsigned char		nRMCUPF;
unsigned char		nRMCUPS;
unsigned char		nRMCUTotalPacketNum;
unsigned char		nRMCUMultiPacketMessageType;

unsigned char		nClusterPF;
unsigned char		nClusterPS;
unsigned char		nClusterTotalPacketNum;

unsigned char		nECMPF;
unsigned char		nECMPS;
unsigned char		nECMTotalPacketNum;

unsigned char		nTCUPF;
unsigned char		nTCUPS;
unsigned char		nTCUTotalPacketNum;

unsigned char		nCIDPF;
unsigned char		nCIDPS;
unsigned char		nCIDTotalPacketNum;

unsigned char		nBKCUPF;
unsigned char		nBKCUPS;
unsigned char		nBKCUTotalPacketNum;

unsigned int		nRecvSeedFlag;
unsigned int		nRecvPasswordResultFlag;
unsigned int		nRecvPasswordChangeResultFlag;
unsigned int		nRecvResDownFlag;
/////////////////////// CMD Data/////////////////
RES_RTC							RX_RES_RTC;
RES_VERSION						RX_RES_Version;
RES_SMK							RX_RES_SMK;
CMD_LAMP						TX_CMD_Lamp;
RES_DOWN						RX_RES_DOWN;
RES_KEY							RX_RES_KEY;
////////////////////////////////////////////////////

///////////////CAN RX DATA//////////////////////////
DTC_INFORMATION_REQUEST_61184_11									RX_DTC_INFORMATION_REQUEST_61184_11;
MAINTENANCE_REQUSET_61184_12										RX_MAINTENANCE_REQUSET_61184_12;
MAINTENANCE_ITEM_LIST_61184_13										RX_MAINTENANCE_ITEM_LIST_61184_13;
MAINTENANCE_INFORMATION_61184_14									RX_MAINTENANCE_INFORMATION_61184_14;
MAINTENANCE_HISTORY_61184_15										RX_MAINTENANCE_HISTORY_61184_15;
MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16						RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16;
HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21						RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21;
HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22								RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22;
HCE_ANTI_THEFT_REQUEST_61184_23										RX_HCE_ANTI_THEFT_REQUEST_61184_23;
HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24						RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24;
HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25						RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25;
COOLING_FAN_SETTING_61184_61										RX_COOLING_FAN_SETTING_61184_61;
WEIGHING_SYSTEM_SETTING_REQUEST_61184_62							RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62;
WEIGHT_OFFSET_61184_63												RX_WEIGHT_OFFSET_61184_63;
PARALLEL_LIFT_OPERATION_STATUS_61184_65								RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65;
MACHINE_MODE_SETTING_61184_101										RX_MACHINE_MODE_SETTING_61184_101;
TRAVEL_MODE_SETTING_61184_104										RX_TRAVEL_MODE_SETTING_61184_104;
TRAVEL_CONTROL_VALUE_SETTING_61184_105								RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105;
TRAVEL_CONTROL_VALUE_61184_106										RX_TRAVEL_CONTROL_VALUE_61184_106;
MACHINE_ACCESSORY_SETTING_REQUEST_61184_109							RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109;
ENGINE_SHUTDOWN_MODE_SETTING_61184_121								RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121;
ENGINE_SHUTDOWN_MODE_STATUS_61184_122								RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122;
DETENT_MODE_SETTING_61184_123										RX_DETENT_MODE_SETTING_61184_123;
DETENT_MODE_STATUS_61184_124										RX_DETENT_MODE_STATUS_61184_124;
ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129							RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129;
AS_PHONE_NUMBER_SETTING_61184_151									RX_AS_PHONE_NUMBER_SETTING_61184_151;
WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201					RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201;
WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202					RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202;
WHEEL_LOADER_EHCU_SETTING_61184_203									RX_WHEEL_LOADER_EHCU_SETTING_61184_203;
MONIOTR_STATUS_65327												RX_MONIOTR_STATUS_65327;
RMCU_STATUS_65329													RX_RMCU_STATUS_65329;
COMPONENT_IDENTIFICATION_65330										RX_COMPONENT_IDENTIFICATION_65330;
COMPONENT_IDENTIFICATION_ECM_65330									RX_COMPONENT_IDENTIFICATION_ECM_65330;
COMPONENT_IDENTIFICATION_TCU_65330									RX_COMPONENT_IDENTIFICATION_TCU_65330;
COMPONENT_IDENTIFICATION_ACU_65330									RX_COMPONENT_IDENTIFICATION_ACU_65330;
TRIP_TIME_INFORMATION_65344											RX_TRIP_TIME_INFORMATION_65344;
MACHINE_SECURITY_STATUS_65348										RX_MACHINE_SECURITY_STATUS_65348;
MACHINE_MODE_STATUS_65350											RX_MACHINE_MODE_STATUS_65350;
HYDRAULIC_PRESSURE4_65354											RX_HYDRAULIC_PRESSURE4_65354;
HYDRAULIC_PRESSURE6_65356											RX_HYDRAULIC_PRESSURE6_65356;
HYDRAULIC_PRESSURE7_65357											RX_HYDRAULIC_PRESSURE7_65357;
ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360								RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360;
RELAY_BUZZER_STATUS_65364											RX_RELAY_BUZZER_STATUS_65364;
SOLENOID_STATUS_65365												RX_SOLENOID_STATUS_65365;
ACCELERATOR_BRAKE_PEDAL_STATUS_65368								RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368;
COOLING_FAN_STATUS_65369											RX_COOLING_FAN_STATUS_65369;
ENGINE_STATUS2_65370												RX_ENGINE_STATUS2_65370;
ENGINE_STATUS1_65371												RX_ENGINE_STATUS1_65371;
AIR_CONDITIONER_STATUS_65373										RX_AIR_CONDITIONER_STATUS_65373;
VEHICLE_DISTANCE_65389												RX_VEHICLE_DISTANCE_65389;
FUEL_INFORMATION_ECO_GAUGE_65390									RX_FUEL_INFORMATION_ECO_GAUGE_65390;
CYLINDER_ANGLE_STROKE1_65395										RX_CYLINDER_ANGLE_STROKE1_65395;
CYLINDER_ANGLE_STROKE2_65396										RX_CYLINDER_ANGLE_STROKE2_65396;
AS_PHONE_NUMBER_65425												RX_AS_PHONE_NUMBER_65425;
WARNING_LAMP_65427													RX_WARNING_LAMP_65427;
INDICATOR_LAMP_65428												RX_INDICATOR_LAMP_65428;
GAUGE_65431															RX_GAUGE_65431;
HOURMETER_CLOCK_WIPER_65433											RX_HOURMETER_CLOCK_WIPER_65433;
MACHINE_TRAVEL_STATUS_65434											RX_MACHINE_TRAVEL_STATUS_65434;
DTC_INFORMATION_TYPE1_65438											RX_DTC_INFORMATION_TYPE1_65438;
WEIGHING_SYSTEM_STATUS_65450										RX_WEIGHING_SYSTEM_STATUS_65450;
WEIGHING_SYSTEM_DATA1_65451											RX_WEIGHING_SYSTEM_DATA1_65451;
WEIGHING_SYSTEM_DATA2_65452											RX_WEIGHING_SYSTEM_DATA2_65452;
BKCU_STATUS_65514													RX_BKCU_STATUS_65514;
JOYSTICK_POSITION_STATUS_65515										RX_JOYSTICK_POSITION_STATUS_65515;
WHEEL_LOADER_EHCU_STATUS_65517										RX_WHEEL_LOADER_EHCU_STATUS_65517;
WHEEL_LOADER_EHCU_STATUS2_65524										RX_WHEEL_LOADER_EHCU_STATUS2_65524;
ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527						RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527;


COMPONENT_IDENTIFICATION_65330										RX_COMPONENT_IDENTIFICATION_CLUSTER_65330;
COMPONENT_IDENTIFICATION_65330										RX_COMPONENT_IDENTIFICATION_MONITOR_65330;
COMPONENT_IDENTIFICATION_65330										RX_COMPONENT_IDENTIFICATION_RMCU_65330;
COMPONENT_IDENTIFICATION_65330										RX_COMPONENT_IDENTIFICATION_EHCU_65330;
COMPONENT_IDENTIFICATION_65330										RX_COMPONENT_IDENTIFICATION_BKCU_65330;

////////////////////////////////////////////////////
///////////////CAN TX DATA//////////////////////////

DTC_INFORMATION_REQUEST_61184_11									TX_DTC_INFORMATION_REQUEST_61184_11;
MAINTENANCE_REQUSET_61184_12										TX_MAINTENANCE_REQUSET_61184_12;
MAINTENANCE_ITEM_LIST_61184_13										TX_MAINTENANCE_ITEM_LIST_61184_13;
MAINTENANCE_INFORMATION_61184_14									TX_MAINTENANCE_INFORMATION_61184_14;
MAINTENANCE_HISTORY_61184_15										TX_MAINTENANCE_HISTORY_61184_15;
MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16						TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16;
HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21						TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21;
HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22								TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22;
HCE_ANTI_THEFT_REQUEST_61184_23										TX_HCE_ANTI_THEFT_REQUEST_61184_23;
HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24						TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24;
HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25						TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25;
COOLING_FAN_SETTING_61184_61										TX_COOLING_FAN_SETTING_61184_61;
WEIGHING_SYSTEM_SETTING_REQUEST_61184_62							TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62;
WEIGHT_OFFSET_61184_63												TX_WEIGHT_OFFSET_61184_63;
MACHINE_MODE_SETTING_61184_101										TX_MACHINE_MODE_SETTING_61184_101;
TRAVEL_MODE_SETTING_61184_104										TX_TRAVEL_MODE_SETTING_61184_104;
TRAVEL_CONTROL_VALUE_SETTING_61184_105								TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105;
TRAVEL_CONTROL_VALUE_61184_106										TX_TRAVEL_CONTROL_VALUE_61184_106;
MACHINE_ACCESSORY_SETTING_REQUEST_61184_109							TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109;
ENGINE_SHUTDOWN_MODE_SETTING_61184_121								TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121;
ENGINE_SHUTDOWN_MODE_STATUS_61184_122								TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122;
DETENT_MODE_SETTING_61184_123										TX_DETENT_MODE_SETTING_61184_123;
DETENT_MODE_STATUS_61184_124										TX_DETENT_MODE_STATUS_61184_124;
ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129							TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129;
AS_PHONE_NUMBER_SETTING_61184_151									TX_AS_PHONE_NUMBER_SETTING_61184_151;
WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201					TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201;
WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202					TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202;
WHEEL_LOADER_EHCU_SETTING_61184_203									TX_WHEEL_LOADER_EHCU_SETTING_61184_203;
MONIOTR_STATUS_65327												TX_MONIOTR_STATUS_65327;
RMCU_STATUS_65329													TX_RMCU_STATUS_65329;
COMPONENT_IDENTIFICATION_65330										TX_COMPONENT_IDENTIFICATION_65330;
COMPONENT_IDENTIFICATION_ECM_65330									TX_COMPONENT_IDENTIFICATION_ECM_65330;
COMPONENT_IDENTIFICATION_TCU_65330									TX_COMPONENT_IDENTIFICATION_TCU_65330;
TRIP_TIME_INFORMATION_65344											TX_TRIP_TIME_INFORMATION_65344;
MACHINE_SECURITY_STATUS_65348										TX_MACHINE_SECURITY_STATUS_65348;
MACHINE_MODE_STATUS_65350											TX_MACHINE_MODE_STATUS_65350;
HYDRAULIC_PRESSURE4_65354											TX_HYDRAULIC_PRESSURE4_65354;
HYDRAULIC_PRESSURE6_65356											TX_HYDRAULIC_PRESSURE6_65356;
HYDRAULIC_PRESSURE7_65357											TX_HYDRAULIC_PRESSURE7_65357;
ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360								TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360;
RELAY_BUZZER_STATUS_65364											TX_RELAY_BUZZER_STATUS_65364;
SOLENOID_STATUS_65365												TX_SOLENOID_STATUS_65365;
ACCELERATOR_BRAKE_PEDAL_STATUS_65368								TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368;
COOLING_FAN_STATUS_65369											TX_COOLING_FAN_STATUS_65369;
ENGINE_STATUS1_65371												TX_ENGINE_STATUS1_65371;
VEHICLE_DISTANCE_65389												TX_VEHICLE_DISTANCE_65389;
FUEL_INFORMATION_ECO_GAUGE_65390									TX_FUEL_INFORMATION_ECO_GAUGE_65390;
CYLINDER_ANGLE_STROKE1_65395										TX_CYLINDER_ANGLE_STROKE1_65395;
AS_PHONE_NUMBER_65425												TX_AS_PHONE_NUMBER_65425;
WARNING_LAMP_65427													TX_WARNING_LAMP_65427;
INDICATOR_LAMP_65428												TX_INDICATOR_LAMP_65428;
GAUGE_65431															TX_GAUGE_65431;
HOURMETER_CLOCK_WIPER_65433											TX_HOURMETER_CLOCK_WIPER_65433;
MACHINE_TRAVEL_STATUS_65434											TX_MACHINE_TRAVEL_STATUS_65434;
DTC_INFORMATION_TYPE1_65438											TX_DTC_INFORMATION_TYPE1_65438;
WEIGHING_SYSTEM_STATUS_65450										TX_WEIGHING_SYSTEM_STATUS_65450;
WEIGHING_SYSTEM_DATA1_65451											TX_WEIGHING_SYSTEM_DATA1_65451;
WEIGHING_SYSTEM_DATA2_65452											TX_WEIGHING_SYSTEM_DATA2_65452;
JOYSTICK_POSITION_STATUS_65515										TX_JOYSTICK_POSITION_STATUS_65515;
WHEEL_LOADER_EHCU_STATUS_65517										TX_WHEEL_LOADER_EHCU_STATUS_65517;
ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527						TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527;

COMPONENT_IDENTIFICATION_65330										TX_COMPONENT_IDENTIFICATION_MONITOR_65330;
////////////////////////////////////////////////////


#endif


