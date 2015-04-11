package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.LongPressChecker;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.view.View.OnTouchListener;
import taeha.wheelloader.fseries_monitor.main.LongPressChecker.OnLongPressListener;

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
	int LastestConsumed;
	
	public LongPressChecker mLongPressChecker;	// ++, --, 150406 cjg
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
		// ++, 150406 cjg
//		imgbtnFuel.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(ClickFlag == true)
//					ClickFuel();
//			}
//		});
		imgbtnFuel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mLongPressChecker.getLongPressed() == false){
					if(ClickFlag == true)
						ClickFuel();
				}
			}
		});
		imgbtnFuel.setOnTouchListener( new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mLongPressChecker.deliverMotionEvent(v, event);
				return false;
			}
		});

		mLongPressChecker = new LongPressChecker(ParentActivity);
		mLongPressChecker.setOnLongPressListener( new OnLongPressListener() {
			@Override
			public void onLongPressed() {
//				Log.d(TAG, "Long Pressed");
				imgbtnFuel.setBackgroundResource(R.drawable.main_default_monitoringhistory);
				if(ParentActivity.FuelIndex == CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE)
					CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE_INFO_CLEAR);
				else if(ParentActivity.FuelIndex == CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED)
					CAN1Comm.Set_OperationHistory_1101_PGN61184_31(CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED_CLEAR);
				CAN1Comm.TxCANToMCU(31);
				CAN1Comm.Set_OperationHistory_1101_PGN61184_31(0xFF);
				
			}
		});
		// --, 150416 cjg
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		AverageFuelRate = CAN1Comm.Get_AverageFuelRate_333_PGN65390();
		LastestConsumed = CAN1Comm.Get_ADaysFuelUsed_1405_PGN65390();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		FuelTitleDisplay(ParentActivity.FuelIndex);
		FuelDataDisplay(ParentActivity.FuelIndex,AverageFuelRate,LastestConsumed);
	}
	/////////////////////////////////////////////////////////////////////	
	public void FuelTitleDisplay(int _Index){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
//			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,"");
//			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getResources().getString(string.AVERAGE_FUEL_RATE));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
			FuelTitleAnimation.FlipAnimation(textViewFuelTitle,getResources().getString(string.A_DAYS_FUEL_USED));
			break;
		default:
			break;
		}
	}
	public void FuelDataDisplay(int _Index, int AverageFuel, int LatestConsumed){
		switch (_Index) {
		case CAN1CommManager.DATA_STATE_FUEL_NOSELECT:
//			textViewFuelData.setText("");
//			textViewFuelUnit.setText("");
//			break;
		case CAN1CommManager.DATA_STATE_AVERAGE_FUEL_RATE:
			textViewFuelData.setText(ParentActivity.GetFuelRateString(AverageFuel));
			textViewFuelUnit.setText(ParentActivity.getResources().getString(string.l_h));
			break;
		case CAN1CommManager.DATA_STATE_A_DAYS_FUEL_USED:
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
		ParentActivity._MainBBaseFragment._MainBLeftDownFuelSelectFragment = new MainBLeftDownFuelSelectFragment();
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterHourOdometerFragment);
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftDownFuelSelectFragment);
		

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