package taeha.wheelloader.fseries_monitor.main.b.key;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
public class MainBKeyRideControlFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioOff;
	RadioButton radioOnAlways;
	RadioButton radioOnConditional;
	
	ImageButton imgbtnOK;
	TextFitTextView	textViewOK;
	
	TextFitTextView	textViewNotTitle;
	
	
	RelativeLayout	layoutAvailable;
	RelativeLayout	layoutNotAvailable;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int RideControl;
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
		TAG = "MainBKeyRideControlFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_ridecontrol, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_RIDECONTROL;
		RideControlDisplay(RideControl);
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
		radioOff = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_ridecontrol_off);
		radioOff.setText(getString(ParentActivity.getResources().getString(R.string.Off), 20));
		ParentActivity.setMarqueeRadio(radioOff);
		radioOnAlways = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_ridecontrol_on_always);
		radioOnAlways.setText(getString(ParentActivity.getResources().getString(R.string.On_Always), 23));
		ParentActivity.setMarqueeRadio(radioOnAlways);
		radioOnConditional = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_ridecontrol_on_conditional);
		radioOnConditional.setText(getString(ParentActivity.getResources().getString(R.string.On_Conditional_Speed), 168));
		ParentActivity.setMarqueeRadio(radioOnConditional);

		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_ridecontrol_low_ok);
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));

		textViewNotTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_key_main_b_ridecontrol_notavailable_title);
		textViewNotTitle.setText(getString(ParentActivity.getResources().getString(R.string.Ride_Control_is_NOT_equipped), 194));		
		layoutAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_ridecontrol_available);
		layoutNotAvailable = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_key_main_b_ridecontrol_notavailable);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
		CursurIndex = RideControl+1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOff();
			}
		});
		radioOnAlways.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnAlways();
			}
		});
		radioOnConditional.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOnConditional();
			}
		});
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		RideControl = CAN1Comm.Get_RideControlOperationStatus_3447_PGN65527();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RideControlDisplay(RideControl);
		KeyBGDisplay(RideControl);
	}
	/////////////////////////////////////////////////////////////////////	
	public void KeyBGDisplay(int Data){
		switch (Data){
		case CAN1CommManager.DATA_STATE_NOTAVAILABLE:
			layoutAvailable.setVisibility(View.INVISIBLE);
			layoutNotAvailable.setVisibility(View.VISIBLE);
			break;
		default:
			layoutAvailable.setVisibility(View.VISIBLE);
			layoutNotAvailable.setVisibility(View.INVISIBLE);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////	
	public void RideControlDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF:
			radioOff.setChecked(true);
			radioOnAlways.setChecked(false);
			radioOnConditional.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL:
			radioOff.setChecked(false);
			radioOnAlways.setChecked(true);
			radioOnConditional.setChecked(false);
			break;
		case CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_AUTO:
			radioOff.setChecked(false);
			radioOnAlways.setChecked(false);
			radioOnConditional.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void ClickOff(){
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_OFF);	// Off
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();
	}
	public void ClickOnAlways(){
		CAN1Comm.Set_RideControlOperationStatus_3447_PGN65527(CAN1CommManager.DATA_STATE_KEY_RIDECONTROL_MANUAL);	// Off
		CAN1Comm.TxCANToMCU(247);
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();	
	}
	public void ClickOnConditional(){
		ParentActivity._MainBBaseFragment.showRideControlSpeedAnimation();
	}
	public void ClickOK(){
		ParentActivity._MainBBaseFragment.showKeytoDefaultScreenAnimation();	
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 2:
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
		case 2:
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
			ClickOff();
			break;
		case 2:
			ClickOnAlways();
			break;
		case 3:
			ClickOnConditional();
			break;
		case 4:
			ClickOK();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioOff.setPressed(false);
		radioOnAlways.setPressed(false);
		radioOnConditional.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (CursurIndex) {
		case 1:
			radioOff.setPressed(true);
			break;
		case 2:
			radioOnAlways.setPressed(true);
			break;
		case 3:
			radioOnConditional.setPressed(true);
			break;
		case 4:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
}