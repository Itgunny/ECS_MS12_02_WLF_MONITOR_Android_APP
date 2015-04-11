package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
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

public class MainBKeyQuickCouplerFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewLock;
	TextView textViewUnlock;
	
	ImageButton imgbtnOK;
	
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Quickcoupler;
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
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewLock = (TextView)mRoot.findViewById(R.id.textView_key_main_b_quickcoupler_lock);
		textViewUnlock = (TextView)mRoot.findViewById(R.id.textView_key_main_b_quickcoupler_unlock);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_quickcoupler_low_ok);

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_quickcoupler_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_quickcoupler_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		Quickcoupler = CAN1Comm.Get_QuickCouplerOperationStatus_3448_PGN65527();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewLock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickLock();
			}
		});
		textViewUnlock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickUnlock();
			}
		});
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		
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
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			layoutAvailable.setVisibility(View.INVISIBLE);
			layoutNotAvailable.setVisibility(View.VISIBLE);
			break;
		default:
			layoutAvailable.setVisibility(View.VISIBLE);
			layoutNotAvailable.setVisibility(View.INVISIBLE);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickLock(){
		ParentActivity.OldScreenIndex = ParentActivity.ScreenIndex;
		ParentActivity.showQuickCouplerPopupLocking2();
	}
	public void ClickUnlock(){
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;	// ++, --, 150407 bwk
		ParentActivity.showQuickCouplerPopupUnlocking2();
	}
	public void ClickOK(){
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	
}