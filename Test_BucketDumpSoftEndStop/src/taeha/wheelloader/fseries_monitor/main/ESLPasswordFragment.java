package taeha.wheelloader.fseries_monitor.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class ESLPasswordFragment extends PasswordFragment{
	RelativeLayout LayoutSmkIcon;	// ++, --, 150325 bwk
	Handler HandleESLPassword;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "ESLPasswordFragment";
		Log.d(TAG, "onCreateView");		
		mRoot = inflater.inflate(R.layout.main_esl_password, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		SetTextIndicatorTitle(1);
		showSmartkeyIcon();	// ++, --, 150326 bwk
		
		HandleESLPassword = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(ParentActivity.AnimationRunningFlag == true)
					return;
				else
					ParentActivity.StartAnimationRunningTimer();
				//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
				//ParentActivity.showMainScreen();
				ParentActivity.showInputMachineSerial();
			
			}
		};
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		CursurDisplay(CursurIndex);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_ESL_PASSWORD;
		return mRoot;
	}
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_num_0);
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_main_esl_password_text_title);
		textViewPassword = (TextFitTextView)mRoot.findViewById(R.id.textView_main_esl_password_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_main_esl_password_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_main_esl_password_num_enter);
		
		// ++, 150325 bwk
		LayoutSmkIcon = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_main_esl_password_icon);
		// --, 150325 bwk
		checkDisplay = (CheckBox)mRoot.findViewById(R.id.checkBox_main_esl_password_display);
	}
	@Override
	protected void InitButtonListener() {
		super.InitButtonListener();
	}
	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		HandleESLPassword.sendMessage(HandleESLPassword.obtainMessage(0));
	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		Log.d(TAG,"showUserPasswordNextScreen");
		HandleESLPassword.sendMessage(HandleESLPassword.obtainMessage(0));
	}
	////////////////////////////////////////////////////////////////////////////////////////
	// ++, 150325 bwk
	public void showSmartkeyIcon(){
		if(ParentActivity.SmartKeyUse == CAN1CommManager.DATA_STATE_SMARTKEY_USE_ON)
			LayoutSmkIcon.setVisibility(View.VISIBLE);
		else
			LayoutSmkIcon.setVisibility(View.GONE);
	}
	// --, 150325 bwk
	////////////////////////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(int key){
		switch (key) {
		case CAN1CommManager.LEFT:
			ClickLeft();
			break;
		case CAN1CommManager.RIGHT:
			ClickRight();
			break;
		case CAN1CommManager.ENTER:
			ClickEnter();
			break;
		default:
			break;
		}
	}

}
