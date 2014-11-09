package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
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
import android.widget.TextView;

public class SpeedometerFreqFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;

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
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

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
		 TAG = "SpeedometerFreqFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_speedometerfreq, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_FREQ_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Speedometer_Freq_Setting));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_speedometerfreq_low_default);
		
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_0);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_mode_speedometerfreq_num_back);
		textViewNext = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_speedometerfreq_num_next);
		
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
		imgbtnDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDefault();
			}
		});	
		textViewNum1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum1();
			}
		});	
		textViewNum2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum2();
			}
		});	
		textViewNum3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum3();
			}
		});	
		textViewNum4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum4();
			}
		});	
		textViewNum5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum5();
			}
		});	
		textViewNum6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum6();
			}
		});	
		textViewNum7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum7();
			}
		});	
		textViewNum8.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum8();
			}
		});	
		textViewNum9.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum9();
			}
		});	
		textViewNum0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNum0();
			}
		});	
		imgbtnBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNumBack();
			}
		});	
		textViewNext.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickNumNext();
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
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);

	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_ETC_TOP);
	}
	public void ClickDefault(){
		
	}
	public void ClickNum1(){
		
	}
	public void ClickNum2(){
		
	}
	public void ClickNum3(){
		
	}
	public void ClickNum4(){
		
	}
	public void ClickNum5(){
		
	}
	public void ClickNum6(){
		
	}
	public void ClickNum7(){
		
	}
	public void ClickNum8(){
		
	}
	public void ClickNum9(){
		
	}
	public void ClickNum0(){
		
	}
	public void ClickNumBack(){
		
	}
	public void ClickNumNext(){
		
	}
	
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	
}
