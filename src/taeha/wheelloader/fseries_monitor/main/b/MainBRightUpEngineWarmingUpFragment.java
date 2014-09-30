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

public class MainBRightUpEngineWarmingUpFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBRightUpengineWarmingUpFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOn;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineWarmingUp;
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
		mRoot = inflater.inflate(R.layout.rightup_main_b_enginewarmingup, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_WARMINGUP;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		EngineWarmingUpDisplay(EngineWarmingUp);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginewarmingup_off);
		radioOn = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginewarmingup_on);

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		EngineWarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
		
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
	public void EngineWarmingUpDisplay(int _EngineWarmingUp){
		switch (_EngineWarmingUp) {
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
			radioOff.setChecked(true);
			radioOn.setChecked(false);

			break;
		case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
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
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickOn(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EngineAlternateLowIdleSwitch_348_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
}