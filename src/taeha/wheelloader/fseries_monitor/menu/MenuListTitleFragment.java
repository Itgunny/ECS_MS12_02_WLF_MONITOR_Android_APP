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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuListTitleFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	ImageButton imgbtnBack;
	ImageButton imgbtnHome;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	String strTitle;
	boolean bBackVisibleFlag;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	LeftRightShiftAnimation TitleTextShiftAnimation;
	AppearAnimation TitleTextAppearAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 TAG = "MenuListTitleFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_list_title, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		SetTitleText(strTitle);
		setBackButtonEnable(bBackVisibleFlag);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_list_title_text);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_title_back);
		imgbtnHome = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_list_title_home);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		TitleTextShiftAnimation = new LeftRightShiftAnimation(ParentActivity, textViewTitle, 100, 0);
		TitleTextAppearAnimation = new AppearAnimation(ParentActivity, textViewTitle, 500);
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBack();
			}
		});
		imgbtnHome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHome();
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
	
	/////////////////////////////////////////////////////////////////////
	public void SetTitleText(String str){
		try {
			strTitle = str;
			textViewTitle.setText(str);
			TitleTextShiftAnimation.StartShiftAnimation();
			TitleTextAppearAnimation.StartAnimation();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException SetTitleText");
		}
	}
	public void SetTitleText(String str, int index){
		try {
			strTitle = str;
			if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
				textViewTitle.setText(str);
				Log.d(TAG, "Android");
			}else {
				textViewTitle.setText(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex));
				Log.d(TAG, "Excel");
			}
			TitleTextShiftAnimation.StartShiftAnimation();
			TitleTextAppearAnimation.StartAnimation();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException SetTitleText");
		}
	}
	public void SetTitleText(String str, int index, int index2){
		try {
			strTitle = str;
			if((ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null) || 
					(ParentActivity.langDb.findStrGetString(index2, ParentActivity.LanguageIndex) == null)){
				textViewTitle.setText(str);
				Log.d(TAG, "Android");
			}else {
				textViewTitle.setText(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) + " / " + ParentActivity.langDb.findStrGetString(index2, ParentActivity.LanguageIndex));
				Log.d(TAG, "Excel");
			}
			TitleTextShiftAnimation.StartShiftAnimation();
			TitleTextAppearAnimation.StartAnimation();
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException SetTitleText");
		}
	}
	public void setBackButtonEnable(boolean flag){
		bBackVisibleFlag = flag;
		if(flag == true){
			imgbtnBack.setVisibility(View.VISIBLE);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textViewTitle.getLayoutParams();
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,0);
			textViewTitle.setLayoutParams(params); //causes layout update
		}
		else{
			imgbtnBack.setVisibility(View.GONE);
			RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)textViewTitle.getLayoutParams();
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,1);
			textViewTitle.setLayoutParams(params); //causes layout update
		}
			
	}
	public void ClickBack(){
		Log.d(TAG,"ClickBack - ParentActivity.ScreenIndex"+ParentActivity.ScreenIndex);
		switch (ParentActivity.ScreenIndex) {
			case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
				ParentActivity._MenuBaseFragment._MenuModeFragment.showEngTM();
				SetTitleText(ParentActivity.getResources().getString(R.string.Mode), 195);
				setBackButtonEnable(false);
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP);
				ParentActivity._MenuBaseFragment._MenuModeFragment.showTab();
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_TOP:
			case Home.SCREEN_STATE_MENU_MONITORING_FUELHISTORY_TOP:
				ParentActivity._MenuBaseFragment.showBodyMonitoring();
				setBackButtonEnable(false);
				break;
			case Home.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW:
				ParentActivity._MenuBaseFragment.showBodyFaultHistory();
				setBackButtonEnable(true);
				break;
			// ++, 150409 cjg
			case Home.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP:
				ParentActivity._MenuBaseFragment._MenuModeFragment.showETC();
				SetTitleText(ParentActivity.getResources().getString(R.string.Mode), 195);
				setBackButtonEnable(false);
				ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
				ParentActivity._MenuBaseFragment._MenuModeFragment.showTab();
				break;
			// --, 150409 cjg
			case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP:
			case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PW:
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_TOP:
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW:
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SOFTWAREUPDAT_PW:	// ++, --, 150323 bwk
				ParentActivity._MenuBaseFragment.showBodyManagement();
				setBackButtonEnable(false);
				break;
			case Home.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE:
				ParentActivity._MenuBaseFragment.showBodyMachineSecurityList();
				setBackButtonEnable(true);
				break;
			case Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP:
				ParentActivity._MenuBaseFragment.showBodyPreference();
				setBackButtonEnable(false);
				break;
			case Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SOFTANDSTOP_CAL_TOP:
				ParentActivity._MenuBaseFragment.showBodyServiceMenuList();
				setBackButtonEnable(false);
				break;

		default:
			break;
		}
	}
	public void ClickHome(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		// ++, 150309 bwk
		//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
		ParentActivity.showMainScreen();
		// --, 150309 bwk
	//	ParentActivity.showMainBFragment();
	}
	public void setClickable(boolean clickable){
		imgbtnBack.setClickable(clickable);
		imgbtnHome.setClickable(clickable);
	}
	/////////////////////////////////////////////////////////////////////
	
}
