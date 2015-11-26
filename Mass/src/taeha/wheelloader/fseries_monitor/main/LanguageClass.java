package taeha.wheelloader.fseries_monitor.main;

import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

public class LanguageClass {
	//CONSTANT////////////////////////////////////////
	public static final String 				TAG 					= "LanguageClass";
	
	protected Home ParentActivity;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	//Context context;
	protected View mRoot;

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int LanguageIndex;

	InputMethodManager showSoftInput;
	List <InputMethodInfo> imis;
	InputMethodInfo imi;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public LanguageClass(Context _context){
		//context = _context;
		ParentActivity = (Home)_context;
		
		showSoftInput = (InputMethodManager)ParentActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
		imis = showSoftInput.getInputMethodList();
		for(int i = 0; i < imis.size(); i++){
			imi = imis.get(i);
			//imi.getId();
			Log.d(TAG, "input method" + imi.getId());
		}
		
		LoadPref();
	}

	public void setLanugage(int list){
		LanguageIndex = list;
		SavePref();
		 
		//Resources res = context.getResources();
		Resources res = ParentActivity.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		android.content.res.Configuration conf = res.getConfiguration();
		conf.locale = new Locale("en");
		
		switch (list) {
			case Home.STATE_DISPLAY_LANGUAGE_KOREAN:
				conf.locale = new Locale("ko");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ENGLISH:
				conf.locale = new Locale("en");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_GERMAN:
				conf.locale = new Locale("de");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_FRENCH:
				conf.locale = new Locale("fr");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SPANISH:
				conf.locale = new Locale("es");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_PORTUGUE:
				conf.locale = new Locale("pt");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ITALIAN:
				conf.locale = new Locale("it");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_NEDERLAND:
				conf.locale = new Locale("nl");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SWEDISH:
				conf.locale = new Locale("sv");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_TURKISH:
				conf.locale = new Locale("tr");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_SLOVAKIAN:
				conf.locale = new Locale("sk");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_ESTONIAN:
				conf.locale = new Locale("et");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_FINNISH:
				conf.locale = new Locale("fi");
				break;
			case Home.STATE_DISPLAY_LANGUAGE_HEBREW:
				conf.locale = new Locale("iw");
				break;
			default:
				conf.locale = new Locale("en");
				break;
		}

		res.updateConfiguration(conf, dm);
		
	}
	
	public int GetLanguage(){
		return LanguageIndex;
	}
	

	public void SavePref(){
		//SharedPreferences SharePref = context.getSharedPreferences("Home", 0);
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("LanguageIndex", LanguageIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	
	public void LoadPref(){
		//SharedPreferences SharePref = context.getSharedPreferences("Home", 0);
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		LanguageIndex = SharePref.getInt("LanguageIndex", Home.STATE_DISPLAY_LANGUAGE_ENGLISH);
		Log.d(TAG, "LanguageIndex : " + Integer.toString(LanguageIndex));
		Log.d(TAG,"LoadPref");
		
		if(LanguageIndex == Home.STATE_DISPLAY_LANGUAGE_KOREAN){
			try{
				showSoftInput.setInputMethod(null,imis.get(1).getId());	
			}catch(IndexOutOfBoundsException e){
				showSoftInput.setInputMethod(null, imis.get(0).getId());
			}
		}else{
			showSoftInput.setInputMethod(null, imis.get(0).getId());
			
		}
	}
		
}
