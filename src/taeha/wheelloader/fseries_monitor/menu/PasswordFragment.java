package taeha.wheelloader.fseries_monitor.menu;

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

public abstract class PasswordFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
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
	
	TextView textViewPassword;
	TextView textViewTitle;
	
	ImageButton imgbtnBack;
	ImageButton imgbtnEnter;
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
		 TAG = "PasswordFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_password, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_num_0);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_text_title);
		textViewPassword = (TextView)mRoot.findViewById(R.id.textView_menu_body_password_text_data);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_password_num_back);
		imgbtnEnter = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_password_num_enter);


	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
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
				ClickBack();
			}
		});
		imgbtnEnter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickEnter();
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

	public void ClickBack(){
		
	}

	public void ClickEnter(){
		
	}


	/////////////////////////////////////////////////////////////////////
	public void setTitleText(String str){
		textViewTitle.setText(str);
	}
	/////////////////////////////////////////////////////////////////////
	abstract public void showServicePasswordNextScreen();
	abstract public void showUserPasswordNextScreen();
	/////////////////////////////////////////////////////////////////////
	
}
