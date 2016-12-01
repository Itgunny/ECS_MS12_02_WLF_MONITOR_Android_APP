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

public class MenuModeHYDFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BucketPriority;
	int MaxFlowLevel;
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
		 TAG = "MenuModeHYDFragment";
		Log.d(TAG, "onCreateView");

		InitList();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_HYD_TOP;
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
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
		
		setListTitle1(ParentActivity.getResources().getString(string.Work_Load),156);
		setListTitle2(ParentActivity.getResources().getString(string.Boom_Bucket_Detent_Mode),212);
		if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
			setClickableList3(false);
			setClickableList4(false);
			setClickableList5(false);
		}else{
			setClickableList3(true);
			setClickableList4(true);
			setClickableList5(true);
			setListTitle3(ParentActivity.getResources().getString(string.Bucket_Priority), 211);
			setListTitle4(ParentActivity.getResources().getString(string.Auxilliary_Attachment_Max_Flow_Level), 213);
			setWidthTitle4(500);
			setListTitle5(ParentActivity.getResources().getString(string.Soft_End_Stop), 214);
		}
		
	}
	
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		BucketPriority = CAN1Comm.Get_BucketPriorityOperation_2301_PGN65517();
		MaxFlowLevel = CAN1Comm.Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BucketPriorityDisplay(BucketPriority);
		MaxFlowLevelDisplay(MaxFlowLevel);
	}
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyWorkLoadAnimation();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyDetentAnimation();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		
		ParentActivity.showBucketPriority();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMaxFlowAnimation();
		CursurIndex = 4;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySoftStopAnimation();
		CursurIndex = 5;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		
	}



	/////////////////////////////////////////////////////////////////////

	public void ClickLeft(){
		Log.d(TAG,"ClickLeft");
		switch (ParentActivity._MenuBaseFragment._MenuModeFragment.GetModeFocusIndex()) {
		case MenuModeFragment.STATE_CURSUR_LIST:
			switch (CursurIndex) {
			case 1:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
					CursurIndex = 2;
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
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
					CursurIndex = 2;
				}else{
					CursurIndex--;
				}
				CursurDisplay(CursurIndex);
				break;
			case 4:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
					CursurIndex = 2;
				}else{
					CursurIndex--;
				}
				CursurDisplay(CursurIndex);
				break;
			case 5:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
					CursurIndex = 2;
				}else{
					CursurIndex--;
				}
				CursurDisplay(CursurIndex);
				break;
			default:
				break;
			}
			break;
		case MenuModeFragment.STATE_CURSUR_TAB:
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeHYDFragment.CursurIndex = 0;
			ParentActivity._MenuBaseFragment._MenuModeFragment.showEngTM();
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
			break;
		case MenuModeFragment.STATE_CURSUR_LEFT:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMultimedia();
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
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
					CursurIndex = 1;
				}else{
					CursurIndex++;
				}
				CursurDisplay(CursurIndex);
				break;
			case 3:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
					CursurIndex = 1;
				}else{
					CursurIndex++;
				}
				CursurDisplay(CursurIndex);
				break;
			case 4:
				if(CAN1Comm.Get_ComponentCode_1699_PGN65330_EHCU() != CAN1CommManager.STATE_COMPONENTCODE_EHCU){
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
			ParentActivity._MenuBaseFragment._MenuModeFragment.showETC();
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
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
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
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
	public void BucketPriorityDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF:
			setListData3(ParentActivity.getResources().getString(string.Off), 20);
			break;
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_ON:
			setListData3(ParentActivity.getResources().getString(string.On), 19);
			break;
		default:
			break;
		}
	}
	public void MaxFlowLevelDisplay(int data){
		setListData4(Integer.toString(data));
	}
	/////////////////////////////////////////////////////////////////////	
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
}
