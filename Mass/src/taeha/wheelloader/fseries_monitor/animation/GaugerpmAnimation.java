package taeha.wheelloader.fseries_monitor.animation;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.main.Home;
import android.content.Context;
import android.view.View;

public class GaugerpmAnimation extends View{
	final private String TAG = "rpmAnimation";

	public Home ParentActivity;
	private Context context;

	private View viewrpmPoint;

	////// rpm Guage//////
	private float fDegree;
	private int nDegree;
	private int nRPM;

	////// Animation Speed////
	final static private int PointSpeed = 15;

	//////////////////////////

	// Timer
	private Timer viewrpmPointerTimer;

	public GaugerpmAnimation(Context _context, View Point) {
		super(_context);
		// TODO Auto-generated constructor stub

		viewrpmPoint = Point;
		context = _context;
		ParentActivity = (Home) _context;

		StartPointerTimer();

	}

	public int GetDegree(){
		return nDegree;
	}
	public int Getrpm(){
		return nRPM;
	}
	public void Setrpm(int rpm){
		if(rpm == 0xFFFF)
			rpm = 0;
		else if(rpm > 9999)
			rpm = 9999;

		SetrpmGuage(rpm);
	}

	//////////////////////////////////////// RPM Gauge//////////////////////////////////////////////////////////
	private float CalRPM(int rpm){
		float result;

		final int MAX = 4000;
		final int MIN = 0;

		if(rpm > MAX){
			rpm = MAX;
		}
		else if(rpm < MIN){
			rpm = MIN;
		}

		result = (rpm - 2000) / 16.66f;

		return result;

	}

	private void SetrpmGuage(int rpm){
		nRPM = rpm;
		rpm = rpm / 70;
		rpm = rpm * 70;
		fDegree = CalRPM(rpm);
		nDegree = (int)fDegree;
		//Log.d(TAG,"nDegree: " + Integer.toString(nDegree));
	}


	public void StartPointerTimer(){
		CancelviewrpmPointerTimer();
		viewrpmPointerTimer = new Timer();
		viewrpmPointerTimer.schedule(new viewrpmPointerTimerClass(),1,PointSpeed);	
	}

	public void CancelviewrpmPointerTimer(){
		if(viewrpmPointerTimer != null){
			viewrpmPointerTimer.cancel();
			viewrpmPointerTimer.purge();
			viewrpmPointerTimer = null;
		}
	}

	public class viewrpmPointerTimerClass extends TimerTask{
		private int nCurrentPosition;
		private int nEndPosition;
		@Override
		public void run() {		
			ParentActivity.runOnUiThread(new Runnable() {
				@Override
				public void run() {

					nEndPosition = GetDegree();

					if(nCurrentPosition > nEndPosition){		// Increase
						nCurrentPosition -= 1;
						viewrpmPoint.setPivotX(20);
						viewrpmPoint.setPivotY(120);
						viewrpmPoint.setRotation(nCurrentPosition);
					}else if(nCurrentPosition < nEndPosition){	// Decrease
						nCurrentPosition += 1;
						viewrpmPoint.setPivotX(20);
						viewrpmPoint.setPivotY(120);
						viewrpmPoint.setRotation(nCurrentPosition);
					}else{

					}

				}
			});
		}

	}
}
