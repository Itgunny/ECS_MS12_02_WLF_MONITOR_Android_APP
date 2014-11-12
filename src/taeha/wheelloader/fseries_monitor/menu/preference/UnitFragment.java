package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.SharedPreferences;
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
		radioTempC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTempC();
			}
		});
		radioTempF.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTempF();
			}
		});
		radioSpeedKM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSpeedKM();
			}
		});
		radioSpeedMile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSpeedMile();
			}
		});
		radioWeightTon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeightTon();
			}
		});
		radioWeightLB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWeightLB();
			}
		});
		radioPressureBar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPressureBar();
			}
		});
		radioPressureMpa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPressureMpa();
			}
		});
		radioPressureKgf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickPressureKgf();
			}
		});
		radioPressurePsi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
		
		SavePref();
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
}
