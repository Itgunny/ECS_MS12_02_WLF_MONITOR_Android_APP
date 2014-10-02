package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBKeyAutoGreaseFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int AutoGrease;
	int SelectAutoGrease;
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

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_AUTOGREASE;
		
		AutoGreaseDisplay(AutoGrease);
		ClickHardKey();
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_autogrease_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_autogrease_on);
		
		radioOff.setClickable(false);
		radioOn.setClickable(false);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		AutoGrease = CAN1Comm.Get_AutoGreaseOperationStatus_3449_PGN65527();
		SelectAutoGrease = AutoGrease;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
//		radioOff.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		radioOn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AutoGrease = CAN1Comm.Get_AutoGreaseOperationStatus_3449_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		AutoGreaseDisplay(AutoGrease);
	}
	/////////////////////////////////////////////////////////////////////	
	public void AutoGreaseDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_OFF:
			radioOff.setChecked(true);
			radioOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ON:
			radioOff.setChecked(false);
			radioOn.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ClickHardKey(){
		
		switch (SelectAutoGrease) {
		case CAN1CommManager.DATA_STATE_OFF:
		default:
			SelectAutoGrease = CAN1CommManager.DATA_STATE_ON;
			CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(CAN1CommManager.DATA_STATE_ON);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(3);
			ParentActivity.StartAutoGreaseStopTimer();
			break;
		case CAN1CommManager.DATA_STATE_ON:
			SelectAutoGrease = CAN1CommManager.DATA_STATE_OFF;
			CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(CAN1CommManager.DATA_STATE_OFF);
			CAN1Comm.TxCANToMCU(247);
			CAN1Comm.Set_AutoGreaseOperationStatus_3449_PGN65527(3);
			ParentActivity.CancelAutoGreaseStopTimer();
			break;
		}
	}
	public void ClickOff(){
		
	}
	public void ClickOn(){
		
	}
	
}