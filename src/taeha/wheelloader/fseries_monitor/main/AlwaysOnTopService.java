// ++, 150324 cjg
package taeha.wheelloader.fseries_monitor.main;

import java.util.List;

import android.app.ActivityManager;
import android.app.Service;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;

public class AlwaysOnTopService extends Service{
    private TextView mPopupView;                            //항상 보이게 할 뷰
    private WindowManager.LayoutParams mParams;  //layout params 객체. 뷰의 위치 및 크기
    private WindowManager mWindowManager;          //윈도우 매니저
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
            WindowManager.LayoutParams.TYPE_PHONE,			//항상 최 상위. 터치 이벤트 받을 수 있음.
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  //포커스를 가지지 않음
            PixelFormat.TRANSLUCENT);                       //투명
        mParams.gravity = Gravity.CENTER| Gravity.TOP;       //왼쪽 상단에 위치하게 함.
        
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);  //윈도우 매니저
        mWindowManager.addView(mPopupView, mParams);      					//윈도우에 뷰 넣기. permission 필요.
        mPopupView.setOnTouchListener(mViewTouchListener);
     
	}
	private OnTouchListener mViewTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				Log.d("TAG", "x : " + event.getX() + "y : " + event.getY());
				if(CommService.multimediaFlag == false){
					CommService.BackKeyEvent();
				}else{
					CommService.MenuKeyEvent();
					Log.d(TAG, "org.ebook");
				}
				
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
        if(mWindowManager != null) {        //서비스 종료시 뷰 제거. *중요 : 뷰를 꼭 제거 해야함.
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
}
//--, 150324 cjg