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

public class MenuModeETCFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CoolingFanReverse;
	int EngineAutoShutdownStatus;
	int EngineAutoShutdownTime;
	int EngineAutoShutdownType;
	int WiperLevel;	
	int WiperStatus;
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
		 TAG = "MenuModeETCFragment";
		Log.d(TAG, "onCreateView");

		InitList();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_TOP;
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
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
		
		
		setListTitle1(ParentActivity.getResources().getString(string.Speedometer_Freq_Setting));
		setListTitle2(ParentActivity.getResources().getString(string.Cooling_Fan_Reverse_Mode));
		setListTitle3(ParentActivity.getResources().getString(string.Wiper_Level_Setting));
		setListTitle4(ParentActivity.getResources().getString(string.Camera_Setting));
		if(CAN1Comm.Get_CheckBKCUComm() == 1){
			setListTitle5(ParentActivity.getResources().getString(string.Engine_Auto_Shutdown));
			setClickableList5(true);
		}else{
			setClickableList5(false);
		}
		
	}
	
	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		CoolingFanReverse = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		EngineAutoShutdownTime = CAN1Comm.Get_SettingTimeforAutomaticEngineShutdown_364_PGN61184_122();
		EngineAutoShutdownStatus = CAN1Comm.Get_AutomaticEngineShutdown_363_PGN61184_122();
		WiperLevel = CAN1Comm.Get_WiperSpeedLevel_718_PGN65433();
		WiperStatus = CAN1Comm.Get_WiperOperationStatus_717_PGN65433();
		EngineAutoShutdownType = CAN1Comm.Get_AutomaticEngineShutdownType_PGN61184_122();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		CoolingFanReverseDisplay(CoolingFanReverse);
		EngineAutoShutdownDisplay(EngineAutoShutdownStatus, EngineAutoShutdownTime);
		WiperDisplay(WiperStatus,WiperLevel);
	}
	//////////////////////////////////////////////////////////////////////////

	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodySpeedometerFreqAnimation();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
	}

	@Override
	public void ClickList2() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyCoolingFanAnimation();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyWiperAnimation();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MODE_ETC_TOP;
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyCameraSettingAnimation();
		CursurIndex = 4;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();		
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyEngineAutoShutdownAnimation();
		CursurIndex = 5;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
		ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity._MenuBaseFragment.showBodyEngineDelayShutdownAnimation();
		CursurIndex = 6;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MenuModeFragment.SetModeFocusIndex(MenuModeFragment.STATE_CURSUR_LIST);
	}



	/////////////////////////////////////////////////////////////////////

	public void ClickLeft(){
		Log.d(TAG,"ClickLeft");
		switch (ParentActivity._MenuBaseFragment._MenuModeFragment.GetModeFocusIndex()) {
		case MenuModeFragment.STATE_CURSUR_LIST:
			switch (CursurIndex) {
			case 1:
				if(CAN1Comm.Get_CheckBKCUComm() == 1){
					CursurIndex = 5;
					CursurDisplay(CursurIndex);
				}else{
					CursurIndex = 4;
					CursurDisplay(CursurIndex);
				}
			
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
			ParentActivity._MenuBaseFragment._MenuModeFragment.showHYD();
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageHYD();
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
				CursurIndex++;
				CursurDisplay(CursurIndex);
				break;
			case 3:
				CursurIndex++;
				CursurDisplay(CursurIndex);
				break;
			case 4:
				if(CAN1Comm.Get_CheckBKCUComm() == 1){
					CursurIndex++;
					CursurDisplay(CursurIndex);
				}else{
					CursurIndex = 1;
					CursurDisplay(CursurIndex);
				}
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
			ParentActivity._MenuBaseFragment._MenuModeFragment.showEngTM();
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageEngineTM();
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
			ParentActivity._MenuBaseFragment._MenuModeFragment._MenuModeTabFragment.setClickImageETC();
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
	public void CoolingFanReverseDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			setListData2(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL:
			setListData2(ParentActivity.getResources().getString(string.Manual));
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			setListData2(ParentActivity.getResources().getString(string.Automatic));
			break;

		default:
			break;
		}
	}
	public void EngineAutoShutdownDisplay(int status, int time){
		switch (status) {
		case CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF:
			setListData5(ParentActivity.getResources().getString(string.Off));
			break;
		case CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_ON:
			// ++, 150212 bwk
			// 받은 값이 2분 이하 이거나 40분 이상일 경우 강제로 맞춤
			if(time < 12)
				time = 12;
			else if(time > 240)
				time = 240;
			//setListData5(ParentActivity.GetSectoMinString(time*10) + ParentActivity.getResources().getString(string.Min));
			setListData5(ParentActivity.GetSectoMinString(time*10, ParentActivity.getResources().getString(string.Min),ParentActivity.getResources().getString(string.Sec)));
			// --, 150212 bwk			
			break;

		default:
			break;
		}
	}
	public void WiperDisplay(int status, int level){
		if(status == CAN1CommManager.DATA_STATE_WIPER_OFF){
			setListData3(ParentActivity.getResources().getString(string.Off));
		}else if(status == CAN1CommManager.DATA_STATE_WIPER_ON){
			switch (level) {
			case CAN1CommManager.DATA_STATE_WIPER_LEVEL1:
				setListData3(ParentActivity.getResources().getString(string.Slow));
				break;
			case CAN1CommManager.DATA_STATE_WIPER_LEVEL2:
				setListData3(ParentActivity.getResources().getString(string.Normal));
				break;
			case CAN1CommManager.DATA_STATE_WIPER_LEVEL3:
				setListData3(ParentActivity.getResources().getString(string.Fast));
				break;
			case CAN1CommManager.DATA_STATE_WIPER_LEVEL4:
				setListData3(ParentActivity.getResources().getString(string.Very_Fast));
				break;
			default:
				break;
			}
		}
	}
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
