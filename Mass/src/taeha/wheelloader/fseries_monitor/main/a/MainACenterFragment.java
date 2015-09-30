package taeha.wheelloader.fseries_monitor.main.a;


import taeha.wheelloader.fseries_monitor.animation.GaugerpmAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainACenterFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout Layoutcenter;
	TextView textViewRPMData;
	ImageButton imgbtnOption;
	ImageView imgVeiwrpmGuage;
	ImageView imgViewSmkIcon;		// ++, --, 150326 bwk
	ImageView imgViewOptionSelect;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	protected int RPM;
	
	int Maint;
	int FaultCode;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	GaugerpmAnimation rpmAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainACenterFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_a, null);
		InitResource();
		InitValuables();
		InitAnimation();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG,"onResume");
		ClickFlag = true;
		setClickEnable(ClickFlag);
//		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
		ParentActivity.CheckAttachmentUnlock();
		
		if(ParentActivity.AxleWarningFlag != true)
		{
			Log.d(TAG, "AxleInit ");
			ParentActivity.FrontAxleWarningFlag = false;
			ParentActivity.RearAxleWarningFlag = false;
		}	
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause");
	}
	////////////////////////////////////////////////

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		Layoutcenter = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_a_bg);
		imgVeiwrpmGuage = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_gauge);
		
		textViewRPMData = (TextView)mRoot.findViewById(R.id.textView_center_main_a_rpm);
		
		imgbtnOption = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_option);
		
		imgViewSmkIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_smkicon);	// ++, --, 150326 bwk
		imgViewOptionSelect = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_option_select);
		
		Layoutcenter.setClickable(false);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();

		CursurDisplayDetail(ParentActivity._MainABaseFragment.CursurIndex);
	}
	public void InitAnimation(){
		rpmAnimation = new GaugerpmAnimation(ParentActivity, imgVeiwrpmGuage, RPM);
	}
	boolean Temp = false;
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		Layoutcenter.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		
		imgbtnOption.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickBG();
				
			
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
		FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RPMDisplay(RPM);
		IconDisplay(FaultCode,Maint);
		SmkIconDisplay(ParentActivity.SmartIconDisplay);	// ++, --, 150326 bwk
	}
	/////////////////////////////////////////////////////////////////////
	public void RPMDisplay(int Data){
		if(Data == 0xFFFF)
			Data = 0;
		else if(Data > 9999)
			Data = 9999;
		try {
			textViewRPMData.setText(Integer.toString(Data));
			rpmAnimation.Setrpm(Data);
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
	}
	
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainABaseFragment.showQuickScreenAnimation();
		Log.d(TAG,"ClickOption");
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnOption.setClickable(ClickFlag);
	}
	//////////////////////////////////////////////////////////////////////
	public void IconDisplay(int _fault, int _maint){
		if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON && _maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_faultmaint_btn);
		}else if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_fault_btn);
		}else if(_maint == CAN1CommManager.DATA_STATE_LAMP_ON){
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_maint_btn);
		}else{
			imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_quick_btn);
		}
	}
	//////////////////////////////////////////////////////////////////////
	// ++, 150326 bwk
	public void SmkIconDisplay(boolean flag){
		try{
		if(flag == false)
			imgViewSmkIcon.setVisibility(View.INVISIBLE);
		else
			imgViewSmkIcon.setVisibility(View.VISIBLE);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
	}
	// --, 150326 bwk
	public void CursurDisplayDetail(int index){
		if(index == 4)
			imgViewOptionSelect.setVisibility(View.VISIBLE);
		else
			imgViewOptionSelect.setVisibility(View.GONE);
	}
}