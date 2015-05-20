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
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Security));
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
		
		setListTitle1(ParentActivity.getResources().getString(string.ESL_System_Setting));
		setListTitle2(ParentActivity.getResources().getString(string.Change_Password));
		setListTitle3(ParentActivity.getResources().getString(string.Smart_Key));
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
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS:
			str = ParentActivity.getResources().getString(string.On_Always);
			break;
		case CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_INTERVAL:
			switch (interval) {
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_5MIN:
				str = "5" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_10MIN:
				str = "10" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_20MIN:
				str = "20" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_30MIN:
				str = "30" + ParentActivity.getResources().getString(string.Min);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1HR:
				str = "1" + ParentActivity.getResources().getString(string.Hour);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2HR:
				str = "2" + ParentActivity.getResources().getString(string.Hour);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_4HR:
				str = "4" + ParentActivity.getResources().getString(string.Hour);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_1DAY:
				str = "1" + ParentActivity.getResources().getString(string.Day);
				break;
			case CAN1CommManager.DATA_STATE_ESL_INTERVAL_2DAY:
				str = "2" + ParentActivity.getResources().getString(string.Day);
				break;

			default:
				break;
			}
			break;
		default:
			break;
		}
		
		setListData1(str);
	}
	public void SmartKeyDisplay(int _data){
		switch (_data) {
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF:
			setListData3(ParentActivity.getResources().getString(string.Disable));
			break;
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON:
			setListData3(ParentActivity.getResources().getString(string.Enable));
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
