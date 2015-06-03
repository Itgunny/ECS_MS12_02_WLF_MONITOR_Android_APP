package taeha.wheelloader.fseries_monitor.menu.preference;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.LanguageClass;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home.SeatBeltTimerClass;

public class LanguageListFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	// ++, --, 150225 bwk 다국어 26개국
	// ++, 150402 bwk
	// 양산 사양 확정 13개국어
	// 한국어, 영어, 독일어, 프랑스어, 스페인어, 포르투갈어, 이탈리아어, 네덜란드어, 스웨덴어, 핀란드어, 슬로바키아어, 에스토니아어, 터키어
	// 미지원 9개 
	// 헤브라이어, 중국어, 러시아어, 태국어, 힌디어, 몽골어, 아랍어, 페르시아어, 인도네시아어
	// --, 1500402 bwk
	RadioButton radioKorean;
	RadioButton radioEnglish;
	RadioButton radioGerman;
	RadioButton radioFrench;
	RadioButton radioSpanish;
	RadioButton radioPortuguese;
	RadioButton radioItalian;
	RadioButton radioDutch;
	RadioButton radioSwedish;
	RadioButton radioFinnish;
	RadioButton radioSlovakian;
	RadioButton radioEstonian;
	RadioButton radioTurkish;
	
	RelativeLayout	LayoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	
	//LanguageClass LangClass;
	
	Handler HandleCursurDisplay;
	int CursurIndex;
	
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
		 TAG = "LanguageListFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_language, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurFirstDisplay(ParentActivity.LanguageIndex);
		EnableRadioButton(false);
		StartEnableButtonTimer();
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
		//LangClass = new LanguageClass(ParentActivity);
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
		radioGerman = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang3);
		radioFrench = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang4);
		radioSpanish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang5);
		radioPortuguese = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang6);
		radioItalian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang7);
		radioDutch = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang8);
		radioSwedish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang9);
		radioFinnish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang10);
		radioSlovakian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang11);
		radioEstonian = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang12);
		radioTurkish = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_Lang13);
		// --, 150225 bwk
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_language);
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
		radioItalian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ITALIAN);
			}
		});	
		radioDutch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_NEDERLAND);
			}
		});	
		radioSwedish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SWEDISH);
			}
		});	
		radioFinnish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_FINNISH);
			}
		});	
		radioSlovakian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN);
			}
		});	
		radioEstonian.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_ESTONIAN);
			}
		});	
		radioTurkish.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetLanguage(Home.STATE_DISPLAY_LANGUAGE_TURKISH);
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
		switch (CursurIndex) {
			case 1:
				CursurIndex = 13;		
				CursurDisplay(CursurIndex);
				break;
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			
			break;
		case 13:
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
		// --, 150225 bwk
		default:
		
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
		case Home.STATE_DISPLAY_LANGUAGE_FINNISH:
			CursurIndex = 10;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN:
			CursurIndex = 11;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_ESTONIAN:
			CursurIndex = 12;
			break;
		case Home.STATE_DISPLAY_LANGUAGE_TURKISH:
			CursurIndex = 13;
			break;
		default:
			CursurIndex = 1;
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

		radioKorean.setClickable(bEnable);
		radioEnglish.setClickable(bEnable);
		radioGerman.setClickable(bEnable);
		radioFrench.setClickable(bEnable);
		radioSpanish.setClickable(bEnable);
		radioPortuguese.setClickable(bEnable);
		radioItalian.setClickable(bEnable);
		radioDutch.setClickable(bEnable);
		radioSwedish.setClickable(bEnable);
		radioTurkish.setClickable(bEnable);
		radioSlovakian.setClickable(bEnable);
		radioEstonian.setClickable(bEnable);
		radioFinnish.setClickable(bEnable);		
	}
	
	public void CursurDisplay(int Index){
		CheckButtonDisplay(Index);
		radioKorean.setPressed(false);
		radioEnglish.setPressed(false);
		radioGerman.setPressed(false);
		radioFrench.setPressed(false);
		radioSpanish.setPressed(false);
		radioPortuguese.setPressed(false);
		radioItalian.setPressed(false);
		radioDutch.setPressed(false);
		radioSwedish.setPressed(false);
		radioTurkish.setPressed(false);
		radioSlovakian.setPressed(false);
		radioEstonian.setPressed(false);
		radioFinnish.setPressed(false);
		
		switch (Index) {
			case 1:
				radioKorean.setPressed(true);
				break;
			case 2:
				radioEnglish.setPressed(true);
				break;
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
				radioItalian.setPressed(true);
				break;
			case 8:
				radioDutch.setPressed(true);
				break;
			case 9:
				radioSwedish.setPressed(true);
				break;
			case 10:
				radioFinnish.setPressed(true);
				break;
			case 11:
				radioSlovakian.setPressed(true);
				break;
			case 12:
				radioEstonian.setPressed(true);
				break;
			case 13:
				radioTurkish.setPressed(true);
				break;
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
		radioItalian.setChecked(false);
		radioDutch.setChecked(false);
		radioSwedish.setChecked(false);
		radioTurkish.setChecked(false);
		radioSlovakian.setChecked(false);
		radioEstonian.setChecked(false);
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
				radioItalian.setChecked(true);
				break;
			case 8:
				radioDutch.setChecked(true);
				break;
			case 9:
				radioSwedish.setChecked(true);
				break;
			case 10:
				radioFinnish.setChecked(true);
				break;
			case 11:
				radioSlovakian.setChecked(true);
				break;
			case 12:
				radioEstonian.setChecked(true);
				break;
			case 13:
				radioTurkish.setChecked(true);
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
		ParentActivity.LangClass.setLanugage(Index);
		ParentActivity.SavePref();
		ParentActivity.ResetPopup(); 		// ++, --, 150305 bwk
		
		if(ParentActivity.OldScreenIndex == ParentActivity.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP)
		{
			if(ParentActivity.AnimationRunningFlag == true)
			{
				Log.d(TAG, "ParentActivity.AnimationRunningFlag == true");
				return;
			}
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
	
}
