#include "CAN_DataParsing.c"

//////RX_DTC_INFORMATION_REQUEST_61184_11///////
jint Get_MessageType_PGN61184_11(JNIEnv * env, jobject this) {
	return RX_DTC_INFORMATION_REQUEST_61184_11.MessageType;
}
jint Get_DTCInformationRequest_1515_PGN61184_11(JNIEnv * env, jobject this) {
	return RX_DTC_INFORMATION_REQUEST_61184_11.DTCInformationRequest_1515;
}
jint Get_DTCType_1510_PGN61184_11(JNIEnv * env, jobject this) {
	return RX_DTC_INFORMATION_REQUEST_61184_11.DTCType_1510;
}
jint Get_MessageType_PGN61184_12(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_REQUSET_61184_12.MessageType;
}
jint Get_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(JNIEnv * env,
		jobject this) {
	return RX_DTC_INFORMATION_REQUEST_61184_11.SeqenceNumberofDTCInformationPacket_1513;
}
//////RX_MAINTENANCE_REQUSET_61184_12///////
jint Get_MaintenanceCommant_1097_PGN61184_12(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_REQUSET_61184_12.MaintenanceCommant_1097;
}
jint Get_MaintenanceItem_1098_PGN61184_12(JNIEnv * env, jobject this) {
	return TX_MAINTENANCE_REQUSET_61184_12.MaintenanceItem_1098;
}
jint Get_MaintenanceInterval_1091_PGN61184_12(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_REQUSET_61184_12.MaintenanceInterval_1091;
}
//////RX_MAINTENANCE_ITEM_LIST_61184_13///////
jint Get_MessageType_PGN61184_13(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_ITEM_LIST_61184_13.MessageType;
}
jint Get_TotalNumberofMaintenanceItems_1100_PGN61184_13(JNIEnv * env,
		jobject this) {
	return RX_MAINTENANCE_ITEM_LIST_61184_13.TotalNumberofMaintenanceItems_1100;
}
jbyteArray Get_MaintenanceItem_1098_PGN61184_13(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_MAINTENANCE_ITEM_LIST_61184_13.MaintenanceItem_1098));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_MAINTENANCE_ITEM_LIST_61184_13.MaintenanceItem_1098),
			RX_MAINTENANCE_ITEM_LIST_61184_13.MaintenanceItem_1098);

	return Data;
}
//////RX_MAINTENANCE_INFORMATION_61184_14///////
jint Get_MessageType_PGN61184_14(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.MessageType;
}
jint Get_MaintenanceItem_1098_PGN61184_14(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.MaintenanceItem_1098;
}
jint Get_MaintenanceAlarmLamp_1099_PGN61184_14(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.MaintenanceAlarmLamp_1099;
}
jint Get_MaintenanceEvent_PGN61184_14(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.MaintenanceEvent;
}
jint Get_MaintenanceInterval_1091_PGN61184_14(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.MaintenanceInterval_1091;
}
jint Get_MaintenanceTotalCount_1092_PGN61184_14(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.MaintenanceTotalCount_1092;
}
jint Get_HourmeterattheLastMaintenance_1093_PGN61184_14(JNIEnv * env,
		jobject this) {
	return RX_MAINTENANCE_INFORMATION_61184_14.HourmeterattheLastMaintenance_1093;
}
//////RX_MAINTENANCE_HISTORY_61184_15///////
jint Get_MessageType_PGN61184_15(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_HISTORY_61184_15.MessageType;
}
jint Get_MaintenanceItem_1098_PGN61184_15(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_HISTORY_61184_15.MaintenanceItem_1098;
}
jint Get_HourmeterattheLastMaintenance_1093_PGN61184_15(JNIEnv * env,
		jobject this) {
	return RX_MAINTENANCE_HISTORY_61184_15.HourmeterattheLastMaintenance_1093;
}
jshortArray Get_MaintenanceHistory_1094_PGN61184_15(JNIEnv * env, jobject this) {
	jshortArray Data = (*env)->NewShortArray(env, sizeof(RX_MAINTENANCE_HISTORY_61184_15.MaintenanceHistory_1094));

	(*env)->SetShortArrayRegion(env, Data, 0, sizeof(RX_MAINTENANCE_HISTORY_61184_15.MaintenanceHistory_1094),RX_MAINTENANCE_HISTORY_61184_15.MaintenanceHistory_1094);

	return Data;
}
//////RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
jint Get_MessageType_PGN61184_16(JNIEnv * env, jobject this) {
	return RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MessageType;
}
jint Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16(JNIEnv * env,
		jobject this) {
	return RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.TotalNumberofMaintenanceAlarmLampOnItems_1100;
}
jbyteArray Get_MaintenanceItem_PGN61184_16(JNIEnv * env, jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MaintenanceItem));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MaintenanceItem),RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MaintenanceItem);

	return Data;

}
//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
jint Get_MessageType_PGN61184_21(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21.MessageType;
}
//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
jint Get_MessageType_PGN61184_22(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.MessageType;
}
jbyteArray Get_HCEAntiTheftRandomNumber_1632_PGN61184_22(JNIEnv * env, jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.HCEAntiTheftRandomNumber_1632));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.HCEAntiTheftRandomNumber_1632),RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.HCEAntiTheftRandomNumber_1632);

	nRecvSeedFlag = 0;
	return Data;
}
jint Get_RecvSeedFlag_PGN61184_22(JNIEnv * env, jobject this) {
	return nRecvSeedFlag;
}
//////RX_HCE_ANTI_THEFT_REQUEST_61184_23///////
jint Get_MessageType_PGN61184_23(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_REQUEST_61184_23.MessageType;
}
jint Get_HCEAntiTheftCommand_1633_PGN61184_23(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftCommand_1633;
}
jint Get_ESLMode_820_PGN61184_23(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_REQUEST_61184_23.ESLMode_820;
}
jint Get_ESLInterval_825_PGN61184_23(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_REQUEST_61184_23.ESLInterval_825;
}
jbyteArray Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(JNIEnv * env,
		jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftPasswordRepresentation_1634));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftPasswordRepresentation_1634),RX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftPasswordRepresentation_1634);

	return Data;
}
//////RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
jint Get_MessageType_PGN61184_24(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24.MessageType;
}
jint Get_PasswordCertificationResult_956_PGN61184_24(JNIEnv * env, jobject this) {
	int returnValue;
	returnValue = RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24.PasswordCertificationResult_956;
	RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24.PasswordCertificationResult_956 = 0xF;
	nRecvPasswordResultFlag = 0;
	return returnValue;
}
jint Get_RecvPasswordResultFlag_PGN61184_24(JNIEnv * env, jobject this) {
	return nRecvPasswordResultFlag;
}

//////RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
jint Get_MessageType_PGN61184_25(JNIEnv * env, jobject this) {
	return RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.MessageType;
}
jint Get_PasswordChangeResult_958_PGN61184_25(JNIEnv * env, jobject this) {
	nRecvPasswordChangeResultFlag = 0;
	return RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.PasswordChangeResult_958;
}
jbyteArray Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25(JNIEnv * env,
		jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.HCEAntiTheftPasswordRepresentation_1634));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.HCEAntiTheftPasswordRepresentation_1634),RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.HCEAntiTheftPasswordRepresentation_1634);

	return Data;
}
jint Get_RecvPasswordChangeResultFlag_PGN61184_25(JNIEnv * env, jobject this) {
	return nRecvPasswordChangeResultFlag;
}
//////RX_AVERAGE_FUEL_RATE_HISTORY_61184_33///////
jint Get_MessageType_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33.MessageType;
}
jint Get_OperationHistoryType_1101_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33.OperationHistoryType_1101;
}
jint Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._1storP_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_4th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._4th_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_5th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._5th_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_6th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._6th_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_7th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._7th_HourAverageFuelRateorDayFuelUsed_333_1405;
}
jint Get_8th_HourAverageFuelRate_333_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._8th_HourAverageFuelRate_333;
}
jint Get_9th_HourAverageFuelRate_333_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._9th_HourAverageFuelRate_333;
}
jint Get_10th_HourAverageFuelRate_333_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._10th_HourAverageFuelRate_333;
}
jint Get_11th_HourAverageFuelRate_333_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._11th_HourAverageFuelRate_333;
}
jint Get_12th_HourAverageFuelRate_333_PGN61184_33(JNIEnv * env, jobject this) {
	return RX_AVERAGE_FUEL_RATE_HISTORY_61184_33._12th_HourAverageFuelRate_333;
}
//////RX_COOLING_FAN_SETTING_61184_61///////
jint Get_MessageType_PGN61184_61(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_SETTING_61184_61.MessageType;
}
jint Get_TestMode_PGN61184_61(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_SETTING_61184_61.TestMode;
}
jint Get_CoolingFanReverseMode_182_PGN61184_61(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseMode_182;
}
jint Get_CoolingFanValveCurrent_146_PGN61184_61(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_SETTING_61184_61.CoolingFanValveCurrent_146;
}
jint Get_CoolingFanReverseIntervalTime_211_PGN61184_61(JNIEnv * env,
		jobject this) {
	return RX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseIntervalTime_211;
}
jint Get_CoolingFanReverseOperatingTime_212_PGN61184_61(JNIEnv * env,
		jobject this) {
	return RX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseOperatingTime_212;
}
//////RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
jint Get_MessageType_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.MessageType;
}
jint Get_WeighingSystemAccumulationMode_1941_PGN61184_62(JNIEnv * env,
		jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeighingSystemAccumulationMode_1941;
}
jint Get_RequestReweighing_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.RequestReweighing;
}
jint Get_RequestTotalWorkWeightReset_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.RequestTotalWorkWeightReset;
}
jint Get_WeightOffsetSelection_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeightOffsetSelection;
}
jint Get_WeightOffsetSetting_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeightOffsetSetting;
}
jint Get_WeightOffset_1922_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeightOffset_1922;
}
jint Get_WeighingDisplayMode1_1910_PGN61184_62(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeighingDisplayMode1_1910;
}
//////RX_WEIGHT_OFFSET_61184_63///////
jint Get_MessageType_PGN61184_63(JNIEnv * env, jobject this) {
	return RX_WEIGHT_OFFSET_61184_63.MessageType;
}
jint Get_WeightOffsetSelectionStatus_PGN61184_63(JNIEnv * env, jobject this) {
	return RX_WEIGHT_OFFSET_61184_63.WeightOffsetSelectionStatus;
}
jint Get_WeightOffsetWorkTool1_1922_PGN61184_63(JNIEnv * env, jobject this) {
	return RX_WEIGHT_OFFSET_61184_63.WeightOffsetWorkTool1_1922;
}
jint Get_WeightOffsetWorkTool2_1922_PGN61184_63(JNIEnv * env, jobject this) {
	return RX_WEIGHT_OFFSET_61184_63.WeightOffsetWorkTool2_1922;
}
jint Get_WeightOffsetWorkTool3_1922_PGN61184_63(JNIEnv * env, jobject this) {
	return RX_WEIGHT_OFFSET_61184_63.WeightOffsetWorkTool3_1922;
}
//////PARALLEL_LIFT_OPERATION_STATUS_61184_65///////
jint Get_MessageType_PGN61184_65(JNIEnv * env, jobject this) {
	return RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65.MessageType;
}
jint Get_ParallelLiftTargetAngleSettingErrorStatus_1924_PGN61184_65(JNIEnv * env, jobject this) {
	return RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65.ParallelLiftTargetAngleSettingErrorStatus_1924;
}
jint Get_ParallelLiftInhibitedStatus_1925_PGN61184_65(JNIEnv * env, jobject this) {
	return RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65.ParallelLiftInhibitedStatus_1925;
}
jint Get_ParallelLiftOverloadStatus_1926_PGN61184_65(JNIEnv * env, jobject this) {
	return RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65.ParallelLiftOverloadStatus_1926;
}
//////RX_MACHINE_MODE_SETTING_61184_101///////
jint Get_MessageType_PGN61184_101(JNIEnv * env, jobject this) {
	return RX_MACHINE_MODE_SETTING_61184_101.MessageType;
}
jint Get_EngineAlternateLowIdleSwitch_348_PGN61184_101(JNIEnv * env,
		jobject this) {
	return RX_MACHINE_MODE_SETTING_61184_101.EngineAlternateLowIdleSwitch_348;
}
jint Get_EnginePowerMode_347_PGN61184_101(JNIEnv * env, jobject this) {
	return RX_MACHINE_MODE_SETTING_61184_101.EnginePowerMode_347;
}
//////RX_TRAVEL_MODE_SETTING_61184_104///////
jint Get_MessageType_PGN61184_104(JNIEnv * env, jobject this) {
	return RX_TRAVEL_MODE_SETTING_61184_104.MessageType;
}
jint Get_TransmisstionShiftMode_543_PGN61184_104(JNIEnv * env, jobject this) {
	return RX_TRAVEL_MODE_SETTING_61184_104.TransmisstionShiftMode_543;
}
jint Get_ClutchCutoffMode_544_PGN61184_104(JNIEnv * env, jobject this) {
	return RX_TRAVEL_MODE_SETTING_61184_104.ClutchCutoffMode_544;
}
jint Get_KickDownShiftMode_547_PGN61184_104(JNIEnv * env, jobject this) {
	return RX_TRAVEL_MODE_SETTING_61184_104.KickDownShiftMode_547;
}
jint Get_TransmissionTCLockupEngaged_568_PGN61184_104(JNIEnv * env,
		jobject this) {
	return RX_TRAVEL_MODE_SETTING_61184_104.TransmissionTCLockupEngaged_568;
}
jint Get_VehicleSpeedLimitMode_575_PGN61184_104(JNIEnv * env, jobject this) {
	return RX_TRAVEL_MODE_SETTING_61184_104.VehicleSpeedLimitMode_575;
}
//////RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
jint Get_MessageType_PGN61184_105(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.MessageType;
}
jint Get_SettingSelection_PGN61184_105(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.SettingSelection;
}
jint Get_SpeedometerFrequency_534_PGN61184_105(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.SpeedometerFrequency_534;
}
jint Get_AutoRideControlOperationSpeedForward_574_PGN61184_105(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.AutoRideControlOperationSpeedForward_574;
}
jint Get_AutoRideControlOperationSpeedBackward_576_PGN61184_105(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.AutoRideControlOperationSpeedBackward_576;
}
jint Get_VehicleSpeedLimit_572_PGN61184_105(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.VehicleSpeedLimit_572;
}
//////RX_TRAVEL_CONTROL_VALUE_61184_106///////
jint Get_MessageType_PGN61184_106(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_61184_106.MessageType;
}
jint Get_SpeedometerFrequency_534_PGN61184_106(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_61184_106.SpeedometerFrequency_534;
}
jint Get_AutoRideControlOperationSpeedForward_574_PGN61184_106(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_61184_106.AutoRideControlOperationSpeedForward_574;
}
jint Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_61184_106.AutoRideControlOperationSpeedBackward_576;
}
jint Get_VehicleSpeedLimit_572_PGN61184_106(JNIEnv * env, jobject this) {
	return RX_TRAVEL_CONTROL_VALUE_61184_106.VehicleSpeedLimit_572;
}
//////RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
jint Get_MessageType_PGN61184_109(JNIEnv * env, jobject this) {
	return RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.MessageType;
}
jint Get_BacklightIlluminationLevel_719_PGN61184_109(JNIEnv * env, jobject this) {
	return RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.BacklightIlluminationLevel_719;
}
jint Get_WiperSpeedLevel_718_PGN61184_109(JNIEnv * env, jobject this) {
	return RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.WiperSpeedLevel_718;
}
jint Get_Clock_819_PGN61184_109(JNIEnv * env, jobject this) {
	return RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.Clock_819;
}
jint Get_RequestEngineLowIdleSpeed_PGN61184_109(JNIEnv * env, jobject this) {
	return RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.RequestEngineLowIdleSpeed;
}
jint Get_RequestTripDataReset_PGN61184_109(JNIEnv * env, jobject this) {
	return RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.RequestTripDataReset;
}
//////RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
jint Get_MessageType_PGN61184_121(JNIEnv * env, jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.MessageType;
}
jint Get_AutomaticEngineShutdown_363_PGN61184_121(JNIEnv * env, jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.AutomaticEngineShutdown_363;
}
jint Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.SettingTimeforAutomaticEngineShutdown_364;
}
jint Get_EngineShutdownCotrolByte_PGN61184_121(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.EngineShutdownCotrolByte;
}
jint Get_DelayedEngineShutdown_365_PGN61184_121(JNIEnv * env, jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.DelayedEngineShutdown_365;
}
jint Get_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.SettingTimeofrDelayedEngineShutdown_366;
}
//////RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
jint Get_MessageType_PGN61184_122(JNIEnv * env, jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.MessageType;
}
jint Get_AutomaticEngineShutdown_363_PGN61184_122(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.AutomaticEngineShutdown_363;
}
jint Get_AutomaticEngineShutdownType_PGN61184_122(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.AutomaticEngineShutdownType;
}
jint Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.SettingTimeforAutomaticEngineShutdown_364;
}
jint Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.RemainingTimeforAutomaticEngineShutdown;
}
jint Get_SettingTimeforDelayedEngineShutdown_366_PGN61184_122(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.SettingTimeforDelayedEngineShutdown_366;
}
jint Get_RemainingTimeforDelayedEngineShutdown_PGN61184_122(JNIEnv * env,
		jobject this) {
	return RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.RemainingTimeforDelayedEngineShutdown;
}
//////RX_DETENT_MODE_SETTING_61184_123///////
jint Get_MessageType_PGN61184_123(JNIEnv * env, jobject this) {
	return RX_DETENT_MODE_SETTING_61184_123.MessageType;
}
jint Get_BoomDetentMode_223_PGN61184_123(JNIEnv * env, jobject this) {
	return RX_DETENT_MODE_SETTING_61184_123.BoomDetentMode_223;
}
jint Get_BucketDetentMode_224_PGN61184_123(JNIEnv * env, jobject this) {
	return RX_DETENT_MODE_SETTING_61184_123.BucketDetentMode_224;
}
jint Get_RequestDetentReleasePositionSetting_PGN61184_123(JNIEnv * env,
		jobject this) {
	return RX_DETENT_MODE_SETTING_61184_123.RequestDetentReleasePositionSetting;
}
//////RX_DETENT_MODE_STATUS_61184_124///////
jint Get_MessageType_PGN61184_124(JNIEnv * env, jobject this) {
	return RX_DETENT_MODE_STATUS_61184_124.MessageType;
}
jint Get_BoomDetentMode_223_PGN61184_124(JNIEnv * env, jobject this) {
	return RX_DETENT_MODE_STATUS_61184_124.BoomDetentMode_223;
}
jint Get_BucketDetentMode_224_PGN61184_124(JNIEnv * env, jobject this) {
	return RX_DETENT_MODE_STATUS_61184_124.BucketDetentMode_224;
}
jint Get_DetentReleasePositionSettingStatus_PGN61184_124(JNIEnv * env,
		jobject this) {
	return RX_DETENT_MODE_STATUS_61184_124.DetentReleasePositionSettingStatus;
}
//////RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
jint Get_MessageType_PGN61184_129(JNIEnv * env, jobject this) {
	return RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129.MessageType;
}
jint Get_EngineShutdownCommand_3472_PGN61184_129(JNIEnv * env, jobject this) {
	return RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129.EngineShutdownCommand_3472;
}
//////RX_AS_PHONE_NUMBER_SETTING_61184_151///////
jint Get_MessageType_PGN61184_151(JNIEnv * env, jobject this) {
	return RX_AS_PHONE_NUMBER_SETTING_61184_151.MessageType;
}
jbyteArray Get_ASPhoneNumber_PGN61184_151(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_AS_PHONE_NUMBER_SETTING_61184_151.ASPhoneNumber));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_AS_PHONE_NUMBER_SETTING_61184_151.ASPhoneNumber),
			RX_AS_PHONE_NUMBER_SETTING_61184_151.ASPhoneNumber);

	return Data;
}
//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
jint Get_MessageType_PGN61184_201(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.MessageType;
}
jint Get_RequestBoomPressureCalibration_PGN61184_201(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestBoomPressureCalibration;
}
jint Get_RequestBoomBucketAngleSensorCalibration_PGN61184_201(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestBoomBucketAngleSensorCalibration;
}
jint Get_RequestAEB_PGN61184_201(JNIEnv * env, jobject this) {
	return TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestAEB;
}
jint Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201(JNIEnv * env,
		jobject this) {
	return TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestBrakePedalPositionSensorCalibration;
}
//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
jint Get_MessageType_PGN61184_202(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.MessageType;
}
jint Get_BoomPressureCalibrationStatus_1908_PGN61184_202(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.BoomPressureCalibrationStatus_1908;
}
jint Get_AngleSensorCalibrationStatus_1909_PGN61184_202(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AngleSensorCalibrationStatus_1909;
}
jint Get_AEBStatusInformation_MainCode_562_PGN61184_202(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AEBStatusInformation_MainCode_562;
}
jint Get_AEBCycleNumber_540_PGN61184_202(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AEBCycleNumber_540;
}
jint Get_AEBStatusInformation_SubCode_563_PGN61184_202(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AEBStatusInformation_SubCode_563;
}
jint Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202(
		JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.BrakePedalPositionSensorCalibrationStatus_564;
}
jint Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202(
		JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.BrakePedalPositionSensorCalibration_FaultInformation_565;
}

//////RX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
jint Get_MessageType_PGN61184_203(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_SETTING_61184_203.MessageType;
}
jint Get_BucketPriorityOperation_2301_PGN61184_203(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_SETTING_61184_203.BucketPriorityOperation_2301;
}
jint Get_FlowFineModulationOperation_2302_PGN61184_203(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_EHCU_SETTING_61184_203.FlowFineModulationOperation_2302;
}
jint Get_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_EHCU_SETTING_61184_203.AuxiliaryAttachmentMaxFlowLevel;
}
jint Get_BoomLeverFloatingPosition_PGN61184_203(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_EHCU_SETTING_61184_203.BoomLeverFloatingPosition;
}

//////RX_MONIOTR_STATUS_65327///////
jint Get_RequestBuzzerStop_PGN65327(JNIEnv * env, jobject this) {
	return RX_MONIOTR_STATUS_65327.RequestBuzzerStop;
}
jint Get_SpeedmeterUnitChange_PGN65327(JNIEnv * env, jobject this) {
	return RX_MONIOTR_STATUS_65327.SpeedmeterUnitChange;
}
//////RX_RMCU_STATUS_65329///////
jint Get_RMCUNetworkType_1621_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.RMCUNetworkType_1621;
}
jint Get_RMCUBackupBatteryVoltage_1590_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.RMCUBackupBatteryVoltage_1590;
}
jint Get_RMCUPowerSource_1594_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.RMCUPowerSource_1594;
}
jint Get_RMCUBoxOpeningStatus_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.RMCUBoxOpeningStatus;
}
jint Get_NetworkCommunicationStatus1_1622_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.NetworkCommunicationStatus1_1622;
}
jint Get_PositionUpdateStatus_852_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.PositionUpdateStatus_852;
}
jint Get_MachinePositionStatus_1593_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.MachinePositionStatus_1593;
}
jint Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329(JNIEnv * env,
		jobject this) {
	return RX_RMCU_STATUS_65329.GPSAntennaConnectionAlarmStatus_1595;
}
jint Get_NetworkTransceiverStatus1_1623_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.NetworkTransceiverStatus1_1623;
}
jint Get_NetworkServiceStatus1_1624_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.NetworkServiceStatus1_1624;
}
jint Get_NetworkAntennaStatus1_1625_PGN65329(JNIEnv * env, jobject this) {
	return RX_RMCU_STATUS_65329.NetworkAntennaStatus1_1625;
}
jint Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329(JNIEnv * env,
		jobject this) {
	return RX_RMCU_STATUS_65329.RMCUData_NumberofMessagestoTransmit_855;
}
//////RX_COMPONENT_IDENTIFICATION_65330///////
jint Get_ComponentCode_1699_PGN65330(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330(JNIEnv * env,
		jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_CLUSTER_65330///////
jint Get_ComponentCode_1699_PGN65330_CLUSTER(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_CLUSTER_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_CLUSTER(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_CLUSTER_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_CLUSTER(JNIEnv * env,
		jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_CLUSTER_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_CLUSTER_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_CLUSTER_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
jint Get_ComponentCode_1699_PGN65330_MONITOR(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_MONITOR(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_MONITOR_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_MONITOR(JNIEnv * env,
		jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_EHCU_65330///////
jint Get_ComponentCode_1699_PGN65330_EHCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_EHCU_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_EHCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_EHCU_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_EHCU(JNIEnv * env,
		jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_EHCU_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_EHCU_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_EHCU_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_RMCU_65330///////
jint Get_ComponentCode_1699_PGN65330_RMCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_RMCU_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_RMCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_RMCU_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_RMCU(JNIEnv * env,
		jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_RMCU_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_RMCU_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_RMCU_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_ECM_65330///////
jint Get_ComponentCode_1699_PGN65330_ECM(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_ECM(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_ECM_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_ECM(JNIEnv * env,
		jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_TCU_65330///////
jint Get_ComponentCode_1699_PGN65330_TCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_TCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_TCU_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_TCU(JNIEnv * env,
		jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_ACU_65330///////
jint Get_ComponentCode_1699_PGN65330_ACU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_ACU_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_ACU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_ACU_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_ACU(JNIEnv * env,
		jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_ACU_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_ACU_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_ACU_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_COMPONENT_IDENTIFICATION_BKCU_65330///////
jint Get_ComponentCode_1699_PGN65330_BKCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_BKCU_65330.ComponentCode_1699;
}
jint Get_ManufacturerCode_1700_PGN65330_BKCU(JNIEnv * env, jobject this) {
	return RX_COMPONENT_IDENTIFICATION_BKCU_65330.ManufacturerCode_1700;
}
jbyteArray Get_ComponentBasicInformation_1698_PGN65330_BKCU(JNIEnv * env,
		jobject this) {

	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_COMPONENT_IDENTIFICATION_BKCU_65330.ComponentBasicInformation_1698));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_COMPONENT_IDENTIFICATION_BKCU_65330.ComponentBasicInformation_1698),RX_COMPONENT_IDENTIFICATION_BKCU_65330.ComponentBasicInformation_1698);

	return Data;
}
//////RX_TRIP_TIME_INFORMATION_65344///////
jint Get_TripTime_849_PGN65344(JNIEnv * env, jobject this) {
	return RX_TRIP_TIME_INFORMATION_65344.TripTime_849;
}
//////RX_MACHINE_SECURITY_STATUS_65348///////
jint Get_ESLMode_820_PGN65348(JNIEnv * env, jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.ESLMode_820;
}
jint Get_LockMode_822_PGN65348(JNIEnv * env, jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.LockMode_822;
}
jint Get_LockLevel_823_PGN65348(JNIEnv * env, jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.LockLevel_823;
}
jint Get_LockDemander_824_PGN65348(JNIEnv * env, jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.LockDemander_824;
}
jint Get_EngineStartingPermission_821_PGN65348(JNIEnv * env, jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.EngineStartingPermission_821;
}
jint Get_PasswordIdentificationBlockedState_1631_PGN65348(JNIEnv * env,
		jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.PasswordIdentificationBlockedState_1631;
}
jint Get_ESLInterval_825_PGN65348(JNIEnv * env, jobject this) {
	return RX_MACHINE_SECURITY_STATUS_65348.ESLInterval_825;
}
//////RX_MACHINE_MODE_STATUS_65350///////
jint Get_EngineAlternateLowIdelSwitch_348_PGN65350(JNIEnv * env, jobject this) {
	return RX_MACHINE_MODE_STATUS_65350.EngineAlternateLowIdelSwitch_348;
}
jint Get_EnginePowerMode_347_PGN65350(JNIEnv * env, jobject this) {
	return RX_MACHINE_MODE_STATUS_65350.EnginePowerMode_347;
}
//////RX_HYDRAULIC_PRESSURE4_65354///////
jint Get_BrakeOilPressure_503_PGN65354(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE4_65354.BrakeOilPressure_503;
}
jint Get_BrakeOilChargingPriorityPressure_557_PGN65354(JNIEnv * env,
		jobject this) {
	return RX_HYDRAULIC_PRESSURE4_65354.BrakeOilChargingPriorityPressure_557;
}
//////RX_HYDRAULIC_PRESSURE6_65356///////
jint Get_BoomCylinderHeadPressure_204_PGN65356(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE6_65356.BoomCylinderHeadPressure_204;
}
jint Get_BoomCylinderRodPressure_205_PGN65356(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE6_65356.BoomCylinderRodPressure_205;
}
//////RX_HYDRAULIC_PRESSURE7_65357///////
jint Get_SteeringMainPumpPressure_202_PGN65357(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE7_65357.SteeringMainPumpPressure_202;
}
jint Get_EmergencySteeringPumpPressure_203_PGN65357(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE7_65357.EmergencySteeringPumpPressure_203;
}
jint Get_ParkingOilPressure_507_PGN65357(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE7_65357.ParkingOilPressure_507;
}
jint Get_DifferentialLockPressure_558_PGN65357(JNIEnv * env, jobject this) {
	return RX_HYDRAULIC_PRESSURE7_65357.DifferentialLockPressure_558;
}
//////RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
jint Get_AlternatorVoltage_707_PGN65360(JNIEnv * env, jobject this) {
	return RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360.AlternatorVoltage_707;
}
//////RX_RELAY_BUZZER_STATUS_65364///////
jint Get_AntiRestartRelay_327_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.AntiRestartRelay_327;
}
jint Get_FuelWarmerRelay_325_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.FuelWarmerRelay_325;
}
jint Get_EnginePreheatRelay_322_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.EnginePreheatRelay_322;
}
jint Get_EngineStopRelay_345_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.EngineStopRelay_345;
}
jint Get_WorkingCutoffRelay_164_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.WorkingCutoffRelay_164;
}
jint Get_TravelingCutoffRelay_517_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.TravelingCutoffRelay_517;
}
jint Get_ParkingRelay_514_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.ParkingRelay_514;
}
jint Get_WiperRelay_727_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.WiperRelay_727;
}
jint Get_EmergencySteeringPumpRelay_187_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.EmergencySteeringPumpRelay_187;
}
jint Get_TravelAlarmBuzzer_722_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.TravelAlarmBuzzer_722;
}
jint Get_Buzzer_723_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.Buzzer_723;
}
jint Get_FuelCutoffRelay_324_PGN65364(JNIEnv * env, jobject this) {
	return RX_RELAY_BUZZER_STATUS_65364.FuelCutoffRelay_324;
}
//////RX_SOLENOID_STATUS_65365///////
jint Get_BoomUpLeverDetentSolenoid_172_PGN65365(JNIEnv * env, jobject this) {
	return RX_SOLENOID_STATUS_65365.BoomUpLeverDetentSolenoid_172;
}
jint Get_BoomDownLeverDetentSolenoid_173_PGN65365(JNIEnv * env, jobject this) {
	return RX_SOLENOID_STATUS_65365.BoomDownLeverDetentSolenoid_173;
}
jint Get_BucketLeverDetentSolenoid_174_PGN65365(JNIEnv * env, jobject this) {
	return RX_SOLENOID_STATUS_65365.BucketLeverDetentSolenoid_174;
}
//////RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
jint Get_AcceleratorPedalPositionVoltage1_710_PGN65368(JNIEnv * env,
		jobject this) {
	return RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.AcceleratorPedalPositionVoltage1_710;
}
jint Get_BrakePedalPositionVoltage_573_PGN65368(JNIEnv * env, jobject this) {
	return RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.BrakePedalPositionVoltage_573;
}
jint Get_AcceleratorPedalPosition1_339_PGN65368(JNIEnv * env, jobject this) {
	return RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.AcceleratorPedalPosition1_339;
}
jint Get_AcceleratorPedalPositionVoltage2_710_PGN65368(JNIEnv * env,
		jobject this) {
	return RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.AcceleratorPedalPositionVoltage2_710;
}
//////RX_COOLING_FAN_STATUS_65369///////
jint Get_CoolingFanReverseMode_182_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanReverseMode_182;
}
jint Get_CoolingFanReverseSolenoid_181_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanReverseSolenoid_181;
}
jint Get_CoolingFanReverseSwitchManual_740_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanReverseSwitchManual_740;
}
jint Get_CoolingFanReverseSwitchAuto_741_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanReverseSwitchAuto_741;
}
jint Get_CoolingFanValveCurrent_146_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanValveCurrent_146;
}
jint Get_CoolingFanSpeed_318_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanSpeed_318;
}
jint Get_CoolingFanReverseIntervalTime_211_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanReverseIntervalTime_211;
}
jint Get_CoolingFanReverseOperatingTime_212_PGN65369(JNIEnv * env, jobject this) {
	return RX_COOLING_FAN_STATUS_65369.CoolingFanReverseOperatingTime_212;
}
//////RX_ENGINE_STATUS2_65370///////
jint Get_EngineFuelRate_331_PGN65370(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS2_65370.EngineFuelRate_331;
}
jint Get_EnginePercentLoadatCurrentSpeed_334_PGN65370(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS2_65370.EnginePercentLoadatCurrentSpeed_334;
}
jint Get_EngineActualPercentTorque_335_PGN65370(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS2_65370.EngineActualPercentTorque_335;
}
//////RX_ENGINE_STATUS1_65371///////
jint Get_EngineOperatingCondition_336_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.EngineOperatingCondition_336;
}
jint Get_EngineIntakeManifold1Temperatue_329_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.EngineIntakeManifold1Temperatue_329;
}
jint Get_EngineFuelTemperature_330_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.EngineFuelTemperature_330;
}
jint Get_EngineOilPressure_311_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.EngineOilPressure_311;
}
jint Get_BarometricPressure_328_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.BarometricPressure_328;
}
jint Get_EngineAirIntakePressure_337_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.EngineAirIntakePressure_337;
}
jint Get_DEFTankLevel_362_PGN65371(JNIEnv * env, jobject this) {
	return RX_ENGINE_STATUS1_65371.DEFTankLevel_362;
}
//////RX_AIR_CONDITIONER_STATUS_65373////////
jint Get_FATCSettingTemperatureCelsius_3408_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.FATCSettingTemperatureCelsius_3408;
}
jint Get_FATCSettingTemperatureFahrenheit_3409_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.FATCSettingTemperatureFahrenheit_3409;
}
jint Get_Ambienttemperaturesensoropen_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Ambienttemperaturesensoropen;
}
jint Get_Ambienttemperaturesensorshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Ambienttemperaturesensorshort;
}
jint Get_Incabtemperaturesensoropen_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Incabtemperaturesensoropen;
}
jint Get_Incabtemperaturesensorshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Incabtemperaturesensorshort;
}
jint Get_Evaptemperaturesensoropen_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Evaptemperaturesensoropen;
}
jint Get_Evaptemperaturesensorshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Evaptemperaturesensorshort;
}
jint Get_Mode1actuatoropenshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Mode1actuatoropenshort;
}
jint Get_Mode1actuatordrivecircuitmalfunction_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Mode1actuatordrivecircuitmalfunction;
}
jint Get_Intakeactuatoropenshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Intakeactuatoropenshort;
}
jint Get_Intakeactuatordrivecircuitmalfunction_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Intakeactuatordrivecircuitmalfunction;
}
jint Get_Temperatureactuatoropenshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Temperatureactuatoropenshort;
}
jint Get_Temperatureactuatordrivecircuitmalfunction_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Temperatureactuatordrivecircuitmalfunction;
}
jint Get_Ducttemperaturesensoropen_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Ducttemperaturesensoropen;
}
jint Get_Ducttemperaturesensorshort_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.Ducttemperaturesensorshort;
}
jint Get_WaterValveSensorError_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.WaterValveSensorError;
}
jint Get_GPSCircuitError_PGN65373(JNIEnv * env, jobject this) {
	return RX_AIR_CONDITIONER_STATUS_65373.GPSCircuitError;
}
//////RX_VEHICLE_DISTANCE_65389///////
jint Get_TripDistance_600_PGN65389(JNIEnv * env, jobject this) {
	return RX_VEHICLE_DISTANCE_65389.TripDistance_600;
}
jint Get_TotalVehicleDistance_601_PGN65389(JNIEnv * env, jobject this) {
	return RX_VEHICLE_DISTANCE_65389.TotalVehicleDistance_601;
}
///////////////////////////RX_FUEL_INFORMATION_ECO_GAUGE_65390///////////////////////////////////
jint Get_FuelLevel_302_PGN65390(JNIEnv *env, jobject this) {
	return RX_FUEL_INFORMATION_ECO_GAUGE_65390.FuelLevel_302;
}
jint Get_EcoGaugeLevel_1304_PGN65390(JNIEnv *env, jobject this) {
	return RX_FUEL_INFORMATION_ECO_GAUGE_65390.EcoGaugeLevel_1304;
}
jint Get_EcoGaugeStatus_1305_PGN65390(JNIEnv *env, jobject this) {
	return RX_FUEL_INFORMATION_ECO_GAUGE_65390.EcoGaugeStatus_1305;
}
jint Get_AverageFuelRate_333_PGN65390(JNIEnv *env, jobject this) {
	return RX_FUEL_INFORMATION_ECO_GAUGE_65390.AverageFuelRate_333;
}
jint Get_ADaysFuelUsed_1405_PGN65390(JNIEnv *env, jobject this) {
	return RX_FUEL_INFORMATION_ECO_GAUGE_65390.ADaysFuelUsed_1405;
}
//////////////////////////////////////////////////////////////////////////////////////////
//////RX_CYLINDER_ANGLE_STROKE1_65395///////
jint Get_BoomLinkAngle_1920_PGN65395(JNIEnv * env, jobject this) {
	return RX_CYLINDER_ANGLE_STROKE1_65395.BoomLinkAngle_1920;
}
jint Get_BellCrankAngle_1921_PGN65395(JNIEnv * env, jobject this) {
	return RX_CYLINDER_ANGLE_STROKE1_65395.BellCrankAngle_1921;
}
jint Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395(JNIEnv * env,
		jobject this) {
	return RX_CYLINDER_ANGLE_STROKE1_65395.BoomLinkAngleSensorSignalVoltage_728;
}
jint Get_BellCrankAngleSensorSignalVoltage_729_PGN65395(JNIEnv * env,
		jobject this) {
	return RX_CYLINDER_ANGLE_STROKE1_65395.BellCrankAngleSensorSignalVoltage_729;
}
jint Get_BucketCylinderStroke_1930_PGN65395(JNIEnv * env, jobject this) {
	return RX_CYLINDER_ANGLE_STROKE1_65395.BucketCylinderStroke_1930;
}
//////RX_CYLINDER_ANGLE_STROKE2_65396///////
jint Get_BucketAngle_1931_PGN65396(JNIEnv * env, jobject this) {
	return RX_CYLINDER_ANGLE_STROKE2_65396.BucketAngle_1931;
}
//////RX_AS_PHONE_NUMBER_65425///////
jbyteArray Get_ASPhoneNumber_1095_PGN65425(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_AS_PHONE_NUMBER_65425.ASPhoneNumber_1095));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_AS_PHONE_NUMBER_65425.ASPhoneNumber_1095),RX_AS_PHONE_NUMBER_65425.ASPhoneNumber_1095);

	return Data;
}
//////RX_WARNING_LAMP_65427///////
jint Get_FuelLevelLow_303_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.FuelLevelLow_303;
}
jint Get_HydraulicOilTemperatureHigh_102_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.HydraulicOilTemperatureHigh_102;
}
jint Get_BatteryVoltageLow_706_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.BatteryVoltageLow_706;
}
jint Get_Overload_104_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.Overload_104;
}
jint Get_AirCleanerClog_317_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.AirCleanerClog_317;
}
jint Get_EngineCheckLamp_320_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EngineCheckLamp_320;
}
jint Get_TransmissionOilPressureLow_502_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.TransmissionOilPressureLow_502;
}
jint Get_BrakeOilPressureLow_504_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.BrakeOilPressureLow_504;
}
jint Get_EngineCoolantTemperatureHigh_305_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EngineCoolantTemperatureHigh_305;
}
jint Get_EngineCoolantLevelLow_307_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EngineCoolantLevelLow_307;
}
jint Get_EngineOilPressureLow_313_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EngineOilPressureLow_313;
}
jint Get_EngineOilFilterClog_315_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EngineOilFilterClog_315;
}
jint Get_TransmissionOilTemperatureHigh_537_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.TransmissionOilTemperatureHigh_537;
}
jint Get_TransmissionCheck_538_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.TransmissionCheck_538;
}
jint Get_SteeringMainPumpPressureLow_184_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.SteeringMainPumpPressureLow_184;
}
jint Get_EmergencySteeringActive_185_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EmergencySteeringActive_185;
}
jint Get_WarningSymbolLamp_709_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.WarningSymbolLamp_709;
}
jint Get_WaterInFuelIndicator_360_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.WaterInFuelIndicator_360;
}
jint Get_DTCAlarmLamp_1509_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.DTCAlarmLamp_1509;
}
jint Get_EngineStopLamp_319_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EngineStopLamp_319;
}
jint Get_DPFLampCommand_352_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.DPFLampCommand_352;
}
jint Get_DEFLowLamp_358_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.DEFLowLamp_358;
}
jint Get_ClutchSlip_569_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.ClutchSlip_569;
}
jint Get_BrakeOilLevelLow_566_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.BrakeOilLevelLow_566;
}
jint Get_BrakeOilTemperatureHigh_567_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.BrakeOilTemperatureHigh_567;
}
jint Get_EmissionSystemFailLamp_357_PGN65427(JNIEnv * env, jobject this) {
	return RX_WARNING_LAMP_65427.EmissionSystemFailLamp_357;
}
//////RX_INDICATOR_LAMP_65428///////
jint Get_PowerMaxStatus_802_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.PowerMaxStatus_802;
}
jint Get_DecelerationStatus_803_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.DecelerationStatus_803;
}
jint Get_WarmingUpStatus_804_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.WarmingUpStatus_804;
}
jint Get_EnginePreheatStatus_323_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.EnginePreheatStatus_323;
}
jint Get_FuelWarmerActiveStatus_326_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.FuelWarmerActiveStatus_326;
}
jint Get_CoolingFandrivingStatus_180_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.CoolingFandrivingStatus_180;
}
jint Get_CruiseStatus_519_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.CruiseStatus_519;
}
jint Get_ParkingStatus_508_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.ParkingStatus_508;
}
jint Get_ClutchCutoffStatus_545_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.ClutchCutoffStatus_545;
}
jint Get_FNRJoystickActiveStatus_546_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.FNRJoystickActiveStatus_546;
}
jint Get_RideControlStatus_550_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.RideControlStatus_550;
}
jint Get_DifferentialLockStatus_559_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.DifferentialLockStatus_559;
}
jint Get_JoystickSteeringActiveStatus_186_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.JoystickSteeringActiveStatus_186;
}
jint Get_InchingStatus_549_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.InchingStatus_549;
}
jint Get_RamLockStatus_520_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.RamLockStatus_520;
}
jint Get_WorkingBrakeStatus_506_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.WorkingBrakeStatus_506;
}
jint Get_MaintenanceAlarmLamp_1099_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.MaintenanceAlarmLamp_1099;
}
jint Get_OperatorAbsenceStatus_832_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.OperatorAbsenceStatus_832;
}
jint Get_MirrorHeaterStatus_724_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.MirrorHeaterStatus_724;
}
jint Get_HighBeamStatus_725_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.HighBeamStatus_725;
}
jint Get_TravelAlarmSwitch_721_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.TravelAlarmSwitch_721;
}
jint Get_SwingBoomSwitch_726_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.SwingBoomSwitch_726;
}
jint Get_ParkingWorkingTravelStatus_510_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.ParkingWorkingTravelStatus_510;
}
jint Get_TransmissionTCLockupEngaged_556_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.TransmissionTCLockupEngaged_556;
}
jint Get_ExhaustSystemHighTemperatureLampCommand_353_PGN65428(JNIEnv * env,
		jobject this) {
	return RX_INDICATOR_LAMP_65428.ExhaustSystemHighTemperatureLampCommand_353;
}
jint Get_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428(
		JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354;
}
jint Get_AutomaticEngineShutdown_363_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.AutomaticEngineShutdown_363;
}
jint Get_DelayedEngineShutdown_365_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.DelayedEngineShutdown_365;
}
jint Get_AutoGreaseGreenLEDStatus_3453_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.AutoGreaseGreenLEDStatus_3453;
}
jint Get_AutoGreaseRedLEDStatus_3453_PGN65428(JNIEnv * env, jobject this) {
	return RX_INDICATOR_LAMP_65428.AutoGreaseRedLEDStatus_3453;
}

//////RX_GAUGE_65431///////
jint Get_FuelLevel_301_PGN65431(JNIEnv * env, jobject this) {
	return RX_GAUGE_65431.FuelLevel_301;
}
jint Get_EngineCoolantTemperature_304_PGN65431(JNIEnv * env, jobject this) {
	return RX_GAUGE_65431.EngineCoolantTemperature_304;
}
jint Get_HydraulicOilTemperature_101_PGN65431(JNIEnv * env, jobject this) {
	return RX_GAUGE_65431.HydraulicOilTemperature_101;
}
jint Get_TransmissionOilTemperature_536_PGN65431(JNIEnv * env, jobject this) {
	return RX_GAUGE_65431.TransmissionOilTemperature_536;
}
jint Get_EngineSpeed_310_PGN65431(JNIEnv * env, jobject this) {
	return RX_GAUGE_65431.EngineSpeed_310;
}
jint Get_BatteryVoltage_705_PGN65431(JNIEnv * env, jobject this) {
	return RX_GAUGE_65431.BatteryVoltage_705;
}
//////RX_HOURMETER_CLOCK_WIPER_65433///////
jint Get_Hourmeter_1601_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.Hourmeter_1601;
}
jint Get_Clock_819_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.Clock_819;
}
jint Get_HourmeterActiveStatus_703_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.HourmeterActiveStatus_703;
}
jint Get_WiperOperationStatus_717_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.WiperOperationStatus_717;
}
jint Get_WiperSpeedLevel_718_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.WiperSpeedLevel_718;
}
jint Get_BacklightIlluminationLevel_719_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.BacklightIlluminationLevel_719;
}
jint Get_IlluminationStatus_737_PGN65433(JNIEnv * env, jobject this) {
	return RX_HOURMETER_CLOCK_WIPER_65433.IlluminationStatus_737;
}
//////RX_MACHINE_TRAVEL_STATUS_65434///////
jint Get_WheelBasedVehicleSpeed_532_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.WheelBasedVehicleSpeed_532;
}
jint Get_SelectGear_541_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.SelectGear_541;
}
jint Get_ActualGear_542_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.ActualGear_542;
}
jint Get_TransmissionShiftMode_543_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.TransmissionShiftMode_543;
}
jint Get_ClutchCutoffMode_544_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.ClutchCutoffMode_544;
}
jint Get_KickDownShiftMode_547_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.KickDownShiftMode_547;
}
jint Get_TransmissionTCLockupEngaged_568_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.TransmissionTCLockupEngaged_568;
}
jint Get_DifferentialLockMode_570_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.DifferentialLockMode_570;
}
jint Get_VehicleSpeedLimitMode_575_PGN65434(JNIEnv * env, jobject this) {
	return RX_MACHINE_TRAVEL_STATUS_65434.VehicleSpeedLimitMode_575;
}
//////RX_DTC_INFORMATION_TYPE1_65438///////
jint Get_DTCType_1510_PGN65438(JNIEnv * env, jobject this) {
	return RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510;
}
jint Get_NumberofDTCinThisPacket_1514_PGN65438(JNIEnv * env, jobject this) {
	return RX_DTC_INFORMATION_TYPE1_65438.NumberofDTCinThisPacket_1514;
}
jint Get_TotalNumberofDTC_PGN65438(JNIEnv * env, jobject this) {
	return RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
}
jint Get_TotalNumberofDTCInformationPacket_1512_PGN65438(JNIEnv * env,
		jobject this) {
	return RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
}
jint Get_SequenceNumberofDTCInformationPacket_1513_PGN65438(JNIEnv * env,
		jobject this) {
	return RX_DTC_INFORMATION_TYPE1_65438.SequenceNumberofDTCInformationPacket_1513;
}
jbyteArray Get_DTC_1_PGN65438(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_1));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_1),RX_DTC_INFORMATION_TYPE1_65438.DTC_1);

	return Data;
}
jbyteArray Get_DTC_2_PGN65438(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_2));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_2),RX_DTC_INFORMATION_TYPE1_65438.DTC_2);

	return Data;
}
jbyteArray Get_DTC_3_PGN65438(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_3));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_3),RX_DTC_INFORMATION_TYPE1_65438.DTC_3);

	return Data;
}
jbyteArray Get_DTC_4_PGN65438(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_4));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_4),RX_DTC_INFORMATION_TYPE1_65438.DTC_4);

	return Data;
}
jbyteArray Get_DTC_5_PGN65438(JNIEnv * env, jobject this) {
	jbyteArray Data = (*env)->NewByteArray(env, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_5));

	(*env)->SetByteArrayRegion(env, Data, 0, sizeof(RX_DTC_INFORMATION_TYPE1_65438.DTC_5),RX_DTC_INFORMATION_TYPE1_65438.DTC_5);

	return Data;
}
//////RX_CLUSTER_STATUS_65445///////
jint Get_Dashboard_Program_Version_988_PGN65445(JNIEnv * env, jobject this) {
	return RX_CLUSTER_STATUS_65445.Dashboard_Program_Version_988;
}
jint Get_Dashboard_Hardware_Version_989_PGN65445(JNIEnv * env, jobject this) {
	return RX_CLUSTER_STATUS_65445.Dashboard_Hardware_Version_989;
}
jint Get_Mirror_Heater_Status_724_PGN65445(JNIEnv * env, jobject this) {
	return RX_CLUSTER_STATUS_65445.Mirror_Heater_Status_724;
}
jint Get_High_Beam_Status_725_PGN65445(JNIEnv * env, jobject this) {
	return RX_CLUSTER_STATUS_65445.High_Beam_Status_725;
}
jint Get_Front_Rear_Lamp_Status_726_PGN65445(JNIEnv * env, jobject this) {
	return RX_CLUSTER_STATUS_65445.Front_Rear_Lamp_Status_726;
}
jint Get_HW_Vers_Sub_PGN65445(JNIEnv * env, jobject this) {
	return RX_CLUSTER_STATUS_65445.HW_Vers_Sub;
}
//////RX_AXLE_STATUS_65449///////
jint Get_Front_Axle_Oil_Temperature_577_PGN65449(JNIEnv * env,
		jobject this) {
	return RX_AXLE_STATUS_65449.Front_Axle_Oil_Temperature_577;
}
jint Get_Rear_Axle_Oil_Temperature_578_PGN65449(JNIEnv * env, jobject this) {
	return RX_AXLE_STATUS_65449.Rear_Axle_Oil_Temperature_578;
}
jint Get_Front_Axle_Oil_Temp_Warning_580_PGN65449(JNIEnv * env,
		jobject this) {
	return RX_AXLE_STATUS_65449.Front_Axle_Oil_Temp_Warning_580;
}
jint Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449(JNIEnv * env, jobject this) {
	return RX_AXLE_STATUS_65449.Rear_Axle_Oil_Temp_Warning_581;
}
//////RX_WEIGHING_SYSTEM_STATUS_65450///////
jint Get_WeighingSystemAccumulationMode_1941_PGN65450(JNIEnv * env,
		jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemAccumulationMode_1941;
}
jint Get_WeighingSystemBuzzer_1907_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemBuzzer_1907;
}
jint Get_CurrentWeighingResult_1919_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.CurrentWeighingResult_1919;
}
jint Get_WeighingDisplayMode1_1910_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.WeighingDisplayMode1_1910;
}
// ++, 150212 bwk
jint Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemError_BoomLiftSpeed_1942;
}
jint Get_WeighingSystemError_BucketFullIn_1943_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemError_BucketFullIn_1943;
}
jint Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemError_HydraulicOilTemperature_1944;
}
// --, 150212 bwk
jint Get_CurrentWeight_1911_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.CurrentWeight_1911;
}
jint Get_TodayWeight_1915_PGN65450(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_STATUS_65450.TodayWeight_1915;
}
//////RX_WEIGHING_SYSTEM_DATA1_65451///////
jint Get_TotalWorkAWeight_1912_PGN65451(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_DATA1_65451.TotalWorkAWeight_1912;
}
jint Get_TotalWorkBWeight_1913_PGN65451(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_DATA1_65451.TotalWorkBWeight_1913;
}
//////RX_WEIGHING_SYSTEM_DATA2_65452///////
jint Get_TotalWorkCWeight_1914_PGN65452(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_DATA2_65452.TotalWorkCWeight_1914;
}
jint Get_ADayBeforeWeight_1916_PGN65452(JNIEnv * env, jobject this) {
	return RX_WEIGHING_SYSTEM_DATA2_65452.ADayBeforeWeight_1916;
}
//////RX_BKCU_STATUS_65514///////
jint Get_ButtonKeyPosition_3471_PGN65514(JNIEnv * env, jobject this) {
	return RX_BKCU_STATUS_65514.ButtonKeyPosition;
}
//////RX_JOYSTICK_POSITION_STATUS_65515///////
jint Get_BoomJoystickPositionStatus_2310_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.BoomJoystickPositionStatus_2310;
}
jint Get_BoomJoystickPosition_2311_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.BoomJoystickPosition_2311;
}
jint Get_BucketJoystickPositionStatus_2312_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.BucketJoystickPositionStatus_2312;
}
jint Get_BucketJoystickPosition_2313_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.BucketJoystickPosition_2313;
}
jint Get_AuxJoystickPositionStatus_2314_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.AuxJoystickPositionStatus_2314;
}
jint Get_AuxJoystickPosition_2315_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.AuxJoystickPosition_2315;
}
jint Get_BoomLeverFloatingPosition_2336_PGN65515(JNIEnv * env, jobject this) {
	return RX_JOYSTICK_POSITION_STATUS_65515.BoomLeverFloatingPosition_2336;
}
//////RX_WHEEL_LOADER_EHCU_STATUS_65517///////
jint Get_BucketPriorityOperation_2301_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.BucketPriorityOperation_2301;
}
jint Get_FlowFineModulationOperation_2302_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.FlowFineModulationOperation_2302;
}
jint Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.AuxiliaryAttachmentMaxFlowLevel_2303;
}
jint Get_AttachmentPilotCutoffStatus_225_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.AttachmentPilotCutoffStatus_225;
}
jint Get_FloatMode_2316_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.FloatMode_2316;
}
jint Get_JoystickSteeringActiveStatusEHCU_186_PGN65517(JNIEnv * env,
		jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.JoystickSteeringActiveStatusEHCU_186;
}
jint Get_BoomUpEPPRValveCurrent_2304_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.BoomUpEPPRValveCurrent_2304;
}
jint Get_BoomDownEPPRValveCurrent_2305_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.BoomDownEPPRValveCurrent_2305;
}
jint Get_BucketInEPPRValveCurrent_2306_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.BucketInEPPRValveCurrent_2306;
}
jint Get_BucketOutEPPRValveCurrent_2307_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.BucketOutEPPRValveCurrent_2307;
}
jint Get_AUX1EPPRValveCurrent_2308_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.AUX1EPPRValveCurrent_2308;
}
jint Get_AUX2EPPRValveCurrent_2309_PGN65517(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS_65517.AUX2EPPRValveCurrent_2309;
}
//////RX_WHEEL_LOADER_EHCU_STATUS2_65524///////
jint Get_SoftStopBoomUp_2337_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.SoftStopBoomUp_2337;
}
jint Get_SoftStopBoomDown_2338_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.SoftStopBoomDown_2338;
}
jint Get_SoftStopBucketIn_2339_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.SoftStopBucketIn_2339;
}
jint Get_SoftStopBucketOut_2340_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.SoftStopBucketOut_2340;
}
jint Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.BoomDownEPPRValveMaxCurrent_2341;
}
jint Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.BucketOutEPPRValveMaxCurrent_2342;
}
jint Get_JoystickSteeringEnableFailCondition_2343_PGN65524(JNIEnv * env, jobject this) {
	return RX_WHEEL_LOADER_EHCU_STATUS2_65524.JoystickSteeringEnableFailCondition_2343;
}
//////RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
jint Get_TravelAlarmOperationStatus_3431_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.TravelAlarmOperationStatus_3431;
}
jint Get_WasherOperationStatus_3432_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.WasherOperationStatus_3432;
}
jint Get_wiperoperationstatus_3433_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.wiperoperationstatus_3433;
}
jint Get_IntWiperOperationStatus_3434_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IntWiperOperationStatus_3434;
}
jint Get_WorkLampOperationStatus_3435_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.WorkLampOperationStatus_3435;
}
jint Get_HeadLampOperationStatus_3436_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.HeadLampOperationStatus_3436;
}
jint Get_CabinLampOperationStatus_3437_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.CabinLampOperationStatus_3437;
}
jint Get_IlluminationOperationStatus_3438_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IlluminationOperationStatus_3438;
}
jint Get_OutriggerFrontRHOperationStatus_3439_PGN65527(JNIEnv * env,
		jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerFrontRHOperationStatus_3439;
}
jint Get_OutriggerFrontLHOperationStatus_3440_PGN65527(JNIEnv * env,
		jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerFrontLHOperationStatus_3440;
}
jint Get_OutriggerRearRHOperationStatus_3441_PGN65527(JNIEnv * env,
		jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerRearRHOperationStatus_3441;
}
jint Get_OutriggerRearLHOperationStatus_3442_PGN65527(JNIEnv * env,
		jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerRearLHOperationStatus_3442;
}
jint Get_OverloadSwitchOperationStatus_3443_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OverloadSwitchOperationStatus_3443;
}
jint Get_BeaconLampOperationStatus_3444_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.BeaconLampOperationStatus_3444;
}
jint Get_ForwardTravelAlarmOperationStatus_3445_PGN65527(JNIEnv * env,
		jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.ForwardTravelAlarmOperationStatus_3445;
}
jint Get_RearWorkLampOperationStatus_3446_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWorkLampOperationStatus_3446;
}
jint Get_RideControlOperationStatus_3447_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RideControlOperationStatus_3447;
}
jint Get_QuickCouplerOperationStatus_3448_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.QuickCouplerOperationStatus_3448;
}
jint Get_AutoGreaseOperationStatus_3449_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.AutoGreaseOperationStatus_3449;
}
jint Get_MirrorHeatOperationStatus_3450_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.MirrorHeatOperationStatus_3450;
}
jint Get_RearWiperOperationStatus_3451_PGN65527(JNIEnv * env, jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperOperationStatus_3451;
}
jint Get_RearWiperWasherOperationStatus_3452_PGN65527(JNIEnv * env,
		jobject this) {
	return RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperWasherOperationStatus_3452;
}
//////TX_DTC_INFORMATION_REQUEST_61184_11///////
void Set_MessageType_PGN61184_11(JNIEnv * env, jobject this, int Data) {
	TX_DTC_INFORMATION_REQUEST_61184_11.MessageType = Data;
}
void Set_DTCInformationRequest_1515_PGN61184_11(JNIEnv * env, jobject this,
		int Data) {
	TX_DTC_INFORMATION_REQUEST_61184_11.DTCInformationRequest_1515 = Data;
}
void Set_DTCType_1510_PGN61184_11(JNIEnv * env, jobject this, int Data) {
	TX_DTC_INFORMATION_REQUEST_61184_11.DTCType_1510 = Data;
}
void Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(JNIEnv * env,
		jobject this, int Data) {
	TX_DTC_INFORMATION_REQUEST_61184_11.SeqenceNumberofDTCInformationPacket_1513 =
			Data;
}
//////TX_MAINTENANCE_REQUSET_61184_12///////
void Set_MessageType_PGN61184_12(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_REQUSET_61184_12.MessageType = Data;
}
void Set_MaintenanceCommant_1097_PGN61184_12(JNIEnv * env, jobject this,
		int Data) {
	TX_MAINTENANCE_REQUSET_61184_12.MaintenanceCommant_1097 = Data;
}
void Set_MaintenanceItem_1098_PGN61184_12(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_REQUSET_61184_12.MaintenanceItem_1098 = Data;
}
void Set_MaintenanceInterval_1091_PGN61184_12(JNIEnv * env, jobject this,
		int Data) {
	TX_MAINTENANCE_REQUSET_61184_12.MaintenanceInterval_1091 = Data;
}
//////TX_MAINTENANCE_ITEM_LIST_61184_13///////
void Set_MessageType_PGN61184_13(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_ITEM_LIST_61184_13.MessageType = Data;
}
void Set_TotalNumberofMaintenanceItems_1100_PGN61184_13(JNIEnv * env,
		jobject this, int Data) {
	TX_MAINTENANCE_ITEM_LIST_61184_13.TotalNumberofMaintenanceItems_1100 = Data;
}
void Set_MaintenanceItem_1098_PGN61184_13(JNIEnv * env, jobject this,
		jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_MAINTENANCE_ITEM_LIST_61184_13.MaintenanceItem_1098);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_MAINTENANCE_ITEM_LIST_61184_13.MaintenanceItem_1098[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_MAINTENANCE_INFORMATION_61184_14///////
void Set_MessageType_PGN61184_14(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.MessageType = Data;
}
void Set_MaintenanceItem_1098_PGN61184_14(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.MaintenanceItem_1098 = Data;
}
void Set_MaintenanceAlarmLamp_1099_PGN61184_14(JNIEnv * env, jobject this,
		int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.MaintenanceAlarmLamp_1099 = Data;
}
void Set_MaintenanceEvent_PGN61184_14(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.MaintenanceEvent = Data;
}
void Set_MaintenanceInterval_1091_PGN61184_14(JNIEnv * env, jobject this,
		int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.MaintenanceInterval_1091 = Data;
}
void Set_MaintenanceTotalCount_1092_PGN61184_14(JNIEnv * env, jobject this,
		int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.MaintenanceTotalCount_1092 = Data;
}
void Set_HourmeterattheLastMaintenance_1093_PGN61184_14(JNIEnv * env,
		jobject this, int Data) {
	TX_MAINTENANCE_INFORMATION_61184_14.HourmeterattheLastMaintenance_1093 =
			Data;
}
//////TX_MAINTENANCE_HISTORY_61184_15///////
void Set_MessageType_PGN61184_15(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_HISTORY_61184_15.MessageType = Data;
}
void Set_MaintenanceItem_1098_PGN61184_15(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_HISTORY_61184_15.MaintenanceItem_1098 = Data;
}
void Set_HourmeterattheLastMaintenance_1093_PGN61184_15(JNIEnv * env,
		jobject this, int Data) {
	TX_MAINTENANCE_HISTORY_61184_15.HourmeterattheLastMaintenance_1093 = Data;
}
void Set_MaintenanceHistory_1094_PGN61184_15(JNIEnv * env, jobject this,
		jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_MAINTENANCE_HISTORY_61184_15.MaintenanceHistory_1094);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_MAINTENANCE_HISTORY_61184_15.MaintenanceHistory_1094[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);

}
//////TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
void Set_MessageType_PGN61184_16(JNIEnv * env, jobject this, int Data) {
	TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MessageType = Data;
}
void Set_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16(JNIEnv * env,
		jobject this, int Data) {
	TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.TotalNumberofMaintenanceAlarmLampOnItems_1100 =
			Data;
}
void Set_MaintenanceItem_PGN61184_16(JNIEnv * env, jobject this,
		jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MaintenanceItem);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MaintenanceItem[i] =
				pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);

}
//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
void Set_MessageType_PGN61184_21(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21.MessageType = Data;
}
//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
void Set_MessageType_PGN61184_22(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.MessageType = Data;
}
void Set_HCEAntiTheftRandomNumber_1632_PGN61184_22(JNIEnv * env, jobject this,
		jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.HCEAntiTheftRandomNumber_1632);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.HCEAntiTheftRandomNumber_1632[i] =
				pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);

}
//////TX_HCE_ANTI_THEFT_REQUEST_61184_23///////
void Set_MessageType_PGN61184_23(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_REQUEST_61184_23.MessageType = Data;
}
void Set_HCEAntiTheftCommand_1633_PGN61184_23(JNIEnv * env, jobject this,
		int Data) {
	TX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftCommand_1633 = Data;
}
void Set_ESLMode_820_PGN61184_23(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_REQUEST_61184_23.ESLMode_820 = Data;
}
void Set_ESLInterval_825_PGN61184_23(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_REQUEST_61184_23.ESLInterval_825 = Data;
}
void Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(JNIEnv * env, jobject this, jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftPasswordRepresentation_1634);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_HCE_ANTI_THEFT_REQUEST_61184_23.HCEAntiTheftPasswordRepresentation_1634[i] = pArr[i];


	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);

}
//////TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
void Set_MessageType_PGN61184_24(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24.MessageType = Data;
}
void Set_PasswordCertificationResult_956_PGN61184_24(JNIEnv * env, jobject this,
		int Data) {
	TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24.PasswordCertificationResult_956 =
			Data;
}
//////TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
void Set_MessageType_PGN61184_25(JNIEnv * env, jobject this, int Data) {
	TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.MessageType = Data;
}
void Set_PasswordChangeResult_958_PGN61184_25(JNIEnv * env, jobject this,
		int Data) {
	TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.PasswordChangeResult_958 =
			Data;
}
void Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25(JNIEnv * env,
		jobject this, jbyteArray Data) {

	jbyte *pArr;
	int i;
	int size = sizeof(TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.HCEAntiTheftPasswordRepresentation_1634);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.HCEAntiTheftPasswordRepresentation_1634[i] =
				pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);

}
//////TX_OPERATION_HISTORY_REQUEST_61184_31///////
void Set_MessageType_PGN61184_31(JNIEnv * env, jobject this, int Data) {
	TX_OPERATION_HISTORY_REQUEST_61184_31.MessageType = Data;
}
void Set_OperationHistory_1101_PGN61184_31(JNIEnv * env, jobject this, int Data) {
	TX_OPERATION_HISTORY_REQUEST_61184_31.OperationHistory_1101 = Data;
}
//////TX_COOLING_FAN_SETTING_61184_61///////
void Set_MessageType_PGN61184_61(JNIEnv * env, jobject this, int Data) {
	TX_COOLING_FAN_SETTING_61184_61.MessageType = Data;
}
void Set_TestMode_PGN61184_61(JNIEnv * env, jobject this, int Data) {
	TX_COOLING_FAN_SETTING_61184_61.TestMode = Data;
}
void Set_CoolingFanReverseMode_182_PGN61184_61(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseMode_182 = Data;
}
void Set_CoolingFanReverseManual_PGN61184_61(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseManual = Data;
}
void Set_CoolingFanValveCurrent_146_PGN61184_61(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_SETTING_61184_61.CoolingFanValveCurrent_146 = Data;
}
void Set_CoolingFanReverseIntervalTime_211_PGN61184_61(JNIEnv * env,
		jobject this, int Data) {
	TX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseIntervalTime_211 = Data;
}
void Set_CoolingFanReverseOperatingTime_212_PGN61184_61(JNIEnv * env,
		jobject this, int Data) {
	TX_COOLING_FAN_SETTING_61184_61.CoolingFanReverseOperatingTime_212 = Data;
}
//////TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
void Set_MessageType_PGN61184_62(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.MessageType = Data;
}
void Set_WeighingSystemAccumulationMode_1941_PGN61184_62(JNIEnv * env,
		jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeighingSystemAccumulationMode_1941 =
			Data;
}
void Set_RequestReweighing_PGN61184_62(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.RequestReweighing = Data;
}
void Set_RequestTotalWorkWeightReset_PGN61184_62(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.RequestTotalWorkWeightReset =
			Data;
}
void Set_WeightOffsetSelection_PGN61184_62(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeightOffsetSelection = Data;
}
void Set_WeightOffsetSetting_PGN61184_62(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeightOffsetSetting = Data;
}
void Set_WeightOffset_1922_PGN61184_62(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeightOffset_1922 = Data;
}
void Set_WeighingDisplayMode1_1910_PGN61184_62(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.WeighingDisplayMode1_1910 = Data;
}
//////TX_WEIGHT_OFFSET_61184_63///////
void Set_MessageType_PGN61184_63(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHT_OFFSET_61184_63.MessageType = Data;
}
void Set_WeightOffsetSelectionStatus_PGN61184_63(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHT_OFFSET_61184_63.WeightOffsetSelectionStatus = Data;
}
void Set_WeightOffsetWorkTool1_1922_PGN61184_63(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHT_OFFSET_61184_63.WeightOffsetWorkTool1_1922 = Data;
}
void Set_WeightOffsetWorkTool2_1922_PGN61184_63(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHT_OFFSET_61184_63.WeightOffsetWorkTool2_1922 = Data;
}
void Set_WeightOffsetWorkTool3_1922_PGN61184_63(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHT_OFFSET_61184_63.WeightOffsetWorkTool3_1922 = Data;
}
//////TX_MACHINE_MODE_SETTING_61184_101///////
void Set_MessageType_PGN61184_101(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_MODE_SETTING_61184_101.MessageType = Data;
}
void Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(JNIEnv * env,
		jobject this, int Data) {
	TX_MACHINE_MODE_SETTING_61184_101.EngineAlternateLowIdleSwitch_348 = Data;
}
void Set_EnginePowerMode_347_PGN61184_101(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_MODE_SETTING_61184_101.EnginePowerMode_347 = Data;
}
//////TX_TRAVEL_MODE_SETTING_61184_104///////
void Set_MessageType_PGN61184_104(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_MODE_SETTING_61184_104.MessageType = Data;
}
void Set_TransmisstionShiftMode_543_PGN61184_104(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_MODE_SETTING_61184_104.TransmisstionShiftMode_543 = Data;
}
void Set_ClutchCutoffMode_544_PGN61184_104(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_MODE_SETTING_61184_104.ClutchCutoffMode_544 = Data;
}
void Set_KickDownShiftMode_547_PGN61184_104(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_MODE_SETTING_61184_104.KickDownShiftMode_547 = Data;
}
void Set_TransmissionTCLockupEngaged_568_PGN61184_104(JNIEnv * env,
		jobject this, int Data) {
	TX_TRAVEL_MODE_SETTING_61184_104.TransmissionTCLockupEngaged_568 = Data;
}
void Set_VehicleSpeedLimitMode_575_PGN61184_104(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_MODE_SETTING_61184_104.VehicleSpeedLimitMode_575 = Data;
}
//////TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
void Set_MessageType_PGN61184_105(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.MessageType = Data;
}
void Set_SettingSelection_PGN61184_105(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.SettingSelection = Data;
}
void Set_SpeedometerFrequency_534_PGN61184_105(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.SpeedometerFrequency_534 = Data;
}
void Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.AutoRideControlOperationSpeedForward_574 =Data;
}
void Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.AutoRideControlOperationSpeedBackward_576 = Data;
}
void Set_VehicleSpeedLimit_572_PGN61184_105(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.VehicleSpeedLimit_572 = Data;
}
//////TX_TRAVEL_CONTROL_VALUE_61184_106///////
void Set_MessageType_PGN61184_106(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_61184_106.MessageType = Data;
}
void Set_SpeedometerFrequency_534_PGN61184_106(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_CONTROL_VALUE_61184_106.SpeedometerFrequency_534 = Data;
}
void Set_AutoRideControlOperationSpeedForward_574_PGN61184_106(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_61184_106.AutoRideControlOperationSpeedForward_574 = Data;
}
void Set_AutoRideControlOperationSpeedBackward_576_PGN61184_106(JNIEnv * env, jobject this, int Data) {
	TX_TRAVEL_CONTROL_VALUE_61184_106.AutoRideControlOperationSpeedBackward_576 = Data;
}
void Set_VehicleSpeedLimit_572_PGN61184_106(JNIEnv * env, jobject this,
		int Data) {
	TX_TRAVEL_CONTROL_VALUE_61184_106.VehicleSpeedLimit_572 = Data;
}
//////TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
void Set_MessageType_PGN61184_109(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.MessageType = Data;
}
void Set_BacklightIlluminationLevel_719_PGN61184_109(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.BacklightIlluminationLevel_719 =
			Data;
}
void Set_WiperSpeedLevel_718_PGN61184_109(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.WiperSpeedLevel_718 = Data;
}
void Set_Clock_819_PGN61184_109(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.Clock_819 = Data;
}
void Set_RequestEngineLowIdleSpeed_PGN61184_109(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.RequestEngineLowIdleSpeed =
			Data;
}
void Set_RequestTripDataReset_PGN61184_109(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.RequestTripDataReset = Data;
}
//////TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
void Set_MessageType_PGN61184_121(JNIEnv * env, jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.MessageType = Data;
}
void Set_AutomaticEngineShutdown_363_PGN61184_121(JNIEnv * env, jobject this,
		int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.AutomaticEngineShutdown_363 =
			Data;
}
void Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(JNIEnv * env, jobject this,
		int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.AutomaticEngineShutdownTypeControlByte =
			Data;
}
void Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(JNIEnv * env,
		jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.SettingTimeforAutomaticEngineShutdown_364 =
			Data;
}
void Set_EngineShutdownCotrolByte_PGN61184_121(JNIEnv * env,jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.EngineShutdownCotrolByte = Data;
}
void Set_DelayedEngineShutdown_365_PGN61184_121(JNIEnv * env, jobject this,
		int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.DelayedEngineShutdown_365 = Data;
}
void Set_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121(JNIEnv * env,
		jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.SettingTimeofrDelayedEngineShutdown_366 =
			Data;
}
//////TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
void Set_MessageType_PGN61184_122(JNIEnv * env, jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.MessageType = Data;
}
void Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122(JNIEnv * env,
		jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.SettingTimeforAutomaticEngineShutdown_364 =
			Data;
}
void Set_RemainingTimeforAutomaticEngineShutdown_PGN61184_122(JNIEnv * env,
		jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.RemainingTimeforAutomaticEngineShutdown =
			Data;
}
void Set_SettingTimeforDelayedEngineShutdown_366_PGN61184_122(JNIEnv * env,
		jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.SettingTimeforDelayedEngineShutdown_366 =
			Data;
}
void Set_RemainingTimeforDelayedEngineShutdown_PGN61184_122(JNIEnv * env,
		jobject this, int Data) {
	TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.RemainingTimeforDelayedEngineShutdown =
			Data;
}
//////TX_DETENT_MODE_SETTING_61184_123///////
void Set_MessageType_PGN61184_123(JNIEnv * env, jobject this, int Data) {
	TX_DETENT_MODE_SETTING_61184_123.MessageType = Data;
}
void Set_BoomDetentMode_223_PGN61184_123(JNIEnv * env, jobject this, int Data) {
	TX_DETENT_MODE_SETTING_61184_123.BoomDetentMode_223 = Data;
}
void Set_BucketDetentMode_224_PGN61184_123(JNIEnv * env, jobject this, int Data) {
	TX_DETENT_MODE_SETTING_61184_123.BucketDetentMode_224 = Data;
}
void Set_RequestDetentReleasePositionSetting_PGN61184_123(JNIEnv * env,
		jobject this, int Data) {
	TX_DETENT_MODE_SETTING_61184_123.RequestDetentReleasePositionSetting = Data;
}
//////TX_DETENT_MODE_STATUS_61184_124///////
void Set_MessageType_PGN61184_124(JNIEnv * env, jobject this, int Data) {
	TX_DETENT_MODE_STATUS_61184_124.MessageType = Data;
}
void Set_BoomDetentMode_223_PGN61184_124(JNIEnv * env, jobject this, int Data) {
	TX_DETENT_MODE_STATUS_61184_124.BoomDetentMode_223 = Data;
}
void Set_BucketDetentMode_224_PGN61184_124(JNIEnv * env, jobject this, int Data) {
	TX_DETENT_MODE_STATUS_61184_124.BucketDetentMode_224 = Data;
}
void Set_DetentReleasePositionSettingStatus_PGN61184_124(JNIEnv * env,
		jobject this, int Data) {
	TX_DETENT_MODE_STATUS_61184_124.DetentReleasePositionSettingStatus = Data;
}
//////TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
void Set_MessageType_PGN61184_129(JNIEnv * env, jobject this, int Data) {
	TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129.MessageType = Data;
}
void Set_EngineShutdownCommand_3472_PGN61184_129(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129.EngineShutdownCommand_3472 =
			Data;
}
//////TX_AS_PHONE_NUMBER_SETTING_61184_151///////
void Set_MessageType_PGN61184_151(JNIEnv * env, jobject this, int Data) {
	TX_AS_PHONE_NUMBER_SETTING_61184_151.MessageType = Data;
}
void Set_ASPhoneNumber_PGN61184_151(JNIEnv * env, jobject this, jbyteArray Data) {

	jbyte *pArr;
	int i;
	int size = sizeof(TX_AS_PHONE_NUMBER_SETTING_61184_151.ASPhoneNumber);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++){
		TX_AS_PHONE_NUMBER_SETTING_61184_151.ASPhoneNumber[i] = pArr[i];
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "ASPhoneNumber[%d]\n", TX_AS_PHONE_NUMBER_SETTING_61184_151.ASPhoneNumber[i]);
	}

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
void Set_MessageType_PGN61184_201(JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.MessageType = Data;
}
void Set_RequestBoomPressureCalibration_PGN61184_201(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestBoomPressureCalibration =
			Data;
}
void Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestBoomBucketAngleSensorCalibration =
			Data;
}
void Set_RequestAEB_PGN61184_201(JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestAEB = Data;
}
void Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.RequestBrakePedalPositionSensorCalibration =
			Data;
}
//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
void Set_MessageType_PGN61184_202(JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.MessageType = Data;
}
void Set_BoomPressureCalibrationStatus_1908_PGN61184_202(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.BoomPressureCalibrationStatus_1908 =
			Data;
}
void Set_AngleSensorCalibrationStatus_1909_PGN61184_202(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AngleSensorCalibrationStatus_1909 =
			Data;
}
void Set_AEBStatusInformation_MainCode_562_PGN61184_202(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AEBStatusInformation_MainCode_562 =
			Data;
}
void Set_AEBCycleNumber_540_PGN61184_202(JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AEBCycleNumber_540 =
			Data;
}
void Set_AEBStatusInformation_SubCode_563_PGN61184_202(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.AEBStatusInformation_SubCode_563 =
			Data;
}
void Set_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202(
		JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.BrakePedalPositionSensorCalibrationStatus_564 =
			Data;
}
void Set_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202(
		JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.BrakePedalPositionSensorCalibration_FaultInformation_565 =
			Data;
}
//////TX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
void Set_MessageType_PGN61184_203(JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.MessageType = Data;
}
void Set_BucketPriorityOperation_2301_PGN61184_203(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.BucketPriorityOperation_2301 = Data;
}
void Set_FlowFineModulationOperation_2302_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.FlowFineModulationOperation_2302 =
			Data;
}
void Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.AuxiliaryAttachmentMaxFlowLevel =
			Data;
}
void Set_BoomLeverFloatingPosition_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.BoomLeverFloatingPosition =
			Data;
}

void Set_SoftStopBoomUp_2337_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.SoftStopBoomUp_2337 = Data;
}
void Set_SoftStopBoomDown_2338_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.SoftStopBoomDown_2338 = Data;
}
void Set_SoftStopBucketIn_2339_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.SoftStopBucketIn_2339 = Data;
}
void Set_SoftStopBucketOut_2340_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.SoftStopBucketOut_2340 = Data;
}
void Set_BoomDownSpeedAdjust_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.BoomDownSpeedAdjust = Data;
}
void Set_BucketOutSpeedAdjust_PGN61184_203(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.BucketOutSpeedAdjust = Data;
}
//////TX_MONIOTR_STATUS_65327///////
void Set_RequestBuzzerStop_PGN65327(JNIEnv * env, jobject this, int Data) {
	TX_MONIOTR_STATUS_65327.RequestBuzzerStop = Data;
}
void Set_SpeedmeterUnitChange_PGN65327(JNIEnv * env, jobject this, int Data) {
	TX_MONIOTR_STATUS_65327.SpeedmeterUnitChange = Data;
}
void Set_MonitorScreenNumber_836_PGN65327(JNIEnv * env, jobject this, int Data) {
	TX_MONIOTR_STATUS_65327.MonitorScreenNumber_836 = Data;
}
//////TX_RMCU_STATUS_65329///////
void Set_RMCUNetworkType_1621_PGN65329(JNIEnv * env, jobject this, int Data) {
	TX_RMCU_STATUS_65329.RMCUNetworkType_1621 = Data;
}
void Set_RMCUBackupBatteryVoltage_1590_PGN65329(JNIEnv * env, jobject this,
		int Data) {
	TX_RMCU_STATUS_65329.RMCUBackupBatteryVoltage_1590 = Data;
}
void Set_RMCUPowerSource_1594_PGN65329(JNIEnv * env, jobject this, int Data) {
	TX_RMCU_STATUS_65329.RMCUPowerSource_1594 = Data;
}
void Set_RMCUBoxOpeningStatus_PGN65329(JNIEnv * env, jobject this, int Data) {
	TX_RMCU_STATUS_65329.RMCUBoxOpeningStatus = Data;
}
void Set_NetworkCommunicationStatus1_1622_PGN65329(JNIEnv * env, jobject this,
		int Data) {
	TX_RMCU_STATUS_65329.NetworkCommunicationStatus1_1622 = Data;
}
void Set_PositionUpdateStatus_852_PGN65329(JNIEnv * env, jobject this, int Data) {
	TX_RMCU_STATUS_65329.PositionUpdateStatus_852 = Data;
}
void Set_MachinePositionStatus_1593_PGN65329(JNIEnv * env, jobject this,
		int Data) {
	TX_RMCU_STATUS_65329.MachinePositionStatus_1593 = Data;
}
void Set_GPSAntennaConnectionAlarmStatus_1595_PGN65329(JNIEnv * env,
		jobject this, int Data) {
	TX_RMCU_STATUS_65329.GPSAntennaConnectionAlarmStatus_1595 = Data;
}
void Set_NetworkTransceiverStatus1_1623_PGN65329(JNIEnv * env, jobject this,
		int Data) {
	TX_RMCU_STATUS_65329.NetworkTransceiverStatus1_1623 = Data;
}
void Set_NetworkServiceStatus1_1624_PGN65329(JNIEnv * env, jobject this,
		int Data) {
	TX_RMCU_STATUS_65329.NetworkServiceStatus1_1624 = Data;
}
void Set_NetworkAntennaStatus1_1625_PGN65329(JNIEnv * env, jobject this,
		int Data) {
	TX_RMCU_STATUS_65329.NetworkAntennaStatus1_1625 = Data;
}
void Set_RMCUData_NumberofMessagestoTransmit_855_PGN65329(JNIEnv * env,
		jobject this, int Data) {
	TX_RMCU_STATUS_65329.RMCUData_NumberofMessagestoTransmit_855 = Data;
}
//////TX_COMPONENT_IDENTIFICATION_65330///////
void Set_ComponentCode_1699_PGN65330(JNIEnv * env, jobject this, int Data) {
	TX_COMPONENT_IDENTIFICATION_65330.ComponentCode_1699 = Data;
}
void Set_ManufacturerCode_1700_PGN65330(JNIEnv * env, jobject this, int Data) {
	TX_COMPONENT_IDENTIFICATION_65330.ManufacturerCode_1700 = Data;
}
void Set_ComponentBasicInformation_1698_PGN65330(JNIEnv * env, jobject this,
		jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_COMPONENT_IDENTIFICATION_65330.ComponentBasicInformation_1698);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_COMPONENT_IDENTIFICATION_65330.ComponentBasicInformation_1698[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_COMPONENT_IDENTIFICATION_ECM_65330///////
void Set_ComponentCode_1699_PGN65330_ECM(JNIEnv * env, jobject this, int Data) {
	TX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentCode_1699 = Data;
}
void Set_ManufacturerCode_1700_PGN65330_ECM(JNIEnv * env, jobject this,
		int Data) {
	TX_COMPONENT_IDENTIFICATION_ECM_65330.ManufacturerCode_1700 = Data;
}
void Set_ComponentBasicInformation_1698_PGN65330_ECM(JNIEnv * env, jobject this,
		jbyteArray Data) {
	Data;
	jbyte *pArr;
	int i;
	int size = sizeof(TX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentBasicInformation_1698);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_COMPONENT_IDENTIFICATION_ECM_65330.ComponentBasicInformation_1698[i] =
				pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_COMPONENT_IDENTIFICATION_TCU_65330///////
void Set_ComponentCode_1699_PGN65330_TCU(JNIEnv * env, jobject this, int Data) {
	TX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentCode_1699 = Data;
}
void Set_ManufacturerCode_1700_PGN65330_TCU(JNIEnv * env, jobject this,
		int Data) {
	TX_COMPONENT_IDENTIFICATION_TCU_65330.ManufacturerCode_1700 = Data;
}
void Set_ComponentBasicInformation_1698_PGN65330_TCU(JNIEnv * env, jobject this,
		jbyteArray Data) {

	jbyte *pArr;
	int i;
	int size = sizeof(TX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentBasicInformation_1698);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_COMPONENT_IDENTIFICATION_TCU_65330.ComponentBasicInformation_1698[i] =
				pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
void Set_ComponentCode_1699_PGN65330_MONITOR(JNIEnv * env, jobject this, int Data) {
	TX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentCode_1699 = Data;
}
void Set_ManufacturerCode_1700_PGN65330_MONITOR(JNIEnv * env, jobject this,
		int Data) {
	TX_COMPONENT_IDENTIFICATION_MONITOR_65330.ManufacturerCode_1700 = Data;
}
void Set_ComponentBasicInformation_1698_PGN65330_MONITOR(JNIEnv * env, jobject this,
		jbyteArray Data) {

	jbyte *pArr;
	int i;
	int size = sizeof(TX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentBasicInformation_1698);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentBasicInformation_1698[i] =
				pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_TRIP_TIME_INFORMATION_65344///////
void Set_TripTime_849_PGN65344(JNIEnv * env, jobject this, int Data) {
	TX_TRIP_TIME_INFORMATION_65344.TripTime_849 = Data;
}
//////TX_MACHINE_SECURITY_STATUS_65348///////
void Set_ESLMode_820_PGN65348(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.ESLMode_820 = Data;
}
void Set_LockMode_822_PGN65348(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.LockMode_822 = Data;
}
void Set_LockLevel_823_PGN65348(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.LockLevel_823 = Data;
}
void Set_LockDemander_824_PGN65348(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.LockDemander_824 = Data;
}
void Set_EngineStartingPermission_821_PGN65348(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.EngineStartingPermission_821 = Data;
}
void Set_PasswordIdentificationBlockedState_1631_PGN65348(JNIEnv * env,
		jobject this, int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.PasswordIdentificationBlockedState_1631 =
			Data;
}
void Set_ESLInterval_825_PGN65348(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_SECURITY_STATUS_65348.ESLInterval_825 = Data;
}
//////TX_MACHINE_MODE_STATUS_65350///////
void Set_EngineAlternateLowIdelSwitch_348_PGN65350(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_MODE_STATUS_65350.EngineAlternateLowIdelSwitch_348 = Data;
}
void Set_EnginePowerMode_347_PGN65350(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_MODE_STATUS_65350.EnginePowerMode_347 = Data;
}
//////TX_HYDRAULIC_PRESSURE4_65354///////
void Set_BrakeOilPressure_503_PGN65354(JNIEnv * env, jobject this, int Data) {
	TX_HYDRAULIC_PRESSURE4_65354.BrakeOilPressure_503 = Data;
}
void Set_BrakeOilChargingPriorityPressure_557_PGN65354(JNIEnv * env,
		jobject this, int Data) {
	TX_HYDRAULIC_PRESSURE4_65354.BrakeOilChargingPriorityPressure_557 = Data;
}
//////TX_HYDRAULIC_PRESSURE6_65356///////
void Set_BoomCylinderHeadPressure_204_PGN65356(JNIEnv * env, jobject this,
		int Data) {
	TX_HYDRAULIC_PRESSURE6_65356.BoomCylinderHeadPressure_204 = Data;
}
void Set_BoomCylinderRodPressure_205_PGN65356(JNIEnv * env, jobject this,
		int Data) {
	TX_HYDRAULIC_PRESSURE6_65356.BoomCylinderRodPressure_205 = Data;
}
//////TX_HYDRAULIC_PRESSURE7_65357///////
void Set_SteeringMainPumpPressure_202_PGN65357(JNIEnv * env, jobject this,
		int Data) {
	TX_HYDRAULIC_PRESSURE7_65357.SteeringMainPumpPressure_202 = Data;
}
void Set_EmergencySteeringPumpPressure_203_PGN65357(JNIEnv * env, jobject this,
		int Data) {
	TX_HYDRAULIC_PRESSURE7_65357.EmergencySteeringPumpPressure_203 = Data;
}
void Set_ParkingOilPressure_507_PGN65357(JNIEnv * env, jobject this, int Data) {
	TX_HYDRAULIC_PRESSURE7_65357.ParkingOilPressure_507 = Data;
}
void Set_DifferentialLockPressure_558_PGN65357(JNIEnv * env, jobject this,
		int Data) {
	TX_HYDRAULIC_PRESSURE7_65357.DifferentialLockPressure_558 = Data;
}
//////TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
void Set_AlternatorVoltage_707_PGN65360(JNIEnv * env, jobject this, int Data) {
	TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360.AlternatorVoltage_707 = Data;
}
//////TX_RELAY_BUZZER_STATUS_65364///////
void Set_AntiRestartRelay_327_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.AntiRestartRelay_327 = Data;
}
void Set_FuelWarmerRelay_325_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.FuelWarmerRelay_325 = Data;
}
void Set_EnginePreheatRelay_322_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.EnginePreheatRelay_322 = Data;
}
void Set_EngineStopRelay_345_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.EngineStopRelay_345 = Data;
}
void Set_WorkingCutoffRelay_164_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.WorkingCutoffRelay_164 = Data;
}
void Set_TravelingCutoffRelay_517_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.TravelingCutoffRelay_517 = Data;
}
void Set_ParkingRelay_514_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.ParkingRelay_514 = Data;
}
void Set_WiperRelay_727_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.WiperRelay_727 = Data;
}
void Set_EmergencySteeringPumpRelay_187_PGN65364(JNIEnv * env, jobject this,
		int Data) {
	TX_RELAY_BUZZER_STATUS_65364.EmergencySteeringPumpRelay_187 = Data;
}
void Set_TravelAlarmBuzzer_722_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.TravelAlarmBuzzer_722 = Data;
}
void Set_Buzzer_723_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.Buzzer_723 = Data;
}
void Set_FuelCutoffRelay_324_PGN65364(JNIEnv * env, jobject this, int Data) {
	TX_RELAY_BUZZER_STATUS_65364.FuelCutoffRelay_324 = Data;
}
//////TX_SOLENOID_STATUS_65365///////
void Set_BoomUpLeverDetentSolenoid_172_PGN65365(JNIEnv * env, jobject this,
		int Data) {
	TX_SOLENOID_STATUS_65365.BoomUpLeverDetentSolenoid_172 = Data;
}
void Set_BoomDownLeverDetentSolenoid_173_PGN65365(JNIEnv * env, jobject this,
		int Data) {
	TX_SOLENOID_STATUS_65365.BoomDownLeverDetentSolenoid_173 = Data;
}
void Set_BucketLeverDetentSolenoid_174_PGN65365(JNIEnv * env, jobject this,
		int Data) {
	TX_SOLENOID_STATUS_65365.BucketLeverDetentSolenoid_174 = Data;
}
//////TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
void Set_AcceleratorPedalPositionVoltage1_710_PGN65368(JNIEnv * env,
		jobject this, int Data) {
	TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.AcceleratorPedalPositionVoltage1_710 =
			Data;
}
void Set_BrakePedalPositionVoltage_573_PGN65368(JNIEnv * env, jobject this,
		int Data) {
	TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.BrakePedalPositionVoltage_573 =
			Data;
}
void Set_AcceleratorPedalPosition1_339_PGN65368(JNIEnv * env, jobject this,
		int Data) {
	TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.AcceleratorPedalPosition1_339 =
			Data;
}
void Set_AcceleratorPedalPositionVoltage2_710_PGN65368(JNIEnv * env,
		jobject this, int Data) {
	TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368.AcceleratorPedalPositionVoltage2_710 =
			Data;
}
//////TX_COOLING_FAN_STATUS_65369///////
void Set_CoolingFanReverseMode_182_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanReverseMode_182 = Data;
}
void Set_CoolingFanReverseSolenoid_181_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanReverseSolenoid_181 = Data;
}
void Set_CoolingFanReverseSwitchManual_740_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanReverseSwitchManual_740 = Data;
}
void Set_CoolingFanReverseSwitchAuto_741_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanReverseSwitchAuto_741 = Data;
}
void Set_CoolingFanValveCurrent_146_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanValveCurrent_146 = Data;
}
void Set_CoolingFanSpeed_318_PGN65369(JNIEnv * env, jobject this, int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanSpeed_318 = Data;
}
void Set_CoolingFanReverseIntervalTime_211_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanReverseIntervalTime_211 = Data;
}
void Set_CoolingFanReverseOperatingTime_212_PGN65369(JNIEnv * env, jobject this,
		int Data) {
	TX_COOLING_FAN_STATUS_65369.CoolingFanReverseOperatingTime_212 = Data;
}
//////TX_ENGINE_STATUS1_65371///////
void Set_EngineOperatingCondition_336_PGN65371(JNIEnv * env, jobject this,
		int Data) {
	TX_ENGINE_STATUS1_65371.EngineOperatingCondition_336 = Data;
}
void Set_DEFTankLevel_362_PGN65371(JNIEnv * env, jobject this, int Data) {
	TX_ENGINE_STATUS1_65371.DEFTankLevel_362 = Data;
}
//////TX_VEHICLE_DISTANCE_65389///////
void Set_TripDistance_600_PGN65389(JNIEnv * env, jobject this, int Data) {
	TX_VEHICLE_DISTANCE_65389.TripDistance_600 = Data;
}
void Set_TotalVehicleDistance_601_PGN65389(JNIEnv * env, jobject this, int Data) {
	TX_VEHICLE_DISTANCE_65389.TotalVehicleDistance_601 = Data;
}
//////TX_CYLINDER_ANGLE_STROKE1_65395///////
void Set_BoomLinkAngle_1920_PGN65395(JNIEnv * env, jobject this, int Data) {
	TX_CYLINDER_ANGLE_STROKE1_65395.BoomLinkAngle_1920 = Data;
}
void Set_BellCrankAngle_1921_PGN65395(JNIEnv * env, jobject this, int Data) {
	TX_CYLINDER_ANGLE_STROKE1_65395.BellCrankAngle_1921 = Data;
}
void Set_BoomLinkAngleSensorSignalVoltage_728_PGN65395(JNIEnv * env,
		jobject this, int Data) {
	TX_CYLINDER_ANGLE_STROKE1_65395.BoomLinkAngleSensorSignalVoltage_728 = Data;
}
void Set_BellCrankAngleSensorSignalVoltage_729_PGN65395(JNIEnv * env,
		jobject this, int Data) {
	TX_CYLINDER_ANGLE_STROKE1_65395.BellCrankAngleSensorSignalVoltage_729 =
			Data;
}
void Set_BucketCylinderStroke_1930_PGN65395(JNIEnv * env, jobject this,
		int Data) {
	TX_CYLINDER_ANGLE_STROKE1_65395.BucketCylinderStroke_1930 = Data;
}
//////TX_AS_PHONE_NUMBER_65425///////
void Set_ASPhoneNumber_1095_PGN65425(JNIEnv * env, jobject this,
		jbyteArray Data) {

	jbyte *pArr;
	int i;
	int size = sizeof(TX_AS_PHONE_NUMBER_65425.ASPhoneNumber_1095);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_AS_PHONE_NUMBER_65425.ASPhoneNumber_1095[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_WARNING_LAMP_65427///////
void Set_FuelLevelLow_303_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.FuelLevelLow_303 = Data;
}
void Set_HydraulicOilTemperatureHigh_102_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.HydraulicOilTemperatureHigh_102 = Data;
}
void Set_BatteryVoltageLow_706_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.BatteryVoltageLow_706 = Data;
}
void Set_Overload_104_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.Overload_104 = Data;
}
void Set_AirCleanerClog_317_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.AirCleanerClog_317 = Data;
}
void Set_EngineCheckLamp_320_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.EngineCheckLamp_320 = Data;
}
void Set_TransmissionOilPressureLow_502_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.TransmissionOilPressureLow_502 = Data;
}
void Set_BrakeOilPressureLow_504_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.BrakeOilPressureLow_504 = Data;
}
void Set_EngineCoolantTemperatureHigh_305_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.EngineCoolantTemperatureHigh_305 = Data;
}
void Set_EngineCoolantLevelLow_307_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.EngineCoolantLevelLow_307 = Data;
}
void Set_EngineOilPressureLow_313_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.EngineOilPressureLow_313 = Data;
}
void Set_EngineOilFilterClog_315_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.EngineOilFilterClog_315 = Data;
}
void Set_TransmissionOilTemperatureHigh_537_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.TransmissionOilTemperatureHigh_537 = Data;
}
void Set_TransmissionCheck_538_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.TransmissionCheck_538 = Data;
}
void Set_SteeringMainPumpPressureLow_184_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.SteeringMainPumpPressureLow_184 = Data;
}
void Set_EmergencySteeringActive_185_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.EmergencySteeringActive_185 = Data;
}
void Set_WarningSymbolLamp_709_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.WarningSymbolLamp_709 = Data;
}
void Set_WaterInFuelIndicator_360_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.WaterInFuelIndicator_360 = Data;
}
void Set_DTCAlarmLamp_1509_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.DTCAlarmLamp_1509 = Data;
}
void Set_EngineStopLamp_319_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.EngineStopLamp_319 = Data;
}
void Set_DPFLampCommand_352_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.DPFLampCommand_352 = Data;
}
void Set_DEFLowLamp_358_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.DEFLowLamp_358 = Data;
}
void Set_ClutchSlip_569_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.ClutchSlip_569 = Data;
}
void Set_BrakeOilLevelLow_566_PGN65427(JNIEnv * env, jobject this, int Data) {
	TX_WARNING_LAMP_65427.BrakeOilLevelLow_566 = Data;
}
void Set_BrakeOilTemperatureHigh_567_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.BrakeOilTemperatureHigh_567 = Data;
}
void Set_EmissionSystemFailLamp_357_PGN65427(JNIEnv * env, jobject this,
		int Data) {
	TX_WARNING_LAMP_65427.EmissionSystemFailLamp_357 = Data;
}
//////TX_INDICATOR_LAMP_65428///////
void Set_PowerMaxStatus_802_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.PowerMaxStatus_802 = Data;
}
void Set_DecelerationStatus_803_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.DecelerationStatus_803 = Data;
}
void Set_WarmingUpStatus_804_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.WarmingUpStatus_804 = Data;
}
void Set_EnginePreheatStatus_323_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.EnginePreheatStatus_323 = Data;
}
void Set_FuelWarmerActiveStatus_326_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.FuelWarmerActiveStatus_326 = Data;
}
void Set_CoolingFandrivingStatus_180_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.CoolingFandrivingStatus_180 = Data;
}
void Set_CruiseStatus_519_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.CruiseStatus_519 = Data;
}
void Set_ParkingStatus_508_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.ParkingStatus_508 = Data;
}
void Set_ClutchCutoffStatus_545_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.ClutchCutoffStatus_545 = Data;
}
void Set_FNRJoystickActiveStatus_546_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.FNRJoystickActiveStatus_546 = Data;
}
void Set_RideControlStatus_550_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.RideControlStatus_550 = Data;
}
void Set_DifferentialLockStatus_559_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.DifferentialLockStatus_559 = Data;
}
void Set_JoystickSteeringActiveStatus_186_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.JoystickSteeringActiveStatus_186 = Data;
}
void Set_InchingStatus_549_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.InchingStatus_549 = Data;
}
void Set_RamLockStatus_520_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.RamLockStatus_520 = Data;
}
void Set_WorkingBrakeStatus_506_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.WorkingBrakeStatus_506 = Data;
}
void Set_MaintenanceAlarmLamp_1099_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.MaintenanceAlarmLamp_1099 = Data;
}
void Set_OperatorAbsenceStatus_832_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.OperatorAbsenceStatus_832 = Data;
}
void Set_MirrorHeaterStatus_724_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.MirrorHeaterStatus_724 = Data;
}
void Set_HighBeamStatus_725_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.HighBeamStatus_725 = Data;
}
void Set_TravelAlarmSwitch_721_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.TravelAlarmSwitch_721 = Data;
}
void Set_SwingBoomSwitch_726_PGN65428(JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.SwingBoomSwitch_726 = Data;
}
void Set_ParkingWorkingTravelStatus_510_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.ParkingWorkingTravelStatus_510 = Data;
}
void Set_TransmissionTCLockupEngaged_556_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.TransmissionTCLockupEngaged_556 = Data;
}
void Set_ExhaustSystemHighTemperatureLampCommand_353_PGN65428(JNIEnv * env,
		jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.ExhaustSystemHighTemperatureLampCommand_353 = Data;
}
void Set_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428(
		JNIEnv * env, jobject this, int Data) {
	TX_INDICATOR_LAMP_65428.DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354 =
			Data;
}
void Set_AutomaticEngineShutdown_363_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.AutomaticEngineShutdown_363 = Data;
}
void Set_DelayedEngineShutdown_365_PGN65428(JNIEnv * env, jobject this,
		int Data) {
	TX_INDICATOR_LAMP_65428.DelayedEngineShutdown_365 = Data;
}
//////TX_GAUGE_65431///////
void Set_FuelLevel_301_PGN65431(JNIEnv * env, jobject this, int Data) {
	TX_GAUGE_65431.FuelLevel_301 = Data;
}
void Set_EngineCoolantTemperature_304_PGN65431(JNIEnv * env, jobject this,
		int Data) {
	TX_GAUGE_65431.EngineCoolantTemperature_304 = Data;
}
void Set_HydraulicOilTemperature_101_PGN65431(JNIEnv * env, jobject this,
		int Data) {
	TX_GAUGE_65431.HydraulicOilTemperature_101 = Data;
}
void Set_TransmissionOilTemperature_536_PGN65431(JNIEnv * env, jobject this,
		int Data) {
	TX_GAUGE_65431.TransmissionOilTemperature_536 = Data;
}
void Set_EngineSpeed_310_PGN65431(JNIEnv * env, jobject this, int Data) {
	TX_GAUGE_65431.EngineSpeed_310 = Data;
}
void Set_BatteryVoltage_705_PGN65431(JNIEnv * env, jobject this, int Data) {
	TX_GAUGE_65431.BatteryVoltage_705 = Data;
}
//////TX_HOURMETER_CLOCK_WIPER_65433///////
void Set_Hourmeter_1601_PGN65433(JNIEnv * env, jobject this, int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.Hourmeter_1601 = Data;
}
void Set_Clock_819_PGN65433(JNIEnv * env, jobject this, int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.Clock_819 = Data;
}
void Set_HourmeterActiveStatus_703_PGN65433(JNIEnv * env, jobject this,
		int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.HourmeterActiveStatus_703 = Data;
}
void Set_WiperOperationStatus_717_PGN65433(JNIEnv * env, jobject this, int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.WiperOperationStatus_717 = Data;
}
void Set_WiperSpeedLevel_718_PGN65433(JNIEnv * env, jobject this, int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.WiperSpeedLevel_718 = Data;
}
void Set_BacklightIlluminationLevel_719_PGN65433(JNIEnv * env, jobject this,
		int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.BacklightIlluminationLevel_719 = Data;
}
void Set_IlluminationStatus_737_PGN65433(JNIEnv * env, jobject this, int Data) {
	TX_HOURMETER_CLOCK_WIPER_65433.IlluminationStatus_737 = Data;
}
//////TX_MACHINE_TRAVEL_STATUS_65434///////
void Set_WheelBasedVehicleSpeed_532_PGN65434(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.WheelBasedVehicleSpeed_532 = Data;
}
void Set_SelectGear_541_PGN65434(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.SelectGear_541 = Data;
}
void Set_ActualGear_542_PGN65434(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.ActualGear_542 = Data;
}
void Set_TransmissionShiftMode_543_PGN65434(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.TransmissionShiftMode_543 = Data;
}
void Set_ClutchCutoffMode_544_PGN65434(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.ClutchCutoffMode_544 = Data;
}
void Set_KickDownShiftMode_547_PGN65434(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.KickDownShiftMode_547 = Data;
}
void Set_TransmissionTCLockupEngaged_568_PGN65434(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.TransmissionTCLockupEngaged_568 = Data;
}
void Set_DifferentialLockMode_570_PGN65434(JNIEnv * env, jobject this, int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.DifferentialLockMode_570 = Data;
}
void Set_VehicleSpeedLimitMode_575_PGN65434(JNIEnv * env, jobject this,
		int Data) {
	TX_MACHINE_TRAVEL_STATUS_65434.VehicleSpeedLimitMode_575 = Data;
}
//////TX_DTC_INFORMATION_TYPE1_65438///////
void Set_DTCType_1510_PGN65438(JNIEnv * env, jobject this, int Data) {
	TX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 = Data;
}
void Set_NumberofDTCinThisPacket_1514_PGN65438(JNIEnv * env, jobject this,
		int Data) {
	TX_DTC_INFORMATION_TYPE1_65438.NumberofDTCinThisPacket_1514 = Data;
}
void Set_TotalNumberofDTC_PGN65438(JNIEnv * env, jobject this, int Data) {
	TX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC = Data;
}
void Set_TotalNumberofDTCInformationPacket_1512_PGN65438(JNIEnv * env,
		jobject this, int Data) {
	TX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512 =
			Data;
}
void Set_SequenceNumberofDTCInformationPacket_1513_PGN65438(JNIEnv * env,
		jobject this, int Data) {
	TX_DTC_INFORMATION_TYPE1_65438.SequenceNumberofDTCInformationPacket_1513 =
			Data;
}
void Set_DTC_1_PGN65438(JNIEnv * env, jobject this, jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_DTC_INFORMATION_TYPE1_65438.DTC_1);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_DTC_INFORMATION_TYPE1_65438.DTC_1[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
void Set_DTC_2_PGN65438(JNIEnv * env, jobject this, jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_DTC_INFORMATION_TYPE1_65438.DTC_2);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_DTC_INFORMATION_TYPE1_65438.DTC_2[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
void Set_DTC_3_PGN65438(JNIEnv * env, jobject this, jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_DTC_INFORMATION_TYPE1_65438.DTC_3);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_DTC_INFORMATION_TYPE1_65438.DTC_3[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
void Set_DTC_4_PGN65438(JNIEnv * env, jobject this, jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_DTC_INFORMATION_TYPE1_65438.DTC_4);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_DTC_INFORMATION_TYPE1_65438.DTC_4[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
void Set_DTC_5_PGN65438(JNIEnv * env, jobject this, jbyteArray Data) {
	jbyte *pArr;
	int i;
	int size = sizeof(TX_DTC_INFORMATION_TYPE1_65438.DTC_5);

	pArr = (*env)->GetByteArrayElements(env, Data, NULL);

	for (i = 0; i < size; i++)
		TX_DTC_INFORMATION_TYPE1_65438.DTC_5[i] = pArr[i];

	(*env)->ReleaseByteArrayElements(env, Data, pArr, 0);
}
//////TX_WEIGHING_SYSTEM_STATUS_65450///////
void Set_WeighingSystemAccumulationMode_1941_PGN65450(JNIEnv * env,
		jobject this, int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemAccumulationMode_1941 =
			Data;
}
void Set_WeighingSystemBuzzer_1907_PGN65450(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemBuzzer_1907 = Data;
}
void Set_CurrentWeighingResult_1919_PGN65450(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.CurrentWeighingResult_1919 = Data;
}
void Set_WeighingDisplayMode1_1910_PGN65450(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.WeighingDisplayMode1_1910 = Data;
}
// ++, 150212 bwk
void Set_WeighingSystemError_BoomLiftSpeed_1942_PGN65450(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemError_BoomLiftSpeed_1942 = Data;
}
void Set_WeighingSystemError_BucketFullIn_1943_PGN65450(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemError_BucketFullIn_1943 = Data;
}
void Set_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450(JNIEnv * env, jobject this,
		int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemError_HydraulicOilTemperature_1944 = Data;
}
// --, 150212 bwk
void Set_CurrentWeight_1911_PGN65450(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.CurrentWeight_1911 = Data;
}
void Set_TodayWeight_1915_PGN65450(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_STATUS_65450.TodayWeight_1915 = Data;
}
//////TX_WEIGHING_SYSTEM_DATA1_65451///////
void Set_TotalWorkAWeight_1912_PGN65451(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_DATA1_65451.TotalWorkAWeight_1912 = Data;
}
void Set_TotalWorkBWeight_1913_PGN65451(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_DATA1_65451.TotalWorkBWeight_1913 = Data;
}
//////TX_WEIGHING_SYSTEM_DATA2_65452///////
void Set_TotalWorkCWeight_1914_PGN65452(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_DATA2_65452.TotalWorkCWeight_1914 = Data;
}
void Set_ADayBeforeWeight_1916_PGN65452(JNIEnv * env, jobject this, int Data) {
	TX_WEIGHING_SYSTEM_DATA2_65452.ADayBeforeWeight_1916 = Data;
}
//////TX_JOYSTICK_POSITION_STATUS_65515///////
void Set_BoomJoystickPositionStatus_2310_PGN65515(JNIEnv * env, jobject this,
		int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.BoomJoystickPositionStatus_2310 = Data;
}
void Set_BoomJoystickPosition_2311_PGN65515(JNIEnv * env, jobject this,
		int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.BoomJoystickPosition_2311 = Data;
}
void Set_BucketJoystickPositionStatus_2312_PGN65515(JNIEnv * env, jobject this,
		int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.BucketJoystickPositionStatus_2312 = Data;
}
void Set_BucketJoystickPosition_2313_PGN65515(JNIEnv * env, jobject this,
		int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.BucketJoystickPosition_2313 = Data;
}
void Set_AuxJoystickPositionStatus_2314_PGN65515(JNIEnv * env, jobject this,
		int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.AuxJoystickPositionStatus_2314 = Data;
}
void Set_AuxJoystickPosition_2315_PGN65515(JNIEnv * env, jobject this, int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.AuxJoystickPosition_2315 = Data;
}
void Set_BoomLeverFloatingPosition_2336_PGN65515(JNIEnv * env, jobject this, int Data) {
	TX_JOYSTICK_POSITION_STATUS_65515.BoomLeverFloatingPosition_2336 = Data;
}
//////TX_WHEEL_LOADER_EHCU_STATUS_65517///////
void Set_BucketPriorityOperation_2301_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.BucketPriorityOperation_2301 = Data;
}
void Set_FlowFineModulationOperation_2302_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.FlowFineModulationOperation_2302 = Data;
}
void Set_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.AuxiliaryAttachmentMaxFlowLevel_2303 =
			Data;
}
void Set_AttachmentPilotCutoffStatus_225_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.AttachmentPilotCutoffStatus_225 = Data;
}
void Set_FloatMode_2316_PGN65517(JNIEnv * env, jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.FloatMode_2316 = Data;
}
void Set_JoystickSteeringActiveStatusEHCU_186_PGN65517(JNIEnv * env,
		jobject this, int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.JoystickSteeringActiveStatusEHCU_186 =
			Data;
}
void Set_BoomUpEPPRValveCurrent_2304_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.BoomUpEPPRValveCurrent_2304 = Data;
}
void Set_BoomDownEPPRValveCurrent_2305_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.BoomDownEPPRValveCurrent_2305 = Data;
}
void Set_BucketInEPPRValveCurrent_2306_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.BucketInEPPRValveCurrent_2306 = Data;
}
void Set_BucketOutEPPRValveCurrent_2307_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.BucketOutEPPRValveCurrent_2307 = Data;
}
void Set_AUX1EPPRValveCurrent_2308_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.AUX1EPPRValveCurrent_2308 = Data;
}
void Set_AUX2EPPRValveCurrent_2309_PGN65517(JNIEnv * env, jobject this,
		int Data) {
	TX_WHEEL_LOADER_EHCU_STATUS_65517.AUX2EPPRValveCurrent_2309 = Data;
}
//////TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
void Set_TravelAlarmOperationStatus_3431_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.TravelAlarmOperationStatus_3431 =
			Data;
}
void Set_WasherOperationStatus_3432_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.WasherOperationStatus_3432 =
			Data;
}
void Set_wiperoperationstatus_3433_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.wiperoperationstatus_3433 =
			Data;
}
void Set_IntWiperOperationStatus_3434_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IntWiperOperationStatus_3434 =
			Data;
}
void Set_WorkLampOperationStatus_3435_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.WorkLampOperationStatus_3435 =
			Data;
}
void Set_HeadLampOperationStatus_3436_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.HeadLampOperationStatus_3436 =
			Data;
}
void Set_CabinLampOperationStatus_3437_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.CabinLampOperationStatus_3437 =
			Data;
}
void Set_IlluminationOperationStatus_3438_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IlluminationOperationStatus_3438 =
			Data;
}
void Set_OutriggerFrontRHOperationStatus_3439_PGN65527(JNIEnv * env,
		jobject this, int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerFrontRHOperationStatus_3439 =
			Data;
}
void Set_OutriggerFrontLHOperationStatus_3440_PGN65527(JNIEnv * env,
		jobject this, int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerFrontLHOperationStatus_3440 =
			Data;
}
void Set_OutriggerRearRHOperationStatus_3441_PGN65527(JNIEnv * env,
		jobject this, int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerRearRHOperationStatus_3441 =
			Data;
}
void Set_OutriggerRearLHOperationStatus_3442_PGN65527(JNIEnv * env,
		jobject this, int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OutriggerRearLHOperationStatus_3442 =
			Data;
}
void Set_OverloadSwitchOperationStatus_3443_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.OverloadSwitchOperationStatus_3443 =
			Data;
}
void Set_BeaconLampOperationStatus_3444_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.BeaconLampOperationStatus_3444 =
			Data;
}
void Set_ForwardTravelAlarmOperationStatus_3445_PGN65527(JNIEnv * env,
		jobject this, int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.ForwardTravelAlarmOperationStatus_3445 =
			Data;
}
void Set_RearWorkLampOperationStatus_3446_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWorkLampOperationStatus_3446 =
			Data;
}
void Set_RideControlOperationStatus_3447_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RideControlOperationStatus_3447 =
			Data;
}
void Set_QuickCouplerOperationStatus_3448_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.QuickCouplerOperationStatus_3448 =
			Data;
}
void Set_AutoGreaseOperationStatus_3449_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.AutoGreaseOperationStatus_3449 =
			Data;
}
void Set_MirrorHeatOperationStatus_3450_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.MirrorHeatOperationStatus_3450 =
			Data;
}
void Set_RearWiperOperationStatus_3451_PGN65527(JNIEnv * env, jobject this,
		int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperOperationStatus_3451 =
			Data;
}
void Set_RearWiperWasherOperationStatus_3452_PGN65527(JNIEnv * env,
		jobject this, int Data) {
	TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperWasherOperationStatus_3452 =
			Data;
}
///////////////////////////////////////////////////////////////////////////////////////////////////////
jint Get_FirmwareVersionHigh(JNIEnv * env, jobject this) {
	return RX_RES_Version.SWVersionHigh;
}
jint Get_FirmwareVersionLow(JNIEnv * env, jobject this) {
	return RX_RES_Version.SWVersionLow;
}
jint Get_FirmwareVersionSubHigh(JNIEnv * env, jobject this) {
	return RX_RES_Version.SWVersionSubHigh;
}
jint Get_FirmwareVersionSubLow(JNIEnv * env, jobject this) {
	return RX_RES_Version.SWVersionSubLow;
}
jint Get_HWVersion(JNIEnv * env, jobject this) {
	return RX_RES_Version.HWVersion;
}
jint Get_CommErrCnt(JNIEnv * env, jobject this) {
	return CommErrCnt;
}
jint Get_CheckBKCUComm(JNIEnv * env, jobject this) {
	return CheckBKCUComm;
}

void Set_CommErrCnt(JNIEnv * env, jobject this, int Data) {
	CommErrCnt = Data;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////


jintArray _Get_TcuErr(JNIEnv *env, jobject this) {
	jintArray TCU_Err = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, TCU_Err, 0, ERR_BUF_SIZE, gErr_Tcu);

	return TCU_Err;
}

jintArray _Get_EcuErr(JNIEnv *env, jobject this) {
	jintArray ECU_Err = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, ECU_Err, 0, ERR_BUF_SIZE, gErr_Ecu);

	return ECU_Err;
}

jintArray _Get_McuErr(JNIEnv *env, jobject this) {
	jintArray MCU_Err = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, MCU_Err, 0, ERR_BUF_SIZE, gErr_Mcu);

	return MCU_Err;
}

jintArray _Get_TcuErrLogged(JNIEnv *env, jobject this) {
	jintArray TCU_Err_Logged = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, TCU_Err_Logged, 0, ERR_BUF_SIZE, gErr_Tcu_Logged);

	return TCU_Err_Logged;
}

jintArray _Get_EcuErrLogged(JNIEnv *env, jobject this) {
	jintArray ECU_Err_Logged = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, ECU_Err_Logged, 0, ERR_BUF_SIZE, gErr_Ecu_Logged);

	return ECU_Err_Logged;
}

jintArray _Get_EHCUErrLogged(JNIEnv *env, jobject this) {
	jintArray EHCU_Err_Logged = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, EHCU_Err_Logged, 0, ERR_BUF_SIZE, gErr_EHCU_Logged);

	return EHCU_Err_Logged;
}

jintArray _Get_McuErrLogged(JNIEnv *env, jobject this) {
	jintArray MCU_Err_Logged = (*env)->NewIntArray(env, ERR_BUF_SIZE);

	(*env)->SetIntArrayRegion(env, MCU_Err_Logged, 0, ERR_BUF_SIZE, gErr_Mcu_Logged);

	return MCU_Err_Logged;
}

jint _Get_EHCUSingleOrMulti(JNIEnv *env, jobject this) {
	return ehcu_s_or_m;
}
jint _Get_EHCUTotalError(JNIEnv *env, jobject this) {
	return (nEHCUTotalError - 2) / 4;
}
jint _Get_EHCUTotalPacket(JNIEnv *env, jobject this) {
	int temp;
	temp = _Get_EHCUTotalError(env, this);
	#if 0
	if (temp <= 5)
		return 1;
	else if (temp <= 10)
		return 2;
	else if (temp <= 14)
		return 3;
	else
		return 1;
	#else
	return temp/4;
	#endif
}

jint _Get_EHCUSingleErrorData(JNIEnv *env, jobject this) {
	return rx_error_ehcu_s.DTC;
}

jint _Get_EHCUErrorData(JNIEnv *env, jobject this, int nErrNum) {
	return rx_error_ehcu_m.DTC_Error[nErrNum];
}
jint _Get_RecvESL_Flag(JNIEnv *env, jobject this) {
	return gRecvESL;
}
jint _Get_RecvSMK_Flag(JNIEnv *env, jobject this) {
	int result;
	result = gRecvSMK;
	gRecvSMK = 0;
	return result;
}
jint _Get_SmkAuthResult(JNIEnv *env, jobject this) {
	return rx_smk_result.SMK_Auth_Result;
}
jint _Get_SmkMsgResult(JNIEnv *env, jobject this) {
	return rx_smk_result.SMK_Msg_Result;
}
jint _Get_SmkRegTagCount(JNIEnv *env, jobject this) {
	return rx_smk_result.SMK_Tag_Reg_Count;
}
jint Get_RTColock_Year(JNIEnv *env, jobject this) {
	return RX_RES_RTC.Year;
}
jint Get_RTColock_Month(JNIEnv *env, jobject this) {
	return RX_RES_RTC.Month;
}
jint Get_RTColock_Date(JNIEnv *env, jobject this) {
	return RX_RES_RTC.Date;
}
jint Get_RTColock_Sec(JNIEnv *env, jobject this) {
	return RX_RES_RTC.Sec;
}
jint _Get_RTColock_Hour(JNIEnv *env, jobject this) {
	return RX_RES_RTC.Hour;
}
jint _Get_RTColock_Min(JNIEnv *env, jobject this) {
	return RX_RES_RTC.Min;
}
jint _GET_Buzzer(JNIEnv *env, jobject this) {
	return rx_CMD_Data.Buzzer_Data;
}

jint GET_RecvResDownFlag(JNIEnv *env, jobject this) {
	int result;
	result = nRecvResDownFlag;
	nRecvResDownFlag = 0;
	return result;
}

jint GET_RX_RES_DOWN_UpdateResponse(JNIEnv *env, jobject this) {
	int result;
	result = RX_RES_DOWN.UpdateResponse;
	RX_RES_DOWN.UpdateResponse = 0x00;
	return result;
}
jint Get_RX_RES_KEY_LongFlag(JNIEnv *env, jobject this) {

	return RX_RES_KEY.LongFlag;
}
jint Get_RX_RES_KEY_Menu(JNIEnv *env, jobject this) {

	return RX_RES_KEY.Menu;
}
jint Get_RX_RES_KEY_ESC(JNIEnv *env, jobject this) {

	return RX_RES_KEY.ESC;
}
jint Get_RX_RES_KEY_Left(JNIEnv *env, jobject this) {

	return RX_RES_KEY.Left;
}
jint Get_RX_RES_KEY_Right(JNIEnv *env, jobject this) {

	return RX_RES_KEY.Right;
}

jint Get_RX_RES_KEY_Enter(JNIEnv *env, jobject this) {

	return RX_RES_KEY.Enter;
}
jint Get_RX_RES_KEY_CAM(JNIEnv *env, jobject this) {

	return RX_RES_KEY.CAM;
}
jint Get_RX_RES_KEY_MainLight(JNIEnv *env, jobject this) {

	return RX_RES_KEY.MainLight;
}
jint Get_RX_RES_KEY_WorkLight(JNIEnv *env, jobject this) {

	return RX_RES_KEY.WorkLight;
}
jint Get_RX_RES_KEY_AutoGrease(JNIEnv *env, jobject this) {

	return RX_RES_KEY.AutoGrease;
}
jint Get_RX_RES_KEY_QuickCouupler(JNIEnv *env, jobject this) {

	return RX_RES_KEY.QuickCouupler;
}
jint Get_RX_RES_KEY_RideControl(JNIEnv *env, jobject this) {

	return RX_RES_KEY.RideControl;
}
jint Get_RX_RES_KEY_WorkLoad(JNIEnv *env, jobject this) {

	return RX_RES_KEY.WorkLoad;
}
jint Get_RX_RES_KEY_BeaconLamp(JNIEnv *env, jobject this) {

	return RX_RES_KEY.BeaconLamp;
}
jint Get_RX_RES_KEY_RearWiper(JNIEnv *env, jobject this) {

	return RX_RES_KEY.RearWiper;
}
jint Get_RX_RES_KEY_MirrorHeat(JNIEnv *env, jobject this) {

	return RX_RES_KEY.MirrorHeat;
}
jint Get_RX_RES_KEY_DetentSetting(JNIEnv *env, jobject this) {

	return RX_RES_KEY.DetentSetting;
}
jint Get_RX_RES_KEY_FineModulation(JNIEnv *env, jobject this) {

	return RX_RES_KEY.FineModulation;
}
jint Get_RX_RES_KEY_FN(JNIEnv *env, jobject this) {

	return RX_RES_KEY.FN;
}
jint Get_gErr_Mcu_TotalPacket(JNIEnv *env, jobject this) {

	return gErr_Mcu_TotalPacket;
}
jint Get_gErr_Ecu_TotalPacket(JNIEnv *env, jobject this) {

	return gErr_Ecu_TotalPacket;
}
jint Get_gErr_Tcu_TotalPacket(JNIEnv *env, jobject this) {

	return gErr_Tcu_TotalPacket;
}
jint Get_gErr_Mcu_TotalPacket_Logged(JNIEnv *env, jobject this) {

	return gErr_Mcu_TotalPacket_Logged;
}
jint Get_gErr_Ecu_TotalPacket_Logged(JNIEnv *env, jobject this) {

	return gErr_Ecu_TotalPacket_Logged;
}
jint Get_gErr_Tcu_TotalPacket_Logged(JNIEnv *env, jobject this) {

	return gErr_Tcu_TotalPacket_Logged;
}
jint Get_gErr_EHCU_TotalPacket_Logged(JNIEnv *env, jobject this) {

	return gErr_EHCU_TotalPacket_Logged;
}
jint Get_gErr_Mcu_Total(JNIEnv *env, jobject this) {

	return gErr_Mcu_Total;
}
jint Get_gErr_Ecu_Total(JNIEnv *env, jobject this) {

	return gErr_Ecu_Total;
}
jint Get_gErr_Tcu_Total(JNIEnv *env, jobject this) {

	return gErr_Tcu_Total;
}
jint Get_gErr_Mcu_Total_Logged(JNIEnv *env, jobject this) {

	return gErr_Mcu_Total_Logged;
}
jint Get_gErr_Ecu_Total_Logged(JNIEnv *env, jobject this) {

	return gErr_Ecu_Total_Logged;
}

jint Get_gErr_Tcu_Total_Logged(JNIEnv *env, jobject this) {

	return gErr_Tcu_Total_Logged;
}
jint Get_gErr_EHCU_Total_Logged(JNIEnv *env, jobject this) {

	return gErr_EHCU_Total_Logged;
}


void SetFNKeypadLamp(JNIEnv * env, jobject this,
		int Data) {
	nFNLamp = Data;
	SetKeypadLamp();
}

static JNINativeMethod methods[] =
		{
				{ "Open_UART1", "(Ljava/lang/String;II)Ljava/io/FileDescriptor;",(void*) _Open_UART1 },
				{ "Close_UART1", "()V",(void*) _Close_UART1 },
				{ "Write_UART1", "([BI)I",(void*) _Write_UART1 },
				{ "UART1_TxComm", "(I)I",(void*) _UART1_TxComm },

				{ "Open_UART3", "(Ljava/lang/String;II)Ljava/io/FileDescriptor;",(void*) _Open_UART3 },
				{ "Close_UART3", "()V",(void*) _Close_UART3 },
				{ "Write_UART3", "([BI)I",(void*) _Write_UART3 },
				{ "UART3_TxComm", "(IIIIIIIII)I",(void*) _UART3_TxComm },

				{ "SetFNKeypadLamp", "(I)V",
						(void*) SetFNKeypadLamp },

				{"Get_TcuErr_FromNative", "()[I", (void*) _Get_TcuErr },
				{"Get_EcuErr_FromNative", "()[I", (void*) _Get_EcuErr },
				{ "Get_McuErr_FromNative", "()[I", (void*) _Get_McuErr },
				{"Get_TcuErr_Logged_FromNative", "()[I",(void*) _Get_TcuErrLogged },
				{"Get_EcuErr_Logged_FromNative", "()[I",(void*) _Get_EcuErrLogged },
				{"Get_EHCUErr_Logged_FromNative", "()[I",(void*) _Get_EHCUErrLogged },
				{"Get_McuErr_Logged_FromNative", "()[I",(void*) _Get_McuErrLogged },
				{"Get_EHCUSingleOrMulti_FromNative", "()I",(void*) _Get_EHCUSingleOrMulti },
				{"Get_EHCUTotalError_FromNative", "()I",(void*) _Get_EHCUTotalError },
				{"Get_EHCUTotalPacket_FromNative", "()I",(void*) _Get_EHCUTotalPacket },
				{"Get_EHCUSingleErrorData_FromNative", "()I",(void*) _Get_EHCUSingleErrorData },
				{"Get_EHCUErrorData_FromNative", "(I)I",(void*) _Get_EHCUErrorData },

				{"Get_RecvESL_Flag_FromNative", "()I",(void*) _Get_RecvESL_Flag },
				{"Get_RecvSMK_Flag_FromNative", "()I",(void*) _Get_RecvSMK_Flag },
				{"Get_SmkAuthResult_FromNative", "()I",(void*) _Get_SmkAuthResult },
				{"Get_SmkMsgResult_FromNative", "()I",(void*) _Get_SmkMsgResult },
				{"Get_SmkRegTagCount_FromNative", "()I",(void*) _Get_SmkRegTagCount },
				{"Get_RTColock_Year", "()I",(void*) Get_RTColock_Year },
				{"Get_RTColock_Month", "()I",(void*) Get_RTColock_Month },
				{"Get_RTColock_Date", "()I",(void*) Get_RTColock_Date },
				{"Get_RTColock_Sec", "()I",(void*) Get_RTColock_Sec },
				{"Get_RTColock_Hour_FromNative", "()I",(void*) _Get_RTColock_Hour },
				{"Get_RTColock_Min_FromNative", "()I",(void*) _Get_RTColock_Min },
				{"GET_Buzzer_FromNative","()I", (void*) _GET_Buzzer },










				{ "UART1_Tx", "(III)I",(void*) UART1_Tx },
				//////RX_DTC_INFORMATION_REQUEST_61184_11///////
				{ "Get_MessageType_PGN61184_11", "()I",
						(void*) Get_MessageType_PGN61184_11 }, {
						"Get_DTCInformationRequest_1515_PGN61184_11", "()I",
						(void*) Get_DTCInformationRequest_1515_PGN61184_11 }, {
						"Get_DTCType_1510_PGN61184_11", "()I",
						(void*) Get_DTCType_1510_PGN61184_11 },
				{ "Get_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11",
						"()I",
						(void*) Get_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11 },
				//////RX_MAINTENANCE_REQUSET_61184_12///////
				{ "Get_MessageType_PGN61184_12", "()I",
						(void*) Get_MessageType_PGN61184_12 }, {
						"Get_MaintenanceCommant_1097_PGN61184_12", "()I",
						(void*) Get_MaintenanceCommant_1097_PGN61184_12 }, {
						"Get_MaintenanceItem_1098_PGN61184_12", "()I",
						(void*) Get_MaintenanceItem_1098_PGN61184_12 }, {
						"Get_MaintenanceInterval_1091_PGN61184_12", "()I",
						(void*) Get_MaintenanceInterval_1091_PGN61184_12 },
				//////RX_MAINTENANCE_ITEM_LIST_61184_13///////
				{ "Get_MessageType_PGN61184_13", "()I",
						(void*) Get_MessageType_PGN61184_13 },
				{ "Get_TotalNumberofMaintenanceItems_1100_PGN61184_13", "()I",
						(void*) Get_TotalNumberofMaintenanceItems_1100_PGN61184_13 },
				{ "Get_MaintenanceItem_1098_PGN61184_13", "()[B",
						Get_MaintenanceItem_1098_PGN61184_13 },
				//////RX_MAINTENANCE_INFORMATION_61184_14///////
				{ "Get_MessageType_PGN61184_14", "()I",
						(void*) Get_MessageType_PGN61184_14 }, {
						"Get_MaintenanceItem_1098_PGN61184_14", "()I",
						(void*) Get_MaintenanceItem_1098_PGN61184_14 }, {
						"Get_MaintenanceAlarmLamp_1099_PGN61184_14", "()I",
						(void*) Get_MaintenanceAlarmLamp_1099_PGN61184_14 }, {
						"Get_MaintenanceEvent_PGN61184_14", "()I",
						(void*) Get_MaintenanceEvent_PGN61184_14 }, {
						"Get_MaintenanceInterval_1091_PGN61184_14", "()I",
						(void*) Get_MaintenanceInterval_1091_PGN61184_14 }, {
						"Get_MaintenanceTotalCount_1092_PGN61184_14", "()I",
						(void*) Get_MaintenanceTotalCount_1092_PGN61184_14 },
				{ "Get_HourmeterattheLastMaintenance_1093_PGN61184_14", "()I",
						(void*) Get_HourmeterattheLastMaintenance_1093_PGN61184_14 },
				//////RX_MAINTENANCE_HISTORY_61184_15///////
				{ "Get_MessageType_PGN61184_15", "()I",
						(void*) Get_MessageType_PGN61184_15 }, {
						"Get_MaintenanceItem_1098_PGN61184_15", "()I",
						(void*) Get_MaintenanceItem_1098_PGN61184_15 },
				{ "Get_HourmeterattheLastMaintenance_1093_PGN61184_15", "()I",
						(void*) Get_HourmeterattheLastMaintenance_1093_PGN61184_15 },
				{ "Get_MaintenanceHistory_1094_PGN61184_15", "()[S",
						Get_MaintenanceHistory_1094_PGN61184_15 },
				//////RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
				{ "Get_MessageType_PGN61184_16", "()I",
						(void*) Get_MessageType_PGN61184_16 },
				{
						"Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16",
						"()I",
						(void*) Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16 },
				{ "Get_MaintenanceItem_PGN61184_16", "()[B",
						Get_MaintenanceItem_PGN61184_16 },
				//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
				{ "Get_MessageType_PGN61184_21", "()I",
						(void*) Get_MessageType_PGN61184_21 },
				//////RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
				{ "Get_MessageType_PGN61184_22", "()I",
						(void*) Get_MessageType_PGN61184_22 }, {
						"Get_HCEAntiTheftRandomNumber_1632_PGN61184_22", "()[B",
						Get_HCEAntiTheftRandomNumber_1632_PGN61184_22 },
				{ "Get_RecvSeedFlag_PGN61184_22", "()I",
						(void*) Get_RecvSeedFlag_PGN61184_22 },
				//////RX_HCE_ANTI_THEFT_REQUEST_61184_23///////
				{ "Get_MessageType_PGN61184_23", "()I",
						(void*) Get_MessageType_PGN61184_23 }, {
						"Get_HCEAntiTheftCommand_1633_PGN61184_23", "()I",
						(void*) Get_HCEAntiTheftCommand_1633_PGN61184_23 }, {
						"Get_ESLMode_820_PGN61184_23", "()I",
						(void*) Get_ESLMode_820_PGN61184_23 }, {
						"Get_ESLInterval_825_PGN61184_23", "()I",
						(void*) Get_ESLInterval_825_PGN61184_23 },
				{ "Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23",
						"()[B",
						Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23 },
				//////RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
				{ "Get_MessageType_PGN61184_24", "()I",
						(void*) Get_MessageType_PGN61184_24 },
				{ "Get_PasswordCertificationResult_956_PGN61184_24", "()I",
						(void*) Get_PasswordCertificationResult_956_PGN61184_24 },
				{ "Get_RecvPasswordResultFlag_PGN61184_24", "()I",
						(void*) Get_RecvPasswordResultFlag_PGN61184_24 },

				//////RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
				{ "Get_MessageType_PGN61184_25", "()I",
						(void*) Get_MessageType_PGN61184_25 }, {
						"Get_PasswordChangeResult_958_PGN61184_25", "()I",
						(void*) Get_PasswordChangeResult_958_PGN61184_25 },
				{ "Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25",
						"()[B",
						Get_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25 },
				{ "Get_RecvPasswordChangeResultFlag_PGN61184_25", "()I",
										(void*) Get_RecvPasswordChangeResultFlag_PGN61184_25 },
				//////RX_AVERAGE_FUEL_RATE_HISTORY_61184_33///////
				{ "Get_MessageType_PGN61184_33", "()I",(void*) Get_MessageType_PGN61184_33 },
				{"Get_OperationHistoryType_1101_PGN61184_33", "()I",(void*) Get_OperationHistoryType_1101_PGN61184_33 },
				{"Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_1storP_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_2ndorS_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_3rdorE_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_4th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_4th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_5th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_5th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_6th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_6th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_7th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33", "()I",(void*) Get_7th_HourAverageFuelRateorDayFuelUsed_333_1405_PGN61184_33 },
				{"Get_8th_HourAverageFuelRate_333_PGN61184_33", "()I",(void*) Get_8th_HourAverageFuelRate_333_PGN61184_33 },
				{"Get_9th_HourAverageFuelRate_333_PGN61184_33", "()I",(void*) Get_9th_HourAverageFuelRate_333_PGN61184_33 },
				{"Get_10th_HourAverageFuelRate_333_PGN61184_33", "()I",(void*) Get_10th_HourAverageFuelRate_333_PGN61184_33 },
				{"Get_11th_HourAverageFuelRate_333_PGN61184_33", "()I",(void*) Get_11th_HourAverageFuelRate_333_PGN61184_33 },
				{"Get_12th_HourAverageFuelRate_333_PGN61184_33", "()I",(void*) Get_12th_HourAverageFuelRate_333_PGN61184_33 },
				//////RX_COOLING_FAN_SETTING_61184_61///////
				{ "Get_MessageType_PGN61184_61", "()I",(void*) Get_MessageType_PGN61184_61 },
				{"Get_TestMode_PGN61184_61", "()I",(void*) Get_TestMode_PGN61184_61 },
				{"Get_CoolingFanReverseMode_182_PGN61184_61", "()I",(void*) Get_CoolingFanReverseMode_182_PGN61184_61 },
				{"Get_CoolingFanValveCurrent_146_PGN61184_61", "()I",(void*) Get_CoolingFanValveCurrent_146_PGN61184_61 },
				{ "Get_CoolingFanReverseIntervalTime_211_PGN61184_61", "()I",(void*) Get_CoolingFanReverseIntervalTime_211_PGN61184_61 },
				{ "Get_CoolingFanReverseOperatingTime_212_PGN61184_61", "()I",(void*) Get_CoolingFanReverseOperatingTime_212_PGN61184_61 },
				//////RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
				{ "Get_MessageType_PGN61184_62", "()I",
						(void*) Get_MessageType_PGN61184_62 },
				{ "Get_WeighingSystemAccumulationMode_1941_PGN61184_62",
						"()I",
						(void*) Get_WeighingSystemAccumulationMode_1941_PGN61184_62 },
				{ "Get_RequestReweighing_PGN61184_62", "()I",
						(void*) Get_RequestReweighing_PGN61184_62 }, {
						"Get_RequestTotalWorkWeightReset_PGN61184_62", "()I",
						(void*) Get_RequestTotalWorkWeightReset_PGN61184_62 },
						{"Get_WeightOffsetSelection_PGN61184_62", "()I",(void*)Get_WeightOffsetSelection_PGN61184_62  },
						{"Get_WeightOffsetSetting_PGN61184_62", "()I",(void*)Get_WeightOffsetSetting_PGN61184_62  },
						{"Get_WeightOffset_1922_PGN61184_62", "()I",(void*) Get_WeightOffset_1922_PGN61184_62 },
						{"Get_WeighingDisplayMode1_1910_PGN61184_62", "()I",(void*)Get_WeighingDisplayMode1_1910_PGN61184_62  },

				//////RX_WEIGHT_OFFSET_61184_63///////
				{ "Get_MessageType_PGN61184_63", "()I",(void*) Get_MessageType_PGN61184_63 },
				{"Get_WeightOffsetSelectionStatus_PGN61184_63", "()I",(void*) Get_WeightOffsetSelectionStatus_PGN61184_63 },
				{"Get_WeightOffsetWorkTool1_1922_PGN61184_63", "()I",(void*) Get_WeightOffsetWorkTool1_1922_PGN61184_63 },
				{"Get_WeightOffsetWorkTool2_1922_PGN61184_63", "()I",(void*) Get_WeightOffsetWorkTool2_1922_PGN61184_63 },
				{"Get_WeightOffsetWorkTool3_1922_PGN61184_63", "()I",(void*) Get_WeightOffsetWorkTool3_1922_PGN61184_63 },
				//////PARALLEL_LIFT_OPERATION_STATUS_61184_65///////
				{"Get_MessageType_PGN61184_65", "()I",(void*) Get_MessageType_PGN61184_65 },
				{"Get_ParallelLiftTargetAngleSettingErrorStatus_1924_PGN61184_65", "()I",(void*) Get_ParallelLiftTargetAngleSettingErrorStatus_1924_PGN61184_65 },
				{"Get_ParallelLiftInhibitedStatus_1925_PGN61184_65", "()I",(void*) Get_ParallelLiftInhibitedStatus_1925_PGN61184_65 },
				{"Get_ParallelLiftOverloadStatus_1926_PGN61184_65", "()I",(void*) Get_ParallelLiftOverloadStatus_1926_PGN61184_65 },
				//////RX_MACHINE_MODE_SETTING_61184_101///////
				{ "Get_MessageType_PGN61184_101", "()I",
						(void*) Get_MessageType_PGN61184_101 },
				{ "Get_EngineAlternateLowIdleSwitch_348_PGN61184_101", "()I",
						(void*) Get_EngineAlternateLowIdleSwitch_348_PGN61184_101 },
				{ "Get_EnginePowerMode_347_PGN61184_101", "()I",
						(void*) Get_EnginePowerMode_347_PGN61184_101 },
				//////RX_TRAVEL_MODE_SETTING_61184_104///////
				{ "Get_MessageType_PGN61184_104", "()I",
						(void*) Get_MessageType_PGN61184_104 }, {
						"Get_TransmisstionShiftMode_543_PGN61184_104", "()I",
						(void*) Get_TransmisstionShiftMode_543_PGN61184_104 }, {
						"Get_ClutchCutoffMode_544_PGN61184_104", "()I",
						(void*) Get_ClutchCutoffMode_544_PGN61184_104 }, {
						"Get_KickDownShiftMode_547_PGN61184_104", "()I",
						(void*) Get_KickDownShiftMode_547_PGN61184_104 },
				{ "Get_TransmissionTCLockupEngaged_568_PGN61184_104", "()I",
						(void*) Get_TransmissionTCLockupEngaged_568_PGN61184_104 },
				{"Get_VehicleSpeedLimitMode_575_PGN61184_104", "()I",(void*) Get_VehicleSpeedLimitMode_575_PGN61184_104 },
				//////RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
				{ "Get_MessageType_PGN61184_105", "()I",
						(void*) Get_MessageType_PGN61184_105 }, {
						"Get_SettingSelection_PGN61184_105", "()I",
						(void*) Get_SettingSelection_PGN61184_105 }, {
						"Get_SpeedometerFrequency_534_PGN61184_105", "()I",
						(void*) Get_SpeedometerFrequency_534_PGN61184_105 },
				{ "Get_AutoRideControlOperationSpeedForward_574_PGN61184_105", "()I",(void*) Get_AutoRideControlOperationSpeedForward_574_PGN61184_105 },
				{ "Get_AutoRideControlOperationSpeedBackward_576_PGN61184_105", "()I",(void*) Get_AutoRideControlOperationSpeedBackward_576_PGN61184_105 },
				{ "Get_VehicleSpeedLimit_572_PGN61184_105", "()I",
						(void*) Get_VehicleSpeedLimit_572_PGN61184_105 },
				//////RX_TRAVEL_CONTROL_VALUE_61184_106///////
				{ "Get_MessageType_PGN61184_106", "()I",
						(void*) Get_MessageType_PGN61184_106 }, {
						"Get_SpeedometerFrequency_534_PGN61184_106", "()I",
						(void*) Get_SpeedometerFrequency_534_PGN61184_106 },
				{ "Get_AutoRideControlOperationSpeedForward_574_PGN61184_106", "()I",(void*) Get_AutoRideControlOperationSpeedForward_574_PGN61184_106 },
				{ "Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106", "()I",(void*) Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106 },
				{ "Get_VehicleSpeedLimit_572_PGN61184_106", "()I",
						(void*) Get_VehicleSpeedLimit_572_PGN61184_106 },
				//////RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
				{ "Get_MessageType_PGN61184_109", "()I",
						(void*) Get_MessageType_PGN61184_109 },
				{ "Get_BacklightIlluminationLevel_719_PGN61184_109", "()I",
						(void*) Get_BacklightIlluminationLevel_719_PGN61184_109 },
				{ "Get_WiperSpeedLevel_718_PGN61184_109", "()I",
						(void*) Get_WiperSpeedLevel_718_PGN61184_109 }, {
						"Get_Clock_819_PGN61184_109", "()I",
						(void*) Get_Clock_819_PGN61184_109 }, {
						"Get_RequestEngineLowIdleSpeed_PGN61184_109", "()I",
						(void*) Get_RequestEngineLowIdleSpeed_PGN61184_109 }, {
						"Get_RequestTripDataReset_PGN61184_109", "()I",
						(void*) Get_RequestTripDataReset_PGN61184_109 },
				//////RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
				{ "Get_MessageType_PGN61184_121", "()I",
						(void*) Get_MessageType_PGN61184_121 }, {
						"Get_AutomaticEngineShutdown_363_PGN61184_121", "()I",
						(void*) Get_AutomaticEngineShutdown_363_PGN61184_121 },
				{ "Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121",
						"()I",
						(void*) Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121 },
				{ "Get_EngineShutdownCotrolByte_PGN61184_121",
										"()I",
										(void*) Get_EngineShutdownCotrolByte_PGN61184_121 },
				{ "Get_DelayedEngineShutdown_365_PGN61184_121", "()I",
						(void*) Get_DelayedEngineShutdown_365_PGN61184_121 },
				{ "Get_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121",
						"()I",
						(void*) Get_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121 },
				//////RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
				{ "Get_MessageType_PGN61184_122", "()I",(void*) Get_MessageType_PGN61184_122 },
				{ "Get_AutomaticEngineShutdown_363_PGN61184_122","()I",(void*) Get_AutomaticEngineShutdown_363_PGN61184_122 },
				{ "Get_AutomaticEngineShutdownType_PGN61184_122","()I",(void*) Get_AutomaticEngineShutdownType_PGN61184_122 },
				{ "Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122","()I",(void*) Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122 },
				{ "Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122","()I",(void*) Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122 },
				{ "Get_SettingTimeforDelayedEngineShutdown_366_PGN61184_122","()I",(void*) Get_SettingTimeforDelayedEngineShutdown_366_PGN61184_122 },
				{ "Get_RemainingTimeforDelayedEngineShutdown_PGN61184_122","()I",(void*) Get_RemainingTimeforDelayedEngineShutdown_PGN61184_122 },
				//////RX_DETENT_MODE_SETTING_61184_123///////
				{ "Get_MessageType_PGN61184_123", "()I",
						(void*) Get_MessageType_PGN61184_123 }, {
						"Get_BoomDetentMode_223_PGN61184_123", "()I",
						(void*) Get_BoomDetentMode_223_PGN61184_123 }, {
						"Get_BucketDetentMode_224_PGN61184_123", "()I",
						(void*) Get_BucketDetentMode_224_PGN61184_123 },
				{ "Get_RequestDetentReleasePositionSetting_PGN61184_123", "()I",
						(void*) Get_RequestDetentReleasePositionSetting_PGN61184_123 },
				//////RX_DETENT_MODE_STATUS_61184_124///////
				{ "Get_MessageType_PGN61184_124", "()I",
						(void*) Get_MessageType_PGN61184_124 }, {
						"Get_BoomDetentMode_223_PGN61184_124", "()I",
						(void*) Get_BoomDetentMode_223_PGN61184_124 }, {
						"Get_BucketDetentMode_224_PGN61184_124", "()I",
						(void*) Get_BucketDetentMode_224_PGN61184_124 },
				{ "Get_DetentReleasePositionSettingStatus_PGN61184_124", "()I",
						(void*) Get_DetentReleasePositionSettingStatus_PGN61184_124 },
				//////RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
				{ "Get_MessageType_PGN61184_129", "()I",
						(void*) Get_MessageType_PGN61184_129 }, {
						"Get_EngineShutdownCommand_3472_PGN61184_129", "()I",
						(void*) Get_EngineShutdownCommand_3472_PGN61184_129 },
				//////RX_AS_PHONE_NUMBER_SETTING_61184_151///////
				{ "Get_MessageType_PGN61184_151", "()I",
						(void*) Get_MessageType_PGN61184_151 }, {
						"Get_ASPhoneNumber_PGN61184_151", "()[B",
						Get_ASPhoneNumber_PGN61184_151 },
				//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
				{ "Get_MessageType_PGN61184_201", "()I",
						(void*) Get_MessageType_PGN61184_201 },
				{ "Get_RequestBoomPressureCalibration_PGN61184_201", "()I",
						(void*) Get_RequestBoomPressureCalibration_PGN61184_201 },
				{ "Get_RequestBoomBucketAngleSensorCalibration_PGN61184_201",
						"()I",
						(void*) Get_RequestBoomBucketAngleSensorCalibration_PGN61184_201 },
				{ "Get_RequestAEB_PGN61184_201", "()I",
						(void*) Get_RequestAEB_PGN61184_201 },
				{ "Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201",
						"()I",
						(void*) Get_RequestBrakePedalPositionSensorCalibration_PGN61184_201 },
				//////RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
				{ "Get_MessageType_PGN61184_202", "()I",
						(void*) Get_MessageType_PGN61184_202 },
				{ "Get_BoomPressureCalibrationStatus_1908_PGN61184_202", "()I",
						(void*) Get_BoomPressureCalibrationStatus_1908_PGN61184_202 },
				{ "Get_AngleSensorCalibrationStatus_1909_PGN61184_202", "()I",
						(void*) Get_AngleSensorCalibrationStatus_1909_PGN61184_202 },
				{ "Get_AEBStatusInformation_MainCode_562_PGN61184_202", "()I",
						(void*) Get_AEBStatusInformation_MainCode_562_PGN61184_202 },
				{ "Get_AEBCycleNumber_540_PGN61184_202", "()I",
						(void*) Get_AEBCycleNumber_540_PGN61184_202 },
				{ "Get_AEBStatusInformation_SubCode_563_PGN61184_202", "()I",
						(void*) Get_AEBStatusInformation_SubCode_563_PGN61184_202 },
				{
						"Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202",
						"()I",
						(void*) Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202 },
				{"Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202","()I",(void*) Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202 },
				//////RX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
				{ "Get_MessageType_PGN61184_203", "()I",
						(void*) Get_MessageType_PGN61184_203 }, {
						"Get_BucketPriorityOperation_2301_PGN61184_203", "()I",
						(void*) Get_BucketPriorityOperation_2301_PGN61184_203 },
				{ "Get_FlowFineModulationOperation_2302_PGN61184_203", "()I",
						(void*) Get_FlowFineModulationOperation_2302_PGN61184_203 },
				{ "Get_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203", "()I",(void*) Get_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203 },
				{ "Get_BoomLeverFloatingPosition_PGN61184_203", "()I",(void*) Get_BoomLeverFloatingPosition_PGN61184_203 },
				//////RX_MONIOTR_STATUS_65327///////
						{"Get_RequestBuzzerStop_PGN65327", "()I",(void*) Get_RequestBuzzerStop_PGN65327 },
						{"Get_SpeedmeterUnitChange_PGN65327", "()I",(void*) Get_SpeedmeterUnitChange_PGN65327 },
				//////RX_RMCU_STATUS_65329///////
				{ "Get_RMCUNetworkType_1621_PGN65329", "()I",
						(void*) Get_RMCUNetworkType_1621_PGN65329 }, {
						"Get_RMCUBackupBatteryVoltage_1590_PGN65329", "()I",
						(void*) Get_RMCUBackupBatteryVoltage_1590_PGN65329 }, {
						"Get_RMCUPowerSource_1594_PGN65329", "()I",
						(void*) Get_RMCUPowerSource_1594_PGN65329 }, {
						"Get_RMCUBoxOpeningStatus_PGN65329", "()I",
						(void*) Get_RMCUBoxOpeningStatus_PGN65329 }, {
						"Get_NetworkCommunicationStatus1_1622_PGN65329", "()I",
						(void*) Get_NetworkCommunicationStatus1_1622_PGN65329 },
				{ "Get_PositionUpdateStatus_852_PGN65329", "()I",
						(void*) Get_PositionUpdateStatus_852_PGN65329 }, {
						"Get_MachinePositionStatus_1593_PGN65329", "()I",
						(void*) Get_MachinePositionStatus_1593_PGN65329 },
				{ "Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329", "()I",
						(void*) Get_GPSAntennaConnectionAlarmStatus_1595_PGN65329 },
				{ "Get_NetworkTransceiverStatus1_1623_PGN65329", "()I",
						(void*) Get_NetworkTransceiverStatus1_1623_PGN65329 }, {
						"Get_NetworkServiceStatus1_1624_PGN65329", "()I",
						(void*) Get_NetworkServiceStatus1_1624_PGN65329 }, {
						"Get_NetworkAntennaStatus1_1625_PGN65329", "()I",
						(void*) Get_NetworkAntennaStatus1_1625_PGN65329 },
				{ "Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329", "()I",
						(void*) Get_RMCUData_NumberofMessagestoTransmit_855_PGN65329 },
				//////RX_COMPONENT_IDENTIFICATION_65330///////
				{ "Get_ComponentCode_1699_PGN65330", "()I",
						(void*) Get_ComponentCode_1699_PGN65330 }, {
						"Get_ManufacturerCode_1700_PGN65330", "()I",
						(void*) Get_ManufacturerCode_1700_PGN65330 }, {
						"Get_ComponentBasicInformation_1698_PGN65330", "()[B",
						Get_ComponentBasicInformation_1698_PGN65330 },
				//////RX_COMPONENT_IDENTIFICATION_CLUSTER_65330///////
				{ "Get_ComponentCode_1699_PGN65330_CLUSTER", "()I",(void*) Get_ComponentCode_1699_PGN65330_CLUSTER },
				{ "Get_ManufacturerCode_1700_PGN65330_CLUSTER", "()I",(void*) Get_ManufacturerCode_1700_PGN65330_CLUSTER },
				{ "Get_ComponentBasicInformation_1698_PGN65330_CLUSTER", "()[B",Get_ComponentBasicInformation_1698_PGN65330_CLUSTER },
				//////RX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
				{ "Get_ComponentCode_1699_PGN65330_MONITOR", "()I",
						(void*) Get_ComponentCode_1699_PGN65330_MONITOR }, {
						"Get_ManufacturerCode_1700_PGN65330_MONITOR", "()I",
						(void*) Get_ManufacturerCode_1700_PGN65330_MONITOR },
				{ "Get_ComponentBasicInformation_1698_PGN65330_MONITOR", "()[B",
						Get_ComponentBasicInformation_1698_PGN65330_MONITOR },
				//////RX_COMPONENT_IDENTIFICATION_EHCU_65330///////
				{ "Get_ComponentCode_1699_PGN65330_EHCU", "()I",
						(void*) Get_ComponentCode_1699_PGN65330_EHCU }, {
						"Get_ManufacturerCode_1700_PGN65330_EHCU", "()I",
						(void*) Get_ManufacturerCode_1700_PGN65330_EHCU },
				{ "Get_ComponentBasicInformation_1698_PGN65330_EHCU", "()[B",
						Get_ComponentBasicInformation_1698_PGN65330_EHCU },
				//////RX_COMPONENT_IDENTIFICATION_RMCU_65330///////
				{ "Get_ComponentCode_1699_PGN65330_RMCU", "()I",
						(void*) Get_ComponentCode_1699_PGN65330_RMCU }, {
						"Get_ManufacturerCode_1700_PGN65330_RMCU", "()I",
						(void*) Get_ManufacturerCode_1700_PGN65330_RMCU },
				{ "Get_ComponentBasicInformation_1698_PGN65330_RMCU", "()[B",
						Get_ComponentBasicInformation_1698_PGN65330_RMCU },
				//////RX_COMPONENT_IDENTIFICATION_ECM_65330///////
				{ "Get_ComponentCode_1699_PGN65330_ECM", "()I",
						(void*) Get_ComponentCode_1699_PGN65330_ECM }, {
						"Get_ManufacturerCode_1700_PGN65330_ECM", "()I",
						(void*) Get_ManufacturerCode_1700_PGN65330_ECM },
				{ "Get_ComponentBasicInformation_1698_PGN65330_ECM", "()[B",
						Get_ComponentBasicInformation_1698_PGN65330_ECM },
				//////RX_COMPONENT_IDENTIFICATION_TCU_65330///////
				{"Get_ComponentCode_1699_PGN65330_TCU", "()I",(void*) Get_ComponentCode_1699_PGN65330_TCU },
				{"Get_ManufacturerCode_1700_PGN65330_TCU", "()I",(void*) Get_ManufacturerCode_1700_PGN65330_TCU },
				{"Get_ComponentBasicInformation_1698_PGN65330_TCU", "()[B",Get_ComponentBasicInformation_1698_PGN65330_TCU },
				//////RX_COMPONENT_IDENTIFICATION_ACU_65330///////
				{"Get_ComponentCode_1699_PGN65330_ACU", "()I",(void*) Get_ComponentCode_1699_PGN65330_ACU },
				{"Get_ManufacturerCode_1700_PGN65330_ACU", "()I",(void*) Get_ManufacturerCode_1700_PGN65330_ACU },
				{"Get_ComponentBasicInformation_1698_PGN65330_ACU", "()[B",Get_ComponentBasicInformation_1698_PGN65330_ACU },
		        //////RX_COMPONENT_IDENTIFICATION_BKCU_65330///////
				{ "Get_ComponentCode_1699_PGN65330_BKCU", "()I",(void*) Get_ComponentCode_1699_PGN65330_BKCU },
				{ "Get_ManufacturerCode_1700_PGN65330_BKCU", "()I",(void*) Get_ManufacturerCode_1700_PGN65330_BKCU },
				{ "Get_ComponentBasicInformation_1698_PGN65330_BKCU", "()[B",Get_ComponentBasicInformation_1698_PGN65330_BKCU },
				//////RX_TRIP_TIME_INFORMATION_65344///////
				{ "Get_TripTime_849_PGN65344", "()I",
						(void*) Get_TripTime_849_PGN65344 },
				//////RX_MACHINE_SECURITY_STATUS_65348///////
				{ "Get_ESLMode_820_PGN65348", "()I",
						(void*) Get_ESLMode_820_PGN65348 }, {
						"Get_LockMode_822_PGN65348", "()I",
						(void*) Get_LockMode_822_PGN65348 }, {
						"Get_LockLevel_823_PGN65348", "()I",
						(void*) Get_LockLevel_823_PGN65348 }, {
						"Get_LockDemander_824_PGN65348", "()I",
						(void*) Get_LockDemander_824_PGN65348 }, {
						"Get_EngineStartingPermission_821_PGN65348", "()I",
						(void*) Get_EngineStartingPermission_821_PGN65348 },
				{ "Get_PasswordIdentificationBlockedState_1631_PGN65348", "()I",
						(void*) Get_PasswordIdentificationBlockedState_1631_PGN65348 },
				{ "Get_ESLInterval_825_PGN65348", "()I",
						(void*) Get_ESLInterval_825_PGN65348 },
				//////RX_MACHINE_MODE_STATUS_65350///////
				{ "Get_EngineAlternateLowIdelSwitch_348_PGN65350", "()I",(void*) Get_EngineAlternateLowIdelSwitch_348_PGN65350 },
				{ "Get_EnginePowerMode_347_PGN65350", "()I",(void*) Get_EnginePowerMode_347_PGN65350 },
				//////RX_HYDRAULIC_PRESSURE4_65354///////
				{ "Get_BrakeOilPressure_503_PGN65354", "()I",
						(void*) Get_BrakeOilPressure_503_PGN65354 },
				{ "Get_BrakeOilChargingPriorityPressure_557_PGN65354", "()I",
						(void*) Get_BrakeOilChargingPriorityPressure_557_PGN65354 },
				//////RX_HYDRAULIC_PRESSURE6_65356///////
				{ "Get_BoomCylinderHeadPressure_204_PGN65356", "()I",
						(void*) Get_BoomCylinderHeadPressure_204_PGN65356 }, {
						"Get_BoomCylinderRodPressure_205_PGN65356", "()I",
						(void*) Get_BoomCylinderRodPressure_205_PGN65356 },
				//////RX_HYDRAULIC_PRESSURE7_65357///////
				{ "Get_SteeringMainPumpPressure_202_PGN65357", "()I",
						(void*) Get_SteeringMainPumpPressure_202_PGN65357 },
				{ "Get_EmergencySteeringPumpPressure_203_PGN65357", "()I",
						(void*) Get_EmergencySteeringPumpPressure_203_PGN65357 },
				{ "Get_ParkingOilPressure_507_PGN65357", "()I",
						(void*) Get_ParkingOilPressure_507_PGN65357 }, {
						"Get_DifferentialLockPressure_558_PGN65357", "()I",
						(void*) Get_DifferentialLockPressure_558_PGN65357 },
				//////RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
				{ "Get_AlternatorVoltage_707_PGN65360", "()I",
						(void*) Get_AlternatorVoltage_707_PGN65360 },
				//////RX_RELAY_BUZZER_STATUS_65364///////
				{ "Get_AntiRestartRelay_327_PGN65364", "()I",
						(void*) Get_AntiRestartRelay_327_PGN65364 }, {
						"Get_FuelWarmerRelay_325_PGN65364", "()I",
						(void*) Get_FuelWarmerRelay_325_PGN65364 }, {
						"Get_EnginePreheatRelay_322_PGN65364", "()I",
						(void*) Get_EnginePreheatRelay_322_PGN65364 }, {
						"Get_EngineStopRelay_345_PGN65364", "()I",
						(void*) Get_EngineStopRelay_345_PGN65364 }, {
						"Get_WorkingCutoffRelay_164_PGN65364", "()I",
						(void*) Get_WorkingCutoffRelay_164_PGN65364 }, {
						"Get_TravelingCutoffRelay_517_PGN65364", "()I",
						(void*) Get_TravelingCutoffRelay_517_PGN65364 }, {
						"Get_ParkingRelay_514_PGN65364", "()I",
						(void*) Get_ParkingRelay_514_PGN65364 }, {
						"Get_WiperRelay_727_PGN65364", "()I",
						(void*) Get_WiperRelay_727_PGN65364 }, {
						"Get_EmergencySteeringPumpRelay_187_PGN65364", "()I",
						(void*) Get_EmergencySteeringPumpRelay_187_PGN65364 }, {
						"Get_TravelAlarmBuzzer_722_PGN65364", "()I",
						(void*) Get_TravelAlarmBuzzer_722_PGN65364 }, {
						"Get_Buzzer_723_PGN65364", "()I",
						(void*) Get_Buzzer_723_PGN65364 }, {
						"Get_FuelCutoffRelay_324_PGN65364", "()I",
						(void*) Get_FuelCutoffRelay_324_PGN65364 },
				//////RX_SOLENOID_STATUS_65365///////
				{ "Get_BoomUpLeverDetentSolenoid_172_PGN65365", "()I",
						(void*) Get_BoomUpLeverDetentSolenoid_172_PGN65365 }, {
						"Get_BoomDownLeverDetentSolenoid_173_PGN65365", "()I",
						(void*) Get_BoomDownLeverDetentSolenoid_173_PGN65365 },
				{ "Get_BucketLeverDetentSolenoid_174_PGN65365", "()I",
						(void*) Get_BucketLeverDetentSolenoid_174_PGN65365 },
				//////RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
				{ "Get_AcceleratorPedalPositionVoltage1_710_PGN65368", "()I",
						(void*) Get_AcceleratorPedalPositionVoltage1_710_PGN65368 },
				{ "Get_BrakePedalPositionVoltage_573_PGN65368", "()I",
						(void*) Get_BrakePedalPositionVoltage_573_PGN65368 }, {
						"Get_AcceleratorPedalPosition1_339_PGN65368", "()I",
						(void*) Get_AcceleratorPedalPosition1_339_PGN65368 },
				{ "Get_AcceleratorPedalPositionVoltage2_710_PGN65368", "()I",
						(void*) Get_AcceleratorPedalPositionVoltage2_710_PGN65368 },
				//////RX_COOLING_FAN_STATUS_65369///////
				{ "Get_CoolingFanReverseMode_182_PGN65369", "()I",
						(void*) Get_CoolingFanReverseMode_182_PGN65369 }, {
						"Get_CoolingFanReverseSolenoid_181_PGN65369", "()I",
						(void*) Get_CoolingFanReverseSolenoid_181_PGN65369 },
				{ "Get_CoolingFanReverseSwitchManual_740_PGN65369", "()I",
						(void*) Get_CoolingFanReverseSwitchManual_740_PGN65369 },
				{ "Get_CoolingFanReverseSwitchAuto_741_PGN65369", "()I",
						(void*) Get_CoolingFanReverseSwitchAuto_741_PGN65369 },
				{ "Get_CoolingFanValveCurrent_146_PGN65369", "()I",
						(void*) Get_CoolingFanValveCurrent_146_PGN65369 }, {
						"Get_CoolingFanSpeed_318_PGN65369", "()I",
						(void*) Get_CoolingFanSpeed_318_PGN65369 },
				{ "Get_CoolingFanReverseIntervalTime_211_PGN65369", "()I",
						(void*) Get_CoolingFanReverseIntervalTime_211_PGN65369 },
				{ "Get_CoolingFanReverseOperatingTime_212_PGN65369", "()I",
						(void*) Get_CoolingFanReverseOperatingTime_212_PGN65369 },
				//////RX_ENGINE_STATUS2_65370///////
				{"Get_EngineFuelRate_331_PGN65370", "()I", (void*) Get_EngineFuelRate_331_PGN65370 },
				{"Get_EnginePercentLoadatCurrentSpeed_334_PGN65370", "()I", (void*) Get_EnginePercentLoadatCurrentSpeed_334_PGN65370 },
				{"Get_EngineActualPercentTorque_335_PGN65370", "()I", (void*) Get_EngineActualPercentTorque_335_PGN65370 },
				//////RX_ENGINE_STATUS1_65371///////
				{"Get_EngineOperatingCondition_336_PGN65371", "()I",(void*) Get_EngineOperatingCondition_336_PGN65371 },
				{"Get_DEFTankLevel_362_PGN65371", "()I",(void*) Get_DEFTankLevel_362_PGN65371 },
				{"Get_EngineIntakeManifold1Temperatue_329_PGN65371", "()I",(void*) Get_EngineIntakeManifold1Temperatue_329_PGN65371 },
				{"Get_EngineFuelTemperature_330_PGN65371", "()I",(void*) Get_EngineFuelTemperature_330_PGN65371 },
				{"Get_EngineOilPressure_311_PGN65371", "()I",(void*) Get_EngineOilPressure_311_PGN65371 },
				{"Get_BarometricPressure_328_PGN65371", "()I",(void*) Get_BarometricPressure_328_PGN65371 },
				{"Get_EngineAirIntakePressure_337_PGN65371", "()I",(void*) Get_EngineAirIntakePressure_337_PGN65371 },
				//////RX_AIR_CONDITIONER_STATUS_65373///////
				{"Get_FATCSettingTemperatureCelsius_3408_PGN65373", "()I",(void*) Get_FATCSettingTemperatureCelsius_3408_PGN65373 },
				{"Get_FATCSettingTemperatureFahrenheit_3409_PGN65373", "()I",(void*) Get_FATCSettingTemperatureFahrenheit_3409_PGN65373 },
				{"Get_Ambienttemperaturesensoropen_PGN65373", "()I",(void*) Get_Ambienttemperaturesensoropen_PGN65373 },
				{"Get_Ambienttemperaturesensorshort_PGN65373", "()I",(void*) Get_Ambienttemperaturesensorshort_PGN65373 },
				{"Get_Incabtemperaturesensoropen_PGN65373", "()I",(void*) Get_Incabtemperaturesensoropen_PGN65373 },
				{"Get_Incabtemperaturesensorshort_PGN65373", "()I",(void*) Get_Incabtemperaturesensorshort_PGN65373 },
				{"Get_Evaptemperaturesensoropen_PGN65373", "()I",(void*) Get_Evaptemperaturesensoropen_PGN65373 },
				{"Get_Evaptemperaturesensorshort_PGN65373", "()I",(void*) Get_Evaptemperaturesensorshort_PGN65373 },
				{"Get_Mode1actuatoropenshort_PGN65373", "()I",(void*) Get_Mode1actuatoropenshort_PGN65373 },
				{"Get_Mode1actuatordrivecircuitmalfunction_PGN65373", "()I",(void*) Get_Mode1actuatordrivecircuitmalfunction_PGN65373 },
				{"Get_Intakeactuatoropenshort_PGN65373", "()I",(void*) Get_Intakeactuatoropenshort_PGN65373 },
				{"Get_Intakeactuatordrivecircuitmalfunction_PGN65373", "()I",(void*) Get_Intakeactuatordrivecircuitmalfunction_PGN65373 },
				{"Get_Temperatureactuatoropenshort_PGN65373", "()I",(void*) Get_Temperatureactuatoropenshort_PGN65373 },
				{"Get_Temperatureactuatordrivecircuitmalfunction_PGN65373", "()I",(void*) Get_Temperatureactuatordrivecircuitmalfunction_PGN65373 },
				{"Get_Ducttemperaturesensoropen_PGN65373", "()I",(void*) Get_Ducttemperaturesensoropen_PGN65373 },
				{"Get_Ducttemperaturesensorshort_PGN65373", "()I",(void*) Get_Ducttemperaturesensorshort_PGN65373 },
				{"Get_WaterValveSensorError_PGN65373", "()I",(void*) Get_WaterValveSensorError_PGN65373 },
				{"Get_GPSCircuitError_PGN65373", "()I",(void*) Get_GPSCircuitError_PGN65373 },
				//////RX_VEHICLE_DISTANCE_65389///////
				{ "Get_TripDistance_600_PGN65389", "()I",
						(void*) Get_TripDistance_600_PGN65389 }, {
						"Get_TotalVehicleDistance_601_PGN65389", "()I",
						(void*) Get_TotalVehicleDistance_601_PGN65389 },
				///////RX_FUEL_INFORMATION_ECO_GAUGE_65390////////////////
				{"Get_FuelLevel_302_PGN65390", "()I", (void*) Get_FuelLevel_302_PGN65390 },
				{"Get_EcoGaugeLevel_1304_PGN65390", "()I",(void*) Get_EcoGaugeLevel_1304_PGN65390 },
				{"Get_EcoGaugeStatus_1305_PGN65390", "()I",(void*) Get_EcoGaugeStatus_1305_PGN65390 },
				{"Get_AverageFuelRate_333_PGN65390", "()I",(void*) Get_AverageFuelRate_333_PGN65390 },
				{"Get_ADaysFuelUsed_1405_PGN65390", "()I",(void*) Get_ADaysFuelUsed_1405_PGN65390 },

				//////RX_CYLINDER_ANGLE_STROKE1_65395///////
				{ "Get_BoomLinkAngle_1920_PGN65395", "()I",
						(void*) Get_BoomLinkAngle_1920_PGN65395 }, {
						"Get_BellCrankAngle_1921_PGN65395", "()I",
						(void*) Get_BellCrankAngle_1921_PGN65395 },
				{ "Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395", "()I",
						(void*) Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395 },
				{ "Get_BellCrankAngleSensorSignalVoltage_729_PGN65395", "()I",
						(void*) Get_BellCrankAngleSensorSignalVoltage_729_PGN65395 },
				{ "Get_BucketCylinderStroke_1930_PGN65395", "()I",
						(void*) Get_BucketCylinderStroke_1930_PGN65395 },
				//////RX_CYLINDER_ANGLE_STROKE2_65396///////
				{ "Get_BucketAngle_1931_PGN65396", "()I",(void*) Get_BucketAngle_1931_PGN65396 },
				//////RX_AS_PHONE_NUMBER_65425///////
				{ "Get_ASPhoneNumber_1095_PGN65425", "()[B",
						Get_ASPhoneNumber_1095_PGN65425 },
				//////RX_WARNING_LAMP_65427///////
				{ "Get_FuelLevelLow_303_PGN65427", "()I",
						(void*) Get_FuelLevelLow_303_PGN65427 }, {
						"Get_HydraulicOilTemperatureHigh_102_PGN65427", "()I",
						(void*) Get_HydraulicOilTemperatureHigh_102_PGN65427 },
				{ "Get_BatteryVoltageLow_706_PGN65427", "()I",
						(void*) Get_BatteryVoltageLow_706_PGN65427 }, {
						"Get_Overload_104_PGN65427", "()I",
						(void*) Get_Overload_104_PGN65427 }, {
						"Get_AirCleanerClog_317_PGN65427", "()I",
						(void*) Get_AirCleanerClog_317_PGN65427 }, {
						"Get_EngineCheckLamp_320_PGN65427", "()I",
						(void*) Get_EngineCheckLamp_320_PGN65427 }, {
						"Get_TransmissionOilPressureLow_502_PGN65427", "()I",
						(void*) Get_TransmissionOilPressureLow_502_PGN65427 }, {
						"Get_BrakeOilPressureLow_504_PGN65427", "()I",
						(void*) Get_BrakeOilPressureLow_504_PGN65427 }, {
						"Get_EngineCoolantTemperatureHigh_305_PGN65427", "()I",
						(void*) Get_EngineCoolantTemperatureHigh_305_PGN65427 },
				{ "Get_EngineCoolantLevelLow_307_PGN65427", "()I",
						(void*) Get_EngineCoolantLevelLow_307_PGN65427 }, {
						"Get_EngineOilPressureLow_313_PGN65427", "()I",
						(void*) Get_EngineOilPressureLow_313_PGN65427 }, {
						"Get_EngineOilFilterClog_315_PGN65427", "()I",
						(void*) Get_EngineOilFilterClog_315_PGN65427 },
				{ "Get_TransmissionOilTemperatureHigh_537_PGN65427", "()I",
						(void*) Get_TransmissionOilTemperatureHigh_537_PGN65427 },
				{ "Get_TransmissionCheck_538_PGN65427", "()I",
						(void*) Get_TransmissionCheck_538_PGN65427 }, {
						"Get_SteeringMainPumpPressureLow_184_PGN65427", "()I",
						(void*) Get_SteeringMainPumpPressureLow_184_PGN65427 },
				{ "Get_EmergencySteeringActive_185_PGN65427", "()I",
						(void*) Get_EmergencySteeringActive_185_PGN65427 }, {
						"Get_WarningSymbolLamp_709_PGN65427", "()I",
						(void*) Get_WarningSymbolLamp_709_PGN65427 }, {
						"Get_WaterInFuelIndicator_360_PGN65427", "()I",
						(void*) Get_WaterInFuelIndicator_360_PGN65427 }, {
						"Get_DTCAlarmLamp_1509_PGN65427", "()I",
						(void*) Get_DTCAlarmLamp_1509_PGN65427 }, {
						"Get_EngineStopLamp_319_PGN65427", "()I",
						(void*) Get_EngineStopLamp_319_PGN65427 }, {
						"Get_DPFLampCommand_352_PGN65427", "()I",
						(void*) Get_DPFLampCommand_352_PGN65427 }, {
						"Get_DEFLowLamp_358_PGN65427", "()I",
						(void*) Get_DEFLowLamp_358_PGN65427 }, {
						"Get_ClutchSlip_569_PGN65427", "()I",
						(void*) Get_ClutchSlip_569_PGN65427 }, {
						"Get_BrakeOilLevelLow_566_PGN65427", "()I",
						(void*) Get_BrakeOilLevelLow_566_PGN65427 }, {
						"Get_BrakeOilTemperatureHigh_567_PGN65427", "()I",
						(void*) Get_BrakeOilTemperatureHigh_567_PGN65427 }, {
						"Get_EmissionSystemFailLamp_357_PGN65427", "()I",
						(void*) Get_EmissionSystemFailLamp_357_PGN65427 },
				//////RX_INDICATOR_LAMP_65428///////
				{ "Get_PowerMaxStatus_802_PGN65428", "()I",
						(void*) Get_PowerMaxStatus_802_PGN65428 }, {
						"Get_DecelerationStatus_803_PGN65428", "()I",
						(void*) Get_DecelerationStatus_803_PGN65428 }, {
						"Get_WarmingUpStatus_804_PGN65428", "()I",
						(void*) Get_WarmingUpStatus_804_PGN65428 }, {
						"Get_EnginePreheatStatus_323_PGN65428", "()I",
						(void*) Get_EnginePreheatStatus_323_PGN65428 }, {
						"Get_FuelWarmerActiveStatus_326_PGN65428", "()I",
						(void*) Get_FuelWarmerActiveStatus_326_PGN65428 }, {
						"Get_CoolingFandrivingStatus_180_PGN65428", "()I",
						(void*) Get_CoolingFandrivingStatus_180_PGN65428 }, {
						"Get_CruiseStatus_519_PGN65428", "()I",
						(void*) Get_CruiseStatus_519_PGN65428 }, {
						"Get_ParkingStatus_508_PGN65428", "()I",
						(void*) Get_ParkingStatus_508_PGN65428 }, {
						"Get_ClutchCutoffStatus_545_PGN65428", "()I",
						(void*) Get_ClutchCutoffStatus_545_PGN65428 }, {
						"Get_FNRJoystickActiveStatus_546_PGN65428", "()I",
						(void*) Get_FNRJoystickActiveStatus_546_PGN65428 }, {
						"Get_RideControlStatus_550_PGN65428", "()I",
						(void*) Get_RideControlStatus_550_PGN65428 }, {
						"Get_DifferentialLockStatus_559_PGN65428", "()I",
						(void*) Get_DifferentialLockStatus_559_PGN65428 }, {
						"Get_JoystickSteeringActiveStatus_186_PGN65428", "()I",
						(void*) Get_JoystickSteeringActiveStatus_186_PGN65428 },
				{ "Get_InchingStatus_549_PGN65428", "()I",
						(void*) Get_InchingStatus_549_PGN65428 }, {
						"Get_RamLockStatus_520_PGN65428", "()I",
						(void*) Get_RamLockStatus_520_PGN65428 }, {
						"Get_WorkingBrakeStatus_506_PGN65428", "()I",
						(void*) Get_WorkingBrakeStatus_506_PGN65428 }, {
						"Get_MaintenanceAlarmLamp_1099_PGN65428", "()I",
						(void*) Get_MaintenanceAlarmLamp_1099_PGN65428 }, {
						"Get_OperatorAbsenceStatus_832_PGN65428", "()I",
						(void*) Get_OperatorAbsenceStatus_832_PGN65428 }, {
						"Get_MirrorHeaterStatus_724_PGN65428", "()I",
						(void*) Get_MirrorHeaterStatus_724_PGN65428 }, {
						"Get_HighBeamStatus_725_PGN65428", "()I",
						(void*) Get_HighBeamStatus_725_PGN65428 }, {
						"Get_TravelAlarmSwitch_721_PGN65428", "()I",
						(void*) Get_TravelAlarmSwitch_721_PGN65428 }, {
						"Get_SwingBoomSwitch_726_PGN65428", "()I",
						(void*) Get_SwingBoomSwitch_726_PGN65428 }, {
						"Get_ParkingWorkingTravelStatus_510_PGN65428", "()I",
						(void*) Get_ParkingWorkingTravelStatus_510_PGN65428 }, {
						"Get_TransmissionTCLockupEngaged_556_PGN65428", "()I",
						(void*) Get_TransmissionTCLockupEngaged_556_PGN65428 },
				{ "Get_ExhaustSystemHighTemperatureLampCommand_353_PGN65428",
						"()I",
						(void*) Get_ExhaustSystemHighTemperatureLampCommand_353_PGN65428 },
				{
						"Get_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428",
						"()I",
						(void*) Get_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428 },
				{"Get_AutomaticEngineShutdown_363_PGN65428", "()I",(void*) Get_AutomaticEngineShutdown_363_PGN65428 },
				{"Get_DelayedEngineShutdown_365_PGN65428", "()I",(void*) Get_DelayedEngineShutdown_365_PGN65428 },
				{"Get_AutoGreaseGreenLEDStatus_3453_PGN65428", "()I",(void*) Get_AutoGreaseGreenLEDStatus_3453_PGN65428 },
				{"Get_AutoGreaseRedLEDStatus_3453_PGN65428", "()I",(void*) Get_AutoGreaseRedLEDStatus_3453_PGN65428 },


				//////RX_GAUGE_65431///////
				{ "Get_FuelLevel_301_PGN65431", "()I",
						(void*) Get_FuelLevel_301_PGN65431 }, {
						"Get_EngineCoolantTemperature_304_PGN65431", "()I",
						(void*) Get_EngineCoolantTemperature_304_PGN65431 }, {
						"Get_HydraulicOilTemperature_101_PGN65431", "()I",
						(void*) Get_HydraulicOilTemperature_101_PGN65431 }, {
						"Get_TransmissionOilTemperature_536_PGN65431", "()I",
						(void*) Get_TransmissionOilTemperature_536_PGN65431 }, {
						"Get_EngineSpeed_310_PGN65431", "()I",
						(void*) Get_EngineSpeed_310_PGN65431 }, {
						"Get_BatteryVoltage_705_PGN65431", "()I",
						(void*) Get_BatteryVoltage_705_PGN65431 },
				//////RX_HOURMETER_CLOCK_WIPER_65433///////
				{ "Get_Hourmeter_1601_PGN65433", "()I",
						(void*) Get_Hourmeter_1601_PGN65433 }, {
						"Get_Clock_819_PGN65433", "()I",
						(void*) Get_Clock_819_PGN65433 }, {
						"Get_HourmeterActiveStatus_703_PGN65433", "()I",
						(void*) Get_HourmeterActiveStatus_703_PGN65433 }, {
						"Get_WiperOperationStatus_717_PGN65433", "()I",
						(void*) Get_WiperOperationStatus_717_PGN65433 }, {
						"Get_WiperSpeedLevel_718_PGN65433", "()I",
						(void*) Get_WiperSpeedLevel_718_PGN65433 }, {
						"Get_BacklightIlluminationLevel_719_PGN65433", "()I",
						(void*) Get_BacklightIlluminationLevel_719_PGN65433 }, {
						"Get_IlluminationStatus_737_PGN65433", "()I",
						(void*) Get_IlluminationStatus_737_PGN65433 },
				//////RX_MACHINE_TRAVEL_STATUS_65434///////
				{ "Get_WheelBasedVehicleSpeed_532_PGN65434", "()I",
						(void*) Get_WheelBasedVehicleSpeed_532_PGN65434 }, {
						"Get_SelectGear_541_PGN65434", "()I",
						(void*) Get_SelectGear_541_PGN65434 }, {
						"Get_ActualGear_542_PGN65434", "()I",
						(void*) Get_ActualGear_542_PGN65434 }, {
						"Get_TransmissionShiftMode_543_PGN65434", "()I",
						(void*) Get_TransmissionShiftMode_543_PGN65434 }, {
						"Get_ClutchCutoffMode_544_PGN65434", "()I",
						(void*) Get_ClutchCutoffMode_544_PGN65434 }, {
						"Get_KickDownShiftMode_547_PGN65434", "()I",
						(void*) Get_KickDownShiftMode_547_PGN65434 }, {
						"Get_TransmissionTCLockupEngaged_568_PGN65434", "()I",
						(void*) Get_TransmissionTCLockupEngaged_568_PGN65434 },
				{ "Get_DifferentialLockMode_570_PGN65434", "()I",
						(void*) Get_DifferentialLockMode_570_PGN65434 }, {
						"Get_VehicleSpeedLimitMode_575_PGN65434", "()I",
						(void*) Get_VehicleSpeedLimitMode_575_PGN65434 },
				//////RX_DTC_INFORMATION_TYPE1_65438///////
				{ "Get_DTCType_1510_PGN65438", "()I",
						(void*) Get_DTCType_1510_PGN65438 }, {
						"Get_NumberofDTCinThisPacket_1514_PGN65438", "()I",
						(void*) Get_NumberofDTCinThisPacket_1514_PGN65438 }, {
						"Get_TotalNumberofDTC_PGN65438", "()I",
						(void*) Get_TotalNumberofDTC_PGN65438 },
				{ "Get_TotalNumberofDTCInformationPacket_1512_PGN65438", "()I",
						(void*) Get_TotalNumberofDTCInformationPacket_1512_PGN65438 },
				{ "Get_SequenceNumberofDTCInformationPacket_1513_PGN65438",
						"()I",
						(void*) Get_SequenceNumberofDTCInformationPacket_1513_PGN65438 },
				{ "Get_DTC_1_PGN65438", "()[B", Get_DTC_1_PGN65438 }, {
						"Get_DTC_2_PGN65438", "()[B", Get_DTC_2_PGN65438 }, {
						"Get_DTC_3_PGN65438", "()[B", Get_DTC_3_PGN65438 }, {
						"Get_DTC_4_PGN65438", "()[B", Get_DTC_4_PGN65438 }, {
						"Get_DTC_5_PGN65438", "()[B", Get_DTC_5_PGN65438 },
				//////RX_CLUSTER_STATUS_65445///////
				{ "Get_Dashboard_Program_Version_988_PGN65445", "()I",
						(void*) Get_Dashboard_Program_Version_988_PGN65445 }, {
						"Get_Dashboard_Hardware_Version_989_PGN65445", "()I",
						(void*) Get_Dashboard_Hardware_Version_989_PGN65445 },{
						"Get_Mirror_Heater_Status_724_PGN65445", "()I",
						(void*) Get_Mirror_Heater_Status_724_PGN65445 }, {
						"Get_High_Beam_Status_725_PGN65445", "()I",
						(void*) Get_High_Beam_Status_725_PGN65445 },{
						"Get_Front_Rear_Lamp_Status_726_PGN65445", "()I",
						(void*) Get_Front_Rear_Lamp_Status_726_PGN65445 }, {
						"Get_HW_Vers_Sub_PGN65445", "()I",
						(void*) Get_HW_Vers_Sub_PGN65445 },
				//////RX_AXLE_STATUS_65449///////
				{ "Get_Front_Axle_Oil_Temperature_577_PGN65449", "()I",
						(void*) Get_Front_Axle_Oil_Temperature_577_PGN65449 },
				{ "Get_Rear_Axle_Oil_Temperature_578_PGN65449", "()I",
						(void*) Get_Rear_Axle_Oil_Temperature_578_PGN65449 },
				{ "Get_Front_Axle_Oil_Temp_Warning_580_PGN65449", "()I",
						(void*) Get_Front_Axle_Oil_Temp_Warning_580_PGN65449 },
				{ "Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449", "()I",
						(void*) Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449 },
				//////RX_WEIGHING_SYSTEM_STATUS_65450///////
				{ "Get_WeighingSystemAccumulationMode_1941_PGN65450",
						"()I",
						(void*) Get_WeighingSystemAccumulationMode_1941_PGN65450 },
				{ "Get_WeighingSystemBuzzer_1907_PGN65450", "()I",
						(void*) Get_WeighingSystemBuzzer_1907_PGN65450 },
				{"Get_CurrentWeighingResult_1919_PGN65450", "()I",(void*) Get_CurrentWeighingResult_1919_PGN65450 },
				{"Get_WeighingDisplayMode1_1910_PGN65450", "()I",(void*) Get_WeighingDisplayMode1_1910_PGN65450 },
				// ++, 150212 bwk
				{"Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450", "()I",(void*) Get_WeighingSystemError_BoomLiftSpeed_1942_PGN65450 },
				{"Get_WeighingSystemError_BucketFullIn_1943_PGN65450", "()I",(void*) Get_WeighingSystemError_BucketFullIn_1943_PGN65450 },
				{"Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450", "()I",(void*) Get_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450 },
				// --, 150212 bwk
				{"Get_CurrentWeight_1911_PGN65450", "()I",(void*) Get_CurrentWeight_1911_PGN65450 },
				{"Get_TodayWeight_1915_PGN65450", "()I",(void*) Get_TodayWeight_1915_PGN65450 },
				//////RX_WEIGHING_SYSTEM_DATA1_65451///////
				{ "Get_TotalWorkAWeight_1912_PGN65451", "()I",
						(void*) Get_TotalWorkAWeight_1912_PGN65451 }, {
						"Get_TotalWorkBWeight_1913_PGN65451", "()I",
						(void*) Get_TotalWorkBWeight_1913_PGN65451 },
				//////RX_WEIGHING_SYSTEM_DATA2_65452///////
				{ "Get_TotalWorkCWeight_1914_PGN65452", "()I",
						(void*) Get_TotalWorkCWeight_1914_PGN65452 }, {
						"Get_ADayBeforeWeight_1916_PGN65452", "()I",
						(void*) Get_ADayBeforeWeight_1916_PGN65452 },
				//////RX_BKCU_STATUS_65514///////
				{ "Get_ButtonKeyPosition_3471_PGN65514", "()I",(void*) Get_ButtonKeyPosition_3471_PGN65514 },
				//////RX_JOYSTICK_POSITION_STATUS_65515///////
				{ "Get_BoomJoystickPositionStatus_2310_PGN65515", "()I",
						(void*) Get_BoomJoystickPositionStatus_2310_PGN65515 },
				{ "Get_BoomJoystickPosition_2311_PGN65515", "()I",
						(void*) Get_BoomJoystickPosition_2311_PGN65515 },
				{ "Get_BucketJoystickPositionStatus_2312_PGN65515", "()I",
						(void*) Get_BucketJoystickPositionStatus_2312_PGN65515 },
				{ "Get_BucketJoystickPosition_2313_PGN65515", "()I",
						(void*) Get_BucketJoystickPosition_2313_PGN65515 }, {
						"Get_AuxJoystickPositionStatus_2314_PGN65515", "()I",
						(void*) Get_AuxJoystickPositionStatus_2314_PGN65515 },
				{"Get_AuxJoystickPosition_2315_PGN65515", "()I",(void*) Get_AuxJoystickPosition_2315_PGN65515 },
				{"Get_BoomLeverFloatingPosition_2336_PGN65515", "()I",(void*) Get_BoomLeverFloatingPosition_2336_PGN65515 },

				//////RX_WHEEL_LOADER_EHCU_STATUS_65517///////
				{ "Get_BucketPriorityOperation_2301_PGN65517", "()I",
						(void*) Get_BucketPriorityOperation_2301_PGN65517 }, {
						"Get_FlowFineModulationOperation_2302_PGN65517", "()I",
						(void*) Get_FlowFineModulationOperation_2302_PGN65517 },
				{ "Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517", "()I",
						(void*) Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517 },
				{ "Get_AttachmentPilotCutoffStatus_225_PGN65517", "()I",
						(void*) Get_AttachmentPilotCutoffStatus_225_PGN65517 },
				{ "Get_FloatMode_2316_PGN65517", "()I",
						(void*) Get_FloatMode_2316_PGN65517 },
				{ "Get_JoystickSteeringActiveStatusEHCU_186_PGN65517", "()I",
						(void*) Get_JoystickSteeringActiveStatusEHCU_186_PGN65517 },
				{ "Get_BoomUpEPPRValveCurrent_2304_PGN65517", "()I",
						(void*) Get_BoomUpEPPRValveCurrent_2304_PGN65517 }, {
						"Get_BoomDownEPPRValveCurrent_2305_PGN65517", "()I",
						(void*) Get_BoomDownEPPRValveCurrent_2305_PGN65517 }, {
						"Get_BucketInEPPRValveCurrent_2306_PGN65517", "()I",
						(void*) Get_BucketInEPPRValveCurrent_2306_PGN65517 }, {
						"Get_BucketOutEPPRValveCurrent_2307_PGN65517", "()I",
						(void*) Get_BucketOutEPPRValveCurrent_2307_PGN65517 }, {
						"Get_AUX1EPPRValveCurrent_2308_PGN65517", "()I",
						(void*) Get_AUX1EPPRValveCurrent_2308_PGN65517 }, {
						"Get_AUX2EPPRValveCurrent_2309_PGN65517", "()I",
						(void*) Get_AUX2EPPRValveCurrent_2309_PGN65517 },

				//////RX_WHEEL_LOADER_EHCU_STATUS2_65524///////
				{"Get_SoftStopBoomUp_2337_PGN65524", "()I",(void*) Get_SoftStopBoomUp_2337_PGN65524 },
				{"Get_SoftStopBoomDown_2338_PGN65524", "()I",(void*) Get_SoftStopBoomDown_2338_PGN65524 },
				{"Get_SoftStopBucketIn_2339_PGN65524", "()I",(void*) Get_SoftStopBucketIn_2339_PGN65524 },
				{"Get_SoftStopBucketOut_2340_PGN65524", "()I",(void*) Get_SoftStopBucketOut_2340_PGN65524 },
				{"Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524", "()I",(void*) Get_BoomDownEPPRValveMaxCurrent_2341_PGN65524 },
				{"Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524", "()I",(void*) Get_BucketOutEPPRValveMaxCurrent_2342_PGN65524 },
				{"Get_JoystickSteeringEnableFailCondition_2343_PGN65524", "()I",(void*) Get_JoystickSteeringEnableFailCondition_2343_PGN65524 },
				//////RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
				{ "Get_TravelAlarmOperationStatus_3431_PGN65527", "()I",
						(void*) Get_TravelAlarmOperationStatus_3431_PGN65527 },
				{ "Get_WasherOperationStatus_3432_PGN65527", "()I",
						(void*) Get_WasherOperationStatus_3432_PGN65527 }, {
						"Get_wiperoperationstatus_3433_PGN65527", "()I",
						(void*) Get_wiperoperationstatus_3433_PGN65527 }, {
						"Get_IntWiperOperationStatus_3434_PGN65527", "()I",
						(void*) Get_IntWiperOperationStatus_3434_PGN65527 }, {
						"Get_WorkLampOperationStatus_3435_PGN65527", "()I",
						(void*) Get_WorkLampOperationStatus_3435_PGN65527 }, {
						"Get_HeadLampOperationStatus_3436_PGN65527", "()I",
						(void*) Get_HeadLampOperationStatus_3436_PGN65527 }, {
						"Get_CabinLampOperationStatus_3437_PGN65527", "()I",
						(void*) Get_CabinLampOperationStatus_3437_PGN65527 }, {
						"Get_IlluminationOperationStatus_3438_PGN65527", "()I",
						(void*) Get_IlluminationOperationStatus_3438_PGN65527 },
				{ "Get_OutriggerFrontRHOperationStatus_3439_PGN65527", "()I",
						(void*) Get_OutriggerFrontRHOperationStatus_3439_PGN65527 },
				{ "Get_OutriggerFrontLHOperationStatus_3440_PGN65527", "()I",
						(void*) Get_OutriggerFrontLHOperationStatus_3440_PGN65527 },
				{ "Get_OutriggerRearRHOperationStatus_3441_PGN65527", "()I",
						(void*) Get_OutriggerRearRHOperationStatus_3441_PGN65527 },
				{ "Get_OutriggerRearLHOperationStatus_3442_PGN65527", "()I",
						(void*) Get_OutriggerRearLHOperationStatus_3442_PGN65527 },
				{ "Get_OverloadSwitchOperationStatus_3443_PGN65527", "()I",
						(void*) Get_OverloadSwitchOperationStatus_3443_PGN65527 },
				{ "Get_BeaconLampOperationStatus_3444_PGN65527", "()I",
						(void*) Get_BeaconLampOperationStatus_3444_PGN65527 },
				{ "Get_ForwardTravelAlarmOperationStatus_3445_PGN65527", "()I",
						(void*) Get_ForwardTravelAlarmOperationStatus_3445_PGN65527 },
				{ "Get_RearWorkLampOperationStatus_3446_PGN65527", "()I",
						(void*) Get_RearWorkLampOperationStatus_3446_PGN65527 },
				{ "Get_RideControlOperationStatus_3447_PGN65527", "()I",
						(void*) Get_RideControlOperationStatus_3447_PGN65527 },
				{ "Get_QuickCouplerOperationStatus_3448_PGN65527", "()I",
						(void*) Get_QuickCouplerOperationStatus_3448_PGN65527 },
				{ "Get_AutoGreaseOperationStatus_3449_PGN65527", "()I",
						(void*) Get_AutoGreaseOperationStatus_3449_PGN65527 }, {
						"Get_MirrorHeatOperationStatus_3450_PGN65527", "()I",
						(void*) Get_MirrorHeatOperationStatus_3450_PGN65527 }, {
						"Get_RearWiperOperationStatus_3451_PGN65527", "()I",
						(void*) Get_RearWiperOperationStatus_3451_PGN65527 },
				{ "Get_RearWiperWasherOperationStatus_3452_PGN65527", "()I",
						(void*) Get_RearWiperWasherOperationStatus_3452_PGN65527 },
				//////TX_DTC_INFORMATION_REQUEST_61184_11///////
				{ "Set_MessageType_PGN61184_11", "(I)V",
						(void*) Set_MessageType_PGN61184_11 }, {
						"Set_DTCInformationRequest_1515_PGN61184_11", "(I)V",
						(void*) Set_DTCInformationRequest_1515_PGN61184_11 }, {
						"Set_DTCType_1510_PGN61184_11", "(I)V",
						(void*) Set_DTCType_1510_PGN61184_11 },
				{ "Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11",
						"(I)V",
						(void*) Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11 },
				//////TX_MAINTENANCE_REQUSET_61184_12///////
				{ "Set_MessageType_PGN61184_12", "(I)V",
						(void*) Set_MessageType_PGN61184_12 }, {
						"Set_MaintenanceCommant_1097_PGN61184_12", "(I)V",
						(void*) Set_MaintenanceCommant_1097_PGN61184_12 }, {
						"Set_MaintenanceItem_1098_PGN61184_12", "(I)V",
						(void*) Set_MaintenanceItem_1098_PGN61184_12 }, {
						"Set_MaintenanceInterval_1091_PGN61184_12", "(I)V",
						(void*) Set_MaintenanceInterval_1091_PGN61184_12 },
				//////TX_MAINTENANCE_ITEM_LIST_61184_13///////
				{ "Set_MessageType_PGN61184_13", "(I)V",
						(void*) Set_MessageType_PGN61184_13 },
				{ "Set_TotalNumberofMaintenanceItems_1100_PGN61184_13", "(I)V",
						(void*) Set_TotalNumberofMaintenanceItems_1100_PGN61184_13 },
				{ "Set_MaintenanceItem_1098_PGN61184_13", "([B)V",
						Set_MaintenanceItem_1098_PGN61184_13 },
				//////TX_MAINTENANCE_INFORMATION_61184_14///////
				{ "Set_MessageType_PGN61184_14", "(I)V",
						(void*) Set_MessageType_PGN61184_14 }, {
						"Set_MaintenanceItem_1098_PGN61184_14", "(I)V",
						(void*) Set_MaintenanceItem_1098_PGN61184_14 }, {
						"Set_MaintenanceAlarmLamp_1099_PGN61184_14", "(I)V",
						(void*) Set_MaintenanceAlarmLamp_1099_PGN61184_14 }, {
						"Set_MaintenanceEvent_PGN61184_14", "(I)V",
						(void*) Set_MaintenanceEvent_PGN61184_14 }, {
						"Set_MaintenanceInterval_1091_PGN61184_14", "(I)V",
						(void*) Set_MaintenanceInterval_1091_PGN61184_14 }, {
						"Set_MaintenanceTotalCount_1092_PGN61184_14", "(I)V",
						(void*) Set_MaintenanceTotalCount_1092_PGN61184_14 },
				{ "Set_HourmeterattheLastMaintenance_1093_PGN61184_14", "(I)V",
						(void*) Set_HourmeterattheLastMaintenance_1093_PGN61184_14 },
				//////TX_MAINTENANCE_HISTORY_61184_15///////
				{ "Set_MessageType_PGN61184_15", "(I)V",
						(void*) Set_MessageType_PGN61184_15 }, {
						"Set_MaintenanceItem_1098_PGN61184_15", "(I)V",
						(void*) Set_MaintenanceItem_1098_PGN61184_15 },
				{ "Set_HourmeterattheLastMaintenance_1093_PGN61184_15", "(I)V",
						(void*) Set_HourmeterattheLastMaintenance_1093_PGN61184_15 },
				{ "Set_MaintenanceHistory_1094_PGN61184_15", "([B)V",
						Set_MaintenanceHistory_1094_PGN61184_15 },
				//////TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16///////
				{ "Set_MessageType_PGN61184_16", "(I)V",
						(void*) Set_MessageType_PGN61184_16 },
				{
						"Set_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16",
						"(I)V",
						(void*) Set_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16 },
				{ "Set_MaintenanceItem_PGN61184_16", "([B)V",
						Set_MaintenanceItem_PGN61184_16 },
				//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21///////
				{ "Set_MessageType_PGN61184_21", "(I)V",
						(void*) Set_MessageType_PGN61184_21 },
				//////TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22///////
				{ "Set_MessageType_PGN61184_22", "(I)V",
						(void*) Set_MessageType_PGN61184_22 },
				{ "Set_HCEAntiTheftRandomNumber_1632_PGN61184_22", "([B)V",
						Set_HCEAntiTheftRandomNumber_1632_PGN61184_22 },
				//////TX_HCE_ANTI_THEFT_REQUEST_61184_23///////
				{ "Set_MessageType_PGN61184_23", "(I)V",
						(void*) Set_MessageType_PGN61184_23 }, {
						"Set_HCEAntiTheftCommand_1633_PGN61184_23", "(I)V",
						(void*) Set_HCEAntiTheftCommand_1633_PGN61184_23 }, {
						"Set_ESLMode_820_PGN61184_23", "(I)V",
						(void*) Set_ESLMode_820_PGN61184_23 }, {
						"Set_ESLInterval_825_PGN61184_23", "(I)V",
						(void*) Set_ESLInterval_825_PGN61184_23 },
				{ "Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23",
						"([B)V",
						Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23 },
				//////TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24///////
				{ "Set_MessageType_PGN61184_24", "(I)V",
						(void*) Set_MessageType_PGN61184_24 },
				{ "Set_PasswordCertificationResult_956_PGN61184_24", "(I)V",
						(void*) Set_PasswordCertificationResult_956_PGN61184_24 },
				//////TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25///////
				{ "Set_MessageType_PGN61184_25", "(I)V",
						(void*) Set_MessageType_PGN61184_25 }, {
						"Set_PasswordChangeResult_958_PGN61184_25", "(I)V",
						(void*) Set_PasswordChangeResult_958_PGN61184_25 },
				{ "Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25",
						"([B)V",
						Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_25 },
				//////TX_OPERATION_HISTORY_REQUEST_61184_31///////
				{ "Set_MessageType_PGN61184_31", "(I)V",(void*) Set_MessageType_PGN61184_31 },
				{"Set_OperationHistory_1101_PGN61184_31", "(I)V",(void*) Set_OperationHistory_1101_PGN61184_31 },
				//////TX_COOLING_FAN_SETTING_61184_61///////
				{ "Set_MessageType_PGN61184_61", "(I)V",(void*) Set_MessageType_PGN61184_61 },
				{"Set_TestMode_PGN61184_61", "(I)V",(void*) Set_TestMode_PGN61184_61 },
				{"Set_CoolingFanReverseMode_182_PGN61184_61", "(I)V",(void*) Set_CoolingFanReverseMode_182_PGN61184_61 },
				{"Set_CoolingFanReverseManual_PGN61184_61", "(I)V",(void*) Set_CoolingFanReverseManual_PGN61184_61 },
				{"Set_CoolingFanValveCurrent_146_PGN61184_61", "(I)V",(void*) Set_CoolingFanValveCurrent_146_PGN61184_61 },
				{ "Set_CoolingFanReverseIntervalTime_211_PGN61184_61", "(I)V",(void*) Set_CoolingFanReverseIntervalTime_211_PGN61184_61 },
				{ "Set_CoolingFanReverseOperatingTime_212_PGN61184_61", "(I)V",(void*) Set_CoolingFanReverseOperatingTime_212_PGN61184_61 },
				//////TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62///////
				{ "Set_MessageType_PGN61184_62", "(I)V",
						(void*) Set_MessageType_PGN61184_62 },
				{ "Set_WeighingSystemAccumulationMode_1941_PGN61184_62",
						"(I)V",
						(void*) Set_WeighingSystemAccumulationMode_1941_PGN61184_62 },
				{ "Set_RequestReweighing_PGN61184_62", "(I)V",
						(void*) Set_RequestReweighing_PGN61184_62 }, {
						"Set_RequestTotalWorkWeightReset_PGN61184_62", "(I)V",
						(void*) Set_RequestTotalWorkWeightReset_PGN61184_62 },
						{"Set_WeightOffsetSelection_PGN61184_62", "(I)V",(void*) Set_WeightOffsetSelection_PGN61184_62 },
						{"Set_WeightOffsetSetting_PGN61184_62", "(I)V",(void*) Set_WeightOffsetSetting_PGN61184_62 },
						{"Set_WeightOffset_1922_PGN61184_62", "(I)V",(void*) Set_WeightOffset_1922_PGN61184_62 },
						{"Set_WeighingDisplayMode1_1910_PGN61184_62", "(I)V",(void*) Set_WeighingDisplayMode1_1910_PGN61184_62 },

				//////TX_WEIGHT_OFFSET_61184_63///////
				{ "Set_MessageType_PGN61184_63", "(I)V",(void*) Set_MessageType_PGN61184_63 },
				{"Set_WeightOffsetSelectionStatus_PGN61184_63", "(I)V",(void*) Set_WeightOffsetSelectionStatus_PGN61184_63 },
				{"Set_WeightOffsetWorkTool1_1922_PGN61184_63", "(I)V",(void*) Set_WeightOffsetWorkTool1_1922_PGN61184_63 },
				{"Set_WeightOffsetWorkTool2_1922_PGN61184_63", "(I)V",(void*) Set_WeightOffsetWorkTool2_1922_PGN61184_63 },
				{"Set_WeightOffsetWorkTool3_1922_PGN61184_63", "(I)V",(void*) Set_WeightOffsetWorkTool3_1922_PGN61184_63 },
				//////TX_MACHINE_MODE_SETTING_61184_101///////
				{ "Set_MessageType_PGN61184_101", "(I)V",(void*) Set_MessageType_PGN61184_101 },
				{ "Set_EngineAlternateLowIdleSwitch_348_PGN61184_101", "(I)V",(void*) Set_EngineAlternateLowIdleSwitch_348_PGN61184_101 },
				{ "Set_EnginePowerMode_347_PGN61184_101", "(I)V",(void*) Set_EnginePowerMode_347_PGN61184_101 },
				//////TX_TRAVEL_MODE_SETTING_61184_104///////
				{ "Set_MessageType_PGN61184_104", "(I)V",
						(void*) Set_MessageType_PGN61184_104 }, {
						"Set_TransmisstionShiftMode_543_PGN61184_104", "(I)V",
						(void*) Set_TransmisstionShiftMode_543_PGN61184_104 }, {
						"Set_ClutchCutoffMode_544_PGN61184_104", "(I)V",
						(void*) Set_ClutchCutoffMode_544_PGN61184_104 }, {
						"Set_KickDownShiftMode_547_PGN61184_104", "(I)V",
						(void*) Set_KickDownShiftMode_547_PGN61184_104 },
				{ "Set_TransmissionTCLockupEngaged_568_PGN61184_104", "(I)V",
						(void*) Set_TransmissionTCLockupEngaged_568_PGN61184_104 },
				{"Set_VehicleSpeedLimitMode_575_PGN61184_104", "(I)V",(void*) Set_VehicleSpeedLimitMode_575_PGN61184_104 },
				//////TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105///////
				{ "Set_MessageType_PGN61184_105", "(I)V",
						(void*) Set_MessageType_PGN61184_105 }, {
						"Set_SettingSelection_PGN61184_105", "(I)V",
						(void*) Set_SettingSelection_PGN61184_105 }, {
						"Set_SpeedometerFrequency_534_PGN61184_105", "(I)V",
						(void*) Set_SpeedometerFrequency_534_PGN61184_105 },
				{ "Set_AutoRideControlOperationSpeedForward_574_PGN61184_105", "(I)V",(void*) Set_AutoRideControlOperationSpeedForward_574_PGN61184_105 },
				{ "Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105", "(I)V",(void*) Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105 },
				{ "Set_VehicleSpeedLimit_572_PGN61184_105", "(I)V",
						(void*) Set_VehicleSpeedLimit_572_PGN61184_105 },
				//////TX_TRAVEL_CONTROL_VALUE_61184_106///////
				{ "Set_MessageType_PGN61184_106", "(I)V",
						(void*) Set_MessageType_PGN61184_106 }, {
						"Set_SpeedometerFrequency_534_PGN61184_106", "(I)V",
						(void*) Set_SpeedometerFrequency_534_PGN61184_106 },
				{ "Set_AutoRideControlOperationSpeedForward_574_PGN61184_106", "(I)V",(void*) Set_AutoRideControlOperationSpeedForward_574_PGN61184_106 },
				{ "Set_AutoRideControlOperationSpeedBackward_576_PGN61184_106", "(I)V",(void*) Set_AutoRideControlOperationSpeedBackward_576_PGN61184_106 },
				{ "Set_VehicleSpeedLimit_572_PGN61184_106", "(I)V",
						(void*) Set_VehicleSpeedLimit_572_PGN61184_106 },
				//////TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109///////
				{ "Set_MessageType_PGN61184_109", "(I)V",
						(void*) Set_MessageType_PGN61184_109 },
				{ "Set_BacklightIlluminationLevel_719_PGN61184_109", "(I)V",
						(void*) Set_BacklightIlluminationLevel_719_PGN61184_109 },
				{ "Set_WiperSpeedLevel_718_PGN61184_109", "(I)V",
						(void*) Set_WiperSpeedLevel_718_PGN61184_109 }, {
						"Set_Clock_819_PGN61184_109", "(I)V",
						(void*) Set_Clock_819_PGN61184_109 }, {
						"Set_RequestEngineLowIdleSpeed_PGN61184_109", "(I)V",
						(void*) Set_RequestEngineLowIdleSpeed_PGN61184_109 }, {
						"Set_RequestTripDataReset_PGN61184_109", "(I)V",
						(void*) Set_RequestTripDataReset_PGN61184_109 },
				//////TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121///////
				{"Set_MessageType_PGN61184_121", "(I)V",(void*) Set_MessageType_PGN61184_121 },
				{"Set_AutomaticEngineShutdown_363_PGN61184_121", "(I)V",(void*) Set_AutomaticEngineShutdown_363_PGN61184_121 },
				{"Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121", "(I)V",(void*) Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121 },
				{ "Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121","(I)V",(void*) Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121 },
				{ "Set_EngineShutdownCotrolByte_PGN61184_121","(I)V",(void*) Set_EngineShutdownCotrolByte_PGN61184_121 },
				{ "Set_DelayedEngineShutdown_365_PGN61184_121", "(I)V",(void*) Set_DelayedEngineShutdown_365_PGN61184_121 },
				{ "Set_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121","(I)V",(void*) Set_SettingTimeofrDelayedEngineShutdown_366_PGN61184_121 },
				//////TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122///////
				{ "Set_MessageType_PGN61184_122", "(I)V",
						(void*) Set_MessageType_PGN61184_122 },
				{ "Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122",
						"(I)V",
						(void*) Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122 },
				{ "Set_RemainingTimeforAutomaticEngineShutdown_PGN61184_122",
						"(I)V",
						(void*) Set_RemainingTimeforAutomaticEngineShutdown_PGN61184_122 },
				{ "Set_SettingTimeforDelayedEngineShutdown_366_PGN61184_122",
						"(I)V",
						(void*) Set_SettingTimeforDelayedEngineShutdown_366_PGN61184_122 },
				{ "Set_RemainingTimeforDelayedEngineShutdown_PGN61184_122",
						"(I)V",
						(void*) Set_RemainingTimeforDelayedEngineShutdown_PGN61184_122 },
				//////TX_DETENT_MODE_SETTING_61184_123///////
				{ "Set_MessageType_PGN61184_123", "(I)V",
						(void*) Set_MessageType_PGN61184_123 }, {
						"Set_BoomDetentMode_223_PGN61184_123", "(I)V",
						(void*) Set_BoomDetentMode_223_PGN61184_123 }, {
						"Set_BucketDetentMode_224_PGN61184_123", "(I)V",
						(void*) Set_BucketDetentMode_224_PGN61184_123 },
				{ "Set_RequestDetentReleasePositionSetting_PGN61184_123",
						"(I)V",
						(void*) Set_RequestDetentReleasePositionSetting_PGN61184_123 },
				//////TX_DETENT_MODE_STATUS_61184_124///////
				{ "Set_MessageType_PGN61184_124", "(I)V",
						(void*) Set_MessageType_PGN61184_124 }, {
						"Set_BoomDetentMode_223_PGN61184_124", "(I)V",
						(void*) Set_BoomDetentMode_223_PGN61184_124 }, {
						"Set_BucketDetentMode_224_PGN61184_124", "(I)V",
						(void*) Set_BucketDetentMode_224_PGN61184_124 },
				{ "Set_DetentReleasePositionSettingStatus_PGN61184_124", "(I)V",
						(void*) Set_DetentReleasePositionSettingStatus_PGN61184_124 },
				//////TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129///////
				{ "Set_MessageType_PGN61184_129", "(I)V",
						(void*) Set_MessageType_PGN61184_129 }, {
						"Set_EngineShutdownCommand_3472_PGN61184_129", "(I)V",
						(void*) Set_EngineShutdownCommand_3472_PGN61184_129 },
				//////TX_AS_PHONE_NUMBER_SETTING_61184_151///////
				{ "Set_MessageType_PGN61184_151", "(I)V",
						(void*) Set_MessageType_PGN61184_151 }, {
						"Set_ASPhoneNumber_PGN61184_151", "([B)V",
						Set_ASPhoneNumber_PGN61184_151 },
				//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201///////
				{ "Set_MessageType_PGN61184_201", "(I)V",
						(void*) Set_MessageType_PGN61184_201 },
				{ "Set_RequestBoomPressureCalibration_PGN61184_201", "(I)V",
						(void*) Set_RequestBoomPressureCalibration_PGN61184_201 },
				{ "Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201",
						"(I)V",
						(void*) Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201 },
				{ "Set_RequestAEB_PGN61184_201", "(I)V",
						(void*) Set_RequestAEB_PGN61184_201 },
				{ "Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201",
						"(I)V",
						(void*) Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201 },
				//////TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202///////
				{ "Set_MessageType_PGN61184_202", "(I)V",
						(void*) Set_MessageType_PGN61184_202 },
				{ "Set_BoomPressureCalibrationStatus_1908_PGN61184_202", "(I)V",
						(void*) Set_BoomPressureCalibrationStatus_1908_PGN61184_202 },
				{ "Set_AngleSensorCalibrationStatus_1909_PGN61184_202", "(I)V",
						(void*) Set_AngleSensorCalibrationStatus_1909_PGN61184_202 },
				{ "Set_AEBStatusInformation_MainCode_562_PGN61184_202", "(I)V",
						(void*) Set_AEBStatusInformation_MainCode_562_PGN61184_202 },
				{ "Set_AEBCycleNumber_540_PGN61184_202", "(I)V",
						(void*) Set_AEBCycleNumber_540_PGN61184_202 },
				{ "Set_AEBStatusInformation_SubCode_563_PGN61184_202", "(I)V",
						(void*) Set_AEBStatusInformation_SubCode_563_PGN61184_202 },
				{
						"Set_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202",
						"(I)V",
						(void*) Set_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202 },
				{
						"Set_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202",
						"(I)V",
						(void*) Set_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202 },
				//////TX_WHEEL_LOADER_EHCU_SETTING_61184_203///////
				{ "Set_MessageType_PGN61184_203", "(I)V",
						(void*) Set_MessageType_PGN61184_203 }, {
						"Set_BucketPriorityOperation_2301_PGN61184_203", "(I)V",
						(void*) Set_BucketPriorityOperation_2301_PGN61184_203 },
				{ "Set_FlowFineModulationOperation_2302_PGN61184_203", "(I)V",
						(void*) Set_FlowFineModulationOperation_2302_PGN61184_203 },
				{ "Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203", "(I)V",(void*) Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203 },
				{ "Set_BoomLeverFloatingPosition_PGN61184_203", "(I)V",(void*) Set_BoomLeverFloatingPosition_PGN61184_203 },
				{ "Set_SoftStopBoomUp_2337_PGN61184_203", "(I)V",(void*) Set_SoftStopBoomUp_2337_PGN61184_203 },
				{ "Set_SoftStopBoomDown_2338_PGN61184_203", "(I)V",(void*) Set_SoftStopBoomDown_2338_PGN61184_203 },
				{ "Set_SoftStopBucketIn_2339_PGN61184_203", "(I)V",(void*) Set_SoftStopBucketIn_2339_PGN61184_203 },
				{ "Set_SoftStopBucketOut_2340_PGN61184_203", "(I)V",(void*) Set_SoftStopBucketOut_2340_PGN61184_203 },
				{ "Set_BoomDownSpeedAdjust_PGN61184_203", "(I)V",(void*) Set_BoomDownSpeedAdjust_PGN61184_203 },
				{ "Set_BucketOutSpeedAdjust_PGN61184_203", "(I)V",(void*) Set_BucketOutSpeedAdjust_PGN61184_203 },

				//////TX_MONIOTR_STATUS_65327///////
						{"Set_RequestBuzzerStop_PGN65327", "(I)V",(void*) Set_RequestBuzzerStop_PGN65327 },
						{"Set_SpeedmeterUnitChange_PGN65327", "(I)V",(void*) Set_SpeedmeterUnitChange_PGN65327 },
						{"Set_MonitorScreenNumber_836_PGN65327", "(I)V",(void*) Set_MonitorScreenNumber_836_PGN65327 },
				//////TX_RMCU_STATUS_65329///////
				{ "Set_RMCUNetworkType_1621_PGN65329", "(I)V",
						(void*) Set_RMCUNetworkType_1621_PGN65329 }, {
						"Set_RMCUBackupBatteryVoltage_1590_PGN65329", "(I)V",
						(void*) Set_RMCUBackupBatteryVoltage_1590_PGN65329 }, {
						"Set_RMCUPowerSource_1594_PGN65329", "(I)V",
						(void*) Set_RMCUPowerSource_1594_PGN65329 }, {
						"Set_RMCUBoxOpeningStatus_PGN65329", "(I)V",
						(void*) Set_RMCUBoxOpeningStatus_PGN65329 }, {
						"Set_NetworkCommunicationStatus1_1622_PGN65329", "(I)V",
						(void*) Set_NetworkCommunicationStatus1_1622_PGN65329 },
				{ "Set_PositionUpdateStatus_852_PGN65329", "(I)V",
						(void*) Set_PositionUpdateStatus_852_PGN65329 }, {
						"Set_MachinePositionStatus_1593_PGN65329", "(I)V",
						(void*) Set_MachinePositionStatus_1593_PGN65329 },
				{ "Set_GPSAntennaConnectionAlarmStatus_1595_PGN65329", "(I)V",
						(void*) Set_GPSAntennaConnectionAlarmStatus_1595_PGN65329 },
				{ "Set_NetworkTransceiverStatus1_1623_PGN65329", "(I)V",
						(void*) Set_NetworkTransceiverStatus1_1623_PGN65329 }, {
						"Set_NetworkServiceStatus1_1624_PGN65329", "(I)V",
						(void*) Set_NetworkServiceStatus1_1624_PGN65329 }, {
						"Set_NetworkAntennaStatus1_1625_PGN65329", "(I)V",
						(void*) Set_NetworkAntennaStatus1_1625_PGN65329 },
				{ "Set_RMCUData_NumberofMessagestoTransmit_855_PGN65329",
						"(I)V",
						(void*) Set_RMCUData_NumberofMessagestoTransmit_855_PGN65329 },
				//////TX_COMPONENT_IDENTIFICATION_65330///////
				{ "Set_ComponentCode_1699_PGN65330", "(I)V",
						(void*) Set_ComponentCode_1699_PGN65330 }, {
						"Set_ManufacturerCode_1700_PGN65330", "(I)V",
						(void*) Set_ManufacturerCode_1700_PGN65330 }, {
						"Set_ComponentBasicInformation_1698_PGN65330", "([B)V",
						Set_ComponentBasicInformation_1698_PGN65330 },
				//////TX_COMPONENT_IDENTIFICATION_ECM_65330///////
				{ "Set_ComponentCode_1699_PGN65330_ECM", "(I)V",
						(void*) Set_ComponentCode_1699_PGN65330_ECM }, {
						"Set_ManufacturerCode_1700_PGN65330_ECM", "(I)V",
						(void*) Set_ManufacturerCode_1700_PGN65330_ECM },
				{ "Set_ComponentBasicInformation_1698_PGN65330_ECM", "([B)V",
						Set_ComponentBasicInformation_1698_PGN65330_ECM },
				//////TX_COMPONENT_IDENTIFICATION_TCU_65330///////
				{ "Set_ComponentCode_1699_PGN65330_TCU", "(I)V",
						(void*) Set_ComponentCode_1699_PGN65330_TCU }, {
						"Set_ManufacturerCode_1700_PGN65330_TCU", "(I)V",
						(void*) Set_ManufacturerCode_1700_PGN65330_TCU },
				{ "Set_ComponentBasicInformation_1698_PGN65330_TCU", "([B)V",
						Set_ComponentBasicInformation_1698_PGN65330_TCU },
				//////TX_COMPONENT_IDENTIFICATION_MONITOR_65330///////
				{ "Set_ComponentCode_1699_PGN65330_MONITOR", "(I)V",
						(void*) Set_ComponentCode_1699_PGN65330_MONITOR }, {
						"Set_ManufacturerCode_1700_PGN65330_MONITOR", "(I)V",
						(void*) Set_ManufacturerCode_1700_PGN65330_MONITOR },
				{ "Set_ComponentBasicInformation_1698_PGN65330_MONITOR", "([B)V",
						Set_ComponentBasicInformation_1698_PGN65330_MONITOR },
				//////TX_TRIP_TIME_INFORMATION_65344///////
				{ "Set_TripTime_849_PGN65344", "(I)V",
						(void*) Set_TripTime_849_PGN65344 },
				//////TX_MACHINE_SECURITY_STATUS_65348///////
				{ "Set_ESLMode_820_PGN65348", "(I)V",
						(void*) Set_ESLMode_820_PGN65348 }, {
						"Set_LockMode_822_PGN65348", "(I)V",
						(void*) Set_LockMode_822_PGN65348 }, {
						"Set_LockLevel_823_PGN65348", "(I)V",
						(void*) Set_LockLevel_823_PGN65348 }, {
						"Set_LockDemander_824_PGN65348", "(I)V",
						(void*) Set_LockDemander_824_PGN65348 }, {
						"Set_EngineStartingPermission_821_PGN65348", "(I)V",
						(void*) Set_EngineStartingPermission_821_PGN65348 },
				{ "Set_PasswordIdentificationBlockedState_1631_PGN65348",
						"(I)V",
						(void*) Set_PasswordIdentificationBlockedState_1631_PGN65348 },
				{ "Set_ESLInterval_825_PGN65348", "(I)V",
						(void*) Set_ESLInterval_825_PGN65348 },
				//////TX_MACHINE_MODE_STATUS_65350///////
				{ "Set_EngineAlternateLowIdelSwitch_348_PGN65350", "(I)V",
						(void*) Set_EngineAlternateLowIdelSwitch_348_PGN65350 },
				{ "Set_EnginePowerMode_347_PGN65350", "(I)V",
						(void*) Set_EnginePowerMode_347_PGN65350 },
				//////TX_HYDRAULIC_PRESSURE4_65354///////
				{ "Set_BrakeOilPressure_503_PGN65354", "(I)V",
						(void*) Set_BrakeOilPressure_503_PGN65354 },
				{ "Set_BrakeOilChargingPriorityPressure_557_PGN65354", "(I)V",
						(void*) Set_BrakeOilChargingPriorityPressure_557_PGN65354 },
				//////TX_HYDRAULIC_PRESSURE6_65356///////
				{ "Set_BoomCylinderHeadPressure_204_PGN65356", "(I)V",
						(void*) Set_BoomCylinderHeadPressure_204_PGN65356 }, {
						"Set_BoomCylinderRodPressure_205_PGN65356", "(I)V",
						(void*) Set_BoomCylinderRodPressure_205_PGN65356 },
				//////TX_HYDRAULIC_PRESSURE7_65357///////
				{ "Set_SteeringMainPumpPressure_202_PGN65357", "(I)V",
						(void*) Set_SteeringMainPumpPressure_202_PGN65357 },
				{ "Set_EmergencySteeringPumpPressure_203_PGN65357", "(I)V",
						(void*) Set_EmergencySteeringPumpPressure_203_PGN65357 },
				{ "Set_ParkingOilPressure_507_PGN65357", "(I)V",
						(void*) Set_ParkingOilPressure_507_PGN65357 }, {
						"Set_DifferentialLockPressure_558_PGN65357", "(I)V",
						(void*) Set_DifferentialLockPressure_558_PGN65357 },
				//////TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360///////
				{ "Set_AlternatorVoltage_707_PGN65360", "(I)V",
						(void*) Set_AlternatorVoltage_707_PGN65360 },
				//////TX_RELAY_BUZZER_STATUS_65364///////
				{ "Set_AntiRestartRelay_327_PGN65364", "(I)V",
						(void*) Set_AntiRestartRelay_327_PGN65364 }, {
						"Set_FuelWarmerRelay_325_PGN65364", "(I)V",
						(void*) Set_FuelWarmerRelay_325_PGN65364 }, {
						"Set_EnginePreheatRelay_322_PGN65364", "(I)V",
						(void*) Set_EnginePreheatRelay_322_PGN65364 }, {
						"Set_EngineStopRelay_345_PGN65364", "(I)V",
						(void*) Set_EngineStopRelay_345_PGN65364 }, {
						"Set_WorkingCutoffRelay_164_PGN65364", "(I)V",
						(void*) Set_WorkingCutoffRelay_164_PGN65364 }, {
						"Set_TravelingCutoffRelay_517_PGN65364", "(I)V",
						(void*) Set_TravelingCutoffRelay_517_PGN65364 }, {
						"Set_ParkingRelay_514_PGN65364", "(I)V",
						(void*) Set_ParkingRelay_514_PGN65364 }, {
						"Set_WiperRelay_727_PGN65364", "(I)V",
						(void*) Set_WiperRelay_727_PGN65364 }, {
						"Set_EmergencySteeringPumpRelay_187_PGN65364", "(I)V",
						(void*) Set_EmergencySteeringPumpRelay_187_PGN65364 }, {
						"Set_TravelAlarmBuzzer_722_PGN65364", "(I)V",
						(void*) Set_TravelAlarmBuzzer_722_PGN65364 }, {
						"Set_Buzzer_723_PGN65364", "(I)V",
						(void*) Set_Buzzer_723_PGN65364 }, {
						"Set_FuelCutoffRelay_324_PGN65364", "(I)V",
						(void*) Set_FuelCutoffRelay_324_PGN65364 },
				//////TX_SOLENOID_STATUS_65365///////
				{ "Set_BoomUpLeverDetentSolenoid_172_PGN65365", "(I)V",
						(void*) Set_BoomUpLeverDetentSolenoid_172_PGN65365 }, {
						"Set_BoomDownLeverDetentSolenoid_173_PGN65365", "(I)V",
						(void*) Set_BoomDownLeverDetentSolenoid_173_PGN65365 },
				{ "Set_BucketLeverDetentSolenoid_174_PGN65365", "(I)V",
						(void*) Set_BucketLeverDetentSolenoid_174_PGN65365 },
				//////TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368///////
				{ "Set_AcceleratorPedalPositionVoltage1_710_PGN65368", "(I)V",
						(void*) Set_AcceleratorPedalPositionVoltage1_710_PGN65368 },
				{ "Set_BrakePedalPositionVoltage_573_PGN65368", "(I)V",
						(void*) Set_BrakePedalPositionVoltage_573_PGN65368 }, {
						"Set_AcceleratorPedalPosition1_339_PGN65368", "(I)V",
						(void*) Set_AcceleratorPedalPosition1_339_PGN65368 },
				{ "Set_AcceleratorPedalPositionVoltage2_710_PGN65368", "(I)V",
						(void*) Set_AcceleratorPedalPositionVoltage2_710_PGN65368 },
				//////TX_COOLING_FAN_STATUS_65369///////
				{ "Set_CoolingFanReverseMode_182_PGN65369", "(I)V",
						(void*) Set_CoolingFanReverseMode_182_PGN65369 }, {
						"Set_CoolingFanReverseSolenoid_181_PGN65369", "(I)V",
						(void*) Set_CoolingFanReverseSolenoid_181_PGN65369 },
				{ "Set_CoolingFanReverseSwitchManual_740_PGN65369", "(I)V",
						(void*) Set_CoolingFanReverseSwitchManual_740_PGN65369 },
				{ "Set_CoolingFanReverseSwitchAuto_741_PGN65369", "(I)V",
						(void*) Set_CoolingFanReverseSwitchAuto_741_PGN65369 },
				{ "Set_CoolingFanValveCurrent_146_PGN65369", "(I)V",
						(void*) Set_CoolingFanValveCurrent_146_PGN65369 }, {
						"Set_CoolingFanSpeed_318_PGN65369", "(I)V",
						(void*) Set_CoolingFanSpeed_318_PGN65369 },
				{ "Set_CoolingFanReverseIntervalTime_211_PGN65369", "(I)V",
						(void*) Set_CoolingFanReverseIntervalTime_211_PGN65369 },
				{ "Set_CoolingFanReverseOperatingTime_212_PGN65369", "(I)V",
						(void*) Set_CoolingFanReverseOperatingTime_212_PGN65369 },
				//////TX_ENGINE_STATUS1_65371///////
				{ "Set_EngineOperatingCondition_336_PGN65371", "(I)V",
						(void*) Set_EngineOperatingCondition_336_PGN65371 }, {
						"Set_DEFTankLevel_362_PGN65371", "(I)V",
						(void*) Set_DEFTankLevel_362_PGN65371 },
				//////TX_VEHICLE_DISTANCE_65389///////
				{ "Set_TripDistance_600_PGN65389", "(I)V",
						(void*) Set_TripDistance_600_PGN65389 }, {
						"Set_TotalVehicleDistance_601_PGN65389", "(I)V",
						(void*) Set_TotalVehicleDistance_601_PGN65389 },
				//////TX_CYLINDER_ANGLE_STROKE1_65395///////
				{ "Set_BoomLinkAngle_1920_PGN65395", "(I)V",
						(void*) Set_BoomLinkAngle_1920_PGN65395 }, {
						"Set_BellCrankAngle_1921_PGN65395", "(I)V",
						(void*) Set_BellCrankAngle_1921_PGN65395 },
				{ "Set_BoomLinkAngleSensorSignalVoltage_728_PGN65395", "(I)V",
						(void*) Set_BoomLinkAngleSensorSignalVoltage_728_PGN65395 },
				{ "Set_BellCrankAngleSensorSignalVoltage_729_PGN65395", "(I)V",
						(void*) Set_BellCrankAngleSensorSignalVoltage_729_PGN65395 },
				{ "Set_BucketCylinderStroke_1930_PGN65395", "(I)V",
						(void*) Set_BucketCylinderStroke_1930_PGN65395 },
				//////TX_AS_PHONE_NUMBER_65425///////
				{ "Set_ASPhoneNumber_1095_PGN65425", "([B)V",
						Set_ASPhoneNumber_1095_PGN65425 },
				//////TX_WARNING_LAMP_65427///////
				{ "Set_FuelLevelLow_303_PGN65427", "(I)V",
						(void*) Set_FuelLevelLow_303_PGN65427 }, {
						"Set_HydraulicOilTemperatureHigh_102_PGN65427", "(I)V",
						(void*) Set_HydraulicOilTemperatureHigh_102_PGN65427 },
				{ "Set_BatteryVoltageLow_706_PGN65427", "(I)V",
						(void*) Set_BatteryVoltageLow_706_PGN65427 }, {
						"Set_Overload_104_PGN65427", "(I)V",
						(void*) Set_Overload_104_PGN65427 }, {
						"Set_AirCleanerClog_317_PGN65427", "(I)V",
						(void*) Set_AirCleanerClog_317_PGN65427 }, {
						"Set_EngineCheckLamp_320_PGN65427", "(I)V",
						(void*) Set_EngineCheckLamp_320_PGN65427 }, {
						"Set_TransmissionOilPressureLow_502_PGN65427", "(I)V",
						(void*) Set_TransmissionOilPressureLow_502_PGN65427 }, {
						"Set_BrakeOilPressureLow_504_PGN65427", "(I)V",
						(void*) Set_BrakeOilPressureLow_504_PGN65427 }, {
						"Set_EngineCoolantTemperatureHigh_305_PGN65427", "(I)V",
						(void*) Set_EngineCoolantTemperatureHigh_305_PGN65427 },
				{ "Set_EngineCoolantLevelLow_307_PGN65427", "(I)V",
						(void*) Set_EngineCoolantLevelLow_307_PGN65427 }, {
						"Set_EngineOilPressureLow_313_PGN65427", "(I)V",
						(void*) Set_EngineOilPressureLow_313_PGN65427 }, {
						"Set_EngineOilFilterClog_315_PGN65427", "(I)V",
						(void*) Set_EngineOilFilterClog_315_PGN65427 },
				{ "Set_TransmissionOilTemperatureHigh_537_PGN65427", "(I)V",
						(void*) Set_TransmissionOilTemperatureHigh_537_PGN65427 },
				{ "Set_TransmissionCheck_538_PGN65427", "(I)V",
						(void*) Set_TransmissionCheck_538_PGN65427 }, {
						"Set_SteeringMainPumpPressureLow_184_PGN65427", "(I)V",
						(void*) Set_SteeringMainPumpPressureLow_184_PGN65427 },
				{ "Set_EmergencySteeringActive_185_PGN65427", "(I)V",
						(void*) Set_EmergencySteeringActive_185_PGN65427 }, {
						"Set_WarningSymbolLamp_709_PGN65427", "(I)V",
						(void*) Set_WarningSymbolLamp_709_PGN65427 }, {
						"Set_WaterInFuelIndicator_360_PGN65427", "(I)V",
						(void*) Set_WaterInFuelIndicator_360_PGN65427 }, {
						"Set_DTCAlarmLamp_1509_PGN65427", "(I)V",
						(void*) Set_DTCAlarmLamp_1509_PGN65427 }, {
						"Set_EngineStopLamp_319_PGN65427", "(I)V",
						(void*) Set_EngineStopLamp_319_PGN65427 }, {
						"Set_DPFLampCommand_352_PGN65427", "(I)V",
						(void*) Set_DPFLampCommand_352_PGN65427 }, {
						"Set_DEFLowLamp_358_PGN65427", "(I)V",
						(void*) Set_DEFLowLamp_358_PGN65427 }, {
						"Set_ClutchSlip_569_PGN65427", "(I)V",
						(void*) Set_ClutchSlip_569_PGN65427 }, {
						"Set_BrakeOilLevelLow_566_PGN65427", "(I)V",
						(void*) Set_BrakeOilLevelLow_566_PGN65427 }, {
						"Set_BrakeOilTemperatureHigh_567_PGN65427", "(I)V",
						(void*) Set_BrakeOilTemperatureHigh_567_PGN65427 }, {
						"Set_EmissionSystemFailLamp_357_PGN65427", "(I)V",
						(void*) Set_EmissionSystemFailLamp_357_PGN65427 },
				//////TX_INDICATOR_LAMP_65428///////
				{ "Set_PowerMaxStatus_802_PGN65428", "(I)V",
						(void*) Set_PowerMaxStatus_802_PGN65428 }, {
						"Set_DecelerationStatus_803_PGN65428", "(I)V",
						(void*) Set_DecelerationStatus_803_PGN65428 }, {
						"Set_WarmingUpStatus_804_PGN65428", "(I)V",
						(void*) Set_WarmingUpStatus_804_PGN65428 }, {
						"Set_EnginePreheatStatus_323_PGN65428", "(I)V",
						(void*) Set_EnginePreheatStatus_323_PGN65428 }, {
						"Set_FuelWarmerActiveStatus_326_PGN65428", "(I)V",
						(void*) Set_FuelWarmerActiveStatus_326_PGN65428 }, {
						"Set_CoolingFandrivingStatus_180_PGN65428", "(I)V",
						(void*) Set_CoolingFandrivingStatus_180_PGN65428 }, {
						"Set_CruiseStatus_519_PGN65428", "(I)V",
						(void*) Set_CruiseStatus_519_PGN65428 }, {
						"Set_ParkingStatus_508_PGN65428", "(I)V",
						(void*) Set_ParkingStatus_508_PGN65428 }, {
						"Set_ClutchCutoffStatus_545_PGN65428", "(I)V",
						(void*) Set_ClutchCutoffStatus_545_PGN65428 }, {
						"Set_FNRJoystickActiveStatus_546_PGN65428", "(I)V",
						(void*) Set_FNRJoystickActiveStatus_546_PGN65428 }, {
						"Set_RideControlStatus_550_PGN65428", "(I)V",
						(void*) Set_RideControlStatus_550_PGN65428 }, {
						"Set_DifferentialLockStatus_559_PGN65428", "(I)V",
						(void*) Set_DifferentialLockStatus_559_PGN65428 }, {
						"Set_JoystickSteeringActiveStatus_186_PGN65428", "(I)V",
						(void*) Set_JoystickSteeringActiveStatus_186_PGN65428 },
				{ "Set_InchingStatus_549_PGN65428", "(I)V",
						(void*) Set_InchingStatus_549_PGN65428 }, {
						"Set_RamLockStatus_520_PGN65428", "(I)V",
						(void*) Set_RamLockStatus_520_PGN65428 }, {
						"Set_WorkingBrakeStatus_506_PGN65428", "(I)V",
						(void*) Set_WorkingBrakeStatus_506_PGN65428 }, {
						"Set_MaintenanceAlarmLamp_1099_PGN65428", "(I)V",
						(void*) Set_MaintenanceAlarmLamp_1099_PGN65428 }, {
						"Set_OperatorAbsenceStatus_832_PGN65428", "(I)V",
						(void*) Set_OperatorAbsenceStatus_832_PGN65428 }, {
						"Set_MirrorHeaterStatus_724_PGN65428", "(I)V",
						(void*) Set_MirrorHeaterStatus_724_PGN65428 }, {
						"Set_HighBeamStatus_725_PGN65428", "(I)V",
						(void*) Set_HighBeamStatus_725_PGN65428 }, {
						"Set_TravelAlarmSwitch_721_PGN65428", "(I)V",
						(void*) Set_TravelAlarmSwitch_721_PGN65428 }, {
						"Set_SwingBoomSwitch_726_PGN65428", "(I)V",
						(void*) Set_SwingBoomSwitch_726_PGN65428 }, {
						"Set_ParkingWorkingTravelStatus_510_PGN65428", "(I)V",
						(void*) Set_ParkingWorkingTravelStatus_510_PGN65428 }, {
						"Set_TransmissionTCLockupEngaged_556_PGN65428", "(I)V",
						(void*) Set_TransmissionTCLockupEngaged_556_PGN65428 },
				{ "Set_ExhaustSystemHighTemperatureLampCommand_353_PGN65428",
						"(I)V",
						(void*) Set_ExhaustSystemHighTemperatureLampCommand_353_PGN65428 },
				{
						"Set_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428",
						"(I)V",
						(void*) Set_DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354_PGN65428 },
				{ "Set_AutomaticEngineShutdown_363_PGN65428", "(I)V",
						(void*) Set_AutomaticEngineShutdown_363_PGN65428 }, {
						"Set_DelayedEngineShutdown_365_PGN65428", "(I)V",
						(void*) Set_DelayedEngineShutdown_365_PGN65428 },
				//////TX_GAUGE_65431///////
				{ "Set_FuelLevel_301_PGN65431", "(I)V",
						(void*) Set_FuelLevel_301_PGN65431 }, {
						"Set_EngineCoolantTemperature_304_PGN65431", "(I)V",
						(void*) Set_EngineCoolantTemperature_304_PGN65431 }, {
						"Set_HydraulicOilTemperature_101_PGN65431", "(I)V",
						(void*) Set_HydraulicOilTemperature_101_PGN65431 }, {
						"Set_TransmissionOilTemperature_536_PGN65431", "(I)V",
						(void*) Set_TransmissionOilTemperature_536_PGN65431 }, {
						"Set_EngineSpeed_310_PGN65431", "(I)V",
						(void*) Set_EngineSpeed_310_PGN65431 }, {
						"Set_BatteryVoltage_705_PGN65431", "(I)V",
						(void*) Set_BatteryVoltage_705_PGN65431 },
				//////TX_HOURMETER_CLOCK_WIPER_65433///////
				{ "Set_Hourmeter_1601_PGN65433", "(I)V",
						(void*) Set_Hourmeter_1601_PGN65433 }, {
						"Set_Clock_819_PGN65433", "(I)V",
						(void*) Set_Clock_819_PGN65433 }, {
						"Set_HourmeterActiveStatus_703_PGN65433", "(I)V",
						(void*) Set_HourmeterActiveStatus_703_PGN65433 }, {
						"Set_WiperOperationStatus_717_PGN65433", "(I)V",
						(void*) Set_WiperOperationStatus_717_PGN65433 }, {
						"Set_WiperSpeedLevel_718_PGN65433", "(I)V",
						(void*) Set_WiperSpeedLevel_718_PGN65433 }, {
						"Set_BacklightIlluminationLevel_719_PGN65433", "(I)V",
						(void*) Set_BacklightIlluminationLevel_719_PGN65433 }, {
						"Set_IlluminationStatus_737_PGN65433", "(I)V",
						(void*) Set_IlluminationStatus_737_PGN65433 },
				//////TX_MACHINE_TRAVEL_STATUS_65434///////
				{ "Set_WheelBasedVehicleSpeed_532_PGN65434", "(I)V",
						(void*) Set_WheelBasedVehicleSpeed_532_PGN65434 }, {
						"Set_SelectGear_541_PGN65434", "(I)V",
						(void*) Set_SelectGear_541_PGN65434 }, {
						"Set_ActualGear_542_PGN65434", "(I)V",
						(void*) Set_ActualGear_542_PGN65434 }, {
						"Set_TransmissionShiftMode_543_PGN65434", "(I)V",
						(void*) Set_TransmissionShiftMode_543_PGN65434 }, {
						"Set_ClutchCutoffMode_544_PGN65434", "(I)V",
						(void*) Set_ClutchCutoffMode_544_PGN65434 }, {
						"Set_KickDownShiftMode_547_PGN65434", "(I)V",
						(void*) Set_KickDownShiftMode_547_PGN65434 }, {
						"Set_TransmissionTCLockupEngaged_568_PGN65434", "(I)V",
						(void*) Set_TransmissionTCLockupEngaged_568_PGN65434 },
				{ "Set_DifferentialLockMode_570_PGN65434", "(I)V",
						(void*) Set_DifferentialLockMode_570_PGN65434 }, {
						"Set_VehicleSpeedLimitMode_575_PGN65434", "(I)V",
						(void*) Set_VehicleSpeedLimitMode_575_PGN65434 },
				//////TX_DTC_INFORMATION_TYPE1_65438///////
				{ "Set_DTCType_1510_PGN65438", "(I)V",
						(void*) Set_DTCType_1510_PGN65438 }, {
						"Set_NumberofDTCinThisPacket_1514_PGN65438", "(I)V",
						(void*) Set_NumberofDTCinThisPacket_1514_PGN65438 }, {
						"Set_TotalNumberofDTC_PGN65438", "(I)V",
						(void*) Set_TotalNumberofDTC_PGN65438 },
				{ "Set_TotalNumberofDTCInformationPacket_1512_PGN65438", "(I)V",
						(void*) Set_TotalNumberofDTCInformationPacket_1512_PGN65438 },
				{ "Set_SequenceNumberofDTCInformationPacket_1513_PGN65438",
						"(I)V",
						(void*) Set_SequenceNumberofDTCInformationPacket_1513_PGN65438 },
				{ "Set_DTC_1_PGN65438", "([B)V", Set_DTC_1_PGN65438 }, {
						"Set_DTC_2_PGN65438", "([B)V", Set_DTC_2_PGN65438 }, {
						"Set_DTC_3_PGN65438", "([B)V", Set_DTC_3_PGN65438 }, {
						"Set_DTC_4_PGN65438", "([B)V", Set_DTC_4_PGN65438 }, {
						"Set_DTC_5_PGN65438", "([B)V", Set_DTC_5_PGN65438 },
				//////TX_WEIGHING_SYSTEM_STATUS_65450///////
				{ "Set_WeighingSystemAccumulationMode_1941_PGN65450",
						"(I)V",
						(void*) Set_WeighingSystemAccumulationMode_1941_PGN65450 },
				{ "Set_WeighingSystemBuzzer_1907_PGN65450", "(I)V",(void*) Set_WeighingSystemBuzzer_1907_PGN65450 },
				{"Set_CurrentWeighingResult_1919_PGN65450", "(I)V",(void*) Set_CurrentWeighingResult_1919_PGN65450 },
				{"Set_WeighingDisplayMode1_1910_PGN65450", "(I)V",(void*) Set_WeighingDisplayMode1_1910_PGN65450 },
				// ++, 150212 bwk
				{"Set_WeighingSystemError_BoomLiftSpeed_1942_PGN65450", "(I)V",(void*) Set_WeighingSystemError_BoomLiftSpeed_1942_PGN65450 },
				{"Set_WeighingSystemError_BucketFullIn_1943_PGN65450", "(I)V",(void*) Set_WeighingSystemError_BucketFullIn_1943_PGN65450 },
				{"Set_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450", "(I)V",(void*) Set_WeighingSystemError_HydraulicOilTemperature_1944_PGN65450 },
				// --, 150212 bwk
				{"Set_CurrentWeight_1911_PGN65450", "(I)V",(void*) Set_CurrentWeight_1911_PGN65450 },
				{"Set_TodayWeight_1915_PGN65450", "(I)V",(void*) Set_TodayWeight_1915_PGN65450 },
				//////TX_WEIGHING_SYSTEM_DATA1_65451///////
				{ "Set_TotalWorkAWeight_1912_PGN65451", "(I)V",
						(void*) Set_TotalWorkAWeight_1912_PGN65451 }, {
						"Set_TotalWorkBWeight_1913_PGN65451", "(I)V",
						(void*) Set_TotalWorkBWeight_1913_PGN65451 },
				//////TX_WEIGHING_SYSTEM_DATA2_65452///////
				{ "Set_TotalWorkCWeight_1914_PGN65452", "(I)V",
						(void*) Set_TotalWorkCWeight_1914_PGN65452 }, {
						"Set_ADayBeforeWeight_1916_PGN65452", "(I)V",
						(void*) Set_ADayBeforeWeight_1916_PGN65452 },
				//////TX_JOYSTICK_POSITION_STATUS_65515///////
				{ "Set_BoomJoystickPositionStatus_2310_PGN65515", "(I)V",
						(void*) Set_BoomJoystickPositionStatus_2310_PGN65515 },
				{ "Set_BoomJoystickPosition_2311_PGN65515", "(I)V",
						(void*) Set_BoomJoystickPosition_2311_PGN65515 },
				{ "Set_BucketJoystickPositionStatus_2312_PGN65515", "(I)V",
						(void*) Set_BucketJoystickPositionStatus_2312_PGN65515 },
				{ "Set_BucketJoystickPosition_2313_PGN65515", "(I)V",
						(void*) Set_BucketJoystickPosition_2313_PGN65515 }, {
						"Set_AuxJoystickPositionStatus_2314_PGN65515", "(I)V",
						(void*) Set_AuxJoystickPositionStatus_2314_PGN65515 },
				{"Set_AuxJoystickPosition_2315_PGN65515", "(I)V",(void*) Set_AuxJoystickPosition_2315_PGN65515 },
				{"Set_BoomLeverFloatingPosition_2336_PGN65515", "(I)V",(void*) Set_BoomLeverFloatingPosition_2336_PGN65515 },
				//////TX_WHEEL_LOADER_EHCU_STATUS_65517///////
				{ "Set_BucketPriorityOperation_2301_PGN65517", "(I)V",
						(void*) Set_BucketPriorityOperation_2301_PGN65517 }, {
						"Set_FlowFineModulationOperation_2302_PGN65517", "(I)V",
						(void*) Set_FlowFineModulationOperation_2302_PGN65517 },
				{ "Set_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517", "(I)V",
						(void*) Set_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517 },
				{ "Set_AttachmentPilotCutoffStatus_225_PGN65517", "(I)V",
						(void*) Set_AttachmentPilotCutoffStatus_225_PGN65517 },
				{ "Set_FloatMode_2316_PGN65517", "(I)V",
						(void*) Set_FloatMode_2316_PGN65517 },
				{ "Set_JoystickSteeringActiveStatusEHCU_186_PGN65517", "(I)V",
						(void*) Set_JoystickSteeringActiveStatusEHCU_186_PGN65517 },
				{ "Set_BoomUpEPPRValveCurrent_2304_PGN65517", "(I)V",
						(void*) Set_BoomUpEPPRValveCurrent_2304_PGN65517 }, {
						"Set_BoomDownEPPRValveCurrent_2305_PGN65517", "(I)V",
						(void*) Set_BoomDownEPPRValveCurrent_2305_PGN65517 }, {
						"Set_BucketInEPPRValveCurrent_2306_PGN65517", "(I)V",
						(void*) Set_BucketInEPPRValveCurrent_2306_PGN65517 }, {
						"Set_BucketOutEPPRValveCurrent_2307_PGN65517", "(I)V",
						(void*) Set_BucketOutEPPRValveCurrent_2307_PGN65517 }, {
						"Set_AUX1EPPRValveCurrent_2308_PGN65517", "(I)V",
						(void*) Set_AUX1EPPRValveCurrent_2308_PGN65517 }, {
						"Set_AUX2EPPRValveCurrent_2309_PGN65517", "(I)V",
						(void*) Set_AUX2EPPRValveCurrent_2309_PGN65517 },
				//////TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527///////
				{ "Set_TravelAlarmOperationStatus_3431_PGN65527", "(I)V",
						(void*) Set_TravelAlarmOperationStatus_3431_PGN65527 },
				{ "Set_WasherOperationStatus_3432_PGN65527", "(I)V",
						(void*) Set_WasherOperationStatus_3432_PGN65527 }, {
						"Set_wiperoperationstatus_3433_PGN65527", "(I)V",
						(void*) Set_wiperoperationstatus_3433_PGN65527 }, {
						"Set_IntWiperOperationStatus_3434_PGN65527", "(I)V",
						(void*) Set_IntWiperOperationStatus_3434_PGN65527 }, {
						"Set_WorkLampOperationStatus_3435_PGN65527", "(I)V",
						(void*) Set_WorkLampOperationStatus_3435_PGN65527 }, {
						"Set_HeadLampOperationStatus_3436_PGN65527", "(I)V",
						(void*) Set_HeadLampOperationStatus_3436_PGN65527 }, {
						"Set_CabinLampOperationStatus_3437_PGN65527", "(I)V",
						(void*) Set_CabinLampOperationStatus_3437_PGN65527 }, {
						"Set_IlluminationOperationStatus_3438_PGN65527", "(I)V",
						(void*) Set_IlluminationOperationStatus_3438_PGN65527 },
				{ "Set_OutriggerFrontRHOperationStatus_3439_PGN65527", "(I)V",
						(void*) Set_OutriggerFrontRHOperationStatus_3439_PGN65527 },
				{ "Set_OutriggerFrontLHOperationStatus_3440_PGN65527", "(I)V",
						(void*) Set_OutriggerFrontLHOperationStatus_3440_PGN65527 },
				{ "Set_OutriggerRearRHOperationStatus_3441_PGN65527", "(I)V",
						(void*) Set_OutriggerRearRHOperationStatus_3441_PGN65527 },
				{ "Set_OutriggerRearLHOperationStatus_3442_PGN65527", "(I)V",
						(void*) Set_OutriggerRearLHOperationStatus_3442_PGN65527 },
				{ "Set_OverloadSwitchOperationStatus_3443_PGN65527", "(I)V",
						(void*) Set_OverloadSwitchOperationStatus_3443_PGN65527 },
				{ "Set_BeaconLampOperationStatus_3444_PGN65527", "(I)V",
						(void*) Set_BeaconLampOperationStatus_3444_PGN65527 },
				{ "Set_ForwardTravelAlarmOperationStatus_3445_PGN65527", "(I)V",
						(void*) Set_ForwardTravelAlarmOperationStatus_3445_PGN65527 },
				{ "Set_RearWorkLampOperationStatus_3446_PGN65527", "(I)V",
						(void*) Set_RearWorkLampOperationStatus_3446_PGN65527 },
				{ "Set_RideControlOperationStatus_3447_PGN65527", "(I)V",
						(void*) Set_RideControlOperationStatus_3447_PGN65527 },
				{ "Set_QuickCouplerOperationStatus_3448_PGN65527", "(I)V",
						(void*) Set_QuickCouplerOperationStatus_3448_PGN65527 },
				{ "Set_AutoGreaseOperationStatus_3449_PGN65527", "(I)V",
						(void*) Set_AutoGreaseOperationStatus_3449_PGN65527 }, {
						"Set_MirrorHeatOperationStatus_3450_PGN65527", "(I)V",
						(void*) Set_MirrorHeatOperationStatus_3450_PGN65527 }, {
						"Set_RearWiperOperationStatus_3451_PGN65527", "(I)V",
						(void*) Set_RearWiperOperationStatus_3451_PGN65527 },
				{ "Set_RearWiperWasherOperationStatus_3452_PGN65527", "(I)V",
						(void*) Set_RearWiperWasherOperationStatus_3452_PGN65527 },

				///////////////////////////////////////////////////////////////////////////////////////////////////////
				{ "Get_FirmwareVersionHigh", "()I",(void*) Get_FirmwareVersionHigh },
				{ "Get_FirmwareVersionLow", "()I",(void*) Get_FirmwareVersionLow },
				{ "Get_FirmwareVersionSubHigh", "()I",(void*) Get_FirmwareVersionSubHigh },
				{ "Get_FirmwareVersionSubLow", "()I",(void*) Get_FirmwareVersionSubLow },
				{ "Get_HWVersion", "()I",(void*) Get_HWVersion },
				{ "Get_CommErrCnt", "()I",(void*) Get_CommErrCnt },
				{ "Get_CheckBKCUComm", "()I",(void*) Get_CheckBKCUComm },


				{"Set_CommErrCnt", "(I)V",(void*) Set_CommErrCnt },

				{ "GET_RecvResDownFlag", "()I",(void*) GET_RecvResDownFlag },
				{ "GET_RX_RES_DOWN_UpdateResponse", "()I",(void*) GET_RX_RES_DOWN_UpdateResponse },

				{"Get_RX_RES_KEY_LongFlag", "()I",(void*) Get_RX_RES_KEY_LongFlag},
				{"Get_RX_RES_KEY_Menu", "()I",(void*) Get_RX_RES_KEY_Menu},
				{"Get_RX_RES_KEY_ESC", "()I",(void*) Get_RX_RES_KEY_ESC},
				{"Get_RX_RES_KEY_Left", "()I",(void*) Get_RX_RES_KEY_Left},
				{"Get_RX_RES_KEY_Right", "()I",(void*) Get_RX_RES_KEY_Right},
				{"Get_RX_RES_KEY_Enter", "()I",(void*) Get_RX_RES_KEY_Enter},
				{"Get_RX_RES_KEY_CAM", "()I",(void*) Get_RX_RES_KEY_CAM},
				{"Get_RX_RES_KEY_MainLight", "()I",(void*) Get_RX_RES_KEY_MainLight},
				{"Get_RX_RES_KEY_WorkLight", "()I",(void*) Get_RX_RES_KEY_WorkLight},
				{"Get_RX_RES_KEY_AutoGrease", "()I",(void*) Get_RX_RES_KEY_AutoGrease},
				{"Get_RX_RES_KEY_QuickCouupler", "()I",(void*) Get_RX_RES_KEY_QuickCouupler},
				{"Get_RX_RES_KEY_RideControl", "()I",(void*) Get_RX_RES_KEY_RideControl},
				{"Get_RX_RES_KEY_WorkLoad", "()I",(void*) Get_RX_RES_KEY_WorkLoad},
				{"Get_RX_RES_KEY_BeaconLamp", "()I",(void*) Get_RX_RES_KEY_BeaconLamp},
				{"Get_RX_RES_KEY_RearWiper", "()I",(void*) Get_RX_RES_KEY_RearWiper},
				{"Get_RX_RES_KEY_MirrorHeat", "()I",(void*) Get_RX_RES_KEY_MirrorHeat},
				{"Get_RX_RES_KEY_DetentSetting", "()I",(void*) Get_RX_RES_KEY_DetentSetting},
				{"Get_RX_RES_KEY_FineModulation", "()I",(void*) Get_RX_RES_KEY_FineModulation},
				{"Get_RX_RES_KEY_FN", "()I",(void*) Get_RX_RES_KEY_FN},
				{"Get_gErr_Mcu_TotalPacket", "()I",(void*) Get_gErr_Mcu_TotalPacket},
				{"Get_gErr_Ecu_TotalPacket", "()I",(void*) Get_gErr_Ecu_TotalPacket},
				{"Get_gErr_Tcu_TotalPacket", "()I",(void*) Get_gErr_Tcu_TotalPacket},
				{"Get_gErr_Mcu_TotalPacket_Logged", "()I",(void*) Get_gErr_Mcu_TotalPacket_Logged},
				{"Get_gErr_Ecu_TotalPacket_Logged", "()I",(void*) Get_gErr_Ecu_TotalPacket_Logged},
				{"Get_gErr_Tcu_TotalPacket_Logged", "()I",(void*) Get_gErr_Tcu_TotalPacket_Logged},
				{"Get_gErr_EHCU_TotalPacket_Logged", "()I",(void*) Get_gErr_EHCU_TotalPacket_Logged},
				{"Get_gErr_Mcu_Total", "()I",(void*) Get_gErr_Mcu_Total},
				{"Get_gErr_Ecu_Total", "()I",(void*) Get_gErr_Ecu_Total},
				{"Get_gErr_Tcu_Total", "()I",(void*) Get_gErr_Tcu_Total},
				{"Get_gErr_Mcu_Total_Logged", "()I",(void*) Get_gErr_Mcu_Total_Logged},
				{"Get_gErr_Ecu_Total_Logged", "()I",(void*) Get_gErr_Ecu_Total_Logged},
				{"Get_gErr_Tcu_Total_Logged", "()I",(void*) Get_gErr_Tcu_Total_Logged},
				{"Get_gErr_EHCU_Total_Logged", "()I",(void*) Get_gErr_EHCU_Total_Logged},




				///////////////////////////////////////////////////////////////////////////////////////////////////////

		};

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "JNI_OnLoad.\n");
	jint result = -1;
	JNIEnv* env = NULL;
	jclass cls;

	if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "GetEnv failed.\n");
		goto error;
	}
	cls = (*env)->FindClass(env,
			"taeha/wheelloader/fseries_monitor/main/CommService");
	if (cls == NULL) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
				"Native registration unable to find class(AVMJni)");
		goto error;
	}

	if ((*env)->RegisterNatives(env, cls, methods,
			sizeof(methods) / sizeof(methods[0])) < 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
				"RegisterNatives failed !!!\n");
		goto error;
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "JNI_OnLoad !!!\n");
	result = JNI_VERSION_1_4;
	glpVM = vm;
	jObject = (jclass)(*env)->NewGlobalRef(env, cls);

	error: return result;
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////

