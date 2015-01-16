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

public class CoolingFanManualFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	
	TextView textViewCoolingFanManual;
	
	ImageView imgViewCoolingFanIcon;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ManualPress;
	
	int ReverseFan;
	
	int CursurIndex = 1;
	
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
		 TAG = "CoolingFanManualFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_coolingfan_manual, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL;
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		
		return mRoot;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CursurDisplay(CursurIndex);
		Log.d(TAG,"CursurIndex : " + Integer.toString(CursurIndex));
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_coolingfan_manual_low_ok);
		
		textViewCoolingFanManual = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_coolingfan_manual);
		
		imgViewCoolingFanIcon = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_coolingfan_manual);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		ManualPress = false;
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		textViewCoolingFanManual.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCoolingFanManual();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		CheckCoolingFanManualButton();
		ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ReverseFanDisplay(ReverseFan);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		
	//	CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO);
	//	CAN1Comm.TxCANToMCU(61);
	}
	public void ClickCoolingFanManual(){
		
	}
	/////////////////////////////////////////////////////////////////////
	public void CheckCoolingFanManualButton(){
		if(textViewCoolingFanManual.isPressed() == true){
//			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(1);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = true;
		}
		
		if(ManualPress == true && textViewCoolingFanManual.isPressed() == false){
//			CAN1Comm.Set_CoolingFanReverseMode_182_PGN61184_61(CAN1CommManager.DATA_STATE_REVERSEFAN_AUTO);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = false;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void setCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ReverseFanDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_COOLINGFAN_OFF:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_off);

			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_FORWARD:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_off);

			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_REVERSE:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_on);

			break;
		default:
			break;
		}
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
			ParentActivity._MenuBaseFragment._CoolingFanFragment.ClickLeft();
			break;
		default:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
			ParentActivity._MenuBaseFragment._CoolingFanFragment.ClickRight();
			break;		
		default:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
			ClickOK();
			break;
		case 4:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			ParentActivity._MenuBaseFragment._CoolingFanFragment.setCursurIndex(3);
			ParentActivity._MenuBaseFragment._CoolingFanFragment.CursurDisplay(3);
			break;
		default:
			break;
		}
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
			ParentActivity._MenuBaseFragment._CoolingFanFragment.ClickEnter();
			break;
		case 4:
			ClickOK();
			break;
		default:

			break;
		}
	}
	public void CursurDisplay(int Index){
		try {
			imgbtnOK.setPressed(false);
			switch (CursurIndex) {
				case 4:
					imgbtnOK.setPressed(true);
					break;
				default:
					break;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException CursurDisplay");
		}
		
	}
	/////////////////////////////////////////////////////////////////////
}
