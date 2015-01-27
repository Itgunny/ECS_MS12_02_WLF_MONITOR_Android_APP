package taeha.wheelloader.fseries_monitor.menu.preference;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
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

public class ClockFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView textViewNum1;
	TextView textViewNum2;
	TextView textViewNum3;
	TextView textViewNum4;
	TextView textViewNum5;
	TextView textViewNum6;
	TextView textViewNum7;
	TextView textViewNum8;
	TextView textViewNum9;
	TextView textViewNum0;
	
	ImageButton imgbtnBack;
	TextView textViewNext;
	
	RadioButton radioHour10;
	RadioButton radioHour1;
	RadioButton radioMin10;
	RadioButton radioMin1;
	TextView textViewAMPM;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int Hour;
	int Min;
	
	int ClockIndex;
	
	int Hour10;
	int Hour1;
	int Min10;
	int Min1;
	
	boolean AMFlag;
	
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
		 TAG = "ClockFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_preference_clock, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		DisableHour10Button();
		
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_CLOCK_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Clock_Setting));
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
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_clock_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_preference_clock_low_cancel);
		
		
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_0);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_preference_clock_num_back);
		textViewNext = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_num_next);
		
		textViewAMPM = (TextView)mRoot.findViewById(R.id.textView_menu_body_preference_clock_ampm);
		
		radioHour10 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_preference_clock_hour_10);
		radioHour1 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_preference_clock_hour_1);
		radioMin10 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_preference_clock_min_10);
		radioMin1 = (RadioButton)mRoot.findViewById(R.id.radio_menu_body_preference_clock_min_1);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		ClockIndex = 0;
		Hour = CAN1Comm.Get_RTColock_Hour();
		Min = CAN1Comm.Get_RTColock_Min();

		ClockDisplay(Hour,Min);
		CheckButton();
		CursurIndex = 1;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 15;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 14;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickCancel();
			}
		});
		textViewNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum1();
			}
		});	
		textViewNum2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum2();
			}
		});	
		textViewNum3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum3();
			}
		});	
		textViewNum4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum4();
			}
		});	
		textViewNum5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum5();
			}
		});	
		textViewNum6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum6();
			}
		});	
		textViewNum7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum7();
			}
		});	
		textViewNum8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum8();
			}
		});	
		textViewNum9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum9();
			}
		});	
		textViewNum0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 12;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNum0();
			}
		});	
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNumBack();
			}
		});	
		textViewNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 13;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickNumNext();
			}
		});	
		textViewAMPM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickAMPM();
			}
		});	
		radioHour10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHour10();
			}
		});	
		radioHour1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHour1();
			}
		});	
		radioMin10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMin10();
			}
		});
		radioMin1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMin1();
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
		
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.OldScreenIndex = 0;
		}else{
			ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
		}
	

		CAN1Comm.TxCMDToMCU(CAN1Comm.CMD_RTC,ParentActivity.Year,ParentActivity.Month,ParentActivity.Date,0x01,Hour,Min,0x00,0x00);
		
	
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_TOP){
			ParentActivity._MainChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment);
			ParentActivity.OldScreenIndex = 0;
		}else{
			ParentActivity._MenuBaseFragment.showBodyPreferenceAnimation();
			ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_PREFERENCE_TOP);
		}
	}
	public void ClickNum1(){
		setNumber(1);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum2(){
		setNumber(2);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum3(){
		setNumber(3);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum4(){
		setNumber(4);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum5(){
		setNumber(5);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum6(){
		setNumber(6);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum7(){
		setNumber(7);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum8(){
		setNumber(8);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum9(){
		setNumber(9);
	
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNum0(){
		setNumber(0);
		
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNumBack(){
		if(ClockIndex <= 0)
			ClockIndex = 3;
		else 
			ClockIndex--;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickNumNext(){
		if(ClockIndex >= 3)
			ClockIndex = 0;
		else 
			ClockIndex++;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickAMPM(){
		if(AMFlag == true){
			Hour += 12;
			ClockDisplay(Hour,Min);
		}else{
			Hour -= 12;
			ClockDisplay(Hour,Min);
		}
	}
	public void ClickHour10(){
		ClockIndex = 0;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickHour1(){
		ClockIndex = 1;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickMin10(){
		ClockIndex = 2;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	public void ClickMin1(){
		ClockIndex = 3;
		CheckButton();
		ClockDisplay(Hour,Min);
	}
	
	/////////////////////////////////////////////////////////////////////
	public void setNumber(int _data){
		switch (ClockIndex) {
		case 0:
			Hour10 = _data;
			break;
		case 1:
			Hour1 = _data;
			break;
		case 2:
			Min10 = _data;
			break;
		case 3:
			Min1 = _data;
			break;
		default:
			break;
		}
		
		Hour = Hour10 * 10 + Hour1;
		Min = Min10 * 10 + Min1;
		
		if(Hour == 12)
			Hour = 0;
		
		if(AMFlag == false)
			Hour += 12;
		
		Log.d(TAG,"Hour : " + Integer.toString(Hour) + " Min : " + Integer.toString(Min));
	}
	public void EnalbeAllButton(){
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(true);
		textViewNum3.setClickable(true);
		textViewNum4.setClickable(true);
		textViewNum5.setClickable(true);
		textViewNum6.setClickable(true);
		textViewNum7.setClickable(true);
		textViewNum8.setClickable(true);
		textViewNum9.setClickable(true);
		textViewNum0.setClickable(true);
		
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)1.0);
		textViewNum3.setAlpha((float)1.0);
		textViewNum4.setAlpha((float)1.0);
		textViewNum5.setAlpha((float)1.0);
		textViewNum6.setAlpha((float)1.0);
		textViewNum7.setAlpha((float)1.0);
		textViewNum8.setAlpha((float)1.0);
		textViewNum9.setAlpha((float)1.0);
		textViewNum0.setAlpha((float)1.0);
	}
	public void DisableHour10Button(){
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(false);
		textViewNum3.setClickable(false);
		textViewNum4.setClickable(false);
		textViewNum5.setClickable(false);
		textViewNum6.setClickable(false);
		textViewNum7.setClickable(false);
		textViewNum8.setClickable(false);
		textViewNum9.setClickable(false);
		textViewNum0.setClickable(true);
		
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)0.5);
		textViewNum3.setAlpha((float)0.5);
		textViewNum4.setAlpha((float)0.5);
		textViewNum5.setAlpha((float)0.5);
		textViewNum6.setAlpha((float)0.5);
		textViewNum7.setAlpha((float)0.5);
		textViewNum8.setAlpha((float)0.5);
		textViewNum9.setAlpha((float)0.5);
		textViewNum0.setAlpha((float)1.0);
	}
	public void DisableHour1Button(){
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(true);
		textViewNum3.setClickable(false);
		textViewNum4.setClickable(false);
		textViewNum5.setClickable(false);
		textViewNum6.setClickable(false);
		textViewNum7.setClickable(false);
		textViewNum8.setClickable(false);
		textViewNum9.setClickable(false);
		textViewNum0.setClickable(true);
		
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)1.0);
		textViewNum3.setAlpha((float)0.5);
		textViewNum4.setAlpha((float)0.5);
		textViewNum5.setAlpha((float)0.5);
		textViewNum6.setAlpha((float)0.5);
		textViewNum7.setAlpha((float)0.5);
		textViewNum8.setAlpha((float)0.5);
		textViewNum9.setAlpha((float)0.5);
		textViewNum0.setAlpha((float)1.0);
	}
	public void DisableHour1Button2(){
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(true);
		textViewNum3.setClickable(true);
		textViewNum4.setClickable(true);
		textViewNum5.setClickable(true);
		textViewNum6.setClickable(true);
		textViewNum7.setClickable(true);
		textViewNum8.setClickable(true);
		textViewNum9.setClickable(true);
		textViewNum0.setClickable(false);
		
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)1.0);
		textViewNum3.setAlpha((float)1.0);
		textViewNum4.setAlpha((float)1.0);
		textViewNum5.setAlpha((float)1.0);
		textViewNum6.setAlpha((float)1.0);
		textViewNum7.setAlpha((float)1.0);
		textViewNum8.setAlpha((float)1.0);
		textViewNum9.setAlpha((float)1.0);
		textViewNum0.setAlpha((float)0.5);
	}
	public void DisableMin10Button(){
		textViewNum1.setClickable(true);
		textViewNum2.setClickable(true);
		textViewNum3.setClickable(true);
		textViewNum4.setClickable(true);
		textViewNum5.setClickable(true);
		textViewNum6.setClickable(false);
		textViewNum7.setClickable(false);
		textViewNum8.setClickable(false);
		textViewNum9.setClickable(false);
		textViewNum0.setClickable(true);
		
		textViewNum1.setAlpha((float)1.0);
		textViewNum2.setAlpha((float)1.0);
		textViewNum3.setAlpha((float)1.0);
		textViewNum4.setAlpha((float)1.0);
		textViewNum5.setAlpha((float)1.0);
		textViewNum6.setAlpha((float)0.5);
		textViewNum7.setAlpha((float)0.5);
		textViewNum8.setAlpha((float)0.5);
		textViewNum9.setAlpha((float)0.5);
		textViewNum0.setAlpha((float)1.0);
	}
	public void CheckButton(){
		if(ClockIndex == 0){
			DisableHour10Button();
		}else if(ClockIndex == 1){
			if(Hour10 == 1){
				DisableHour1Button();	
				if(Hour1 == 0 || Hour1 == 1 || Hour1 == 2){
					
				}else{
					setNumber(0);
					ClockDisplay(Hour,Min);
				}
			}else if(Hour10 == 0){
				DisableHour1Button2();
			}
		}else if(ClockIndex == 2){
			DisableMin10Button();
		}else if(ClockIndex == 3){
			EnalbeAllButton();
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClockDisplay(int Hour, int Min){
		int TempHour;
		if(ParentActivity.GetAMPM(Hour) == ParentActivity.CLOCK_AM){
			textViewAMPM.setText(ParentActivity.getResources().getString(string.AM));
			if(Hour == 0){
				Hour10 = 1;
				Hour1 = 2;
				Min10 = Min / 10;
				Min1 = Min % 10;
			}else{
				Hour10 = Hour / 10;
				Hour1 = Hour % 10;
				Min10 = Min / 10;
				Min1 = Min % 10;
			}
			AMFlag = true;
			
		}else{
			textViewAMPM.setText(ParentActivity.getResources().getString(string.PM));
			AMFlag = false;
			if(Hour == 12){
				TempHour = Hour;
				Hour10 = TempHour / 10;
				Hour1 = TempHour % 10;
				Min10 = Min / 10;
				Min1 = Min % 10;
			}else{
				TempHour = Hour - 12;
				Hour10 = TempHour / 10;
				Hour1 = TempHour % 10;
				Min10 = Min / 10;
				Min1 = Min % 10;
			}
			
		}
//		radioHour10.setText(ParentActivity.GetHour2(Hour10));
//		radioHour1.setText(ParentActivity.GetHour2(Hour1));
//		radioMin10.setText(ParentActivity.GetMin2(Min10));
//		radioMin1.setText(ParentActivity.GetMin2(Min1));
		radioHour10.setText(Integer.toString(Hour10));
		radioHour1.setText(Integer.toString(Hour1));
		radioMin10.setText(Integer.toString(Min10));
		radioMin1.setText(Integer.toString(Min1));
		switch (ClockIndex) {
		case 0:
			radioHour10.setChecked(true);
			radioHour1.setChecked(false);
			radioMin10.setChecked(false);
			radioMin1.setChecked(false);
			break;
		case 1:
			radioHour10.setChecked(false);
			radioHour1.setChecked(true);
			radioMin10.setChecked(false);
			radioMin1.setChecked(false);
			break;
		case 2:
			radioHour10.setChecked(false);
			radioHour1.setChecked(false);
			radioMin10.setChecked(true);
			radioMin1.setChecked(false);
			break;
		case 3:
			radioHour10.setChecked(false);
			radioHour1.setChecked(false);
			radioMin10.setChecked(false);
			radioMin1.setChecked(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 15;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 5:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 6:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 7:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 8:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 9:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 10:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 11:
			if(ClockIndex == 0){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 6;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 12:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 13:
			if(ClockIndex == 1 && Hour10 == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 14:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 15:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 3:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 4:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 5:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 6:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 7:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 8:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 9:
			if(ClockIndex == 0){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 1 && Hour10 == 1){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}else if(ClockIndex == 2){
				CursurIndex = 11;
				CursurDisplay(CursurIndex);
			}
			else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 10:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			if(ClockIndex == 1 && Hour10 == 0){
				CursurIndex = 13;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 12:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 13:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 14:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 15:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickAMPM();
			break;
		case 2:
			ClickNum1();
			break;

		case 3:
			ClickNum2();
			break;

		case 4:
			ClickNum3();
			break;

		case 5:
			ClickNum4();
			break;

		case 6:
			ClickNum5();
			break;

		case 7:
			ClickNum6();
			break;

		case 8:
			ClickNum7();
			break;

		case 9:
			ClickNum8();
			break;

		case 10:
			ClickNum9();
			break;

		case 11:
			ClickNumBack();
			break;

		case 12:
			ClickNum0();
			break;

		case 13:
			ClickNumNext();
			break;

		case 14:
			ClickCancel();
			break;

		case 15:
			ClickOK();
			break;
			
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		textViewAMPM.setPressed(false);

		textViewNum1.setPressed(false);
		textViewNum2.setPressed(false);
		textViewNum3.setPressed(false);
		textViewNum4.setPressed(false);
		textViewNum5.setPressed(false);
		textViewNum6.setPressed(false);
		textViewNum7.setPressed(false);
		textViewNum8.setPressed(false);
		textViewNum9.setPressed(false);
		textViewNum0.setPressed(false);
		imgbtnBack.setPressed(false);
		textViewNext.setPressed(false);

		switch (Index) {
		case 1:
			textViewAMPM.setPressed(true);
			break;
		case 2:
			textViewNum1.setPressed(true);
			break;
		case 3:
			textViewNum2.setPressed(true);
			break;
		case 4:
			textViewNum3.setPressed(true);
			break;
		case 5:
			textViewNum4.setPressed(true);
			break;
		case 6:
			textViewNum5.setPressed(true);
			break;
		case 7:
			textViewNum6.setPressed(true);
			break;
		case 8:
			textViewNum7.setPressed(true);
			break;
		case 9:
			textViewNum8.setPressed(true);
			break;
		case 10:
			textViewNum9.setPressed(true);
			break;
		case 11:
			imgbtnBack.setPressed(true);
			break;
		case 12:
			textViewNum0.setPressed(true);
			break;
		case 13:
			textViewNext.setPressed(true);
			break;
		case 14:
			imgbtnCancel.setPressed(true);
			break;
		case 15:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////
}
