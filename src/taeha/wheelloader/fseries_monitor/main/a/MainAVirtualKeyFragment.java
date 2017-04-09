package taeha.wheelloader.fseries_monitor.main.a;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainAVirtualKeyFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout layoutBG;
	
	ImageButton imgbtnKeyPad;
	
	ImageButton imgbtnMainLight;
	ImageButton imgbtnWorkLight;
	ImageButton imgbtnAutoGrease;
	ImageButton imgbtnQuickCoupler;
	ImageButton imgbtnRideControl;
	ImageButton imgbtnWorkLoad;
	ImageButton imgbtnBeaconLamp;
	ImageButton imgbtnRearWiper;
	ImageButton imgbtnMirrorHeat;
	ImageButton imgbtnDetent;
	ImageButton imgbtnFineModulation;
	ImageButton imgbtnFN;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	public boolean bScreenOnFlag;
	
	int AutoGrease;
	int Quickcoupler;
	int RideControl;
	int BeaconLamp;
	int MirrorHeat;
	int FineModulation;	
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
		TAG = "MainAVirtualKeyFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.lower_main_a_virtualkey, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG,"onPause");
	
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
	
	////////////////////////////////////////////////
	
	


	//Common Function//////////////////////////////
	
	
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_lower_main_a_virtualkey);
		imgbtnKeyPad = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_keypad);
		
		imgbtnMainLight = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_mainlight);
		imgbtnWorkLight = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_worklight);
		imgbtnAutoGrease = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_autogrease);
		imgbtnQuickCoupler = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_quickcoupler);
		imgbtnRideControl = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_ridecontrol);
		imgbtnWorkLoad = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_workload);
		imgbtnBeaconLamp = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_beaconlamp);
		imgbtnRearWiper = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_rearwiper);
		imgbtnMirrorHeat = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_mirrorheat);
		imgbtnDetent = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_detent);
		imgbtnFineModulation = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_finemodulation);
		imgbtnFN = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_a_virtualkey_fn);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		bScreenOnFlag = false;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
//		layoutBG.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		imgbtnKeyPad.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickKeyPad();
			}
		});
		imgbtnMainLight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMainLight();
			}
		});
		imgbtnWorkLight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWorkLight();
			}
		});
		imgbtnAutoGrease.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAutoGrease();
			}
		});
		imgbtnQuickCoupler.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickQuickCoupler();
			}
		});
		imgbtnRideControl.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRideControl();
			}
		});
		imgbtnWorkLoad.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWorkLoad();
			}
		});
		imgbtnBeaconLamp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBeaconLamp();
			}
		});
		imgbtnRearWiper.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRearWiper();
			}
		});
		imgbtnMirrorHeat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMirrorHeat();
			}
		});
		imgbtnDetent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDetent();
			}
		});
		imgbtnFineModulation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickFineModulation();
			}
		});
		imgbtnFN.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickFN();
			}
		});

	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AutoGrease = CAN1Comm.Get_AutoGreaseOperationStatus_3449_PGN65527();
		Quickcoupler = CAN1Comm.Get_QuickCouplerOperationStatus_3448_PGN65527();
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
		BeaconLamp = CAN1Comm.Get_BeaconLampOperationStatus_3444_PGN65527();
		MirrorHeat = CAN1Comm.Get_MirrorHeatOperationStatus_3450_PGN65527();
		FineModulation = CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		FineModulationDisplay(FineModulation);
		AutoGreaseDisplay(AutoGrease);
		QuickcouplerDisplay(Quickcoupler);
		RideControlDisplay(RideControl);
		BeaconLampDisplay(BeaconLamp);
		MirrorHeatDisplay(MirrorHeat);
//		FNKeyDisplay(ParentActivity.LockEntertainment);
	}
	/////////////////////////////////////////////////////////////////////	
	public void FineModulationDisplay(int Data){
		if(Data != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
			imgbtnFineModulation.setAlpha((float)0.5);
		}else{
			imgbtnFineModulation.setAlpha((float)1);
		}
	}
	public void AutoGreaseDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			imgbtnAutoGrease.setAlpha((float)0.5);
			break;
		default:
			imgbtnAutoGrease.setAlpha((float)1);
			break;
		}
	}
	public void QuickcouplerDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			imgbtnQuickCoupler.setAlpha((float)0.5);
			break;
		default:
			imgbtnQuickCoupler.setAlpha((float)1);
			break;
		}
	}
	public void RideControlDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			imgbtnRideControl.setAlpha((float)0.5);
			break;
		default:
			imgbtnRideControl.setAlpha((float)1);
			break;
		}
	}
	public void BeaconLampDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			imgbtnBeaconLamp.setAlpha((float)0.5);
			break;
		default:
			imgbtnBeaconLamp.setAlpha((float)1);
			break;
		}
	}
	public void MirrorHeatDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			imgbtnMirrorHeat.setAlpha((float)0.5);
			break;
		default:
			imgbtnMirrorHeat.setAlpha((float)1);
			break;
		}
	}                                                     
	public void FNKeyDisplay(int Data){
		switch (Data){
		case Home.STATE_USERSWITCHING_LOCK:
			imgbtnFN.setAlpha((float)0.5);
			break;
		default:
			imgbtnFN.setAlpha((float)1);
			break;
		}
	} 
	/////////////////////////////////////////////////////////////////////
	public void ClickKeyPad(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainABaseFragment.showVirtualKeytoQuickScreenAnimation();
		bScreenOnFlag = false;
		ParentActivity._MainABaseFragment._MainALeftQuickFragment.Clickable(true);
		ParentActivity._MainABaseFragment._MainARightQuickFragment.Clickable(true);
		ParentActivity._MainABaseFragment._MainACenterQuickFragment.Clickable(true);
	}
	public void ClickMainLight(){
		ParentActivity._MainABaseFragment.showMainLightAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickWorkLight(){
		ParentActivity._MainABaseFragment.showWorkLightAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickAutoGrease(){
		ParentActivity._MainABaseFragment.showAutoGreaseAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickQuickCoupler(){
		ParentActivity._MainABaseFragment.showQuickCouplerAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickRideControl(){
		ParentActivity._MainABaseFragment.showRideControlAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickWorkLoad(){
		ParentActivity._MainABaseFragment.showWorkLoadAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickBeaconLamp(){
		ParentActivity._MainABaseFragment.showBeaconLampAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickRearWiper(){
		ParentActivity._MainABaseFragment.showRearWiperAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickMirrorHeat(){
		ParentActivity._MainABaseFragment.showMirrorHeatAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickDetent(){
		ParentActivity._MainABaseFragment.showDetentAnimation();
		ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
	}
	public void ClickFineModulation(){
//		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1Comm.STATE_COMPONENTCODE_EHCU){
//			ParentActivity.showFineModulation();
//		}else{
			ParentActivity._MainABaseFragment.showFineModulationAnimation();
			ParentActivity._MainABaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainABaseFragment._MainAIndicatorFragment);
//		}
		
	}
	public void ClickFN(){
		CAN1Comm.ClickFN();
	}
	
	///////////////////////Timer/////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}