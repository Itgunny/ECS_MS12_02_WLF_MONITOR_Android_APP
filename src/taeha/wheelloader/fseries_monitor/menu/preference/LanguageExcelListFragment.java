package taeha.wheelloader.fseries_monitor.menu.preference;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.preference.LanguageListFragment.EnableButtonTimerClass;

public class LanguageExcelListFragment extends ParentFragment{
	RelativeLayout LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CursurIndex;
	RadioButton[] radioLanguageButton;
	Handler HandleCursurDisplay;
	Timer	mEnableButtonTimer = null;
	
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 TAG = "LanguageExcelListFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_language_excel, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurFirstDisplay(ParentActivity.LanguageIndex);
		EnableRadioButton(false);
		StartEnableButtonTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_EXCEL_LANG_CHANGE;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Language), 422);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
					CursurDisplay(msg.what);
			}
		};
		
		return mRoot;
		
		
	}
	//Common Function//////////////////////////////
	//////////////////////////////////////////////////////////////////////
	@Override
	protected void InitValuables(){
		super.InitValuables();
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_language_excel);
		if(ParentActivity.langDb.getCountLanguage() == 0){
			int count = 0;
			radioLanguageButton = new RadioButton[2];
			for(int i = 0; i < radioLanguageButton.length; i++){
				radioLanguageButton[i] = new RadioButton(ParentActivity);
				if(i== 0){
					radioLanguageButton[i].setText("ÇÑ±¹¾î");
				}else{
					radioLanguageButton[i].setText("English");
				}
				radioLanguageButton[i].setWidth(250);
				radioLanguageButton[i].setTextSize(25);
				radioLanguageButton[i].setId(i);
				radioLanguageButton[i].setTextColor(ParentActivity.getResources().getColorStateList(R.drawable._selector_radio_txt_white_amber));
				radioLanguageButton[i].setButtonDrawable(R.drawable._selector_radio_btn);
				radioLanguageButton[i].setGravity(Gravity.LEFT);
				if(i % 3 == 0){
					radioLanguageButton[i].setX(15);
					radioLanguageButton[i].setY(22 + (count * 65));
				}else if(i % 3 == 1){
					radioLanguageButton[i].setX(275);
					radioLanguageButton[i].setY(22 + (count * 65));
				}else{
					radioLanguageButton[i].setX(535);
					radioLanguageButton[i].setY(22 + (count * 65));
					count++;
				}
				LayoutBG.addView(radioLanguageButton[i]);
			}
		} else{
			radioLanguageButton = new RadioButton[ParentActivity.langDb.getCountLanguage()];
			int count = 0;
			int Y = 65;

			for(int i = 0;  i < radioLanguageButton.length; i++){

				radioLanguageButton[i] = new RadioButton(ParentActivity);

				radioLanguageButton[i].setText(ParentActivity.langDb.findStrGetString(i+2));
				radioLanguageButton[i].setWidth(250);
				radioLanguageButton[i].setTextSize(25);
				radioLanguageButton[i].setId(i);
				radioLanguageButton[i].setTextColor(ParentActivity.getResources().getColorStateList(R.drawable._selector_radio_txt_white_amber));
				radioLanguageButton[i].setButtonDrawable(R.drawable._selector_radio_btn);
				radioLanguageButton[i].setGravity(Gravity.LEFT);

				if(i % 3 == 0){
					radioLanguageButton[i].setX(15);
					radioLanguageButton[i].setY(22 + (count * 65));
				}else if(i % 3 == 1){
					radioLanguageButton[i].setX(275);
					radioLanguageButton[i].setY(22 + (count * 65));
				}else{
					radioLanguageButton[i].setX(535);
					radioLanguageButton[i].setY(22 + (count * 65));
					count++;
				}
				LayoutBG.addView(radioLanguageButton[i]);
			}
		}
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < radioLanguageButton.length; i++){
			
			radioLanguageButton[i].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CursurIndex = 1 + v.getId();
					Log.d(TAG, "CursurIndex : " + CursurIndex);
					HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
					SetLanguage(CursurIndex - 1);
					Log.d(TAG, "CursurIndex : " + CursurIndex);
				}
			});
			}
		
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
	public class EnableButtonTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ParentActivity.AnimationRunningFlag == false)
					{
						CancelEnableButtonTimer();
						EnableRadioButton(true);
					}
				}
			});
			
		}
		
	}
	
	public void StartEnableButtonTimer(){
		CancelEnableButtonTimer();
		mEnableButtonTimer = new Timer();
		mEnableButtonTimer.schedule(new EnableButtonTimerClass(),1,50);	
	}
	
	public void CancelEnableButtonTimer(){
		if(mEnableButtonTimer != null){
			mEnableButtonTimer.cancel();
			mEnableButtonTimer.purge();
			mEnableButtonTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		for(int i = 0; i <radioLanguageButton.length; i++){
			if(CursurIndex == 1){
				CursurIndex = radioLanguageButton.length;
				CursurDisplay(CursurIndex);
				break;
			}else if(CursurIndex > 1){
				CursurIndex--;
				CursurDisplay(CursurIndex);
				break;
			}
		}
	}
	
	public void ClickRight(){
		for(int i = 0; i <radioLanguageButton.length; i++){
			if(CursurIndex == radioLanguageButton.length ){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
				break;
			}else if(CursurIndex > 0){
				CursurIndex++;
				CursurDisplay(CursurIndex);
				break;
			}
		}
	}
	
	public void ClickESC(){
		if(ParentActivity.OldScreenIndex == ParentActivity.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP)
		{
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			else
				ParentActivity.StartAnimationRunningTimer();
			
			ParentActivity._MenuBaseFragment.showDisplayTypeLangAnimation();
		}
		else
		{
			// ++, 150309 bwk
			//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.showMainScreen();
			// --, 150309 bwk
			ParentActivity.OldScreenIndex = 0;			
		}		
	}
	
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_KOREAN);
			break;
		case 2:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ENGLISH);
			break;
		case 3:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_GERMAN);
			break;
		case 4:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FRENCH);
			break;
		case 5:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SPANISH);
			break;
		case 6:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_PORTUGUE);
			break;
		case 7:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ITALIAN);
			break;
		case 8:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_NEDERLAND);
			break;
		case 9:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SWEDISH);
			break;
		case 10:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FINNISH);
			break;
		case 11:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN);
			break;
		case 12:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ESTONIAN);
			break;
		case 13:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_TURKISH);
			break;
		case 14:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_HEBREW);
			break;
		// --, 150225 bwk
		default:
			SetLanguage(CursurIndex - 1);
			Log.d(TAG, "CursurIndex : " + CursurIndex);
			break;
		}
	}
	
	public void CursurFirstDisplay(int data){
		switch (data) {
		case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
			CursurIndex = 1;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
			CursurIndex = 2;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_GERMAN:
			CursurIndex = 3;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_FRENCH:
			CursurIndex = 4;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_SPANISH:
			CursurIndex = 5;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_PORTUGUE:
			CursurIndex = 6;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_ITALIAN:
			CursurIndex = 7;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_NEDERLAND:
			CursurIndex = 8;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_SWEDISH:
			CursurIndex = 9;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_TURKISH:
			CursurIndex = 13;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN:
			CursurIndex = 11;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_ESTONIAN:
			CursurIndex = 12;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_FINNISH:
			CursurIndex = 10;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_HEBREW:
			CursurIndex = 14;
			break;
		default:
			CursurIndex = data + 1;
			break;
		}
		CursurDisplay(CursurIndex);
		CheckButtonDisplay(CursurIndex);
	}
	
	public void EnableRadioButton(boolean bEnable){
		float alpha;
		if(bEnable == true)
			alpha = (float)1;
		else
			alpha = (float)0;
		
		LayoutBG.setAlpha(alpha);
		
		for(int i = 0; i < radioLanguageButton.length; i++){
			radioLanguageButton[i].setClickable(bEnable);
		}
	}
	
	public void CursurDisplay(int Index){
		CheckButtonDisplay(Index);
		for(int i = 0; i < radioLanguageButton.length; i++){
			radioLanguageButton[i].setPressed(false);
		}
		for(int i = 0; i < radioLanguageButton.length; i++){
			if(Index == i+1){
				radioLanguageButton[i].setPressed(true);
				break;
			}
		}
	}
	
	// ++, 150225 bwk
	public void CheckButtonDisplay(int data){
		for(int i = 0; i < radioLanguageButton.length; i++){
			radioLanguageButton[i].setChecked(false);
		}
		for(int i = 0; i < radioLanguageButton.length; i++){
			if(data == i+1){
				radioLanguageButton[i].setChecked(true);
				break;
			}
		}
	}
	// --, 150225 bwk
	/////////////////////////////////////////////////////////////////////
	
	public void SetLanguage(int Index)
	{
		ParentActivity.LanguageIndex = Index;
		ParentActivity.LangClass.setLanugage(Index);
		ParentActivity.SavePref();
		ParentActivity.ResetPopup(); 		// ++, --, 150305 bwk
		ParentActivity.showLanguageChangePopup();
	}
	

}
