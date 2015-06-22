package taeha.wheelloader.fseries_monitor.menu.multimedia;

import java.util.List;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuBodyList_ParentFragment;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Intent;
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

/*
public class MenuMultimediaFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
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
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "MenuMultimediaFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(false);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MULTIMEDIA_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Multimedia));
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		
		
		setListTitle1(ParentActivity.getResources().getString(string.Media_Palyer));
		setListTitle2(ParentActivity.getResources().getString(string.Smart_Terminal));
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		ClickMediaPlayer();
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		ClickSmartTerminal();	
		
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
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
			// --, 150323 bwk
				Intent intent;
				intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
				if(intent != null){
					//CAN1Comm.SetMultimediaFlag(true);	// ++, --, 150323 bwk
					CAN1Comm.SetMiracastFlag(false);
					ParentActivity.startActivity(intent);
					ParentActivity.StartAlwaysOntopService();		// ++, --, 150324 cjg
					ParentActivity.StartCheckMultimediaTimer();
				}
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
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
			if(intent != null){
				ParentActivity.startActivity(intent);
				CAN1Comm.SetMultimediaFlag(false);
				ParentActivity.StartCheckSmartTerminalTimer();
			}
		}		

		CursurIndex = 2;
		CursurDisplay(CursurIndex);
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
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		Log.d(TAG,"ClickLeft");
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickPreference();
			break;
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
		case 4:
		case 5:
		case 6:
			
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		Log.d(TAG,"ClickRight");
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_TOP);
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMode();			
			break;
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickHome();
			break;
		case 1:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		default:
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
			imgbtnList[0].callOnClick();
			break;
		case 2:
			imgbtnList[1].callOnClick();
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void CursurDisplay(int Index){
		try {
			switch (Index) {
			case 1:
				setListFocus(1);
				break;
			case 2:
				setListFocus(2);
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				setListFocus(0);
				break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException CursurDisplay");
		}
		
	}
	/////////////////////////////////////////////////////////////////////
*/

public class MenuMultimediaFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewMediaPlayer;
	TextView textViewSmartTerminal;
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
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Multimedia));
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		textViewMediaPlayer = (TextView)mRoot.findViewById(R.id.textView_menu_body_multimedia);
		textViewSmartTerminal = (TextView)mRoot.findViewById(R.id.textView_menu_body_smartterminal);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
	}
	
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewMediaPlayer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMediaPlayer();
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
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
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
			ClickMediaPlayer();
			break;
		case 2:
			ClickSmartTerminal();
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
			// --, 150323 bwk
				Intent intent;
				intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
				if(intent != null){
					//CAN1Comm.SetMultimediaFlag(true);	// ++, --, 150323 bwk
					ParentActivity.startActivity(intent);
					ParentActivity.StartAlwaysOntopService();		// ++, --, 150324 cjg
					ParentActivity.StartCheckMultimediaTimer();
				}
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
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
			if(intent != null){
				ParentActivity.startActivity(intent);
				CAN1Comm.SetMultimediaFlag(false);
				ParentActivity.StartCheckSmartTerminalTimer();
			}
		}		

		CursurIndex = 2;
		CursurDisplay(CursurIndex);
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
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////

}
