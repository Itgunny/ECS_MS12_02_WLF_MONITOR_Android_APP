package taeha.wheelloader.fseries_monitor.main.a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainALeftQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBGUp;
	RelativeLayout LayoutBGDown;
	
	ImageView imgViewIconUser;
	ImageView imgViewIconHelp;
	
	TextFitTextView textViewTitleUser;
	TextFitTextView textViewTitleHelp;
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
		TAG = "MainALeftQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.left_main_a_quick, null);
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
		LayoutBGUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_a_quick);
		LayoutBGDown = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftdown_main_a_quick);
		
		imgViewIconUser = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_a_quick_icon);
		imgViewIconHelp = (ImageView)mRoot.findViewById(R.id.imageView_leftdown_main_a_quick_icon);

		textViewTitleUser = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_main_a_quick_title);
		textViewTitleUser.setText(getString(ParentActivity.getResources().getString(R.string.User_Switching), 116));
		
		textViewTitleHelp = (TextFitTextView)mRoot.findViewById(R.id.textView_leftdown_main_a_quick_title);
		textViewTitleHelp.setText(getString(ParentActivity.getResources().getString(R.string.Help), 117));
		
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
				ParentActivity._MainABaseFragment.CursurIndex = 1;
				ClickUserSwitching();
			}
		});
		
		LayoutBGDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 2;
				ParentActivity._MainABaseFragment.CursurDisplay(ParentActivity._MainABaseFragment.CursurIndex);
				ClickHelp();
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
	
	public void ClickUserSwitching(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MenuBaseFragment);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_USERSWITCHING_TOP);
	}
	public void ClickHelp(){
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("org.ebookdroid");
		if(intent != null)
		{
			startActivity(intent);
			// ++, 150326 bwk
			CAN1Comm.SetMiracastFlag(false);
			CAN1Comm.SetMultimediaFlag(false);
			// --, 150326 bwk
			ParentActivity.StartAlwaysOntopService(); // ++, --, 150324 cjg
		}
	}

	public void Clickable(boolean flag){
		LayoutBGUp.setClickable(flag);
		LayoutBGDown.setClickable(flag);
	}

	public void CursurDisplayDetail(int index){
		LayoutBGUp.setBackgroundResource(R.drawable._selector_leftup_btn);
		LayoutBGDown.setBackgroundResource(R.drawable._selector_leftdown_quick_btn2);
		switch(index){
			case 1:
				LayoutBGUp.setBackgroundResource(R.drawable.main_b_quick_userswitching_s_02);
				break;
			case 2:
				LayoutBGDown.setBackgroundResource(R.drawable.main_b_quick_help_s_02);
				break;
		}
	}
}