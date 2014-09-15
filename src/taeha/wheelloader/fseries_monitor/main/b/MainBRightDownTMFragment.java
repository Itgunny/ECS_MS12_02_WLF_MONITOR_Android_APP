package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBRightDownTMFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBRightDownTMFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewCCOModeTitle;
	TextView textViewCCOModeData;
	TextView textViewShiftModeTitle;
	TextView textViewShiftModeData;
	TextView textViewTCLockUpTitle;
	TextView textViewTCLockUpData;
	
	ImageButton imgbtnCCOMode;
	ImageButton imgbtnShiftMode;
	ImageButton imgbtnTCLockUp;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
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
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_b_tm, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}
	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		textViewCCOModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_ccomode_title);
		textViewCCOModeData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_ccomode_data);
		
		textViewShiftModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_shiftmode_title);
		textViewShiftModeData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_shiftmode_data);
		
		textViewTCLockUpTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_tclockup_title);
		textViewTCLockUpData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_tclockup_data);
		
		imgbtnCCOMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_ccomode);
		imgbtnShiftMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_shiftmode);
		imgbtnTCLockUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_tclockup);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnCCOMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickCCOMode();
			}
		});
		imgbtnShiftMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickShiftMode();
			}
		});
		imgbtnTCLockUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickTCLockUp();
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
	
	public void ClickCCOMode(){
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMCCOModeFragment);

		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	public void ClickShiftMode(){
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMShiftModeFragment);

		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	public void ClickTCLockUp(){
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMTCLockUpFragment);

		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		
		imgbtnCCOMode.setClickable(ClickFlag);
		imgbtnShiftMode.setClickable(ClickFlag);
		imgbtnTCLockUp.setClickable(ClickFlag);
	}
}