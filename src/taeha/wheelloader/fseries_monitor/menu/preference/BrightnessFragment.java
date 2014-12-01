package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class BrightnessFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAuto;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BrightnessManualAuto;
	
	int CursurIndex;
	
	Handler HandleCursurDisplay;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	public BrightnessManualFragment	_BrightnessManualFragment;
	public BrightnessAutoFragment _BrightnessAutoFragment;
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
		 TAG = "BrightnessFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_brightness, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Brightness_Setting));
		CursurFirstDisplay(BrightnessManualAuto);
		BrightnessDisplay(BrightnessManualAuto);
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_brightness_manual);
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_brightness_auto);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		_BrightnessManualFragment = new BrightnessManualFragment();
		_BrightnessAutoFragment = new BrightnessAutoFragment();		
		
		BrightnessManualAuto = ParentActivity.BrightnessManualAuto;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickManual();
			}
		});
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_MANUAL_TOP)
			showBodyBrightnessManual();
	}
	public void ClickAuto(){
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_PREFERENCE_BRIGHTNESS_AUTO_TOP)
			showBodyBrightnessAuto();
	}

	/////////////////////////////////////////////////////////////////////
	public void showBodyBrightnessManual(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_preference_brightness, _BrightnessManualFragment);
		transaction.commit();
		
	}
	public void showBodyBrightnessAuto(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_preference_brightness, _BrightnessAutoFragment);
		transaction.commit();
		
	}
	/////////////////////////////////////////////////////////////////////
	public void BrightnessDisplay(int _data){
		switch (_data) {
		case Home.BRIGHTNESS_MANUAL:
			ClickManual();
			radioManual.setChecked(true);
			radioAuto.setChecked(false);
			break;
		case Home.BRIGHTNESS_AUTO:
			ClickAuto();
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void setCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
		
			break;
		case 2:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		
	}
	public void ClickEnter(){
		Log.d(TAG,"ClickEnter");
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			_BrightnessManualFragment.setCursurIndex(3);
			BrightnessDisplay(Home.BRIGHTNESS_MANUAL);
			_BrightnessManualFragment.CursurDisplay(3);
			break;
		case 2:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			_BrightnessAutoFragment.setCursurIndex(3);
			BrightnessDisplay(Home.BRIGHTNESS_AUTO);
			_BrightnessAutoFragment.CursurDisplay(3);
			break;
		default:

			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case Home.BRIGHTNESS_MANUAL:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case Home.BRIGHTNESS_AUTO:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){

		radioAuto.setPressed(false);
		radioManual.setPressed(false);

		switch (CursurIndex) {
			case 1:
				radioManual.setPressed(true);
				break;
			case 2:
				radioAuto.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
