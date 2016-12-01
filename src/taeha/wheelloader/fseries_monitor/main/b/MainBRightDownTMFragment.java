package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBRightDownTMFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewCCOModeTitle;
	TextFitTextView textViewCCOModeData;
	TextFitTextView textViewShiftModeTitle;
	TextFitTextView textViewShiftModeData;
	TextFitTextView textViewTCLockUpTitle;
	TextFitTextView textViewTCLockUpData;
	
	ImageButton imgbtnCCOMode;
	ImageButton imgbtnShiftMode;
	ImageButton imgbtnTCLockUp;
	
	RelativeLayout layoutTCLockUp;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	
	int CCOMode;
	int ShiftMode;
	int TCLockUp;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	//TextViewXAxisFlipAnimation TMCCOModeTitleDataAnimation;	// ++, --, 150305 HHI 변경 요청
	TextViewXAxisFlipAnimation TMCCOModeDataAnimation;
	TextViewXAxisFlipAnimation TMShiftModeDataAnimation;
	TextViewXAxisFlipAnimation TMTCLockUpDataAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBRightDownTMFragment";
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

		textViewCCOModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_ccomode_title);
		textViewCCOModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.CCO_MODE), 86)  + " ");
		textViewCCOModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_ccomode_data);
		
		textViewShiftModeTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_shiftmode_title);
		textViewShiftModeTitle.setText(getString(ParentActivity.getResources().getString(R.string.SHIFT_MODE), 88)  + " ");
		textViewShiftModeData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_shiftmode_data);
		
		textViewTCLockUpTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_tclockup_title);
		textViewTCLockUpTitle.setText(getString(ParentActivity.getResources().getString(R.string.TC_LOCK_UP), 89)  + " ");
		textViewTCLockUpData = (TextFitTextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_tclockup_data);
		
		imgbtnCCOMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_ccomode);
		imgbtnShiftMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_shiftmode);
		imgbtnTCLockUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_tclockup);
		
		layoutTCLockUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_b_tm_tclockup);
		
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		//TMCCOModeTitleDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);	// ++, --, 150305 HHI 변경 요청
		TMCCOModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		TMShiftModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		TMTCLockUpDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnCCOMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 7;
				if(ClickFlag == true)
					ClickCCOMode();
			}
		});
		imgbtnShiftMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 8;
				if(ClickFlag == true)
					ClickShiftMode();
			}
		});
		imgbtnTCLockUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 9;
				if(ClickFlag == true)
					ClickTCLockUp();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
		TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		//TMCCOModeTitleDisplay(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()));
		TMCCOModeTitleDisplay(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()));
		TMCCOModeDisplay(CCOMode);
		TMShiftModeDisplay(ShiftMode);
		TMTCLockUpDisplay(TCLockUp);
		TCLockUpShow();
	}
	/////////////////////////////////////////////////////////////////////	
	public void TCLockUpShow(){
		/*if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			layoutTCLockUp.setVisibility(View.GONE);
			imgbtnTCLockUp.setClickable(false);
		}else*/ if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
			layoutTCLockUp.setVisibility(View.GONE);
			imgbtnTCLockUp.setClickable(false);
		}else{
			layoutTCLockUp.setVisibility(View.VISIBLE);
			imgbtnTCLockUp.setClickable(true);
		}
	}
	public void TMCCOModeTitleDisplay(int Model){
		// ++, 150305 bwk
		//if(Model == CheckModel.MODEL_980){
		//	TMCCOModeTitleDataAnimation.FlipAnimation(textViewCCOModeTitle,getResources().getString(string.ICCO_MODE));
		//}else{
		//	TMCCOModeTitleDataAnimation.FlipAnimation(textViewCCOModeTitle,getResources().getString(string.CCO_MODE));
		//}
		//if(Model == CheckModel.MODEL_980){
		if(Model == 980){
			textViewCCOModeTitle.setText(getString(getResources().getString(string.ICCO_MODE), 87) + " ");
		}else{
			textViewCCOModeTitle.setText(getString(getResources().getString(string.CCO_MODE), 86)  + " ");
		}
		// --, 150305 bwk
		
	}
	public void TMCCOModeDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.L), 99));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.M), 100));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
				if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
					TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.ON), 97));
				else
					TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getString(getResources().getString(string.H), 101));
				break;
			default:
//				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.OFF));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMCCOModeDisplay");
		}
	}
	public void TMShiftModeDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.MANUAL), 102));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.AL), 103));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.AN), 104));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getString(getResources().getString(string.AH), 105));
				break;
			default:
//				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.MANUAL));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMShiftModeDisplay");
		}
	}
	public void TMTCLockUpDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getString(getResources().getString(string.OFF), 98));
				break;
			case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getString(getResources().getString(string.ON), 97));
				break;
			default:
//				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.OFF));
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException TMTCLockUpDisplay");
		}
	}
	public void ClickCCOMode(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainBBaseFragment._MainBCenterTMFragment = new MainBCenterTMFragment();
		//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
			ParentActivity._MainBBaseFragment._MainBRightDownTMICCOModeFragment = new MainBRightDownTMICCOModeFragment();
		else
			ParentActivity._MainBBaseFragment._MainBRightDownTMCCOModeFragment = new MainBRightDownTMCCOModeFragment();
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		
		//if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
			ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMICCOModeFragment);
		else
			ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMCCOModeFragment);

		ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		
	
	}
	public void ClickShiftMode(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterTMFragment = new MainBCenterTMFragment();
		ParentActivity._MainBBaseFragment._MainBRightDownTMShiftModeFragment = new MainBRightDownTMShiftModeFragment();
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMShiftModeFragment);

		ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		
		
	}
	public void ClickTCLockUp(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterTMFragment = new MainBCenterTMFragment();
		ParentActivity._MainBBaseFragment._MainBRightDownTMTCLockUpFragment = new MainBRightDownTMTCLockUpFragment();
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightDownTMTCLockUpFragment);

		ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		

	}
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		
		imgbtnCCOMode.setClickable(ClickFlag);
		imgbtnShiftMode.setClickable(ClickFlag);
		imgbtnTCLockUp.setClickable(ClickFlag);
	}
	public void CursurDisplayDetail(int index){
		imgbtnCCOMode.setBackgroundResource(R.drawable._selector_rightdown_main_b_tm_ccomode_btn1);
		imgbtnShiftMode.setBackgroundResource(R.drawable._selector_rightdown_main_b_tm_shiftmode_btn1);
		imgbtnTCLockUp.setBackgroundResource(R.drawable._selector_rightdown_main_b_tm_tclockup_btn1);
		switch(index){
			case 7:
				imgbtnCCOMode.setBackgroundResource(R.drawable.main_default_tm01_selected02);
				break;
			case 8:
				imgbtnShiftMode.setBackgroundResource(R.drawable.main_default_tm02_selected02);
				break;
			case 9:
				imgbtnTCLockUp.setBackgroundResource(R.drawable.main_default_tm03_selected02);
				break;
		}
	}	
}
