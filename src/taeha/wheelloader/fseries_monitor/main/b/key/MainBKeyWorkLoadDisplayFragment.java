package taeha.wheelloader.fseries_monitor.main.b.key;

import android.content.SharedPreferences;
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
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBKeyWorkLoadDisplayFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RadioButton radioDaily;
	RadioButton radioTotalA;
	RadioButton radioTotalB;
	RadioButton radioTotalC;
	
	ImageButton imgbtnOK;
	
	//TextView textViewInitialization;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int WeighingDisplayMode;
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
		 TAG = "MainBKeyWorkLoadDisplayFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.key_main_b_workload_display, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_DISPLAY;
		WeighingDisplayDisplay(WeighingDisplayMode);
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
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
		radioDaily = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_display_daily);
		radioTotalA = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_display_totala);
		radioTotalB = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_display_totalb);
		radioTotalC = (RadioButton)mRoot.findViewById(R.id.radioButton_key_main_b_workload_display_totalc);
			
		
//		textViewInitialization = (TextView)mRoot.findViewById(R.id.textView_key_main_b_workload_display_init);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_key_main_b_workload_display_low_ok);
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
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickDaily();
			}
		});
		radioTotalA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTotalA();
			}
		});
		radioTotalB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTotalB();
			}
		});
		radioTotalC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTotalC();
			}
		});
//		textViewInitialization.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ClickInit();
//			}
//		});
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
			CursurIndex = 1;
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			radioDaily.setChecked(false);
			radioTotalA.setChecked(true);
			radioTotalB.setChecked(false);
			radioTotalC.setChecked(false);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Total_A) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			CursurIndex = 2;
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			radioDaily.setChecked(false);
			radioTotalA.setChecked(false);
			radioTotalB.setChecked(true);
			radioTotalC.setChecked(false);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Total_B) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			CursurIndex = 3;
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			radioDaily.setChecked(false);
			radioTotalA.setChecked(false);
			radioTotalB.setChecked(false);
			radioTotalC.setChecked(true);
//			textViewInitialization.setText(ParentActivity.getResources().getString(R.string.Total_C) 
//					+ " " + ParentActivity.getResources().getString(R.string.Initialization));
			CursurIndex = 4;
			break;
		
		default:
			CursurIndex = 1;
			break;
		}
		CursurDisplay(CursurIndex);
		
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
//	public void ClickInit(){
////		CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(WeighingDisplayMode);
////		CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
////		CAN1Comm.TxCANToMCU(62);
////		CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
//		ParentActivity.OldScreenIndex = ParentActivity.ScreenIndex;
//		ParentActivity.showWorkLoadWeighingInit1();
//	}
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

		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_DISPLAY;
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD;
		ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment = new MainBKeyWorkLoadFragment();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment);
	}
	
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 5;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
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
		case 4:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 5:
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickDaily();
			break;
		case 2:
			ClickTotalA();
			break;
		case 3:
			ClickTotalB();
			break;
		case 4:
			ClickTotalC();
			break;
		case 5:
			ClickOK();
			break;
		default:
			break;
		}
	}
	
	public void CursurDisplay(int Index){
		radioDaily.setPressed(false);
		radioTotalA.setPressed(false);
		radioTotalB.setPressed(false);
		radioTotalC.setPressed(false);
		imgbtnOK.setPressed(false);
		switch (CursurIndex) {
			case 1:
				radioDaily.setPressed(true);
				break;
			case 2:
				radioTotalA.setPressed(true);
				break;
			case 3:
				radioTotalB.setPressed(true);
				break;
			case 4:
				radioTotalC.setPressed(true);
				break;
			case 5:
				imgbtnOK.setPressed(true);
				break;
			default:
				break;
		}
	}
	
}