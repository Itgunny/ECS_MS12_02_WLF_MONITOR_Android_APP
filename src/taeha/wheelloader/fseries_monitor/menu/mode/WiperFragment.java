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

public class WiperFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton	imgbtnCancel;
	
	RadioButton radioLevel1;
	RadioButton radioLevel2;
	RadioButton radioLevel3;
	RadioButton radioLevel4;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WiperLevel;
	
	int CursurIndex;
	
	Handler HandleCursurDisplay;
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
		 TAG = "WiperFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_wiper, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_WIPER_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Wiper_Level_Setting));
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_wiper_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_wiper_low_cancel);
		
		radioLevel1 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_wiper_level1);
		radioLevel2 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_wiper_level2);
		radioLevel3 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_wiper_level3);
		radioLevel4 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_wiper_level4);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		WiperLevel = CAN1Comm.Get_WiperSpeedLevel_718_PGN65433();
		WiperLevelDisplay(WiperLevel);
		CursurFirstDisplay(WiperLevel);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioLevel1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLevel1();
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioLevel2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLevel2();
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioLevel3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLevel3();
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioLevel4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLevel4();
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.OldScreenIndex = 0;
		}else{
			ParentActivity._MenuBaseFragment.showBodyModeAnimation();
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		}
		
		CAN1Comm.Set_WiperSpeedLevel_718_PGN61184_109(WiperLevel);
		CAN1Comm.TxCANToMCU(109);
		CAN1Comm.Set_WiperSpeedLevel_718_PGN61184_109(0xFF);
		
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.OldScreenIndex = 0;
		}else{
			ParentActivity._MenuBaseFragment.showBodyModeAnimation();
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		}
	}
	public void ClickLevel1(){
		radioLevel1.setChecked(true);
		radioLevel2.setChecked(false);
		radioLevel3.setChecked(false);
		radioLevel4.setChecked(false);
		WiperLevel = CAN1CommManager.DATA_STATE_WIPER_LEVEL1;
	}
	public void ClickLevel2(){
		radioLevel1.setChecked(false);
		radioLevel2.setChecked(true);
		radioLevel3.setChecked(false);
		radioLevel4.setChecked(false);
		WiperLevel = CAN1CommManager.DATA_STATE_WIPER_LEVEL2;
	}
	public void ClickLevel3(){
		radioLevel1.setChecked(false);
		radioLevel2.setChecked(false);
		radioLevel3.setChecked(true);
		radioLevel4.setChecked(false);
		WiperLevel = CAN1CommManager.DATA_STATE_WIPER_LEVEL3;
	}
	public void ClickLevel4(){
		radioLevel1.setChecked(false);
		radioLevel2.setChecked(false);
		radioLevel3.setChecked(false);
		radioLevel4.setChecked(true);
		WiperLevel = CAN1CommManager.DATA_STATE_WIPER_LEVEL4;
	}
	/////////////////////////////////////////////////////////////////////
	public void WiperLevelDisplay(int _data){
		switch (_data) {
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL1:
			radioLevel1.setChecked(true);
			radioLevel2.setChecked(false);
			radioLevel3.setChecked(false);
			radioLevel4.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL2:
			radioLevel1.setChecked(false);
			radioLevel2.setChecked(true);
			radioLevel3.setChecked(false);
			radioLevel4.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL3:
			radioLevel1.setChecked(false);
			radioLevel2.setChecked(false);
			radioLevel3.setChecked(true);
			radioLevel4.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL4:
			radioLevel1.setChecked(false);
			radioLevel2.setChecked(false);
			radioLevel3.setChecked(false);
			radioLevel4.setChecked(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 6;
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 6:
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
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickLevel1();
			break;
		case 2:
			ClickLevel2();
			break;
		case 3:
			ClickLevel3();
			break;
		case 4:
			ClickLevel4();
			break;
		case 5:
			ClickCancel();
			break;
		case 6:
			ClickOK();
			break;
			
		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL1:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL2:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL3:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_WIPER_LEVEL4:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		radioLevel1.setPressed(false);
		radioLevel2.setPressed(false);
		radioLevel3.setPressed(false);
		radioLevel4.setPressed(false);
		
		
		switch (CursurIndex) {
		case 1:
			radioLevel1.setPressed(true);
			break;
		case 2:
			radioLevel2.setPressed(true);
			break;
		case 3:
			radioLevel3.setPressed(true);
			break;
		case 4:
			radioLevel4.setPressed(true);
			break;
		case 5:
			imgbtnCancel.setPressed(true);
			break;
		case 6:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
