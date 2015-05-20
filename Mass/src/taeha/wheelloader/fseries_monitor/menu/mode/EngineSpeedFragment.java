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
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;
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
import android.widget.TextView;

public class EngineSpeedFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnPlus;
	ImageButton imgbtnMinus;
	
	TextView	textViewRPM;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineRPM;
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
		 TAG = "EngineSpeedFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_enginespeed, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_SPEED;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Engine_Speed));
		
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_enginespeed_low_ok);
		imgbtnPlus = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_enginespeed_plus);
		imgbtnMinus = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_enginespeed_minus);
		
		textViewRPM = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_enginespeed_data);

	
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CursurIndex = 1;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				
			}
		});
		imgbtnPlus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPlus();
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		imgbtnMinus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMinus();
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		EngineRPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		EngineRPMDisplay(EngineRPM);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ENGINETM_ENGINESETTING_TOP);
	}
	public void ClickPlus(){
		CAN1Comm.Set_RequestEngineLowIdleSpeed_PGN61184_109(CAN1CommManager.DATA_STATE_ENGINERPM_UP);
		CAN1Comm.TxCANToMCU(109);
		CAN1Comm.Set_RequestEngineLowIdleSpeed_PGN61184_109(3); 
	}
	public void ClickMinus(){
		CAN1Comm.Set_RequestEngineLowIdleSpeed_PGN61184_109(CAN1CommManager.DATA_STATE_ENGINERPM_DOWN);
		CAN1Comm.TxCANToMCU(109);
		CAN1Comm.Set_RequestEngineLowIdleSpeed_PGN61184_109(3); 
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
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
		case 1:
		case 2:
			CursurIndex++;
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
		ClickOK();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickMinus();
			break;
		case 2:
			ClickPlus();
			break;
		case 3:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnMinus.setPressed(true);
			imgbtnPlus.setPressed(false);
			imgbtnOK.setPressed(false);
			break;
		case 2:
			imgbtnMinus.setPressed(false);
			imgbtnPlus.setPressed(true);
			imgbtnOK.setPressed(false);
			break;
		case 3:
			imgbtnMinus.setPressed(false);
			imgbtnPlus.setPressed(false);
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void EngineRPMDisplay(int Data){
		if(Data > 8031)	// Operational Range : 0 to 8,031
			Data = 0;
		textViewRPM.setText(Integer.toString(Data));
	}
	/////////////////////////////////////////////////////////////////////
	
}
