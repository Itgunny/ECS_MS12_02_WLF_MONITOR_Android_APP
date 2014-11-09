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
		
		setListTitle1(ParentActivity.getResources().getString(string.Work_Load));
		setListTitle2(ParentActivity.getResources().getString(string.Boom_Bucket_Detent_Mode));
		setListTitle3(ParentActivity.getResources().getString(string.Bucket_Priority));
		setListTitle4(ParentActivity.getResources().getString(string.Auxilliary_Attachment_Max_Flow_Level));
		setListTitle5(ParentActivity.getResources().getString(string.Soft_Stop));
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
		
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyDetentAnimation();
		
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		
		ParentActivity.showBucketPriority();
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMaxFlowAnimation();
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySoftStopAnimation();
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		
	}



	/////////////////////////////////////////////////////////////////////
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
		case Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP:
			setListFocus(1);
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_DETENT:
			setListFocus(2);
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_BUCKETPRIORITY:
			setListFocus(3);
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_MAXFLOW:
			setListFocus(4);
			break;
		case Home.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP:
			setListFocus(5);
			break;
		default:
			setListFocus(0);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void BucketPriorityDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_OFF:
			setListData3(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_BUCKETPRIORITY_ON:
			setListData3(ParentActivity.getResources().getString(string.On));
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
