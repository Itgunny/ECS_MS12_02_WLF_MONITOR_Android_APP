package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainBLeftUpMachineStatusSelectFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBLeftUpMachineStatusSelectFragment";
	
	private static final String TAB = "         ";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioHYDTemp;
	RadioButton radioTMOilTemp;
	RadioButton radioBatteryVoltage;
	RadioButton radioWeighingSystem;
	RadioButton radioCoolantTemp;
	
	TextView textViewOK;
	
	ImageView imgViewHYDTemp;
	ImageView imgViewTMOilTemp;
	ImageView imgViewBatteryVoltage;
	ImageView imgViewWeighingSystem;
	ImageView imgViewCoolantTemp;
	
	ImageButton imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftup_main_b_machinestatus_select, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS;
		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		radioHYDTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_hydtemp);
		radioTMOilTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_tmoiltemp);
		radioBatteryVoltage = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_battery);
		radioWeighingSystem = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_weighing);
		radioCoolantTemp = (RadioButton)mRoot.findViewById(R.id.radioButton_leftup_main_b_machinestatus_select_coolanttemp);
		
		textViewOK = (TextView)mRoot.findViewById(R.id.textView_leftup_main_b_machinestatus_select_ok);
		
		imgViewHYDTemp = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_hydtemp);
		imgViewTMOilTemp = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_tmoiltemp);
		imgViewBatteryVoltage = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_battery);
		imgViewWeighingSystem = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_weighing);
		imgViewCoolantTemp = (ImageView)mRoot.findViewById(R.id.imageView_leftup_main_b_machinestatus_select_coolanttemp);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_leftup_main_b_machinestatus_select_ok);
		
		radioHYDTemp.setText(TAB + ParentActivity.getResources().getString(string.HYD_Temp));
		radioTMOilTemp.setText(TAB + ParentActivity.getResources().getString(string.TM_Oil_Temp));
		radioBatteryVoltage.setText(TAB + ParentActivity.getResources().getString(string.Battery_Volt));
		radioWeighingSystem.setText(TAB + ParentActivity.getResources().getString(string.Weighing_System));
		radioCoolantTemp.setText(TAB + ParentActivity.getResources().getString(string.Coolant_Temp));
		
		 
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParentActivity._MainBBaseFragment.showDefaultScreenAnimation();
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
	
	public void ClickMode(){
		
	}
	public void ClickWarmingUp(){

	}
}