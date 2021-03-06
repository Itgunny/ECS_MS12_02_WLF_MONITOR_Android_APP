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
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBKeyQuickCouplerFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewLock;
	TextFitTextView textViewUnlock;
	
	//ImageButton imgbtnOK;
	//TextFitTextView	textViewOK;
	
	TextFitTextView	textViewNotTitle;
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Quickcoupler;
	int CursurIndex;
	int OldQuickcoupler;
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
		TAG = "MainBKeyQuickCouplerFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_quickcoupler, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
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
		textViewLock = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_quickcoupler_lock);
		textViewLock.setText(getString(ParentActivity.getResources().getString(R.string.Unlocking_Attachment), 167));
		textViewUnlock = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_quickcoupler_unlock);
		textViewUnlock.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
		
		//imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_quickcoupler_low_ok);
		//textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_quickcoupler_low_ok);
		//textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		textViewNotTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_quickcoupler_notavailable_title);
		textViewNotTitle.setText(getString(ParentActivity.getResources().getString(R.string.Quick_Coupler_is_NOT_equipped), 193));

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_quickcoupler_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_quickcoupler_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		Quickcoupler = CAN1Comm.Get_QuickCouplerOperationStatus_3448_PGN65527();
		OldQuickcoupler = -1;
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewLock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickUnlock();
			}
		});
		textViewUnlock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		/*imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});*/
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		Quickcoupler = CAN1Comm.Get_QuickCouplerOperationStatus_3448_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		KeyBGDisplay(Quickcoupler);
	}
	/////////////////////////////////////////////////////////////////////	
	public void KeyBGDisplay(int Data){
		if(OldQuickcoupler != Data)
		{
			OldQuickcoupler = Data;
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
	public void ClickLock(){
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		ParentActivity.showQuickCouplerPopupLocking2();
	}
	public void ClickUnlock(){
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;	// ++, --, 150407 bwk
		ParentActivity.showQuickCouplerPopupUnlocking2();
	}
	public void ClickOK(){
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
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickUnlock();
			break;
		case 2:
			ClickOK();
			break;
		//case 3:	
			//ClickOk();
			//break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		textViewLock.setPressed(false);
		textViewUnlock.setPressed(false);
		//imgbtnOK.setPressed(false);
		switch (CursurIndex) {
		case 1:
			textViewLock.setPressed(true);
			break;
		case 2:
			textViewUnlock.setPressed(true);
			break;
		//case 3:
			//imgbtnOK.setPressed(true);
			//break;
		default:
			break;
		}
	}
}