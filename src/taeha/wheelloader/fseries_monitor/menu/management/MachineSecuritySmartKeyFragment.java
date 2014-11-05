package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MachineSecuritySmartKeyFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

	TextView textViewRegistration;
	TextView textViewDelete;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
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
		 TAG = "MachineSecuritySmartKeyFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_smartkey, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Smart_Key));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_smartkey_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_smartkey_low_cancel);
	
		textViewRegistration = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_smartkey_tag_registration);
		textViewDelete = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_smartkey_tag_delete);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		textViewRegistration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRegistration();
			}
		});
		textViewDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDelete();
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
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();

	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();
	}
	public void ClickRegistration(){
		
	}
	public void ClickDelete(){
		
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
