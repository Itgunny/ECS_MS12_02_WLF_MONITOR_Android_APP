package taeha.wheelloader.fseries_monitor.menu.multimedia;

import java.util.List;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CommService;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuBodyList_ParentFragment;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
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
import android.widget.TextView;

public class MenuMultimediaFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewMediaPlayer;
	TextFitTextView textViewSmartTerminal;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		 TAG = "MenuMultimediaFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_multimedia, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(false);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MULTIMEDIA_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Multimedia), 199);
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewMediaPlayer = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_multimedia);
		textViewMediaPlayer.setText(getString(ParentActivity.getString(R.string.Multimedia), 199));
		textViewSmartTerminal = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_smartterminal);
		textViewSmartTerminal.setText(getString(ParentActivity.getString(R.string.Smart_Terminal), 431));
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		MultiMediaDisplay(ParentActivity.LockMultiMedia);
		SmartTerminalDisplay(ParentActivity.LockSmartTerminal);
	}
	
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewMediaPlayer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK == ParentActivity.LockMultiMedia){
					ClickSmartTerminal();	
				} else {
					ClickMediaPlayer();
				}
			}
		});
		textViewSmartTerminal.setOnClickListener(new View.OnClickListener(	) {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSmartTerminal();
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
		/*
		// ++, 150325 bwk
		// 멀티미디어 실행 후 돌아왔을 때 포커스 잃는 문제가 있어 OnResume에 CursurIndex = 1을 넣었으나,
		// 상단메뉴에서 Left, Right 키를 누를 경우 바로 멀티미디어에 포커스가 가는 문제가 발생하여
		// 아래처럼 변경
		try{
			CursurDisplay(CursurIndex);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		// --, 150325 bwk
		 */
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CursurDisplay(CursurIndex);
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickPreference();
			break;
		case 1:
			if(Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK == ParentActivity.LockMultiMedia || Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK == ParentActivity.LockSmartTerminal){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}else {
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_TOP);
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMode();			
			break;
		case 1:
			if(Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK == ParentActivity.LockMultiMedia || Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK == ParentActivity.LockSmartTerminal){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}else {
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 2:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickHome();
			break;
		default:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 0:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 1:
			if(ParentActivity.LockMultiMedia != Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK){
				ClickMediaPlayer();
			} else {
				ClickSmartTerminal();
			}
			break;
		case 2:
			if(ParentActivity.LockSmartTerminal != Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK){
				if(ParentActivity.LockMultiMedia != Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK){
					ClickSmartTerminal();
				}
			}
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 0:
			textViewMediaPlayer.setPressed(false);
			textViewSmartTerminal.setPressed(false);
			break;
		case 1:
			textViewMediaPlayer.setPressed(true);
			textViewSmartTerminal.setPressed(false);
			break;
		case 2:
			textViewMediaPlayer.setPressed(false);
			textViewSmartTerminal.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickMediaPlayer(){
		// ++, 150319 bwk
		//if(CAN1Comm.GetrpmFlag() == false)
		{
		// --, 150319 bwk
			// ++, 150323 bwk
			if(ParentActivity.CheckRunningApp("com.powerone.wfd.sink")){
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MULTIMEDIA_TOP;
				ParentActivity._MiracastClosePopup.show();
			}else{
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MULTIMEDIA_TOP;
				ParentActivity._MultimediaWarningPopup.show();
			// --, 150323 bwk
/*				Intent intent;
				intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
				if(intent != null){
					CAN1Comm.SetMiracastFlag(false);
					//CAN1Comm.SetMultimediaFlag(true);	// ++, --, 150323 bwk
					ParentActivity.startActivity(intent);
					ParentActivity.StartAlwaysOntopService();		// ++, --, 150324 cjg
					ParentActivity.StartCheckMultimediaTimer();
				}*/
			}	// ++, --, 150323 bwk
		}	// ++, --, 150319 bwk
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	public void ClickSmartTerminal(){
		if(ParentActivity.CheckRunningApp("com.mxtech.videoplayer.ad")){
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MULTIMEDIA_TOP;
			ParentActivity._MultimediaClosePopup.show();
		}else{
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
				ParentActivity.StartAlwaysOntopService();
				if(CommService.pi != null){
					if(!CommService.pi.versionName.equals("1.0.5BF")){
						CAN1Comm.setRunningCheckMiracast(true);
					}					
				}
				ParentActivity.StartCheckSmartTerminalTimer();
			}
		}		
		if(Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK == ParentActivity.LockMultiMedia || Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK == ParentActivity.LockSmartTerminal){
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
		}else {
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
		}

	}
	public void ExcuteFileManaget(){
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"com.rhmsoft.fm.hd");
		if(intent != null)
			startActivity(intent);
	}
	public void ExcuteSettings(){
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"com.android.settings");
		if(intent != null)
			startActivity(intent);
	}
	public void ExcuteSettingsApplication(){
		startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS));
	}
	/////////////////////////////////////////////////////////////////////
	public void MultiMediaDisplay(int data){
		switch(data){
		case Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK:
			textViewMediaPlayer.setEnabled(false);
			textViewMediaPlayer.setVisibility(View.INVISIBLE);
			break;
		case Home.STATE_ENTERTAINMENT_MULTIMEDIA_UNLOCK:
			textViewMediaPlayer.setEnabled(true);
			textViewMediaPlayer.setVisibility(View.VISIBLE);
			break;
		}
	}
	public void SmartTerminalDisplay(int data){
		switch(data){
		case Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK:
			textViewSmartTerminal.setEnabled(false);
			textViewSmartTerminal.setVisibility(View.INVISIBLE);
			break;
		case Home.STATE_ENTERTAINMENT_SMARTTERMINAL_UNLOCK:
			if(Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK == ParentActivity.LockMultiMedia){
				textViewSmartTerminal.setEnabled(false);
				textViewSmartTerminal.setVisibility(View.INVISIBLE);
				textViewMediaPlayer.setEnabled(true);
				textViewMediaPlayer.setVisibility(View.VISIBLE);
				textViewMediaPlayer.setText(getString(ParentActivity.getString(R.string.Smart_Terminal), 431));
				textViewMediaPlayer.setBackgroundResource(R.drawable._selector_menu_body_multimedia_btn2);
			}else {
				textViewSmartTerminal.setEnabled(true);
				textViewSmartTerminal.setVisibility(View.VISIBLE);
			}
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
