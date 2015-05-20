package taeha.wheelloader.fseries_monitor.main.b;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainBCenterQuickFragment extends MainBCenterFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		TAG = "MainBCenterQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_b, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_QUICK_TOP;
//		Log.d(TAG,"14ScreenIndex="+Integer.toHexString(ParentActivity.ScreenIndex));
		// ++, 150325 bwk
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.COMMAND_MAINTENANCE_ITEM_LIST_REQUEST);
		CAN1Comm.TxCANToMCU(12);
		// --, 150325 bwk
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		super.InitResource();
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		CursurDisplayDetail(ParentActivity._MainBBaseFragment.CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickBG();
			}
		});
	}

	/////////////////////////////////////////////////////////////////////	
	
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		// ++, 150407 bwk
		//ParentActivity._MainBBaseFragment.showDefaultScreenAnimation();
		ParentActivity._MainBBaseFragment.showQuicktoDefaultScreenAnimation();
		// --, 150407 bwk
		Log.d(TAG,"ClickOption");
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnOption.setClickable(ClickFlag);
	}
	
	public void Clickable(boolean flag){
		imgbtnOption.setClickable(flag);
	}
	/////////////////////////////////////////////////////////////////////	
	public void IconDisplay(int _fault, int _maint){
		if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON && _maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_faultmaint_btn);
		}else if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_fault_btn);
		}else if(_maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_maint_btn);
		}else{
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_b_home_btn);
		}
	}
	public void CursurDisplayDetail(int index){
		layoutBG.setBackgroundResource(R.drawable.main_bg_center);
		switch(index){
			case 4:
				layoutBG.setBackgroundResource(R.drawable.main_bg_center_s_02);
				break;
		}		
	}
}
