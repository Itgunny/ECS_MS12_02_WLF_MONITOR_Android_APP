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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
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
	TextFitTextView 	textViewOK;

	
	TextFitTextView textViewWorkDaily;
	TextFitTextView textViewWorkTotalA;
	TextFitTextView textViewWorkTotalB;
	TextFitTextView textViewWorkTotalC;
	TextFitTextView textViewOdoTotal;
	TextFitTextView textViewOdoLatest;
	TextFitTextView textViewHourLatest;
	
	TextFitTextView textViewWorkDailyTitle;
	TextFitTextView textViewOdoTotalTitle;
	
	TextFitTextView textViewWorkDailyUnit;
	TextFitTextView textViewWorkTotalAUnit;
	TextFitTextView textViewWorkTotalBUnit;
	TextFitTextView textViewWorkTotalCUnit;
	TextFitTextView textViewOdoTotalUnit;
	TextFitTextView textViewOdoLatestUnit;
	TextFitTextView textViewHourLatestUnit;
	
	TextFitTextView textViewInit;
	
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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Operation_History), 254);
		
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
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));

		textViewWorkDailyTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_daily);
		textViewWorkDailyTitle.setText(getString(ParentActivity.getResources().getString(R.string.Daily), 173));
		textViewOdoTotalTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_total);
		textViewOdoTotalTitle.setText(getString(ParentActivity.getResources().getString(R.string.Total_Odometer), 109));
		
		textViewWorkDaily = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_daily_data);
		textViewWorkTotalA = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totala_data);
		textViewWorkTotalB = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalb_data);
		textViewWorkTotalC = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalc_data);
		textViewOdoTotal = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_total_data);
		textViewOdoLatest = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_latest_data);
		textViewHourLatest = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_hour_latest_data);
		
		textViewWorkDailyUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_daily_unit);
		textViewWorkDailyUnit.setText(getString(ParentActivity.getResources().getString(R.string.ton), 11));
		textViewWorkTotalAUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totala_unit);
		textViewWorkTotalAUnit.setText(getString(ParentActivity.getResources().getString(R.string.ton), 11));
		textViewWorkTotalBUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalb_unit);
		textViewWorkTotalBUnit.setText(getString(ParentActivity.getResources().getString(R.string.ton), 11));
		textViewWorkTotalCUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_work_totalc_unit);
		textViewWorkTotalCUnit.setText(getString(ParentActivity.getResources().getString(R.string.ton), 11));
		textViewOdoTotalUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_total_unit);
		textViewOdoTotalUnit.setText(getString(ParentActivity.getResources().getString(R.string.km), 37));
		textViewOdoLatestUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_odo_latest_unit);
		textViewOdoLatestUnit.setText(getString(ParentActivity.getResources().getString(R.string.km), 37));
		textViewHourLatestUnit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_hour_latest_unit);
		textViewHourLatestUnit.setText(getString(ParentActivity.getResources().getString(R.string.Hr), 7));
		
		textViewInit = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_operationhistory_init);
		textViewInit.setText(getString(ParentActivity.getResources().getString(R.string.Initialization), 30));

		if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){	
			checkWorkTotalA = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totala);
			checkWorkTotalA.setPadding(50, 0, 0, 0);
			checkWorkTotalA.setText(getString(ParentActivity.getResources().getString(R.string.Total_A), 174));
			ParentActivity.setMarqueeText(checkWorkTotalA);
			checkWorkTotalB = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalb);
			checkWorkTotalB.setPadding(50, 0, 0, 0);
			checkWorkTotalB.setText(getString(ParentActivity.getResources().getString(R.string.Total_B), 175));
			ParentActivity.setMarqueeText(checkWorkTotalB);
			checkWorkTotalC = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalc);
			checkWorkTotalC.setPadding(50, 0, 0, 0);
			checkWorkTotalC.setText(getString(ParentActivity.getResources().getString(R.string.Total_C), 176));
			ParentActivity.setMarqueeText(checkWorkTotalC);
			checkOdoLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_odo_latest);
			checkOdoLatest.setPadding(50, 0, 0, 0);
			checkOdoLatest.setText(getString(ParentActivity.getResources().getString(R.string.Latest_Odometer), 110));
			ParentActivity.setMarqueeText(checkOdoLatest);
			checkHourLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_hour_latest);
			checkHourLatest.setPadding(50, 0, 0, 0);
			checkHourLatest.setText(getString(ParentActivity.getResources().getString(R.string.Latest_Hourmeter), 107));
			ParentActivity.setMarqueeText(checkHourLatest);
		} else {
			checkWorkTotalA = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totala);
			checkWorkTotalA.setText(getString(ParentActivity.getResources().getString(R.string.Total_A), 174));
			ParentActivity.setMarqueeText(checkWorkTotalA);
			checkWorkTotalB = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalb);
			checkWorkTotalB.setText(getString(ParentActivity.getResources().getString(R.string.Total_B), 175));
			ParentActivity.setMarqueeText(checkWorkTotalB);
			checkWorkTotalC = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_work_totalc);
			checkWorkTotalC.setText(getString(ParentActivity.getResources().getString(R.string.Total_C), 176));
			ParentActivity.setMarqueeText(checkWorkTotalC);
			checkOdoLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_odo_latest);
			checkOdoLatest.setText(getString(ParentActivity.getResources().getString(R.string.Latest_Odometer), 110));
			ParentActivity.setMarqueeText(checkOdoLatest);
			checkHourLatest = (CheckBox)mRoot.findViewById(R.id.checkBox_menu_body_monitoring_operationhistory_hour_latest);
			checkHourLatest.setText(getString(ParentActivity.getResources().getString(R.string.Latest_Hourmeter), 107));
			ParentActivity.setMarqueeText(checkHourLatest);
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
		if(_unit == Home.UNIT_WEIGHT_LB){
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.lb), 12));
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.lb), 12));
		}else if(ParentActivity.UnitWeight == Home.UNIT_WEIGHT_US_TON){
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.USTon), 467));
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.USTon), 467));
		}else{
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.ton), 11));
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.ton), 11));
		}
	}
	public void OdometerDislay(int _data, int _unit, TextView _textviewData, TextView _textviewUnit){
		_textviewData.setText(ParentActivity.GetOdometerStrng(_data,_unit));
		if(_unit == Home.UNIT_ODO_MILE){
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.mile), 38));
		}else{
			_textviewUnit.setText(getString(ParentActivity.getResources().getString(string.km), 37));
		}
	}

	public void LatestHourmeterDislay(int _data){
		textViewHourLatest.setText(ParentActivity.GetHourmeterString(_data));
		textViewHourLatestUnit.setText(getString(ParentActivity.getResources().getString(string.Hr), 7));
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
