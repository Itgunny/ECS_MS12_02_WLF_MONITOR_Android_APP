package taeha.wheelloader.fseries_monitor.menu.preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class DisplayTypeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewTypeA;
	TextView textViewTypeB;
	//////////////////////////////////////////////////

	//VALUABLE////////////////////////////////////////
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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "DisplayTypeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_displaytype, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TYPE;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Display_Style));
		
		CursurDisplay(CursurIndex);

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
	protected void InitValuables() {
		// TODO Auto-generated method stub
		CursurIndex = ParentActivity.DisplayType;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTypeA = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_displaytype_a);
		textViewTypeB = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_displaytype_b);
	}
	
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewTypeA.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 0;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetDisplayType();
			}
		});
		
		textViewTypeB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				SetDisplayType();
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
		case 0:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 1:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 0:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 1:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showDisplayTypeLangAnimation();
	}
	public void ClickEnter(){
		SetDisplayType();
	}
	/////////////////////////////////////////////////////////////////////	
	public void CursurDisplay(int Index){
		textViewTypeA.setPressed(false);
		textViewTypeB.setPressed(false);
		switch (Index) {
		case 0:
			textViewTypeA.setPressed(true);
			break;
		case 1:
			textViewTypeB.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void SetDisplayType(){
		ParentActivity.DisplayType = CursurIndex;
		SavePref();

		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showDisplayTypeLangAnimation();
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("DisplayType", CursurIndex);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////

}
