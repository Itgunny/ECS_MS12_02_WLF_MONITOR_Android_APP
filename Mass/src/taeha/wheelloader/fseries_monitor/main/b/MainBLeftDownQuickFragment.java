package taeha.wheelloader.fseries_monitor.main.b;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBLeftDownQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	
	ImageView imgViewIcon;
	
	TextView textViewTitle;
	
	ImageButton imgbtnMirror;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		TAG = "MainBLeftDownQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftdown_main_b_quick, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftdown_main_b_quick);
		
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftdown_main_b_quick_icon);

		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_leftdown_main_b_quick_title);
		
		imgbtnMirror = (ImageButton)mRoot.findViewById(R.id.imageButton_leftdown_main_b_quick_mirror);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		LayoutBG.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 2;
				ParentActivity._MainBBaseFragment.CursurDisplay(ParentActivity._MainBBaseFragment.CursurIndex);
				ClickHelp();
			}
		});
		imgbtnMirror.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.CursurIndex = 3;
				ParentActivity._MainBBaseFragment.CursurDisplay(ParentActivity._MainBBaseFragment.CursurIndex);
				ClickMirror();
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
	
	public void ClickHelp(){
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("org.ebookdroid");
		if(intent != null)
		{
			startActivity(intent);
			// ++, 150326 bwk
			CAN1Comm.SetMiracastFlag(false);
			CAN1Comm.SetMultimediaFlag(false);
			// --, 150326 bwk
			ParentActivity.StartAlwaysOntopService(); // ++, --, 150325 cjg
		}
	}
	public void ClickMirror(){
		// ++, 150320 cjg
//		ParentActivity.KillApps("com.mxtech.videoplayer.ad");
//		Intent intent;
//		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
//		if(intent != null)
//			startActivity(intent);
		if(ParentActivity.CheckRunningApp("com.mxtech.videoplayer.ad")){
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_QUICK_TOP;
			ParentActivity._MultimediaClosePopup.show();
		}else{
			String service = Context.WIFI_SERVICE;
			final WifiManager wifi = (WifiManager)ParentActivity.getSystemService(service);
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
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
			if(intent != null){
				// ++, 150323 bwk
				ParentActivity.startActivity(intent);
				CAN1Comm.SetMultimediaFlag(false);
//				CAN1Comm.SetMiracastFlag(true);
				// --, 150323 bwk
				ParentActivity.StartAlwaysOntopService();
				ParentActivity.StartCheckSmartTerminalTimer();
//				ParentActivity.StartAlwaysOntopService(); // ++, --, 150324 cjg
			}
		}
		// --, 150320 cjg
	}
	
	public void Clickable(boolean flag){
		LayoutBG.setClickable(flag);
		imgbtnMirror.setClickable(flag);
	}
	public void CursurDisplayDetail(int index){
		Log.d(TAG, "CursurDisplayDetail:index"+index);
		
		LayoutBG.setBackgroundResource(R.drawable._selector_leftdown_quick_btn);
		imgbtnMirror.setBackgroundResource(R.drawable._selector_virtualkey_mirroring);
		switch(index){
			case 2:
				LayoutBG.setBackgroundResource(R.drawable.main_bg_left_down_quick_s_02);
				break;
			case 3:
				imgbtnMirror.setBackgroundResource(R.drawable.main_quick_btn_screen_mirroring_s);
				break;
		}
	}
}