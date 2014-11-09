package taeha.wheelloader.fseries_monitor.menu.mode;

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

public class SoftStopFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;
	
	RadioButton radioBoomUpOn;
	RadioButton radioBoomUpOff;
	RadioButton radioBoomDownOn;
	RadioButton radioBoomDownOff;
	RadioButton radioBucketInOn;
	RadioButton radioBucketInOff;
	RadioButton radioBucketOutOn;
	RadioButton radioBucketOutOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BoomUp;
	int BoomDown;
	int BucketIn;
	int BucketOut;
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
		 TAG = "SoftStopFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_softstop, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Soft_Stop));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_softstop_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_softstop_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_softstop_low_default);
		
		radioBoomUpOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomup_on);
		radioBoomUpOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomup_off);
		radioBoomDownOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomdown_on);
		radioBoomDownOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomdown_off);
		radioBucketInOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketin_on);
		radioBucketInOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketin_off);
		radioBucketOutOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketout_on);
		radioBucketOutOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketout_off);

	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		BoomUp = CAN1Comm.Get_SoftStopBoomUp_2337_PGN65524();
		BoomDown = CAN1Comm.Get_SoftStopBoomDown_2338_PGN65524();
		BucketIn = CAN1Comm.Get_SoftStopBucketIn_2339_PGN65524();
		BucketOut = CAN1Comm.Get_SoftStopBucketOut_2340_PGN65524();
		
		BoomUpDisplay(BoomUp);
		BoomDownDisplay(BoomDown);
		BucketInDisplay(BucketIn);
		BucketOutDisplay(BucketOut);
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
		imgbtnDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDefault();
			}
		});
		radioBoomUpOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomUpOn();

			}
		});
		radioBoomUpOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomUpOff();
	
			}
		});
		radioBoomDownOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomDownOn();

			}
		});
		radioBoomDownOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomDownOff();
	
			}
		});
		radioBucketInOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketInOn();
	
			}
		});
		radioBucketInOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketInOff();

			}
		});
		radioBucketOutOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketOutOn();

			}
		});
		radioBucketOutOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketOutOff();

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
		
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(BoomUp);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(BoomDown);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(BucketIn);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(BucketOut);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickDefault(){
		BoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON;
		BoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON;
		BucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON;
		BucketOut = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON;
		
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(BoomUp);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(BoomDown);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(BucketIn);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(BucketOut);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		
		BoomUpDisplay(BoomUp);
		BoomDownDisplay(BoomDown);
		BucketInDisplay(BucketIn);
		BucketOutDisplay(BucketOut);
	}
	public void ClickBoomUpOn(){
		radioBoomUpOn.setChecked(true);
		radioBoomUpOff.setChecked(false);
		BoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON;
	}
	public void ClickBoomUpOff(){
		radioBoomUpOn.setChecked(false);
		radioBoomUpOff.setChecked(true);
		BoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF;
	}
	public void ClickBoomDownOn(){
		radioBoomDownOn.setChecked(true);
		radioBoomDownOff.setChecked(false);
		BoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON;
	}
	public void ClickBoomDownOff(){
		radioBoomDownOn.setChecked(false);
		radioBoomDownOff.setChecked(true);
		BoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF;
	}
	public void ClickBucketInOn(){
		radioBucketInOn.setChecked(true);
		radioBucketInOff.setChecked(false);
		BucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON;
	}
	public void ClickBucketInOff(){
		radioBucketInOn.setChecked(false);
		radioBucketInOff.setChecked(true);
		BucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF;
	}
	public void ClickBucketOutOn(){
		radioBucketOutOn.setChecked(true);
		radioBucketOutOff.setChecked(false);
		BucketOut = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON;
	}
	public void ClickBucketOutOff(){
		radioBucketOutOn.setChecked(false);
		radioBucketOutOff.setChecked(true);
		BucketOut = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF;
	}
	/////////////////////////////////////////////////////////////////////
	public void BoomUpDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			radioBoomUpOn.setChecked(false);
			radioBoomUpOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
			radioBoomUpOn.setChecked(true);
			radioBoomUpOff.setChecked(false);
			break;
		
		default:
			break;
		}
	}
	public void BoomDownDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF:
			radioBoomDownOn.setChecked(false);
			radioBoomDownOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON:
			radioBoomDownOn.setChecked(true);
			radioBoomDownOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	public void BucketInDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF:
			radioBucketInOn.setChecked(false);
			radioBucketInOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON:
			radioBucketInOn.setChecked(true);
			radioBucketInOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	public void BucketOutDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF:
			radioBucketOutOn.setChecked(false);
			radioBucketOutOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON:
			radioBucketOutOn.setChecked(true);
			radioBucketOutOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
