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
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBLeftUpQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	
	ImageView imgViewIcon;
	
	TextFitTextView textViewTitle;
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
		TAG = "MainBLeftUpQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftup_main_b_quick, null);
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
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_quick);
		
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_quick_icon);

		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_leftup_main_b_quick_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.User_Switching), 116));
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		LayoutBG.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 1;
				ClickUserSwitching();
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
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_B_TOP;
		ParentActivity._MenuBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MENU_USERSWITCHING_TOP);
	}
	public void Clickable(boolean flag){
		LayoutBG.setClickable(flag);
	}
	public void CursurDisplayDetail(int index){
		Log.d(TAG, "CursurDisplayDetail:index"+index);
		LayoutBG.setBackgroundResource(R.drawable._selector_leftup_quick_btn);
		switch(index){
			case 1:
				LayoutBG.setBackgroundResource(R.drawable.main_bg_left_up_quick_s_02);
				break;
		}
	}
	
	
}