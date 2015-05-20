package taeha.wheelloader.fseries_monitor.animation;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;

public class BarAnimation{
	
	final private String TAG = "BarAnimation";
	
	final  private int Speed = 10;
	final  private int AnimationSpeed = 250;
	final  private int STEP			= 1;	
	
	public Home ParentActivity;
	
	View Body;
	
	// Timer
	private Timer ScaleTimer = null;
	int Scale;

	public BarAnimation(Context _context, View _Body, int Pos){
		ParentActivity = (Home)_context;
		
		Body = _Body;
		Scale = Pos;
		Body.setPivotX(0);	
		Body.setScaleX((float)(Pos/100.0));
		StartScaleTimer();
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		Log.d(TAG,"finalize");
		CancelScaleTimer();
	}

	///////////////////Scale Animation//////////////////////////
	public void SetScale(int Pos){
		Scale = Pos;
	}
	public int GetScale(){
		return Scale;
	}
	
	public void StartScaleTimer(){
		CancelScaleTimer();
		ScaleTimer = new Timer();
		ScaleTimer.schedule(new ScaleTimerClass(),1,Speed);	
	}
	
	public void CancelScaleTimer(){
		if(ScaleTimer != null){
			ScaleTimer.cancel();
			ScaleTimer.purge();
			ScaleTimer = null;
		}
	}
	
	private class ScaleTimerClass extends TimerTask{
		private int nScale;
		private int nCurrentScale = Scale;
		private int nEndPointScale;
		@Override
		public void run() {		
			ParentActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
				
					nEndPointScale = GetScale();
					if(nCurrentScale > nEndPointScale){		// Increase
						nCurrentScale -= STEP;
						Body.setScaleX((float)(nCurrentScale/100.0));
						
					}else if(nCurrentScale < nEndPointScale){	// Decrease
						nCurrentScale += STEP;
						Body.setScaleX((float)(nCurrentScale/100.0));
						
					}else{
						
					}
				
					
				}
			});
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////
	
}
