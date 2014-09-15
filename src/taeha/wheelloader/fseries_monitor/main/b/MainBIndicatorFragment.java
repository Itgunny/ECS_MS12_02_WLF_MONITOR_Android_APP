package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBIndicatorFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBIndicatorFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView imgViewWarmingUp;
	ImageView imgViewFuelWarmer;
	ImageView imgViewPreHeat;
	ImageView imgViewRideControl;
	ImageView imgViewFloatingMode;
	ImageView imgViewReverseFan;
	ImageView imgViewClutchCutOff;
	ImageView imgViewLockUpClutch;
	ImageView imgViewSeatBelt;
	ImageView imgViewEngineAutoShutdown;
	ImageView imgViewEngineDelayShutdown;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.indicator_main_b, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgViewWarmingUp = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_WarmingUp);
		imgViewFuelWarmer = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_FuelWarmer);
		imgViewPreHeat = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_PreHeat);
		imgViewRideControl = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_RideControl);
		imgViewFloatingMode = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_FloatingMode);
		imgViewReverseFan = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_ReverseFan);
		imgViewClutchCutOff = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_ClutchCutOff);
		imgViewLockUpClutch = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_LockUpClutch);
		imgViewSeatBelt = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_SeatBelt);
		imgViewEngineAutoShutdown = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_EngineAutoShutdown);
		imgViewEngineDelayShutdown = (ImageView)mRoot.findViewById(R.id.imageView_indicator_main_b_EngineDelayShutdown);
		
		imgViewWarmingUp.setAlpha((float)0.15);
		imgViewFuelWarmer.setAlpha((float)0.15);
		imgViewPreHeat.setAlpha((float)0.15);
		imgViewRideControl.setAlpha((float)0.15);
		imgViewFloatingMode.setAlpha((float)0.15);
		imgViewReverseFan.setAlpha((float)0.15);
		imgViewClutchCutOff.setAlpha((float)0.15);
		imgViewLockUpClutch.setAlpha((float)0.15);
		imgViewSeatBelt.setAlpha((float)0.15);
		imgViewEngineAutoShutdown.setAlpha((float)0.15);
		imgViewEngineDelayShutdown.setAlpha((float)0.15);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void DisableVirtualKey(){
		imgViewLockUpClutch.setY((float)57);
		imgViewSeatBelt.setY((float)57); 
		imgViewEngineAutoShutdown.setY((float)57); 
	}
	public void EnableVirtualKey(){
		imgViewLockUpClutch.setY((float)35);
		imgViewSeatBelt.setY((float)35); 
		imgViewEngineAutoShutdown.setY((float)35); 
	}
}