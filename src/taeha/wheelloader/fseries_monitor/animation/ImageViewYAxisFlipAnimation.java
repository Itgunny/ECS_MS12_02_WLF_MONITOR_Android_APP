package taeha.wheelloader.fseries_monitor.animation;

import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home;



import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class ImageViewYAxisFlipAnimation extends View{

	final static private String TAG = "ImageViewYAxisFlipAnimation";
	
	final static private int AnimationSpeed = 200;
	
	private Context context;
	private int CurrentDrawable;
	private int NextDrawable;
	
	ObjectAnimator animation;

	ImageView TartgetView;
	
	public ImageViewYAxisFlipAnimation(Context _context) {
		super(_context);
		context = _context;
	}
	
	public ImageViewYAxisFlipAnimation(Context _context, int drawable) {
		super(_context);
		context = _context;
		CurrentDrawable = drawable;
	}
	
	public void FlipAnimation(ImageView view, int drawable){
		if(drawable != CurrentDrawable){
			Log.d(TAG,"FilpAnimation");
			TartgetView = view;
			animation = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_y_0_90);
			animation.setTarget(TartgetView);
			animation.setDuration(AnimationSpeed);
			animation.start();
			
			setNextDrawable(drawable);
			
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
					showNextDrawable();
					ObjectAnimator animation2 = (ObjectAnimator)AnimatorInflater.loadAnimator(context, R.anim.ani_flip_y_270_360);
					animation2.setTarget(TartgetView);
					animation2.setDuration(AnimationSpeed);
					animation2.start();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					Log.d(TAG,"onAnimationCancel");
				}
			});
			CurrentDrawable = NextDrawable;
		}
	}
	
	public void setNextDrawable(int drawable){
		NextDrawable = drawable;
	}
	
	public void showNextDrawable(){
		TartgetView.setImageResource(NextDrawable);
	}

}
