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
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBCenterBrakePedalCalibrationFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	// ImageView
	ImageView imgViewGear;
	ImageView imgViewGearLevel4;
	ImageView imgViewGearLevel3;
	ImageView imgViewGearLevel2;
	ImageView imgViewGearLevel1;
	ImageView imgViewGearLevelUp;
	ImageView imgViewGearLevelDown;
	
	RelativeLayout layoutBG;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Status;
	int FaultInfo;
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
		TAG = "MainBCenterBrakePedalCalibrationFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_b_brakepedal, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_BRKAEPEDALCALIBRATION_TOP;

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
		imgViewGear = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear);
		imgViewGearLevel4 = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear_level_4);
		imgViewGearLevel3 = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear_level_3);
		imgViewGearLevel2 = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear_level_2);
		imgViewGearLevel1 = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear_level_1);
		imgViewGearLevelUp = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear_level_up);
		imgViewGearLevelDown = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_brakepedal_gear_level_down);
		
		layoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_b_brakepedal);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
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
		Status = CAN1Comm.Get_BrakePedalPositionSensorCalibrationStatus_564_PGN61184_202();
		FaultInfo = CAN1Comm.Get_BrakePedalPositionSensorCalibration_FaultInformation_565_PGN61184_202();
		if(Status == 0xFF){
			Status = 0;
		}
		if(FaultInfo == 0xFF){
			FaultInfo = 0;
		}
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BrakePedalDisplay(Status,FaultInfo);
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickBG(){
		ParentActivity._MainBBaseFragment.showCalibrationtoDefaultScreenAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public void BrakePedalDisplay(int status, int faultinfo){
		int Index = 0;
		if(((faultinfo >= 0x0A) && (faultinfo <= 0x10)) || ((faultinfo >= 0x12) && (faultinfo <= 0x14)))
		{
			if((faultinfo >= 0x0A) && (faultinfo <= 0x10))			// FN ~ IU
				Index = faultinfo -0x07;
			else if((faultinfo >= 0x12) && (faultinfo <= 0x14))		// TO ~ DU
				Index = faultinfo -0x08;
			
			imgViewGearLevelUp.setImageResource(R.drawable.select_gear_up_off);
			imgViewGearLevel4.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel3.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel2.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel1.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevelDown.setImageResource(R.drawable.select_gear_down_off);
			
		}
		else if((status == 0) && (faultinfo == 0))	// IP¸¸....
		{
			Index = 0;
			imgViewGearLevelUp.setImageResource(R.drawable.select_gear_up_off);
			imgViewGearLevel4.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel3.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel2.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel1.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevelDown.setImageResource(R.drawable.select_gear_down_off);
		}
		else if((faultinfo == 0x11) && (faultinfo == 0x15))		// Blank
		{
			imgViewGear.setVisibility(View.INVISIBLE);
			imgViewGearLevelUp.setImageResource(R.drawable.select_gear_up_off);
			imgViewGearLevel4.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel3.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel2.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevel1.setImageResource(R.drawable.select_gear_off);
			imgViewGearLevelDown.setImageResource(R.drawable.select_gear_down_off);
		}
		else
		{
			switch( status )
			{
				case 0x01 :			// IP + Select Gear R4

					Index = 0;
					break;
				case 0x02 :			// IP + Select Gear F4

					Index = 0;
					break;
				case 0x03 :			// OK
					Index = 1;
					
					break;
				case 0x04 :			// STOP

					Index = 2;
					break;
				default :			// Only IP

					Index = 0;
					break;
			}
			
			if(status == 0x01 || status == 0x02)
			{
				imgViewGearLevelUp.setImageResource(R.drawable.select_gear_up_off);
				imgViewGearLevelDown.setImageResource(R.drawable.select_gear_down_off);
				if(status == 0x01)		// R
				{
					imgViewGearLevelDown.setImageResource(R.drawable.select_gear_down_on);
					imgViewGearLevel4.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevel3.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevel2.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevel1.setImageResource(R.drawable.select_gear_on);
				}
				else if(status == 0x02)		// F
				{
					imgViewGearLevel4.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevel3.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevel2.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevel1.setImageResource(R.drawable.select_gear_on);
					imgViewGearLevelUp.setImageResource(R.drawable.select_gear_up_on);
				}
			}
			else
			{
				imgViewGearLevelUp.setImageResource(R.drawable.select_gear_up_off);
				imgViewGearLevel4.setImageResource(R.drawable.select_gear_off);
				imgViewGearLevel3.setImageResource(R.drawable.select_gear_off);
				imgViewGearLevel2.setImageResource(R.drawable.select_gear_off);
				imgViewGearLevel1.setImageResource(R.drawable.select_gear_off);
				imgViewGearLevelDown.setImageResource(R.drawable.select_gear_down_off);
			}
		}
		
		switch (Index) {
		case 0x00:
			imgViewGear.setImageResource(R.drawable.actual_gear_ip);
			break;
		case 0x01:
			imgViewGear.setImageResource(R.drawable.actual_gear_ok);
			break;
		case 0x02:
			imgViewGear.setImageResource(R.drawable.actual_gear_stop);
			break;
		case 0x03:
			imgViewGear.setImageResource(R.drawable.actual_gear_fn_s);
			break;
		case 0x04:
			imgViewGear.setImageResource(R.drawable.actual_gear_fs_s);
			break;
		case 0x05:
			imgViewGear.setImageResource(R.drawable.actual_gear_fo_s);
			break;
		case 0x06:
			imgViewGear.setImageResource(R.drawable.actual_gear_sl_s);
			break;
		case 0x07:
			imgViewGear.setImageResource(R.drawable.actual_gear_su_s);
			break;
		case 0x08:
			imgViewGear.setImageResource(R.drawable.actual_gear_il_s);
			break;
		case 0x09:
			imgViewGear.setImageResource(R.drawable.actual_gear_iu_s);
			break;
		case 0x0A:
			imgViewGear.setImageResource(R.drawable.actual_gear_to_s);
			break;
		case 0x0B:
			imgViewGear.setImageResource(R.drawable.actual_gear_dl_s);
			break;
		case 0x0C:
			imgViewGear.setImageResource(R.drawable.actual_gear_du_s);
			break;

		}
	}
	/////////////////////////////////////////////////////////////////////
}