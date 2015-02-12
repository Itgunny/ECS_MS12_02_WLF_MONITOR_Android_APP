
#define NEW_CAN2

//////////////// CMD Structure////////////////////
typedef struct
{
	unsigned char 	Year;

	unsigned char 	Month;

	unsigned char 	Date;

	unsigned char	Day;

	unsigned char	Hour;

	unsigned char	Min;

	unsigned char 	Sec;

	unsigned char	DM;

}__attribute__((packed))  RES_RTC;

typedef struct
{
	unsigned char 	SWVersionLow:4;
	unsigned char	SWVersionHigh:4;

	unsigned char	SWVersionSubLow:4;
	unsigned char	SWVersionSubHigh:4;

	unsigned short	HWVersion;


	unsigned char	DM[4];

}__attribute__((packed))  RES_VERSION;

typedef struct
{
	unsigned char	Auth;

	unsigned char	MSG;

	unsigned char	Count;

	unsigned char	DM[5];

}__attribute__((packed))  RES_SMK;
typedef struct
{
	unsigned char	MainLight1:2;
	unsigned char	MainLight2:2;
	unsigned char	WorkLight1:2;
	unsigned char	WorkLight2:2;

	unsigned char	AutoGrease1:2;
	unsigned char	AutoGrease2:2;
	unsigned char	QuickCoupler1:2;
	unsigned char	QuickCoupler2:2;

	unsigned char	RideControl1:2;
	unsigned char	RideControl2:2;
	unsigned char	WorkLoad1:2;
	unsigned char	WorkLoad2:2;

	unsigned char	BeaconLamp:2;
	unsigned char	RearWiper:2;
	unsigned char	MirrorHeat:2;
	unsigned char	AutoPosition1:2;

	unsigned char	AutoPosition2:2;
	unsigned char	FineModulation:2;
	unsigned char	FN:2;
	unsigned char	Illumination:2;


	unsigned char	DM[3];

}__attribute__((packed))  CMD_LAMP;

typedef struct
{
	unsigned char	UpdateResponse;

	unsigned char	DM[7];


}__attribute__((packed))  RES_DOWN;

typedef struct
{
	unsigned char	Menu:1;
	unsigned char	ESC:1;
	unsigned char	Left:1;
	unsigned char	Right:1;
	unsigned char	Enter:1;
	unsigned char 	CAM:1;
	unsigned char	Reserved0:2;

	unsigned char	MainLight:1;
	unsigned char	WorkLight:1;
	unsigned char	AutoGrease:1;
	unsigned char	QuickCouupler:1;
	unsigned char	RideControl:1;
	unsigned char	WorkLoad:1;
	unsigned char	Reserved1:2;

	unsigned char	BeaconLamp:1;
	unsigned char	RearWiper:1;
	unsigned char	MirrorHeat:1;
	unsigned char	DetentSetting:1;
	unsigned char	FineModulation:1;
	unsigned char	FN:1;
	unsigned char	Reserved2:2;

	unsigned char	LongFlag;

	unsigned char	DM[4];

}__attribute__((packed))  RES_KEY;

//////////////////////////////////////////////////

////////////CAN Structure/////////////////////////

typedef struct
{
	unsigned char		RX_STX;
	unsigned char		RX_ID;
	unsigned char		RX_LEN;

	unsigned char		RX_SOURCEADDR;
	unsigned char		RX_PS;
	unsigned char		RX_PF;
	unsigned char		RX_PRIORITY;

	unsigned char		RX_DATA[8];
	//unsigned int		RX_RESERVED;
	unsigned char		RX_SAVE_DATA;
	unsigned char		RX_ETX;
}__attribute__((packed))  CAN_RX_PACKET;

//=================================================================


typedef struct
{
	unsigned char	TorqueConverterTemp;
	unsigned char 	Reserved0;
	unsigned char 	Reserved1;
	unsigned char 	Reserved2;
	unsigned char 	OilTempSump;
	unsigned char 	Reserved3;
	unsigned char 	Reserved4;
	unsigned char 	Reserved5;
}__attribute__((packed))  TCU_ERR;

//=================================================================


typedef struct
{
	unsigned int	TripDistance;
	unsigned int	TotalVehicleDistance;
}__attribute__((packed))  VEHICLE_DISTANCE;

//=================================================================

typedef struct
{
	unsigned char		EslMode:2;
	unsigned char		LockMode:2;
	unsigned char		LockLevel:4;

	unsigned char		LockDemander:4;
	unsigned char		EngStartPermission:2;
	unsigned char		Reserved0:2;

	unsigned char		EslInterval;

	unsigned char		PwIdResult:4;
	unsigned char		PwChgResult:2;
	unsigned char		Reserved1:2;

	unsigned char		UserPw[11];
	unsigned char		Reserved2[6];
}__attribute__((packed))  MACHINE_SECURITY_STATUS;


typedef struct
{
	unsigned char		EslMode:2;
	unsigned char		LockMode:2;
	unsigned char		LockLevel:4;
	
	unsigned char		LockDemander:4;
	unsigned char		EngStartPermission:2;
	unsigned char		Reserved0:2;
	
	unsigned char		EslInterval;
	
	unsigned char		PwIdResult:4;
	unsigned char		PwChgResult:2;
	unsigned char		Reserved1:2;

	unsigned char		UserPw[4];
}__attribute__((packed))  MACHINE_SECURITY_STATUS_SINGLE;


//=================================================================


typedef struct
{
	unsigned char	M_Item;
	unsigned char	M_Lamp:2;
	unsigned char	Reserved0:6;
	unsigned char	M_Interval;
	unsigned char	M_Count;
	unsigned short	M_HourMeter;
	unsigned short	Reserved1;
}__attribute__((packed))  MAINTENANCE_INFORMATION2;

//=================================================================

typedef struct
{
	unsigned char	M_Item;
	unsigned short	M_HourMeter;
	unsigned short	M_History[9];
}__attribute__((packed))  MAINTENANCE_INFORMATION3;

//=================================================================

typedef struct
{
	unsigned char		PhoneNum[13];
}__attribute__((packed))  AS_PHONE_NUMBER;

//=================================================================

typedef struct
{
	unsigned char		McuProgramVer;
	unsigned char		McuHardVer;
	unsigned char		MachineModel[16];
	unsigned char		Dummy;
}__attribute__((packed))  VEHICLE_INFORMATION;

//=================================================================

typedef struct
{
	unsigned char	FuelLevel;	// ( % )
	unsigned char	EngCoolantTemp;
	unsigned char	HydOilTemp;
	unsigned char	TcuOilTemp;
	unsigned short	EngineSpeed;
	unsigned short	BattVolt;
}__attribute__((packed))  GAUGE;

//=================================================================

typedef struct
{
	unsigned char	FuelLevelLow:2;
	unsigned char	HydOilTempHigh:2;
	unsigned char	BattVoltLow:2;
	unsigned char	TorqueCnvLockUpStatus:2;

	unsigned char	AirCleanerClog:2;
	unsigned char	Reserved1:4;
	unsigned char 	BreakOilPressLow:2;

	unsigned char 	EngCoolTempHigh:2;
	unsigned char 	Reserved2:2;
	unsigned char 	EngOilPressLow:2;
	unsigned char 	Reserved3:2;

	unsigned char 	TcuOilTempHigh:2;
	unsigned char 	TcuCheck:2;
	unsigned char 	StMainPumpPressLow:2;
	unsigned char 	EmerSteeringActive:2;

	unsigned char 	Reserved4:4;
	unsigned char 	WarmUpStatus:2;
	unsigned char 	EngPreheatStatus:2;

	unsigned char 	FuelWarmActiveStatus:2;
	unsigned char 	EngCoolFanReverseStatus:2;
	unsigned char 	Reserved5:2;
	unsigned char 	ParkingStatus:2;

	unsigned char 	ClutchCutOffStatus:2;
	unsigned char 	JoysticActiveStatus:2;
	unsigned char 	RideControlStatus:2;
	unsigned char 	DiffLockStatus:2;

	unsigned char 	JoysticStActiveStatus:2;
	unsigned char 	Reserved6:2;
	unsigned char 	WarnignSymbol:2;
	unsigned char 	MainteAlarmLamp:2;
}__attribute__((packed))  WARNING_INDICATOR_LAMP;


//=================================================================


typedef struct
{
	unsigned int	HourMeter;	// second
	unsigned short 	Clock;		// Now Not used
	unsigned char 	HourMeterActive:2;
	unsigned char 	WiperStatus:2;
	unsigned char 	WiperSpeed:4;
	unsigned char 	BackLight:4;
	unsigned char 	BuzzerOnCmd:2;
	unsigned char 	Reserved0:2;
}__attribute__((packed))  HOURMETER_WIPER;


//=================================================================


typedef struct
{
	unsigned short	WheelVehicleSpeed;

	unsigned short	SpeedMeterFreq;

	unsigned char 	SelectGear;

	unsigned char 	ActualGear;

	unsigned char 	TcuShiftMode:4;
	unsigned char 	ClutchCutOff:4;

	unsigned char 	KickDownShiftMode:2;
	unsigned char 	SpeedUnit:2;		// 0 -> Km/h        1 -> MPH
	unsigned char 	SpeedMeterModelSel:4;
}__attribute__((packed))  VEHICLE_TRAVEL;


//=================================================================


typedef struct
{
	unsigned int	TripTime;

	unsigned char 	EnginePowerMode:4;
	unsigned char 	EngAlternateLowIldeSw:2;
	unsigned char 	DM0:2;

	unsigned char	Reserved0;
	unsigned char	Reserved1;
	unsigned char	Reserved2;
	//unsigned char 	EngCoolFanReverseMode:2;
	//unsigned char 	EngCoolFanValveCurrent;		// ( % )
	//unsigned short	EngCoolFanSpeed;
}__attribute__((packed))  VEHICLE_STATUS1;


//=================================================================


typedef struct
{
	unsigned char		PressSenCaliStatus:4;
	unsigned char		AngleSenCaliStatus:4;
	unsigned short		BoomAngle;
	unsigned short		Reserved0;
	//unsigned char		FanIntervalTime;
	//unsigned char		FanOperatingTime;
	unsigned char		Reserved1;
	unsigned char		Reserved2;
	unsigned char		Reserved3;
}__attribute__((packed))  VEHICLE_STATUS2;


//=================================================================


typedef struct
{
	unsigned char		DTC_Type:4;
	unsigned char		DTC_Num:4;			// Number of DTC in This Packet (Max=5)
	unsigned char		DTC_Total;			// Total Number of DTC
	unsigned char		DTC_TotalPacket;		// Total Number of DTC Information Packet
	unsigned char		DTC_SequenceNum;	// Sequence Number of DTC Informatio Packet
	unsigned char		DTC_1[3];
	unsigned char		DTC_2[3];
	unsigned char		DTC_3[3];
	unsigned char		DTC_4[3];
	unsigned char		DTC_5[3];
}__attribute__((packed))  DTC_INFORMATION;


//=================================================================


typedef struct
{
	unsigned char	AebMainStatusInfo;
	unsigned char	AebCycleNum;
	unsigned char	AebSubStatusInfo;
	unsigned char	BrakePedalCaliStatus;
	unsigned char	BrakePedalCaliFaultInfo;
	unsigned short	Reserved0;
	unsigned char	Reserved1;
}__attribute__((packed))  AEB_INFORMATION;


//=================================================================


typedef struct
{
	unsigned char 	DashProgVer;
	unsigned char 	DashHardVer;
	unsigned char 	MirrorHeaterStatus:2;
	unsigned char 	HighBeamStatus:2;
	unsigned char 	FrontRearLampStatus:2;
	unsigned char 	Reserved0:2;
	unsigned char 	Reserved1;
	unsigned int	Reserved2;
}__attribute__((packed))  WHEEL_LOADER_DASHBOARD;


//=================================================================


typedef struct
{
	unsigned char 	weOpMode1Status:2;
	unsigned char 	weBuzzer:2;
	unsigned char 	weDispMode1:4;
	unsigned char 	weOpMode2Status:2;
	unsigned char 	weDispMode2:2;
	unsigned char 	weLoadType:4;
	unsigned short	TargetWeight;
	unsigned int	SubTotalWeight;
}__attribute__((packed))  WHEGHING_SYSTEM_STATUS;


//=================================================================


typedef struct
{
	unsigned short	CurrWeight;
	unsigned char	WeightStatus:2;
	unsigned char	Reserved0:6;
	unsigned char	Reserved1;
	unsigned int	TotalAWeight;
}__attribute__((packed))  WEIGHING_SYSTEM_DATA1;


//=================================================================


typedef struct
{
	unsigned int	TotalBWeight;
	unsigned int	TotalCWeight;
}__attribute__((packed))  WEIGHING_SYSTEM_DATA2;


//=================================================================


typedef struct
{
	unsigned int	TodayWeight;
	unsigned int	aDayAgoWeight;
}__attribute__((packed))  WEIGHING_SYSTEM_DATA3;


//=================================================================


typedef struct
{
	unsigned char	weInfoType:2;		// 0 : Data        1 : Hour-meter
	unsigned char 	Reserved0:2;
	unsigned char 	DateData:4;
	unsigned char 	Hour_Start;
	unsigned char 	Hour_End;
	unsigned char 	Reserved1;
	unsigned int 	TotalWeight;
	unsigned int 	RocksWeight;
	unsigned int 	SandWeight;
	unsigned int 	GravelWeight;
	unsigned int 	Unknown1Weight;
	unsigned int	Unknown2Weight;
}__attribute__((packed))  WEIGHING_HISTORY_INFORMATION;




//=================================================================
//=================================================================
//=================================================================
//=================================================================
//=================================================================




typedef struct
{
	unsigned char		 Mode;
	unsigned char		 ESLMode:2;
	unsigned char		 Reserved0:6;
	unsigned char		 ESLInterval;
	unsigned char		 PwCheckReqCount;
	unsigned int		 Reserved1;
}__attribute__((packed))  ESL_PASSWORD_SETTING;


//=================================================================


typedef struct
{
	unsigned char		Mode;
	unsigned char		ESLMode:2;
	unsigned char		Reserved0:6;
	unsigned char		ESLInterval;
	unsigned char		PwCheckReqCount;
	unsigned char		PassW[17];
}__attribute__((packed))  ESL_PASSWORD_SETTING_MULTI;


//=================================================================


typedef struct
{
	unsigned char		ASPhoneNo[12];
	unsigned int		Reserved0;
}__attribute__((packed))  AS_PHONE_NUM_MULTI;


//=================================================================


typedef struct
{
	unsigned char	ReqAEB:2;
	unsigned char 	ReqTripReset:2;
	unsigned char 	ReqBuzzerStop:2;
	unsigned char 	ReqEngLowIdleSpeed:2;
	
	unsigned char 	ReqBoomPressCali:2;
	unsigned char 	ReqSpeedUnit:2;
	unsigned char 	ReqAngleSensorCali:4;
	
	unsigned char 	ReqDtcInfo:2;
	unsigned char 	ReqDtcDel:2;
	unsigned char 	DTC_Type:4;
	
	unsigned char 	DTC_SequenceNum;	// Sequence Number of DTC Information Packet
	
	unsigned char 	ReqMaintenance:4;	// Request Maintenance (of the Item - byte 6)
	unsigned char 	ReqBrakePedalSensorCali:2;
	unsigned char 	Reserved2:2;
	
	unsigned char 	ReqMaintenanceItem;
	
	unsigned char 	ReqMaintenanceInterval;
	
	unsigned char 	ReplyASNum:2;
	unsigned char	ReqWeightClearSignal:2;
	unsigned char 	ReqWeighingErrorCompensationUnitChange:2;
	unsigned char 	Reserved3:2;
}__attribute__((packed))  MONITOR_REQUEST_REPLY;
//=================================================================


typedef struct
{
	unsigned char	MoniProgramVer;

	unsigned char 	MoniHardVer;

	//unsigned char 	TestMode:2;		// 0 : Test Mode Off        1 : Test Mode On        3 : default
	//unsigned char 	Reserved0:6;
	unsigned char	Reserved0;

	//unsigned char 	EngCoolFanValveCurrent;		// Engine Cooling Fan Valve Current ( % )
	unsigned char	Reserved1;

	unsigned char 	MoniSwStatus;

	unsigned char 	Reserved2;

	unsigned short	Reserved3;
}__attribute__((packed))  WHEEL_LOADER_MONITOR;


//=================================================================


typedef struct
{
	unsigned char	weOpMode2Status:2;
	unsigned char	weDispMode2:2;
	unsigned char	weLoadType:4;
	unsigned short	TargetWeight;
	unsigned char 	reqWEDateInfo:2;
	unsigned char 	reqWEHourInfo:2;
	unsigned char 	weDateInfo:4;
	unsigned char 	weHourInfo_Start;
	unsigned char 	weHourInfo_End;
	unsigned short	Reserved0;
	unsigned char	Reserved1;
}__attribute__((packed))  WEIGHING_SYSTEM_MODE2;


//=================================================================


typedef struct
{
	unsigned short	SteeringPumpPS;
	unsigned short 	EmergencyMotorPumpPS;
	unsigned short 	BoomHeadCylinderPS;
	unsigned short 	BoomRodCylinderPS;
	unsigned short 	BrakefailureWarningPS;
	unsigned short 	ParkingPS;
	unsigned short 	DiffernentialLockPS;
	unsigned short 	AlternatorNodeIVoltage;
	unsigned char	BoomPosSensorVolt;
	unsigned char	BucketPosSensorVolt;
	unsigned char 	EngineModeSelVolt;
	unsigned char 	ClutchModeSelVolt;
	unsigned char 	TMModeSel;
	unsigned short	BrakePriorityPS;
	unsigned char	BrakePedalPositionVoltage; 	// ClutchCutOffVolts), HCESP : 3458
}__attribute__((packed))  MACH_STATE;


//=================================================================


typedef struct
{
	unsigned int		Reserved0;
	unsigned short		Reserved1;
	unsigned char		Reserved2;
	unsigned char	 	JssErr_Joystic:2;
	unsigned char	 	JssErr_PresentButton:2;
	unsigned char	 	JssErr_CoilError:2;
	unsigned char	 	JssErr_PressureSensorError:2;
}__attribute__((packed))  SCU_ERROR;


//=================================================================


typedef struct
{

	unsigned char		SMK_Auth_Result;
	unsigned char		SMK_Msg_Result;
	unsigned char		SMK_Tag_Reg_Count;
	unsigned char		Reserved0;
	unsigned char		Hour;
	unsigned char		Min;
	unsigned short		Reserved1;
}__attribute__((packed))  RTC;


//=================================================================


typedef struct
{
	unsigned char		MessageType;		// 203

	unsigned char		ReqParallelLiftPosition:4;
	unsigned char		Reserved0:4;

	unsigned short		Reserved1;

	unsigned char		ReqBucketPriorityFunction:2;
	unsigned char		FineModulation:2;
	unsigned char		Reserved2:4;

	unsigned char		MaxFlowLevel:4;
	unsigned char		Reserved3:4;
	unsigned short		Reserved4;
}__attribute__((packed))  EHCU;


//=================================================================
#if 0
typedef struct
{
	unsigned char		DM0:2;
	unsigned char		DM1:2;
	unsigned char		Warming_Up:2;
	unsigned char		DM2:2;
	
	unsigned char		Fuel_Warmer:2;
	unsigned char		DM3:4;
	unsigned char 	DM4:2;
	unsigned char 	Parking:2;
	
	unsigned char 	Clutch_Cutoff:2;
	unsigned char 	FNR_Active:2;
	unsigned char 	Ride_Control_Status:2;
	unsigned char 	Differential_Lock:2;
	
	unsigned char 	Reserved1;
	
	unsigned char 	Maintenance_Alarm:2;
	unsigned char 	DM5:2;
	unsigned char 	DM6:2;
	unsigned char 	DM7:2;
	
	unsigned char 	DM8:2;
	unsigned char 	DM9:2;
	unsigned char 	DM10:2;
	unsigned char 	TMLockup_Status:2;
	
	unsigned char 	Reserved2;
	unsigned char 	Reserved3;
}__attribute__((packed))  INDICATOR_LAMP;
#endif
//=================================================================
//=================================================================
//=================================================================
//=================================================================




typedef struct
{
	unsigned short 	HCEPGN;
	unsigned short 	Reserved0;
	unsigned int 	Reserved1;
}__attribute__((packed))  HCEPGN_REQUEST;


//=================================================================


typedef struct
{
	unsigned short 	HCESPN_DATA;
	unsigned short 	Reserved0;
	unsigned int 	Reserved1;
}__attribute__((packed))  HCESPN_DATA_REQUEST;


//=================================================================


typedef struct
{
	unsigned char 	Message_type;	// 101

	unsigned char	Reserved0;

	unsigned char   DM0:6;
	unsigned char   EngAlternateLowIdleSw:2;

	unsigned char 	Reserved1;
	unsigned char	Reserved2;
	unsigned char 	DM1:2;
	unsigned char 	DM2:2;
	unsigned char 	Engine_Power_Mode:4;
	unsigned short 	Reserved3;
}__attribute__((packed))  MACHINE_MODE_SETTING;

//=================================================================


typedef struct
{
	unsigned char 	Message_type;		// 102

	unsigned char	KickDownShiftMode:2;
	unsigned char	DM0:2;
	unsigned char 	SpeedFreqInit:4;

	unsigned short 	SpeedFreq;

	unsigned char	Transmission_Shift_Mode:4;
	unsigned char 	Clutch_Cutoff_Mode:4;

	unsigned char 	Transmission_TC_Lockup:2;
	unsigned char 	Differential_Lock:2;
	unsigned char 	Vehicle_Speed_Limit_Status:2;
	unsigned char 	DM1:2;

	unsigned char 	Vehicle_Speed_Limit;

	unsigned char	Reserved0;
}__attribute__((packed))  MACHINE_TRAVEL_SETTING;

typedef struct
{
	unsigned char 	VehicleSpeedLimit;

	unsigned char 	WorkToolSet:4;
	unsigned char	DifferentialLockMode:2;
	unsigned char	DM0:2;

	unsigned char	BoomDetentMode:3;
	unsigned char	BucketDetentMode:3;
	unsigned char	DM1:2;

	unsigned char	Reserved0;
	unsigned char	Reserved1;
	unsigned char	Reserved2;
	unsigned char	Reserved3;
	unsigned char	Reserved4;

}__attribute__((packed))  MACHINE_SETTING_STATUS;


//=================================================================
//=================================================================
//=================================================================
//=================================================================
//=================================================================



#if 0
typedef struct
{
	unsigned char WeighingSystem:2;		// ¾ç»ê   ²¨Áü / ÄÑÁü   0, 1
	unsigned char testWorkLoad:2;			// Å×½ºÆ® ²¨Áü / ÄÑÁü   0, 1
	unsigned char Unit:4;
	unsigned char DispType:2;
	unsigned char InfoViewPriority:2;
	unsigned char UnitPressure:2;
	unsigned char UnitWeight:2;
	unsigned char Lang;
	unsigned char ManualBackLight;
	unsigned char AutoBackLight;
	unsigned char TopInfoView:4;
	unsigned char BottomInfoView:4;
	unsigned char InfoViewCnt:2;
	unsigned char HR_Start:2;
	unsigned char RearCamReverseMode:2;
	unsigned char ActiveCamNum:2;
	unsigned char FirstCam:2;
	unsigned char SecondCam:2;
	unsigned char ThirdCam:2;
	unsigned char FourthCam:2;
}__attribute__((packed))  SAVE_DATA1;
#else
typedef struct
{
	unsigned char WeighingSystem:2;		// ¾ç»ê   ²¨Áü / ÄÑÁü   0, 1
	unsigned char testWorkLoad:2;			// Å×½ºÆ® ²¨Áü / ÄÑÁü   0, 1
	unsigned char Unit:4;

	unsigned char DispType:2;
	unsigned char InfoViewPriority:2;
	unsigned char UnitPressure:2;
	unsigned char UnitWeight:2;

	unsigned char Lang;

	unsigned char ManualBackLight;

	unsigned char AutoBackLight;

	unsigned char TopInfoView:4;
	unsigned char BottomInfoView:4;

	unsigned char InfoViewCnt:2;
	unsigned char HR_Start:2;
	unsigned char RearCamReverseMode:2;
	unsigned char Reserved0:2;

	unsigned char CamChMode;
}__attribute__((packed))  SAVE_DATA1;
#endif

//=================================================================


typedef struct
{
	unsigned char LightMode:2;
	unsigned char DM0:2;
	unsigned char JobOdoSelect:4;
	unsigned char LightLowTime;
	unsigned char LightUpperTime;
	unsigned char AutoLightDay:4;
	unsigned char AutoLightNight:4;
	unsigned short HardRevAdc;
	unsigned char SmartKey:2;
	unsigned char DM1:6;
	unsigned char Reserved1;
}__attribute__((packed))  SAVE_DATA2;


//=================================================================


typedef struct
{
	unsigned char Phone[6];
	unsigned short Reserved0;
}__attribute__((packed))  SAVE_DATA3;


//=================================================================


typedef struct
{
	unsigned char DTC_Type:4;
	unsigned char DTC_Num:4;			// Number of DTC in This Packet (Max=5)
	unsigned char DTC_Total;			// Total Number of DTC
	unsigned char DTC_TotalPacket;		// Total Number of DTC Information Packet
	unsigned char DTC_SequenceNum;		// Sequence Number of DTC Informatio Packet
	unsigned char DTC_1[3];
	unsigned char DTC_2[3];
	unsigned char DTC_3[3];
	unsigned char DTC_4[3];
	unsigned char DTC_5[3];
}__attribute__((packed))  ERROR_MULTI;


//=================================================================


typedef struct
{
	unsigned char	ControlByte;
	unsigned short	HCEPGN;
}__attribute__((packed))  PGN_ACK;


//=================================================================


typedef struct
{
	unsigned char recv_as_phone_num;
	unsigned char recv_as_phone_num_once;
	unsigned char draw_as_phone_num;
}__attribute__((packed))  COMM;


//=================================================================


typedef struct
{
	unsigned char	PhoneNum[8];
}__attribute__((packed))  SINGLE_PACKET_PHONE_NUM;




typedef struct
{
	unsigned char	PhoneNum[13];
}__attribute__((packed))  MULTI_PACKET_PHONE_NUM;


//=================================================================



typedef struct
{
	unsigned char		SMK_Auth_Result;
	unsigned char		SMK_Msg_Result;
	unsigned char		SMK_Tag_Reg_Count;
	unsigned char		Reserved0;
	unsigned int		Reserved1;
}__attribute__((packed))  SMK_RESULT;


//=================================================================

#if 0
typedef struct
{
	unsigned char		MessageType;		// 204
	unsigned char		ParallelLiftCaliStatus:4;
	unsigned char		Reserved0:4;
	unsigned short	Cali_A_Boom;
	unsigned short	Cali_A_BellCrank;
	unsigned short	Cali_B_Boom;
	unsigned short	Cali_B_BellCrank;
	unsigned short	Cali_C_Boom;
	unsigned short	Cali_C_BellCrank;
	unsigned short	Cali_D_Boom;
	unsigned short	Cali_D_BellCrank;
	unsigned char		BucketPriorityStatus:2;
	unsigned char		Reserved1:2;
	unsigned char		MaxFlowLevelStatus:4;
}__attribute__((packed))  EHCU_STATUS;
#else
typedef struct
{
	unsigned char	MessageType;		// 204

	unsigned char	ParallelLiftCaliStatus:4;
	unsigned char	Reserved0:4;

	unsigned short	Cali_A_Boom;

	unsigned short	Cali_A_BellCrank;

	unsigned short	Cali_B_Boom;

	unsigned short	Cali_B_BellCrank;

	unsigned short	Cali_C_Boom;

	unsigned short	Cali_C_BellCrank;

	unsigned short	Cali_D_Boom;

	unsigned short	Cali_D_BellCrank;
//	unsigned char		BucketPriorityStatus:2;
//	unsigned char		Reserved1:2;
//	unsigned char		MaxFlowLevelStatus:4;
}__attribute__((packed))  EHCU_STATUS;

typedef struct
{
		unsigned char		BucketPriorityStatus:2;					//HCESPN 2301
		unsigned char		FineModulation:2;						//HCESPN 2302
		unsigned char		MaxFlowLevelStatus:4;					//HCESPN 2303

		unsigned char		AttachmentPilotCutoffStatus:2;			//HCESPN 225
		unsigned char 		FloatMode:2;							//HCESPN 2316
		unsigned char 		JoystickSteeringActiveStatus_EHCU:2;	//HCESPN 186
		unsigned char 		Reserved0:2;

		unsigned char 		BoomUpEPPRValveCurrent;					//HCESPN 2304

		unsigned char		BoomDownEPPRValveCurrent;				//HCESPN 2305

		unsigned char		BucketInEPPRValveCurrent;				//HCESPN 2306

		unsigned char		BucketOutEPPRValveCurrent;				//HCESPN 2307

		unsigned char		AuxUpEPPRValveCurrent;					//HCESPN 2308

		unsigned char		AuxDownEPPRValveCurrent;				//HCESPN 2309

}__attribute__((packed))  EHCU_STATUS2;
#endif
//=================================================================


typedef struct
{
	unsigned short	BoomAngle;
	unsigned short	BellCrankAngle;
	unsigned char 	BoomVolt;
	unsigned char	BellCrankVolt;
	unsigned short	BucketCylinderStroke;
}__attribute__((packed))  CYLINDER;



//=================================================================

typedef struct
{	
	unsigned char Power_Max_Status:2; 						//HCESPN 802
	unsigned char Deceleration_Status:2; 					//HCESPN 803
	unsigned char Warming_Up_Status:2; 						//HCESPN 804
	unsigned char Engine_Preheat_Status:2;					//HCESPN 323

	unsigned char Fuel_Warmer_Active_Status:2; 				//HCESPN 326
	unsigned char Cooling_Fan_Driving_Status:2;				//HCESPN 180
	unsigned char Cruise_Status:2;							//HCESPN 519
	unsigned char Parking_Status:2; 						//HCESPN 508

	
	unsigned char Clutch_Cutoff_Status:2; 					//HCESPN 545
	unsigned char FNR_Joystick_Active_Status:2;				//HCESPN 546
	unsigned char Ride_Control_Status:2; 					//HCESPN 550
	unsigned char Differential_Lock_Status:2;				//HCESPN 559

	
	unsigned char Joystick_Steering_Active_Status:2; 		//HCESPN 186
	unsigned char Inching_Status:2; 						//HCESPN 549
	unsigned char Ram_Lock_Status:2; 						//HCESPN 520
	unsigned char Working_Brake_Status:2; 					//HCESPN 506

	unsigned char Maintenance_Alarm_Lamp:2;					//HCESPN 1099
	unsigned char Operator_Absence_Status:2;                //HCESPN 832
	unsigned char Mirror_Heater_Status:2; 					//HCESPN 724
	unsigned char High_Beam_Status:2; 						//HCESPN 725

	
	unsigned char Travel_Alarm_Switch:2; 					//HCESPN 721
	unsigned char Swing_Boom_Switch:2;						//HCESPN 726
	unsigned char Parking_Working_Travel_Status:2;			//HCESPN 510
	unsigned char Transmission_Lockup_Status:2; 			//HCESPN 556

	
	unsigned char Exhaust_System_High_temp:2; 				//HCESPN 353
	unsigned char DM1:2; 									
	unsigned char DM2:2; 			
	unsigned char DPF_Regeneration_Inhibit:2; 				//HCESPN 354

	
	unsigned char DM3;
}__attribute__((packed)) Indicator_Lamp;

//=================================================================

#if 0
typedef struct
{	
	unsigned char Reserved1;
	unsigned char Reserved2;
	unsigned char Reserved3;

	unsigned char DM0:2; 
	unsigned char Beacon_Lamp:2; 				//HCESPN 3444
	unsigned char DM1:4; 					

	unsigned char Ride_Control:2;				//HCESPN 3447
	unsigned char Quick_Coupler:2;                       //HCESPN 3448
	unsigned char Auto_Grease:2; 				//HCESPN 3449
	unsigned char Mirror_Heat:2; 				//HCESPN 3450

	unsigned char Rear_Wiper:2; 				//HCESPN 3451
	unsigned char Rear_Wiper_Washer:2;		//HCESPN 3452	
	unsigned char DM2:4;	//HCESPN 510

	unsigned char Reserved4;
	
	unsigned char Ride_Control_Speed;					//HCESPN 3460
}__attribute__((packed)) Realy_Control;
#else
typedef struct
{
	unsigned char Reserved1;

	unsigned char WorkLamp:2;							// HCESPN 3435
	unsigned char HeadLamp:2;							// HCESPN 3436
	unsigned char DM0:2;
	unsigned char Illumination:2;						// HCESPN 3438

	//unsigned char Reserved3;
	//unsigned char EG_Idle_Delay_time:4;
	unsigned char DM1:4;
	unsigned char Outrigger_Rear_RH:2;
	unsigned char Outrigger_Rear_LH:2;

	unsigned char DM2:2;
	unsigned char Beacon_Lamp:2; 						//HCESPN 3444
	unsigned char DM3:2;
	unsigned char RearWorkLamp:2;						// HCESPN 3446

	unsigned char Ride_Control:2;						//HCESPN 3447
	unsigned char Quick_Coupler:2;                      //HCESPN 3448
	unsigned char Auto_Grease:2; 						//HCESPN 3449
	unsigned char Mirror_Heat:2; 						//HCESPN 3450

	unsigned char Rear_Wiper:2; 						//HCESPN 3451
	unsigned char Rear_Wiper_Washer:2;					//HCESPN 3452
	unsigned char KeyPositionStatus:2;					//HCESPN 3807
	unsigned char EngineDelayShutdownOperation:2;		//HCESPN 3808

	unsigned char EngineDelayShutdownTime;

	unsigned char Ride_Control_Speed;					//HCESPN 3460
}__attribute__((packed)) Realy_Control;
#endif
//=================================================================

typedef struct
{
	unsigned char	Reserved0;

	unsigned char	DM0:6;
	unsigned char	EngineAlternateLwIdleSwitch:2;

	unsigned short	Reserved1;

	unsigned char	DM1:2;
	unsigned char	DM2:2;
	unsigned char	EnginePowerMode:4;

	unsigned char	Reserved2;

	unsigned short	Reserved3;
}__attribute__((packed))  Machine_Mode_Status;

//=================================================================
#if 0
typedef struct
{
	unsigned char	LampStatus;
	unsigned char	SAEReserved;
	unsigned int	DTC;
	unsigned short	Reserved0;
}__attribute__((packed))  EHCU_ERROR_SINGLE;
#else
typedef struct
{
	unsigned short	LampStatus;

	unsigned int	DTC;

	unsigned short	Reserved0;
}__attribute__((packed))  EHCU_ERROR_SINGLE;
#endif

//=================================================================


typedef struct
{
	unsigned char		LampStatus;
	unsigned char		SAEReserved;
	unsigned int		DTC_Error[14];
}__attribute__((packed))  EHCU_ERROR_MULTI;


//=================================================================

typedef struct
{
	unsigned char 	EngPwrMode:4;				// Auxiliary SW
	unsigned char 	EngAlternateLowIdleSw:2;
	//unsigned char 	EngCoolFanReverseMode:2;	// Auxiliary SW
	unsigned char	DM0:2;

	unsigned char 	TcuShiftMode:4;				// Auxiliary SW
	unsigned char 	ClutchCutOffMode:4;			// Auxiliary SW

	unsigned char 	KickDownShiftMode:2;
	unsigned char 	weOpMode1Status:2;			// Weighinhg System Operation Mode 1 Status
	unsigned char 	WiperSpeedLevel:4;

	unsigned char 	BackLightLevel:4;
	unsigned char 	SpeedFreqInit:4;

	unsigned short	Clock;

	unsigned short	SpeedFreq;

	//unsigned char 	FanIntervalTime;
	unsigned char 	Reserved0;
	//unsigned char 	FanOperatingTime;
	unsigned char 	Reserved1;

	unsigned short	WeighingErrorCompensationValue;
	unsigned int	Reserved2;
}__attribute__((packed))  MONITOR_SETTING;

typedef struct
{
	unsigned char KEY_Data;
	unsigned char LCDBL_Data;
	unsigned char Buzzer_Data;
	unsigned char Lamp_Value_Data:4;
	unsigned char Lamp_Name_Data:4;
	unsigned char RTC_Hour_Data;
	unsigned char RTC_Min_Data;
	unsigned char CAM_Mode_Data;
}__attribute__((packed))  CMD_DATA;

typedef struct
{
	unsigned char 	Message_Type;		// 109

	unsigned char 	BacklightIlluminationLevel:4;
	unsigned char 	WiperSpeedLevel:4;

	unsigned short 	Clock;

	unsigned char 	Request_AEB:2;
	unsigned char 	Request_TripDataReset:2;
	unsigned char 	Request_EngineLowidleSpeed:2;
	unsigned char 	Request_BrakePedalPosSensorCal:2;

	unsigned char 	WorkToolSet:4;
	unsigned char 	DetentPosSetting:2;
	unsigned char 	DM0:2;

	unsigned char 	BoomDetentMode:3;
	unsigned char	BucketDetentMode:3;
	unsigned char	DM2;
}__attribute__((packed))  MACHINE_ACCESSORY_SETTING_REQUEST;

typedef struct
{
	unsigned char Message_Type;					// 61

	unsigned char TestMode:2;
	unsigned char CoolingFanReverseMode:2;			// HCESPN : 182
	unsigned char DM0:4;

	unsigned char CoolingFanValveCurrent;			// HCESPN : 146

	unsigned char CoolingFanReverseIntervalTime;	// HCESPN : 211

	unsigned char CoolingFanReverseOperatingTime;	// HCESPN : 212

	unsigned char Reserved0;
	unsigned char Reserved1;
	unsigned char Reserved2;

}__attribute__((packed))  COOLING_FAN_SETTING;

typedef struct
{
	unsigned char CoolingFanReverseMode:2;				// HCESPN : 182
	unsigned char CoolingFanReverseSolenoid:2;			// HCESPN : 181
	unsigned char CoolingFanReverseSW_Manual:2;			// HCESPN : 740
	unsigned char CoolingFanReverseSW_Auto:2;			// HCESPN : 741

	unsigned char CoolingFanValveCurrent;				// HCESPN : 146

	unsigned short CoolingFanSpeed;						// HCESPN : 318

	unsigned char CoolingFanReverseIntervalTime;		// HCESPN : 211

	unsigned char CoolingFanReverseOperatingTime;		// HCESPN : 212

	unsigned short CoolingFanMotorPressure;				// HCESPN : 214

}__attribute__((packed))  COOLING_FAN_STATUS;

typedef struct
{
	unsigned char FuelLevelLow:2;			// HCESPN : 303
	unsigned char HydOilTempHigh:2;			// HCESPN : 102
	unsigned char BatteryVoltLow:2;			// HCESPN : 706
	unsigned char Overload:2;				// HCESPN : 104

	unsigned char AirCleanerClog:2;				// HCESPN : 317
	unsigned char EngineCheck:2;				// HCESPN : 320
	unsigned char TMOilPS:2;					// HCESPN :	502
	unsigned char BrakeOilPSLow:2;				// HCESPN :	504

	unsigned char EngineCoolantTempHigh:2;		// HCESPN :	305
	unsigned char EngineCoolantLevelLow:2;		// HCESPN :	307
	unsigned char EngineOilPSLow:2;				// HCESPN :	313
	unsigned char EngineOilFilterClog:2;		// HCESPN :	315

	unsigned char TMOilTempHigh:2;				// HCESPN :	537
	unsigned char TMCheck:2;					// HCESPN :	538
	unsigned char SteeringMainPumpPSLow:2;		// HCESPN :	184
	unsigned char EmergencySteeringActive:2;	// HCESPN :	185

	unsigned char WarningSymbolLamp:2;			// HCESPN :	709
	unsigned char WaterInFuelIndicator:2;		// HCESPN :	360(97)
	unsigned char DTCAlarmLamp:2;				// HCESPN :	1509
	unsigned char EngineStopLamp:2;				// HCESPN :	319

	unsigned char DPFLampCommand:3;				// HCESPN :	352
	unsigned char Reserved0:3;
	unsigned char ClutchSlip:2;					// HCESPN :	569

	unsigned char BrakeOilLevelLow:2;			// HCESPN :	566
	unsigned char BrakeOilTempHigh:2;			// HCESPN :	567
	unsigned char EmissionSystemFailLamp:2;		// HCESPN :	357
	unsigned char DEFLowLamp:2;					// HCESPN :	358

	unsigned char Reserved1;

}__attribute__((packed))  WARNING_LAMP;

typedef struct
{
	unsigned char RMCUNetworkType;					// HCESPN : 1621

	unsigned char RMCUBackupBatteryVoltage;			// HCESPN : 1590

	unsigned char RMCUPowerSource:2;				// HCESPN : 1594
	unsigned char RMCUBoxOpeningStatus:2;			// HCESPN : 1592
	unsigned char NetworkCommStatus1:2;				// HCESPN : 1622
	unsigned char DM0:2;

	unsigned char PositionUpdateStatus:2;			// HCESPN : 852
	unsigned char MachinePositionStatus:2;			// HCESPN : 1593
	unsigned char GPSAntennaConnectionAlarmStatus:2;// HCESPN : 1595
	unsigned char DM1:2;

	unsigned char NetworkTransceiverStatus1;		// HCESPN : 1623(3368)

	unsigned char NetworkServiceStatus1;			// HCESPN : 1624(3369)

	unsigned char NetworkAntennaStatus1;			// HCESPN : 1625

	unsigned char RMCUData;							// HCESPN : 855

}__attribute__((packed))  RMCU_STATUS;

typedef struct
{
	unsigned char ComponentCode;				// HCESPN : 1699
	unsigned char ManufacturerCode;				// HCESPN : 1700
	unsigned char ComponentBasicInfo[37];		// HCESPN : 1698

}__attribute__((packed))  COMPONENT_IDENTIFICATION;

typedef struct
{
	unsigned char ComponentCode;					// HCESPN : 1699
	unsigned char ManufacturerCode;					// HCESPN : 1700
	unsigned char SoftwareIdentification[48];		// HCESPN : 234

}__attribute__((packed))  COMPONENT_IDENTIFICATION_ECM;

typedef struct
{
	unsigned char ComponentCode;					// HCESPN : 1699
	unsigned char ManufacturerCode;					// HCESPN : 1700
	unsigned char SoftwareIdentification[67];		// HCESPN : 234

}__attribute__((packed))  COMPONENT_IDENTIFICATION_TCU;

typedef struct
{
	unsigned char OnIdleStateAccPedalPosition:2;	// HCESPN : 560
	unsigned char OffIdleStateAccPedalPosition:2;	// HCESPN : 561
	unsigned char Reseved0:4;

	unsigned char AccPedalPositionVolt1;			// HCESPN : 710

	unsigned char BrakePedalPositionVolt;			// HCESPN : 573

	unsigned char AccPedalPositionPercent;			// HCESPN : 339

	unsigned char AccPedalPositionVolt2;			// HCESPN : 710

	unsigned char Reserved1[3];
}__attribute__((packed))  ACCELERATOR_BRAKEPEDAL_STATUS;

typedef struct
{
	unsigned short 	WeighingErrorCompensationValue;

	unsigned char 	SuddenChangeError:2;
	unsigned char	BucketFullInError:2;
	unsigned char	DM0:4;

	unsigned char	Reserved0[5];
}__attribute__((packed))  WEIGHING_SYSTEM_ERROR;

typedef struct
{
	unsigned char MessageType;		// 121

	unsigned char AutomaticEngineShutdown:2;					// HCESPN : 363
	unsigned char Reserved0:6;

	unsigned char SettingTimeforAutomaticEngineShutdown;		// HCESPN : 364

	unsigned char EngineShutdownConrolByte:4;
	unsigned char Reserved1:4;

	unsigned char DelayedEngineShutdown:2;						// HCESPN : 365
	unsigned char Reserved2:6;

	unsigned char SettingTimeforDelayedEngineShutdown;			// HCESPN : 366

	unsigned char DM0[2];
}__attribute__((packed))  ENGINE_SHUTDOWN_MODE_SETTING;

typedef struct
{
	unsigned char MessageType;		// 122

	unsigned char Reserved0;

	unsigned char SettingTimeforAutomaticEngineShutdown;		// HCESPN : 364

	unsigned char RemainingTimeforAutomaticEngineShutdown;		// HCESPN :

	unsigned char Reserved1;

	unsigned char SettingTimeforDelayedEngineShutdown;			// HCESPN : 366

	unsigned short RemainingTimeforDelayedEngineShutdown;			// HCESPN :
}__attribute__((packed))  ENGINE_SHUTDOWN_MODE_STATUS;

typedef struct
{
	unsigned char WeighingSystemMode:2;							// HCESPN : 1941
	unsigned char WeighingDataReset:4;							// HCESPN : 1942
	unsigned char Reserved0:2;

	unsigned char DM0[7];
}__attribute__((packed))  WEIGHING_SYSTEM_MODE;

typedef struct
{
	unsigned char MessageType;		// 123

	unsigned char BoomDetentMode:3;								// HCESPN : 223
	unsigned char BucketDetentMode:3;							// HCESPN : 224
	unsigned char Reserved0:2;

	unsigned char ReqDetentRelasePositionSetting:4;
	unsigned char Reserved1:4;

	unsigned char DM0[5];

}__attribute__((packed))  DETENT_MODE_SETTING;

typedef struct
{
	unsigned char 	BoomJoystickPositionStatus:2;				// HCESPN : 2310
	unsigned char 	Reserved0:4;
	unsigned short	BoomJoystickPosition:10;					// HCESPN : 2311


	unsigned char 	BucketJoystickPositionStatus:2;				// HCESPN : 2312
	unsigned char 	Reserved1:4;
	unsigned short	BucketJoystickPosition:10;					// HCESPN : 2313

	unsigned char 	AuxJoystickPositionStatus:2;				// HCESPN : 2314
	unsigned char 	Reserved2:4;
	unsigned short	AuxJoystickPosition:10;						// HCESPN : 2315

	unsigned char 	DM0[2];

}__attribute__((packed))  JOYSTICK_POSITION_STATUS;





#ifdef NEW_CAN2

typedef struct
{
	unsigned char	MessageType;

	unsigned char	DTCInformationRequest_1515:4;
	unsigned char	DTCType_1510:4;

	unsigned char	SeqenceNumberofDTCInformationPacket_1513;

	unsigned char	DM0[5];

}__attribute__((packed))  DTC_INFORMATION_REQUEST_61184_11;

typedef struct
{
	unsigned char	MessageType;

	unsigned char	MaintenanceCommant_1097:4;
	unsigned char	Reserved0:4;

	unsigned char	MaintenanceItem_1098;

	unsigned char 	MaintenanceInterval_1091;

	unsigned char	DM0[4];

}__attribute__((packed))  MAINTENANCE_REQUSET_61184_12;

typedef struct
{
	unsigned char	MessageType;

	unsigned char	TotalNumberofMaintenanceItems_1100;

	unsigned char	MaintenanceItem_1098[23];

}__attribute__((packed))  MAINTENANCE_ITEM_LIST_61184_13;
typedef struct
{
	unsigned char	MessageType;

	unsigned char 	MaintenanceItem_1098;

	unsigned char	MaintenanceAlarmLamp_1099:2;
	unsigned char 	Reserved0:2;
	unsigned char	MaintenanceEvent:4;

	unsigned char 	MaintenanceInterval_1091;

	unsigned char 	MaintenanceTotalCount_1092;

	unsigned short	HourmeterattheLastMaintenance_1093;

	unsigned char 	DM0;

}__attribute__((packed))  MAINTENANCE_INFORMATION_61184_14;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	MaintenanceItem_1098;

	unsigned short	HourmeterattheLastMaintenance_1093;

	unsigned short	MaintenanceHistory_1094[9];

}__attribute__((packed))  MAINTENANCE_HISTORY_61184_15;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	TotalNumberofMaintenanceAlarmLampOnItems_1100;

	unsigned char	MaintenanceItem[23];

}__attribute__((packed))  MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	DM0[7];

}__attribute__((packed))  HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	HCEAntiTheftRandomNumber_1632[7];

}__attribute__((packed))  HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	HCEAntiTheftCommand_1633:4;
	unsigned char	ESLMode_820:2;
	unsigned char	Reserved0:2;

	unsigned char	ESLInterval_825;

	unsigned char	HCEAntiTheftPasswordRepresentation_1634[10];

}__attribute__((packed))  HCE_ANTI_THEFT_REQUEST_61184_23;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	PasswordCertificationResult_956:4;
	unsigned char	Reserved0:4;

	unsigned char	DM0[6];

}__attribute__((packed))  HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	PasswordChangeResult_958:2;
	unsigned char	Reserved0:6;

	unsigned char	Reserved;		// should be filled with 0xFF

	unsigned char	HCEAntiTheftPasswordRepresentation_1634[10];


}__attribute__((packed))  HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	TestMode:2;
	unsigned char	CoolingFanReverseMode_182:2;
	unsigned char	CoolingFanReverseManual:2;
	unsigned char	Reserved0:2;

	unsigned char	CoolingFanValveCurrent_146;

	unsigned char	CoolingFanReverseIntervalTime_211;

	unsigned char	CoolingFanReverseOperatingTime_212;

	unsigned char	DM[3];

}__attribute__((packed))  COOLING_FAN_SETTING_61184_61;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	WeighingSystemAccumulationMode_1941:2;
	unsigned char	RequestReweighing:2;
	unsigned char	RequestTotalWorkWeightReset:4;

	unsigned char	WeightOffsetSelection:4;
	unsigned char	WeightOffsetSetting:4;

	unsigned short	WeightOffset_1922;

	unsigned char	WeighingDisplayMode1_1910:4;
	unsigned char 	Reserved0:4;

	unsigned char	DM0[2];

}__attribute__((packed))  WEIGHING_SYSTEM_SETTING_REQUEST_61184_62;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	WeightOffsetSelectionStatus:4;
	unsigned char	Reserved0:4;

	unsigned short	WeightOffsetWorkTool1_1922;

	unsigned short	WeightOffsetWorkTool2_1922;

	unsigned short	WeightOffsetWorkTool3_1922;


}__attribute__((packed))  WEIGHT_OFFSET_61184_63;

typedef struct
{
	unsigned char	MessageType;

	unsigned char 	ParallelLiftTargetAngleSettingErrorStatus_1924:2;
	unsigned char	ParallelLiftInhibitedStatus_1925:2;
	unsigned char	ParallelLiftOverloadStatus_1926:2;
	unsigned char 	Reserved0;

	unsigned char 	DM[6];

}__attribute__((packed))  PARALLEL_LIFT_OPERATION_STATUS_61184_65;

typedef struct
{
	unsigned char	MessageType;

	unsigned char	Reserved0;

	unsigned char	Reserved1:6;
	unsigned char	EngineAlternateLowIdleSwitch_348:2;

	unsigned char	Reserved2;

	unsigned char	Reserved3;

	unsigned char	Reserved4:4;
	unsigned char	EnginePowerMode_347:4;

	unsigned char	Reserved5;

	unsigned char	Reserved6;

}__attribute__((packed))  MACHINE_MODE_SETTING_61184_101;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	TransmisstionShiftMode_543:4;
	unsigned char	ClutchCutoffMode_544:4;

	unsigned char	KickDownShiftMode_547:2;
	unsigned char	TransmissionTCLockupEngaged_568:2;
	unsigned char	Reserved0:2;
	unsigned char	VehicleSpeedLimitMode_575:2;

	unsigned char	DM0[4];

}__attribute__((packed))  TRAVEL_MODE_SETTING_61184_104;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	SettingSelection:4;
	unsigned char	Reserved0:4;

	unsigned short	SpeedometerFrequency_534;

	unsigned char	AutoRideControlOperationSpeedForward_574;

	unsigned char	AutoRideControlOperationSpeedBackward_576;

	unsigned char	VehicleSpeedLimit_572;

	unsigned char	DM0;

}__attribute__((packed))  TRAVEL_CONTROL_VALUE_SETTING_61184_105;
typedef struct
{
	unsigned char	MessageType;

	unsigned short	SpeedometerFrequency_534;


	unsigned char	AutoRideControlOperationSpeedForward_574;

	unsigned char	AutoRideControlOperationSpeedBackward_576;

	unsigned char	VehicleSpeedLimit_572;

	unsigned char	DM0[2];

}__attribute__((packed))  TRAVEL_CONTROL_VALUE_61184_106;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	BacklightIlluminationLevel_719:4;
	unsigned char	WiperSpeedLevel_718:4;

	unsigned short	Clock_819;

	unsigned char	RequestEngineLowIdleSpeed:2;
	unsigned char	RequestTripDataReset:2;
	unsigned char	Reserved0:4;

	unsigned char	DM[3];

}__attribute__((packed))  MACHINE_ACCESSORY_SETTING_REQUEST_61184_109;

typedef struct
{
	unsigned char	MessageType;

	unsigned char	AutomaticEngineShutdown_363:2;
	unsigned char	AutomaticEngineShutdownTypeControlByte:2;
	unsigned char	Reserved0:4;

	unsigned char	SettingTimeforAutomaticEngineShutdown_364;

	unsigned char	EngineShutdownCotrolByte;

	unsigned char	DelayedEngineShutdown_365:2;
	unsigned char	Reserved1:6;

	unsigned char	SettingTimeofrDelayedEngineShutdown_366;

	unsigned char	DM0[2];

}__attribute__((packed))  ENGINE_SHUTDOWN_MODE_SETTING_61184_121;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	AutomaticEngineShutdown_363:2;
	unsigned char	AutomaticEngineShutdownType:2;
	unsigned char	Reserved0:4;

	unsigned char	SettingTimeforAutomaticEngineShutdown_364;

	unsigned char	RemainingTimeforAutomaticEngineShutdown;

	unsigned char	Reserved1;

	unsigned char	SettingTimeforDelayedEngineShutdown_366;

	unsigned short	RemainingTimeforDelayedEngineShutdown;

}__attribute__((packed))  ENGINE_SHUTDOWN_MODE_STATUS_61184_122;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	BoomDetentMode_223:3;
	unsigned char	BucketDetentMode_224:3;
	unsigned char	Reserved0:2;

	unsigned char 	RequestDetentReleasePositionSetting:4;
	unsigned char	Reserved1:4;

	unsigned char	DM0[5];


}__attribute__((packed))  DETENT_MODE_SETTING_61184_123;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	BoomDetentMode_223:3;
	unsigned char	BucketDetentMode_224:3;
	unsigned char	Reserved0:2;

	unsigned char 	DetentReleasePositionSettingStatus:4;
	unsigned char	Reserved1:4;

	unsigned char	DM0[5];

}__attribute__((packed))  DETENT_MODE_STATUS_61184_124;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	EngineShutdownCommand_3472:2;
	unsigned char	Reserved0:6;

	unsigned char	DM0[6];

}__attribute__((packed))  ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	ASPhoneNumber[21];

}__attribute__((packed))  AS_PHONE_NUMBER_SETTING_61184_151;

typedef struct
{
	unsigned char	MessageType;

	unsigned char	RequestBoomPressureCalibration:2;
	unsigned char 	Reserved0:2;
	unsigned char	RequestBoomBucketAngleSensorCalibration:4;

	unsigned char	RequestAEB:2;
	unsigned char	RequestBrakePedalPositionSensorCalibration:2;
	unsigned char	Reserved1:4;

	unsigned char	DM0[5];

}__attribute__((packed))  WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201;
typedef struct
{
	unsigned char	MessageType;

	unsigned char 	BoomPressureCalibrationStatus_1908:4;
	unsigned char	AngleSensorCalibrationStatus_1909:4;

	unsigned char	AEBStatusInformation_MainCode_562;

	unsigned char 	AEBCycleNumber_540;

	unsigned char	AEBStatusInformation_SubCode_563;

	unsigned char	BrakePedalPositionSensorCalibrationStatus_564;

	unsigned char	BrakePedalPositionSensorCalibration_FaultInformation_565;

	unsigned char	DM0;

}__attribute__((packed))  WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202;
typedef struct
{
	unsigned char	MessageType;

	unsigned char	BucketPriorityOperation_2301:2;
	unsigned char	FlowFineModulationOperation_2302:2;
	unsigned char	AuxiliaryAttachmentMaxFlowLevel:2;
	unsigned char	BoomLeverFloatingPosition:2;

	unsigned char	SoftStopBoomUp_2337:2;
	unsigned char	SoftStopBoomDown_2338:2;
	unsigned char	SoftStopBucketIn_2339:2;
	unsigned char	SoftStopBucketOut_2340:2;

	unsigned char	BoomDownSpeedAdjust:2;
	unsigned char	BucketOutSpeedAdjust:2;
	unsigned char	Reserved0:4;

	unsigned char	DM0[4];

}__attribute__((packed))  WHEEL_LOADER_EHCU_SETTING_61184_203;
typedef struct
{
	unsigned char	Reserved0:4;
	unsigned char	RequestBuzzerStop:2;
	unsigned char	SpeedmeterUnitChange:2;

	unsigned char	MonitorScreenNumber_836;

	unsigned char	DM0[6];

}__attribute__((packed))  MONIOTR_STATUS_65327;
typedef struct
{
	unsigned char	RMCUNetworkType_1621;

	unsigned char	RMCUBackupBatteryVoltage_1590;

	unsigned char	RMCUPowerSource_1594:2;
	unsigned char	RMCUBoxOpeningStatus:2;
	unsigned char	NetworkCommunicationStatus1_1622:2;
	unsigned char	Reserved0:2;

	unsigned char	PositionUpdateStatus_852:2;
	unsigned char	MachinePositionStatus_1593:2;
	unsigned char	GPSAntennaConnectionAlarmStatus_1595:2;
	unsigned char	Reserved1:2;

	unsigned char	NetworkTransceiverStatus1_1623;

	unsigned char	NetworkServiceStatus1_1624;

	unsigned char	NetworkAntennaStatus1_1625;

	unsigned char	RMCUData_NumberofMessagestoTransmit_855;

}__attribute__((packed))  RMCU_STATUS_65329;
typedef struct
{
	unsigned char	ComponentCode_1699;
	unsigned char	ManufacturerCode_1700;
	unsigned char	ComponentBasicInformation_1698[37];

}__attribute__((packed))  COMPONENT_IDENTIFICATION_65330;

typedef struct
{
	unsigned char	ComponentCode_1699;
	unsigned char	ManufacturerCode_1700;
	unsigned char	ComponentBasicInformation_1698[200];

}__attribute__((packed))  COMPONENT_IDENTIFICATION_ECM_65330;
typedef struct
{
	unsigned char	ComponentCode_1699;
	unsigned char	ManufacturerCode_1700;
	unsigned char	ComponentBasicInformation_1698[67];

}__attribute__((packed))  COMPONENT_IDENTIFICATION_TCU_65330;
typedef struct
{
	unsigned char	ComponentCode_1699;
	unsigned char	ManufacturerCode_1700;
	unsigned char	ComponentBasicInformation_1698[6];

}__attribute__((packed))  COMPONENT_IDENTIFICATION_ACU_65330;
typedef struct
{
	unsigned int	TripTime_849;

	unsigned char	DM0[4];

}__attribute__((packed))  TRIP_TIME_INFORMATION_65344;
typedef struct
{
	unsigned char	ESLMode_820:2;
	unsigned char	LockMode_822:2;
	unsigned char	LockLevel_823:4;

	unsigned char	LockDemander_824:4;
	unsigned char	EngineStartingPermission_821:2;
	unsigned char	PasswordIdentificationBlockedState_1631:2;

	unsigned char	ESLInterval_825;

	unsigned char	DM0[5];

}__attribute__((packed))  MACHINE_SECURITY_STATUS_65348;
typedef struct
{
	unsigned char	Reserved0;

	unsigned char	Reserved1:6;
	unsigned char	EngineAlternateLowIdelSwitch_348:2;

	unsigned char	Reserved2;

	unsigned char	Reserved3;

	unsigned char	Reserved4:4;
	unsigned char	EnginePowerMode_347:4;

	unsigned char	Reserved5;

	unsigned char	DM0[2];

}__attribute__((packed))  MACHINE_MODE_STATUS_65350;
typedef struct
{
	unsigned short 	Reserved0;

	unsigned short 	Reserved1;

	unsigned short	BrakeOilPressure_503;

	unsigned short	BrakeOilChargingPriorityPressure_557;

}__attribute__((packed))  HYDRAULIC_PRESSURE4_65354;
typedef struct
{
	unsigned short 	BoomCylinderHeadPressure_204;

	unsigned short	BoomCylinderRodPressure_205;

	unsigned short	Reserved0;

	unsigned short	Reserved1;

}__attribute__((packed))  HYDRAULIC_PRESSURE6_65356;
typedef struct
{
	unsigned short	SteeringMainPumpPressure_202;

	unsigned short	EmergencySteeringPumpPressure_203;

	unsigned short	ParkingOilPressure_507;

	unsigned short	DifferentialLockPressure_558;

}__attribute__((packed))  HYDRAULIC_PRESSURE7_65357;
typedef struct
{
	unsigned short	AlternatorVoltage_707;

	unsigned char	Reserved0;

	unsigned char	Reserved1;

	unsigned char	DM0[4];

}__attribute__((packed))  ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360;
typedef struct
{
	unsigned char	AntiRestartRelay_327:2;
	unsigned char	FuelWarmerRelay_325:2;
	unsigned char	EnginePreheatRelay_322:2;
	unsigned char	EngineStopRelay_345:2;

	unsigned char	WorkingCutoffRelay_164:2;
	unsigned char	TravelingCutoffRelay_517:2;
	unsigned char	ParkingRelay_514:2;
	unsigned char	WiperRelay_727:2;

	unsigned char	EmergencySteeringPumpRelay_187:2;
	unsigned char	TravelAlarmBuzzer_722:2;
	unsigned char	Buzzer_723:2;
	unsigned char	FuelCutoffRelay_324:2;

	unsigned char	DM0[5];

}__attribute__((packed))  RELAY_BUZZER_STATUS_65364;
typedef struct
{
	unsigned char	Reserved0[7];

	unsigned char	BoomUpLeverDetentSolenoid_172:2;
	unsigned char	BoomDownLeverDetentSolenoid_173:2;
	unsigned char	BucketLeverDetentSolenoid_174:2;
	unsigned char	Reserved1:2;


}__attribute__((packed))  SOLENOID_STATUS_65365;
typedef struct
{
	unsigned char	Reserved0;

	unsigned char	AcceleratorPedalPositionVoltage1_710;

	unsigned char	BrakePedalPositionVoltage_573;

	unsigned char	AcceleratorPedalPosition1_339;

	unsigned char	AcceleratorPedalPositionVoltage2_710;

	unsigned char	DM0[3];

}__attribute__((packed))  ACCELERATOR_BRAKE_PEDAL_STATUS_65368;
typedef struct
{
	unsigned char	CoolingFanReverseMode_182:2;
	unsigned char	CoolingFanReverseSolenoid_181:2;
	unsigned char	CoolingFanReverseSwitchManual_740:2;
	unsigned char	CoolingFanReverseSwitchAuto_741:2;

	unsigned char 	CoolingFanValveCurrent_146;

	unsigned short	CoolingFanSpeed_318;

	unsigned char	CoolingFanReverseIntervalTime_211;

	unsigned char	CoolingFanReverseOperatingTime_212;

	unsigned char	DM0[2];

}__attribute__((packed))  COOLING_FAN_STATUS_65369;
typedef struct
{
	unsigned short	EngineFuelRate_331;

	unsigned char 	EnginePercentLoadatCurrentSpeed_334;

	unsigned char	EngineActualPercentTorque_335;

	unsigned char	DM0[4];

}__attribute__((packed))  ENGINE_STATUS2_65370;
typedef struct
{
	unsigned char	EngineOperatingCondition_336:4;
	unsigned char	Reserved0:4;

	unsigned char	EngineIntakeManifold1Temperatue_329;

	unsigned char	EngineFuelTemperature_330;

	unsigned char	EngineOilPressure_311;

	unsigned char	BarometricPressure_328;

	unsigned char	EngineAirIntakePressure_337;

	unsigned char	DEFTankLevel_362;

	unsigned char	DM0;

}__attribute__((packed))  ENGINE_STATUS1_65371;

typedef struct
{
	unsigned char Reserved0;

	unsigned char Reserved1;

	unsigned char Reserved2;

	unsigned char FATCSettingTemperatureCelsius_3408;

	unsigned char FATCSettingTemperatureFahrenheit_3409;

	unsigned char Ambienttemperaturesensoropen:1;
	unsigned char Ambienttemperaturesensorshort:1;
	unsigned char Incabtemperaturesensoropen:1;
	unsigned char Incabtemperaturesensorshort:1;
	unsigned char Evaptemperaturesensoropen:1;
	unsigned char Evaptemperaturesensorshort:1;
	unsigned char Reserved3:2;

	unsigned char Mode1actuatoropenshort:1;
	unsigned char Mode1actuatordrivecircuitmalfunction:1;
	unsigned char Intakeactuatoropenshort:1;
	unsigned char Intakeactuatordrivecircuitmalfunction:1;
	unsigned char Temperatureactuatoropenshort:1;
	unsigned char Temperatureactuatordrivecircuitmalfunction:1;
	unsigned char Reserved4:2;

	unsigned char Ducttemperaturesensoropen:1;
	unsigned char Ducttemperaturesensorshort:1;
	unsigned char WaterValveSensorError:1;
	unsigned char GPSCircuitError:1;
	unsigned char Reserved:4;
}__attribute__((packed))  AIR_CONDITIONER_STATUS_65373;

typedef struct
{
	unsigned int	TripDistance_600;

	unsigned int	TotalVehicleDistance_601;

}__attribute__((packed))  VEHICLE_DISTANCE_65389;
typedef struct
{
	unsigned short	FuelLevel_302;									// HCESPN : 302

	unsigned char 	EcoGaugeLevel_1304;								// HCESPN : 1304

	unsigned char	EcoGaugeStatus_1305:4;							// HCESPN : 1305
	unsigned char 	Reserved0:4;

	unsigned short	CurrentFuelRate;

	unsigned short	AverageFuelRate;
}__attribute__((packed))  FUEL_INFORMATION_ECO_GAUGE_65390;
typedef struct
{
	unsigned short	BoomLinkAngle_1920;

	unsigned short	BellCrankAngle_1921;

	unsigned char	BoomLinkAngleSensorSignalVoltage_728;

	unsigned char	BellCrankAngleSensorSignalVoltage_729;

	unsigned short	BucketCylinderStroke_1930;

}__attribute__((packed))  CYLINDER_ANGLE_STROKE1_65395;
typedef struct
{
	unsigned short	BucketAngle_1931;

	unsigned char 	DM[6];

}__attribute__((packed))  CYLINDER_ANGLE_STROKE2_65396;
typedef struct
{
	//unsigned char	ASPhoneNum_D8;
	unsigned char	ASPhoneNumber_1095[21];

}__attribute__((packed))  AS_PHONE_NUMBER_65425;
typedef struct
{
	unsigned char	FuelLevelLow_303:2;
	unsigned char	HydraulicOilTemperatureHigh_102:2;
	unsigned char	BatteryVoltageLow_706:2;
	unsigned char	Overload_104:2;

	unsigned char	AirCleanerClog_317:2;
	unsigned char	EngineCheckLamp_320:2;
	unsigned char	TransmissionOilPressureLow_502:2;
	unsigned char	BrakeOilPressureLow_504:2;

	unsigned char	EngineCoolantTemperatureHigh_305:2;
	unsigned char	EngineCoolantLevelLow_307:2;
	unsigned char	EngineOilPressureLow_313:2;
	unsigned char	EngineOilFilterClog_315:2;

	unsigned char	TransmissionOilTemperatureHigh_537:2;
	unsigned char	TransmissionCheck_538:2;
	unsigned char	SteeringMainPumpPressureLow_184:2;
	unsigned char	EmergencySteeringActive_185:2;

	unsigned char	WarningSymbolLamp_709:2;
	unsigned char	WaterInFuelIndicator_360:2;
	unsigned char	DTCAlarmLamp_1509:2;
	unsigned char	EngineStopLamp_319:2;

	unsigned char	DPFLampCommand_352:3;
	unsigned char	DEFLowLamp_358:3;
	unsigned char	ClutchSlip_569:2;

	unsigned char	BrakeOilLevelLow_566:2;
	unsigned char	BrakeOilTemperatureHigh_567:2;
	unsigned char	Reserved0:4;

	unsigned char	EmissionSystemFailLamp_357:3;
	unsigned char	Reserved1:5;


}__attribute__((packed))  WARNING_LAMP_65427;
typedef struct
{
	unsigned char	PowerMaxStatus_802:2;
	unsigned char	DecelerationStatus_803:2;
	unsigned char	WarmingUpStatus_804:2;
	unsigned char	EnginePreheatStatus_323:2;

	unsigned char	FuelWarmerActiveStatus_326:2;
	unsigned char	CoolingFandrivingStatus_180:2;
	unsigned char	CruiseStatus_519:2;
	unsigned char	ParkingStatus_508:2;

	unsigned char	ClutchCutoffStatus_545:2;
	unsigned char	FNRJoystickActiveStatus_546:2;
	unsigned char	RideControlStatus_550:2;
	unsigned char	DifferentialLockStatus_559:2;

	unsigned char	JoystickSteeringActiveStatus_186:2;
	unsigned char	InchingStatus_549:2;
	unsigned char	RamLockStatus_520:2;
	unsigned char	WorkingBrakeStatus_506:2;

	unsigned char	MaintenanceAlarmLamp_1099:2;
	unsigned char	OperatorAbsenceStatus_832:2;
	unsigned char	MirrorHeaterStatus_724:2;
	unsigned char	HighBeamStatus_725:2;

	unsigned char	TravelAlarmSwitch_721:2;
	unsigned char	SwingBoomSwitch_726:2;
	unsigned char	ParkingWorkingTravelStatus_510:2;
	unsigned char	TransmissionTCLockupEngaged_556:2;

	unsigned char	ExhaustSystemHighTemperatureLampCommand_353:3;
	unsigned char	Reserved0:3;
	unsigned char	DieselParticulateFilterActiveRegenerationInhibitedDuetoInhibitSwitch_354:2;

	unsigned char	AutomaticEngineShutdown_363:2;
	unsigned char	DelayedEngineShutdown_365:2;
	unsigned char	AutoGreaseGreenLEDStatus_3453:2;
	unsigned char	AutoGreaseRedLEDStatus_3453:2;

}__attribute__((packed))  INDICATOR_LAMP_65428;
typedef struct
{
	unsigned char	FuelLevel_301;

	unsigned char	EngineCoolantTemperature_304;

	unsigned char	HydraulicOilTemperature_101;

	unsigned char	TransmissionOilTemperature_536;

	unsigned short	EngineSpeed_310;

	unsigned short	BatteryVoltage_705;

}__attribute__((packed))  GAUGE_65431;
typedef struct
{
	unsigned int	Hourmeter_1601;

	unsigned short	Clock_819;

	unsigned char	HourmeterActiveStatus_703:2;
	unsigned char	WiperOperationStatus_717:2;
	unsigned char	WiperSpeedLevel_718:4;

	unsigned char	BacklightIlluminationLevel_719:4;
	unsigned char	IlluminationStatus_737:2;
	unsigned char	Reserved0:2;


}__attribute__((packed))  HOURMETER_CLOCK_WIPER_65433;
typedef struct
{
	unsigned short	WheelBasedVehicleSpeed_532;

	unsigned char	SelectGear_541;

	unsigned char	ActualGear_542;

	unsigned char	TransmissionShiftMode_543:4;
	unsigned char	ClutchCutoffMode_544:4;

	unsigned char	KickDownShiftMode_547:2;
	unsigned char	TransmissionTCLockupEngaged_568:2;
	unsigned char	DifferentialLockMode_570:2;
	unsigned char	VehicleSpeedLimitMode_575:2;

	unsigned char	DM0[2];

}__attribute__((packed))  MACHINE_TRAVEL_STATUS_65434;
typedef struct
{
	unsigned char	DTCType_1510:4;
	unsigned char	NumberofDTCinThisPacket_1514:4;

	unsigned char	TotalNumberofDTC;

	unsigned char	TotalNumberofDTCInformationPacket_1512;

	unsigned char	SequenceNumberofDTCInformationPacket_1513;

	unsigned char	DTC_1[3];

	unsigned char	DTC_2[3];

	unsigned char	DTC_3[3];

	unsigned char	DTC_4[3];

	unsigned char	DTC_5[3];

}__attribute__((packed))  DTC_INFORMATION_TYPE1_65438;
typedef struct
{
	unsigned char	WeighingSystemAccumulationMode_1941:2;
	unsigned char	WeighingSystemBuzzer_1907:2;
	// ++, 150212 bwk
#if 0
	unsigned char	CurrentWeighingResult_1919:4;

	unsigned char	WeighingDisplayMode1_1910:4;
	unsigned char	Reserved0:4;
#else
	unsigned char	WeighingDisplayMode1_1910:4;
	unsigned char	CurrentWeighingResult_1919:2;
	unsigned char	WeighingSystemError_BoomLiftSpeed_1942:2;
	unsigned char	WeighingSystemError_BucketFullIn_1943:2;
	unsigned char	WeighingSystemError_HydraulicOilTemperature_1944:2;
#endif
	// --, 150212 bwk
 	unsigned short	CurrentWeight_1911;

	unsigned int	TodayWeight_1915;

}__attribute__((packed))  WEIGHING_SYSTEM_STATUS_65450;
typedef struct
{
	unsigned int	TotalWorkAWeight_1912;

	unsigned int	TotalWorkBWeight_1913;

}__attribute__((packed))  WEIGHING_SYSTEM_DATA1_65451;
typedef struct
{
	unsigned int	TotalWorkCWeight_1914;

	unsigned int	ADayBeforeWeight_1916;

}__attribute__((packed))  WEIGHING_SYSTEM_DATA2_65452;

typedef struct
{
	unsigned char	ButtonKeyPosition:2;
	unsigned char	Reserved0:6;

	unsigned char	DM[7];
}__attribute__((packed))  BKCU_STATUS_65514;

typedef struct
{
	unsigned char	BoomJoystickPositionStatus_2310:2;
	unsigned char	Reserved0:4;
	unsigned short	BoomJoystickPosition_2311:10;

	unsigned char	BucketJoystickPositionStatus_2312:2;
	unsigned char	Reserved1:4;
	unsigned short	BucketJoystickPosition_2313:10;

	unsigned char	AuxJoystickPositionStatus_2314:2;
	unsigned char	Reserved2:4;
	unsigned short	AuxJoystickPosition_2315:10;

	unsigned char	BoomLeverFloatingPosition_2336;

	unsigned char	DM0;

}__attribute__((packed))  JOYSTICK_POSITION_STATUS_65515;
typedef struct
{
	unsigned char	BucketPriorityOperation_2301:2;
	unsigned char	FlowFineModulationOperation_2302:2;
	unsigned char	AuxiliaryAttachmentMaxFlowLevel_2303:4;

	unsigned char	AttachmentPilotCutoffStatus_225:2;
	unsigned char	FloatMode_2316:2;
	unsigned char	JoystickSteeringActiveStatusEHCU_186:2;
	unsigned char	Reserved0:2;

	unsigned char	BoomUpEPPRValveCurrent_2304;

	unsigned char	BoomDownEPPRValveCurrent_2305;

	unsigned char	BucketInEPPRValveCurrent_2306;

	unsigned char	BucketOutEPPRValveCurrent_2307;

	unsigned char	AUX1EPPRValveCurrent_2308;

	unsigned char	AUX2EPPRValveCurrent_2309;
}__attribute__((packed))  WHEEL_LOADER_EHCU_STATUS_65517;
typedef struct
{
	unsigned char	SoftStopBoomUp_2337:2;
	unsigned char	SoftStopBoomDown_2338:2;
	unsigned char	SoftStopBucketIn_2339:2;
	unsigned char	SoftStopBucketOut_2340:2;

	unsigned char	BoomDownEPPRValveMaxCurrent_2341;

	unsigned char	BucketOutEPPRValveMaxCurrent_2342;

	unsigned short	JoystickSteeringEnableFailCondition_2343;

	unsigned char	DM[3];
}__attribute__((packed))  WHEEL_LOADER_EHCU_STATUS2_65524;
typedef struct
{
	unsigned char	TravelAlarmOperationStatus_3431:2;
	unsigned char	WasherOperationStatus_3432:2;
	unsigned char	wiperoperationstatus_3433:2;
	unsigned char	IntWiperOperationStatus_3434:2;

	unsigned char	WorkLampOperationStatus_3435:2;
	unsigned char	HeadLampOperationStatus_3436:2;
	unsigned char	CabinLampOperationStatus_3437:2;
	unsigned char	IlluminationOperationStatus_3438:2;

	unsigned char	OutriggerFrontRHOperationStatus_3439:2;
	unsigned char	OutriggerFrontLHOperationStatus_3440:2;
	unsigned char	OutriggerRearRHOperationStatus_3441:2;
	unsigned char	OutriggerRearLHOperationStatus_3442:2;

	unsigned char	OverloadSwitchOperationStatus_3443:2;
	unsigned char	BeaconLampOperationStatus_3444:2;
	unsigned char	ForwardTravelAlarmOperationStatus_3445:2;
	unsigned char	RearWorkLampOperationStatus_3446:2;

	unsigned char	RideControlOperationStatus_3447:2;
	unsigned char	QuickCouplerOperationStatus_3448:2;
	unsigned char	AutoGreaseOperationStatus_3449:2;
	unsigned char	MirrorHeatOperationStatus_3450:2;

	unsigned char	RearWiperOperationStatus_3451:2;
	unsigned char	RearWiperWasherOperationStatus_3452:2;
	unsigned char	Reserved0:4;

	unsigned char	DM0[2];


}__attribute__((packed))  ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527;
#endif
