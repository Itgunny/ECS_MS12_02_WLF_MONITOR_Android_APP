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
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainBRightUpEngineModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBRightUpEngineModeFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioPower;
	RadioButton radioStandard;
	RadioButton radioEcono;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
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
		mRoot = inflater.inflate(R.layout.rightup_main_b_enginemode, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_MODE;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		EngineModeDisplay(EngineMode);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioPower = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginemode_power);
		radioStandard = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginemode_standard);
		radioEcono = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_b_enginemode_econo);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioPower.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPower();
			}
		});
		radioStandard.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickStandard();
			}
		});
		radioEcono.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEcono();
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
	public void EngineModeDisplay(int _EngineMode){
		switch (_EngineMode) {
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
			radioPower.setChecked(true);
			radioStandard.setChecked(false);
			radioEcono.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
			radioPower.setChecked(false);
			radioStandard.setChecked(true);
			radioEcono.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
			radioPower.setChecked(false);
			radioStandard.setChecked(false);
			radioEcono.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ClickPower(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickStandard(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_STD);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickEcono(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainBBaseFragment.showRightUptoDefaultScreenAnimation();
	}
}