package taeha.wheelloader.fseries_monitor.main;


import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.EndingAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;

public class EndingFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView imgViewEnding;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
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
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_ENDING;
		
		Animation.Ending();
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d(TAG,"onDestroy()");

		
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		Log.d(TAG,"InitResource");
		imgViewEnding = (ImageView)mRoot.findViewById(R.id.imageView_screen_ending);
		Log.d(TAG,"InitResource2");
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		Animation = new EndingAnimation(ParentActivity, imgViewEnding);
	

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