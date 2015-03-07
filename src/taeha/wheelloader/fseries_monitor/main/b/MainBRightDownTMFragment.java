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

public class MainBRightDownTMFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
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

		textViewCCOModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_ccomode_title);
		textViewCCOModeData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_ccomode_data);
		
		textViewShiftModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_shiftmode_title);
		textViewShiftModeData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_shiftmode_data);
		
		textViewTCLockUpTitle = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_tclockup_title);
		textViewTCLockUpData = (TextView)mRoot.findViewById(R.id.textView_rightdown_main_b_tm_tclockup_data);
		
		imgbtnCCOMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_ccomode);
		imgbtnShiftMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_shiftmode);
		imgbtnTCLockUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightdown_main_b_tm_tclockup);
		
		layoutTCLockUp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_rightdown_main_b_tm_tclockup);
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
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
		TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		TMCCOModeTitleDisplay(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()));
		TMCCOModeDisplay(CCOMode);
		TMShiftModeDisplay(ShiftMode);
		TMTCLockUpDisplay(TCLockUp);
		TCLockUpShow();
	}
	/////////////////////////////////////////////////////////////////////	
	public void TCLockUpShow(){
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940){
			layoutTCLockUp.setVisibility(View.GONE);
			imgbtnTCLockUp.setClickable(false);
		}else if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
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
		if(Model == CheckModel.MODEL_980){
			textViewCCOModeTitle.setText(getResources().getString(string.ICCO_MODE));
		}else{
			textViewCCOModeTitle.setText(getResources().getString(string.CCO_MODE));
		}
		// --, 150305 bwk
		
	}
	public void TMCCOModeDisplay(int Data){
		try {
			switch (Data) {
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.OFF));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.L));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.M));
				break;
			case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
				TMCCOModeDataAnimation.FlipAnimation(textViewCCOModeData,getResources().getString(string.H));
				break;
			default:
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
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.MANUAL));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.AL));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.AN));
				break;
			case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
				TMShiftModeDataAnimation.FlipAnimation(textViewShiftModeData,getResources().getString(string.AH));
				break;
			default:
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
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.OFF));
				break;
			case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
				TMTCLockUpDataAnimation.FlipAnimation(textViewTCLockUpData,getResources().getString(string.ON));
				break;
			default:
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
		ParentActivity._MainBBaseFragment._MainBRightDownTMCCOModeFragment = new MainBRightDownTMCCOModeFragment();
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterTMFragment);
		
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980)
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
}