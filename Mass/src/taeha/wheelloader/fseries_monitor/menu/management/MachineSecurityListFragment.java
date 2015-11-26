package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
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

public class MachineSecurityListFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int ESLInterval;
	int ESLMode;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	static int CursurIndex = 1;
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
		 TAG = "MachineSecurityListFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Security), 319);
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ESLMode = CAN1Comm.Get_ESLMode_820_PGN65348();
		ESLInterval = CAN1Comm.Get_ESLInterval_825_PGN65348();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ESLSystemDisplay(ESLMode,ESLInterval);
		SmartKeyDisplay(ParentActivity.SmartKeyUse);
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.ESL_System_Setting), 324);
		setListTitle2(ParentActivity.getResources().getString(string.Change_Password), 325);
		setListTitle3(ParentActivity.getResources().getString(string.Smart_Key), 326);
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		if(ParentActivity.SmartKeyUse != CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON){
			ParentActivity._MenuBaseFragment.showBodyESLAnimation();
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
		}
	
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMachineSecurityPWChange();
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
		ParentActivity._MenuBaseFragment.showBodySmartKeyAnimation();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		
	}
	
	/////////////////////////////////////////////////////////////////////	
	
	/////////////////////////////////////////////////////////////////////
	public void ESLSystemDisplay(int mode, int interval){
		String str = "";
		switch (mode) {
		case CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE:
			str = ParentActivity.getResources().getString(string.Disable);
			setListData1(str, 21);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			str = ParentActivity.getResources().getString(string.On_Always);
			setListData1(str, 23);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			switch (interval) {
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "5" + ParentActivity.getResources().getString(string.Min);
				}else {
					str = "5" + ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "10" + ParentActivity.getResources().getString(string.Min);
				}else {
					str = "10" + ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "20" + ParentActivity.getResources().getString(string.Min);
				}else {
					str = "20" + ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "30" + ParentActivity.getResources().getString(string.Min);
				}else {
					str = "30" + ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "1" + ParentActivity.getResources().getString(string.Hour);
				}else {
					str = "1" + ParentActivity.langDb.findStrGetString(47, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "2" + ParentActivity.getResources().getString(string.Hour);
				}else {
					str = "2" + ParentActivity.langDb.findStrGetString(47, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "4" + ParentActivity.getResources().getString(string.Hour);
				}else {
					str = "4" + ParentActivity.langDb.findStrGetString(47, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "1" + ParentActivity.getResources().getString(string.Day);
				}else {
					str = "1" + ParentActivity.langDb.findStrGetString(49, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY:
				if(ParentActivity.langDb.findStrGetString(48, ParentActivity.LanguageIndex) == null){
				str = "2" + ParentActivity.getResources().getString(string.Day);
				}else {
					str = "2" + ParentActivity.langDb.findStrGetString(49, ParentActivity.LanguageIndex);
				}
				setListData1(str);
				break;

			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	public void SmartKeyDisplay(int _data){
		switch (_data) {
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF:
			setListData3(ParentActivity.getResources().getString(string.Disable), 21);
			break;
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON:
			setListData3(ParentActivity.getResources().getString(string.Enable), 22);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 0:
			
			break;
		case 1:
			CursurIndex = 3;
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
		default:
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 0:
			
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickBack();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			imgbtnList[0].callOnClick();
			break;
		case 2:
			imgbtnList[1].callOnClick();
			break;
		case 3:
			imgbtnList[2].callOnClick();
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
				setListFocus(5);
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
}
