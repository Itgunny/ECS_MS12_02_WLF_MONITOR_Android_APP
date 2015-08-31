package taeha.wheelloader.fseries_monitor.menu;

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
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class PasswordFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	protected static final int MAX_DATA_LENGTH = 21;
	protected static final int MAX_DATA_PW_LENGTH = 21;
	protected static final int SEED_LENGTH = 7;
	protected static final int MAX_ERR_CNT = 5;
	

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	protected TextView textViewNum1;
	protected TextView textViewNum2;
	protected TextView textViewNum3;
	protected TextView textViewNum4;
	protected TextView textViewNum5;
	protected TextView textViewNum6;
	protected TextView textViewNum7;
	protected TextView textViewNum8;
	protected TextView textViewNum9;
	protected TextView textViewNum0;
	
	protected TextView textViewPassword;
	protected TextView textViewTitle;
	
	protected ImageButton imgbtnBack;
	protected ImageButton imgbtnEnter;
	
	protected CheckBox	checkDisplay;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	protected byte[] NumDataBuf;
	protected byte[] SeedBuf;
	protected byte[] Password;
	protected byte[] tempNumDataBuf;	// ++, --, 150323 bwk
	protected int DataBufIndex;
	protected static int RequestCount = 0;
	protected static int ErrCount = 0;
	
	protected int HCEAntiTheftCommand;
	
	public int TitleIndex = 2;
	
	protected int MAX_INPUT_LENGTH = 10;
	
	// Timer
	protected Timer mPasswordCheckTimer = null;
	protected Timer mTimeOutTimer = null;
	protected Timer mIndicatorTitleTimer = null;
	protected Timer mSeedCheckTimer = null;
	
	protected Handler HandleCursurDisplay;
	protected int CursurIndex;
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
		 TAG = "PasswordFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_password, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		CursurDisplay(CursurIndex);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		Log.d(TAG,"onDestroyView");
		super.onDestroyView();
		CancelIndicatorTitleTimer();
		CancelPasswordCheckTimer();
		CancelSeedCheckTimer();
		CancelTimeOutTimer();

	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_0);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_text_title);
		textViewPassword = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_password_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_password_num_enter);

		checkDisplay = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_password_display);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		NumDataBuf = new byte[MAX_DATA_LENGTH];
		Password = new byte[MAX_DATA_PW_LENGTH];
		SeedBuf = new byte[SEED_LENGTH];
		tempNumDataBuf = new byte[MAX_DATA_LENGTH];	// ++, --, 150323 bwk
		for(int i = 0; i <MAX_DATA_LENGTH; i++){
			NumDataBuf[i] = (byte)0xFF;
		}
		
		for(int i = 0; i < MAX_DATA_PW_LENGTH; i++){
			Password[i] = (byte)0xFF;
		}
		
		for(int i = 0; i < SEED_LENGTH; i++){
			SeedBuf[i] = (byte)0xFF;
		}
		
		// ++, 150323 bwk
		for(int i = 0; i <MAX_DATA_LENGTH; i++){
			tempNumDataBuf[i] = (byte)0xFF;
		}
		// --, 150323 bwk
		DataBufIndex = 0;
	//	RequestCount = 0;
		
		if(ErrCount > MAX_ERR_CNT){
			SetTextIndicatorTitle(13);
		}
		
		HCEAntiTheftCommand = CAN1CommManager.DATA_STATE_PASSWORD_IDENTIFICATION_REQUEST;
		CursurIndex = 1;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum1();
			}
		});
		textViewNum2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum2();
			}
		});
		textViewNum3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum3();
			}
		});
		textViewNum4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum4();
			}
		});
		textViewNum5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum5();	
			}
		});
		textViewNum6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum6();
			}
		});
		textViewNum7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum7();
			}
		});
		textViewNum8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum8();
			}
		});
		textViewNum9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum9();
			}
		});
		textViewNum0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickNum0();
			}
		});
		imgbtnEnter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickEnterButton();
			}
		});
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickBack();
			}
		});
		imgbtnBack.setOnLongClickListener(new View.OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				LongClickBack();
				return true;
			}
		});
		checkDisplay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				PasswordIndicatorDisplay();
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
	public void ClickNum1(){
		CursurIndex = 1;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x31;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
		
	}
	public void ClickNum2(){
		
		CursurIndex = 2;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x32;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
	
	}

	public void ClickNum3(){
		
		CursurIndex = 3;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x33;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
	
	}

	public void ClickNum4(){
		
		CursurIndex = 4;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x34;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
	
	}

	public void ClickNum5(){
		
		CursurIndex = 5;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x35;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
		
	}

	public void ClickNum6(){
		
		CursurIndex = 6;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x36;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
		
	}

	public void ClickNum7(){
		
		CursurIndex = 7;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x37;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
		
	}

	public void ClickNum8(){
		
		CursurIndex = 8;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x38;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
	
	}

	public void ClickNum9(){
		
		CursurIndex = 9;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x39;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
		
	}

	public void ClickNum0(){
		
		CursurIndex = 11;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex < MAX_INPUT_LENGTH){
			NumDataBuf[DataBufIndex] = 0x30;
			DataBufIndex++;
		}
		PasswordIndicatorDisplay();
	
	}

	public void ClickBack(){
		
		CursurIndex = 10;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(DataBufIndex > 0){
			DataBufIndex--;
			NumDataBuf[DataBufIndex] = (byte) 0xFF;
		}
		PasswordIndicatorDisplay();
	
	}
	public void LongClickBack(){
		for(int i = 0; i <MAX_DATA_LENGTH; i++){
			NumDataBuf[i] = (byte)0xFF;
		}
		DataBufIndex = 0;
		PasswordIndicatorDisplay();
	}

	public void ClickEnterButton(){
		
		CursurIndex = 12;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		EnterClick();
	
	}


	/////////////////////////////////////////////////////////////////////
	public void EnterClick(){
		if(ErrCount >= MAX_ERR_CNT){
			return;
		}
		if(DataBufIndex < 5)
		{
			SetTextIndicatorTitle(4);
			PasswordIndicatorDisplay();
			
			StartIndicatorTitleTimer(1000);
		}
		else
		{
			
			SetTextIndicatorTitle(5);
			CAN1Comm.Set_MessageType_PGN61184_21(21);
			CAN1Comm.TxCANToMCU(21);
			
			StartSeedCheckTimer(1,100);
			StartTimeOutTimer(3000);
		}
		
		
	}

	public byte[] MakeEncryptedPassword(byte[] Password, byte[]Seed){
		byte[] EncryptedPassword;
		EncryptedPassword = new byte[MAX_DATA_PW_LENGTH];
		
		EncryptedPassword[0] = (byte) (Password[3]^Seed[1]);
		EncryptedPassword[1] = (byte) (Password[8]^Seed[6]);
		EncryptedPassword[2] = (byte) (Password[4]^Seed[3]);
		EncryptedPassword[3] = (byte) (Password[0]^Seed[2]);
		EncryptedPassword[4] = (byte) (Password[9]^Seed[5]);
		EncryptedPassword[5] = (byte) (Password[5]^Seed[0]);
		EncryptedPassword[6] = (byte) (Password[7]^Seed[4]);
		EncryptedPassword[7] = (byte) (Password[2]^Seed[3]);
		EncryptedPassword[8] = (byte) (Password[1]^Seed[6]);
		EncryptedPassword[9] = (byte) (Password[6]^Seed[0]);
		
		return EncryptedPassword;
	}	
	
	public void SetTextIndicatorTitle(final int nText){
		ParentActivity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (nText) {
				case 0:
					textViewTitle.setText("");
					break;
				case 1:
					textViewTitle.setText(ParentActivity.getResources().getString(string.User_Password));
					break;
				case 2:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Service_Password));
					break;
				case 3:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Incorrect_Password)
							 + " (" + Integer.toString(ErrCount) + "/" + Integer.toString(MAX_ERR_CNT) + ")");
					break;
				case 4:
					textViewTitle.setText(ParentActivity.getResources().getString(string._5_10_digits));
					break;
				case 5:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Searching));
					break;
				case 6:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Password_Changed));
					break;
				case 7:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Re_entering_Password));
					break;
				case 8:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Current_Password));
					break;
				case 9:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Current_Password));
					break;
				case 10:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Change_Password));
					break;
				case 11:
					textViewTitle.setText(ParentActivity.getResources().getString(string.New_Password));
					break;
				case 12:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Re_entering_New_Password));
					break;
				case 13:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Access_Denied));
					break;
				/*
				case 14:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Input_new_phone_number));
					break;
				*/
				case 15:
					textViewTitle.setText(ParentActivity.getResources().getString(string._5_12_digits));
					break;
				case 16:
					textViewTitle.setText(ParentActivity.getResources().getString(string.No_Changes_executed));
					break;

					
				case 17:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Serial_No));
					break;
				case 18:
					textViewTitle.setText(ParentActivity.getResources().getString(string.Re_entering_Serial_No));
					break;
					
				default:
					break;
				}
			}
			
		});
	}
	public void SetTitleIndex(int Index){
		TitleIndex = Index;
	}
	public void PasswordIndicatorDisplay(){			// Password number Ç¥½Ã
		ParentActivity.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String IndicatorText = "";

				if(DataBufIndex > 0)
				{
					for(int i = 0; i < DataBufIndex; i++){
						if(checkDisplay.isChecked() == true)
						{
							if(NumDataBuf[i] == 0x23){
								IndicatorText += "#";
							}
							else{
								IndicatorText += Integer.toString(NumDataBuf[i]-0x30);
							}
						}
						else
							IndicatorText += "*";
							
						
					}
				}
				textViewPassword.setText(IndicatorText);
			}
			
		});
		
		
	}
	public int CheckSeed(){
		return CAN1Comm.Get_RecvSeedFlag_PGN61184_22();
	}
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
			SetTextIndicatorTitle(2);

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
	/////////////////////////////////////////////////////////////////////

	public void StartPasswordCheckTimer(int Delay, int Interval){
		CancelPasswordCheckTimer();
		mPasswordCheckTimer = new Timer();
		mPasswordCheckTimer.schedule(new PasswordCheckTimerClass(),Delay,Interval);	
		
	}
	
	public void CancelPasswordCheckTimer(){
		if(mPasswordCheckTimer != null){
			mPasswordCheckTimer.cancel();
			mPasswordCheckTimer.purge();
			mPasswordCheckTimer = null;
		}
	}
	
	public class PasswordCheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(CAN1Comm.Get_RecvPasswordResultFlag_PGN61184_24() == 1){
				CheckPassword();
			}
				
		}
		
	}
	
	
	public void StartSeedCheckTimer(int Delay, int Interval){
		CancelSeedCheckTimer();
		mSeedCheckTimer = new Timer();
		mSeedCheckTimer.schedule(new SeedCheckTimerClass(),Delay,Interval);	
		
	}
	
	public void CancelSeedCheckTimer(){
		if(mSeedCheckTimer != null){
			mSeedCheckTimer.cancel();
			mSeedCheckTimer.purge();
			mSeedCheckTimer = null;
		}
	}
	
	public class SeedCheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(CheckSeed() == 1)
			{
				Log.d(TAG,"GET SEED");
				SeedBuf = CAN1Comm.Get_HCEAntiTheftRandomNumber_1632_PGN61184_22();
				Password = MakeEncryptedPassword(NumDataBuf, SeedBuf);
				CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(HCEAntiTheftCommand);
				CAN1Comm.Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(Password);
				CAN1Comm.Set_ESLMode_820_PGN61184_23(3);
				CAN1Comm.Set_ESLInterval_825_PGN61184_23(0xFF);
				CAN1Comm.TxCANToMCU(23);

				for(int i = 0; i <SEED_LENGTH; i++){
					//Log.d(TAG,"SeedBuf[" + Integer.toString(i) + "] : " + Integer.toString(SeedBuf[i]));
					SeedBuf[i] = (byte)0xFF;
				}
				
				for(int i = 0; i <MAX_DATA_LENGTH; i++){
					//Log.d(TAG,"NumDataBuf[" + Integer.toString(i) + "] : " + Integer.toString(NumDataBuf[i]));	
					tempNumDataBuf[i] = NumDataBuf[i];		// ++, --, 150323 bwk	
					NumDataBuf[i] = (byte)0xFF;
				}

				
				CancelSeedCheckTimer();
				StartPasswordCheckTimer(1,100);
			}

		}
		
	}
	

	public void StartTimeOutTimer(int Delay){
		CancelTimeOutTimer();
		mTimeOutTimer = new Timer();
		mTimeOutTimer.schedule(new TimeOutTimerClass(),Delay);	
	}
	
	public void CancelTimeOutTimer(){
		if(mTimeOutTimer != null){
			mTimeOutTimer.cancel();
			mTimeOutTimer.purge();
			mTimeOutTimer = null;
		}
	}
	
	public class TimeOutTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ErrCount++;
			SetTextIndicatorTitle(3);
			StartIndicatorTitleTimer(1000);
			CancelSeedCheckTimer();
			CancelPasswordCheckTimer();
		}
		
	}
	
	public void StartIndicatorTitleTimer(int Delay){
		CancelIndicatorTitleTimer();
		mIndicatorTitleTimer = new Timer();
		mIndicatorTitleTimer.schedule(new IndicatorTitleTimerClass(),Delay);	
	}
	
	public void CancelIndicatorTitleTimer(){
		if(mIndicatorTitleTimer != null){
			mIndicatorTitleTimer.cancel();
			mIndicatorTitleTimer.purge();
			mIndicatorTitleTimer = null;
		}
		
	}
	
	public class IndicatorTitleTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d(TAG,"mIndicatorTitleTimerTask");	
			SetTextIndicatorTitle(TitleIndex);
			DataBufIndex = 0;
			PasswordIndicatorDisplay();
			CancelIndicatorTitleTimer();
			
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void setTitleText(String str){
		textViewTitle.setText(str);
	}
	/////////////////////////////////////////////////////////////////////
	abstract public void showServicePasswordNextScreen();
	abstract public void showUserPasswordNextScreen();
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 12;
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
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 12:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.ClickBack();
	}
	public void ClickEnter(){
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
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
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

		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
