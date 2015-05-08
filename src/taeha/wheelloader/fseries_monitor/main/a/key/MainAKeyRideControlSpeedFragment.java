package taeha.wheelloader.fseries_monitor.main.a.key;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainAKeyRideControlSpeedFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	
	private  final int MAX_LEVEL	 	= 15;
	private  final int MIN_LEVEL 		= 1;
	
	private  final int TOTAL_STEP		= 15;
	private  final int STEP			= 1;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewForwardData;
	TextView textViewBackwardData;
	TextView textViewForwardMax;
	TextView textViewForwardMin;
	TextView textViewBackwardMax;
	TextView textViewBackwardMin;
	
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	SeekBar seekBarForward;
	SeekBar seekBarBackward;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SpeedForward;
	int SpeedBackward;

	int CursurIndex;
	Handler HandleCursurDisplay;
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
		 TAG = "MainAKeyRideControlSpeedFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_ridecontrol_speed, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL_SPEED;
		
		SpeedDisplay(textViewForwardMax,MAX_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewForwardMin,MIN_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewBackwardMax,MAX_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewBackwardMin,MIN_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewForwardData,SpeedForward,ParentActivity.UnitOdo);
		SpeedDisplay(textViewBackwardData,SpeedBackward,ParentActivity.UnitOdo);
		SetSeekBarPosition(seekBarForward,SpeedForward-1);
		SetSeekBarPosition(seekBarBackward,SpeedBackward-1);
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
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
		textViewForwardData = (TextView)mRoot.findViewById(R.id.textView_key_main_a_ridecontrol_speed_forward_value);
		textViewBackwardData = (TextView)mRoot.findViewById(R.id.textView_key_main_a_ridecontrol_speed_backward_value);
		textViewForwardMax = (TextView)mRoot.findViewById(R.id.textView_key_main_a_ridecontrol_speed_forward_max);
		textViewForwardMin = (TextView)mRoot.findViewById(R.id.textView_key_main_a_ridecontrol_speed_forward_min);
		textViewBackwardMax = (TextView)mRoot.findViewById(R.id.textView_key_main_a_ridecontrol_speed_backward_max);
		textViewBackwardMin = (TextView)mRoot.findViewById(R.id.textView_key_main_a_ridecontrol_speed_backward_min);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_ridecontrol_speed_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_ridecontrol_speed_low_cancel);

		seekBarForward = (SeekBar)mRoot.findViewById(R.id.seekBar_key_main_a_ridecontrol_speed_forward);
		seekBarBackward = (SeekBar)mRoot.findViewById(R.id.seekBar_key_main_a_ridecontrol_speed_backward);
		
		
		seekBarForward.setMax(TOTAL_STEP-1);
		seekBarForward.incrementProgressBy(1);
		
		seekBarBackward.setMax(TOTAL_STEP-1);
		seekBarBackward.incrementProgressBy(1);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();		
		SpeedForward = CAN1Comm.Get_AutoRideControlOperationSpeedForward_574_PGN61184_106();
		SpeedBackward = CAN1Comm.Get_AutoRideControlOperationSpeedBackward_576_PGN61184_106();
		
		if(SpeedForward < MIN_LEVEL)
			SpeedForward = MIN_LEVEL;
		else if(SpeedForward > MAX_LEVEL)
			SpeedForward = MAX_LEVEL;
		
		if(SpeedBackward < MIN_LEVEL)
			SpeedBackward = MIN_LEVEL;
		else if(SpeedBackward > MAX_LEVEL)
			SpeedBackward = MAX_LEVEL;

	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		seekBarForward.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int progress = seekBar.getProgress();
			
				SpeedForward = progress+1;
				SpeedDisplay(textViewForwardData,SpeedForward,ParentActivity.UnitOdo);
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				SpeedForward = progress+1;
				SpeedDisplay(textViewForwardData,SpeedForward,ParentActivity.UnitOdo);
				
			}
		});
		seekBarBackward.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int progress = seekBar.getProgress();
				SpeedBackward = progress+1;
				SpeedDisplay(textViewBackwardData,SpeedBackward,ParentActivity.UnitOdo);
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				SpeedBackward = progress+1;
				SpeedDisplay(textViewBackwardData,SpeedBackward,ParentActivity.UnitOdo);
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
	public void SpeedDisplay(TextView textview, int Data, int Unit){
		String strSpeed;
		strSpeed = ParentActivity.GetRideControlSpeed(Data, Unit);
		if(Unit == ParentActivity.UNIT_ODO_MILE){
			textview.setText(strSpeed + " " + ParentActivity.getResources().getString(R.string.mph));
		}else{
			textview.setText(strSpeed + " " + ParentActivity.getResources().getString(R.string.km_h));
		}
	}
	public void SetSeekBarPosition(SeekBar _seekbar, int _Speed){
		int Progress = _Speed;
		
//		if(_Speed < 7){
//			Progress = 0;
//		}else if(_Speed < 12){
//			Progress = 33;
//		}else if(_Speed < 17){
//			Progress = 66;
//		}else{
//			Progress = 100;
//		}
		
		_seekbar.setProgress(Progress);
	}
	
	public void ClickOK(){
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(CAN1CommManager.DATA_STATE_RIDECONTROL_AUTO);
		CAN1Comm.TxCANToMCU(247);
		
		CAN1Comm.Set_SettingSelection_PGN61184_105(2);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(SpeedForward);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(SpeedBackward);
		CAN1Comm.TxCANToMCU(105);
		CAN1Comm.Set_SettingSelection_PGN61184_105(15);
		CAN1Comm.Set_AutoRideControlOperationSpeedForward_574_PGN61184_105(0xF);
		CAN1Comm.Set_AutoRideControlOperationSpeedBackward_576_PGN61184_105(0xF);
		showRideControlAnimation();
	}
	public void ClickCancel(){
		showRideControlAnimation();
	}
	//Progress////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
	//Back////////////////////////////////////////////////////////////////
	public void showRideControlAnimation(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_RIDECONTROL;
		ParentActivity._MainABaseFragment._MainAKeyRideControlFragment = new MainAKeyRideControlFragment();
		ParentActivity._MainABaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainAKeyRideControlFragment);
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			SpeedForward -= 1;
			if(SpeedForward < MIN_LEVEL){
				SpeedForward = MIN_LEVEL;
			}
			seekBarForward.setProgress(SpeedForward-1);	
			break;
		case 2:
			SpeedBackward -= 1;
			if(SpeedBackward < MIN_LEVEL){
				SpeedBackward = MIN_LEVEL;
			}
			seekBarBackward.setProgress(SpeedBackward-1);	
			break;
		case 3:
		case 4:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 1:
			SpeedForward += 1;
			if(SpeedForward > MAX_LEVEL){
				SpeedForward = MAX_LEVEL;
			}
			seekBarForward.setProgress(SpeedForward-1);	
			break;
		case 2:
			SpeedBackward += 1;
			if(SpeedBackward > MAX_LEVEL){
				SpeedBackward = MAX_LEVEL;
			}
			seekBarBackward.setProgress(SpeedBackward-1);	
			break;			
		case 4:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			ClickCancel();
			break;
		case 4:
			ClickOK();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		seekBarForward.setPressed(false);
		seekBarBackward.setPressed(false);
		imgbtnCancel.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (CursurIndex) {
		case 1:
			seekBarForward.setPressed(true);
			break;
		case 2:
			seekBarBackward.setPressed(true);
			break;
		case 3:
			imgbtnCancel.setPressed(true);
			break;
		case 4:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
}