package taeha.wheelloader.fseries_monitor.menu.monitoring;

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
import android.os.Build;
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
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OperationHistoryFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;

	TextView textViewWorkDaily;
	TextView textViewWorkTotalA;
	TextView textViewWorkTotalB;
	TextView textViewWorkTotalC;
	TextView textViewOdoTotal;
	TextView textViewOdoLatest;
	TextView textViewHourLatest;
	
	TextView textViewWorkDailyUnit;
	TextView textViewWorkTotalAUnit;
	TextView textViewWorkTotalBUnit;
	TextView textViewWorkTotalCUnit;
	TextView textViewOdoTotalUnit;
	TextView textViewOdoLatestUnit;
	TextView textViewHourLatestUnit;
	
	TextView textViewInit;
	
	public CheckBox checkWorkTotalA;
	public CheckBox checkWorkTotalB;
	public CheckBox checkWorkTotalC;
	public CheckBox checkOdoLatest;
	public CheckBox checkHourLatest;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeightInfoDataCurrent;
	int WeightInfoDataDay1;
	int WeightInfoDataToday;
	int WeightInfoDataTotalA;
	int WeightInfoDataTotalB;
	int WeightInfoDataTotalC;
	
	int TotalOdometer;
	int LatestOdometer;
	int LatestHourmeter;
	
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
		 TAG = "OperationHistoryFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_operationhistory, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Operation_History));
		
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_operationhistory_low_ok);

		textViewWorkDaily = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_daily_data);
		textViewWorkTotalA = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totala_data);
		textViewWorkTotalB = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalb_data);
		textViewWorkTotalC = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalc_data);
		textViewOdoTotal = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_total_data);
		textViewOdoLatest = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_latest_data);
		textViewHourLatest = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_hour_latest_data);
		
		textViewWorkDailyUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_daily_unit);
		textViewWorkTotalAUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totala_unit);
		textViewWorkTotalBUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalb_unit);
		textViewWorkTotalCUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalc_unit);
		textViewOdoTotalUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_total_unit);
		textViewOdoLatestUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_latest_unit);
		textViewHourLatestUnit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_hour_latest_unit);
		
		textViewInit = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_init);

		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){	
			checkWorkTotalA = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totala);
			checkWorkTotalA.setPadding(50, 0, 0, 0);
			checkWorkTotalB = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalb);
			checkWorkTotalB.setPadding(50, 0, 0, 0);
			checkWorkTotalC = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalc);
			checkWorkTotalC.setPadding(50, 0, 0, 0);
			checkOdoLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_odo_latest);
			checkOdoLatest.setPadding(50, 0, 0, 0);
			checkHourLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_hour_latest);
			checkHourLatest.setPadding(50, 0, 0, 0);
		} else {
			checkWorkTotalA = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totala);
			checkWorkTotalB = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalb);
			checkWorkTotalC = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalc);
			checkOdoLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_odo_latest);
			checkHourLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_hour_latest);
		}
		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
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
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		textViewInit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickInit();
			}
		});
		checkWorkTotalA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		checkWorkTotalB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		checkWorkTotalC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		checkOdoLatest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		checkHourLatest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
	
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		WeightInfoDataDay1 = CAN1Comm.Get_ADayBeforeWeight_1916_PGN65452();
		WeightInfoDataToday = CAN1Comm.Get_TodayWeight_1915_PGN65450();
		WeightInfoDataTotalA = CAN1Comm.Get_TotalWorkAWeight_1912_PGN65451();
		WeightInfoDataTotalB = CAN1Comm.Get_TotalWorkBWeight_1913_PGN65451();
		WeightInfoDataTotalC = CAN1Comm.Get_TotalWorkCWeight_1914_PGN65452();
		WeightInfoDataCurrent = CAN1Comm.Get_CurrentWeight_1911_PGN65450();
		
		TotalOdometer = CAN1Comm.Get_TotalVehicleDistance_601_PGN65389();
		LatestOdometer = CAN1Comm.Get_TripDistance_600_PGN65389();
		LatestHourmeter = CAN1Comm.Get_TripTime_849_PGN65344();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		WorkDisplay(WeightInfoDataToday,ParentActivity.UnitWeight,textViewWorkDaily,textViewWorkDailyUnit);
		WorkDisplay(WeightInfoDataTotalA,ParentActivity.UnitWeight,textViewWorkTotalA,textViewWorkTotalAUnit);
		WorkDisplay(WeightInfoDataTotalB,ParentActivity.UnitWeight,textViewWorkTotalB,textViewWorkTotalBUnit);
		WorkDisplay(WeightInfoDataTotalC,ParentActivity.UnitWeight,textViewWorkTotalC,textViewWorkTotalCUnit);
		
		OdometerDislay(TotalOdometer,ParentActivity.UnitOdo,textViewOdoTotal,textViewOdoTotalUnit);
		OdometerDislay(LatestOdometer,ParentActivity.UnitOdo,textViewOdoLatest,textViewOdoLatestUnit);
		LatestHourmeterDislay(LatestHourmeter);
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyMonitoringAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_TOP);
	}
	public void ClickInit(){
		ParentActivity.showOperationHistoryInit();
	}
	/////////////////////////////////////////////////////////////////////
	public void WorkDisplay(int _data, int _unit, TextView _textviewData, TextView _textviewUnit){
		_textviewData.setText(ParentActivity.GetWeighit(_data, _unit));
		if(_unit == ParentActivity.UNIT_WEIGHT_LB){
			_textviewUnit.setText(ParentActivity.getResources().getString(string.lb));
			_textviewUnit.setText(ParentActivity.getResources().getString(string.lb));
		}else{
			_textviewUnit.setText(ParentActivity.getResources().getString(string.ton));
			_textviewUnit.setText(ParentActivity.getResources().getString(string.ton));
		}
	}
	public void OdometerDislay(int _data, int _unit, TextView _textviewData, TextView _textviewUnit){
		_textviewData.setText(ParentActivity.GetOdometerStrng(_data,_unit));
		if(_unit == ParentActivity.UNIT_ODO_MILE){
			_textviewUnit.setText(ParentActivity.getResources().getString(string.mile));
		}else{
			_textviewUnit.setText(ParentActivity.getResources().getString(string.km));
		}
	}

	public void LatestHourmeterDislay(int _data){
		textViewHourLatest.setText(ParentActivity.GetHourmeterString(_data));
		textViewHourLatestUnit.setText(ParentActivity.getResources().getString(string.Hr));
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 7;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
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
		case 3:	
		case 4:
		case 5:
		case 6:
			
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 7:
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
			if(checkWorkTotalA.isChecked() == true){
				checkWorkTotalA.setChecked(false);
			}else{
				checkWorkTotalA.setChecked(true);
			}
			break;
		case 2:
			if(checkOdoLatest.isChecked() == true){
				checkOdoLatest.setChecked(false);
			}else{
				checkOdoLatest.setChecked(true);
			}
			break;
		case 3:
			if(checkWorkTotalB.isChecked() == true){
				checkWorkTotalB.setChecked(false);
			}else{
				checkWorkTotalB.setChecked(true);
			}
			break;
		case 4:
			if(checkHourLatest.isChecked() == true){
				checkHourLatest.setChecked(false);
			}else{
				checkHourLatest.setChecked(true);
			}
			break;
		case 5:
			if(checkWorkTotalC.isChecked() == true){
				checkWorkTotalC.setChecked(false);
			}else{
				checkWorkTotalC.setChecked(true);
			}
			break;
		case 6:
			ClickInit();
			break;
		case 7:
			ClickOK();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		textViewInit.setPressed(false);
		imgbtnOK.setPressed(false);
		checkWorkTotalA.setPressed(false);
		checkWorkTotalB.setPressed(false);
		checkWorkTotalC.setPressed(false);
		checkOdoLatest.setPressed(false);
		checkHourLatest.setPressed(false);
		
		switch (CursurIndex) {
		case 1:
			checkWorkTotalA.setPressed(true);
			break;
		case 2:
			checkOdoLatest.setPressed(true);
			break;
		case 3:
			checkWorkTotalB.setPressed(true);
			break;
		case 4:
			checkHourLatest.setPressed(true);
			break;
		case 5:
			checkWorkTotalC.setPressed(true);
			break;
		case 6:
			textViewInit.setPressed(true);
			break;
		case 7:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
}
