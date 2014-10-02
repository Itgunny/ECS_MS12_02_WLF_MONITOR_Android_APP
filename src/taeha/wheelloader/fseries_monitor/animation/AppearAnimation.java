package taeha.wheelloader.fseries_monitor.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import android.util.Log;
import android.animation.Animator.AnimatorListener;

public class AppearAnimation extends View{
	
	final private String TAG = "AppearAnimation";
	
	private int AnimationSpeed = 200;
	
	public Home ParentActivity;
	public View viewItem;
	
	ObjectAnimator Animation;
	
	boolean AnimiationRunning = false;
	
	public AppearAnimation(Context _context, View view){
		super(_context);
		ParentActivity = (Home)_context;
		viewItem = view;
		AnimiationRunning = false;
		
		Animation = (ObjectAnimator)AnimatorInflater.loadAnimator(_context, R.anim.appear);
		Animation.setTarget(viewItem);
		Animation.setDuration(AnimationSpeed);
	
		AnimationListener();
	}
	
	public void AnimationListener(){
		Animation.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = true;
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = false;
				viewItem.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = false;
				
			}
		});
	}
	
	public void CancelAnimation(){
		try {
			Animation.cancel();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"CancelAnimation NullPointerException");
		}
	}
	public void StartAnimation(){
		try {
			Animation.start();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"StartAnimation NullPointerException");
		}	
	}
	public boolean GetAnimationStatus(){
		return AnimiationRunning;
	}
	public void SetAnimaitionTime(int Time){
		AnimationSpeed = Time;
		Animation.setTarget(viewItem);
	}
	
}
