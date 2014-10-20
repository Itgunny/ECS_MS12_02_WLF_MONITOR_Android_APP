package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
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
import android.widget.TextView;

public class MenuModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutTab;
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	MenuModeTabFragment	_MenuModeTabFragment;
	MenuModeEngTMFragment _MenuModeEngTMFragment;
	MenuModeHYDFragment _MenuModeHYDFragment;
	MenuModeETCFragment _MenuModeETCFragment;
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
		 TAG = "MenuModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		showTab();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_TOP;
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutTab = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_body_mode_tab);
		LayoutList = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_body_mode_list);

	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		_MenuModeTabFragment = new MenuModeTabFragment();
		_MenuModeEngTMFragment = new MenuModeEngTMFragment();
		_MenuModeHYDFragment = new MenuModeHYDFragment();
		_MenuModeETCFragment = new MenuModeETCFragment();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	//Show Fragment//////////////////////////////////////////////////////
	public void showTab(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_tab, _MenuModeTabFragment);
		transaction.commit();
	}
	public void showEngTM(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_list, _MenuModeEngTMFragment);
		transaction.commit();
	}
	public void showHYD(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_list, _MenuModeHYDFragment);
		transaction.commit();
	}
	public void showETC(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_list, _MenuModeETCFragment);
		transaction.commit();
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////	
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
