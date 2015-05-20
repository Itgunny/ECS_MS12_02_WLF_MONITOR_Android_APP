package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
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

public class CalibrationFragment extends MenuBodyList_ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	FrameLayout LayoutList;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	static int CursurIndex = 1;
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
		 TAG = "CalibrationFragment";
		Log.d(TAG, "onCreateView");
		
		InitList();
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_CALIBRATION_TOP;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Calibration));
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
		
	}
	@Override
	protected void InitList() {
		// TODO Auto-generated method stub
		setClickableList1(true);
		setClickableList2(true);
		setClickableList3(true);
		setClickableList4(true);

		
		setListTitle1(ParentActivity.getResources().getString(string.Boom_Bucket_Angle_Calibration));
		setListTitle2(ParentActivity.getResources().getString(string.Boom_Pressure_Calibration));
		setListTitle3(ParentActivity.getResources().getString(string.Brake_Pedal_Sensor_Calibration));
		setListTitle4(ParentActivity.getResources().getString(string.AEB));
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void ClickList1() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyAngleCalibrationAnimation();
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
		ParentActivity._MenuBaseFragment.showBodyPressureCalibrationAnimation();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList3() {
		// TODO Auto-generated method stub
		ParentActivity.showBrkaePedalCalibration();
		CursurIndex = 3;
		CursurDisplay(CursurIndex);
	}

	@Override
	public void ClickList4() {
		// TODO Auto-generated method stub
		CAN1Comm.Set_RequestAEB_PGN61184_201(1);
		CAN1Comm.TxCANToMCU(201);
		// ++, 150309 bwk
		//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
		ParentActivity.showMainScreen();
		// --, 150309 bwk
		
		CursurIndex = 4;
		CursurDisplay(CursurIndex);
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
	public void ClickLeft(){
		switch (CursurIndex) {
		case 0:
			
			break;
		case 1:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
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
		case 2:
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
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
		case 4:
			imgbtnList[3].callOnClick();
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
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
