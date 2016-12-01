package taeha.wheelloader.fseries_monitor.main.a;

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
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainACenterAEBFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	// ImageView
	ImageView imgViewGear;
	
	RelativeLayout layoutBG;
	TextFitTextView textViewRPMDataUnit;
	TextFitTextView textViewRPMData;	// ++, --, 150212 bwk
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int AEBMainStatus;
	int AEBSubStatus;
	
	// ++, 150212 bwk
	protected int RPM;
	
	int MachineStatusUpperIndex;
	int MachineStatusLowerIndex;
	// --, 150212 bwk
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
		TAG = "MainACenterAEBFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_a_aeb, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_AEB_TOP;

		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		// ++, 150212 bwk
		ParentActivity.MachineStatusUpperIndex = MachineStatusUpperIndex;
		ParentActivity.MachineStatusLowerIndex = MachineStatusLowerIndex;
		// --, 150212 bwk
		
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestAEB_PGN61184_201(0);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(0);
		CAN1Comm.TxCANToMCU(201);
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		CAN1Comm.Set_RequestAEB_PGN61184_201(3);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgViewGear = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_aeb_gear);
		
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_a_aeb);
		
		textViewRPMData = (TextFitTextView)mRoot.findViewById(R.id.textView_center_main_a_aeb_rpm);	// ++, --, 150212 bwk
		
		
		textViewRPMDataUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_center_main_a_aeb_rpm_unit);
		textViewRPMDataUnit.setText(getString(ParentActivity.getResources().getString(string.rpm), 34));
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		// ++, 150212 bwk
		MachineStatusUpperIndex = ParentActivity.MachineStatusUpperIndex;
		MachineStatusLowerIndex = ParentActivity.MachineStatusLowerIndex;
		// --, 150212 bwk
		ParentActivity.MachineStatusUpperIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD;
		ParentActivity.MachineStatusLowerIndex = CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL;	// ++, --, 150212 bwk NOSELECT -> TMOIL
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
		RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();	// ++, --, 150212 bwk
		AEBMainStatus = CAN1Comm.Get_AEBStatusInformation_MainCode_562_PGN61184_202();
		AEBSubStatus = CAN1Comm.Get_AEBStatusInformation_SubCode_563_PGN61184_202();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RPMDisplay(RPM);		// ++, --, 150212 bwk
		AEBGearDisplay(AEBMainStatus,AEBSubStatus);
	}
	/////////////////////////////////////////////////////////////////////
	//++, 150212 bwk
	public void RPMDisplay(int Data){
		if(Data == 0xFFFF)
			Data = 0;
		else if(Data > 9999)
			Data = 9999;
		try {
			textViewRPMData.setText(Integer.toString(Data));
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
	}
	// --, 150212 bwk
	
	public void ClickBG(){
		ParentActivity._MainABaseFragment.showCalibrationtoDefaultScreenAnimation();
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