package taeha.wheelloader.fseries_monitor.main.b;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBVirtualKeyFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBVirtualKeyFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout layoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Timer mClickTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.lower_main_b_virtualkey, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		CancelClickTimer();
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StartClickTimer();
	}
	
	////////////////////////////////////////////////
	
	


	//Common Function//////////////////////////////
	
	
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_lower_main_b_virtualkey);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
//		layoutBG.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	/////////////////////////////////////////////////////////////////////	
	///////////////////////Timer/////////////////////////////////////////
	public class ClickTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
		
	}
	
	public void StartClickTimer(){
		CancelClickTimer();
		mClickTimer = new Timer();
		mClickTimer.schedule(new ClickTimerClass(),1,100);	
	}
	
	public void CancelClickTimer(){
		if(mClickTimer != null){
			mClickTimer.cancel();
			mClickTimer.purge();
			mClickTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	
}