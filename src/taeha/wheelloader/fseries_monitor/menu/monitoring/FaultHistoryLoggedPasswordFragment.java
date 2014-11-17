package taeha.wheelloader.fseries_monitor.menu.monitoring;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class FaultHistoryLoggedPasswordFragment extends PasswordFragment{

	ImageButton imgbtnCancel;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "FaultHistoryLoggedPasswordFragment";
		Log.d(TAG, "onCreateView");		
		mRoot = inflater.inflate(R.layout.menu_body_mode_engineautoshutdown_pw, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		SetTextIndicatorTitle(1);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Logged_Fault));
		return mRoot;
	}
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_num_0);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_text_title);
		textViewPassword = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_engineautoshutdown_pw_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_engineautoshutdown_pw_num_enter);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_engineautoshutdown_pw_low_cancel);
	}
	@Override
	protected void InitButtonListener() {
		super.InitButtonListener();
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
	}
	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyLoggedFault();

	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		DataBufIndex = 0;
		PasswordIndicatorDisplay();
		
		SetTextIndicatorTitle(3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CancelPasswordCheckTimer();
		SetTextIndicatorTitle(2);
	}
	@Override
	public void CheckPassword(){
		int Result;
		Result = CAN1Comm.Get_PasswordCertificationResult_956_PGN61184_24();
		Log.d(TAG,"CheckPassword Result : " + Integer.toString(Result));	
		//if(Result == 1)	// UserPassword
		//	Result = 0;
		
		switch (Result) {
		case 0:			// Not OK
		case 13:		// TimeOut
			ErrCount++;
			DataBufIndex = 0;
			PasswordIndicatorDisplay();
			if(ErrCount >= MAX_ERR_CNT)
			{
				CancelPasswordCheckTimer();
				CancelTimeOutTimer();
				SetTextIndicatorTitle(13);
				return;
			}
			
			SetTextIndicatorTitle(3);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CancelPasswordCheckTimer();
			CancelTimeOutTimer();
			SetTextIndicatorTitle(1);

			break;
		case 1:	// UserPassword	OK
		case 2:	// Service Password OK
		
			CancelPasswordCheckTimer();
			CancelTimeOutTimer();
			
			if(Result == 2)
			{
				showServicePasswordNextScreen();
			}
			else if(Result == 1)
			{
				showUserPasswordNextScreen();
			}
			Log.d(TAG,"Password OK : " + Integer.toString(Result));
			break;
		
		case 5:	// MLC Password OK
		case 10:	// Master Password OK
		case 15:
		default:
			break;
		}
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showFaultHistoryAnimation();
	}
}
