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
import android.app.Fragment;

public class ChangeFragmentAnimation extends View{
	
	final static private String TAG = "ChangeFragmentAnimation";
	
	final static private int AnimationSpeed = 200;
	
	public Home ParentActivity;
	public View viewItem;
	
	ObjectAnimator AnimationDisappear;
	ObjectAnimator AnimationAppear;
	
	boolean AnimiationRunning = false;
	boolean ChangeFlag = false;
	
	Fragment CurrentFragment;
	Fragment NextFragment;
	
	int nFrameLayoutID;
	public ChangeFragmentAnimation(Context _context, View view, int _FrameLayoutID, Fragment _fragment){
		super(_context);
		ParentActivity = (Home)_context;
		viewItem = view;
		nFrameLayoutID = _FrameLayoutID;
		CurrentFragment =_fragment;
		NextFragment = _fragment;
		AnimiationRunning = false;
		
		AnimationDisappear = (ObjectAnimator)AnimatorInflater.loadAnimator(_context, R.anim.disappear);
		AnimationDisappear.setTarget(viewItem);
		AnimationDisappear.setDuration(AnimationSpeed);
		
		AnimationAppear = (ObjectAnimator)AnimatorInflater.loadAnimator(_context, R.anim.appear);
		AnimationAppear.setTarget(viewItem);
		AnimationAppear.setDuration(AnimationSpeed);
	
		AnimationListener();
	}
	
	public void AnimationListener(){
		AnimationDisappear.addListener(new AnimatorListener() {
			
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
				viewItem.setVisibility(View.GONE);
				if(ChangeFlag == true){
					showNextFragment(NextFragment);
					StartAppearAnimation();
				}
				
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = false;
				
			}
		});
		
		AnimationAppear.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = true;
				viewItem.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = false;
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				AnimiationRunning = false;
			}
		});
	}
	public void StartDisappearAnimation(){
		ChangeFlag = false;
		try {
			AnimationDisappear.start();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"StartAnimation NullPointerException");
		}	
	}
	
	
	public void CancelChangeAnimation(){
		try {
			AnimationDisappear.cancel();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"CancelAnimation NullPointerException");
		}
	}
	public void StartChangeAnimation(Fragment _fragment){
			NextFragment = _fragment;
			ChangeFlag = true;
			try {
				AnimationDisappear.start();
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"StartAnimation NullPointerException");
			}	
	
		
	}
	public void CancelAppearAnimation(){
		try {
			AnimationAppear.cancel();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"CancelAnimation NullPointerException");
		}
	}
	public void StartAppearAnimation(){
		try {
			AnimationAppear.start();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"StartAnimation NullPointerException");
		}	
	}
	public boolean GetAnimationStatus(){
		return AnimiationRunning;
	}
	public void showNextFragment(Fragment _fragment){
		try{
			if(_fragment != CurrentFragment){
				android.app.FragmentTransaction transaction = ParentActivity.getFragmentManager().beginTransaction();
				transaction.replace(nFrameLayoutID, _fragment);
				transaction.commit();
				CurrentFragment = _fragment;
			}
			
		} catch (IllegalStateException e){
			Log.e(TAG,"IllegalStateException");
		}
		catch (RuntimeException e){
			Log.e(TAG,"RuntimeException");
		}
	}
}
