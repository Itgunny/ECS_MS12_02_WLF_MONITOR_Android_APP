package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class UserSwitchingLockingPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewSettingLocking;
	TextFitTextView textViewApply;
	
	RadioButton radioBtnOn;
	RadioButton radioBtnOff;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int LockingState;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public UserSwitchingLockingPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "UserSwitchingLockingPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_lockinguserswitching, null);
		this.addContentView(mRoot,  new LayoutParams(448,310));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_POPUP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		LockingState = ParentActivity.LockUserSwitching; 
		Log.d(TAG, "Init LockingState : " + ParentActivity.LockUserSwitching);
		UserSwitchingLockingDisplay(LockingState);
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_TOP;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewSettingLocking = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_lock_userswitching_setting_locking_state);
		textViewSettingLocking.setText(getString(ParentActivity.getResources().getString(string.Setting_Lock_List), 500));
		
		textViewApply = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_lock_userswitching_apply);
		textViewApply.setText(getString(ParentActivity.getResources().getString(string.Apply), 125));
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_lock_userswitching_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Lock_User_Setting), 499));
		
		radioBtnOn = (RadioButton)mRoot.findViewById(R.id.radioButton_popup_lock_userswitching_on);
		radioBtnOn.setText(getString(ParentActivity.getResources().getString(string.ON), 19));
		
		radioBtnOff = (RadioButton)mRoot.findViewById(R.id.radioButton_popup_lock_userswitching_off);
		radioBtnOff.setText(getString(ParentActivity.getResources().getString(string.OFF), 20));
		
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewSettingLocking.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				ClickLockingSetting();
				
			}
		});
		textViewApply.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				ClickApply();
			}
		});
		
		radioBtnOn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				ClickRadioBtnOn();
			}
		});
		
		radioBtnOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				ClickRadioBtnOff();
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
	///////////////////////////////////////////////////////////////////////////////
	public void ClickLockingSetting(){
		this.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_LOCKING;
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showLockingUserSwitching();
	}	
	public void ClickApply(){
		ParentActivity.LockUserSwitching = LockingState;
		ParentActivity.SavePref();
		this.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_TOP;
		
		Log.d(TAG, "Apply LockingState : " + ParentActivity.LockUserSwitching);

	}	
	public void ClickRadioBtnOn(){
		LockingState = Home.STATE_USERSWITCHING_LOCK;
		UserSwitchingLockingDisplay(LockingState);
		textViewSettingLocking.setClickable(true);
		textViewSettingLocking.setAlpha((float) 1);
	}
	
	public void ClickRadioBtnOff() {
		LockingState = Home.STATE_USERSWITCHING_UNLOCK;
		UserSwitchingLockingDisplay(LockingState);
		textViewSettingLocking.setClickable(false);
		textViewSettingLocking.setAlpha((float) 0.5);
		
	}
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
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
			if(LockingState == Home.STATE_USERSWITCHING_LOCK){
				CursurIndex--;
				CursurDisplay(CursurIndex);
			} else {
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}
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
			if(LockingState == Home.STATE_USERSWITCHING_LOCK){
				CursurIndex++;
				CursurDisplay(CursurIndex);
			} else {
				CursurIndex = 4;
				CursurDisplay(CursurIndex);
			}
			break;
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
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
		this.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_USERSWITCHING_TOP;
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickRadioBtnOn();
			break;
		case 2:
			ClickRadioBtnOff();
			break;
		case 3:
			ClickLockingSetting();
			break;
		case 4:
			ClickApply();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			radioBtnOn.setPressed(true);
			radioBtnOff.setPressed(false);
			textViewSettingLocking.setPressed(false);
			textViewApply.setPressed(false);
			break;
		case 2:
			radioBtnOn.setPressed(false);
			radioBtnOff.setPressed(true);
			textViewSettingLocking.setPressed(false);
			textViewApply.setPressed(false);
			break;
		case 3:
			radioBtnOn.setPressed(false);
			radioBtnOff.setPressed(false);
			textViewSettingLocking.setPressed(true);
			textViewApply.setPressed(false);
			break;
		case 4:
			radioBtnOn.setPressed(false);
			radioBtnOff.setPressed(false);
			textViewSettingLocking.setPressed(false);
			textViewApply.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void UserSwitchingLockingDisplay(int data){
		switch (data) {
		case Home.STATE_USERSWITCHING_LOCK:
			radioBtnOn.setChecked(true);
			radioBtnOff.setChecked(false);
			textViewSettingLocking.setClickable(true);
			textViewSettingLocking.setAlpha((float) 1);
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case Home.STATE_USERSWITCHING_UNLOCK:
			radioBtnOn.setChecked(false);
			radioBtnOff.setChecked(true);
			textViewSettingLocking.setClickable(false);
			textViewSettingLocking.setAlpha((float) 0.5);
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
}
