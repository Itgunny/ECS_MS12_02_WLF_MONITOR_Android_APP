package taeha.wheelloader.fseries_monitor.main.b.key;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBKeyDetentFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioBoomOff;
	RadioButton radioBoomOn;
	RadioButton radioBucketOff;
	RadioButton radioBucketOn;
	
	TextView textViewBoomSavePosition;
	TextView textViewBucketSavePosition;
	
	ImageButton imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BoomDetentMode;
	int BucketDetentMode;
	
	int SendCount;

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
		TAG = "MainBKeyDetentFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_detent, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_DETENT;
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
		radioBoomOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_detent_boom_off);
		radioBoomOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_detent_boom_on);
		radioBucketOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_detent_bucket_off);
		radioBucketOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_detent_bucket_on);
		
		textViewBoomSavePosition = (TextView)mRoot.findViewById(R.id.textView_key_main_b_detent_boom_saveposition);
		textViewBucketSavePosition = (TextView)mRoot.findViewById(R.id.textView_key_main_b_detent_bucket_saveposition);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_detent_low_ok);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		BoomDetentMode = CAN1Comm.Get_BoomDetentMode_223_PGN61184_124();
		BucketDetentMode = CAN1Comm.Get_BucketDetentMode_224_PGN61184_124();
		
		SendCount = 0;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
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
		
		
		
		
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
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
	public void DetentBoomDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF:
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
			radioBucketOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN:
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
	public void ClickOK(){
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	
	
}