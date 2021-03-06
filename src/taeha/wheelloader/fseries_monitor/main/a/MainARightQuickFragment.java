package taeha.wheelloader.fseries_monitor.main.a;

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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainARightQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBGUp;
	RelativeLayout LayoutBGDown;
	
	ImageView imgViewIconFault;
	ImageView imgViewIconMaint;
	
	TextFitTextView textViewTitleFault;
	TextFitTextView textViewTitleMaint;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int FaultCode;
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
		TAG = "MainARightQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.right_main_a_quick, null);
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
		LayoutBGUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightup_main_a_quick);
		LayoutBGDown = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_a_quick);
		
		imgViewIconFault = (ImageView)mRoot.findViewById(R.id.imageView_rightup_main_a_quick_icon);
		imgViewIconMaint = (ImageView)mRoot.findViewById(R.id.imageView_rightdown_main_a_quick_icon);

		textViewTitleFault = (TextFitTextView)mRoot.findViewById(R.id.textView_rightup_main_a_quick_title);	
		textViewTitleFault.setText(getString(ParentActivity.getResources().getString(R.string.Active_Fault), 17));
		textViewTitleMaint = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_a_quick_title);
		textViewTitleMaint.setText(getString(ParentActivity.getResources().getString(R.string.Maintenance), 18));
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurDisplayDetail(ParentActivity._MainABaseFragment.CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
		LayoutBGUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 6;
				ClickActiveFault();
			}
		});		
		
		LayoutBGDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 7;
				ClickMaintenance();
			}
		});
		
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplayFault(FaultCode);
		IconDisplayMaint(Maint);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickActiveFault(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP);
	}
	
	public void ClickMaintenance(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP);
		
	}
	public void IconDisplayFault(int _data){
		if(_data == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgViewIconFault.setImageResource(R.drawable.main_quick_icon_fault_red);
		}else{
			imgViewIconFault.setImageResource(R.drawable.main_quick_icon_fault);
		}
	}
	public void IconDisplayMaint(int _data){
		if(_data == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgViewIconMaint.setImageResource(R.drawable.main_quick_icon_maint_red);
		}else{
			imgViewIconMaint.setImageResource(R.drawable.main_quick_icon_maint);
		}
	}
	public void Clickable(boolean flag){
		LayoutBGUp.setClickable(flag);
		LayoutBGDown.setClickable(flag);
	}

	public void CursurDisplayDetail(int index){
		LayoutBGUp.setBackgroundResource(R.drawable._selector_rightup_quick_btn2);
		LayoutBGDown.setBackgroundResource(R.drawable._selector_rightdown_quick_btn2);
		switch(index){
			case 6:
				LayoutBGUp.setBackgroundResource(R.drawable.main_b_quick_fault_s_02);
				break;
			case 7:
				LayoutBGDown.setBackgroundResource(R.drawable.main_b_quick_maintenance_s_02);
				break;
		}
	}
}