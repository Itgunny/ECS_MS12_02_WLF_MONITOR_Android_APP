package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class MachineSecurityPasswordChangeFragment extends PasswordFragment{

	private int PasswordChangeStatus;

	private byte[] tmpNumDataBuf;
	private int tmpDataBufIndex;
	
	Handler HandleNextScreen;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "MachineSecurityPasswordChangeFragment";
		Log.d(TAG, "onCreateView");		
		
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Change_Password));
		SetTitleIndex(1);
		SetTextIndicatorTitle(1);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		HandleNextScreen = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ParentActivity._MenuBaseFragment.showBodyMachineSecurityList();
			}
		};
		return mRoot;
	}
	@Override
	public void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		tmpNumDataBuf = new byte[MAX_DATA_LENGTH];
		for (int i = 0; i < MAX_DATA_LENGTH; i++) {
			tmpNumDataBuf[i] = (byte)0xFF;
		}
		tmpDataBufIndex = 0;
		HCEAntiTheftCommand = CAN1CommManager.DATA_STATE_LOGIN;
		PasswordChangeStatus = CAN1CommManager.DATA_STATE_PW_CHANGE_OLD;
	}
	
	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		HandleNextScreen.sendMessage(HandleNextScreen.obtainMessage(0));
	}
	
	@Override
	public void CheckPassword() {
		// TODO Auto-generated method stub
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
		case 2: // ServicePassword OK
			
			PasswordChangeStatus = CAN1CommManager.DATA_STATE_PW_CHANGE_NEW;
			
			CancelPasswordCheckTimer();
			CancelTimeOutTimer();
			
			SetTitleIndex(11);
			SetTextIndicatorTitle(11);
			
		
			
			Log.d(TAG,"Password OK : " + Integer.toString(Result));
			break;
		case 15:

		default:
			break;
		}

	}
	
	public void CheckNewPassword(){
		int Result;
		int RecvFlag;
		RecvFlag = CAN1Comm.Get_RecvPasswordChangeResultFlag_PGN61184_25();
		Result = CAN1Comm.Get_PasswordChangeResult_958_PGN61184_25();
		Log.d(TAG, "Check Password : " + Integer.toString(PasswordChangeStatus));
		if (PasswordChangeStatus == CAN1CommManager.DATA_STATE_PW_CHANGE_CONFIRM) {
			Log.d(TAG, "Check Password RecvFlag : " + Integer.toString(RecvFlag));
			if (RecvFlag == 1) {
				if(Result == CAN1CommManager.DATA_STATE_PW_CHANGE_CHANGE_OK){
					SetTextIndicatorTitle(6);
					CancelPasswordCheckTimer();
					CancelTimeOutTimer();
					InitValuables();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					showUserPasswordNextScreen();
					Log.d(TAG, "Check Password OK");
				}else{
					SetTextIndicatorTitle(16);
					CancelPasswordCheckTimer();
					CancelTimeOutTimer();
					InitValuables();
					
					Log.d(TAG, "Check Password Not OK");
				}
				
				
			} else if (RecvFlag == 0) {
				SetTextIndicatorTitle(11);
				// InitValuables();
			}
		}
	}

	@Override
	public void EnterClick() {
		// TODO Auto-generated method stub
		// super.EnterClick();
		Log.d(TAG,"PasswordChangeStatus : " + Integer.toString(PasswordChangeStatus));
		if (DataBufIndex < 5) {
			SetTextIndicatorTitle(4);
			PasswordIndicatorDisplay();

			StartIndicatorTitleTimer(1000);
		} else {
			if (PasswordChangeStatus == CAN1CommManager.DATA_STATE_PW_CHANGE_OLD){
				HCEAntiTheftCommand = CAN1CommManager.DATA_STATE_LOGIN;
				
				
				CAN1Comm.Set_MessageType_PGN61184_21(21);
				CAN1Comm.TxCANToMCU(21);
				
				DataBufIndex = 0;
				PasswordIndicatorDisplay();
				
				SetTextIndicatorTitle(5);
				
				StartSeedCheckTimer(1,100);
				StartTimeOutTimer(3000);
				RequestCount = 0;
				
			}
			if (PasswordChangeStatus == CAN1CommManager.DATA_STATE_PW_CHANGE_NEW) {
				SetTextIndicatorTitle(12);
				tmpDataBufIndex = DataBufIndex;
				for (int i = 0; i < tmpDataBufIndex; i++) {
					tmpNumDataBuf[i] = NumDataBuf[i];
					Log.d(TAG, "tmp : " + Integer.toString(tmpNumDataBuf[i]));
				}

				DataBufIndex = 0;
				PasswordIndicatorDisplay();
				PasswordChangeStatus = CAN1CommManager.DATA_STATE_PW_CHANGE_CONFIRM;
			} else if (PasswordChangeStatus == CAN1CommManager.DATA_STATE_PW_CHANGE_CONFIRM) {
				HCEAntiTheftCommand = CAN1CommManager.DATA_STATE_PASSWORD_CHANGE_COMMAND;
				boolean State = true;
				for (int i = 0; i < DataBufIndex; i++) {
					Log.d(TAG, "confirm : " + Integer.toString(tmpNumDataBuf[i]));
					if (tmpNumDataBuf[i] != NumDataBuf[i]) {
						State = false;
						break;
					}
				}
				if (DataBufIndex != tmpDataBufIndex) {
					State = false;
				}

				if (State == true) {
					Log.d(TAG, "confirm ok");

					SeedBuf = CAN1Comm.Get_HCEAntiTheftRandomNumber_1632_PGN61184_22();
					Password = MakeEncryptedPassword(NumDataBuf, SeedBuf);
					CAN1Comm.Set_HCEAntiTheftCommand_1633_PGN61184_23(HCEAntiTheftCommand);
					CAN1Comm.Set_HCEAntiTheftPasswordRepresentation_1634_PGN61184_23(Password);
					CAN1Comm.TxCANToMCU(23);
					
					for (int i = 0; i < MAX_DATA_LENGTH; i++) {
						Log.d(TAG,"NumDataBuf : " + Integer.toString(NumDataBuf[i]));
					}
					
					StartPasswordCheckTimer(1,100);
					StartTimeOutTimer(3000);
					RequestCount = 0;

				} else {
					if (RequestCount >= 5) {
						SetTextIndicatorTitle(13);
					} else {
						SetTitleIndex(1);
						SetTextIndicatorTitle(1);
						DataBufIndex = 0;
						PasswordIndicatorDisplay();
					}
						
					
					PasswordChangeStatus = CAN1CommManager.DATA_STATE_PW_CHANGE_OLD;
				}

			}
		}
	}

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
			if(PasswordChangeStatus == CAN1CommManager.DATA_STATE_PW_CHANGE_OLD)
				CheckPassword();
			else if(PasswordChangeStatus == CAN1CommManager.DATA_STATE_PW_CHANGE_CONFIRM)
				CheckNewPassword();
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
				CAN1Comm.TxCANToMCU(23);
				
				
				CancelSeedCheckTimer();
				StartPasswordCheckTimer(1,100);
				
				for (int i = 0; i < MAX_DATA_LENGTH; i++) {
					NumDataBuf[i] = (byte)0xFF;
				}
				
				DataBufIndex = 0;
			}

		}
	}

}


