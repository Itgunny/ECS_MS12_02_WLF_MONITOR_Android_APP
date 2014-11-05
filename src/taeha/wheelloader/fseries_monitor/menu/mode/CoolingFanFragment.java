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
import android.widget.RadioButton;
import android.widget.TextView;

public class CoolingFanFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAuto;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	public CoolingFanManualFragment	_CoolingFanManualFragment;
	public CoolingFanAutoFragment _CoolingFanAutoFragment;
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
		 TAG = "CoolingFanFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_coolingfan, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Cooling_Fan_Reverse_Mode));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfan_manual);
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfan_auto);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		_CoolingFanManualFragment = new CoolingFanManualFragment();
		_CoolingFanAutoFragment = new CoolingFanAutoFragment();		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickManual();
			}
		});
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickAuto();
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
	public void ClickManual(){

		
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL)
			showBodyCoolingFanManual();
	}
	public void ClickAuto(){

		
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_AUTO)
		showBodyCoolingFanAuto();
	}

	/////////////////////////////////////////////////////////////////////
	public void showBodyCoolingFanManual(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_coolingfan, _CoolingFanManualFragment);
		transaction.commit();
		
	}
	public void showBodyCoolingFanAuto(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_coolingfan, _CoolingFanAutoFragment);
		transaction.commit();
		
	}
	/////////////////////////////////////////////////////////////////////
	
}
