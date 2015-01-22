package taeha.wheelloader.fseries_monitor.main;

public class UserData {
	public int EngineMode;
	public int WarmingUp;
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
	public int Brightness;
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
	
	public UserData(){
		EngineMode = CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR;
		WarmingUp = CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF;
		CCOMode = CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF;
		ShiftMode = CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL;
		TCLockUp = CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF;
		RideControl = CAN1CommManager.DATA_STATE_RIDECONTROL_OFF;
		WeighingSystem = CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_MANUAL;
		WeighingDisplay = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		ErrorDetection = CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_OFF;
		KickDown = CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN;
		BucketPriority = CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF;
		SoftEndStopBoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF;
		SoftEndStopBoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF;
		SoftEndStopBucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF;
		SoftEndStopBucketDump = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF;
		Brightness = 7;
		DisplayType = Home.DISPLAY_TYPE_B;
		UnitTemp = Home.UNIT_TEMP_C;
		UnitOdo = Home.UNIT_ODO_KM;
		UnitWeight = Home.UNIT_WEIGHT_TON;
		UnitPressure = Home.UNIT_PRESSURE_BAR;
		MachineStatusUpper = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
		MachineStatusLower = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
		Language = 0;
		SoundOutput = Home.STATE_INTERNAL_SPK;
		HourmeterDisplay = CAN1CommManager.DATA_STATE_HOURMETER_LATEST;
	}
}
