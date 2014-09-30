package taeha.wheelloader.fseries_monitor.main;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuListLeftFragment;
import taeha.wheelloader.fseries_monitor.menu.MenuListTitleFragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MenuBaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MenuBaseFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	AbsoluteLayout LayoutInter;
	AbsoluteLayout LayoutList;
	
	FrameLayout framelayoutInterTitle;
	FrameLayout framelayoutInterBody;
	FrameLayout framelayoutInterLow;
	FrameLayout framelayoutListTitle;
	FrameLayout framelayoutListBody;
	FrameLayout framelayoutListLow;
	
	ImageView imgViewtitleBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	MenuListLeftFragment _MenuListLeftFragment;
	MenuListTitleFragment _MenuListTitleFragment;
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
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.screen_menu, null);
		InitResource();
		InitFragment();
		InitValuables();
		
		InitButtonListener();
		
		showListLeft();
		showListTitle();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_TOP;
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutInter = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_menu_inter);
		LayoutList = (AbsoluteLayout)mRoot.findViewById(R.id.AbsoluteLayout_menu_list);
		
		framelayoutInterTitle = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_inter_title);
		framelayoutInterBody = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_inter_body);
		framelayoutInterLow = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_inter_low);
		framelayoutListTitle = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_list_title);
		framelayoutListBody = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_list_body);
		framelayoutListLow = (FrameLayout)mRoot.findViewById(R.id.FrameLayout_menu_list_left);
		
		imgViewtitleBG = (ImageView)mRoot.findViewById(R.id.imageView_menu_title_bg);
	}
	
	protected void InitFragment(){
		_MenuListLeftFragment = new MenuListLeftFragment();
		_MenuListTitleFragment = new MenuListTitleFragment();
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	
		
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
	/////////////////////////////////////////////////////////////////////	
	
	//Show Fragment//////////////////////////////////////////////////////
	public void showListLeft(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_list_left, _MenuListLeftFragment);
		transaction.commit();
	}
	public void showListTitle(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_list_title, _MenuListTitleFragment);
		transaction.commit();
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(final int key){
		Log.d(TAG,"KeyButtonClick : 0x" + Integer.toHexString(key));
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (key) {
				case CAN1CommManager.MAINLIGHT:
					
					break;
				case CAN1CommManager.WORKLIGHT:
					
					break;
				case CAN1CommManager.AUTO_GREASE:
				
					break;
				case CAN1CommManager.QUICK_COUPLER:
				
					break;
				case CAN1CommManager.RIDE_CONTROL:
					
					break;
				case CAN1CommManager.WORK_LOAD:
				
					break;
				case CAN1CommManager.BEACON_LAMP:
					
					break;
				case CAN1CommManager.REAR_WIPER:
					
					break;
				case CAN1CommManager.MIRROR_HEAT:
					
					break;
				case CAN1CommManager.AUTOPOSITION:
				
					break;
				case CAN1CommManager.FINEMODULATION:
					
					break;
				default:
					break;
				}
				
			}
		});
	}
}
