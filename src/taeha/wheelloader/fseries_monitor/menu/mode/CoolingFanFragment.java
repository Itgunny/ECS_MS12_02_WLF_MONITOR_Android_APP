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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RadioButton;
import android.widget.TextView;

public class CoolingFanFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioManual;
	RadioButton radioAuto;
	RadioButton radioOff;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CoolingFanReverse;
	
	int CursurIndex;
	
	Handler HandleCursurDisplay;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	public CoolingFanManualFragment	_CoolingFanManualFragment;
	public CoolingFanAutoFragment 	_CoolingFanAutoFragment;
	public CoolingFanOffFragment	_CoolingFanOffFragment;
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
		 TAG = "CoolingFanFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_coolingfan, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		setCoolingFanReverseRadio(CoolingFanReverse);
		CursurFirstDisplay(CoolingFanReverse);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Cooling_Fan_Reverse_Mode));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioManual = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfan_manual);
		radioAuto = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfan_auto);
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_coolingfan_off);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		_CoolingFanManualFragment = new CoolingFanManualFragment();
		_CoolingFanAutoFragment = new CoolingFanAutoFragment();		
		_CoolingFanOffFragment = new CoolingFanOffFragment();
		
		CoolingFanReverse = CAN1Comm.Get_CoolingFanReverseMode_182_PGN65369();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickManual();
			}
		});
		radioAuto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAuto();
			}
		});
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOff();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickManual(){
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL)
			showBodyCoolingFanManual();
	}
	public void ClickAuto(){
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_AUTO)
			showBodyCoolingFanAuto();
	}
	public void ClickOff(){
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_OFF)
			showBodyCoolingFanOff();
		Log.d(TAG,"ClickOff");
	}
	/////////////////////////////////////////////////////////////////////
	public void showBodyCoolingFanManual(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_coolingfan, _CoolingFanManualFragment);
		transaction.commit();
		
	}
	public void showBodyCoolingFanAuto(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_coolingfan, _CoolingFanAutoFragment);
		transaction.commit();
		
	}
	public void showBodyCoolingFanOff(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_menu_body_mode_coolingfan, _CoolingFanOffFragment);
		transaction.commit();
		
	}	
	/////////////////////////////////////////////////////////////////////
	public void setCoolingFanReverseRadio(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			radioOff.setChecked(true);
			radioManual.setChecked(false);
			radioAuto.setChecked(false);
			ClickOff();
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL:
			radioOff.setChecked(false);
			radioManual.setChecked(true);
			radioAuto.setChecked(false);
			ClickManual();
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			radioOff.setChecked(false);
			radioManual.setChecked(false);
			radioAuto.setChecked(true);
			ClickAuto();
			break;

		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void setCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
		switch (CursurIndex) {
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
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
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		
	}
	public void ClickEnter(){
		Log.d(TAG,"ClickEnter");
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			_CoolingFanOffFragment.setCursurIndex(5);
			setCoolingFanReverseRadio(CAN1CommManager.DATA_STATE_REVERSEFAN_OFF);
			_CoolingFanOffFragment.CursurDisplay(5);
			break;
		case 2:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			_CoolingFanAutoFragment.setCursurIndex(4);
			setCoolingFanReverseRadio(CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO);
			_CoolingFanAutoFragment.CursurDisplay(4);
			break;
		case 3:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			_CoolingFanManualFragment.setCursurIndex(4);
			setCoolingFanReverseRadio(CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL);
			_CoolingFanManualFragment.CursurDisplay(4);
			break;
		default:

			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_REVERSEFAN_OFF:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_REVERSEFAN_MANUAL:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){

		radioOff.setPressed(false);
		radioAuto.setPressed(false);
		radioManual.setPressed(false);

		switch (CursurIndex) {
			case 1:
				radioOff.setPressed(true);
				break;
			case 2:
				radioAuto.setPressed(true);
				break;
			case 3:
				radioManual.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
