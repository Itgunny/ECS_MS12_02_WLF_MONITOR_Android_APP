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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CameraSettingFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int MIN_CAM	= 1;
	private static final int MAX_CAM	= 4;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton	imgbtnCancel;
	ImageButton imgbtnActiveCameraMinus;
	ImageButton imgbtnActiveCameraPlus;
	
	TextView 	textViewCAM1;
	TextView 	textViewCAM2;
	TextView 	textViewCAM3;
	TextView 	textViewCAM4;
	
	TextView 	textViewCAM1txt;
	TextView 	textViewCAM2txt;
	TextView 	textViewCAM3txt;
	TextView 	textViewCAM4txt;
	
	TextView 	textViewCameraNum;
	
	CheckBox	checkReverseMode;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int ActiveCameraNum;
	int CameraOrder1;
	int CameraOrder2;
	int CameraOrder3;
	int CameraOrder4;
	int CameraReverseMode;
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
		 TAG = "CameraSettingFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_camerasetting, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_CAMERASETTING_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Camera_Setting));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_camerasetting_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_camerasetting_low_cancel);

		imgbtnActiveCameraMinus = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_camerasetting_cameranumber_minus);
		imgbtnActiveCameraPlus = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_camerasetting_cameranumber_plus);
	
		textViewCAM1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_btn1);
		textViewCAM2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_btn2);
		textViewCAM3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_btn3);
		textViewCAM4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_btn4);
		
		textViewCAM1txt = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_txt1);
		textViewCAM2txt = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_txt2);
		textViewCAM3txt = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_txt3);
		textViewCAM4txt = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_dislayorder_txt4);
		
		textViewCameraNum = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_camerasetting_cameranumber_data);
		
		checkReverseMode = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_mode_camerasetting_gearmode);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();

		ActiveCameraNum = ParentActivity.ActiveCameraNum;
		CameraOrder1 = ParentActivity.CameraOrder1;
		CameraOrder2 = ParentActivity.CameraOrder2;
		CameraOrder3 = ParentActivity.CameraOrder3;
		CameraOrder4 = ParentActivity.CameraOrder4;
		CameraReverseMode = ParentActivity.CameraReverseMode;
		textViewCameraNum.setText(Integer.toString(ActiveCameraNum));
		CAMOrderDisplayOnOff(ActiveCameraNum);
		CAMDisplay(textViewCAM1,CameraOrder1);
		CAMDisplay(textViewCAM2,CameraOrder2);
		CAMDisplay(textViewCAM3,CameraOrder3);
		CAMDisplay(textViewCAM4,CameraOrder4);
		ReverseModeDisplay(CameraReverseMode);
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
		imgbtnActiveCameraMinus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMinus();
			}
		});
		imgbtnActiveCameraPlus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPlus();
			}
		});
		textViewCAM1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCAM1();
			}
		});
		textViewCAM2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCAM2();
			}
		});
		textViewCAM3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCAM3();
			}
		});
		textViewCAM4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCAM4();
			}
		});
		checkReverseMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickReverseMode();
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
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
		
		ParentActivity.ActiveCameraNum = ActiveCameraNum;
		ParentActivity.CameraOrder1 = CameraOrder1;
		ParentActivity.CameraOrder2 = CameraOrder2;
		ParentActivity.CameraOrder3 = CameraOrder3;
		ParentActivity.CameraOrder4 = CameraOrder4;
		ParentActivity.CameraReverseMode = CameraReverseMode; 
		
		SavePref();
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
	}
	public void ClickMinus(){
		if(ActiveCameraNum <= 1){
			ActiveCameraNum = 4;
		}else {
			ActiveCameraNum--;
		}
		textViewCameraNum.setText(Integer.toString(ActiveCameraNum));
		CAMOrderDisplayOnOff(ActiveCameraNum);
	}
	public void ClickPlus(){
		if(ActiveCameraNum >= 4){
			ActiveCameraNum = 1;
		}else {
			ActiveCameraNum++;
		}
		textViewCameraNum.setText(Integer.toString(ActiveCameraNum));
		CAMOrderDisplayOnOff(ActiveCameraNum);
	}
	
	public void ClickCAM1(){
		int TempCam;
		TempCam = CameraOrder1;
		if(CameraOrder1 >= 3)
			CameraOrder1 = 0;
		else
			CameraOrder1++;
		
//		if(CameraOrder1 == CameraOrder2)
//			CameraOrder2 = TempCam;
//		if(CameraOrder1 == CameraOrder3)
//			CameraOrder3 = TempCam;
//		if(CameraOrder1 == CameraOrder4)
//			CameraOrder4 = TempCam;

		CAMDisplay(textViewCAM1,CameraOrder1);
		CAMDisplay(textViewCAM2,CameraOrder2);
		CAMDisplay(textViewCAM3,CameraOrder3);
		CAMDisplay(textViewCAM4,CameraOrder4);
	}
	public void ClickCAM2(){
		int TempCam;
		TempCam = CameraOrder2;
		if(CameraOrder2 >= 3)
			CameraOrder2 = 0;
		else
			CameraOrder2++;
		
//		if(CameraOrder2 == CameraOrder1)
//			CameraOrder1 = TempCam;
//		if(CameraOrder2 == CameraOrder3)
//			CameraOrder3 = TempCam;
//		if(CameraOrder2 == CameraOrder4)
//			CameraOrder4 = TempCam;
		
		CAMDisplay(textViewCAM1,CameraOrder1);
		CAMDisplay(textViewCAM2,CameraOrder2);
		CAMDisplay(textViewCAM3,CameraOrder3);
		CAMDisplay(textViewCAM4,CameraOrder4);
	}
	public void ClickCAM3(){
		int TempCam;
		TempCam = CameraOrder3;
		if(CameraOrder3 >= 3)
			CameraOrder3 = 0;
		else
			CameraOrder3++;
		
//		if(CameraOrder3 == CameraOrder1)
//			CameraOrder1 = TempCam;
//		if(CameraOrder3 == CameraOrder2)
//			CameraOrder2 = TempCam;
//		if(CameraOrder3 == CameraOrder4)
//			CameraOrder4 = TempCam;
		
		CAMDisplay(textViewCAM1,CameraOrder1);
		CAMDisplay(textViewCAM2,CameraOrder2);
		CAMDisplay(textViewCAM3,CameraOrder3);
		CAMDisplay(textViewCAM4,CameraOrder4);
	}
	public void ClickCAM4(){
		int TempCam;
		TempCam = CameraOrder4;
		if(CameraOrder4 >= 3)
			CameraOrder4 = 0;
		else
			CameraOrder4++;
		
//		if(CameraOrder4 == CameraOrder1)
//			CameraOrder1 = TempCam;
//		if(CameraOrder4 == CameraOrder2)
//			CameraOrder2 = TempCam;
//		if(CameraOrder4 == CameraOrder3)
//			CameraOrder3 = TempCam;
		
		CAMDisplay(textViewCAM1,CameraOrder1);
		CAMDisplay(textViewCAM2,CameraOrder2);
		CAMDisplay(textViewCAM3,CameraOrder3);
		CAMDisplay(textViewCAM4,CameraOrder4);
	}
	public void ClickReverseMode(){
		if(checkReverseMode.isChecked() == true)
			CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_ON;
		else
			CameraReverseMode = CAN1CommManager.DATA_STATE_CAMERA_REVERSE_OFF;
	}
	/////////////////////////////////////////////////////////////////////
	public void CAMDisplay(TextView textviewcam, int _data){
		switch (_data) {
		case 0:
			textviewcam.setText(ParentActivity.getResources().getString(string.CAM_1));
			break;
		case 1:
			textviewcam.setText(ParentActivity.getResources().getString(string.CAM_2));
			break;
		case 2:
			textviewcam.setText(ParentActivity.getResources().getString(string.CAM_3));
			break;
		case 3:
			textviewcam.setText(ParentActivity.getResources().getString(string.CAM_4));
			break;
		default:
			break;
		}
	}
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
	public void CAMOrder1DisplayOnOff(boolean flag){
		if(flag == true){
			textViewCAM1.setVisibility(View.VISIBLE);
			textViewCAM1txt.setVisibility(View.VISIBLE);
		}else{
			textViewCAM1.setVisibility(View.INVISIBLE);
			textViewCAM1txt.setVisibility(View.INVISIBLE);
		}
	}
	public void CAMOrder2DisplayOnOff(boolean flag){
		if(flag == true){
			textViewCAM2.setVisibility(View.VISIBLE);
			textViewCAM2txt.setVisibility(View.VISIBLE);
		}else{
			textViewCAM2.setVisibility(View.INVISIBLE);
			textViewCAM2txt.setVisibility(View.INVISIBLE);
		}
	}
	public void CAMOrder3DisplayOnOff(boolean flag){
		if(flag == true){
			textViewCAM3.setVisibility(View.VISIBLE);
			textViewCAM3txt.setVisibility(View.VISIBLE);
		}else{
			textViewCAM3.setVisibility(View.INVISIBLE);
			textViewCAM3txt.setVisibility(View.INVISIBLE);
		}
	}
	public void CAMOrder4DisplayOnOff(boolean flag){
		if(flag == true){
			textViewCAM4.setVisibility(View.VISIBLE);
			textViewCAM4txt.setVisibility(View.VISIBLE);
		}else{
			textViewCAM4.setVisibility(View.INVISIBLE);
			textViewCAM4txt.setVisibility(View.INVISIBLE);
		}
	}
	public void CAMOrderDisplayOnOff(int _activecam){
		switch (_activecam) {
		case 1:
			CAMOrder1DisplayOnOff(true);
			CAMOrder2DisplayOnOff(false);
			CAMOrder3DisplayOnOff(false);
			CAMOrder4DisplayOnOff(false);
			break;
		case 2:
			CAMOrder1DisplayOnOff(true);
			CAMOrder2DisplayOnOff(true);
			CAMOrder3DisplayOnOff(false);
			CAMOrder4DisplayOnOff(false);
			break;
		case 3:
			CAMOrder1DisplayOnOff(true);
			CAMOrder2DisplayOnOff(true);
			CAMOrder3DisplayOnOff(true);
			CAMOrder4DisplayOnOff(false);
			break;
		case 4:
			CAMOrder1DisplayOnOff(true);
			CAMOrder2DisplayOnOff(true);
			CAMOrder3DisplayOnOff(true);
			CAMOrder4DisplayOnOff(true);
			break;

		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("ActiveCameraNum", ActiveCameraNum);
		edit.putInt("CameraOrder1", CameraOrder1);
		edit.putInt("CameraOrder2", CameraOrder2);
		edit.putInt("CameraOrder3", CameraOrder3);
		edit.putInt("CameraOrder4", CameraOrder4);
		edit.putInt("CameraReverseMode", CameraReverseMode);
		edit.commit();
		Log.d(TAG,"SavePref");
		
	}
	/////////////////////////////////////////////////////////////////////
}
