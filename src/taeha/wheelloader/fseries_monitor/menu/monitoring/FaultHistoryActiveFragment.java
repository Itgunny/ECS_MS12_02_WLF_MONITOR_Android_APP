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
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.R.color;
import android.graphics.Color;
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
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FaultHistoryActiveFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int REQ_ERR_MACHINE_ACTIVE		= 0;
//	private static final int REQ_ERR_MACHINE_LOGGED		= 1;
	
	private static final int REQ_ERR_ENGINE_ACTIVE		= 2;
//	private static final int REQ_ERR_ENGINE_LOGGED		= 3;
	
	private static final int REQ_ERR_TM_ACTIVE			= 4;
//	private static final int REQ_ERR_TM_LOGGED			= 5;
	
	private static final int REQ_ERR_EHCU_ACTIVE		= 6;
//	private static final int REQ_ERR_EHCU_LOGGED		= 7;
	
	private static final int REQ_ERR_ACU_ACTIVE			= 250;
	
	private static final int EHCU_SPN = 0;
	private static final int EHCU_FMI = 1;
	private static final int EHCU_LENTGH = 168;		// ++, --, 150202 bwk : 84 -> 168
	
	// ++, 150202 bwk : 150128 HHI ¿”«ı¡ÿ ø‰√ª
	private static final int TCU_LENTGH = 173;
	
	private static final int ECU_SPN = 0;
	private static final int ECU_FMI = 1;
	private static final int CUMMINS_ECM_LENTGH = 645;
	private static final int SCANIA_ECM_LENTGH = 698;
	
	protected int ManufacturerCode;
	
	protected static final int STATE_MANUFACTURERCODE_CUMMINS					= 112;
	protected static final int STATE_MANUFACTURERCODE_SCANIA					= 116;
	// --, 150202 bwk
	
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
	RadioButton radioACU;
	
	RelativeLayout layoutDetail;
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
	int DTCTotalACU;
	
	int SendDTCIndex;
	int SendSeqIndex;
	
	int[] Err_Tcu;
	int[] Err_Mcu;
	int[] Err_Ecu;
	int[] Err_EHCU;	
	int[] Err_ACU;
	
	int FATCSettingTemperatureCelsius;
	int FATCSettingTemperatureFahrenheit;
	int Ambienttemperaturesensoropen;
	int Ambienttemperaturesensorshort;
	int Incabtemperaturesensoropen;
	int Incabtemperaturesensorshort;
	int Evaptemperaturesensoropen;
	int Evaptemperaturesensorshort;
	int Mode1actuatoropenshort;
	int Mode1actuatordrivecircuitmalfunction;
	int Intakeactuatoropenshort;
	int Intakeactuatordrivecircuitmalfunction;
	int Temperatureactuatoropenshort;
	int Temperatureactuatordrivecircuitmalfunction;
	int Ducttemperaturesensoropen;
	int Ducttemperaturesensorshort;
	int WaterValveSensorError;
	int GPSCircuitError;
	
	// ++, 150211 bwk
	int CursurIndex;		
	int CursurDetailIndex;
	Handler HandleCursurDisplay;
	// --, 150211 bwk
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
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_fault_active, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CursurDisplay(CursurIndex);	// ++, --, 150211 bwk
		ClickMachine();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Active_Fault));
		
		// ++, 150211 bwk
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		// --, 150211 bwk
		return mRoot;
	}
	
	// ++, 150205 bwk
	public void onDestroyView()
	{
		super.onDestroyView();
		CAN1Comm.Set_MonitorScreenNumber_836_PGN65327(0xFF);
		CAN1Comm.TxCANToMCU(47);
	}
	// --, 150205 bwk
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_fault_active_low_ok);
		
		textViewAS = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_active_low_as);
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_active_list_title);
		textViewDetailTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_active_detail_title);
		textViewDetail = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_fault_active_detail_data);
		
		radioMachine = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_machine);
		radioEngine = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_engine);
		radioTransmission = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_transmission);
		radioEHCU = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_ehcu);
		radioACU = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_monitoring_fault_acu);
		
		layoutDetail = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_monitoring_fault_active_detail);

		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_monitoring_fault_active_list);
		adapter = new IconTextListAdapterFault(ParentActivity);
		adapter.clearItem();

		CursurIndex = 1;		// ++, --, 150211 bwk
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
			
		SendDTCIndex = REQ_ERR_MACHINE_ACTIVE;
		SendSeqIndex = 1;
		
		Err_Tcu = new int[400];
		Err_Mcu = new int[400];
		Err_Ecu = new int[400];
		Err_EHCU = new int[400];
		Err_ACU = new int[400];
		for(int i = 0; i < 400; i++){
			Err_Tcu[i] = 0;
			Err_Mcu[i] = 0;
			Err_Ecu[i] = 0;
			Err_EHCU[i] = 0;
			Err_ACU[i] = 0;
		}
		
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
				// ++, 150211 bwk
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				// --, 150211 bwk
				ClickOK();
			}
		});
		radioMachine.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ++, 150211 bwk
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				// --, 150211 bwk
				ClickMachine();
			}
		});
		radioEngine.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ++, 150211 bwk
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				// --, 150211 bwk
				ClickEngine();
			}
		});
		radioTransmission.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ++, 150211 bwk
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				// --, 150211 bwk
				ClickTransmission();
			}
		});
		radioEHCU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ++, 150211 bwk
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				// --, 150211 bwk
				ClickEHCU();
			}
		});
		radioACU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ++, 150211 bwk
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				// --, 150211 bwk
				ClickACU();
			}
		});
		
		textViewDetailTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ++, 150213 bwk
				CursurIndex = 7;
				CursurDisplay(CursurIndex);
				// --, 150213 bwk
				ClickDetailTitle();
			}
		});
		AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				// ++, 150213 bwk
				CursurIndex = 8;
				CursurDisplay(CursurIndex);
				CursurDetailIndex = arg2;
				// --, 150213 bwk
				ErrDetailDisplay(arg2);
				
			}
		};
		listView.setOnItemClickListener(mItemClickListener);
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		ReqestErrorCode();
		DTCTotalPacketMachine = CAN1Comm.Get_gErr_Mcu_TotalPacket();
		DTCTotalPacketEngine = CAN1Comm.Get_gErr_Ecu_TotalPacket();
		DTCTotalPacketTM = CAN1Comm.Get_gErr_Tcu_TotalPacket();
		DTCTotalPacketEHCU = CAN1Comm.Get_EHCUTotalPacket();
		if(CAN1Comm.Get_EHCUSingleOrMulti() == 1)
		{
			if(CAN1Comm.Get_EHCUSingleErrorData() == 0)
			{
				DTCTotalEHCU = 0;
			}
			else
			{
				DTCTotalEHCU = 1;
			}
			//Log.d(TAG,"EHCU Single Error : " + Integer.toString(DTC_Total));
		}
		else
		{
			DTCTotalEHCU = CAN1Comm.Get_EHCUTotalError();
			//Log.d(TAG,"EHCU Multi Error : " + Integer.toString(DTC_Total));
		}
		
		DTCTotalMachine = CAN1Comm.Get_gErr_Mcu_Total();
		DTCTotalEngine = CAN1Comm.Get_gErr_Ecu_Total();
		DTCTotalTM = CAN1Comm.Get_gErr_Tcu_Total();
		
		//TEMP
		FATCSettingTemperatureCelsius = CAN1Comm.Get_FATCSettingTemperatureCelsius_3408_PGN65373();
		FATCSettingTemperatureFahrenheit = CAN1Comm.Get_FATCSettingTemperatureFahrenheit_3409_PGN65373();
		//DTC
		Ambienttemperaturesensoropen = CAN1Comm.Get_Ambienttemperaturesensoropen_PGN65373();
		Ambienttemperaturesensorshort = CAN1Comm.Get_Ambienttemperaturesensorshort_PGN65373();
		Incabtemperaturesensoropen = CAN1Comm.Get_Incabtemperaturesensoropen_PGN65373();
		Incabtemperaturesensorshort = CAN1Comm.Get_Incabtemperaturesensorshort_PGN65373();
		Evaptemperaturesensoropen = CAN1Comm.Get_Evaptemperaturesensoropen_PGN65373();
		Evaptemperaturesensorshort = CAN1Comm.Get_Evaptemperaturesensorshort_PGN65373();
		Mode1actuatoropenshort = CAN1Comm.Get_Mode1actuatoropenshort_PGN65373();
		Mode1actuatordrivecircuitmalfunction = CAN1Comm.Get_Mode1actuatordrivecircuitmalfunction_PGN65373();
		Intakeactuatoropenshort = CAN1Comm.Get_Intakeactuatoropenshort_PGN65373();
		Intakeactuatordrivecircuitmalfunction = CAN1Comm.Get_Intakeactuatordrivecircuitmalfunction_PGN65373();
		Temperatureactuatoropenshort = CAN1Comm.Get_Temperatureactuatoropenshort_PGN65373();
		Temperatureactuatordrivecircuitmalfunction = CAN1Comm.Get_Temperatureactuatordrivecircuitmalfunction_PGN65373();
		Ducttemperaturesensoropen = CAN1Comm.Get_Ducttemperaturesensoropen_PGN65373();
		Ducttemperaturesensorshort = CAN1Comm.Get_Ducttemperaturesensorshort_PGN65373();
		WaterValveSensorError = CAN1Comm.Get_WaterValveSensorError_PGN65373();
		GPSCircuitError = CAN1Comm.Get_GPSCircuitError_PGN65373();
		

		Err_ACU[0] = CAN1Comm.Get_Ambienttemperaturesensoropen_PGN65373();
		Err_ACU[1] = CAN1Comm.Get_Ambienttemperaturesensorshort_PGN65373();
		Err_ACU[2] = CAN1Comm.Get_Incabtemperaturesensoropen_PGN65373();
		Err_ACU[3] = CAN1Comm.Get_Incabtemperaturesensorshort_PGN65373();
		Err_ACU[4] = CAN1Comm.Get_Evaptemperaturesensoropen_PGN65373();
		Err_ACU[5] = CAN1Comm.Get_Evaptemperaturesensorshort_PGN65373();
		Err_ACU[6] = CAN1Comm.Get_Mode1actuatoropenshort_PGN65373();
		Err_ACU[7] = CAN1Comm.Get_Mode1actuatordrivecircuitmalfunction_PGN65373();
		Err_ACU[8] = CAN1Comm.Get_Intakeactuatoropenshort_PGN65373();
		Err_ACU[9] = CAN1Comm.Get_Intakeactuatordrivecircuitmalfunction_PGN65373();
		Err_ACU[10] = CAN1Comm.Get_Temperatureactuatoropenshort_PGN65373();
		Err_ACU[11] = CAN1Comm.Get_Temperatureactuatordrivecircuitmalfunction_PGN65373();
		Err_ACU[12] = CAN1Comm.Get_Ducttemperaturesensoropen_PGN65373();
		Err_ACU[13] = CAN1Comm.Get_Ducttemperaturesensorshort_PGN65373();
		Err_ACU[14] = CAN1Comm.Get_WaterValveSensorError_PGN65373();
		Err_ACU[15] = CAN1Comm.Get_GPSCircuitError_PGN65373();
		
		DTCTotalACU = CheckACUDTCNumber();
		
		ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_ECM();	// ++, --, 150202 bwk
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ErrListDisplay();
		ErrorNumberDisplay();
		EHCUShow();
		
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
			ParentActivity.showMainScreen();
		// --, 150309 bwk			
			ParentActivity.OldScreenIndex = 0;
		}
		else{
			ParentActivity._MenuBaseFragment.showFaultHistoryAnimation();
		}
			
	}
	public void ClickMachine(){
		TitleDisplay(ParentActivity.getResources().getString(string.Machine));
		radioMachine.setChecked(true);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(false);
		radioACU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_MACHINE_ACTIVE;
		
		// ++, 150205 bwk
		CAN1Comm.Set_MonitorScreenNumber_836_PGN65327(121);
		CAN1Comm.TxCANToMCU(47);
		// --, 150205 bwk
		
	}
	public void ClickEngine(){
		TitleDisplay(ParentActivity.getResources().getString(string.Engine));
		radioMachine.setChecked(false);
		radioEngine.setChecked(true);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(false);
		radioACU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_ENGINE_ACTIVE;
		
		// ++, 150205 bwk
		CAN1Comm.Set_MonitorScreenNumber_836_PGN65327(0xFF);
		CAN1Comm.TxCANToMCU(47);
		// --, 150205 bwk
		
	}
	public void ClickTransmission(){
		TitleDisplay(ParentActivity.getResources().getString(string.Transmission));
		radioMachine.setChecked(false);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(true);
		radioEHCU.setChecked(false);
		radioACU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_TM_ACTIVE;
		
		// ++, 150205 bwk
		CAN1Comm.Set_MonitorScreenNumber_836_PGN65327(0xFF);
		CAN1Comm.TxCANToMCU(47);
		// --, 150205 bwk
		
	}
	public void ClickEHCU(){
		TitleDisplay(ParentActivity.getResources().getString(string.EHCU));
		radioMachine.setChecked(false);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(true);
		radioACU.setChecked(false);
		DetailEnable(false);
		SelectedMode = REQ_ERR_EHCU_ACTIVE;

		// ++, 150205 bwk
		CAN1Comm.Set_MonitorScreenNumber_836_PGN65327(0xFF);
		CAN1Comm.TxCANToMCU(47);
		// --, 150205 bwk

	}
	public void ClickACU(){
		TitleDisplay(ParentActivity.getResources().getString(string.ACU));
		radioMachine.setChecked(false);
		radioEngine.setChecked(false);
		radioTransmission.setChecked(false);
		radioEHCU.setChecked(false);
		radioACU.setChecked(true);
		DetailEnable(false);
		SelectedMode = REQ_ERR_ACU_ACTIVE;
		
		// ++, 150205 bwk
		CAN1Comm.Set_MonitorScreenNumber_836_PGN65327(0xFF);
		CAN1Comm.TxCANToMCU(47);
		// --, 150205 bwk
		
	}
	
	public void ClickDetailTitle(){
		DetailEnable(false);
	}
	/////////////////////////////////////////////////////////////////////
	public void EHCUShow(){
		if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935){
			radioEHCU.setVisibility(View.GONE);
		}else{
			radioEHCU.setVisibility(View.VISIBLE);
		}
	}
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
		radioACU.setText(ParentActivity.getResources().getString(string.ACU) + "(" + Integer.toString(DTCTotalACU) + ")");
	}
	public void ErrListDisplay(){
		switch (SelectedMode) {
		case REQ_ERR_MACHINE_ACTIVE:
			SetErrList(DTCTotalMachine, SelectedMode);
			break;
		case REQ_ERR_ENGINE_ACTIVE:
			SetErrList(DTCTotalEngine, SelectedMode);
			break;
		case REQ_ERR_TM_ACTIVE:
			SetErrList(DTCTotalTM, SelectedMode);
			break;
		case REQ_ERR_EHCU_ACTIVE:
			SetErrList(DTCTotalEHCU, SelectedMode);
			break;
		case REQ_ERR_ACU_ACTIVE:
			SetACUErrList();
			break;
		default:
			break;
		}
	}
	public void SetACUErrList(){
		adapter.clearItem();
		for(int i = 0; i < 16; i++){
			if(Err_ACU[i] == 1){
				adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), 
						"No : " + Integer.toString(i), "", ""));
			}
		}
		adapter.notifyDataSetChanged();
	}

	public void SetErrList(int NumofErr, int Mode){
		int SPN = 0;
		int FMI = 0;
		//Log.d(TAG,"DTC_Type : " + Integer.toString(DTC_Type));
		adapter.clearItem();
		if(NumofErr > 400){
			NumofErr = 400;
		}

		else
		{
			for(int i = 0; i < NumofErr; i++){
				if(Mode == REQ_ERR_MACHINE_ACTIVE)		// MCU 
				{
					//Log.d(TAG,"ReqMode : " + Integer.toString(ReqMode));
					Err_Mcu = CAN1Comm.Get_McuErr();
					
					SPN = Err_Mcu[i] & 0xffff;
					SPN |= ((Err_Mcu[i] & 0xe00000) >> 5);
					FMI = ((Err_Mcu[i] & 0x1f0000) >> 16); 
					// ++, 150213 bwk
					if(CursurIndex == 7 && CursurDetailIndex == i)
					{
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn_selected), "HCESPN : " + Integer.toString(SPN)
								+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
					}
					else
					// --, 150213 bwk
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "HCESPN : " + Integer.toString(SPN)
								+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
				
				//	Log.d(TAG,"SPN : " + Integer.toString(SPN) + "     FMI : " + Integer.toString(FMI));
				}
				else if(Mode == REQ_ERR_ENGINE_ACTIVE)	// ECU	
				{
					//Log.d(TAG,"ReqMode : " + Integer.toString(ReqMode));
					Err_Ecu = CAN1Comm.Get_EcuErr();
					
					SPN = Err_Ecu[i] & 0xffff;
					SPN |= ((Err_Ecu[i] & 0xe00000) >> 5);
					FMI = ((Err_Ecu[i] & 0x1f0000) >> 16); 
					// ++, 150213 bwk
					if(CursurIndex == 7 && CursurDetailIndex == i)
					{
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn_selected), "SPN : " + Integer.toString(SPN)
								+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
					}
					else
					// --, 150213 bwk
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "SPN : " + Integer.toString(SPN)
								+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
				}
				else if(Mode == REQ_ERR_TM_ACTIVE)		// TCU
				{
					Err_Tcu = CAN1Comm.Get_TcuErr();
					// ++, 150213 bwk
					if(CursurIndex == 7 && CursurDetailIndex == i)
					{
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn_selected), "No : " + Integer.toHexString(Err_Tcu[i]), "", ""));
					}
					else
					// --, 150213 bwk
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "No : " + Integer.toHexString(Err_Tcu[i]), "", ""));
				}
				else if(Mode == REQ_ERR_EHCU_ACTIVE)		// EHCU
				{
					
					int EHCU_M_S = CAN1Comm.Get_EHCUSingleOrMulti();
					//Log.d(TAG,"SetErrList EHCU_M_S : " + Integer.toString(EHCU_M_S));
					if(EHCU_M_S == 2)
					{
						Err_EHCU[i] = CAN1Comm.Get_EHCUErrorData(i);
						//Log.d(TAG,"SetErrList Err_EHCU[i] : " + Integer.toString(Err_EHCU[i]));
					}
					else
					{
						//Log.d(TAG,"SetErrList [i] : " + Integer.toString(i));
						Err_EHCU[i] = CAN1Comm.Get_EHCUSingleErrorData();
						//Log.d(TAG,"SetErrList Err_EHCU[i] : 0x0" + Integer.toHexString(Err_EHCU[i]));
					}
					
					SPN = Err_EHCU[i] & 0x0000FFFF;
					FMI = ((Err_EHCU[i] & 0x00FF0000) >> 16);
					
					// ++, 150213 bwk
					if(CursurIndex == 7 && CursurDetailIndex == i)
					{
						adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn_selected), "SPN : " + Integer.toString(SPN)
								+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
					}
					else
					// --, 150213 bwk
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
		if(SelectedMode == REQ_ERR_MACHINE_ACTIVE)
		{
			SPN = Err_Mcu[Index] & 0xFFFF;
			SPN |= ((Err_Mcu[Index] & 0xe00000) >> 5);
			FMI = ((Err_Mcu[Index] & 0x1f0000)>>16);
			for(int i = 0; i < SPNDATA.length; i++){
				if(SPN == SPNDATA[i] && FMI == FMIDATA[i])
					textViewDetail.setText(textErrorCode[i]);

			}
		}
		else if(SelectedMode == REQ_ERR_EHCU_ACTIVE)
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
		}else if(SelectedMode == REQ_ERR_ACU_ACTIVE)
		{
			try {
				textViewDetail.setText(textACUErrCode[Index]);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				Log.e(TAG,"ArrayIndexOutOfBoundsException ErrDetailDisplay");
			}
			
		}
		
		// ++, 150202 bwk
		else if(SelectedMode == REQ_ERR_TM_ACTIVE)
		{
			for(int i = 0; i < TCU_LENTGH; i++){
				if(Err_Tcu[Index] == TCU_SPN[i])
				{
					//Log.d(TAG,"ErrDetailDisplay i : " + Integer.toHexString(i));
					textViewDetail.setText(textTCUErrCode[i]);
					break;
				}
				else{
					textViewDetail.setText(textTCUErrCode[TCU_LENTGH]);
				}
			}
		}
		else if(SelectedMode == REQ_ERR_ENGINE_ACTIVE)
		{
			SPN = Err_Ecu[Index] & 0xFFFF;
			SPN |= ((Err_Ecu[Index] & 0xe00000) >> 5);
			FMI = ((Err_Ecu[Index] & 0x1f0000)>>16);

			if(ManufacturerCode == STATE_MANUFACTURERCODE_CUMMINS)
			{
				for(int i = 0; i < CUMMINS_ECM_LENTGH; i++){
					if(SPN == Cummins_ECM_SPNFMI[i][ECU_SPN] && FMI == Cummins_ECM_SPNFMI[i][ECU_FMI])
						textViewDetail.setText(textCumminsECMErrCode[i]);
				}
			}
			else if(ManufacturerCode == STATE_MANUFACTURERCODE_SCANIA)
			{
				for(int i = 0; i < SCANIA_ECM_LENTGH; i++){
					if(SPN == Scania_ECM_SPNFMI[i][ECU_SPN] && FMI == Scania_ECM_SPNFMI[i][ECU_FMI])
						textViewDetail.setText(textScaniaECMErrCodep[i]);
				}
			}
			else
			{
				textViewDetail.setText(ParentActivity.getResources().getString(string.Please_refer_to_machine_manual_for_more_detailed_description));
			}
		}
		// --, 150202 bwk
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
		case REQ_ERR_MACHINE_ACTIVE:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
				SetThreadSleepTime(200);
			}
			else if(SendSeqIndex > DTCTotalPacketMachine){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_ENGINE_ACTIVE;
				SetThreadSleepTime(1000);
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
				
			break;
		case REQ_ERR_ENGINE_ACTIVE:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
				SetThreadSleepTime(200);
			}
			else if(SendSeqIndex > DTCTotalPacketEngine){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_TM_ACTIVE;
				SetThreadSleepTime(1000);
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
				
			break;
		case REQ_ERR_TM_ACTIVE:
			if(SendSeqIndex == 1){
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
				SetThreadSleepTime(200);
			}
			else if(SendSeqIndex > DTCTotalPacketTM){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_MACHINE_ACTIVE;
				SetThreadSleepTime(1000);
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
//	public int CheckACUDTCNumber(){
//		int _DTCTotal = 0;
//		if(FATCSettingTemperatureCelsius == 1)
//			_DTCTotal++;
//		if(FATCSettingTemperatureFahrenheit == 1)
//			_DTCTotal++;
//		if(Ambienttemperaturesensoropen == 1)
//			_DTCTotal++;
//		if(Ambienttemperaturesensorshort == 1)
//			_DTCTotal++;
//		if(Incabtemperaturesensoropen == 1)
//			_DTCTotal++;
//		if(Incabtemperaturesensorshort == 1)
//			_DTCTotal++;
//		if(Evaptemperaturesensoropen == 1)
//			_DTCTotal++;
//		if(Evaptemperaturesensorshort == 1)
//			_DTCTotal++;
//		if(Mode1actuatoropenshort == 1)
//			_DTCTotal++;
//		if(Mode1actuatordrivecircuitmalfunction == 1)
//			_DTCTotal++;
//		if(Intakeactuatoropenshort == 1)
//			_DTCTotal++;
//		if(Intakeactuatordrivecircuitmalfunction == 1)
//			_DTCTotal++;
//		if(Temperatureactuatoropenshort == 1)
//			_DTCTotal++;
//		if(Temperatureactuatordrivecircuitmalfunction == 1)
//			_DTCTotal++;
//		if(Ducttemperaturesensoropen == 1)
//			_DTCTotal++;
//		if(Ducttemperaturesensorshort == 1)
//			_DTCTotal++;
//		if(WaterValveSensorError == 1)
//			_DTCTotal++;
//		if(GPSCircuitError == 1)
//			_DTCTotal++;
//	
//		return _DTCTotal;
//	
//	}
	public int CheckACUDTCNumber(){
		int _DTCTotal = 0;
		if(Err_ACU[0] == 1)
			_DTCTotal++;
		if(Err_ACU[1] == 1)
			_DTCTotal++;
		if(Err_ACU[2] == 1)
			_DTCTotal++;
		if(Err_ACU[3] == 1)
			_DTCTotal++;
		if(Err_ACU[4] == 1)
			_DTCTotal++;
		if(Err_ACU[5] == 1)
			_DTCTotal++;
		if(Err_ACU[6] == 1)
			_DTCTotal++;
		if(Err_ACU[7] == 1)
			_DTCTotal++;
		if(Err_ACU[8] == 1)
			_DTCTotal++;
		if(Err_ACU[9] == 1)
			_DTCTotal++;
		if(Err_ACU[10] == 1)
			_DTCTotal++;
		if(Err_ACU[11] == 1)
			_DTCTotal++;
		if(Err_ACU[12] == 1)
			_DTCTotal++;
		if(Err_ACU[13] == 1)
			_DTCTotal++;
		if(Err_ACU[14] == 1)
			_DTCTotal++;
		if(Err_ACU[15] == 1)
			_DTCTotal++;
		if(Err_ACU[16] == 1)
			_DTCTotal++;
		if(Err_ACU[17] == 1)
			_DTCTotal++;
	
		return _DTCTotal;
	
	}
	
	// ++, 150213 bwk
	public int CountErrorList(){
		switch (SelectedMode) {
			case REQ_ERR_MACHINE_ACTIVE:
				return DTCTotalMachine;
			case REQ_ERR_ENGINE_ACTIVE:
				return DTCTotalEngine;
			case REQ_ERR_TM_ACTIVE:
				return DTCTotalTM;
			case REQ_ERR_EHCU_ACTIVE:
				return DTCTotalEHCU;
			case REQ_ERR_ACU_ACTIVE:
			default:
				return 0;		
		}
	}
	// --, 150213 bwk
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		// ++, 150211 bwk
		switch (CursurIndex) {
		case 1:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		// ++, 150216 bwk
		case 7:
			if(CursurDetailIndex > 0)
				CursurDetailIndex--;
			else
				CursurDetailIndex = adapter.getCount()-1;
			CursurDisplay(CursurIndex);
			
			int TotalItem, ListCursurIndex = 0;
			
			if(CursurDetailIndex == adapter.getCount()-1 && adapter.getCount() > 6)
			{
				TotalItem = listView.getCount();
				ListCursurIndex = TotalItem - 6;
				
				listView.setSelectionFromTop(ListCursurIndex,0);
				//Log.d(TAG,"ClickLeft ListCursurIndex : " + Integer.toString(ListCursurIndex));
			}
			else if(listView.getFirstVisiblePosition() > CursurDetailIndex)
			{
				ListCursurIndex = listView.getFirstVisiblePosition()-6;
				TotalItem = listView.getCount();
				
				if(ListCursurIndex <= 0){
					ListCursurIndex = 0;
				}
				
				listView.setSelectionFromTop(ListCursurIndex,0);
				//Log.d(TAG,"ClickLeft ListCursurIndex : " + Integer.toString(ListCursurIndex));
			}			
			break;
		// --, 150216 bwk
		case 5:
		default:
			break;
		}
		// --, 150211 bwk
	}
	public void ClickRight(){
		// ++, 150211 bwk
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 6;
			CursurDisplay(CursurIndex);
			break;
		case 6:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		// ++, 150213 bwk
		case 7:
			if(CursurDetailIndex < adapter.getCount()-1)
				CursurDetailIndex++;
			else
				CursurDetailIndex = 0;
			CursurDisplay(CursurIndex);
			
			int TotalItem, ListCursurIndex = 0;
			
			if(listView.getFirstVisiblePosition() + 6 <= CursurDetailIndex || CursurDetailIndex == 0)
			{
				ListCursurIndex = CursurDetailIndex;
				TotalItem = listView.getCount();
				
				if(ListCursurIndex >= TotalItem){
					ListCursurIndex = TotalItem - 6;
				}
				
				listView.setSelectionFromTop(ListCursurIndex,0);
				//Log.d(TAG,"ListCursurIndex : " + Integer.toString(ListCursurIndex));			
			}
			break;
		// --, 150213 bwk			
		case 5:
		default:
			break;
		}
		// --, 150211 bwk
	}
	public void ClickESC(){
		// ++, 150213 bwk
		if(CursurIndex == 7)
		{
			switch(SelectedMode)
			{
				case REQ_ERR_MACHINE_ACTIVE:
					CursurIndex = 1;
					break;
				case REQ_ERR_ENGINE_ACTIVE:
					CursurIndex = 2;
					break;
				case REQ_ERR_TM_ACTIVE:
					CursurIndex = 3;
					break;
				case REQ_ERR_EHCU_ACTIVE:
					CursurIndex = 4;
					break;
				case REQ_ERR_ACU_ACTIVE:
					CursurIndex = 5;
					break;
				default:
					break;
			}
			CursurDisplay(CursurIndex);
		}
		else if(CursurIndex == 8)
		{
			CursurIndex =7;
			CursurDisplay(CursurIndex);
			ClickDetailTitle();
		}
		else
		// --, 150213 bwk
			ClickOK();
	}
	public void ClickEnter(){
		// ++, 150211 bwk
		switch (CursurIndex) {
		case 1:
			ClickMachine();
			break;
		case 2:
			ClickEngine();
			break;
		case 3:
			ClickTransmission();
			break;
		case 4:
			ClickEHCU();
			break;
		case 5:
			ClickACU();
			break;
		case 6:
			ClickOK();
			break;
		// ++, 150213 bwk
		case 7:
			CursurIndex = 8;
			if(adapter.getCount() > 0 && CountErrorList() > 0)
				ErrDetailDisplay(CursurDetailIndex);
			break;
		case 8:
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			ClickDetailTitle();
			break;
		// --, 150213 bwk
		default:
			break;
		}
		// --, 150211 bwk
		
		// ++. 150213 bwk
		if(CursurIndex < 6)
		{
			if(adapter.getCount() > 0 && CountErrorList() > 0)
			{
				CursurIndex = 7;
				CursurDetailIndex = 0;
				CursurDisplay(CursurIndex);
			}
		}
		// --, 150213 bwk
	}
	
	// ++, 150209 bwk
	public void ClickHome(){
		if(SelectedMode != REQ_ERR_MACHINE_ACTIVE)
		{
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			else
				ParentActivity.StartAnimationRunningTimer();
			// ++, 150309 bwk
			//ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.showMainScreen();
			// --, 150309 bwk
		}
	}
	// --, 150209 bwk
	
	// ++, 150211 bwk
	public void CursurDisplay(int Index){
		radioMachine.setPressed(false);
		radioEngine.setPressed(false);
		radioTransmission.setPressed(false);
		radioEHCU.setPressed(false);
		//radioACU.setPressed(false);
		imgbtnOK.setPressed(false);
		// ++, 150213 bwk
		if(adapter.getCount() > 0)
		{
			for(int i=0;i<adapter.getCount();i++)
				adapter.UpdateIcon(i, ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn));
			adapter.notifyDataSetChanged();
		}
		// --, 150213 bwk
		
		switch(Index){
			case 1:
				radioMachine.setPressed(true);
				break;
			case 2:
				radioEngine.setPressed(true);
				break;
			case 3:
				radioTransmission.setPressed(true);
				break;
			case 4:
				radioEHCU.setPressed(true);
				break;
			case 5:
				radioACU.setPressed(true);
				break;
			case 6:
				imgbtnOK.setPressed(true);
				break;
			// ++, 150213 bwk
			case 7:
				if(CursurDetailIndex < adapter.getCount())
					adapter.UpdateIcon(CursurDetailIndex, ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn_selected));
				else if(adapter.getCount() > 0)
					adapter.UpdateIcon(0, ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn_selected));
				
				adapter.notifyDataSetChanged();
				break;
			// --, 150213 bwk
		}
		
	}
	// --, 150211 bwk
	
	/////////////////////////////////////////////////////////////////////
	static final int EHCU_SPNFMI[][] = {{842,9},{844,9},{978,9},{2316,9},{2317,9},{2319,2},{2320,2},{2321,2},{2322,2},{2323,2},{2324,2},
		{2325,2},{2326,5},{2326,6},{2327,0},{2327,1},{2327,5},{2327,6},{2327,14},{2311,2},{2311,0},{2311,1},{2311,3},{2311,4},{2311,13},
		{2311,14},{2311,31},{2313,2},{2313,0},{2313,1},{2313,3},{2313,4},{2313,13},{2313,14},{2313,31},{2315,2},{2315,0},{2315,1},
		{2315,3},{2315,4},{2315,13},{2315,14},{2315,31},{2304,0},{2304,1},{2304,5},{2304,6},{2304,14},{2305,0},{2305,1},{2305,5},{2305,6},
		{2305,14},{2306,0},{2306,1},{2306,5},{2306,6},{2306,14},{2307,0},{2307,1},{2307,5},{2307,6},{2307,14},{2308,0},{2308,1},{2308,5},
		{2308,6},{2308,14},{2309,0},{2309,1},{2309,5},{2309,6},{2309,14},{2328,0},{2328,1},{2328,3},{2328,4},{2329,0},{2329,1},
		{738,2},{751,2},{2330,2},{172,2},{174,2},
		// ++, 150202 bwk : 150128 HHI ¿”«ı¡ÿ ø‰√ª
		{2333, 9}, {2331, 9}, {2332, 9}, {2317, 9}, {2319, 2}, {2320, 2}, {2321, 2}, {2322, 2}, {2323, 2}, {2324, 2}, {2325, 2}, 
		{2326, 5}, {2326, 6}, {2327, 0}, {2327, 1}, {2327, 5}, {2327, 6}, {2327, 14}, {2311, 2}, {2311, 0}, {2311, 1}, {2311, 3}, 
		{2311, 4}, {2311, 13}, {2311, 14}, {2311, 31}, {2313, 2}, {2313, 0}, {2313, 1}, {2313, 3}, {2313, 4}, {2313, 13}, {2313, 14}, 
		{2313, 31}, {2315, 2}, {2315, 0}, {2315, 1}, {2315, 3}, {2315, 4}, {2315, 13}, {2315, 14}, {2315, 31}, {2304, 0}, {2304, 1}, 
		{2304, 5}, {2304, 6}, {2304, 14}, {2305, 0}, {2305, 1}, {2305, 5}, {2305, 6}, {2305, 14}, {2306, 0}, {2306, 1}, {2306, 5}, 
		{2306, 6}, {2306, 14}, {2307, 0}, {2307, 1}, {2307, 5}, {2307, 6}, {2307, 14}, {2308, 0}, {2308, 1}, {2308, 5}, {2308, 6}, 
		{2308, 14}, {2309, 0}, {2309, 1}, {2309, 5}, {2309, 6}, {2309, 14}, {2328, 0}, {2328, 1}, {2328, 3}, {2328, 4}, {2329, 0}, 
		{2329, 1}, {2329, 11}, {739,  2}, {2334, 0}, {2334, 1}, {2335, 2}, {2335, 14}};
		// --, 150202 bwk
	static final int SPNDATA[] = {101,101,145,145,172,172,173,173,174,174,181,181,183,183,187,187,202,202,202,202		
		,203,203,203,203,204,204,204,204,205,205,205,205,301,301,304,304,310,318,322,322,325,325,327,327,346		
		,346,503,503,503,503,507,507,507,507,551,551,552,552,558,558,558,558,701,705,705,707,723,723,727,727		
		,728,728,729,729,730,830,840,841,842,843,844,850,869};
	static final int FMIDATA[] = {3, 4,	5,	6,	4,	6,	4,	6,	4,	6,	4,	6,	4,	6,	4,	6,	0,	1,	2,	4,	0,	1,	2,	4,	0,	
		1,	2,	4,	0,	1,	2,	4,	3,	4,	3,	4,	8,	8,	4,	6,	4,	6,	4,	6,	3,	4,	0,	1,	2,	4,	0,	1,	2,	4,	3,	4,	3,	
		4,	0,	1,	2,	4,	4,	0,	1,	1,	3,	4,	4,	6,	3,	4,	3,	4,	19,	12,	2,	2,	2,	2,	2,	2, 2};
	// ++, 150202 bwk : 150128 HHI ¿”«ı¡ÿ ø‰√ª
	static final int TCU_SPN[] = {0x10,0x11,0x12,0x13,0x14,0x15,0x16,0x17,0x18,0x19,0x1A,0x1B,0x1C,0x1D,0x1E,0x1F,
		0x21,0x22,0x23,0x24,0x25,0x26,0x27,0x28,0x29,0x2A,0x2B,0x2C,0x2D,0x2E,0x2F,
		0x31,0x32,0x33,0x34,0x35,0x36,0x37,0x38,0x39,0x3A,0x3B,0x3C,0x3D,0x3E,
		0x40,0x41,0x50,0x51,0x52,0x53,0x54,0x55,0x56,0x57,0x58,0x59,0x5A,0x5B,0x5C,0x5D,0x5E,0x5F,
		0x60,0x61,0x62,0x63,0x64,0x65,0x69,0x6A,0x6B,0x6E,0x71,0x72,0x73,0x74,0x75,0x76,0x77,0x78,0x79,0x7A,0x7B,0x7C,0x7D,0x7E,0x7F,
		0x81,0x82,0x83,0x84,0x85,0x86,0x87,0x88,0x89,0x8A,0x8B,0x8C,0x8D,0x8E,0x8F,
		0x91,0x92,0x93,0x94,0x95,0x96,0x97,0x98,0x99,0x9A,0x9B,0x9C,0x9D,0x9E,0x9F,
		0xA1,0xA2,0xA3,0xA4,0xA5,0xA6,0xA7,0xA8,0xA9,0xAA,0xAB,0xAC,0xAD,0xAE,0xAF,
		0xB1,0xB2,0xB3,0xB4,0xB5,0xB6,0xB7,0xB8,0xB9,0xBA,0xBB,0xBD,0xBE,0xBF,
		0xC0,0xC1,0xC2,0xC3,0xC4,0xC5,0xC6,0xD1,0xD2,0xD3,0xD4,0xD5,0xD6,0xE1,0xE2,0xE3,0xE4,0xE5,0xE6,
		0xF1,0xF2,0xF3,0xF4,0xF5,0xF6,0xF7};
	static final int Cummins_ECM_SPNFMI[][] = {{27,2},{27,4},{81,16},{84,2},{84,9},{84,10},{84,19},{91,0},{91,1},{91,2},{91,3},{91,4},{91,9},{91,19},{93,2},{94,0},{94,3},{94,4},{94,15},
		{94,17},{94,18},{95,16},{97,3},{97,4},{97,15},{97,16},{98,0},{98,1},{98,17},{100,1},{100,2},{100,3},{100,4},{100,18},{101,0},{101,2},
		{101,3},{101,4},{101,15},{101,16},{102,2},{102,3},{102,4},{102,10},{102,16},{102,18},{103,2},{103,15},{103,16},{103,18},{104,18},
		{105,0},{105,2},{105,3},{105,4},{105,15},{105,16},{105,18},{107,15},{107,16},{108,2},{108,3},{108,4},{109,3},{109,4},{109,18},{110,0},
		{110,2},{110,3},{110,4},{110,14},{110,15},{110,16},{110,18},{110,31},{110,31},{111,1},{111,2},{111,3},{111,4},{111,17},{111,17},
		{111,18},{111,18},{157,0},{157,1},{157,2},{157,3},{157,4},{157,7},{157,15},{157,16},{157,18},{168,16},{168,17},{168,18},{171,2},
		{171,3},{171,4},{171,9},{171,19},{174,0},{174,2},{174,3},{174,4},{174,16},{175,0},{175,2},{175,3},{175,4},{175,16},
		{188,16},{188,18},{190,0},{190,2},{190,2},{190,16},{190,16},{191,9},{191,16},{191,18},{191,19},{237,2},{237,13},{237,31},
		{251,2},{411,2},{411,3},{411,4},{412,2},{412,3},{412,4},{412,15},{412,16},{441,3},{441,4},{441,14},{442,3},{442,4},
		{521,2},{558,2},{558,9},{558,13},{558,19},{563,9},{563,31},{596,2},{596,7},{596,13},{597,3},{597,4},{599,2},
		{611,2},{612,2},{625,9},{626,3},{626,4},{626,18},{629,12},{629,31},{630,12},{633,31},{639,2},{639,9},{639,13},{640,14},
		{641,7},{641,9},{641,11},{641,12},{641,13},{641,15},{641,31},{644,2},{647,3},{647,4},{649,3},{649,4},{649,5},
		{651,5},{651,7},{652,5},{652,7},{653,5},{653,7},{654,5},{654,7},{655,5},{655,7},{656,5},{656,7},{657,5},{658,5},{659,5},
		{660,5},{661,5},{662,5},{663,5},{664,5},{665,5},{666,5},{677,3},{677,4},{697,3},{697,4},{701,14},{702,3},{702,5},{702,6},{703,3},
		{723,2},{723,7},{729,3},{729,4},{748,9},{862,3},{862,4},{974,3},{974,4},{974,19},{1072,3},{1072,4},{1073,3},{1073,4},{1075,3},{1075,4},
		{1081,7},{1081,9},{1081,19},{1081,31},{1109,0},{1112,3},{1112,4},{1127,7},{1136,2},{1136,3},{1136,4},{1172,2},{1172,3},{1172,4},{1172,9},
		{1172,19},{1176,1},{1176,2},{1176,3},{1176,4},{1176,9},{1176,18},{1176,19},{1194,13},{1195,2},{1209,2},{1209,3},{1209,4},{1209,16},
		{1213,9},{1231,2},{1235,2},{1239,16},{1267,3},{1267,4},{1322,31},{1323,31},{1324,31},{1325,31},{1326,31},{1327,31},{1328,31},
		{1347,3},{1347,4},{1347,7},{1349,3},{1349,4},{1377,2},{1378,31},{1383,31},{1387,3},{1387,4},{1388,3},{1388,4},{1388,14},
		{1563,2},{1569,31},{1590,2},{1623,9},{1623,13},{1623,19},{1632,14},{1632,31},{1639,0},{1639,1},{1668,2},{1675,31},
		{1761,1},{1761,2},{1761,3},{1761,4},{1761,5},{1761,6},{1761,10},{1761,11},{1761,13},{1761,17},{1761,18},{1800,16},{1800,18},{1818,31},
		{2006,9},{2623,3},{2623,4},{2629,15},{2630,2},{2630,3},{2630,4},{2633,7},{2634,3},{2634,4},{2789,15},{2789,16},{2791,5},{2791,6},
		{2791,7},{2791,9},{2791,13},{2791,15},{2797,13},{2884,9},{2978,9},{3031,2},{3031,3},{3031,4},{3031,5},{3031,6},{3031,9},{3031,11},
		{3031,13},{3060,18},{3216,2},{3216,4},{3216,9},{3216,10},{3216,13},{3216,16},{3216,20},{3217,2},{3218,2},{3226,2},{3226,4},{3226,9},
		{3226,10},{3226,13},{3226,20},{3227,9},{3228,2},{3242,0},{3242,2},{3242,3},{3242,4},{3242,15},{3242,16},{3246,0},{3246,2},{3246,3},
		{3246,4},{3246,15},{3246,16},{3249,17},{3249,18},{3251,0},{3251,2},{3251,3},{3251,4},{3251,15},{3251,16},{3255,9},{3265,9},{3353,3},
		{3353,4},{3361,2},{3361,3},{3361,4},{3362,31},{3363,3},{3363,4},{3363,7},{3363,16},{3363,18},{3364,1},{3364,2},{3364,3},{3364,4},{3364,5},
		{3364,6},{3364,7},{3364,9},{3364,10},{3364,11},{3364,12},{3364,13},{3364,15},{3364,18},{3364,19},{3480,2},{3480,3},{3480,4},{3480,17},
		{3481,16},{3482,2},{3482,3},{3482,4},{3482,7},{3482,13},{3482,16},{3490,3},{3490,4},{3490,7},{3509,3},{3509,4},{3510,3},{3510,4},
		{3511,3},{3511,4},{3512,3},{3512,4},{3513,3},{3513,4},{3514,3},{3514,4},{3515,2},{3515,3},{3515,4},{3515,5},{3515,6},{3515,10},
		{3515,11},{3521,11},{3521,31},{3556,2},{3556,5},{3556,7},{3556,18},{3597,2},{3597,3},{3597,4},{3597,12},{3597,18},{3610,2},{3610,3},
		{3610,4},{3667,2},{3667,3},{3667,4},{3695,2},{3703,31},{3713,31},{3750,31},{3826,18},{3936,7},{3936,14},{3936,15},{4094,31},{4096,31},
		{4097,3},{4097,4},{4097,7},{4182,4},{4183,4},{4184,4},{4185,31},{4186,31},{4187,31},{4188,31},{4223,31},{4331,18},{4334,2},{4334,3},
		{4334,4},{4334,16},{4334,18},{4337,2},{4337,10},{4339,31},{4340,3},{4340,4},{4340,5},{4342,3},{4342,4},{4342,5},{4344,3},{4344,4},
		{4344,5},{4360,0},{4360,2},{4360,3},{4360,4},{4360,15},{4360,16},{4363,0},{4363,2},{4363,3},{4363,4},{4363,16},{4364,18},{4376,3},
		{4376,4},{4376,7},{4490,9},{4490,19},{4765,2},{4765,3},{4765,4},{4765,13},{4765,16},{4792,7},{4792,14},{4793,31},{4794,31},{4795,31},
		{4796,31},{4809,2},{4809,3},{4809,4},{4809,13},{4809,16},{4810,0},{4810,2},{4810,3},{4810,4},{4810,15},{4810,16},{5018,11},{5019,2},
		{5019,3},{5019,4},{5024,10},{5031,10},{5097,3},{5097,4},{5125,3},{5125,4},{5245,31},{5246,0},{5298,18},{5302,18},{5319,31},{5357,31},
		{5380,11},{5380,13},{5394,2},{5394,5},{5394,7},{5395,16},{5395,18},{5396,31},{5397,31},{5484,3},{5484,4},{5491,3},{5491,4},{5491,7},
		{5571,0},{5571,3},{5571,4},{5571,7},{5571,11},{5571,31},{5585,18},{5603,9},{5603,31},{5605,31},{5625,2},{5625,3},{5625,4},{5626,13},
		{5741,2},{5741,3},{5741,4},{5742,3},{5742,4},{5742,9},{5742,11},{5742,12},{5742,16},{5743,3},{5743,4},{5743,9},{5743,11},{5743,12},
		{5743,16},{5745,3},{5745,4},{5745,18},{5746,3},{5746,4},{5747,3},{5747,4},{5747,10},{5793,9},{5797,3},{5797,4},{5797,11},{5797,12},
		{5797,16},{5798,2},{5798,10},{5838,31},{5839,31},{5840,31},{5841,31},{5842,31},{5848,2},{5848,4},{5848,9},{5848,10},{5848,12},{5848,13},
		{5848,20},{5848,21},{5851,2},{5851,16},{5851,18},{5853,10},{6301,3},{6301,4},{6653,16},{6655,3},{6655,4},{520199,3},{520199,4},{520320,7},
		{520332,3},{520332,4},{520435,12},{520595,2},{520595,3},{520595,4},{520668,31},{520669,31},{520784,3},{520784,4},{520784,5},{520791,2},
		{520808,31},{520809,31},{524286,31}};
	static final int Scania_ECM_SPNFMI[][] = {{46,1},{46,19},{51,3},{51,4},{51,7},{51,8},{51,9},{91,2},{91,9},{91,10},{91,19},{94,0},{98,2},{98,3},{98,4},{98,10},
		{100,1},{100,2},{100,3},{100,4},{100,13},{100,16},{100,17},{100,18},{102,0},{102,1},{102,3},{102,4},{102,7},{102,8},
		{102,9},{102,10},{102,15},{102,16},{102,18},{102,20},{102,21},{103,0},{103,2},{103,3},{103,4},{103,5},{103,9},{103,20},
		{103,21},{105,0},{105,1},{105,2},{105,3},{105,4},{105,9},{105,15},{105,16},{105,17},{105,20},{105,21},{107,1},{107,2},
		{108,2},{108,3},{108,4},{108,15},{108,16},{108,20},{108,21},{110,0},{110,1},{110,2},{110,3},{110,4},{110,8},{110,9},
		{110,10},{110,16},{110,17},{110,18},{110,20},{110,21},{111,1},{111,3},{111,4},{131,2},{131,3},{131,4},{131,7},{131,8},
		{131,9},{131,10},{131,15},{131,16},{131,18},{131,20},{131,21},{132,0},{132,1},{132,2},{132,3},{132,4},{132,5},{132,7},
		{156,0},{156,1},{156,2},{156,3},{156,4},{156,8},{156,9},{156,18},{167,2},{167,3},{167,4},{167,5},{167,9},{167,10},
		{168,0},{168,1},{168,4},{168,5},{168,15},{168,16},{168,17},{168,18},{171,0},{171,1},{171,2},{171,3},{171,4},{171,7},
		{171,9},{171,15},{171,16},{171,17},{171,18},{171,19},{171,20},{171,21},{172,2},{172,3},{172,4},{172,7},{175,3},
		{175,4},{175,11},{188,14},{190,0},{190,10},{190,15},{190,16},{190,20},{234,2},{234,19},{532,14},{558,2},{559,2},
		{559,9},{559,10},{597,2},{598,2},{598,7},{598,19},{636,1},{636,2},{636,3},{636,4},{636,5},{636,7},{636,8},{641,2},
		{641,4},{641,5},{641,7},{641,8},{641,9},{641,10},{641,11},{641,12},{641,13},{641,15},{641,16},{641,19},{645,19},
		{651,1},{651,2},{651,4},{651,5},{651,6},{651,7},{651,8},{651,10},{651,13},{651,15},{651,16},{651,18},{651,20},
		{651,21},{652,1},{652,2},{652,4},{652,5},{652,6},{652,7},{652,8},{652,10},{652,13},{652,15},{652,16},{652,18},
		{652,20},{653,1},{653,2},{653,4},{653,5},{653,6},{653,7},{653,8},{653,10},{653,13},{653,15},{653,16},{653,18},
		{653,20},{654,1},{654,2},{654,4},{654,5},{654,6},{654,7},{654,8},{654,10},{654,13},{654,15},{654,16},{654,18},
		{654,20},{655,1},{655,2},{655,4},{655,5},{655,6},{655,7},{655,8},{655,10},{655,13},{655,15},{655,16},{655,18},
		{655,20},{656,1},{656,2},{656,5},{656,6},{656,7},{656,8},{656,13},{656,15},{656,16},{656,18},{656,20},{657,1},
		{657,2},{657,5},{657,6},{657,7},{657,8},{657,13},{657,15},{657,16},{657,18},{657,20},{658,1},{658,2},{658,5},
		{658,6},{658,7},{658,8},{658,13},{658,15},{658,16},{658,18},{658,20},{677,0},{677,2},{677,3},{677,4},{677,5},
		{677,7},{677,19},{723,2},{723,4},{723,7},{723,8},{723,9},{723,10},{723,14},{974,0},{974,1},{986,2},{986,3},
		{986,4},{986,5},{986,7},{1086,2},{1108,14},{1110,14},{1239,7},{1322,7},{1323,7},{1324,7},{1325,7},{1326,7},
		{1327,7},{1442,2},{1442,3},{1442,5},{1442,7},{1442,8},{1442,10},{1443,1},{1443,6},{1483,2},{1483,2},{1483,2},
		{1483,8},{1483,8},{1483,9},{1483,11},{1483,12},{1484,9},{1484,10},{1484,16},{1484,18},{1484,19},{1484,20},{1484,21},
		{1485,16},{1485,18},{1569,14},{1639,3},{1639,4},{1639,8},{1675,2},{1675,9},{1675,12},{1675,13},{1675,19},{1761,1},
		{1761,2},{1761,3},{1761,4},{1761,5},{1761,6},{1761,8},{1761,10},{1761,17},{1761,18},{1761,19},{2609,2},{2609,3},
		{2609,4},{2609,5},{2791,2},{2791,3},{2791,4},{2791,5},{2791,7},{2791,8},{2791,10},{2791,11},{2791,16},{2791,20},
		{2791,21},{2797,2},{2797,3},{2797,4},{2797,5},{2797,8},{2798,2},{2798,3},{2798,4},{2798,8},{2858,13},{2859,13},
		{2860,13},{2861,13},{2862,13},{3031,0},{3031,1},{3031,3},{3031,4},{3216,2},{3216,4},{3216,5},{3216,6},{3216,7},
		{3216,8},{3216,8},{3216,9},{3216,10},{3216,17},{3216,18},{3216,19},{3216,19},{3216,20},{3226,2},{3226,3},{3226,4},
		{3226,4},{3226,5},{3226,5},{3226,6},{3226,7},{3226,8},{3226,9},{3226,10},{3226,17},{3226,18},{3226,19},{3226,20},
		{3241,2},{3241,3},{3241,4},{3241,5},{3241,6},{3241,8},{3241,10},{3241,11},{3241,12},{3241,16},{3241,18},{3241,19},
		{3242,0},{3242,3},{3242,4},{3242,5},{3242,6},{3242,7},{3242,9},{3242,10},{3242,11},{3242,12},{3242,16},{3242,19},
		{3245,3},{3245,4},{3245,6},{3245,19},{3246,2},{3246,3},{3246,4},{3246,5},{3246,6},{3246,9},{3246,11},{3246,12},
		{3246,15},{3246,16},{3246,19},{3249,3},{3249,4},{3251,2},{3251,3},{3251,4},{3251,7},{3251,8},{3251,9},{3275,0},
		{3275,1},{3275,2},{3275,3},{3275,4},{3275,5},{3275,6},{3275,9},{3275,11},{3275,12},{3275,19},{3279,2},{3279,8},
		{3279,9},{3279,19},{3340,1},{3340,3},{3340,4},{3340,7},{3340,9},{3340,10},{3340,15},{3340,16},{3340,20},{3340,21},
		{3360,0},{3360,1},{3360,2},{3360,3},{3360,4},{3360,5},{3360,6},{3360,7},{3360,9},{3360,10},{3360,12},{3360,16},
		{3360,19},{3361,2},{3361,3},{3361,4},{3361,5},{3361,6},{3361,10},{3362,0},{3362,1},{3362,2},{3363,0},{3363,2},
		{3363,3},{3363,4},{3363,5},{3363,6},{3363,8},{3363,15},{3363,16},{3363,17},{3363,18},{3464,2},{3464,3},{3464,4},
		{3464,5},{3464,6},{3464,7},{3464,8},{3471,2},{3471,3},{3471,4},{3471,5},{3471,9},{3471,10},{3472,2},{3472,3},
		{3472,4},{3472,5},{3472,9},{3480,2},{3480,3},{3480,4},{3480,8},{3480,9},{3480,10},{3485,1},{3485,2},{3485,3},
		{3485,4},{3485,7},{3485,9},{3485,18},{3485,20},{3515,2},{3516,2},{3563,11},{3563,15},{3563,17},{3607,2},{3607,8},
		{3673,3},{3673,4},{3822,3},{3822,4},{3822,7},{3822,8},{3822,12},{3822,13},{3822,16},{3822,19},{3822,20},{3822,21},
		{3936,2},{3936,6},{3936,10},{4090,0},{4090,11},{4090,16},{4095,2},{4096,2},{4201,2},{4201,4},{4201,7},{4201,8},
		{4201,9},{4201,10},{4202,2},{4225,2},{4301,2},{4301,3},{4301,4},{4301,5},{4301,6},{4301,7},{4301,9},{4301,10},
		{4334,0},{4334,1},{4334,2},{4334,3},{4334,4},{4334,8},{4334,10},{4334,15},{4334,16},{4334,17},{4337,1},{4337,3},
		{4341,2},{4341,3},{4341,4},{4341,5},{4341,6},{4343,2},{4343,3},{4343,4},{4343,5},{4343,6},{4345,2},{4345,3},{4345,4},
		{4345,5},{4345,6},{4347,2},{4347,3},{4347,4},{4347,5},{4347,6},{4374,0},{4374,1},{4374,2},{4374,4},{4374,5},{4374,6},
		{4374,7},{4374,16},{4374,17},{4427,2},{4782,0},{4782,16},{4809,2},{4809,7},{4809,8},{4809,9},{4809,16},{4809,18},
		{4810,9},{4810,18},{4814,2},{4814,3},{4814,4},{4814,7},{4814,8},{4814,10},{5285,1},{5401,2},{5401,3},{5401,4},
		{5401,5},{5419,2},{5419,3},{5419,5},{5419,6},{5419,9},{5419,10},{5419,11},{5419,12},{5419,13},{5419,14},{5419,16},
		{5419,19},{5419,31},{5421,3},{5421,4},{5421,5},{5421,6},{5543,2},{5543,3},{5543,4},{5543,5},{5543,6},{5543,7},
		{5543,12},{5543,13},{5543,16},{5543,19},{5543,21},{5706,3},{5706,4},{5706,6},{5743,2},{5743,4},{5743,5},{5745,3},
		{5745,5},{5841,1}};
	// --, 150202 bwk

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
		// ++, 150202 bwk : 150128 HHI ¿”«ı¡ÿ ø‰√ª
		"Communication Timeout between EHCU and TCU",
		"Communication Timeout between EHCU and MCU",
		"Communication Timeout between EHCU and Working Joystick",
		"Communication Timeout between EHCU and Steering Joystick",
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
		"Bucket Dump EPPR Valve Input Value Above Normal Operation Range",
		"Bucket Dump EPPR Valve Input Value Below Normal Operation Range",
		"Bucket Dump EPPR Valve Input Current Below Normal or Open Circuit",
		"Bucket Dump EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Bucket Dump EPPR Valve Block Parameter Invalid",
		"Aux. Up EPPR Valve Input Value Above Normal Operation Range",
		"Aux. Up EPPR Valve Input Value Below Normal Operation Range",
		"Aux. Up EPPR Valve Input Current Below Normal or Open Circuit",
		"Aux. Up EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Aux. Up EPPR Valve Block Parameter Invalid",
		"Aux. Down EPPR Valve Input Data Above Normal Operation Range",
		"Aux. Down EPPR Valve Input Data Below Normal Operation Range",
		"Aux. Down EPPR Valve Input Current Below Normal or Open Circuit",
		"Aux. Down EPPR Valve Input Current Above Normal or Grounded Circuit",
		"Aux. Down EPPR Valve Block Parameter Invalid",
		"EHCU Sensor Power Voltage High",
		"EHCU Sensor Power Voltage Low",
		"EHCU Sensor Power Voltage Above Normal or Shorted to High Source",
		"EHCU Sensor Power Voltage Below Normal or Shorted to Low Source",
		"EHCU Power Voltage High",
		"EHCU Power Voltage Low",
		"EHCU Safety CPU Error",
		"Armrest Switch Signal Error",
		"Steering Pilot Pressure Sensor Data Above Normal Range",
		"Steering Pilot Pressure Sensor Data Below Normal Range",
		"Steering Proportional Valve Moving Position Error",
		"Steering Proportional Valve Start Position Error",
		// --, 150202 bwk
		"Not define."
	};
	static final String textACUErrCode[] = {
		"Ambient temperature sensor open",
		"Ambient temperature sensor short",
		"In-cab temperature sensor open",
		"In-cab temperature sensor short",
		"Evap. temperature sensor open",
		"Evap. temperature sensor short",
		"Mode 1 actuator open/short",
		"Mode 1 actuator drive circuit malfunction",
		"Intake actuator open/short",
		"Intake actuator drive circuit malfunction",
		"Temperature actuator open/short",
		"Temperature actuator drive circuit malfunction",
		"Duct temperature sensor open",
		"Duct temperature sensor short",
		"Water Valve Sensor Error",
		"G.P.S Circuit Error"
	};
	
	// ++, 150202 bwk : 150128 HHI ¿”«ı¡ÿ ø‰√ª
	static final String textTCUErrCode[] = 
	{
		"Logical Error At Direction Select Signal 3Rd Shift Lever ",
		"Logical Error At Gear Range Signal",
		"Logical Error At Direction Select Signal ",
		"Logical Error At Engine Derating Device ",
		"Logical Error At Parkbrake Status",
		"Logical Error At Direction Select Signal 2. Shift Lever",
		"Logical Error At Axle Connection",
		"S.C. To Ground At Customer Specific Function No. 1",
		"S.C. To Battery Voltage At Customer Specific Function No. 1",
		"O.C. At Customer Specific Function No. 1",
		"S.C. To Ground At Customer Specific Function No. 2",
		"S.C. To Battery Voltage At Customer Specific Function No. 2",
		"O.C. At Customer Specific Function No. 2",
		"S.C. To Ground At Customer Specific Function No. 3",
		"S.C. To Battery Voltage At Customer Specific Function No. 3 ",
		"O.C. At Customer Specific Function No. 3",
		"S.C. To Battery Voltage At Clutch Cutoff Input / Inchpedal Input",
		"S.C. To Ground Or O.C. At Clutch Cutoff Input / Inchpedal Input",
		"S.C. To Battery Voltage At Load Sensor Input",
		"S.C. To Ground Or O.C. At Load Sensor Input",
		"S.C. To Battery Voltage Or O.C. At Transmission Sump Temperature Sensor Input",
		"S.C. To Ground At Transmission Sump Temperature Sensor Input",
		"S.C. To Battery Voltage Or O.C. At Retarder / Torqueconverter Temperature Sensor Input",
		"S.C. To Ground At Retarder /Torqueconverter Temperature Sensor Input",
		"S.C. To Battery Voltage Or O.C. At Parking Brake Sensor Input ",
		"S.C. To Ground Parking Brake Sensor Input",
		"Inchsensor-Signal Mismatch",
		"S.C. To Battery Voltage Or O.C. At Dlm Traction Adjust Dashboard Device Input",
		"S.C. To Ground Dlm Traction Adjust Dashboard Device Input",
		"S.C. To Battery Voltage Or O.C. At Dlm Steering Angle Sensor Input",
		"S.C. To Ground Dlm Steering Angle Sensor Input",
		"S.C. To Battery Voltage Or O.C. At Engine Speed Input",
		"S.C. To Ground At Engine Speed Input",
		"Logical Error At Engine Speed Input",
		"S.C. To Battery Voltage Or O.C. At Turbine Speed Input",
		"S.C. To Ground At Turbine Speed Input",
		"Logical Error At Turbine Speed Input",
		"S.C. To Battery Voltage Or O.C. At Internal Speed Input",
		"S.C. To Ground At Internal Speed Input",
		"Logical Error At Internal Speed Input",
		"S.C. To Battery Voltage Or O.C. At Output Speed Input",
		"S.C. To Ground At Output Speed Input",
		"Logical Error At Output Speed Input",
		"Turbine Speed Zero Doesn°ØT Fit To Other Speed Signals",
		"Output Speed Zero Doesn°ØT Fit To Other Speed Signals",
		"Gear Range Restriction Signal",
		"Declutch Modulation Selection Signal",
		"Fmr1 Timeout",
		"Fmr2 Timeout",
		"Eamodul1 Timeout",
		"Abs Timeout",
		"Dct1 Timeout",
		"Jss Timeout",
		"Engine Conf Timeout",
		"Eec1 Timeout",
		"Eec3 Timeout",
		"Engine Speed Limit Function Does Not Work Properly",
		"Parkbrake Status  Signal",
		"Shift Quality Sel Signal",
		"Auto Downshift Signal",
		"Manual Downshift Signal",
		"Cco Request Signal",
		"Shift Lever Signal",
		"Additional Brake Status Signal",
		"Aeb Request Signal",
		"Pto Torque Signal",
		"Driving Mode Signal",
		"Starting Gear Signal",
		"Enginge Torque Signal",
		"Reference Engine Torque Signal",
		"Actual Engine Torque Signal",
		"Nom Friction Torque Signal",
		"Eec2 Timeout",
		"S.C. To Battery Voltage At Clutch K1",
		"S.C. To Ground At Clutch K1",
		"O.C. At Clutch K1",
		"S.C. To Battery Voltage At Clutch K2",
		"S.C. To Ground At Clutch K2",
		"O.C. At Clutch K2",
		"S.C. To Battery Voltage At Clutch K3",
		"S.C. To Ground At Clutch K3",
		"O.C. At Clutch K3",
		"S.C. To Battery Voltage At Converter Clutch",
		"S.C. To Ground At Converter Clutch",
		"O.C. At Converter Clutch",
		"S.C. To Ground At Engine  Derating Device",
		"S.C. To Battery Voltage At Engine  Derating Device",
		"O.C. At Engine  Derating Device",
		"S.C. To Battery Voltage At Clutch K4",
		"S.C. To Ground At Clutch K4",
		"O.C. At Clutch K4",
		"S.C. To Battery Voltage At Clutch Kv",
		"S.C. To Ground At Clutch Kv",
		"O.C. At Clutch Kv",
		"S.C. To Battery Voltage At Clutch Kr",
		"S.C. To Ground At Clutch Kr",
		"O.C. At Clutch Kr",
		"S.C. To Ground At Dlm Transversal Output",
		"S.C. To Battery Voltage At Dlm Transversal Output",
		"O.C. At Dlm Transversal Output",
		"S.C. To Ground At Dlm Indicator Lamp Output",
		"S.C. To Battery Voltage At Dlm Indicator Lamp Output",
		"O.C. Dlm Indicator Lamp Output",
		"S.C. To Ground At Relay Reverse Warning Alarm",
		"S.C. To Battery Voltage At Relay Reverse Warning Alarm",
		"O.C. At Relay Reverse Warning Alarm",
		"S.C. To Ground At Relay Starter Interlock",
		"S.C. To Battery Voltage At Relay Starter Interlock",
		"O.C. At Relay Starter Interlock",
		"S.C. To Ground At Park Brake Solenoid",
		"S.C. To Battery Voltage At Park Brake Solenoid",
		"O.C. At Park Brake Solenoid",
		"S.C. To Ground At Converter Lock Up Clutch Solenoid",
		"O.C. At Converter Lock Up Clutch Solenoid",
		"S.C. To Battery Voltage At Converter Lock Up Clutch Solenoid",
		"S.C. To Ground At Retarder Solenoid",
		"O.C. At Retarder Solenoid",
		"S.C. To Battery Voltage At Retarder Solenoid",
		"S.C. To Ground At Difflock Or Axle Connection Solenoid",
		"S.C. To Battery Voltage At Difflock Or Axle Connection Solenoid",
		"O.C. At Difflock Or Axle Connection Solenoid",
		"S.C. To Ground At Warning Signal Output",
		"O.C. At Warning Signal Output",
		"S.C. To Battery Voltage At Warning Signal Output",
		"S.C. To Ground At Customer Specific Function No. 4",
		"S.C. To Battery Voltage At Customer Specific Function No. 4",
		"O.C. At Customer Specific Function No. 4",
		"S.C. To Ground At Customer Specific Function No. 5",
		"S.C. To Battery Voltage At Customer Specific Function No. 5",
		"O.C. At Customer Specific Function No. 5",
		"S.C. To Ground At Customer Specific Function No. 6",
		"S.C. To Battery Voltage At Customer Specific Function No. 6",
		"O.C. At Customer Specific Function No. 6",
		"Slippage At Clutch K1",
		"Slippage At Clutch K2",
		"Slippage At Clutch K3",
		"Slippage At Clutch K4",
		"Slippage At Clutch Kv",
		"Slippage At Clutch Kr",
		"Overtemp Sump",
		"Overtemp Retarder ",
		"Overspeed Engine ",
		"Differential Pressure Oil Filter ",
		"Slippage At Converter Lockup Clutch",
		"S.C. To Ground At Engine Brkae Solenoid",
		"S.C. To Battery Voltage At Engine Brake",
		"O.C. At Engine Brake",
		"Engine Torque Or Engine Power Overload",
		"Transmission Output Torque Overload",
		"Transmission Input Torque Overload",
		"Overtemp Converter Output ",
		"S.C. To Ground At Joystick Status Indicator ",
		"S.C. To Battery Voltage At Joystick Status Indicator",
		"O.C. At Joystick Status Indicator",
		"S.C. To Battery Voltage At Power Supply For Sensors",
		"S.C. To Ground At Power Supply For Sensors",
		"Low Voltage At Battery",
		"High Voltage At Battery",
		"Error At Valve Power Supply Vps1",
		"Error Valve Power Supply Vps2",
		"S.C. To Battery Voltage At Speedometer Output",
		"S.C. To Ground Or O.C. At Speedometer Output",
		"S.C. To Battery Voltage At Display Output",
		"S.C. To Ground At Display Output",
		"Dispid1_Timeout",
		"Illegal Id Request Via Can",
		"General Eeprom Fault",
		"Configuration Lost",
		"Application Error",
		"Limp Home Request",
		"Clutch Failure",
		"Clutch Adjustment Data Lost",
		"Substitute Clutch Control",
		"Not Define."
	};
	static final String textCumminsECMErrCode[] = {
		"EGR Valve Position - Data erratic, intermittent or incorrect ",
		"EGR Valve Position Circuit - Voltage below normal, or shorted to low source ",
		"Engine Diesel Particulate Filter Intake Pressure - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Wheel-Based Vehicle Speed - Data erratic, intermittent or incorrect",
		"Wheel-Based Vehicle Speed - Abnormal update rate ",
		"Wheel-Based Vehicle Speed Sensor Circuit tampering has been detected - Abnormal rate of change",
		"Wheel-Based Vehicle Speed - Received Network Data In Error",
		"Accelerator Pedal or Lever Position Sensor 1 - Data valid but above normal operational range - Most Severe Level",
		"Accelerator Pedal or Lever Position 1 Sensor Circuit Frequency - Data valid but below normal operating Range",
		"Accelerator Pedal or Lever Position Sensor 1 - Data erratic, intermittent or incorrect",
		"Accelerator Pedal or Lever Position Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Accelerator Pedal or Lever Position Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"SAE J1939 Multiplexed Accelerator Pedal or Lever Sensor System - Abnormal update rate",
		"SAE J1939 Multiplexed Accelerator Pedal or Lever Sensor System - Received Network Data In Error",
		"Auxiliary Alternate Torque Validation Switch - Data erratic, intermittent or incorrect",
		"Engine Fuel Delivery Pressure - Data Valid but Above Normal Operational Range - Most Severe Level ",
		"Fuel Delivery Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Fuel Delivery Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Fuel Pump Delivery Pressure - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Fuel Pump Delivery Pressure - Data Valid But Below Normal Operating Range - Least Severe Level",
		"Fuel Pump Delivery Pressure - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Fuel Filter Differential Pressure - Data Valid But Above Normal Operating Range - Moderately Severe ",
		"Water in Fuel Indicator Sensor Circuit - Voltage above normal, or shorted to high source",
		"Water in Fuel Indicator Sensor Circuit - Voltage below normal, or shorted to low source",
		"Water in Fuel Indicator - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Water in Fuel Indicator - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Engine Oil Level - Data valid but above normal operational range - Most Severe Level",
		"Engine Oil Level - Data valid but below normal operational range - Most Severe Level",
		"Engine Oil Level - Data Valid But Below Normal Operating Range - Least Severe Level ",
		"Engine Oil Rifle Pressure - Data valid but below normal operational range - Most Severe Level",
		"Engine Oil Rifle Pressure - Data erratic, intermittent or incorrect",
		"Engine Oil Rifle Pressure 1 Sensor Circuit - Voltage above normal, or shorted to high source",
		"Engine Oil Rifle Pressure 1 Sensor Circuit - Voltage below normal, or shorted to low source",
		"Engine Oil Rifle Pressure - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Crankcase Pressure - Data valid but above normal operational range - Most Severe Level",
		"Crankcase Pressure - Data erratic, intermittent or incorrect",
		"Crankcase Pressure Circuit - Voltage above normal, or shorted to high source",
		"Crankcase Pressure Circuit - Voltage below normal, or shorted to low source",
		"Crankcase Pressure - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Crankcase Pressure - Data Valid But Above Normal Operating Range - Moderately Severe Level ",
		"Intake Manifold 1 Pressure - Data erratic, intermittent or incorrect ",
		"Intake Manifold 1 Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Intake Manifold 1 Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Intake Manifold 1 Pressure - Abnormal rate of change ",
		"Intake Manifold 1 Pressure - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Intake Manifold 1 Pressure - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Turbocharger 1 Speed - Data erratic, intermittent or incorrect",
		"Turbocharger 1 Speed - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Turbocharger 1 Speed - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Turbocharger 1 Speed - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Engine Turbocharger Lube Oil Pressure - Data Valid But Below Normal Operating Range - Moderately Severe Level ",
		"Intake Manifold 1 Temperature - Data valid but above normal operational range - Most Severe Level",
		"Intake Manifold 1 Temperature - Data erratic, intermittent or incorrect",
		"Intake Manifold 1 Temperature Sensor Circuit - Voltage above normal, or shorted to high source ",
		"Intake Manifold 1 Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Intake Manifold 1 Temperature - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Intake Manifold 1 Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Intake Manifold 1 Temperature - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Engine Air Filter Differential Pressure - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Engine Air Filter Differential Pressure - Data Valid But Above Normal Operating Range - Moderately Severe Level ",
		"Barometric Pressure - Data erratic, intermittent or incorrect ",
		"Barometric Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Barometric Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Coolant Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Coolant Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Coolant Pressure - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Engine Coolant Temperature - Data valid but above normal operational range - Most Severe Level",
		"Engine Coolant Temperature - Data erratic, intermittent or incorrect",
		"Engine Coolant Temperature 1 Sensor Circuit - Voltage above normal, or shorted to high source",
		"Engine Coolant Temperature 1 Sensor Circuit - Voltage below normal, or shorted to low source",
		"Engine Coolant Temperature - Special Instructions",
		"Engine Coolant Temperature - Data Valid But Above Normal Operating Range - Least Severe Level ",
		"Engine Coolant Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Engine Coolant Temperature - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Engine Coolant Temperature - Condition Exists ",
		"Engine Coolant Temperature - Condition Exists",
		"Coolant Level - Data valid but below normal operational range - Most Severe Level ",
		"Coolant Level - Data erratic, intermittent or incorrect ",
		"Coolant Level Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Coolant Level Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"Coolant Level - Data Valid But Below Normal Operating Range - Least Severe Level",
		"Coolant Level - Data Valid But Below Normal Operating Range - Least Severe Level",
		"Coolant Level - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Coolant Level - Data Valid But Below Normal Operating  Range - Moderately Severe Level",
		"Injector Metering Rail 1 Pressure - Data valid but above normal operational range - Most Severe Level",
		"Injector Metering Rail 1 Pressure - Data valid but below normal operational range - Most Severe Level",
		"Injector Metering Rail 1 Pressure - Data erratic, intermittent or incorrect ",
		"Injector Metering Rail 1 Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Injector Metering Rail 1 Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Injector Metering Rail 1 Pressure - Mechanical system not responding or out of adjustment",
		"Injector Metering Rail 1 Pressure - Data Valid But Above Normal Operating Range - Least Severe Level ",
		"Injector Metering Rail 1 Pressure - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Injector Metering Rail 1 Pressure - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Battery 1 Voltage - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Battery 1 Voltage - Data Valid But Below Normal Operating Range - Least Severe Level ",
		"Battery 1 Voltage - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Ambient Air Temperature - Data erratic, intermittent or incorrect",
		"Ambient Air Temperature Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Ambient Air Temperature Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"Ambient Air Temperature - Abnormal update rate ",
		"Ambient Air Temperature - Received Network Data In Error",
		"Engine Fuel Temperature - Data valid but above normal operational range - Most Severe Level",
		"Engine Fuel Temperature - Data erratic, intermittent or  incorrect",
		"Engine Fuel Temperature Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Engine Fuel Temperature Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"Engine Fuel Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Engine Oil Temperature - Data valid but above normal operational range - Most Severe Level",
		"Engine Oil Temperature - Data erratic, intermittent or incorrect",
		"Engine Oil Temperature Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Engine Oil Temperature Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"Engine Oil Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Engine Speed At Idle - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Engine Speed At Idle - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Engine Crankshaft Speed/Position - Data valid but above normal operational range - Most Severe Level",
		"Engine Crankshaft Speed/Position - Data erratic, intermittent or incorrect",
		"Engine Crankshaft Speed / Position - Data erratic, intermittent or incorrect ",
		"Engine Crankshaft Speed/Position - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Engine Crankshaft Speed/Position - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Transmission Output Shaft Speed - Abnormal update rate ",
		"Transmission Output Shaft Speed - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Transmission Output Shaft Speed - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Transmission Output Shaft Speed - Received Network Data In Error",
		"Vehicle Identification Number - Data erratic, intermittent or incorrect",
		"Vehicle Identification Number - Out of Calibration",
		"Vehicle Identification Number - Condition Exists",
		"Real Time Clock - Data erratic, intermittent or incorrect",
		"Exhaust Gas Recirculation Differential Pressure - Data erratic, intermittent or incorrect",
		"Exhaust Gas Recirculation Differential Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Exhaust Gas Recirculation Differential Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Exhaust Gas Recirculation Temperature - Data erratic, intermittent or incorrect ",
		"Exhaust Gas Recirculation Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Exhaust Gas Recirculation Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Exhaust Gas Recirculation Temperature - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Exhaust Gas Recirculation Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Auxiliary Temperature Sensor Input 1 Circuit - Voltage above normal, or shorted to high source",
		"Auxiliary Temperature Sensor Input 1 Circuit - Voltage below normal, or shorted to low source",
		"Auxiliary Temperature Sensor Input 1 - Special Instructions ",
		"Auxiliary Temperature Sensor Input 2 Circuit - Voltage above normal, or shorted to high source",
		"Auxiliary Temperature Sensor Input 2 Circuit - Voltage below normal, or shorted to low source",
		"Brake Pedal Position - Data erratic, intermittent or incorrect ",
		"Accelerator Pedal or Lever Idle Validation Switch - Data erratic, intermittent or incorrect",
		"Accelerator Pedal or Lever Idle Validation Switch - Abnormal update rate ",
		"Accelerator Pedal or Lever Idle Validation Switch Circuit - Out of Calibration",
		"Accelerator Pedal or Lever Idle Validation Switch - Received Network Data In Error",
		"Anti-Lock Braking (ABS) Controller - Abnormal update rate",
		"Anti-Lock Braking (ABS) Active - Condition Exists ",
		"Cruise Control Enable Switch - Data erratic, intermittent or incorrect",
		"Cruise Control Enable Switch - Mechanical system not responding or out of adjustment",
		"Cruise Control Enable Switch - Out of Calibration ",
		"Brake Switch Circuit - Voltage above normal, or shorted to high source",
		"Brake Switch Circuit - Voltage below normal, or shorted to low source",
		"Cruise Control Set Switch - Data erratic, intermittent or incorrect",
		"Auxiliary Intermediate (PTO) Speed Switch Validation - Data erratic, intermittent or incorrect",
		"Engine Magnetic Speed/Position Lost Both of Two Signals - Data erratic, intermittent or incorrect",
		"System - Received Network D Proprietary Datalink Error (OEM/Vehicle Datalink) - Abnormal update rate",
		"Start Enable Device 1 Circuit (Ether Injection) - Voltage above normal, or shorted to high source",
		"Start Enable Device 1Circuit (Ether Injection) - Voltage below normal, or shorted to low source",
		"Start Enable Device 1 Canister Empty (Ether Injection) - Data Valid But Below Normal Operating Range",
		"Engine Control Module Critical Internal Failure - Bad intelligent device or component",
		"At Least One Unacknowledged Most Severe Fault - Condition Exists",
		"Engine Control Module Calibration Memory - Bad intelligent device or component",
		"Electronic Fuel Injection Control Valve Circuit - Condition Exists ",
		"J1939 Network #1 - Data erratic, intermittent or incorrect",
		"SAE J1939 Multiplexing PGN Timeout Error - Abnormal update rate ",
		"SAE J1939 Multiplexing Configuration Error - Out of Calibration",
		"Auxiliary Commanded Dual Output Shutdown - Special Instructions",
		"VGT Actuator Driver Circuit (Motor) - Mechanical system not responding or out of adjustment",
		"VGT Actuator Driver Circuit - Abnormal update rate",
		"VGT Actuator Driver Circuit - Root Cause Not Known",
		"VGT Actuator Controller - Bad intelligent device or component ",
		"VGT Actuator Controller - Out of Calibration",
		"VGT Actuator Driver Over Temperature (Calculated) - Data Valid But Above Normal Operating Range - Least Severe Level",
		"VGT Actuator Driver Circuit - Condition Exists",
		"External Speed Command Input (Multiple Unit Synchronization) - Data erratic, intermittent or incorrect",
		"Fan Control Circuit - Voltage above normal, or shorted to high source ",
		"Fan Control Circuit - Voltage below normal, or shorted to low source ",
		"Engine Exhaust Back Pressure Regulator Control Circuit - Voltage Above Normal, or Shorted to High Source",
		"Engine Exhaust Back Pressure Regulator Control Circuit - Voltage Below Normal, or Shorted to Low Source",
		"Engine Exhaust Back Pressure Regulator Control Circuit - Current Below Normal or Open Circuit",
		"Injector Solenoid Driver Cylinder 1 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 1 - Mechanical system not responding or out of adjustment",
		"Injector Solenoid Driver Cylinder 2 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 2 - Mechanical system not responding or out of adjustment",
		"Injector Solenoid Driver Cylinder 3 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 3 - Mechanical system not responding or out of adjustment",
		"Injector Solenoid Driver Cylinder 4 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 4 - Mechanical system not responding or out of adjustment",
		"Injector Solenoid Driver Cylinder 5 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 5 - Mechanical system not responding or out of adjustment",
		"Injector Solenoid Driver Cylinder 6 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 6 - Mechanical system not responding or out of adjustment",
		"Injector Solenoid Driver Cylinder 7 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 8 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 9 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 10 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 11 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 12 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 13 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 14 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 15 Circuit - Current below normal or open circuit",
		"Injector Solenoid Driver Cylinder 16 Circuit - Current below normal or open circuit",
		"Starter Relay Driver Circuit - Voltage above normal, or shorted to high source",
		"Starter Relay Driver Circuit - Voltage below normal, or shorted to low source",
		"Auxiliary PWM Driver 1 Circuit - Voltage above normal, or shorted to high source",
		"Auxiliary PWM Driver 1 Circuit - Voltage below normal, or shorted to low source",
		"Auxiliary Input/Output 1 - Special Instructions ",
		"Auxiliary Input/Output 2 Circuit - Voltage above normal, or shorted to high source",
		"Auxiliary Input/Output 2 Circuit - Current below normal or open circuit",
		"Auxiliary Input/Output 2 Circuit - Current above normal or grounded circuit",
		"Auxiliary Input/Output 3 Circuit - Voltage above normal, or shorted to high source",
		"Engine Camshaft Speed / Position Sensor - Data erratic, intermittent or incorrect",
		"Engine Speed / Position Camshaft and Crankshaft Misalignment - Mechanical system not responding or out of adjustment",
		"Engine Intake Air Heater 1 Circuit - Voltage above normal, or shorted to high source",
		"Engine Intake Air Heater 1 Circuit - Voltage below normal, or shorted to low source",
		"Transmission Output Retarder - Abnormal update rate",
		"Crankcase Breather Filter Heater Circuit - Voltage above normal, or shorted to high source",
		"Crankcase Breather Filter Heater Circuit - Voltage below normal, or shorted to low source",
		"Remote Accelerator Pedal or Lever Position Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Remote Accelerator Pedal or Lever Position Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"SAE J1939 Multiplexing Remote Accelerator Pedal or Lever Position Sensor",
		"Engine Brake Actuator Driver 1 Circuit - Voltage above normal, or shorted to high source",
		"Engine Brake Actuator Driver 1 Circuit - Voltage below normal, or shorted to low source",
		"Engine Brake Actuator Driver Output 2 Circuit - Voltage above normal, or shorted to high source",
		"Engine Brake Actuator Driver Output 2 Circuit - Voltage below normal, or shorted to low source",
		"Electric Lift Pump for Engine Fuel Supply Circuit - Voltage above normal, or shorted to high source",
		"Electric Lift Pump for Engine Fuel Supply Circuit - Voltage below normal, or shorted to low source",
		"Engine Wait to Start Lamp - Mechanical system not responding or out of adjustment",
		"Engine Wait to Start Lamp - Abnormal update rate ",
		"Engine Wait to Start Lamp - Received Network Data In Error",
		"Engine Wait to Start Lamp - Condition Exists ",
		"Engine Protection System Approaching Shutdown - Data valid but above normal operational range - Most ",
		"Engine Brake Actuator Driver 3 Circuit - Voltage above normal, or shorted to high source",
		"Engine Brake Actuator Driver Output 3 Circuit - Voltage below normal, or shorted to low source",
		"Engine Turbocharger 1 Boost Pressure - Mechanical system not responding or out of adjustment",
		"Engine ECU Temperature - Data erratic, intermittent or incorrect ",
		"Engine ECU Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Engine ECU Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Turbocharger 1 Compressor Intake Temperature - Data erratic, intermittent or incorrect",
		"Turbocharger 1 Compressor Intake Temperature Circuit - Voltage above normal, or shorted to high source",
		"Turbocharger 1 Compressor Intake Temperature Circuit - Voltage below normal, or shorted to low source",
		"Turbocharger 1 Compressor Intake Temperature Sensor - Abnormal update rate",
		"Turbocharger 1 Compressor Intake Temperature Sensor - Received Network Data In Error ",
		"Turbocharger 1 Compressor Intake Pressure - Data valid but below normal operational range - Most Severe Level",
		"Turbocharger 1 Compressor Intake Pressure - Data erratic, intermittent or incorrect",
		"Turbocharger 1 Compressor Intake Pressure Circuit - Voltage above normal, or shorted to high source",
		"Turbocharger 1 Compressor Intake Pressure Circuit - Voltage below normal, or shorted to low source",
		"Turbocharger 1 Compressor Intake Pressure - Abnormal update rate ",
		"Turbocharger 1 Compressor Intake Pressure - Data Valid But Below Normal Operating Range - Moderately",
		"Turbocharger 1 Compressor Intake Pressure - Received Network Data In Error",
		"Anti-theft Encryption Seed - Out of Calibration ",
		"Antitheft Password Valid Indicator - Data erratic, intermittent or incorrect Engine Fuel Pump",
		"Exhaust Gas Pressure 1 - Data erratic, intermittent or incorrect ",
		"Exhaust Gas Pressure Sensor 1 Circuit - Voltage above normal, or shorted to high source",
		"Exhaust Gas Pressure Sensor 1 Circuit - Voltage below normal, or shorted to low source",
		"Exhaust Gas Pressure 1 - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Malfunction Indicator Lamp - Abnormal update rate ",
		"J1939 Network #2 - Data erratic, intermittent or incorrect",
		"J1939 Network #3 - Data erratic, intermittent or incorrect",
		"Engine Fuel Leakage - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Idle Shutdown Vehicle Accessories Relay Driver Circuit - Voltage above normal, or shorted to high source",
		"Idle Shutdown Vehicle Accessories Relay Driver Circuit - Voltage below normal, or shorted to low source",
		"Engine Misfire for Multiple Cylinders - Condition Exists",
		"Engine Misfire Cylinder 1 - Condition Exists ",
		"Engine Misfire Cylinder 2 - Condition Exists",
		"Engine Misfire Cylinder 3 - Condition Exists",
		"Engine Misfire Cylinder 4 - Condition Exists",
		"Engine Misfire Cylinder 5 - Condition Exists",
		"Engine Misfire Cylinder 6 - Condition Exists",
		"Pressurizing Assembly 1 Circuit - Voltage above normal, or shorted to high source Engine Fuel Pump",
		"Pressurizing Assembly 1 Circuit - Voltage below normal, or shorted to low source Engine Fuel Pump",
		"Pressurizing Assembly 1 - Mechanical system not responding or out of adjustment",
		"Injector Metering Rail 2 Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Injector Metering Rail 2 Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Multiple Unit Synchronization Switch - Data erratic, intermittent or incorrect",
		"Engine Oil Change Interval - Condition Exists ",
		"Engine Shut Down Hot - Condition Exists",
		"Auxiliary Pressure Sensor Input 1 Circuit - Voltage above normal, or shorted to high source",
		"Auxiliary Pressure Sensor Input 1 Circuit - Voltage below normal, or shorted to low source",
		"Auxiliary Pressure Sensor Input 2 Circuit - Voltage above normal, or shorted to high source",
		"Auxiliary Pressure Sensor Input 2 Circuit - Voltage below normal, or shorted to low source",
		"Auxiliary Pressure Sensor Input 2 - Special Instructions",
		"Control Module Identification Input State Error - Data erratic, intermittent or incorrect",
		"Engine Protection Torque Derate - Condition Exists ",
		"Adaptive Cruise Control Mode - Data erratic, intermittent or incorrect ",
		"Tachograph Output Shaft Speed - Abnormal update rate",
		"Tachograph Output Shaft Speed - Out of Calibration ",
		"Tachograph Output Shaft Speed - Received Network Data In Error",
		"Engine Torque Limit Feature - Special Instructions ",
		"Engine Torque Limit Feature - Condition Exists ",
		"Fan Speed - Data Valid but Above Normal Operational Range - Most Severe Level",
		"Fan Speed - Data Valid but Below Normal Operational Range - Most Severe Level",
		"J1939 Network #4 - Data erratic, intermittent or incorrect ",
		"Engine Starter Mode Overcrank Protection - Condition Exists",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level - Data valid but below normal operational range -Most Severe Level",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor Circuit - Current below normal or open circuit",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor Circuit - Current above normal or grounded circuit",
		"Aftertreatment 1 DieselExhaust Fluid Tank Level Sensor - Abnormal Rate of Change",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor - Root Cause Not Known",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level Sensor - Out of Calibration ",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level - Data Valid But Below Normal Operating Range - Least Severe Level",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Level - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Battery Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Battery Temperature - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Roll Over Protection Brake Control Active - Condition Exists",
		"Source Address 6 - Abnormal Update Rate ",
		"Accelerator Pedal or Lever Position Sensor 2 Circuit - Voltage above normal, or shorted to high source",
		"Accelerator Pedal or Lever Position Sensor 2 Circuit - Voltage below normal, or shorted to low source",
		"Turbocharger Compressor Outlet Temperature (Calculated) - Data Valid But Above Normal Operating Range",
		"Engine Charge Air Cooler Outlet Temperature - Data erratic, intermittent or incorrect ",
		"Engine Charge Air Cooler Outlet Temperature - Voltage above normal, or shorted to high source",
		"Engine Charge Air Cooler Outlet Temperature - Voltage below normal, or shorted to low source",
		"Engine VGT Nozzle Position - Mechanical system not responding or out of adjustment",
		"Power Relay Driver Circuit - Voltage above normal, or shorted to high source",
		"Power Relay Driver Circuit - Voltage below normal, or shorted to low source",
		"Turbocharger Turbine Intake Temperature - Data Valid But Above Normal Operating Range - Least Severe",
		"Turbocharger Turbine Intake Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"EGR Valve Control Circuit - Current below normal or open circuit ",
		"EGR Valve Control Circuit - Current above normal or grounded circuit",
		"EGR Valve Control Circuit - Mechanical system not responding or out of adjustment ",
		"EGR Valve Control Circuit - Abnormal update rate",
		"EGR Valve Controller - Out of Calibration ",
		"EGR Valve Control Circuit Over Temperature - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Engine Injector Bank 1 Barcodes - Out of Calibration ",
		"Engine Auxiliary Governor Switch - Abnormal update rate",
		"Estimated Engine Parasitic Losses - Percent Torque - Abnormal update rate ",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature Sensor - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature Sensor - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature Sensor Circuit - Current below normal or open circuit",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature Sensor Circuit - Current above normal or grounded circuit",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature - Abnormal Update Rate",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature - Root Cause Not Known",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Temperature Sensor - Out of Calibration",
		"Engine Cooling System Monitor - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 Intake NOx Sensor - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Intake NOx Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Intake NOx Sensor - Abnormal update rate",
		"Aftertreatment 1 Intake NOx Sensor - Abnormal rate of change",
		"Aftertreatment 1 Intake NOx - Out of Calibration",
		"Aftertreatment 1 Intake NOx - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 Intake NOx Sensor - Data not Rational - Drifted High",
		"Aftertreatment Intake Oxygen Sensor - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Intake NOx Sensor Power Supply - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Outlet NOx Sensor - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Outlet NOx Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Outlet NOx Sensor - Abnormal update rate",
		"Aftertreatment 1 Outlet NOx Sensor - Abnormal rate of change",
		"Aftertreatment 1 Outlet NOx Sensor - Out of Calibration ",
		"Aftertreatment 1 Outlet NOx Sensor - Data not Rational - Drifted High",
		"Aftertreatment Outlet Oxygen Sensor Circuit - Abnormal update rate",
		"Aftertreatment 1 Outlet NOx Sensor Power Supply - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature - Data valid but above normal operation",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Temperature - Data valid but above normal operation",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Temperature - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment Exhaust Gas Temperature 2 - Data Valid But Below Normal Operating Range - Least Sever ",
		"Aftertreatment Exhaust Gas Temperature 2 - Data Valid But Below Normal Operating Range - Moderately",
		"Aftertreatment Diesel Particulate Filter Differential Pressure - Data valid but above normal Operating Range",
		"Aftertreatment Diesel Particulate Filter Differential Pressure Sensor - Data erratic, intermittent or incorrect",
		"Aftertreatment Diesel Particulate Filter Differential Pressure Sensor Circuit - Voltage above normal",
		"Aftertreatment Diesel Particulate Filter Differential Pressure Sensor Circuit - Voltage below normal",
		"Aftertreatment Diesel Particulate Filter Differential Pressure - Data valid but above normal Operating Range",
		"Aftertreatment Diesel Particulate Filter Differential Pressure - Data Valid But Above Normal Operating Range",
		"Aftertreatment 2 Intake Nox Sensor - Abnormal update rate",
		"Aftertreatment 2 Outlet NOx - Abnormal Update Rate ",
		"Alternator 1 Status - Voltage Above Normal, or Shorted to High Source",
		"Alternator 1 Status - Voltage Below Normal, or Shorted to Low Source",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Temperature - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Input Lines - Condition Exists ",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Heater - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Heater - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Heater - Mechanical system not responding or out of adjustment",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Heater - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 Diesel Exhaust Fluid Tank Heater - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Aftertreatment Diesel Exhaust Fluid Quality - Data valid but below normal operational range - Most Severe Level",
		"Aftertreatment Diesel Exhaust Fluid Quality - Data erratic, intermittent or incorrect",
		"Aftertreatment Diesel Exhaust Fluid Quality Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment Diesel Exhaust Fluid Quality Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Diesel Exhaust Fluid Quality Sensor Circuit - Current below normal or open circuit",
		"Aftertreatment Diesel Exhaust Fluid Quality Sensor Circuit - Current above normal or grounded circuit",
		"Aftertreatment Diesel Exhaust Fluid Quality Sensor - Mechanical system not responding or out of adjustment",
		"Aftertreatment Diesel Exhaust Fluid Quality - Abnormal update rate",
		"Aftertreatment Diesel Exhaust Fluid Quality - Abnormal Rate of Change ",
		"Aftertreatment Diesel Exhaust Fluid Quality - Root Cause Not Known",
		"Aftertreatment Diesel Exhaust Fluid Quality Sensor - Bad intelligent device or component",
		"Aftertreatment Diesel Exhaust Fluid Quality - Out of Calibration",
		"Aftertreatment Diesel Exhaust Fluid Quality - Data Valid But Above Normal Operating Range - Least Severe Level",
		"Aftertreatment Diesel Exhaust Fluid Quality - Data Valid But Below Normal Operating Range - Moderate Severe Level",
		"Aftertreatment Diesel Exhaust Fluid Quality - Received Network Data In Error",
		"Aftertreatment Fuel Pressure Sensor - Data erratic, intermittent or incorrect ",
		"Aftertreatment Fuel Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment Fuel Pressure Sensor Circuit - Voltage  below normal, or shorted to low source",
		"Aftertreatment Fuel Pressure Sensor - Data Valid But Below Normal Operating Range - Least Severe Level",
		"Aftertreatment Fuel Rate - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Aftertreatment Fuel Shutoff Valve - Data erratic, intermittent or incorrect",
		"Aftertreatment Fuel Shutoff Valve Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment Fuel Shutoff Valve Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Fuel Shutoff Valve - Mechanical system not responding or out of adjustment",
		"Aftertreatment Fuel Shutoff Valve Swapped - Out of Calibration",
		"Aftertreatment Fuel Shutoff Valve - Data Valid But Above Normal Operating Range - Moderately Severe",
		"Aftertreatment Purge Air Actuator Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment Purge Air Actuator Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Purge Air Actuator - Mechanical system not responding or out of adjustment",
		"Sensor Supply 1 Circuit - Voltage above normal, or shorted to high source",
		"Sensor Supply 1 Circuit - Voltage below normal, or shorted to low source ",
		"Sensor Supply 2 Circuit - Voltage above normal, or shorted to high source ",
		"Sensor Supply 2 Circuit - Voltage below normal, or shorted to low source Cruise Control (Resistive)",
		"Sensor Supply 3 Circuit - Voltage above normal, or shorted to high source",
		"Sensor Supply 3 Circuit - Voltage below normal, or shorted to low source ",
		"Sensor Supply 4 Circuit - Voltage above normal, or shorted to high source ",
		"Sensor Supply 4 Circuit - Voltage below normal, or shorted to low source",
		"Sensor Supply 5 - Voltage above normal, or shorted to high source",
		"Sensor Supply 5 - Voltage below normal, or shorted to low source",
		"Sensor Supply 6 Circuit - Voltage above normal, or shorted to high source ",
		"Sensor Supply 6 Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 Sensor Circuit - Current below normal or open circuit",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 Sensor Circuit - Current above normal or grounded",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 - Abnormal Rate of Change ",
		"Aftertreatment 1 Diesel Exhaust Fluid Temperature 2 - Root Cause Not Known Aftertreatment 1 Diesel",
		"Exhaust Fluid Property - Root Cause Not Known ",
		"Aftertreatment 1 Diesel Exhaust Fluid Property - Condition Exists ",
		"Aftertreatment Doser - Data erratic, intermittent or incorrect",
		"Aftertreatment Doser Circuit - Current below normal or open circuit.",
		"Aftertreatment Doser - Mechanical system not responding or out of adjustment",
		"Aftertreatment Doser - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Power Supply Lost With Ignition On - Data erratic, intermittent or incorrect ",
		"ECU Power Output Supply Voltage 1 - Voltage above normal, or shorted to high source",
		"ECU Power Output Supply Voltage 1 - Voltage below normal, or shorted to low source",
		"Injector Power Supply - Bad intelligent device or component ",
		"ECU Power Output Supply Voltage 1 - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Pressure - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Particulate Filter Outlet Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Engine Air Shutoff Status - Data erratic, intermittent or incorrect",
		"Engine Air Shutoff Circuit - Voltage above normal, or shorted to high source ",
		"Engine Air Shutoff Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Diesel Particulate Filter Regeneration Inhibit Switch - Data erratic, intermittent or incorrect",
		"Particulate Trap Active Regeneration Inhibited Due to Inhibit Switch - Condition Exists",
		"Diesel Particulate Filter Active Regeneration Inhibited Due to System Timeout - Condition Exists",
		"Diesel Particulate Filter 1 Conditions Not Met for Active Regeneration - Condition Exists",
		"Aftertreatment 1 Diesel Exhaust Fluid Average Consumption - Data Valid But Below Normal Operating Range",
		"Aftertreatment 1 Diesel Particulate Filter System - Mechanical system not responding or out of adjustment",
		"Aftertreatment Diesel Particulate Filter System - Special Instructions ",
		"Aftertreatment 1 Diesel Particulate Filter System - Data Valid But Above Normal Operating Range - Level",
		"NOx limits exceeded due to Insufficient Reagent Quality - Condition Exists",
		"Aftertreatment Diesel Exhaust Fluid Tank Empty - Condition Exists",
		"Aftertreatment Fuel Drain Valve Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment Fuel Drain Valve Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Fuel Drain Valve - Mechanical system not responding or out of adjustment",
		"Generator Output Frequency Adjust Potentiometer Circuit - Voltage below normal, or shorted to low source",
		"Droop Adjust Potentiometer Circuit - Voltage below normal, or shorted to low source",
		"Gain Adjust Potentiometer Circuit - Voltage below normal, or shorted to low source",
		"Overspeed Shutdown Relay Driver Diagnostic has detected an error - Condition Exists",
		"Low Oil Pressure (LOP) Shutdown Relay Driver Diagnostic has detected an error - Condition Exists",
		"High Engine Temperature (HET) Shutdown Relay Driver Diagnostic has detected an error - Condition Exists",
		"Pre-Low Oil Pressure Warning Relay Driver Diagnostic has detected an error - Condition Exists",
		"Pre-High Engine Temperature Warning Relay Driver Diagnostic has detected an error - Condition Exists",
		"Aftertreatment SCR Actual Dosing Reagent Quantity - Data Valid But Below Normal Operating Range - Mo",
		"Aftertreatment 1 Diesel Exhaust Fluid Pressure Sensor - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Diesel Exhaust Fluid Pressure Sensor - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Pressure Sensor - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Pressure Sensor - Data Valid But Above Normal Operating Range",
		"Aftertreatment 1 Diesel Exhaust Fluid Pressure Sensor - Data Valid But Below Normal Operating Range",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Temperature - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Temperature - Abnormal Rate of Change ",
		"Aftertreatment 1 SCR Feedback Control Status - Condition Exists",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater 1 Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater 1 Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater 1 Circuit - Current below normal or open circuit ",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater 2 Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater 2 Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater 2 Circuit - Current below normal or open circuit",
		"Aftertreatment Diesel Exhaust Fluid Line Heater 3 Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment Diesel Exhaust Fluid Line Heater 3 Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Diesel Exhaust Fluid Line Heater 3 Circuit - Current below normal or open circuit",
		"Aftertreatment 1 SCR Intake Temperature - Data valid but above normal operational range - Most Severe Level",
		"Aftertreatment 1 SCR Intake Temperature Sensor - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 SCR Intake Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 SCR Intake Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 SCR Intake Temperature - Data Valid But Above Normal Operating Range - Least Severe",
		"Aftertreatment 1 SCR Intake Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 SCR Outlet Temperature - Data valid but above normal operational range - Most Severe",
		"Aftertreatment 1 SCR Outlet Temperature Sensor - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 SCR Outlet Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 SCR Outlet Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 SCR Outlet Temperature - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Aftertreatment SCR Catalyst Conversion Efficiency - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Aftertreatment Diesel Exhaust Fluid Return Valve - Voltage above normal, or shorted to high source ",
		"Aftertreatment Diesel Exhaust Fluid Return Valve - Voltage below normal, or shorted to low source ",
		"Aftertreatment Diesel Exhaust Fluid Return Valve - Mechanical system not responding or out of adjust",
		"Specific Humidity Sensor - Abnormal update rate",
		"Specific Humidity Sensor - Received Network Data In Error",
		"Aftertreatment 1 Diesel Oxidation Catalyst Intake Temperature - Data erratic, intermittent or incorrect ",
		"Aftertreatment 1 Diesel Oxidation Catalyst Intake Temperature Sensor Circuit - Voltage above normal, or shorted to high source",
		"Aftertreatment 1 Diesel Oxidation Catalyst Intake Temperature Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Oxidation Catalyst Intake Temperature Swapped - Out of Calibration ",
		"Aftertreatment 1 Diesel Oxidation Catalyst Intake Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment SCR Catalyst System - Mechanical system not responding or out of adjustment",
		"Aftertreatment 1 SCR Catalyst System - Special Instructions ",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Missing - Condition Exists ",
		"Aftertreatment 1 SCR Catalyst System Missing - Condition Exists ",
		"Aftertreatment 1 Diesel Particulate Filter Missing - Condition Exists ",
		"Aftertreatment 1 Diesel Oxidation Catalyst Missing - Condition Exists",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Intake Temperature - Data erratic, intermittent or incorrect",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Intake Temperature Sensor Circuit - Voltage above normal",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Intake Temperature Sensor Circuit - Voltage below normal",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Intake Temperature Sensor Swapped - Out of Calibration",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Intake Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Outlet Temperature - Data valid but above normal operating Range - Most Severe level",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Outlet Temperature - Data erratic, intermittent or incorrect",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Outlet Temperature Sensor Circuit - Voltage above normal",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Outlet Temperature Sensor Circuit - Voltage below normal",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Outlet Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Outlet Temperature - Data Valid But Above Normal Operating Range",
		"Aftertreatment 1 Diesel Oxidation Catalyst Face Plugged - Root Cause Not Known",
		"Engine Exhaust Gas Recirculation Outlet Pressure - Data erratic, intermittent or incorrect ",
		"Engine Exhaust Gas Recirculation Outlet Pressure Sensor Circuit - Voltage above normal, or shorted to high source",
		"Engine Exhaust Gas Recirculation Outlet Pressure Sensor Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Intake NOx Sensor Heater - Abnormal rate of change",
		"Aftertreatment 1 Outlet NOx Sensor Heater - Abnormal rate of change ",
		"Engine Brake Active Lamp - Voltage Above Normal, or Shorted to High Source ",
		"Engine Brake Active Lamp -  Voltage below normal, or shorted to low source",
		"Sensor Supply 7 Circuit - Voltage above normal, or shorted to high source",
		"Sensor Supply 7 Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment SCR Operator Inducement Active - Condition Exists ",
		"Aftertreatment SCR Operator Inducement - Data valid but above normal operational range - Most Severe level",
		"Aftertreatment 1 Diesel Oxidation Catalyst Conversion Efficiency - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 Post SCR NH3 Conversion Efficiency - Data Valid But Below Normal Operating Range - ",
		"Aftertreatment Diesel Particulate Filter Incomplete Regeneration - Condition Exists",
		"Engine Fuel Injection Quantity Error for Multiple Cylinders - Condition Exists",
		"Engine Fuel Valve 1 - Root Cause Not Known",
		"Engine Fuel Valve 1 - Out of Calibration",
		"Aftertreatment Diesel Exhaust Fluid Dosing Valve - Data erratic, intermittent or incorrect",
		"Aftertreatment Diesel Exhaust Fluid Dosing Valve - Current below normal or open circuit",
		"Aftertreatment Diesel Exhaust Fluid Dosing Valve - Mechanical system not responding or out of adjustment",
		"Engine Idle Fuel Quantity - Data Valid But Above Normal Operating Range - Moderately Severe Level ",
		"Engine Idle Fuel Quantity - Data Valid But Below Normal Operating Range - Moderately Severe Level",
		"Engine Crankcase Ventilation Hose Disconnected - Condition Exists",
		"Aftertreatment Diesel Particulate Filter Regeneration too Frequent - Condition Exists",
		"Engine Fan Clutch 2 Control Circuit - Voltage above normal, or shorted to high source",
		"Engine Fan Clutch 2 Control Circuit - Voltage below normal, or shorted to low source",
		"Aftertreatment Diesel Exhaust Fluid Line Heater Relay - Voltage above normal, or shorted to high source",
		"Aftertreatment Diesel Exhaust Fluid Line Heater Relay - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Line Heater Relay - Mechanical system not responding or out of adjustment",
		"High Pressure Common Rail Fuel Pressure Relief Valve - Data valid but above normal operational range",
		"High Pressure Common Rail Fuel Pressure Relief Valve - Voltage Above Normal, or Shorted to High Source",
		"High Pressure Common Rail Fuel Pressure Relief Valve - Voltage below normal, or shorted to low source",
		"High Pressure Common Rail Fuel Pressure Relief Valve - Mechanical system not responding or out of adjustment",
		"High Pressure Common Rail Fuel Pressure Relief Valve - Root Cause Not Known ",
		"High Pressure Common Rail Fuel Pressure Relief Valve - Condition Exists",
		"Engine Injector Metering Rail 1 Cranking Pressure - Data Valid But Below Normal Operating Range - Mo",
		"Cruise Control Disable Command - Abnormal update rate",
		"Cruise Control Disable Command - Condition Exists ",
		"Cruise Control Pause Command - Condition Exists ",
		"Engine Exhaust Back Pressure Regulator Position - Data Erratic, Intermittent or Incorrect",
		"Engine Exhaust Back Pressure Regulator Position Sensor Circuit - Voltage Above Normal, or Shorted to High Source",
		"Engine Exhaust Back Pressure Regulator Position Sensor Circuit - Voltage Below Normal, or Shorted to Low Source",
		"Engine Exhaust Back Pressure Regulator - Out of Calibration ",
		"Aftertreatment 1 Outlet Soot - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Outlet Soot Sensor - Voltage Above Normal, or Shorted to High Source",
		"Aftertreatment 1 Outlet Soot Sensor - Voltage below normal, or shorted to low source",
		"Aftertreatment Diesel Particulate Filter Temperature Sensor Module - Voltage Above Normal, or Shorted to high source",
		"Aftertreatment Diesel Particulate Filter Temperature Sensor Module - Voltage below normal, or shorted to low source",
		"Aftertreatment Diesel Particulate Filter Temperature Sensor Module - Abnormal update rate",
		"Aftertreatment Diesel Particulate Filter Temperature Sensor Module - Root Cause Not Known",
		"Aftertreatment Diesel Particulate Filter Temperature Sensor Module - Bad intelligent device or component",
		"Aftertreatment Diesel Particulate Filter Temperature Sensor Module - Data Valid But Above Normal Operating Range",
		"Aftertreatment Selective Catalytic Reduction Temperature Sensor Module - Voltage Above Normal, or Shorted to high source",
		"Aftertreatment Selective Catalytic Reduction Temperature Sensor Module - Voltage below normal, or Shorted to low source",
		"Aftertreatment Selective Catalytic Reduction Temperature Sensor Module - Abnormal update rate",
		"Aftertreatment Selective Catalytic Reduction Temperature Sensor Module - Root Cause Not Known",
		"Aftertreatment Selective Catalytic Reduction Temperature Sensor Module - Bad intelligent device or component",
		"Aftertreatment Selective Catalytic Reduction Temperature Sensor Module - Data Valid But Above Normal",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater - Voltage Above Normal, or Shorted to High ",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater - Data Valid But Below Normal Operating Range",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater Relay - Voltage Above Normal, or Shorted to high source",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater Relay - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Outlet Soot Sensor Heater - Voltage Above Normal, or Shorted to High Source",
		"Aftertreatment 1 Outlet Soot Sensor Heater - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Outlet Soot Sensor Heater - Abnormal rate of change",
		"Desired Engine Fueling State - Abnormal Update Rate ",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Temperature Sensor Module - Voltage Above Normal, or shorted to high source",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Temperature Sensor Module - Voltage below normal, or shorted to low source",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Temperature Sensor Module - Root Cause Not Known",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Temperature Sensor Module - Bad intelligent device",
		"Aftertreatment Warm Up Diesel Oxidation Catalyst Temperature Sensor Module - Data Valid But Above Normal Operating Range - Moderately Severe Level",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater Temperature - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 Diesel Exhaust Fluid Dosing Unit Heater Temperature - Abnormal Rate of Change",
		"EGR Valve Malfunction - Condition Exists",
		"Diesel Exhaust Fluid Consumption Malfunction - Condition Exists",
		"Diesel Exhaust Fluid Dosing Malfunction - Condition Exists ",
		"Diesel Exhaust Fluid Quality Malfunction - Condition Exists ",
		"SCR Monitoring System Malfunction - Condition Exists",
		"Aftertreatment 1 SCR Intermediate NH3 - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 SCR Intermediate NH3 Sensor - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 SCR Intermediate NH3 Sensor - Abnormal update rate ",
		"Aftertreatment 1 SCR Intermediate NH3 Sensor - Abnormal rate of change ",
		"Aftertreatment 1 SCR Intermediate NH3 Sensor - Bad intelligent device or component",
		"Aftertreatment 1 SCR Intermediate NH3 Sensor - Out of Calibration ",
		"Aftertreatment 1 SCR Intermediate NH3 - Data not Rational - Drifted High",
		"Aftertreatment 1 SCR Intermediate NH3 - Data not Rational - Drifted Low",
		"Aftertreatment 1 SCR Intermediate NH3 Gas Sensor Power Supply - Data erratic, intermittent or incorrect",
		"Aftertreatment 1 SCR Intermediate NH3 Gas Sensor Power Supply - Data Valid But Above Normal Operating Range - Most Severe Level",
		"Aftertreatment 1 SCR Intermediate NH3 Gas Sensor Power Supply - Data Valid But Below Normal Operating Range - Most Severe Level",
		"Aftertreatment 1 Outlet NH3 Gas Sensor Heater - Abnormal rate of change ",
		"Water in Fuel Indicator 2 Sensor Circuit - Voltage above normal, or shorted to high source",
		"Water in Fuel Indicator 2 Sensor Circuit - Voltage below normal, or shorted to low source",
		"Cold Start Injector Metering Rail 1 Pressure - Data Valid But Above Normal Operating Range - Moderate Severe Level",
		"Maintain ECU Power Lamp - Voltage Above Normal, or Shorted to High Source",
		"Maintain ECU Power Lamp - Voltage Below Normal, or Shorted to Low Source",
		"Signal Circuit - Voltage above normal, or shorted to high source",
		"Cruise Control (Resistive) Signal Circuit - Voltage below normal, or shorted to low source",
		"Crankcase Depression Valve - Mechanical system not responding or out of adjustment",
		"Cruise Control (Resistive) #2 Signal Circuit - Voltage above normal, or shorted to high source",
		"Cruise Control (Resistive) #2 Signal Circuit - Voltage below normal, or shorted to low source",
		"Glow Plug Module - Bad intelligent device or component",
		"Closed Crankcase Ventilation System Pressure - Data erratic, intermittent or incorrect",
		"Closed Crankcase Ventilation System Pressure Sensor - Voltage Above Normal, or Shorted to High Source",
		"Closed Crankcase Ventilation System Pressure Sensor - Voltage below normal, or shorted to low source",
		"Aftertreatment 1 Outlet NOx Sensor Closed Loop Operation - Condition Exists ",
		"Aftertreatment 1 Outlet NH3 Sensor Closed Loop Operation - Condition Exists",
		"Fan Blade Pitch Position Sensor Circuit - Voltage Above Normal, or Shorted to High Source",
		"Fan Blade Pitch Position Sensor Circuit - Voltage Below Normal, or Shorted to Low Source",
		"Fan Blade Pitch - Mechanical system not responding or out of adjustment",
		"Engine Boost Curve Selection - Data erratic, intermittent or incorrect ",
		"Engine Emergency Shutdown Switch Actived - Condition Exists",
		"Excessive Time Since Last Engine Air Shutoff Maintenance Test - Condition Exists",
		"Aftertreatment 1 Diesel Particulate Filter Intake Temperature - Data valid but above normal operation",
		"Not Define."
	};

	static final String textScaniaECMErrCodep[] = {
		"Low air pressure signal from APS",
		"CAN message timeout from APS",
		"Throttle Position Sensor 1, short circuit to +24",
		"Throttle Position Sensor 1, short circuit to ground",
		"Throttle Position Sensor, not plausible",
		"Endpoints of throttle position sensor are out of range",
		"Throttle Position Sensor, correlation error",
		"Auxiliary accelerator pedal is used due to other fault",
		"Accelerator pedal faulty or error via can",
		"Accelerator pedal not plausible, faulty",
		"Accelerator pedal value out of range via CAN",
		"Accumulator pressure is too high",
		"Oil level sensor, faulty",
		"Oil level sensor, short circuit to +24V",
		"Oil level sensor, short circuit to ground",
		"Oil level sensor stuck",
		"Oil pressure sensor, pressure too low",
		"Oil pressure sensor, faulty",
		"Oil pressure sensor, short circuit to +24V",
		"Oil pressure sensor, short circuit to ground",
		"Oil pressure sensor, pressure not plausible",
		"Oil pressure sensor, pressure above normal",
		"Oil pressure sensor, pressure too low and engine protective action",
		"Oil pressure sensor, pressure below normal",
		"Boost pressure higher than reference",
		"Boost pressure lower than reference",
		"Boost pressure sensor, short circuit to +24V",
		"Boost pressure sensor, short circuit to ground",
		"Boost pressure, too low",
		"Boost pressure sensor, faulty",
		"Boost pressure, not plausible",
		"Boost pressure sensor, faulty",
		"Boost pressure sensor and exhaust pressure sensor do not correlate",
		"Boost pressure above normal",
		"Boost pressure, lower than reference at part load",
		"Boost pressure, too high not plausible ",
		"Boost pressure, too low not plausible ",
		"Turbine excessive overspeed",
		"Turbine speed sensor, faulty",
		"Turbine speed sensor, short circuit to +24V",
		"Turbine speed sensor, short circuit to ground",
		"Turbine speed sensor, open load",
		"Turbine speed not plausible",
		"Turbine speed sensor above model, not plausible",
		"Turbine speed sensor below model, not plausible",
		"Boost temp sensor excessive high",
		"Boost temp sensor excessive low",
		"Boost temp sensor, faulty",
		"Boost temp sensor, short circuit to +24V",
		"Boost temp sensor, short circuit to ground",
		"Boost temperature above ambient, not plausible",
		"Boost temperature to high for longer period",
		"Boost temperature above normal",
		"Boost temperature below ambient, not plausible",
		"Boost temperature to high, not plausible",
		"Boost temperature to low, not plausible",
		"Air filter clogged",
		"Air filter control switch broken",
		"Ambient Pressure Sensor Error via CAN",
		"Ambient Pressure Sensor, short circuit to +24V",
		"Ambient Pressure Sensor, short circuit to ground",
		"Ambient Pressure Sensor and Exhaust Pressure Sensor do not correlate",
		"Ambient Pressure above normal",
		"Ambient Pressure too high, not plausible",
		"Ambient Pressure too low, not plausible",
		"Engine temperature, excessive high",
		"Engine temperature too low",
		"Engine temp sensor fault",
		"Engine temp sensor, short circuit to +24V",
		"Engine temp sensor, short circuit to ground",
		"Engine temp sensor, stuck",
		"Engine temp sensor, faulty",
		"Engine temperature is not plausble",
		"Engine temperature, too high",
		"Engine temp sensor, temp below normal or VGT-temp above normal",
		"Engine temp sensor, temp above normal or VGT-temp below normal",
		"Engine Coolant Water Temperature Too High",
		"Coolant Temperature Below Thermostat Regulating Temperature",
		"Coolant level too low",
		"Coolant level sensor, short circuit to +24",
		"Coolant level sensor, short circuit to ground",
		"Exhaust pressure sensor, not plausible",
		"Exhaust pressure sensor, short circuit to +24V",
		"Exhaust pressure sensor, short circuit to ground or open load",
		"Exhaust pressure sensor and boost pressure sensor do not correlate",
		"Exhaust pressure sensor, faulty",
		"Exhaust pressure sensor, stuck",
		"Exhaust pressure sensor and ambient pressure sensor do not correlate",
		"Exhaust pressure, high exhaust pressure during normal fueling",
		"Exhaust pressure, high exhaust pressure during motoring, no fueling",
		"Exhaust pressure, low exhaust pressure during exhaust brake",
		"Exhaust pressure too high, not plausible",
		"Exhaust pressure too low, not plausible",
		"Mass flow sensor, short circuit to +24V",
		"Mass flow sensor, short circuit to ground or open load",
		"Mass flow sensor, faulty",
		"Mass flow sensor, supply",
		"Mass flow sensor, sdaptation under low threshold",
		"Mass flow sensor, adaptation over high threshold",
		"Mass flow sensor, stuck",
		"Fuel rail pressure is excessively above command",
		"Fuel rail pressure is excessively below command",
		"Fuel rail pressure sensor, faulty",
		"Fuel rail pressure sensor, short circuit to +24V or open load",
		"Fuel rail pressure sensor, short circuit to ground",
		"Fuel rail pressure sensor, stuck",
		"Fuel rail pressure is lagging",
		"Fuel rail pressure is too low during cranking",
		"Alternator actuator,  faulty",
		"Alternator actuator, short circuit to +24V",
		"Alternator actuator, short circuit to ground",
		"Alternator actuator, open load",
		"Alternator 1, signal not plausible",
		"Alternator 2, signal not plausible",
		"Battery voltage above 47 V for 1 s",
		"Battery voltage below 9 V for 0.5 s",
		"Battery voltage 1 for engine control unit is low",
		"Battery voltage 2 for engine control unit is low",
		"Battery voltage too high for SCR main unit",
		"Battery voltage above 32 V",
		"Battery voltage too low for SCR main unit",
		"Battery voltage below 21 V",
		"Ambient temperature sensors correlation error",
		"Ambient temperature low or boost temperature high",
		"Ambient temperature sensor, faulty",
		"Ambient temperature sensor error via CAN",
		"Ambient temperature sensor error via CAN",
		"Ambient temperature sensor stuck",
		"CAN message AMBIENT CONDITION from coordinator timeout",
		"Ambient temperature sensors correlation error",
		"Ambient temperature high or boost temperature low",
		"Ambient temperature sensors correlation error",
		"Ambient temperature sensors correlation error",
		"Ambient temperature sensor signal defect ",
		"Temperature sensor before compressor low or ambient temperature sensor high",
		"Temperature sensor before compressor high or ambient temperature sensor low",
		"Air inlet temp sensor, faulty",
		"Air inlet temp sensor, short circuit to +24V",
		"Air inlet temp sensor, short circuit to ground",
		"Air inlet temp sensor, stuck",
		"Oil temp sensor, short circuit to +24V",
		"Oil temp sensor, short circuit to ground",
		"Oil temp sensor, faulty",
		"Idle due to other fault",
		"Severe overspeed has occured",
		"Overspeed protection, fast over speed",
		"Engine speed has been above the limit",
		"Overspeed protection, over speed",
		"Engine overspeed, value to high",
		"The EMS and EEC control units are incompatible",
		"Wrong CAN version transmitted by COO",
		"Increased idle due to other fault",
		"Low idle switch error state from coordinator",
		"Kickdown signal defect via CAN",
		"Accelerator pedal kickdown CAN message, faulty",
		"Accelerator pedal/kick down switch, EMS and coordinator do not agree",
		"Brake pedal signal defect via CAN",
		"Clutch pedal signal defect via CAN",
		"Excessive clutch slip",
		"CAN-signal or engine shut-down command from OPC for automatic clutch failure, timeout",
		"Camshaft position sensor, faulty",
		"Camshaft position sensor, intermittent fault",
		"Camshaft position sensor, short circuit to +24V",
		"Camshaft position sensor, short circuit to ground",
		"Camshaft position sensor, open circuit",
		"Engine speed detected by flywheel sensor, but no signal from camshaft sensor",
		"Camshaft Pulse Pattern, Gap or Sync Error or other fault",
		"VGT internal temperature sensor stuck",
		"VGT voltage supply open load",
		"VGT internal temperature sensor open circuit",
		"VGT motion limited or restricted",
		"VGT reference or position not found",
		"VGT temperature sensor value not plausible",
		"VGT motion error, span too large",
		"VGT actuator faulty",
		"VGT internal fault",
		"VGT actuator installation procedure was not completed",
		"VGT error",
		"VGT temperature too high",
		"VGT timeout on CAN",
		"CAN message TCO1 from tachograph timeout",
		"Two or more injectors with the same trim code, injector cyl. 1",
		"Injector trim code, checksum error injector cyl. 1",
		"Injector 1 cable short circuit to ground",
		"Injector cyl. 1 cable/injector open load",
		"Injector cyl. 1 cable/injector short circuit",
		"Injection error, physical cylinder 1",
		"Injector cyl. 1, over or under fueling",
		"Fault with sensors/actuators for the particulate filter",
		"Injector trim code version error, injector cyl. 1",
		"Cylinder 1 torque error",
		"Cylinder 1 injector fault, high torque",
		"Cylinder 1 injector fault, low torque",
		"Cylinder 1 balancing min or max",
		"Cylinder balancing, not plausible",
		"Two or more injectors with the same trim code, injector cyl. 2",
		"Injector trim code, checksum error injector cyl. 2",
		"Injector 2 cable short circuit to ground",
		"Injector cyl. 2 cable/injector open load",
		"Injector cyl. 2 cable/injector short circuit",
		"Injection error, physical cylinder 2",
		"Injector cyl. 2, over or under fueling",
		"Fault with sensors/actuators for the particulate filter",
		"Injector trim code version error, injector cyl. 2",
		"Cylinder 2 torque error",
		"Cylinder 2 injector fault, high torque",
		"Cylinder 2 injector fault, low torque",
		"Cylinder 2 balancing min or max",
		"Two or more injectors with the same trim code, injector cyl. 3",
		"Injector trim code, checksum error injector cyl. 3",
		"njector 3 cable short circuit to ground",
		"Injector cyl. 3 cable/injector open load",
		"Injector cyl. 3 cable/injector short circuit",
		"Injection error, physical cylinder 3",
		"Injector cyl. 3, over or under fueling",
		"Fault with sensors/actuators for the particulate filter",
		"Injector trim code version error, injector cyl. 3",
		"Cylinder 3 torque error",
		"Cylinder 3 injector fault, high torque",
		"Cylinder 3 injector fault, low torque",
		"Cylinder 3 balancing min or max",
		"Two or more injectors with the same trim code, injector cyl. 4",
		"Injector trim code, checksum error injector cyl. 4",
		"Injector 4 cable short circuit to ground",
		"Injector cyl. 4 cable/injector open load",
		"Injector cyl. 4 cable/injector short circuit",
		"Injection error, physical cylinder 4",
		"Injector cyl. 4, over or under fueling",
		"Fault with sensors/actuators for the particulate filter",
		"Injector trim code version error, injector cyl. 4",
		"Cylinder 4 torque error",
		"Cylinder 4 injector fault, high torque",
		"Cylinder 4 injector fault, low torque",
		"Cylinder 4 balancing min or max",
		"Two or more injectors with the same trim code, injector cyl. 5",
		"Injector trim code, checksum error injector cyl. 5",
		"Injector 5 cable short circuit to ground",
		"Injector cyl. 5 cable/injector open load",
		"Injector cyl. 5 cable/injector short circuit",
		"Injection error, physical cylinder 5",
		"Injector cyl. 5, over or under fueling",
		"Fault with sensors/actuators for the particulate filter",
		"Injector trim code version error, injector cyl. 5",
		"Cylinder 5 torque error",
		"Cylinder 5 injector fault, high torque",
		"Cylinder 5 injector fault, low torque",
		"Cylinder 5 balancing min or max",
		"Two or more injectors with the same trim code, injector cyl. 6",
		"Injector trim code, checksum error injector cyl. 6",
		"Injector cyl. 6 cable/injector open load",
		"Injector cyl. 6 cable/injector short circuit",
		"Injection error, physical cylinder 6",
		"Injector cyl. 6, over or under fueling",
		"Injector trim code version error, injector cyl. 6",
		"Cylinder 6 torque error",
		"Cylinder 6 injector fault, high torque",
		"Cylinder 6 injector fault, low torque",
		"Cylinder 6 balancing min or max",
		"Two or more injectors with the same trim code, injector cyl. 7",
		"Injector trim code, checksum error injector cyl. 7",
		"Injector cyl. 7 cable/injector open load",
		"Injector cyl. 7 cable/injector short circuit",
		"Injection error, physical cylinder 7",
		"Injector cyl. 7, over or under fueling",
		"Injector trim code version error, injector cyl. 7",
		"Cylinder 7 torque error",
		"Cylinder 7 injector fault, high torque",
		"Cylinder 7 injector fault, low torque",
		"Cylinder 7 balancing min or max",
		"Two or more injectors with the same trim code, injector cyl. 8",
		"Injector trim code, checksum error injector cyl. 8",
		"Injector cyl. 8 cable/injector open load",
		"Injector cyl. 8 cable/injector short circuit",
		"Injection error, physical cylinder 8",
		"Injector cyl. 8, over or under fueling",
		"Injector trim code version error, injector cyl. 8",
		"Cylinder 8 torque error",
		"Cylinder 8 injector fault, high torque",
		"Cylinder 8 injector fault, low torque",
		"Cylinder 8 balancing min or max",
		"Unintentional starter activation while moving or idling",
		"Starter actuator, faulty",
		"Starter actuator, short circuit to +24V",
		"Starter actuator, short circuit to ground",
		"Starter actuator, open load",
		"Starter actuator, blind start",
		"Starter motor demand defect via CAN",
		"Engine position sensor 2, faulty",
		"Engine position sensor 2, too weak signal",
		"Engine position sensor 2, faulty",
		"Engine position sensor 2, Gap Puls or Sync error",
		"Engine position sensor 2, Time out",
		"Engine position sensor 2, position diff",
		"Engine position sensor 2 error torque limit",
		"Signal level from redundant gas pedal above high limit",
		"Signal level from redundant gas pedal below low limit",
		"Fan actuator, faulty",
		"Fan actuator, short circuit to +24V",
		"Fan actuator, short circuit high to ground",
		"Fan actuator, open load",
		"Fan coupling unit, bad performance",
		"Electrical fault on the parking brake pressure sensor",
		"Overridden due to other fault",
		"Engine Stop due to other fault",
		"Fuel Rail pressure, small volume leak",
		"Random/Multiple Cylinder Misfire Detected",
		"Cylinder 1 Misfire Detected",
		"Cylinder 2 Misfire Detected",
		"Cylinder 3 Misfire Detected",
		"Cylinder 4 Misfire Detected",
		"Cylinder 5 Misfire Detected",
		"Inlet metering valve 1, faulty",
		"Inlet metering valve 1, short circuit to +24V",
		"Inlet metering valve 1, short circuit to ground",
		"Inlet metering valve 1, stuck",
		"Inlet metering valve 1, plausible leakage",
		"Inlet metering valve 1, calculated resistance error",
		"Mechanical dump dalve, opened",
		"Mechanical dump valve, tripped",
		"EMS internal error",
		"EMS Memory Error",
		"EMS Memory Error",
		"EMS Memory or TPU Error",
		"EMS memory or TPU error",
		"Camshaft TPU Supervision Error",
		"Software Watchdog Reset",
		"Hardware watchdog error",
		"CAN message DLN1 from coordinator timeout",
		"CAN message CRUISE CONTROL/ VEHICLE SPEED from coordinator timeout",
		"CAN message from EMSX, invalid data",
		"CAN message from EMSX, invalid data",
		"CAN message DLN6 from coordinator timeout",
		"CAN message timout from EMSX",
		"CAN message timout from EMSX",
		"SCR main unit, power switched off too early",
		"SCR main unit, power switched off too late",
		"Torque reduction due to other fault",
		"Fan speed sensor, short circuit to +24V",
		"Fan speed sensor supply too low",
		"Fan speed sensor circuit no signal",
		"Immobiliser - EMS and EMSX",
		"Invalid Data Received From Vehicle Control Module",
		"Immobiliser error",
		"Software Incompatibility With Vehicle Immoblilizer Control Module",
		"Lost Communication With Vehicle Immobilizer Control Module",
		"Reductant tank, empty",
		"Reductant tank level sensor, short circuit to ground",
		"Reductant tank level sensor, short circuit to +24V",
		"Aftertreatment Diesel Exhaust Fluid Tank Level",
		"Reductant tank level sensor, open circuit",
		"Aftertreatment Diesel Exhaust Fluid Tank Level",
		"Aftertreatment Diesel Exhaust Fluid Tank Level",
		"Aftertreatment Diesel Exhaust Fluid Tank Level",
		"Aftertreatment Diesel Exhaust Fluid Tank Level",
		"Reductant tank, low level",
		"Aftertreatment Diesel Exhaust Fluid Tank Level",
		"AC compressor actuator, faulty",
		"AC compressor actuator, short circuit to +24V",
		"AC compressor actuator, short circuit to ground",
		"AC compressor actuator, open load",
		"EGR actuator, control error",
		"EGR actuator, short circuit to +24V",
		"EGR actuator, short circuit to ground",
		"EGR actuator, stuck open",
		"EGR actuator, stuck close",
		"The EGR valve is responding too slow",
		"NOx Exceedence - Incorrect EGR Flow",
		"EGR system faulty",
		"NOx Exceedence - Deactivation of EGR",
		"EGR higher than desired",
		"EGR lower than desired",
		"Injector group A, short circuit to other bank",
		"Injector group A, short circuit to +24V",
		"Injector group A, short circuit to ground",
		"Injector drive voltage, faulty",
		"Injector group A, injection error",
		"Injector group B, short circuit to other bank",
		"Injector group B, short circuit +24V",
		"Injector group B, short circuit ground",
		"Injection error, group B",
		"EMS, Default EOL Data in E2",
		"EMS, Default Barcoding Data in E2",
		"EMS internal software error",
		"EMS Configuration for Automatic Clutch Faulty",
		"Internal software error",
		"SCR main unit, high temperature low limit exceedence",
		"Aftertreatment Diesel Exhaust Fluid Tank Temperature",
		"Aftertreatment Diesel Exhaust Fluid Tank Temperature",
		"Aftertreatment Diesel Exhaust Fluid Tank Temperature",
		"Aftertreatment Intake NOx",
		"NOx sensor upstream, internal fault or open circuit",
		"NOx sensor upstream, open circuit",
		"Aftertreatment Intake NOx",
		"NOx sensor upstream, internal fault",
		"NOx sensor upstream of catalytic converter",
		"Aftertreatment Intake NOx",
		"NOx sensor upstream of catalytic converter",
		"NOx sensor upstream, stuck",
		"NOx sensor upstream, low signal",
		"NOx sensor upstream, too low value",
		"NOx sensor upstream error via CAN",
		"Aftertreatment Intake NOx",
		"NOx sensor upstream, not plausible",
		"Aftertreatment Outlet NOx",
		"Aftertreatment Outlet NOx",
		"NOx sensor downstream, internal fault or open circuit",
		"Aftertreatment Outlet NOx",
		"NOx sensor downstream, open circuit",
		"Aftertreatment Outlet NOx",
		"Aftertreatment Outlet NOx",
		"NOx sensor downstream, internal fault",
		"NOx sensor downstream error via CAN",
		"NOx sensor downstream of the SCR catalytic converter",
		"NOx sensor downstream, stuck",
		"NOx sensor downstream, low signal",
		"NOx sensor downstream, too low value",
		"NOx sensor downstream of the catalytic converter",
		"NOx sensor downstream, not plausible",
		"Upstream catalyst temperature sensor not plausible, not plausible",
		"Upstream catalyst temperature sensor not plausible, to high",
		"Upstream catalyst temperature sensor not plausible, short circuit",
		"Upstream catalyst temperature sensor not plausible, open circuit",
		"Aftertreatment Exhaust Gas Temperature ",
		"Upstream catalyst temperature sensor not plausible, to high",
		"Upstream catalyst temperature sensor not plausible, not plausible",
		"Aftertreatment Exhaust Gas Temperature ",
		"Aftertreatment Exhaust Gas Temperature ",
		"Upstream catalyst temperature too high",
		"Upstream catalyst temperature sensor not plausible, to low",
		"CAN Error from Exhaust Temperature Sensors",
		"Upstream DPF temperature sensor, to high",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Upstream DPF temperature sensor, not plausible",
		"Upstream DPF temperature sensor, not plausible",
		"Upstream DPF temperature too high during normal condition",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Upstream DPF temperature too high during regeneration",
		"Aftertreatment Diesel Particulate Filter Intake Gas Temperature",
		"Aftertreatment Exhaust Gas Temperature",
		"Aftertreatment Exhaust Gas Temperature",
		"Aftertreatment Exhaust Gas Temperature",
		"Auxiliary Temperature Sensor Error on CAN",
		"Downstream DPF temperature sensor error",
		"Exhaust temperature sensor after SCR catalytic converter, short circuit",
		"Exhaust temperature sensor after SCR catalytic converter, open circuit",
		"Aftertreatment Diesel Particulate Filter Outlet Gas Temperature",
		"Aftertreatment Diesel Particulate Filter Outlet Gas Temperature",
		"Downstream exhaust temperature sensor, not plausible",
		"Aftertreatment Diesel Particulate Filter Outlet Gas Temperature",
		"Aftertreatment Diesel Particulate Filter Outlet Gas Temperature",
		"Downstream DPF temperature too high during normal condition",
		"Downstream DPF temperature too high during regeneration",
		"Aftertreatment Diesel Particulate Filter Outlet Gas Temperature",
		"Aftertreatment Exhaust Gas Temperature ",
		"Aftertreatment Exhaust Gas Temperature ",
		"Particulate filter is missing",
		"Fault in the filter system",
		"Fault in the filter system",
		"Differential pressure sensor over particulate filter, faulty",
		"Differential pressure sensor not plausible",
		"Differential pressure sensor over particulate filter, not plausible",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"EEC unit has no contact with sensors",
		"Exhaust gas temperature upstream",
		"Exhaust gas temperature upstream",
		"EEC unit has no contact with sensors",
		"Electrical fault in the sensors",
		"Electrical fault in the sensors",
		"Electrical fault in the sensors",
		"EEC unit has no contact with sensors",
		"Intercooler temperature, too low",
		"Intercooler pressure sensor, short circuit to ground",
		"Intercooler pressure sensor, short circuit to +24",
		"Intercooler pressure sensor, stuck",
		"Intercooler pressure sensor, not plausible",
		"Intercooler pressure sensor, not plausible",
		"Intercooler pressure, above normal",
		"Intercooler pressure, above normal",
		"Intercooler pressure too high",
		"Intercooler pressure too low",
		"SCR system adaptation have reached max values",
		"SCR system adaptation have reached min values",
		"EEC3 System has demanded \"SCR Hazardous major functional failure\" actions",
		"SCR main unit, ventilation valve test, short to battery",
		"SCR main unit, internal supply voltage low",
		"SCR main unit, ventilation valve test, open load",
		"SCR main unit, system voltage error",
		"SCR main unit, ignition switch plausible error",
		"EEC3 has demanded \"SCR Major functional failure reductant dosing stopped\" actions",
		"EEC3 System has demanded \"SCR minor functional failure\" actions",
		"SCR main unit, error",
		"SCR main unit, internal supply voltage high",
		"SCR main unit, communication error",
		"Reductant doser injection valve",
		"SCR reductant dosing valve, short circuit to battery",
		"Reductant doser injection valve",
		"SCR reductant dosing valve, open circuit",
		"Reductant doser injection valve",
		"SCR main unit, reductant pressure not plausible",
		"Reductant pressure",
		"Reductant pressure",
		"SCR reductant pressure, error",
		"SCR main unit, reductant heater, circuit high",
		"SCR main unit, reductant heater, open load",
		"SCR main unit, internal heating pump, short circuit to battery",
		"SCR main unit, reductant temperature sensor circuit low",
		"SCR main unit, internal heating pump, open load",
		"Reductant doser fault",
		"SCR main unit, reductant heater, circuit performance",
		"SCR reagent tank temperature too high",
		"SCR main unit, high temperature high limit exceeded",
		"SCR reductant tank temperatur too low",
		"SCR main unit, low temperature limit exceeded",
		"Throttle, control error",
		"Throttle Actuator, short circuit to +24V",
		"Throttle Actuator, short circuit",
		"Throttle Actuator, slow response",
		"Throttle Actuator Control System - Forced Limited Power",
		"Throttle, stuck in open position",
		"Throttle, stuck in closed position",
		"Reductant doser fault",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in doser valve",
		"Fault in dose valve sensor",
		"Fault in dose valve sensor",
		"Reductant circuit pressure",
		"Reductant circuit pressure",
		"Reductant circuit pressure",
		"SCR main unit, air pressure too low",
		"SCR main unit, air pressure sensor after orifice circuit supply",
		"SCR main unit, air pressure sensor after orifice circuit high",
		"SCR main unit, air pressure sensor after orifice circuit low",
		"SCR, air circuit blocked",
		"SCR main unit, air pressure sensor after orifice performance",
		"EEC, air supply low",
		"SCR main unit, air pressure sensor after orifice plausible error",
		"Reductant doser temperature",
		"Reductant pick up fault",
		"Boost pressure sensor and ambient pressure sensor do not correlate",
		"Boost pressure sensor and ambient pressure sensor do not correlate",
		"Boost pressure sensor and ambient pressure sensor do not correlate",
		"Incorrect EMS shutdown",
		"Incorrect EEC shutdown",
		"Throttle Position Sensor 2, short circuit to +24V",
		"Throttle Position Sensor 2, short circuit to ground",
		"EGR position sensor, short circuit to +24V",
		"EGR position sensor, short circuit to ground",
		"EGR SRA reports a warning during Learn Stops.",
		"EGR position sensor, outside the permitted range",
		"EGR SRA reports it has a continuous fault.",
		"EGR position sensor, not plausible",
		"EGR SRA reports a running conditions warning  for high temp or low voltage.",
		"EGR CAN timeout",
		"EGR position sensor, voltage shows large variation in open position",
		"EGR position sensor, voltage shows large variation in closed position",
		"Particulate filter, clogged",
		"Particulate filter, ash level too high",
		"Exhaust temperature sensors, not plausible",
		"NOx level after catalytic converter too high",
		"NOx Exceedence - Root Cause Unknown",
		"SCR main unit, NOx level too high",
		"NOx Exceedence - Interruption of Reagent Dosing Activity",
		"NOx Exceedence - Empty Reagent Tank",
		"Engine position sensor 1, faulty",
		"Engine position sensor 1, too weak signal",
		"Engine position sensor 1, faulty",
		"Engine position sensor 1, Gap Puls or Sync error",
		"Engine position sensor 1, time out",
		"Engine position sensor 1, position diff",
		"Engine speed sensor faulty",
		"Failure in the NOx control monitoring system",
		"Reductant doser fault",
		"Reductant doser fault",
		"Reductant doser fault",
		"Reductant doser fault",
		"Reductant doser fault",
		"Reductant doser fault",
		"Reductant doser fault",
		"Reductant doser fault",
		"SCR reductant pressure error",
		"EEC3 has demanded \"SCR Hazardous functional failure reductant dosing stopped\" actions",
		"Urea pressure sensor, plausible error during start-up",
		"Urea pressure sensor, SRC high",
		"Urea pressure sensor, SRC low",
		"Urea pressure sensor, pressure too high not plausible",
		"Reductant circuit pressure",
		"Reductant circuit pressure",
		"Reductant pressure",
		"Reductant circuit pressure",
		"Reductant doser temperature",
		"Reductant doser temperature",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant hose fault",
		"Reductant heater fault",
		"Reductant heater fault",
		"Reductant heater fault",
		"Reductant heater fault",
		"Reductant heater fault",
		"Reductant pump fault, pump speed too high",
		"Reductant pump fault, pump speed too low",
		"Reductant pump no contact with EEC",
		"Reductant pump voltage supply",
		"Reductant pump voltage supply",
		"Reductant pump voltage supply",
		"Reductant circuit pressure",
		"Reductant pump speed",
		"Reductant pump speed",
		"Reductant pick up fault",
		"Particulate filter is clogged, hazardous",
		"Particulate filter is clogged, major",
		"Upstream exhaust temperature sensor error",
		"Upstream exhaust temperature sensor, stuck",
		"Upstream exhaust temperature sensor error",
		"Upstream exhaust temperature sensor, not plausible",
		"Upstream exhaust temperature sensor, above limit",
		"Upstream exhaust temperature sensor, below limit",
		"Particulate filter, temperature drop not plausible",
		"Upstream exhaust temperature too low during regeneration",
		"Coolant water pump actuator, faulty",
		"Coolant water pump actuator, short circiut on high side",
		"Coolant water pump actuator, short circiut on low side",
		"Coolant pump speed sensor, stuck",
		"Electrically controlled coolant pump",
		"Coolant pump speed sensor, no signal",
		"Boost temperature to high, not plausible",
		"EGR bypass actuator, faulty",
		"EGR bypass actuator, short circuit high to +24V",
		"EGR bypass actuator, short circuit high to ground",
		"EGR bypass actuator, open load",
		"Throttle M42, CAN interface fault",
		"Throttle M42, supply voltage fault",
		"Throttle M42, current limited",
		"Throttle M42, overload",
		"Throttle M42 has detected a CAN timeout",
		"Throttle M42, control error",
		"Throttle M42, internal fault",
		"Throttle M42, software execution error",
		"Throttle M42, unsuccessful learning of the reference position",
		"Throttle M42 has detected a CAN timeout",
		"Throttle M42, too high temperature",
		"Throttle M42, CAN timeout",
		"Throttle M42, service mode enabled",
		"Wastegate actuator, short circuit to +24V",
		"Wastegate actuator, short circuit",
		"Wastegate actuator, short circuit to ground",
		"Wastegate actuator, short circuit",
		"Exhaust brake actuator, control fault",
		"Exhaust brake actuator, short circuit to +24V",
		"Exhaust brake actuator, short circuit to ground",
		"Exhaust brake actuator, stuck in open position",
		"Exhaust brake actuator, faulty",
		"Exhaust brake actuator, stuck in closed position",
		"Exhaust brake actuator, control fault",
		"Exhaust brake actuator, fault with stop position",
		"Exhaust brake actuator, over temperature",
		"Exhaust brake actuator, CAN timeout",
		"Exhaust brake actuator, error",
		"Reductant pump control",
		"Reductant pump control",
		"Reductant pump control",
		"Reductant tank temperature sensor, not plausible",
		"Reductant tank temperature sensor, short circuit",
		"Reductant tank temperature sensor, open load",
		"SCR water valve, short circuit to battery",
		"SCR water valve, open load",
		"SCR main unit, reductant quality too low",
		"Not Define."
	};
	// --, 150202 bwk
	/////////////////////////////////////////////////////////////////////
	
}
