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

public class MaintenanceDetailFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextView	textViewAS;
	
	ImageButton	imgbtnLeft;
	ImageButton	imgbtnRight;
	ImageView	imgViewIcon;
	
	TextView	textViewTitle;
	
	TextView	textViewElapsedTime;
	TextView	textViewCycle;
	
	TextView	textViewChangeCount;
	
	TextView	textViewLast;
	TextView[]	textViewHistory;
	
	TextView	textViewReplace;
	TextView	textViewChangeCycle;

	ProgressBar	progressTime;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int MaintenanceItem;
	int AlarmLamp;
	int Interval;
	int TotalCount;
	int Hourmeter;
	
	int	LastHourmeter;
	short[] History;
	byte[] MaintenanceItemList;
	int TotalNumberofMaintenanceItems;
	
	private Timer mSendCommandTimer = null;
	int SendCommandFlag;
	
	int ElapsedTime;
	
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
		 TAG = "MaintenanceDetailFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_maint_detail, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		StartSendCommandTimer();
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MAINTENANCE_DETAIL_TOP;
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
		textViewHistory = new TextView[9];
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_maint_detail_low_ok);

		textViewAS = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_low_as);
		
		imgbtnLeft = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_detail_title_left);
		imgbtnRight = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_maint_detail_title_right);
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_maint_detail_title_icon);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_title);
		
		textViewElapsedTime = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_time_use_data);
		textViewCycle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_time_cycle_data);

		textViewChangeCount = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_count_data);
		
		textViewLast = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_title_data);
		textViewHistory[0] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_1);
		textViewHistory[1] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_2);
		textViewHistory[2] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_3);
		textViewHistory[3] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_4);
		textViewHistory[4] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_5);
		textViewHistory[5] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_6);
		textViewHistory[6] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_7);
		textViewHistory[7] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_8);
		textViewHistory[8] = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_history_9);
		
		textViewReplace = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_button_replace);
		textViewChangeCycle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_maint_detail_button_changecycle);
		
		progressTime = (ProgressBar)mRoot.findViewById(R.id.progressBar_menu_body_management_maint_detail_time);
		progressTime.setMax(100);	
		
		ASDisplay(ParentActivity.strASNumDash);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		MaintenanceItem = 0;
		AlarmLamp = CAN1CommManager.DATA_STATE_LAMP_OFF;
		Interval = 0;
		TotalCount = 0;
		Hourmeter = 0;
		
		LastHourmeter = 0;
		History = new short[9];
		
		TotalNumberofMaintenanceItems = CAN1Comm.Get_TotalNumberofMaintenanceItems_1100_PGN61184_13();
		MaintenanceItemList = new byte[TotalNumberofMaintenanceItems];
		SendCommandFlag = CAN1CommManager.SEND_COMMAND_FLAG_INFO;
		
		CursurIndex = 1;
		Log.d(TAG,"TotalNumberofMaintenanceItems : " +Integer.toString(TotalNumberofMaintenanceItems));
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub

		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnLeft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTitleLeft();
			}
		});
		imgbtnRight.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTitleRight();
			}
		});
		textViewReplace.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickReplace();
			}
		});
		textViewChangeCycle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickChangeCycle();
			}
		});
		
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		MaintenanceItem = CAN1Comm.Get_MaintenanceItem_1098_PGN61184_12();
		MaintenanceItemList = CAN1Comm.Get_MaintenanceItem_1098_PGN61184_13();
		AlarmLamp = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN61184_14();
		Interval = CAN1Comm.Get_MaintenanceInterval_1091_PGN61184_14();
		TotalCount = CAN1Comm.Get_MaintenanceTotalCount_1092_PGN61184_14();
		LastHourmeter = CAN1Comm.Get_HourmeterattheLastMaintenance_1093_PGN61184_14();
		History = CAN1Comm.Get_MaintenanceHistory_1094_PGN61184_15();
		Hourmeter = CAN1Comm.Get_Hourmeter_1601_PGN65433();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		IconDisplay(MaintenanceItem,AlarmLamp);
		ElapsedTime = ElapsedTimeDisplay(LastHourmeter, Hourmeter);
		IntervalDisplay(Interval);
		HistoryDisplay(LastHourmeter,History,TotalCount);
		ProgressDisplay(ElapsedTime,Interval);
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		int CurrentIndex = 0;
		try {
			for(int i = 0; i < TotalNumberofMaintenanceItems; i++){
				if(MaintenanceItem == MaintenanceItemList[i]){
					CurrentIndex = i;
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			Log.e(TAG,"ArrayIndexOutOfBoundsException ClickOK");
		}
		
		if(CurrentIndex > 14){
			Log.e(TAG,"Over Length");
			ParentActivity._MenuBaseFragment._MaintenanceFragment.setCursurIndex(1);
			ParentActivity._MenuBaseFragment.showBodyMaintenance();
			return;
		}
		ParentActivity._MenuBaseFragment._MaintenanceFragment.setCursurIndex(CurrentIndex+1);
		ParentActivity._MenuBaseFragment.showBodyMaintenance();
		
	}
	public void ClickTitleLeft(){
		int CurrentIndex = 0;
		int NextIndex = 0;
		try {
			for(int i = 0; i < TotalNumberofMaintenanceItems; i++){
				if(MaintenanceItem == MaintenanceItemList[i]){
					CurrentIndex = i;
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			Log.e(TAG,"ArrayIndexOutOfBoundsException ClickTitleLeft");
		}
		
		if(CurrentIndex <= 0){
			NextIndex = TotalNumberofMaintenanceItems - 1;
		}else{
			NextIndex = CurrentIndex - 1;
		}
		if(NextIndex > 14){
			Log.e(TAG,"Over Length");
			return;
		}
		Log.d(TAG,"CurrentIndex : " + Integer.toString(CurrentIndex));
		Log.d(TAG,"NextIndex : " + Integer.toString(NextIndex));
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTETNANCE_INFORMATION_REQUEST);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItemList[NextIndex]);
		CAN1Comm.TxCANToMCU(12);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_HISTORY_REQUEST);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItemList[NextIndex]);
		CAN1Comm.TxCANToMCU(12);
		
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(15);
		
		Log.d(TAG,"ClickTitleLeft");
	}
	public void ClickTitleRight(){
		int CurrentIndex = 0;
		int NextIndex = 0;
		try {
			for(int i = 0; i < TotalNumberofMaintenanceItems; i++){
				if(MaintenanceItem == MaintenanceItemList[i]){
					CurrentIndex = i;
					break;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			Log.e(TAG,"ArrayIndexOutOfBoundsException ClickTitleRight");
		}
		
		if(CurrentIndex + 1 >= TotalNumberofMaintenanceItems){
			NextIndex = 0;
		}else{
			NextIndex = CurrentIndex + 1;
		}
		if(NextIndex > 14){
			Log.e(TAG,"Over Length");
			return;
		}
		Log.d(TAG,"CurrentIndex : " + Integer.toString(CurrentIndex));
		Log.d(TAG,"NextIndex : " + Integer.toString(NextIndex));
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTETNANCE_INFORMATION_REQUEST);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItemList[NextIndex]);
		CAN1Comm.TxCANToMCU(12);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_HISTORY_REQUEST);
		CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItemList[NextIndex]);
		CAN1Comm.TxCANToMCU(12);
		
		CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(15);
		Log.d(TAG,"ClickTitleRight");
	}
	public void ClickReplace(){
		ParentActivity.showMaintReplace();
	}
	public void ClickChangeCycle(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MenuBaseFragment.showBodyMaintenanceChangeCycle();
	}
	/////////////////////////////////////////////////////////////////////

	public void ASDisplay(String str){
		textViewAS.setText(ParentActivity.getResources().getString(string.AS) + " " + str);
	}
	public void ProgressDisplay(int _elapsedtime, int _cycle){
		int _progress;
		float fCycle;
		fCycle = _cycle * 50;
		if(_cycle == 0)
			_progress = 100;
		else
			_progress = (int)((float)(_elapsedtime / fCycle) * 100);
		
		if(_progress > 100)
			_progress = 100;
		progressTime.setProgress(_progress);
		
		
	}
	public void IconDisplay(int MaintItem, int Alarm){
		switch (MaintItem) {
		case	CAN1CommManager.ENGINE_OIL	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_01_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_01_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Engine_oil));
			break;
		case	CAN1CommManager.TRAVEL_REDUCTION_GEAR_CASE	:	
			
			break;
		case	CAN1CommManager.SWING_REDUCTION_GEAR_CASE	:	
			
			break;
		case	CAN1CommManager.HYDRAULIC_OIL	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_05_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_05_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Hydraulic_oil));
			break;
		case	CAN1CommManager.PILOT_LINE_FILTER_ELEMENT	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_07_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_07_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Pilot_line_filter_element));
			break;
		case	CAN1CommManager.HYDRAULIC_TANK_DRAIN_FILTER_CARTRIDGE	:	
			
			break;
		case	CAN1CommManager.HYDRAULIC_OIL_RETURN_FILTER	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_06_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_06_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Hydraulic_oil_return_filter));
			break;
		case	CAN1CommManager.ENGINE_OIL_FILTER	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_02_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_02_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Engine_oil_filter));
			break;
		case	CAN1CommManager.FUEL_FILTER_ELEMENT	:	
			
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_03_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_03_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Fuel_filter_element));
			break;
		case	CAN1CommManager.PRE_FILTER	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_04_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_04_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Fuel_Pre_filter));
			break;
		case	CAN1CommManager.HYDRAULIC_TANK_AIR_BREATHER_ELEMENT	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_08_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_08_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Hydraulic_Tank_Drain_Filter_Cartridge));
			break;
		case	CAN1CommManager.AIR_CLEANER_ELEMENT	:	
		
			break;
		case	CAN1CommManager.RADIATOR_COOLANT	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_13_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_13_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Radiator_engine_coolant));
			break;
		case	CAN1CommManager.SWING_BEARING_GEAR_PINION_GREASE	:	
			
			break;
		case	CAN1CommManager.TRANSMISSION_OIL	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_09_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_09_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Transmission_oil));
			break;
		case	CAN1CommManager.TRANSMISSION_OIL_FILTER	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_10_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_10_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Transmission_oil_filter));
			break;
		case	CAN1CommManager.FRONT_AXLE_DIFFERENTIAL_GEAR_OIL	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_11_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_11_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Front_axle_oil));
			break;
		case	CAN1CommManager.REAR_AXLE_DIFFERENTIAL_GEAR_OIL	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_12_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_12_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Rear_axle_oil));
			break;
		case	CAN1CommManager.AXLE_PLANETARY_GEAR_OIL	:	
			
			break;
		case	CAN1CommManager.BRAKE_COOLING_OIL	:	
		
			break;
		case	CAN1CommManager.HYDRAULIC_OIL_STRAINER	:	
		
			break;
		case	CAN1CommManager.CRANKCASE_VENTILATION_FILTER	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_14_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_14_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Crankcase_Ventilation_Filter));
			break;
		case	CAN1CommManager.AIR_CON_HEATER_OUTER_FILTER	:	
			if(Alarm == CAN1CommManager.DATA_STATE_LAMP_ON){
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_15_2_small);
			}else{
				imgViewIcon.setImageResource(R.drawable.menu_management_maintenance_icon_15_small);
			}
			textViewTitle.setText(ParentActivity.getResources().getString(string.Aircon_Heater_Outer_Filter));
			break;
		default:
			break;
		}
	}
	public int ElapsedTimeDisplay(int _LastHourmeter, int _Hourmeter){
		int hour;
		int _ElapsedTime;
		

		hour = _Hourmeter/3600;
		
		_ElapsedTime = hour - _LastHourmeter;
		
		if(_LastHourmeter > 64255)
			textViewElapsedTime.setText("");
		else
			textViewElapsedTime.setText(Integer.toString(_ElapsedTime));
		
		return _ElapsedTime;
	}

	public void IntervalDisplay(int Time){
		if(Time > 250){
			textViewCycle.setText("");
		}else{
			textViewCycle.setText(Integer.toString(Time*50));
		}
		
	}
	public void HistoryDisplay(int _LastHourmeter, short[] Log, int Total){
		if(Total > 250)
			textViewChangeCount.setText("");
		else
			textViewChangeCount.setText(Integer.toString(Total));
		if(Total == 0){
			textViewLast.setText("");
			for(int i = 0; i < 9; i++){
				textViewHistory[i].setText("");
			}
		}else{
			if(_LastHourmeter > 64255)
				textViewLast.setText("");
			else
				textViewLast.setText(Integer.toString(_LastHourmeter));
			for(int i = 0; i < 9; i++){
				if(i >= Total){
					textViewHistory[i].setText("");
				}else{
					if(Total > 250)
						textViewHistory[i].setText("");
					else
						textViewHistory[i].setText(Integer.toString(Log[i]));
				}	
			}
		}
		
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
					if(SendCommandFlag == CAN1CommManager.SEND_COMMAND_FLAG_INFO){
						CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTETNANCE_INFORMATION_REQUEST);
						CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItem);
						CAN1Comm.TxCANToMCU(12);
						
						SendCommandFlag = CAN1CommManager.SEND_COMMAND_FLAG_HISTORY;
					}else{
						CAN1Comm.Set_MaintenanceCommant_1097_PGN61184_12(CAN1CommManager.MAINTENANCE_HISTORY_REQUEST);
						CAN1Comm.Set_MaintenanceItem_1098_PGN61184_12(MaintenanceItem);
						CAN1Comm.TxCANToMCU(12);
						
						SendCommandFlag = CAN1CommManager.SEND_COMMAND_FLAG_INFO;
								
					}
					
				}
				
			});
			
		}
		
	}
	
	public void StartSendCommandTimer(){
		CancelSendCommandTimer();
		mSendCommandTimer = new Timer();
		mSendCommandTimer.schedule(new SendCommandTimerClass(),1000,3000);	
	}
	
	public void CancelSendCommandTimer(){
		if(mSendCommandTimer != null){
			mSendCommandTimer.cancel();
			mSendCommandTimer.purge();
			mSendCommandTimer = null;
		}
		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 2:	
		case 3:		
		case 4:	
		case 5:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
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
			ClickTitleLeft();
			break;
		case 2:
			ClickTitleRight();
			break;
		case 3:
			ClickReplace();
			break;
		case 4:
			ClickChangeCycle();
			break;
		case 5:
			ClickOK();
			break;
		
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnLeft.setPressed(false);
		imgbtnRight.setPressed(false);
		textViewReplace.setPressed(false);
		textViewChangeCycle.setPressed(false);
		

		switch (CursurIndex) {
			case 1:
				imgbtnLeft.setPressed(true);
				break;
			case 2:
				imgbtnRight.setPressed(true);
				break;
			case 3:
				textViewReplace.setPressed(true);
				break;
			case 4:
				textViewChangeCycle.setPressed(true);
				break;
			case 5:
				imgbtnOK.setPressed(true);
				break;
			
			default:
				break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
