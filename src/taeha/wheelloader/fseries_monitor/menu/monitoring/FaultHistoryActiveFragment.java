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
		
		ClickMachine();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_ACTIVE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Active_Fault));
		return mRoot;
	}
	
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
		radioACU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickACU();
			}
		});
		
		textViewDetailTitle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDetailTitle();
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
		
		FATCSettingTemperatureCelsius = CAN1Comm.Get_FATCSettingTemperatureCelsius_3408_PGN65373();
		FATCSettingTemperatureFahrenheit = CAN1Comm.Get_FATCSettingTemperatureFahrenheit_3409_PGN65373();
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
		
		Err_ACU[0] = CAN1Comm.Get_FATCSettingTemperatureCelsius_3408_PGN65373();
		Err_ACU[1] = CAN1Comm.Get_FATCSettingTemperatureFahrenheit_3409_PGN65373();
		Err_ACU[2] = CAN1Comm.Get_Ambienttemperaturesensoropen_PGN65373();
		Err_ACU[3] = CAN1Comm.Get_Ambienttemperaturesensorshort_PGN65373();
		Err_ACU[4] = CAN1Comm.Get_Incabtemperaturesensoropen_PGN65373();
		Err_ACU[5] = CAN1Comm.Get_Incabtemperaturesensorshort_PGN65373();
		Err_ACU[6] = CAN1Comm.Get_Evaptemperaturesensoropen_PGN65373();
		Err_ACU[7] = CAN1Comm.Get_Evaptemperaturesensorshort_PGN65373();
		Err_ACU[8] = CAN1Comm.Get_Mode1actuatoropenshort_PGN65373();
		Err_ACU[9] = CAN1Comm.Get_Mode1actuatordrivecircuitmalfunction_PGN65373();
		Err_ACU[10] = CAN1Comm.Get_Intakeactuatoropenshort_PGN65373();
		Err_ACU[11] = CAN1Comm.Get_Intakeactuatordrivecircuitmalfunction_PGN65373();
		Err_ACU[12] = CAN1Comm.Get_Temperatureactuatoropenshort_PGN65373();
		Err_ACU[13] = CAN1Comm.Get_Temperatureactuatordrivecircuitmalfunction_PGN65373();
		Err_ACU[14] = CAN1Comm.Get_Ducttemperaturesensoropen_PGN65373();
		Err_ACU[15] = CAN1Comm.Get_Ducttemperaturesensorshort_PGN65373();
		Err_ACU[16] = CAN1Comm.Get_WaterValveSensorError_PGN65373();
		Err_ACU[17] = CAN1Comm.Get_GPSCircuitError_PGN65373();
		
		DTCTotalACU = CheckACUDTCNumber();
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
		
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
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
	}
	
	public void ClickDetailTitle(){
		DetailEnable(false);
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
		for(int i = 0; i < 18; i++){
			if(Err_ACU[i] == 1){
				adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), 
						"No : " + Integer.toString(i), "", ""));
			}
		}
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
					adapter.addItem(new IconTextItemFault(null,ParentActivity.getResources().getDrawable(R.drawable.menu_information_fault_down_btn), "SPN : " + Integer.toString(SPN)
							+ "     " + "FMI : " + Integer.toString(FMI), "", ""));
				}
				else if(Mode == REQ_ERR_TM_ACTIVE)		// TCU
				{
					Err_Tcu = CAN1Comm.Get_TcuErr();
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
					//	Log.d(TAG,"SetErrList Err_EHCU[i] : 0x0" + Integer.toHexString(Err_EHCU[i]));
					}
					
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
			}
			else if(SendSeqIndex > DTCTotalPacketMachine){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_ENGINE_ACTIVE;
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
			}
			else if(SendSeqIndex > DTCTotalPacketEngine){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_TM_ACTIVE;
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
			}
			else if(SendSeqIndex > DTCTotalPacketTM){
				SendSeqIndex = 1;
				SendDTCIndex = REQ_ERR_MACHINE_ACTIVE;
			}
			else{
				RequestErrorCode(SendDTCIndex,1,SendSeqIndex);
				SendSeqIndex++;
			}
			SetThreadSleepTime(1000);
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
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		
	}
	public void ClickRight(){
		
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		
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
	/////////////////////////////////////////////////////////////////////
	
}
