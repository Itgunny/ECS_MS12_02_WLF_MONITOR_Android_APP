package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
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
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuModeEngTMFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int TCLockUp;
	int CCOMode;
	int ShiftMode;
	int KickDown;
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
		 TAG = "MenuModeEngTMFragment";
		Log.d(TAG, "onCreateView");

		InitList();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP;
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP);
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		setClickableList4(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.Engine_Setting), 209);
		setListTitle2(ParentActivity.getResources().getString(string.Kick_Down), 207);
		
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
			setListTitle3(ParentActivity.getResources().getString(string.ICCO_Mode), 205);
		else
			setListTitle3(ParentActivity.getResources().getString(string.CCO_Mode), 204);
		
		setListTitle4(ParentActivity.getResources().getString(string.Shift_Mode), 206);

		if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
			setClickableList5(false);
		}else{
			setClickableList5(true);
			setListTitle5(ParentActivity.getResources().getString(string.TC_Lock_Up), 210);
		}
		
		
	}
	
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434(); 
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		ShiftMode = CAN1Comm.Get_TransmissionShiftMode_543_PGN65434();
		KickDown = CAN1Comm.Get_KickDownShiftMode_547_PGN65434();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		TCLockUpDisplay(TCLockUp);
		CCOModeDisplay(CCOMode);
		ShiftModeDisplay(ShiftMode);
		KickDownDisplay(KickDown);
	}
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment._MenuModeFragment.HideTab();
		ParentActivity._MenuBaseFragment._MenuModeFragment.HideEngTM();
		ParentActivity._MenuBaseFragment._MenuModeFragment.showEngineSetting();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		
		ParentActivity.showKickDown();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		
		if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
			ParentActivity.showICCoMode();	
		else
			ParentActivity.showCCoMode();

		
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		
		ParentActivity.showShiftMode();
		
		CursurIndex = 4;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		
		ParentActivity.showTCLockUp();
		
		CursurIndex = 5;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		
	}

	public void ClickLeft(){
		Log.d(TAG,"ClickLeft");
		switch (ParentActivity._MenuBaseFragment._MenuModeFragment.GetModeFocusIndex()) {
		case MenuModeFragment.STATE_CURSUR_LIST:
			switch (CursurIndex) {
			case 1:
				if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
					CursurIndex = 4;
				}else{
					CursurIndex = 5;
				}
				
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
			default:
				break;
			}
			break;
		case MenuModeFragment.STATE_CURSUR_TAB:
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeHYDFragment.CursurIndex = 0;
			ParentActivity._MenuBaseFragment._MenuModeFragment.showETC();
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
			break;
		case MenuModeFragment.STATE_CURSUR_LEFT:
			if(ParentActivity.LockSmartTerminal == Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK & ParentActivity.LockMultiMedia == Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK){
				ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickPreference();
			} else {
				ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMultimedia();
			}
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		Log.d(TAG,"ClickRight");
		switch (ParentActivity._MenuBaseFragment._MenuModeFragment.GetModeFocusIndex()) {
		case MenuModeFragment.STATE_CURSUR_LIST:
			switch (CursurIndex) {
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
				if(ParentActivity._CheckModel.GetTCUModel(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_TCU()) == CheckModel.TCU_4SPEED){
					CursurIndex = 1;
				}else{
					CursurIndex++;
				}
				CursurDisplay(CursurIndex);
				break;
			case 5:
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
				break;
			default:
				break;
			}
			break;
		case MenuModeFragment.STATE_CURSUR_TAB:
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeHYDFragment.CursurIndex = 0;
			ParentActivity._MenuBaseFragment._MenuModeFragment.showHYD();
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
			break;
		case MenuModeFragment.STATE_CURSUR_LEFT:
			
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMonitoring();
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		switch (ParentActivity._MenuBaseFragment._MenuModeFragment.GetModeFocusIndex()) {
		case MenuModeFragment.STATE_CURSUR_LIST:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_TAB);
			break;
		case MenuModeFragment.STATE_CURSUR_TAB:
			CursurIndex = 0;
			CursurDisplay(CursurIndex);
			ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LEFT);
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickNull();
			break;
		case MenuModeFragment.STATE_CURSUR_LEFT:
			ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickHome();
			break;
		default:
			break;
		}
	}
	public void ClickEnter(){
		switch (ParentActivity._MenuBaseFragment._MenuModeFragment.GetModeFocusIndex()) {
		case MenuModeFragment.STATE_CURSUR_LIST:
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
			case 4:
				imgbtnList[3].callOnClick();
				break;
			case 5:
				imgbtnList[4].callOnClick();
				break;
			default:
				break;
			}
			break;
		case MenuModeFragment.STATE_CURSUR_TAB:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
			break;
		case MenuModeFragment.STATE_CURSUR_LEFT:
			ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_TAB);
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
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
	public void TCLockUpDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			setListData5(ParentActivity.getResources().getString(string.Off), 20);
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			setListData5(ParentActivity.getResources().getString(string.On), 19);
			break;
		default:
			break;
		}
		
	}
	public void CCOModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			setListData3(ParentActivity.getResources().getString(string.Off), 20);
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
			setListData3(ParentActivity.getResources().getString(string.L), 99);
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
			setListData3(ParentActivity.getResources().getString(string.M), 100);
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			if(ParentActivity._CheckModel.GetMCUModelNum(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == 980)
				setListData3(ParentActivity.getResources().getString(string.On), 19);
			else
				setListData3(ParentActivity.getResources().getString(string.H), 101);
			break;
		default:
			break;
		}
	}
	public void ShiftModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
			setListData4(ParentActivity.getResources().getString(string.Manual), 26);
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			setListData4(ParentActivity.getResources().getString(string.AL), 103);
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			setListData4(ParentActivity.getResources().getString(string.AN), 104);
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			setListData4(ParentActivity.getResources().getString(string.AH), 105);
			break;
		default:
			break;
		}
	}
	public void KickDownDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			setListData2(ParentActivity.getResources().getString(string.MODE1), 219);
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			setListData2(ParentActivity.getResources().getString(string.MODE2), 220);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
