package taeha.wheelloader.fseries_monitor.main;


import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.EndingAnimation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class EndingFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	//static int count = 0;		// ++, --, 150403 cjg
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout layoutEnding;
	int[] sourcesId
	= {R.drawable.outro_01, R.drawable.outro_02, R.drawable.outro_03, R.drawable.outro_04, R.drawable.outro_05, R.drawable.outro_06, R.drawable.outro_07
	  ,R.drawable.outro_08, R.drawable.outro_09, R.drawable.outro_10, R.drawable.outro_11, R.drawable.outro_12, R.drawable.outro_13, R.drawable.outro_14
	  ,R.drawable.outro_15, R.drawable.outro_16, R.drawable.outro_17, R.drawable.outro_18, R.drawable.outro_19, R.drawable.outro_20, R.drawable.outro_21
	  ,R.drawable.outro_22, R.drawable.outro_23, R.drawable.outro_24, R.drawable.outro_25, R.drawable.outro_26, R.drawable.outro_27, R.drawable.outro_28
	  ,R.drawable.outro_29, R.drawable.outro_30, R.drawable.outro_31, R.drawable.outro_32, R.drawable.outro_33, R.drawable.outro_34, R.drawable.outro_35
	  ,R.drawable.outro_36, R.drawable.outro_37, R.drawable.outro_38, R.drawable.outro_39, R.drawable.outro_40, R.drawable.outro_41, R.drawable.outro_42
	  ,R.drawable.outro_43, R.drawable.outro_44, R.drawable.outro_45, R.drawable.outro_46, R.drawable.outro_47, R.drawable.outro_48, R.drawable.outro_49
	  ,R.drawable.outro_50, R.drawable.outro_51, R.drawable.outro_52, R.drawable.outro_53, R.drawable.outro_54, R.drawable.outro_55, R.drawable.outro_56
	  ,R.drawable.outro_57, R.drawable.outro_58, R.drawable.outro_59, R.drawable.outro_60};
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Handler refreshHandler = null;
	private Timer mEndingFinishTimer = null;
	private Timer mEndingAnimationTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	EndingAnimation Animation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "EndingFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.screen_ending, null);
		// ++, 150403 cjg
		//count++;		
		//Log.d("TAG", "count" + count);
		// --, 150403 cjg
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_ENDING;
		
		
		
		refreshHandler = new Handler(){
			public void handleMessage(Message msg){
				if(msg.what == 1000){
					//Log.d(TAG, "OnHandler");
					Animation.update(layoutEnding);
					Animation.invalidate();
					sleep(10);
				}
				
			}
			public void sleep(long delayMillis){
				this.removeMessages(1000);
				sendMessageDelayed(obtainMessage(1000), delayMillis);
			}
		};
		// ++, 150403 cjg
//		if(count == 2){
//			refreshHandler.sendEmptyMessage(1000);
//		}
		refreshHandler.sendEmptyMessage(1000);
		// --, 150403 cjg
		
		ParentActivity.SavePref();
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		//Log.d(TAG,"onDestroy()");
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		//Log.d(TAG,"InitResource");
		layoutEnding = (RelativeLayout)mRoot.findViewById(R.id.imageView_screen_ending);
		//Log.d(TAG,"InitResource2");
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		Animation = new EndingAnimation(ParentActivity);
		Animation.setImage(ParentActivity, layoutEnding, sourcesId);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	public class EndingFinishTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ParentActivity.finish();
				
				}
			});
			
		}
		
	}
	
	public void StartEndingFinishTimer(){
		CancelEndingFinishTimer();
		mEndingFinishTimer = new Timer();
		mEndingFinishTimer.schedule(new EndingFinishTimerClass(),1000);	
	}
	
	public void CancelEndingFinishTimer(){
		if(mEndingFinishTimer != null){
			mEndingFinishTimer.cancel();
			mEndingFinishTimer.purge();
			mEndingFinishTimer = null;
		}
		
	}
	
	
	public class EndingAnimationTimerClass extends TimerTask{

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
	
	public void StartEndingAnimationTimer(){
		CancelEndingAnimationTimer();
		mEndingAnimationTimer = new Timer();
		mEndingAnimationTimer.schedule(new EndingAnimationTimerClass(),1,100);	
	}
	
	public void CancelEndingAnimationTimer(){
		if(mEndingAnimationTimer != null){
			mEndingAnimationTimer.cancel();
			mEndingAnimationTimer.purge();
			mEndingAnimationTimer = null;
		}
		
	}
}