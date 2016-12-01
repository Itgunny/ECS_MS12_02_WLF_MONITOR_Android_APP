
#include <jni.h>
#include "android/log.h"
#include "CAN_Structure.h"
#include <string.h>
#include <pthread.h>
#include "GlobalValuable.h"



#include <termios.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include <pthread.h>

#include "android/log.h"
#include <signal.h>
#include <stdio.h>
#include <sys/time.h>


static void timer_handler( int sig, siginfo_t *si, void *uc );
static int makeTimer( char *name, timer_t *timerID, int sec, int msec );
void SetDatatoRingBuffer(unsigned char* Buf);
int SendDataFromRingBuffer();
void MakeCANDataSingle(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char SA, unsigned char *Data);
void MakeCANDataMultiBoradcast(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char DA, unsigned char SA, unsigned char *Data, unsigned int Length);
void MakeMultiPacketData(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char DA, unsigned char SA, unsigned char* Data, unsigned int Length, unsigned int Index);
void Send_CTS(unsigned char Priority,unsigned char DA, unsigned char SA, unsigned char* Data);
void Send_RTS(unsigned char Priority, unsigned char PF, unsigned char PS, unsigned char DA, unsigned char SA, unsigned short Size);
void Send_ASPhoneNumber();
void Send_RTSData(unsigned char* Data, unsigned int Length, unsigned char DA);
void InitNewProtoclValuable();
void UART1_SeperateData_NEWCAN2(int Priority, int PF, int PS, int SourceAddress, unsigned char* Data);
void UART1_SeperateData_Default(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_Default_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_Cluster(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_Cluster_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_EHCU(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_EHCU_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_RMCU(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_RMCU_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_ECM(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_ECM_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_TCU(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_TCU_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_CID(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_CID_Multi(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_ACU(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_BKCU(int Priority, int PF, int PS, unsigned char* Data);
void UART1_SeperateData_BKCU_Multi(int Priority, int PF, int PS, unsigned char* Data);
void SaveErrorCode_NEW_CAN2(void);
jint UART1_Tx(JNIEnv *env, jobject this, jint PF, jint PS, jint Flag);
void InitUART1Valuable();
void Thread_Write_UART1(void *data);
void ThreadParsing_UART3(void *data);
void UART1_DataParsing(unsigned char* Data);
void UART3_DataParsing(unsigned char (*pBuff)[UART3_RXPACKET_SIZE]);
void UART3_SeparateData(unsigned char RES_Kind);
void UART1_SaveData(unsigned char BuffSelect);
void SaveErrorCode(void);
void *Thread_Read_UART1(void *data);
void *Thread_Read_UART3(void *data);
speed_t getBaudrate(jint baudrate);
jobject _Open_UART1(JNIEnv *env, jclass this, jstring path, jint baudrate,jint flags);
jobject _Open_UART3(JNIEnv *env, jclass this, jstring path, jint baudrate,jint flags);
void _Close_UART1(JNIEnv *env, jobject this);
void _Close_UART3(JNIEnv *env, jobject this);
jint _Write_UART1(JNIEnv *env, jobject this, jbyteArray arr, jint size);
jint _Write_UART3(JNIEnv *env, jobject this, jbyteArray arr, jint size);
jint _UART1_TxComm(JNIEnv *env, jobject this, jint PS);
jint _UART3_TxComm(JNIEnv *env, jobject this, jint CMD, jint DAT1, jint DAT2,jint DAT3, jint DAT4, jint DAT5, jint DAT6, jint DAT7, jint DAT8);
void SetKeypadLamp();
void KeyButtonCallback(unsigned int KeyData);
void EEPRomTestCallback(unsigned int Data);
void FlashTestCallback(unsigned int Data) ;
void CIDCallback();
void ASCallback();
