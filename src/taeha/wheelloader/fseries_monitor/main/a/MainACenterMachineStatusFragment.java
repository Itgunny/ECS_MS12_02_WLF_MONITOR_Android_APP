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
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainACenterMachineStatusFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutTop;
	// ++, 150326 bwk
	ImageView	imageViewUpIcon;
	ImageView	imageViewDownIcon;
	// --, 150326 bwk
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
		TAG = "MainACenterMachineStatusFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_a_machinestatus, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutTop = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_a_machinestatus);
		
		imageViewUpIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_machinestatus_up);
		imageViewDownIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_machinestatus_down);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		LayoutTop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOption();
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
		IconDisplay(ParentActivity.MachineStatusUpperIndex,ParentActivity.MachineStatusLowerIndex);
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void ClickOption(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainABaseFragment.showLeftUptoDefaultScreenAnimation();
	}
	
	public void IconDisplay(int Upper, int Lower){
		imageViewUpIcon.setVisibility(View.VISIBLE);
		imageViewDownIcon.setVisibility(View.VISIBLE);
		switch (Upper) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_hyd);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_tm_oil);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_batt);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_work);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_coolant);
			break;
//		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
//			if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_955
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_955TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_960
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_960TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980TM)
//				imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_front_axle);
//			break;
//		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
//			if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980TM)
//				imageViewUpIcon.setBackgroundResource(R.drawable.main_icon_rear_axle);
//			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
		default:
			imageViewUpIcon.setVisibility(View.INVISIBLE);
			break;
		}
		switch (Lower) {
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_HYD:
			imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_hyd);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_TMOIL:
			imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_tm_oil);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_BATTERY:
			imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_batt);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING:
			if(Upper != CAN1CommManager.DATA_STATE_MACHINESTATUS_WEIGHING)
				imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_work);
			else
				imageViewDownIcon.setVisibility(View.INVISIBLE);
			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_COOLANT:
			imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_coolant);
			break;
//		case CAN1CommManager.DATA_STATE_MACHINESTATUS_FRONTAXLE:
//			if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_955
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_955TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_960
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_960TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980TM)
//				imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_front_axle);
//			break;
//		case CAN1CommManager.DATA_STATE_MACHINESTATUS_REARAXLE:
//			if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970TM
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980
//			|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980TM)
//				imageViewDownIcon.setBackgroundResource(R.drawable.main_icon_rear_axle);
//			break;
		case CAN1CommManager.DATA_STATE_MACHINESTATUS_NOSELECT:
		default:
			imageViewDownIcon.setVisibility(View.INVISIBLE);
			break;
		}		
	}
}