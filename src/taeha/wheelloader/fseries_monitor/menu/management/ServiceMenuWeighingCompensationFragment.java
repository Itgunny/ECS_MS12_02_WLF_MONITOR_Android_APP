package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ServiceMenuWeighingCompensationFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

	TextView textViewNum1;
	TextView textViewNum2;
	TextView textViewNum3;
	TextView textViewNum4;
	TextView textViewNum5;
	TextView textViewNum6;
	TextView textViewNum7;
	TextView textViewNum8;
	TextView textViewNum9;
	TextView textViewNum0;
	
	ImageButton imgbtnBack;
	TextView textViewDot;
	ImageButton imgbtnPlusMinu;
	
	RadioButton radioNoOffset;
	RadioButton radioWorkTool1;
	RadioButton radioWorkTool2;
	RadioButton radioWorkTool3;
	
	RelativeLayout layoutWorkTool1;
	RelativeLayout layoutWorkTool2;
	RelativeLayout layoutWorkTool3;
	
	TextView textViewWorkTool1Data;
	TextView textViewWorkTool2Data;
	TextView textViewWorkTool3Data;
	
	TextView textViewWorkTool1Unit;
	TextView textViewWorkTool2Unit;
	TextView textViewWorkTool3Unit;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeightOffsetSelectionStatus;
	int WorkTool1;
	int WorkTool2;
	int WorkTool3;
	
	int Num100;
	int Num10;
	int Num1;
	int NumUnder;
	int NumIndex;
	
	int NumSetting;
	
	Boolean NumSign;
	
	Handler HandleCursurDisplay;
	int CursurIndex;
	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
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
		 TAG = "ServiceMenuWeighingCompensationFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_service_weighingcompensation, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_CALL);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(15);

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_WEIGHINGCOMPENSATION_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Weighing_System_Compensation));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_weighingcompensation_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_weighingcompensation_low_cancel);
	
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_0);

		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_weighingcompensation_num_back);
		
		textViewDot = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_num_dot);
		
		imgbtnPlusMinu = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_weighingcompensation_num_plusminus);
		
		radioNoOffset = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_weighingcompensation_nooffset);
		radioWorkTool1 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_weighingcompensation_worktool1);
		radioWorkTool2 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_weighingcompensation_worktool2);
		radioWorkTool3 = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_weighingcompensation_worktool3);
		
		layoutWorkTool1 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_service_weighingcompensation_worktool1);
		layoutWorkTool2 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_service_weighingcompensation_worktool2);
		layoutWorkTool3 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_service_weighingcompensation_worktool3);
		
		textViewWorkTool1Data = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_worktool1_data);
		textViewWorkTool2Data = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_worktool2_data);
		textViewWorkTool3Data = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_worktool3_data);
		
		textViewWorkTool1Unit = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_worktool1_unit);
		textViewWorkTool2Unit = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_worktool2_unit);
		textViewWorkTool3Unit = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_weighingcompensation_worktool3_unit);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		NumSign = true;
		NumIndex = 5;		// ++, --, 150203 bwk : 0 -> 5
		NumSetting = 0;
		Num100 = 0;
		Num10 = 0;
		Num1 = 0;
		NumUnder = 0;
		
		WeightOffsetSelectionStatus = CAN1Comm.Get_WeightOffsetSelectionStatus_PGN61184_63();
		WorkTool1 = CAN1Comm.Get_WeightOffsetWorkTool1_1922_PGN61184_63();
		WorkTool2 = CAN1Comm.Get_WeightOffsetWorkTool2_1922_PGN61184_63();
		WorkTool3 = CAN1Comm.Get_WeightOffsetWorkTool3_1922_PGN61184_63();
		
		switch (WeightOffsetSelectionStatus) {
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET:
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1:
			NumSetting = WorkTool1;
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2:
			NumSetting = WorkTool2;
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3:
			NumSetting = WorkTool3;
			break;
		default:
			break;
		}
		
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,WorkTool1,WorkTool2,WorkTool3);
		CursurFirstDisplay(WeightOffsetSelectionStatus);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				ClickCancel();
			}
		});
	    textViewNum1.setOnClickListener(new View.OnClickListener() {
			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					ClickNum1();
				}
		});	
		textViewNum2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum2();
			}
		});	
		textViewNum3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum3();
			}
		});	
		textViewNum4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum4();
			}
		});	
		textViewNum5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum5();
			}
		});	
		textViewNum6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum6();
			}
		});	
		textViewNum7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum7();
			}
		});	
		textViewNum8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNum8();
			}
		});	
		textViewNum9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				ClickNum9();
			}
		});	
		textViewNum0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	
				ClickNum0();
			}
		});	
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNumBack();
			}
		});	
		textViewDot.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				ClickNumDot();
			}
		});	
		imgbtnPlusMinu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNumPlusMinus();
			}
		});	
		radioNoOffset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickNoOffset();
			}
		});	
		radioWorkTool1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickWorkTool1();
			}
		});	
		radioWorkTool2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickWorkTool2();
			}
		});	
		radioWorkTool3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				ClickWorkTool3();
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
	public void ClickOK(){
		CursurIndex = 19;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
		if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET){
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(WeightOffsetSelectionStatus);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(15);
		}else if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1){
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(WeightOffsetSelectionStatus);
			CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1);
			CAN1Comm.Set_WeightOffset_1922_PGN61184_62(NumSetting);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(15);
			CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(0xF);
			CAN1Comm.Set_WeightOffset_1922_PGN61184_62(0xFFFF);
		}else if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2){
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(WeightOffsetSelectionStatus);
			CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2);
			CAN1Comm.Set_WeightOffset_1922_PGN61184_62(NumSetting);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(15);
			CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(0xF);
			CAN1Comm.Set_WeightOffset_1922_PGN61184_62(0xFFFF);
		}else if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3){
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(WeightOffsetSelectionStatus);
			CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3);
			CAN1Comm.Set_WeightOffset_1922_PGN61184_62(NumSetting);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_WeightOffsetSelection_PGN61184_62(15);
			CAN1Comm.Set_WeightOffsetSetting_PGN61184_62(0xF);
			CAN1Comm.Set_WeightOffset_1922_PGN61184_62(0xFFFF);
		}

	}
	public void ClickCancel(){
		CursurIndex = 18;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
	}
	public void ClickDefault(){
		
	}
	
	// ++, --, 150203 bwk
	/*
	 * 숫자 함수 수정
	  	public void ClickNumx(){
			CursurIndex = x;
			HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			NumSetting = SetNumber(NumIndex,1,NumSign);
			if(NumIndex>=2)
				NumIndex = 0;
			else
				NumIndex++;
			WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		}
		->
		public void ClickNumx(){
			CursurIndex = x;
			HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			NumSetting = SetNumber(NumIndex,1,NumSign);
			WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
			NumIndex++;
		}

	 */
	public void ClickNum1(){
		CursurIndex = 5;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,1,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum2(){
		CursurIndex = 6;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,2,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum3(){
		CursurIndex = 7;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,3,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum4(){
		CursurIndex = 8;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,4,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum5(){
		CursurIndex = 9;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,5,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum6(){
		CursurIndex = 10;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,6,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum7(){
		CursurIndex = 11;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,7,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum8(){
		CursurIndex = 12;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,8,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum9(){
		CursurIndex = 13;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,9,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNum0(){
		CursurIndex = 15;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumSetting = SetNumber(NumIndex,0,NumSign);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
		NumIndex++;
	}
	public void ClickNumBack(){
		CursurIndex = 14;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumIndex = -1;	// ++, --, 150203 bwk 0 -> -1
		NumSetting = 0;
		Num100 = 0;
		Num10 = 0;
		Num1 = 0;
		NumUnder = 0;
		NumSign = true;
		NumSetting = SetNumber(true);
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
	}
	public void ClickNumDot(){
		CursurIndex = 16;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		NumIndex = 3;
		// ++, 150203 bwk
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);	
		NumIndex++;	
		// --, 150203 bwk
	}
	public void ClickNumPlusMinus(){
		CursurIndex = 17;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		if(NumSign == true)
			NumSign = false;
		else
			NumSign = true;
		
		NumSetting = SetNumber(NumSign);
		
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,NumSetting,NumSetting,NumSetting);
	}
	public void ClickNoOffset(){
		CursurIndex = 1;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		WeightOffsetSelectionStatus = CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET;
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,WorkTool1,WorkTool2,WorkTool3);
		NumIndex = 0;
		NumSetting = 0;
		Num100 = 0;
		Num10 = 0;
		Num1 = 0;
		NumUnder = 0;
		NumSign = true;
	}
	public void ClickWorkTool1(){
		CursurIndex = 2;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		WeightOffsetSelectionStatus = CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1;
		NumIndex = 5;	// ++, --, 150203 bwk
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,WorkTool1,WorkTool2,WorkTool3);
		NumIndex = 0; 
		NumSetting = WorkTool1;
		Num100 = 0;
		Num10 = 0;
		Num1 = 0;
		NumUnder = 0;
		NumSign = true;
	}
	public void ClickWorkTool2(){
		CursurIndex = 3;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		WeightOffsetSelectionStatus = CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2;
		NumIndex = 5;	// ++, --, 150203 bwk
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,WorkTool1,WorkTool2,WorkTool3);
		NumIndex = 0; 
		NumSetting = WorkTool2;
		Num100 = 0;
		Num10 = 0;
		Num1 = 0;
		NumUnder = 0;
		NumSign = true;
	}
	public void ClickWorkTool3(){
		CursurIndex = 4;
		HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
		WeightOffsetSelectionStatus = CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3;
		NumIndex = 5;	// ++, --, 150203 bwk
		WeighingSystemCompensationDisplay(WeightOffsetSelectionStatus,WorkTool1,WorkTool2,WorkTool3);
		NumIndex = 0; 
		NumSetting = WorkTool3;
		Num100 = 0;
		Num10 = 0;
		Num1 = 0;
		NumUnder = 0;
		NumSign = true;
	}
	/////////////////////////////////////////////////////////////////////
	public void WeighingSystemCompensationDisplay(int _status, int _worktool1, int _worktool2, int _worktool3){
		switch (_status) {
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET:
			radioNoOffset.setChecked(true);
			radioWorkTool1.setChecked(false);
			radioWorkTool2.setChecked(false);
			radioWorkTool3.setChecked(false);
			layoutWorkTool1.setVisibility(View.GONE);
			layoutWorkTool2.setVisibility(View.GONE);
			layoutWorkTool3.setVisibility(View.GONE);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1:
			radioNoOffset.setChecked(false);
			radioWorkTool1.setChecked(true);
			radioWorkTool2.setChecked(false);
			radioWorkTool3.setChecked(false);
			layoutWorkTool1.setVisibility(View.VISIBLE);
			layoutWorkTool2.setVisibility(View.GONE);
			layoutWorkTool3.setVisibility(View.GONE);
			CompensationValueDisplay(_worktool1,textViewWorkTool1Data);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2:
			radioNoOffset.setChecked(false);
			radioWorkTool1.setChecked(false);
			radioWorkTool2.setChecked(true);
			radioWorkTool3.setChecked(false);
			layoutWorkTool1.setVisibility(View.GONE);
			layoutWorkTool2.setVisibility(View.VISIBLE);
			layoutWorkTool3.setVisibility(View.GONE);
			CompensationValueDisplay(_worktool2,textViewWorkTool2Data);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3:
			radioNoOffset.setChecked(false);
			radioWorkTool1.setChecked(false);
			radioWorkTool2.setChecked(false);
			radioWorkTool3.setChecked(true);
			layoutWorkTool1.setVisibility(View.GONE);
			layoutWorkTool2.setVisibility(View.GONE);
			layoutWorkTool3.setVisibility(View.VISIBLE);
			CompensationValueDisplay(_worktool3,textViewWorkTool3Data);
			break;
		default:
			break;
		}
	}
	
	public void CompensationValueDisplay(int value, TextView textview){
		int Temp;
		int n100;
		int n10;
		int n1;
		int Under1;
		boolean sign = true;
		String str;
		
	
		Temp = value - 1000;
		
		if(Temp >= 0){
			sign = true;
		}else{
			sign = false;
			Temp *= -1;
		}
		
		n100 = (Temp  / 1000) % 10;
		n10 = (Temp  / 100) % 10;
		n1 = (Temp  / 10) % 10;
		Under1 = (Temp  / 1) % 10;
		
		// ++, 150203 bwk
		/*
		if(sign == true){
			if(n100 != 0){
				str = Integer.toString(n100) + Integer.toString(n10) + Integer.toString(n1) + "." + Integer.toString(Under1);
			}else if(n10 != 0){
				str = Integer.toString(n10) + Integer.toString(n1) + "." + Integer.toString(Under1);
			}else {
				str = Integer.toString(n1) + "." + Integer.toString(Under1);
			}
		}else{
			if(n100 != 0){
				str = "-" + Integer.toString(n100) + Integer.toString(n10) + Integer.toString(n1) + "." + Integer.toString(Under1);
			}else if(n10 != 0){
				str = "-" + Integer.toString(n10) + Integer.toString(n1) + "." + Integer.toString(Under1);
			}else {
				str = "-" + Integer.toString(n1) + "." + Integer.toString(Under1);
			}
		}
		*/
		
		if(sign == false)
			str = "-";
		else 
			str = "";
		
		if(NumIndex == -1)
		{
			str = "";
			NumIndex++;
		}
		else if(NumIndex == 0)
		{
			str += Integer.toString(n1);
		}
		else if(NumIndex == 1)
		{
			if(n10 != 0)
				str += Integer.toString(n10);
			str += Integer.toString(n1);
		}
		else
		{
			if(n100 != 0){
				str += Integer.toString(n100) + Integer.toString(n10) + Integer.toString(n1);
			}else if(n10 != 0){
				str += Integer.toString(n10) + Integer.toString(n1);
			}else {
				str += Integer.toString(n1);
			}
			
			if(NumIndex == 3)
			{
				if(CursurIndex == 16)
					str += ".";
			}
			else if(NumIndex > 3)
			{
				str += "." + Integer.toString(Under1);
				
				if(NumIndex == 5)
				{
					Num100 = 0;
					Num10 = 0;
					Num1 = 0;
					NumUnder = 0;
					NumSign = true;
					NumIndex = 0;
				}
				else if(NumIndex == 4)
				{
					if(Num100 == 1 && Num10 == 0 && Num1 == 0 && NumUnder == 0)
					{
						Num100 = 0;
						Num10 = 0;
						Num1 = 0;
						NumUnder = 0;
					}
					NumIndex = 6;
				}
			}
		}
		// --, 150203 bwk
		
		
		textview.setText(str);
	
	}
	/////////////////////////////////////////////////////////////////////
	public int SetNumber(int _index, int _num, boolean sign){

		int result;
		
		// ++, 150203 bwk
		if(_index >= 6 || _index == 3)
		{
			Num100 = 0;
			Num10 = 0;
			Num1 = 0;
			NumUnder = 0;
			NumSign = true;
			NumIndex = 0;
			_index = 0;
		}
		else if(_index==1 && Num1 == 0)
		{
			_index = 0;
			NumIndex = 0;
		}
		// --, 150203 bwk
		
		switch (_index) {
		case 0:
			Num1 = _num;
			break;
		case 1:
			Num10 = Num1;
			Num1 = _num;
			break;
		case 2:
			Num100 = Num10;
			Num10 = Num1;
			Num1 = _num;
			break;
		case 4:		// ++, --, 150203 bwk 3 -> 4
			NumUnder = _num;
			break;
		default:
			break;
		}
		result = Num100 * 1000 + Num10 * 100 + Num1 * 10 + NumUnder;
		if(sign == false)
			result *= -1;
		
		result += 1000;
		
		if(result > 2000)
		{
			result = 2000;
			// ++, 150203 bwk 
			Num100 = 1;
			Num10 = 0;
			Num1 = 0;
			NumUnder = 0;
			// --, 150203 bwk
		}
		else if(result < 0)
		{
			result = 0;
			// ++, 150203 bwk
			Num100 = 0;
			Num10 = 0;
			Num1 = 0;
			NumUnder = 0;
			// --, 150203 bwk
		}
		
		return result;
	}
	public int SetNumber(boolean sign){

		int result;
	
		result = Num100 * 1000 + Num10 * 100 + Num1 * 10 + NumUnder;
		if(sign == false)
			result *= -1;
		
		result += 1000;
		
		if(result > 2000)
			result = 2000;
		else if(result < 0)
			result = 0;
		
		return result;
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 5:
			CursurIndex = 19;
			CursurDisplay(CursurIndex);
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
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
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 19:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 4:
			ClickCancel();
			break;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
			if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}else if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else if(WeightOffsetSelectionStatus == CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3){
				CursurIndex = 4;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}
			break;
		default:

			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		// ++, --, 150204 bwk : Enter 눌렀을 경우 포커스 이동안되는 문제 수정 = 함수부르고 CursurDisplay 부름(기존 커서이동 후 함수 호출하여 제자리에 있었음)
		case 1:
			// ++, 150204 bwk
			/*
			CursurIndex = 19;
			CursurDisplay(CursurIndex);
			ClickNoOffset();
			*/
			ClickNoOffset();
			CursurIndex = 19;
			HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			// --, 150204 bwk
			break;
		case 2:
			// ++, 150204 bwk
			/*
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			ClickWorkTool1();
			*/
			ClickWorkTool1();
			CursurIndex = 5;
			HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			// --, 150204 bwk
			break;
		case 3:
			// ++, 150204 bwk
			/*
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			ClickWorkTool2();
			*/
			ClickWorkTool2();
			CursurIndex = 5;
			HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			// --, 150204 bwk
			break;
		case 4:
			// ++, 150204 bwk
			/*
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			ClickWorkTool3();
			*/
			ClickWorkTool3();
			CursurIndex = 5;
			HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			// --, 150204 bwk
			break;
		case 5:
			ClickNum1();
			break;
		case 6:
			ClickNum2();
			break;
		case 7:
			ClickNum3();
			break;
		case 8:
			ClickNum4();
			break;
		case 9:
			ClickNum5();
			break;
		case 10:
			ClickNum6();
			break;
		case 11:
			ClickNum7();
			break;
		case 12:
			ClickNum8();
			break;
		case 13:
			ClickNum9();
			break;
		case 14:
			ClickNumBack();
			break;
		case 15:
			ClickNum0();
			break;
		case 16:
			ClickNumDot();
			break;
		case 17:
			ClickNumPlusMinus();
			break;
		case 18:
			ClickCancel();
			break;
		case 19:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		
		switch (data) {
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_NOOFFSET:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_2:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_WEIGHT_OFFSET_SETTING_WORKTOOL_3:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		}
		
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);

		textViewNum1.setPressed(false);
		textViewNum2.setPressed(false);
		textViewNum3.setPressed(false);
		textViewNum4.setPressed(false);
		textViewNum5.setPressed(false);
		textViewNum6.setPressed(false);
		textViewNum7.setPressed(false);
		textViewNum8.setPressed(false);
		textViewNum9.setPressed(false);
		textViewNum0.setPressed(false);
		imgbtnBack.setPressed(false);
		textViewDot.setPressed(false);
		imgbtnPlusMinu.setPressed(false);
		radioNoOffset.setPressed(false);
		radioWorkTool1.setPressed(false);
		radioWorkTool2.setPressed(false);
		radioWorkTool3.setPressed(false);
		
		switch (Index) {
		case 1:
			radioNoOffset.setPressed(true);
			break;
		case 2:
			radioWorkTool1.setPressed(true);
			break;
		case 3:
			radioWorkTool2.setPressed(true);
			break;
		case 4:
			radioWorkTool3.setPressed(true);
			break;
		case 5:
			textViewNum1.setPressed(true);
			break;
		case 6:
			textViewNum2.setPressed(true);
			break;
		case 7:
			textViewNum3.setPressed(true);
			break;
		case 8:
			textViewNum4.setPressed(true);
			break;
		case 9:
			textViewNum5.setPressed(true);
			break;
		case 10:
			textViewNum6.setPressed(true);
			break;
		case 11:
			textViewNum7.setPressed(true);
			break;
		case 12:
			textViewNum8.setPressed(true);
			break;
		case 13:
			textViewNum9.setPressed(true);
			break;
		case 14:
			imgbtnBack.setPressed(true);
			break;
		case 15:
			textViewNum0.setPressed(true);
			break;
		case 16:
			textViewDot.setPressed(true);
			break;
		case 17:
			imgbtnPlusMinu.setPressed(true);
			break;
		case 18:
			imgbtnCancel.setPressed(true);
			break;
		case 19:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
}
