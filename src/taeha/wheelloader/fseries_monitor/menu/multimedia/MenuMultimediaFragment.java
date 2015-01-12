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

public class MenuMultimediaFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewMediaPlayer;
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
	public void ClickLeft(){
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickPreference();
			break;
		case 1:

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
		case 1:
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
			ClickMediaPlayer();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 0:
			textViewMediaPlayer.setPressed(false);
			break;
		case 1:
			textViewMediaPlayer.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickMediaPlayer(){
		ParentActivity.KillApps("com.example.wfdsink");
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
		if(intent != null){
			ParentActivity.startActivity(intent);
		}
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	public void ExcuteFileManaget(){
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage(
				"com.rhmsoft.fm.hd");
		if(intent != null)
			startActivity(intent);
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
