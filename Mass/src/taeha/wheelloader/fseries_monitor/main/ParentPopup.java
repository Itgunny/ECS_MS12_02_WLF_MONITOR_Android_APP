package taeha.wheelloader.fseries_monitor.main;

import java.lang.ref.WeakReference;

import taeha.wheelloader.fseries_monitor.main.ParentFragment.ReadThread;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public abstract class ParentPopup extends Dialog{
	public String TAG = "ParentPopup";
	
	// CAN1CommManager
	protected  CAN1CommManager CAN1Comm;
	protected  Home ParentActivity;
	
	// Thread Sleep Time
	private  int ThreadSleepTime;
	
	// Thread
	protected Thread threadRead = null;
	
	protected LayoutInflater inflater;
	
	protected View mRoot;
	
	protected int CursurIndex;
	
	abstract protected void InitResource();
	abstract protected void InitButtonListener();
	abstract protected void GetDataFromNative();
	abstract protected void UpdateUI();
	
	public ParentPopup(Context _context) {
		super(_context,R.style.Dialog);
		// TODO Auto-generated constructor stub
		ParentActivity = (Home)_context;
		CursurIndex = 0;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(ParentActivity.ScreenIndex >= Home.SCREEN_STATE_MAIN_CAMERA_TOP && ParentActivity.ScreenIndex <= Home.SCREEN_STATE_MAIN_CAMERA_END)
		{
			if(CAN1Comm.CameraOnFlag == CAN1CommManager.STATE_CAMERA_MANUAL)
				ParentActivity.ExitCam();
			return false;
		}
		else
			return super.dispatchTouchEvent(ev);
    }
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		threadRead.interrupt();
		ParentActivity.HomeDialog= null;
	}
	
	public void InitValuable(){
		CAN1Comm = CAN1CommManager.getInstance();	
		ThreadSleepTime = 200;
		threadRead = new Thread(new ReadThread(this));
		threadRead.start();
	}
	

	protected void SetThreadSleepTime(int Time){
		ThreadSleepTime = Time;
	}
	protected int GetThreadSleepTime(){
		return ThreadSleepTime;
	}
	
	private void DisplayUI(){
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				UpdateUI();
			}
		});
	}
	
	// Read Thread
	public class ReadThread implements Runnable {
		private WeakReference<ParentPopup> DialogRef = null;
		public Message msg = null;
		public ReadThread(ParentPopup _dialog){
			this.DialogRef = new WeakReference<ParentPopup>(_dialog);
			msg = new Message();
		}
	
		@Override
		public void run() {
			try{
				while(!DialogRef.get().threadRead.currentThread().isInterrupted())
				{
					DialogRef.get().GetDataFromNative();
					DialogRef.get().DisplayUI();
					Thread.sleep(DialogRef.get().GetThreadSleepTime());		
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

}
