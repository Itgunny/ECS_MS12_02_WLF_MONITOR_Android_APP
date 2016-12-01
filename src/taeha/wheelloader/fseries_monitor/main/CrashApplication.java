package taeha.wheelloader.fseries_monitor.main;

import org.acra.annotation.ReportsCrashes;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;

import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;

import android.app.Application;
import android.util.Log;

@ReportsCrashes
(
	// Google Docs로 전송시에만 사용됨 비워두면 된다.
	formKey				= "",
	
	// Crash 발생 즉시 Toast 메시지로 알림 : Toast 내용
	resToastText		= R.string.Crash_Toast_Text,
		
	// Dialog 형태로 알림
	mode				= ReportingInteractionMode.TOAST,
	
	//	Dialog 표시 아이콘
	resDialogIcon		= android.R.drawable.ic_popup_disk_full,
	
	// Dialog title 표시 문구
	resDialogTitle		= R.string.Error_Report,
	
	// Dialog 본문 표시 문구
	resDialogText		= R.string.Error_Report,
	
	// Dialog OK 선택시 발생 Toast
	resDialogOkToast	= R.string.Error_Report,
	
	// Dialog OK 선택시 메일 발송
	mailTo				= ""
	
)

public class CrashApplication extends Application{
	int Year;
	int Month;
	int Date;
	int Hour;
	int Min;
	int Sec;
	
	ErrorReportSender _ErrorReportSender;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ACRA.init(this);
		_ErrorReportSender = new ErrorReportSender(); 
		ACRA.getErrorReporter().setReportSender(_ErrorReportSender);
		
		if (BuildConfig.DEBUG == false) {
	        new ANRWatchDog().setANRListener(new ANRWatchDog.ANRListener() {
	        
				@Override
				public void onAppNotResponding(ANRError arg0) {
					// TODO Auto-generated method stub
					Log.d("ANR",arg0.toString());
				}
	        }).start();
	    }
	}
	public void SetYear(int Data){
		_ErrorReportSender.SetYear(Data);
	}
	public void SetMonth(int Data){
		_ErrorReportSender.SetMonth(Data);
	}
	public void SetDate(int Data){
		_ErrorReportSender.SetDate(Data);
	}
	public void SetHour(int Data){
		_ErrorReportSender.SetHour(Data);
	}
	public void SetMin(int Data){
		_ErrorReportSender.SetMin(Data);
	}
	public void SetSec(int Data){
		_ErrorReportSender.SetSec(Data);
	}
	public int GetYear(){
		return _ErrorReportSender.GetYear();
	}
	public int GetMonth(){
		return _ErrorReportSender.GetMonth();
	}
	public int GetDate(){
		return _ErrorReportSender.GetDate();
	}
	public int GetHour(){
		return _ErrorReportSender.GetHour();
	}
	public int GetMin(){
		return _ErrorReportSender.GetMin();
	}
	public int GetSec(){
		return _ErrorReportSender.GetSec();
	}
}
