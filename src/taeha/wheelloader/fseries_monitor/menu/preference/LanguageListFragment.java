package taeha.wheelloader.fseries_monitor.menu.preference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LanguageClass;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class LanguageListFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioKorean;
	RadioButton radioEnglish;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	
	LanguageClass LangClass;
	
	Handler HandleCursurDisplay;
	int CursurIndex;
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
		 TAG = "LanguageListFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_language, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurFirstDisplay(ParentActivity.LanguageIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Language));
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
	//////////////////////////////////////////////////////////////////////
	@Override
	protected void InitValuables(){
		super.InitValuables();
		LangClass = new LanguageClass(ParentActivity);
	}
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		radioKorean = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang1);
		radioEnglish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang2);
		
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioKorean.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_KOREAN);
			}
		});
		radioEnglish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ENGLISH);
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
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
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
		
		default:
		
			break;
		}
	}
	
	public void CursurFirstDisplay(int data){
		switch (data) {
			case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
				radioKorean.setChecked(true);
				radioEnglish.setChecked(false);
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
				radioKorean.setChecked(false);
				radioEnglish.setChecked(true);
				break;
			default:
				break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioKorean.setPressed(false);
		radioEnglish.setPressed(false);
		
		switch (Index) {
			case 1:
				radioKorean.setPressed(true);
				break;
			case 2:
				radioEnglish.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
	public void SetLanguage(int Index)
	{
		ParentActivity.LanguageIndex = Index;
		LangClass.setLanugage(Index);
		
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
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.OldScreenIndex = 0;			
		}
	}
	
}
