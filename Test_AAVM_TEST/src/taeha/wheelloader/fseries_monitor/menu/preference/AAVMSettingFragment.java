package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AAVMSettingFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton	imgbtnCancel;
	TextFitTextView	textViewOK;
	TextFitTextView	textViewCancel;
	
	
	TextFitTextView	textViewReverseCameraAAVM;
	RadioButton radioUseAAVMOn;
	RadioButton radioUseAAVMOff;
	
	ImageButton frontButton;
	ImageButton	rearButton;
	ImageButton	leftButton;
	ImageButton rightButton;
	ImageButton ch4Button;
	ImageButton bird3dButton;
	ImageButton rear3dButton;
	ImageButton	bird2dButton;
	ImageButton left3dButton;
	ImageButton right3dButton;
	
	CheckBox	checkReverseMode;
	
	RelativeLayout layoutType;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int reverseGearUseAAVM;
	int CameraReverseMode;
	int CameraStatus = 4;
	Handler HandleCursurDisplay;
	int CursurIndex;
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
		 TAG = "AAVMSettingFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_aavmsetting, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_AAVMSETTING_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText("AAVM Setting");
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_aavmsetting_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_aavmsetting_low_cancel);

		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_aavmsetting_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_aavmsetting_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
				
		
		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){	
			checkReverseMode = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_preference_aavmsetting_gearmode);
			checkReverseMode.setText(getString(ParentActivity.getResources().getString(R.string.Gear_Linkage_Mode), 233));
			checkReverseMode.setPadding(50, 0, 0, 0);
		} else {
			checkReverseMode = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_preference_aavmsetting_gearmode);
			checkReverseMode.setText(getString(ParentActivity.getResources().getString(R.string.Gear_Linkage_Mode), 233));
		}
		
		textViewReverseCameraAAVM = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_aavmsetting_reversecamera_title);
		textViewReverseCameraAAVM.setText("후진기어시 AAVM 화면타입 설정");
		
		radioUseAAVMOn = (RadioButton)mRoot.findViewById(R.id.radioGroup_menu_body_preference_aavmsetting_reversecamera_on);
		radioUseAAVMOn.setText(getString(ParentActivity.getResources().getString(string.On), 19));
		ParentActivity.setMarqueeRadio(radioUseAAVMOn);
		radioUseAAVMOff = (RadioButton)mRoot.findViewById(R.id.radioGroup_menu_body_preference_aavmsetting_reversecamera_off);
		radioUseAAVMOff.setText(getString(ParentActivity.getResources().getString(string.Off), 20));
		ParentActivity.setMarqueeRadio(radioUseAAVMOff);
		
		
		frontButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_front);
		rearButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_rear);
		leftButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_left);
		rightButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_right);
		ch4Button = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_camera_4ch);
		bird3dButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_3d_bird);
		rear3dButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_3d_rear);
		bird2dButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_2d_bird);
		left3dButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_3d_left);
		right3dButton = (ImageButton)mRoot.findViewById(R.id.ImageView_menu_body_preference_aavm_3d_right);
		
		
		layoutType = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_aavmsetting_displaytype);
		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		CameraReverseMode = ParentActivity.CameraReverseMode;
		reverseGearUseAAVM = ParentActivity.ReverseGearUseAAVM;
		CameraStatus = ParentActivity.AAVM_Reverse_Camera_Status;
		ReverseModeDisplay(CameraReverseMode);
		UseAAVMDisplay(reverseGearUseAAVM);
		AAVMDisplayType(CameraStatus);
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 15;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 14;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		radioUseAAVMOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickRadioUseAAVMOn();
			}
		});
		radioUseAAVMOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickRadioUseAAVMOff();
			}
		});
		
		checkReverseMode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickReverseMode();
			}
		});
		frontButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "frontButton");
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_FRONT_VIEW_2D);
			}
		});
		rearButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "rearButton");
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_REAR_VIEW_2D);
			}
		});
		leftButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "leftButton");
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_LEFT_VIEW_2D);
			}
		});
		rightButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "rightButton");
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_RIGHT_VIEW_2D);
			}
		});
		ch4Button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "CH4Button");
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_4CHANNEL_MULTIVIEW_2D);
			}
		});
		bird3dButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "bird 3d Button");
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_OMNI_VIEW_3D);
			}
		});
		rear3dButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "rear 3d Button");
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_OMNI_REAR_VIEW_3D);
			}
		});
		bird2dButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "bird 2d Button");
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_BIRD_VIEW_2D);
			}
		});
		left3dButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "left 3d Button");
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_OMNI_LEFT_VIEW_3D);
			}
		});
		right3dButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "right 3d Button");
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAAVMDisplayType(Home.AAVM_OMNI_RIGHT_VIEW_3D);
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
		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
		
		ParentActivity.ReverseGearUseAAVM = reverseGearUseAAVM;
		ParentActivity.CameraReverseMode = CameraReverseMode; 
		ParentActivity.AAVM_Reverse_Camera_Status = CameraStatus;
		
		Log.d(TAG, "ParentActivity.ReverseGearUseAAVM" + ParentActivity.ReverseGearUseAAVM);
		
		SavePref();
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
	}
	
	public void ClickRadioUseAAVMOn(){
		reverseGearUseAAVM = CAN1CommManager.DATA_STATE_REVERSE_GEAR_USE_AAVM;
		UseAAVMDisplay(CAN1CommManager.DATA_STATE_REVERSE_GEAR_USE_AAVM);
		AAVMDisplayType(CameraStatus);
	}
	
	public void ClickRadioUseAAVMOff(){
		reverseGearUseAAVM = CAN1CommManager.DATA_STATE_REVERSE_GEAR_NOT_USE_AAVM;
		UseAAVMDisplay(CAN1CommManager.DATA_STATE_REVERSE_GEAR_NOT_USE_AAVM);
		CameraStatus = Home.AAVM_REAR_VIEW_2D;
		AAVMDisplayType(CameraStatus);
	}
	
	public void UseAAVMDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_REVERSE_GEAR_USE_AAVM:
			radioUseAAVMOn.setChecked(true);
			radioUseAAVMOff.setChecked(false);
			frontButton.setEnabled(true);
			rearButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);
			ch4Button.setEnabled(true);
			bird3dButton.setEnabled(true);
			rear3dButton.setEnabled(true);
			bird2dButton.setEnabled(true);
			left3dButton.setEnabled(true);
			right3dButton.setEnabled(true);
			layoutType.setAlpha(1);
			break;
		case CAN1CommManager.DATA_STATE_REVERSE_GEAR_NOT_USE_AAVM:
			radioUseAAVMOn.setChecked(false);
			radioUseAAVMOff.setChecked(true);
			frontButton.setEnabled(false);
			rearButton.setEnabled(false);
			leftButton.setEnabled(false);
			rightButton.setEnabled(false);
			ch4Button.setEnabled(false);
			bird3dButton.setEnabled(false);
			rear3dButton.setEnabled(false);
			bird2dButton.setEnabled(false);
			left3dButton.setEnabled(false);
			right3dButton.setEnabled(false);
			layoutType.setAlpha((float)0.5);
			break;
		default:
			break;
		}
	}
	public void ClickAAVMDisplayType(int Data){
		CameraStatus = Data;
		AAVMDisplayType(CameraStatus);

	}
	public void AAVMDisplayType(int Data){
		switch(Data){
		case Home.AAVM_OMNI_VIEW_3D: // 0
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(true);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_OMNI_REAR_VIEW_3D: // 1
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(true);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_BIRD_VIEW_2D: // 2
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(true);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_4CHANNEL_MULTIVIEW_2D: // 3
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(true);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_FRONT_VIEW_2D: // 4
			frontButton.setPressed(true);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_REAR_VIEW_2D: // 5
			frontButton.setPressed(false);
			rearButton.setPressed(true);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_LEFT_VIEW_2D: // 6
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(true);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_RIGHT_VIEW_2D: // 7
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(true);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_OMNI_LEFT_VIEW_3D: // 8
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(true);
			right3dButton.setPressed(false);
			break;
		case Home.AAVM_OMNI_RIGHT_VIEW_3D: // 9
			frontButton.setPressed(false);
			rearButton.setPressed(false);
			leftButton.setPressed(false);
			rightButton.setPressed(false);
			ch4Button.setPressed(false);
			bird3dButton.setPressed(false);
			rear3dButton.setPressed(false);
			bird2dButton.setPressed(false);
			left3dButton.setPressed(false);
			right3dButton.setPressed(true);
			break;
		}
		Log.d(TAG, "CameraStatus : " + CameraStatus);
	}
	public void ClickReverseMode(){
		if(checkReverseMode.isChecked() == true)
			CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON;
		else
			CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF;
	}
	/////////////////////////////////////////////////////////////////////
	public void ReverseModeDisplay(int _data){
		switch (_data) {
		case CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF:
			checkReverseMode.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON:
			checkReverseMode.setChecked(true);
			break;
		default:
			break;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("CameraReverseMode", CameraReverseMode);
		edit.putInt("ReverseGearUseAAVM", reverseGearUseAAVM);
		edit.putInt("AAVMReverseCameraStatus", CameraStatus);
		edit.commit();
		Log.d(TAG,"SavePref");
		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 13;
			ClickAAVMDisplayType(Home.AAVM_OMNI_RIGHT_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_FRONT_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_REAR_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_LEFT_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_RIGHT_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 9:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_4CHANNEL_MULTIVIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 10:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_OMNI_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 11:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_OMNI_REAR_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 12:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_BIRD_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 13:
			CursurIndex--;
			ClickAAVMDisplayType(Home.AAVM_OMNI_LEFT_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 14:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 15:
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
			CursurIndex = 14;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_REAR_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_LEFT_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_RIGHT_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 7:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_4CHANNEL_MULTIVIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 8:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_OMNI_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 9:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_OMNI_REAR_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 10:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_BIRD_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 11:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_OMNI_LEFT_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 12:
			CursurIndex++;
			ClickAAVMDisplayType(Home.AAVM_OMNI_RIGHT_VIEW_3D);
			CursurDisplay(CursurIndex);
			break;
		case 13:
			CursurIndex = 4;
			ClickAAVMDisplayType(Home.AAVM_FRONT_VIEW_2D);
			CursurDisplay(CursurIndex);
			break;
		case 14:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 15:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		switch(CursurIndex){
		case 1:
		case 2:
		case 3:
		case 14:
		case 15:
			ClickCancel();
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickRadioUseAAVMOn();
			switch(CameraStatus){
			case Home.AAVM_OMNI_VIEW_3D: // 0
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_OMNI_REAR_VIEW_3D: // 1
				CursurIndex = 10;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_BIRD_VIEW_2D: // 2
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_4CHANNEL_MULTIVIEW_2D: // 3
				CursurIndex = 8;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_FRONT_VIEW_2D: // 4
				CursurIndex = 4;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_REAR_VIEW_2D: // 5
				CursurIndex = 5;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_LEFT_VIEW_2D: // 6
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_RIGHT_VIEW_2D: // 7
				CursurIndex = 7;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_OMNI_LEFT_VIEW_3D: // 8
				CursurIndex = 12;
				CursurDisplay(CursurIndex);
				break;
			case Home.AAVM_OMNI_RIGHT_VIEW_3D: // 9
				CursurIndex = 13;
				CursurDisplay(CursurIndex);
				break;
			}
			break;
		case 2:
			ClickRadioUseAAVMOff();
			break;
		case 3:
			if(CameraReverseMode == CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON)
				CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF;
			else
				CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON;
			ReverseModeDisplay(CameraReverseMode);
			break;
		case 4:
			ClickAAVMDisplayType(Home.AAVM_FRONT_VIEW_2D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			ClickAAVMDisplayType(Home.AAVM_REAR_VIEW_2D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			ClickAAVMDisplayType(Home.AAVM_LEFT_VIEW_2D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 7:
			ClickAAVMDisplayType(Home.AAVM_RIGHT_VIEW_2D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 8:
			ClickAAVMDisplayType(Home.AAVM_4CHANNEL_MULTIVIEW_2D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 9:
			ClickAAVMDisplayType(Home.AAVM_OMNI_VIEW_3D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 10:
			ClickAAVMDisplayType(Home.AAVM_OMNI_REAR_VIEW_3D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			ClickAAVMDisplayType(Home.AAVM_BIRD_VIEW_2D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 12:
			ClickAAVMDisplayType(Home.AAVM_OMNI_LEFT_VIEW_3D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 13:
			ClickAAVMDisplayType(Home.AAVM_OMNI_RIGHT_VIEW_3D);
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 14:
			ClickCancel();
			break;
		case 15:
			ClickOK();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioUseAAVMOn.setPressed(false);
		radioUseAAVMOff.setPressed(false);
		checkReverseMode.setPressed(false);
		frontButton.setPressed(false);
		rearButton.setPressed(false);
		leftButton.setPressed(false);
		rightButton.setPressed(false);
		ch4Button.setPressed(false);
		bird3dButton.setPressed(false);
		rear3dButton.setPressed(false);
		bird2dButton.setPressed(false);
		left3dButton.setPressed(false);
		right3dButton.setPressed(false);
		imgbtnCancel.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (Index) {
		case 1:
			radioUseAAVMOn.setPressed(true);
			ClickAAVMDisplayType(CameraStatus);
			break;
		case 2:
			radioUseAAVMOff.setPressed(true);
			ClickAAVMDisplayType(CameraStatus);
			break;
		case 3:
			checkReverseMode.setPressed(true);
			ClickAAVMDisplayType(CameraStatus);
			break;
		case 4:
			frontButton.setPressed(true);
			break;
		case 5:
			rearButton.setPressed(true);	
			break;
		case 6:
			leftButton.setPressed(true);
			break;
		case 7:
			rightButton.setPressed(true);
			break;
		case 8:
			ch4Button.setPressed(true);
			break;
		case 9:
			bird3dButton.setPressed(true);
			break;
		case 10:
			rear3dButton.setPressed(true);
			break;
		case 11:
			bird2dButton.setPressed(true);
			break;
		case 12:
			left3dButton.setPressed(true);
			break;
		case 13:
			right3dButton.setPressed(true);
			break;
		case 14:
			imgbtnCancel.setPressed(true);
			ClickAAVMDisplayType(CameraStatus);
			break;
		case 15:
			imgbtnOK.setPressed(true);
			ClickAAVMDisplayType(CameraStatus);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public String getString(String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			return str;
		}else {
			return ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex);	
		}
	}
}
