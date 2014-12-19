package taeha.wheelloader.fseries_monitor.animation;

import taeha.wheelloader.fseries_monitor.main.Home;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
public class EndingAnimation extends View{
	final static private String TAG = "EndingAnimation";
	
	final static private int AnimationSpeed = 1000;
	
	public Home ParentActivity;
	private Context context;
	
	Animation animation;

	View viewTarget;

	
	public EndingAnimation(Context _context, View Target) {
		super(_context);
		// TODO Auto-generated constructor stub
		context = _context;
		ParentActivity = (Home) _context;
		viewTarget = Target;
		
		animation  = new ScaleAnimation((float)1,(float)1 , (float)1, (float)0.1, (float)400, (float)240);
		animation.setDuration(AnimationSpeed);
		viewTarget.startAnimation(animation);
		
		
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				
				
			}
		});
	}
	
	public void Ending(){
		animation.start();
	}
}
