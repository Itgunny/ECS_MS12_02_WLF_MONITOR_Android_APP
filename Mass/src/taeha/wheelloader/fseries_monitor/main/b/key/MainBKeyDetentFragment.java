package taeha.wheelloader.fseries_monitor.main.b.key;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	ImageView imageViewBoomSavePositionBoarder;
	ImageView imageViewBucketSavePositionBoarder;
	
	ImageButton imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BoomDetentMode;
	int BucketDetentMode;
	
	int SendCount;

	int OldCursurIndex;
	int CursurIndex;
	Handler HandleCursurDisplay;
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
		if(BoomDetentMode == CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN)
			CursurIndex = 2;
		else
			CursurIndex = 1;
		CursurDisplay(CursurIndex);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
		CAN1Comm.TxCANToMCU(123);
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
		imageViewBoomSavePositionBoarder = (ImageView)mRoot.findViewById(R.id.imageView_key_main_b_detent_boom_boarder);
		imageViewBucketSavePositionBoarder = (ImageView)mRoot.findViewById(R.id.imageView_key_main_b_detent_bucket_boarder);
		
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
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBoomOff();
			}
		});
		radioBoomOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBoomOn();
			}
		});
		textViewBoomSavePosition.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBoomSavePosition();
			}
		});
		
		radioBucketOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBucketOff();
			}
		});
		radioBucketOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBucketOn();
			}
		});
		textViewBucketSavePosition.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickBucketSavePosition();
			}
		});
		
		
		
		
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
				if((CursurIndex == 3) || (CursurIndex == 8)){
					
				}else
				{
					CursurIndex = 3;
					imageViewBucketSavePositionBoarder.setVisibility(View.INVISIBLE);
					CursurDisplay(CursurIndex);
				}
				
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_POS);
				CAN1Comm.TxCANToMCU(123);
				CAN1Comm.Set_RequestDetentReleasePositionSetting_PGN61184_123(15);
			}else if(textViewBucketSavePosition.isPressed() == true){
				if((CursurIndex == 6) || (CursurIndex == 8)){
					
				}else
				{
					CursurIndex = 6;
					imageViewBoomSavePositionBoarder.setVisibility(View.INVISIBLE);
					CursurDisplay(CursurIndex);
				}
				
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
		DetentBoomDisplay(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_OFF);
	}
	public void ClickBoomOn(){
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BoomDetentMode_223_PGN61184_123(7);
		DetentBoomDisplay(CAN1CommManager.DATA_STATE_KEY_DETENT_BOOM_UPDOWN);
	}
	public void ClickBucketOff(){
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
		DetentBucketDisplay(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_OFF);
	}
	public void ClickBucketOn(){
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN);
		CAN1Comm.TxCANToMCU(123);
		CAN1Comm.Set_BucketDetentMode_224_PGN61184_123(7);
		DetentBucketDisplay(CAN1CommManager.DATA_STATE_KEY_DETENT_BUCKET_IN);
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

	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		if((textViewBoomSavePosition.isPressed() == true) || (textViewBucketSavePosition.isPressed() == true))
			return;
		switch (CursurIndex) {
		case 1:
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		if((textViewBoomSavePosition.isPressed() == true) || (textViewBucketSavePosition.isPressed() == true))
			return;
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickBoomOff();
			
			break;
		case 2:
			ClickBoomOn();
			break;
		case 3:
			OldCursurIndex = 3;
			CursurIndex = 8;
			CursurDisplay(CursurIndex);
			break;
		case 4:			
			ClickBucketOff();
			break;
		case 5:
			ClickBucketOn();
			break;
		case 6:
			OldCursurIndex = 6;
			CursurIndex = 8;
			CursurDisplay(CursurIndex);
			break;			
		case 7:
			ClickOK();
			break;
		case 8:
			if(OldCursurIndex == 3){
				CursurIndex  = 3;
				CursurDisplay(CursurIndex);	
			} else if(OldCursurIndex == 6){
				CursurIndex  = 6;
				CursurDisplay(CursurIndex);				
			}
			break;
		default:
			break;
		}
		

	}
	
	public void CursurDisplay(int Index){
		radioBoomOff.setPressed(false);
		radioBoomOn.setPressed(false);
		radioBucketOff.setPressed(false);
		radioBucketOn.setPressed(false);
		imgbtnOK.setPressed(false);		
		textViewBoomSavePosition.setPressed(false);
		textViewBucketSavePosition.setPressed(false);
		imageViewBoomSavePositionBoarder.setVisibility(View.INVISIBLE);
		imageViewBucketSavePositionBoarder.setVisibility(View.INVISIBLE);
		
		switch (CursurIndex) {
		case 1:
			radioBoomOff.setPressed(true);
			break;
		case 2:
			radioBoomOn.setPressed(true);
			break;
		case 3:
			textViewBoomSavePosition.setPressed(false);
			imageViewBoomSavePositionBoarder.setVisibility(View.VISIBLE);
			ClickBoomSavePosition();
			break;
		case 4:
			radioBucketOff.setPressed(true);
			break;
		case 5:
			radioBucketOn.setPressed(true);
			break;
		case 6:
			textViewBucketSavePosition.setPressed(false);
			imageViewBucketSavePositionBoarder.setVisibility(View.VISIBLE);
			ClickBucketSavePosition();
			break;
		case 7:
			imgbtnOK.setPressed(true);
			break;
		case 8:
			if(OldCursurIndex == 3){
				textViewBoomSavePosition.setPressed(true);
				imageViewBoomSavePositionBoarder.setVisibility(View.VISIBLE);
			}else if(OldCursurIndex == 6){
				textViewBucketSavePosition.setPressed(true);
				imageViewBucketSavePositionBoarder.setVisibility(View.VISIBLE);
			}
			break;
		default:
			break;
		}
	}
	
}