package taeha.wheelloader.fseries_monitor.main.a;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class MainARightUpEngineModeFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioPower;
	RadioButton radioStandard;
	RadioButton radioEcono;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int EngineMode;
	
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
		TAG = "MainARightUpEngineModeFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.rightup_main_a_enginemode, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_RIGHTUP_ENGINE_MODE;
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};		
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
		radioPower = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_a_enginemode_power);
		radioStandard = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_a_enginemode_standard);
		radioEcono = (RadioButton)mRoot.findViewById(R.id.radioButton_rightup_main_a_enginemode_econo);
		
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
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPower();
			}
		});
		radioStandard.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickStandard();
			}
		});
		radioEcono.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
		
		CursurIndex = _EngineMode+1;
		CursurDisplay(CursurIndex);		

	}
	public void ClickPower(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainABaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickStandard(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_STD);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainABaseFragment.showRightUptoDefaultScreenAnimation();
	}
	public void ClickEcono(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		CAN1Comm.Set_EnginePowerMode_347_PGN61184_101(CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO);
		CAN1Comm.TxCANToMCU(101);
		ParentActivity._MainABaseFragment.showRightUptoDefaultScreenAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
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
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickPower();
			break;
		case 2:
			ClickStandard();
			break;
		case 3:
			ClickEcono();
			break;
		default:

			break;
		}
	}
	public void CursurDisplay(int Index){
		radioPower.setPressed(false);
		radioStandard.setPressed(false);
		radioEcono.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioPower.setPressed(true);
			break;
		case 2:
			radioStandard.setPressed(true);
			break;
		case 3:
			radioEcono.setPressed(true);
			break;
		default:
			break;
		}
	}
}