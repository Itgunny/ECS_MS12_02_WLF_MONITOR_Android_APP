package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class MainBKeyRideControlSpeedFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////

	
	private  final int MAX_LEVEL	 	= 15;
	private  final int MIN_LEVEL 		= 1;
	
	private  final int TOTAL_STEP		= 15;
	private  final int STEP			= 1;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewForwardTitle;
	TextFitTextView textViewBackwardTitle;
	TextFitTextView textViewForwardData;
	TextFitTextView textViewBackwardData;
	TextFitTextView textViewForwardMax;
	TextFitTextView textViewForwardMin;
	TextFitTextView textViewBackwardMax;
	TextFitTextView textViewBackwardMin;
	
	TextFitTextView	textViewOK;
	CheckBox checkBoxOnAlways;
	ImageButton imgbtnOK;
	ImageButton imgbtnOnAlways;
	
	SeekBar seekBarForward;
	SeekBar seekBarBackward;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int RideControl;
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
		 TAG = "MainBKeyRideControlSpeedFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_ridecontrol_speed, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED;
		
		SpeedDisplay(textViewForwardMax,MAX_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewForwardMin,MIN_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewBackwardMax,MAX_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewBackwardMin,MIN_LEVEL,ParentActivity.UnitOdo);
		SpeedDisplay(textViewForwardData,SpeedForward,ParentActivity.UnitOdo);
		SpeedDisplay(textViewBackwardData,SpeedBackward,ParentActivity.UnitOdo);
		SetSeekBarPosition(seekBarForward,SpeedForward-1);
		SetSeekBarPosition(seekBarBackward,SpeedBackward-1);
		checkRideControlManual(RideControl);
		
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
		
		textViewForwardTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_forward_title);
		textViewForwardTitle.setText(getString(ParentActivity.getResources().getString(R.string.Forward), 181));
		textViewBackwardTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_backward_title);
		textViewBackwardTitle.setText(getString(ParentActivity.getResources().getString(R.string.Backward), 182));
		textViewForwardData = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_forward_value);
		textViewBackwardData = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_backward_value);
		textViewForwardMax = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_forward_max);
		textViewForwardMin = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_forward_min);
		textViewBackwardMax = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_backward_max);
		textViewBackwardMin = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_backward_min);
		
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_speed_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		checkBoxOnAlways = (CheckBox)mRoot.findViewById(R.id.checkBox_key_main_b_ridecontrol_speed_low_onalway);
		checkBoxOnAlways.setText(getString(ParentActivity.getResources().getString(R.string.On_Always), 23));
		
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_ridecontrol_speed_low_ok);
		imgbtnOnAlways = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_ridecontrol_speed_low_onalways);
		
		seekBarForward = (SeekBar)mRoot.findViewById(R.id.seekBar_key_main_b_ridecontrol_speed_forward);
		seekBarBackward = (SeekBar)mRoot.findViewById(R.id.seekBar_key_main_b_ridecontrol_speed_backward);
		
		
		seekBarForward.setMax(TOTAL_STEP-1);
		seekBarForward.incrementProgressBy(1);
		
		seekBarBackward.setMax(TOTAL_STEP-1);
		seekBarBackward.incrementProgressBy(1);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();		
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
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
		checkBoxOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnAlways();
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
	
	public void checkRideControlManual(int data) {
		if(data == CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL) {
			checkBoxOnAlways.setChecked(true);
			seekBarForward.setAlpha((float)0.2);
			seekBarForward.setClickable(false);
			seekBarForward.setEnabled(false);
			seekBarBackward.setAlpha((float)0.2);
			seekBarBackward.setClickable(false);
			seekBarBackward.setEnabled(false);
		} else {
			seekBarForward.setAlpha((float)1.0);
			seekBarForward.setClickable(true);
			seekBarForward.setEnabled(true);
			seekBarBackward.setAlpha((float)1.0);
			seekBarBackward.setClickable(true);
			seekBarBackward.setEnabled(true);
		}
	}
	public void SpeedDisplay(TextView textview, int Data, int Unit){
		String strSpeed;
		strSpeed = ParentActivity.GetRideControlSpeed(Data, Unit);
		if(Unit == ParentActivity.UNIT_ODO_MILE){
			textview.setText(strSpeed + " " + getString(ParentActivity.getResources().getString(R.string.mph), 32));
		}else{
			textview.setText(strSpeed + " " + getString(ParentActivity.getResources().getString(R.string.km_h), 31));
		}
	}
	public void SetSeekBarPosition(SeekBar _seekbar, int _Speed){
		int Progress = _Speed;
		_seekbar.setProgress(Progress);
	}
	
	public void ClickOK(){
		if(checkBoxOnAlways.isChecked()) {
			CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL);	// Off
			CAN1Comm.TxCANToMCU(247);
			showRideControlAnimation();
		} else {
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
	}
	public void ClickCancel(){
		showRideControlAnimation();
	}
	public void ClickOnAlways(){
		if(checkBoxOnAlways.isChecked() == false) {
			seekBarForward.setAlpha((float)1.0);
			seekBarForward.setClickable(true);
			seekBarForward.setEnabled(true);
			seekBarBackward.setAlpha((float)1.0);
			seekBarBackward.setClickable(true);
			seekBarBackward.setEnabled(true);
		} else {
			seekBarForward.setAlpha((float)0.2);
			seekBarForward.setClickable(false);
			seekBarForward.setEnabled(false);
			seekBarBackward.setAlpha((float)0.2);
			seekBarBackward.setClickable(false);
			seekBarBackward.setEnabled(false);
			ParentActivity.showRideControlPopup();
		}
	}
	//Progress////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
	//Back////////////////////////////////////////////////////////////////
	public void showRideControlAnimation(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL;
		ParentActivity._MainBBaseFragment._MainBKeyRideControlFragment = new MainBKeyRideControlFragment();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBKeyRideControlFragment);
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			if(seekBarForward.isEnabled()) {
				SpeedForward -= 1;
				if(SpeedForward < MIN_LEVEL){
					SpeedForward = MIN_LEVEL;
				}
				seekBarForward.setProgress(SpeedForward-1);
			}
			break;
		case 2:
			if(seekBarBackward.isEnabled()) {
				SpeedBackward -= 1;
				if(SpeedBackward < MIN_LEVEL){
					SpeedBackward = MIN_LEVEL;
				}
				seekBarBackward.setProgress(SpeedBackward-1);
			}
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
		case 1:
			if(seekBarForward.isEnabled()) {
				SpeedForward += 1;
				if(SpeedForward > MAX_LEVEL){
					SpeedForward = MAX_LEVEL;
				}
				seekBarForward.setProgress(SpeedForward-1);
			}
			break;
		case 2:
			if(seekBarBackward.isEnabled()) {
				SpeedBackward += 1;
				if(SpeedBackward > MAX_LEVEL){
					SpeedBackward = MAX_LEVEL;
				}
				seekBarBackward.setProgress(SpeedBackward-1);
			}
			break;	
		case 3:
			CursurIndex++;
			CursurDisplay(CursurIndex);
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
			if(checkBoxOnAlways.isChecked() == true){
				checkBoxOnAlways.setChecked(false);
				seekBarForward.setAlpha((float)1.0);
				seekBarForward.setClickable(true);
				seekBarForward.setEnabled(true);
				seekBarBackward.setAlpha((float)1.0);
				seekBarBackward.setClickable(true);
				seekBarBackward.setEnabled(true);
			}else{
				checkBoxOnAlways.setChecked(true);
				seekBarForward.setAlpha((float)0.2);
				seekBarForward.setClickable(false);
				seekBarForward.setEnabled(false);
				seekBarBackward.setAlpha((float)0.2);
				seekBarBackward.setClickable(false);
				seekBarBackward.setEnabled(false);
				ParentActivity.showRideControlPopup();
			}
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
		imgbtnOK.setPressed(false);
		checkBoxOnAlways.setPressed(false);
		switch (CursurIndex) {
		case 1:
			seekBarForward.setPressed(true);
			break;
		case 2:
			seekBarBackward.setPressed(true);
			break;
		case 3:
			checkBoxOnAlways.setPressed(true);	
			break;
		case 4:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
}