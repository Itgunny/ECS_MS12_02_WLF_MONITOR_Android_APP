package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
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

public class MenuModeTabFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewLeft;
	TextFitTextView textViewCenter;
	TextFitTextView textViewRight;	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	static int FirstScreenIndex;

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
		TAG = "MenuModeTabFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_tab, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ModeBodyDisplay(FirstScreenIndex);
		setEngineText(getString(ParentActivity.getResources().getString(string.Eng), 200) + " / " + getString(ParentActivity.getResources().getString(string.TM), 201));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	



	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewLeft = (TextFitTextView)mRoot.findViewById(R.id.TextView_menu_body_mode_tab_engine);
		textViewLeft.setText(getString(ParentActivity.getResources().getString(R.string.Eng), 200));
		textViewCenter = (TextFitTextView)mRoot.findViewById(R.id.TextView_menu_body_mode_tab_hyd);
		textViewCenter.setText(getString(ParentActivity.getResources().getString(R.string.HYD), 202));
		textViewRight = (TextFitTextView)mRoot.findViewById(R.id.TextView_menu_body_mode_tab_etc);
		textViewRight.setText(getString(ParentActivity.getResources().getString(R.string.ETC), 203));
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();

		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEngineTM();
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurIndex = 1;
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurDisplay(CursurIndex);
				ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
			}
		});
		textViewCenter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHYD();
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeHYDFragment.CursurIndex = 1;
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurDisplay(CursurIndex);
				ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
			}
		});
		textViewRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickETC();
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeETCFragment.CursurIndex = 1;
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurDisplay(CursurIndex);
				ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
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
	public void ClickEngineTM(){
		setClickImageEngineTM();
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MODE_ENGINETM_END)){
				ParentActivity._MenuBaseFragment._MenuModeFragment.showEngTM();
		}
	}
	public void ClickHYD(){
		setClickImageHYD();
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MODE_HYD_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MODE_HYD_END)){
				ParentActivity._MenuBaseFragment._MenuModeFragment.showHYD();
		}	
	}
	public void ClickETC(){
		setClickImageETC();
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MODE_ETC_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MODE_ETC_END)){
				ParentActivity._MenuBaseFragment._MenuModeFragment.showETC();
		}	
	}
	/////////////////////////////////////////////////////////////////////
	public void setEngineText(String str){
		textViewLeft.setText(str);
	}
	public void setHYDText(String str){
		textViewCenter.setText(str);
	}
	public void setETCText(String str){
		textViewRight.setText(str);
	}
	public void setClickImageEngineTM(){
		textViewLeft.setBackgroundResource(R.drawable.menu_list_tab_left_selected);
		textViewLeft.setTextColor(ParentActivity.getResources().getColor(R.color.menu_body_mode_tab_amber));

		textViewCenter.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_center_btn);
		textViewCenter.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));
		
		textViewRight.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_right_btn);
		textViewRight.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));
	}
	public void setClickImageHYD(){
		textViewLeft.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_left_btn);
		textViewLeft.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));

		textViewCenter.setBackgroundResource(R.drawable.menu_list_tab_center_selected);
		textViewCenter.setTextColor(ParentActivity.getResources().getColor(R.color.menu_body_mode_tab_amber));
		
		textViewRight.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_right_btn);
		textViewRight.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));
	}
	public void setClickImageETC(){
		textViewLeft.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_left_btn);
		textViewLeft.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));

		textViewCenter.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_center_btn);
		textViewCenter.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));
		
		textViewRight.setBackgroundResource(R.drawable.menu_list_tab_right_selected);
		textViewRight.setTextColor(ParentActivity.getResources().getColor(R.color.menu_body_mode_tab_amber));
	}
	public void setClickNull(){
		textViewLeft.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_left_btn);
		textViewLeft.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));

		textViewCenter.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_center_btn);
		textViewCenter.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));
		
		textViewRight.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_right_btn);
		textViewRight.setTextColor(ParentActivity.getResources().getColor(R.drawable._selector_menu_body_mode_tab_txt));

	}
	public void setPushEngineTM(){
		textViewLeft.setBackgroundResource(R.drawable.menu_list_tab_left_selected);
		
		textViewCenter.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_center_btn);

		textViewRight.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_right_btn);
	}
	public void setPushHYD(){
		textViewLeft.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_left_btn);
	
		textViewCenter.setBackgroundResource(R.drawable.menu_list_tab_center_selected);
	
		textViewRight.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_right_btn);
	
	}
	public void setPushETC(){
		textViewLeft.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_left_btn);

		textViewCenter.setBackgroundResource(R.drawable._selector_menu_body_mode_tab_center_btn);
		
		textViewRight.setBackgroundResource(R.drawable.menu_list_tab_right_selected);
	}
	/////////////////////////////////////////////////////////////////////
	public void setFirstScreen(int Index){
		FirstScreenIndex = Index;
	}

	public void ModeBodyDisplay(int Index){
		switch (Index) {
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP:
		default:
			ClickEngineTM();
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_TOP:
			ClickHYD();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_TOP:
			ClickETC();
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
			ParentActivity._MenuBaseFragment._MenuModeFragment.HideTab();
			ParentActivity._MenuBaseFragment._MenuModeFragment.HideEngTM();
			ParentActivity._MenuBaseFragment._MenuModeFragment.showEngineSetting();
			break;
		case Home.SCREEN_STATE_MENU_MODE_TOP:
			ParentActivity._MenuBaseFragment._MenuModeFragment.showEngTM();
			setClickNull();
			ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LEFT);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP:
			ParentActivity._MenuBaseFragment._MenuModeFragment.HideTab();
			ParentActivity._MenuBaseFragment._MenuModeFragment.HideETC();
			ParentActivity._MenuBaseFragment._MenuModeFragment.showCalibrationSetting();
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		
	}
	public void ClickRight(){
		
	}
	public void ClickESC(){
		
	}
	public void ClickEnter(){
		
	}
	/////////////////////////////////////////////////////////////////////
}
