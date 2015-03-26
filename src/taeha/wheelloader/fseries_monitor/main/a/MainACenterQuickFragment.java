package taeha.wheelloader.fseries_monitor.main.a;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class MainACenterQuickFragment extends MainACenterFragment{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnSmartTerminal;
	ImageButton imgbtnMedia;
	ImageButton imgbtnKeypad;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Maint;
	int FaultCode;

	// ++, 150326 bwk
	int SendDTCIndex;
	int SendSeqIndex;

	int DTCTotalPacketMachine;
	int DTCTotalPacketEngine;
	int DTCTotalPacketTM;
	// --, 150326 bwk
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
		TAG = "MainACenterQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_a_quick, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
		// ++, 150325 bwk
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.COMMAND_MAINTENANCE_ITEM_LIST_REQUEST);
		CAN1Comm.TxCANToMCU(12);
		// --, 150325 bwk
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		//super.InitResource();
		
		imgbtnSmartTerminal = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_mirror);
		imgbtnMedia = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_mediaplayer);
		imgbtnKeypad = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_keypad);
		imgbtnOption = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_quick_option);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		// ++, 150326 bwk
		SendDTCIndex = Home.REQ_ERR_MACHINE_ACTIVE;
		SendSeqIndex = 1;
		// --, 150326 bwk
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
		imgbtnSmartTerminal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMirror();
			}
		});
		imgbtnMedia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMultimedia();
			}
		});
		imgbtnKeypad.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickKeypad();
			}
		});		
		imgbtnOption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickBG();
			}
		});
	}
	
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
		// ++, 150326 bwk
		if(SendDTCIndex != Home.REQ_ERR_END)
		{
			ReqestErrorCode();
			DTCTotalPacketMachine = CAN1Comm.Get_gErr_Mcu_TotalPacket();
			DTCTotalPacketEngine = CAN1Comm.Get_gErr_Ecu_TotalPacket();
			DTCTotalPacketTM = CAN1Comm.Get_gErr_Tcu_TotalPacket();
		}
		// --, 150326 bwk
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplay(FaultCode,Maint);
	}
	/////////////////////////////////////////////////////////////////////
	// ++, 150326 bwk
	public void RequestErrorCode(int Err, int Req, int SeqNo){
		CAN1Comm.Set_DTCInformationRequest_1515_PGN61184_11(Req);
		CAN1Comm.Set_DTCType_1510_PGN61184_11(Err);
		CAN1Comm.Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(SeqNo);
		CAN1Comm.TxCANToMCU(11);
	}
	public void ReqestErrorCode(){
		switch (SendDTCIndex) {
		case Home.REQ_ERR_MACHINE_ACTIVE:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
				SetThreadSleepTime(200);
			}
			else if(SendSeqIndex > DTCTotalPacketMachine){
				SendSeqIndex = 1;
				SendDTCIndex = Home.REQ_ERR_ENGINE_ACTIVE;
				SetThreadSleepTime(1000);
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}

			break;
		case Home.REQ_ERR_ENGINE_ACTIVE:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
				SetThreadSleepTime(200);
			}
			else if(SendSeqIndex > DTCTotalPacketEngine){
				SendSeqIndex = 1;
				SendDTCIndex = Home.REQ_ERR_TM_ACTIVE;
				SetThreadSleepTime(1000);
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}

			break;
		case Home.REQ_ERR_TM_ACTIVE:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
				SetThreadSleepTime(200);
			}
			else if(SendSeqIndex > DTCTotalPacketTM){
				SendSeqIndex = 1;
				SendDTCIndex = Home.REQ_ERR_END;
				SetThreadSleepTime(1000);
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}

			break;

		default:
			break;
		}
	}
	// --, 150326 bwk
	/////////////////////////////////////////////////////////////////////	
	
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainABaseFragment.showDefaultScreenAnimation();
		Log.d(TAG,"ClickOption");
	}
	public void ClickMirror(){
		if(ParentActivity.CheckRunningApp("com.mxtech.videoplayer.ad")){
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
			ParentActivity._MultimediaClosePopup.show();
		}else{
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.example.wfdsink");
			if(intent != null){
				startActivity(intent);
				// ++, 150323 bwk
				CAN1Comm.SetMultimediaFlag(false);
				CAN1Comm.SetMiracastFlag(true);
				// --, 150323 bwk				
			}
		}		
	}
	public void ClickMultimedia(){
		if(CAN1Comm.GetrpmFlag() == false)
		{
			if(ParentActivity.CheckRunningApp("com.example.wfdsink")){
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
				ParentActivity._MiracastClosePopup.show();
			}else{
				ParentActivity.KillApps("com.example.wfdsink");
				Intent intent;
				intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
				if(intent != null){
					CAN1Comm.SetPlayerFlag(true);	
					CAN1Comm.SetMultimediaFlag(true);
					CAN1Comm.SetMiracastFlag(false);
					ParentActivity.startActivity(intent);
					ParentActivity.StartAlwaysOntopService(); // ++, --, 150324 cjg
				}
			}
		}
	}	
	public void ClickKeypad(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainABaseFragment.showVritualKeyScreenAnimation();
		ParentActivity._MainABaseFragment._MainALeftQuickFragment.Clickable(false);
		ParentActivity._MainABaseFragment._MainARightQuickFragment.Clickable(false);
		Clickable(false);
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnOption.setClickable(ClickFlag);
		imgbtnSmartTerminal.setClickable(ClickFlag);
		imgbtnMedia.setClickable(ClickFlag);
		imgbtnKeypad.setClickable(ClickFlag);
	}
	
	public void Clickable(boolean flag){
		imgbtnOption.setClickable(flag);
		imgbtnSmartTerminal.setClickable(flag);
		imgbtnMedia.setClickable(flag);
		imgbtnKeypad.setClickable(flag);
	}
	
}
