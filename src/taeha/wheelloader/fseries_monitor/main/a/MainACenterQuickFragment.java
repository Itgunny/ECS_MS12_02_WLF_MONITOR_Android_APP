package taeha.wheelloader.fseries_monitor.main.a;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CommService;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainACenterQuickFragment extends MainACenterFragment{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnSmartTerminal;
	ImageButton imgbtnMedia;
	ImageButton imgbtnKeypad;
	ImageView	imgViewOptionSelect;
	ImageView	imgViewSmartTerminalSelect;
	ImageView	imgViewMediaSelect;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Maint;
	int FaultCode;
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
		
		imgViewOptionSelect = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_quick_option_select);
		imgViewSmartTerminalSelect = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_quick_mirror_select);
		imgViewMediaSelect = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_quick_mediaplayer_select);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurDisplayDetail(ParentActivity._MainABaseFragment.CursurIndex);
		MultiMediaDisplay(ParentActivity.LockMultiMedia);
		SmartTerminalDisplay(ParentActivity.LockSmartTerminal);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
		imgbtnSmartTerminal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 3;
				ParentActivity._MainABaseFragment.CursurDisplay(ParentActivity._MainABaseFragment.CursurIndex);
				ClickMirror();
			}
		});
		imgbtnMedia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainABaseFragment.CursurIndex = 5;
				ParentActivity._MainABaseFragment.CursurDisplay(ParentActivity._MainABaseFragment.CursurIndex);
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

	/////////////////////////////////////////////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplay(FaultCode,Maint);
	}
	/////////////////////////////////////////////////////////////////////
	
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		Clickable(false);	// ++, --, 150511 cjg
		ParentActivity._MainABaseFragment.showDefaultScreenAnimation();
		Log.d(TAG,"ClickOption");
	}
	public void ClickMirror(){
		if(ParentActivity.CheckRunningApp("com.mxtech.videoplayer.ad")){
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
			ParentActivity._MultimediaClosePopup.show();
		}else{
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
			String service = Context.WIFI_SERVICE;
			final WifiManager wifi = (WifiManager)ParentActivity.getSystemService(service);
			if(!ParentActivity.CheckRunningApp("com.powerone.wfd.sink")){
				if(wifi.isWifiEnabled()){
					wifi.setWifiEnabled(false);
					synchronized (wifi) {
						try {
							wifi.wait(500);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
			if(intent != null){
				ParentActivity.startActivity(intent);
				CAN1Comm.SetMultimediaFlag(false);
				Clickable(false);	// ++, --, 150511 cjg
				ParentActivity.StartAlwaysOntopService();
//				CAN1Comm.SetMiracastFlag(true);
				if(CommService.pi != null){
					if(!CommService.pi.versionName.equals("1.0.5BF")){
						CAN1Comm.setRunningCheckMiracast(true);
					}					
				}
				ParentActivity.StartCheckSmartTerminalTimer();
			}
		}		
	}
	public void ClickMultimedia(){
		//if(CAN1Comm.GetrpmFlag() == false)
		{
			if(ParentActivity.CheckRunningApp("com.powerone.wfd.sink")){
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
				ParentActivity._MiracastClosePopup.show();
			}else{
				
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
				ParentActivity._MultimediaWarningPopup.show();

			}
		}
	}	
	public void ClickKeypad(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainABaseFragment._MainALeftQuickFragment.Clickable(false);
		ParentActivity._MainABaseFragment._MainARightQuickFragment.Clickable(false);
		Clickable(false);
		ParentActivity._MainABaseFragment.showVritualKeyScreenAnimation();
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
	//////////////////////////////////////////////////////////////////////
	public void IconDisplay(int _fault, int _maint){
		if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON && _maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_faultmaint_btn);
		}else if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_fault_btn);
		}else if(_maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_maint_btn);
		}else{
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_home_btn);
		}
	}

	public void CursurDisplayDetail(int index){
		imgViewSmartTerminalSelect.setVisibility(View.GONE);
		imgViewMediaSelect.setVisibility(View.GONE);
		imgViewOptionSelect.setVisibility(View.GONE);
		switch(index){
			case 3:
				imgViewSmartTerminalSelect.setVisibility(View.VISIBLE);
				break;
			case 4:
				imgViewOptionSelect.setVisibility(View.VISIBLE);
				break;
			case 5:
				imgViewMediaSelect.setVisibility(View.VISIBLE);
				break;
		}
	}
	
	public void MultiMediaDisplay(int data){
		switch(data){
		case Home.STATE_ENTERTAINMENT_MULTIMEDIA_UNLOCK:
			imgbtnMedia.setEnabled(true);
			imgbtnMedia.setVisibility(View.VISIBLE);
			imgViewMediaSelect.setEnabled(true);
			break;
		case Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK:
			imgbtnMedia.setEnabled(false);
			imgbtnMedia.setVisibility(View.INVISIBLE);
			imgViewMediaSelect.setEnabled(false);
			break;
		}
	}
	
	public void SmartTerminalDisplay(int data){
		switch(data){
		case Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK:
			imgbtnSmartTerminal.setEnabled(false);
			imgbtnSmartTerminal.setVisibility(View.INVISIBLE);
			break;
		case Home.STATE_ENTERTAINMENT_SMARTTERMINAL_UNLOCK:
			imgbtnSmartTerminal.setEnabled(true);
			imgbtnSmartTerminal.setVisibility(View.VISIBLE);
			break;
		}
	}
}
