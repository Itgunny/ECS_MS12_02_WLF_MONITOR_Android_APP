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
	TextView textViewLeft;
	TextView textViewCenter;
	TextView textViewRight;	
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
		setEngineText(ParentActivity.getResources().getString(string.Eng) + " / " + ParentActivity.getResources().getString(string.TM));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	



	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewLeft = (TextView)mRoot.findViewById(R.id.TextView_menu_body_mode_tab_engine);
		textViewCenter = (TextView)mRoot.findViewById(R.id.TextView_menu_body_mode_tab_hyd);
		textViewRight = (TextView)mRoot.findViewById(R.id.TextView_menu_body_mode_tab_etc);
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
			}
		});
		textViewCenter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHYD();
			}
		});
		textViewRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickETC();
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
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
