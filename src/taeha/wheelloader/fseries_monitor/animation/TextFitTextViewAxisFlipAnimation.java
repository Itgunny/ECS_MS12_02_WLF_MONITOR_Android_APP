package taeha.wheelloader.fseries_monitor.animation;

import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.location.Address;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TextFitTextViewAxisFlipAnimation extends View{

	final  private String TAG = "TextViewXAxisFlipAnimation";
	
	final  private int AnimationSpeed = 200;
	
	private Context context;
	private String CurrentString;
	private String NextString;
	
	ObjectAnimator animation;
	ObjectAnimator animation2;
	TextFitTextView TargetView;
	
	public TextFitTextViewAxisFlipAnimation(Context _context) {
		super(_context);
		context = _context;
		CurrentString = "";
		NextString = "";
	}
	public TextFitTextViewAxisFlipAnimation(Context _context, String str, TextFitTextView view) {
		super(_context);
		context = _context;
		CurrentString = str;
		TargetView = view;
		TargetView.setText(CurrentString);
	}
	
	public void FlipAnimation(TextFitTextView view, String str){
		if(CurrentString.equals(str) == false){
			Log.d(TAG,"FilpAnimation");
			TargetView = view;
			animation = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_0_90);
			animation.setTarget(TargetView);
			animation.setDuration(AnimationSpeed);
			animation.start();
			setNextString(str);
			
			animation2 = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_270_360);
			animation2.setTarget(TargetView);
			animation2.setDuration(AnimationSpeed);
			
			
			animation.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationStart");
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationRepeat");
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationEnd");
					showNextString();
					animation2.start();
					
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationCancel");
				}

			});
			animation2.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG, "clearAnimation");
					TargetView.clearAnimation();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}
			});
			CurrentString = NextString;
			
		}else{
			TargetView.clearAnimation();
			
		}
	}
	

	public void FlipAnimation(TextFitTextView view, String str, final boolean FontChangeFlag){
		if(CurrentString.equals(str) == false){
			Log.d(TAG,"FilpAnimation");
			TargetView = view;
			animation = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_0_90);
			animation.setTarget(TargetView);
			animation.setDuration(AnimationSpeed);
			animation.start();
			
			setNextString(str);
			
			animation2 = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_270_360);
			animation2.setTarget(TargetView);
			animation2.setDuration(AnimationSpeed);
			animation.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationStart");
				}
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationRepeat");
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationEnd");
					showNextString();
					animation2.start();
					
				}
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationCancel");
				}
			});
			animation2.addListener(new AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG, "clearAnimation");
					TargetView.clearAnimation();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}
			});
			CurrentString = NextString;
		}else{
			TargetView.clearAnimation();
		}
	}
	

	public void setNextString(String str){
		NextString = str;
	}
	
	public void showNextString(){
		TargetView.setFitTextToBox(true);
		TargetView.setText(NextString);
		
	}
}
