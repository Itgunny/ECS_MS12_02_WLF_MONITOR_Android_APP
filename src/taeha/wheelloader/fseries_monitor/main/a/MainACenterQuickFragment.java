package taeha.wheelloader.fseries_monitor.main.a;

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
	
	ImageButton imgbtnMain;
	
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
		imgbtnMain = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_main);
		
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
		imgbtnMain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickBG();
			}
		});
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
		
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMain.setClickable(ClickFlag);
		imgbtnSmartTerminal.setClickable(ClickFlag);
		imgbtnMedia.setClickable(ClickFlag);
		imgbtnKeypad.setClickable(ClickFlag);
	}
	
	public void Clickable(boolean flag){
		imgbtnMain.setClickable(flag);
		imgbtnSmartTerminal.setClickable(flag);
		imgbtnMedia.setClickable(flag);
		imgbtnKeypad.setClickable(flag);
	}
}
