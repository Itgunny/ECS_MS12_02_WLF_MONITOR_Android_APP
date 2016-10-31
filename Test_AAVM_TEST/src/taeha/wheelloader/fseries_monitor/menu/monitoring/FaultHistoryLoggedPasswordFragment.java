package taeha.wheelloader.fseries_monitor.menu.monitoring;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class FaultHistoryLoggedPasswordFragment extends PasswordFragment{

	ImageButton imgbtnCancel;
	TextFitTextView	textViewCancel;
	int SendDTCIndex;		// ++, --, 150329 bwk
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
		SetTextIndicatorTitle(2);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_PW;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Logged_Fault), 264);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		CursurDisplay(CursurIndex);
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
	// ++, 150329 bwk
	@Override
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		SendDTCIndex = Home.REQ_ERR_EHCU_LOGGED;	
	}
	// --, 150329 bwk
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
	// ++, 150329 bwk
	//////////////////////////////////////////////////////
	@Override
	protected void GetDataFromNative() {
		if(SendDTCIndex < Home.REQ_ERR_END)
		{
			ReqestErrorCode();
		}
	}
	//FAULT CODE//////////////////////////////////////////
	public void RequestErrorCode(int Err, int Req, int SeqNo){
		CAN1Comm.Set_DTCInformationRequest_1515_PGN61184_11(Req);
		CAN1Comm.Set_DTCType_1510_PGN61184_11(Err);
		CAN1Comm.Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(SeqNo);
		CAN1Comm.TxCANToMCU(11);
	}
	public void ReqestErrorCode(){
		switch (SendDTCIndex) {
		case Home.REQ_ERR_MACHINE_LOGGED:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = Home.REQ_ERR_END;
			SetThreadSleepTime(200);
			break;
		case Home.REQ_ERR_ENGINE_LOGGED:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = Home.REQ_ERR_MACHINE_LOGGED;
			SetThreadSleepTime(200);

			break;
		case Home.REQ_ERR_TM_LOGGED:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = Home.REQ_ERR_ENGINE_LOGGED;
			SetThreadSleepTime(200);

			break;
		case Home.REQ_ERR_EHCU_LOGGED:
			RequestErrorCode(SendDTCIndex,1,1);
			SendDTCIndex = Home.REQ_ERR_TM_LOGGED;
			SetThreadSleepTime(200);
			break;
		default:
			break;
		}
	}
	// --, 150329 bwk	
	/////////////////////////////////////////////////////
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
		case 1:			// UserPassword	OK		// ++, --, 150216 bwk 에러 카운트안되는 문제로 case 2 위에에서 여기로 이동
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
	/////////////////////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		super.ClickLeft();
	}
	public void ClickRight(){	
		super.ClickRight();
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		super.ClickEnter();
	}
	public void CursurDisplay(int Index){
		super.CursurDisplay(Index);
	}
	/////////////////////////////////////////////////////////////////////////////////////
	public String getString(String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			Log.d(TAG, "Android");
			return str;
		}else {
			Log.d(TAG, "Excel");
			return ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex);	
		}
	}
}
