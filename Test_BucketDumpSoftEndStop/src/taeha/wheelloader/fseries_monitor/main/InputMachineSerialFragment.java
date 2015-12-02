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

public class InputMachineSerialFragment extends PasswordFragment{
	Handler HandleMainScreen;
	
	ImageButton imgbtnCancel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "InputMachineSerialFragment";
		Log.d(TAG, "onCreateView");		
		mRoot = inflater.inflate(R.layout.main_input_machineserial, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		SetTextIndicatorTitle(17);
		
		HandleMainScreen = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if(ParentActivity.AnimationRunningFlag == true)
					return;
				else
					ParentActivity.StartAnimationRunningTimer();

//				ParentActivity.SetMachineSerialNumber();
			}
		};
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		CursurDisplay(CursurIndex);
		
		MAX_INPUT_LENGTH = 6;
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_CHECK_MACHINE_SERIAL;
		return mRoot;
	}
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_main_input_machineserial_num_0);
		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_main_input_machineserial_text_title);
		textViewPassword = (TextFitTextView)mRoot.findViewById(R.id.textView_main_input_machineserial_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_main_input_machineserial_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_main_input_machineserial_num_enter);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_main_input_machineserial_low_cancel);

		checkDisplay = (CheckBox)mRoot.findViewById(R.id.checkBox_main_input_machineserial_display);
	}
	@Override
	protected void InitButtonListener() {
		super.InitButtonListener();
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickESC();
			}
		});
	}
	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		HandleMainScreen.sendMessage(HandleMainScreen.obtainMessage(0));
	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		Log.d(TAG,"showUserPasswordNextScreen");
		HandleMainScreen.sendMessage(HandleMainScreen.obtainMessage(0));
	}
	////////////////////////////////////////////////////////////////////////////////////////
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
//			if(ParentActivity.tempMachineSerialNumber == 0xffffff)
//			{
//				ParentActivity.tempMachineSerialNumber = GetMachineSerialNumber();
//				SetTextIndicatorTitle(18);
//				PasswordIndicatorDisplay();
//				
//				SetTitleIndex(18);
//				StartIndicatorTitleTimer(500);
//			}
//			else if(GetMachineSerialNumber() != ParentActivity.tempMachineSerialNumber)
//			{
//				ParentActivity.tempMachineSerialNumber = 0xffffff;
//				PasswordIndicatorDisplay();
//				
//				SetTitleIndex(17);
//				StartIndicatorTitleTimer(500);
//			}
//			else 
//			{
//				ParentActivity.MachineSerialNumber = GetMachineSerialNumber();
//				ParentActivity.SaveMachineSerialNumber();
//				HandleMainScreen.sendMessage(HandleMainScreen.obtainMessage(0));
//			}
		}		
	}
	@Override
	public void ClickESC(){
		HandleMainScreen.sendMessage(HandleMainScreen.obtainMessage(0));
	}	
	
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
		case CAN1CommManager.ESC:
			ClickESC();
		default:
			break;
		}
	}
	
}
