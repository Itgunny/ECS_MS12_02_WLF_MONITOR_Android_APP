package taeha.wheelloader.fseries_monitor.menu.monitoring;

import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;
import customlist_fault.IconTextItemFault;
import customlist_fault.IconTextListAdapterFault;
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
import android.R.color;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FaultHistoryLoggedFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
//	private static final int REQ_ERR_MACHINE_ACTIVE		= 0;
	private static final int REQ_ERR_MACHINE_LOGGED		= 1;
	
//	private static final int REQ_ERR_ENGINE_ACTIVE		= 2;
	private static final int REQ_ERR_ENGINE_LOGGED		= 3;
	
//	private static final int REQ_ERR_TM_ACTIVE			= 4;
	private static final int REQ_ERR_TM_LOGGED			= 5;
	
//	private static final int REQ_ERR_EHCU_ACTIVE		= 6;
	private static final int REQ_ERR_EHCU_LOGGED		= 7;
	
	private static final int EHCU_SPN = 0;
	private static final int EHCU_FMI = 1;
	private static final int EHCU_LENTGH = 84;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	// ListView
	ListView listView;
	// ListItem
	IconTextListAdapterFault adapter;
	
	TextView textViewAS;
	TextView textViewTitle;
	TextView textViewDetailTitle;
	TextView textViewDetail;
	
	RadioButton radioMachine;
	RadioButton radioEngine;
	RadioButton radioTransmission;
	RadioButton radioEHCU;
	
	RelativeLayout layoutDetail;
	
	TextView textViewDelete;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectedMode;
	
	int DTCTotalPacketMachine;
	int DTCTotalPacketEngine;
	int DTCTotalPacketTM;
	int DTCTotalPacketEHCU;
	int DTCTotalMachine;
	int DTCTotalEngine;
	int DTCTotalTM;
	int DTCTotalEHCU;
	
	int SendDTCIndex;
	int SendSeqIndex;
	
	int[] Err_Tcu;
	int[] Err_Mcu;
	int[] Err_Ecu;
	int[] Err_EHCU;	
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
		 TAG = "FaultHistoryActiveFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_fault_logged, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ClickMachine();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Logged_Fault));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fault_logged_low_ok);
		
		textViewAS = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_logged_low_as);
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_logged_list_title);
		textViewDetailTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_logged_detail_title);
		textViewDetail = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_logged_detail_data);
		
		radioMachine = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_machine);
		radioEngine = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_engine);
		radioTransmission = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_transmission);
		radioEHCU = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_ehcu);
		
		layoutDetail = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_fault_logged_detail);
		
		textViewDelete = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_logged_list_delete);

		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_monitoring_fault_logged_list);
		adapter = new IconTextListAdapterFault(ParentActivity);
		adapter.clearItem();
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		SendDTCIndex = REQ_ERR_MACHINE_LOGGED;
		SendSeqIndex = 1;
		
		Err_Tcu = new int[400];
		Err_Mcu = new int[400];
		Err_Ecu = new int[400];
		Err_EHCU = new int[400];
		
		
		listView.setAdapter(adapter);
		
		adapter.notifyDataSetChanged();
		
		ASDisplay(ParentActivity.strASNumDash);
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
		radioMachine.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMachine();
			}
		});
		radioEngine.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEngine();
			}
		});
		radioTransmission.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTransmission();
			}
		});
		radioEHCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEHCU();
			}
		});
		textViewDetailTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDetailTitle();
			}
		});
		textViewDelete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDelete();
			}
		});
		AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				ErrDetailDisplay(arg2);
				
			}
		};
		listView.setOnItemClickListener(mItemClickListener);
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ReqestErrorCode();
		DTCTotalPacketMachine = CAN1Comm.Get_gErr_Mcu_TotalPacket_Logged();
		DTCTotalPacketEngine = CAN1Comm.Get_gErr_Ecu_TotalPacket_Logged();
		DTCTotalPacketTM = CAN1Comm.Get_gErr_Tcu_TotalPacket_Logged();
		DTCTotalPacketEHCU = CAN1Comm.Get_gErr_EHCU_TotalPacket_Logged();
		
		DTCTotalMachine = CAN1Comm.Get_gErr_Mcu_Total_Logged();
		DTCTotalEngine = CAN1Comm.Get_gErr_Ecu_Total_Logged();
		DTCTotalTM = CAN1Comm.Get_gErr_Tcu_Total_Logged();
		DTCTotalEHCU = CAN1Comm.Get_gErr_EHCU_Total_Logged();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ErrListDisplay();
		ErrorNumberDisplay();
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showFaultHistoryAnimation();
	}
	public void ClickMachine(){
		TitleDisplay(ParentActivity.getResources().getString(string.Machine));
		radioMachine.setChecked(true);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_MACHINE_LOGGED;
	}
	public void ClickEngine(){
		TitleDisplay(ParentActivity.getResources().getString(string.Engine));
		radioMachine.setChecked(false);
		radioEngine.setChecked(true);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_ENGINE_LOGGED;
	}
	public void ClickTransmission(){
		TitleDisplay(ParentActivity.getResources().getString(string.Transmission));
		radioMachine.setChecked(false);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(true);
		radioEHCU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_TM_LOGGED;
	}
	public void ClickEHCU(){
		TitleDisplay(ParentActivity.getResources().getString(string.EHCU));
		radioMachine.setChecked(false);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(true);
		DetailEnable(false);
		SelectedMode = REQ_ERR_EHCU_LOGGED;
	}
	public void ClickDetailTitle(){
		DetailEnable(false);
	}
	public void ClickDelete(){
		ParentActivity._LoggedFaultDeletePopup.setMode(SelectedMode);
		ParentActivity.showLoggedFaultDelete();
	}
	/////////////////////////////////////////////////////////////////////
	public void ASDisplay(String str){
		textViewAS.setText(ParentActivity.getResources().getString(string.AS) + " " + str);
	}
	public void TitleDisplay(String str){
		textViewTitle.setText(str);
	}
	public void ErrorNumberDisplay(){
		radioMachine.setText(ParentActivity.getResources().getString(string.Machine) + "(" + Integer.toString(DTCTotalMachine) + ")");
		radioEngine.setText(ParentActivity.getResources().getString(string.Engine) + "(" + Integer.toString(DTCTotalEngine) + ")");
		radioTransmission.setText(ParentActivity.getResources().getString(string.Transmission) + "(" + Integer.toString(DTCTotalTM) + ")");
		radioEHCU.setText(ParentActivity.getResources().getString(string.EHCU) + "(" + Integer.toString(DTCTotalEHCU) + ")");
	}
	public void ErrListDisplay(){
		switch (SelectedMode) {
		case REQ_ERR_MACHINE_LOGGED:
			SetErrList(DTCTotalMachine, SelectedMode);
			break;
		case REQ_ERR_ENGINE_LOGGED:
			SetErrList(DTCTotalEngine, SelectedMode);
			break;
		case REQ_ERR_TM_LOGGED:
			SetErrList(DTCTotalTM, SelectedMode);
			break;
		case REQ_ERR_EHCU_LOGGED:
			SetErrList(DTCTotalEHCU, SelectedMode);
			break;
			
		default:
			break;
		}
	}
	public void SetErrList(int NumofErr, int Mode){
		int SPN = 0;
		int FMI = 0;

		adapter.clearItem();
		if(NumofErr > 250){
			NumofErr = 250;
		}
		
		else
		{
			for(int i = 0; i < NumofErr; i++){
				if(Mode == REQ_ERR_MACHINE_LOGGED)		// MCU 
				{
					//Log.d(TAG,"ReqMode : " + Integer.toString(ReqMode));
					Err_Mcu = CAN1Comm.Get_McuErr_Logged();
					
					SPN = Err_Mcu[i] & 0xffff;
					SPN |= ((Err_Mcu[i] & 0xe00000) >> 5);
					FMI = ((Err_Mcu[i] & 0x1f0000) >> 16); 
					adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "HCESPN : " + Integer.toString(SPN)
							+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
				
				//	Log.d(TAG,"SPN : " + Integer.toString(SPN) + "     FMI : " + Integer.toString(FMI));
				}
				else if(Mode == REQ_ERR_ENGINE_LOGGED)	// ECU	
				{
					//Log.d(TAG,"ReqMode : " + Integer.toString(ReqMode));
					Err_Ecu = CAN1Comm.Get_EcuErr_Logged();
					
					SPN = Err_Ecu[i] & 0xffff;
					SPN |= ((Err_Ecu[i] & 0xe00000) >> 5);
					FMI = ((Err_Ecu[i] & 0x1f0000) >> 16); 
					adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "SPN : " + Integer.toString(SPN)
							+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
				}
				else if(Mode == REQ_ERR_TM_LOGGED)		// TCU
				{
					Err_Tcu = CAN1Comm.Get_TcuErr_Logged();
					adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "No : " + Integer.toHexString(Err_Tcu[i]), "", ""));
				}
				else if(Mode == REQ_ERR_EHCU_LOGGED)		// EHCU
				{
					Err_EHCU = CAN1Comm.Get_EHCUErr_Logged();
					
					SPN = Err_EHCU[i] & 0x0000FFFF;
					FMI = ((Err_EHCU[i] & 0x00FF0000) >> 16);

					adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "SPN : " + Integer.toString(SPN)
							+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
				}
			}
		}
		adapter.notifyDataSetChanged();
	}
	public void ErrDetailDisplay(int Index){
		DetailEnable(true);
		int SPN = 0;
		int FMI = 0;
		String str = adapter.getFirst(Index);
		textViewDetailTitle.setText(str);
		//Log.d(TAG,"ErrDetailDisplay");
		textViewDetail.setText("Not define");
		if(SelectedMode == REQ_ERR_MACHINE_LOGGED)
		{
			SPN = Err_Mcu[Index] & 0xFFFF;
			SPN |= ((Err_Mcu[Index] & 0xe00000) >> 5);
			FMI = ((Err_Mcu[Index] & 0x1f0000)>>16);
			for(int i = 0; i < SPNDATA.length; i++){
				if(SPN == SPNDATA[i] && FMI == FMIDATA[i])
					textViewDetail.setText(textErrorCode[i]);

			}
		}
		else if(SelectedMode == REQ_ERR_EHCU_LOGGED)
		{
			//Log.d(TAG,"ErrDetailDisplay Err_EHCU[Index] : " + Integer.toHexString(Err_EHCU[Index]));
			int TempSPN;
			int TempFMI;
			TempSPN = Err_EHCU[Index] & 0x0000FFFF;
			TempFMI = ((Err_EHCU[Index] & 0x00FF0000) >> 16);
			for(int i = 0; i < EHCU_LENTGH; i++){
				if(TempSPN == EHCU_SPNFMI[i][EHCU_SPN] && TempFMI == EHCU_SPNFMI[i][EHCU_FMI])
				{
					//Log.d(TAG,"ErrDetailDisplay i : " + Integer.toHexString(i));
					textViewDetail.setText(textEHCUErrCode[i]);
					break;
				}
				else{
					textViewDetail.setText(textEHCUErrCode[EHCU_LENTGH]);
				}
			}
		}

		else
		{
			textViewDetail.setText(ParentActivity.getResources().getString(string.Please_refer_to_machine_manual_for_more_detailed_description));
		}

	}
	public void DetailEnable(boolean flag){
		if(flag == true){
			layoutDetail.setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
		}else{
			layoutDetail.setVisibility(View.INVISIBLE);
			listView.setVisibility(View.VISIBLE);
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void RequestErrorCode(int Err, int Req, int SeqNo){
		CAN1Comm.Set_DTCInformationRequest_1515_PGN61184_11(Req);
		CAN1Comm.Set_DTCType_1510_PGN61184_11(Err);
		CAN1Comm.Set_SeqenceNumberofDTCInformationPacket_1513_PGN61184_11(SeqNo);
		CAN1Comm.TxCANToMCU(11);
	}
	public void ReqestErrorCode(){
		switch (SendDTCIndex) {
		case REQ_ERR_MACHINE_LOGGED:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
			else if(SendSeqIndex > DTCTotalPacketMachine){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_ENGINE_LOGGED;
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
				
			break;
		case REQ_ERR_ENGINE_LOGGED:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
			else if(SendSeqIndex > DTCTotalPacketEngine){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_TM_LOGGED;
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
				
			break;
		case REQ_ERR_TM_LOGGED:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
			else if(SendSeqIndex > DTCTotalPacketTM){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_EHCU_LOGGED;
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
				
			break;
		case REQ_ERR_EHCU_LOGGED:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
			else if(SendSeqIndex > DTCTotalPacketTM){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_MACHINE_LOGGED;
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
				
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	static final int EHCU_SPNFMI[][] = {{842,9},{844,9},{978,9},{2316,9},{2317,9},{2319,2},{2320,2},{2321,2},{2322,2},{2323,2},{2324,2},
		{2325,2},{2326,5},{2326,6},{2327,0},{2327,1},{2327,5},{2327,6},{2327,14},{2311,2},{2311,0},{2311,1},{2311,3},{2311,4},{2311,13},
		{2311,14},{2311,31},{2313,2},{2313,0},{2313,1},{2313,3},{2313,4},{2313,13},{2313,14},{2313,31},{2315,2},{2315,0},{2315,1},
		{2315,3},{2315,4},{2315,13},{2315,14},{2315,31},{2304,0},{2304,1},{2304,5},{2304,6},{2304,14},{2305,0},{2305,1},{2305,5},{2305,6},
		{2305,14},{2306,0},{2306,1},{2306,5},{2306,6},{2306,14},{2307,0},{2307,1},{2307,5},{2307,6},{2307,14},{2308,0},{2308,1},{2308,5},
		{2308,6},{2308,14},{2309,0},{2309,1},{2309,5},{2309,6},{2309,14},{2328,0},{2328,1},{2328,3},{2328,4},{2329,0},{2329,1},
		{738,2},{751,2},{2330,2},{172,2},{174,2}};
	static final int SPNDATA[] = {101,101,145,145,172,172,173,173,174,174,181,181,183,183,187,187,202,202,202,202		
		,203,203,203,203,204,204,204,204,205,205,205,205,301,301,304,304,310,318,322,322,325,325,327,327,346		
		,346,503,503,503,503,507,507,507,507,551,551,552,552,558,558,558,558,701,705,705,707,723,723,727,727		
		,728,728,729,729,730,830,840,841,842,843,844,850,869};
	static final int FMIDATA[] = {3, 4,	5,	6,	4,	6,	4,	6,	4,	6,	4,	6,	4,	6,	4,	6,	0,	1,	2,	4,	0,	1,	2,	4,	0,	
		1,	2,	4,	0,	1,	2,	4,	3,	4,	3,	4,	8,	8,	4,	6,	4,	6,	4,	6,	3,	4,	0,	1,	2,	4,	0,	1,	2,	4,	3,	4,	3,	
		4,	0,	1,	2,	4,	4,	0,	1,	1,	3,	4,	4,	6,	3,	4,	3,	4,	19,	12,	2,	2,	2,	2,	2,	2, 2};
	static final String	textErrorCode[] = 
		{
		"Hydraulic Oil Temperature Sensor Circuit Voltage Above Normal, or Shorted to High Source(or Open Circuit)",
		"Hydraulic Oil Temperature Sensor Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Engine Cooling Fan EPPR Valve Circuit Current Below Normal, or Open Circuit ",
		"Engine Cooling Fan EPPR Valve Circuit Current Above Norma", 
		"Boom Up Lever Detent Solenoid Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)", 
		"Boom Up Lever Detent Solenoid Circuit Current Above Norma", 
		"Boom Down Lever Detent Solenoid Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)", 
		"Boom Down Lever Detent Solenoid Circuit Current Above Norma",
		"Bucket Lever Detent Solenoid Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)", 
		"Bucket Lever Detent Solenoid Circuit Current Above Norma", 
		"Engine Cooling Fan Reverse Solenoid Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)",
		"Engine Cooling Fan Reverse Solenoid Circuit Current Above Norma", 
		"Engine Cooling Fan Reverse Driving Status Signal Circuit - Voltage Below Normal, or Shorted to Low Source(or Open Circuit)", 
		"Engine Cooling Fan Reverse Driving Status Signal Circuit - Current Above Norma", 
		"Emergency Steering Pump Relay Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)",
		"Emergency Steering Pump Relay Circuit Current Above Norma", 
		"Steering Main Pump Pressure Sensor Data Above Normal Range(or Open Circuit)",
		"Steering Main Pump Pressure Sensor Data Below Normal Range", 
		"Steering Main Pump Pressure Sensor Data Error",  
		"Steering Main Pump Pressure Sensor Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Emergency Steering Pump Pressure Sensor Data Above Normal Range (or Open Circuit)", 
		"Emergency Steering Pump Pressure Sensor Data Below Normal Range",
		"Emergency Steering Pump Pressure Sensor Data Error", 
		"Emergency Steering Pump Pressure Sensor Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Boom Cylinder Pressure Sensor Data Above Normal Range(or Open Circuit)", 
		"Boom Cylinder Pressure Sensor Data Below Normal Range", 
		"Boom Cylinder Pressure Sensor Data Error",
		"Boom Cylinder Pressure Sensor Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Bucket Cylinder Pressure Sensor Data Above Normal Range(or Open Circuit)", 
		"Bucket Cylinder Pressure Sensor Data Below Normal Range", 
		"Bucket Cylinder Pressure Sensor Data Error", 
		"Bucket Cylinder Pressure Sensor Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Fuel Level Sensor Circuit - Voltage Above Normal, or Shorted to High Source(or Open Circuit)", 
		"Fuel Level Sensor Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Engine Coolant Temperature Sensor Circuit - Voltage Above Normal, or Shorted to High Source(or Open Circuit)",
		"Engine Coolant Temperature Sensor Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Engine Speed Signal Error Abnormal Frequency or Pulse Width", 
		"Engine Cooling Fan Speed Signal Error Abnormal Frequency or Pulse Width",
		"Engine Preheat Relay Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)",
		"Engine Preheat Relay Circuit Current Above Norma", 
		"Fuel Warmer Relay Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)",
		"Fuel Warmer Relay Circuit Current Above Norma", 
		"Anti-Restart Relay Circuit Voltage Below Normal, or Shorted to Low Source(or Open Circuit)",
		"Anti-Restart Relay Circuit Current Above Norma", 
		"Engine Power Mode Selector Circuit Voltage Above Normal, or Shorted to Low Source(or Open Circuit)", 
		"Engine Power Mode Selector Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Brake Oil Pressure Sensor Data Above Normal Range (or Open Circuit)", 
		"Brake Oil Pressure Sensor Data Below Normal Range", 
		"Brake Oil Pressure Sensor Data Error", 
		"Brake Oil Pressure Sensor Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Parking Oil Pressure Sensor Data Above Normal Range(or Open Circuit)", 
		"Parking Oil Pressure Sensor Data Below Normal Range", 
		"Parking Oil Pressure Sensor Data Error", 
		"Parking Oil Pressure Sensor Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Clutch Cutoff Mode Selector Circuit Voltage Above Normal, or Shorted to High Source(or Open Circuit)",
		"Clutch Cutoff Mode Selector Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Transmission Shift Mode Selector Circuit Voltage Above Normal, or Shorted to High Source(or Open Circuit)", 
		"Transmission Shift Mode Selector Circuit Voltage Below Normal, or Shorted to Low Source",
		"Differential Lock Pressure Sensor Data Above Normal Range (or Open Circuit)",
		"Differential Lock Pressure Sensor Data Below Normal Range", 
		"Differential Lock Pressure Sensor Data Error", 
		"Differential Lock Pressure Sensor Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Hourmeter Circuit - Voltage Below Normal, or Shorted to Low Source", 
		"Battery Voltage High", 
		"Battery Voltage Low", 
		"Alternator Node I Voltage Low(or Open Circuit)", 
		"Buzzer Circuit - Voltage Above Normal, or Shorted to High Source", 
		"Buzzer Circuit - Voltage Below Normal, or Shorted to Low Source (or Open Circuit)", 
		"Wiper Relay Circuit - Voltage Below Normal, or Shorted to Low Source (or Open Circuit)",
		"Wiper Relay Circuit - Current Above Norma", 
		"Boom Position Sensor Signal Circuit Voltage Above Normal, or Shorted to High Source(or Open Circuit)", 
		"Boom Position Sensor Signal Circuit Voltage Below Normal, or Shorted to Low Source", 
		"Bucket Position Sensor Signal Circuit Voltage Above Normal, or Shorted to High Source(or Open Circuit)", 
		"Bucket Position Sensor Signal Circuit Voltage Below Normal, or Shorted to Low Source", 
		"ATPC Heater PWM Output Duty Operation Error", 
		"MCU Internal Memory Error",
		"Cluster Communication Error", 
		"ECM Communication Error",
		"TCU Communication Error", 
		"APTC Communication Error", 
		"Monitor Communication Error", 
		"RCM Communication Error", 
		"BKCU Communication error",
		"Not define."	
	};

	static final String textEHCUErrCode[] = 
	{
		"TCU CAN Timeout",
		"Monitor CAN Timeout",
		"MCU CAN Timeout",
		"Working Joystick CAN Timeout",
		"Steering Joystick CAN Timeout",
		"Steering Joystick Position Signal Error",
		"Steering Joystick - FNR Enable Switch Error",
		"Steering Joystick - Foward Switch Error",
		"Steering Joystick - Neutral Switch Error",
		"Steering Joystick - Reverse Switch Error",
		"Steering Joystick - Kick Down Switch Error",
		"Steering Joystick - Steering On Switch Error",
		"PVE Coil Power Current Below Normal or Open Circuit",
		"PVE Coil Power Current Above Normal or Grounded Circuit",
		"PVE Coil PWM Duty Cycle Input Value Above Normal Operation Range",
		"PVE Coil PWM Duty Cycle Input Value Below Normal Operation Range",
		"PVE Coil PWM Duty Cycle Current Below Normal or Open Circuit",
		"PVE Coil PWM Duty Cycle Current Above Normal or Grounded Circuit",
		"PVE Coil PWM Duty Cycle Control Block Parameter Invalid",
		"Boom Joystick Position Signal Error",
		"Boom Joystick Position Input Value Above Normal Operation Range",
		"Boom Joystick Position Input Value Below Normal Operation Range",
		"Boom Joystick Position Input Voltage Above Normal or Shorted to High Source",
		"Boom Joystick Position Input Voltage Below Normal or Shorted to Low Source",
		"Boom Joystick Position Control Block Out of Calibration",
		"Boom Joystick Position Control Block Parameter Invalid",
		"Boom Joysitck Position Signal Redundancy Lost",
		"Bucket Joystick Position Signal Error",
		"Bucket Joystick Position Input Value Above Normal Operation Range",
		"Bucket Joystick Position Input Value Below Normal Operation Range",
		"Bucket Joystick Position Input Voltage Above Normal or Shorted to High Source",
		"Bucket Joystick Position Input Voltage Below Normal or Shorted to Low Source",
		"Bucket Joystick Position Control Block Out of Calibration",
		"Bucket Joystick Position Control Block Parameter Invalid",
		"Bucket Joysitck Position Signal Redundancy Lost",
		"Aux Joystick Position Signal Error",
		"Aux Joystick Position Input Value Above Normal Operation Range",
		"Aux Joystick Position Input Value Below Normal Operation Range",
		"Aux Joystick Position Input Voltage Above Normal or Shorted to High Source",
		"Aux Joystick Position Input Voltage Below Normal or Shorted to Low Source",
		"Aux Joystick Position Control Block Out of Calibration",
		"Aux Joystick Position Control Block Parameter Invalid",
		"Aux Joysitck Position Signal Redundancy Lost",
		"Boom Up EPPR Valve Input Value Above Normal Operation Range",
		"Boom Up EPPR Valve Input Value Below Normal Operation Range",
		"Boom Up EPPR Valve Input Current Below Normal or Open Circuit",
		"Boom Up EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Boom Up EPPR Valve Block Parameter Invalid",
		"Boom Down EPPR Valve Input Value Above Normal Operation Range",
		"Boom Down EPPR Valve Input Value Below Normal Operation Range",
		"Boom Down EPPR Valve Input Current Below Normal or Open Circuit",
		"Boom Down EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Boom Down EPPR Valve Block Parameter Invalid",
		"Bucket In EPPR Valve Input Value Above Normal Operation Range",
		"Bucket In EPPR Valve Input Value Below Normal Operation Range",
		"Bucket In EPPR Valve Input Current Below Normal or Open Circuit",
		"Bucket In EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Bucket In EPPR Valve Block Parameter Invalid",
		"Bucket Out EPPR Valve Input Value Above Normal Operation Range",
		"Bucket Out EPPR Valve Input Value Below Normal Operation Range",
		"Bucket Out EPPR Valve Input Current Below Normal or Open Circuit",
		"Bucket Out EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Bucket Out EPPR Valve Block Parameter Invalid",
		"Aux. 1 EPPR Valve Input Value Above Normal Operation Range",
		"Aux. 1 EPPR Valve Input Value Below Normal Operation Range",
		"Aux. 1 EPPR Valve Input Current Below Normal or Open Circuit",
		"Aux. 1 EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Aux. 1 EPPR Valve Block Parameter Invalid",
		"Aux. Out EPPR Valve Input Data Above Normal Operation Range",
		"Aux. Out EPPR Valve Input Data Below Normal Operation Range",
		"Aux. Out EPPR Valve Input Current Below Normal or Open Circuit",
		"Aux. Out EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Aux. Out EPPR Valve Block Parameter Invalid",
		"EHCU Sensor Power Voltage High",
		"EHCU Sensor Power Voltage Low",
		"EHCU Sensor Power Voltage Above Normal or Shorted to High Source",
		"EHCU Sensor Power Voltage Below Normal or Shorted to Low Source",
		"EHCU Power Voltage High",
		"EHCU Power Voltage Low",
		"Seat Switch Status Error",
		"Armrest Switch Signal Error",
		"Working Joystick Type Select Switch Error",
		"Boom Detent Solenoid Signal Error",
		"Bucket Detent Solenoid Signal Error",
		"Not define."
	};
	/////////////////////////////////////////////////////////////////////
	
}
