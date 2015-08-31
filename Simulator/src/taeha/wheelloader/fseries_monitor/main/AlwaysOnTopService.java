// ++, 150324 cjg
package taeha.wheelloader.fseries_monitor.main;

import java.util.List;

import android.app.ActivityManager;
import android.app.Instrumentation;
import android.app.Service;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;

public class AlwaysOnTopService extends Service{
    private TextView mPopupView;                            //�׻� ���̰� �� ��
    private WindowManager.LayoutParams mParams;  //layout params ��ü. ���� ��ġ �� ũ��
    private WindowManager mWindowManager;          //������ �Ŵ���
    private static final String TAG = "AlwaysOnTopService";
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
        mPopupView = new TextView(this);                        	// Create View
        mPopupView.setText("Menu");                  		// Set View 
        mPopupView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25); 	// Text Size : 18dp
        mPopupView.setTextColor(Color.TRANSPARENT);                        // Text Color : BLUE
        mPopupView.setBackgroundColor(Color.TRANSPARENT);// Back Color : 127 0 255 255
        // Set 
        mParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,			//�׻� �� ����. ��ġ �̺�Ʈ ���� �� ����.
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  //��Ŀ���� ������ ����
            PixelFormat.TRANSLUCENT);                       //����
        mParams.gravity = Gravity.CENTER| Gravity.TOP;       //���� ��ܿ� ��ġ�ϰ� ��.
        
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);  //������ �Ŵ���
        mWindowManager.addView(mPopupView, mParams);      					//�����쿡 �� �ֱ�. permission �ʿ�.
        mPopupView.setOnTouchListener(mViewTouchListener);
     
	}
	private OnTouchListener mViewTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				//Log.d("TAG", "x : " + event.getX() + "y : " + event.getY());
				if (CommService.multimediaFlag == true){
					//++, 150715 cjg
					Runtime runtime = Runtime.getRuntime();
					Process process;
					try{
						String cmd = "am force-stop com.mxtech.videoplayer.ad";
						process = runtime.exec(cmd);
						Log.d(TAG, "am force-stop com.mxtech.videoplayer.ad");
					}catch(Exception e){
						e.fillInStackTrace();
					}
					//--, 150715 cjg
				}else if(CommService.miracastFlag == true){
					Runtime runtime = Runtime.getRuntime();
					Process process;
					try{
						String cmd = "am force-stop com.powerone.wfd.sink";
						process = runtime.exec(cmd);
						Log.d(TAG, "am force-stop com.powerone.wfd.sink");
					}catch(Exception e){
						e.fillInStackTrace();
					}
				}else
					CommService.BackKeyEvent();

				
			}
			return false;
		}
	};
	public void setButtonInvisible(){
		mPopupView.setVisibility(View.INVISIBLE);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
        if(mWindowManager != null) {        //���� ����� �� ����. *�߿� : �並 �� ���� �ؾ���.
            if(mPopupView != null) mWindowManager.removeView(mPopupView);
           
        }
	}
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
//--, 150324 cjg
	// ++, 150326 bwk
	public void CloseMXPlayer(){
		Thread thread = new Thread(new Runnable()	{

			Instrumentation inst = new Instrumentation();
			long downTime = SystemClock.uptimeMillis();
			long eventTime = SystemClock.uptimeMillis() + 100;
			@Override
			public void run() {
				// TODO Auto-generated method stub	
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MotionEvent event = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, 700, 455, 0);
				MotionEvent event2 = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, 700, 455, 0);
				inst.sendPointerSync(event);
				inst.sendPointerSync(event2);

			}
		});

		thread.start();
//		CommService.multimediaFlag = false;
	}
	// --, 150326 bwk
}