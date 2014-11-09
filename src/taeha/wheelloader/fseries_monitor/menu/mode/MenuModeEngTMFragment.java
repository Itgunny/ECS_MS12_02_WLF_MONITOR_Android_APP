package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
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
		setClickableList5(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.Engine_Setting));
		setListTitle2(ParentActivity.getResources().getString(string.TC_Lock_Up));
		setListTitle3(ParentActivity.getResources().getString(string.CCO_Mode));
		setListTitle4(ParentActivity.getResources().getString(string.Shift_Mode));
		setListTitle5(ParentActivity.getResources().getString(string.Kick_Down));
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
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		ParentActivity.showTCLockUp();
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		ParentActivity.showCCoMode();	
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		ParentActivity.showShiftMode();
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		ParentActivity.showKickDown();
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		
	}

	public void ClickLeft(){
		
		
	}
	public void ClickRight(){
		
		
	}
	public void ClickESC(){
		
	}
	public void ClickEnter(){
	
		
	}

	/////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP:
			setListFocus(1);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_TCLOCKUP:
			setListFocus(2);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE:
			setListFocus(3);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_SHIFTMODE:
			setListFocus(4);
			break;
		case Home.SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN:
			setListFocus(5);
			break;
		default:
			setListFocus(0);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void TCLockUpDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			setListData2(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			setListData2(ParentActivity.getResources().getString(string.On));
			break;
		default:
			break;
		}
		
	}
	public void CCOModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			setListData3(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
			setListData3(ParentActivity.getResources().getString(string.L));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
			setListData3(ParentActivity.getResources().getString(string.M));
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			setListData3(ParentActivity.getResources().getString(string.H));
			break;
		default:
			break;
		}
	}
	public void ShiftModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_MANUAL:
			setListData4(ParentActivity.getResources().getString(string.Manual));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AL:
			setListData4(ParentActivity.getResources().getString(string.AL));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AN:
			setListData4(ParentActivity.getResources().getString(string.AN));
			break;
		case CAN1CommManager.DATA_STATE_TM_SHIFTMODE_AH:
			setListData4(ParentActivity.getResources().getString(string.AH));
			break;
		default:
			break;
		}
	}
	public void KickDownDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			setListData5(ParentActivity.getResources().getString(string.MODE1));
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			setListData5(ParentActivity.getResources().getString(string.MODE2));
			break;

		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
