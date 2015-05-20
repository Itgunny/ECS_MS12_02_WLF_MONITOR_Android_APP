package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import customlist.sensormonitoring.IconTextItem;
import customlist.sensormonitoring.IconTextListAdapter;

public class ServiceMenuSensorMonitoringHiddenFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int NumofItem = 20;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	
	ImageButton imgbtnUp;
	ImageButton imgbtnDown;
	
	// TextView
	TextView textViewEpprValue;
	
	// ListView
	ListView listView;
	// ListItem
	IconTextListAdapter adapter;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int nFanRpm;
	int nEpprCurrent;
	boolean EpprActive = false;
	
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
		 TAG = "ServiceMenuSensorMonitoringHiddenFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_service_sensormonitoring_hidden, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		CAN1Comm.Set_TestMode_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Fan_EPPR_Current_Adjust));
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
		CAN1Comm.Set_TestMode_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
	}
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_sensormonitoring_hidden_low_ok);
		
		imgbtnUp = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_plus);
		imgbtnDown = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_service_sensormonitoring_hidden_minus);
		textViewEpprValue = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_sensormonitoring_hidden_data);
		
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_management_service_sensormonitoring_hidden);
		adapter = new IconTextListAdapter(ParentActivity);
		adapter.clearItem();
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		nFanRpm = 0;
		nEpprCurrent = 0;
		EpprActive = false;
		listView.setAdapter(adapter);
		
		
		CursurIndex = 1;
		
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
		imgbtnUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPlus();
			}
		});
		imgbtnDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMinus();
			}
		});
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		nFanRpm = CAN1Comm.Get_CoolingFanSpeed_318_PGN65369();
		nEpprCurrent = CAN1Comm.Get_CoolingFanValveCurrent_146_PGN65369();	
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		StatusListDisplay(nFanRpm,nEpprCurrent);
	}
	/////////////////////////////////////////////////////////////////////	

	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodySensorMonitoring();
	}
	public void ClickPlus(){
		int EpprCurrent = CAN1Comm.Get_CoolingFanValveCurrent_146_PGN65369();
		EpprActive = true;
		if(EpprCurrent <= 95){
			EpprCurrent += 5;
		}
		else{
			EpprCurrent = 100;
		}
		CAN1Comm.Set_TestMode_PGN61184_61(1);
		CAN1Comm.Set_CoolingFanValveCurrent_146_PGN61184_61(EpprCurrent);
		CAN1Comm.TxCANToMCU(61);
		
		CAN1Comm.Set_TestMode_PGN61184_61(3);
		Log.d(TAG,"RightKeyClick, EpprCurrent : " + Integer.toString(EpprCurrent));
	}
	public void ClickMinus(){
		int EpprCurrent = CAN1Comm.Get_CoolingFanValveCurrent_146_PGN65369();
		EpprActive = true;
		if(EpprCurrent >= 5){
			EpprCurrent -= 5;
		}
		else{
			EpprCurrent = 0;
		}
		CAN1Comm.Set_TestMode_PGN61184_61(1);
		CAN1Comm.Set_CoolingFanValveCurrent_146_PGN61184_61(EpprCurrent);
		CAN1Comm.TxCANToMCU(61);
		
		CAN1Comm.Set_TestMode_PGN61184_61(3);
		Log.d(TAG,"LeftKeyClick, EpprCurrent : " + Integer.toString(EpprCurrent));
	}
	/////////////////////////////////////////////////////////////////////

	public void StatusListDisplay(int FanRpm, int EpprCurrent){
		if(FanRpm == 10000)
			FanRpm = 0;
		else if (FanRpm < 10000)
			FanRpm = 0;
		else if (FanRpm > 10000)
			FanRpm = FanRpm - 10000;
		adapter.clearItem();
		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_dark),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line)
				,"Fan rpm", Integer.toString(FanRpm), "rpm"));
		adapter.addItem(new IconTextItem(ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_light),ParentActivity.getResources().getDrawable(R.drawable.menu_management_machine_monitoring_bg_line)
				, "Engine Cooling Fan Valve Current", Integer.toString(EpprCurrent), "%"));		// ++, --, 150407 bwk HHI 요청으로 전류단위를 mA에서 %로 변경
		adapter.notifyDataSetChanged();
		textViewEpprValue.setText(Integer.toString(EpprCurrent));
						
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
		
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickMinus();
			break;
		case 2:
			ClickPlus();
			break;
		case 3:
			ClickOK();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnDown.setPressed(true);
			imgbtnUp.setPressed(false);
			imgbtnOK.setPressed(false);
			break;
		case 2:
			imgbtnDown.setPressed(false);
			imgbtnUp.setPressed(true);
			imgbtnOK.setPressed(false);
			break;
		case 3:
			imgbtnDown.setPressed(false);
			imgbtnUp.setPressed(false);
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
}
