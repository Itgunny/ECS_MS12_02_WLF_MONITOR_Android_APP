package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuBodyList_ParentFragment;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuPreferenceFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "MenuPreferenceFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(false);
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_PREFERENCE_TOP;
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_PREFERENCE_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Preference), 198);
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BrightnessDisplay(ParentActivity.BrightnessManualAuto);
		SoundOutputDisplay(ParentActivity.SoundState);
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		setClickableList4(true);
		setClickableList5(true);
		setClickableList6(true);

		
		setListTitle1(ParentActivity.getResources().getString(string.Brightness_Setting), 412);
		setListTitle2(ParentActivity.getResources().getString(string.Clock_Setting), 413);
		setListTitle3(ParentActivity.getResources().getString(string.Unit_Setting), 414);
		setListTitle4((ParentActivity.getResources().getString(string.Display_Style) + " / " + ParentActivity.getResources().getString(string.Language)), 416, 422);
		setListTitle5(ParentActivity.getResources().getString(string.Sound_Output_Setting), 415);	
		setListTitle6(ParentActivity.getResources().getString(string.Camera_Setting), 216);
		
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyBrightnessAnimation();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyClockAnimation();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity._MenuBaseFragment.showBodyUnitAnimation();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		ParentActivity._MenuBaseFragment.showBodyDisplayTypeLangList();
		CursurIndex = 4;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		// ++, 150324 bwk
		ParentActivity.showSoundOutput();
		//ParentActivity._MenuBaseFragment.showBodySoundOutputSettingAnimation();
		// --, 150324 bwk
		CursurIndex = 5;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		ParentActivity._MenuBaseFragment.showBodyCameraSettingAnimation();
		CursurIndex = 6;
		CursurDisplay(CursurIndex);
		
	}
	
	/////////////////////////////////////////////////////////////////////	
	public void BrightnessDisplay(int _data){
		switch (_data) {
		case Home.BRIGHTNESS_MANUAL:
			setListData1(ParentActivity.getResources().getString(string.Manual), 26);
			break;
		case Home.BRIGHTNESS_AUTO:
			setListData1(ParentActivity.getResources().getString(string.Automatic), 27);
			break;
		default:
			break;
		}
		
	}
	public void SoundOutputDisplay(int _data){
		switch (_data) {
		case Home.STATE_INTERNAL_SPK:
			setListData5(ParentActivity.getResources().getString(string.Internal_Speaker), 420);
			break;
		case Home.STATE_EXTERNAL_AUX:
			setListData5(ParentActivity.getResources().getString(string.External_Aux), 421);
			break;
		default:
			break;
		}
	}
	//////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		Log.d(TAG,"ClickLeft");
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickManagement();
			break;
		case 1:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		Log.d(TAG,"ClickRight");
		switch (CursurIndex) {
		case 0:
			if(ParentActivity.LockSmartTerminal == Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK & ParentActivity.LockMultiMedia == Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK){
				ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_TOP);
				ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMode();
			}else {
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMultimedia();
			}
			break;
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickHome();
			break;
		case 1:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 0:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 1:
			imgbtnList[0].callOnClick();
			break;
		case 2:
			imgbtnList[1].callOnClick();
			break;
		case 3:
			imgbtnList[2].callOnClick();
			break;
		case 4:
			imgbtnList[3].callOnClick();
			break;
		case 5:
			imgbtnList[4].callOnClick();
			break;
		case 6:
			imgbtnList[5].callOnClick();
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void CursurDisplay(int Index){
		try {
			switch (Index) {
			case 1:
				setListFocus(1);
				break;
			case 2:
				setListFocus(2);
				break;
			case 3:
				setListFocus(3);
				break;
			case 4:
				setListFocus(4);
				break;
			case 5:
				setListFocus(5);
				break;
			case 6:
				setListFocus(6);
				break;
			default:
				setListFocus(0);
				break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException CursurDisplay");
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
