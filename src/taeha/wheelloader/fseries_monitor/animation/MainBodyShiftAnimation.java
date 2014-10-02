package taeha.wheelloader.fseries_monitor.animation;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;

public class MainBodyShiftAnimation{
	
	final private String TAG = "MainBodyShiftAnimation";
	
	final private int SHIFT_POSITION_RIGHT_UP_X = -345;
	final private int SHIFT_POSITION_RIGHT_UP_Y = 106;
	
	final private int SHIFT_POSITION_RIGHT_DOWN_X = -345;
	final private int SHIFT_POSITION_RIGHT_DOWN_Y = -140;
	
	final private int SHIFT_POSITION_LEFT_DOWN_X = 345;
	final private int SHIFT_POSITION_LEFT_DOWN_Y = -166;
	
	final  private int SHIFT_POSITION_LEFT_UP_X = 345;
	final  private int SHIFT_POSITION_LEFT_UP_Y = 106;
	
	final  private int ShiftSpeed = 2;
	final  private int AnimationSpeed = 250;
	final  private int MOVE_STEP_X			= 5;	
	final  private int MOVE_STEP_Y			= 2;	
	
	public Home ParentActivity;
	
	View Body;
	
	// Timer
	private Timer ShiftLayoutTimer = null;
	int PositionX = 0;
	int PositionY = 0;
	
	public MainBodyShiftAnimation(Context _context, View _Body){
		ParentActivity = (Home)_context;
		
		Body = _Body;

				
		StartShiftLayoutTimer();
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		Log.d(TAG,"finalize");
		CancelShiftLayoutTimer();
	}

	///////////////////Shift Animation//////////////////////////
	public void StartShiftZeroAnimation(){
		SetShiftPositionX(0);
		SetShiftPositionY(0);
	}	
	public void StartShiftRightUpAnimation(){
		SetShiftPositionX(SHIFT_POSITION_RIGHT_UP_X);
		SetShiftPositionY(SHIFT_POSITION_RIGHT_UP_Y);
	}
	public void StartShiftRightDownAnimation(){
		SetShiftPositionX(SHIFT_POSITION_RIGHT_DOWN_X);
		SetShiftPositionY(SHIFT_POSITION_RIGHT_DOWN_Y);
	}
	public void StartShiftLeftDownAnimation(){
		SetShiftPositionX(SHIFT_POSITION_LEFT_DOWN_X);
		SetShiftPositionY(SHIFT_POSITION_LEFT_DOWN_Y);
	}
	public void StartShiftLeftUpAnimation(){
		SetShiftPositionX(SHIFT_POSITION_LEFT_UP_X);
		SetShiftPositionY(SHIFT_POSITION_LEFT_UP_Y);
	}
	public void SetShiftPositionX(int Pos){
		PositionX = Pos;
	}
	public int GetShiftPositionX(){
		return PositionX;
	}
	public void SetShiftPositionY(int Pos){
		PositionY = Pos;
	}
	public int GetShiftPositionY(){
		return PositionY;
	}
	
	public void StartShiftLayoutTimer(){
		CancelShiftLayoutTimer();
		ShiftLayoutTimer = new Timer();
		ShiftLayoutTimer.schedule(new ShiftLayoutTimerClass(),1,ShiftSpeed);	
	}
	
	public void CancelShiftLayoutTimer(){
		if(ShiftLayoutTimer != null){
			ShiftLayoutTimer.cancel();
			ShiftLayoutTimer.purge();
			ShiftLayoutTimer = null;
		}
	}
	
	private class ShiftLayoutTimerClass extends TimerTask{
		private int nCurrentPositionX;
		private int nEndPositionX;
		private int nCurrentPositionY;
		private int nEndPositionY;
		@Override
		public void run() {		
			ParentActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
				
					nEndPositionX = GetShiftPositionX();
					nEndPositionY = GetShiftPositionY();
					
					if(nCurrentPositionX > nEndPositionX){		// Increase
						nCurrentPositionX -= MOVE_STEP_X;

						Body.setTranslationX(nCurrentPositionX);
						
						
					}else if(nCurrentPositionX < nEndPositionX){	// Decrease
						nCurrentPositionX += MOVE_STEP_X;
						Body.setTranslationX(nCurrentPositionX);
						
					}else{
						
					}
					
					if(nCurrentPositionY > nEndPositionY){		// Increase
						nCurrentPositionY -= MOVE_STEP_Y;

						Body.setTranslationY(nCurrentPositionY);
						
						
					}else if(nCurrentPositionY < nEndPositionY){	// Decrease
						nCurrentPositionY += MOVE_STEP_Y;
						Body.setTranslationY(nCurrentPositionY);
						
					}else{
						
					}
					
					
				}
			});
		}
		
	}
	//////////////////////////////////////////////////////////////////////////////
	
}
