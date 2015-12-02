package taeha.wheelloader.fseries_monitor.main;

import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.popup.AngleCalibrationResultPopup;
import taeha.wheelloader.fseries_monitor.popup.AxleTempWarningPopup;
import taeha.wheelloader.fseries_monitor.popup.BrakePedalCalibrationPopup;
import taeha.wheelloader.fseries_monitor.popup.BucketPriorityPopup;
import taeha.wheelloader.fseries_monitor.popup.CCOModePopup;
import taeha.wheelloader.fseries_monitor.popup.CalibrationEHCUPopup;
import taeha.wheelloader.fseries_monitor.popup.EHCUErrorPopup;
import taeha.wheelloader.fseries_monitor.popup.EngineAutoShutdownCountPopup;
import taeha.wheelloader.fseries_monitor.popup.EngineModePopup;
import taeha.wheelloader.fseries_monitor.popup.EngineWarmingUpPopup;
import taeha.wheelloader.fseries_monitor.popup.FanSelectModePopup;
import taeha.wheelloader.fseries_monitor.popup.FuelInitalPopup;
import taeha.wheelloader.fseries_monitor.popup.ICCOModePopup;
import taeha.wheelloader.fseries_monitor.popup.KickDownPopup;
import taeha.wheelloader.fseries_monitor.popup.LanguageChangePopup;
import taeha.wheelloader.fseries_monitor.popup.LoggedFaultDeletePopup;
import taeha.wheelloader.fseries_monitor.popup.MaintReplacePopup;
import taeha.wheelloader.fseries_monitor.popup.MiracastClosePopup;
import taeha.wheelloader.fseries_monitor.popup.MultimediaClosePopup;
import taeha.wheelloader.fseries_monitor.popup.OperationHistoryInitPopup;
import taeha.wheelloader.fseries_monitor.popup.PressureCalibrationResultPopup;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking2;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking2;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking3;
import taeha.wheelloader.fseries_monitor.popup.ShiftModePopup;
import taeha.wheelloader.fseries_monitor.popup.SoftStopInitPopup;
import taeha.wheelloader.fseries_monitor.popup.SoftwareUpdateErrorPopup;
import taeha.wheelloader.fseries_monitor.popup.SoundOutputPopup;
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup;
import taeha.wheelloader.fseries_monitor.popup.TCLockUpPopup;
import taeha.wheelloader.fseries_monitor.popup.WeighingErrorToast;
import taeha.wheelloader.fseries_monitor.popup.WorkLoadInitPopup;
import taeha.wheelloader.fseries_monitor.popup.WorkLoadWeighingInitPopup1;
import taeha.wheelloader.fseries_monitor.popup.WorkLoadWeighingInitPopup2;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class Home extends Activity {
	//CONSTANT//////////////////////////////////////////
	//Version/////////////////////////////////////////////////////////////////////////////
	//
	public static final int VERSION_HIGH 		= 2;
	public static final int VERSION_LOW 		= 2;
	public static final int VERSION_SUB_HIGH 	= 0;
	public static final int VERSION_SUB_LOW 	= 1;
	public static final int VERSION_TAEHA		= 1;
	////1.0.2.3
	// UI B �� ���� ���� 2014.12.10
	////1.0.2.4
	// Eco Gauge Pivot �Լ� �߰�(Progress Bar�� ����� �����̴� ���� ����) 2014.12.11
	// Current Fuel Rate ��ġ ����(NDK ����ü ��ġ ����) 2014.12.11
	// 61184/62�� 8��° ����Ʈ 0���� ������ ���� ����(����ü ���̹���) 2014.12.12
	//// 1.0.2.5
	// Error Report �߰� (��� : mnt/sdcard/alarams/WheelLoader_Logxxx.txt) 2014.12.12
	// 1.0.2.6		UI A �ȿ��� ���� 2014.12.16
	//// 1.0.2.7 2014.12.17
	// Hardware Version ǥ�� �߰� 2014.12.12
	// Error Report ���� �̸� ���� (Year + Month + Date + Hour + Min + Sec) 214.12.16
	// Hardware Revision ǥ�� �߰�(RevD.03.01 6.8K) 2014.12.17
	//// 1.0.2.8 2014.12.18
	// AutoGrease, MirrorHeat, PreHeat Ÿ�̸� ���� 2014.12.17
	// AutoGrease ȭ�鿡�� ��ġ �����ϰ� ���� 2014.12.17
	// Ending Animation �߰� 2014.12.17
	//// 1.0.2.8 2015.01.12
	// Version Info Detail TCU,ECM Index ���� �ʴ� �� ���� 2014.12.19
	// Error Report �ִ� ���� 100���� ����(100�� �ʰ� �� ���� ������ ��¥�� �α� ����) 2014.12.19
	// Service Menu�� focus �� escŰ ���� �ʴ� ���� ���� 2014.12.19
	// Start Can Command ���� 2014.12.22
	// ��й�ȣ �Է� �� backŰ ���� enter ������ ���Ե��� �ʴ� ���� ���� 2014.12.22
	// Engine Auto Shutdown ���� �� �޴� �κ� 65428 -> 61184_122�� ���� 2014.12.26
	// ���� ��� ���� ���� ���� 2014.12.29
	// Firmware Version ���� ���� ���� 2014.12.30
	// Boom Bucket Detent Mode ȭ���� Boom Off/On ���ý� �ݴ�� ������ ���� ���� 2014.12.30
	// Boom Pressure Calibration �Ϸ� �˾� �� List ȭ������ �������� ���� 2014.12.30
	// FAN EPPR V/V Current ���� ���� ���� 2014.12.30
	// �Ҹ�ǰ ���� Hyd. Tank Air Breatehr Filter Cartridge ���� Cartridge �ܾ� ���� 2014.12.30
	// ���� ��ư ���� ���� ���� 2014.12.30
	// ����ȭ���� �������, Hourmeter ������ �ִϸ��̼� �� ��濡 ���� �������� ������ �� ���� 2014.12.30
	// ����ȭ���� ���޴����� Wifi, Bluetooth ���� �� ��Ƽ�̵�� ��ư �̵� 214.12.30
	// ī�޶� ���� �� �ٸ� Ű ��ư �Էµ��� �ʵ��� ���� 2014.12.31
	// Engine Auto Shutdown Countdown �� Cancel ��ư Ŭ�� �� 61184_121(SPN363) Off�� �����ؼ� Tx�ϰ� ���� 2014.12.31
	// Eco Gauge ���� ����, Yellow, Red ���� 2015.01.05
	// Cooling Fan Manual ��ư ���� �� Off�� ���ư��� -> Manual ���� �� ���� ��, Auto�� ���ư��� ������ ���� 2015.01.05
	// Cooling Fan Manual �������� �߰� 2015.01.07
	// Auto Shutdown ��� BKCU�� ��� �ÿ��� Ȱ��ȭ 2015.01.07
	////1.0.2.9 2015.01.15
	// SmartKey ��� ���� ��Ȳ������ ���� 2015.01.14
	// �ð��� ��ȸ�� Manual ���� �� ���� ���·� ���ư����� ����(Auto Setting ����) 2015.01.14
	// Hydraulic Tank Air Breather ���� ���� 2015.01.14
	// Fine Modulation Ű��ư Ŭ�� �� ���� 1ȸ ������ ���� �ȵǴ� ���� ���� 2015.01.14
	// ī�޶� ���� ��� ���� �� Ű ��ư �Է� �ȵǴ� ���� ���� 2015.01.14
	// ī�޶� ���� ��� ���� �� ���� ��� Check �ð� ���� (1000ms -> 400ms) 2015.01.14
	// ECM Version ���� ǥ�� ȭ�� Crash ���� ���� 2015.01.14
	// Latest Odometer Reset �ȵǴ� ���� ���� 2015.01.14
	////1.0.3.0 2015.01.27
	// ���� ��ư ������ ����
	// ī�޶� ȭ�鿡�� Off ��ư ����
	// ����Ŀ ������ 2������ ���� (Off / On)
	// ����ƮŰ Timeout ���� �߰�
	// ī�޶�ȭ�� ���� �� ������ ���� �� ����ȭ������ ������ ���� ����
	// WorkLoad Cancel ��ư ����
	// QuickCoupler �˾� UI �߰�
	// User Switching ��� �߰�
	// BKCU �������� �߰�
	// Battery Voltage ���� ���� ��� - �� ǥ��
	// Soft End Stop Default Popup �߰�
	// ������ ������� ���� ȭ�鿡�� ���� ��ư ������ ���� �� ������ �� �ȵǴ� ���� ����
	// ���� Ű�е� �� ���� ��ġ�Ǵ� ���� ����
	// Main Buzzer Stop �� ��񵿾� Buzzer ������ ���� ����
	// �ð� ���� Ŀ�� �̹��� ���� 
	// AS Phone Number RMCU�� ���� 
	////1.0.3.1 2015.01.29
	// ���� Ű�е� ����
	// ��ư���� ī�޶� ���� �� ȭ�� ��ġ�� ������ ��� ������ �κ� �߰�
	// �ð� ���� 10���� ��� �ð���ġ�� 0 �Է� �� 12�÷� �ٲ�� ���� ����
	// ī�޶� ������� ���� �� Ű��ư �ν��ϴ� ���� ����
	// Sensor Monitoring, Version Info ��ũ�ѿ� �¿�Ű�� ��ũ�� �����ϴ� ��� �߰�
	// Quick Coupler �˾� �� Camera ���ԵǴ� ���� ����
	////1.0.3.2 2015.01.29
	// Quick Coupler �˾� �� Camera ���Եǰ� ����
	// �ð� ���� 10���� ��� �ð���ġ�� 0 �Է� �� 12�÷� �ٲ�� �ٽ� ���� 
	////-----bwk
	////1.0.3.3 2015.02.12
	// weighting system compensation �Է� ��� ���� �� ��Ÿ ���� ����
	// ECM/TCU/EHCU fault code description �߰� 
	// Boom pressure Calibration : HCESPN1908 = 14 �߰� �� UI ����(�۵��� �µ� ǥ�� �� ����� �߰�)
	// F�ø��� ��Ű��� �ڵ�ȭ(������ ���������� Screen Number ����, �׿� 0xff) + Ű������(ST 1.0.1.9), ������� ���������� HomeŰ ����
	// ���� ȭ���� �۾��� ������ ǥ�ú� : ��Ż�纯��, Icon�߰�, ���̴��� �߰�, Enter�� reweigh�ϵ��� ���� ����
	// Ű�е� ���� ��� ���� : < + > + Enter -> �ٱ���
	// EHCU POP UP ���� �ݿ� : Safety CPU Error �˾��� ��� Keyoff���� ������ ����. 0000�� ��� �˾� ����
	// �ٱ��� ���� : �ѱ�� ����, �ٱ��� ������ �߰�
	// speedometer freq. setting : <- Ű ���� ����
	// Work Load : Ű�е� ��ư UI�� �� Default �˾�â �߰�, �վз� �����׸� ������ ���� ȭ������ �̵�(Main�� �ش��������� �ƴ� main���� �̵�)
	// Main->�ð輳��, �����ۼ���,�ٱ���� ������ �̵� �� HomeŰ ���� ��� Crash�ߴ� ���� ����
	// Operation History : ��ġ ���� ����Ʈ ��ü�� �ǵ��� ����
	// Ű�е� ��ư UI : Main Light : 3�ܰ� Position + Head Lamp
	// Ű�е� ��ư UI : Work Light : 3�ܰ� Front + Rear Lamp
	// Ű�е� ��ư UI : Mirror Heat : cancel�� off��
	// Ű�е� ��ư UI : rear wiper : keypad longkey �Է� �� washer ���� intermittant, �� �� ���������� �̵��ǵ��� ����
	// Intro/Outro ���� (Intro : OS 1.0.6)
	// �ð��� ���� : Manual�� Execute ��ư�� ���� ���� ���� ���� (��push and hold to reverse rotation��)
	// Version ���� : EHCU ���� �Ǵ��Ͽ� ǥ��
	// ��Ƽ�̵�� ���� �� rpm 1500 �̻��� ���(3������) Home����, 1500 ������ ��� �ٽ� ��Ƽ�̵���
	// �����̷� : �������, ���Ű��� Ű�е� ����(detail list ����)
	// ���Ű��� - Ʈ�����̼� - Detail Error Ŭ�� �� Crash ����(150212)
	// AEB ��ȯ �� TM �߰� �� �������� ���ư� ��� ���� ǥ���ϴ� �����·� ����(150212)
	// Auto Grease : ���� �� �ڿ� for once �߰�(150212)
	// Weighing ��������(PGN 65450) MCU ���������� ����(150212)
	////1.0.3.4 2015.02.13
	// Engine Auto Shutdown ���� ����
	//  - Left, Right Ű �ּ�/�ִ� ���� ����
	//  - �޴� ���� �ð� ǥ�úκ� �Ҽ��� ����
	//  - MCU�κ��� ���� ���� 2�� ���ϸ� 2��, 40�� �̻��̸� 40������ ǥ��
	// User Switching : �������� ������ Index�� ������, �ٱ��� ǥ��
	// AEB : center�� rpm ǥ�� �߰�
	// Weighing �������� ����� ������ ����(PGN 65450)
	////1.0.3.5
	//1. User Switching : ī�װ� Language ����
	//2. Speedometer Freq. Setting : �������� �����ļ��� ���� �� ��������(Hz -> rpm/km/h)
	//3. ���� ��й�ȣ
	// - ����� ��й�ȣ �Է� �� ��й�ȣ ���� ī��Ʈ �ȵǴ� ���� ����(���Ű���, ������ �޴�)
	// - ���� ��й�ȣ �Է� ���� �� ������ ��й�ȣ���� ���� �ϴµ� ������� ��й�ȣ����� �ߴ� ���� ����
	//4. �����̷� : �������, ���Ű��� Ű�е� ����(detail list ����)
	//5. �ٱ��� ����(�� 21�� ���)
	// - �ƶ��� List �κ� ������ ���ĵǴ� �κ� ��� ���� ���ķ� ����(List, ������ȯ, Workload, Soft End Stop)
	// - �ٱ��� ���� �� �˾��� ������� �ʴ� ���� ����
	// - �޴� ���� �� ������ �Ʒ� ���� ũ�� ����
	// - ȭ�� Ÿ�� ���� / �ٱ��� ���� �������� ���ٱ��� ������ -> ���ٱ���� ����(�ѱ��� ����)
	//   (�ʹ� �� ���ٷ� ǥ�õǾ� ������)
	// - �޴� ����Ʈ���� �ٱ�� �� ��� 2�ٷ� ǥ�õǾ� TextView ������ �ø�
	//   (���� �����ʿ� ǥ�õǴ� Data�� ��ġ�� �ʴ��� Ȯ�� �ʿ�)
	// - ������������ 'EHCU', 'TCU', 'Machine', 'Transmission', 'Engine' �ٱ��� ���� �� ������ ©���� ��찡 �߻��Ͽ� 
	//   "EHCU", "TCU", "Machine", "Transmission", "Engine" ǥ��� ����
	//   (���� ���� Ȥ�� UI ���� �ʿ�)
	// - ���� ���ٷ� ǥ�õǴ� �κ��� ������, ���뿡�� �ٱ��� �������� ��� �� �� ���� ����(ex : �ڵ�)
	//6. Media Player ��Ÿ ����(Palyer�� �Ǿ��־���)
	//7. AM 10:20 -> 0�� ������ 12�÷� ����Ǵ� ���� ����
	////1.0.3.6(15.03.06)
	//1. MIAN-WARMING UP �Ʒ� '(MANUAL)' ���� �߰�
	//2. ����ƮŰ ����� + ����ƮŰ ������ ��� ���������� Service�� �׾ ����ƮŰ ȭ���� ��� ����
	//   -> ����ƮŰ ���� �̹��� �����ִ� �κ� ����, Ÿ�̸� 2�����̴� �� 1���� ���̵��� ��
	//3. ���� �� ��� �˾� ���� ���� �ʴ� ���� ����(�ٱ��� ���� �� �˾� ����)
	//  - ���� �ٱ��� ������ �� �˾����� new �Ͽ��� �κ� ���󺹱�
	//4. ���� �� ��� �˾� : ���� ©���� ��� �߻� -> ����(margin ��)
	//5. ����ȭ�鿡�� CCO, ICCO Ÿ��Ʋ�� �ִϸ��̼� ����(HHI ������ ��û)
	//6. ����ƮŰ ������ + �õ����� �� ��� ���� �� �õ����� ��й�ȣ �ȶߴ� ���� ����(�������� ��� ST�� ������ �κ��� �ּ��Ǿ��־���)
	////1.0.3.6(15.03.07)
	//1. RevD.04.01 Revision ����
	////1.0.4.0
	//1. ȭ��Ÿ�Լ��� �߰�
	//2. B�� ����
	//3. Main : Right Up  
	//	- WarmingUp ����
	//	- Odometer, Hourmeter ��ġ�� Engine Mode��ġ�� �̵� 
	//	- Engine Mode�� ���� Warming Up ��ġ�� �̵�
	//	- Enigne Mode ǥ�� �ÿ��� Engine Icon ǥ��, Fuel, OdoHourmeter ������ �߰�
	//4. Main : Left Down => Average Fuel, Lastest Fuel Consumed �߰�, Hourmenter, Odometer ����
	//5. ���� �۾� �ϴû����� ����
	//6. Quick �޴� Maintenance ������ ����
	//7. �� �޴����� �������� ���ƿ� ��, A������ BŸ������ Ȯ���ϰ� ���ƿ�
	//8. �޴�-��Ƽ�̵��-�̵���÷��̾� 
	//	- ���� �� ���ƿ��� �� ��Ŀ�� ���� ���� ����
	//	- rpm Ȯ�� �� ����
	//9. ����Ű FN ��� ����
	//10. ���� KEY UI���� ���� Ŭ�� �� ���� ��ư ��� �̼������� �Ǵ� ���� ����
	//11. �Ϻ� UI ��ġ �̵�
	//	- Software Update UI ��ġ �̵� : �Ŵ� - �Ŵ�����Ʈ - ��й�ȣ�Է� �� ����
	//	- Mode - Engine Setting - Warming Up ����
	//12. ������ �޴����� '1234567890' ������ H/W Test�� �̵�
	//13. ī�޶� ä�� 1�� ǥ�� -> Ȱ��ȭ ���¿� ���� ä�� ���氡��(Left, Right Ű�� ����)
	// ## by cjg
	// 1. ��������Ʈ USB ���� ��� : LEFT - RIGHT ���ÿ� ���� ��� USB�� ALARM ������ �ڵ� �����!!!
	// 2. �̷��� Ȥ�� MxPlayer�� ������ �� ���� �Ѱ��� ���α׷��� ����Ǿ� ���� ��� ���� ��û �˾� ���
	// 3. �̷��� Ȥ�� MxPlayer �ʿ� �� ��������
	// 4. Mxplayer���� ESC ��ư ������ ��� ��������
	// 5. H/W Test ���α׷�(������ �޴����� Ư�� ���� �Է� �� H/W Test ���α׷� ����)
	// 6. Help, Multimedia���� Ű�е� �ȵ� ��� ���� �߾��� ������ ������ �� �ֵ��� ��
    //	- Help : �߾� ������ �ٷ� ����
    //	- Multimedia : �߾� ������ �޴� ȣ��
	// 7. Mediaplayer���� Ending ������ ���� ���� ����
	// 20150325 HHI
	// 1. menu ��ܿ��� Ű�е�� �޴� ������ ��� ��Ƽ�̵��� ���� �ٷ� �̵�� �÷��̾ ���õ�
	// 2. ��Ƽ�̵�� ���� -> FNŰ -> ī�޶� Ű -> FNŰ -> ESC -> ���� ESC Ű �ȸԴ� ���� ����
	// 3. �Ϻ� UI ��ġ �̵�
    //	- EHCU I/O Information : �Ŵ�����Ʈ - ���� �޴� ���� �̵�
	// 4. ��Ƽ�̵�� -> Ű�� ���� ���̶���Ʈ ����
	// 5. �Ҹ�ǰ ����
	//	- ���� �׸� �ֱⵥ���� ǥ�ÿ� �׸� ���� onǥ��, ��ü ���� onǥ���� �ð� ���̰� ŭ
	//	- �� �׸� �� History ǥ�� UI : ���������� ���̵��� ����� �ʿ�
	//	- MCU - �Ҹ�ǰ ���� ��� : �Ҹ�ǰ �׸� ��� ���� ���� / �Ҹ�ǰ���� ��� ���� ���� ������ ���� Ÿ�̹��� �����ϰ� ���������� ��
	//	- maintenance �˶�
	//	  => key on ����, �ٷ� �޴� �̵� ��, �˶� ���� ǥ�� �ð� �ٸ�
	//	  => History ui ���������� ����
	//	  => ó�� ��ü ��, history �� 0���� ǥ�� �Ǵ� �� Ȯ��
	//	  => �˶� �ֱ� �ð� ��Ȯ�� ���
	// 6. Key on �� ����Ʈ �±� �ν� ����/���� ���� message ó�� (���� graphic ǥ�� ��� ��ü)
	//	- ����ƮŰ �ʱ� �̹��� ����
	//	- ����ȭ�鿡�� ������Ʈ ������ �����ٶ� ���� ����ƮŰ ���� �̹��� ������
	//	- ���нÿ��� �õ����� ��й�ȣ �Է�â�� ������ �������� ����
	// 7. ��Ƽ�̵��/���� ��ġ ���� ���� ����
	//	- ��Ƽ�̵�� ���� -> �߾� ��ġ -> ESC Ű ������ ���� �ȵ�
	//		==> �߾���ġ�� ����� ����
	//	- ��Ƽ�̵�� ���� -> MENU Ű -> LEFt Ű -> ���� ���� -> �߾� ��ġ �� ������� �ʰ� �޴��� ��
	//		==> ���� ���� ��  Multimediaflag false�� ����!
	//// v1.0.4.1
	// 1. ������ -> �����ʿ� ���� ���¿� ���� 2�� ǥ��
	// 2. Fault History -> ����� ȭ�鿡 ��� �ݿ��� ����. ���� �ֱ� �� �����Ϸ�
	// 3. Model �߰� : 965, 975
	// 4. �׽� �µ� ����͸�
	//	- ���� ȭ�� �׽� �µ� ����͸� ǥ�� �߰� (�ɼ� ������ �� ����)
	//	- �޴� �� ��� ����(Machine monitoring) ǥ�� ���(�µ�, ���͸� ���� ��)�� �׽� �µ� �׸� �߰� (�ɼ� ������ �� ����)
	//	- �������� �ű� �߰�
	//	- �׽� ���� ��� �������� �߰�
	//	- ���� �ɺ� ����(���� Front �ɺ��� �ϰ�, Front/Rear ������ ���� F �� R �ɺ��� �׸��� �߰�)
	// 5. Left Down : Current Fuel Rate ǥ�� ����
	// 6. ���� -> �޴����� ��Ŀ���� ��ɼ��� ��� ������
	// 7. KEYPAD �� �Ϻ�(���� ��ġ �Է� ���� ��ư �Է¸����� �۵� ������ ���)�� ���ؼ��� �޴� ���� �߿��� �����ϵ��� ����
	//	- ��� KEY : Main lamp, Work lamp, Beacon lamp, Rear wiper
	//	- ���� ��� : �޴� ���� �� ��� KEY �Է� �� UI ȭ�� ��ȯ ���� �ܼ� CAN Message ��/����
	// 8. �ð� PM 10:34 -> 0�� ������ 12�÷� ����Ǵ� ���� ���� �Ϸ�
	//	- ����� �ð� ���� ȭ�鿡�� Ű�е� ���ڹ�ư ��� ����
	// 9. Rear Wiper ��ư ���� ������ ���°� ����Ǿ�� �ϴ���(���� ��� �����̵� ������ Intermittent�� �����) -> ���� ����
	// 10. Work Load : ���� Boom PS calibraion �׸� ���� ��, ESC ������ Home���� �̵��� ( ���� �׸����� �̵��ǵ��� ����)
	// 11. A/S ��ȣ ���� : 1899-7282
	// 12. ECO ������ �׷��� UI ����, Status�� ������� ����� UI�� ǥ��
	// 13. ����ȭ�� �»�� �۾��� ���� ǥ��
	//	- Ʈ�� A, B, C �ɺ����� ���ĺ��� �� �Ⱥ��δٴ� �ǰ� -> �ɺ� ũ�� �� ���ĺ� �� ���� ����
	// 14. �ٱ��� - ��� ��� Ȯ��(13���� ���)
	//	- �ٱ��� ���� ����
	// 15. SoftEndStop �ʱ� ������ ���� �� Ŀ�� ���� ���� ����
	// 16. User Switching Default ����
	//	- ����� ��ȯ ��� : �⺻�� �� ���� �� save ��ư �̼��� �ǵ��� �� �� (ȸ������ ǥ��)
	// 17. Fuel Consumption History �޴� ����
	// 18. �ð��� EPPR ���� ���� ȭ�� - ���� ������ mA���� %�� ����
	// 19. Ű�е�, �� Ŀ�÷� ��ư
	//	- UNLOCK - FINISH - Ȯ�� - '�˾�â ����' - LOCKING ATTACHMENT ���� - FINISH
	//	- �� ������� ���۽� Ű�е� �۵� �Ұ� ���°� ��
	//	-> �����Ϸ�
	// 20. Fine Modulation Ű�е� ��ư
	//	1) EHCU �ν��Ͽ� EHCU ������� ���� �޽��� ��� : ��EH SYSTEM is NOT equiped.�� : ����θ� ǥ�� �մϴ�.
	//	2) Fine Modulation �ɼ� ������ �� ��� ���� ����
	//		A. ���� : EH SYSTEM is not equipped
	//		B. ���� : This machine does not support this feature.
	// 21. ���� ȭ�� Default ǥ�� ���
	//		A. �� ��� : 9A ���� -> �۵��� �µ� / �ð��� �µ�
	//		B. �� �ϴ� : ��� ����
	//		C. �� ��� : 9A ���� -> Total Hourmeter => �����Ƿ� Latest Hourmeter�� ���� ��û
	// 22. Main Ű�е� �ӽ� ����
	//	- ������
	//	- Fuel Info
	//	- OdoHourmeter
	// ## by cjg
	// 1. Mediaplayer���� Ending ������ ���� ���� ����(Firmware Update �ʿ�)
	// 2. Update���α׷� -> BKCU���� ����
	// 3. H/W Test���α׷����� S/N ����
	// 2015.04.08 HHI
	// 1. Latest Fuel Consumed -> A Days Fuel Used�� ��Ī ����(HHI ��û)
	// 2. EHCU ���� �Ǻ���� ����
	//	- ���� �� : MCU Model = 940, 935
	//		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
	//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935)
	//	- ���� �� : EHCU CID �Ǻ�
	//		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU)
	// 3. Axle Temp �ӽ� ����(HHI ������ ��û)
	// 4. ���� �����ڵ����� ���� ���̴��� �ѱ��� -> ����� ����
	// 5. ����� ��ȯ Default �� -> ���� Default���� �����ϰ� ����
	// 2015.04.09 HHI
	// 1. ���������� : �ڵ� ���� �߰� ��� ���� default ���� 60%�� �����(4)
	// 2. AŸ�� ����ȭ������ ���� �� ���� Ű�е� ��ư�� ��� ��Ÿ���� ���� ����
	// 3. �ѱ��� ���� �߰�(�������, ����, ��ɼ��� ����)
	// 4. Key on �� ���� ��ɵ��� can ���� ���� Not available�� ���� ��� �ɼ� ������ ǥ�� �߰�
	//	- Auto Grease, Wuick coupler, Ride control, Beacon lamp, Mirror heat, Fine Modulation
	//	- Fine Modulation : �˾� -> Ű�е� â���� ǥ�� ��� ����
	//	- LED�� ��� status ���� �켱������ ����, Fine Modulation�� ��Ȯ��(���� ���� ����)
	//	- ���� �ٱ��� �߰�
	// 5. A�� ���ϴ� title ���� 240dp�� ����
	// 6. ����ȭ�� Ŀ�� ù��° ���� ��ư ��ġ���� ���õ� ���� �������� ����
	// 7. �Ϻ� UI ��ġ �̵�
	//	- ī�޶� ���� -> ȯ�漳������ �̵�
	//	- ���� -> ��ɼ��� - ��Ÿ������ �̵�
	// 8. TCU 4SPEED �� �߰�
	//	- 6057018809
	// 9. T.C Lock UP �޴��� EHCU�� ���þ��� ���� ���� ���̹Ƿ� ������� ����(940,935�� ��� ����)
	// 10. ��ɼ��� ����� �̵� ���󺹱�(Ŀ�� �Ҵ� ���׷� ����)
	// 11. SoftEndStop Default �� UserSwitch�� �����ϰ� ����
	// 12. Fuel Info �޴� Ű�е� Enter �� ���� ����
	// 13. UserSwitch ��� ���� ����/�ڵ� ������� �� ����
	// 14. Popup ESC �߰�
	//	- BucketPriority, CCOMode, EngineMode, WarmingUp, ICCO, KickDown,
	//	  MiracastClose, MultimediaClose, ShiftMode, SoundOutput, T.C.Lock Up
	//	  WorkLoadWeighingInitPopup1
	// 15. FuelInfoPopup���� ESC�� ������ ��� �� �������� �ʱ�ȭ ��ư���� Ŀ�� �̵�
	//// v1.0.4.2
	// 1. ���� List �󿡼� Ű�е� �ȵǴ� ���� ����
	// 2. Cooling fan reverse mode Ui ����
	// 3. ���� ���� ���� �˾����� ESC ������ ��� ���Ű��� ���������� Ŀ�� �Ҵ� ���� ����(Crash -> UpdateUI������� ����)
	// 4. Version �������� EHCU ���� �� LEFT/RIGHT Ŀ�� ���� ����
	// 5. MainAKeyWorkLoadDisplayFragment���� �˾� ���� ESC ������ Crash�ߴ� ���� ����
	// 6. WorkLoadWeighingInitPopup1, 2 �˾� ESC ������ ��Ŀ�� �Ҵ� ���� ����
	// 7. QuickCouple, ECHU Error, �����ڵ����� Popup ���� ��� Keypad ȣȯ �Ϸ� 
	// 8. ����ƮŰ ���� ȭ�� B�� �̹��� ���(HHI ��û)
	// 9. �̶�ĳ��Ʈ ��Ű���� ����
	// 10. ����, ��������, ���� ���� ��Ŀ�� �ʱ�ȭ�Ǵ� ���� ����
	// 11. ���Ű������ �˾� : ���� /n �߰�
	// 12. ������� - �����ڸ޴� - ��й�ȣ �Է�â : ��Ű�� ��й�ȣ ���� ��� ����
	// 13. ������� - �����ڸ޴� - ����Ʈ���� ������Ʈ : ��Ű ��й�ȣ ���� ��� �߰� - CAN ���� ������Ʈ ����
	// 14. �޴� ���� �� �� ����Ʈ ��Ƽ��ġ ����
	// 15. Fine Modulation �����Ͽ� EHCU CID �̼��� �� LED Off
	// 16. �̵�� �÷��̾� : RPM �����Ͽ� RPM ��½� ��׶���� ����(flag ���� ���� �� ���� �� LED ǥ��)
	// 17. HW Test ���α׷� CAN ���� �����ϰ� ���� �� ��й�ȣ ����(0314451227)
	// 18. ���� ���� ǥ��ȭ�� ����
	//	- �������� -> ������� �̸� ����(�ѱ���)
	//	- ECM ǥ�ó��� : ������, ECM Identifier(������ �븮)->Calibration_Version_Number(�����ؾ�)
	//	- TCM ǥ�� ���� : ������, HW Serial Number(������ �븮)->Software Version(�����ؾ�)
	//	- �׿� �׸� ǥ�ó��� : ���α׷� ����, �ø��� ��ȣ(RMCU ���ʿ��� ������ ��� ����)
	// 19. User switching �⺻�� ����(HHI 150421 ��û)
	//	- ENIGNE MODE : STANDARD -> POWER
	//	- TC LOCK UP : OFF -> ON
	//	- ������� : HYD/COOLANT -> COOLANT/BATTERY
	// 20. �ʱ� ��Ƽ��Ŷ ���� �� 10�ʰ� CID �۽�
	// 21. ���� - Fuel Init(�� ��ġ) -> �˾����� ���·� ����
	////v1.0.4.3
	// 1. ����Ű FN, Fine Modulation �̹��� ����
	// 2. EHCU POUUP 0xFFFF�� ��� ����
	// 3. ����Ű Not available�� ��� ȸ�� ���� ó��(Auto grease, Quick coupler, Ride control, Beacon lamp, Mirror heat)
	// 4. ������� ������ �׸� -> Hidden Page���� ������
	////v1.0.4.4
	// 1. User Switching / Default -> Display Type : A -> B
	// 2. User Switching / Default -> CCO MODE : OFF -> H
	// 3. CID ���� ��� �ʱ� SendCommandTimer �������� ���� ���� ����
	// 4. ���� �� Main �ִϸ��̼� �� Ű �������� ���� ��� Crash�Ͼ�� ���� ����(setScreenIndex �ּ�ó��)
	// 5. ������ �̵� �� ���̿���ư Ŭ������ ������ �������� ��� �ʱ� �ִϸ��̼� �� ������ �ٸ� �׸� ���� ��� ���� ���� Ȥ�� ���������� ������ ���ϴ� ���� �߻� 
	//	-> �ִϸ��̼��� �Ϸ�Ǹ� ȭ���� Ȱ��ȭ
	//		- �ٱ��� ����
	//		- ����(Fuel Select, ODO/HOURMETER Select, Engine Mode, CCO/ICCO Mode, Shift Mode, T.C.Lock Up)
	// 6. �ٱ��� - �ѱ���, ���� ������ ����
	// 7. �ٱ��� - �ѱ��� ���뿡 ���� ���� - �վз� ���� ������ ����� ����� UI �迭 ����
	// 8. MAIN-KEY-WORKLOAD -> �վзº��� ��ư ���� �ʱ�ȭ ��ư �߰�
	// 9. ������� - �����ڸ޴� - �۾��� ���� : CAN ���� ��� ESC �ȸԴ� ���� ����
	// 10. �� ���� -> Ȩȭ�� �� ��� ������ HOME ���������� ����
	// 11. 980 ��� Main -> ICCO Mode Change �϶� ���� ����
	// 12. �ӵ��輳�� : �ʱ� ���� 0xffff�� ��� 0���� ǥ��
	// 13. ���� - ������ - �۾����� ��� LongŰ�� �ʱ�ȭ �˾� �߰�
	//	- ������ ��/�Ʒ� ��ư Ȱ��ȭ �̹��� ����
	// 14. ���� Ű�е� ����(Engine Mode, CCO Mode, ICCO Mode, Shift Mode, TC Lock Up)
	// 15. RMCU���� MCU Error Description �߰�
	////v1.0.4.5(20150507)
	// 1. �� ScreenIndex �ȸ´� ���� ����
	// 2. ���� Ű�е� ����
	// 3. Ȩ���� ����� ��ȯ, ��������, �Ҹ�ǰ ���� ESC ������ �� ������ ���ư��� ���� 
	// 4. ECO Gauge ��纯��(White�� ���� �� ���� ���� ������ �̹��� �ٸ��� Display)
	// 5. TC LOCK UP �������� TYPE B ���� ȭ�� ����
	// 6. Preference - Sound Output Setting - External AUX �Ʒ� ��� ���� �߰�
	// 7. Preference - DisplayStyle/Language - DisplayStyle : �ִϸ��̼� �� Display ����
	// 8. Display type B���� rpm�κ� Ŭ�� �� rpm ���� �����̴� ����(������) - ����(Touch OFF)
	// 9. �ð��� ���� ���� ������ ��� �����ϰ� ����
	// 10. ���� Ű Ű�е� ����(A/B��)
	// 11. ICCO Mode�� ��� 'H'�� �ƴ� 'ON'���� ǥ��
	// 12. Weight ���� 2�� ���� ��� ���̴��� ������ Crash�ߴ� ���� ����
	// 13. �̵�� �÷��̾�� ��� ��ġ�Ͽ� �����Ͽ��� ��� FN LED ������ �ʴ� ���� ����
	// 14. Quick Coupler LED ������ �ʴ� ���� Ȯ�� �� �������� �߰�(2�� ���� ����� ������ -> MCU LED OFF ������� Ȯ�� ��)
	// 15. ��Ƽ�̵�� ����Ʈ�� ����(����Ʈ �͹̳� ���� �߰�)
	// 16. ����Ʈ �͹̳� ON/OFF�� ���� FN LED ���� �߰�
	// 17. AUX ����� ������ �۰� ����
	////v1.0.4.5(20150508)
	// 1. RevD.05.01 ����
	////v1.0.4.6(20150511)
	// 1. JB ������Ʈ�� UI �������� ����
	// 2. JB ������Ʈ�� Ű�е� �Ҹ��ȳ��� ���� ����
	// 3. RevD.05.01 -> RevF.01.01�� ����
	////v1.0.4.7
	// EHCU Error Popup �����
	// HCEPGN 65524 (4~5) : 0x0000 - Joystick Steering Enable OK -> 0x0000 - Not Available
	// HCEPGN 65517 (2.5) : OK -> �˾� ���� �� �˾� ����� ����
	////v1.0.4.8
	// 1. Display Type B : Center �ƹ� ���̳� ������ Ŭ���� ��� Crash�ߴ� ���� ����
	// 2. Mediaplay rpm����  �������(HHI��û)
	// 3. Ÿ apk���� �޴�/ESC/���� Ű ���� ��� �Ҹ� �ι��鸮�� ���� ����
	// 4. ��Ƽ�̵�� UI ���� : �̵�� �÷��̾�, ����Ʈ �͹̳� �ΰ� ��� �̹��� ���������� ǥ��
	// 5. ���� ��Ű ����(���� ������Ű�� �κ� ���� �־ ��Ű�϶� ���º��ϰ� �����ϴ� ������ �� -> �����ʿ�)
	// 6. ��Ȱ��ȭ �� Ű�� ��Ⱑ �ʹ� ��ο� �� ���� (�ƹ��͵� ���� �� ���� ������ �ش� ��ư ȭ���� �ߴ� ����)
	// 7. Axle ���� ��� ����(svn����)
	////v.2.0.0.0
	// 1.��/���� �������� ���
	//	- Step 1 : �׸� ���� (bucket full in ���·�)
	//  - Step 4, 5 : �� ���� ���� ǥ�� step 3 ���ķ� ����
	//	- Boom ���� ���ٷ� ǥ��
	// 2. ��Ű ���� ��� Ű�е� ���� ���
	//	A. ��Ű ���� ��� :�ð��� ���� ����, Detent - Save position, Rear Wiper ? Washer
	//	B. Ű�е� ����
	//		- ���� 1ȸ �Է� : (Ŀ�� ������鼭) ��ġ ����
	//		- ���� 1ȸ �߰� �Է� �Ǵ� ESC �Է� �Ǵ� ȭ�� ��ġ �Է� : (Ŀ�� ��Ÿ���鼭) ��ġ ��
	//		- ��ġ ���۰� �� ���̿��� �ٸ� Ű�е� ����(ex. Ŀ���̵�) �ȵǵ���
	// 3. UserSwitching
	//	- Boom Detent Mode �߰�
	//	- Bucket Detent Mode �߰�
	//	- Display Type ����ȵǴ� �κ� ����
	//	- Fuel ����  Drak gray ���� ����
	// 4. �޴����� CAM ��ư, ������� ���� : home ��ư + cam ȭ�� ǥ�� 
	// 5. �̵�� �÷��̾� / ����Ʈ �͹̳� / PDF ����
	//	- CAM ��ư, ������� ���� : ȭ����ȯ ��ư + cam ȭ�� ǥ�� (���� : ���� ȭ��)
	//	- ������/�Ĺ������ ��ư : ��׶��忡�� �������ݸ� �ۺ��� ��. (�޴� ȭ�鿡���� ���� ����)
	// 6. ���� ��Ű ���� ����
	//	- ST �����ʿ�(1.0.2.3)
	// 7. auxilliary -> auxiliary ��Ÿ���� 
	// 8. ��Ƽ�̵�� UI ����(�̵���÷��̾� ȸ���̹��� �׵θ� �Ͼ�� ����)
	// 9. ���� - LeftUp
	//	- Weight ���� �ΰ� ���� ��� ������ ����� Ŭ���� �����θ� ���̴��� ǥ�÷� ����
	//	- UI Ton ��ǥ ����
	// 10. Ŭ������ H/W Version ǥ�� �߰�
	// 11. F.01.01 ���װ� ���� ���� : 708~728 -> 688~738
	//// v2.0.0.1
	// 1. Maintance : draulic Tank Air Breather Filter���� �ֱ⺯�� ������ Crash �ߴ� ����
	//	- �ٱ��� �����ϸ鼭 ������ �����Ǿ� �߻� -> ����� ������ ǥ��
	// 2. Main - Detent : ��ġ���� �ȵǴ� ���� �ذ�
	// 3. CCO, Display Mode : �ʱⰪ�� UserSwitch Default �� ����
	// 4. Main A Quick���� �Ҹ�ǰ����/UserSwitch/������� ������ �̵��Ͽ��ٰ� Back�Ͽ��� ��� ����Ű�е� �ȵǴ� ���� �ذ�
	// 5. UserSwitching
	//	- �ʱ� Ŀ�� ��ġ�� ������ ������ User���� �⺻�� Ȥ�� ����� �������ȯ ���� ǥ��
	//	- ���� ����ȵǴ� ���� ����
	//// v2.0.0.2
	// 1. MultiTouch ����
	// 2. rpm gauge ����
	// 3. ������� ǥ�ñ�� - BKCU ���� �׸� CID �ν� ���ο� ���� ����
	// 4. Quick coupler ���
	//	- unlock �� lock �ÿ� �����Ҹ� �̻� ���� 
	//	- ��ſ��� �����Ͽ� Ÿ�̸� ������ ���� ����
	// 5. �ٱ��� ���� - �ѱ��� ���� �� KEY OFF �� ����� �ٲ�(Ÿ �� ���� �������� ����) -> ����
	// 6. CID S/N ���� ��� '-'�� ǥ��
	//// v2.0.0.3
	// 1. SmartKey SA ����(MCU�� CID�� ���ĺ���)
	// 2. ���� Ű ����
	//	- ���ܱ��-�������-�� ���ȭ�� / 8+0 / Hidden ���� ǥ��
	//	- ��Ƽ�̵�� / 8+0 / File manager
	//	- ��Ƽ�̵�� / 8+9+0 / �ý��� ���� ����
	// 3. ��/���� ��������, �� �зº��� ��� - EHCU CID �ν��Ͽ� EHCU ���� ��񿡼��� ���� ȭ�鿡 �߰� ���� or �˾� ǥ�� 
	//	- "You should turn OFF Soft end stop before start calibration."
	// 4. Main lamp�� ���� Ŭ�� �� �˾� ���� �� crash �ذ�
	//// v2.0.0.4
	// 1. Axle Popup 3�� -> 5�ʷ� ����
	// 2. DTC List (HL9XX �ø���, 15�� 5�� 22�� ����) ����
	// 3. TCU 4SPEED �� �߰�
	//	- 6057018709 
	// 4. �Ĺ�ī�޶� ���� �� ��������� ��� ���� �︮�� �ʴ� ���� ����
	//	- ��Ŀ�÷��� �浹�� ����
	//// v2.0.0.5 15.06.12
	// 1. PDF���� ESCŰ ������ ��� �Ҹ��ȳ��� ���� ����
	// 2. �˾��� �� ä�� ī�޶� ���� ���� �˾��� �����ų� Ŭ���Ǵ� ���� ����
	//	- ParentPopup���� ī�޶��� ���¸� ���� ����
	// 3. �Ĺ�ī�޶��� ��� ȭ�� ��ġ�ϸ� Ű/��ġ �Ҵ����� ����
	//	- Home���� imgViewCameraScreen�� Click �̺�Ʈ���� Touch �̺�Ʈ�� ����
	//	- Ÿ apk ���� �� ī�޶� ��ư ����
	// 4. �޴� - ��Ƽ�̵�� - �̵�� �÷��̾� ���� �� ������ ����Ʈ�͹̳��� Off�ϴ� �κ� ����
	// 5. ��Ƽ�̵�� ���� �� ����Ʈ �͹̳� ���� �� LED Off �Ǵ� ���� ����
	// 6. ����Ʈ �͹̳� ���� �� FNŰ ������ LED ������ �ʴ� ���� ����
	// 7. H/W Revision ���� F.01.01 738 �ν� ���� �߻� -> max�� 740���� ����
	// 8. MainKeyRearWiper ClickRight,ClickLeft �� textview ���� ��� NullPoint���� ����
	//// v2.0.0.5 15.06.15
	// 1. [ī�޶� ���� ���]�� ù��° ī�޶� �������� ���� ǥ��
	// 2. ����Ŭ��ġ ��� ����/���� ���Ʒ� ��ġ �ٲ� (ICCO ��� ��ɰ� ������ ��) : ����ȭ��, �޴� �� ����
	// 3. ���� �ڵ����� ��� �˾� �߰� ��� ��ư ������ ���� Ű�е� �Է� �� CRASH ����
	// 4. �µ� ���� 0xff�̻��� ��� '-'�� ǥ��
	// 5. A�� Finemodulation ȭ�� Touch �� crash ����
	// 6. Sync ����
	// 7. KeyButton Filter ����
	//// v2.0.0.5 15.06.16
	// 1. �����ڵ����� 60�� ���ϵ����� ������ 60�� �̻� ������ �� ��� �˾��� �ȶߴ� ���� �ذ�
	//// v2.0.0.a 15.06.22(Axle Test) : svn ����
	//// v2.0.0.6 15.06.23(Axle Test) : svn ����
	//// v2.0.0.51 
	// 1. Main B ���̵� ��Ʈ�� UI �̹��� ���� ���� ����
	// 2. Software Update ������� �غ���(�õ� ���� ��� Software Update�� ���� �ʰ� ��� �˾��� ���(Key on�ÿ��� ������Ʈ �ǵ���))
	// 3. �ð� -> RTC ���� 24�̻��� ��� 12�� ����
	// 4. Icon ���� 
	// 5. Hidden version �߰�(����� �������� MENU+ESC+LEFT+RIGHT+ENTER)
	// 6. Sync ���� ����(native_system_updates_native �߰�)
	// 5. 2.0.0.a + 2.0.0.6�� �����
	//	- main ��� ���� ���� ������ UI ����(�ѱ��� ����ǥ�� ����)
	//	- main ������ �����ִ� �κ� Axle ���� �˾� ǥ�� ���� -> Home���� �̵�����(�켱������ ��Ŀ�÷��� ����)
	//	- Main CurserDisplay Null Point ���� try catch�� ����(ǰ�� ������ ����)
	//	- Axle �˾��� ���� ��� Ű �ϳ��� ���۾���(��ġ�� ������ �͸� ����)
	//	- Axle, Quick coupler �˾� OFF �� ������ Ű Ȥ�� ��ġ���� ��� ScreenIndex�� ���̴� ���� ����
	//	- ī�޶��ư ������ ������ �޴� ������ ScreenIndex �ٲ�� ���� ����(ǰ�� ������ ����)
	//	- GetVersionString Hex�� ǥ��
	//// v2.0.0.52 
	// 1. SmartKey ��Ŷ ���� ��� Timeout Timer ����
	// 2. F/W Hidden ���� �߰�
	// 3. EHCU ���Ű��� ǥ�� ���� ����
	// 4. RMCU �ɼ� ���ۿ� ���� ǥ�� �׸� ����->���� ����
	// 5. �õ����� ������Ʈ ���� -> ���� ����
	//// v2.0.0.61
	// 1. �õ����� ������Ʈ ����
	// 2. RMCU �ɼ� ���ۿ� ���� ǥ�� �׸� ����
	// 3. EHCU ���� ǥ�ÿ� �� ���� ǥ�� �߰�
	// 4. �������/���Ű��� ǥ�� ����(SPN 0 / FMI 0 -> �������� ǥ��)
	// 5. �� �з� ���� 1�� �� �ۼ�Ʈ ������ 0 ������ �̵�(�ʱ�ȭ ���� �ʰ� 100%�� �Ǿ� �־���)
	// 6. Axle ����
	// 7. SMK ����  �������� �߰�(���� �׽�Ʈ �ʿ�) 
	// 8. ��ưŰ �Է� ���� �ð�(10��) �� ����ȭ������ ����
	// 9. �ٱ��� ���� �� �˾� �߰� (�ý��� ������ KEY OFF/ON�϶�� ����)
	// 10. �������� 3��° �̹��� ����(�Ʒ��� ���ϵ���)
	//// v2.0.0.7
	// 1. �ٱ��� ����� popup ���� HHI ��� ����
	// 2. Axle ���� ���� �����°� ����Ǿ��� �� �۾����� ��� �Ʒ� ��ǥ��� ����
	// 3. Axle ��� popup ���� /n -> \n ��Ÿ ����
	// 4. ��ȸ�� popup ���� �� ��ȸ�� ���� ��Ŷ ����
	// 5. ����ƮŰ ��� ���� �� �±� ���� ���� �� �ְ� ����
	// 6. ��ưŰ �Է� ���� �ð�(10��) �� ����ȭ������ ���� ��� ����� ������ �Է°� ��� 10�� �� ����
	//	- KEY : AutoGrease, MirrorHeat
	//	- Main : FuelSelect, MachineStatusSelect, TMCCOMode/ICCOMode/ShiftMode/TCLockUp, EngineMode, HourOdometerSelect
	// 7. 10�� ���� ���� �� ������� ������ ��ư ���� ��쿡�� ���͵Ǵ� ���� ����
	// 8. ��ȸ�� ���� ���� ����
	//	- [����] Ŀ�� ���� �� ENTER Ű �Է� -> �̶� ��ȸ�� ��ġ�� ����Ű �۵� �ȵ� -> CurserIndex �߰�
	//	- [����] Ŀ�� ���� �� ENTER Ű �Է� -> �̶� ESC Ű �Է� �� ���� ��ȸ�� ������ �ʰ� ��� ���� -> �˾����� ��� OFF �������� �ۺ�
	//// v2.0.0.8
	// 1. �ð��� UI ���� �� ���� ����
	//	- �ڵ��� ��� ok��ư ���� ��쿡�� �������� ����
	//	- ������ ��� ������ư �������� ��� �ڵ� OFF �������� ����
	//	- �� ���������� ���õ� �Ϳ� ���� UI ǥ��(�˾������)
	// 2. �� �������� �̹��� 1,2,3�ܰ� F�ø��� ���ϸ������ ����
	// 3. Fan EPPR Current Adjust �þ� ����
	//	- �ð��� �ڵ�/���� ���� �߰�
	//	- �ð��� ���� Excute �߰�
	//	- MaxFanControl �������� �� UI �߰�
	//	- SelectMode�� ���� �ش� ������ ����(�˾��߰�)
	// 4. ī�޶� Left/Right ���� ����
	//	- �������� 3ä�η� �س��� ī�޶� OFF ���� ��� �ʱ� 1 -> Right Ű�� ������ CH4�� ����Ǵ� ���� ����(�׻� ù��° ī�޶� ������)
	// 5. Excute -> Exectue�� ��Ÿ ����
	// 6. ��Ƽ�̵��(MediaPlayer, ����Ʈ �͹̳�) ���� ��� ����
	// 7. '�嵥��' -> '���͸�' ǥ�ؾ� ���� 
	// 8. Wiper ��纯��
	//	- ����ġ�� ���� ǥ�õȴ� ���� �ƴ϶� Level �� ���� ǥ��
	//	- ������ ���� �޴��� ������ �����ϰ� ����
	// 9. ����Ʈ �͹̳� ��� ���� ��ġ�� ���� ��� �߰�
	//// v2.0.0.81
	// 1. PDF Viewr ESC ���� ��� ����
	// 2. popup dismiss �� �� Homedialog = null�� ����
	// 3. ���Ű����� ��� detail view�� �������� �� �����ϴ� ��� detail view�� ��� �����ִ� ���� ����
	// 4. �������/���Ű��� UI ����
	//// v2.0.0.82
	// 1. Revision ���� 3.9K ����
	// 2. cluster hidden version �߰�
	// 3. FN Ű�� ��Ƽ�̵�� ������ ��� ��� ��ġ�� ������� �ʴ� ���� ����
	// 4. PDF, ��Ƽ�̵��, ����Ʈ�͹̳� �� �ٸ� ���� ESC�� ������� �ʴ� ���� ����(ȯ�漳��, File Viewer ��)
	//// v2.0.0.90
	// 1. �� ���� ���� �̹��� ����(��ġ�� �������� ���� ������ �̵�)
	// 2. ��� �������� ǥ�õǴ� ���α׷� ������ 3�ڸ������� ǥ��, ��ȭ��(hidden)������ ��ü�ڸ� �� ���
	// 3. �н����� �Է�â ǥ�� * �Ǵ� ���� �� �� 1 �� �� �ֵ��� ����
	// 4. Soft End Stop �⺻ �� ���� 
	//	- Bucket In OFF �� �� ��� ON
	// 5. WeighingErrorDetect �ʱⰪ ON���� ����
	// 6. RMCU ȣ��� �Է� ���
	// 7. �õ����� �ɷ��� ��� Ȥ�� ����ƮŰ ���� ������ ��� Ű�е� ȣȯ(������ Ű�е� �ȵ�)
	// 8. ������ �޴� ��й�ȣ���� LEFT+RIGHT ���� ��� H/W Test ����
	// 9. UI �浹/FW ������Ʈ/OS ������Ʈ ���� ���� ���� �ҽ� ���� ����
	//	- MONITOR CID ���� �� CID ������ ���� ��� 1ȸ ��û
	// 10. pdf reader, media player, ����Ʈ �͹̳� �� ��ɿ��� Axle ���(pop up �Ǵ� lamp)�� �߻��� �� �ٷ� ���� �ǰ� ����ȭ������ ��ȯ
	//// v2.0.0.91
	// 1. HCEPGN 65330 CID : ��� ������ �������� ǥ�� �׸�(FATC) �߰� 
	// 2. HCEPGN 65373 Air Conditioner Status : ���� �̷� - ���� ���忡 ǥ���׸�(FATC) �߰�
	//  - ���� ���� : MCU���� ���� �� ���� ����
	// 3. HCEPGN 65373 Air Conditioner Status : ������ �޴� - ��� ����͸��� ǥ�� �׸�(FATC Setting Temperature (Celsius)) �߰�
	// 4. HCEPGN 65519 Ambient Conditions : ������ �޴� - ��� ����͸��� ǥ�� �׸�(Ambient Temperature, In-cab Temperature) �߰�
	//// v2.0.0.83(1���׸񶧹��� v2.0.0.82���� �ӽ� ����)
	// 1. TextViewXAxisFlipAnimation ������ ������ ��� ���� ���� �ӽ� ����
	// 2. H/W Test LEFT+RIGHT Ű�� �̵�
	// 3. H/W Test �� �� �� ������ �ʴ� ���� ����
	// 4. WeighingErrorDetect �ʱⰪ ON���� ����
	// 5. RevF.04.01 ����
	//// v2.0.0.92
	// 1. TextViewXAxisFlipAnimation ������ ������ ��� ���� ���� ����
	// 2. RPM ������ �ִϸ��̼� (�ٴ� ������)
	//	- ���� �� : ���� ���ý� + Ÿ ȭ�鿡�� ����ȭ�� ���� ��
	//	- ���� �� : ���� ���ý�
	// 3. RevF.04.01 ����
	// 4. MenuList textView Title, Data clearAnimation �߰�
	// 5. TCU, ECM�� Model �� Manufacture Date�� ����!! Hidden���������� ����(�������Ȯ�ο��)
	// 6. ������� ��ĭǥ�ô� ��� '-'�� ����.
	// 7. Loading �̹��� �߰�(���������� �Ⱥ��ϵ�)
	// 8. �̶�ĳ��Ʈ ���� �� ���� �� WiFi OFF
	// 9. Boom -0.1~-0.9 ǥ�� �ȵǴ� ���� ����
	// 10. BKCU Component �߰� 
	//// v2.0.0.93
	// 1. ������ ���� ���� ǥ�� ��� ����
	// 2. RMCU ȣ��� �Է� ����
	// 3. HOUR METER ǥ�� ���� ����
	// 4. UserSwitching ��ɿ��� �ɼ� ������ �׸��� ǥ�� ����
	// 5. Popup �켱���� ����
	//	- �켱 ���� ���� 
	//		a. ���� �ڵ�����, Locking, UnLocking : �۵� �� �ٸ� �˾� �߻� x
	//		b. Q coupler ���� �� �ڵ����� �߻� �� �ڵ����� ī��Ʈ �˾� �켱
	//	- �켱 ���� �߰�
	//		a. Engine SCR Temp High, Axle Temp, Joystick EHCU > Quickcoupler Unlock Alarm
	//		b. �ð��� ���� ���� �ֱٿ� �߻��� �˾� �켱 ǥ��
	// 6. ��Ÿ ���� ����
	//	- �ٸ� �˾��� ���� OldScreenIndex�� �������� �Ǿ��� ��� ���� �޴��� �̵����ϴ� ����
	//		a. Mode-ETC-Calibration-�վзº���
	//		b. Preference-Clock Setting
	//		c. Monitoring-Fault History-Active Fault
	//		d. Management-Maintenance
	// 7. B�� �ִϸ��̼� ���� ����
	////v2.1.0.00
	// 1. EHCU error ǥ��
	//		A.     ��Please reset power�� �˾��� ���� �ڵ����� countdown �˾� ���� �߻� �� ó����� ����
	//	  		- �ڵ����� �˾��� �켱 ó���ϰ�, EHCU error �˾� ���� �÷��� ����.(��Please reset power�� �˾��� �ٽ� ��Ÿ������)
	//		B.     �� �� Joystick Steering ���� �˾� �ÿ��� ���������� �ڵ����� �˾��� �켱 ó��.
	// 2. ���ǿ� App(PDF Viewer, Media Player, Smart Terminal) ���� �� ��� ǥ��
	//		A.     ���� ��� ǥ�� �߻� �� ����� ����ȭ������ ������ �� (�������̴� App�� ��׶��� ����, �� PDF Viewer�� ���� ��)
	//			i.     ���� �ڵ����� countdown �˾�
	//			ii.     Axle Temperature Warning �˾�
	//			iii.     EHCU error �˾�
	//			iv.     Buzzer �߻� (MCU �Ǵ� ����� 070 ���� �߻� ��)
	// 3. �ѱ��� Ű�е� ����(Ű�е� ���ø����̼� ��ġ�ؾ� ��)
	////v2.1.0.01
	// 1. H/W Test ���α׷� ���� 070 �����߹Ƿ� UI�� �����ϴ� ���� �ذ�
	// 2. Update ���α׷����� 10���̳� App ��ġ�� 070 �����߹Ƿ� UI�� �����ϴ� ���� �ذ�
	// 3. SoftEndStop BucketOut OFF -> ON(�޴����� Default�� ��)
	////v2.1.0.10
	// 1. ���� ȣ��� �Է� �� ����������� Ȯ���� �� �ֵ��� �ּ�ó���س���!
	// 2. ����� �������� ��� ����(METRIC, US, CUSTOM)
	// 3. �������� ���� �߰�(l/h, gal/h)
	// 4. ���Լ��� ���� US ton �߰�
	// 5. �ٱ��� ��Ī ����(��Ȯ�� ��Ī�� �ٱ��� ���������� Ȯ��!!!! �Ʒ��� ����� ǥ��)
	//	- Porutukaleo -> Portugues
	//	- Swedish -> Svenska
	//	- Slovakian -> Slovensky
	//	- Estonian -> Eesti
	////v2.1.0.11(��������)
	// 1. A���� ��� Axle �� Quick->Home���� �� �� Keypad �������� �ʴ� ���� ����
	// 2. ESL �����ð� �� ���� Enter �Է� �� Ŀ�� �ȿ����̴� ���� ����(Ȯ���ʿ�)
	////v2.1.0.20
	// 1. T.C Lock Up ��� ����(�������� ������ �ʰ�, TCU �����θ� �Ǻ�(0x20, ���ڸ� ��ȿ�����ͷ� ��). Default���� 5Speed)
	// 2. ESL �����ð� �� ���� Enter �Է� �� Ŀ�� �ȿ����̴� ���� ����(Ȯ�οϷ�)
	// 3. MCU Model Num Check ��� ����(HL980, HL980XT, HL980TM ��� ������ HL980���� ó��)
	// 4. MCU Model Option Check ��� ����(5���� ���ĸ� ��� �ɼ����� ��)
	// 5. ������͸� Hidden ������ ListVeiw Ŭ�� �� crash ���� ���� ����
	// 6. ����� �����������, ���Լ��� ���� USton, ���� ���� �����߰� �����Ͽ� ����������� ����
	// 7. Fuel �˾� �ʱ�ȭ �� �˾��̿� Ŭ�� �� crash �ߴ� ���� �� �ʱ�ȭ�� OK ��ư ���� Ŭ�� �� UI ���� �ٲ�� ���� ����
	// 8. UserSwitching SoftEndStop TM �ɼ� ���� 
	// 9. Workload Init �˾� ��������, ī�޶��ư �ǵ��� ����
	////v2.1.0.21
	// 1. Gauge Animation ���� : TimerTask ������� �ʴ� ���� ����
	// 2. SoftEndStop Default ���� �� TM �ɼ� ������� ��� ������ �����ϴ� ���� ����
	// 3. UserSwitching���� �ɼ����� �����Ͽ� CAN ������ ����(�ɼǿ� ���� ���������� CAN ���������۵Ǿ���)
	////v2.1.0.30
	// 1. ����� �����������, ���Լ��� ���� USton, ���� ���� �����߰�
	// 2. ���� ON�� �� ���� ����� Fault code �߻� ���� ��ġ �� ���� OFF��� �߰�
	// 3. UserSwitching Fuel �߰�
	//----���� �� ����
	// 4. US ton ���� ȯ�� ����(1.012311 -> 1.1.02311)
	// 5. Userswitching���� ��� ���� �� �˾��� ������� �ʴ� ���� ����
	// 6. Userswitching�� ���� �� ��� Axle ���� �� ��ǥ��Ǵ� ���� ����
	////v2.1.0.40
	// 1. ACU test��
	// 2. ������ �޴� -> left + right ������ ��й�ȣ ������
	// 3. ������忡 acu �׻� ǥ��
	////v2.2.0.0
	// 1. �ٱ��� ������� ����
	// 2. ��/���� �������� Next ���� ȭ��ǥ ����(HHI ���� �Ϸ�)
	////v2.2.0.1
	// 1. Display B�� Main US ton �� gal ���� ������� ����
	// 2. UserSwitching ����
	//	- Display Type : B�� -> A������ ����
	//	- ���� -> ���Ƿ� ��Ī ����
	//	- ���� ���� l/h -> l�� ����
	//	- �ӵ� -> �Ÿ��� ��Ī ����
	// 3. ������� EHCU : MCU�κ��� �޵��� ����
	// 4. EHCU ���� ������ 3���� ��� �����ϴ� �κ� �������� ����
	// 5. Smart Terminal 1.05 ������ �ƴҰ��� ����(���Ŀ� �� ������Ʈ�� �� �����Ƿ�)
	// 6. ���� �Һ� ���� �׷������� gal�� ����Ǿ��� �� ���� �ȸ´� �κ� ����
	////v2.2.0.11
	// 1. H/W Test CID ���� �������� ���� ����
	//////////////////////////////////////////////////////////////////////////////////////
	// TAG
	private  final String TAG = "Home";
	
	public  static final int SCREEN_STATE_FILTER 											= 0xF0000000;
	public	static final int SCREEN_STATE_MAIN_FILTER 										= 0x0F000000;
	public	static final int SCREEN_STATE_MAIN_KEY_FILTER 									= 0x00F00000;
	
	public  static final int SCREEN_STATE_MAIN_B_TOP 										= 0x10000000;
	
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_TOP								= 0x11000000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_MODE						= 0x11100000;
	// ++, 150316 bwk
	//public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_WARMINGUP					= 0x11200000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_HOURODMETER						= 0x11200000;
	// --, 150316 bwk
	public  static final int SCREEN_STATE_MAIN_B_RIGHTUP_END								= 0x11FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_TOP								= 0x12000000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_CCOMODE							= 0x12100000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_SHIFTMODE						= 0x12200000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_TCLOCKUP							= 0x12300000;
	public  static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_END								= 0x12FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_TOP									= 0x13000000;
	// ++, 150330 bwk
	//public  static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS						= 0x13100000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS1						= 0x13100000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS2						= 0x13200000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS3						= 0x13300000;
	// --, 150330 bwk
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS_POPUP					= 0x13400000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTUP_END									= 0x13FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_TOP								= 0x14000000;
	// ++, 150317 bwk
	//public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_HOURODOMETER						= 0x14100000;
	public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_FUEL								= 0x14100000;
	// --, 150317 bwk
	public  static final int SCREEN_STATE_MAIN_B_LEFTDOWN_END								= 0x14FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_B_QUICK_TOP									= 0x15000000;
	// ++, 150323 bwk
	public  static final int SCREEN_STATE_MAIN_B_QUICK_MULTICLOSE							= 0x15100000;
	public	static final int SCREEN_STATE_MAIN_B_QUICK_MIRACLOSE							= 0x15200000;
	// --, 150323 bwk
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
	public  static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3			= 0x16450000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_RIDECONTROL							= 0x16500000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED						= 0x16510000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD								= 0x16600000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ACCUMULATION					= 0x16610000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_DISPLAY						= 0x16620000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ERRORDETECT					= 0x16630000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_INIT							= 0x16640000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT1				= 0x16650000;
	public  static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT2				= 0x16660000;
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
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_ICCOMODE						= 0x21131000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE						= 0x21140000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP						= 0x21150000;
	public  static final int SCREEN_STATE_MENU_MODE_ENGINETM_END							= 0x211FFFFF;
	
	public  static final int SCREEN_STATE_MENU_MODE_HYD_TOP									= 0x21200000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP						= 0x21210000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_INIT						= 0x21211000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT1				= 0x21212000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT2				= 0x21213000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_END						= 0x2121FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_DETENT								= 0x21220000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY						= 0x21230000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_MAXFLOW								= 0x21240000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_TOP						= 0x21250000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_INIT						= 0x21251000;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_END						= 0x2125FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_HYD_END									= 0x212FFFFF;
	
	public  static final int SCREEN_STATE_MENU_MODE_ETC_TOP									= 0x21300000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP							= 0x21310000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_FREQ_INIT							= 0x21311000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_FREQ_END							= 0x2131FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP						= 0x21320000;
//	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_OFF						= 0x21321000;
//	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_AUTO						= 0x21321000;
//	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL					= 0x21322000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_END						= 0x2132FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_TOP					= 0x21330000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_PW						= 0x21331000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_AUTOSHUTDOWN_END					= 0x2133FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP							= 0x21340000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_WIPER_END							= 0x2134FFFF;
//	public  static final int SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_TOP					= 0x21350000;
//	public  static final int SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_END					= 0x2135FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP						= 0x21350000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_TOP				= 0x21351000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_POPUP				= 0x21351100;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_RESULT			= 0x21351200;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_ANGLE_END				= 0x21351FFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_TOP			= 0x21352000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_POPUP			= 0x21352100;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_RESULT			= 0x21352200;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_PRESSURE_END			= 0x21352FFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_BRAKEPEDAL_TOP			= 0x21353000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_BRAKEPEDAL_END			= 0x21353FFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_AEB_TOP					= 0x21354000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_AEB_END					= 0x21354FFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_END						= 0x2135FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_DELAYSHUTDOWN_TOP					= 0x21360000;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_DELAYSHUTDOWN_END					= 0x2136FFFF;
	public  static final int SCREEN_STATE_MENU_MODE_ETC_END									= 0x213FFFFF;
	
	
	public  static final int SCREEN_STATE_MENU_MODE_END										= 0x21FFFFFF;
	public  static final int SCREEN_STATE_MENU_MONITORING_TOP								= 0x22000000;
	// ++, 150329 bwk
	//public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING					= 0x22100000;
	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_TOP				= 0x22100000;
	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_1				= 0x22110000;
	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_2				= 0x22120000;
	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_3				= 0x22130000;
	public  static final int SCREEN_STATE_MENU_MONITORING_MACHINEMONITORING_END				= 0x221FFFFF;
	// --, 150329 bwk
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
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_BKCU					= 0x22480000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_ACU					= 0x22490000;
	public  static final int SCREEN_STATE_MENU_MONITORING_VERSIONINFO_END					= 0x224FFFFF;
	// ++, 150329 bwk
//	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_TOP						= 0x22500000;
//	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_BOOMLEVERFLOAT			= 0x22510000;
//	public  static final int SCREEN_STATE_MENU_MONITORING_EHCUINFO_END						= 0x225FFFFF;
	// --, 150329 bwk
	public  static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_TOP					= 0x22500000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_GENERALRECORD			= 0x22510000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_HOURLYRECORD			= 0x22520000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_DAILYRECORD			= 0x22530000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_MODERECORD			= 0x22540000;
	public	static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_INITIAL				= 0x25550000;
	public  static final int SCREEN_STATE_MENU_MONITORING_FUELHISTORY_END					= 0x225FFFFF;
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
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_TOP					= 0x23300000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_TOP				= 0x23310000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_RESULT			= 0x23311000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_ANGLE_END				= 0x2331FFFF;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_TOP			= 0x23320000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_RESULT		= 0x23321000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_PRESSURE_END			= 0x2332FFFF;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_BRAKEPEDAL_TOP		= 0x23330000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_BRAKEPEDAL_END		= 0x2333FFFF;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_AEB_TOP				= 0x23340000;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_AEB_END				= 0x2334FFFF;
//	public  static final int SCREEN_STATE_MENU_MANAGEMENT_CALIBRATION_END					= 0x233FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP						= 0x23300000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW						= 0x23310000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP		= 0x23320000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_POPUP	= 0x23321000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN	= 0x23322000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_END		= 0x2332FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP			= 0x23330000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_END			= 0x2333FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP	= 0x23340000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_END	= 0x2334FFFF;
	//public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SOFTWAREUPATE_TOP		= 0x23550000;
	//public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SOFTWAREUPATE_END		= 0x2355FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_TOP				= 0x23350000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_BOOMLEVERFLOAT	= 0x23351000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_EHCUINFO_END				= 0x2335FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_MACHINESERIALNUMBER_TOP	= 0x23360000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_MACHINESERIALNUMBER_END	= 0x2336FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SERVICE_END						= 0x233FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP						= 0x23400000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_END						= 0x234FFFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_TOP					= 0x23500000;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW					= 0x2351FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_POPUP				= 0x2352FFFF;
	public  static final int SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_END					= 0x235FFFFF;
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
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP				= 0x24400000;	
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TYPE				= 0x24410000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_TOP			= 0x24420000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE		= 0x24421000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_EXCEL_LANG_CHANGE	= 0x24421100;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE_POPUP	= 0x24422000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_END			= 0x2442FFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_END				= 0x244FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP					= 0x24500000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_CHANGE				= 0x24510000;	// ++, --, 150323 bwk
	public  static final int SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_END					= 0x245FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_CAMERASETTING_TOP					= 0x24600000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_CAMERASETTING_END					= 0x246FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_WIRELESS_TOP						= 0x24700000;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_WIRELESS_END						= 0x247FFFFF;
	public  static final int SCREEN_STATE_MENU_PREFERENCE_END								= 0x24FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_MULTIMEDIA_TOP								= 0x25000000;
	public  static final int SCREEN_STATE_MENU_MULTIMEDIA_END								= 0x25FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_USERSWITCHING_TOP							= 0x26000000;
	public  static final int SCREEN_STATE_MENU_USERSWITCHING_END							= 0x26FFFFFF;
	
	public  static final int SCREEN_STATE_MENU_END 											= 0x2FFFFFFF;
	
	public  static final int SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP						= 0x30000000;
	
	public  static final int SCREEN_STATE_MAIN_ESL_CHECK_TOP								= 0x40000000;
	public  static final int SCREEN_STATE_MAIN_ESL_PASSWORD									= 0x41000000;
	public  static final int SCREEN_STATE_MAIN_ESL_CHECK_END								= 0x4FFFFFFF;

	public  static final int SCREEN_STATE_MAIN_CAMERA_TOP									= 0x50000000;
	public  static final int SCREEN_STATE_MAIN_CAMERA_KEY									= 0x51000000;
	public  static final int SCREEN_STATE_MAIN_CAMERA_GEAR									= 0x52000000;
	public  static final int SCREEN_STATE_MAIN_CAMERA_END									= 0x5FFFFFFF;

	
	public  static final int SCREEN_STATE_MAIN_ENDING										= 0x60000000;
	
	public  static final int SCREEN_STATE_MAIN_A_TOP 										= 0x70000000;
	// ++, 150310 bwk
	public  static final int SCREEN_STATE_MAIN_A_RIGHTUP_TOP								= 0x71000000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTUP_ENGINE_MODE						= 0x71100000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTUP_HOURODMETER						= 0x71200000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTUP_END								= 0x71FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_A_RIGHTDOWN_TOP								= 0x72000000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTDOWN_CCOMODE							= 0x72100000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTDOWN_SHIFTMODE						= 0x72200000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTDOWN_TCLOCKUP							= 0x72300000;
	public  static final int SCREEN_STATE_MAIN_A_RIGHTDOWN_END								= 0x72FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_A_LEFTUP_TOP									= 0x73000000;
	public  static final int SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS1						= 0x73100000;
	public  static final int SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS2						= 0x73200000;
	public  static final int SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS3						= 0x73300000;
	public	static final int SCREEN_STATE_MAIN_A_LEFTUP_MACHINESTATUS_POPUP					= 0x73400000;
	public  static final int SCREEN_STATE_MAIN_A_LEFTUP_END									= 0x73FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_A_LEFTDOWN_TOP								= 0x74000000;
	public  static final int SCREEN_STATE_MAIN_A_LEFTDOWN_FUEL								= 0x74100000;
	public  static final int SCREEN_STATE_MAIN_A_LEFTDOWN_END								= 0x74FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_A_QUICK_TOP									= 0x75000000;
	// ++, 150323 bwk
	public  static final int SCREEN_STATE_MAIN_A_QUICK_MULTICLOSE							= 0x75100000;
	public	static final int SCREEN_STATE_MAIN_A_QUICK_MIRACLOSE							= 0x75200000;
	// --, 150323 bwk
	public  static final int SCREEN_STATE_MAIN_A_QUICK_END									= 0x75FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_A_KEY_TOP									= 0x76000000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_MAINLIGHT								= 0x76100000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLIGHT								= 0x76200000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_AUTOGREASE								= 0x76300000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER							= 0x76400000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING1			= 0x76410000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2			= 0x76420000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING1			= 0x76430000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2			= 0x76440000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3			= 0x76450000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_RIDECONTROL							= 0x76500000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_RIDECONTROL_SPEED						= 0x76510000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD								= 0x76600000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ACCUMULATION					= 0x76610000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD_DISPLAY						= 0x76620000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD_ERRORDETECT					= 0x76630000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD_INIT							= 0x76640000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT1				= 0x76650000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT2				= 0x76660000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_BEACONLAMP								= 0x76700000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_REARWIPER								= 0x76800000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_MIRRORHEAT								= 0x76900000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_DETENT									= 0x76A00000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_FINEMODULATION							= 0x76B00000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_FN										= 0x76C00000;
	public  static final int SCREEN_STATE_MAIN_A_KEY_END									= 0x76FFFFFF;
	
	public  static final int SCREEN_STATE_MAIN_A_BRKAEPEDALCALIBRATION_TOP					= 0x77000000;
	public  static final int SCREEN_STATE_MAIN_A_BRKAEPEDALCALIBRATION_END					= 0x77FFFFFF;
	public  static final int SCREEN_STATE_MAIN_A_AEB_TOP									= 0x78000000;
	public  static final int SCREEN_STATE_MAIN_A_AEB_END									= 0x78FFFFFF;
	// --, 150310 bwk
		
	public  static final int SCREEN_STATE_MAIN_A_END 										= 0x7FFFFFFF;
	
	public  static final int SCREEN_STATE_POPUP												= 0x80000000;
	public  static final int SCREEN_STATE_EHCUERR_POPUP										= 0x81000000;
	public  static final int SCREEN_STATE_AXLE_POPUP										= 0x82000000;

	public  static final int SCREEN_STATE_MAIN_CHECK_MACHINE_SERIAL							= 0x90000000;
	
	public	static final int UNIT_TYPE_METRIC		= 0;
	public	static final int UNIT_TYPE_US			= 1;
	public	static final int UNIT_TYPE_CUSTOM		= 2;
	
	public  static final int UNIT_FUEL_L 			= 0;
	public  static final int UNIT_FUEL_GAL 			= 1;
	
	public  static final int UNIT_ODO_KM 			= 0;
	public  static final int UNIT_ODO_MILE 			= 1;
	
	public  static final int UNIT_TEMP_F 			= 1;
	public  static final int UNIT_TEMP_C 			= 0;
	
	public  static final int UNIT_WEIGHT_TON 		= 0;
	public  static final int UNIT_WEIGHT_LB 		= 1;
	public  static final int UNIT_WEIGHT_US_TON		= 2;
	
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
	
	// ++, 150323 bwk
	public  static final int INTERNAL_SPK_MIN		= 0;
	public 	static final int INTERNAL_SPK_MAX		= 7;
	// --, 150323 bwk	
	
	public static final int STATE_INTERNAL_SPK		= 0;
	public static final int STATE_EXTERNAL_AUX	 	= 1;
	
	public static final int MAX_AS_LENGTH 			= 21;
	
	public static final int	DISPLAY_TYPE_A			= 0;
	public static final int	DISPLAY_TYPE_B			= 1;
	
	// ++, 150206 bwk
	public static final int STATE_DISPLAY_LANGUAGE_KOREAN 			= 0;
	public static final int STATE_DISPLAY_LANGUAGE_ENGLISH   		= 1;
	public static final int STATE_DISPLAY_LANGUAGE_GERMAN   		= 2;
	public static final int STATE_DISPLAY_LANGUAGE_FRENCH   		= 3;
	public static final int STATE_DISPLAY_LANGUAGE_SPANISH   		= 4;
	public static final int STATE_DISPLAY_LANGUAGE_PORTUGUE   		= 5;
	public static final int STATE_DISPLAY_LANGUAGE_ITALIAN   		= 6;
	public static final int STATE_DISPLAY_LANGUAGE_NEDERLAND   		= 7;
	public static final int STATE_DISPLAY_LANGUAGE_SWEDISH   		= 8;
	public static final int STATE_DISPLAY_LANGUAGE_FINNISH  		= 9;
	public static final int STATE_DISPLAY_LANGUAGE_SLOVAKIAN   		= 10;
	public static final int STATE_DISPLAY_LANGUAGE_ESTONIAN   		= 11;
	public static final int STATE_DISPLAY_LANGUAGE_TURKISH   		= 12;
	public static final int STATE_DISPLAY_LANGUAGE_HEBREW	 		= 13;
	
	// ++, 151005 cjg
	/*public static final int STATE_DISPLAY_LANGUAGE_CHINESE   		= 13;
	public static final int STATE_DISPLAY_LANGUAGE_RUSIAN   		= 14;
	public static final int STATE_DISPLAY_LANGUAGE_THAILAND   		= 15;
	public static final int STATE_DISPLAY_LANGUAGE_HINDI	   		= 16;
	public static final int STATE_DISPLAY_LANGUAGE_MONGOL   		= 17;
	public static final int STATE_DISPLAY_LANGUAGE_ARABIC   		= 18;
	public static final int STATE_DISPLAY_LANGUAGE_FARSI	   		= 19;
	public static final int STATE_DISPLAY_LANGUAGE_INDONESIAN  		= 20;*/
	// --, 151005 cjg
	// --, 150206 bwk
		
	public static final int WARMINGUP 				= 0;
	public static final int FUELWARMER				= 1;
	public static final int PREHEAT 				= 2;
	public static final int RIDECONTROL 			= 3;
	public static final int FLOATMODE 				= 4;
	public static final int FANREVERSE				= 5;
	public static final int CLUTCHCUTOFF 			= 6;
	public static final int LOCKUPCLUTCH 			= 7;
	public static final int SEATBELT 				= 8;
	public static final int ENGINEAUTOSHUTDOWN 		= 9;

	// ++, 150326 bwk
	public static final int REQ_ERR_MACHINE_ACTIVE		= 0;
	public static final int REQ_ERR_MACHINE_LOGGED		= 1;
	
	public static final int REQ_ERR_ENGINE_ACTIVE		= 2;
	public static final int REQ_ERR_ENGINE_LOGGED		= 3;
	
	public static final int REQ_ERR_TM_ACTIVE			= 4;
	public static final int REQ_ERR_TM_LOGGED			= 5;
	
	public static final int REQ_ERR_EHCU_ACTIVE			= 6;
	public static final int REQ_ERR_EHCU_LOGGED			= 7;
	
	public static final int REQ_ERR_START				= -1;
	public static final int REQ_ERR_END					= 15;
	// --, 150326 bwk
	////////////////////////////////////////////////////

	//Resource//////////////////////////////////////////
	FrameLayout framelayoutMain;
	ImageView imgViewCameraScreen;
	ImageView imgViewEnding;
	////////////////////////////////////////////////////
	//Valuable//////////////////////////////////////////
	public int ScreenIndex;
	public int OldScreenIndex;
	public int TempScreenIndex;
	// Display Type
	public int DisplayType;
	public int LanguageIndex;	// ++, --, 150206 bwk
	public LanguageClass LangClass;	// ++, --, 150209 bwk
	public LanguageDB langDb;
	
	// Unit
	public int UnitType;
	public int UnitFuel;
	public int UnitOdo;
	public int UnitTemp;
	public int UnitWeight;
	public int UnitPressure;
	
	// HourOdometer Index
	public int HourOdometerIndex;
	
	// ++, 150317 bwk
	// Fuel Index
	public int FuelIndex;
	// --, 150317 bwk
	
	// MachineStatus Index
	public int MachineStatusUpperIndex;
	public int MachineStatusLowerIndex;
	
	// Weighing Display Index
	
	// Camera
	public int ActiveCameraNum;		//the number of Available Camera
	public int CameraOrder1;		//CH1
	public int CameraOrder2;		//CH2
	public int CameraOrder3;		//CH3
	public int CameraOrder4;		//CH4
	public int CameraReverseMode;	//GEAE Reverse Mode
	int SelectCameraNum;			// ++, --, 150324 bwk
	int SelectGear;					//Select GEAR
	int SelectGearRange;			//Select GEAR ����
	int SelectGearDirection;		//Select GEAR ����
	int GearIndex;					//GEAR ��ġ
	int CameraReverseOnCount;		// CAMERA REVERSE ON COUNTER
	int CameraReverseOffCount;		// CAMERA REVERSE OFF COUNTER
	
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
	public boolean SmartIconDisplay;		// ++, --, 150326 bwk
	
	// Sound Output
	public int SoundState;
	public int InternalSoundLevel;
	
	// SeatBelt
	public int SeatBelt;
	
	// Weighing
	public int WeighingErrorDetect;
	
	// Buzzer
	int Buzzer;
	public boolean BuzzerOnFlag;
	
	// QuickCoupler Status
	public int AttachmentStatus;
	
	// AxleTempHighWarning
	public boolean FrontAxleWarningFlag;
	public boolean RearAxleWarningFlag;
	public int FrontAxleTempWarning;
	public int RearAxleTempWarning;
	public boolean AxleWarningFlag;
	
	// User Data
	public UserData UserDataDefault;
	public UserData UserDataUser1;
	public UserData UserDataUser2;
	public UserData UserDataUser3;
	public UserData UserDataUser4;
	public int UserIndex;	// ++, 150212 bwk;
	
	// CAN1CommManager
	private CAN1CommManager CAN1Comm = null;	
	
	// Thread
	private Thread threadRead = null;
	private Thread threadLoading = null;
	
	// Dialog
	Dialog HomeDialog;
	public QuickCouplerPopupLocking1 		_QuickCouplerPopupLocking1;
	public QuickCouplerPopupUnlocking1 		_QuickCouplerPopupUnlocking1;
	public QuickCouplerPopupLocking2 		_QuickCouplerPopupLocking2;
	public QuickCouplerPopupUnlocking2 		_QuickCouplerPopupUnlocking2;
	public QuickCouplerPopupUnlocking3		_QuickCouplerPopupUnlocking3;
	public CCOModePopup						_CCoModePopup;
	public ICCOModePopup					_ICCOModePopup;
	public ShiftModePopup 					_ShiftModePopup;
	public KickDownPopup 					_KickDownPopup;
	public TCLockUpPopup 					_TCLockUpPopup;
	public BucketPriorityPopup 				_BucketPriorityPopup;
	public EngineModePopup 					_EngineModePopup;
	public EngineWarmingUpPopup				_EngineWarmingUpPopup;
	public SpeedometerInitPopup				_SpeedometerInitPopup;
	public OperationHistoryInitPopup		_OperationHistoryInitPopup;
	public AngleCalibrationResultPopup		_AngleCalibrationResultPopup;
	public PressureCalibrationResultPopup	_PressureCalibrationResultPopup;
	public SoundOutputPopup					_SoundOutputPopup;
	public BrakePedalCalibrationPopup		_BrakePedalCalibrationPopup;
	public EngineAutoShutdownCountPopup		_EngineAutoShutdownCountPopup;
	public MaintReplacePopup				_MaintReplacePopup;
	public LoggedFaultDeletePopup			_LoggedFaultDeletePopup;
	public WorkLoadInitPopup				_WorkLoadInitPopup;
	public WorkLoadWeighingInitPopup1		_WorkLoadWeighingInitPopup1;
	public WorkLoadWeighingInitPopup2		_WorkLoadWeighingInitPopup2;
	public EHCUErrorPopup					_EHCUErrorPopup;
	public SoftStopInitPopup				_SoftStopInitPopup;
	// ++, 150313 cjg
	public MultimediaClosePopup				_MultimediaClosePopup;
	public MiracastClosePopup				_MiracastClosePopup;
	// --, 150313 cjg
	public FuelInitalPopup					_FuelInitalPopup;			// ++, --, 150406 bwk
	public AxleTempWarningPopup				_AxleTempWarningPopup;
	public CalibrationEHCUPopup				_CalibrationEHCUPopup;
	public SoftwareUpdateErrorPopup			_SoftwareUpdateErrorPopup;
	public LanguageChangePopup 				_LanguageChangePopup;
	public FanSelectModePopup				_FanSelectModePopup;
	
	//Toast
	public WeighingErrorToast				_WeighingErrorToast;
	
	
	// Timer
	private Timer mSeatBeltTimer = null;
	private Timer mAnimationRunningTimer = null;
	private Timer mSendCommandTimer = null;
	private Timer mCommErrStopTimer = null;
	private Timer mMirrorHeatTimer = null;
	private Timer mAutoGreaseTimer = null;
	private Timer mCheckMultimediaTimer = null;
	private Timer mCheckSmartTerminalTimer = null;
	private Timer mSendCIDTimer = null;
	private Timer mBackHomeTimer = null;
	
	// Count
	int MirrorHeatTimerCount;
	int AutoGreaseTimerCount;
	public int BuzzerStopCount;
	int CIDTimerCount;
	int BackHomeCount;

	// Flag
	public boolean AnimationRunningFlag;
	public boolean rpmRunningFlag;
	
	// Data
	int PreHeat;
	//int RPM;
	
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
	//public int MachineSerialNumber;
	//public int tempMachineSerialNumber;
	
	// Time
	public int Year;
	public int Month;
	public int Date;
	public int Hour;
	public int Min;
	public int Sec;
	
	// EHCU Err
	public int JoystickSteeringEnableFailCondition;
	public int OldJoystickSteeringEnableFailCondition;
	public int JoystickSteeringActiveStatus;
	public boolean bEHCUErrPopup;
	
	// ++, 150211 bwk
	// MediaPlayer <-> rpm
//	int HighrpmCount;
//	int LowrpmCount;
//	int PressFnKey;
	// --, 150211 bwk
	
	public int count = 0;// ++, --, 150324 cjg
	
	// ++, 150326 bwk
	int SendDTCIndex;
	// --, 150326 bwk
	
	// ++, 150520 bwk
	// Menu->KeyPad Value
	// -- MAINLIGHT
	int HeadLamp;
	int Illumination;
	int SelectMainLampStatus;
	// -- WORKLIGHT
	int WorkLamp;
	int RearWorkLamp;
	int SelectWorkLampStatus;
	// -- BEACONLAMP
	int SelectBeaconLamp;
	// -- WIPER
	int SelectWiperSpeedState;
	// --, 150520 bwk	
	////////////////////////////////////////////////////
	
	//Fragment//////////////////////////////////////////
	public  MainBBaseFragment 	_MainBBaseFragment;
	public  MenuBaseFragment 	_MenuBaseFragment;
	public 	ESLCheckFragment	_ESLCheckFragment;
	public 	ESLPasswordFragment	_ESLPasswordFragment;
	public	InputMachineSerialFragment	_InputMachineSerialFragment;
	public 	EndingFragment		_EndingFragment;
	public 	MainABaseFragment	_MainABaseFragment;
	////////////////////////////////////////////////////
	
	//Animation/////////////////////////////////////////
	public ChangeFragmentAnimation _MainChangeAnimation;
	AnimationDrawable EndingAnimation;
	////////////////////////////////////////////////////

	////////////////////////////////////////////////////
	CrashApplication _CrashApplication;
	////////////////////////////////////////////////////
	boolean runningCheckMiracast = false;
	
	
	//Lift Cycle Function///////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreateView");
		InitResource();
		InitValuable();		// ++, --, 150212 bwk InitPopup �Ʒ����� ���� �̵�
		InitFragment();
		InitPopup();
		InitAnimation();
		InitButtonListener();
		LoadPref();
		imgViewCameraScreen.setClickable(false);
		
		HandleKeyButton = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				KeyButtonClick(msg.what);
				if (msg.what != 0x00)
					Log.d(TAG, "Key : 0x" + Integer.toHexString(msg.what));
			}
		};
		
		//StartSeatBeltTimer();
		
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
		// ++, 150403 cjg
		if(CommService.GetPowerOffFlag() == true){
			showEndingFragment();
		}

		// --, 150403 cjg		
		try {			
			StartCommService();
			StopAlwaysOntopService();	// ++, --, 150324 cjg
			threadRead = new Thread(new ReadThread(this));
			threadLoading = new Thread(new LoadingThread(this));
//			threadLoading.start();
			if(CommService.pi != null){
				if(!CommService.pi.versionName.equals("1.0.5BF")){
					if(CheckRunningApp("com.powerone.wfd.sink") && CAN1Comm.isTopCheckMiracast() == true){
						CAN1Comm.SetScreenTopFlag(false);
					}else if(CheckRunningApp("com.powerone.wfd.sink") && CAN1Comm.isTopCheckMiracast() == false){
						CAN1Comm.SetScreenTopFlag(true);	
					}else {
						CAN1Comm.SetScreenTopFlag(true);
					}
				}else{
					CAN1Comm.SetScreenTopFlag(true);
				}
			}else{
				CAN1Comm.SetScreenTopFlag(true);
			}

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
		imgViewEnding = (ImageView)findViewById(R.id.imageView_main_ending);
		imgViewCameraScreen.setClickable(false);
	}
	public void InitValuable(){
		// ++, 150209 bwk
		LangClass = new LanguageClass(this);
		langDb = new LanguageDB(this);
		langDb.getCountLanguage();
		//LangClass.setLanugage(LangClass.GetLanguage());
		SetLanguage();
		JoystickSteeringEnableFailCondition = 0;
		JoystickSteeringActiveStatus = 0;
		// --, 150209 bwk
		ScreenIndex = 0;
		SeatBelt = CAN1CommManager.DATA_STATE_LAMP_ON;
		AnimationRunningFlag = false;
		MirrorHeatPreHeatFlag = false;
		MirrorHeatCount = 0;
		BuzzerStopCount = 0;
		nSendCommandTimerIndex = 0;
		BuzzerOnFlag = false;
		EngineAutoShutdownESLFlag = false;
		EngineAutoShutdownESLSetFlag = false;
		bEHCUErrPopup = false;
		_MainChangeAnimation = new ChangeFragmentAnimation(this, framelayoutMain, R.id.FrameLayout_main, null);
		_CheckModel = new CheckModel();
		
		UserDataDefault = new UserData();
		UserDataUser1 = new UserData();
		UserDataUser2 = new UserData();
		UserDataUser3 = new UserData();
		UserDataUser4 = new UserData();
		
		UserDataUser1 = LoadUserData(1);
		UserDataUser2 = LoadUserData(2);
		UserDataUser3 = LoadUserData(3);
		UserDataUser4 = LoadUserData(4);
		
		UserIndex = 1;		// ++, 150212 bwk
		
		//tempMachineSerialNumber = 0xffffff;
		
		// ++, 150211 bwk
//		HighrpmCount = 0;
//		LowrpmCount = 0;
//		PressFnKey = 0;
		BackHomeCount = 0xff;
		// --, 150211 bwk
		
		SelectCameraNum = 1;	// ++, 150324 bwk
		
		// ++, 150326 bwk
		SendDTCIndex = Home.REQ_ERR_START;
		// --, 150326 bwk		
		FrontAxleWarningFlag = false;
		RearAxleWarningFlag = false;
		AxleWarningFlag = false;
		
		rpmRunningFlag = true;
		
		_CrashApplication = (CrashApplication)getApplicationContext();		
	}
	
	public void SetLanguage(){
		try {  
			Locale locale;
			
			switch (LangClass.GetLanguage()) {
			case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
				locale = new Locale("ko");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
				locale = new Locale("en");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_GERMAN:
				locale = new Locale("de");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_FRENCH:
				locale = new Locale("fr");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SPANISH:
				locale = new Locale("es");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_PORTUGUE:
				locale = new Locale("pt");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ITALIAN:
				locale = new Locale("it");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_NEDERLAND:
				locale = new Locale("nl");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SWEDISH:
				locale = new Locale("sv");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_TURKISH:
				locale = new Locale("tr");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN:
				locale = new Locale("sk");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ESTONIAN:
				locale = new Locale("et");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_FINNISH:
				locale = new Locale("fi");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_HEBREW:
				locale = new Locale("iw");
				break;
			default:
				locale = new Locale("en");
				break;
		}
			
            Class<?> activityManagerNative = Class.forName("android.app.ActivityManagerNative");  
            Log.i("amnType", activityManagerNative.toString());  
              
            Object am=activityManagerNative.getMethod("getDefault").invoke(activityManagerNative);  
            Log.i("amType", am.getClass().toString());  
              
            Object config=am.getClass().getMethod("getConfiguration").invoke(am);  
            Log.i("configType", config.getClass().toString());  
            config.getClass().getDeclaredField("locale").set(config, locale);  
            config.getClass().getDeclaredField("userSetLocale").setBoolean(config, true);  
            am.getClass().getMethod("updateConfiguration",android.content.res.Configuration.class).invoke(am,config);  
          
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
			
	}
	public void InitFragment(){
		_MainBBaseFragment = new MainBBaseFragment();
		_MenuBaseFragment = new MenuBaseFragment();
		_ESLCheckFragment = new ESLCheckFragment();
		_ESLPasswordFragment = new ESLPasswordFragment();
		_InputMachineSerialFragment = new InputMachineSerialFragment();
		_EndingFragment = new EndingFragment();
		_MainABaseFragment = new MainABaseFragment();
	}
	public void InitPopup(){
		HomeDialog = null;
		_QuickCouplerPopupLocking1 = new QuickCouplerPopupLocking1(this);
		_QuickCouplerPopupUnlocking1 = new QuickCouplerPopupUnlocking1(this);
		_QuickCouplerPopupLocking2 = new QuickCouplerPopupLocking2(this);
		_QuickCouplerPopupUnlocking2 = new QuickCouplerPopupUnlocking2(this);
		_QuickCouplerPopupUnlocking3 = new QuickCouplerPopupUnlocking3(this);
		_CCoModePopup = new CCOModePopup(this);
		_ICCOModePopup = new ICCOModePopup(this);
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
		_WorkLoadInitPopup = new WorkLoadInitPopup(this);
		_WorkLoadWeighingInitPopup1 = new WorkLoadWeighingInitPopup1(this);
		_WorkLoadWeighingInitPopup2 = new WorkLoadWeighingInitPopup2(this);
		_EHCUErrorPopup = new EHCUErrorPopup(this);
		_SoftStopInitPopup = new SoftStopInitPopup(this);
		// ++, 150313 cjg
		_MultimediaClosePopup = new MultimediaClosePopup(this);
		_MiracastClosePopup = new MiracastClosePopup(this);
		// --, 150313 cjg
		_FuelInitalPopup = new FuelInitalPopup(this);
		_AxleTempWarningPopup = new AxleTempWarningPopup(this);
		_CalibrationEHCUPopup = new CalibrationEHCUPopup(this);
		_SoftwareUpdateErrorPopup = new SoftwareUpdateErrorPopup(this);
		_LanguageChangePopup = new LanguageChangePopup(this);
		_FanSelectModePopup = new FanSelectModePopup(this);
		
		_WeighingErrorToast = new WeighingErrorToast(this);
	}

	// ++, 150306 bwk
	public void ResetPopup(){
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		_QuickCouplerPopupLocking1 = new QuickCouplerPopupLocking1(this);
		_QuickCouplerPopupUnlocking1 = new QuickCouplerPopupUnlocking1(this);
		_QuickCouplerPopupLocking2 = new QuickCouplerPopupLocking2(this);
		_QuickCouplerPopupUnlocking2 = new QuickCouplerPopupUnlocking2(this);
		_QuickCouplerPopupUnlocking3 = new QuickCouplerPopupUnlocking3(this);
		_CCoModePopup = new CCOModePopup(this);
		_ICCOModePopup = new ICCOModePopup(this);
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
		_WorkLoadInitPopup = new WorkLoadInitPopup(this);
		_WorkLoadWeighingInitPopup1 = new WorkLoadWeighingInitPopup1(this);
		_WorkLoadWeighingInitPopup2 = new WorkLoadWeighingInitPopup2(this);
		_EHCUErrorPopup = new EHCUErrorPopup(this);
		_SoftStopInitPopup = new SoftStopInitPopup(this);
		// ++, 150323 bwk
		_MultimediaClosePopup = new MultimediaClosePopup(this);
		_MiracastClosePopup = new MiracastClosePopup(this);
		// --, 150323 bwk
		_FuelInitalPopup = new FuelInitalPopup(this);
		_AxleTempWarningPopup = new AxleTempWarningPopup(this);
		
		_WeighingErrorToast = new WeighingErrorToast(this);
		_CalibrationEHCUPopup = new CalibrationEHCUPopup(this);
		_SoftwareUpdateErrorPopup = new SoftwareUpdateErrorPopup(this);
		_LanguageChangePopup = new LanguageChangePopup(this);
		_FanSelectModePopup = new FanSelectModePopup(this);
	}
	// --, 150306 bwk
	
	public void InitAnimation(){
		
	}
	public void InitButtonListener(){	
//		imgViewCameraScreen.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ExitCam();
//			}
//		});
		imgViewCameraScreen.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch(event.getAction()){
					case MotionEvent.ACTION_UP:
						//Log.d(TAG, "imgViewCameraScreen.ScreenIndex"+Integer.toHexString(ScreenIndex));
						if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL)
							ExitCam();
						else
							Log.d(TAG, "imgViewCameraScreen.setOnClickListener"+CAN1Comm.CameraOnFlag);
						
				}
				return false;
			}
		});
		
	}
	public void ExcuteCamActivitybyKey(){
		//Log.d(TAG,"ExcuteCamActivitybyKey" + CAN1Comm.GetScreenTopFlag());
		imgViewCameraScreen.setClickable(true);
		CAN1Comm.CameraCurrentOnOff = true;	// ++, --, 150326 cjg
		TempScreenIndex = OldScreenIndex;	// ++, --, 151110 cjg
		OldScreenIndex = ScreenIndex;
		ScreenIndex = SCREEN_STATE_MAIN_CAMERA_KEY;
		CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_MANUAL;
		SelectCameraNum = CameraOrder1+1;
		CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_CAM, CameraOrder1);
	}
	// �Ʒ� �Լ� ���������� ���� (++, --, 151110 cjg)
//	public void ExcuteCamActivitybyReverseGear(){
//		CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_REVERSEGEAR;
//		CAN1Comm.CameraCurrentOnOff = true;	// ++, --, 150326 cjg
//		
//	}
	public boolean ExitCam(){
		//Log.d(TAG,"ExitCam() CameraCurrentOnOff : "+CAN1Comm.CameraCurrentOnOff+"CameraOnFlag"+CAN1Comm.CameraOnFlag);
		// ++, 151110 cjg
//		ScreenIndex = OldScreenIndex;
//		OldScreenIndex = SCREEN_STATE_MAIN_CAMERA_KEY;
		ScreenIndex = OldScreenIndex;
		OldScreenIndex = TempScreenIndex;
		// --, 151110 cjg
		try {
			CAN1Comm.CameraCurrentOnOff = false;	// ++, --, 150326 cjg
			if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL){
				CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_OFF;
				CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_CAM, 0xFF);
				imgViewCameraScreen.setClickable(false);
				
				if (ScreenIndex == SCREEN_STATE_MAIN_B_TOP	|| ScreenIndex == SCREEN_STATE_MAIN_A_TOP) {
					FrontAxleWarningFlag = false;
					RearAxleWarningFlag = false;
				}
				
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
	// ++, 150324 bwk
	public void ChangeCam(int Key){
		if(Key == CAN1CommManager.LEFT){
			if(SelectCameraNum > 1)
				SelectCameraNum--;
			else
				SelectCameraNum = ActiveCameraNum;
		}else if(Key == CAN1CommManager.RIGHT){
			if(SelectCameraNum < ActiveCameraNum)
				SelectCameraNum++;
			else
				SelectCameraNum = 1;
		}
		
		CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_CAM, SelectCameraNum-1);
	}
	// --, 150324 bwk
//	public void SetMachineSerialNumber()
//	{
//		SaveMachineSerialNumber();
//		showMainScreen();
//		StartSeatBeltTimer();
//	}
//	public void SaveMachineSerialNumber(){
//		String str= Integer.toString(MachineSerialNumber);
//		int tempNumber = Integer.parseInt(str,16);
//		
//		byte[] Temp = new byte[3];
//		Temp[0] = (byte)( (tempNumber >>> 16) & 0xFF );
//		Temp[1] = (byte)( (tempNumber >>> 8) & 0xFF );
//		Temp[2] = (byte)( (tempNumber >>> 0) & 0xFF );
//		
//		CAN1Comm.Set_MachineSerialNumber2_962_PGN65327(Temp);
//		CAN1Comm.TxCANToMCU(47);
//
//		SharedPreferences SharePref = getSharedPreferences("Home", 0);
//		SharedPreferences.Editor edit = SharePref.edit();
//		edit.putInt("MachineSerialNumber", MachineSerialNumber);
//		edit.commit();
//		
//		Log.d(TAG,"SaveMachineSerialNumber");
//	}
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
		
//		MachineSerialNumber = 0xffffff;
//		SaveMachineSerialNumber();
	}
	public void CheckCID(){
		Log.d(TAG,"CheckCID");
		CAN1Comm.Set_HCEPGN_PGN59904(65330);
		
		if((CAN1Comm.Get_ComponentCode_1699_PGN65330() != CAN1CommManager.STATE_COMPONENTCODE_MCU)
			|| (CAN1Comm.Get_ComponentCode_1699_PGN65330_CLUSTER() != CAN1CommManager.STATE_COMPONENTCODE_CLUSTER)
			|| (CAN1Comm.Get_ComponentCode_1699_PGN65330_TCU() != CAN1CommManager.STATE_COMPONENTCODE_TCU)
			|| (CAN1Comm.Get_ComponentCode_1699_PGN65330_ECM() != CAN1CommManager.STATE_COMPONENTCODE_ECM))
		{
			Log.d(TAG,"All CID Request");
			CAN1Comm.Set_TargetSourceAddress(0xFF);
			CAN1Comm.TxCANToMCU(0xEA);
		}
		else
		{
			if(CAN1Comm.Get_ComponentCode_1699_PGN65330_BKCU() != CAN1CommManager.STATE_COMPONENTCODE_BKCU)
			{
				Log.d(TAG,"BKCU CID Request");
				CAN1Comm.Set_TargetSourceAddress(CAN1CommManager.SA_BKCU);
				CAN1Comm.TxCANToMCU(0xEA);
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU)
			{
				Log.d(TAG,"EHCU CID Request");
				CAN1Comm.Set_TargetSourceAddress(CAN1CommManager.SA_EHCU);
				CAN1Comm.TxCANToMCU(0xEA);
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(CAN1Comm.Get_ComponentCode_1699_PGN65330_RMCU() != CAN1CommManager.STATE_COMPONENTCODE_RMCU)
			{
				Log.d(TAG,"RMCU CID Request");
				CAN1Comm.Set_TargetSourceAddress(CAN1CommManager.SA_RMCU);
				CAN1Comm.TxCANToMCU(0xEA);
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			if(CAN1Comm.Get_ComponentCode_1699_PGN65330_ACU() != CAN1CommManager.STATE_COMPONENTCODE_AIRCONCONTROLLER)
//			{
//				Log.d(TAG,"ACU CID Request");
//				CAN1Comm.Set_TargetSourceAddress(CAN1CommManager.SA_ACU);
//				CAN1Comm.TxCANToMCU(0xEA);
//				
//				try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		}
	}
	public void SendCID(){
		int _Componentcode;
		int _Manufacturecode;
		byte[] _ComponentBasicInformation;
		int Index = 4;
		int Index2 = 0;
		boolean bAsterisk = false;
		
		_ComponentBasicInformation = new byte[CAN1CommManager.LENGTH_COMPONENTBASICINFORMATION];

		SharedPreferences SharePref = getSharedPreferences("CID", 0);
		
		_Componentcode = SharePref.getInt("ComponentCode_Monitor", 11);
		_Manufacturecode = SharePref.getInt("ManufacturerCode_Monitor", 1);
		_ComponentBasicInformation = GetMonitorComponentBasicInfo();
		
		_ComponentBasicInformation[3] = ((VERSION_HIGH & 0x0F) << 4) + (VERSION_LOW & 0x0F);

		//////////////Find Serial Number/////////////
		for(int i = 4; i < 20; i++){
			if(_ComponentBasicInformation[i] != 0x2A)
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
			if(_ComponentBasicInformation[i] != 0x2A)
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

		if(bAsterisk == true)
			_ComponentBasicInformation[Index + Index2+2] = ((VERSION_SUB_HIGH & 0x0F) << 4) + (VERSION_SUB_LOW & 0x0F);

		CAN1Comm.Set_ComponentCode_1699_PGN65330_MONITOR(_Componentcode);
		CAN1Comm.Set_ManufacturerCode_1700_PGN65330_MONITOR(_Manufacturecode);
		CAN1Comm.Set_ComponentBasicInformation_1698_PGN65330_MONITOR(_ComponentBasicInformation);
		
		CAN1Comm.TxCANToMCU(50);
	}
	public void SavePref(){
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("UnitType", UnitType);
		edit.putInt("UnitFuel", UnitFuel);
		edit.putInt("UnitOdo", UnitOdo);
		edit.putInt("UnitTemp", UnitTemp);
		edit.putInt("UnitWeight", UnitWeight);
		edit.putInt("UnitPressure", UnitPressure);
		edit.putInt("HourOdometerIndex", HourOdometerIndex);
		edit.putInt("FuelIndex", FuelIndex);		// ++, --, 150317 bwk
		edit.putInt("MachineStatusUpperIndex", MachineStatusUpperIndex);
		edit.putInt("MachineStatusLowerIndex", MachineStatusLowerIndex);
		edit.putInt("WeighingErrorDetect", WeighingErrorDetect);
		edit.putInt("DisplayType", DisplayType);
		edit.putInt("AttachmentStatus", AttachmentStatus);		
		edit.putInt("BrightnessManualLevel", BrightnessManualLevel);
		edit.putInt("BrightnessManualAuto", BrightnessManualAuto);
		edit.putInt("SoundState", SoundState);
		edit.putInt("LanguageIndex", LanguageIndex);		// ++, --, 150206 bwk
		edit.putInt("InternalSoundLevel", InternalSoundLevel);		// ++, --, 150324 bwk
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	public void LoadPref(){
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		UnitType = SharePref.getInt("UnitType", UNIT_TYPE_CUSTOM);
		UnitFuel = SharePref.getInt("UnitFuel", UNIT_FUEL_L);
		UnitOdo = SharePref.getInt("UnitOdo", UNIT_ODO_KM);
		UnitTemp = SharePref.getInt("UnitTemp", UNIT_TEMP_C);
		UnitWeight = SharePref.getInt("UnitWeight", UNIT_WEIGHT_TON);
		UnitPressure = SharePref.getInt("UnitPressure", UNIT_PRESSURE_BAR);
		HourOdometerIndex = SharePref.getInt("HourOdometerIndex", CAN1CommManager.DATA_STATE_HOURMETER_LATEST);
		FuelIndex = SharePref.getInt("FuelIndex", CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE);	// ++, --, 150331 bwk			// ++, --, 150407 bwk �ʱⰪ ��տ���
		MachineStatusUpperIndex = SharePref.getInt("MachineStatusUpperIndex", CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT);		// ++, --, 150407 bwk 9A �����ϰ�(NoSelect -> �۵���)
		MachineStatusLowerIndex = SharePref.getInt("MachineStatusLowerIndex", CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY);	// ++, --, 150407 bwk 9A �����ϰ�(NoSelect -> �ð���)
		WeighingErrorDetect = SharePref.getInt("WeighingErrorDetect", CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON);
		
		ActiveCameraNum = SharePref.getInt("ActiveCameraNum", 4);
		CameraOrder1 = SharePref.getInt("CameraOrder1", 0);
		CameraOrder2 = SharePref.getInt("CameraOrder2", 1);
		CameraOrder3 = SharePref.getInt("CameraOrder3", 2);
		CameraOrder4 = SharePref.getInt("CameraOrder4", 3);
		CameraReverseMode = SharePref.getInt("CameraReverseMode", CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF);
		
		BrightnessManualAuto = SharePref.getInt("BrightnessManualAuto", BRIGHTNESS_MANUAL);
		BrightnessManualLevel = SharePref.getInt("BrightnessManualLevel", BRIGHTNESS_MAX);
		BrightnessAutoDayLevel = SharePref.getInt("BrightnessAutoDayLevel", BRIGHTNESS_MAX);
		BrightnessAutoNightLevel = SharePref.getInt("BrightnessAutoNightLevel", 4);
		BrightnessAutoStartTime = SharePref.getInt("BrightnessAutoStartTime", 8);
		BrightnessAutoEndTime = SharePref.getInt("BrightnessAutoEndTime", 18);
		SoundState = SharePref.getInt("SoundState", STATE_INTERNAL_SPK);
		InternalSoundLevel = SharePref.getInt("InternalSoundLevel", INTERNAL_SPK_MAX);		// ++, --, 150324 bwk
		
		SmartKeyUse = SharePref.getInt("SmartKeyUse", CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF);
		
		strASNumDash = SharePref.getString("strASNumDash", "1899-7282");	// ++, --, 150402 bwk A/S ��ȣ �߰� 
		strASNum = SharePref.getString("strASNum", "18997282");	// ++, --, 150402 bwk A/S ��ȣ �߰� 
		
		DisplayType = SharePref.getInt("DisplayType", DISPLAY_TYPE_A);	// ++, --, 151130 B->A, 150323 bwk A->B
//		setScreenIndex();	// ++, --, 150310 bwk
		
		LanguageIndex = SharePref.getInt("LanguageIndex", STATE_DISPLAY_LANGUAGE_ENGLISH);		// ++, --, 150206 bwk
		//LangClass.setLanugage(LanguageIndex);
		
		AttachmentStatus = SharePref.getInt("AttachmentStatus", CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_OFF);
		
//		MachineSerialNumber = SharePref.getInt("MachineSerialNumber", 0xFFFFFF);
		Log.d(TAG,"LoadPref");
	}
	
	public void SaveUserData(int Index, UserData _userdata){
		String strEngineMode = "EngineMode" + Integer.toString(Index);
		//String strWarmingUp = "WarmingUp" + Integer.toString(Index);
		String strCCOMode = "CCOMode" + Integer.toString(Index);
		String strShiftMode = "ShiftMode" + Integer.toString(Index);
		String strTCLockUp = "TCLockUp" + Integer.toString(Index);
		String strRideControl = "RideControl" + Integer.toString(Index);
		String strWeighingSystem = "WeighingSystem" + Integer.toString(Index);
		String strWeighingDisplay = "WeighingDisplay" + Integer.toString(Index);
		String strErrorDetection = "ErrorDetection" + Integer.toString(Index);
		String strKickDown = "KickDown" + Integer.toString(Index);
		String strBucketPriority = "BucketPriority" + Integer.toString(Index);
		String strSoftEndStopBoomUp = "SoftEndStopBoomUp" + Integer.toString(Index);
		String strSoftEndStopBoomDown = "SoftEndStopBoomDown" + Integer.toString(Index);
		String strSoftEndStopBucketIn = "SoftEndStopBucketIn" + Integer.toString(Index);
		String strSoftEndStopBucketDump = "SoftEndStopBucketDump" + Integer.toString(Index);
		String strDisplayType = "DisplayType" + Integer.toString(Index);
		// ++, 150407 bwk
		//String strBrightness = "Brightness" + Integer.toString(Index);
		String strBrightnessManualAuto = "BrightnessManualAuto" + Integer.toString(Index);
		String strBrightnessManualLevel = "BrightnessManualLevel" + Integer.toString(Index);
		String strBrightnessAutoDayLevel = "BrightnessAutoDayLevel" + Integer.toString(Index);
		String strBrightnessAutoNightLevel = "BrightnessAutoNightLevel" + Integer.toString(Index);
		String strBrightnessAutoStartTime = "BrightnessAutoStartTime" + Integer.toString(Index);
		String strBrightnessAutoEndTime = "BrightnessAutoEndTime" + Integer.toString(Index);
		// --, 150407 bwk
		String strUnitFuel = "UnitFuel" + Integer.toString(Index);
		String strUnitTemp = "UnitTemp" + Integer.toString(Index);
		String strUnitOdo = "UnitOdo" + Integer.toString(Index);
		String strUnitWeight = "UnitWeight" + Integer.toString(Index);
		String strUnitPressure = "UnitPressure" + Integer.toString(Index);
		String strMachineStatusUpper = "MachineStatusUpper" + Integer.toString(Index);
		String strMachineStatusLower = "MachineStatusLower" + Integer.toString(Index);
		String strLanguage = "Language" + Integer.toString(Index);
		String strSoundOutput = "SoundOutput" + Integer.toString(Index);
		String strHourmeterDisplay = "HourmeterDisplay" + Integer.toString(Index);
		String strFuelDisplay = "FuelDisplay" + Integer.toString(Index);
		String strBoomDetentMode = "BoomDetentMode" + Integer.toString(Index);
		String strBucketDetentMode = "BucketDetentMode" + Integer.toString(Index);

		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt(strEngineMode, _userdata.EngineMode);
		//edit.putInt(strWarmingUp, _userdata.WarmingUp);
		edit.putInt(strCCOMode, _userdata.CCOMode);
		edit.putInt(strShiftMode, _userdata.ShiftMode);
		edit.putInt(strTCLockUp, _userdata.TCLockUp);
		edit.putInt(strRideControl, _userdata.RideControl);
		edit.putInt(strWeighingSystem, _userdata.WeighingSystem);
		edit.putInt(strWeighingDisplay, _userdata.WeighingDisplay);
		edit.putInt(strErrorDetection, _userdata.ErrorDetection);
		edit.putInt(strKickDown, _userdata.KickDown);
		edit.putInt(strBucketPriority, _userdata.BucketPriority);
		edit.putInt(strSoftEndStopBoomUp, _userdata.SoftEndStopBoomUp);
		edit.putInt(strSoftEndStopBoomDown, _userdata.SoftEndStopBoomDown);
		edit.putInt(strSoftEndStopBucketIn, _userdata.SoftEndStopBucketIn);
		edit.putInt(strSoftEndStopBucketDump, _userdata.SoftEndStopBucketDump);
		// ++, 150407 bwk
		//edit.putInt(strBrightness, _userdata.Brightness);
		edit.putInt(strBrightnessManualAuto, _userdata.BrightnessManualAuto);
		edit.putInt(strBrightnessManualLevel, _userdata.BrightnessManualLevel);
		edit.putInt(strBrightnessAutoDayLevel, _userdata.BrightnessAutoDayLevel);
		edit.putInt(strBrightnessAutoNightLevel, _userdata.BrightnessAutoNightLevel);
		edit.putInt(strBrightnessAutoStartTime, _userdata.BrightnessAutoStartTime);
		edit.putInt(strBrightnessAutoEndTime, _userdata.BrightnessAutoEndTime);
		// --, 150407 bwk		
		edit.putInt(strDisplayType, _userdata.DisplayType);
		edit.putInt(strUnitFuel, _userdata.UnitFuel);
		edit.putInt(strUnitTemp, _userdata.UnitTemp);
		edit.putInt(strUnitOdo, _userdata.UnitOdo);
		edit.putInt(strUnitWeight, _userdata.UnitWeight);
		edit.putInt(strUnitPressure, _userdata.UnitPressure);
		edit.putInt(strMachineStatusUpper, _userdata.MachineStatusUpper);
		edit.putInt(strMachineStatusLower, _userdata.MachineStatusLower);
		edit.putInt(strLanguage, _userdata.Language);
		edit.putInt(strSoundOutput, _userdata.SoundOutput);
		edit.putInt(strHourmeterDisplay, _userdata.HourmeterDisplay);
		edit.putInt(strFuelDisplay, _userdata.FuelDisplay);

		edit.putInt(strBoomDetentMode, _userdata.BoomDetentMode);
		edit.putInt(strBucketDetentMode, _userdata.BucketDetentMode);
		
		edit.commit();
		Log.d(TAG,"SaveUserData" + Integer.toString(Index));
		
	}
	public UserData LoadUserData(int Index){
		String strEngineMode = "EngineMode" + Integer.toString(Index);
		//String strWarmingUp = "WarmingUp" + Integer.toString(Index);
		String strCCOMode = "CCOMode" + Integer.toString(Index);
		String strShiftMode = "ShiftMode" + Integer.toString(Index);
		String strTCLockUp = "TCLockUp" + Integer.toString(Index);
		String strRideControl = "RideControl" + Integer.toString(Index);
		String strWeighingSystem = "WeighingSystem" + Integer.toString(Index);
		String strWeighingDisplay = "WeighingDisplay" + Integer.toString(Index);
		String strErrorDetection = "ErrorDetection" + Integer.toString(Index);
		String strKickDown = "KickDown" + Integer.toString(Index);
		String strBucketPriority = "BucketPriority" + Integer.toString(Index);
		String strSoftEndStopBoomUp = "SoftEndStopBoomUp" + Integer.toString(Index);
		String strSoftEndStopBoomDown = "SoftEndStopBoomDown" + Integer.toString(Index);
		String strSoftEndStopBucketIn = "SoftEndStopBucketIn" + Integer.toString(Index);
		String strSoftEndStopBucketDump = "SoftEndStopBucketDump" + Integer.toString(Index);
		String strBrightness = "Brightness" + Integer.toString(Index);
		// ++, 150407 bwk
		//String strBrightness = "Brightness" + Integer.toString(Index);
		String strBrightnessManualAuto = "BrightnessManualAuto" + Integer.toString(Index);
		String strBrightnessManualLevel = "BrightnessManualLevel" + Integer.toString(Index);
		String strBrightnessAutoDayLevel = "BrightnessAutoDayLevel" + Integer.toString(Index);
		String strBrightnessAutoNightLevel = "BrightnessAutoNightLevel" + Integer.toString(Index);
		String strBrightnessAutoStartTime = "BrightnessAutoStartTime" + Integer.toString(Index);
		String strBrightnessAutoEndTime = "BrightnessAutoEndTime" + Integer.toString(Index);
		// --, 150407 bwk		
		String strDisplayType = "DisplayType" + Integer.toString(Index);
		String strUnitFuel = "UnitFuel" + Integer.toString(Index);
		String strUnitTemp = "UnitTemp" + Integer.toString(Index);
		String strUnitOdo = "UnitOdo" + Integer.toString(Index);
		String strUnitWeight = "UnitWeight" + Integer.toString(Index);
		String strUnitPressure = "UnitPressure" + Integer.toString(Index);
		String strMachineStatusUpper = "MachineStatusUpper" + Integer.toString(Index);
		String strMachineStatusLower = "MachineStatusLower" + Integer.toString(Index);
		String strLanguage = "Language" + Integer.toString(Index);
		String strSoundOutput = "SoundOutput" + Integer.toString(Index);
		String strHourmeterDisplay = "HourmeterDisplay" + Integer.toString(Index);
		String strFuelDisplay = "FuelDisplay" + Integer.toString(Index);
		String strBoomDetentMode = "BoomDetentMode" + Integer.toString(Index);
		String strBucketDetentMode = "BucketDetentMode" + Integer.toString(Index);
		
		UserData _userdata;
		_userdata = new UserData();
		
		SharedPreferences SharePref = getSharedPreferences("Home", 0);
		
		_userdata.EngineMode = SharePref.getInt(strEngineMode, CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR);
		//_userdata.WarmingUp = SharePref.getInt(strWarmingUp, CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF);
		_userdata.CCOMode = SharePref.getInt(strCCOMode, CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H);
		_userdata.ShiftMode = SharePref.getInt(strShiftMode, CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL);
		_userdata.TCLockUp = SharePref.getInt(strTCLockUp, CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON);
		_userdata.RideControl = SharePref.getInt(strRideControl, CAN1CommManager.DATA_STATE_RIDECONTROL_OFF);
		_userdata.WeighingSystem = SharePref.getInt(strWeighingSystem, CAN1CommManager.DATA_STATE_WEIGHING_ACCUMULATION_AUTO);
		_userdata.WeighingDisplay = SharePref.getInt(strWeighingDisplay, CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A);
		_userdata.ErrorDetection = SharePref.getInt(strErrorDetection, CAN1CommManager.DATA_STATE_WEIGHING_ERRORDETECT_ON);
		_userdata.KickDown = SharePref.getInt(strKickDown, CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN);
		_userdata.BucketPriority = SharePref.getInt(strBucketPriority, CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF);
		_userdata.SoftEndStopBoomUp = SharePref.getInt(strSoftEndStopBoomUp, CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON);
		_userdata.SoftEndStopBoomDown = SharePref.getInt(strSoftEndStopBoomDown, CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON);
		_userdata.SoftEndStopBucketIn = SharePref.getInt(strSoftEndStopBucketIn, CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF);
		_userdata.SoftEndStopBucketDump = SharePref.getInt(strSoftEndStopBucketDump, CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON);
		// ++, 150407 bwk
		//_userdata.Brightness = SharePref.getInt(strBrightness,8);
		_userdata.BrightnessManualAuto = SharePref.getInt(strBrightnessManualAuto,BRIGHTNESS_MANUAL);
		_userdata.BrightnessManualLevel = SharePref.getInt(strBrightnessManualLevel,BRIGHTNESS_MAX);
		_userdata.BrightnessAutoDayLevel = SharePref.getInt(strBrightnessAutoDayLevel,BRIGHTNESS_MAX);
		_userdata.BrightnessAutoNightLevel = SharePref.getInt(strBrightnessAutoNightLevel,4);
		_userdata.BrightnessAutoStartTime = SharePref.getInt(strBrightnessAutoStartTime,8);
		_userdata.BrightnessAutoEndTime = SharePref.getInt(strBrightnessAutoEndTime,18);
		// --, 150407 bwk
		_userdata.DisplayType = SharePref.getInt(strDisplayType,Home.DISPLAY_TYPE_A);
		_userdata.UnitFuel = SharePref.getInt(strUnitFuel, Home.UNIT_FUEL_L);
		_userdata.UnitTemp = SharePref.getInt(strUnitTemp, Home.UNIT_TEMP_C);
		_userdata.UnitOdo = SharePref.getInt(strUnitOdo, Home.UNIT_ODO_KM);
		_userdata.UnitWeight = SharePref.getInt(strUnitWeight, Home.UNIT_WEIGHT_TON);
		_userdata.UnitPressure = SharePref.getInt(strUnitPressure, Home.UNIT_PRESSURE_BAR);
		_userdata.MachineStatusUpper = SharePref.getInt(strMachineStatusUpper, CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT);
		_userdata.MachineStatusLower = SharePref.getInt(strMachineStatusLower, CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY);
		_userdata.Language = SharePref.getInt(strLanguage,Home.STATE_DISPLAY_LANGUAGE_ENGLISH);
		_userdata.SoundOutput = SharePref.getInt(strSoundOutput, Home.STATE_INTERNAL_SPK);
		_userdata.HourmeterDisplay = SharePref.getInt(strHourmeterDisplay, CAN1CommManager.DATA_STATE_HOURMETER_LATEST);
		_userdata.FuelDisplay = SharePref.getInt(strFuelDisplay, CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE);

		_userdata.BoomDetentMode = SharePref.getInt(strBoomDetentMode, CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN);
		_userdata.BucketDetentMode = SharePref.getInt(strBucketDetentMode, CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN);

		Log.d(TAG,"LoadUserData" + Integer.toString(Index));
		return _userdata;
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
	// ++, 150213 bwk
	public void setLanguage(){
		try {
			LangClass.setLanugage(LanguageIndex);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG, "NullPointerException");
		} catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG, "Load Library Error");
		}
	}
	// --, 150213 bwk
/////////////////////////////////////////////////////
	public void MainLightLampStatus(int _headlamp, int _illumination){
		if(_headlamp == 0 && _illumination == 0){
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF;
		}else if(_headlamp == 0 && _illumination == 1){
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV1;
		}else if(_headlamp == 1 && _illumination == 1){
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV2;
		}else{
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF;
		}
		
	}
	
	public void WorkLightLampDisplay(int _worklamp, int _rearworklamp){
		if(_worklamp == 0 && _rearworklamp == 0){
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
		}else if(_worklamp == 1 && _rearworklamp == 0){
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1;
		}else if(_worklamp == 1 && _rearworklamp == 1){
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2;
		}else{
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
		}
	}
	
	
	public void ClickMainLightHardKey(){
		switch (SelectMainLampStatus) {
		case CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF:
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV1;
			CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV1:
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV2;
			CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_LV2:
		default:
			SelectMainLampStatus = CAN1CommManager.DATA_STATE_KEY_MAINLIGHT_OFF;
			CAN1Comm.Set_HeadLampOperationStatus_3436_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_IlluminationOperationStatus_3438_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		}
	}
	
	public void ClickWorkLightHardKey(){
		switch (SelectWorkLampStatus) {
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV1:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		case CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_LV2:
		default:
			SelectWorkLampStatus = CAN1CommManager.DATA_STATE_KEY_WORKLIGHT_OFF;
			CAN1Comm.Set_WorkLampOperationStatus_3435_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.Set_RearWorkLampOperationStatus_3446_PGN65527(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(247);
			
			break;
		}
	}
	
	public void ClickBeaconLampHardKey(){
		switch (SelectBeaconLamp) {
		case CAN1CommManager.DATA_STATE_OFF:
		default:
			SelectBeaconLamp = CAN1CommManager.DATA_STATE_ON;
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(CAN1CommManager.DATA_STATE_ON);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(3);
			break;
		case CAN1CommManager.DATA_STATE_ON:
			SelectBeaconLamp = CAN1CommManager.DATA_STATE_OFF;
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_BeaconLampOperationStatus_3444_PGN65527(3);
			break;
		}		
	}
	
	public void ClickRearWiperHardKey(){
		switch (SelectWiperSpeedState) {
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF:
		default:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_INT:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			break;
		case CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_LOW:
			SelectWiperSpeedState = CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF;
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(CAN1CommManager.DATA_STATE_KEY_REARWIPER_SPEED_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_RearWiperOperationStatus_3451_PGN65527(3);
			break;
		}
	}
	/////////////////////////////////////////////////////
//	public void showInputMachineSerial(){
//		if(MachineSerialNumber == 0xffffff)
//		{
//			_MainChangeAnimation.StartChangeAnimation(_InputMachineSerialFragment);
//		}
//		else
//		{
//			SetMachineSerialNumber();
//		}
//	}
	// ++, 150309 bwk
	public void showMainScreen(){
		if(DisplayType == DISPLAY_TYPE_A){
			_MainChangeAnimation.StartChangeAnimation(_MainBBaseFragment);
		}else{
			_MainChangeAnimation.StartChangeAnimation(_MainABaseFragment);
		}
	}	
	public void setScreenIndex(){
//		Log.d(TAG,"ScreenIndex="+Integer.toHexString(ScreenIndex));
		if(DisplayType == DISPLAY_TYPE_A){
			ScreenIndex = SCREEN_STATE_MAIN_B_TOP;
		}else{
			ScreenIndex = SCREEN_STATE_MAIN_A_TOP;
		}
//		Log.d(TAG,"ScreenIndex="+Integer.toHexString(ScreenIndex));
	}	
	// --, 150309 bwk
	// ++, 150331 bwk
	public void showMaintoKey(int Key){
		if(DisplayType == DISPLAY_TYPE_A){
			_MainChangeAnimation.StartChangeAnimation(_MainBBaseFragment);
			switch(Key){
				case CAN1CommManager.WORK_LOAD:
					_MainBBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD);
					break;
			}
		}else{
			_MainChangeAnimation.StartChangeAnimation(_MainABaseFragment);
			switch(Key){
				case CAN1CommManager.WORK_LOAD:
					_MainABaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD);
					break;
			}
		}
	}	
	// --, 150331 bwk
	//Main Screen Fragment///////////////////////////////
	public void showMainBFragment(){
		_MainBBaseFragment = new MainBBaseFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _MainBBaseFragment);
		transaction.commit();
		
	}
	public void showMainAFragment(){
		_MainABaseFragment = new MainABaseFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _MainABaseFragment);
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
	// Input Machine Serial Fragment
	public void showInputMachineSerialFragment(){
		_InputMachineSerialFragment = new InputMachineSerialFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _InputMachineSerialFragment);
		transaction.commit();
	}
	//Ending Screen Fragment
	public void showEndingFragment(){
		_EndingFragment = new EndingFragment();
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _EndingFragment);
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
			
			
			//CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_STARTCAN);
			
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
			if (Data == CAN1CommManager.OFF)
				return;
			
			Log.i(TAG,"KeyButton Callback : 0x" + Integer.toHexString(Data));
			if(Data == CAN1CommManager.FN){
				try {
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException");
				}
				//Log.d(TAG,"Click FN!!!");
			}
			// ++, 150323 cjg
			else if(Data == CAN1CommManager.ESC){
				try{
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				} catch (NullPointerException e){
					Log.e(TAG,"NullPointerException");
				}
				//Log.d(TAG, "Click ESC!!!");
			}
			// --, 150323 cjg
			else if(Data == CAN1CommManager.POWER_OFF){
				showEndingFragment();
				//allKillRunningApps("taeha.wheelloader.fseries_monitor.main");	// ++, --, 150326 cjg multimedia ending ���� 
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
			}
			else if(CAN1Comm.GetScreenTopFlag() == true){
				try {
					HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(Data));
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException");
				}
			}else if(Data == CAN1CommManager.MAINLIGHT){
				ClickMainLightHardKey();
			}else if(Data == CAN1CommManager.WORKLIGHT){
				ClickWorkLightHardKey();
			}else if(Data == CAN1CommManager.BEACON_LAMP){
				ClickBeaconLampHardKey();
			}else if(Data == CAN1CommManager.REAR_WIPER){
				ClickRearWiperHardKey();
			}else if(Data == CAN1CommManager.LEFT || Data == CAN1CommManager.RIGHT){
				if(ScreenIndex == SCREEN_STATE_MAIN_CAMERA_KEY){
					ChangeCam(Data);
				}
			}else if(Data == CAN1CommManager.ESC){
				if(ScreenIndex == SCREEN_STATE_MAIN_CAMERA_KEY){
					ExitCam();
				}
			}else if(Data == CAN1CommManager.CAMERA){
				if(CAN1Comm.CameraOnFlag ==  CAN1CommManager.STATE_CAMERA_OFF){
					ExcuteCamActivitybyKey();
				}else if(CAN1Comm.CameraOnFlag != CAN1CommManager.STATE_CAMERA_REVERSEGEAR){
					ExitCam();
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
			
			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_RTC,ComponentBasicInformation[0],ComponentBasicInformation[1],ComponentBasicInformation[2],0x01,Hour,Min,Sec,0x00);
		}

		@Override
		public void ASCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "ASCallBack");
			SaveASPhoneNumber();
		}

		@Override
		public void StopCommServiceCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "StopCommServiceCallBack");
			stopCommService();
		}

		@Override
		public void EEPRomCallBack(int Data) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "EEPRomCallBack : " + Integer.toString(Data));
		}

		@Override
		public void FlashCallBack(int Data) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "FlashCallBack : " + Integer.toString(Data));
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
	
	public  class LoadingThread implements Runnable {
		private WeakReference<Home> activityRef = null;
		public Message msg = null;
		public LoadingThread(Home activity){
			this.activityRef = new WeakReference<Home>(activity);
			msg = new Message();
		}

		
		@Override
		public void run() {
			try{
				Log.d(TAG,"LoadingThread Start");
				LoadingEndingAnimation();
				Log.d(TAG,"LoadingThread End");
			}	
			catch(RuntimeException ee){
				Log.e(TAG,"RuntimeException");
			}
		}
	}
	
	public void LoadingEndingAnimation(){
		imgViewEnding.setBackgroundResource(R.drawable.endinganimation);         
		EndingAnimation = (AnimationDrawable) imgViewEnding.getBackground();
	}
	public void StartEndingAnimation(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, null);
		transaction.commit();
		
		imgViewEnding.setVisibility(View.VISIBLE);
		EndingAnimation.start();
	}
	public void GetDataFromNative(){
		PreHeat = CAN1Comm.Get_MirrorHeaterStatus_724_PGN65428();
		//RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		Buzzer = CAN1Comm.Get_Buzzer_723_PGN65364();
		SelectGear = CAN1Comm.Get_SelectGear_541_PGN65434();
		SelectGearRange = SelectGear & 0x0F;
		SelectGearDirection = ((SelectGear & 0x30) >> 4);
		
		EngineAutoShutdownRemainingTime = CAN1Comm.Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122();
		EngineAutoShutdownMode = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN61184_122();
		
		JoystickSteeringEnableFailCondition = CAN1Comm.Get_JoystickSteeringEnableFailCondition_2343_PGN65524();
		JoystickSteeringActiveStatus = CAN1Comm.Get_JoystickSteeringActiveStatusEHCU_186_PGN65517();

		FrontAxleTempWarning = CAN1Comm.Get_Front_Axle_Oil_Temp_Warning_580_PGN65449();
		RearAxleTempWarning = CAN1Comm.Get_Rear_Axle_Oil_Temp_Warning_581_PGN65449();

		Year = CAN1Comm.Get_RTColock_Year();
		Month = CAN1Comm.Get_RTColock_Month();
		Date = CAN1Comm.Get_RTColock_Date();
		Hour = CAN1Comm.Get_RTColock_Hour();
		Min = CAN1Comm.Get_RTColock_Min();
		Sec = CAN1Comm.Get_RTColock_Sec();

		_CrashApplication.SetYear(Year);
		_CrashApplication.SetMonth(Month);
		_CrashApplication.SetDate(Date);
		_CrashApplication.SetHour(Hour);
		_CrashApplication.SetMin(Min);
		_CrashApplication.SetSec(Sec);
		
		SetBackLight();
		
		// ++, 150326 bwk
		if(SendDTCIndex > REQ_ERR_START && SendDTCIndex < Home.REQ_ERR_END)
		{
			ReqestErrorCode();
		}
		// --, 150326 bwk
		
		HeadLamp = CAN1Comm.Get_HeadLampOperationStatus_3436_PGN65527();
		Illumination = CAN1Comm.Get_IlluminationOperationStatus_3438_PGN65527();
		WorkLamp = CAN1Comm.Get_WorkLampOperationStatus_3435_PGN65527();
		RearWorkLamp = CAN1Comm.Get_RearWorkLampOperationStatus_3446_PGN65527();
		SelectBeaconLamp = CAN1Comm.Get_BeaconLampOperationStatus_3444_PGN65527();
		SelectWiperSpeedState = CAN1Comm.Get_RearWiperOperationStatus_3451_PGN65527();

		MainLightLampStatus(HeadLamp,Illumination);
		WorkLightLampDisplay(WorkLamp,RearWorkLamp);
		
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
				CheckEHCUErr(JoystickSteeringEnableFailCondition, JoystickSteeringActiveStatus);
				//Checkrpm(RPM);		// ++, --, 150211 bwk
				CheckFNLed();
				CheckAxleTempWarning(FrontAxleTempWarning, RearAxleTempWarning);
			}
		});
	}

	
	public void CheckBuzzer(){
		if(ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2
		|| ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2
		// ++, 150313 bwk
		|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2
		|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2){
		// --, 150313 bwk
			
		}else if((ScreenIndex >= SCREEN_STATE_MAIN_CAMERA_TOP && ScreenIndex <= SCREEN_STATE_MAIN_CAMERA_END)
			&&(OldScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2
					|| OldScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2
					|| OldScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2
					|| OldScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2)){
			
		}else{
			if(BuzzerStopCount > 5){
				if(CAN1Comm.Get_CommErrCnt() < 1000){
					if(Buzzer == CAN1CommManager.BUZZER_ON){
						BuzzerOnFlag = true;
						
						if(CAN1Comm.BuzzerStatus == CAN1CommManager.BUZZER_OFF || CAN1Comm.BuzzerStatus == CAN1CommManager.BUZZER_STOP){
							CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_BUZ, CAN1CommManager.BUZZER_ON);	// Buzzer On
							if(CAN1Comm.GetScreenTopFlag() == false)
							{
								Log.d(TAG,"GetScreenTopFlag() false CheckBuzzer");
								CAN1Comm.ClickFN_Buzzer();
							}
						}
					}else if(Buzzer == CAN1CommManager.BUZZER_OFF){
						if(CAN1Comm.BuzzerStatus == CAN1CommManager.BUZZER_ON){
							CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_BUZ, CAN1CommManager.BUZZER_OFF);	// Buzzer Off
							CAN1Comm.BuzzerStatus = CAN1CommManager.BUZZER_STOP;
						}
					}			
				}
			}else{
				BuzzerStopCount++;
			}
		}
		
		
	}
	public void CameraDisplay(){
		if((ScreenIndex >= SCREEN_STATE_MAIN_B_TOP && ScreenIndex <= SCREEN_STATE_MAIN_B_END)
		|| (ScreenIndex >= SCREEN_STATE_MAIN_A_TOP && ScreenIndex <= SCREEN_STATE_MAIN_A_END)	// ++, --, 150310 bwk
		|| (ScreenIndex >= SCREEN_STATE_MENU_TOP   && ScreenIndex <= SCREEN_STATE_MENU_END)
		|| (ScreenIndex == SCREEN_STATE_MAIN_CAMERA_GEAR)){
			if(CameraReverseMode == CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON){
				if(SelectGearDirection == CAN1CommManager.DATA_INDEX_SELECTGEAR_DIR_R){	
					CameraReverseOffCount = 0; 
					if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_OFF
					|| CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL){
						CameraReverseOnCount++;
						
					}
					else{
						CameraReverseOnCount = 0;
					}
					if(CameraReverseOnCount >= 2){
						TempScreenIndex = OldScreenIndex;	// ++, --, 151110 cjg
						OldScreenIndex = ScreenIndex;
						ScreenIndex = SCREEN_STATE_MAIN_CAMERA_GEAR;
						CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_REVERSEGEAR;
						CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_CAM, CameraOrder1);
						CAN1Comm.CameraCurrentOnOff = true;
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
					
					if(CameraReverseOffCount >= 2){
						CAN1Comm.CameraOnFlag = CAN1CommManager.STATE_CAMERA_OFF;
						ScreenIndex = OldScreenIndex;
						OldScreenIndex = TempScreenIndex;
						CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_CAM, 0xFF);
						CAN1Comm.CameraCurrentOnOff = false;
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
		
	}
	/////////////////////////////////////////////////////
	//FAULT CODE//////////////////////////////////////////
	// ++, 150326 bwk
	public void RequestErrorCode(int Err, int Req, int SeqNo){
		CAN1Comm.Set_DTCInformationRequest_1515_PGN61184_11(Req);
		CAN1Comm.Set_DTCType_1510_PGN61184_11(Err);
		CAN1Comm.Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(SeqNo);
		CAN1Comm.TxCANToMCU(11);
	}
	public void ReqestErrorCode(){
		switch (SendDTCIndex) {
		case REQ_ERR_MACHINE_ACTIVE:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = REQ_ERR_END;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case REQ_ERR_ENGINE_ACTIVE:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = REQ_ERR_MACHINE_ACTIVE;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case REQ_ERR_TM_ACTIVE:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = REQ_ERR_ENGINE_ACTIVE;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case REQ_ERR_EHCU_ACTIVE:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = REQ_ERR_TM_ACTIVE;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	// --, 150326 bwk	
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
		if(CAN1Comm.GetScreenTopFlag() == false)
		{
			if(EngineAutoShutdownMode == 1 && EngineAutoShutdownRemainingTime <= 60)
			{
				Log.d(TAG,"GetScreenTopFlag() false CheckEngineAutoShutdown");
				CAN1Comm.ClickFN_Home();
			}
		}
		else if(ScreenIndex != SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP
			&& ScreenIndex != SCREEN_STATE_MAIN_ENDING
			&& EngineAutoShutdownMode == 1
			&& EngineAutoShutdownRemainingTime <= 60){
			
			if(ScreenIndex == SCREEN_STATE_AXLE_POPUP)
				_AxleTempWarningPopup.ClickCancel();
			else if(ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3
					|| ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3)
				_QuickCouplerPopupUnlocking3.ClickOK();
			else if(ScreenIndex == SCREEN_STATE_EHCUERR_POPUP)
			{
				Log.d(TAG, "CheckEngineAutoShutdown _EHCUErrorPopup.dismiss()");
				_EHCUErrorPopup.dismiss();
			}
			
			//OldScreenIndex = ScreenIndex;
			showEngineAutoShutdownCount();
			FrontAxleWarningFlag = false;
			RearAxleWarningFlag = false;

			OldJoystickSteeringEnableFailCondition = 0xffff;
			bEHCUErrPopup = false;
			_EHCUErrorPopup.Safety_CPU_Error = 0;
			
		}else if(ScreenIndex == SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP){
			if( EngineAutoShutdownMode == 0
					|| EngineAutoShutdownRemainingTime > 60){

				if(HomeDialog != null){
					HomeDialog.dismiss();
					ScreenIndex = OldScreenIndex;
					//Log.d(TAG,"CheckEngineAutoShutdown Dismiss : ");
					HomeDialog = null;
				}
			}
		}else if(ScreenIndex == SCREEN_STATE_MAIN_ENDING){
			if(HomeDialog != null){
				HomeDialog.dismiss();
				HomeDialog = null;
			}
		}
	}
	public void CheckAttachmentUnlock(){
		// ++, 150310 bwk
		//if(ScreenIndex == SCREEN_STATE_MAIN_B_TOP){
		if(ScreenIndex == SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP || ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3
				|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3 || ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2
				|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2 || ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2
				|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2)
			{
			}
		else if(ScreenIndex == SCREEN_STATE_MAIN_B_TOP || ScreenIndex == SCREEN_STATE_MAIN_A_TOP){
		// --, 150310 bwk
			if((FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON)
					|| (RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON)
					|| AxleWarningFlag == true)
			{
				// AxleTempWarning�� ��� �̰� �߰� �ٷ� ���â �߹Ƿ�, PASS
			}
			else if(AttachmentStatus == CAN1CommManager.DATA_STATE_KEY_QUICKCOUPLER_UNLOCK){
				OldScreenIndex = ScreenIndex;
				showQuickCouplerPopupUnlocking3();
			}
		}else if(ScreenIndex == SCREEN_STATE_MAIN_ENDING){
			if(HomeDialog != null){
				HomeDialog.dismiss();
				HomeDialog = null;
			}
		}
	}
	public void CheckEHCUErr(int Data, int PopupOff){
		// ++, 150209 bwk
		/*
		if(JoystickSteeringEnableFailCondition != 0xFFFF
				&& JoystickSteeringEnableFailCondition != 0x0000){
					if(bEHCUErrPopup == false){
						if(JoystickSteeringEnableFailCondition != 0){
							if(JoystickSteeringEnableFailCondition != OldJoystickSteeringEnableFailCondition){
								OldJoystickSteeringEnableFailCondition = JoystickSteeringEnableFailCondition;
								OldScreenIndex = ScreenIndex;
								showEHCUErr();
							}
						}
						
					}
				}
		*/
//		if(bEHCUErrPopup == false)
//		{
//			if(JoystickSteeringEnableFailCondition == 0xFFFF || JoystickSteeringEnableFailCondition == 0x0000)
//			{
//				OldJoystickSteeringEnableFailCondition = JoystickSteeringEnableFailCondition;
//				bEHCUErrPopup = false;
//			}
//			else if(JoystickSteeringEnableFailCondition != 0xFFFF && JoystickSteeringEnableFailCondition != 0x0000){
//				if(bEHCUErrPopup == false){
//					if(JoystickSteeringEnableFailCondition != 0){
//						if(JoystickSteeringEnableFailCondition != OldJoystickSteeringEnableFailCondition){
//							OldJoystickSteeringEnableFailCondition = JoystickSteeringEnableFailCondition;
//							OldScreenIndex = ScreenIndex;
//							showEHCUErr();
//						}
//					}
//					
//				}
//			}
//		}
		if(ScreenIndex == SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP || ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2
			|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2 || ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2
			|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2)
		{
		}
		else if(bEHCUErrPopup == false)
		{
			if(Data == 0xFFFF || Data == 0x0000)
			{
				OldJoystickSteeringEnableFailCondition = Data;
				bEHCUErrPopup = false;
			}
			else if(Data != 0xFFFF && Data != 0x0000 && PopupOff != 1){
				if(bEHCUErrPopup == false){
					if(CAN1Comm.GetScreenTopFlag() == false)
					{
						if(Data != 0 && Data != OldJoystickSteeringEnableFailCondition)
						{
							Log.d(TAG,"GetScreenTopFlag() false showEHCUErr");
							CAN1Comm.ClickFN_Home();
						}
					}
					else if(Data != 0){
						if(Data != OldJoystickSteeringEnableFailCondition){
							OldJoystickSteeringEnableFailCondition = Data;
							if(ScreenIndex == SCREEN_STATE_AXLE_POPUP)
								_AxleTempWarningPopup.ClickCancel();
							else if(ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3
									|| ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3)
								_QuickCouplerPopupUnlocking3.ClickOK();
							//OldScreenIndex = ScreenIndex;
							showEHCUErr();
						}
					}
					
				}
			}		
		}
		else if(ScreenIndex == SCREEN_STATE_MAIN_ENDING){
			if(HomeDialog != null){
				HomeDialog.dismiss();
				HomeDialog = null;
			}
		}
		// --, 150209 bwk
		
	}
	
	public void CheckFNLed(){
		
		if(CAN1Comm.GetMultimediaFlag() == true)
		{
			CAN1Comm.CheckMultimedia();
		}
		
		if(CAN1Comm.GetMiracastFlag() == true)
		{
			CAN1Comm.CheckMiracast();
		}		
	}
	
	// ++, 150211 bwk
	/*
	public void Checkrpm(int Data){
		if(CAN1Comm.GetMultimediaFlag() == true && PressFnKey == 0)
		{
//			Log.d(TAG, "Player On");
			PressFnKey = 1;
		}
		else if(CAN1Comm.GetMultimediaFlag() == false && PressFnKey == 1)
		{
//			Log.d(TAG, "Player OFF");
			PressFnKey = 0;
		}

		if(PressFnKey == 1)
		{
			if(Data == 0xFFFF)
				Data = 0;
			else if(Data > 9999)
				Data = 9999;
						
			if(Data >= 1500)
			{
				if(HighrpmCount < 20)
					HighrpmCount++;
				LowrpmCount = 0;
			}
			else
			{
				if(LowrpmCount < 20)
					LowrpmCount++;
				HighrpmCount = 0;
			}
			
			if(HighrpmCount >= 20 && HighrpmCount != 30)
			{
				CAN1Comm.ChangeMediatoHome();
				HighrpmCount = 30;
			}
			else if(LowrpmCount >= 20 && LowrpmCount != 30)
			{
				CAN1Comm.ChangeHometoMedia();
				LowrpmCount = 30;
			}
		}
		else
		{
			HighrpmCount = 0;
			LowrpmCount = 0;
		}
		
	}
	// --, 150210 bwk
	 */
	/////////////////////////////////////////////////////
	public void CheckAxleTempWarning(int _FrontAxleTempWarning, int _RearAxleTempWarning){
		if(CAN1Comm.GetScreenTopFlag() == false)
		{
			if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (FrontAxleWarningFlag == false))
					|| ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (RearAxleWarningFlag == false)))
			{
				Log.d(TAG,"GetScreenTopFlag() false AxleTempWarningPopup");
				if(ScreenIndex != SCREEN_STATE_MAIN_B_TOP && ScreenIndex != SCREEN_STATE_MAIN_A_TOP){
					Log.d(TAG,"No Mian!!!!! Move to Main");
					
					if(((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_B_TOP) || 
							((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_A_TOP))
					{
						if ((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x06000000) {
							Log.d(TAG, "BackHomeKey!!!");
							if (DisplayType == DISPLAY_TYPE_A) {
								_MainBBaseFragment.showKeytoDefaultScreenAnimation();
							} else {
								_MainABaseFragment.showKeytoDefaultScreenAnimation();
							}
						} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x01000000){
							Log.d(TAG, "BackHomeRightUp!!!");
							if (DisplayType == DISPLAY_TYPE_A) {
								_MainBBaseFragment.showRightUptoDefaultScreenAnimation();
							} else {
								_MainABaseFragment.showRightUptoDefaultScreenAnimation();
							}
						} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x02000000){
							Log.d(TAG, "BackHomeRightDown!!!");
							if (DisplayType == DISPLAY_TYPE_A) {
								_MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
							} else {
								_MainABaseFragment.showRightDowntoDefaultScreenAnimation();
							}
						} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x03000000){
							Log.d(TAG, "BackHomeLeftUp!!!");
							if (DisplayType == DISPLAY_TYPE_A) {
								_MainBBaseFragment.showLeftUptoDefaultScreenAnimation();
							} else {
								_MainABaseFragment.showLeftUptoDefaultScreenAnimation();
							}
						} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x04000000){
							Log.d(TAG, "BackHomeLeftDown!!!");
							if (DisplayType == DISPLAY_TYPE_A) {
								_MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
							} else {
								_MainABaseFragment.showLeftDowntoDefaultScreenAnimation();
							}
						} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x05000000){
							Log.d(TAG, "BackHomeQucik!!!");
							if (DisplayType == DISPLAY_TYPE_A) {
								_MainBBaseFragment.showQuicktoDefaultScreenAnimation();
							} else {
								_MainABaseFragment.showDefaultScreenAnimation();
							}
						}
					}else{
						Log.d(TAG, "BackNoHome!!!"+Integer.toHexString(ScreenIndex));
						showMainScreen();
						setScreenIndex();
					}
				}
				
				CAN1Comm.ClickFN_Home();
			}
			else if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
					|| ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true)))
			{
				Log.d(TAG,"showAxleTempWarningPopupInit");
				if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
						&& ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true)))
				{
					FrontAxleWarningFlag = false;
					RearAxleWarningFlag = false;
				}
				else if((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
					FrontAxleWarningFlag = false;
				else if((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true))
					RearAxleWarningFlag = false;
			}

		}
		else if(ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3 
				|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3
				|| (ScreenIndex == SCREEN_STATE_EHCUERR_POPUP && _EHCUErrorPopup.Safety_CPU_Error != 1)
				|| ScreenIndex == SCREEN_STATE_MAIN_B_TOP || ScreenIndex == SCREEN_STATE_MAIN_A_TOP){
			{
				if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (FrontAxleWarningFlag == false))
				   || ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (RearAxleWarningFlag == false)))
				{
					if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (FrontAxleWarningFlag == false))
							   && ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (RearAxleWarningFlag == false)))
					{
						FrontAxleWarningFlag = true;
						RearAxleWarningFlag = true;
						
						if((MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE)
								&& (MachineStatusLowerIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE))
						{
							if(MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE)
								MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE;
							else 
								MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE;
						}
						
						if((MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE)
								&& (MachineStatusLowerIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE))
						{
							if(MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE)
								MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE;
							else 
								MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE;
						}
					}
					else if((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (FrontAxleWarningFlag == false))
					{
						FrontAxleWarningFlag = true;

						if((MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE)
								&& (MachineStatusLowerIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE))
						{
							if(MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING)
							{
								MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE;
								MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
							}
							else
							{
								if(MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE)
									MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE;
								else 
									MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE;
							}
						}

					}
					else if((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_ON) && (RearAxleWarningFlag == false))
					{
						RearAxleWarningFlag = true;
						
						if((MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE)
								&& (MachineStatusLowerIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE))
						{
							if(MachineStatusUpperIndex == CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING)
							{
								MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE;
								MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
							}
							else
							{
								if(MachineStatusUpperIndex != CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE)
									MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE;
								else 
									MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE;
							}
						}
						
					}
					if(ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3 
							|| ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3
							|| (ScreenIndex == SCREEN_STATE_EHCUERR_POPUP && _EHCUErrorPopup.Safety_CPU_Error != 1)){
						if(ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3
								|| ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3)
							_QuickCouplerPopupUnlocking3.ClickOK();
						else if((ScreenIndex == SCREEN_STATE_EHCUERR_POPUP) && 
								((OldScreenIndex == SCREEN_STATE_MAIN_B_TOP) || (OldScreenIndex == SCREEN_STATE_MAIN_A_TOP)))
							_EHCUErrorPopup.dismiss();
					}
					
					if(ScreenIndex == SCREEN_STATE_MAIN_B_TOP || ScreenIndex == SCREEN_STATE_MAIN_A_TOP){
						Log.d(TAG,"showAxleTempWarningPopup");
						showAxleTempWarningPopup();
					}else{
						FrontAxleWarningFlag = false;
						RearAxleWarningFlag = false;
					}
				}
				else if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
						   || ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true)))
				{
					Log.d(TAG,"showAxleTempWarningPopupInit");
					if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
							   && ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true)))
					{
						FrontAxleWarningFlag = false;
						RearAxleWarningFlag = false;
					}
					else if((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
						FrontAxleWarningFlag = false;
					else if((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true))
						RearAxleWarningFlag = false;
				}
			}
		}else if(ScreenIndex != SCREEN_STATE_MAIN_ENDING){
			if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
					   || ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true)))
			{
				Log.d(TAG,"showAxleTempWarningPopupInit");
				if(((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
						   && ((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true)))
				{
					FrontAxleWarningFlag = false;
					RearAxleWarningFlag = false;
				}
				else if((_FrontAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (FrontAxleWarningFlag == true))
					FrontAxleWarningFlag = false;
				else if((_RearAxleTempWarning  == CAN1CommManager.DATA_STATE_LAMP_OFF) && (RearAxleWarningFlag == true))
					RearAxleWarningFlag = false;
			}
		}
		else if(ScreenIndex == SCREEN_STATE_MAIN_ENDING){
			if(HomeDialog != null){
				HomeDialog.dismiss();
				HomeDialog = null;
			}
		}
	}

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
				HandleKeyButton.sendMessage(HandleKeyButton.obtainMessage(CAN1CommManager.MENU));
			}
		}
		//return super.dispatchKeyEvent(event);
		return true;
	}
	
	public void KeyButtonClick(final int Data){
		if(ScreenIndex == 0){
			Log.d(TAG, "KeyButtonClick:NULL");
			return;
		}
		Log.d(TAG,"KeyButtonClick : ScreenIndex"+Integer.toHexString(ScreenIndex));
		// TODO Auto-generated method stub
		if(ScreenIndex == SCREEN_STATE_MAIN_CAMERA_KEY){
			if(Data == CAN1CommManager.CAMERA || Data == CAN1CommManager.ESC){
				ExitCam();
			}
			// ++, 150324 bwk
			else if(Data == CAN1CommManager.LEFT || Data == CAN1CommManager.RIGHT){
				ChangeCam(Data);
			}
			// --, 150324 bwk
		// ++, 150310 bwk
		}else if(ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING2
				||	 ScreenIndex == SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING2
				||	 ScreenIndex	== SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_UNLOCKING3){
			if(Data == CAN1CommManager.CAMERA){
				ExcuteCamActivitybyKey();
			}
			Log.d(TAG,"Click QuickCoupler Key");	
		} else if (ScreenIndex == SCREEN_STATE_AXLE_POPUP || AxleWarningFlag == true) {
			Log.d(TAG, "Click Key - AxleWarning");
			 _AxleTempWarningPopup.KeyButtonClick(Data);
		} else if((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_A_TOP){
			Log.d(TAG,"Click Main A Key");
			_MainABaseFragment.KeyButtonClick(Data);
		// --, 150310 bwk
		}else if(ScreenIndex == SCREEN_STATE_MAIN_CAMERA_GEAR){
			
		}else if(ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2
			||	 ScreenIndex == SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2
			||	 ScreenIndex	== SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING3){
			if(Data == CAN1CommManager.CAMERA){
				ExcuteCamActivitybyKey();
			}
			Log.d(TAG,"Click QuickCoupler Key");
		// ++, 151110 cjg Menu ������ �̵�
//		}else if(ScreenIndex == SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT1){
//			Log.d(TAG,"Click WeighingInit1 Key");
//			_WorkLoadWeighingInitPopup1.KeyButtonClick(Data);
//		}else if(ScreenIndex == SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT2){
//			Log.d(TAG,"Click WeighingInit2 Key");
//			_WorkLoadWeighingInitPopup2.KeyButtonClick(Data);
		// --, 151110 cjg
		}else if((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_B_TOP){
			Log.d(TAG,"Click Main B Key");
			_MainBBaseFragment.KeyButtonClick(Data);
		}else if((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_ENDING){
			Log.d(TAG,"Click Ending Key");
			
		}else if((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MENU_TOP){
			Log.d(TAG,"Click Menu Key");
			_MenuBaseFragment.KeyButtonClick(Data);
		}else if(ScreenIndex == SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP){
			Log.d(TAG,"Click EngineAutoShutdownCount Key");
			_EngineAutoShutdownCountPopup.KeyButtonClick(Data);
		}else if(ScreenIndex == SCREEN_STATE_MAIN_ESL_PASSWORD){
			Log.d(TAG, "Click ESL Password Key");
			_ESLPasswordFragment.KeyButtonClick(Data);
		}else if(ScreenIndex == SCREEN_STATE_MAIN_CHECK_MACHINE_SERIAL){
			Log.d(TAG, "Click InputMachineSerialFragment Key");
			_InputMachineSerialFragment.KeyButtonClick(Data);
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
	public void showQuickCouplerPopupUnlocking3(){
//		if(AnimationRunningFlag == true)
//			return;
//		else
//			StartAnimationRunningTimer();
//		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _QuickCouplerPopupUnlocking3;
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
	public void showICCoMode(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _ICCOModePopup;
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
		//_SpeedometerInitPopup = new SpeedometerInitPopup(this);
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
		//_OperationHistoryInitPopup = new OperationHistoryInitPopup(this);
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
//		if(AnimationRunningFlag == true)
//			return;
//		else
//			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		OldScreenIndex = ScreenIndex;
	//	Log.d(TAG, "showEngineAutoShutdownCount:OldScreenIndex"+Integer.toHexString(OldScreenIndex));

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
	public void showWorkLoadInit(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _WorkLoadInitPopup;
		HomeDialog.show();
	}
	public void showWorkLoadWeighingInit1(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _WorkLoadWeighingInitPopup1;
		HomeDialog.show();
	}
	public void showWorkLoadWeighingInit2(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _WorkLoadWeighingInitPopup2;
		HomeDialog.show();
	}
	public void showEHCUErr(){
//		if(AnimationRunningFlag == true)
//			return;
//		else
//			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		OldScreenIndex = ScreenIndex;

		HomeDialog = _EHCUErrorPopup;
		HomeDialog.show();
	}
	public void showSoftStopInit(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _SoftStopInitPopup;
		HomeDialog.show();
	}
	// ++, 150313 cjg
	public void showMultiClose(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		_MultimediaClosePopup = new MultimediaClosePopup(this);
		HomeDialog = _MultimediaClosePopup;
		HomeDialog.show();
	}
	public void showMiraClose(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		_MiracastClosePopup = new MiracastClosePopup(this);
		HomeDialog = _MiracastClosePopup;
		HomeDialog.show();
	}
	// --, 150313 cjg	 
	public void showFuelInitalPopup(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _FuelInitalPopup;
		HomeDialog.show();
	}
	public void showAxleTempWarningPopup(){
//		if(AnimationRunningFlag == true)
//			return;
//		else
//			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			//Log.d(TAG, "Dismiss()"+Integer.toHexString(ScreenIndex));
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		OldScreenIndex = ScreenIndex;
		//Log.d(TAG, "showAxleTempWarningPopup:OldScreenIndex"+Integer.toHexString(OldScreenIndex));

		HomeDialog = _AxleTempWarningPopup;
		HomeDialog.show();
	}
	public void showCalibrationEHCUPopup(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
	
		HomeDialog = _CalibrationEHCUPopup;
		HomeDialog.show();
	}
	public void showSoftwareUpdateErrorPopup(){
		if(AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();
		
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
	
		HomeDialog = _SoftwareUpdateErrorPopup;
		HomeDialog.show();
	}
	
	public void showLanguageChangePopup() {
		if (AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();

		if (HomeDialog != null) {
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _LanguageChangePopup;
		HomeDialog.show();
	}
	public void showFanSelectModePopup() {
		if (AnimationRunningFlag == true)
			return;
		else
			StartAnimationRunningTimer();

		if (HomeDialog != null) {
			HomeDialog.dismiss();
			HomeDialog = null;
		}

		HomeDialog = _FanSelectModePopup;
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
					SmartIconDisplay = false;	// ++, 150326 bwk
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
	
	public void StartSendCIDTimer(){
		CIDTimerCount = 0;
		CancelSendCIDTimer();
		mSendCIDTimer = new Timer();
		mSendCIDTimer.schedule(new SendCIDTimerClass(),1,1000);
	}
	
	public void CancelSendCIDTimer(){
		if(mSendCIDTimer != null){
			mSendCIDTimer.cancel();
			mSendCIDTimer.purge();
			mSendCIDTimer = null;
		}
		
	}
	
	public class SendCIDTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG, "CIDTimerCount"+CIDTimerCount);
			if(++CIDTimerCount > 10){
				CheckCID();
				CancelSendCIDTimer();
			}else{
				SendCID();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			Log.d(TAG,"mSendCommandTimer"+nSendCommandTimerIndex);	
						
			try {
				if(nSendCommandTimerIndex == 0){
					setSoundOutput(SoundState);
					//CAN1Comm.setVolume(InternalSoundLevel);		// ++, --, 150324 bwk
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
				}else if(nSendCommandTimerIndex == 3){
					CAN1Comm.Set_SettingSelection_PGN61184_105(0xF);
					CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
					CAN1Comm.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(0xF);
					CAN1Comm.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(0xF);
					CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
					CAN1Comm.TxCANToMCU(105);
					CAN1Comm.Set_SettingSelection_PGN61184_105(15);
				}else if (nSendCommandTimerIndex == 4){
					CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(0); //STATE_WEIGHT_OFFSET_SETTING_CALL
					CAN1Comm.TxCANToMCU(62);
					CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(3);
					CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
				}else if (nSendCommandTimerIndex == 5){
					CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_VERSION,1);
				}else if(nSendCommandTimerIndex == 6){
					CAN1Comm.Set_AutomaticEngineShutdown_363_PGN61184_121(3);
					CAN1Comm.Set_AutomaticEngineShutdownTypeControlByte_PGN61184_121(3);
					CAN1Comm.Set_EngineShutdownCotrolByte_PGN61184_121(0xF);
					CAN1Comm.Set_SettingTimeforAutomaticEngineShutdown_364_PGN61184_121(0xFF);
					CAN1Comm.TxCANToMCU(121);
				}else if(nSendCommandTimerIndex == 7){
					CAN1Comm.Set_SpeedmeterUnitChange_PGN65327(UnitOdo);
					CAN1Comm.TxCANToMCU(47);
					CAN1Comm.Set_SpeedmeterUnitChange_PGN65327(3);
				}
				else if(nSendCommandTimerIndex == 8){
					//SendDTCIndex = REQ_ERR_TM_ACTIVE;
					SendDTCIndex = REQ_ERR_EHCU_ACTIVE;
					CancelSendCommandTimer();
					StartSendCIDTimer();
					//SendCID();
				}
				else {
	//				SendCID();
	//				if(nSendCommandTimerIndex >= 27)
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
				if(CAN1Comm.GetScreenTopFlag() == false)
				{
					Log.d(TAG,"GetScreenTopFlag() false CommErrStopTimerClass");
					CAN1Comm.ClickFN_Buzzer();
				}
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
	
	
	public class MirrorHeatTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					MirrorHeatTimerCount++;
					if(MirrorHeatTimerCount > 10){
						CancelMirrorHeatTimer();
						CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
					}else{
						CAN1Comm.TxCANToMCU(247);
					}
				}
			});
			
		}
		
	}
	
	public void StartMirrorHeatTimer(){
		MirrorHeatTimerCount = 0;
		CancelMirrorHeatTimer();
		mMirrorHeatTimer = new Timer();
		mMirrorHeatTimer.schedule(new MirrorHeatTimerClass(),1,100);	
	}
	
	public void CancelMirrorHeatTimer(){
		if(mMirrorHeatTimer != null){
			mMirrorHeatTimer.cancel();
			mMirrorHeatTimer.purge();
			mMirrorHeatTimer = null;
		}
		
	}
	
	public class AutoGreaseTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					AutoGreaseTimerCount++;
					if(AutoGreaseTimerCount > 10){
						CancelAutoGreaseTimer();
						CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(3);
					}else{
						CAN1Comm.TxCANToMCU(247);
					}
				}
			});
			
		}
		
	}
	
	public void StartAutoGreaseTimer(){
		AutoGreaseTimerCount = 0;
		CancelAutoGreaseTimer();
		mAutoGreaseTimer = new Timer();
		mAutoGreaseTimer.schedule(new AutoGreaseTimerClass(),1,100);	
	}
	
	public void CancelAutoGreaseTimer(){
		if(mAutoGreaseTimer != null){
			mAutoGreaseTimer.cancel();
			mAutoGreaseTimer.purge();
			mAutoGreaseTimer = null;
		}
		
	}
	
	public class CheckMultimediaTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					CAN1Comm.CheckMultimedia();
					if(CAN1Comm.GetMultimediaFlag() == true){
						CancelCheckMultimediaTimer();
					}
				}
			});
			
		}
		
	}
	
	public void StartCheckMultimediaTimer(){
		CancelCheckMultimediaTimer();
		mCheckMultimediaTimer = new Timer();
		mCheckMultimediaTimer.schedule(new CheckMultimediaTimerClass(),1,200);	
	}
	
	public void CancelCheckMultimediaTimer(){
		Log.d(TAG,"CancelCheckMultimediaTimer()");
		if(mCheckMultimediaTimer != null){
			mCheckMultimediaTimer.cancel();
			mCheckMultimediaTimer.purge();
			mCheckMultimediaTimer = null;
		}
		
	}
	public class CheckSmartTerminalTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					CAN1Comm.CheckMiracast();
					if(CAN1Comm.GetMiracastFlag() == true){
						CancelCheckSmartTerminalTimer();
					}
				}
			});
			
		}
		
	}
	
	public void StartCheckSmartTerminalTimer(){
		CancelCheckSmartTerminalTimer();
		mCheckSmartTerminalTimer = new Timer();
		mCheckSmartTerminalTimer.schedule(new CheckSmartTerminalTimerClass(),1,200);	
	}
	
	public void CancelCheckSmartTerminalTimer(){
//		Log.d(TAG,"CancelCheckSmartTerminalTimer()");
		if(mCheckSmartTerminalTimer != null){
			mCheckSmartTerminalTimer.cancel();
			mCheckSmartTerminalTimer.purge();
			mCheckSmartTerminalTimer = null;
		}
		
	}

	public class BackHomeTimerClass extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (BackHomeCount == 0xff) {
						CancelBackHomeTimer();
					} else if(CAN1Comm.GetScreenTopFlag() == false){
						BackHomeCount = 0;
					} else if ((ScreenIndex == SCREEN_STATE_MAIN_B_TOP)
							|| (ScreenIndex == SCREEN_STATE_MAIN_A_TOP)) {
						CancelBackHomeTimer();
					} else if(((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_A_TOP)
							|| ((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_B_TOP)) {
						if(((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x06000000) &&
							(((ScreenIndex & SCREEN_STATE_MAIN_KEY_FILTER) == 0x00400000) || ((ScreenIndex & SCREEN_STATE_MAIN_KEY_FILTER) == 0x00500000)
									|| ((ScreenIndex & SCREEN_STATE_MAIN_KEY_FILTER) == 0x00600000) || ((ScreenIndex & SCREEN_STATE_MAIN_KEY_FILTER) == 0x00A00000)))
						{
							CancelBackHomeTimer();
							BackHomeCount = 0;
						}
						else {
							if (BackHomeCount++ >= 10) {
								if ((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x06000000) {
									Log.d(TAG, "BackHomeKey!!!");
									if (DisplayType == DISPLAY_TYPE_A) {
										_MainBBaseFragment.showKeytoDefaultScreenAnimation();
									} else {
										_MainABaseFragment.showKeytoDefaultScreenAnimation();
									}
								} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x01000000){
									Log.d(TAG, "BackHomeRightUp!!!");
									SavePref();
									if (DisplayType == DISPLAY_TYPE_A) {
										_MainBBaseFragment.showRightUptoDefaultScreenAnimation();
									} else {
										_MainABaseFragment.showRightUptoDefaultScreenAnimation();
									}
								} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x02000000){
									Log.d(TAG, "BackHomeRightDown!!!");
									SavePref();
									if (DisplayType == DISPLAY_TYPE_A) {
										_MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
									} else {
										_MainABaseFragment.showRightDowntoDefaultScreenAnimation();
									}
								} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x03000000){
									Log.d(TAG, "BackHomeLeftUp!!!");
									SavePref();
									if (DisplayType == DISPLAY_TYPE_A) {
										_MainBBaseFragment.showLeftUptoDefaultScreenAnimation();
									} else {
										_MainABaseFragment.showLeftUptoDefaultScreenAnimation();
									}
								} else if((ScreenIndex & SCREEN_STATE_MAIN_FILTER) == 0x04000000){
									Log.d(TAG, "BackHomeLeftDown!!!");
									SavePref();
									if (DisplayType == DISPLAY_TYPE_A) {
										_MainBBaseFragment.showLeftDowntoDefaultScreenAnimation();
									} else {
										_MainABaseFragment.showLeftDowntoDefaultScreenAnimation();
									}
								} else {
									Log.d(TAG, "BackHome!!!" + Integer.toHexString(ScreenIndex));
									showMainScreen();
									setScreenIndex();
								}
								BackHomeCount = 0xff;
							}
							else
								Log.d(TAG, "BackHomeCount" + BackHomeCount);
						}
					}
					else if((ScreenIndex & SCREEN_STATE_FILTER) == SCREEN_STATE_MAIN_CAMERA_TOP){
						BackHomeCount = 0;
					}
					else
						CancelBackHomeTimer();
				}
			});
		}
	}
	public void ResetBackHomeCount(){
		BackHomeCount = 0;
	}

	public void StartBackHomeTimer() {
		CancelBackHomeTimer();
		Log.d(TAG, "StartBackHomeTimer!!!");
		BackHomeCount = 0;
		mBackHomeTimer = new Timer();
		mBackHomeTimer.schedule(new BackHomeTimerClass(), 1, 1000);
	}

	public void CancelBackHomeTimer() {
		BackHomeCount = 0xff;
		if (mBackHomeTimer != null) {
			mBackHomeTimer.cancel();
			mBackHomeTimer.purge();
			mBackHomeTimer = null;
			Log.d(TAG, "CancelBackHomeTimer!!!");
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
			strModel = "-";
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
		String strVer;
		nVersion = (BasicInfo[3] & 0xFF);
		VersionHigh = ((nVersion & 0xF0) >> 4);
		VersionLow = (nVersion & 0x0F);

		if(VersionLow > 10 && VersionHigh >= 15){
			strVer = "-";
		}else{
			strVer = Integer.toString(VersionHigh) + "." + Integer.toString(VersionLow);
		}

		return strVer;
	}
	public String GetVersionString(byte[] BasicInfo, int SubInfo)throws NullPointerException{
		String strVersion = GetProgramVersion(BasicInfo);
		int SubVersionHigh = ((SubInfo & 0xF0) >> 4);
		
		if(0 <= SubVersionHigh && SubVersionHigh <= 14){
			strVersion = strVersion + "." + Integer.toHexString(SubVersionHigh);
		}
		
		return strVersion;
	}
	public String GetHiddenVersionString(byte[] BasicInfo, int SubInfo)throws NullPointerException{
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
	public String GetVersionString(int VersionHigh, int VersionLow, int VersionSubHigh)throws NullPointerException{
	//public String GetVersionString(int VersionHigh, int VersionLow, int VersionSubHigh, int VersionSubLow)throws NullPointerException{
		String strVersion;
		strVersion = Integer.toHexString(VersionHigh) + "." + Integer.toHexString(VersionLow) 
				+ "." +Integer.toHexString(VersionSubHigh);//  + "." + Integer.toHexString(VersionSubLow);
		return strVersion;
	}
	/////////////////////////////////////////////////////
	//Calculate//////////////////////////////////////////
	public String GetNumberString(long _Number){
		String strNumber;
		strNumber = NumberFormat.getNumberInstance(Locale.US).format(_Number);
		return strNumber;
	}
//	public String GetFuelRateString(int _FuelRate){
	public String GetFuelRateString(int _FuelRate, int _unit){
		String strFuelRate;
		long long_Fuel;
		int nFuel;
		int nFuel_Under;
		
		long_Fuel = _FuelRate & 0xFFFFFFFFL;
		if(long_Fuel > 0xFB00L){
			long_Fuel = 0;
		}
		
		long_Fuel *= 500;
		
		if(_unit == UNIT_FUEL_GAL){
			long_Fuel *= 264172;
			long_Fuel /= 1000000;
			nFuel = (int)(long_Fuel / 10000);
			nFuel_Under = (int)((long_Fuel % 10000) / 1000);
		}else{
			nFuel = (int)(long_Fuel / 10000);
			nFuel_Under = (int)((long_Fuel % 10000) / 1000);
		}
		
		strFuelRate = Integer.toString(nFuel) + "." + Integer.toString(nFuel_Under);
		return strFuelRate;
		
	}
	
	public float GetFuelRateFloat(int _FuelRate, int _unit){
		long long_Fuel;
		float nFuel;
		
		long_Fuel = _FuelRate & 0xFFFFFFFFL;
		if(long_Fuel > 0xFB00L){
			long_Fuel = 0;
		}
		
		long_Fuel *= 500;
		
		if(_unit == UNIT_FUEL_GAL){
			long_Fuel *= 264172;
			long_Fuel /= 1000000;
			nFuel = (float)(long_Fuel / 10000);
		}else{
			nFuel = (float)(long_Fuel / 10000);
		}
		
		return nFuel;
		
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
		}else if(Unit == UNIT_WEIGHT_US_TON){
			long_Weight *= 1102311;
			nWeight =  (long_Weight / 10000000);
			nWeight_Under = ( (long_Weight % 10000000) / 100000);
			if((nWeight_Under % 10) < 5)
				nWeight_Under = nWeight_Under / 10;
			else
				nWeight_Under = (nWeight_Under / 10) + 1;
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
		
		if(_Battery == 0xFFFF){
			return "-";
		}
		
		long_Battery = (_Battery  & 0xFFFFFFFFL);
		if(long_Battery > 500){
		//	long_Battery = 0;
			return "-";
		}
		
		nBattery = (int) (long_Battery / 10);
		nBattery_Under = (int) (long_Battery % 10);
		strBattery = GetNumberString(nBattery) + "." + Integer.toString(nBattery_Under);
		return strBattery;
	}
	public String GetTempAxle(int _Temp, int Unit) {
		String strTemp;
		int nTemp;
		long long_Temp;

		long_Temp = (_Temp & 0xFFFFFFFFL);
		if (long_Temp >= 0xFEL) {
			return "-";
		} else {
			nTemp = GetTemp(_Temp);
			if (Unit == UNIT_TEMP_F) {
				nTemp *= 18;
				nTemp = nTemp / 10;
				nTemp += 32;
				strTemp = GetNumberString(nTemp);
			} else {
				strTemp = GetNumberString(nTemp);
			}
		}

		return strTemp;
	}
	public String GetTemp(int _Temp, int Unit){
		String strTemp;
		int nTemp;
		long long_Temp;
		
		long_Temp = (_Temp  & 0xFFFFFFFFL);
		if(long_Temp >= 0xFFL){
			return "-";
		}else
		{
			nTemp = GetTemp(_Temp);
			if(Unit == UNIT_TEMP_F){
				nTemp *= 18;
				nTemp = nTemp / 10;
				nTemp += 32;
				strTemp = GetNumberString(nTemp);
			}else{
				strTemp = GetNumberString(nTemp);
			}
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
		
		if(AbsHour >= 24){
			AbsHour = 12;
		}
		else if(AbsHour > 12){
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
	public String GetHour2(int _Hour){
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
			strHour =  Integer.toString(AbsHour);
		else
			strHour = Integer.toString(AbsHour);
		
		return strHour;
	}
	public String GetMin2(int _Min){
		String strMin = "";
		int AbsMin;
		
		AbsMin = Math.abs(_Min);
		if(AbsMin >= 60){
			AbsMin = 0;
		}
		
		if(AbsMin < 10)
			strMin = Integer.toString(AbsMin);
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
	// ++, 150323 bwk
	public boolean CheckRunningApp(String strPrcessName){
		 ActivityManager activity_manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningAppProcessInfo> app_list = activity_manager.getRunningAppProcesses();

		 for(int i=0; i<app_list.size(); i++)	 {
	
			 if(strPrcessName.equals(app_list.get(i).processName) == true)	 {
				 Log.d(TAG,"Process is running : " + app_list.get(i).processName);
				 return true;
			 }
		 }
		 System.gc();
		 return false;
	}
	// --, 150323 bwk
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
	public void KillApps(String strPrcessName){
		 ActivityManager activity_manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		 List<RunningAppProcessInfo> app_list = activity_manager.getRunningAppProcesses();
		 Log.d(TAG, "Try kill process..");

		 for(int i=0; i<app_list.size(); i++)	 {
			 Log.d(TAG,"Process : " + app_list.get(i).processName);

			 if(strPrcessName.equals(app_list.get(i).processName) == true)	 {
				 android.os.Process.sendSignal(app_list.get(i).pid, android.os.Process.SIGNAL_KILL);
				 activity_manager.killBackgroundProcesses(app_list.get(i).processName);
				 Log.d(TAG,"Kill Process : " + app_list.get(i).processName);
			 }
		 }
		 System.gc();
	}
	// ++, 150324 cjg
	public void StartAlwaysOntopService(){
		startService(new Intent(Home.this,AlwaysOnTopService.class));
	}
	// --, 150324 cjg
	// ++, 150324 cjg
	public void StopAlwaysOntopService(){
		stopService(new Intent(Home.this,AlwaysOnTopService.class));
	}
	// --, 150324 cjg
	/////////////////////////////////////////////////////
	public void setMarqueeRadio(RadioButton radioButton){
		//radioButton.setSingleLine();
		radioButton.setEllipsize(TruncateAt.MARQUEE);
		radioButton.setSelected(true);
	}
	public void setMarqueeText(TextView textView){
		textView.setSingleLine();
		textView.setEllipsize(TruncateAt.MARQUEE);
		textView.setSelected(true);
	}
}
