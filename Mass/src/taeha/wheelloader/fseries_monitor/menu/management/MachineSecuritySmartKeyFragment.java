package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ESLCheckFragment.SMKCheckTimerClass;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.content.SharedPreferences;
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
		 TAG = "MachineSecuritySmartKeyFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_smartkey, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurFirstDisplay(SmartKeyUse);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_SMARTKEY;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Smart_Key));
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
		SmartKeyUseDisplay(SmartKeyUse);
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
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		textViewRegistration.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickRegistration();
			}
		});
		textViewDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDelete();
			}
		});
		radioDisable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDisable();
			}
		});
		radioEnable.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
		ManagementDisplay(Result);
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
			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_SMK, CAN1CommManager.DATA_INDEX_TAG_USE_SAVE,0);			// ++, 150305 bwk 주석 풀음
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
//		Log.d(TAG,"Smarkt__Registration");
		ButtonIndex = CAN1CommManager.DATA_INDEX_TAG_REGISTRATION;
		CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_SMK, CAN1CommManager.DATA_INDEX_TAG_REGISTRATION);
		textViewDetail.setText(ParentActivity.getResources().getString(string.Registering));
		StartManagementDisplayTimer();
	}
	public void ClickDelete(){
//		Log.d(TAG,"Smarkt__Delete");
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
			//layoutContents.setVisibility(View.INVISIBLE);
			layoutContents.setAlpha((float)0.2);
			textViewRegistration.setClickable(false);
			textViewDelete.setClickable(false);
			break;
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON:
			radioDisable.setChecked(false);
			radioEnable.setChecked(true);
			layoutContents.setVisibility(View.VISIBLE);
			layoutContents.setAlpha((float)1);
			textViewRegistration.setClickable(true);
			textViewDelete.setClickable(true);
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
		if(CAN1Comm.Get_RecvSMK_Flag() == 1){
//			Log.d(TAG,"Smarkt__ManagementDisplay"+Data);
			switch (Data) {
			case CAN1CommManager.DATA_STATE_1ST_TAG_REG_SUCCESS:
				textViewDetail.setText(ParentActivity.getResources().getString(string._1st_user_tag_registered));
				CancelManagementDisplayTimer();
				break;
			case CAN1CommManager.DATA_STATE_2ND_TAG_REG_SUCCESS:
				textViewDetail.setText(ParentActivity.getResources().getString(string._2nd_user_tag_registered));
				CancelManagementDisplayTimer();
				break;

			case CAN1CommManager.DATA_STATE_TAG_NO_REALIZE:
				
				break;
			case CAN1CommManager.DATA_STATE_FAIL:
				if(ButtonIndex == CAN1CommManager.DATA_INDEX_TAG_REGISTRATION){
					textViewDetail.setText(ParentActivity.getResources().getString(string.Registration_failed));
					CancelManagementDisplayTimer();
				}else if(ButtonIndex == CAN1CommManager.DATA_INDEX_TAG_ELIMINATION){
					textViewDetail.setText(ParentActivity.getResources().getString(string.Delete_failed));
					CancelManagementDisplayTimer();
				}
				break;
				
			case CAN1CommManager.DATA_STATE_TAG_ALREADY_REG:
				textViewDetail.setText(ParentActivity.getResources().getString(string.Tag_already_registered));
				CancelManagementDisplayTimer();
				break;
			case CAN1CommManager.DATA_STATE_TAG_ELIMINATION_SUCESS:
				textViewDetail.setText(ParentActivity.getResources().getString(string.All_user_tags_deleted));
				CancelManagementDisplayTimer();
				break;
			case CAN1CommManager.DATA_STATE_3RD_TAG_REG_SUCCESS:
				textViewDetail.setText(ParentActivity.getResources().getString(string._3rd_user_tag_registered));
				CancelManagementDisplayTimer();
				break;
			case CAN1CommManager.DATA_STATE_4TH_TAG_REG_SUCCESS:
				textViewDetail.setText(ParentActivity.getResources().getString(string._4th_user_tag_registered));
				CancelManagementDisplayTimer();
				break;
			case CAN1CommManager.DATA_STATE_5TH_TAG_REG_SUCCESS:
				textViewDetail.setText(ParentActivity.getResources().getString(string._5th_user_tag_registered));
				CancelManagementDisplayTimer();
				break;
			default:
				textViewDetail.setText("");
				break;
			}
			Log.d(TAG,"Data : " + Integer.toString(Data));
			
		}
		
	}
	public void TimeoutDisplay(){
		textViewDetail.setText(ParentActivity.getResources().getString(string.Time_Out));
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
					TimeoutDisplay();
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
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			if(SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF){
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
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
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex = 1;
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
			if(SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF){
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}
			
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 1:
		case 2:
			ClickCancel();
			break;
		case 3:
		case 4:
		case 5:
		case 6:
			CursurFirstDisplay(SmartKeyUse);
			break;

		default:
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickDisable();
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			ClickEnable();
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			ClickRegistration();
			break;
		case 4:
			ClickDelete();
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
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_OFF:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		radioDisable.setPressed(false);
		radioEnable.setPressed(false);
		textViewRegistration.setPressed(false);
		textViewDelete.setPressed(false);

		switch (CursurIndex) {
			case 1:
				radioDisable.setPressed(true);
				break;
			case 2:
				radioEnable.setPressed(true);
				break;
			case 3:
				textViewRegistration.setPressed(true);
				break;
			case 4:
				textViewDelete.setPressed(true);
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
