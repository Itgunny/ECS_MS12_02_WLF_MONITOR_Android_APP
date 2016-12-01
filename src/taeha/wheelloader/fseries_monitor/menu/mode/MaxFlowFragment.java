package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
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

public class MaxFlowFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextFitTextView    textViewOK;
	ImageButton imgbtnPlus;
	ImageButton imgbtnMinus;
	
	TextFitTextView	textViewMaxFlow;
	
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int MaxFlowLevel;
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
		 TAG = "MaxFlowFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_maxflow, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_HYD_MAXFLOW;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Auxilliary_Attachment_Max_Flow_Level), 213);
		
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_maxflow_low_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_maxflow_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		imgbtnPlus = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_maxflow_plus);
		imgbtnMinus = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_maxflow_minus);
	
		
		textViewMaxFlow = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_maxflow_data);

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
		MaxFlowLevel = CAN1Comm.Get_AuxiliaryAttachmentMaxFlowLevel_2303_PGN65517();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		MaxFlowLevelDisplay(MaxFlowLevel);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickPlus(){
		CAN1Comm.Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(1);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(3);
	}
	public void ClickMinus(){
		CAN1Comm.Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(0);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_AuxiliaryAttachmentMaxFlowLevel_PGN61184_203(3);
	}
	/////////////////////////////////////////////////////////////////////
	public void MaxFlowLevelDisplay(int data){
		if(data > 15)
			data = 0;
		textViewMaxFlow.setText(Integer.toString(data));
	}
	/////////////////////////////////////////////////////////////////////
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
}
