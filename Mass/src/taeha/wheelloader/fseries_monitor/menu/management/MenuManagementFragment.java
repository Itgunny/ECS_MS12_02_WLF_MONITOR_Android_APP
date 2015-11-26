package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home.SeatBeltTimerClass;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.MenuBodyList_ParentFragment;
import taeha.wheelloader.fseries_monitor.menu.management.MaintenanceFragment.SendCommandTimerClass;
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

public class MenuManagementFragment extends MenuBodyList_ParentFragment{
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
	
	//Test////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "MenuManagementFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(false);
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_TOP;
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Management), 197);
		CursurDisplay(CursurIndex);
		// ++, 150325 bwk
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.COMMAND_MAINTENANCE_ITEM_LIST_REQUEST);
		CAN1Comm.TxCANToMCU(12);
		// --, 150325 bwk
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
		
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		setClickableList4(true);
		setClickableList5(true);
		
		setListTitle1(ParentActivity.getResources().getString(string.Machine_Security), 319);
		setListTitle2(ParentActivity.getResources().getString(string.Maintenance),408);
		//setListTitle3(ParentActivity.getResources().getString(string.Calibration));
		setListTitle3(ParentActivity.getResources().getString(string.Service_Menu), 323);
		setListTitle4(ParentActivity.getResources().getString(string.Change_AS_Phone_Number), 322);
		setListTitle5(ParentActivity.getResources().getString(string.Software_Update), 274);		// ++, --, 150323 bwk
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyMachineSecurityPassword();
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
		
		ParentActivity._MenuBaseFragment.showBodyMaintenanceAnimation();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
		ParentActivity._MenuBaseFragment._MaintenanceFragment.setCursurIndex(1);
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		//ParentActivity._MenuBaseFragment.showBodyCalibration();
		ParentActivity._MenuBaseFragment.showBodyServiceMenuPassword();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyChangeASPhoneNumberAnimation();
		CursurIndex = 4;
		CursurDisplay(CursurIndex);
		
	}

	@Override
	public void ClickList5() {
		// TODO Auto-generated method stub
		
		int rpm = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		int AlternatorVoltage = CAN1Comm.Get_AlternatorVoltage_707_PGN65360();
		
		Log.d(TAG, "rpm="+rpm+"AlternatorVoltage"+AlternatorVoltage);

		if((rpm >= 500 && rpm < 8031) && (AlternatorVoltage > 255 && AlternatorVoltage <= 360))
		{
			ParentActivity.showSoftwareUpdateErrorPopup();
		}
		else
		{
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			else
				ParentActivity.StartAnimationRunningTimer();
			ParentActivity._MenuBaseFragment.showBodySoftwareUpdatePassword();
		}
		CursurIndex = 5;
		
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList6() {
		// TODO Auto-generated method stub
		// ++, 150323 bwk
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CursurIndex = 6;
		CursurDisplay(CursurIndex);
		// --, 150323 bwk
	}
	
	public void ClickLeft(){
		Log.d(TAG,"ClickLeft");
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickMonitoring();
			break;
		case 1:
			CursurIndex = 5;
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
			// ++, 150323 bwk
			CursurIndex--;
			CursurDisplay(CursurIndex);
			// --, 150323 bwk
			break;
		default:
			break;
		}
	}
	public void ClickRight(){
		Log.d(TAG,"ClickRight");
		switch (CursurIndex) {
		case 0:
			ParentActivity._MenuBaseFragment._MenuListLeftFragment.ClickPreference();
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
			// ++, 150323 bwk
			CursurIndex = 1;
			// --, 150323 bwk
			CursurDisplay(CursurIndex);
			break;
		case 6:
			// ++, 150323 bwk
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			// --, 150323 bwk
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
			imgbtnList[5].callOnClick();		// ++, --, 150323 bwk
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
			// ++, 150323 bwk
			case 6:
				setListFocus(6);
				break;
			// --, 150323 bwk
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
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
