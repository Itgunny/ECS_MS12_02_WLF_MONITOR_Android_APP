package taeha.wheelloader.fseries_monitor.animation;

import taeha.wheelloader.fseries_monitor.main.Home;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class LeftRightShiftAnimation {
	
	final static private String TAG = "LeftRightShiftAnimation";
	
	final static private int AnimationSpeed = 200;
	
	int START_POSITION_X = 600;
	int START_POSITION_Y = -400;
	
	View Body;
	
	Home ParentAcitivty;
	
	Animation ShiftAnimation;
	
	public LeftRightShiftAnimation(Context _context, View _Body){
		ParentAcitivty = (Home)_context;
		
		Body = _Body;
		
		ShiftAnimation = new TranslateAnimation(START_POSITION_X, 0, START_POSITION_Y, 0);
		ShiftAnimation.setDuration(AnimationSpeed);
	}
	
	public LeftRightShiftAnimation(Context _context, View _Body, int _StartPositionX, int _StartPositionY){
		ParentAcitivty = (Home)_context;
		
		Body = _Body;
		
		START_POSITION_X = _StartPositionX;
		START_POSITION_Y = _StartPositionY;
		
		ShiftAnimation = new TranslateAnimation(START_POSITION_X, 0, START_POSITION_Y, 0);
		ShiftAnimation.setDuration(AnimationSpeed);
	}
	
	
	public void StartShiftAnimation(){
		Body.startAnimation(ShiftAnimation);
	}
}
