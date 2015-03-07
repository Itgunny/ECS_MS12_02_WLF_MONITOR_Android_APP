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
	// ++, 150225 bwk
	RadioButton radioGerman;
	RadioButton radioFrench;
	RadioButton radioSpanish;
	RadioButton radioPortuguese;
	RadioButton radioChinese;
	RadioButton radioRussian;
	RadioButton radioItalian;
	RadioButton radioDutch;
	RadioButton radioSwedish;
	RadioButton radioTurkish;
	RadioButton radioSlovakian;
	RadioButton radioEstonian;
	RadioButton radioThai;
	RadioButton radioHindi;
	RadioButton radioMongolian;
	RadioButton radioArabic;
	RadioButton radioPersian;
	RadioButton radioIndonesian;
	RadioButton radioFinnish;
	// --, 150225 bwk
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
		// ++, 150225 bwk
		radioGerman = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang3);
		radioFrench = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang4);
		radioSpanish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang5);
		radioPortuguese = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang6);
		radioChinese = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang7);
		radioRussian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang8);
		radioItalian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang9);
		radioDutch = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang10);
		radioSwedish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang11);
		radioTurkish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang12);
		radioSlovakian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang13);
		radioEstonian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang14);
		radioThai = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang15);
		radioHindi = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang16);
		radioMongolian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang17);
		radioArabic = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang18);
		radioPersian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang19);
		radioIndonesian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang20);
		radioFinnish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang21);
		// --, 150225 bwk
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
		// ++, 150225 bwk
		radioGerman.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_GERMAN);
			}
		});	
		radioFrench.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FRENCH);
			}
		});	
		radioSpanish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SPANISH);
			}
		});	
		radioPortuguese.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_PORTUGUE);
			}
		});	
		radioChinese.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_CHINESE);
			}
		});	
		radioRussian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_RUSIAN);
			}
		});	
		radioItalian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ITALIAN);
			}
		});	
		radioDutch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_NEDERLAND);
			}
		});	
		radioSwedish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SWEDISH);
			}
		});	
		radioTurkish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_TURKISH);
			}
		});	
		radioSlovakian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN);
			}
		});	
		radioEstonian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 14;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ESTONIAN);
			}
		});	
		radioThai.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 15;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_THAILAND);
			}
		});	
		radioHindi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 16;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_HINDI);
			}
		});	
	 	radioMongolian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 17;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_MONGOL);
			}
		});	
	 	radioArabic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 18;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ARABIC);
			}
		});	
		radioPersian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 19;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FARSI);
			}
		});	
		radioIndonesian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 20;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_INDONESIAN);
			}
		});	
		radioFinnish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 21;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FINNISH);
			}
		});	
		// --, 150225 bwk
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
				CursurIndex = 21;		// ++, --, 150225 bwk 2->21
				CursurDisplay(CursurIndex);
				break;
			case 2:
			// ++, 150225 bwk
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
			case 20:
			case 21:
			// --, 150225 bwk
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
		// ++, 150225 bwk
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		// --, 150225 bwk			
			CursurIndex++;
			CursurDisplay(CursurIndex);
			
			break;
		case 21:		// ++, --, 150225 bwk 2 -> 21
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
		// ++, 150225 bwk
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
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_CHINESE);
			break;
		case 8:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_RUSIAN);
			break;
		case 9:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ITALIAN);
			break;
		case 10:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_NEDERLAND);
			break;
		case 11:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SWEDISH);
			break;
		case 12:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_TURKISH);
			break;
		case 13:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN);
			break;
		case 14:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ESTONIAN);
			break;
		case 15:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_THAILAND);
			break;
		case 16:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_HINDI);
			break;
		case 17:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_MONGOL);
			break;
		case 18:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ARABIC);
			break;
		case 19:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FARSI);
			break;
		case 20:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_INDONESIAN);
			break;
		case 21:
			SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FINNISH);
			break;
		// --, 150225 bwk
		default:
		
			break;
		}
	}
	
	public void CursurFirstDisplay(int data){
		CursurIndex = data + 1;
		CursurDisplay(CursurIndex);
		CheckButtonDisplay(CursurIndex);
	}
	
	public void CursurDisplay(int Index){
		radioKorean.setPressed(false);
		radioEnglish.setPressed(false);
		// ++, 150225 bwk
		radioGerman.setPressed(false);
		radioFrench.setPressed(false);
		radioSpanish.setPressed(false);
		radioPortuguese.setPressed(false);
		radioChinese.setPressed(false);
		radioRussian.setPressed(false);
		radioItalian.setPressed(false);
		radioDutch.setPressed(false);
		radioSwedish.setPressed(false);
		radioTurkish.setPressed(false);
		radioSlovakian.setPressed(false);
		radioEstonian.setPressed(false);
		radioThai.setPressed(false);
		radioHindi.setPressed(false);
		radioMongolian.setPressed(false);
		radioArabic.setPressed(false);
		radioPersian.setPressed(false);
		radioIndonesian.setPressed(false);
		radioFinnish.setPressed(false);
		// --, 150225 bwk
		
		switch (Index) {
			case 1:
				radioKorean.setPressed(true);
				break;
			case 2:
				radioEnglish.setPressed(true);
				break;
			// ++, 150225 bwk
			case 3:
				radioGerman.setPressed(true);
				break;
			case 4:
				radioFrench.setPressed(true);
				break;
			case 5:
				radioSpanish.setPressed(true);
				break;
			case 6:
				radioPortuguese.setPressed(true);
				break;
			case 7:
				radioChinese.setPressed(true);
				break;
			case 8:
				radioRussian.setPressed(true);
				break;
			case 9:
				radioItalian.setPressed(true);
				break;
			case 10:
				radioDutch.setPressed(true);
				break;
			case 11:
				radioSwedish.setPressed(true);
				break;
			case 12:
				radioTurkish.setPressed(true);
				break;
			case 13:
				radioSlovakian.setPressed(true);
				break;
			case 14:
				radioEstonian.setPressed(true);
				break;
			case 15:
				radioThai.setPressed(true);
				break;
			case 16:
				radioHindi.setPressed(true);
				break;
			case 17:
				radioMongolian.setPressed(true);
				break;
			case 18:
				radioArabic.setPressed(true);
				break;
			case 19:
				radioPersian.setPressed(true);
				break;
			case 20:
				radioIndonesian.setPressed(true);
				break;
			case 21:
				radioFinnish.setPressed(true);
				break;
			// --, 150225 bwk
			default:
				break;
		}
	}
	
	// ++, 150225 bwk
	public void CheckButtonDisplay(int data){
	    radioKorean.setChecked(false);
		radioEnglish.setChecked(false);
		radioGerman.setChecked(false);
		radioFrench.setChecked(false);
		radioSpanish.setChecked(false);
		radioPortuguese.setChecked(false);
		radioChinese.setChecked(false);
		radioRussian.setChecked(false);
		radioItalian.setChecked(false);
		radioDutch.setChecked(false);
		radioSwedish.setChecked(false);
		radioTurkish.setChecked(false);
		radioSlovakian.setChecked(false);
		radioEstonian.setChecked(false);
		radioThai.setChecked(false);
		radioHindi.setChecked(false);
		radioMongolian.setChecked(false);
		radioArabic.setChecked(false);
		radioPersian.setChecked(false);
		radioIndonesian.setChecked(false);
		radioFinnish.setChecked(false);
		
		switch (data) {
			case 1:
				radioKorean.setChecked(true);
				break;
			case 2:
				radioEnglish.setChecked(true);
				break;
			case 3:
				radioGerman.setChecked(true);
				break;
			case 4:
				radioFrench.setChecked(true);
				break;
			case 5:
				radioSpanish.setChecked(true);
				break;
			case 6:
				radioPortuguese.setChecked(true);
				break;
			case 7:
				radioChinese.setChecked(true);
				break;
			case 8:
				radioRussian.setChecked(true);
				break;
			case 9:
				radioItalian.setChecked(true);
				break;
			case 10:
				radioDutch.setChecked(true);
				break;
			case 11:
				radioSwedish.setChecked(true);
				break;
			case 12:
				radioTurkish.setChecked(true);
				break;
			case 13:
				radioSlovakian.setChecked(true);
				break;
			case 14:
				radioEstonian.setChecked(true);
				break;
			case 15:
				radioThai.setChecked(true);
				break;
			case 16:
				radioHindi.setChecked(true);
				break;
			case 17:
				radioMongolian.setChecked(true);
				break;
			case 18:
				radioArabic.setChecked(true);
				break;
			case 19:
				radioPersian.setChecked(true);
				break;
			case 20:
				radioIndonesian.setChecked(true);
				break;
			case 21:
				radioFinnish.setChecked(true);
				break;
			default:
				break;
		}
	}
	// --, 150225 bwk
	/////////////////////////////////////////////////////////////////////
	
	public void SetLanguage(int Index)
	{
		ParentActivity.LanguageIndex = Index;
		LangClass.setLanugage(Index);
		ParentActivity.ResetPopup(); 		// ++, --, 150305 bwk
		
		CheckButtonDisplay(Index+1);		// ++, --, 150225 bwk
		
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
