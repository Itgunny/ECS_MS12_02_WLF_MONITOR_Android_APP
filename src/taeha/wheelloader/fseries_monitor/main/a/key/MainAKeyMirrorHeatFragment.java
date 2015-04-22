package taeha.wheelloader.fseries_monitor.main.a.key;

import android.os.Bundle;
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

public class MainAKeyMirrorHeatFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewOK;
	TextView textViewCancel;
	
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int MirrorHeat;
	int SelectMirrorHeat;
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
		TAG = "MainAKeyMirrorHeatFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_mirrorheat, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_MIRRORHEAT;

		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub	
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_key_main_a_mirrorheat_ok);
		textViewCancel = (TextView)mRoot.findViewById(R.id.textView_key_main_a_mirrorheat_cancel);

		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_mirrorheat_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_a_mirrorheat_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		MirrorHeat = CAN1Comm.Get_MirrorHeatOperationStatus_3450_PGN65527();
		SelectMirrorHeat = MirrorHeat;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		textViewCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		MirrorHeat = CAN1Comm.Get_MirrorHeatOperationStatus_3450_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		KeyBGDisplay(MirrorHeat);
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
	
	public void ClickHardKey(){
		
		
	}
	public void ClickCancel(){
		CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_OFF);
		//CAN1Comm.TxCANToMCU(247);
		//CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
		ParentActivity.StartMirrorHeatTimer();
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOK(){
		CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(CAN1CommManager.DATA_STATE_ON);
		//CAN1Comm.TxCANToMCU(247);
		//CAN1Comm.Set_MirrorHeatOperationStatus_3450_PGN65527(3);
		ParentActivity.StartMirrorHeatTimer();
		ParentActivity._MainABaseFragment.showKeytoDefaultScreenAnimation();
	}
	
	
}