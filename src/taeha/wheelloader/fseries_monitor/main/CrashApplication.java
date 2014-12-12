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
	// Google Docs�� ���۽ÿ��� ���� ����θ� �ȴ�.
	formKey				= "",
	
	// Crash �߻� ��� Toast �޽����� �˸� : Toast ����
	resToastText		= R.string.Crash_Toast_Text,
		
	// Dialog ���·� �˸�
	mode				= ReportingInteractionMode.TOAST,
	
	//	Dialog ǥ�� ������
	resDialogIcon		= android.R.drawable.ic_popup_disk_full,
	
	// Dialog title ǥ�� ����
	resDialogTitle		= R.string.Error_Report,
	
	// Dialog ���� ǥ�� ����
	resDialogText		= R.string.Error_Report,
	
	// Dialog OK ���ý� �߻� Toast
	resDialogOkToast	= R.string.Error_Report,
	
	// Dialog OK ���ý� ���� �߼�
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
