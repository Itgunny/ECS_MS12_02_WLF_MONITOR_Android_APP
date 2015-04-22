package taeha.wheelloader.fseries_monitor.main.a.key;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainAKeyWorkLoadDisplayFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioDaily;
	RadioButton radioTotalA;
	RadioButton radioTotalB;
	RadioButton radioTotalC;
	
	ImageButton imgbtnOK;
	
	TextView textViewInitialization;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingDisplayMode;
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
		 TAG = "MainAKeyWorkLoadDisplayFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_a_workload_display, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_DISPLAY;
		WeighingDisplayDisplay(WeighingDisplayMode);
		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		SavePref();
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		radioDaily = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_display_daily);
		radioTotalA = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_display_totala);
		radioTotalB = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_display_totalb);
		radioTotalC = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_a_workload_display_totalc);
			
		
		textViewInitialization = (TextView)mRoot.findViewById(R.id.textView_key_main_a_workload_display_init);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_a_workload_display_low_ok);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		WeighingDisplayMode = CAN1Comm.Get_WeighingDisplayMode1_1910_PGN65450();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		radioDaily.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDaily();
			}
		});
		radioTotalA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalA();
			}
		});
		radioTotalB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalB();
			}
		});
		radioTotalC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalC();
			}
		});
		textViewInitialization.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickInit();
			}
		});
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
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
	public void WeighingDisplayDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY:
			radioDaily.setChecked(true);
			radioTotalA.setChecked(false);
			radioTotalB.setChecked(false);
			radioTotalC.setChecked(false);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Daily) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			radioDaily.setChecked(false);
			radioTotalA.setChecked(true);
			radioTotalB.setChecked(false);
			radioTotalC.setChecked(false);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Total_A) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			radioDaily.setChecked(false);
			radioTotalA.setChecked(false);
			radioTotalB.setChecked(true);
			radioTotalC.setChecked(false);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Total_B) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			radioDaily.setChecked(false);
			radioTotalA.setChecked(false);
			radioTotalB.setChecked(false);
			radioTotalC.setChecked(true);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Total_C) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			break;
		
		default:
			break;
		}
		
	}
	
	public void ClickDaily(){
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_DAILY;
		WeighingDisplayDisplay(WeighingDisplayMode);
	}
	public void ClickTotalA(){
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A;
		WeighingDisplayDisplay(WeighingDisplayMode);
	}
	public void ClickTotalB(){
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B;
		WeighingDisplayDisplay(WeighingDisplayMode);
	}
	public void ClickTotalC(){
		WeighingDisplayMode = CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C;
		WeighingDisplayDisplay(WeighingDisplayMode);
	}
	public void ClickInit(){
//		CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(WeighingDisplayMode);
//		CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
//		CAN1Comm.TxCANToMCU(62);
//		CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
		ParentActivity.OldScreenIndex = ParentActivity.ScreenIndex;
		ParentActivity.showWorkLoadWeighingInit1();
	}
	public void ClickOK(){
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(WeighingDisplayMode);
		CAN1Comm.TxCANToMCU(62);
		CAN1Comm.Set_WeighingDisplayMode1_1910_PGN61184_62(15);
		showWorkLoadAnimation();
	}
	public void showWorkLoadAnimation(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD;
		ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment = new MainAKeyWorkLoadFragment();
		ParentActivity._MainABaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment);
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	
}