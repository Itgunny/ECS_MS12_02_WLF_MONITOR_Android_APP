#include "CAN_DataParsing.h"

#define NEW_CAN2

///////////////////////////// Timer////////////////////////////////////////////////////////////////
timer_t firstTimerID;
timer_t SecondTimerID;

static void timer_handler( int sig, siginfo_t *si, void *uc )
{
	timer_t *tidp;
	tidp = si->si_value.sival_ptr;

	if ( *tidp == firstTimerID )
	{
//		__android_log_print(ANDROID_LOG_INFO, "Timer","Timer1\n");
//		SendDataFromRingBuffer();
//		if(nCTSFlag_MCU == 1)
//		{
//			Send_RTSData(&RTSData[0],nRTSDataLength,SA_MCU);
//			nCTSFlag_MCU = 0;
//		}
//		if(KeyButtonCount < KEYBUTTON_CNT_MAX){
//			KeyButtonCount++;
//		}
		//sleep(0);
		//timer_delete(*tidp);		// Timer Kill
	}

	else if ( *tidp == SecondTimerID )
	{
		__android_log_print(ANDROID_LOG_INFO, "Timer","Timer2\n");
		//timer_delete(SecondTimerID);	// Timer Kill
	}
}

static int makeTimer( char *name, timer_t *timerID, int sec, int msec )
{
	struct sigevent         te;
	struct itimerspec       its;
	struct sigaction        sa;
	int                     sigNo = SIGRTMIN;

	/* Set up signal handler. */
	sa.sa_flags = SA_SIGINFO;
	sa.sa_sigaction = timer_handler;
	sigemptyset(&sa.sa_mask);

	if (sigaction(sigNo, &sa, NULL) == -1)
	{
		printf("sigaction error\n");
		return -1;
	}

	/* Set and enable alarm */
	te.sigev_notify = SIGEV_SIGNAL;
	te.sigev_signo = sigNo;
	te.sigev_value.sival_ptr = timerID;
	timer_create(CLOCK_REALTIME, &te, timerID);

	its.it_interval.tv_sec = sec;
	its.it_interval.tv_nsec = msec * 1000000;
	its.it_value.tv_sec = sec;

	its.it_value.tv_nsec = msec * 1000000;
	timer_settime(*timerID, 0, &its, NULL);

	return 0;
}
//TxRingBuff에 메모리 세팅을 한다.
void SetDatatoRingBuffer(unsigned char* Buf)
{
	pthread_mutex_lock(&mutex_UART1_tx);
	memcpy((unsigned char*)&UART1_TxBuff[TxRingBuffHead][0],&Buf[0],UART1_TXPACKET_SIZE);

	if(TxRingBuffHead >= RINGBUFF_SIZE - 1 )
		TxRingBuffHead = 0;
	else
		TxRingBuffHead++;
	pthread_mutex_unlock(&mutex_UART1_tx);
}
//CANData(Single)를 Buf에 저장한 후 값을 저장한다.
void MakeCANDataSingle(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char SA, unsigned char *Data)
{
	unsigned char Buf[UART1_TXPACKET_SIZE]; //UART1_TXPACKET_SIZE 17
	int i;
	// ex) 18FF3247
	Buf[0] = SERIAL_RX_STX; //0x02
	Buf[1] = Priority;
	Buf[2] = PF;
	Buf[3] = PS;
	Buf[4] = SA;
	// data
	for(i = 0; i < 8; i++)
		Buf[i + 5] = Data[i];
	Buf[UART1_TXPACKET_SIZE-1] = SERIAL_RX_ETX;

	SetDatatoRingBuffer(Buf);
}

void MakeCANDataMultiBoradcast(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char DA, unsigned char SA, unsigned char *Data, unsigned int Length)
{
	unsigned char Buf[UART1_TXPACKET_SIZE];
	int PacketNum;
	int i;
    // MultiPacket Data 부분 PacketNumber
	if(Length % 7 == 0)
	{
		PacketNum = Length / 7;
	}
	else
	{
		PacketNum = (Length / 7) + 1;
	}


	//////////////TP.CM_BAM///////////////////////
	Buf[0] = SERIAL_RX_STX; // 0x02
	Buf[1] = Priority; // 0x1C
	Buf[2] = 0xEC; // 0xEC
	Buf[3] = DA; // Destination Address
	Buf[4] = SA; // Source Address
	Buf[5] = 0x20; // Control Bit
	Buf[6] = (Length & 0x00FF); // Length 1
	Buf[7] = (Length & 0xFF00) >> 8; //Length 11
	Buf[8] = PacketNum; //Packet Number
	Buf[9] = 0xFF;
	Buf[10] = PS;//PGN Specific
	Buf[11] = PF;//PGN
	Buf[12] = 0;
	Buf[UART1_TXPACKET_SIZE-1] = SERIAL_RX_ETX; // 0x03

	SetDatatoRingBuffer(Buf);
	//////////////////////////////////////////

	////////////////TP.DT/////////////////////
	// Make MultiDataPacket
	for(i = 0; i < PacketNum; i++){
		MakeMultiPacketData(Priority,PF,PS,DA,SA,&Data[i*7],Length,i);
	}


	//////////////////////////////////////////
}

void MakeMultiPacketData(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char DA, unsigned char SA, unsigned char* Data, unsigned int Length, unsigned int Index)
{
	unsigned char Buf[UART1_TXPACKET_SIZE]; // 14
	memset((unsigned char*) &Buf[0], 0xFF,sizeof(Buf));
	int i;
	Buf[0] = SERIAL_RX_STX; //0x02
	Buf[1] = Priority; //0x1C
	Buf[2] = 0xEB; //0xEB
	Buf[3] = DA; //Destination Address
	Buf[4] = SA; //Source Address
	Buf[5] = Index+1; // Index == 0 이면 0번째 PacketNum -> Packet Data(7) 완료

	for(i = 0; i < 7; i++){
		if((Index * 7) + i >= Length + 1)
			break;
		else
			Buf[i + 6] = Data[i];
	}


	Buf[UART1_TXPACKET_SIZE-1] = SERIAL_RX_ETX;

	SetDatatoRingBuffer(Buf);
}
int SendDataFromRingBuffer()
{
	unsigned char Buf[UART1_TXPACKET_SIZE];
	int result = 0;

	pthread_mutex_lock(&mutex_UART1_tx);
	//Ring Buffer의 마지막이 아니면
	if(TxRingBuffHead != TxRingBuffTail)
	{
		// 보내는 패킷을 메모리 할당을 하고
		memcpy((unsigned char*)&Buf[0],&UART1_TxBuff[TxRingBuffTail][0],UART1_TXPACKET_SIZE);
		// Tail이 RingBuffSize를 초과하면
		if(TxRingBuffTail >= RINGBUFF_SIZE - 1)
			TxRingBuffTail = 0; // TxRingBuffTail = 0;
		else
			TxRingBuffTail++; // 아니면 Tail 증가
		result = write(fd_UART1, Buf, UART1_TXPACKET_SIZE);


		__android_log_print(ANDROID_LOG_INFO, "RingBuffer","SendDataFromRingBuffer Head[%d] Tail[%d]\n",TxRingBuffHead,TxRingBuffTail);
	}
	pthread_mutex_unlock(&mutex_UART1_tx);
	return result;
}
// ClearToSend Packet
void Send_CTS(unsigned char Priority,unsigned char DA, unsigned char SA, unsigned char* Data)
{
	unsigned char Buf[UART1_TXPACKET_SIZE];
	int i;
	Buf[0] = SERIAL_RX_STX;
	Buf[1] = Priority;
	Buf[2] = 0xEC;
	Buf[3] = DA;
	Buf[4] = SA;
	Buf[5] = 0x11;		// CTS
	Buf[6] = 0xFF;		//Data[3];
	Buf[7] = 1;
	Buf[8] = 0xFF;
	Buf[9] = 0xFF;
	Buf[10] = Data[5];
	Buf[11] = Data[6];
	Buf[12] = Data[7];
	Buf[UART1_TXPACKET_SIZE-1] = SERIAL_RX_ETX;

	SetDatatoRingBuffer(Buf);
}
void Send_ACK(unsigned char Priority, unsigned char DA, unsigned char SA, unsigned char* Data)
{
	unsigned char Buf[UART1_TXPACKET_SIZE];
	int i;
	Buf[0] = SERIAL_RX_STX;
	Buf[1] = Priority;
	Buf[2] = 0xEC;
	Buf[3] = DA;
	Buf[4] = SA;
	Buf[5] = 0x13;		// ACK
	Buf[6] = Data[1];
	Buf[7] = Data[2];
	Buf[8] = Data[3];
	Buf[9] = Data[4];
	Buf[10] = Data[5];
	Buf[11] = Data[6];
	Buf[12] = Data[7];
	Buf[UART1_TXPACKET_SIZE-1] = SERIAL_RX_ETX;

	SetDatatoRingBuffer(Buf);
}
// RequestToSend Packet
void Send_RTS(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char DA, unsigned char SA, unsigned short Size)
{
	unsigned char _TotalPacketNum;
	unsigned char Buf[UART1_TXPACKET_SIZE];
	int i;

	if(Size % 7 == 0){
		_TotalPacketNum = Size / 7;
	}else{
		_TotalPacketNum = (Size / 7) + 1;
	}

	Buf[0] = SERIAL_RX_STX;
	Buf[1] = Priority;
	Buf[2] = 0xEC;
	Buf[3] = DA;
	Buf[4] = SA;
	Buf[5] = 0x10;		// RTS
	Buf[6] = Size & 0x00FF;
	Buf[7] = (Size & 0xFF00) >> 8;
	Buf[8] = _TotalPacketNum;
	Buf[9] = 0xFF;
	Buf[10] = PS;
	Buf[11] = PF;
	Buf[12] = 0x00;
	Buf[UART1_TXPACKET_SIZE-1] = SERIAL_RX_ETX;

	SetDatatoRingBuffer(Buf);
}

void Send_RTSData(unsigned char* Data, unsigned int Length, unsigned char DA)
{

	int i;
	int PacketNum;

	if(Length % 7 == 0)
		PacketNum = Length / 7;
	else
		PacketNum = (Length / 7) + 1;

	for(i = 0; i < PacketNum; i++)
		MakeMultiPacketData(0x1C,0xEF,0x00,DA,SA_MONITOR,&Data[i*7],Length,i);
}

void Send_ASPhoneNumber()
{
	int Length = 1;
	int i;
	for(i = 0; i < sizeof(TX_AS_PHONE_NUMBER_65425); i++){
		if(TX_AS_PHONE_NUMBER_65425.ASPhoneNumber_1095[i] == 0x2A)
			break;
		else
			Length++;
	}
	MakeCANDataMultiBoradcast(0x1C,0xFF,0x91,0xFF,SA_MONITOR,&TX_AS_PHONE_NUMBER_65425,Length);
}


void Send_CID()
{
	int Length = 2;
	int Cnt=0;
	int i;
	for(i = 0; i < 37; i++){
		if(TX_COMPONENT_IDENTIFICATION_MONITOR_65330.ComponentBasicInformation_1698[i] == 0x2A)
		{
			Length++;
			if(++Cnt == 2)
				break;
		}
		else
			Length++;
	}

	MakeCANDataMultiBoradcast(0x1C,0xFF,0x32,0xFF,SA_MONITOR,&TX_COMPONENT_IDENTIFICATION_MONITOR_65330,Length+1);
}



//////////////////////////////////////////////////////////////////////////////////////////////////



// Protocol 등록
void InitNewProtoclValuable() {

	memset((unsigned char*) &RX_DTC_INFORMATION_REQUEST_61184_11, 0xFF,
		sizeof(RX_DTC_INFORMATION_REQUEST_61184_11));
	memset((unsigned char*) &RX_MAINTENANCE_REQUSET_61184_12, 0xFF,
		sizeof(RX_MAINTENANCE_REQUSET_61184_12));
	memset((unsigned char*) &RX_MAINTENANCE_ITEM_LIST_61184_13, 0xFF,
		sizeof(RX_MAINTENANCE_ITEM_LIST_61184_13));
	memset((unsigned char*) &RX_MAINTENANCE_INFORMATION_61184_14, 0xFF,
		sizeof(RX_MAINTENANCE_INFORMATION_61184_14));
	memset((unsigned char*) &RX_MAINTENANCE_HISTORY_61184_15, 0xFF,
		sizeof(RX_MAINTENANCE_HISTORY_61184_15));
	memset((unsigned char*) &RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16,
		0xFF, sizeof(RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16));
	memset((unsigned char*) &RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21,
		0xFF, sizeof(RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21));
	memset((unsigned char*) &RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22, 0xFF,
		sizeof(RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22));
	memset((unsigned char*) &RX_HCE_ANTI_THEFT_REQUEST_61184_23, 0xFF,
		sizeof(RX_HCE_ANTI_THEFT_REQUEST_61184_23));
	memset((unsigned char*) &RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24,
		0xFF, sizeof(RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24));
	memset((unsigned char*) &RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25,
		0xFF, sizeof(RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25));
	memset((unsigned char*) &RX_AVERAGE_FUEL_RATE_HISTORY_61184_33,
		0xFF, sizeof(RX_AVERAGE_FUEL_RATE_HISTORY_61184_33));
	memset((unsigned char*) &RX_COOLING_FAN_SETTING_61184_61, 0xFF,
		sizeof(RX_COOLING_FAN_SETTING_61184_61));
	memset((unsigned char*) &RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62, 0xFF,
		sizeof(RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62));
	memset((unsigned char*) &RX_WEIGHT_OFFSET_61184_63, 0xFF,
		sizeof(RX_WEIGHT_OFFSET_61184_63));
	memset((unsigned char*) &RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65, 0xFF,
		sizeof(RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65));
	memset((unsigned char*) &RX_MACHINE_MODE_SETTING_61184_101, 0xFF,
		sizeof(RX_MACHINE_MODE_SETTING_61184_101));
	memset((unsigned char*) &RX_TRAVEL_MODE_SETTING_61184_104, 0xFF,
		sizeof(RX_TRAVEL_MODE_SETTING_61184_104));
	memset((unsigned char*) &RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105, 0xFF,
		sizeof(RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105));
	memset((unsigned char*) &RX_TRAVEL_CONTROL_VALUE_61184_106, 0xFF,
		sizeof(RX_TRAVEL_CONTROL_VALUE_61184_106));
	memset((unsigned char*) &RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109,
		0xFF, sizeof(RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109));
	memset((unsigned char*) &RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121, 0xFF,
		sizeof(RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121));
	memset((unsigned char*) &RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122, 0xFF,
		sizeof(RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122));
	memset((unsigned char*) &RX_DETENT_MODE_SETTING_61184_123, 0xFF,
		sizeof(RX_DETENT_MODE_SETTING_61184_123));
	memset((unsigned char*) &RX_DETENT_MODE_STATUS_61184_124, 0xFF,
		sizeof(RX_DETENT_MODE_STATUS_61184_124));
	memset((unsigned char*) &RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129,
		0xFF, sizeof(RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129));
	memset((unsigned char*) &RX_AS_PHONE_NUMBER_SETTING_61184_151, 0xFF,
		sizeof(RX_AS_PHONE_NUMBER_SETTING_61184_151));
	memset(
		(unsigned char*) &RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201,
		0xFF, sizeof(RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201));
	memset(
		(unsigned char*) &RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202,
		0xFF, sizeof(RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202));
	memset((unsigned char*) &RX_WHEEL_LOADER_EHCU_SETTING_61184_203, 0xFF,
		sizeof(RX_WHEEL_LOADER_EHCU_SETTING_61184_203));
	memset((unsigned char*) &RX_MONIOTR_STATUS_65327, 0xFF,
		sizeof(RX_MONIOTR_STATUS_65327));
	memset((unsigned char*) &RX_RMCU_STATUS_65329, 0xFF,
		sizeof(RX_RMCU_STATUS_65329));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_65330, 0xFF,
		sizeof(RX_COMPONENT_IDENTIFICATION_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_ECM_65330, 0xFF,
		sizeof(RX_COMPONENT_IDENTIFICATION_ECM_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_TCU_65330, 0xFF,
		sizeof(RX_COMPONENT_IDENTIFICATION_TCU_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_ACU_65330, 0xFF,
			sizeof(RX_COMPONENT_IDENTIFICATION_ACU_65330));
	memset((unsigned char*) &RX_TRIP_TIME_INFORMATION_65344, 0xFF,
		sizeof(RX_TRIP_TIME_INFORMATION_65344));
	memset((unsigned char*) &RX_MACHINE_SECURITY_STATUS_65348, 0xFF,
		sizeof(RX_MACHINE_SECURITY_STATUS_65348));
	memset((unsigned char*) &RX_MACHINE_MODE_STATUS_65350, 0xFF,
		sizeof(RX_MACHINE_MODE_STATUS_65350));
	memset((unsigned char*) &RX_HYDRAULIC_PRESSURE4_65354, 0xFF,
		sizeof(RX_HYDRAULIC_PRESSURE4_65354));
	memset((unsigned char*) &RX_HYDRAULIC_PRESSURE6_65356, 0xFF,
		sizeof(RX_HYDRAULIC_PRESSURE6_65356));
	memset((unsigned char*) &RX_HYDRAULIC_PRESSURE7_65357, 0xFF,
		sizeof(RX_HYDRAULIC_PRESSURE7_65357));
	memset((unsigned char*) &RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360, 0xFF,
		sizeof(RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360));
	memset((unsigned char*) &RX_RELAY_BUZZER_STATUS_65364, 0xFF,
		sizeof(RX_RELAY_BUZZER_STATUS_65364));
	memset((unsigned char*) &RX_SOLENOID_STATUS_65365, 0xFF,
		sizeof(RX_SOLENOID_STATUS_65365));
	memset((unsigned char*) &RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368, 0xFF,
		sizeof(RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368));
	memset((unsigned char*) &RX_COOLING_FAN_STATUS_65369, 0xFF,
		sizeof(RX_COOLING_FAN_STATUS_65369));
	memset((unsigned char*) &RX_ENGINE_STATUS2_65370, 0xFF,
			sizeof(RX_ENGINE_STATUS2_65370));
	memset((unsigned char*) &RX_ENGINE_STATUS1_65371, 0xFF,
		sizeof(RX_ENGINE_STATUS1_65371));
	memset((unsigned char*) &RX_AIR_CONDITIONER_STATUS_65373, 0xFF,
			sizeof(RX_AIR_CONDITIONER_STATUS_65373));
	memset((unsigned char*) &RX_VEHICLE_DISTANCE_65389, 0xFF,
		sizeof(RX_VEHICLE_DISTANCE_65389));
	memset((unsigned char*) &RX_FUEL_INFORMATION_ECO_GAUGE_65390, 0xFF,
		sizeof(RX_FUEL_INFORMATION_ECO_GAUGE_65390));					;
	memset((unsigned char*) &RX_CYLINDER_ANGLE_STROKE1_65395, 0xFF,
		sizeof(RX_CYLINDER_ANGLE_STROKE1_65395));
	memset((unsigned char*) &RX_CYLINDER_ANGLE_STROKE2_65396, 0xFF,
		sizeof(RX_CYLINDER_ANGLE_STROKE2_65396));
	memset((unsigned char*) &RX_AS_PHONE_NUMBER_65425, 0xFF,
		sizeof(RX_AS_PHONE_NUMBER_65425));
	memset((unsigned char*) &RX_WARNING_LAMP_65427, 0xFF,
		sizeof(RX_WARNING_LAMP_65427));
	memset((unsigned char*) &RX_INDICATOR_LAMP_65428, 0xFF,
		sizeof(RX_INDICATOR_LAMP_65428));
	memset((unsigned char*) &RX_GAUGE_65431, 0xFF, sizeof(RX_GAUGE_65431));
	memset((unsigned char*) &RX_HOURMETER_CLOCK_WIPER_65433, 0xFF,
		sizeof(RX_HOURMETER_CLOCK_WIPER_65433));
	memset((unsigned char*) &RX_MACHINE_TRAVEL_STATUS_65434, 0xFF,
		sizeof(RX_MACHINE_TRAVEL_STATUS_65434));
	memset((unsigned char*) &RX_DTC_INFORMATION_TYPE1_65438, 0xFF,
		sizeof(RX_DTC_INFORMATION_TYPE1_65438));
	memset((unsigned char*) &RX_CLUSTER_STATUS_65445, 0xFF,
		sizeof(RX_CLUSTER_STATUS_65445));
	memset((unsigned char*) &RX_AXLE_STATUS_65449, 0xFF,
		sizeof(RX_AXLE_STATUS_65449));
	memset((unsigned char*) &RX_WEIGHING_SYSTEM_STATUS_65450, 0xFF,
		sizeof(RX_WEIGHING_SYSTEM_STATUS_65450));
	memset((unsigned char*) &RX_WEIGHING_SYSTEM_DATA1_65451, 0xFF,
		sizeof(RX_WEIGHING_SYSTEM_DATA1_65451));
	memset((unsigned char*) &RX_WEIGHING_SYSTEM_DATA2_65452, 0xFF,
		sizeof(RX_WEIGHING_SYSTEM_DATA2_65452));
	memset((unsigned char*) &RX_BKCU_STATUS_65514, 0xFF,
		sizeof(RX_BKCU_STATUS_65514));
	memset((unsigned char*) &RX_JOYSTICK_POSITION_STATUS_65515, 0xFF,
		sizeof(RX_JOYSTICK_POSITION_STATUS_65515));
	memset((unsigned char*) &RX_WHEEL_LOADER_EHCU_STATUS_65517, 0xFF,
		sizeof(RX_WHEEL_LOADER_EHCU_STATUS_65517));
	memset((unsigned char*) &RX_AMBIENT_CONDITIONS_65519, 0xFF,
		sizeof(RX_AMBIENT_CONDITIONS_65519));
	memset((unsigned char*) &RX_WHEEL_LOADER_EHCU_STATUS2_65524, 0xFF,
			sizeof(RX_WHEEL_LOADER_EHCU_STATUS2_65524));
	memset((unsigned char*) &RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527,
		0xFF, sizeof(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527));


	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_CLUSTER_65330,
		0xFF, sizeof(RX_COMPONENT_IDENTIFICATION_CLUSTER_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_MONITOR_65330,
		0xFF, sizeof(RX_COMPONENT_IDENTIFICATION_MONITOR_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_RMCU_65330,
		0xFF, sizeof(RX_COMPONENT_IDENTIFICATION_RMCU_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_EHCU_65330,
		0xFF, sizeof(RX_COMPONENT_IDENTIFICATION_EHCU_65330));
	memset((unsigned char*) &RX_COMPONENT_IDENTIFICATION_BKCU_65330,
		0xFF, sizeof(RX_COMPONENT_IDENTIFICATION_BKCU_65330));


	memset((unsigned char*) &TX_DTC_INFORMATION_REQUEST_61184_11, 0xFF,
		sizeof(TX_DTC_INFORMATION_REQUEST_61184_11));
	memset((unsigned char*) &TX_MAINTENANCE_REQUSET_61184_12, 0xFF,
		sizeof(TX_MAINTENANCE_REQUSET_61184_12));
	memset((unsigned char*) &TX_MAINTENANCE_ITEM_LIST_61184_13, 0xFF,
		sizeof(TX_MAINTENANCE_ITEM_LIST_61184_13));
	memset((unsigned char*) &TX_MAINTENANCE_INFORMATION_61184_14, 0xFF,
		sizeof(TX_MAINTENANCE_INFORMATION_61184_14));
	memset((unsigned char*) &TX_MAINTENANCE_HISTORY_61184_15, 0xFF,
		sizeof(TX_MAINTENANCE_HISTORY_61184_15));
	memset((unsigned char*) &TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16,
		0xFF, sizeof(TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16));
	memset((unsigned char*) &TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21,
		0xFF, sizeof(TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21));
	memset((unsigned char*) &TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22, 0xFF,
		sizeof(TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22));
	memset((unsigned char*) &TX_HCE_ANTI_THEFT_REQUEST_61184_23, 0xFF,
		sizeof(TX_HCE_ANTI_THEFT_REQUEST_61184_23));
	memset((unsigned char*) &TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24,
		0xFF, sizeof(TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24));
	memset((unsigned char*) &TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25,
		0xFF, sizeof(TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25));
	memset((unsigned char*) &TX_OPERATION_HISTORY_REQUEST_61184_31,
		0xFF, sizeof(TX_OPERATION_HISTORY_REQUEST_61184_31));
	memset((unsigned char*) &TX_COOLING_FAN_SETTING_61184_61, 0xFF,
		sizeof(TX_COOLING_FAN_SETTING_61184_61));
	memset((unsigned char*) &TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62, 0xFF,
		sizeof(TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62));
	memset((unsigned char*) &TX_WEIGHT_OFFSET_61184_63, 0xFF,
		sizeof(TX_WEIGHT_OFFSET_61184_63));
	memset((unsigned char*) &TX_MACHINE_MODE_SETTING_61184_101, 0xFF,
		sizeof(TX_MACHINE_MODE_SETTING_61184_101));
	memset((unsigned char*) &TX_TRAVEL_MODE_SETTING_61184_104, 0xFF,
		sizeof(TX_TRAVEL_MODE_SETTING_61184_104));
	memset((unsigned char*) &TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105, 0xFF,
		sizeof(TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105));
	memset((unsigned char*) &TX_TRAVEL_CONTROL_VALUE_61184_106, 0xFF,
		sizeof(TX_TRAVEL_CONTROL_VALUE_61184_106));
	memset((unsigned char*) &TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109,
		0xFF, sizeof(TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109));
	memset((unsigned char*) &TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121, 0xFF,
		sizeof(TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121));
	memset((unsigned char*) &TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122, 0xFF,
		sizeof(TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122));
	memset((unsigned char*) &TX_DETENT_MODE_SETTING_61184_123, 0xFF,
		sizeof(TX_DETENT_MODE_SETTING_61184_123));
	memset((unsigned char*) &TX_DETENT_MODE_STATUS_61184_124, 0xFF,
		sizeof(TX_DETENT_MODE_STATUS_61184_124));
	memset((unsigned char*) &TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129,
		0xFF, sizeof(TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129));
	memset((unsigned char*) &TX_AS_PHONE_NUMBER_SETTING_61184_151, 0xFF,
		sizeof(TX_AS_PHONE_NUMBER_SETTING_61184_151));
	memset(
		(unsigned char*) &TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201,
		0xFF, sizeof(TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201));
	memset(
		(unsigned char*) &TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202,
		0xFF, sizeof(TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202));
	memset((unsigned char*) &TX_WHEEL_LOADER_EHCU_SETTING_61184_203, 0xFF,
		sizeof(TX_WHEEL_LOADER_EHCU_SETTING_61184_203));
	memset((unsigned char*) &TX_MONIOTR_STATUS_65327, 0xFF,
		sizeof(TX_MONIOTR_STATUS_65327));
	memset((unsigned char*) &TX_RMCU_STATUS_65329, 0xFF,
		sizeof(TX_RMCU_STATUS_65329));
	memset((unsigned char*) &TX_COMPONENT_IDENTIFICATION_65330, 0xFF,
		sizeof(TX_COMPONENT_IDENTIFICATION_65330));
	memset((unsigned char*) &TX_COMPONENT_IDENTIFICATION_ECM_65330, 0xFF,
		sizeof(TX_COMPONENT_IDENTIFICATION_ECM_65330));
	memset((unsigned char*) &TX_COMPONENT_IDENTIFICATION_TCU_65330, 0xFF,
		sizeof(TX_COMPONENT_IDENTIFICATION_TCU_65330));
	memset((unsigned char*) &TX_TRIP_TIME_INFORMATION_65344, 0xFF,
		sizeof(TX_TRIP_TIME_INFORMATION_65344));
	memset((unsigned char*) &TX_MACHINE_SECURITY_STATUS_65348, 0xFF,
		sizeof(TX_MACHINE_SECURITY_STATUS_65348));
	memset((unsigned char*) &TX_MACHINE_MODE_STATUS_65350, 0xFF,
		sizeof(TX_MACHINE_MODE_STATUS_65350));
	memset((unsigned char*) &TX_HYDRAULIC_PRESSURE4_65354, 0xFF,
		sizeof(TX_HYDRAULIC_PRESSURE4_65354));
	memset((unsigned char*) &TX_HYDRAULIC_PRESSURE6_65356, 0xFF,
		sizeof(TX_HYDRAULIC_PRESSURE6_65356));
	memset((unsigned char*) &TX_HYDRAULIC_PRESSURE7_65357, 0xFF,
		sizeof(TX_HYDRAULIC_PRESSURE7_65357));
	memset((unsigned char*) &TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360, 0xFF,
		sizeof(TX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360));
	memset((unsigned char*) &TX_RELAY_BUZZER_STATUS_65364, 0xFF,
		sizeof(TX_RELAY_BUZZER_STATUS_65364));
	memset((unsigned char*) &TX_SOLENOID_STATUS_65365, 0xFF,
		sizeof(TX_SOLENOID_STATUS_65365));
	memset((unsigned char*) &TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368, 0xFF,
		sizeof(TX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368));
	memset((unsigned char*) &TX_COOLING_FAN_STATUS_65369, 0xFF,
		sizeof(TX_COOLING_FAN_STATUS_65369));
	memset((unsigned char*) &TX_ENGINE_STATUS1_65371, 0xFF,
		sizeof(TX_ENGINE_STATUS1_65371));
	memset((unsigned char*) &TX_VEHICLE_DISTANCE_65389, 0xFF,
		sizeof(TX_VEHICLE_DISTANCE_65389));
	memset((unsigned char*) &TX_FUEL_INFORMATION_ECO_GAUGE_65390, 0xFF,
		sizeof(TX_FUEL_INFORMATION_ECO_GAUGE_65390));
	memset((unsigned char*) &TX_CYLINDER_ANGLE_STROKE1_65395, 0xFF,
		sizeof(TX_CYLINDER_ANGLE_STROKE1_65395));
	memset((unsigned char*) &TX_AS_PHONE_NUMBER_65425, 0xFF,
		sizeof(TX_AS_PHONE_NUMBER_65425));
	memset((unsigned char*) &TX_WARNING_LAMP_65427, 0xFF,
		sizeof(TX_WARNING_LAMP_65427));
	memset((unsigned char*) &TX_INDICATOR_LAMP_65428, 0xFF,
		sizeof(TX_INDICATOR_LAMP_65428));
	memset((unsigned char*) &TX_GAUGE_65431, 0xFF, sizeof(TX_GAUGE_65431));
	memset((unsigned char*) &TX_HOURMETER_CLOCK_WIPER_65433, 0xFF,
		sizeof(TX_HOURMETER_CLOCK_WIPER_65433));
	memset((unsigned char*) &TX_MACHINE_TRAVEL_STATUS_65434, 0xFF,
		sizeof(TX_MACHINE_TRAVEL_STATUS_65434));
	memset((unsigned char*) &TX_DTC_INFORMATION_TYPE1_65438, 0xFF,
		sizeof(TX_DTC_INFORMATION_TYPE1_65438));
	memset((unsigned char*) &TX_WEIGHING_SYSTEM_STATUS_65450, 0xFF,
		sizeof(TX_WEIGHING_SYSTEM_STATUS_65450));
	memset((unsigned char*) &TX_WEIGHING_SYSTEM_DATA1_65451, 0xFF,
		sizeof(TX_WEIGHING_SYSTEM_DATA1_65451));
	memset((unsigned char*) &TX_WEIGHING_SYSTEM_DATA2_65452, 0xFF,
		sizeof(TX_WEIGHING_SYSTEM_DATA2_65452));
	memset((unsigned char*) &TX_JOYSTICK_POSITION_STATUS_65515, 0xFF,
		sizeof(TX_JOYSTICK_POSITION_STATUS_65515));
	memset((unsigned char*) &TX_WHEEL_LOADER_EHCU_STATUS_65517, 0xFF,
		sizeof(TX_WHEEL_LOADER_EHCU_STATUS_65517));
	memset((unsigned char*) &TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527,
		0xFF, sizeof(TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527));
	memset((unsigned char*) &TX_COMPONENT_IDENTIFICATION_MONITOR_65330,
		0xFF, sizeof(TX_COMPONENT_IDENTIFICATION_MONITOR_65330));


	memset((unsigned char*) &RX_RES_RTC, 0xFF, sizeof(RX_RES_RTC));
	memset((unsigned char*) &RX_RES_Version, 0xFF, sizeof(RX_RES_Version));
	memset((unsigned char*) &RX_RES_SMK, 0xFF, sizeof(RX_RES_SMK));
	memset((unsigned char*) &TX_CMD_Lamp, 0x0, sizeof(TX_CMD_Lamp));
	memset((unsigned char*) &RX_RES_KEY, 0x0, sizeof(RX_RES_KEY));



	TX_DTC_INFORMATION_REQUEST_61184_11.MessageType = 11;
	TX_MAINTENANCE_REQUSET_61184_12.MessageType = 12;
	TX_MAINTENANCE_ITEM_LIST_61184_13.MessageType = 13;
	TX_MAINTENANCE_INFORMATION_61184_14.MessageType = 14;
	TX_MAINTENANCE_HISTORY_61184_15.MessageType = 15;
	TX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16.MessageType = 16;
	TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21.MessageType = 21;
	TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22.MessageType = 22;
	TX_HCE_ANTI_THEFT_REQUEST_61184_23.MessageType = 23;
	TX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24.MessageType = 24;
	TX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25.MessageType = 25;
	TX_OPERATION_HISTORY_REQUEST_61184_31.MessageType = 31;
	TX_COOLING_FAN_SETTING_61184_61.MessageType = 61;
	TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62.MessageType = 62;
	TX_WEIGHT_OFFSET_61184_63.MessageType = 63;
	TX_MACHINE_MODE_SETTING_61184_101.MessageType = 101;
	TX_TRAVEL_MODE_SETTING_61184_104.MessageType = 104;
	TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105.MessageType = 105;
	TX_TRAVEL_CONTROL_VALUE_61184_106.MessageType = 106;
	TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109.MessageType = 109;
	TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121.MessageType = 121;
	TX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122.MessageType = 122;
	TX_DETENT_MODE_SETTING_61184_123.MessageType = 123;
	TX_DETENT_MODE_STATUS_61184_124.MessageType = 124;
	TX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129.MessageType = 129;
	TX_AS_PHONE_NUMBER_SETTING_61184_151.MessageType = 151;
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201.MessageType = 201;
	TX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202.MessageType = 202;
	TX_WHEEL_LOADER_EHCU_SETTING_61184_203.MessageType = 203;

	//TX_AS_PHONE_NUMBER_65425.ASPhoneNum_D8 = 0xD8;


	nPF = 0;
	nPS = 0;
	nTotalPacketNum = 0;
	nMultiPacketMessageType = 0;
	nEHCUPF	=0;
	nEHCUPS	=0;
	nEHCUTotalPacketNum	=0;
	nEHCU06PF	=0;
	nEHCU06PS	=0;
	nEHCU06TotalPacketNum	=0;
	nRMCUPF	=0;
	nRMCUPS	=0;
	nRMCUTotalPacketNum	=0;
	nRMCUMultiPacketMessageType = 0;
	nClusterPF	=0;
	nClusterPS	=0;
	nClusterTotalPacketNum	=0;
	nECMPF	=0;
	nECMPS	=0;
	nECMTotalPacketNum	=0;
	nTCUPF	=0;
	nTCUPS	=0;
	nTCUTotalPacketNum	=0;
	nCIDPF	=0;
	nCIDPS	=0;
	nCIDTotalPacketNum	=0;
	nBKCUPF	=0;
	nBKCUPS	=0;
	nBKCUTotalPacketNum	=0;


	nRecvSeedFlag = 0;
	nRecvPasswordResultFlag = 0;
	nRecvPasswordChangeResultFlag = 0;
	nRecvResDownFlag = 0;

	CommErrCnt = 0;
	CheckBKCUComm = 0;

	TxRingBuffHead = 0;
	TxRingBuffTail = 0;
	RxRingBuffHead = 0;
	RxRingBuffTail = 0;

	KeyButtonCount = 0;

	gErr_Mcu_TotalPacket = 0;
	gErr_Ecu_TotalPacket = 0;
	gErr_Tcu_TotalPacket = 0;
	gErr_EHCU_TotalPacket = 0;
	gErr_Mcu_TotalPacket_Logged = 0;
	gErr_Ecu_TotalPacket_Logged = 0;
	gErr_Tcu_TotalPacket_Logged = 0;
	gErr_EHCU_TotalPacket_Logged = 0;

	gErr_Mcu_Total = 0;
	gErr_Ecu_Total = 0;
	gErr_Tcu_Total = 0;
	gErr_EHCU_Total = 0;
	gErr_Mcu_Total_Logged = 0;
	gErr_Ecu_Total_Logged = 0;
	gErr_Tcu_Total_Logged = 0;
	gErr_EHCU_Total_Logged = 0;

	RX_AIR_CONDITIONER_STATUS_65373.Ambienttemperaturesensoropen = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Ambienttemperaturesensorshort = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Incabtemperaturesensoropen = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Incabtemperaturesensorshort = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Evaptemperaturesensoropen = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Evaptemperaturesensorshort = 0;


	RX_AIR_CONDITIONER_STATUS_65373.Mode1actuatoropenshort = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Mode1actuatordrivecircuitmalfunction = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Intakeactuatoropenshort = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Intakeactuatordrivecircuitmalfunction = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Temperatureactuatoropenshort = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Temperatureactuatordrivecircuitmalfunction = 0;


	RX_AIR_CONDITIONER_STATUS_65373.Ducttemperaturesensoropen = 0;
	RX_AIR_CONDITIONER_STATUS_65373.Ducttemperaturesensorshort = 0;
	RX_AIR_CONDITIONER_STATUS_65373.WaterValveSensorError = 0;
	RX_AIR_CONDITIONER_STATUS_65373.GPSCircuitError = 0;
}


void UART1_SeperateData_NEWCAN2(int Priority, int PF, int PS, int SourceAddress, unsigned char* Data)
{
	// Source Address에 따라서 들어온 Data가 나뉜다. -> 기본으로 MCU한테 가게 되어있음.
	switch (SourceAddress) {
		case SA_SMK:
			__android_log_print(ANDROID_LOG_INFO, "SA_SMK","Receive PF[%d] PS[%d]\n",PF,PS);
			break;
		case SA_MCU:
		default:
			UART1_SeperateData_Default(Priority,PF,PS,Data);
			break;
		case SA_CLUSTER:
			UART1_SeperateData_Cluster(Priority,PF,PS,Data);
			break;
		case SA_RMCU:
			UART1_SeperateData_RMCU(Priority,PF,PS,Data);
			break;
		case SA_EHCU:
			UART1_SeperateData_EHCU(Priority,PF,PS,Data);
			break;
		case SA_TCU:
			UART1_SeperateData_TCU(Priority,PF,PS,Data);
			break;
		case SA_ECM:
			UART1_SeperateData_ECM(Priority,PF,PS,Data);
			break;
		case SA_CID:
			UART1_SeperateData_CID(Priority,PF,PS,Data);
			break;
		case SA_ACU:
			//UART1_SeperateData_ACU(Priority,PF,PS,Data);
			break;
		case SA_BKCU:
			UART1_SeperateData_BKCU(Priority,PF,PS,Data);
			break;

	}
	CommErrCnt = 0;
}

void UART1_SeperateData_Default(int Priority, int PF, int PS, unsigned char* Data)
{
	CAN_RX_PACKET*		CANPacket;

	CANPacket = (CAN_RX_PACKET*) Data;
	// PDU 포맷에 따른 Data 판단
	switch (PF) {
		case 239:	// 0xEF00 61184
			switch (Data[7]) {	// Message Type
			case 11	:	memcpy((unsigned char*)&RX_DTC_INFORMATION_REQUEST_61184_11,&Data[7],8);	 break	;
			case 12	:	memcpy((unsigned char*)&RX_MAINTENANCE_REQUSET_61184_12,&Data[7],8);	 break	;
			case 13	:	memcpy((unsigned char*)&RX_MAINTENANCE_ITEM_LIST_61184_13,&Data[7],8);	 break	;
			case 14	:	memcpy((unsigned char*)&RX_MAINTENANCE_INFORMATION_61184_14,&Data[7],8);	 break	;
			case 15	:	memcpy((unsigned char*)&RX_MAINTENANCE_HISTORY_61184_15,&Data[7],8);		break	;
			case 16	:	memcpy((unsigned char*)&RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16,&Data[7],8);		break	;
			case 21	:	memcpy((unsigned char*)&RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21,&Data[7],8);	 break	;
			case 22	:	memcpy((unsigned char*)&RX_HCE_ANTI_THEFT_ENCRYPTION_SEED_61184_22,&Data[7],8);nRecvSeedFlag = 1;	 break	;
			case 23	:	memcpy((unsigned char*)&RX_HCE_ANTI_THEFT_REQUEST_61184_23,&Data[7],8);	 break	;
			case 24	:	memcpy((unsigned char*)&RX_HCE_ANTI_THEFT_PASSWORD_VALID_STATUS_61184_24,&Data[7],8); nRecvPasswordResultFlag = 1;break	;
			case 25	:	memcpy((unsigned char*)&RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25,&Data[7],8);	 nRecvPasswordChangeResultFlag = 1;break	;
			case 33 :	memcpy((unsigned char*)&RX_AVERAGE_FUEL_RATE_HISTORY_61184_33,&Data[7],8); break;
			case 61	:	memcpy((unsigned char*)&RX_COOLING_FAN_SETTING_61184_61,&Data[7],8);	 break	;
			case 62	:	memcpy((unsigned char*)&RX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62,&Data[7],8);	 break	;
			case 63	:	memcpy((unsigned char*)&RX_WEIGHT_OFFSET_61184_63,&Data[7],8);	 break	;
			case 65	:	memcpy((unsigned char*)&RX_PARALLEL_LIFT_OPERATION_STATUS_61184_65,&Data[7],8);	 break	;
			case 101	:	memcpy((unsigned char*)&RX_MACHINE_MODE_SETTING_61184_101,&Data[7],8);	 break	;
			case 104	:	memcpy((unsigned char*)&RX_TRAVEL_MODE_SETTING_61184_104,&Data[7],8);	 break	;
			case 105	:	memcpy((unsigned char*)&RX_TRAVEL_CONTROL_VALUE_SETTING_61184_105,&Data[7],8);	 break	;
			case 106	:	memcpy((unsigned char*)&RX_TRAVEL_CONTROL_VALUE_61184_106,&Data[7],8);	 break	;
			case 109	:	memcpy((unsigned char*)&RX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109,&Data[7],8);	 break	;
			case 121	:	memcpy((unsigned char*)&RX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121,&Data[7],8);	 break	;
			case 122	:	memcpy((unsigned char*)&RX_ENGINE_SHUTDOWN_MODE_STATUS_61184_122,&Data[7],8);	 break	;
			case 123	:	memcpy((unsigned char*)&RX_DETENT_MODE_SETTING_61184_123,&Data[7],8);	 break	;
			case 124	:	memcpy((unsigned char*)&RX_DETENT_MODE_STATUS_61184_124,&Data[7],8);	SetKeypadLamp();	break	;
			case 129	:	memcpy((unsigned char*)&RX_ELECTRIC_CIRCUIT_CONTROL_COMMAND_61184_129,&Data[7],8);	 break	;
			case 151	:	memcpy((unsigned char*)&RX_AS_PHONE_NUMBER_SETTING_61184_151,&Data[7],8);	 break	;
			case 201	:	memcpy((unsigned char*)&RX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201,&Data[7],8);	 break	;
			case 202	:	memcpy((unsigned char*)&RX_WHEEL_LOADER_SENSOR_CALIBRATION_STATUS_61184_202,&Data[7],8);	break	;
			case 203	:	memcpy((unsigned char*)&RX_WHEEL_LOADER_EHCU_SETTING_61184_203,&Data[7],8);	 break	;
			////////////////////////////////////////////Old////////////////////////////////////////////////////////////////////
			case 102	:	memcpy((unsigned char*) &rx_Machine_Travel_Setting,&Data[7], 8);	break;
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			default:
				break;
			}
		break;
		case 255:	// 0xFF00
		default:
			switch (PS) {
				case 49 : memcpy((unsigned char*)&RX_RMCU_STATUS_65329,&Data[7],8); break;
				case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_65330,&Data[7],8); break;
				case 64 :memcpy((unsigned char*)&RX_TRIP_TIME_INFORMATION_65344,&Data[7],8); break;
				case 68 :memcpy((unsigned char*)&RX_MACHINE_SECURITY_STATUS_65348,&Data[7],8); gRecvESL = 1;break;
				case 70 :memcpy((unsigned char*)&RX_MACHINE_MODE_STATUS_65350,&Data[7],8);SetKeypadLamp();break;
				case 74 :memcpy((unsigned char*)&RX_HYDRAULIC_PRESSURE4_65354,&Data[7],8); break;
				case 76 :memcpy((unsigned char*)&RX_HYDRAULIC_PRESSURE6_65356,&Data[7],8); break;
				case 77 :memcpy((unsigned char*)&RX_HYDRAULIC_PRESSURE7_65357,&Data[7],8); break;
				case 80 :memcpy((unsigned char*)&RX_ELECTRIC_COMPONENT_SIGNAL_VOLTAGE_65360,&Data[7],8); break;
				case 84 :memcpy((unsigned char*)&RX_RELAY_BUZZER_STATUS_65364,&Data[7],8); break;
				case 85 :memcpy((unsigned char*)&RX_SOLENOID_STATUS_65365,&Data[7],8); break;
				case 88 :memcpy((unsigned char*)&RX_ACCELERATOR_BRAKE_PEDAL_STATUS_65368,&Data[7],8); break;
				case 89 :memcpy((unsigned char*)&RX_COOLING_FAN_STATUS_65369,&Data[7],8); break;
				case 90 :memcpy((unsigned char*)&RX_ENGINE_STATUS2_65370,&Data[7],8); break;
				case 91 :memcpy((unsigned char*)&RX_ENGINE_STATUS1_65371,&Data[7],8); break;
				case 109 :memcpy((unsigned char*)&RX_VEHICLE_DISTANCE_65389,&Data[7],8); break;
				case 110 :memcpy((unsigned char*) &RX_FUEL_INFORMATION_ECO_GAUGE_65390, &Data[7],8);	break;
				case 115 :memcpy((unsigned char*)&RX_CYLINDER_ANGLE_STROKE1_65395,&Data[7],8); break;
				case 116 :memcpy((unsigned char*)&RX_CYLINDER_ANGLE_STROKE2_65396,&Data[7],8); break;
				case 145 :memcpy((unsigned char*)&RX_AS_PHONE_NUMBER_65425,&Data[7],8);break;
				case 147 :memcpy((unsigned char*)&RX_WARNING_LAMP_65427,&Data[7],8); break;
				case 148 :memcpy((unsigned char*)&RX_INDICATOR_LAMP_65428,&Data[7],8);	SetKeypadLamp();break;
				case 151 :memcpy((unsigned char*)&RX_GAUGE_65431,&Data[7],8); break;
				case 153 :memcpy((unsigned char*)&RX_HOURMETER_CLOCK_WIPER_65433,&Data[7],8); break;
				case 154 :memcpy((unsigned char*)&RX_MACHINE_TRAVEL_STATUS_65434,&Data[7],8); break;
				case 158 :memcpy((unsigned char*)&RX_DTC_INFORMATION_TYPE1_65438,&Data[7],8); SaveErrorCode_NEW_CAN2();	break;
				case 169 :memcpy((unsigned char*)&RX_AXLE_STATUS_65449,&Data[7],8); break;
				case 170 :memcpy((unsigned char*)&RX_WEIGHING_SYSTEM_STATUS_65450,&Data[7],8); SetKeypadLamp();	break;
				case 171 :memcpy((unsigned char*)&RX_WEIGHING_SYSTEM_DATA1_65451,&Data[7],8); break;
				case 172 :memcpy((unsigned char*)&RX_WEIGHING_SYSTEM_DATA2_65452,&Data[7],8); break;
				case 235 :memcpy((unsigned char*)&RX_JOYSTICK_POSITION_STATUS_65515, &Data[7],8);	break;
				case 247 :memcpy((unsigned char*)&RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527,&Data[7],8);SetKeypadLamp();break;
				////////////////////////////////////////////Old////////////////////////////////////////////////////////////////////
				//case	224	:	memcpy((unsigned char*) &rx_vehicle_distance, &Data[7], 8);	break;	// 109
				//case	49	:	memcpy((unsigned char*) &rx_RMCU_Status, &Data[7], 8);	break;
				//case	68	:	memcpy((unsigned char*) &rx_mach_security_stat_s, &Data[7], 8);	gRecvESL = 1; break;
				//case	70	:	memcpy((unsigned char*) &rx_Machine_Mode_Status, &Data[7], 8);	break;
				//case	88	:	memcpy((unsigned char*) &rx_AccBrakepedalStatus, &Data[7], 8);	break;
				//case	89	:	memcpy((unsigned char*) &rx_Cooling_Fan_Status, &Data[7], 8);	break;

				//case	115	:	memcpy((unsigned char*) &rx_cylinder_angle, &Data[7], 8);	break;
				//case	141	:	memcpy((unsigned char*) &rx_maintenance_info, &Data[7], 8);	break;
				//case	147	:	memcpy((unsigned char*) &rx_Warning_Lamp, &Data[7], 8);	break;
				//case	148	:	memcpy((unsigned char*) &rx_indicator_lamp, &Data[7], 8);	break;
				//case	150	:	memcpy((unsigned char*) &rx_machine_version, &Data[7], 8);	break;
				//case	151	:	memcpy((unsigned char*) &rx_gauge, &Data[7], 8);	break;
				//case	153	: 	memcpy((unsigned char*) &rx_hr_wiper, &Data[7], 8);	break;
				//case	154	: 	memcpy((unsigned char*) &rx_vehi_travel, &Data[7], 8);	break;
				//case	155	:	memcpy((unsigned char*) &rx_vehicle_status1, &Data[7], 8);	break;
				//case	156	: 	memcpy((unsigned char*) &rx_vehicle_status2, &Data[7], 8);	break;
				//case	158	: 	memcpy(&gRecvMulti[0], &Data[7], 8);
				//				memcpy((unsigned char*) &rx_error_code, &gRecvMulti, 21);
				//				SaveErrorCode_NEW_CAN2();	break;
				case	159	:	memcpy((unsigned char*) &rx_aeb_info, &Data[7], 8);	break;		// SPN 539 -> 562,563 Check
					//case	165	:	memcpy((unsigned char*) &rx_cluster_version, &Data[7], 8);	break;
					//case	170	:	memcpy((unsigned char*) &rx_weighing_system_status, &Data[7],8);	break;
					//case	171	:	memcpy((unsigned char*) &rx_weighing_system_data1, &Data[7],8);	break;
					//case	172	:	memcpy((unsigned char*) &rx_weighing_system_data2, &Data[7],8);	break;
					//case	173	:	memcpy((unsigned char*) &rx_weighing_system_data3, &Data[7],8);	break;
				case	174	:	memcpy((unsigned char*) &rx_Weihing_System_Mode, &Data[7], 8);	break;
				case	175	:	memcpy((unsigned char*) &rx_Weighing_System_Error, &Data[7],8);	break;
				case	250	:	memcpy((unsigned char*) &gRecvSingle_250, &Data[7], 8);	gRecvPassWord = 1;	break;
					//case	145	:	if (flag_comm.recv_as_phone_num_once != 1) {
					//				memcpy((unsigned char*) &rx_single_phone_num, &Data[7], 8);
					//				flag_comm.recv_as_phone_num_once = 1;
					//				flag_comm.recv_as_phone_num = 2;
					//				}	break;
					//case	236	:	memcpy((unsigned char*) &rx_Machine_Setting_Status, &Data[7],8);	break;
				case	252	:	if (((Data[9] << 8) | Data[8]) == 65425) {
					if (Data[7] == 0)	// ACK
						rx_hcepgn_ack.ControlByte = 0;	// Ack
					else
						rx_hcepgn_ack.ControlByte = 0xff;
								}
								break;
								//case	229	:	memcpy((unsigned char*) &rx_scu_error, &Data[7], 8);	RecvScuError = 1;	break;
				case	31	:	memcpy((unsigned char*) &rx_tcu_err, &Data[7], 8);	break;
					//case	235	:	memcpy((unsigned char*) &rx_Joystick_Position_Status, &Data[7],8);	break;
					//case	247	:	memcpy((unsigned char*) &rx_Realy_Control, &Data[7], 8);	break;
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				default:
					break;
				}
			break;
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			case 236:	// 0xEC Multi Packet TP.CM_BAM
				__android_log_print(ANDROID_LOG_INFO, "NATIVE","Multi TP.CM_BAM");
				if(CANPacket->RX_PS == 0xFF || CANPacket->RX_PS == 0x28){
					if(Data[7] == 0x20){				// Control Byte (Normal)
						nPF = Data[13];
						nPS = Data[12];
						nTotalPacketNum = Data[10];
					}else if(Data[7] == 0x10){			// Control Byte (RTS)
						Send_CTS(0x1C,SA_MCU,SA_MONITOR,&CANPacket->RX_DATA[0]);
						memcpy(&MultiPacket_ACK_MCU[0],(unsigned char*) &UART1_Rx_Data->RX_DATA[0], 8);
						bAckFlag_MCU = 1;
						nPF = Data[13];
						nPS = Data[12];
						nTotalPacketNum = Data[10];
						__android_log_print(ANDROID_LOG_INFO, "NATIVE","RTS!!!!");
					}else if(Data[7] == 0x11){			// Control Byte (CTS)
						nCTSFlag_MCU = 1;
						__android_log_print(ANDROID_LOG_INFO, "NATIVE","CTS!!!!");
					}
				}


				break;
			case 235:	// 0xEB	Multi Packet TP.DT
				if(nPF != 0 || nPS != 0)
				{
					UART1_SeperateData_Default_Multi(Priority,nPF,nPS,Data);
				}
				break;
	}
}

void UART1_SeperateData_Default_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 239:
			memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			if(Data[7] == 1)
			{
				nMultiPacketMessageType = Data[8];

			}
			switch (nMultiPacketMessageType) {	// Message Type
		case 13:

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_MAINTENANCE_ITEM_LIST_61184_13, &gRecvMulti,sizeof(RX_MAINTENANCE_ITEM_LIST_61184_13));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 15:

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_MAINTENANCE_HISTORY_61184_15, &gRecvMulti,sizeof(RX_MAINTENANCE_HISTORY_61184_15));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;

		case 16:

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16, &gRecvMulti,sizeof(RX_MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_61184_16));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 23:

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_HCE_ANTI_THEFT_REQUEST_61184_23, &gRecvMulti,sizeof(RX_HCE_ANTI_THEFT_REQUEST_61184_23));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 25:
			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25, &gRecvMulti,sizeof(RX_HCE_ANTI_THEFT_MODIFY_PASSWORD_STATUS_61184_25));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
				nRecvPasswordChangeResultFlag = 1;
			}
			break;
		case 33:
			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_AVERAGE_FUEL_RATE_HISTORY_61184_33, &gRecvMulti,sizeof(RX_AVERAGE_FUEL_RATE_HISTORY_61184_33));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 151:

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_AS_PHONE_NUMBER_SETTING_61184_151, &gRecvMulti,sizeof(RX_AS_PHONE_NUMBER_SETTING_61184_151));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
			/////////////////////////////////////////////////////////Old///////////////////////////////////////
		case 228:	// 0xEFE4 61412


			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &rx_ehcu_status, &gRecvMulti,sizeof(rx_ehcu_status));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
			///////////////////////////////////////////////////////////////////////////////////////////////////

		default:
			break;
			}
			break;

		case 255:
		default:
			switch (PS) {
		case 50:
			memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_65330, &gRecvMulti,sizeof(RX_COMPONENT_IDENTIFICATION_65330));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 145:
			memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_AS_PHONE_NUMBER_65425, &gRecvMulti,sizeof(RX_AS_PHONE_NUMBER_65425));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 158:
			memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &RX_DTC_INFORMATION_TYPE1_65438, &gRecvMulti,sizeof(RX_DTC_INFORMATION_TYPE1_65438));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
				SaveErrorCode_NEW_CAN2();
			}
			break;

			/////////////////////////////////////////////////////////Old///////////////////////////////////////
			//			case 68:	//  65348 Machine Security Status
			//				memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			//
			//				if (Data[7] == nTotalPacketNum) {
			//					memcpy((unsigned char*) &rx_mach_security_stat, &gRecvMulti,sizeof(rx_mach_security_stat));
			//					nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			//				}
			//				break;
			//			case 142:	//  65422 Maintenance Information #3 / Replacement History
			//				memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			//
			//				if (Data[7] == nTotalPacketNum) {
			//					memcpy((unsigned char*) &rx_maintenance_history, &gRecvMulti,sizeof(rx_maintenance_history));
			//					nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			//				}
			//				break;
			//			case 150:	//  65430 Vehicle Information
			//				memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);
			//
			//				if (Data[7] == nTotalPacketNum) {
			//					memcpy((unsigned char*) &rx_machine_version, &gRecvMulti,sizeof(rx_machine_version));
			//					nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			//				}
			//				break;
		case 157:	//  65437 Machine Status
			memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &rx_machine_state, &gRecvMulti,sizeof(rx_machine_state));
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;
		case 250:	//  65530 HCESPN Data ( User P/W )
			memcpy((unsigned char*) &gRecvMulti[(Data[7] - 1) * 7],&Data[8], 7);

			if (Data[7] == nTotalPacketNum) {
				memcpy((unsigned char*) &gRecvMulti_250, &gRecvMulti,sizeof(gRecvMulti_250));
				gRecvPassWord = 2;
				nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			}
			break;

			///////////////////////////////////////////////////////////////////////////////////////////////////
		default:
			nPF = nPS = nTotalPacketNum = nMultiPacketMessageType = 0;
			break;
			}
			break;
	}

	if(bAckFlag_MCU == 1)
	{
		if (Data[7] == nTotalPacketNum)
		{
			bAckFlag_MCU = 0;
			Send_ACK(0x1C,SA_MCU,SA_MONITOR,MultiPacket_ACK_MCU);

		}
	}
}

void UART1_SeperateData_Cluster(int Priority, int PF, int PS, unsigned char* Data)
{
	CAN_RX_PACKET*		CANPacket;
	CANPacket = (CAN_RX_PACKET*) Data;
	switch (PF) {
		case 255:	// 0xFF00
		default:
			switch (PS) {
		case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_CLUSTER_65330,&Data[7],8); break;
		case 165:memcpy((unsigned char*)&RX_CLUSTER_STATUS_65445,&Data[7],8); break;
		//case 165	:	memcpy((unsigned char*) &rx_cluster_version, &Data[7], 8);	break;
		default:
			break;
			}
			break;
		case 236:	// 0xEC Multi Packet TP.CM_BAM
			if(CANPacket->RX_PS == 0xFF || CANPacket->RX_PS == 0x28){
				if(Data[7] == 0x20){	// Control Byte (Normal)
					nClusterPF = Data[13];
					nClusterPS = Data[12];
					nClusterTotalPacketNum = Data[10];
				}
			}

			break;
		case 235:	// 0xEB	Multi Packet TP.DT
			if(nClusterPF != 0 || nClusterPS != 0)
			{
				UART1_SeperateData_Cluster_Multi(Priority,nClusterPF,nClusterPS,Data);
			}
			break;
	}
}
void UART1_SeperateData_Cluster_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:
		default:
			switch (PS) {
		case 50:
			memcpy((unsigned char*) &gRecvMulti_Cluster[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nClusterTotalPacketNum) {
				memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_CLUSTER_65330, &gRecvMulti_Cluster,sizeof(RX_COMPONENT_IDENTIFICATION_CLUSTER_65330));
				nClusterPF = nClusterPS = nClusterTotalPacketNum = 0;
			}

		default:
			break;
			}
			break;
	}
}

void UART1_SeperateData_EHCU(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:	// 0xFF00
		default:
			switch (PS) {
				case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_EHCU_65330,&Data[7],8); break;
				case 235:memcpy((unsigned char*)&RX_JOYSTICK_POSITION_STATUS_65515,&Data[7],8);break;
				case 237:
					memcpy((unsigned char*)&RX_WHEEL_LOADER_EHCU_STATUS_65517,&Data[7],8);
					SetKeypadLamp();
					break;
				case 244:
					memcpy((unsigned char*)&RX_WHEEL_LOADER_EHCU_STATUS2_65524,&Data[7],8);
					break;
				default:
					break;
			}
			break;
		case 254:	// 0xFE00
			switch (PS) {
		case 202:		// Diagnostic Message 1(EHCU)
			memcpy((unsigned char*) &rx_error_ehcu_s, &Data[7], 8);
			ehcu_s_or_m = 1;
			break;
		default:
			break;
			}
			break;

		case 236:	// 0xEC Multi Packet TP.CM_BAM
			if(Priority == 0x06){
				nEHCU06PF = Data[13];
				nEHCU06PS = Data[12];
				nEHCU06TotalPacketNum = Data[10];
				nTempEHCUTotalError = Data[8];
			}
			else{
				nEHCUPF = Data[13];
				nEHCUPS = Data[12];
				nEHCUTotalPacketNum = Data[10];
			}

			break;
		case 235:	// 0xEB	Multi Packet TP.DT

			if(Priority == 0x06){
				if(nEHCU06PF != 0 || nEHCU06PS != 0)
				{

					UART1_SeperateData_EHCU_Multi(Priority,nEHCU06PF,nEHCU06PS,Data);
				}
			}
			else{
				if(nEHCUPF != 0 || nEHCUPS != 0)
				{
					UART1_SeperateData_EHCU_Multi(Priority,nEHCUPF,nEHCUPS,Data);
				}
			}

			break;
	}
}
void UART1_SeperateData_EHCU_Multi(int Priority, int PF, int PS, unsigned char* Data)
{

	switch (Priority) {
		case 0x07:		// 0x1CXXXX
			memcpy((unsigned char*) &gRecvMulti_EHCU[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nEHCUTotalPacketNum) {
				memcpy((unsigned char*) &rx_ehcu_status, &gRecvMulti_EHCU,sizeof(rx_ehcu_status));
				nEHCUPF= nEHCUPS = nEHCUTotalPacketNum = 0;
			}
			break;
		case 0x06:		// 0x18XXXX
		default:
			switch (PF) {
			case 254:	// 0xFE
				switch (PS) {
			case 202:		// 0xCA
				memcpy((unsigned char*) &gRecvMulti_EHCU_06[(Data[7] - 1) * 7],&Data[8], 7);
				if (Data[7] == nEHCU06TotalPacketNum) {
					ehcu_s_or_m = 2;
					nEHCUTotalError = nTempEHCUTotalError;
					memcpy((unsigned char*) &rx_error_ehcu_m, &gRecvMulti_EHCU_06,sizeof(rx_error_ehcu_m));
					nEHCU06PF = nEHCU06PS = nEHCU06TotalPacketNum = nTempEHCUTotalError= 0;

				}
				break;
			default:
				break;
				}
				break;
			case 255:
				switch (PS) {
			case 50:
				memcpy((unsigned char*) &gRecvMulti_EHCU_06[(Data[7]- 1) * 7], &Data[8], 7);
				if (Data[7] == nEHCU06TotalPacketNum) {
					memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_EHCU_65330,&gRecvMulti_EHCU_06,sizeof(RX_COMPONENT_IDENTIFICATION_EHCU_65330));
					nEHCU06PF = nEHCU06PS = nEHCU06TotalPacketNum = 0;

				}
				break;
			default:
				break;
			}
		default:
			break;
			}
			break;
	}
}

void UART1_SeperateData_RMCU(int Priority, int PF, int PS, unsigned char* Data)
{
	CAN_RX_PACKET*		CANPacket;

	CANPacket = (CAN_RX_PACKET*) Data;

	switch (PF) {
		case 239: // 61184
			switch (Data[7]) {	// Message Type
		case 151	:
			memcpy((unsigned char*)&RX_AS_PHONE_NUMBER_SETTING_61184_151,&Data[7],8);
			ASCallback();
			break	;
		default:																							break;

			}

			break;
		case 255:	// 0xFF00
		default:
			switch (PS) {
				case 49 : memcpy((unsigned char*)&RX_RMCU_STATUS_65329,&Data[7],8); break;
				case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_RMCU_65330,&Data[7],8); break;
				default:
					break;
			}
			break;
		case 236:	// 0xEC Multi Packet TP.CM_BAM
			if(CANPacket->RX_PS == 0xFF || CANPacket->RX_PS == 0x28){
				if(Data[7] == 0x20){				// Control Byte (Normal)
					nRMCUPF = Data[13];
					nRMCUPS = Data[12];
					nRMCUTotalPacketNum = Data[10];
				}else if(Data[7] == 0x10){			// Control Byte (RTS)
					Send_CTS(0x1C,SA_RMCU,SA_MONITOR,&CANPacket->RX_DATA[0]);
					memcpy(&MultiPacket_ACK_RMCU[0],(unsigned char*) &CANPacket->RX_DATA[0], 8);
					bAckFlag_RMCU = 1;
					nRMCUPF = Data[13];
					nRMCUPS = Data[12];
					nRMCUTotalPacketNum = Data[10];
					__android_log_print(ANDROID_LOG_INFO, "NATIVE","RTS!!!!");
				}else if(Data[7] == 0x11){			// Control Byte (CTS)
					nCTSFlag_RMCU = 1;
					__android_log_print(ANDROID_LOG_INFO, "NATIVE","CTS!!!!");
				}
			}
			break;
		case 235:	// 0xEB	Multi Packet TP.DT
			if(nRMCUPF != 0 || nRMCUPS != 0)
			{
				UART1_SeperateData_RMCU_Multi(Priority,nRMCUPF,nRMCUPS,Data);
			}
			break;
	}

	if(bAckFlag_RMCU == 1)
	{
		if (Data[7] == nTotalPacketNum)
		{
			bAckFlag_RMCU = 0;
			Send_ACK(0x1C,SA_RMCU,SA_MONITOR,MultiPacket_ACK_RMCU);

		}
	}
}

void UART1_SeperateData_RMCU_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 239: // 61184
			memcpy((unsigned char*) &gRecvMulti_RMCU[(Data[7] - 1) * 7],&Data[8], 7);
			if(Data[7] == 1)
			{
				nRMCUMultiPacketMessageType = Data[8];

			}
			switch (nRMCUMultiPacketMessageType) {	// Message Type
		case 151	:
			if (Data[7] == nRMCUTotalPacketNum) {
				memcpy((unsigned char*) &RX_AS_PHONE_NUMBER_SETTING_61184_151, &gRecvMulti_RMCU,sizeof(RX_AS_PHONE_NUMBER_SETTING_61184_151));
				nRMCUPF = nRMCUPS = nRMCUTotalPacketNum = 0;
				ASCallback();
			}
			break	;
		default:
			break;

			}

			break;
		case 255:
		default:
			switch (PS) {
		case 50:
			memcpy((unsigned char*) &gRecvMulti_RMCU[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nRMCUTotalPacketNum) {
				memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_RMCU_65330, &gRecvMulti_RMCU,sizeof(RX_COMPONENT_IDENTIFICATION_RMCU_65330));
				nRMCUPF = nRMCUPS = nRMCUTotalPacketNum = 0;
			}

		default:
			break;
			}
			break;
	}
}

void UART1_SeperateData_ECM(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:	// 0xFF00
		default:
			switch (PS) {
		case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_ECM_65330,&Data[7],8); break;
		default:
			break;
			}
			break;
		case 236:	// 0xEC Multi Packet TP.CM_BAM
			nECMPF = Data[13];
			nECMPS = Data[12];
			nECMTotalPacketNum = Data[10];
			break;
		case 235:	// 0xEB	Multi Packet TP.DT
			if(nECMPF != 0 || nECMPS != 0)
			{
				UART1_SeperateData_ECM_Multi(Priority,nECMPF,nECMPS,Data);
			}
			break;
	}
}
void UART1_SeperateData_ECM_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:
		default:
			switch (PS) {
		case 50:
			memcpy((unsigned char*) &gRecvMulti_ECM[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nECMTotalPacketNum) {
				memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_ECM_65330, &gRecvMulti_ECM,sizeof(RX_COMPONENT_IDENTIFICATION_ECM_65330));
				nECMPF = nECMPS = nECMTotalPacketNum = 0;
			}

		default:
			break;
			}
			break;
	}
}

void UART1_SeperateData_TCU(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:	// 0xFF00
		default:
			switch (PS) {
		case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_TCU_65330,&Data[7],8); break;
		default:
			break;
			}
			break;
		case 236:	// 0xEC Multi Packet TP.CM_BAM
			nTCUPF = Data[13];
			nTCUPS = Data[12];
			nTCUTotalPacketNum = Data[10];
			break;
		case 235:	// 0xEB	Multi Packet TP.DT
			if(nTCUPF != 0 || nTCUPS != 0)
			{
				UART1_SeperateData_TCU_Multi(Priority,nTCUPF,nTCUPS,Data);
			}
			break;
	}
}
void UART1_SeperateData_TCU_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:
		default:
			switch (PS) {
		case 50:
			memcpy((unsigned char*) &gRecvMulti_TCU[(Data[7] - 1) * 7],&Data[8], 7);
			if (Data[7] == nTCUTotalPacketNum) {
				memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_TCU_65330, &gRecvMulti_TCU,sizeof(RX_COMPONENT_IDENTIFICATION_TCU_65330));
				nTCUPF = nTCUPS = nTCUTotalPacketNum = 0;
			}

		default:
			break;
			}
			break;
	}
}

void UART1_SeperateData_CID(int Priority, int PF, int PS, unsigned char* Data)
{
	if(PS == SA_MONITOR){
		switch (PF) {
			case 236:	// 0xEC Multi Packet TP.CM_BAM
				nCIDPF = Data[13];
				nCIDPS = Data[12];
				nCIDTotalPacketNum = Data[10];
				break;
			case 235:	// 0xEB	Multi Packet TP.DT
				if(nCIDPF != 0 || nCIDPS != 0)
				{
					UART1_SeperateData_CID_Multi(Priority,nCIDPF,nCIDPS,Data);
				}
				break;
		}
	}

}
void UART1_SeperateData_CID_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 239:	// 0xEF
		default:
			switch (PS) {
				case 239:
				default:
					memcpy((unsigned char*) &gRecvMulti_CID[(Data[7] - 1) * 7],&Data[8], 7);
					if (Data[7] == nCIDTotalPacketNum) {
						memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_MONITOR_65330,&gRecvMulti_CID,
							sizeof(RX_COMPONENT_IDENTIFICATION_MONITOR_65330));
						nClusterPGN = nClusterTotalPacketNum = 0;
						CIDCallback();
					}
					break;
			}
			break;
	}
}
void UART1_SeperateData_ACU(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:	// 0xFF00
		default:
			switch (PS) {
				case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_ACU_65330,&Data[7],8); break;
				case 93 :memcpy((unsigned char*)&RX_AIR_CONDITIONER_STATUS_65373,&Data[7],8); break;
				case 239 : memcpy((unsigned char*)&RX_AMBIENT_CONDITIONS_65519,&Data[7],8); break;
				default:
					break;
			}
		break;
	}
}

void UART1_SeperateData_BKCU(int Priority, int PF, int PS, unsigned char* Data)
{
	CheckBKCUComm = 1;
	switch (PF) {
		case 255:	// 0xFF00
		default:
			switch (PS) {
				case 50 :memcpy((unsigned char*)&RX_COMPONENT_IDENTIFICATION_BKCU_65330,&Data[7],8); break;
				case 234 :memcpy((unsigned char*)&RX_BKCU_STATUS_65514,&Data[7],8); break;
				default:
					break;
			}

		break;
		case 236:	// 0xEC Multi Packet TP.CM_BAM
			nBKCUPF = Data[13];
			nBKCUPS = Data[12];
			nBKCUTotalPacketNum = Data[10];
			break;
		case 235:	// 0xEB	Multi Packet TP.DT
			if(nBKCUPF != 0 || nBKCUPS != 0)
			{
				UART1_SeperateData_BKCU_Multi(Priority,nBKCUPF,nBKCUPS,Data);
			}
			break;
}
}
void UART1_SeperateData_BKCU_Multi(int Priority, int PF, int PS, unsigned char* Data)
{
	switch (PF) {
		case 255:
		default:
			switch (PS) {
				case 50:
					memcpy((unsigned char*) &gRecvMulti_BKCU[(Data[7] - 1) * 7],&Data[8], 7);
					if (Data[7] == nBKCUTotalPacketNum) {
						memcpy((unsigned char*) &RX_COMPONENT_IDENTIFICATION_BKCU_65330, &gRecvMulti_BKCU,sizeof(RX_COMPONENT_IDENTIFICATION_BKCU_65330));
						nBKCUPF = nBKCUPS = nBKCUTotalPacketNum = 0;
					}

				default:
					break;
			}
			break;
	}
}
void SaveErrorCode_NEW_CAN2(void) {
	unsigned char PacketNo;

	if (RX_DTC_INFORMATION_TYPE1_65438.SequenceNumberofDTCInformationPacket_1513 > 0)
		PacketNo = RX_DTC_INFORMATION_TYPE1_65438.SequenceNumberofDTCInformationPacket_1513 - 1;
	else
		PacketNo = 0;

	if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 0) // MCU
	{
		gErr_Mcu_TotalPacket = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_Mcu_Total = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_Mcu[PacketNo * 5] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0]);
		gErr_Mcu[PacketNo * 5 + 1] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_2[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0]);
		gErr_Mcu[PacketNo * 5 + 2] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_3[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_3[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_3[0]);
		gErr_Mcu[PacketNo * 5 + 3] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_4[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_4[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_4[0]);
		gErr_Mcu[PacketNo * 5 + 4] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_5[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_5[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_5[0]);


	} else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 1)	// Logged MCU
	{
		gErr_Mcu_TotalPacket_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_Mcu_Total_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_Mcu_Logged[PacketNo * 5] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0]);
		gErr_Mcu_Logged[PacketNo * 5 + 1] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_2[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0]);
		gErr_Mcu_Logged[PacketNo * 5 + 2] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_3[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_3[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_3[0]);
		gErr_Mcu_Logged[PacketNo * 5 + 3] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_4[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_4[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_4[0]);
		gErr_Mcu_Logged[PacketNo * 5 + 4] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_5[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_5[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_5[0]);

	} else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 2)	// ECU
	{
		gErr_Ecu_TotalPacket = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_Ecu_Total = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_Ecu[PacketNo * 5] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0]);
		gErr_Ecu[PacketNo * 5 + 1] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_2[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0]);
		gErr_Ecu[PacketNo * 5 + 2] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_3[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_3[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_3[0]);
		gErr_Ecu[PacketNo * 5 + 3] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_4[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_4[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_4[0]);
		gErr_Ecu[PacketNo * 5 + 4] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_5[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_5[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_5[0]);



	} else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 3)	// Logged ECU
	{
		gErr_Ecu_TotalPacket_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_Ecu_Total_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_Ecu_Logged[PacketNo * 5] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0]);
		gErr_Ecu_Logged[PacketNo * 5 + 1] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_2[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0]);
		gErr_Ecu_Logged[PacketNo * 5 + 2] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_3[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_3[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_3[0]);
		gErr_Ecu_Logged[PacketNo * 5 + 3] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_4[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_4[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_4[0]);
		gErr_Ecu_Logged[PacketNo * 5 + 4] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_5[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_5[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_5[0]);

	}
	else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 4)	// TCU
	{
		gErr_Tcu_TotalPacket = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_Tcu_Total = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_Tcu[PacketNo * 5] = RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0];
		gErr_Tcu[PacketNo * 5 + 1] = RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1];
		gErr_Tcu[PacketNo * 5 + 2] = RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2];
		gErr_Tcu[PacketNo * 5 + 3] = RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0];
		gErr_Tcu[PacketNo * 5 + 4] = RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1];

	}
	else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 5)	// Logged TCU
	{
		gErr_Tcu_TotalPacket_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_Tcu_Total_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_Tcu_Logged[PacketNo * 5] = RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0];
		gErr_Tcu_Logged[PacketNo * 5 + 1] = RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1];
		gErr_Tcu_Logged[PacketNo * 5 + 2] = RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2];
		gErr_Tcu_Logged[PacketNo * 5 + 3] = RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0];
		gErr_Tcu_Logged[PacketNo * 5 + 4] = RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1];

	}
	else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 6)	// EHCU
	{
		gErr_EHCU_TotalPacket = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_EHCU_Total = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_EHCU[PacketNo * 5] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0]);
		gErr_EHCU[PacketNo * 5 + 1] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_2[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0]);
		gErr_EHCU[PacketNo * 5 + 2] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_3[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_3[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_3[0]);
		gErr_EHCU[PacketNo * 5 + 3] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_4[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_4[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_4[0]);
		gErr_EHCU[PacketNo * 5 + 4] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_5[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_5[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_5[0]);


	}
	else if (RX_DTC_INFORMATION_TYPE1_65438.DTCType_1510 == 7)	// Logged EHCU
	{
		gErr_EHCU_TotalPacket_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTCInformationPacket_1512;
		gErr_EHCU_Total_Logged = RX_DTC_INFORMATION_TYPE1_65438.TotalNumberofDTC;
		gErr_EHCU_Logged[PacketNo * 5] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_1[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_1[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_1[0]);
		gErr_EHCU_Logged[PacketNo * 5 + 1] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_2[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_2[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_2[0]);
		gErr_EHCU_Logged[PacketNo * 5 + 2] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_3[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_3[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_3[0]);
		gErr_EHCU_Logged[PacketNo * 5 + 3] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_4[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_4[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_4[0]);
		gErr_EHCU_Logged[PacketNo * 5 + 4] = ((RX_DTC_INFORMATION_TYPE1_65438.DTC_5[2] << 16)
			| (RX_DTC_INFORMATION_TYPE1_65438.DTC_5[1] << 8) | RX_DTC_INFORMATION_TYPE1_65438.DTC_5[0]);

	}

}

jint UART1_Tx(JNIEnv *env, jobject this, jint PF, jint PS, jint Flag) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_Tx [%d]\n", PS);
	int result = 0;

	switch (PS) {
		case 1:

			break;

			///////////////61184//////////////
		case 11	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_DTC_INFORMATION_REQUEST_61184_11);
			break;
		case 12	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_MAINTENANCE_REQUSET_61184_12);
			break;
		case 21	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21);
			break;


		case 23	:
			//	memcpy(&tx_buf[4], (unsigned char*) &TX_HCE_ANTI_THEFT_REQUEST_61184_23, sizeof(TX_HCE_ANTI_THEFT_REQUEST_61184_23));
			break;	// Multi Packet 13

		case 31 :
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_OPERATION_HISTORY_REQUEST_61184_31);
			break;

		case 61	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_COOLING_FAN_SETTING_61184_61);
			break;
		case 62	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62);
			break;
		case 101	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_MACHINE_MODE_SETTING_61184_101);
			break;
		case 104	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_TRAVEL_MODE_SETTING_61184_104);
			break;
		case 105	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105);
			break;
		case 109	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109);
			break;
		case 121	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121);
			break;
		case 123	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_DETENT_MODE_SETTING_61184_123);
			break;
		case 201	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201);
			break;
		case 203	:
			MakeCANDataSingle(0x18,0xEF,SA_EHCU,SA_MONITOR,(unsigned char*)&TX_WHEEL_LOADER_EHCU_SETTING_61184_203);
			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_Tx EHCU\n");
			break;
			///////////////////////////////////
			/////////////0xEAxx////////////////
		case 234	:
			MakeCANDataSingle(0x18,0xEA,TargetSourceAddress,SA_MONITOR,(unsigned char*)&TX_HCEPGN_REQUEST_59904);
			break;
			///////////////////////////////////
			/////////////0xFFxx////////////////
		case 47	:
			MakeCANDataSingle(0x18,0xFF,PS,SA_MONITOR,(unsigned char*)&TX_MONIOTR_STATUS_65327);
			break;
		case 50	:	
			Send_CID();
			//memcpy(&tx_buf[4], (unsigned char*) &TX_COMPONENT_IDENTIFICATION_MONITOR_65330, sizeof(TX_COMPONENT_IDENTIFICATION_MONITOR_65330));
			break;
		case 145	:	// Multi Packet	13
			//memcpy(&tx_buf[4], (unsigned char*) &TX_AS_PHONE_NUMBER_65425, sizeof(TX_AS_PHONE_NUMBER_65425));
			break;
		case 247	:
			MakeCANDataSingle(0x18,0xFF,PS,SA_MONITOR,(unsigned char*)&TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527);
			break;
			///////////////////////////////////

	}

	return result;
}


void InitUART1Valuable() {
	UART1_Rx_Data = (CAN_RX_PACKET*) &cUART1_RxData;

	memset((unsigned char*) &rx_mach_security_stat, 0xff,
		sizeof(rx_mach_security_stat));
	memset((unsigned char*) &rx_mach_security_stat_s, 0xff,
		sizeof(rx_mach_security_stat_s));
	memset((unsigned char*) &rx_machine_state, 0xff, sizeof(rx_machine_state));
	memset((unsigned char*) &gRecvSingle_250, 0xff, sizeof(gRecvSingle_250));
	memset((unsigned char*) &gRecvMulti_250, 0xff, sizeof(gRecvMulti_250));
	memset((unsigned char*) &rx_Realy_Control, 0xff, sizeof(rx_Realy_Control));
	memset((unsigned char*) &rx_Machine_Travel_Setting, 0xff,
		sizeof(rx_Machine_Travel_Setting));
	memset((unsigned char*) &rx_Cooling_Fan_Status, 0xff,
		sizeof(rx_Cooling_Fan_Status));
	memset((unsigned char*) &rx_Warning_Lamp, 0xff, sizeof(rx_Warning_Lamp));
	memset((unsigned char*) &rx_tcu_err, 0xff, sizeof(rx_tcu_err));
	memset((unsigned char*) &rx_RMCU_Status, 0xff, sizeof(rx_RMCU_Status));
	memset((unsigned char*) &rx_Component_Identification_MCU, 0xff,
		sizeof(rx_Component_Identification_MCU));
	memset((unsigned char*) &rx_Component_Identification_Cluster, 0xff,
		sizeof(rx_Component_Identification_Cluster));
	memset((unsigned char*) &rx_Component_Identification_RMCU, 0xff,
		sizeof(rx_Component_Identification_RMCU));
	memset((unsigned char*) &rx_Component_Identification_EHCU, 0xff,
		sizeof(rx_Component_Identification_EHCU));
	memset((unsigned char*) &rx_Component_Identification_ECM, 0xff,
		sizeof(rx_Component_Identification_ECM));
	memset((unsigned char*) &rx_Component_Identification_TCU, 0xff,
		sizeof(rx_Component_Identification_TCU));
	memset((unsigned char*) &rx_Component_Identification_Monitor, 0xff,
		sizeof(rx_Component_Identification_Monitor));
	memset((unsigned char*) &rx_single_phone_num, 0xff,
		sizeof(rx_single_phone_num));
	memset((unsigned char*) &rx_multi_phone_num, 0xff,
		sizeof(rx_multi_phone_num));
	memset((unsigned char*) &rx_AccBrakepedalStatus, 0xff,
		sizeof(rx_AccBrakepedalStatus));
	memset((unsigned char*) &rx_Weighing_System_Error, 0xff,
		sizeof(rx_Weighing_System_Error));
	memset((unsigned char*) &rx_Engine_Shutdown_Mode_Setting, 0xff,
		sizeof(rx_Engine_Shutdown_Mode_Setting));
	memset((unsigned char*) &rx_Engine_Shutdown_Mode_Status, 0xff,
		sizeof(rx_Engine_Shutdown_Mode_Status));
	memset((unsigned char*) &rx_Weihing_System_Mode, 0xff,
		sizeof(rx_Weihing_System_Mode));
	memset((unsigned char*) &rx_Joystick_Position_Status, 0xff,
		sizeof(rx_Joystick_Position_Status));
	memset((unsigned char*) &TX_HCEPGN_REQUEST_59904, 0xff,
		sizeof(TX_HCEPGN_REQUEST_59904));
	memset((unsigned char*) &tx_request_hcespn_data, 0xff,
		sizeof(tx_request_hcespn_data));
	memset((unsigned char*) &tx_esl_password69, 0xff,
		sizeof(tx_esl_password69));
	memset((unsigned char*) &tx_esl_password_multi, 0xff,
		sizeof(tx_esl_password_multi));
	memset((unsigned char*) &tx_as_phone_num_multi, 0xff,
		sizeof(tx_as_phone_num_multi));
	memset((unsigned char*) &tx_monitor_request, 0xff,
		sizeof(tx_monitor_request));
	memset((unsigned char*) &tx_monitor_setting, 0xff,
		sizeof(tx_monitor_setting));
	memset((unsigned char*) &tx_monitor_status, 0xff,
		sizeof(tx_monitor_status));
	memset((unsigned char*) &tx_weighing_sys_mode2, 0xff,
		sizeof(tx_weighing_sys_mode2));
	memset((unsigned char*) &tx_rtc, 0xff, sizeof(tx_rtc));
	memset((unsigned char*) &tx_ehcu_setting, 0xff, sizeof(tx_ehcu_setting));
	memset((unsigned char*) &tx_Realy_Control, 0xff, sizeof(tx_Realy_Control));
	memset((unsigned char*) &tx_Machine_Mode_Setting, 0xff,
		sizeof(tx_Machine_Mode_Setting));
	memset((unsigned char*) &tx_Machine_Travel_Setting, 0xff,
		sizeof(tx_Machine_Travel_Setting));
	memset((unsigned char*) &tx_Machine_Accessory_Setting_Req, 0xff,
		sizeof(tx_Machine_Accessory_Setting_Req));
	memset((unsigned char*) &tx_Cooling_Fan_Setting, 0xff,
		sizeof(tx_Cooling_Fan_Setting));
	memset((unsigned char*) &tx_Engine_Shutdown_Mode_Setting, 0xff,
		sizeof(tx_Engine_Shutdown_Mode_Setting));
	memset((unsigned char*) &tx_Weihing_System_Mode, 0xff,
		sizeof(tx_Weihing_System_Mode));
	memset((unsigned char*) &tx_Detent_Mode_Setting, 0xff,
		sizeof(tx_Detent_Mode_Setting));

	gRecvPassWord = 0;
	gRecvESL = 0;
	gRecvSMK = 0;

	bParsingFlag_UART3 = 0;
	bParsingFlag_UART1 = 0;

	rx_hcepgn_ack.ControlByte = 0xff;
	tx_monitor_request.ReqTripReset = 0x03;

	ehcu_s_or_m = 0;

	TX_HCEPGN_REQUEST_59904.Reserved0 = 0x00;
	TargetSourceAddress = 0xff;

#ifdef NEW_CAN2
	InitNewProtoclValuable();
#endif
}

void Thread_Write_UART1(void *data) {
	int i = 0;

	InitUART1Valuable();
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Write_UART1\n");
	while (bWriteRunningFlag_UART1)
	{
		SendDataFromRingBuffer();
		if(nCTSFlag_MCU == 1)
		{
			Send_RTSData(&RTSData[0],nRTSDataLength,SA_MCU);
			nCTSFlag_MCU = 0;
		}
		sleep(0);
	}
}


void UART1_DataParsing(unsigned char* Data) {
	CAN_RX_PACKET*		CANPacket;

	CANPacket = (CAN_RX_PACKET*) Data;

	if (CANPacket->RX_STX == SERIAL_RX_STX && CANPacket->RX_ID == SERIAL_RX_ID)
	{
		if (CANPacket->RX_LEN == SERIAL_RX_DATA_LEN &&  CANPacket->RX_ETX == SERIAL_RX_ETX)
		{
			UART1_SeperateData_NEWCAN2((CANPacket->RX_PRIORITY) >> 2, CANPacket->RX_PF, CANPacket->RX_PS, CANPacket->RX_SOURCEADDR,Data);
		}
		else
		{
			__android_log_print(ANDROID_LOG_INFO, "NATIVE","UART1 Data Length[0x%x], ETX Fault[0x%x]\n",CANPacket->RX_LEN,CANPacket->RX_ETX);
		}
	}
	else
	{
		__android_log_print(ANDROID_LOG_INFO, "NATIVE","UART1 Data STX[0x%x], RX_ID Fault[0x%x]\n",CANPacket->RX_STX, CANPacket->RX_ID == SERIAL_RX_ID);
	}
}

void ThreadParsing_UART3(void *data) {
	unsigned char UART3_DataCurr[UART3_PARSING_SIZE][UART3_RXPACKET_SIZE];

	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "ThreadParsing UART3\n");
	//	while (bParsingRunningFlag_UART3) {
	//		if (bParsingFlag_UART3 == 1) {
	//			pthread_mutex_lock(&mutex_UART3);
	//			memcpy(&UART3_DataCurr, &UART3_RxTmpTwo,UART3_PARSING_SIZE * UART3_RXPACKET_SIZE);
	//			pthread_mutex_unlock(&mutex_UART3);
	//			UART3_DataParsing(UART3_DataCurr);
	//			bParsingFlag_UART3 = 0;
	//		}
	//		sleep(0); // 占쌕몌옙 Thread 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占�
	//
	//	}

}

void UART3_DataParsing(unsigned char (*pBuff)[UART3_RXPACKET_SIZE]) {
	unsigned char PF, PS, SourceAddr, priority;
	//	int nPF,nPS,nSourceAddr,npriority;
	int i = 0;
	int j = 0;

	for (i = 0; i < UART3_PARSING_SIZE; i++) {
		memcpy(&cUART3_RxData[0], &(pBuff[i][0]), UART3_RXPACKET_SIZE);
		if (cUART3_RxData[0] == SERIAL_RX_STX && cUART3_RxData[UART3_RXPACKET_SIZE-1] == SERIAL_RX_ETX)
		{
			//__android_log_print(ANDROID_LOG_INFO, "NATIVE", "ETX");
			UART3_SeparateData(cUART3_RxData[1]);

		}
		else if(cUART3_RxData[3] == SERIAL_RX_ACK){
			//__android_log_print(ANDROID_LOG_INFO, "NATIVE", "ACK");
			UART3_AckCheck(cUART3_RxData[UART3_RXPACKET_SIZE-1]);
		}
		else if(cUART3_RxData[3] == SERIAL_RX_NAK){
			//__android_log_print(ANDROID_LOG_INFO, "NATIVE", "NAK");
			UART3_AckCheck(cUART3_RxData[UART3_RXPACKET_SIZE-1]);
		}
		else
		{
			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "ELSE");
		}

	}
}

void UART3_SeparateData(unsigned char RES_Kind) {

	switch (RES_Kind) {
	case KeyRES:
		KeyButtonCallback(cUART3_RxData[2] + (cUART3_RxData[3] << 8) + (cUART3_RxData[4] << 16) + (cUART3_RxData[5] << 24));
		memcpy((unsigned char*) &RX_RES_KEY, &cUART3_RxData[2], 8);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Key[0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]",
			cUART3_RxData[2],cUART3_RxData[3],cUART3_RxData[4],cUART3_RxData[5],cUART3_RxData[6],
			cUART3_RxData[7],cUART3_RxData[8],cUART3_RxData[9],cUART3_RxData[10]);
		break;
	case LCDRES:

		break;
	case BuzRES:

		break;
	case RTCRES:
		memcpy((unsigned char*) &RX_RES_RTC, &cUART3_RxData[2], 8);
		break;
	case CAMRES:

		break;
	case VersionRES:
		memcpy((unsigned char*) &RX_RES_Version, &cUART3_RxData[2], 8);
		break;
	case DOWNRES:
		//memcpy((unsigned char*) &RX_RES_DOWN, &cUART3_RxData[2], 8);
		RX_RES_DOWN.UpdateResponse = cUART3_RxData[2];
		nRecvResDownFlag = 1;
		break;
	case SMKRES:

		memcpy((unsigned char*) &RX_RES_SMK, &cUART3_RxData[2], 8);
		if(RX_RES_SMK.Auth == 0xA5 || RX_RES_SMK.Auth == 0xFE){
			rx_smk_result.SMK_Auth_Result = RX_RES_SMK.Auth;
			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "SMK Auth[0x%x]",rx_smk_result.SMK_Auth_Result);
		}

		if(RX_RES_SMK.MSG != 0xFF){
			rx_smk_result.SMK_Msg_Result = RX_RES_SMK.MSG;
			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "SMK Msg[0x%x]",rx_smk_result.SMK_Msg_Result);
		}

		rx_smk_result.SMK_Tag_Reg_Count = RX_RES_SMK.Count;
		rx_smk_result.SMK_Response_Code = RX_RES_SMK.Response_Code;
		rx_smk_result.SMK_Response_Flag = RX_RES_SMK.Response_Flag;

		gRecvSMK = 1;
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "SMK Auth[0x%x] Msg[0x%x] Count[0x%x] ReCode[0x%x] ReFlag[0x%x]",rx_smk_result.SMK_Auth_Result
				,rx_smk_result.SMK_Msg_Result,rx_smk_result.SMK_Tag_Reg_Count,rx_smk_result.SMK_Response_Code,rx_smk_result.SMK_Response_Flag);
		break;
	case LampRES:

		break;
	case StartCANRES:

		break;
	case EEPROMTESTRES:
		EEPRomTestCallback(cUART3_RxData[3]);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "EEPROMTESTRES[0x%x]",cUART3_RxData[3]);
		break;
	case FLASHTESTRES:
		FlashTestCallback(cUART3_RxData[3]);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "FLASHTESTRES[0x%x]",cUART3_RxData[3]);
		break;
	}


}
void UART3_AckCheck(unsigned char Tail)
{
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Tail[0x%x]\n",Tail);
	switch(Tail)
	{
	case SERIAL_RX_ACK:
	case SERIAL_RX_NAK:
		RX_RES_DOWN.UpdateResponse = cUART3_RxData[2];
		nRecvResDownFlag = 1;
		break;
	}
}
void UART1_SaveData(unsigned char BuffSelect) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_SaveData\n");
	switch (BuffSelect) {
	case SERIAL_RX_SAVE_DATA1:
		memcpy((unsigned char*) &rx_save_data1, &cUART1_RxData[7], 8);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"rx_save_data1 [0x%x]  [0x%x] [0x%x] [0x%x] [0x%x] [0x%x] [0x%x] [0x%x]\n",
			cUART1_RxData[7], cUART1_RxData[8], cUART1_RxData[9],
			cUART1_RxData[10], cUART1_RxData[11], cUART1_RxData[12],
			cUART1_RxData[13], cUART1_RxData[14]);
		break;
	case SERIAL_RX_SAVE_DATA2:
		memcpy((unsigned char*) &rx_save_data2, &cUART1_RxData[7], 8);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"rx_save_data2 [0x%x]  [0x%x] [0x%x] [0x%x] [0x%x] [0x%x] [0x%x] [0x%x]\n",
			cUART1_RxData[7], cUART1_RxData[8], cUART1_RxData[9],
			cUART1_RxData[10], cUART1_RxData[11], cUART1_RxData[12],
			cUART1_RxData[13], cUART1_RxData[14]);
		break;
	case SERIAL_RX_SAVE_DATA3:
		memcpy((unsigned char*) &rx_save_data3, &cUART1_RxData[7], 8);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"rx_save_data3 [0x%x]  [0x%x] [0x%x] [0x%x] [0x%x] [0x%x] [0x%x] [0x%x]\n",
			cUART1_RxData[7], cUART1_RxData[8], cUART1_RxData[9],
			cUART1_RxData[10], cUART1_RxData[11], cUART1_RxData[12],
			cUART1_RxData[13], cUART1_RxData[14]);
		break;
	}
}

/////////////////////////////////////////////////
#if 0
void *Thread_Read_UART1(void *data)
{
	int dwRead = 0;
	int i = 0;
	unsigned char		UART1_ReadBuff[UART1_RXPACKET_SIZE];

	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read1\n");
	if (!glpVM)
	{
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL)
	{
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	while (bReadRunningFlag_UART1)
	{
		dwRead = 0;
		//	통신 시작 후 1byte씩 읽고, 정상 데이터를 수신한 다음부터는 원래 싸이즈로 받는다.
		if (UART1ReadFlag == 0 || UART1ReadFlag == 1)
		{
			dwRead = read(fd_UART1, UART1_ReadBuff, 1);
			//			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read_UART1 Read OK\n");
		}

		else
		{
			dwRead = read(fd_UART1, UART1_ReadBuff, UART1_RXPACKET_SIZE);
			//if(UART1_ReadBuff[4] == 0x97 && UART1_ReadBuff[5] == 0xFF)
			//{
			//			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff [%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d]\n",
			//				UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
			//				UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);
			//}
			//__android_log_print(ANDROID_LOG_INFO, "Dataparsing", "UART1_ReadBuff [%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d]\n",
			//    				UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
			//    				UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);

		}

		//	CAN PACKET 구조가 아니면 들어온 것을 모두 버린다.
		if (UART1ReadFlag == 2)
		{
			if (UART1_ReadBuff[0] != SERIAL_RX_STX || UART1_ReadBuff[UART1_RXPACKET_SIZE - 1] != SERIAL_RX_ETX)
			{

				tcflush(fd_UART1, TCIOFLUSH);
				UART1ReadFlag = 0;
				//				__android_log_print(ANDROID_LOG_INFO, "UART1_ReadBuff","UART1_Read Error\n");
				__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff Error[%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d]\n",
					UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
					UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);

				__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff Error RxRingBuffHead[%d] RxRingBuffTail[%d]\n",RxRingBuffHead,RxRingBuffTail);
			}
			else
			{
				UART1_DataParsing(UART1_ReadBuff);
			}
		}
		else if (UART1ReadFlag == 0)
		{
			if (UART1_ReadBuff[0] == SERIAL_RX_STX)
			{
				UART1ReadFlag = 1;
				dwUART1ReadCnt = 1;
			} else
			{
				dwUART1ReadCnt = 0;
			}
		}
		else if (UART1ReadFlag == 1)
		{
			if (dwUART1ReadCnt == (UART1_RXPACKET_SIZE - 1)&& UART1_ReadBuff[0] == SERIAL_RX_ETX)
			{
				UART1ReadFlag = 2;
			}
			else
			{
				if (dwUART1ReadCnt == (UART1_RXPACKET_SIZE - 1))
				{
					UART1ReadFlag = 0;
					dwUART1ReadCnt = 0;
				}
				else
				{
					dwUART1ReadCnt++;
				}
			}
		}
		sleep(0); // 다른 Thread 들의 점유를 위해 사용
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read1 Finish\n");
	(*glpVM)->DetachCurrentThread(glpVM);
}

void *Thread_Read_UART3(void *data) {
	int dwRead = 0;
	int i = 0;

	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read3\n");
	if (!glpVM) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL) {
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	while (bReadRunningFlag_UART3) {
		dwRead = 0;
		//	통신 시작 후 1byte씩 읽고, 정상 데이터를 수신한 다음부터는 원래 싸이즈로 받는다.
		if (UART3ReadFlag == 0 || UART3ReadFlag == 1) {
			dwRead = read(fd_UART3, UART3_ReadBuff, 1);
			__android_log_print(ANDROID_LOG_INFO, "UART3_ReadBuff","UART3_ReadBuff UART3_ReadBuff[0x%x]\n",UART3_ReadBuff[0]);
		}

		else {
			dwRead = read(fd_UART3, UART3_ReadBuff, UART3_RXPACKET_SIZE);
			//			__android_log_print(ANDROID_LOG_INFO, "UART3_ReadBuff", "UART3_ReadBuff [%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d]\n",UART3_ReadBuff[0],UART3_ReadBuff[1]
			//												  ,UART3_ReadBuff[2],UART3_ReadBuff[3],UART3_ReadBuff[4],UART3_ReadBuff[5],UART3_ReadBuff[6],UART3_ReadBuff[7]
			//												  ,UART3_ReadBuff[8],UART3_ReadBuff[9],UART3_ReadBuff[10]);
		}

		//	CMD PACKET 구조가 아니면 들어온 것을 모두 버린다.
		if (UART3ReadFlag == 2) {
			if (UART3_ReadBuff[UART3_RXPACKET_SIZE - 1] != SERIAL_RX_ETX) {
				//if (dwRead == UART3_RXPACKET_SIZE) {
				tcflush(fd_UART3, TCIOFLUSH);
				//}
				__android_log_print(ANDROID_LOG_INFO, "UART3_ReadBuff","UART3_ReadBuff Error dwRead[%d]\n",dwRead);
				__android_log_print(ANDROID_LOG_INFO, "UART3_ReadBuff",
					"UART3_ReadBuff Error[0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
					UART3_ReadBuff[0], UART3_ReadBuff[1], UART3_ReadBuff[2],UART3_ReadBuff[3],UART3_ReadBuff[4],UART3_ReadBuff[5],UART3_ReadBuff[6],UART3_ReadBuff[7],UART3_ReadBuff[8],UART3_ReadBuff[9],
					UART3_ReadBuff[10]);
				UART3ReadFlag = 0
			} else {

				memcpy(&(UART3_RxTmpOne[UART3_RxInx++][0]), &UART3_ReadBuff[0],
					UART3_RXPACKET_SIZE);

				if (UART3_RxInx >= UART3_PARSING_SIZE) {
					UART3_RxInx = 0;
					pthread_mutex_lock(&mutex_UART3);
					memcpy(&(UART3_RxTmpTwo), &(UART3_RxTmpOne),
						UART3_PARSING_SIZE * UART3_RXPACKET_SIZE);
					pthread_mutex_unlock(&mutex_UART3);
					bParsingFlag_UART3 = 1;
				}
			}
		} else if (UART3ReadFlag == 0) {
			if (UART3_ReadBuff[0] == SERIAL_RX_STX) {
				UART3ReadFlag = 1;
				dwUART3ReadCnt = 1;
			} else {
				dwUART3ReadCnt = 0;
			}
		} else if (UART3ReadFlag == 1) {
			if (dwUART3ReadCnt == (UART3_RXPACKET_SIZE - 1) && UART3_ReadBuff[0] == SERIAL_RX_ETX)
			{
				UART3ReadFlag = 2;
			}
			else
			{
				if (dwUART3ReadCnt == (UART3_RXPACKET_SIZE - 1))
				{
					UART3ReadFlag = 0;
					dwUART3ReadCnt = 0;
				}
				else
				{
					dwUART3ReadCnt++;
				}
			}
		}
		sleep(0); // 다른 Thread 들의 점유를 위해 사용
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read3 Finish\n");
	(*glpVM)->DetachCurrentThread(glpVM);
}
#else
void *Thread_Read_UART1(void *data)
{
	int dwRead = 0;
	int i = 0;
	unsigned char UART1_SingleBuff;
	unsigned char UART1_ReadBuff[UART1_RXPACKET_SIZE];
	unsigned char UART1_ReadBuff_Temp[UART1_RXPACKET_SIZE];
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read1\n");
	if (!glpVM)
	{
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL)
	{
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	while (bReadRunningFlag_UART1)
	{
		dwRead = 0;
		//	통신 시작 후 1byte씩 읽고, 정상 데이터를 수신한 다음부터는 원래 싸이즈로 받는다.
		if (UART1ReadFlag == 0)
		{
			dwRead = read(fd_UART1, &UART1_SingleBuff, 1);
			//			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read_UART1 Read OK [0x%x]\n",UART1_SingleBuff);
		}
		else
		{
			//			memcpy(UART1_ReadBuff_Temp,UART1_ReadBuff,UART1_RXPACKET_SIZE);
			dwRead = read(fd_UART1, UART1_ReadBuff, UART1_RXPACKET_SIZE);

			//			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff [0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
			//							UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
			//							UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);
			//			__android_log_print(ANDROID_LOG_INFO, "NATIVE","RX PGN[0x%x%x%x%x] Data[0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
			//					UART1_ReadBuff[6],UART1_ReadBuff[5],UART1_ReadBuff[4],UART1_ReadBuff[3],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9]
			//					,UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14]);
		}

		//	CAN PACKET 구조가 아니면 들어온 것을 모두 버린다.
		if (UART1ReadFlag == 1)
		{
			if (UART1_ReadBuff[0] != SERIAL_RX_STX || UART1_ReadBuff[1] != SERIAL_RX_ID || UART1_ReadBuff[UART1_RXPACKET_SIZE - 1] != SERIAL_RX_ETX)
			{
				__android_log_print(ANDROID_LOG_INFO, "NATIVE","UART1_Read Error dwRead[%d]\n",dwRead);
				__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff Error [0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
					UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
					UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);
				//				dwRead = read(fd_UART1, &UART1_SingleBuff, 1);
				//				__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff Error [0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
				//						UART1_ReadBuff_Temp[0],UART1_ReadBuff_Temp[1],UART1_ReadBuff_Temp[2],UART1_ReadBuff_Temp[3],UART1_ReadBuff_Temp[4],UART1_ReadBuff_Temp[5],UART1_ReadBuff_Temp[6],UART1_ReadBuff_Temp[7],UART1_ReadBuff_Temp[8],UART1_ReadBuff_Temp[9],
				//						UART1_ReadBuff_Temp[10],UART1_ReadBuff_Temp[11],UART1_ReadBuff_Temp[12],UART1_ReadBuff_Temp[13],UART1_ReadBuff_Temp[14],UART1_ReadBuff_Temp[15],UART1_ReadBuff_Temp[16]);
				//								dwRead = read(fd_UART1, &UART1_SingleBuff, 1);
				//				__android_log_print(ANDROID_LOG_INFO, "NATIVE","UART1_Read Error Single[%d]\n",UART1_SingleBuff);

				tcflush(fd_UART1, TCIOFLUSH);
				UART1ReadFlag = 0;
			}
			else
			{
//				pthread_mutex_lock(&mutex_UART1);
				UART1_DataParsing(UART1_ReadBuff);
//				pthread_mutex_unlock(&mutex_UART1);
			}
		}
		else if (UART1ReadFlag == 0)
		{

			switch (dwUART1ReadCnt) {
				case 0:
					if (UART1_SingleBuff == SERIAL_RX_STX)
					{
						UART1_ReadBuff[dwUART1ReadCnt++] = UART1_SingleBuff;
					}
					else
					{
						dwUART1ReadCnt = 0;
					}
					break;
				case UART1_RXPACKET_SIZE - 1:
					if (UART1_SingleBuff == SERIAL_RX_ETX)
					{
						UART1_ReadBuff[dwUART1ReadCnt] = UART1_SingleBuff;
						dwUART1ReadCnt = 0;
						UART1ReadFlag = 1;
//						pthread_mutex_lock(&mutex_UART1);
						UART1_DataParsing(UART1_ReadBuff);
//						pthread_mutex_unlock(&mutex_UART1);
						//						__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff [0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
						//													UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
						//													UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);
					}else
					{
						UART1_ReadBuff[dwUART1ReadCnt] = UART1_SingleBuff;
						tcflush(fd_UART1, TCIOFLUSH);
						dwUART1ReadCnt = 0;
						UART1ReadFlag = 0;
						//						__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_ReadBuff Error[%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d][%d]\n",
						//											UART1_ReadBuff[0],UART1_ReadBuff[1],UART1_ReadBuff[2],UART1_ReadBuff[3],UART1_ReadBuff[4],UART1_ReadBuff[5],UART1_ReadBuff[6],UART1_ReadBuff[7],UART1_ReadBuff[8],UART1_ReadBuff[9],
						//											UART1_ReadBuff[10],UART1_ReadBuff[11],UART1_ReadBuff[12],UART1_ReadBuff[13],UART1_ReadBuff[14],UART1_ReadBuff[15],UART1_ReadBuff[16]);
					}
					break;

				default:
					UART1_ReadBuff[dwUART1ReadCnt++] = UART1_SingleBuff;
					break;
			}

		}

		sleep(0); // 다른 Thread 들의 점유를 위해 사용
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read1 Finish\n");
	(*glpVM)->DetachCurrentThread(glpVM);
}
#endif
void *Thread_Read_UART3(void *data) {

	int dwRead = 0;
	int i = 0;
	unsigned char UART3_SingleBuff;
	unsigned char UART3_ReadBuff[UART3_RXPACKET_SIZE];
	unsigned char UART3_ReadBuff_Temp[UART3_RXPACKET_SIZE];

	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read3\n");
	if (!glpVM)
	{
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL)
	{
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	while (bReadRunningFlag_UART3)
	{
		dwRead = 0;
		//	통신 시작 후 1byte씩 읽고, 정상 데이터를 수신한 다음부터는 원래 싸이즈로 받는다.
		if (UART3ReadFlag == 0)
		{
			dwRead = read(fd_UART3, &UART3_SingleBuff, 1);
		}
		else
		{

			dwRead = read(fd_UART3, UART3_ReadBuff, UART3_RXPACKET_SIZE);
		}

		//	CMD PACKET 구조가 아니면 들어온 것을 모두 버린다.
		if (UART3ReadFlag == 1)
		{
			if (UART3_ReadBuff[0] != SERIAL_RX_STX || UART3_ReadBuff[UART3_RXPACKET_SIZE - 1] != SERIAL_RX_ETX)
			{
				__android_log_print(ANDROID_LOG_INFO, "NATIVE","UART3_Read Error dwRead[%d]\n",dwRead);
				__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART3_ReadBuff Error [0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x][0x%x]\n",
					UART3_ReadBuff[0],UART3_ReadBuff[1],UART3_ReadBuff[2],UART3_ReadBuff[3],UART3_ReadBuff[4],UART3_ReadBuff[5],UART3_ReadBuff[6],UART3_ReadBuff[7],UART3_ReadBuff[8],UART3_ReadBuff[9],
					UART3_ReadBuff[10]);


				tcflush(fd_UART3, TCIOFLUSH);
				UART3ReadFlag = 0;
			}
			else
			{
//				pthread_mutex_lock(&mutex_UART3);
				UART3_DataParsing(UART3_ReadBuff);
//				pthread_mutex_unlock(&mutex_UART3);
			}
		}
		else if (UART3ReadFlag == 0)
		{

			switch (dwUART3ReadCnt) {
				case 0:
					if (UART3_SingleBuff == SERIAL_RX_STX)
					{
						UART3_ReadBuff[dwUART3ReadCnt++] = UART3_SingleBuff;
					}
					else
					{
						dwUART3ReadCnt = 0;
					}
					break;
				case UART3_RXPACKET_SIZE - 1:
					if (UART3_SingleBuff == SERIAL_RX_ETX)
					{
						UART3_ReadBuff[dwUART3ReadCnt] = UART3_SingleBuff;
						dwUART3ReadCnt = 0;
						UART3ReadFlag = 1;
//						pthread_mutex_lock(&mutex_UART3);
						UART3_DataParsing(UART3_ReadBuff);
//						pthread_mutex_unlock(&mutex_UART3);
					}else
					{
						UART3_ReadBuff[dwUART3ReadCnt] = UART3_SingleBuff;
						tcflush(fd_UART3, TCIOFLUSH);
						dwUART3ReadCnt = 0;
						UART3ReadFlag = 0;
					}
					break;

				default:
					UART3_ReadBuff[dwUART3ReadCnt++] = UART3_SingleBuff;
					break;
			}

		}

		sleep(0); // 다른 Thread 들의 점유를 위해 사용
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread_Read1 Finish\n");
	(*glpVM)->DetachCurrentThread(glpVM);
}

speed_t getBaudrate(jint baudrate) {
	switch (baudrate) {
	case 0:
		return B0;
	case 50:
		return B50;
	case 75:
		return B75;
	case 110:
		return B110;
	case 134:
		return B134;
	case 150:
		return B150;
	case 200:
		return B200;
	case 300:
		return B300;
	case 600:
		return B600;
	case 1200:
		return B1200;
	case 1800:
		return B1800;
	case 2400:
		return B2400;
	case 4800:
		return B4800;
	case 9600:
		return B9600;
	case 19200:
		return B19200;
	case 38400:
		return B38400;
	case 57600:
		return B57600;
	case 115200:
		return B115200;
	case 230400:
		return B230400;
	case 460800:
		return B460800;
	case 500000:
		return B500000;
	case 576000:
		return B576000;
	case 921600:
		return B921600;
	case 1000000:
		return B1000000;
	case 1152000:
		return B1152000;
	case 1500000:
		return B1500000;
	case 2000000:
		return B2000000;
	case 2500000:
		return B2500000;
	case 3000000:
		return B3000000;
	case 3500000:
		return B3500000;
	case 4000000:
		return B4000000;
	default:
		return -1;
	}
}

jobject _Open_UART1(JNIEnv *env, jclass this, jstring path, jint baudrate,jint flags) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Oepn UART1.\n");

	speed_t speed;
	jobject mFileDescriptor;

	/* Check arguments */
	{
		speed = getBaudrate(baudrate);
		if (speed == -1) {
			/* TODO: throw an exception */
			LOGE("Invalid baudrate");
			return NULL;
		}
	}

	/* Opening device */
	{
		jboolean iscopy;
		const char *path_utf = (*env)->GetStringUTFChars(env, path, &iscopy);
		LOGD("Opening serial port %s with flags 0x%x", path_utf,
			O_RDWR | flags);
		fd_UART1 = open(path_utf, O_RDWR | flags);
		LOGD("open() fd_UART1 = %d", fd_UART1);
		(*env)->ReleaseStringUTFChars(env, path, path_utf);
		if (fd_UART1 == -1) {
			/* Throw an exception */
			LOGE("Cannot open port UART1");
			/* TODO: throw an exception */
			return NULL;
		}
	}

	/* Configure device */
	{
		struct termios cfg;
		LOGD("Configuring serial port UART1");
		if (tcgetattr(fd_UART1, &cfg)) {
			LOGE("tcgetattr() failed");
			close(fd_UART1);
			/* TODO: throw an exception */
			return NULL;
		}

		cfmakeraw(&cfg);
		cfsetispeed(&cfg, speed);
		cfsetospeed(&cfg, speed);

		cfg.c_cc[VTIME] = 0;
		cfg.c_cc[VMIN] = UART1_RXPACKET_SIZE;

		if (tcsetattr(fd_UART1, TCSANOW, &cfg)) {
			LOGE("tcsetattr() failed");
			close(fd_UART1);
			/* TODO: throw an exception */
			return NULL;
		}
	}

	/* Create a corresponding file descriptor */
	{
		jclass cFileDescriptor = (*env)->FindClass(env,
			"java/io/FileDescriptor");
		jmethodID iFileDescriptor = (*env)->GetMethodID(env, cFileDescriptor,
			"<init>", "()V");
		jfieldID descriptorID = (*env)->GetFieldID(env, cFileDescriptor,
			"descriptor", "I");
		mFileDescriptor = (*env)->NewObject(env, cFileDescriptor,
			iFileDescriptor);
		(*env)->SetIntField(env, mFileDescriptor, descriptorID,
			(jint) fd_UART1);
	}

	int arg;
	bReadRunningFlag_UART1 = 1;
	thr_id[0] = pthread_create(&p_thread[0], NULL, Thread_Read_UART1,
		(void *) &arg);
	if (thr_id[0] < 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE","Create read Thread_Read_UART1 fail.\n");
		return -1;
	} else {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE","Create read Thread_Read_UART1 success id[%d].\n", p_thread[0]);
	}

	int arg2;
	bWriteRunningFlag_UART1 = 1;
	thr_id[1] = pthread_create(&p_thread[1], NULL, Thread_Write_UART1,
			(void *) &arg2);
	if (thr_id[1] < 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE","Create parsing Thread_Write_UART1 fail.\n");
		return -1;
	} else {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE","Create parsing Thread_Write_UART1 success id[%d].\n",p_thread[1]);
	}

	InitUART1Valuable();

	//makeTimer("First Timer", &firstTimerID, 0, TIMER1_INTERVAL);
	//makeTimer("Second Timer", &SecondTimerID, 2, 0);

	return mFileDescriptor;

}

jobject _Open_UART3(JNIEnv *env, jclass this, jstring path, jint baudrate,jint flags) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Oepn UART3.\n");

	speed_t speed;
	jobject mFileDescriptor;

	/* Check arguments */
	{
		speed = getBaudrate(baudrate);
		if (speed == -1) {
			/* TODO: throw an exception */
			LOGE("Invalid baudrate");
			return NULL;
		}
	}

	/* Opening device */
	{
		jboolean iscopy;
		const char *path_utf = (*env)->GetStringUTFChars(env, path, &iscopy);
		LOGD("Opening serial port %s with flags 0x%x", path_utf,O_RDWR | flags);
		fd_UART3 = open(path_utf, O_RDWR | flags);
		LOGD("open() fd_UART3 = %d", fd_UART3);
		(*env)->ReleaseStringUTFChars(env, path, path_utf);
		if (fd_UART3 == -1) {
			/* Throw an exception */
			LOGE("Cannot open port UART3");
			/* TODO: throw an exception */
			return NULL;
		}
	}

	/* Configure device */
	{
		struct termios cfg;
		LOGD("Configuring serial port UART3");
		if (tcgetattr(fd_UART3, &cfg)) {
			LOGE("tcgetattr() failed");
			close(fd_UART3);
			/* TODO: throw an exception */
			return NULL;
		}

		cfmakeraw(&cfg);
		cfsetispeed(&cfg, speed);
		cfsetospeed(&cfg, speed);

		cfg.c_cc[VTIME] = 0;
		cfg.c_cc[VMIN] = UART3_RXPACKET_SIZE;

		if (tcsetattr(fd_UART3, TCSANOW, &cfg)) {
			LOGE("tcsetattr() failed");
			close(fd_UART3);
			/* TODO: throw an exception */
			return NULL;
		}
	}

	/* Create a corresponding file descriptor */
	{
		jclass cFileDescriptor = (*env)->FindClass(env,
			"java/io/FileDescriptor");
		jmethodID iFileDescriptor = (*env)->GetMethodID(env, cFileDescriptor,
			"<init>", "()V");
		jfieldID descriptorID = (*env)->GetFieldID(env, cFileDescriptor,
			"descriptor", "I");
		mFileDescriptor = (*env)->NewObject(env, cFileDescriptor,
			iFileDescriptor);
		(*env)->SetIntField(env, mFileDescriptor, descriptorID,
			(jint) fd_UART3);
	}

	int arg;
	bReadRunningFlag_UART3 = 1;
	thr_id[2] = pthread_create(&p_thread[2], NULL, Thread_Read_UART3,
		(void *) &arg);
	if (thr_id[2] < 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Create read Thread_Read_UART3 fail.\n");
		return -1;
	} else {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Create read Thread_Read_UART3 success id[%d].\n", p_thread[2]);
	}

	//	int arg2;
	//	bParsingRunningFlag_UART3 = 1;
	//	thr_id[3] = pthread_create(&p_thread[3], NULL, ThreadParsing_UART3,(void *) &arg2);
	//	if (thr_id[3] < 0) {
	//		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
	//				"Create parsing ThreadParsing_UART3 fail.\n");
	//		return -1;
	//	} else {
	//		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
	//				"Create parsing ThreadParsing_UART3 success id[%d].\n",
	//				p_thread[3]);
	//	}

	return mFileDescriptor;

}

void _Close_UART1(JNIEnv *env, jobject this) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Close UART1.\n");
	jclass SerialPortClass = (*env)->GetObjectClass(env, this);
	jclass FileDescriptorClass = (*env)->FindClass(env,
		"java/io/FileDescriptor");

	jfieldID mFdID = (*env)->GetFieldID(env, SerialPortClass, "mFdUART1",
		"Ljava/io/FileDescriptor;");
	jfieldID descriptorID = (*env)->GetFieldID(env, FileDescriptorClass,
		"descriptor", "I");

	jobject mFd = (*env)->GetObjectField(env, this, mFdID);
	jint descriptor = (*env)->GetIntField(env, mFd, descriptorID);

	LOGD("close(fd = %d)", descriptor);
	close(descriptor);
	bReadRunningFlag_UART1 = 0;
	bWriteRunningFlag_UART1 = 0;

	int status = pthread_kill(p_thread[0], 0);

	if (status == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Thread ID[%d] kill sucess\n", p_thread[0]);
	}
	//else if ( status == ESRCH ) {
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] not exist..\n", p_thread[0]);
	//}
	//else if ( status == EINVAL ) {
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] is yet alive\n", p_thread[0]);
	//}
	else {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Thread ID[%d] kill fail\n", p_thread[0]);
	}

	status = pthread_kill(p_thread[1], 0);

	if (status == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
				"Thread ID[%d] kill sucess\n", p_thread[1]);
	}
//	else if ( status == ESRCH ) {
//		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] not exist..\n", p_thread[1]);
//	}
//	else if ( status == EINVAL ) {
//		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] is yet alive\n", p_thread[1]);
//	}
	else {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
				"Thread ID[%d] kill fail\n", p_thread[1]);
	}

	timer_delete(&firstTimerID);
}

void _Close_UART3(JNIEnv *env, jobject this) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Close UART3.\n");
	jclass SerialPortClass = (*env)->GetObjectClass(env, this);
	jclass FileDescriptorClass = (*env)->FindClass(env,
		"java/io/FileDescriptor");

	jfieldID mFdID = (*env)->GetFieldID(env, SerialPortClass, "mFdUART3",
		"Ljava/io/FileDescriptor;");
	jfieldID descriptorID = (*env)->GetFieldID(env, FileDescriptorClass,
		"descriptor", "I");

	jobject mFd = (*env)->GetObjectField(env, this, mFdID);
	jint descriptor = (*env)->GetIntField(env, mFd, descriptorID);

	LOGD("close(fd = %d)", descriptor);
	close(descriptor);
	bReadRunningFlag_UART3 = 0;
	bParsingRunningFlag_UART3 = 0;

	int status = pthread_kill(p_thread[2], 0);

	if (status == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Thread ID[%d] kill sucess\n", p_thread[2]);
	}
	//else if ( status == ESRCH ) {
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] not exist..\n", p_thread[2]);
	//}
	//else if ( status == EINVAL ) {
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] is yet alive\n", p_thread[2]);
	//}
	else {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Thread ID[%d] kill fail\n", p_thread[2]);
	}

	//	status = pthread_kill(p_thread[3], 0);
	//
	//	if (status == 0) {
	//		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
	//				"Thread ID[%d] kill sucess\n", p_thread[3]);
	//	}
	//else if ( status == ESRCH ) {
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] not exist..\n", p_thread[3]);
	//}
	//else if ( status == EINVAL ) {
	//	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Thread ID[%d] is yet alive\n", p_thread[3]);
	//}
	//	else {
	//		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
	//				"Thread ID[%d] kill fail\n", p_thread[3]);
	//	}
}

jint _Write_UART1(JNIEnv *env, jobject this, jbyteArray arr, jint size) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Write.\n");
	jbyte *carr;
	int result = 0;

	carr = (*env)->GetByteArrayElements(env, arr, NULL);

	result = write(fd_UART1, carr, size);

#ifdef DEBUG_WRITE
	int i = 0;
	for(i = 0; i < size; i++)
	{
		__android_log_print(ANDROID_LOG_INFO, "NATIVE_WRITE UART1", "carr[%d].\n",carr[i]);
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE_WRITE", "size[%d].\n",size);
	__android_log_print(ANDROID_LOG_INFO, "NATIVE_WRITE", "result[%d].\n",result);
#endif

	(*env)->ReleaseByteArrayElements(env, arr, carr, 0);

	return result;
}

jint _Write_UART3(JNIEnv *env, jobject this, jbyteArray arr, jint size) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Write UART3.\n");
	jbyte *carr;
	int result = 0;

	carr = (*env)->GetByteArrayElements(env, arr, NULL);

	result = write(fd_UART3, carr, size);

#ifdef DEBUG_WRITE
	int i = 0;
	for(i = 0; i < size; i++)
	{
		__android_log_print(ANDROID_LOG_INFO, "NATIVE_WRITE", "carr[%d].\n",carr[i]);
	}
	__android_log_print(ANDROID_LOG_INFO, "NATIVE_WRITE", "size[%d].\n",size);
	__android_log_print(ANDROID_LOG_INFO, "NATIVE_WRITE", "result[%d].\n",result);
#endif

	(*env)->ReleaseByteArrayElements(env, arr, carr, 0);

	return result;
}

jint _UART1_TxComm(JNIEnv *env, jobject this, jint PS) {
	__android_log_print(ANDROID_LOG_INFO, "NATIVE", "_UART1_TxComm [%d]\n", PS);
	int result = 0;

	switch (PS) {
		case 1:

			break;

			///////////////61184//////////////
		case 11	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_DTC_INFORMATION_REQUEST_61184_11);
			break;
		case 12	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_MAINTENANCE_REQUSET_61184_12);
			break;
		case 21	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_HCE_ANTI_THEFT_ENCRYPTION_SEED_REQUEST_61184_21);
			break;
		case 23	:
			//	memcpy(&tx_buf[4], (unsigned char*) &TX_HCE_ANTI_THEFT_REQUEST_61184_23, sizeof(TX_HCE_ANTI_THEFT_REQUEST_61184_23));
			nRTSDataLength = sizeof(TX_HCE_ANTI_THEFT_REQUEST_61184_23);
			memset((unsigned char*) RTSData,0xFF, sizeof(RTSData));
			memcpy(&RTSData[0], (unsigned char*) &TX_HCE_ANTI_THEFT_REQUEST_61184_23, sizeof(TX_HCE_ANTI_THEFT_REQUEST_61184_23));
			Send_RTS(0x1C,0xEF,0x00,SA_MCU,SA_MONITOR,nRTSDataLength);
			break;
		case 31	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_OPERATION_HISTORY_REQUEST_61184_31);
			break;
		case 61	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_COOLING_FAN_SETTING_61184_61);
			break;
		case 62	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_WEIGHING_SYSTEM_SETTING_REQUEST_61184_62);
			break;
		case 101	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_MACHINE_MODE_SETTING_61184_101);
			break;
		case 104	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_TRAVEL_MODE_SETTING_61184_104);
			break;
		case 105	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_TRAVEL_CONTROL_VALUE_SETTING_61184_105);
			break;
		case 109	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_MACHINE_ACCESSORY_SETTING_REQUEST_61184_109);
			break;
		case 121	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_ENGINE_SHUTDOWN_MODE_SETTING_61184_121);
			break;
		case 123	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_DETENT_MODE_SETTING_61184_123);
			break;
		case 201	:
			MakeCANDataSingle(0x18,0xEF,SA_MCU,SA_MONITOR,(unsigned char*)&TX_WHEEL_LOADER_SENSOR_CALIBRATION_REQUEST_61184_201);
			break;
		case 203	:
			MakeCANDataSingle(0x18,0xEF,SA_EHCU,SA_MONITOR,(unsigned char*)&TX_WHEEL_LOADER_EHCU_SETTING_61184_203);
			__android_log_print(ANDROID_LOG_INFO, "NATIVE", "UART1_Tx EHCU\n");
			break;
			///////////////////////////////////
			/////////////0xEAxx////////////////
		case 234	:
			MakeCANDataSingle(0x18,0xEA,TargetSourceAddress,SA_MONITOR,(unsigned char*)&TX_HCEPGN_REQUEST_59904);
			break;
			///////////////////////////////////
			/////////////0xFFxx////////////////
		case 47	:
			MakeCANDataSingle(0x18,0xFF,PS,SA_MONITOR,(unsigned char*)&TX_MONIOTR_STATUS_65327);
			break;
		case 50	:	
			Send_CID();
			//memcpy(&tx_buf[4], (unsigned char*) &TX_COMPONENT_IDENTIFICATION_MONITOR_65330, sizeof(TX_COMPONENT_IDENTIFICATION_MONITOR_65330));
			break;
		case 145	:
			Send_ASPhoneNumber();
			break;
		case 247	:
			MakeCANDataSingle(0x18,0xFF,PS,SA_MONITOR,(unsigned char*)&TX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527);
			break;
			///////////////////////////////////

	}

	return result;
}

jint _UART3_TxComm(JNIEnv *env, jobject this, jint CMD, jint DAT1, jint DAT2,
				   jint DAT3, jint DAT4, jint DAT5, jint DAT6, jint DAT7, jint DAT8) {
					   __android_log_print(ANDROID_LOG_INFO, "NATIVE", "Write UART3.\n");
					   int result = 0;

					   jbyte tx_buf[UART3_TXPACKET_SIZE];

					   tx_buf[0] = 0x02;	// STX
					   tx_buf[1] = CMD;
					   tx_buf[2] = DAT1;
					   tx_buf[3] = DAT2;
					   tx_buf[4] = DAT3;
					   tx_buf[5] = DAT4;
					   tx_buf[6] = DAT5;
					   tx_buf[7] = DAT6;
					   tx_buf[8] = DAT7;
					   tx_buf[9] = DAT8;
					   tx_buf[UART3_TXPACKET_SIZE-1] = 0x03;	// ETX


					   result = write(fd_UART3, tx_buf, UART3_TXPACKET_SIZE);

					   return result;
}
void SetKeypadLamp()
{
	unsigned char OldLamp[8];
	unsigned char NewLamp[8];
	int i;
	int nDiffFlag = 0;

	memset(&NewLamp[0], 0xFF, 8);
	memcpy(&OldLamp[0],(unsigned char*)&TX_CMD_Lamp,8);


	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IlluminationOperationStatus_3438 == 0)
		TX_CMD_Lamp.MainLight1 = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IlluminationOperationStatus_3438 == 1)
		TX_CMD_Lamp.MainLight1 = 1;

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.HeadLampOperationStatus_3436 == 0)
		TX_CMD_Lamp.MainLight2 = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.HeadLampOperationStatus_3436 == 1)
		TX_CMD_Lamp.MainLight2 = 1;

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.WorkLampOperationStatus_3435 == 0)
		TX_CMD_Lamp.WorkLight1 = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.WorkLampOperationStatus_3435 == 1)
		TX_CMD_Lamp.WorkLight1 = 1;

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWorkLampOperationStatus_3446 == 0)
		TX_CMD_Lamp.WorkLight2 = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWorkLampOperationStatus_3446 == 1)
		TX_CMD_Lamp.WorkLight2 = 1;

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.AutoGreaseOperationStatus_3449 == 3)
	{
		TX_CMD_Lamp.AutoGrease1 = 0;
		TX_CMD_Lamp.AutoGrease2 = 0;
	}
	else
	{
		if(RX_INDICATOR_LAMP_65428.AutoGreaseGreenLEDStatus_3453 == 0)
			TX_CMD_Lamp.AutoGrease1 = 0;
		else if(RX_INDICATOR_LAMP_65428.AutoGreaseGreenLEDStatus_3453 == 1)
			TX_CMD_Lamp.AutoGrease1 = 1;

		if(RX_INDICATOR_LAMP_65428.AutoGreaseRedLEDStatus_3453 == 0)
			TX_CMD_Lamp.AutoGrease2 = 0;
		else if(RX_INDICATOR_LAMP_65428.AutoGreaseRedLEDStatus_3453 == 1)
			TX_CMD_Lamp.AutoGrease2 = 1;
	}

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.QuickCouplerOperationStatus_3448 == 0){
		TX_CMD_Lamp.QuickCoupler1 = 0;
		TX_CMD_Lamp.QuickCoupler2 = 1;
	}else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.QuickCouplerOperationStatus_3448 == 1){
		TX_CMD_Lamp.QuickCoupler1 = 1;
		TX_CMD_Lamp.QuickCoupler2 = 0;
	}else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.QuickCouplerOperationStatus_3448 == 2){
		TX_CMD_Lamp.QuickCoupler1 = 0;
		TX_CMD_Lamp.QuickCoupler2 = 0;
	}else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.QuickCouplerOperationStatus_3448 == 3){
		TX_CMD_Lamp.QuickCoupler1 = 0;
		TX_CMD_Lamp.QuickCoupler2 = 0;
	}

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RideControlOperationStatus_3447 == 0){
		TX_CMD_Lamp.RideControl1 = 0;
		TX_CMD_Lamp.RideControl2 = 0;
	}
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RideControlOperationStatus_3447 == 1){
		TX_CMD_Lamp.RideControl1 = 1;
		TX_CMD_Lamp.RideControl2 = 0;
	}
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RideControlOperationStatus_3447 == 2){
		TX_CMD_Lamp.RideControl1 = 0;
		TX_CMD_Lamp.RideControl2 = 1;
	}
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RideControlOperationStatus_3447 == 3){
		TX_CMD_Lamp.RideControl1 = 0;
		TX_CMD_Lamp.RideControl2 = 0;
	}


	if(RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemAccumulationMode_1941 == 0){
		TX_CMD_Lamp.WorkLoad1 = 0;
		TX_CMD_Lamp.WorkLoad2 = 1;
	}

	else if(RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemAccumulationMode_1941 == 1){
		TX_CMD_Lamp.WorkLoad1 = 1;
		TX_CMD_Lamp.WorkLoad2 = 0;
	}

	else if(RX_WEIGHING_SYSTEM_STATUS_65450.WeighingSystemAccumulationMode_1941 == 3){
		TX_CMD_Lamp.WorkLoad1 = 0;
		TX_CMD_Lamp.WorkLoad2 = 1;
	}

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.BeaconLampOperationStatus_3444 == 0)
		TX_CMD_Lamp.BeaconLamp = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.BeaconLampOperationStatus_3444 == 1)
		TX_CMD_Lamp.BeaconLamp = 1;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.BeaconLampOperationStatus_3444 == 3)
		TX_CMD_Lamp.BeaconLamp = 0;

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperOperationStatus_3451 == 0)
		TX_CMD_Lamp.RearWiper = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperOperationStatus_3451 == 1
		||	RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.RearWiperOperationStatus_3451 == 2)
		TX_CMD_Lamp.RearWiper = 1;

	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.MirrorHeatOperationStatus_3450 == 3)
		TX_CMD_Lamp.MirrorHeat = 0;
	else
	{
		if(RX_INDICATOR_LAMP_65428.MirrorHeaterStatus_724 == 0)
			TX_CMD_Lamp.MirrorHeat = 0;
		else if(RX_INDICATOR_LAMP_65428.MirrorHeaterStatus_724 == 1)
			TX_CMD_Lamp.MirrorHeat = 1;
	}

	if(RX_DETENT_MODE_STATUS_61184_124.BoomDetentMode_223 > 0 && RX_DETENT_MODE_STATUS_61184_124.BoomDetentMode_223 < 4)
		TX_CMD_Lamp.AutoPosition1 = 1;
	else
		TX_CMD_Lamp.AutoPosition1 = 0;


	if(RX_DETENT_MODE_STATUS_61184_124.BucketDetentMode_224 > 0 && RX_DETENT_MODE_STATUS_61184_124.BucketDetentMode_224 < 4)
		TX_CMD_Lamp.AutoPosition2 = 1;
	else
		TX_CMD_Lamp.AutoPosition2 = 0;

	if(RX_COMPONENT_IDENTIFICATION_EHCU_65330.ComponentCode_1699 != 91)	// STATE_COMPONENTCODE_EHCU = 91
	{
		TX_CMD_Lamp.FineModulation = 0;
	}
	else
	{
		if(RX_WHEEL_LOADER_EHCU_STATUS_65517.FlowFineModulationOperation_2302 == 0)
			TX_CMD_Lamp.FineModulation = 0;
		else if(RX_WHEEL_LOADER_EHCU_STATUS_65517.FlowFineModulationOperation_2302 == 1)
			TX_CMD_Lamp.FineModulation = 1;
		else
			TX_CMD_Lamp.FineModulation = 0;
	}

	TX_CMD_Lamp.FN = nFNLamp;


	if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IlluminationOperationStatus_3438 == 0)
		TX_CMD_Lamp.Illumination = 0;
	else if(RX_ELECTRICAL_SWITCH_RELAY_OPERATION_STATUS_65527.IlluminationOperationStatus_3438 == 1)
		TX_CMD_Lamp.Illumination = 1;

	memcpy(&NewLamp[0],(unsigned char*)&TX_CMD_Lamp,8);


	for(i = 0; i < 8; i++){
		if(NewLamp[i] != OldLamp[i]){
			nDiffFlag = 1;
			break;
		}
	}
	if(nDiffFlag == 1){
		jbyte tx_buf[UART3_TXPACKET_SIZE];

		memcpy(&tx_buf[2],(unsigned char*)&TX_CMD_Lamp,8);

		tx_buf[0] = 0x02;	// STX
		tx_buf[1] = LampCMD;

		tx_buf[UART3_TXPACKET_SIZE-1] = 0x03;	// ETX


		write(fd_UART3, tx_buf, UART3_TXPACKET_SIZE);

		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "Lamp Data Send");
	}



}

void KeyButtonCallback(unsigned int KeyData) {
	jmethodID funcKeyCallBack;

	if (!glpVM) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL) {
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	funcKeyCallBack = (*env)->GetStaticMethodID(env, jObject,
		"KeyButtonCallBack", "(I)V");

	if (funcKeyCallBack == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Can't fine the function.");
		(*env)->DeleteGlobalRef(env, jObject);
	}

	else {
		(*env)->CallStaticVoidMethod(env, jObject, funcKeyCallBack, KeyData);
	}

	(*glpVM)->DetachCurrentThread(glpVM);
}

void EEPRomTestCallback(unsigned int Data) {
	jmethodID funcEEPRomTestCallBack;

	if (!glpVM) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL) {
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	funcEEPRomTestCallBack = (*env)->GetStaticMethodID(env, jObject,
		"EEPRomTestCallback", "(I)V");

	if (funcEEPRomTestCallBack == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Can't fine the function.");
		(*env)->DeleteGlobalRef(env, jObject);
	}

	else {
		(*env)->CallStaticVoidMethod(env, jObject, funcEEPRomTestCallBack, Data);
	}

	(*glpVM)->DetachCurrentThread(glpVM);
}

void FlashTestCallback(unsigned int Data) {
	jmethodID funcFlashTestCallBack;

	if (!glpVM) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL) {
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	funcFlashTestCallBack = (*env)->GetStaticMethodID(env, jObject,
		"FlashTestCallback", "(I)V");

	if (funcFlashTestCallBack == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Can't fine the function.");
		(*env)->DeleteGlobalRef(env, jObject);
	}

	else {
		(*env)->CallStaticVoidMethod(env, jObject, funcFlashTestCallBack, Data);
	}

	(*glpVM)->DetachCurrentThread(glpVM);
}

void CIDCallback() {
	jmethodID CIDCallBack;

	if (!glpVM) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL) {
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	CIDCallBack = (*env)->GetStaticMethodID(env, jObject, "CIDCallBack", "()V");

	if (CIDCallBack == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Can't fine the function.");
		(*env)->DeleteGlobalRef(env, jObject);
	}

	else {
		(*env)->CallStaticVoidMethod(env, jObject, CIDCallBack);
	}

	(*glpVM)->DetachCurrentThread(glpVM);
}

void ASCallback() {
	jmethodID ASCallback;

	if (!glpVM) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE", "error (!glpVM)");
		return;
	}

	JNIEnv *env = NULL;
	(*glpVM)->AttachCurrentThread(glpVM, &env, NULL);
	if (env == NULL || jObject == NULL) {
		(*glpVM)->DetachCurrentThread(glpVM);
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"error (env == NULL || AVM_JM.jObject == NULL)");
		return;
	}

	ASCallback = (*env)->GetStaticMethodID(env, jObject, "ASCallback", "()V");

	if (ASCallback == 0) {
		__android_log_print(ANDROID_LOG_INFO, "NATIVE",
			"Can't fine the function.");
		(*env)->DeleteGlobalRef(env, jObject);
	}

	else {
		(*env)->CallStaticVoidMethod(env, jObject, ASCallback);
	}

	(*glpVM)->DetachCurrentThread(glpVM);
}


