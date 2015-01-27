package taeha.wheelloader.fseries_monitor.main.b;


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
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBVirtualKeyFragment extends ParentFragment{
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
		TAG = "MainBVirtualKeyFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.lower_main_b_virtualkey, null);
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
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_lower_main_b_virtualkey);
		imgbtnKeyPad = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_keypad);
		
		imgbtnMainLight = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_mainlight);
		imgbtnWorkLight = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_worklight);
		imgbtnAutoGrease = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_autogrease);
		imgbtnQuickCoupler = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_quickcoupler);
		imgbtnRideControl = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_ridecontrol);
		imgbtnWorkLoad = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_workload);
		imgbtnBeaconLamp = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_beaconlamp);
		imgbtnRearWiper = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_rearwiper);
		imgbtnMirrorHeat = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_mirrorheat);
		imgbtnDetent = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_detent);
		imgbtnFineModulation = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_finemodulation);
		imgbtnFN = (ImageButton)mRoot.findViewById(R.id.imageButton_lower_main_b_virtualkey_fn);
		
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
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		FineModulsationDisplay();
	}
	/////////////////////////////////////////////////////////////////////	
	public void FineModulsationDisplay(){
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			imgbtnFineModulation.setAlpha((float)0.2);
		}else{
			imgbtnFineModulation.setAlpha((float)1);
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickKeyPad(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		if(bScreenOnFlag == true){
			ParentActivity._MainBBaseFragment.showVirtualKeytoQuickScreenAnimation();
			layoutBG.setBackgroundResource(R.drawable.main_virtual_key_bg_up);
			bScreenOnFlag = false;
			ParentActivity._MainBBaseFragment._MainBLeftUpQuickFragment.Clickable(true);
			ParentActivity._MainBBaseFragment._MainBLeftDownQuickFragment.Clickable(true);
			ParentActivity._MainBBaseFragment._MainBRightUpQuickFragment.Clickable(true);
			ParentActivity._MainBBaseFragment._MainBRightDownQuickFragment.Clickable(true);
			ParentActivity._MainBBaseFragment._MainBCenterQuickFragment.Clickable(true);
		}else{
			ParentActivity._MainBBaseFragment.showVritualKeyScreenAnimation();
			layoutBG.setBackgroundResource(R.drawable.main_virtual_key_bg_down);
			bScreenOnFlag = true;
			
			ParentActivity._MainBBaseFragment._MainBLeftUpQuickFragment.Clickable(false);
			ParentActivity._MainBBaseFragment._MainBLeftDownQuickFragment.Clickable(false);
			ParentActivity._MainBBaseFragment._MainBRightUpQuickFragment.Clickable(false);
			ParentActivity._MainBBaseFragment._MainBRightDownQuickFragment.Clickable(false);
			ParentActivity._MainBBaseFragment._MainBCenterQuickFragment.Clickable(false);
		}
		


	}
	public void ClickMainLight(){
		ParentActivity._MainBBaseFragment.showMainLightAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickWorkLight(){
		ParentActivity._MainBBaseFragment.showWorkLightAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickAutoGrease(){
		ParentActivity._MainBBaseFragment.showAutoGreaseAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickQuickCoupler(){
		ParentActivity._MainBBaseFragment.showQuickCouplerAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickRideControl(){
		ParentActivity._MainBBaseFragment.showRideControlAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickWorkLoad(){
		ParentActivity._MainBBaseFragment.showWorkLoadAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickBeaconLamp(){
		ParentActivity._MainBBaseFragment.showBeaconLampAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickRearWiper(){
		ParentActivity._MainBBaseFragment.showRearWiperAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickMirrorHeat(){
		ParentActivity._MainBBaseFragment.showMirrorHeatAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickDetent(){
		ParentActivity._MainBBaseFragment.showDetentAnimation();
		ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
	}
	public void ClickFineModulation(){
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			
		}else{
			ParentActivity._MainBBaseFragment.showFineModulationAnimation();
			ParentActivity._MainBBaseFragment.IndicatorChangeAnimation.StartAppearAnimation(ParentActivity._MainBBaseFragment._MainBIndicatorFragment);
		}
		
	}
	public void ClickFN(){
		
	}
	
	///////////////////////Timer/////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}