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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
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
import android.widget.RelativeLayout;
import android.widget.TextView;


public class UnitFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView	textviewMetric;
	TextView	textviewUS;
	TextView	textviewCustom;
	
	ImageView	imageviewMetric;
	ImageView	imageviewUS;
	ImageView	imageviewCustom;
	
	RelativeLayout	layoutFuel;
	RelativeLayout	layoutTemp;
	RelativeLayout	layoutWeight;
	RelativeLayout	layoutSpeed;
	RelativeLayout	layoutPressure;
	
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	TextFitTextView	textViewOK;
	TextFitTextView	textViewCancel;
	
	RadioButton radioFuelL;
	RadioButton radioFuelGal;
	
	RadioButton radioTempC;
	RadioButton radioTempF;
	
	RadioButton radioSpeedKM;
	RadioButton radioSpeedMile;
	
	RadioButton radioWeightTon;
	RadioButton radioWeightLB;
	RadioButton	radioWeightUSTon;
	
	RadioButton radioPressureBar;
	RadioButton radioPressureMpa;
	RadioButton radioPressureKgf;
	RadioButton radioPressurePsi;
	
	TextFitTextView textViewFuelRate;
	TextFitTextView	textViewTemp;
	TextFitTextView	textViewSpeed;
	TextFitTextView	textViewWeight;
	TextFitTextView	textViewPressure;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int UnitType;
	int UnitFuel;
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
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Unit_Setting), 414);
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
		textviewMetric = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_metric);
		textviewMetric.setText(getString(ParentActivity.getResources().getString(R.string.METRIC), 461));
		textviewUS = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_us);
		textviewUS.setText(getString(ParentActivity.getResources().getString(R.string.US), 462));
		textviewCustom = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_custom);
		textviewCustom.setText(getString(ParentActivity.getResources().getString(R.string.CUSTOM), 463));
		
		imageviewMetric = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_preference_unit_metric);
		imageviewUS = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_preference_unit_us);
		imageviewCustom = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_preference_unit_custom);
		
		layoutFuel = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_unit_fuel);
		layoutTemp = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_unit_temp);
		layoutWeight = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_unit_weight);
		layoutSpeed = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_unit_speed);
		layoutPressure = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_preference_unit_pressure);
		
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_unit_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_unit_low_cancel);
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_low_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
		
		radioTempC = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_temp_c);
		radioTempC.setText(getString(ParentActivity.getResources().getString(R.string.Unit_C), 8));
		ParentActivity.setMarqueeRadio(radioTempC);
		radioTempF = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_temp_f);
		radioTempF.setText(getString(ParentActivity.getResources().getString(R.string.Unit_F), 9));
		ParentActivity.setMarqueeRadio(radioTempF);
		
		radioSpeedKM = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_speed_km);
		radioSpeedKM.setText(getString(ParentActivity.getResources().getString(R.string.km), 37));
		ParentActivity.setMarqueeRadio(radioSpeedKM);
		radioSpeedMile = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_speed_mph);
		radioSpeedMile.setText(getString(ParentActivity.getResources().getString(R.string.mile), 38));
		ParentActivity.setMarqueeRadio(radioSpeedMile);
		
		radioWeightTon = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_weight_ton);
		radioWeightTon.setText(getString(ParentActivity.getResources().getString(R.string.ton), 11));
		ParentActivity.setMarqueeRadio(radioWeightTon);
		radioWeightLB = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_weight_lb);
		radioWeightLB.setText(getString(ParentActivity.getResources().getString(R.string.lb), 12));
		ParentActivity.setMarqueeRadio(radioWeightLB);
		radioWeightUSTon = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_weight_uston);
		radioWeightUSTon.setText(getString(ParentActivity.getResources().getString(R.string.USTon), 467));
		ParentActivity.setMarqueeRadio(radioWeightUSTon);
		
		radioPressureBar = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_bar);
		radioPressureBar.setText(getString(ParentActivity.getResources().getString(R.string.bar), 43));
		ParentActivity.setMarqueeRadio(radioPressureBar);
		radioPressureMpa = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_mpa);
		radioPressureMpa.setText(getString(ParentActivity.getResources().getString(R.string.Mpa), 44));
		ParentActivity.setMarqueeRadio(radioPressureMpa);
		
		radioPressureKgf = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_kgfcm);
		radioPressureKgf.setText(getString(ParentActivity.getResources().getString(R.string.kgf_cm), 45));
		ParentActivity.setMarqueeRadio(radioPressureKgf);
		radioPressurePsi = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_pressure_psi);
		radioPressurePsi.setText(getString(ParentActivity.getResources().getString(R.string.Psi), 46));
		ParentActivity.setMarqueeRadio(radioPressurePsi);
		
		
		textViewFuelRate = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_fuel_title);
		textViewFuelRate.setText(getString(ParentActivity.getResources().getString(R.string.Volume), 468));
		textViewTemp = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_temp_title);
		textViewTemp.setText(getString(ParentActivity.getResources().getString(R.string.Temp), 39));
		textViewSpeed = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_speed_title);
		textViewSpeed.setText(getString(ParentActivity.getResources().getString(R.string.Distance), 471));
		textViewWeight = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_weight_title);
		textViewWeight.setText(getString(ParentActivity.getResources().getString(R.string.Weight), 41));
		textViewPressure = (TextFitTextView)mRoot.findViewById(R.id.textView_menu_body_preference_unit_pressure_title);
		textViewPressure.setText(getString(ParentActivity.getResources().getString(R.string.Pressure), 42));
		
		radioFuelL = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_fuel_l);
		radioFuelL.setText(getString(ParentActivity.getResources().getString(R.string.l), 81));
		ParentActivity.setMarqueeRadio(radioFuelL);
		radioFuelGal = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_preference_unit_fuel_gal);
		radioFuelGal.setText(getString(ParentActivity.getResources().getString(R.string.Volume_gallon), 470));
		ParentActivity.setMarqueeRadio(radioFuelGal);

		
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		UnitType = ParentActivity.UnitType;
		switch(UnitType){
			case Home.UNIT_TYPE_METRIC:
				UnitFuel = Home.UNIT_FUEL_L;
				UnitOdo = Home.UNIT_ODO_KM;
				UnitTemp = Home.UNIT_TEMP_C;
				UnitWeight = Home.UNIT_WEIGHT_TON;
				UnitPressure = Home.UNIT_PRESSURE_BAR;
				break;
			case Home.UNIT_TYPE_US:
				UnitFuel = Home.UNIT_FUEL_GAL;
				UnitOdo = Home.UNIT_ODO_MILE;
				UnitTemp = Home.UNIT_TEMP_F;
				UnitWeight = Home.UNIT_WEIGHT_US_TON;
				UnitPressure = Home.UNIT_PRESSURE_PSI;
				break;
			case Home.UNIT_TYPE_CUSTOM:
				UnitFuel = ParentActivity.UnitFuel;
				UnitOdo = ParentActivity.UnitOdo;
				UnitTemp = ParentActivity.UnitTemp;
				UnitWeight = ParentActivity.UnitWeight;
				UnitPressure = ParentActivity.UnitPressure;
				break;
		}
		
		TypeDisplay(UnitType);
		FuelDisplay(UnitFuel);
		TempDisplay(UnitTemp);
		SpeedDisplay(UnitOdo);
		WeightDisplay(UnitWeight);
		PressureDisplay(UnitPressure);
		CursurFirstDisplay(UnitType);
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 18;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 17;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		textviewMetric.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickType(Home.UNIT_TYPE_METRIC);
			}
		});
		textviewUS.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickType(Home.UNIT_TYPE_US);
			}
		});
		textviewCustom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickType(Home.UNIT_TYPE_CUSTOM);
			}
		});
		radioFuelL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickFuelL();
			}
		});
		radioFuelGal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickFuelGal();
			}
		});
		radioTempF.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTempF();
			}
		});
		radioTempC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickTempC();
			}
		});
		radioSpeedKM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickSpeedKM();
			}
		});
		radioSpeedMile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickSpeedMile();
			}
		});
		radioWeightTon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickWeightTon();
			}
		});
		radioWeightLB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickWeightLB();
			}
		});
		radioWeightUSTon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickWeightUSTon();
			}
		});
		radioPressureBar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressureBar();
			}
		});
		radioPressureMpa.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 14;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressureMpa();
			}
		});
		radioPressureKgf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 15;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickPressureKgf();
			}
		});
		radioPressurePsi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 16;
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

		ParentActivity.UnitType = UnitType;
		
		ParentActivity.UnitFuel = UnitFuel;
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
	public void ClickType(int _index){
		switch(_index){
		case Home.UNIT_TYPE_METRIC:
			UnitFuel = Home.UNIT_FUEL_L;
			UnitOdo = Home.UNIT_ODO_KM;
			UnitTemp = Home.UNIT_TEMP_C;
			UnitWeight = Home.UNIT_WEIGHT_TON;
			UnitPressure = Home.UNIT_PRESSURE_BAR;
			break;
		case Home.UNIT_TYPE_US:
			UnitFuel = Home.UNIT_FUEL_GAL;
			UnitOdo = Home.UNIT_ODO_MILE;
			UnitTemp = Home.UNIT_TEMP_F;
			UnitWeight = Home.UNIT_WEIGHT_US_TON;
			UnitPressure = Home.UNIT_PRESSURE_PSI;
			break;
		case Home.UNIT_TYPE_CUSTOM:
			UnitFuel = ParentActivity.UnitFuel;
			UnitOdo = ParentActivity.UnitOdo;
			UnitTemp = ParentActivity.UnitTemp;
			UnitWeight = ParentActivity.UnitWeight;
			UnitPressure = ParentActivity.UnitPressure;
			break;
		}	
		UnitType = _index;
		TypeDisplay(UnitType);
		FuelDisplay(UnitFuel);
		TempDisplay(UnitTemp);
		SpeedDisplay(UnitOdo);
		WeightDisplay(UnitWeight);
		PressureDisplay(UnitPressure);		
	}
	public void ClickFuelL(){
		UnitFuel = Home.UNIT_FUEL_L;
		FuelDisplay(UnitFuel);
	}
	public void ClickFuelGal(){
		UnitFuel = Home.UNIT_FUEL_GAL;
		FuelDisplay(UnitFuel);
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
	public void ClickWeightUSTon(){
		UnitWeight = Home.UNIT_WEIGHT_US_TON;
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
	public void TypeDisplay(int _data){
		switch (_data) {
		case Home.UNIT_TYPE_METRIC:
			textviewMetric.setPressed(true);
			textviewUS.setPressed(false);
			textviewCustom.setPressed(false);
			break;
		case Home.UNIT_TYPE_US:
			textviewMetric.setPressed(false);
			textviewUS.setPressed(true);
			textviewCustom.setPressed(false);
			break;
		case Home.UNIT_TYPE_CUSTOM:
			textviewMetric.setPressed(false);
			textviewUS.setPressed(false);
			textviewCustom.setPressed(true);
			break;
		default:
			break;
		}
		
		float layoutalpha;
		boolean clickable;
		
		if(_data == Home.UNIT_TYPE_METRIC || _data == Home.UNIT_TYPE_US){
			layoutalpha = (float)0.5;
			clickable = false;
		}else{
			layoutalpha = 1;
			clickable = true;
		}
		layoutTemp.setAlpha(layoutalpha);
		layoutFuel.setAlpha(layoutalpha);
		layoutWeight.setAlpha(layoutalpha);
		layoutSpeed.setAlpha(layoutalpha);
		layoutPressure.setAlpha(layoutalpha);
				
		radioTempC.setEnabled(clickable);
		radioTempC.setClickable(clickable);
		radioTempF.setEnabled(clickable);
		radioTempF.setClickable(clickable);
		
		radioFuelGal.setClickable(clickable);
		radioFuelGal.setEnabled(clickable);
		radioFuelL.setClickable(clickable);
		radioFuelL.setEnabled(clickable);
		
		radioWeightLB.setClickable(clickable);
		radioWeightLB.setEnabled(clickable);
		radioWeightTon.setClickable(clickable);
		radioWeightTon.setEnabled(clickable);
		radioWeightUSTon.setClickable(clickable);
		radioWeightUSTon.setEnabled(clickable);
		
		radioSpeedKM.setClickable(clickable);
		radioSpeedKM.setEnabled(clickable);
		radioSpeedMile.setClickable(clickable);
		radioSpeedMile.setEnabled(clickable);
		
		radioPressureBar.setClickable(clickable);
		radioPressureBar.setEnabled(clickable);
		radioPressureKgf.setClickable(clickable);
		radioPressureKgf.setEnabled(clickable);
		radioPressureMpa.setClickable(clickable);
		radioPressureMpa.setEnabled(clickable);
		radioPressurePsi.setClickable(clickable);		
		radioPressurePsi.setEnabled(clickable);		
	}
	public void FuelDisplay(int _data){
		switch (_data) {
		case Home.UNIT_FUEL_L:
			radioFuelL.setChecked(true);
			radioFuelGal.setChecked(false);
			break;
		case Home.UNIT_FUEL_GAL:
			radioFuelL.setChecked(false);
			radioFuelGal.setChecked(true);
			break;
		default:
			break;
		}
	}
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
			radioWeightUSTon.setChecked(false);
			break;
		case Home.UNIT_WEIGHT_LB:
			radioWeightTon.setChecked(false);
			radioWeightLB.setChecked(true);
			radioWeightUSTon.setChecked(false);
			break;
		case Home.UNIT_WEIGHT_US_TON:
			radioWeightTon.setChecked(false);
			radioWeightLB.setChecked(false);
			radioWeightUSTon.setChecked(true);
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
			CursurIndex = 18;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 18:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			CursurIndex = 16;
			CursurDisplay(CursurIndex);
			break;
		case 17:
			CursurIndex = 3;
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
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 17:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex = 17;
			CursurDisplay(CursurIndex);
			break;
		case 16:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 18:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		switch(CursurIndex){
			case 1: case 2: case 3:
			case 17: case 18:
				ClickCancel();
				break;
			default:
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
				break;
		}
		
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickType(Home.UNIT_TYPE_METRIC);
			break;
		case 2:
			ClickType(Home.UNIT_TYPE_US);
			break;
		case 3:
			ClickType(Home.UNIT_TYPE_CUSTOM);
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			ClickFuelL();
			break;
		case 5:
			ClickFuelGal();
			break;
		case 6:
			ClickTempF();
			break;
		case 7:
			ClickTempC();
			break;
		case 8:
			ClickSpeedKM();
			break;
		case 9:
			ClickSpeedMile();
			break;
		case 10:
			ClickWeightTon();
			break;
		case 11:
			ClickWeightLB();
			break;
		case 12:
			ClickWeightUSTon();
			break;
		case 13:
			ClickPressureBar();
			break;
		case 14:
			ClickPressureMpa();
			break;
		case 15:
			ClickPressureKgf();
			break;
		case 16:
			ClickPressurePsi();
			break;
		case 17:
			ClickCancel();
			break;
		case 18:
			ClickOK();
			break;

		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case Home.UNIT_TYPE_METRIC:
			CursurIndex = 1;
			ClickType(Home.UNIT_TYPE_METRIC);
			CursurDisplay(CursurIndex);
			break;
		case Home.UNIT_TYPE_US:
			CursurIndex = 2;
			ClickType(Home.UNIT_TYPE_US);
			CursurDisplay(CursurIndex);
			break;
		case Home.UNIT_TYPE_CUSTOM:
		default:
			CursurIndex = 3;
			ClickType(Home.UNIT_TYPE_CUSTOM);
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		textviewMetric.setPressed(false);
		textviewUS.setPressed(false);
		textviewCustom.setPressed(false);
		imageviewMetric.setVisibility(View.INVISIBLE);
		imageviewUS.setVisibility(View.INVISIBLE);
		imageviewCustom.setVisibility(View.INVISIBLE);
		radioFuelL.setPressed(false);
		radioFuelGal.setPressed(false);
		radioTempC.setPressed(false);
		radioTempF.setPressed(false);
		radioSpeedKM.setPressed(false);
		radioSpeedMile.setPressed(false);
		radioWeightTon.setPressed(false);
		radioWeightLB.setPressed(false);
		radioWeightUSTon.setPressed(false);
		radioPressureBar.setPressed(false);
		radioPressureMpa.setPressed(false);
		radioPressureKgf.setPressed(false);
		radioPressurePsi.setPressed(false);
		
		switch(UnitType){
		case Home.UNIT_TYPE_METRIC:
			textviewMetric.setPressed(true);
			break;
		case Home.UNIT_TYPE_US:
			textviewUS.setPressed(true);
			break;
		case Home.UNIT_TYPE_CUSTOM:
			textviewCustom.setPressed(true);
			break;
		}
		
		switch (Index) {
		case 1:
			imageviewMetric.setVisibility(View.VISIBLE);
			imageviewMetric.setPressed(true);
			break;
		case 2:
			imageviewUS.setVisibility(View.VISIBLE);
			imageviewUS.setPressed(true);
			break;
		case 3:
			imageviewCustom.setVisibility(View.VISIBLE);
			imageviewCustom.setPressed(true);
			break;
		case 4:
			radioFuelL.setPressed(true);
			break;
		case 5:
			radioFuelGal.setPressed(true);
			break;
		case 6:
			radioTempF.setPressed(true);
			break;
		case 7:
			radioTempC.setPressed(true);
			break;
		case 8:
			radioSpeedKM.setPressed(true);
			break;
		case 9:
			radioSpeedMile.setPressed(true);
			break;
		case 10:
			radioWeightTon.setPressed(true);
			break;
		case 11:
			radioWeightLB.setPressed(true);
			break;
		case 12:
			radioWeightUSTon.setPressed(true);
			break;
		case 13:
			radioPressureBar.setPressed(true);
			break;
		case 14:
			radioPressureMpa.setPressed(true);
			break;
		case 15:
			radioPressureKgf.setPressed(true);
			break;
		case 16:
			radioPressurePsi.setPressed(true);
			break;
		case 17:
			imgbtnCancel.setPressed(true);
			break;
		case 18:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
	public String getString(String str, int index){
		if(ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex) == null){
			return str;
		}else {
			return ParentActivity.langDb.findStrGetString(index, ParentActivity.LanguageIndex);	
		}
	}
}