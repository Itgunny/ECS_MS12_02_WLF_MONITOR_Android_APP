package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
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
import android.widget.ProgressBar;
import android.widget.TextView;

public class MaintenanceFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	private static final int ITEMNUMBER							= 15;
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	ImageButton	imgbtnMaintItem[];
	
	TextView	textViewAS;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int TotalNumberofMaintenanceItems;
	int TotalAlramOnItems;
	byte[] MaintenanceItem;
	byte[] MaintennanceAlramOn;
	int SendCommandFlag;
	private Timer mSendCommandTimer = null;

	int CursurIndex;
	
	Handler HandleCursurDisplay;
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
		 TAG = "PressureCalibration";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_maint, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		StartSendCommandTimer();
		
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Maintenance));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		CancelSendCommandTimer();
	}
	////////////////////////////////////////////////
	
	


	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnMaintItem = new ImageButton[ITEMNUMBER];
		
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_maint_low_ok);
		
		imgbtnMaintItem[0] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list1);
		imgbtnMaintItem[1] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list2);
		imgbtnMaintItem[2] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list3);
		imgbtnMaintItem[3] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list4);
		imgbtnMaintItem[4] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list5);
		imgbtnMaintItem[5] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list6);
		imgbtnMaintItem[6] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list7);
		imgbtnMaintItem[7] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list8);
		imgbtnMaintItem[8] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list9);
		imgbtnMaintItem[9] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list10);
		imgbtnMaintItem[10] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list11);
		imgbtnMaintItem[11] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list12);
		imgbtnMaintItem[12] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list13);
		imgbtnMaintItem[13] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list14);
		imgbtnMaintItem[14] =	 (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_list15);
		
		textViewAS = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_low_as);
		
		ASDisplay(ParentActivity.strASNumDash);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
	
		MaintenanceItem = new byte[ITEMNUMBER];
		MaintennanceAlramOn = new byte[ITEMNUMBER];
		
		TotalNumberofMaintenanceItems = CAN1Comm.Get_TotalNumberofMaintenanceItems_1100_PGN61184_13();
		if(TotalNumberofMaintenanceItems > ITEMNUMBER)
			TotalNumberofMaintenanceItems = ITEMNUMBER;

		for(int i  = 0; i < ITEMNUMBER; i++){
			imgbtnMaintItem[i].setVisibility(View.GONE);
			MaintenanceItem[i] = (byte)0xFF;
			MaintennanceAlramOn[i] = (byte)0xFF;
		}
		for(int i  = 0; i < TotalNumberofMaintenanceItems; i++){
			imgbtnMaintItem[i].setVisibility(View.VISIBLE);
		}
		SendCommandFlag = CAN1CommManager.SEND_COMMAND_FLAG_INFO;
		SetThreadSleepTime(1000);		
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
		imgbtnMaintItem[0].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(0);

			}
		});
		imgbtnMaintItem[1].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(1);

			}
		});
		imgbtnMaintItem[2].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(2);

			}
		});
		imgbtnMaintItem[3].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(3);

			}
		});
		imgbtnMaintItem[4].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(4);

			}
		});
		imgbtnMaintItem[5].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(5);

			}
		});
		imgbtnMaintItem[6].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(6);

			}
		});
		imgbtnMaintItem[7].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(7);

			}
		});
		imgbtnMaintItem[8].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(8);

			}
		});
		imgbtnMaintItem[9].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(9);

			}
		});
		imgbtnMaintItem[10].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(10);

			}
		});
		imgbtnMaintItem[11].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(11);

			}
		});
		imgbtnMaintItem[12].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(12);

			}
		});
		imgbtnMaintItem[13].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(13);

			}
		});
		imgbtnMaintItem[14].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ClickMaintDetail(14);

			}
		});
		
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		MaintenanceItem = CAN1Comm.Get_MaintenanceItem_1098_PGN61184_13();
		MaintennanceAlramOn = CAN1Comm.Get_MaintenanceItem_PGN61184_16();
		TotalAlramOnItems = CAN1Comm.Get_TotalNumberofMaintenanceAlarmLampOnItems_1100_PGN61184_16();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		MaintenanceItemDisplay(TotalNumberofMaintenanceItems,MaintenanceItem,TotalAlramOnItems,MaintennanceAlramOn);
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		// ++, 150309 bwk
		//if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_TOP || ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
//			ParentActivity.showMainScreen();
//		// --, 150309 bwk		
//			ParentActivity.OldScreenIndex = 0;
			if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_A){
				ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP;
				ParentActivity._MainBBaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MAIN_B_QUICK_TOP);
			}else{
				ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment);
				ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_TOP;
				ParentActivity._MainABaseFragment.setFirstScreenIndex(Home.SCREEN_STATE_MAIN_A_QUICK_TOP);
			}			
		}
		else{
			ParentActivity._MenuBaseFragment.showBodyManagementAnimation();
		}
		
		
	}
	public void ClickMaintDetail(int _data){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTETNANCE_INFORMATION_REQUEST);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItem[_data]);
		CAN1Comm.TxCANToMCU(12);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_HISTORY_REQUEST);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItem[_data]);
		CAN1Comm.TxCANToMCU(12);
		
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(15);
		
		ParentActivity._MenuBaseFragment.showBodyMaintenanceDetail();
	}
	/////////////////////////////////////////////////////////////////////
	public void MaintenanceItemDisplay(int MaintNumber, byte[] MaintItem, int MaintAlarmNumber, byte[] MaintAlarm){
		for(int i  = 0; i < ITEMNUMBER; i++){
			imgbtnMaintItem[i].setVisibility(View.GONE);
		}
		for(int i = 0; i < MaintNumber; i++){
			imgbtnMaintItem[i].setVisibility(View.VISIBLE);
			if(MaintNumber > ITEMNUMBER)
				break;

			switch (MaintItem[i]) {
			case	CAN1CommManager.ENGINE_OIL	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_01_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_01_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.TRAVEL_REDUCTION_GEAR_CASE	:	
				
				break;
			case	CAN1CommManager.SWING_REDUCTION_GEAR_CASE	:	
				
				break;
			case	CAN1CommManager.HYDRAULIC_OIL	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_05_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_05_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.PILOT_LINE_FILTER_ELEMENT	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_07_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_07_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.HYDRAULIC_TANK_DRAIN_FILTER_CARTRIDGE	:	
				
				break;
			case	CAN1CommManager.HYDRAULIC_OIL_RETURN_FILTER	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_06_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_06_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.ENGINE_OIL_FILTER	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_02_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_02_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.FUEL_FILTER_ELEMENT	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_03_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_03_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.PRE_FILTER	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_04_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_04_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.HYDRAULIC_TANK_AIR_BREATHER_ELEMENT	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_08_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_08_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.AIR_CLEANER_ELEMENT	:	
			
				break;
			case	CAN1CommManager.RADIATOR_COOLANT	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_13_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_13_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.SWING_BEARING_GEAR_PINION_GREASE	:	
				
				break;
			case	CAN1CommManager.TRANSMISSION_OIL	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_09_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_09_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.TRANSMISSION_OIL_FILTER	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_10_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_10_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.FRONT_AXLE_DIFFERENTIAL_GEAR_OIL	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_11_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_11_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.REAR_AXLE_DIFFERENTIAL_GEAR_OIL	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_12_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_12_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.AXLE_PLANETARY_GEAR_OIL	:	
				
				break;
			case	CAN1CommManager.BRAKE_COOLING_OIL	:	
			
				break;
			case	CAN1CommManager.HYDRAULIC_OIL_STRAINER	:	
			
				break;
			case	CAN1CommManager.CRANKCASE_VENTILATION_FILTER	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_14_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_14_2);
						break;
					}
				}
				break;
			case	CAN1CommManager.AIR_CON_HEATER_OUTER_FILTER	:	
				imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_15_1);
				for(int j = 0; j < MaintAlarmNumber; j++){
					if(MaintAlarmNumber > ITEMNUMBER)
						break;
					if(MaintItem[i] == MaintAlarm[j]){
						imgbtnMaintItem[i].setImageResource(R.drawable.menu_management_maintenance_icon_15_2);
						break;
					}
				}
				break;
			default:
				break;
			}
		}
	}
	public void ASDisplay(String str){
		textViewAS.setText(ParentActivity.getResources().getString(string.AS) + " " + str);
	}
	/////////////////////////////////////////////////////////////////////
	public class SendCommandTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					// ++, 150325 bwk
//					if(SendCommandFlag == CAN1CommManager.SEND_COMMAND_FLAG_INFO){
//						CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.COMMAND_MAINTENANCE_ITEM_LIST_REQUEST);
//						CAN1Comm.TxCANToMCU(12);
//						
//						SendCommandFlag = CAN1CommManager.SEND_COMMAND_FLAG_ALRAM;
//					}else{
//						CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_REQUEST);
//						CAN1Comm.TxCANToMCU(12);
//						
//						SendCommandFlag = CAN1CommManager.SEND_COMMAND_FLAG_INFO;
//								
//					}
					CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_ALARM_LAMP_ON_ITEM_LIST_REQUEST);
					CAN1Comm.TxCANToMCU(12);
					
					// --, 150325 bwk
					
				}
				
			});
			
		}
		
	}
	
	public void StartSendCommandTimer(){
		CancelSendCommandTimer();
		mSendCommandTimer = new Timer();
		mSendCommandTimer.schedule(new SendCommandTimerClass(),1,1000);	// ++, --, 150325 bwk 3000->1000	
	}
	
	public void CancelSendCommandTimer(){
		if(mSendCommandTimer != null){
			mSendCommandTimer.cancel();
			mSendCommandTimer.purge();
			mSendCommandTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	public void setCursurIndex(int Index){
		CursurIndex = Index;
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 16;
			CursurDisplay(CursurIndex);
			break;
		case 2:	
		case 3:		
		case 4:	
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
		case 4:	
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 16:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickMaintDetail(0);
			break;
		case 2:
			ClickMaintDetail(1);
			break;
		case 3:
			ClickMaintDetail(2);
			break;
		case 4:
			ClickMaintDetail(3);
			break;
		case 5:
			ClickMaintDetail(4);
			break;
		case 6:
			ClickMaintDetail(5);
			break;
		case 7:
			ClickMaintDetail(6);
			break;
		case 8:
			ClickMaintDetail(7);
			break;
		case 9:
			ClickMaintDetail(8);
			break;
		case 10:
			ClickMaintDetail(9);
			break;
		case 11:
			ClickMaintDetail(10);
			break;
		case 12:
			ClickMaintDetail(11);
			break;
		case 13:
			ClickMaintDetail(12);
			break;
		case 14:
			ClickMaintDetail(13);
			break;
		case 15:
			ClickMaintDetail(14);
			break;
		case 16:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		for(int i = 0; i < 15; i++){
			imgbtnMaintItem[i].setPressed(false);
		}

		switch (CursurIndex) {
			case 1:
				imgbtnMaintItem[0].setPressed(true);
				break;
			case 2:
				imgbtnMaintItem[1].setPressed(true);
				break;
			case 3:
				imgbtnMaintItem[2].setPressed(true);
				break;
			case 4:
				imgbtnMaintItem[3].setPressed(true);
				break;
			case 5:
				imgbtnMaintItem[4].setPressed(true);
				break;
			case 6:
				imgbtnMaintItem[5].setPressed(true);
				break;
			case 7:
				imgbtnMaintItem[6].setPressed(true);
				break;
			case 8:
				imgbtnMaintItem[7].setPressed(true);
				break;
			case 9:
				imgbtnMaintItem[8].setPressed(true);
				break;
			case 10:
				imgbtnMaintItem[9].setPressed(true);
				break;
			case 11:
				imgbtnMaintItem[10].setPressed(true);
				break;
			case 12:
				imgbtnMaintItem[11].setPressed(true);
				break;
			case 13:
				imgbtnMaintItem[12].setPressed(true);
				break;
			case 14:
				imgbtnMaintItem[13].setPressed(true);
				break;
			case 15:
				imgbtnMaintItem[14].setPressed(true);
				break;
			case 16:
				imgbtnOK.setPressed(true);
				break;
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
