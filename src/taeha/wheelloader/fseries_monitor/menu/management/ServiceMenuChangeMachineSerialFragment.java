package taeha.wheelloader.fseries_monitor.menu.management;

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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class ServiceMenuChangeMachineSerialFragment extends PasswordFragment{
	
	ImageButton imgbtnCancel;
	TextFitTextView textViewCancel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "ServiceMenuChangeMachineSerialFragment";
		Log.d(TAG, "onCreateView");		
		
		mRoot = inflater.inflate(R.layout.menu_body_mode_engineautoshutdown_pw, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		if(ParentActivity.MachineSerialNumber == 0xffffff){
			setTitleText(getString(ParentActivity.getResources().getString(string.Serial_No), 83));
		}else{
			setTitleText(getString(ParentActivity.getResources().getString(string.Serial_No), 83)+" "+Integer.toString(ParentActivity.MachineSerialNumber));
		}
		
		InitialMachineSerialNumber();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_MACHINESERIALNUMBER_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Machine_Serial_Number), 82);
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		CursurDisplay(CursurIndex);
		
		MAX_INPUT_LENGTH = 6;
		
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
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_text_title);
		textViewPassword = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_engineautoshutdown_pw_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_engineautoshutdown_pw_num_enter);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_engineautoshutdown_pw_low_cancel);
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_mode_engineautoshutdown_pw_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));

		checkDisplay = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_mode_engineautoshutdown_pw_display);
	}
	
	@Override
	protected void InitButtonListener() {
		super.InitButtonListener();
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickESC();
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
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		Log.d(TAG,"showUserPasswordNextScreen");
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
	}
	////////////////////////////////////////////////////////////////////////////////////////
	public void InitialMachineSerialNumber(){
		ParentActivity.tempMachineSerialNumber = 0xffffff;
		checkDisplay.setVisibility(View.GONE);
	}
	public int GetMachineSerialNumber(){
		int number = 0;
		int nibble = 1;
		if(DataBufIndex > 0)
		{
			for(int i = 0; i < DataBufIndex; i++){
				number += (NumDataBuf[DataBufIndex-1-i]-0x30) * nibble;
				nibble *= 10;
			}
		}
		return number;
	}
	@Override
	public void PasswordIndicatorDisplay(){			// Password number Ç¥½Ã
		ParentActivity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String IndicatorText = "";

				if(DataBufIndex > 0)
				{
					for(int i = 0; i < DataBufIndex; i++){
						IndicatorText += Integer.toString(NumDataBuf[i]-0x30);
					}
				}
				textViewPassword.setText(IndicatorText);
			}
		});
	}
	@Override
	public void EnterClick(){
		if(DataBufIndex < 1)
		{
			PasswordIndicatorDisplay();
			
			SetTitleIndex(17);
			StartIndicatorTitleTimer(500);
		}
		else
		{
			if(ParentActivity.tempMachineSerialNumber == 0xffffff)
			{
				ParentActivity.tempMachineSerialNumber = GetMachineSerialNumber();
				SetTextIndicatorTitle(18);
				PasswordIndicatorDisplay();
				
				SetTitleIndex(18);
				StartIndicatorTitleTimer(500);
			}
			else if(GetMachineSerialNumber() != ParentActivity.tempMachineSerialNumber)
			{
				ParentActivity.tempMachineSerialNumber = 0xffffff;
				PasswordIndicatorDisplay();
				
				setTitleText(getString(ParentActivity.getResources().getString(string.Serial_No), 83)+" "+Integer.toString(ParentActivity.MachineSerialNumber));
				StartIndicatorTitleTimer(500);
			}
			else 
			{
				ParentActivity.MachineSerialNumber = GetMachineSerialNumber();
				ParentActivity.SaveMachineSerialNumber();
				showServicePasswordNextScreen();
			}

		}		
	}
	@Override
	public void ClickLeft() {
		// TODO Auto-generated method stub
		switch (CursurIndex) {
		case 1:
			CursurIndex = 13;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
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
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;

		default:
			break;
		}
		
	}

	@Override
	public void ClickRight() {
		// TODO Auto-generated method stub
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 13:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}

	@Override
	public void ClickEnter() {
		// TODO Auto-generated method stub
		switch (CursurIndex) {
		case 1:
			ClickNum1();
			break;
		case 2:
			ClickNum2();
			break;
		case 3:
			ClickNum3();
			break;
		case 4:
			ClickNum4();
			break;
		case 5:
			ClickNum5();
			break;
		case 6:
			ClickNum6();
			break;
		case 7:
			ClickNum7();
			break;
		case 8:
			ClickNum8();
			break;
		case 9:
			ClickNum9();
			break;
		case 10:
			ClickBack();
			break;
		case 11:
			ClickNum0();
			break;
		case 12:
			ClickEnterButton();
			break;
		case 13:
			ClickESC();
			break;
		default:
			break;
		}
	}

	@Override
	public void CursurDisplay(int Index) {
		// TODO Auto-generated method stub
		textViewNum1.setPressed(false);
		textViewNum2.setPressed(false);
		textViewNum3.setPressed(false);
		textViewNum4.setPressed(false);
		textViewNum5.setPressed(false);
		textViewNum6.setPressed(false);
		textViewNum7.setPressed(false);
		textViewNum8.setPressed(false);
		textViewNum9.setPressed(false);
		textViewNum0.setPressed(false);
		imgbtnBack.setPressed(false);
		imgbtnEnter.setPressed(false);
		imgbtnCancel.setPressed(false);

		switch (Index) {
		case 1:
			textViewNum1.setPressed(true);
			break;
		case 2:
			textViewNum2.setPressed(true);
			break;
		case 3:
			textViewNum3.setPressed(true);
			break;
		case 4:
			textViewNum4.setPressed(true);
			break;
		case 5:
			textViewNum5.setPressed(true);
			break;
		case 6:
			textViewNum6.setPressed(true);
			break;
		case 7:
			textViewNum7.setPressed(true);
			break;
		case 8:
			textViewNum8.setPressed(true);
			break;
		case 9:
			textViewNum9.setPressed(true);
			break;
		case 10:
			imgbtnBack.setPressed(true);
			break;
		case 11:
			textViewNum0.setPressed(true);
			break;
		case 12:
			imgbtnEnter.setPressed(true);
			break;
		case 13:
			imgbtnCancel.setPressed(true);	
			break;
		default:
			break;
		}
	}

	@Override
	public void ClickESC(){
		showServicePasswordNextScreen();
	}
}
