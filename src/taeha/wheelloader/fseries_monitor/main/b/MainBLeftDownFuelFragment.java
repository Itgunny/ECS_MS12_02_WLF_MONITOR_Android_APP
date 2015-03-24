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

public class MainBLeftDownFuelFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewFuelTitle;
	TextView textViewFuelData;
	TextView textViewFuelUnit;
	
	ImageButton imgbtnFuel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	int AverageFuelRate;
	int CurrentFuelRate;
	int LastestConsumed;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	TextViewXAxisFlipAnimation FuelTitleAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBLeftDownFuelFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftdown_main_b_fuel, null);
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
		textViewFuelTitle = (TextView)mRoot.findViewById(R.id.textView_leftdown_fuel_title);
		textViewFuelData = (TextView)mRoot.findViewById(R.id.textView_leftdown_fuel_data);
		textViewFuelUnit = (TextView)mRoot.findViewById(R.id.textView_leftdown_fuel_unit);
		
		
		imgbtnFuel = (ImageButton)mRoot.findViewById(R.id.imageButton_leftdown_fuel);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		FuelTitleAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickFuel();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AverageFuelRate = CAN1Comm.Get_AverageFuelRate_PGN65390();
		CurrentFuelRate = CAN1Comm.Get_CurrentFuelRate_PGN65390();
		LastestConsumed = 0;
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		FuelTitleDisplay(ParentActivity.FuelIndex);
		FuelDataDisplay(ParentActivity.FuelIndex,AverageFuelRate,CurrentFuelRate,LastestConsumed);
	}
	/////////////////////////////////////////////////////////////////////	
	public void FuelTitleDisplay(int _Index){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_CURRENT_FUEL_RATE:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getResources().getString(string.Current_Fuel_Rate));
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getResources().getString(string.Average_Fuel_Rate));
			break;
		case CAN1CommManager.DATA_STATE_LATEST_FUEL_CONSUMED:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getResources().getString(string.Latest_Fuel_Consumed));
			break;
		default:
			break;
		}
	}
	public void FuelDataDisplay(int _Index, int CurrentFuel, int AverageFuel, int LatestConsumed){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_CURRENT_FUEL_RATE:
			textViewFuelData.setText(ParentActivity.GetFuelRateString(CurrentFuel));
			textViewFuelUnit.setText(ParentActivity.getResources().getString(string.l_h));
			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			textViewFuelData.setText(ParentActivity.GetFuelRateString(AverageFuel));
			textViewFuelUnit.setText(ParentActivity.getResources().getString(string.l_h));
			break;
		case CAN1CommManager.DATA_STATE_LATEST_FUEL_CONSUMED:
			textViewFuelData.setText(ParentActivity.GetFuelRateString(LatestConsumed));
			textViewFuelUnit.setText(ParentActivity.getResources().getString(string.l));
			break;
		default:
			break;
		}
		
	}
	public void ClickFuel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterHourOdometerFragment = new MainBCenterFuelFragment();
		ParentActivity._MainBBaseFragment._MainBLeftDownHourOdometerSelectFragment = new MainBLeftDownFuelSelectFragment();
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
		
		
	}
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnFuel.setClickable(ClickFlag);
	}
}