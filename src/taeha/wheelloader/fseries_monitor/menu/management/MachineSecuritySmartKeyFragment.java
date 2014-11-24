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
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.content.SharedPreferences;
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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MachineSecuritySmartKeyFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

	TextView textViewRegistration;
	TextView textViewDelete;
	
	RadioButton radioDisable;
	RadioButton radioEnable;
	
	TextView textViewTagNum;
	TextView textViewDetail;
	
	RelativeLayout layoutContents;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SmartKeyUse;
	
	int TagCount;
	int Result;
	int ButtonIndex;
	
	private Timer mManagementDisplayTimer = null;
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
		 TAG = "MachineSecuritySmartKeyFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_smartkey, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Smart_Key));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_smartkey_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_smartkey_low_cancel);
	
		textViewRegistration = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_smartkey_tag_registration);
		textViewDelete = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_smartkey_tag_delete);
		
		radioDisable = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_smartkey_use_diable);
		radioEnable = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_smartkey_use_enable);
		
		textViewTagNum = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_smartkey_tag_register_num);
		textViewDetail = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_smartkey_tag_detail);
		
		layoutContents = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_smartkey_tag_contents);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		SmartKeyUse = ParentActivity.SmartKeyUse;
		SmartKeyUseDisplay(SmartKeyUse);
		
		TagCount = 0;
		Result = 0;
		ButtonIndex = CAN1CommManager.DATA_INDEX_TAG_REGISTRATION;
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
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		textViewRegistration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickRegistration();
			}
		});
		textViewDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDelete();
			}
		});
		radioDisable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDisable();
			}
		});
		radioEnable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEnable();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		TagCount = CAN1Comm.Get_SmkRegTagCount();
		Result = CAN1Comm.Get_SmkMsgResult();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		TagCountDisplay(TagCount);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();

		ParentActivity.SmartKeyUse = SmartKeyUse;
		SavePref();
		
		if(SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON){
			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_SMK, CAN1CommManager.DATA_INDEX_TAG_USE_SAVE,1);
			CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_SETTING);
			CAN1Comm.Set_ESLMode_820_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_ENABLE_CONTINUOUS);
			CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
			CAN1Comm.TxCANToMCU(23);
			CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
			CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
		}else if(SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF){
//			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_SMK, CAN1CommManager.DATA_INDEX_TAG_USE_SAVE,0);
//			CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_SETTING);
//			CAN1Comm.Set_ESLMode_820_PGN61184_23(CAN1CommManager.DATA_STATE_ESL_MODE_DISABLE);
//			CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
//			CAN1Comm.TxCANToMCU(23);
//			CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(15);
//			CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
		}
		
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showMachineSecurityListAnimation();
	}
	public void ClickRegistration(){
		ButtonIndex = CAN1CommManager.DATA_INDEX_TAG_REGISTRATION;
		CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_SMK, CAN1CommManager.DATA_INDEX_TAG_REGISTRATION);
		textViewDetail.setText(ParentActivity.getResources().getString(string.Registering));
		StartManagementDisplayTimer();
	}
	public void ClickDelete(){
		ButtonIndex = CAN1CommManager.DATA_INDEX_TAG_ELIMINATION;
		CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_SMK, CAN1CommManager.DATA_INDEX_TAG_ELIMINATION);
		textViewDetail.setText(ParentActivity.getResources().getString(string.Deleting));
		StartManagementDisplayTimer();
	}
	public void ClickDisable(){
		SmartKeyUse = CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF;
		SmartKeyUseDisplay(SmartKeyUse);
	}
	public void ClickEnable(){
		SmartKeyUse = CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON;
		SmartKeyUseDisplay(SmartKeyUse);
	}
	/////////////////////////////////////////////////////////////////////
	public void SmartKeyUseDisplay(int _data){
		switch (_data) {
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF:
			radioDisable.setChecked(true);
			radioEnable.setChecked(false);
			layoutContents.setVisibility(View.INVISIBLE);
			break;
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON:
			radioDisable.setChecked(false);
			radioEnable.setChecked(true);
			layoutContents.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	public void TagCountDisplay(int _data){
		String str;
		str = Integer.toString(_data);
		textViewTagNum.setText(str);
	}
	public void ManagementDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_1ST_TAG_REG_SUCCESS:
			textViewDetail.setText(ParentActivity.getResources().getString(string._1st_user_tag_registered));
			break;
		case CAN1CommManager.DATA_STATE_2ND_TAG_REG_SUCCESS:
			textViewDetail.setText(ParentActivity.getResources().getString(string._2nd_user_tag_registered));
			break;

		case CAN1CommManager.DATA_STATE_TAG_NO_REALIZE:
			
			break;
		case CAN1CommManager.DATA_STATE_FAIL:
			if(ButtonIndex == CAN1CommManager.DATA_INDEX_TAG_REGISTRATION){
				textViewDetail.setText(ParentActivity.getResources().getString(string.Registration_failed));
			}else if(ButtonIndex == CAN1CommManager.DATA_INDEX_TAG_ELIMINATION){
				textViewDetail.setText(ParentActivity.getResources().getString(string.Delete_failed));
			}
			break;
			
		case CAN1CommManager.DATA_STATE_TAG_ALREADY_REG:
			textViewDetail.setText(ParentActivity.getResources().getString(string.Tag_already_registered));
			break;
		case CAN1CommManager.DATA_STATE_TAG_ELIMINATION_SUCESS:
			textViewDetail.setText(ParentActivity.getResources().getString(string.All_user_tags_deleted));
			break;
		default:
			textViewDetail.setText("");
			break;
		}
		Log.d(TAG,"Data : " + Integer.toString(Data));
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("SmartKeyUse", SmartKeyUse);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	public class ManagementDisplayTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ManagementDisplay(Result);
				}
				
			});
			
		}
		
	}
	
	public void StartManagementDisplayTimer(){
		CancelManagementDisplayTimer();
		mManagementDisplayTimer = new Timer();
		mManagementDisplayTimer.schedule(new ManagementDisplayTimerClass(),5000);	
	}
	
	public void CancelManagementDisplayTimer(){
		if(mManagementDisplayTimer != null){
			mManagementDisplayTimer.cancel();
			mManagementDisplayTimer.purge();
			mManagementDisplayTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	
	
}
