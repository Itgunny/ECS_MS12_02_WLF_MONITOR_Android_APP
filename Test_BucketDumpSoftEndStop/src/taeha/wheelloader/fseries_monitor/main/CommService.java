package taeha.wheelloader.fseries_monitor.main;

import java.io.FileDescriptor;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Instrumentation;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;

public class CommService extends Service{

	/////////////////VALUABLES///////////////////////////////////////////
	// TAG
	private static final String TAG = "CommService";
	// COMPOART
	private static final int UART1Baudrate = 115200;
	private static final String UART1ComPort = "/dev/ttySAC1";
	private FileDescriptor mFdUART1;
	private static final int UART3Baudrate = 115200;
	private static final String UART3ComPort = "/dev/ttySAC3";
	private FileDescriptor mFdUART3;
	public boolean topCheckMiracast = false;
	
	// SOUND
	private static SoundPool SoundPoolKeyButton;
	static int	SoundID;
	static float fVolume;
	private static SoundPool SoundPoolKeyButtonEnding;
	static int	SoundIDEnding;
	static float fVolumeEnding;
	// Comm Manager
	static CAN1CommManager CAN1Comm;
	
	// BindFlag
	private static boolean BindFlag = false;
	
	// My App Top Flag
	private static boolean ScreenTopFlag = true;
	
	// FN Flag
	private static boolean FNFlag	= true;
	
	// Timer
	protected static Timer mBuzzerStopTimer = null;

	// Handler
	static Handler HandleKeyButton;
	
	// MediaPlayer
	// ++, 150211 bwk
	//private static boolean rpmFlag = false;
	// --, 150211 bwk

	// ++, 150320 cjg
	public static boolean multimediaFlag = false;
	public static boolean miracastFlag = false;
	// --, 150320 cjg	
	// ++, 150403 cjg
	public static boolean powerOffFlag = false;
	public static int endingSoundCount = 0;
	public static int endingKeyIGCount = 0;
	// --, 150403 cjg
	public static PackageInfo pi = null;
	/////////////////////////////////////////////////////////////////////
	
	//////////////////LOAD NATIVELIBRARY/////////////////////////////////
	static{
		try {
			System.loadLibrary("can_serial_port");
			System.loadLibrary("can_data_parsing");
			System.loadLibrary("LineOutclientjni");
			System.loadLibrary("issynctrack"); 		// ++, --, 150615 cjg
		} catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG,"Load Library Error");
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	//////////////////NATIVE METHOD/////////////////////////////////////
	public native int native_system_updates();
	public native int native_system_sync();		// ++, --, 150615 cjg
	public native int LineOutfromJNI_Native(int spk);
	
	public native int[] Get_TcuErr_FromNative();
	public native int[] Get_EcuErr_FromNative();
	public native int[] Get_McuErr_FromNative();
	public native int[] Get_EHCUErr_FromNative();
	public native int[] Get_TcuErr_Logged_FromNative();
	public native int[] Get_EcuErr_Logged_FromNative();
	public native int[] Get_EHCUErr_Logged_FromNative();
	public native int[] Get_McuErr_Logged_FromNative();
	
	public native int Get_EHCUSingleOrMulti_FromNative();
	public native int Get_EHCUTotalError_FromNative();
	public native int Get_EHCUTotalPacket_FromNative();
	public native int Get_EHCUSingleErrorData_FromNative();
	public native int Get_EHCUErrorData_FromNative(int Data);
	
	public native int Get_RecvESL_Flag_FromNative();
	public native int Get_RecvSMK_Flag_FromNative();
	public native int Get_SmkAuthResult_FromNative();
	public native int Get_SmkMsgResult_FromNative();
	public native int Get_SmkRegTagCount_FromNative();
	public native int Get_RTColock_Year();
	public native int Get_RTColock_Month();
	public native int Get_RTColock_Date();
	public native int Get_RTColock_Sec();
	public native int Get_RTColock_Hour_FromNative();
	public native int Get_RTColock_Min_FromNative();
	public native int GET_Buzzer_FromNative();

	public native FileDescriptor Open_UART1(String path, int baudrate, int flag);
	public native void Close_UART1();				
	public native int Write_UART1(byte[] Data, int size);
	public native int UART1_TxComm(int PS);
	public native FileDescriptor Open_UART3(String path, int baudrate, int flag);
	public native void Close_UART3();				// UART 1만 close 됨
	public native int Write_UART3(byte[] Data, int size);
	public native int UART3_TxComm(int CMD, int DAT1, int DAT2, int DAT3, int DAT4, int DAT5, int DAT6, int DAT7, int DAT8);
	
	public native void SetFNKeypadLamp(int Data);
	
	///////////////////////////NEW CAN2//////////////////////////////////////
	public native int UART1_Tx(int PF, int PS, int Flag);
	//////RX_DTC_INFORMATION_REQUEST_61184_11///////
	public native int Get_MessageType_PGN61184_11();
	public native int Get_DTCInformationRequest_1515_PGN61184_11();
	public native int Get_DTCType_1510_PGN61184_11();
	public native int Get_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11();
	//////RX_MAINTENANCE_REQUSET_61184_12///////
	public native int Get_MessageType_PGN61184_12();
	public native int Get_MaintenanceCommant_1097_PGN61184_12();
	public native int Get_MaintenanceItem_1098_PGN61184_12();
	public native int Get_MaintenanceInterval_1091_PGN61184_12();
	//////RX_MAINTENANCE_ITEM_LIST_61184_13///////
	public native int Get_MessageType_PGN61184_13();
	public native int Get_TotalNumberofMaintenanceItems_1100_PGN61184_13();
	public native byte[] Get_MaintenanceItem_1098_PGN61184_13();
	//////RX_MAINTENANCE_INFORMATION_61184_14///////
	public native int Get_MessageType_PGN61184_14();
	public native int Get_MaintenanceItem_1098_PGN61184_14();
	public native int Get_MaintenanceAlarmLamp_1099_PGN61184_14();
	public native int Get_MaintenanceEvent_PGN61184_14();
	public native int Get_MaintenanceInterval_1091_PGN61184_14();
	public native int Get_MaintenanceTotalCount_1092_PGN61184_14();
	public native int Get_HourmeterattheLastMaintenance_1093_PGN61184_14();
	//////RX_MAINTENANCE_HISTORY_61184_15///////
	public native int Get_MessageType_PGN61184_15();
	public native int Get_MaintenanceItem_1098_PGN61184_15();
	public native int Get_HourmeterattheLastMaintenance_1093_PGN61184_15();
	public native short[] Get_MaintenanceHistory_1094_PGN61184_15();
	//////RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
	public native int Get_MessageType_PGN61184_16();
	public native int Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16();
	public native byte[] Get_MaintenanceItem_PGN61184_16();
	//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
	public native int Get_MessageType_PGN61184_21();
	//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
	public native int Get_MessageType_PGN61184_22();
	public native byte[] Get_HCEAntiTheftRandomNumber_1632_PGN61184_22();
	public native int Get_RecvSeedFlag_PGN61184_22();
	//////RX_HCE_ANTI_THEFT_REQUEST_61184_23///////
	public native int Get_MessageType_PGN61184_23();
	public native int Get_HCEAntiTheftCommand_1633_PGN61184_23();
	public native int Get_ESLMode_820_PGN61184_23();
	public native int Get_ESLInterval_825_PGN61184_23();
	public native byte[] Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23();
	//////RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
	public native int Get_MessageType_PGN61184_24();
	public native int Get_PasswordCertificationResult_956_PGN61184_24();
	public native int Get_RecvPasswordResultFlag_PGN61184_24();
	//////RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
	public native int Get_MessageType_PGN61184_25();
	public native int Get_PasswordChangeResult_958_PGN61184_25();
	public native byte[] Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25();
	public native int Get_RecvPasswordChangeResultFlag_PGN61184_25();
	//////RX_AVERAGE_FUEL_RATE_HISTORY_61184_33///////
	public native int Get_MessageType_PGN61184_33();
	public native int Get_OperationHistoryType_1101_PGN61184_33();
	public native int Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_4th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_5th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_6th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_7th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33();
	public native int Get_8th_HourAverageFuelRate_333_PGN61184_33();
	public native int Get_9th_HourAverageFuelRate_333_PGN61184_33();
	public native int Get_10th_HourAverageFuelRate_333_PGN61184_33();
	public native int Get_11th_HourAverageFuelRate_333_PGN61184_33();
	public native int Get_12th_HourAverageFuelRate_333_PGN61184_33();
	//////RX_COOLING_FAN_SETTING_61184_61///////
	public native int Get_MessageType_PGN61184_61();
	public native int Get_TestMode_PGN61184_61();
	public native int Get_CoolingFanReverseMode_182_PGN61184_61();
	public native int Get_CoolingFanValveCurrent_146_PGN61184_61();
	public native int Get_CoolingFanReverseIntervalTime_211_PGN61184_61();
	public native int Get_CoolingFanReverseOperatingTime_212_PGN61184_61();
	//////RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
	public native int Get_MessageType_PGN61184_62();
	public native int Get_WeighingSystemAccumulationMode_1941_PGN61184_62();
	public native int Get_RequestReweighing_PGN61184_62();
	public native int Get_RequestTotalWorkWeightReset_PGN61184_62();
	public native int Get_WeightOffsetSelection_PGN61184_62();
	public native int Get_WeightOffsetSetting_PGN61184_62();
	public native int Get_WeightOffset_1922_PGN61184_62();
	public native int Get_WeighingDisplayMode1_1910_PGN61184_62();
	//////RX_WEIGHT_OFFSET_61184_63///////
	public native int Get_MessageType_PGN61184_63();
	public native int Get_WeightOffsetSelectionStatus_PGN61184_63();
	public native int Get_ErrorSuddenChange_PGN61184_63();
	public native int Get_ErrorBucketFullIn_PGN61184_63();	
	public native int Get_WeightOffsetWorkTool1_1922_PGN61184_63();
	public native int Get_WeightOffsetWorkTool2_1922_PGN61184_63();
	public native int Get_WeightOffsetWorkTool3_1922_PGN61184_63();
	//////PARALLEL_LIFT_OPERATION_STATUS_61184_65///////
	public native int Get_MessageType_PGN61184_65();
	public native int Get_ParallelLiftTargetAngleSettingErrorStatus_1924_PGN61184_65();
	public native int Get_ParallelLiftInhibitedStatus_1925_PGN61184_65();
	public native int Get_ParallelLiftOverloadStatus_1926_PGN61184_65();
	//////RX_MACHINE_MODE_SETTING_61184_101///////
	public native int Get_MessageType_PGN61184_101();
	public native int Get_EngineAlternateLowIdleSwitch_348_PGN61184_101();
	public native int Get_EnginePowerMode_347_PGN61184_101();
	//////RX_TRAVEL_MODE_SETTING_61184_104///////
	public native int Get_MessageType_PGN61184_104();
	public native int Get_TransmisstionShiftMode_543_PGN61184_104();
	public native int Get_ClutchCutoffMode_544_PGN61184_104();
	public native int Get_KickDownShiftMode_547_PGN61184_104();
	public native int Get_TransmissionTCLockupEngaged_568_PGN61184_104();
	public native int Get_VehicleSpeedLimitMode_575_PGN61184_104();
	//////RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
	public native int Get_MessageType_PGN61184_105();
	public native int Get_SettingSelection_PGN61184_105();
	public native int Get_SpeedometerFrequency_534_PGN61184_105();
	public native int Get_AutoRideControlOperationSpeedForward_574_PGN61184_105();
	public native int Get_AutoRideControlOperationSpeedBackward_576_PGN61184_105();
	public native int Get_VehicleSpeedLimit_572_PGN61184_105();
	//////RX_TRAVEL_CONTROL_VALUE_61184_106///////
	public native int Get_MessageType_PGN61184_106();
	public native int Get_SpeedometerFrequency_534_PGN61184_106();
	public native int Get_AutoRideControlOperationSpeedForward_574_PGN61184_106();
	public native int Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106();
	public native int Get_VehicleSpeedLimit_572_PGN61184_106();
	//////RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
	public native int Get_MessageType_PGN61184_109();
	public native int Get_BacklightIlluminationLevel_719_PGN61184_109();
	public native int Get_WiperSpeedLevel_718_PGN61184_109();
	public native int Get_Clock_819_PGN61184_109();
	public native int Get_RequestEngineLowIdleSpeed_PGN61184_109();
	public native int Get_RequestTripDataReset_PGN61184_109();
	//////RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
	public native int Get_MessageType_PGN61184_121();
	public native int Get_AutomaticEngineShutdown_363_PGN61184_121();
	public native int Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121();
	public native int Get_EngineShutdownCotrolByte_PGN61184_121();
	public native int Get_DelayedEngineShutdown_365_PGN61184_121();
	public native int Get_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121();
	//////RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
	public native int Get_MessageType_PGN61184_122();
	public native int Get_AutomaticEngineShutdown_363_PGN61184_122();
	public native int Get_AutomaticEngineShutdownType_PGN61184_122();
	public native int Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122();
	public native int Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122();
	public native int Get_SettingTimeforDelayedEngineShutdown_366_PGN61184_122();
	public native int Get_RemainingTimeforDelayedEngineShutdown_PGN61184_122();
	//////RX_DETENT_MODE_SETTING_61184_123///////
	public native int Get_MessageType_PGN61184_123();
	public native int Get_BoomDetentMode_223_PGN61184_123();
	public native int Get_BucketDetentMode_224_PGN61184_123();
	public native int Get_RequestDetentReleasePositionSetting_PGN61184_123();
	//////RX_DETENT_MODE_STATUS_61184_124///////
	public native int Get_MessageType_PGN61184_124();
	public native int Get_BoomDetentMode_223_PGN61184_124();
	public native int Get_BucketDetentMode_224_PGN61184_124();
	public native int Get_DetentReleasePositionSettingStatus_PGN61184_124();
	//////RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
	public native int Get_MessageType_PGN61184_129();
	public native int Get_EngineShutdownCommand_3472_PGN61184_129();
	//////RX_AS_PHONE_NUMBER_SETTING_61184_151///////
	public native int Get_MessageType_PGN61184_151();
	public native byte[] Get_ASPhoneNumber_PGN61184_151();
	//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
	public native int Get_MessageType_PGN61184_201();
	public native int Get_RequestBoomPressureCalibration_PGN61184_201();
	public native int Get_RequestBoomBucketAngleSensorCalibration_PGN61184_201();
	public native int Get_RequestAEB_PGN61184_201();
	public native int Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201();
	public native int Get_RequestBucketDumpCalibration_PGN61184_201();
	//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
	public native int Get_MessageType_PGN61184_202();
	public native int Get_BoomPressureCalibrationStatus_1908_PGN61184_202();
	public native int Get_AngleSensorCalibrationStatus_1909_PGN61184_202();
	public native int Get_AEBStatusInformation_MainCode_562_PGN61184_202();
	public native int Get_AEBCycleNumber_540_PGN61184_202();
	public native int Get_AEBStatusInformation_SubCode_563_PGN61184_202();
	public native int Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202();
	public native int Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202();
	public native int Get_RequestBucketDumpSpeedCalibrationStatus_1945_PGN61184_202();
	public native int Get_BoomPositionCalibrationError_1946_PGN61184_202();
	public native int Get_BucketPositionCalibrationError_1947_PGN61184_202();
	//////RX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
	public native int Get_MessageType_PGN61184_203();
	public native int Get_BucketPriorityOperation_2301_PGN61184_203();
	public native int Get_FlowFineModulationOperation_2302_PGN61184_203();
	public native int Get_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203();
	public native int Get_BoomLeverFloatingPosition_PGN61184_203();
	//////RX_MONIOTR_STATUS_65327///////
	public native int Get_RequestBuzzerStop_PGN65327();
	public native int Get_SpeedmeterUnitChange_PGN65327();
	//////RX_RMCU_STATUS_65329///////
	public native int Get_RMCUNetworkType_1621_PGN65329();
	public native int Get_RMCUBackupBatteryVoltage_1590_PGN65329();
	public native int Get_RMCUPowerSource_1594_PGN65329();
	public native int Get_RMCUBoxOpeningStatus_PGN65329();
	public native int Get_NetworkCommunicationStatus1_1622_PGN65329();
	public native int Get_PositionUpdateStatus_852_PGN65329();
	public native int Get_MachinePositionStatus_1593_PGN65329();
	public native int Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329();
	public native int Get_NetworkTransceiverStatus1_1623_PGN65329();
	public native int Get_NetworkServiceStatus1_1624_PGN65329();
	public native int Get_NetworkAntennaStatus1_1625_PGN65329();
	public native int Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329();
	//////RX_COMPONENT_IDENTIFICATION_65330///////
	public native int Get_ComponentCode_1699_PGN65330();
	public native int Get_ManufacturerCode_1700_PGN65330();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330();
	//////RX_COMPONENT_IDENTIFICATION_ECM_65330///////
	public native int Get_ComponentCode_1699_PGN65330_ECM();
	public native int Get_ManufacturerCode_1700_PGN65330_ECM();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_ECM();
	//////RX_COMPONENT_IDENTIFICATION_CLUSTER_65330///////
	public native int Get_ComponentCode_1699_PGN65330_CLUSTER();
	public native int Get_ManufacturerCode_1700_PGN65330_CLUSTER();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_CLUSTER();
	//////RX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
	public native int Get_ComponentCode_1699_PGN65330_MONITOR();
	public native int Get_ManufacturerCode_1700_PGN65330_MONITOR();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_MONITOR();
	//////RX_COMPONENT_IDENTIFICATION_EHCU_65330///////
	public native int Get_ComponentCode_1699_PGN65330_EHCU();
	public native int Get_ManufacturerCode_1700_PGN65330_EHCU();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_EHCU();
	//////RX_COMPONENT_IDENTIFICATION_RMCU_65330///////
	public native int Get_ComponentCode_1699_PGN65330_RMCU();
	public native int Get_ManufacturerCode_1700_PGN65330_RMCU();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_RMCU();
	//////RX_COMPONENT_IDENTIFICATION_TCU_65330///////
	public native int Get_ComponentCode_1699_PGN65330_TCU();
	public native int Get_ManufacturerCode_1700_PGN65330_TCU();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_TCU();
	//////RX_COMPONENT_IDENTIFICATION_ACU_65330///////
	public native int Get_ComponentCode_1699_PGN65330_ACU();
	public native int Get_ManufacturerCode_1700_PGN65330_ACU();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_ACU();
	//////RX_COMPONENT_IDENTIFICATION_BKCU_65330///////
	public native int Get_ComponentCode_1699_PGN65330_BKCU();
	public native int Get_ManufacturerCode_1700_PGN65330_BKCU();
	public native byte[] Get_ComponentBasicInformation_1698_PGN65330_BKCU();
	//////RX_TRIP_TIME_INFORMATION_65344///////
	public native int Get_TripTime_849_PGN65344();
	//////RX_MACHINE_SECURITY_STATUS_65348///////
	public native int Get_ESLMode_820_PGN65348();
	public native int Get_LockMode_822_PGN65348();
	public native int Get_LockLevel_823_PGN65348();
	public native int Get_LockDemander_824_PGN65348();
	public native int Get_EngineStartingPermission_821_PGN65348();
	public native int Get_PasswordIdentificationBlockedState_1631_PGN65348();
	public native int Get_ESLInterval_825_PGN65348();
	//////RX_MACHINE_MODE_STATUS_65350///////
	public native int Get_EngineAlternateLowIdelSwitch_348_PGN65350();
	public native int Get_EnginePowerMode_347_PGN65350();
	//////RX_HYDRAULIC_PRESSURE4_65354///////
	public native int Get_BrakeOilPressure_503_PGN65354();
	public native int Get_BrakeOilChargingPriorityPressure_557_PGN65354();
	//////RX_HYDRAULIC_PRESSURE6_65356///////
	public native int Get_BoomCylinderHeadPressure_204_PGN65356();
	public native int Get_BoomCylinderRodPressure_205_PGN65356();
	//////RX_HYDRAULIC_PRESSURE7_65357///////
	public native int Get_SteeringMainPumpPressure_202_PGN65357();
	public native int Get_EmergencySteeringPumpPressure_203_PGN65357();
	public native int Get_ParkingOilPressure_507_PGN65357();
	public native int Get_DifferentialLockPressure_558_PGN65357();
	//////RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
	public native int Get_AlternatorVoltage_707_PGN65360();
	//////RX_RELAY_BUZZER_STATUS_65364///////
	public native int Get_AntiRestartRelay_327_PGN65364();
	public native int Get_FuelWarmerRelay_325_PGN65364();
	public native int Get_EnginePreheatRelay_322_PGN65364();
	public native int Get_EngineStopRelay_345_PGN65364();
	public native int Get_WorkingCutoffRelay_164_PGN65364();
	public native int Get_TravelingCutoffRelay_517_PGN65364();
	public native int Get_ParkingRelay_514_PGN65364();
	public native int Get_WiperRelay_727_PGN65364();
	public native int Get_EmergencySteeringPumpRelay_187_PGN65364();
	public native int Get_TravelAlarmBuzzer_722_PGN65364();
	public native int Get_Buzzer_723_PGN65364();
	public native int Get_FuelCutoffRelay_324_PGN65364();
	//////RX_SOLENOID_STATUS_65365///////
	public native int Get_BoomUpLeverDetentSolenoid_172_PGN65365();
	public native int Get_BoomDownLeverDetentSolenoid_173_PGN65365();
	public native int Get_BucketLeverDetentSolenoid_174_PGN65365();
	//////RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
	public native int Get_AcceleratorPedalPositionVoltage1_710_PGN65368();
	public native int Get_BrakePedalPositionVoltage_573_PGN65368();
	public native int Get_AcceleratorPedalPosition1_339_PGN65368();
	public native int Get_AcceleratorPedalPositionVoltage2_710_PGN65368();
	//////RX_COOLING_FAN_STATUS_65369///////
	public native int Get_CoolingFanReverseMode_182_PGN65369();
	public native int Get_CoolingFanReverseSolenoid_181_PGN65369();
	public native int Get_CoolingFanReverseSwitchManual_740_PGN65369();
	public native int Get_CoolingFanReverseSwitchAuto_741_PGN65369();
	public native int Get_CoolingFanValveCurrent_146_PGN65369();
	public native int Get_CoolingFanSpeed_318_PGN65369();
	public native int Get_CoolingFanReverseIntervalTime_211_PGN65369();
	public native int Get_CoolingFanReverseOperatingTime_212_PGN65369();
	public native int Get_FanSpeedMaxControlMode_210_PGN65369();
	//////RX_ENGINE_STATUS2_65370///////
	public native int Get_EngineFuelRate_331_PGN65370();
	public native int Get_EnginePercentLoadatCurrentSpeed_334_PGN65370();
	public native int Get_EngineActualPercentTorque_335_PGN65370();
	//////RX_ENGINE_STATUS1_65371///////
	public native int Get_EngineOperatingCondition_336_PGN65371();
	public native int Get_EngineIntakeManifold1Temperatue_329_PGN65371();
	public native int Get_EngineFuelTemperature_330_PGN65371();
	public native int Get_EngineOilPressure_311_PGN65371();
	public native int Get_BarometricPressure_328_PGN65371();
	public native int Get_EngineAirIntakePressure_337_PGN65371();
	public native int Get_DEFTankLevel_362_PGN65371();
	//////RX_AIR_CONDITIONER_STATUS_65373///////
	public native int Get_FATCSettingTemperatureCelsius_3408_PGN65373();
	public native int Get_FATCSettingTemperatureFahrenheit_3409_PGN65373();
	public native int Get_Ambienttemperaturesensoropen_PGN65373();
	public native int Get_Ambienttemperaturesensorshort_PGN65373();
	public native int Get_Incabtemperaturesensoropen_PGN65373();
	public native int Get_Incabtemperaturesensorshort_PGN65373();
	public native int Get_Evaptemperaturesensoropen_PGN65373();
	public native int Get_Evaptemperaturesensorshort_PGN65373();
	public native int Get_Mode1actuatoropenshort_PGN65373();
	public native int Get_Mode1actuatordrivecircuitmalfunction_PGN65373();
	public native int Get_Intakeactuatoropenshort_PGN65373();
	public native int Get_Intakeactuatordrivecircuitmalfunction_PGN65373();
	public native int Get_Temperatureactuatoropenshort_PGN65373();
	public native int Get_Temperatureactuatordrivecircuitmalfunction_PGN65373();
	public native int Get_Ducttemperaturesensoropen_PGN65373();
	public native int Get_Ducttemperaturesensorshort_PGN65373();
	public native int Get_WaterValveSensorError_PGN65373();
	public native int Get_GPSCircuitError_PGN65373();
	//////RX_VEHICLE_DISTANCE_65389///////
	public native int Get_TripDistance_600_PGN65389();
	public native int Get_TotalVehicleDistance_601_PGN65389();
	///////RX_FUEL_INFORMATION_ECO_GAUGE_65390////////////////
	public native int Get_FuelLevel_302_PGN65390();
	public native int Get_EcoGaugeLevel_1304_PGN65390();
	public native int Get_EcoGaugeStatus_1305_PGN65390();
	public native int Get_AverageFuelRate_333_PGN65390();
	public native int Get_ADaysFuelUsed_1405_PGN65390();
	//////RX_CYLINDER_ANGLE_STROKE1_65395///////
	public native int Get_BoomLinkAngle_1920_PGN65395();
	public native int Get_BellCrankAngle_1921_PGN65395();
	public native int Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395();
	public native int Get_BellCrankAngleSensorSignalVoltage_729_PGN65395();
	public native int Get_BucketCylinderStroke_1930_PGN65395();
	//////RX_CYLINDER_ANGLE_STROKE2_65396///////
	public native int Get_BucketAngle_1931_PGN65396();
	//////RX_AS_PHONE_NUMBER_65425///////
	public native byte[] Get_ASPhoneNumber_1095_PGN65425();
	//////RX_WARNING_LAMP_65427///////
	public native int Get_FuelLevelLow_303_PGN65427();
	public native int Get_HydraulicOilTemperatureHigh_102_PGN65427();
	public native int Get_BatteryVoltageLow_706_PGN65427();
	public native int Get_Overload_104_PGN65427();
	public native int Get_AirCleanerClog_317_PGN65427();
	public native int Get_EngineCheckLamp_320_PGN65427();
	public native int Get_TransmissionOilPressureLow_502_PGN65427();
	public native int Get_BrakeOilPressureLow_504_PGN65427();
	public native int Get_EngineCoolantTemperatureHigh_305_PGN65427();
	public native int Get_EngineCoolantLevelLow_307_PGN65427();
	public native int Get_EngineOilPressureLow_313_PGN65427();
	public native int Get_EngineOilFilterClog_315_PGN65427();
	public native int Get_TransmissionOilTemperatureHigh_537_PGN65427();
	public native int Get_TransmissionCheck_538_PGN65427();
	public native int Get_SteeringMainPumpPressureLow_184_PGN65427();
	public native int Get_EmergencySteeringActive_185_PGN65427();
	public native int Get_WarningSymbolLamp_709_PGN65427();
	public native int Get_WaterInFuelIndicator_360_PGN65427();
	public native int Get_DTCAlarmLamp_1509_PGN65427();
	public native int Get_EngineStopLamp_319_PGN65427();
	public native int Get_DPFLampCommand_352_PGN65427();
	public native int Get_DEFLowLamp_358_PGN65427();
	public native int Get_ClutchSlip_569_PGN65427();
	public native int Get_BrakeOilLevelLow_566_PGN65427();
	public native int Get_BrakeOilTemperatureHigh_567_PGN65427();
	public native int Get_EmissionSystemFailLamp_357_PGN65427();
	//////RX_INDICATOR_LAMP_65428///////
	public native int Get_PowerMaxStatus_802_PGN65428();
	public native int Get_DecelerationStatus_803_PGN65428();
	public native int Get_WarmingUpStatus_804_PGN65428();
	public native int Get_EnginePreheatStatus_323_PGN65428();
	public native int Get_FuelWarmerActiveStatus_326_PGN65428();
	public native int Get_CoolingFandrivingStatus_180_PGN65428();
	public native int Get_CruiseStatus_519_PGN65428();
	public native int Get_ParkingStatus_508_PGN65428();
	public native int Get_ClutchCutoffStatus_545_PGN65428();
	public native int Get_FNRJoystickActiveStatus_546_PGN65428();
	public native int Get_RideControlStatus_550_PGN65428();
	public native int Get_DifferentialLockStatus_559_PGN65428();
	public native int Get_JoystickSteeringActiveStatus_186_PGN65428();
	public native int Get_InchingStatus_549_PGN65428();
	public native int Get_RamLockStatus_520_PGN65428();
	public native int Get_WorkingBrakeStatus_506_PGN65428();
	public native int Get_MaintenanceAlarmLamp_1099_PGN65428();
	public native int Get_OperatorAbsenceStatus_832_PGN65428();
	public native int Get_MirrorHeaterStatus_724_PGN65428();
	public native int Get_HighBeamStatus_725_PGN65428();
	public native int Get_TravelAlarmSwitch_721_PGN65428();
	public native int Get_SwingBoomSwitch_726_PGN65428();
	public native int Get_ParkingWorkingTravelStatus_510_PGN65428();
	public native int Get_TransmissionTCLockupEngaged_556_PGN65428();
	public native int Get_ExhaustSystemHighTemperatureLampCommand_353_PGN65428();
	public native int Get_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428();
	public native int Get_AutomaticEngineShutdown_363_PGN65428();
	public native int Get_DelayedEngineShutdown_365_PGN65428();
	public native int Get_AutoGreaseGreenLEDStatus_3453_PGN65428();
	public native int Get_AutoGreaseRedLEDStatus_3453_PGN65428();
	//////RX_GAUGE_65431///////
	public native int Get_FuelLevel_301_PGN65431();
	public native int Get_EngineCoolantTemperature_304_PGN65431();
	public native int Get_HydraulicOilTemperature_101_PGN65431();
	public native int Get_TransmissionOilTemperature_536_PGN65431();
	public native int Get_EngineSpeed_310_PGN65431();
	public native int Get_BatteryVoltage_705_PGN65431();
	//////RX_HOURMETER_CLOCK_WIPER_65433///////
	public native int Get_Hourmeter_1601_PGN65433();
	public native int Get_Clock_819_PGN65433();
	public native int Get_HourmeterActiveStatus_703_PGN65433();
	public native int Get_WiperOperationStatus_717_PGN65433();
	public native int Get_WiperSpeedLevel_718_PGN65433();
	public native int Get_BacklightIlluminationLevel_719_PGN65433();
	public native int Get_IlluminationStatus_737_PGN65433();
	//////RX_MACHINE_TRAVEL_STATUS_65434///////
	public native int Get_WheelBasedVehicleSpeed_532_PGN65434();
	public native int Get_SelectGear_541_PGN65434();
	public native int Get_ActualGear_542_PGN65434();
	public native int Get_TransmissionShiftMode_543_PGN65434();
	public native int Get_ClutchCutoffMode_544_PGN65434();
	public native int Get_KickDownShiftMode_547_PGN65434();
	public native int Get_TransmissionTCLockupEngaged_568_PGN65434();
	public native int Get_DifferentialLockMode_570_PGN65434();
	public native int Get_VehicleSpeedLimitMode_575_PGN65434();
	//////RX_DTC_INFORMATION_TYPE1_65438///////
	public native int Get_DTCType_1510_PGN65438();
	public native int Get_NumberofDTCinThisPacket_1514_PGN65438();
	public native int Get_TotalNumberofDTC_PGN65438();
	public native int Get_TotalNumberofDTCInformationPacket_1512_PGN65438();
	public native int Get_SequenceNumberofDTCInformationPacket_1513_PGN65438();
	public native byte[] Get_DTC_1_PGN65438();
	public native byte[] Get_DTC_2_PGN65438();
	public native byte[] Get_DTC_3_PGN65438();
	public native byte[] Get_DTC_4_PGN65438();
	public native byte[] Get_DTC_5_PGN65438();
	//////RX_CLUSTER_STATUS_65445///////
	public native int Get_Dashboard_Program_Version_988_PGN65445();
	public native int Get_Dashboard_Hardware_Version_989_PGN65445();
	public native int Get_Mirror_Heater_Status_724_PGN65445();
	public native int Get_High_Beam_Status_725_PGN65445();
	public native int Get_Front_Rear_Lamp_Status_726_PGN65445();
	public native int Get_Dashboard_Program_Version2_PGN65445();
	public native int Get_Dashboard_Program_Version_H_PGN65445();
	public native int Get_HW_Vers_Sub_PGN65445();
	//////RX_AXLE_STATUS_65449///////
	public native int Get_Front_Axle_Oil_Temperature_577_PGN65449();
	public native int Get_Rear_Axle_Oil_Temperature_578_PGN65449();
	public native int Get_Front_Axle_Oil_Temp_Warning_580_PGN65449();
	public native int Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449();
	//////RX_WEIGHING_SYSTEM_STATUS_65450///////
	public native int Get_WeighingSystemAccumulationMode_1941_PGN65450();
	public native int Get_WeighingSystemBuzzer_1907_PGN65450();
	public native int Get_CurrentWeighingResult_1919_PGN65450();
	public native int Get_WeighingDisplayMode1_1910_PGN65450();
 	// ++, 150212 bwk
	public native int Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450();
	public native int Get_WeighingSystemError_BucketFullIn_1943_PGN65450();
	public native int Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450();
	// --, 150212 bwk
 	public native int Get_CurrentWeight_1911_PGN65450();
	public native int Get_TodayWeight_1915_PGN65450();
	//////RX_WEIGHING_SYSTEM_DATA1_65451///////
	public native int Get_TotalWorkAWeight_1912_PGN65451();
	public native int Get_TotalWorkBWeight_1913_PGN65451();
	//////RX_WEIGHING_SYSTEM_DATA2_65452///////
	public native int Get_TotalWorkCWeight_1914_PGN65452();
	public native int Get_ADayBeforeWeight_1916_PGN65452();
	//////RX_BKCU_STATUS_65514///////
	public native int Get_ButtonKeyPosition_3471_PGN65514();
	//////RX_JOYSTICK_POSITION_STATUS_65515///////
	public native int Get_BoomJoystickPositionStatus_2310_PGN65515();
	public native int Get_BoomJoystickPosition_2311_PGN65515();
	public native int Get_BucketJoystickPositionStatus_2312_PGN65515();
	public native int Get_BucketJoystickPosition_2313_PGN65515();
	public native int Get_AuxJoystickPositionStatus_2314_PGN65515();
	public native int Get_AuxJoystickPosition_2315_PGN65515();
	public native int Get_BoomLeverFloatingPosition_2336_PGN65515();
	//////RX_WHEEL_LOADER_EHCU_STATUS_65517///////
	public native int Get_BucketPriorityOperation_2301_PGN65517();
	public native int Get_FlowFineModulationOperation_2302_PGN65517();
	public native int Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517();
	public native int Get_AttachmentPilotCutoffStatus_225_PGN65517();
	public native int Get_FloatMode_2316_PGN65517();
	public native int Get_JoystickSteeringActiveStatusEHCU_186_PGN65517();
	public native int Get_BoomUpEPPRValveCurrent_2304_PGN65517();
	public native int Get_BoomDownEPPRValveCurrent_2305_PGN65517();
	public native int Get_BucketInEPPRValveCurrent_2306_PGN65517();
	public native int Get_BucketOutEPPRValveCurrent_2307_PGN65517();
	public native int Get_AUX1EPPRValveCurrent_2308_PGN65517();
	public native int Get_AUX2EPPRValveCurrent_2309_PGN65517();
	//////RX_AMBIENT_CONDITIONS_65519///////
	public native int Get_AmbientTemperature_3411_PGN65519();
	public native int Get_InCabTemperature_3412_PGN65519();
	public native int Get_DuctTemperature_3413_PGN65519();
	//////RX_WHEEL_LOADER_EHCU_STATUS2_65524///////
	public native int Get_SoftStopBoomUp_2337_PGN65524();
	public native int Get_SoftStopBoomDown_2338_PGN65524();
	public native int Get_SoftStopBucketIn_2339_PGN65524();
	public native int Get_SoftStopBucketOut_2340_PGN65524();
	public native int Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524();
	public native int Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524();
	public native int Get_JoystickSteeringEnableFailCondition_2343_PGN65524();
	//////RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
	public native int Get_TravelAlarmOperationStatus_3431_PGN65527();
	public native int Get_WasherOperationStatus_3432_PGN65527();
	public native int Get_wiperoperationstatus_3433_PGN65527();
	public native int Get_IntWiperOperationStatus_3434_PGN65527();
	public native int Get_WorkLampOperationStatus_3435_PGN65527();
	public native int Get_HeadLampOperationStatus_3436_PGN65527();
	public native int Get_CabinLampOperationStatus_3437_PGN65527();
	public native int Get_IlluminationOperationStatus_3438_PGN65527();
	public native int Get_OutriggerFrontRHOperationStatus_3439_PGN65527();
	public native int Get_OutriggerFrontLHOperationStatus_3440_PGN65527();
	public native int Get_OutriggerRearRHOperationStatus_3441_PGN65527();
	public native int Get_OutriggerRearLHOperationStatus_3442_PGN65527();
	public native int Get_OverloadSwitchOperationStatus_3443_PGN65527();
	public native int Get_BeaconLampOperationStatus_3444_PGN65527();
	public native int Get_ForwardTravelAlarmOperationStatus_3445_PGN65527();
	public native int Get_RearWorkLampOperationStatus_3446_PGN65527();
	public native int Get_RideControlOperationStatus_3447_PGN65527();
	public native int Get_QuickCouplerOperationStatus_3448_PGN65527();
	public native int Get_AutoGreaseOperationStatus_3449_PGN65527();
	public native int Get_MirrorHeatOperationStatus_3450_PGN65527();
	public native int Get_RearWiperOperationStatus_3451_PGN65527();
	public native int Get_RearWiperWasherOperationStatus_3452_PGN65527();		
	//////TX_HCEPGN_REQUEST_59904//////
	public native void Set_TargetSourceAddress(int Data);
	public native void Set_HCEPGN_PGN59904(int Data);
	//////TX_DTC_INFORMATION_REQUEST_61184_11///////
	public native void Set_MessageType_PGN61184_11(int Data);
	public native void Set_DTCInformationRequest_1515_PGN61184_11(int Data);
	public native void Set_DTCType_1510_PGN61184_11(int Data);
	public native void Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(int Data);
	//////TX_MAINTENANCE_REQUSET_61184_12///////
	public native void Set_MessageType_PGN61184_12(int Data);
	public native void Set_MaintenanceCommant_1097_PGN61184_12(int Data);
	public native void Set_MaintenanceItem_1098_PGN61184_12(int Data);
	public native void Set_MaintenanceInterval_1091_PGN61184_12(int Data);
	//////TX_MAINTENANCE_ITEM_LIST_61184_13///////
	public native void Set_MessageType_PGN61184_13(int Data);
	public native void Set_TotalNumberofMaintenanceItems_1100_PGN61184_13(int Data);
	public native void Set_MaintenanceItem_1098_PGN61184_13(byte[] Data);
	//////TX_MAINTENANCE_INFORMATION_61184_14///////
	public native void Set_MessageType_PGN61184_14(int Data);
	public native void Set_MaintenanceItem_1098_PGN61184_14(int Data);
	public native void Set_MaintenanceAlarmLamp_1099_PGN61184_14(int Data);
	public native void Set_MaintenanceEvent_PGN61184_14(int Data);
	public native void Set_MaintenanceInterval_1091_PGN61184_14(int Data);
	public native void Set_MaintenanceTotalCount_1092_PGN61184_14(int Data);
	public native void Set_HourmeterattheLastMaintenance_1093_PGN61184_14(int Data);
	//////TX_MAINTENANCE_HISTORY_61184_15///////
	public native void Set_MessageType_PGN61184_15(int Data);
	public native void Set_MaintenanceItem_1098_PGN61184_15(int Data);
	public native void Set_HourmeterattheLastMaintenance_1093_PGN61184_15(int Data);
	public native void Set_MaintenanceHistory_1094_PGN61184_15(byte[] Data);
	//////TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
	public native void Set_MessageType_PGN61184_16(int Data);
	public native void Set_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16(int Data);
	public native void Set_MaintenanceItem_PGN61184_16(byte[] Data);
	//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
	public native void Set_MessageType_PGN61184_21(int Data);
	//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
	public native void Set_MessageType_PGN61184_22(int Data);
	public native void Set_HCEAntiTheftRandomNumber_1632_PGN61184_22(byte[] Data);
	//////TX_HCE_ANTI_THEFT_REQUEST_61184_23///////
	public native void Set_MessageType_PGN61184_23(int Data);
	public native void Set_HCEAntiTheftCommand_1633_PGN61184_23(int Data);
	public native void Set_ESLMode_820_PGN61184_23(int Data);
	public native void Set_ESLInterval_825_PGN61184_23(int Data);
	public native void Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(byte[] Data);
	//////TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
	public native void Set_MessageType_PGN61184_24(int Data);
	public native void Set_PasswordCertificationResult_956_PGN61184_24(int Data);
	//////TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
	public native void Set_MessageType_PGN61184_25(int Data);
	public native void Set_PasswordChangeResult_958_PGN61184_25(int Data);
	public native void Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25(byte[] Data);
	//////TX_OPERATION_HISTORY_REQUEST_61184_31///////
	public native void Set_MessageType_PGN61184_31(int Data);
	public native void Set_OperationHistory_1101_PGN61184_31(int Data);
	//////TX_COOLING_FAN_SETTING_61184_61///////
	public native void Set_MessageType_PGN61184_61(int Data);
	public native void Set_TestMode_PGN61184_61(int Data);
	public native void Set_CoolingFanReverseMode_182_PGN61184_61(int Data);
	public native void Set_CoolingFanReverseManual_PGN61184_61(int Data);
	public native void Set_CoolingFanValveCurrent_146_PGN61184_61(int Data);
	public native void Set_CoolingFanReverseIntervalTime_211_PGN61184_61(int Data);
	public native void Set_CoolingFanReverseOperatingTime_212_PGN61184_61(int Data);
	public native void Set_FanSpeedMaxControlMode_210_PGN61184_61(int Data);	
	//////TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
	public native void Set_MessageType_PGN61184_62(int Data);
	public native void Set_WeighingSystemAccumulationMode_1941_PGN61184_62(int Data);
	public native void Set_RequestReweighing_PGN61184_62(int Data);
	public native void Set_RequestTotalWorkWeightReset_PGN61184_62(int Data);
	public native void Set_WeightOffsetSelection_PGN61184_62(int Data);
	public native void Set_WeightOffsetSetting_PGN61184_62(int Data);
	public native void Set_WeightOffset_1922_PGN61184_62(int Data);
	public native void Set_WeighingDisplayMode1_1910_PGN61184_62(int Data);
	//////TX_WEIGHT_OFFSET_61184_63///////
	public native void Set_MessageType_PGN61184_63(int Data);
	public native void Set_WeightOffsetSelectionStatus_PGN61184_63(int Data);
	public native void Set_WeightOffsetWorkTool1_1922_PGN61184_63(int Data);
	public native void Set_WeightOffsetWorkTool2_1922_PGN61184_63(int Data);
	public native void Set_WeightOffsetWorkTool3_1922_PGN61184_63(int Data);
	//////TX_MACHINE_MODE_SETTING_61184_101///////
	public native void Set_MessageType_PGN61184_101(int Data);
	public native void Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(int Data);
	public native void Set_EnginePowerMode_347_PGN61184_101(int Data);
	//////TX_TRAVEL_MODE_SETTING_61184_104///////
	public native void Set_MessageType_PGN61184_104(int Data);
	public native void Set_TransmisstionShiftMode_543_PGN61184_104(int Data);
	public native void Set_ClutchCutoffMode_544_PGN61184_104(int Data);
	public native void Set_KickDownShiftMode_547_PGN61184_104(int Data);
	public native void Set_TransmissionTCLockupEngaged_568_PGN61184_104(int Data);
	public native void Set_VehicleSpeedLimitMode_575_PGN61184_104(int Data);
	//////TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
	public native void Set_MessageType_PGN61184_105(int Data);
	public native void Set_SettingSelection_PGN61184_105(int Data);
	public native void Set_SpeedometerFrequency_534_PGN61184_105(int Data);
	public native void Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(int Data);
	public native void Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(int Data);
	public native void Set_VehicleSpeedLimit_572_PGN61184_105(int Data);
	//////TX_TRAVEL_CONTROL_VALUE_61184_106///////
	public native void Set_MessageType_PGN61184_106(int Data);
	public native void Set_SpeedometerFrequency_534_PGN61184_106(int Data);
	public native void Set_AutoRideControlOperationSpeedForward_574_PGN61184_106(int Data);
	public native void Set_AutoRideControlOperationSpeedBackward_576_PGN61184_106(int Data);
	public native void Set_VehicleSpeedLimit_572_PGN61184_106(int Data);
	//////TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
	public native void Set_MessageType_PGN61184_109(int Data);
	public native void Set_BacklightIlluminationLevel_719_PGN61184_109(int Data);
	public native void Set_WiperSpeedLevel_718_PGN61184_109(int Data);
	public native void Set_Clock_819_PGN61184_109(int Data);
	public native void Set_RequestEngineLowIdleSpeed_PGN61184_109(int Data);
	public native void Set_RequestTripDataReset_PGN61184_109(int Data);
	//////TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
	public native void Set_MessageType_PGN61184_121(int Data);
	public native void Set_AutomaticEngineShutdown_363_PGN61184_121(int Data);
	public native void Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(int Data);
	public native void Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(int Data);
	public native void Set_EngineShutdownCotrolByte_PGN61184_121(int Data);
	public native void Set_DelayedEngineShutdown_365_PGN61184_121(int Data);
	public native void Set_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121(int Data);
	//////TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
	public native void Set_MessageType_PGN61184_122(int Data);
	public native void Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122(int Data);
	public native void Set_RemainingTimeforAutomaticEngineShutdown_PGN61184_122(int Data);
	public native void Set_SettingTimeforDelayedEngineShutdown_366_PGN61184_122(int Data);
	public native void Set_RemainingTimeforDelayedEngineShutdown_PGN61184_122(int Data);
	//////TX_DETENT_MODE_SETTING_61184_123///////
	public native void Set_MessageType_PGN61184_123(int Data);
	public native void Set_BoomDetentMode_223_PGN61184_123(int Data);
	public native void Set_BucketDetentMode_224_PGN61184_123(int Data);
	public native void Set_RequestDetentReleasePositionSetting_PGN61184_123(int Data);
	//////TX_DETENT_MODE_STATUS_61184_124///////
	public native void Set_MessageType_PGN61184_124(int Data);
	public native void Set_BoomDetentMode_223_PGN61184_124(int Data);
	public native void Set_BucketDetentMode_224_PGN61184_124(int Data);
	public native void Set_DetentReleasePositionSettingStatus_PGN61184_124(int Data);
	//////TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
	public native void Set_MessageType_PGN61184_129(int Data);
	public native void Set_EngineShutdownCommand_3472_PGN61184_129(int Data);
	//////TX_AS_PHONE_NUMBER_SETTING_61184_151///////
	public native void Set_MessageType_PGN61184_151(int Data);
	public native void Set_ASPhoneNumber_PGN61184_151(byte[] Data);
	//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
	public native void Set_MessageType_PGN61184_201(int Data);
	public native void Set_RequestBoomPressureCalibration_PGN61184_201(int Data);
	public native void Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(int Data);
	public native void Set_RequestAEB_PGN61184_201(int Data);
	public native void Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(int Data);
	public native void Set_RequestBucketDumpCalibration_PGN61184_201(int Data);
	//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
	public native void Set_MessageType_PGN61184_202(int Data);
	public native void Set_BoomPressureCalibrationStatus_1908_PGN61184_202(int Data);
	public native void Set_AngleSensorCalibrationStatus_1909_PGN61184_202(int Data);
	public native void Set_AEBStatusInformation_MainCode_562_PGN61184_202(int Data);
	public native void Set_AEBCycleNumber_540_PGN61184_202(int Data);
	public native void Set_AEBStatusInformation_SubCode_563_PGN61184_202(int Data);
	public native void Set_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202(int Data);
	public native void Set_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202(int Data);
	public native void Set_RequestBucketDumpSpeedCalibrationStatus_1945_PGN61184_202(int Data);
	public native void Set_BoomPositionCalibrationError_1946_PGN61184_202(int Data);
	public native void Set_BucketPositionCalibrationError_1947_PGN61184_202(int Data);
	//////TX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
	public native void Set_MessageType_PGN61184_203(int Data);
	public native void Set_BucketPriorityOperation_2301_PGN61184_203(int Data);
	public native void Set_FlowFineModulationOperation_2302_PGN61184_203(int Data);
	public native void Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(int Data);
	public native void Set_BoomLeverFloatingPosition_PGN61184_203(int Data);
	public native void Set_SoftStopBoomUp_2337_PGN61184_203(int Data);
	public native void Set_SoftStopBoomDown_2338_PGN61184_203(int Data);
	public native void Set_SoftStopBucketIn_2339_PGN61184_203(int Data);
	public native void Set_SoftStopBucketOut_2340_PGN61184_203(int Data);
	public native void Set_BoomDownSpeedAdjust_PGN61184_203(int Data);
	public native void Set_BucketOutSpeedAdjust_PGN61184_203(int Data);
	//////TX_MONIOTR_STATUS_65327///////
	public native void Set_RequestBuzzerStop_PGN65327(int Data);
	public native void Set_SpeedmeterUnitChange_PGN65327(int Data);
	public native void Set_MonitorScreenNumber_836_PGN65327(int Data);
	public native void Set_MachineSerialNumber2_962_PGN65327(byte[] Data);
	//////TX_RMCU_STATUS_65329///////
	public native void Set_RMCUNetworkType_1621_PGN65329(int Data);
	public native void Set_RMCUBackupBatteryVoltage_1590_PGN65329(int Data);
	public native void Set_RMCUPowerSource_1594_PGN65329(int Data);
	public native void Set_RMCUBoxOpeningStatus_PGN65329(int Data);
	public native void Set_NetworkCommunicationStatus1_1622_PGN65329(int Data);
	public native void Set_PositionUpdateStatus_852_PGN65329(int Data);
	public native void Set_MachinePositionStatus_1593_PGN65329(int Data);
	public native void Set_GPSAntennaConnectionAlarmStatus_1595_PGN65329(int Data);
	public native void Set_NetworkTransceiverStatus1_1623_PGN65329(int Data);
	public native void Set_NetworkServiceStatus1_1624_PGN65329(int Data);
	public native void Set_NetworkAntennaStatus1_1625_PGN65329(int Data);
	public native void Set_RMCUData_NumberofMessagestoTransmit_855_PGN65329(int Data);
	//////TX_COMPONENT_IDENTIFICATION_65330///////
	public native void Set_ComponentCode_1699_PGN65330(int Data);
	public native void Set_ManufacturerCode_1700_PGN65330(int Data);
	public native void Set_ComponentBasicInformation_1698_PGN65330(byte[] Data);
	//////TX_COMPONENT_IDENTIFICATION_ECM_65330///////
	public native void Set_ComponentCode_1699_PGN65330_ECM(int Data);
	public native void Set_ManufacturerCode_1700_PGN65330_ECM(int Data);
	public native void Set_ComponentBasicInformation_1698_PGN65330_ECM(byte[] Data);
	//////TX_COMPONENT_IDENTIFICATION_TCU_65330///////
	public native void Set_ComponentCode_1699_PGN65330_TCU(int Data);
	public native void Set_ManufacturerCode_1700_PGN65330_TCU(int Data);
	public native void Set_ComponentBasicInformation_1698_PGN65330_TCU(byte[] Data);
	//////TX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
	public native void Set_ComponentCode_1699_PGN65330_MONITOR(int Data);
	public native void Set_ManufacturerCode_1700_PGN65330_MONITOR(int Data);
	public native void Set_ComponentBasicInformation_1698_PGN65330_MONITOR(byte[] Data);
	//////TX_TRIP_TIME_INFORMATION_65344///////
	public native void Set_TripTime_849_PGN65344(int Data);
	//////TX_MACHINE_SECURITY_STATUS_65348///////
	public native void Set_ESLMode_820_PGN65348(int Data);
	public native void Set_LockMode_822_PGN65348(int Data);
	public native void Set_LockLevel_823_PGN65348(int Data);
	public native void Set_LockDemander_824_PGN65348(int Data);
	public native void Set_EngineStartingPermission_821_PGN65348(int Data);
	public native void Set_PasswordIdentificationBlockedState_1631_PGN65348(int Data);
	public native void Set_ESLInterval_825_PGN65348(int Data);
	//////TX_MACHINE_MODE_STATUS_65350///////
	public native void Set_EngineAlternateLowIdelSwitch_348_PGN65350(int Data);
	public native void Set_EnginePowerMode_347_PGN65350(int Data);
	//////TX_HYDRAULIC_PRESSURE4_65354///////
	public native void Set_BrakeOilPressure_503_PGN65354(int Data);
	public native void Set_BrakeOilChargingPriorityPressure_557_PGN65354(int Data);
	//////TX_HYDRAULIC_PRESSURE6_65356///////
	public native void Set_BoomCylinderHeadPressure_204_PGN65356(int Data);
	public native void Set_BoomCylinderRodPressure_205_PGN65356(int Data);
	//////TX_HYDRAULIC_PRESSURE7_65357///////
	public native void Set_SteeringMainPumpPressure_202_PGN65357(int Data);
	public native void Set_EmergencySteeringPumpPressure_203_PGN65357(int Data);
	public native void Set_ParkingOilPressure_507_PGN65357(int Data);
	public native void Set_DifferentialLockPressure_558_PGN65357(int Data);
	//////TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
	public native void Set_AlternatorVoltage_707_PGN65360(int Data);
	//////TX_RELAY_BUZZER_STATUS_65364///////
	public native void Set_AntiRestartRelay_327_PGN65364(int Data);
	public native void Set_FuelWarmerRelay_325_PGN65364(int Data);
	public native void Set_EnginePreheatRelay_322_PGN65364(int Data);
	public native void Set_EngineStopRelay_345_PGN65364(int Data);
	public native void Set_WorkingCutoffRelay_164_PGN65364(int Data);
	public native void Set_TravelingCutoffRelay_517_PGN65364(int Data);
	public native void Set_ParkingRelay_514_PGN65364(int Data);
	public native void Set_WiperRelay_727_PGN65364(int Data);
	public native void Set_EmergencySteeringPumpRelay_187_PGN65364(int Data);
	public native void Set_TravelAlarmBuzzer_722_PGN65364(int Data);
	public native void Set_Buzzer_723_PGN65364(int Data);
	public native void Set_FuelCutoffRelay_324_PGN65364(int Data);
	//////TX_SOLENOID_STATUS_65365///////
	public native void Set_BoomUpLeverDetentSolenoid_172_PGN65365(int Data);
	public native void Set_BoomDownLeverDetentSolenoid_173_PGN65365(int Data);
	public native void Set_BucketLeverDetentSolenoid_174_PGN65365(int Data);
	//////TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
	public native void Set_AcceleratorPedalPositionVoltage1_710_PGN65368(int Data);
	public native void Set_BrakePedalPositionVoltage_573_PGN65368(int Data);
	public native void Set_AcceleratorPedalPosition1_339_PGN65368(int Data);
	public native void Set_AcceleratorPedalPositionVoltage2_710_PGN65368(int Data);
	//////TX_COOLING_FAN_STATUS_65369///////
	public native void Set_CoolingFanReverseMode_182_PGN65369(int Data);
	public native void Set_CoolingFanReverseSolenoid_181_PGN65369(int Data);
	public native void Set_CoolingFanReverseSwitchManual_740_PGN65369(int Data);
	public native void Set_CoolingFanReverseSwitchAuto_741_PGN65369(int Data);
	public native void Set_CoolingFanValveCurrent_146_PGN65369(int Data);
	public native void Set_CoolingFanSpeed_318_PGN65369(int Data);
	public native void Set_CoolingFanReverseIntervalTime_211_PGN65369(int Data);
	public native void Set_CoolingFanReverseOperatingTime_212_PGN65369(int Data);
	public native void Set_FanSpeedMaxControlMode_210_PGN65369(int Data);	
	//////TX_ENGINE_STATUS1_65371///////
	public native void Set_EngineOperatingCondition_336_PGN65371(int Data);
	public native void Set_DEFTankLevel_362_PGN65371(int Data);
	//////TX_VEHICLE_DISTANCE_65389///////
	public native void Set_TripDistance_600_PGN65389(int Data);
	public native void Set_TotalVehicleDistance_601_PGN65389(int Data);
	//////TX_CYLINDER_ANGLE_STROKE1_65395///////
	public native void Set_BoomLinkAngle_1920_PGN65395(int Data);
	public native void Set_BellCrankAngle_1921_PGN65395(int Data);
	public native void Set_BoomLinkAngleSensorSignalVoltage_728_PGN65395(int Data);
	public native void Set_BellCrankAngleSensorSignalVoltage_729_PGN65395(int Data);
	public native void Set_BucketCylinderStroke_1930_PGN65395(int Data);
	//////TX_AS_PHONE_NUMBER_65425///////
	public native void Set_ASPhoneNumber_1095_PGN65425(byte[] Data);
	//////TX_WARNING_LAMP_65427///////
	public native void Set_FuelLevelLow_303_PGN65427(int Data);
	public native void Set_HydraulicOilTemperatureHigh_102_PGN65427(int Data);
	public native void Set_BatteryVoltageLow_706_PGN65427(int Data);
	public native void Set_Overload_104_PGN65427(int Data);
	public native void Set_AirCleanerClog_317_PGN65427(int Data);
	public native void Set_EngineCheckLamp_320_PGN65427(int Data);
	public native void Set_TransmissionOilPressureLow_502_PGN65427(int Data);
	public native void Set_BrakeOilPressureLow_504_PGN65427(int Data);
	public native void Set_EngineCoolantTemperatureHigh_305_PGN65427(int Data);
	public native void Set_EngineCoolantLevelLow_307_PGN65427(int Data);
	public native void Set_EngineOilPressureLow_313_PGN65427(int Data);
	public native void Set_EngineOilFilterClog_315_PGN65427(int Data);
	public native void Set_TransmissionOilTemperatureHigh_537_PGN65427(int Data);
	public native void Set_TransmissionCheck_538_PGN65427(int Data);
	public native void Set_SteeringMainPumpPressureLow_184_PGN65427(int Data);
	public native void Set_EmergencySteeringActive_185_PGN65427(int Data);
	public native void Set_WarningSymbolLamp_709_PGN65427(int Data);
	public native void Set_WaterInFuelIndicator_360_PGN65427(int Data);
	public native void Set_DTCAlarmLamp_1509_PGN65427(int Data);
	public native void Set_EngineStopLamp_319_PGN65427(int Data);
	public native void Set_DPFLampCommand_352_PGN65427(int Data);
	public native void Set_DEFLowLamp_358_PGN65427(int Data);
	public native void Set_ClutchSlip_569_PGN65427(int Data);
	public native void Set_BrakeOilLevelLow_566_PGN65427(int Data);
	public native void Set_BrakeOilTemperatureHigh_567_PGN65427(int Data);
	public native void Set_EmissionSystemFailLamp_357_PGN65427(int Data);
	//////TX_INDICATOR_LAMP_65428///////
	public native void Set_PowerMaxStatus_802_PGN65428(int Data);
	public native void Set_DecelerationStatus_803_PGN65428(int Data);
	public native void Set_WarmingUpStatus_804_PGN65428(int Data);
	public native void Set_EnginePreheatStatus_323_PGN65428(int Data);
	public native void Set_FuelWarmerActiveStatus_326_PGN65428(int Data);
	public native void Set_CoolingFandrivingStatus_180_PGN65428(int Data);
	public native void Set_CruiseStatus_519_PGN65428(int Data);
	public native void Set_ParkingStatus_508_PGN65428(int Data);
	public native void Set_ClutchCutoffStatus_545_PGN65428(int Data);
	public native void Set_FNRJoystickActiveStatus_546_PGN65428(int Data);
	public native void Set_RideControlStatus_550_PGN65428(int Data);
	public native void Set_DifferentialLockStatus_559_PGN65428(int Data);
	public native void Set_JoystickSteeringActiveStatus_186_PGN65428(int Data);
	public native void Set_InchingStatus_549_PGN65428(int Data);
	public native void Set_RamLockStatus_520_PGN65428(int Data);
	public native void Set_WorkingBrakeStatus_506_PGN65428(int Data);
	public native void Set_MaintenanceAlarmLamp_1099_PGN65428(int Data);
	public native void Set_OperatorAbsenceStatus_832_PGN65428(int Data);
	public native void Set_MirrorHeaterStatus_724_PGN65428(int Data);
	public native void Set_HighBeamStatus_725_PGN65428(int Data);
	public native void Set_TravelAlarmSwitch_721_PGN65428(int Data);
	public native void Set_SwingBoomSwitch_726_PGN65428(int Data);
	public native void Set_ParkingWorkingTravelStatus_510_PGN65428(int Data);
	public native void Set_TransmissionTCLockupEngaged_556_PGN65428(int Data);
	public native void Set_ExhaustSystemHighTemperatureLampCommand_353_PGN65428(int Data);
	public native void Set_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428(int Data);
	public native void Set_AutomaticEngineShutdown_363_PGN65428(int Data);
	public native void Set_DelayedEngineShutdown_365_PGN65428(int Data);
	//////TX_GAUGE_65431///////
	public native void Set_FuelLevel_301_PGN65431(int Data);
	public native void Set_EngineCoolantTemperature_304_PGN65431(int Data);
	public native void Set_HydraulicOilTemperature_101_PGN65431(int Data);
	public native void Set_TransmissionOilTemperature_536_PGN65431(int Data);
	public native void Set_EngineSpeed_310_PGN65431(int Data);
	public native void Set_BatteryVoltage_705_PGN65431(int Data);
	//////TX_HOURMETER_CLOCK_WIPER_65433///////
	public native void Set_Hourmeter_1601_PGN65433(int Data);
	public native void Set_Clock_819_PGN65433(int Data);
	public native void Set_HourmeterActiveStatus_703_PGN65433(int Data);
	public native void Set_WiperOperationStatus_717_PGN65433(int Data);
	public native void Set_WiperSpeedLevel_718_PGN65433(int Data);
	public native void Set_BacklightIlluminationLevel_719_PGN65433(int Data);
	public native void Set_IlluminationStatus_737_PGN65433(int Data);
	//////TX_MACHINE_TRAVEL_STATUS_65434///////
	public native void Set_WheelBasedVehicleSpeed_532_PGN65434(int Data);
	public native void Set_SelectGear_541_PGN65434(int Data);
	public native void Set_ActualGear_542_PGN65434(int Data);
	public native void Set_TransmissionShiftMode_543_PGN65434(int Data);
	public native void Set_ClutchCutoffMode_544_PGN65434(int Data);
	public native void Set_KickDownShiftMode_547_PGN65434(int Data);
	public native void Set_TransmissionTCLockupEngaged_568_PGN65434(int Data);
	public native void Set_DifferentialLockMode_570_PGN65434(int Data);
	public native void Set_VehicleSpeedLimitMode_575_PGN65434(int Data);
	//////TX_DTC_INFORMATION_TYPE1_65438///////
	public native void Set_DTCType_1510_PGN65438(int Data);
	public native void Set_NumberofDTCinThisPacket_1514_PGN65438(int Data);
	public native void Set_TotalNumberofDTC_PGN65438(int Data);
	public native void Set_TotalNumberofDTCInformationPacket_1512_PGN65438(int Data);
	public native void Set_SequenceNumberofDTCInformationPacket_1513_PGN65438(int Data);
	public native void Set_DTC_1_PGN65438(byte[] Data);
	public native void Set_DTC_2_PGN65438(byte[] Data);
	public native void Set_DTC_3_PGN65438(byte[] Data);
	public native void Set_DTC_4_PGN65438(byte[] Data);
	public native void Set_DTC_5_PGN65438(byte[] Data);
	//////TX_WEIGHING_SYSTEM_STATUS_65450///////
	public native void Set_WeighingSystemAccumulationMode_1941_PGN65450(int Data);
	public native void Set_WeighingSystemBuzzer_1907_PGN65450(int Data);
	public native void Set_CurrentWeighingResult_1919_PGN65450(int Data);
	public native void Set_WeighingDisplayMode1_1910_PGN65450(int Data);
 	// ++, 150212 bwk
	public native void Set_WeighingSystemError_BoomLiftSpeed_1942_PGN65450(int Data);
	public native void Set_WeighingSystemError_BucketFullIn_1943_PGN65450(int Data);
	public native void Set_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450(int Data);
	// --, 150212 bwk
 	public native void Set_CurrentWeight_1911_PGN65450(int Data);
	public native void Set_TodayWeight_1915_PGN65450(int Data);
	//////TX_WEIGHING_SYSTEM_DATA1_65451///////
	public native void Set_TotalWorkAWeight_1912_PGN65451(int Data);
	public native void Set_TotalWorkBWeight_1913_PGN65451(int Data);
	//////TX_WEIGHING_SYSTEM_DATA2_65452///////
	public native void Set_TotalWorkCWeight_1914_PGN65452(int Data);
	public native void Set_ADayBeforeWeight_1916_PGN65452(int Data);
	//////TX_JOYSTICK_POSITION_STATUS_65515///////
	public native void Set_BoomJoystickPositionStatus_2310_PGN65515(int Data);
	public native void Set_BoomJoystickPosition_2311_PGN65515(int Data);
	public native void Set_BucketJoystickPositionStatus_2312_PGN65515(int Data);
	public native void Set_BucketJoystickPosition_2313_PGN65515(int Data);
	public native void Set_AuxJoystickPositionStatus_2314_PGN65515(int Data);
	public native void Set_AuxJoystickPosition_2315_PGN65515(int Data);
	public native void Set_BoomLeverFloatingPosition_2336_PGN65515(int Data);
	//////TX_WHEEL_LOADER_EHCU_STATUS_65517///////
	public native void Set_BucketPriorityOperation_2301_PGN65517(int Data);
	public native void Set_FlowFineModulationOperation_2302_PGN65517(int Data);
	public native void Set_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517(int Data);
	public native void Set_AttachmentPilotCutoffStatus_225_PGN65517(int Data);
	public native void Set_FloatMode_2316_PGN65517(int Data);
	public native void Set_JoystickSteeringActiveStatusEHCU_186_PGN65517(int Data);
	public native void Set_BoomUpEPPRValveCurrent_2304_PGN65517(int Data);
	public native void Set_BoomDownEPPRValveCurrent_2305_PGN65517(int Data);
	public native void Set_BucketInEPPRValveCurrent_2306_PGN65517(int Data);
	public native void Set_BucketOutEPPRValveCurrent_2307_PGN65517(int Data);
	public native void Set_AUX1EPPRValveCurrent_2308_PGN65517(int Data);
	public native void Set_AUX2EPPRValveCurrent_2309_PGN65517(int Data);
	//////TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
	public native void Set_TravelAlarmOperationStatus_3431_PGN65527(int Data);
	public native void Set_WasherOperationStatus_3432_PGN65527(int Data);
	public native void Set_wiperoperationstatus_3433_PGN65527(int Data);
	public native void Set_IntWiperOperationStatus_3434_PGN65527(int Data);
	public native void Set_WorkLampOperationStatus_3435_PGN65527(int Data);
	public native void Set_HeadLampOperationStatus_3436_PGN65527(int Data);
	public native void Set_CabinLampOperationStatus_3437_PGN65527(int Data);
	public native void Set_IlluminationOperationStatus_3438_PGN65527(int Data);
	public native void Set_OutriggerFrontRHOperationStatus_3439_PGN65527(int Data);
	public native void Set_OutriggerFrontLHOperationStatus_3440_PGN65527(int Data);
	public native void Set_OutriggerRearRHOperationStatus_3441_PGN65527(int Data);
	public native void Set_OutriggerRearLHOperationStatus_3442_PGN65527(int Data);
	public native void Set_OverloadSwitchOperationStatus_3443_PGN65527(int Data);
	public native void Set_BeaconLampOperationStatus_3444_PGN65527(int Data);
	public native void Set_ForwardTravelAlarmOperationStatus_3445_PGN65527(int Data);
	public native void Set_RearWorkLampOperationStatus_3446_PGN65527(int Data);
	public native void Set_RideControlOperationStatus_3447_PGN65527(int Data);
	public native void Set_QuickCouplerOperationStatus_3448_PGN65527(int Data);
	public native void Set_AutoGreaseOperationStatus_3449_PGN65527(int Data);
	public native void Set_MirrorHeatOperationStatus_3450_PGN65527(int Data);
	public native void Set_RearWiperOperationStatus_3451_PGN65527(int Data);
	public native void Set_RearWiperWasherOperationStatus_3452_PGN65527(int Data);
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	public native int Get_FirmwareVersionHigh();
	public native int Get_FirmwareVersionLow();
	public native int Get_FirmwareVersionSubHigh();
	public native int Get_FirmwareVersionSubLow();
	public native int Get_FirmwareVersionHidden();
	public native int Get_HWVersion();
	public native int Get_CommErrCnt();
	public native int Get_CheckBKCUComm();
	
	
	public native void Set_CommErrCnt(int Data);
	
	public native int GET_RecvResDownFlag();
	public native int GET_RX_RES_DOWN_UpdateResponse();
	
	public native int Get_RX_RES_KEY_LongFlag();
	public native int Get_RX_RES_KEY_Menu();
	public native int Get_RX_RES_KEY_ESC();
	public native int Get_RX_RES_KEY_Left();
	public native int Get_RX_RES_KEY_Right();
	public native int Get_RX_RES_KEY_Enter();
	public native int Get_RX_RES_KEY_CAM();
	public native int Get_RX_RES_KEY_MainLight();
	public native int Get_RX_RES_KEY_WorkLight();
	public native int Get_RX_RES_KEY_AutoGrease();
	public native int Get_RX_RES_KEY_QuickCouupler();
	public native int Get_RX_RES_KEY_RideControl();
	public native int Get_RX_RES_KEY_WorkLoad();
	public native int Get_RX_RES_KEY_BeaconLamp();
	public native int Get_RX_RES_KEY_RearWiper();
	public native int Get_RX_RES_KEY_MirrorHeat();
	public native int Get_RX_RES_KEY_DetentSetting();
	public native int Get_RX_RES_KEY_FineModulation();
	public native int Get_RX_RES_KEY_FN();
	public native int Get_gErr_Mcu_TotalPacket();
	public native int Get_gErr_Ecu_TotalPacket();
	public native int Get_gErr_Tcu_TotalPacket();
	public native int Get_gErr_Ehcu_TotalPacket();
	public native int Get_gErr_Mcu_TotalPacket_Logged();
	public native int Get_gErr_Ecu_TotalPacket_Logged();
	public native int Get_gErr_Tcu_TotalPacket_Logged();
	public native int Get_gErr_EHCU_TotalPacket_Logged();
	public native int Get_gErr_Mcu_Total();
	public native int Get_gErr_Ecu_Total();
	public native int Get_gErr_Tcu_Total();
	public native int Get_gErr_Ehcu_Total();
	public native int Get_gErr_Mcu_Total_Logged();
	public native int Get_gErr_Ecu_Total_Logged();
	public native int Get_gErr_Tcu_Total_Logged();
	public native int Get_gErr_EHCU_Total_Logged();
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////
	
	
	///////////////USER DEFINE METHOD///////////////////////////////////////
	// Open Comport
	public void InitComport(){
		Log.v(TAG,"InitComport");		
		mFdUART1 = Open_UART1(UART1ComPort,UART1Baudrate,0);
		mFdUART3 = Open_UART3(UART3ComPort,UART3Baudrate,0);

	}
	// close Comport
	public void CloseComport(){
		Log.v(TAG,"Closecomport");
		Close_UART1();
		Close_UART3();
	}
	// Sound Init
	private void InitSound(){
		//++, 150510 cjg
		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
			SoundPoolKeyButton = new SoundPool(1,AudioManager.USE_DEFAULT_STREAM_TYPE,0);
		else
			SoundPoolKeyButton = new SoundPool(1,AudioManager.STREAM_MUSIC,0);	
		//--, 150510 cjg
		SoundID = SoundPoolKeyButton.load(this, R.raw.touch, 1);
		fVolume = (float)0.4;
		
		//++, 150510 cjg
		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
			SoundPoolKeyButtonEnding = new SoundPool(1,AudioManager.USE_DEFAULT_STREAM_TYPE,0);
		else
			SoundPoolKeyButtonEnding = new SoundPool(1,AudioManager.STREAM_MUSIC,0);	
		//--, 150510 cjg
		SoundIDEnding = SoundPoolKeyButtonEnding.load(this, R.raw.ending, 1);
		fVolumeEnding = (float)0.4;
		
	}
	// ++, 150323 bwk
	public static void setVolume(int _Volume){
		fVolume = ((float)(_Volume+1)/10); 
	}
	// --, 150323 bwk
	public static void CIDCallBack(){
		Log.d(TAG,"CIDCallBack");
		try {
			CAN1Comm.Callback_CID();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"CIDCallBack NullPointerException");
		}
		
	}
	
	public static void ASCallback(){
		Log.d(TAG,"ASCallback");
		
		try {
			CAN1Comm.Callback_AS();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"ASCallback NullPointerException");
		}
	}
		
	public static void StopCommServiceCallBack(){
		
	}
	
	public static void KeyButtonCallBack(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.OFF:
				return;
				//CAN1Comm.Callback_KeyButton(Data);
				//break;
			case CAN1CommManager.MENU:
				HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				
				if(GetScreenTopFlag() == true)	// 타 apk에서 소리 2번 울림
					SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
				break;
			case CAN1CommManager.ESC:
				// ++, 150324 bwk 하기 주석 품
				//if(GetScreenTopFlag() == true || CAN1Comm.CameraCurrentOnOff == true)
				if(GetScreenTopFlag() == true || (CAN1Comm.CameraCurrentOnOff == true && CAN1Comm.CameraOnFlag != CAN1CommManager.STATE_CAMERA_REVERSEGEAR))
				{
					CAN1Comm.Callback_KeyButton(Data);
					SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);		// 타 apk에서 소리 2번 울림
				}else if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_REVERSEGEAR){
				}else
				// --, 150324 bwk 하기 주석 품
				{
					// ++, 150320 cjg
					//BackKeyEvent();
//					if((miracastFlag == true) || (multimediaFlag == true)){
//						HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
//					}else{
//						BackKeyEvent();
//						SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
//					}
					SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
					// --, 150320 cjg				
				}
				break;
			// ++, 150324 bwk
			case CAN1CommManager.LEFT:
				if(GetScreenTopFlag() == true || (CAN1Comm.CameraCurrentOnOff == true && CAN1Comm.CameraOnFlag != CAN1CommManager.STATE_CAMERA_REVERSEGEAR))
				{
					CAN1Comm.Callback_KeyButton(Data);
				}else if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_REVERSEGEAR){
				}
				else{
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				}
				SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
				break;
			// --, 150324 bwk
			case CAN1CommManager.RIGHT:
				if(GetScreenTopFlag() == true || (CAN1Comm.CameraCurrentOnOff == true && CAN1Comm.CameraOnFlag != CAN1CommManager.STATE_CAMERA_REVERSEGEAR))
				{
					CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_BUZ, 0);
					CAN1Comm.Set_RequestBuzzerStop_PGN65327(1);
					CAN1Comm.TxCANToMCU(47);
					CAN1Comm.Callback_KeyButton(Data);
					StartBuzzerStopTimer();
				}
				
				SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
				break;
				

			case CAN1CommManager.POWER_OFF:
				// ++, 150403 cjg
//				if(GetScreenTopFlag() == true)
//				{
//					CAN1Comm.Callback_KeyButton(Data);
//				}
//				// ++, 150326 bwk
//				HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));	
//				/*
//				else{//++, --, 150324 cjg
//					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));	
//				}
//				*/
//				// --, 150326 bwk
//				if(count++==0)	// ++, --, 150325 bwk
//					SoundPoolKeyButtonEnding.play(SoundIDEnding, fVolumeEnding, fVolumeEnding, 0, 0, 1);
				if(endingKeyIGCount == 0){
					if(GetScreenTopFlag() == true)
					{
						CAN1Comm.Callback_KeyButton(Data);
						HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
						
					}else if(GetScreenTopFlag() == false){
						HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
					}
					if(endingSoundCount == 0){
						if(multimediaFlag == false || miracastFlag == false){
							SoundPoolKeyButtonEnding.play(SoundIDEnding, fVolumeEnding, fVolumeEnding, 0, 0, 1);
							endingSoundCount++;
						}
					}
					endingKeyIGCount++;
				}
				// ++, 150615 cjg
				try {
					CAN1Comm.native_system_sync_Native();
					Log.d(TAG, "sync");
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException");
				}
				catch (Throwable t) {
					// TODO: handle exception
					Log.e(TAG,"Load Library Error");
				}	
				// --, 150615 cjg
				// --, 150403 cjg			
				break;
			case CAN1CommManager.FN:
				//CAN1Comm.Callback_KeyButton(Data);
				// ++, 150326 cjg
				//if(FNFlag == true)
				if(CAN1Comm.CameraCurrentOnOff == false && FNFlag == true)
				// --, 150326 cjg
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				
				SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
				break;
			case CAN1CommManager.MAINLIGHT:
			case CAN1CommManager.WORKLIGHT:
			case CAN1CommManager.BEACON_LAMP:
			case CAN1CommManager.REAR_WIPER:
			case CAN1CommManager.CAMERA:
				CAN1Comm.Callback_KeyButton(Data);
				SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
				break;
			default:
				if(GetScreenTopFlag() == true)
				{
					CAN1Comm.Callback_KeyButton(Data);
				}
				
				SoundPoolKeyButton.play(SoundID, fVolume, fVolume, 0, 0, 1);
				
				break;
			}
			Log.d(TAG,"GetScreenTopFlag : " + Boolean.toString(GetScreenTopFlag()));
			Log.d(TAG,"Data : " + Integer.toString(Data));
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"KeyButtonCallBack NullPointerException");
		}
		
	}
	
	public static void EEPRomTestCallback(int Data){
		Log.d(TAG,"EEPRomTestCallback");
		CAN1Comm.Callback_EEPRomTest(Data);
	}
	
	public static void FlashTestCallback(int Data){
		Log.e(TAG,"FlashTestCallback");
		CAN1Comm.Callback_FlashTest(Data);
	}
	
	public static void BackKeyEvent(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_BACK);
				new Instrumentation().sendKeySync(event);
				KeyEvent event2 = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_BACK);
				new Instrumentation().sendKeySync(event2);
				Log.d(TAG,"BackKeyEvent");
			}
			
		}).start();
	}
	public static void MenuKeyEvent(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.d(TAG,"MenuKeyEvent");
				KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_MENU);
				new Instrumentation().sendKeySync(event);
				KeyEvent event2 = new KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_MENU);
				new Instrumentation().sendKeySync(event2);
				
			}
			
		}).start();
	}
	// ++, 150403 cjg
	public static boolean GetPowerOffFlag(){
		return powerOffFlag;
	}
	public static void SetPowerOffFlag(boolean flag){
		powerOffFlag = flag;
	}
	// --, 150403 cjg
	public static void SetScreenTopFlag(boolean flag){
		ScreenTopFlag = flag;
	}
	public static boolean GetScreenTopFlag(){
		return ScreenTopFlag;
	}
	public static void SetFNFlag(boolean flag){
		FNFlag = flag;
	}
	public static boolean GetFNFlag(){
		return FNFlag;
	}
	public boolean isTopCheckMiracast() {
		return topCheckMiracast;
	}

	public void setRunningCheckMiracast(boolean _topCheckMiracast) {
		topCheckMiracast = _topCheckMiracast;
	}

	/*
	public static boolean GetrpmFlag(){
		return rpmFlag;
	}
	 */
	
	// ++, 150323 bwk
	public void SetMultimediaFlag(boolean flag){
		multimediaFlag = flag;
		if(multimediaFlag == true || miracastFlag == true 
			|| CheckRunningApp("com.mxtech.videoplayer.ad") == true
			|| CheckRunningApp("com.powerone.wfd.sink") == true)
			SetFNKeypadLamp(1);
		else 
			SetFNKeypadLamp(0);
	}
	public boolean GetMultimediaFlag(){
		return multimediaFlag;
	}
	public void SetMiracastFlag(boolean flag){
		miracastFlag = flag;
		if(multimediaFlag == true || miracastFlag == true
			|| CheckRunningApp("com.mxtech.videoplayer.ad") == true
			|| CheckRunningApp("com.powerone.wfd.sink") == true)
			SetFNKeypadLamp(1);
		else 
			SetFNKeypadLamp(0);
	}	
	public boolean GetMiracastFlag(){
		return miracastFlag;
	}
	// --, 150323 bwk
	public void CheckMultimedia()
	{
		if(CheckRunningApp("com.mxtech.videoplayer.ad") == false)
		{
			SetMultimediaFlag(false);
			//rpmFlag = false;
		}
		else
		{
			SetMultimediaFlag(true);
		}
	}
//	public void CheckMiracast()
//	{
//		if(CheckRunningApp("com.powerone.wfd.sink") == false)
//		{
//			SetMiracastFlag(false);
//		}
//		else
//		{
//			SetMiracastFlag(true);
//		}
//	}	
	public void CheckMiracast()
	{
		if(CheckRunningApp("com.powerone.wfd.sink") == false )
		{
			SetMiracastFlag(false);
		}
		else
		{
			SetMiracastFlag(true);
		}
		if(pi != null){
			if(!pi.versionName.equals("1.0.5BF")){
				if(CheckRunningApp("com.powerone.wfd.sink") == false)
				{
					setRunningCheckMiracast(false);
					SetScreenTopFlag(true);   
				}
				else if(CheckRunningApp("com.powerone.wfd.sink") == true && isTopCheckMiracast() == false)
				{
					setRunningCheckMiracast(false);
				}
				else if(CheckRunningApp("com.powerone.wfd.sink") == true && isTopCheckMiracast() == true)
				{
					setRunningCheckMiracast(true);
				}
			}
		}
	}   	
	////////////////////////////////////////////////////////////////////////
		
	/////////////////OVERRIDE METHOD///////////////////////////////////////
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v(TAG,"onBind");
		BindFlag = true;
		return CAN1CommManager.getInstance(this);
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		Log.v(TAG,"onUnbind");
		BindFlag = false;
		return super.onUnbind(intent);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.v(TAG,"onCreate");
		InitComport();
		CAN1Comm = CAN1CommManager.getInstance(this);
		
		InitSound();
		super.onCreate();
		try {
			pi = getPackageManager().getPackageInfo("com.powerone.wfd.sink", 0);
		} catch (NameNotFoundException e1) {
			
		} catch (NullPointerException e2){
			
		} catch (Exception e){
			
		}
		
		HandleKeyButton = new Handler() {
			@Override
			public void handleMessage(Message msg) {

				if(msg.what == CAN1CommManager.POWER_OFF){
					// ++, 150403 cjg
					//allKillRunningApps("taeha.wheelloader.fseries_monitor.main");
					if(GetScreenTopFlag() == true){
						SetPowerOffFlag(false);
					}else{
						if(pi != null){
							if(pi.versionName.equals("1.0.5BF")){
								if(CheckTopApps("com.mxtech.videoplayer.ad")){
									ClickFN();
								}else{
									CallHome();
								}
								SetPowerOffFlag(true);
							}else{
								if(CheckTopApps("com.mxtech.videoplayer.ad")){
									ClickFN();
								}else if(CAN1Comm.isTopCheckMiracast() == true){
									CloseSmartTerminal();
									CallHome();
								}
								else{
									CallHome();
								}
								SetPowerOffFlag(true);
							}
						}else{
							if(CheckTopApps("com.mxtech.videoplayer.ad")){
								ClickFN();
							}else{
								CallHome();
							}
							SetPowerOffFlag(true);
						}
					}
					// --, 150403 cjg
				}else if(msg.what == CAN1CommManager.MENU){
					if(pi != null){
						
						if(pi.versionName.equals("1.0.5BF")){
							MenuKeyEvent();	
						}else{
							if(CAN1Comm.isTopCheckMiracast() == false){
								MenuKeyEvent();	 
							}
						}
					}else{
						MenuKeyEvent();
					}
					
				}
				else if(msg.what == CAN1CommManager.FN){
					ClickFN();
				// ++, 150319 cjg
				}else if(msg.what == CAN1CommManager.ESC){
					Log.d(TAG, "ESC KEY in CommService");
					if(pi != null){
						if(pi.versionName.equals("1.0.5BF")){
							if(CheckTopApps("com.mxtech.videoplayer.ad")){
								CloseMXPlayer();
							}
							else if(CheckTopApps("com.powerone.wfd.sink")){
								CloseSmartTerminal();
							}else if(CheckTopApps("org.ebookdroid")){
								ClosePDFViewer();
							}else{
								BackKeyEvent();
							}
						}else{
							if(CheckTopApps("com.mxtech.videoplayer.ad")){
								CloseMXPlayer();
							}
							else if(CheckRunningApp("com.powerone.wfd.sink")){
								if(CheckTopApps("org.ebookdroid")){
									ClosePDFViewer();
								}else if(CheckTopApps("com.rhmsoft.fm.hd")){
									BackKeyEvent();
								}else if(CheckTopApps("com.android.settings")){
									BackKeyEvent();
								}
								else{
									CloseSmartTerminal();
								}
							}else if(CheckTopApps("org.ebookdroid")){
								ClosePDFViewer();
							}else{
								BackKeyEvent();
							}
						}
					}else{
						if(CheckTopApps("com.mxtech.videoplayer.ad")){
							CloseMXPlayer();
						}
						else if(CheckTopApps("com.powerone.wfd.sink")){
							CloseSmartTerminal();
						}else if(CheckTopApps("org.ebookdroid")){
							ClosePDFViewer();
						}else{
							BackKeyEvent();
						}
					}
				}else if(msg.what == CAN1CommManager.LEFT){
					if(pi != null){
						if(pi.versionName.equals("1.0.5BF")){
							BackKeyEvent();
						}else{
							if(CAN1Comm.isTopCheckMiracast() == true){
								ClickBackKeyFromMiracast();
							}else{
								BackKeyEvent();
							}
						}
					}else{
						BackKeyEvent();
					}
				}
				// --, 150319 cjg
			
			}
		};
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.v(TAG,"onDestroy");
		CloseComport();
		super.onDestroy();
	}
	
	////////////////////////////////////////////////////////////////////////
	
	///////////////////////Timer////////////////////////////////////////////
	public static class BuzzerStopTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			CAN1Comm.Set_RequestBuzzerStop_PGN65327(0);
			CAN1Comm.TxCANToMCU(47);
		}
		
	}
	
	public static void StartBuzzerStopTimer(){
		CancelBuzzerStopTimer();
		mBuzzerStopTimer = new Timer();
		mBuzzerStopTimer.schedule(new BuzzerStopTimerClass(),1000);	
	}
	
	public static void CancelBuzzerStopTimer(){
		if(mBuzzerStopTimer != null){
			mBuzzerStopTimer.cancel();
			mBuzzerStopTimer.purge();
			mBuzzerStopTimer = null;
		}
	}
	////////////////////////////////////////////////////////////////////////
	public void CallHome(){
//		Intent intent = new Intent();
//	    intent.setAction("android.intent.action.MAIN");
//	    intent.addCategory("android.intent.category.HOME");
//	    intent.addFlags(
//	    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
//	    | Intent.FLAG_ACTIVITY_FORWARD_RESULT
//	    | Intent.FLAG_ACTIVITY_NEW_TASK 
//	    | Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
//	    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
//	    );
//	    startActivity(intent);
		
		Intent intent;
		intent = getPackageManager().getLaunchIntentForPackage("taeha.wheelloader.fseries_monitor.main");
		if(intent != null)
		{
			startActivity(intent);
			// ++, 150323 bwk
			//multimediaFlag = false;
			//miracastFlag = false;
			// --, 150323 bwk
		}
	}

	public boolean CheckTopApps(String strProcess){
		ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> Info = am.getRunningTasks(1);
		ComponentName topActivity = Info.get(0).topActivity;
		String topactivityname = topActivity.getPackageName();
		Log.d(TAG,"Top Activity : " + topactivityname);
		if(strProcess.equalsIgnoreCase(topactivityname)){
			return true;
		}else{
			return false;
		}
	}
	public boolean CheckRunningApp(String strPrcessName){
		 ActivityManager activity_manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningAppProcessInfo> app_list = activity_manager.getRunningAppProcesses();

		 for(int i=0; i<app_list.size(); i++)	 {
	
			 if(strPrcessName.equals(app_list.get(i).processName) == true)	 {
				 //Log.d(TAG,"Process is running : " + app_list.get(i).processName);
				 return true;
			 }
		 }
		 System.gc();
		 return false;
	}
	public void allKillRunningApps(String strPrcessName)	 {
		 ActivityManager activity_manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningAppProcessInfo> app_list = activity_manager.getRunningAppProcesses();
		 Log.d(TAG, "Try kill process..");

		 for(int i=0; i<app_list.size(); i++)	 {
			 Log.d(TAG,"Process : " + app_list.get(i).processName);

			 if(strPrcessName.equals(app_list.get(i).processName) == false
		     && "taeha.wheel_loader_f_series_ui_home".equals(app_list.get(i).processName) == false
		     && "system".equals(app_list.get(i).processName) == false)	 {
				 android.os.Process.sendSignal(app_list.get(i).pid, android.os.Process.SIGNAL_KILL);
				 activity_manager.killBackgroundProcesses(app_list.get(i).processName);
				 Log.d(TAG,"Kill Process : " + app_list.get(i).processName);
			 }
		 }
		 System.gc();
	}
	public void RunMultimedia(){
		Intent intent;
		intent = getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
		if(intent != null){
			startActivity(intent);
			SetMultimediaFlag(true);
			//multimediaFlag = true;		// ++, --, 150323 bwk
			startService(new Intent(this,AlwaysOnTopService.class));
			SetFNKeypadLamp(1);
		}
	}
	public void RunMirror(){
		Intent intent;
		intent = getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
		if(intent != null){
			startActivity(intent);
			SetMiracastFlag(true);
//			miracastFlag = true;		// ++, --, 150323 bwk
			startService(new Intent(this,AlwaysOnTopService.class));
			SetFNKeypadLamp(1);
		}
	}
	public void ClickFN(){
		//if(rpmFlag == false)
			//rpmFlag = false;
		if(pi != null){
			if(pi.versionName.equals("1.0.5BF")){
				if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(false);
					//multimediaFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckTopApps("com.powerone.wfd.sink") == true){
					//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
					//miracastFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckRunningApp("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(true);
					//multimediaFlag = true; //++, --, 150403 cjg
					RunMultimedia();
				}else if(CheckRunningApp("com.powerone.wfd.sink") == true){
					SetMiracastFlag(true);
					//miracastFlag = true; //++, --, 150403 cjg
					RunMirror();
				}
			}else{
				if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(false);
					//multimediaFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckRunningApp("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(true);
					//multimediaFlag = true; //++, --, 150403 cjg
					RunMultimedia();
				}else if(CheckRunningApp("com.powerone.wfd.sink") == true && topCheckMiracast == true){
					SetMiracastFlag(true);
					//miracastFlag = true; //++, --, 150403 cjg
					RunMirror();
					Log.d(TAG, "Smart Terminal1 FN key : " + topCheckMiracast);
					topCheckMiracast = false;
				}else if(CheckRunningApp("com.powerone.wfd.sink") == true && topCheckMiracast == false){
					SetMiracastFlag(true);
					//miracastFlag = true; //++, --, 150403 cjg
					RunMirror();
					Log.d(TAG, "Smart Terminal2 FN key : " + topCheckMiracast);
					topCheckMiracast = true;
				}
			}
		}else{
			if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
				SetMultimediaFlag(false);
				//multimediaFlag = false; //++, --, 150403 cjg
				CallHome();
			}else if(CheckTopApps("com.powerone.wfd.sink") == true){
				//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
				//miracastFlag = false; //++, --, 150403 cjg
				CallHome();
			}else if(CheckRunningApp("com.mxtech.videoplayer.ad") == true){
				SetMultimediaFlag(true);
				//multimediaFlag = true; //++, --, 150403 cjg
				RunMultimedia();
			}else if(CheckRunningApp("com.powerone.wfd.sink") == true){
				SetMiracastFlag(true);
				//miracastFlag = true; //++, --, 150403 cjg
				RunMirror();
			}
		}
		
	}
	public void ClickBackKeyFromMiracast(){
			SetMiracastFlag(true);
			//miracastFlag = true; //++, --, 150403 cjg
			RunMirror();
			Log.d(TAG, "Smart Terminal1 LEFT key : " + topCheckMiracast);
			topCheckMiracast = false;
	}
	public void ClickFN_Home(){
		if(pi != null){
			if(pi.versionName.equals("1.0.5BF")){
				if(CheckTopApps("taeha.wheelloader.update") == true)
				{
					ScreenTopFlag = true;
				}
				else if(CheckTopApps("com.google.android.inputmethod.korean") == true)
				{
					
				}
				else if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(false);
					//multimediaFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckTopApps("com.powerone.wfd.sink") == true){
					//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
					//miracastFlag = false; //++, --, 150403 cjg
					CallHome();
				}else{
					CallHome();
				}
			}else{
				if(CheckTopApps("taeha.wheelloader.update") == true)
				{
					ScreenTopFlag = true;
				}
				else if(CheckTopApps("com.google.android.inputmethod.korean") == true)
				{
					
				}
				else if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(false);
					//multimediaFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckRunningApp("com.powerone.wfd.sink") && isTopCheckMiracast() == true){
					//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
					//miracastFlag = false; //++, --, 150403 cjg
					Runtime runtime = Runtime.getRuntime();
					Process process;
					try{
						//String cmd = "am force-stop com.powerone.wfd.sink";
						String cmd = "am broadcast -a com.powerone.wfd.sink.SCALING";
						process = runtime.exec(cmd);
						Log.d(TAG, "am broadcast -a com.powerone.wfd.sink.SCALING");
					}catch(Exception e){
						e.fillInStackTrace();
					}
					RunMirror();
					topCheckMiracast = false;
					CallHome();
				}else{
					CallHome();
				}
			}
		}else{
			if(CheckTopApps("taeha.wheelloader.update") == true)
			{
				ScreenTopFlag = true;
			}
			else if(CheckTopApps("com.google.android.inputmethod.korean") == true)
			{
				
			}
			else if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
				SetMultimediaFlag(false);
				//multimediaFlag = false; //++, --, 150403 cjg
				CallHome();
			}else if(CheckTopApps("com.powerone.wfd.sink") == true){
				//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
				//miracastFlag = false; //++, --, 150403 cjg
				CallHome();
			}else{
				CallHome();
			}
		}
	}
	public void ClickFN_Buzzer(){
		if(pi != null){
			if(pi.versionName.equals("1.0.5BF")){
				if(CheckTopApps("taeha.wheelloader.update") == true)
				{
					ScreenTopFlag = true;
				}
				else if(CheckTopApps("com.google.android.inputmethod.korean") == true)
				{
					
				}
				else if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(false);
					//multimediaFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckTopApps("com.powerone.wfd.sink") == true){
					//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
					//miracastFlag = false; //++, --, 150403 cjg
					CallHome();
				}else{
					CallHome();
				}
			}else{
				if(CheckTopApps("taeha.wheelloader.update") == true)
				{
					ScreenTopFlag = true;
				}
				else if(CheckTopApps("com.google.android.inputmethod.korean") == true)
				{
					
				}
				else if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
					SetMultimediaFlag(false);
					//multimediaFlag = false; //++, --, 150403 cjg
					CallHome();
				}else if(CheckRunningApp("com.powerone.wfd.sink") && isTopCheckMiracast() == true){
					//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
					//miracastFlag = false; //++, --, 150403 cjg
					Runtime runtime = Runtime.getRuntime();
					Process process;
					try{
						//String cmd = "am force-stop com.powerone.wfd.sink";
						String cmd = "am broadcast -a com.powerone.wfd.sink.SCALING";
						process = runtime.exec(cmd);
						Log.d(TAG, "am broadcast -a com.powerone.wfd.sink.SCALING");
					}catch(Exception e){
						e.fillInStackTrace();
					}
					topCheckMiracast = false;
					CallHome();
					Log.d(TAG, "Run Mira");
				}else{
					CallHome();
				}
			}
		}else{
			if(CheckTopApps("taeha.wheelloader.update") == true)
			{
				ScreenTopFlag = true;
			}
			else if(CheckTopApps("com.google.android.inputmethod.korean") == true)
			{
				
			}
			else if(CheckTopApps("com.mxtech.videoplayer.ad") == true){
				SetMultimediaFlag(false);
				//multimediaFlag = false; //++, --, 150403 cjg
				CallHome();
			}else if(CheckTopApps("com.powerone.wfd.sink") == true){
				//SetMiracastFlag(false);		// 현재 FN키 누르면 해제됨
				//miracastFlag = false; //++, --, 150403 cjg
				CallHome();
			}else{
				CallHome();
			}
		}
	}
	// ++, 150320 cjg
	public void CloseMXPlayer(){
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try{
			String cmd = "am force-stop com.mxtech.videoplayer.ad";
			process = runtime.exec(cmd);
			Log.d(TAG, "am force-stop com.mxtech.videoplayer.ad");
		}catch(Exception e){
			e.fillInStackTrace();
		}
		//--, 150715 cjg
	}
	public void CloseSmartTerminal(){
		if(pi!=null){
			if(pi.versionName.equals("1.0.5BF")){
				Runtime runtime = Runtime.getRuntime();
				Process process;
				//++, 150910 cjg
				String service = Context.WIFI_SERVICE;
				final WifiManager wifi = (WifiManager)getSystemService(service);
				wifi.setWifiEnabled(false);
				//--, 150910 cjg
				try{
					String cmd = "am force-stop com.powerone.wfd.sink";
					process = runtime.exec(cmd);
					Log.d(TAG, "am force-stop com.powerone.wfd.sink");
				}catch(Exception e){
					e.fillInStackTrace();
				}
			}else{
				Runtime runtime = Runtime.getRuntime();
				Process process;
				//++, 150910 cjg
				String service = Context.WIFI_SERVICE;
				final WifiManager wifi = (WifiManager)getSystemService(service);
				wifi.setWifiEnabled(false);
				//--, 150910 cjg
				try{
					//String cmd = "am force-stop com.powerone.wfd.sink";
					String cmd = "am broadcast -a com.powerone.wfd.sink.QUIT";
					process = runtime.exec(cmd);
					Log.d(TAG, "am broadcast -a com.powerone.wfd.sink.QUIT");
				}catch(Exception e){
					e.fillInStackTrace();
				}
				setRunningCheckMiracast(false);
				SetScreenTopFlag(true);
			}
		}else{
			Runtime runtime = Runtime.getRuntime();
			Process process;
			//++, 150910 cjg
			String service = Context.WIFI_SERVICE;
			final WifiManager wifi = (WifiManager)getSystemService(service);
			wifi.setWifiEnabled(false);
			//--, 150910 cjg
			try{
				String cmd = "am force-stop com.powerone.wfd.sink";
				process = runtime.exec(cmd);
				Log.d(TAG, "am force-stop com.powerone.wfd.sink");
			}catch(Exception e){
				e.fillInStackTrace();
			}
		}
	}
	public void ClosePDFViewer(){
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try{
			String cmd = "am force-stop org.ebookdroid";
			process = runtime.exec(cmd);
			Log.d(TAG, "am force-stop org.ebookdroid");
		}catch(Exception e){
			e.fillInStackTrace();
		}
		
	}
}
