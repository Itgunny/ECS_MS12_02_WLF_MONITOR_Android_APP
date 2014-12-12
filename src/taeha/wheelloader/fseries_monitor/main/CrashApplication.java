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
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ACRA.init(this);
		ACRA.getErrorReporter().setReportSender(new ErrorReportSender());
		
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
}
