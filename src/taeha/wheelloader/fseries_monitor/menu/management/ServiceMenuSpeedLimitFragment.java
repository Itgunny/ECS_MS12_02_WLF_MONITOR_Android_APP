package taeha.wheelloader.fseries_monitor.menu.management;

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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class ServiceMenuSpeedLimitFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final int MAX_LEVEL = 40;
	private static final int MIN_LEVEL = 20;
	
	private static final int TOTAL_STEP = 100;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

	RadioButton radioSetting;
	RadioButton radioClear;
	
	TextView textViewSpeed;
	TextView textViewSpeedMax;
	TextView textViewSpeedMin;
	
	SeekBar	seekbarSpeed;
	
	RelativeLayout layoutSpeed;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int nSpeedLimit;
	int SpeedLimitStatus;
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
		 TAG = "ServiceMenuSpeedLimitFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_service_speedlimit, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SPEEDLIMIT_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Speed_Limit_Setting));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_speedlimit_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_service_speedlimit_low_cancel);
	
		radioSetting = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_speedlimit_use_setting);
		radioClear = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_management_service_speedlimit_use_clear);
		
		textViewSpeed = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_speedlimit_time);
		textViewSpeedMax = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_speedlimit_max);
		textViewSpeedMin = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_service_speedlimit_min);
		
		layoutSpeed = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_management_service_speedlimit_time);

		seekbarSpeed = (SeekBar)mRoot.findViewById(R.id.seekBar_menu_body_management_service_speedlimit);
		
		seekbarSpeed.setMax(TOTAL_STEP);
		seekbarSpeed.incrementProgressBy(1);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		nSpeedLimit = CAN1Comm.Get_VehicleSpeedLimit_572_PGN61184_106();
		SpeedLimitStatus = CAN1Comm.Get_VehicleSpeedLimitMode_575_PGN65434();
		
		if(nSpeedLimit < MIN_LEVEL)
			nSpeedLimit = MIN_LEVEL;
		else if(nSpeedLimit > MAX_LEVEL)
			nSpeedLimit = MAX_LEVEL;
		
		if(ParentActivity.UnitOdo == Home.UNIT_ODO_MILE){
			textViewSpeedMax.setText(ParentActivity.GetRideControlSpeed(40,ParentActivity.UnitOdo) + ParentActivity.getResources().getString(string.mph));
			textViewSpeedMin.setText(ParentActivity.GetRideControlSpeed(20,ParentActivity.UnitOdo) + ParentActivity.getResources().getString(string.mph));
		}else{
			textViewSpeedMax.setText(ParentActivity.GetRideControlSpeed(40,ParentActivity.UnitOdo) + ParentActivity.getResources().getString(string.km_h));
			textViewSpeedMin.setText(ParentActivity.GetRideControlSpeed(20,ParentActivity.UnitOdo) + ParentActivity.getResources().getString(string.km_h));
		}
		
		SpeedLimitStatusDisplay(SpeedLimitStatus);
		SpeedDisplay(nSpeedLimit,ParentActivity.UnitOdo);
		SetSeekBarPosition(seekbarSpeed,nSpeedLimit);
		
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
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		radioSetting.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSetting();
			}
		});
		radioClear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickClear();
			}
		});
		seekbarSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int progress = seekBar.getProgress();

				if(progress < 12){
					nSpeedLimit = 20;
				}else if(progress < 37){
					nSpeedLimit = 25;
				}else if(progress < 62){
					nSpeedLimit = 30;
				}else if(progress < 87){
					nSpeedLimit = 35;
				}else{
					nSpeedLimit = 40;
				}
				SetSeekBarPosition(seekBar, nSpeedLimit);
				SpeedDisplay(nSpeedLimit,ParentActivity.UnitOdo);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if(progress < 12){
					nSpeedLimit = 20;
				}else if(progress < 37){
					nSpeedLimit = 25;
				}else if(progress < 62){
					nSpeedLimit = 30;
				}else if(progress < 87){
					nSpeedLimit = 35;
				}else{
					nSpeedLimit = 40;
				}
				SetSeekBarPosition(seekBar, nSpeedLimit);
				SpeedDisplay(nSpeedLimit,ParentActivity.UnitOdo);
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	/////////////////////////////////////////////////////////////////////	
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();

		if(SpeedLimitStatus == CAN1CommManager.DATA_STATE_LAMP_OFF){	
			CAN1Comm.Set_VehicleSpeedLimitMode_575_PGN61184_104(CAN1CommManager.DATA_STATE_LAMP_OFF);
			CAN1Comm.TxCANToMCU(104);
			CAN1Comm.Set_VehicleSpeedLimitMode_575_PGN61184_104(3);
		}else if(SpeedLimitStatus == CAN1CommManager.DATA_STATE_LAMP_ON){
			CAN1Comm.Set_SettingSelection_PGN61184_105(3);
			CAN1Comm.Set_SpeedometerFrequency_534_PGN61184_105(0xFFFF);
			CAN1Comm.Set_AutoRideControlOperationSpeedForward_PGN61184_105(0xF);
			CAN1Comm.Set_AutoRideControlOperationSpeedBackward_PGN61184_105(0xF);
			CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(nSpeedLimit);
			CAN1Comm.TxCANToMCU(105);
			CAN1Comm.Set_SettingSelection_PGN61184_105(15);
			CAN1Comm.Set_VehicleSpeedLimit_572_PGN61184_105(0xFF);
			
			
			CAN1Comm.Set_VehicleSpeedLimitMode_575_PGN61184_104(CAN1CommManager.DATA_STATE_LAMP_ON);
			CAN1Comm.TxCANToMCU(104);
			CAN1Comm.Set_VehicleSpeedLimitMode_575_PGN61184_104(3);
		}
		
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showServiceMenuListAnimation();
	}
	public void ClickSetting(){
		SpeedLimitStatus = CAN1CommManager.DATA_STATE_LAMP_ON;
		SpeedLimitStatusDisplay(SpeedLimitStatus);
	}
	public void ClickClear(){
		SpeedLimitStatus = CAN1CommManager.DATA_STATE_LAMP_OFF;
		SpeedLimitStatusDisplay(SpeedLimitStatus);
	}
	/////////////////////////////////////////////////////////////////////
	public void SpeedLimitStatusDisplay(int _data){
		switch (_data) {
		case CAN1CommManager.DATA_STATE_LAMP_OFF:
			radioClear.setChecked(true);
			radioSetting.setChecked(false);
			layoutSpeed.setVisibility(View.INVISIBLE);
			break;
		case CAN1CommManager.DATA_STATE_LAMP_ON:
			radioClear.setChecked(false);
			radioSetting.setChecked(true);
			layoutSpeed.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	public void SpeedDisplay(int _data, int _unit){
		String strSpeed;
		strSpeed = ParentActivity.GetRideControlSpeed(_data, _unit);
		if(_unit == Home.UNIT_ODO_MILE){
			textViewSpeed.setText(strSpeed + " " + ParentActivity.getResources().getString(R.string.mph));
		}else{
			textViewSpeed.setText(strSpeed + " " + ParentActivity.getResources().getString(R.string.km_h));
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	public void SetSeekBarPosition(SeekBar _seekbar, int _Speed){
		int Progress;
		
		if(_Speed < 23){
			Progress = 0;
		}else if(_Speed < 28){
			Progress = 25;
		}else if(_Speed < 33){
			Progress = 50;
		}else if(_Speed < 38){
			Progress = 75;
		}else{
			Progress = 100;
		}
		
		_seekbar.setProgress(Progress);
	}
	/////////////////////////////////////////////////////////////////////
}
