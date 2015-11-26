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
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EngineSettingFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
	//int EngineWarmingUp;		// ++, --, 150323 bwk
	int EngineRPM;
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
		 TAG = "EngineSettingFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Engine_Setting), 209);
		CursurDisplay(CursurIndex);
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	//	ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		//EngineWarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();	// ++, --, 150323 bwk
		EngineRPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		EngineModeDisplay(EngineMode);
		//EngineWarmingUpDisplay(EngineWarmingUp);	// ++, --, 150323 bwk
		EngineRPMDisplay(EngineRPM);
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		//setClickableList2(true);	// ++, --, 150323 bwk
		

		setListTitle1(ParentActivity.getResources().getString(string.Engine_Mode), 240);
		// ++, 150323 bwk
//		setListTitle2(ParentActivity.getResources().getString(string.Warming_Up));
//		if(CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM() == CheckModel.STATE_MANUFACTURERCODE_SCANIA){
//			setClickableList3(false);
//		}else{
//			setClickableList3(true);
//			setListTitle3(ParentActivity.getResources().getString(string.Engine_Speed));
//		}
		if(CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM() == CheckModel.STATE_MANUFACTURERCODE_SCANIA){
			setClickableList2(false);
		}else{
			setClickableList2(true);
			setListTitle2(ParentActivity.getResources().getString(string.Engine_Speed), 208);
		}
		// --, 150323 bwk
	
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		ParentActivity.showEngineMode();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		// ++, 150323 bwk
		//ParentActivity.showEngineWarmingUp();
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyEngineSpeedAnimation();
		// --, 150323 bwk
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		// ++, 150323 bwk
//		if(ParentActivity.AnimationRunningFlag == true)
//			return;
//		else
//			ParentActivity.StartAnimationRunningTimer();
//		
//		ParentActivity._MenuBaseFragment.showBodyEngineSpeedAnimation();
//		CursurIndex = 3;
//		CursurDisplay(CursurIndex);
		// --, 150323 bwk
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
	public void EngineModeDisplay(int data){
	
		switch (data) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			setListData1(ParentActivity.getResources().getString(string.Power),243);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			setListData1(getResources().getString(string.Standard), 242);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			setListData1(getResources().getString(string.Econo), 241);
			break;
		default:
			break;
		}
		
	}
	// ++, 150323 bwk 
//	public void EngineWarmingUpDisplay(int data){
//		
//		switch (data) {
//		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
//			setListData2(getResources().getString(string.Off));
//			break;
//		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
//			setListData2(getResources().getString(string.On));
//			break;
//		default:
//			break;
//
//		}
//	} 
//	public void EngineRPMDisplay(int data){
//		if(data > 8031)	// Operational Range : 0 to 8,031
//			data = 0;
//		setListData3(Integer.toString(data));
//	}	
	public void EngineRPMDisplay(int data){
	if(data > 8031)	// Operational Range : 0 to 8,031
		data = 0;
	setListData2(Integer.toString(data));
	}	
	// --, 150323 bwk
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 0:
			
			break;
		case 1:
			// ++, 150323 bwk
//			if(CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM() == CheckModel.STATE_MANUFACTURERCODE_SCANIA){
//				CursurIndex = 2;
//			}else{
//				CursurIndex = 3;
//			}
			if(CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM() == CheckModel.STATE_MANUFACTURERCODE_SCANIA){
				CursurIndex = 1;
			}else{
				CursurIndex = 2;
			}
			// --, 150323 bwk
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
			// ++, 150323 bwk
			//CursurIndex++;
			if(CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM() == CheckModel.STATE_MANUFACTURERCODE_SCANIA){
				CursurIndex = 1;
			}else{
				CursurIndex++;
			}
			// --, 150323 bwk
			CursurDisplay(CursurIndex);
			break;
		case 2:
			// ++, 150323 bwk
//			if(CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM() == CheckModel.STATE_MANUFACTURERCODE_SCANIA){
//				CursurIndex = 1;
//			}else{
//				CursurIndex++;
//			}
			CursurIndex = 1;
			// --, 150323 bwk
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
		CursurIndex = 1;
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
