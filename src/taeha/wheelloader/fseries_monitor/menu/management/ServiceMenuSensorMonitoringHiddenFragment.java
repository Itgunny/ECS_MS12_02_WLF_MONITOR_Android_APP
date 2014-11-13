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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Fan_EPPR_Current_Adjust));
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
				, "Engine Cooling Fan Valve Current", Integer.toString(EpprCurrent), "rpm"));
		adapter.notifyDataSetChanged();
		textViewEpprValue.setText(Integer.toString(EpprCurrent));
						
	}
	/////////////////////////////////////////////////////////////////////
	
}
