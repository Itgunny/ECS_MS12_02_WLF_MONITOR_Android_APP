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

public class MainBCenterFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewRPMData;
	
	ImageView imgViewEcoIcon;
	ImageView imgViewEcoBG;
	ImageView imgViewEcoBar;
	
	ImageView imgViewRPMUnit;
	
	ImageButton imgbtnOption;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
	protected int RPM;
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
		TAG = "MainBCenterFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_b, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_TOP;
		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
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
		textViewRPMData = (TextView)mRoot.findViewById(R.id.textView_center_main_b_rpm);
		
		imgViewEcoIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_eco_title);
		imgViewEcoBG = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_eco_bg);
		imgViewEcoBar = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_eco_bar);
		
		imgViewRPMUnit = (ImageView)mRoot.findViewById(R.id.imageView_center_main_b_rpm_unit);
		
		imgbtnOption = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_b_option);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
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
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		RPMDisplay(RPM);
	}
	/////////////////////////////////////////////////////////////////////
	public void RPMDisplay(int Data){
		if(Data == 0xFFFF)
			Data = 0;
		else if(Data > 9999)
			Data = 9999;
		try {
			textViewRPMData.setText(Integer.toString(Data));
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
		ParentActivity._MainBBaseFragment.showQuickScreenAnimation();
		Log.d(TAG,"ClickOption");
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnOption.setClickable(ClickFlag);
	}
}