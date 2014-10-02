package taeha.wheelloader.fseries_monitor.animation;

import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home;



import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TextViewXAxisFlipAnimation extends View{

	final  private String TAG = "TextViewXAxisFlipAnimation";
	
	final  private int AnimationSpeed = 200;
	
	private Context context;
	private String CurrentString;
	private String NextString;
	
	ObjectAnimator animation;

	TextView TargetView;
	
	public TextViewXAxisFlipAnimation(Context _context) {
		super(_context);
		context = _context;
		CurrentString = "";
		NextString = "";
	}
	public TextViewXAxisFlipAnimation(Context _context, String str) {
		super(_context);
		context = _context;
		CurrentString = str;
	}
	
	public void FlipAnimation(TextView view, String str){
		if(CurrentString.equals(str) == false){
			Log.d(TAG,"FilpAnimation");
			TargetView = view;
			animation = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_0_90);
			animation.setTarget(TargetView);
			animation.setDuration(AnimationSpeed);
			animation.start();
			
			setNextString(str);
			
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
					ObjectAnimator animation2 = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_270_360);
					animation2.setTarget(TargetView);
					animation2.setDuration(AnimationSpeed);
					animation2.start();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationCancel");
				}
			});
			CurrentString = NextString;
		}
	}
	

	public void FlipAnimation(TextView view, String str, final boolean FontChangeFlag){
		if(CurrentString.equals(str) == false){
			Log.d(TAG,"FilpAnimation");
			TargetView = view;
			animation = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_0_90);
			animation.setTarget(TargetView);
			animation.setDuration(AnimationSpeed);
			animation.start();
			
			setNextString(str);
						
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
					ObjectAnimator animation2 = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_x_270_360);
					animation2.setTarget(TargetView);
					animation2.setDuration(AnimationSpeed);
					animation2.start();

				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationCancel");
				}
			});
			CurrentString = NextString;
		}
	}
	

	public void setNextString(String str){
		NextString = str;
	}
	
	public void showNextString(){
		TargetView.setText(NextString);
	}
}
