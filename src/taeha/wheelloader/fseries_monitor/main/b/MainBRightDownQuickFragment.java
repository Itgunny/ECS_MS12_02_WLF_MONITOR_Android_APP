package taeha.wheelloader.fseries_monitor.main.b;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBRightDownQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	
	ImageView imgViewIcon;
	
	TextView textViewTitle;
	
	ImageButton imgbtnMultimedia;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Maint;
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
		TAG = "MainBRightDownQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_b_quick, null);
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
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_b_quick);
		
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_rightdown_main_b_quick_icon);

		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_quick_title);
		
		imgbtnMultimedia = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_quick_multimedia);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		LayoutBG.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMaintenance();
			}
		});
		imgbtnMultimedia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMultimedia();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplay(Maint);
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void ClickMaintenance(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_B_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP);
		
	}
	public void ClickMultimedia(){
		if(CAN1Comm.GetrpmFlag() == false)	// ++, --, 150211 bwk
		{
			CAN1Comm.SetPlayerFlag(true);	// ++, --, 150211 bwk
			
			ParentActivity.KillApps("com.example.wfdsink");
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
			if(intent != null){
				ParentActivity.startActivity(intent);
			}
		}
	}
	public void IconDisplay(int _data){
		if(_data == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgViewIcon.setImageResource(R.drawable.main_quick_icon_maint_red);
		}else{
			imgViewIcon.setImageResource(R.drawable.main_quick_icon_maint);
		}
	}
	public void Clickable(boolean flag){
		LayoutBG.setClickable(flag);
		imgbtnMultimedia.setClickable(flag);
	}
	
}