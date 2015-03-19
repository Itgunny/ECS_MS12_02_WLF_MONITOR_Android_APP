package taeha.wheelloader.fseries_monitor.main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class ESLPasswordFragment extends PasswordFragment{
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
		
		HandleESLPassword = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(ParentActivity.AnimationRunningFlag == true)
					return;
				else
					ParentActivity.StartAnimationRunningTimer();
				// ++, 150309 bwk
				//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
				ParentActivity.showMainScreen();
				// --, 150309 bwk
			
			}
		};
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		
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
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_text_title);
		textViewPassword = (TextView)mRoot.findViewById(R.id.textView_main_esl_password_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_main_esl_password_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_main_esl_password_num_enter);
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

}
