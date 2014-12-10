package taeha.wheelloader.fseries_monitor.main;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ParentFragment extends Fragment{
	/////////////////CONSTANT////////////////////////////////////////////
	public String TAG = "ParentFragment";
	/////////////////////////////////////////////////////////////////////
	/////////////////////RESOURCE////////////////////////////////////////
	// Fragment Root
	protected View mRoot;
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////VALUABLE////////////////////////////////////////
	// Home
	protected Home ParentActivity;
	
	// CAN1CommManager
	protected  CAN1CommManager CAN1Comm = null;
	
	// Thread
	protected Thread threadRead = null;
	
	// Thread Sleep Time
	private  int ThreadSleepTime;

	static public int CursurIndex = 1;
	
	boolean ThreadRunFlag;
	/////////////////////////////////////////////////////////////////////	
	
	///////////////////ANIMATION/////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
	///////////////////Common Function///////////////////////////////////
	abstract protected void InitResource();
	abstract protected void InitButtonListener();
	abstract protected void GetDataFromNative();
	abstract protected void UpdateUI();
	protected void InitValuables(){
		Log.d(TAG,"InitValuables");
		ThreadSleepTime = 200;
	
		CAN1Comm = CAN1CommManager.getInstance();	
		
		threadRead = new Thread(new ReadThread(this));
		
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
				try {
					UpdateUI();
				} catch (IllegalStateException e) {
					// TODO: handle exception
					Log.e(TAG,"IllegalStateException DisplayUI");
				}catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException DisplayUI");
				}
				
			}
		});
	}
	
	// Read Thread
	public class ReadThread implements Runnable {
		private WeakReference<ParentFragment> fragmentRef = null;
		public Message msg = null;
		public ReadThread(ParentFragment fragment){
			this.fragmentRef = new WeakReference<ParentFragment>(fragment);
			msg = new Message();
		}
	
		@Override
		public void run() {
			try{
				while(!fragmentRef.get().threadRead.currentThread().isInterrupted() && ThreadRunFlag == true)
				{
					fragmentRef.get().GetDataFromNative();
					fragmentRef.get().DisplayUI();
					Thread.sleep(fragmentRef.get().GetThreadSleepTime());
				}
			}
			catch(InterruptedException ie){
				Log.e(TAG,"InterruptedException ReadThread");
			}		
			catch(RuntimeException ee){
				Log.e(TAG,"RuntimeException ReadThread");
			}
		}
	
	}
	
	public void StopReadThread(){
		try {
			ThreadRunFlag = false;
			threadRead.interrupt();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Log.e(TAG,"RuntimeException StopReadThread");
		}
	}
	public void StartReadThread(){
		try {
			ThreadRunFlag = true;
			threadRead.start();
		} catch (RuntimeException e) {
			// TODO: handle exception
			Log.e(TAG,"RuntimeException StartReadThread");
		}
		
	}
	////////////////////////////////////////////////////////////////////
	

	////////////////////Life Cycle Function/////////////////////////////
	@Override
	public void onInflate(Activity activity, AttributeSet attrs,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onInflate");
		for (int i = 0; i < attrs.getAttributeCount(); i++) {
			Log.v(TAG,
					"    " + attrs.getAttributeName(i) + " = "
							+ attrs.getAttributeValue(i));
		}
		super.onInflate(activity, attrs, savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onAttach");
		super.onAttach(activity);
		this.ParentActivity = (Home) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onActivityCreated");
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroy");
		super.onDestroy();

	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDestroyView");
		super.onDestroyView();
		
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onDetach");
		super.onDetach();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPause");
		super.onPause();
		StopReadThread();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onResume");
		super.onResume();
		StartReadThread();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStart");
		super.onStart();

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStop");
		super.onStop();

	}
	/////////////////////////////////////////////////////////////////////
}
