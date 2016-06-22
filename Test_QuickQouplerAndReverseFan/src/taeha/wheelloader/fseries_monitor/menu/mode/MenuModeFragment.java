package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
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
	public final static int STATE_CURSUR_LIST	= 0;
	public final static int STATE_CURSUR_TAB	= 1;
	public final static int STATE_CURSUR_LEFT	= 2;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutTab;
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int FirstScreenIndex;
	int ModeFocusIndex;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	public MenuModeTabFragment				_MenuModeTabFragment;
	public MenuModeEngTMFragment 			_MenuModeEngTMFragment;
	public MenuModeHYDFragment 				_MenuModeHYDFragment;
	public MenuModeETCFragment 				_MenuModeETCFragment;
	public EngineSettingFragment 			_EngineSettingFragment;
	public CalibrationFragment				_CalibrationFragment;
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
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(false);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Mode), 195);
		_MenuModeTabFragment.setFirstScreen(FirstScreenIndex);
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
		_EngineSettingFragment = new EngineSettingFragment();
		_CalibrationFragment = new CalibrationFragment();
		
		// ++, --, 150410 bwk ฟ๘บน 
		 // ++, 150331 cjg
		ModeFocusIndex = STATE_CURSUR_LIST;
// 		ModeFocusIndex = STATE_CURSUR_TAB;
//	      
//	      ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurIndex = 0;
//	      ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurDisplay(CursurIndex);
	      // --, 150331 cjg
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
	public void showEngineSetting(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_list, _EngineSettingFragment);
		transaction.commit();
	}
	public void showCalibrationSetting(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_list, _CalibrationFragment);
		transaction.commit();
	}	
	public void showBlank(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuModeEngTMFragment);
		transaction.remove(_MenuModeHYDFragment);
		transaction.remove(_MenuModeETCFragment);
		transaction.commit();		
	}
	public void HideTab(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuModeTabFragment);
		transaction.commit();	
	}
	public void HideEngTM(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuModeEngTMFragment);
		transaction.commit();	
	}
	public void HideETC(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.remove(_MenuModeETCFragment);
		transaction.commit();	
	}	
	//Navi KeyButton//////////////////////////////////////////////////////////
	public void ClickLeft(){

		
	}
	public void ClickRight(){

	}
	public void ClickESC(){

	}
	public void ClickEnter(){

	}
	/////////////////////////////////////////////////////////////////////	
	public void setFirstScreen(int Index){
		FirstScreenIndex = Index;
	}
	public int GetModeFocusIndex(){
		return ModeFocusIndex;
	}
	public void SetModeFocusIndex(int Index){
		ModeFocusIndex = Index;
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
