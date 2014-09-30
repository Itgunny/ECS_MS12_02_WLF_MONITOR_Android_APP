package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainBLeftDownHourOdometerFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBLeftDownHourOdometerFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewHourOdoTitle;
	TextView textViewHourOdoData;
	TextView textViewHourOdoUnit;
	
	ImageButton imgbtnHourOdo;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	int TotalHourmeter;
	int LatestHourmeter;
	int TotalOdometer;
	int LatestOdometer;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	TextViewXAxisFlipAnimation HourOdometerTitleAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftdown_main_b_hourodometer, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}
	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewHourOdoTitle = (TextView)mRoot.findViewById(R.id.textView_leftdown_hourodometer_title);
		textViewHourOdoData = (TextView)mRoot.findViewById(R.id.textView_leftdown_hourodometer_data);
		textViewHourOdoUnit = (TextView)mRoot.findViewById(R.id.textView_leftdown_hourodometer_unit);
		
		
		imgbtnHourOdo = (ImageButton)mRoot.findViewById(R.id.imageButton_leftdown_hourodometer);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		HourOdometerTitleAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnHourOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickHourOdo();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		TotalHourmeter = CAN1Comm.Get_Hourmeter_1601_PGN65433();
		LatestHourmeter = CAN1Comm.Get_TripTime_849_PGN65344();
		TotalOdometer = CAN1Comm.Get_TotalVehicleDistance_601_PGN65389();
		LatestOdometer = CAN1Comm.Get_TripDistance_600_PGN65389();
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		try {
			HourOdometerTitleDisplay(ParentActivity.HourOdometerIndex);
			HourOdometerDataDisplay(ParentActivity.HourOdometerIndex,TotalHourmeter,LatestHourmeter,TotalOdometer,LatestOdometer);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e("TAG","IllegalStateException");
		}
	
	}
	/////////////////////////////////////////////////////////////////////	
	public void HourOdometerTitleDisplay(int _Index){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_HOURMETER_TOTAL:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.TOTAL_HOURMETER));
			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.LATEST_HOURMETER));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.TOTAL_ODOMETER));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			HourOdometerTitleAnimation.FlipAnimation(textViewHourOdoTitle,getResources().getString(string.LATEST_ODOMETER));
			break;

		default:
			break;
		}
	}
	public void HourOdometerDataDisplay(int _Index, int TotalHour, int LatestHour, int TotalOdo, int LatestOdo){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_HOURMETER_TOTAL:
			textViewHourOdoData.setText(ParentActivity.GetHourmeterString(TotalHour));
			textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.Hr));
			break;
		case CAN1CommManager.DATA_STATE_HOURMETER_LATEST:
			textViewHourOdoData.setText(ParentActivity.GetHourmeterString(LatestHour));
			textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.Hr));
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_TOTAL:
			textViewHourOdoData.setText(ParentActivity.GetOdometerStrng(TotalOdo,ParentActivity.UnitOdo));
			if(ParentActivity.UnitOdo == ParentActivity.UNIT_ODO_MILE){
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.mph));
			}else{
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.km_h));
			}
			break;
		case CAN1CommManager.DATA_STATE_ODOMETER_LATEST:
			textViewHourOdoData.setText(ParentActivity.GetOdometerStrng(LatestOdo,ParentActivity.UnitOdo));
			if(ParentActivity.UnitOdo == ParentActivity.UNIT_ODO_MILE){
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.mph));
			}else{
				textViewHourOdoUnit.setText(ParentActivity.getResources().getString(string.km_h));
			}
			break;

		default:
			break;
		}
		
	}
	public void ClickHourOdo(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterHourOdometerFragment = new MainBCenterHourOdometerFragment();
		ParentActivity._MainBBaseFragment._MainBLeftDownHourOdometerSelectFragment = new MainBLeftDownHourOdometerSelectFragment();
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterHourOdometerFragment);
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftDownHourOdometerSelectFragment);
		

		ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._MainBIndicatorFragment.DisableVirtualKey();
	}
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnHourOdo.setClickable(ClickFlag);
	}
}