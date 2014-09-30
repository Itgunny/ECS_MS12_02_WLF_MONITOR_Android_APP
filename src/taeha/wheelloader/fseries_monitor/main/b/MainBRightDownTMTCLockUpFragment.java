package taeha.wheelloader.fseries_monitor.main.b;


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

public class MainBRightDownTMTCLockUpFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBRightDownTMTCLockUpFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int TCLockUp;
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
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightdown_main_b_tmtclockup, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_RIGHTDOWN_TCLOCKUP;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TMTCLockUpDisplay(TCLockUp);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_b_tmtclockup_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_rightdown_main_b_tmtclockup_on);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		TCLockUp = CAN1Comm.Get_TransmissionTCLockupEngaged_568_PGN65434();
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
		radioOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOn();
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
	public void TMTCLockUpDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF:
			radioOff.setChecked(true);
			radioOn.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON:
			radioOff.setChecked(false);
			radioOn.setChecked(true);
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
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_OFF);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
	}
	public void ClickOn(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_TransmissionTCLockupEngaged_568_PGN61184_104(CAN1CommManager.DATA_STATE_TM_LOCKUPCLUTCH_ON);
		CAN1Comm.TxCANToMCU(104);
		ParentActivity._MainBBaseFragment.showRightDowntoDefaultScreenAnimation();
	}
}