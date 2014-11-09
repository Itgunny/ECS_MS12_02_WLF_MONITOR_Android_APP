package taeha.wheelloader.fseries_monitor.menu.mode;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
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
import android.widget.RadioButton;
import android.widget.TextView;

public class DetentFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	
	TextView textViewBoomSavePosition;
	TextView textViewBucketSavePosition;
	
	RadioButton radioBoomOff;
	RadioButton radioBoomOn;
	RadioButton radioBucketOff;
	RadioButton radioBucketOn;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BoomDetentMode;
	int BucketDetentMode;
	
	int SendCount;
	
	private Timer mPosTimer = null;
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
		 TAG = "DetentFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_detent, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_HYD_DETENT;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Boom_Bucket_Detent_Mode));
		DetentBoomDisplay(BoomDetentMode);
		DetentBucketDisplay(BucketDetentMode);

		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();

	}
	////////////////////////////////////////////////
	
	

	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_detent_low_ok);

		textViewBoomSavePosition = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_detent_boom_saveposition);
		textViewBucketSavePosition = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_detent_bucket_saveposition);

		radioBoomOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_detent_boom_off);
		radioBoomOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_detent_boom_on);
		radioBucketOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_detent_bucket_off);
		radioBucketOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_detent_bucket_on);
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
		textViewBoomSavePosition.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomSavePosition();
			}
		});
		textViewBucketSavePosition.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketSavePosition();
			}
		});
		radioBoomOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomOff();
			}
		});
		radioBoomOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomOn();
			}
		});
		radioBucketOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketOff();
			}
		});
		radioBucketOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketOn();
			}
		});
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		BoomDetentMode = CAN1Comm.Get_BoomDetentMode_223_PGN61184_124();
		BucketDetentMode = CAN1Comm.Get_BucketDetentMode_224_PGN61184_124();
		CheckBoomBucketDetent();
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
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickBoomOff(){
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(7);
	}
	public void ClickBoomOn(){
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(7);
	}
	public void ClickBucketOff(){
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
	}
	public void ClickBucketOn(){
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
	}
	public void ClickBoomSavePosition(){
		CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
		CAN1Comm.TxCANToMCU(123);
	}
	public void ClickBucketSavePosition(){
		CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
		CAN1Comm.TxCANToMCU(123);
	}
	/////////////////////////////////////////////////////////////////////
	public void DetentBoomDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF:                                                                                                                                                      
			radioBucketOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN:
			radioBoomOff.setChecked(true);
			radioBoomOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN:
			radioBoomOff.setChecked(false);
			radioBoomOn.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void DetentBucketDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF:
			radioBucketOff.setChecked(true);
			radioBucketOff.setChecked(false);
			radioBucketOn.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void CheckBoomBucketDetent(){
		if(SendCount > 5){
			if(textViewBoomSavePosition.isPressed() == true){
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_POS);
				CAN1Comm.TxCANToMCU(123);
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
			}else if(textViewBucketSavePosition.isPressed() == true){
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_POS);
				CAN1Comm.TxCANToMCU(123);
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
			}else{
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
				CAN1Comm.TxCANToMCU(123);
			}
			SendCount = 0;
		}
		SendCount++;
	
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
