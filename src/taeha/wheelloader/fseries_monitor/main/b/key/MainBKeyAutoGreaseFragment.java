package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBKeyAutoGreaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewOK;
	TextFitTextView textViewCancel;
	
	TextFitTextView textViewTitle;
	TextFitTextView textViewNotTitle;
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int AutoGrease;
	int SelectAutoGrease;
	int OldAutoGrease;
	int CursurIndex;
	Handler HandleCursurDisplay;
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
		TAG = "MainBKeyAutoGreaseFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_autogrease, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.StartBackHomeTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_AUTOGREASE;
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_autogrease_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_autogrease_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));

		
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_autogrease_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Do_you_want_to_turn_on_Auto_Grease), 187));
		textViewNotTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_autogrease_notavailable_title);
		textViewNotTitle.setText(getString(ParentActivity.getResources().getString(R.string.Auto_Grease_is_NOT_equipped), 190));
		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_autogrease_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_autogrease_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		AutoGrease = CAN1Comm.Get_AutoGreaseOperationStatus_3449_PGN65527();
		SelectAutoGrease = AutoGrease;
		OldAutoGrease = -1;
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		textViewCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AutoGrease = CAN1Comm.Get_AutoGreaseOperationStatus_3449_PGN65527();
//		Log.d(TAG,"AutoGrease="+AutoGrease);
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		KeyBGDisplay(AutoGrease);
	}
	/////////////////////////////////////////////////////////////////////	
	public void KeyBGDisplay(int Data){
		if(OldAutoGrease != Data)
		{
			OldAutoGrease = Data;
			switch (Data){
			case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
				ParentActivity.StartBackHomeTimer();
				layoutAvailable.setVisibility(View.INVISIBLE);
				layoutNotAvailable.setVisibility(View.VISIBLE);
				break;
			default:
				layoutAvailable.setVisibility(View.VISIBLE);
				layoutNotAvailable.setVisibility(View.INVISIBLE);
				break;
			}
		}
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void ClickHardKey(){
		
	}
	
	public void ClickCancel(){
		CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(CAN1CommManager.DATA_STATE_OFF);
		//CAN1Comm.TxCANToMCU(247);
		//CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(3);
		ParentActivity.StartAutoGreaseTimer();
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOK(){
		CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(CAN1CommManager.DATA_STATE_ON);
		//CAN1Comm.TxCANToMCU(247);
		//CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(3);
		ParentActivity.StartAutoGreaseTimer();
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
		ParentActivity.StartBackHomeTimer();
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
		ParentActivity.StartBackHomeTimer();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickOK();
			break;
		case 2:
			ClickCancel();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		textViewOK.setPressed(false);
		textViewCancel.setPressed(false);
		switch (CursurIndex) {
		case 1:
			textViewOK.setPressed(true);
			break;
		case 2:
			textViewCancel.setPressed(true);
			break;
		default:
			break;
		}
	}

	
}