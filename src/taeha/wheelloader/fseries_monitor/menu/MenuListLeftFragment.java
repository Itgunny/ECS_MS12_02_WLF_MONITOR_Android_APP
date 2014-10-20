package taeha.wheelloader.fseries_monitor.menu;

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
	
	TextView textViewMode;
	TextView textViewMonitoring;
	TextView textViewManagement;
	TextView textViewPreference;
	TextView textViewMultimedia;
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
		TAG = "MenuListLeftFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_list_left, null);
		InitResource();
		InitFragment();
		InitValuables();
		
		InitButtonListener();
		
		setClickMode();
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
		
		textViewMode = (TextView)mRoot.findViewById(R.id.textView_menu_list_left_mode);
		textViewMonitoring = (TextView)mRoot.findViewById(R.id.textView_menu_list_left_monitoring);
		textViewManagement = (TextView)mRoot.findViewById(R.id.textView_menu_list_left_management);
		textViewPreference = (TextView)mRoot.findViewById(R.id.textView_menu_list_left_preference);
		textViewMultimedia = (TextView)mRoot.findViewById(R.id.textView_menu_list_left_multimedia);

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
			}
		});
		imgbtnMonitoring.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMonitoring();
			}
		});
		imgbtnManagement.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickManagement();
			}
		});
		imgbtnPreference.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPreference();
			}
		});
		imgbtnMultimedia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMultimedia();
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
		
		if((ParentActivity.ScreenIndex < ParentActivity.SCREEN_STATE_MENU_MODE_TOP) || (ParentActivity.ScreenIndex > ParentActivity.SCREEN_STATE_MENU_MODE_END)){
			setClickMode();
			ParentActivity._MenuBaseFragment.showBodyMode();
		}
		
	}
	public void ClickMonitoring(){
		setClickMonitoring();
	}
	public void ClickManagement(){
		setClickManagement();
	}
	public void ClickPreference(){
		setClickPreference();
	}
	public void ClickMultimedia(){
		setClickMultimedia();
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
	
}
