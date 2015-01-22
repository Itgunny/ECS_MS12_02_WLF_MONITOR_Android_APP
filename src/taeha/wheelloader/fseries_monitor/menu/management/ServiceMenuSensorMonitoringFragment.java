package taeha.wheelloader.fseries_monitor.menu.management;

import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;
import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
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
import android.widget.ListView;
import android.widget.TextView;

public class ServiceMenuSensorMonitoringFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int NumofItem = 20;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	
	// ListView
	ListView listView;
	// ListItem
	IconTextListAdapter adapter;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int[] StatusValue;
	int TotalHourmeter;
	boolean BackgroundFlag;
	
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
		 TAG = "ServiceMenuSensorMonitoringFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_service_sensormonitoring, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Sensor_Monitoring));
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
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_sensormonitoring_low_ok);
	
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_management_service_sensormonitoring);
		adapter = new IconTextListAdapter(ParentActivity);
		adapter.clearItem();
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		StatusValue = new int[NumofItem];
		listView.setAdapter(adapter);
		
		BackgroundFlag = true;
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
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
		
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		StatusValue[0] = CAN1Comm.Get_CoolingFanSpeed_318_PGN65369();
		StatusValue[1] = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		StatusValue[2] = CAN1Comm.Get_AlternatorVoltage_707_PGN65360();
		StatusValue[3] = CAN1Comm.Get_BoomLinkAngleSensorSignalVoltage_728_PGN65395();
		StatusValue[4] = CAN1Comm.Get_BellCrankAngleSensorSignalVoltage_729_PGN65395();
//		StatusValue[5] = CAN1Comm.Get_EngineModeSelVolt();	//	(미구현)
//		StatusValue[6] = CAN1Comm.Get_ClutchModeSelVolt();	//	(미구현)
//		StatusValue[7] = CAN1Comm.Get_TMModeSel();			//	(미구현)
		StatusValue[8] = CAN1Comm.Get_BrakePedalPositionVoltage_573_PGN65368();
		StatusValue[9] = CAN1Comm.Get_BrakeOilPressure_503_PGN65354();		
		StatusValue[10] = CAN1Comm.Get_ParkingOilPressure_507_PGN65357();
		StatusValue[11] = CAN1Comm.Get_BoomCylinderHeadPressure_204_PGN65356();
		StatusValue[12] = CAN1Comm.Get_BoomCylinderRodPressure_205_PGN65356();
		StatusValue[13] = CAN1Comm.Get_SteeringMainPumpPressure_202_PGN65357();
		StatusValue[14] = CAN1Comm.Get_EmergencySteeringPumpPressure_203_PGN65357();
		StatusValue[15] = CAN1Comm.Get_DifferentialLockPressure_558_PGN65357();
		StatusValue[16] = CAN1Comm.Get_BrakeOilChargingPriorityPressure_557_PGN65354();
		StatusValue[17] = CAN1Comm.Get_AcceleratorPedalPositionVoltage1_710_PGN65368();
		StatusValue[18] = CAN1Comm.Get_AcceleratorPedalPositionVoltage2_710_PGN65368();
		StatusValue[19] = CAN1Comm.Get_AcceleratorPedalPosition1_339_PGN65368();
		
		TotalHourmeter = CAN1Comm.Get_Hourmeter_1601_PGN65433();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		StatusListDisplay();
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
	}
	/////////////////////////////////////////////////////////////////////
	public int ValueReturn_1(int Value){
		if( Value == 0xffff )
			Value = 0;
		else
		{
			if(Value == 10000)
				Value = 0;
			else if(Value < 10000)
				Value = 0;
			else if(Value > 10000)
				Value = Value - 10000;
		}

		return Value;
	}
	
	public int ValueReturn_2(int Value){
		if( Value == 0xffff )
			Value = 0;
		else
			Value = ( Value / 10 ) * 10;

		return Value;
	}
	
	public int PressureConvert(int pressure, int Unit){
		int tmp = pressure;
		//switch (CAN1Comm.Get_UnitPressure()) {
		switch (Unit) {
		case Home.UNIT_PRESSURE_MPA:
			tmp = (int)(tmp/10);
			break;
		case Home.UNIT_PRESSURE_KGF:
			tmp = (int)((float)tmp * 1.01972);
			break;
		case Home.UNIT_PRESSURE_PSI:
			tmp = (int)((float)tmp * 14.5);
			break;
		}
		return tmp;
	}
	public String GetUnit(int Unit){
		String Result;
		
		//switch (CAN1Comm.Get_UnitPressure()) {
		switch (Unit) {
		case 0:
			Result = ParentActivity.getResources().getString(string.bar);
			break;
		case 1:
			Result = ParentActivity.getResources().getString(string.Mpa);
			break;
		case 2:
			Result = ParentActivity.getResources().getString(string.kgf_cm);
			break;
		case 3:
			Result = ParentActivity.getResources().getString(string.Psi);
			break;

		default:
			Result = ParentActivity.getResources().getString(string.bar);
			break;
		}
		return Result;
	}
	
	
	 
	public void StatusListDisplay(){
		int Value;
		int FanRPM,EngineRPM,AlternatorVolt,BoomPosSensorVolt,BucketPosSensorVolt,EngineModeSelVolt,ClutchModeSelVolt
		,TMModeSelVolt,BrakeFailureWarningPS,ParkingPS,BoomHeadCylinderPS,BommRodCylinderPS,SteeringPumpPS,EmergencyMotorPumpPS
		,DifferentialLockPS,BrakePriorityPS,BrakePedalPosVolt,AccPedalPositionVolt,AccPedalPositionPercent;
		int IntegerValue, Point;
		String str;
		adapter.clearItem();
	
//		for(int i = 0; i < 23; i++){
//			Log.d(TAG,"StatusValue " + Integer.toString(i) + " : "+ Integer.toString(StatusValue[i]));
//		}
		
		adapter.addItem(new IconTextItem( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),ParentActivity.getResources().getString(string.Total_Hourmeter)
				, ParentActivity.GetHourmeterString(TotalHourmeter), ParentActivity.getResources().getString(string.Hr)));
		
		// 1. Fan RPM : rpm
		FanRPM = ValueReturn_1(StatusValue[0]);
		
		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Fan rpm", Integer.toString(FanRPM), "rpm"));
		// 2. Engine RPM : rpm
		//EngineRPM = ValueReturn_2(StatusValue[1]);
		EngineRPM = StatusValue[1];
		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Engine rpm", Integer.toString(EngineRPM), "rpm"));
		BackgroundFlag = false;
		// 3. Alternator Volt. : V
		if(StatusValue[2] != 0xffff){
			AlternatorVolt = StatusValue[2];
			IntegerValue = AlternatorVolt / 10;
			Point = AlternatorVolt % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Alternator Volt.", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Alternator Volt.", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}
			
		}
		
		// 4. Boom Pos. Sensor Volt. : V
		if(StatusValue[3] != 0xff){
			BoomPosSensorVolt = StatusValue[3];
			IntegerValue = BoomPosSensorVolt / 10;
			Point = BoomPosSensorVolt % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Boom Pos. Sensor Volt.", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Boom Pos. Sensor Volt.", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}
			
		}
		
		// 5. Bucket Pos. Sensor Volt. : V
		if(StatusValue[4] != 0xff){
			BucketPosSensorVolt = StatusValue[4];
			IntegerValue = BucketPosSensorVolt / 10;
			Point = BucketPosSensorVolt % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Bucket Pos. Sensor Volt.", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Bucket Pos. Sensor Volt.", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}
		
		}
		
//		// 6. Engine Mode Sel. Sensor Volt. : V
//		if(StatusValue[5] != 0xff){
//			EngineModeSelVolt = StatusValue[5];
//			IntegerValue = EngineModeSelVolt / 10;
//			Point = EngineModeSelVolt % 10;
//			adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable._selector_menu_list_item),ParentActivity.getResources().getDrawable(R.drawable.menu_list_bar_status), "Engine Mode Sel Volt.", Integer.toString(IntegerValue) + "." 
//					+ Integer.toString(Point) + " V", ""));
//		}
//
//		// 7. Clutch Mode Sel. Sensor Volt. : V
//		if(StatusValue[6] != 0xff){
//			ClutchModeSelVolt = StatusValue[6];
//			IntegerValue = ClutchModeSelVolt / 10;
//			Point = ClutchModeSelVolt % 10;
//			adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable._selector_menu_list_item),ParentActivity.getResources().getDrawable(R.drawable.menu_list_bar_status), "Clutch Mode Sel Volt.", Integer.toString(IntegerValue) + "." 
//					+ Integer.toString(Point) + " V", ""));
//		}
//		
//		// 8. T/M Mode Sel. Sensor Volt. : V
//		if(StatusValue[7] != 0xff){
//			TMModeSelVolt = StatusValue[7];
//			IntegerValue = TMModeSelVolt / 10;
//			Point = TMModeSelVolt % 10;
//			adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable._selector_menu_list_item),ParentActivity.getResources().getDrawable(R.drawable.menu_list_bar_status), "T/M Mode Sel Volt.", Integer.toString(IntegerValue) + "." 
//					+ Integer.toString(Point) + " V", ""));
//		}
		
		// 9. Brake Pedal Position Voltage ( ClutchCutOffVolts)
		if(StatusValue[8] != 0xFF){
			float fBrakePedalPosVolt;
			int nBrakePedalVolt;
			int nBrakePedalVolt_Under;
			
			BrakePedalPosVolt = StatusValue[8];
			
			fBrakePedalPosVolt = (float)( BrakePedalPosVolt * 0.025);
			
			BrakePedalPosVolt *= 250;
			nBrakePedalVolt = BrakePedalPosVolt / 10000;
			nBrakePedalVolt_Under = (BrakePedalPosVolt % 10000) / 1000;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
//				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Pedal Pos. Voltage", 
//						Float.toString(fBrakePedalPosVolt), "V"));
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Pedal Pos. Voltage", 
						Integer.toString(nBrakePedalVolt) + "." + Integer.toString(nBrakePedalVolt_Under), "V"));
			}else{
				BackgroundFlag = true;
//				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Pedal Pos. Voltage", 
//						Float.toString(fBrakePedalPosVolt), "V"));
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Pedal Pos. Voltage", 
						Integer.toString(nBrakePedalVolt) + "." + Integer.toString(nBrakePedalVolt_Under), "V"));
			}
			
		}
		
		// 10.Brake Failure Warning PS : bar
		if(StatusValue[9] != 0xffff){
			BrakeFailureWarningPS = PressureConvert(StatusValue[9],ParentActivity.UnitPressure);
			IntegerValue = BrakeFailureWarningPS / 10;
			Point = BrakeFailureWarningPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake failure warning PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake failure warning PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
		
		}
		
		// 11. Parking PS : bar
		if(StatusValue[10] != 0xffff){
			ParkingPS = PressureConvert(StatusValue[10],ParentActivity.UnitPressure);
			IntegerValue = ParkingPS / 10;
			Point = ParkingPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Parking PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point) , GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Parking PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point) , GetUnit(ParentActivity.UnitPressure)));
			}
			
		}
		
		// 12. Boom Head Cylinder PS : bar
		if(StatusValue[11] != 0xffff){
			BoomHeadCylinderPS = PressureConvert(StatusValue[11],ParentActivity.UnitPressure);
			IntegerValue = BoomHeadCylinderPS / 10;
			Point = BoomHeadCylinderPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Boom Head Cylinder PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Boom Head Cylinder PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
			
		}
		
		// 13. Boom Rod Cylinder PS : bar
		if(StatusValue[12] != 0xffff){
			BommRodCylinderPS = PressureConvert(StatusValue[12],ParentActivity.UnitPressure);
			IntegerValue = BommRodCylinderPS / 10;
			Point = BommRodCylinderPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Boom Rod Cylinder PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Boom Rod Cylinder PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
			
		}
		
		// 14. Steering Pump PS : bar
		if(StatusValue[13] != 0xffff){
			SteeringPumpPS = PressureConvert(StatusValue[13],ParentActivity.UnitPressure);
			IntegerValue = SteeringPumpPS / 10;
			Point = SteeringPumpPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Steering Pump PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Steering Pump PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
			
		}
		
		// 15. Emergency Motor Pump PS : bar
		if(StatusValue[14] != 0xffff){
			EmergencyMotorPumpPS = PressureConvert(StatusValue[14],ParentActivity.UnitPressure);
			IntegerValue = EmergencyMotorPumpPS / 10;
			Point = EmergencyMotorPumpPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Emergency Motor Pump PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Emergency Motor Pump PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}
	
		}
		
		// 16. Differential Lock PS : bar
		if(StatusValue[15] != 0xffff){
			DifferentialLockPS = PressureConvert(StatusValue[15],ParentActivity.UnitPressure);
			IntegerValue = DifferentialLockPS / 10;
			Point = DifferentialLockPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Differential Lock PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point),GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Differential Lock PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point),GetUnit(ParentActivity.UnitPressure)));
			}
	
		}
		
		// 17. Brake Priority PS : bar 
		if(StatusValue[16] != 0xffff){
			BrakePriorityPS = PressureConvert(StatusValue[16],ParentActivity.UnitPressure);
			IntegerValue = BrakePriorityPS / 10;
			Point = BrakePriorityPS % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Priority PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Brake Priority PS", 
						Integer.toString(IntegerValue) + "." + Integer.toString(Point), GetUnit(ParentActivity.UnitPressure)));
			}

		}		
		
		// 18. Accelerator Pedal Position Voltage1 : V
		if(StatusValue[17] != 0xFF){
			AccPedalPositionVolt = StatusValue[17];
			IntegerValue = AccPedalPositionVolt / 10;
			Point = AccPedalPositionVolt % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Acc. Pedal Pos. Volt. Ch1", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line), "Acc. Pedal Pos. Volt. Ch1", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}
	
			
		}
		
		// 19. Accelerator Pedal Position Voltage2 : V
		if(StatusValue[18] != 0xFF){
			AccPedalPositionVolt = StatusValue[18];
			IntegerValue = AccPedalPositionVolt / 10;
			Point = AccPedalPositionVolt % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Acc. Pedal Pos. Volt. Ch2", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Acc. Pedal Pos. Volt. Ch2", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "V"));
			}
		
			
		}
		
		// 20. Accelerator Pedal Position Percentage : %
		if(StatusValue[19] != 0xFF){
			AccPedalPositionPercent = StatusValue[19] * 4;
			IntegerValue = AccPedalPositionPercent / 10;
			Point = AccPedalPositionPercent % 10;
			if(BackgroundFlag == true){
				BackgroundFlag = false;
				adapter.addItem(new IconTextItem( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Acc. Pedal Pos. Percent", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "%"));
			}else{
				BackgroundFlag = true;
				adapter.addItem(new IconTextItem( ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line),"Acc. Pedal Pos. Percent", Integer.toString(IntegerValue) + "." 
						+ Integer.toString(Point), "%"));
			}
			
		}
		
		
		
		
		adapter.notifyDataSetChanged();
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){

	}
	public void ClickRight(){	

	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		ClickOK();
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			break;
		
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
