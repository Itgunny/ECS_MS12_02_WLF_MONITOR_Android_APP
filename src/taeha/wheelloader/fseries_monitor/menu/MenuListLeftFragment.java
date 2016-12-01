package taeha.wheelloader.fseries_monitor.menu;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.mode.MenuModeFragment;
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

public class MenuListLeftFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnMode;
	ImageButton imgbtnMonitoring;
	ImageButton imgbtnManagement;
	ImageButton imgbtnPreference;
	ImageButton imgbtnMultimedia;
	
	ImageView imgViewMode;
	ImageView imgViewMonitoring;
	ImageView imgViewManagement;
	ImageView imgViewPreference;
	ImageView imgViewMultimedia;
	
	TextFitTextView textViewMode;
	TextFitTextView textViewMonitoring;
	TextFitTextView textViewManagement;
	TextFitTextView textViewPreference;
	TextFitTextView textViewMultimedia;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int FirstScreenIndex;
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
		TAG = "MenuListLeftFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_list_left, null);
		InitResource();
		InitFragment();
		InitValuables();
		
		InitButtonListener();
		
		ModeBodyDisplay(FirstScreenIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnMode = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_left_mode);
		imgbtnMonitoring = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_left_monitoring);
		imgbtnManagement = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_left_management);
		imgbtnPreference = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_left_preference);
		imgbtnMultimedia = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_left_multimedia);
		
		imgViewMode = (ImageView)mRoot.findViewById(R.id.imageView_menu_list_left_mode);
		imgViewMonitoring = (ImageView)mRoot.findViewById(R.id.imageView_menu_list_left_monitoring);
		imgViewManagement = (ImageView)mRoot.findViewById(R.id.imageView_menu_list_left_management);
		imgViewPreference = (ImageView)mRoot.findViewById(R.id.imageView_menu_list_left_preference);
		imgViewMultimedia = (ImageView)mRoot.findViewById(R.id.imageView_menu_list_left_multimedia);
		
		textViewMode = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_list_left_mode);
		SetTitleText(textViewMode, ParentActivity.getResources().getString(string.Mode), 195);
		
		textViewMonitoring = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_list_left_monitoring);
		SetTitleText(textViewMonitoring, ParentActivity.getResources().getString(string.Monitoring), 196);
		
		
		textViewManagement = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_list_left_management);
		SetTitleText(textViewManagement, ParentActivity.getResources().getString(string.Management), 197);
		textViewPreference = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_list_left_preference);
		SetTitleText(textViewPreference, ParentActivity.getResources().getString(string.Preference), 198);
		textViewMultimedia = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_list_left_multimedia);
		SetTitleText(textViewMultimedia, ParentActivity.getResources().getString(string.Multimedia), 199);

	}
	
	protected void InitFragment(){
	
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMode();	
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeEngTMFragment.CursurIndex = 1;
			}
		});
		imgbtnMonitoring.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMonitoring();
				ParentActivity._MenuBaseFragment._MenuMonitoringFragment.CursurIndex = 1;
			}
		});
		imgbtnManagement.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickManagement();
				ParentActivity._MenuBaseFragment._MenuManagementFragment.CursurIndex = 1;
			}
		});
		imgbtnPreference.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPreference();
				ParentActivity._MenuBaseFragment._MenuPreferenceFragment.CursurIndex = 1;
			}
		});
		imgbtnMultimedia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMultimedia();
				ParentActivity._MenuBaseFragment._MenuMultimediaFragment.CursurIndex = 1;
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
	public void ClickMode(){
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MODE_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MODE_END)){
			
				setClickMode();
				ParentActivity._MenuBaseFragment.showBodyMode();
			
			
		}
	}
	public void ClickMonitoring(){
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MONITORING_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MONITORING_END)){
			
				setClickMonitoring();
				ParentActivity._MenuBaseFragment.showBodyMonitoring();
			
			
		}
	}
	public void ClickManagement(){
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MANAGEMENT_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MANAGEMENT_END)){
		
				setClickManagement();
				ParentActivity._MenuBaseFragment.showBodyManagement();
			
			
		}
	}
	public void ClickPreference(){
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_PREFERENCE_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_PREFERENCE_END)){
			
				setClickPreference();
				ParentActivity._MenuBaseFragment.showBodyPreference();
			
			
		}
	}
	public void ClickMultimedia(){
		if((ParentActivity.ScreenIndex < Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP) 
		|| (ParentActivity.ScreenIndex > Home.SCREEN_STATE_MENU_MULTIMEDIA_END)){
			
				setClickMultimedia();
				ParentActivity._MenuBaseFragment.showBodyMultimedia();
			
		
		}
	}

	/////////////////////////////////////////////////////////////////////
	
	public void setClickMode(){
		imgViewMode.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_mode_selected));
		imgViewMonitoring.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_monitoring_normal));
		imgViewManagement.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_management_normal));
		imgViewPreference.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_preperence_normal));
		imgViewMultimedia.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_multimedia_normal));
		
		textViewMode.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_amber));
		textViewMonitoring.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewManagement.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewPreference.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMultimedia.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
	}
	public void setClickMonitoring(){
		imgViewMode.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_mode_normal));
		imgViewMonitoring.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_monitoring_selected));
		imgViewManagement.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_management_normal));
		imgViewPreference.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_preperence_normal));
		imgViewMultimedia.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_multimedia_normal));
		
		textViewMode.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMonitoring.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_amber));
		textViewManagement.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewPreference.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMultimedia.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
	}
	public void setClickManagement(){
		imgViewMode.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_mode_normal));
		imgViewMonitoring.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_monitoring_normal));
		imgViewManagement.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_management_selected));
		imgViewPreference.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_preperence_normal));
		imgViewMultimedia.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_multimedia_normal));
		
		textViewMode.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMonitoring.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewManagement.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_amber));
		textViewPreference.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMultimedia.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
	}
	public void setClickPreference(){
		imgViewMode.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_mode_normal));
		imgViewMonitoring.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_monitoring_normal));
		imgViewManagement.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_management_normal));
		imgViewPreference.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_preperence_selected));
		imgViewMultimedia.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_multimedia_normal));
		
		textViewMode.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMonitoring.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewManagement.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewPreference.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_amber));
		textViewMultimedia.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
	}
	public void setClickMultimedia(){
		imgViewMode.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_mode_normal));
		imgViewMonitoring.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_monitoring_normal));
		imgViewManagement.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_management_normal));
		imgViewPreference.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_preperence_normal));
		imgViewMultimedia.setImageDrawable(ParentActivity.getResources().getDrawable(R.drawable.menu_icon_multimedia_selected));
		
		textViewMode.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMonitoring.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewManagement.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewPreference.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_gray));
		textViewMultimedia.setTextColor(ParentActivity.getResources().getColor(R.color.menu_left_amber));
	}
	/////////////////////////////////////////////////////////////////////
	public void setFirstScreen(int Index){
		FirstScreenIndex = Index;
	}
	public void ModeBodyDisplay(int Index){
		switch (Index) {
		case Home.SCREEN_STATE_MENU_MODE_TOP:
		default:
			setClickMode();
			break;
		case Home.SCREEN_STATE_MENU_MONITORING_TOP:
			setClickMonitoring();
			break;
		case Home.SCREEN_STATE_MENU_MANAGEMENT_TOP:
			setClickManagement();
			break;
		case Home.SCREEN_STATE_MENU_PREFERENCE_TOP:
			setClickPreference();
			break;
		case Home.SCREEN_STATE_MENU_MULTIMEDIA_TOP:
			setClickMultimedia();
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
	public void SetTitleText(TextView textView, String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			textView.setText(str);
		}else {
			textView.setText(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex));
		}
	}
	/////////////////////////////////////////////////////////////////////
}
