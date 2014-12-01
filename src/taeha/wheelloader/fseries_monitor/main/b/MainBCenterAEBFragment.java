package taeha.wheelloader.fseries_monitor.main.b;

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
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBCenterAEBFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	// ImageView
	ImageView imgViewGear;
	
	
	RelativeLayout layoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int AEBMainStatus;
	int AEBSubStatus;
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
		TAG = "MainBCenterAEBFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_b_aeb, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_AEB_TOP;

		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestAEB_PGN61184_201(0);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(0);
		CAN1Comm.TxCANToMCU(201);
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		CAN1Comm.Set_RequestAEB_PGN61184_201(3);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgViewGear = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_aeb_gear);
	
		
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_b_aeb);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		ParentActivity.MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD;
		ParentActivity.MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		layoutBG.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBG();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AEBMainStatus = CAN1Comm.Get_AEBStatusInformation_MainCode_562_PGN61184_202();
		AEBSubStatus = CAN1Comm.Get_AEBStatusInformation_SubCode_563_PGN61184_202();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
	
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickBG(){
		ParentActivity._MainBBaseFragment.showCalibrationtoDefaultScreenAnimation();
	}
	/////////////////////////////////////////////////////////////////////

	public void AEBGearDisplay(int MainStatus, int SubStatus){
		int Index = 0;
		if(MainStatus > 0x10)
		{
			MainStatus = 0;
		}
		
		if(SubStatus > 8)
		{
			SubStatus = 0;
		}
		
		if(SubStatus == 0)
		{
			Index = MainStatus;
			switch (Index) {
			case 0:
				imgViewGear.setImageResource(R.drawable.actual_gear_st);
				break;
			case 1:
				imgViewGear.setImageResource(R.drawable.actual_gear_k1);
				break;
			case 2:
				imgViewGear.setImageResource(R.drawable.actual_gear_k2);
				break;
			case 3:
				imgViewGear.setImageResource(R.drawable.actual_gear_k3);
				break;
			case 4:
				imgViewGear.setImageResource(R.drawable.actual_gear_k4);
				break;
			case 5:
				imgViewGear.setImageResource(R.drawable.actual_gear_kv);
				break;
			case 6:
				imgViewGear.setImageResource(R.drawable.actual_gear_kr);
				break;
			case 7:
				imgViewGear.setImageResource(R.drawable.actual_gear_ok);
				break;
			case 8:
				imgViewGear.setImageResource(R.drawable.actual_gear_stop);
				break;
			case 9:
				imgViewGear.setImageResource(R.drawable.actual_gear_k1_s);
				break;
			case 10:
				imgViewGear.setImageResource(R.drawable.actual_gear_k2_s);
				break;
			case 11:
				imgViewGear.setImageResource(R.drawable.actual_gear_k3_s);
				break;
			case 12:
				imgViewGear.setImageResource(R.drawable.actual_gear_k4_s);
				break;
			case 13:
				imgViewGear.setImageResource(R.drawable.actual_gear_kv_s);
				break;
			case 14:
				imgViewGear.setImageResource(R.drawable.actual_gear_kr_s);
				break;
			case 15:
				imgViewGear.setImageResource(R.drawable.actual_gear_kw_s);
				break;
			case 16:
				imgViewGear.setImageResource(R.drawable.actual_gear_kw);
				break;
			}
		}
		else
		{
			if(SubStatus == 0x01)
			{
				Index = 0;
			}
			else if((SubStatus >= 2) && (SubStatus <= 8))
			{
				Index = SubStatus - 1;
			}
			switch (Index) {
			case 0:
				imgViewGear.setImageResource(R.drawable.actual_gear_stop);
				break;
			case 1:
				imgViewGear.setImageResource(R.drawable.actual_gear_fn);
				break;
			case 2:
				imgViewGear.setImageResource(R.drawable.actual_gear_fp);
				break;
			case 3:
				imgViewGear.setImageResource(R.drawable.actual_gear_fo);
				break;
			case 4:
				imgViewGear.setImageResource(R.drawable.actual_gear_t_up);
				break;
			case 5:
				imgViewGear.setImageResource(R.drawable.actual_gear_t_down);
				break;
			case 6:
				imgViewGear.setImageResource(R.drawable.actual_gear_e_up);
				break;
			case 7:
				imgViewGear.setImageResource(R.drawable.actual_gear_e_down);
				break;
			}
		}
	}
	/////////////////////////////////////////////////////////////////////
}