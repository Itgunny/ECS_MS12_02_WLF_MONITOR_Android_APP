package taeha.wheelloader.fseries_monitor.menu;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuInterTitleFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		 TAG = "MenuInterTitleFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_inter_title, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_inter_title_text);
	
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
		textViewTitle.setText(str);
		TitleTextShiftAnimation.StartShiftAnimation();
		TitleTextAppearAnimation.StartAnimation();
	}
	public void SetTitleText(String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			textViewTitle.setText(str);
			Log.d(TAG, "Android");
		}else {
			textViewTitle.setText(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex));
			Log.d(TAG, "Excel");
		}
		TitleTextShiftAnimation.StartShiftAnimation();
		TitleTextAppearAnimation.StartAnimation();
	}
	public void SetTitleText(String str, int index, int index2){
		if((ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null) || 
				(ParentActivity.langDb.findStrGetString(index2, ParentActivity.LanguageIndex) == null)){
			textViewTitle.setText(str);
			Log.d(TAG, "Android");
		}else {
			textViewTitle.setText(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) + " - " + ParentActivity.langDb.findStrGetString(index2, ParentActivity.LanguageIndex));
			Log.d(TAG, "Excel");
		}
		TitleTextShiftAnimation.StartShiftAnimation();
		TitleTextAppearAnimation.StartAnimation();
	}
	
	/////////////////////////////////////////////////////////////////////
	
}
