package taeha.wheelloader.fseries_monitor.main.a;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainARightDownTMICCOModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioH;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CCOMode;
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
		TAG = "MainARightDownTMICCOModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_a_tmiccomode, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_RIGHTDOWN_CCOMODE;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG,"onResume");
		TMCCOModeDisplay(CCOMode);	
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmiccomode_off);
		radioH = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_a_tmiccomode_h);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		Log.d(TAG,"InitValuables");
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
			}
		});
		radioH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickH();
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
	public void TMCCOModeDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			radioOff.setChecked(true);
			radioH.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			radioOff.setChecked(false);
			radioH.setChecked(true);

			break;
		default:
			break;
		}

	}

	
	public void ClickOff(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();

	}
	public void ClickH(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainABaseFragment.showRightDowntoDefaultScreenAnimation();

	}
}