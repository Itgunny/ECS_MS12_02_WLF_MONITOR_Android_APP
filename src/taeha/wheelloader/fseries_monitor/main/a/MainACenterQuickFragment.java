package taeha.wheelloader.fseries_monitor.main.a;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainACenterQuickFragment extends MainACenterFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnSmartTerminal;
	ImageButton imgbtnMedia;
	ImageButton imgbtnKeypad;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Maint;
	int FaultCode;
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
		TAG = "MainACenterQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_a_quick, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		//super.InitResource();
		
		imgbtnSmartTerminal = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_mirror);
		imgbtnMedia = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_mediaplayer);
		imgbtnKeypad = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_keypad);
		imgbtnOption = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_option);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
		imgbtnSmartTerminal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMirror();
			}
		});
		imgbtnMedia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMultimedia();
			}
		});
		imgbtnKeypad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickKeypad();
			}
		});		
		imgbtnOption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickBG();
			}
		});
	}
	
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplay(FaultCode,Maint);
	}

	/////////////////////////////////////////////////////////////////////	
	
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainABaseFragment.showDefaultScreenAnimation();
		Log.d(TAG,"ClickOption");
	}
	public void ClickMirror(){
		ParentActivity.KillApps("com.mxtech.videoplayer.ad");
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.example.wfdsink");
		if(intent != null)
			startActivity(intent);
	}
	public void ClickMultimedia(){
		if(CAN1Comm.GetrpmFlag() == false)
		{
			CAN1Comm.SetPlayerFlag(true);
			
			ParentActivity.KillApps("com.example.wfdsink");
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
			if(intent != null){
				ParentActivity.startActivity(intent);
			}
		}
	}	
	public void ClickKeypad(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainABaseFragment.showVritualKeyScreenAnimation();
		ParentActivity._MainABaseFragment._MainALeftQuickFragment.Clickable(false);
		ParentActivity._MainABaseFragment._MainARightQuickFragment.Clickable(false);
		Clickable(false);
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnOption.setClickable(ClickFlag);
		imgbtnSmartTerminal.setClickable(ClickFlag);
		imgbtnMedia.setClickable(ClickFlag);
		imgbtnKeypad.setClickable(ClickFlag);
	}
	
	public void Clickable(boolean flag){
		imgbtnOption.setClickable(flag);
		imgbtnSmartTerminal.setClickable(flag);
		imgbtnMedia.setClickable(flag);
		imgbtnKeypad.setClickable(flag);
	}
}
