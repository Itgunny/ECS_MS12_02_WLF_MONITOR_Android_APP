package taeha.wheelloader.fseries_monitor.main;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;



public class LongPressChecker {			
	
	public interface OnLongPressListener{
		public void onLongPressed();
	}

	private Handler mHandler = new Handler();
	private LongPressCheckRunnable mLongPressCheckRunnable = new LongPressCheckRunnable();
	
	private int mLongPressTimeout;
	private int mScaledTouchSlope;
	
	private View mTargetView;
	private OnLongPressListener mOnLongPressListener;
	private boolean mLongPressed = false;
	
	private float mLastX;
	private float mLastY;
	
	public LongPressChecker( Context context ){
		if ( Looper.myLooper() != Looper.getMainLooper() )
			throw new RuntimeException();
		mLongPressTimeout = 1500;
	}
	public boolean getLongPressed(){
		return mLongPressed;
	}
	public void setOnLongPressListener( OnLongPressListener listener ){
		mOnLongPressListener = listener;
	}
	
	public void deliverMotionEvent( View v, MotionEvent event ){
		switch( event.getAction() ){
		case MotionEvent.ACTION_DOWN:
			mTargetView = v;
			mLastX = event.getX();
			mLastY = event.getY();
			startTimeout();
			break;
		case MotionEvent.ACTION_MOVE:
			float x = event.getX();
			float y = event.getY();
			if ( Math.abs( x - mLastX ) > v.getWidth() || Math.abs( y - mLastY ) > v.getHeight() )
				stopTimeout();
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			stopTimeout();
			break;
		}
	}
	
	public void startTimeout(){
		mLongPressed = false;
		mHandler.postDelayed( mLongPressCheckRunnable, mLongPressTimeout );
	}
	
	public void stopTimeout(){
		if ( !mLongPressed )
			mHandler.removeCallbacks( mLongPressCheckRunnable );
	}
	
	private class LongPressCheckRunnable implements Runnable{
		@Override
		public void run() {
			mLongPressed = true;
			if ( mOnLongPressListener != null ){
				mTargetView.performHapticFeedback( HapticFeedbackConstants.LONG_PRESS );
				mOnLongPressListener.onLongPressed();
			}
		}
	}
}