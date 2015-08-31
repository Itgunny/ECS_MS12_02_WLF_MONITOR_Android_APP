package taeha.wheelloader.fseries_monitor.main;

public class UserData {
	public int EngineMode;
	//public int WarmingUp;	// ++, --, 150403 bwk 삭제
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
	public int FuelDisplay;		// ++, --, 150403 bwk 추가
	public int BoomDetentMode;
	public int BucketDetentMode;
	
	public UserData(){
		EngineMode = CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR;
		//WarmingUp = CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF;	// ++, --, 150403 bwk 삭제
		CCOMode = CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H;
		ShiftMode = CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL;
		TCLockUp = CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON;
		RideControl = CAN1CommManager.DATA_STATE_RIDECONTROL_OFF;
		WeighingSystem = CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO;
		WeighingDisplay = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		ErrorDetection = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON;
		KickDown = CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN;
		BucketPriority = CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF;
		SoftEndStopBoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON;
		SoftEndStopBoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON;
		SoftEndStopBucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF;
		SoftEndStopBucketDump = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON;
		// ++, 150407 bwk
		//Brightness = 7;
		BrightnessManualAuto = Home.BRIGHTNESS_MANUAL;
		BrightnessManualLevel = Home.BRIGHTNESS_MAX;
		BrightnessAutoDayLevel = Home.BRIGHTNESS_MAX;
		BrightnessAutoNightLevel = 4;
		BrightnessAutoStartTime = 8;
		BrightnessAutoEndTime = 18;
		// --, 150407 bwk		
		DisplayType = Home.DISPLAY_TYPE_B;
		UnitTemp = Home.UNIT_TEMP_C;
		UnitOdo = Home.UNIT_ODO_KM;
		UnitWeight = Home.UNIT_WEIGHT_TON;
		UnitPressure = Home.UNIT_PRESSURE_BAR;
		MachineStatusUpper = CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT;
		MachineStatusLower = CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY;
		Language = Home.STATE_DISPLAY_LANGUAGE_ENGLISH;
		SoundOutput = Home.STATE_INTERNAL_SPK;
		HourmeterDisplay = CAN1CommManager.DATA_STATE_HOURMETER_LATEST;
		FuelDisplay = CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE;			// ++, --, 150403 bwk 추가
		BoomDetentMode = CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN;
		BucketDetentMode = CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN;
	}
}
