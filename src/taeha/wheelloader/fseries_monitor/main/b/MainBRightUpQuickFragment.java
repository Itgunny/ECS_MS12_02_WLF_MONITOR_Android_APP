package taeha.wheelloader.fseries_monitor.main.b;

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

public class MainBRightUpQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	
	ImageView imgViewIcon;
	
	TextView textViewTitle;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
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
		TAG = "MainBRightUpQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightup_main_b_quick, null);
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
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightup_main_b_quick);
		
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_rightup_main_b_quick_icon);

		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_quick_title);
		
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
				ClickActiveFault();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplay(FaultCode);
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void ClickActiveFault(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_B_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP);
	}
	public void IconDisplay(int _data){
		if(_data == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgViewIcon.setImageResource(R.drawable.main_quick_icon_fault_red);
		}else{
			imgViewIcon.setImageResource(R.drawable.main_quick_icon_fault);
		}
	}
	
}