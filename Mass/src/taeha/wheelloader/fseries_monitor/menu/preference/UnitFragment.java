package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;

public class UnitFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	RadioButton radioTempC;
	RadioButton radioTempF;
	
	RadioButton radioSpeedKM;
	RadioButton radioSpeedMile;
	
	RadioButton radioWeightTon;
	RadioButton radioWeightLB;
	
	RadioButton radioPressureBar;
	RadioButton radioPressureMpa;
	RadioButton radioPressureKgf;
	RadioButton radioPressurePsi;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int UnitOdo;
	int UnitTemp;
	int UnitWeight;
	int UnitPressure;
	
	Handler HandleCursurDisplay;
	int CursurIndex;
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
		 TAG = "UnitFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_unit, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_UNIT_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Unit_Setting));
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_unit_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_unit_low_cancel);
		
		radioTempC = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_temp_c);
		radioTempF = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_temp_f);
		
		radioSpeedKM = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_speed_km);
		radioSpeedMile = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_speed_mph);
		
		radioWeightTon = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_weight_ton);
		radioWeightLB = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_weight_lb);
		
		radioPressureBar = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_bar);
		radioPressureMpa = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_mpa);
		radioPressureKgf = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_kgfcm);
		radioPressurePsi = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_psi);
		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		UnitOdo = ParentActivity.UnitOdo;
		UnitTemp = ParentActivity.UnitTemp;
		UnitWeight = ParentActivity.UnitWeight;
		UnitPressure = ParentActivity.UnitPressure;
		
		TempDisplay(UnitTemp);
		SpeedDisplay(UnitOdo);
		WeightDisplay(UnitWeight);
		PressureDisplay(UnitPressure);
		
		CursurFirstDisplay(UnitTemp);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		radioTempC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTempC();
			}
		});
		radioTempF.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTempF();
			}
		});
		radioSpeedKM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickSpeedKM();
			}
		});
		radioSpeedMile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickSpeedMile();
			}
		});
		radioWeightTon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickWeightTon();
			}
		});
		radioWeightLB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickWeightLB();
			}
		});
		radioPressureBar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressureBar();
			}
		});
		radioPressureMpa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressureMpa();
			}
		});
		radioPressureKgf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressureKgf();
			}
		});
		radioPressurePsi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressurePsi();
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
		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);

		ParentActivity.UnitOdo = UnitOdo;
		ParentActivity.UnitTemp = UnitTemp;
		ParentActivity.UnitWeight = UnitWeight;
		ParentActivity.UnitPressure = UnitPressure;
		
		CAN1Comm.Set_SpeedmeterUnitChange_PGN65327(UnitOdo);
		CAN1Comm.TxCANToMCU(47);
		CAN1Comm.Set_SpeedmeterUnitChange_PGN65327(3);
		
		ParentActivity.SavePref();
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
	}
	public void ClickTempC(){
		UnitTemp = Home.UNIT_TEMP_C;
		TempDisplay(UnitTemp);
	}
	public void ClickTempF(){
		UnitTemp = Home.UNIT_TEMP_F;
		TempDisplay(UnitTemp);
	}
	public void ClickSpeedKM(){
		UnitOdo = Home.UNIT_ODO_KM;
		SpeedDisplay(UnitOdo);
	}
	public void ClickSpeedMile(){
		UnitOdo = Home.UNIT_ODO_MILE;
		SpeedDisplay(UnitOdo);
	}
	public void ClickWeightTon(){
		UnitWeight = Home.UNIT_WEIGHT_TON;
		WeightDisplay(UnitWeight);
	}
	public void ClickWeightLB(){
		UnitWeight = Home.UNIT_WEIGHT_LB;
		WeightDisplay(UnitWeight);
	}
	public void ClickPressureBar(){
		UnitPressure = Home.UNIT_PRESSURE_BAR;
		PressureDisplay(UnitPressure);
	}
	public void ClickPressureMpa(){
		UnitPressure = Home.UNIT_PRESSURE_MPA;
		PressureDisplay(UnitPressure);
	}
	public void ClickPressureKgf(){
		UnitPressure = Home.UNIT_PRESSURE_KGF;
		PressureDisplay(UnitPressure);
	}
	public void ClickPressurePsi(){
		UnitPressure = Home.UNIT_PRESSURE_PSI;
		PressureDisplay(UnitPressure);
	}
	/////////////////////////////////////////////////////////////////////
	public void TempDisplay(int _data){
		switch (_data) {
		case Home.UNIT_TEMP_C:
			radioTempC.setChecked(true);
			radioTempF.setChecked(false);
			break;
		case Home.UNIT_TEMP_F:
			radioTempC.setChecked(false);
			radioTempF.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void SpeedDisplay(int _data){
		switch (_data) {
		case Home.UNIT_ODO_KM:
			radioSpeedKM.setChecked(true);
			radioSpeedMile.setChecked(false);
			break;
		case Home.UNIT_ODO_MILE:
			radioSpeedKM.setChecked(false);
			radioSpeedMile.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void WeightDisplay(int _data){
		switch (_data) {
		case Home.UNIT_WEIGHT_TON:
			radioWeightTon.setChecked(true);
			radioWeightLB.setChecked(false);
			break;
		case Home.UNIT_WEIGHT_LB:
			radioWeightTon.setChecked(false);
			radioWeightLB.setChecked(true);
			break;
		default:
			break;
		}
	}
	public void PressureDisplay(int _data){
		switch (_data) {
		case Home.UNIT_PRESSURE_BAR:
			radioPressureBar.setChecked(true);
			radioPressureMpa.setChecked(false);
			radioPressureKgf.setChecked(false);
			radioPressurePsi.setChecked(false);
			break;
		case Home.UNIT_PRESSURE_MPA:
			radioPressureBar.setChecked(false);
			radioPressureMpa.setChecked(true);
			radioPressureKgf.setChecked(false);
			radioPressurePsi.setChecked(false);
			break;
		case Home.UNIT_PRESSURE_KGF:
			radioPressureBar.setChecked(false);
			radioPressureMpa.setChecked(false);
			radioPressureKgf.setChecked(true);
			radioPressurePsi.setChecked(false);
			break;
		case Home.UNIT_PRESSURE_PSI:
			radioPressureBar.setChecked(false);
			radioPressureMpa.setChecked(false);
			radioPressureKgf.setChecked(false);
			radioPressurePsi.setChecked(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("UnitOdo", UnitOdo);
		edit.putInt("UnitTemp", UnitTemp);
		edit.putInt("UnitWeight", UnitWeight);
		edit.putInt("UnitPressure", UnitPressure);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 12;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
		
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 12:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickTempF();
			break;
		case 2:
			ClickTempC();
			break;
		case 3:
			ClickSpeedKM();
			break;
		case 4:
			ClickSpeedMile();
			break;
		case 5:
			ClickWeightTon();
			break;
		case 6:
			ClickWeightLB();
			break;
		case 7:
			ClickPressureBar();
			break;
		case 8:
			ClickPressureMpa();
			break;
		case 9:
			ClickPressureKgf();
			break;
		case 10:
			ClickPressurePsi();
			break;
		case 11:
			ClickCancel();
			break;
		case 12:
			ClickOK();
			break;

		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case Home.UNIT_TEMP_C:
		default:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case Home.UNIT_TEMP_F:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
	
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		radioTempC.setPressed(false);
		radioTempF.setPressed(false);
		radioSpeedKM.setPressed(false);
		radioSpeedMile.setPressed(false);
		radioWeightTon.setPressed(false);
		radioWeightLB.setPressed(false);
		radioPressureBar.setPressed(false);
		radioPressureMpa.setPressed(false);
		radioPressureKgf.setPressed(false);
		radioPressurePsi.setPressed(false);
		switch (Index) {
		case 1:
			radioTempF.setPressed(true);
			break;
		case 2:
			radioTempC.setPressed(true);
			break;
		case 3:
			radioSpeedKM.setPressed(true);
			break;
		case 4:
			radioSpeedMile.setPressed(true);
			break;
		case 5:
			radioWeightTon.setPressed(true);
			break;
		case 6:
			radioWeightLB.setPressed(true);
			break;
		case 7:
			radioPressureBar.setPressed(true);
			break;
		case 8:
			radioPressureMpa.setPressed(true);
			break;
		case 9:
			radioPressureKgf.setPressed(true);
			break;
		case 10:
			radioPressurePsi.setPressed(true);
			break;
		case 11:
			imgbtnCancel.setPressed(true);
			break;
		case 12:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
}
