package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBUpperMenuBarFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBUpperMenuBarFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewTimeAM;
	TextView textViewTimePM;
	TextView textViewTimeHour;
	TextView textViewColon;
	TextView textViewMin;
	
	ImageView imgViewBuzzerNormal;
	ImageView imgViewBuzzer070Left;
	ImageView imgViewBuzzer070Right;
	
	ImageView imgViewWiperIcon;
	ImageView imgViewWiperLevel1;
	ImageView imgViewWiperLevel2;
	ImageView imgViewWiperLevel3;
	ImageView imgViewWiperLevel4;
	
	ImageView imgViewCamera;
	
	ImageView imgViewMenu;
	
	ImageButton imgbtnTime;
	ImageButton imgbtnBuzzer;
	ImageButton imgbtnWiper;
	ImageButton imgbtnCamera;
	ImageButton imgbtnMenu;

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
		mRoot = inflater.inflate(R.layout.upper_main_b_menubar, null);
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
		
		textViewTimeAM = (TextView)mRoot.findViewById(R.id.textView_upper_main_b_menubar_time_am);
		textViewTimePM = (TextView)mRoot.findViewById(R.id.textView_upper_main_b_menubar_time_pm);
		textViewTimeHour = (TextView)mRoot.findViewById(R.id.textView_upper_main_b_menubar_time_hour);
		textViewColon = (TextView)mRoot.findViewById(R.id.textView_upper_main_b_menubar_time_colon);
		textViewMin = (TextView)mRoot.findViewById(R.id.textView_upper_main_b_menubar_time_min);
		
		
		imgViewBuzzerNormal = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_buzzer_normal);
		imgViewBuzzer070Left = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_buzzer_070_left);
		imgViewBuzzer070Right = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_buzzer_070_right);
		
		imgViewWiperIcon = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_wiper_icon);
		imgViewWiperLevel1 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_wiper_level_1);
		imgViewWiperLevel2 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_wiper_level_2);
		imgViewWiperLevel3 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_wiper_level_3);
		imgViewWiperLevel4 = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_wiper_level_4);
		
		imgViewCamera = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_camera);
		
		imgViewMenu = (ImageView)mRoot.findViewById(R.id.imageView_upper_main_b_menubar_menu);
		
		imgbtnTime = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_b_menubar_time);
		imgbtnBuzzer = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_b_menubar_buzzer);
		imgbtnWiper = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_b_menubar_wiper);
		imgbtnCamera = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_b_menubar_camera);
		imgbtnMenu = (ImageButton)mRoot.findViewById(R.id.imageButton_upper_main_b_menubar_menu);
		
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTime();
			}
		});
		imgbtnBuzzer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBuzzer();
			}
		});
		imgbtnWiper.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickWiper();
			}
		});
		imgbtnCamera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCamera();
			}
		});
		imgbtnMenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMenu();
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
	
	public void ClickTime(){
		
	}
	public void ClickBuzzer(){
		
	}
	public void ClickWiper(){
		
	}
	public void ClickCamera(){
		
	}
	public void ClickMenu(){
		
	}
}	