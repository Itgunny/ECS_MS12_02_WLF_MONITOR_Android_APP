package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import taeha.wheelloader.fseries_monitor.animation.ImageViewYAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBIndicatorFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	
	private  final float DARK = (float)0.15;
	private  final float LIGHT = (float)1;
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
	int WarmingUp;
	int FuelWarmer;
	int PreHeat;
	int RideControl;
	int FloatingMode;
	int ReverseFan;
	int ClutchCutOff;
	int LockUpClutch;
	int SeatBelt;
	int EngineAutoShutdown;
	int EngineDelayShutdown;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	ImageViewYAxisFlipAnimation WarmingUpAnimation;
	ImageViewYAxisFlipAnimation FuelWarmerAnimation;
	ImageViewYAxisFlipAnimation PreHeatAnimation;
	ImageViewYAxisFlipAnimation RideControlAnimation;
	ImageViewYAxisFlipAnimation FloatingModeAnimation;
	ImageViewYAxisFlipAnimation ReverseFanAnimation;
	ImageViewYAxisFlipAnimation ClutchCutOffAnimation;
	ImageViewYAxisFlipAnimation LockUpClutchAnimation;
	ImageViewYAxisFlipAnimation SeatBeltAnimation;
	ImageViewYAxisFlipAnimation EngineAutoShutdownAnimation;
	ImageViewYAxisFlipAnimation EngineDelayShutdownAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBIndicatorFragment";
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
		
		imgViewWarmingUp.setAlpha(DARK);
		imgViewFuelWarmer.setAlpha(DARK);
		imgViewPreHeat.setAlpha(DARK);
		imgViewRideControl.setAlpha(DARK);
		imgViewFloatingMode.setAlpha(DARK);
		imgViewReverseFan.setAlpha(DARK);
		imgViewClutchCutOff.setAlpha(DARK);
		imgViewLockUpClutch.setAlpha(DARK);
		imgViewSeatBelt.setAlpha(DARK);
		imgViewEngineAutoShutdown.setAlpha(DARK);
		imgViewEngineDelayShutdown.setAlpha(DARK);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		WarmingUpAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		FuelWarmerAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		PreHeatAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		RideControlAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		FloatingModeAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		ReverseFanAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		ClutchCutOffAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		LockUpClutchAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		SeatBeltAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		EngineAutoShutdownAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);
		EngineDelayShutdownAnimation = new ImageViewYAxisFlipAnimation(ParentActivity);		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
		WarmingUp = CAN1Comm.Get_WarmingUpStatus_804_PGN65428();
		FuelWarmer = CAN1Comm.Get_FuelWarmerActiveStatus_326_PGN65428();
		PreHeat = CAN1Comm.Get_EnginePreheatStatus_323_PGN65428();
		RideControl = CAN1Comm.Get_RideControlStatus_550_PGN65428();
		FloatingMode = CAN1Comm.Get_FloatMode_2316_PGN65517();
		ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
		ClutchCutOff = CAN1Comm.Get_ClutchCutoffStatus_545_PGN65428();
		LockUpClutch = CAN1Comm.Get_TransmissionTCLockupEngaged_556_PGN65428();
		//SeatBelt;
		EngineAutoShutdown = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN65428();
		EngineDelayShutdown = CAN1Comm.Get_DelayedEngineShutdown_365_PGN65428();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		WarmingUpDisplay(WarmingUp);
		FuelWarmerDisplay(FuelWarmer);
		PreHeatDisplay(PreHeat);
		RideControlDisplay(RideControl);
		FloatingModeDisplay(FloatingMode);
		ReverseFanDisplay(ReverseFan);
		ClutchCutOffDisplay(ClutchCutOff);
		LockUpClutchDisplay(LockUpClutch);
		SeatBeltDisplay(ParentActivity.SeatBelt);
		EngineAutoShutdownDisplay(EngineAutoShutdown);
		EngineDelayShutdownDisplay(EngineDelayShutdown);
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void WarmingUpDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			WarmingUpAnimation.FlipAnimation(imgViewWarmingUp, R.drawable.main_indicator_warmimgup);
			imgViewWarmingUp.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			WarmingUpAnimation.FlipAnimation(imgViewWarmingUp, R.drawable.main_indicator_warmimgup_on);
			imgViewWarmingUp.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void FuelWarmerDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			FuelWarmerAnimation.FlipAnimation(imgViewFuelWarmer, R.drawable.main_indicator_fuelwamer);
			imgViewFuelWarmer.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			FuelWarmerAnimation.FlipAnimation(imgViewFuelWarmer, R.drawable.main_indicator_fuelwamer_on);
			imgViewFuelWarmer.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void PreHeatDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			PreHeatAnimation.FlipAnimation(imgViewPreHeat, R.drawable.main_indicator_preheat);
			imgViewPreHeat.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			PreHeatAnimation.FlipAnimation(imgViewPreHeat, R.drawable.main_indicator_preheat_on);
			imgViewPreHeat.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void RideControlDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_RIDECONTROL_OFF:
			RideControlAnimation.FlipAnimation(imgViewRideControl, R.drawable.main_indicator_rideoff);
			imgViewRideControl.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_RIDECONTROL_ON:
			RideControlAnimation.FlipAnimation(imgViewRideControl, R.drawable.main_indicator_rideon_on);
			imgViewRideControl.setAlpha(LIGHT);
			break;
		case CAN1CommManager.DATA_STATE_RIDECONTROL_AUTO:
			RideControlAnimation.FlipAnimation(imgViewRideControl, R.drawable.main_indicator_rideauto_on);
			imgViewRideControl.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void FloatingModeDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			FloatingModeAnimation.FlipAnimation(imgViewFloatingMode, R.drawable.main_indicator_floatmode);
			imgViewFloatingMode.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			FloatingModeAnimation.FlipAnimation(imgViewFloatingMode, R.drawable.main_indicator_floatmode_on);
			imgViewFloatingMode.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void ReverseFanDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_COOLINGFAN_OFF:
			ReverseFanAnimation.FlipAnimation(imgViewReverseFan, R.drawable.main_indicator_fanreverse);
			imgViewReverseFan.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_FORWARD:
			ReverseFanAnimation.FlipAnimation(imgViewReverseFan, R.drawable.main_indicator_fanreverse);
			imgViewReverseFan.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_REVERSE:
			ReverseFanAnimation.FlipAnimation(imgViewReverseFan, R.drawable.main_indicator_fanreverse_on);
			imgViewReverseFan.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void ClutchCutOffDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			ClutchCutOffAnimation.FlipAnimation(imgViewClutchCutOff, R.drawable.main_indicator_clutchcutoff);
			imgViewClutchCutOff.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			ClutchCutOffAnimation.FlipAnimation(imgViewClutchCutOff, R.drawable.main_indicator_clutchcutoff_on);
			imgViewClutchCutOff.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void LockUpClutchDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			LockUpClutchAnimation.FlipAnimation(imgViewLockUpClutch, R.drawable.main_indicator_lockupclutch);
			imgViewLockUpClutch.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			LockUpClutchAnimation.FlipAnimation(imgViewLockUpClutch, R.drawable.main_indicator_lockupclutch_on);
			imgViewLockUpClutch.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void SeatBeltDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			SeatBeltAnimation.FlipAnimation(imgViewSeatBelt, R.drawable.main_indicator_seatbelt);
			imgViewSeatBelt.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			SeatBeltAnimation.FlipAnimation(imgViewSeatBelt, R.drawable.main_indicator_seatbelt_on);
			imgViewSeatBelt.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void EngineAutoShutdownDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			EngineAutoShutdownAnimation.FlipAnimation(imgViewEngineAutoShutdown, R.drawable.main_indicator_engineautoshutdown);
			imgViewEngineAutoShutdown.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			EngineAutoShutdownAnimation.FlipAnimation(imgViewEngineAutoShutdown, R.drawable.main_indicator_engineautoshutdown_on);
			imgViewEngineAutoShutdown.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
	public void EngineDelayShutdownDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			EngineDelayShutdownAnimation.FlipAnimation(imgViewEngineDelayShutdown, R.drawable.main_indicator_enginedelayshutdown);
			imgViewEngineDelayShutdown.setAlpha(DARK);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			EngineDelayShutdownAnimation.FlipAnimation(imgViewEngineDelayShutdown, R.drawable.main_indicator_enginedelayshutdown_on);
			imgViewEngineDelayShutdown.setAlpha(LIGHT);
			break;
		default:
			break;
		}
	}
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