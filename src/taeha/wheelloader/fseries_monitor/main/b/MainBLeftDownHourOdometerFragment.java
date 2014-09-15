package taeha.wheelloader.fseries_monitor.main.b;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBLeftDownHourOdometerFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBLeftDownHourOdometerFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewHourOdoTitle;
	TextView textViewHourOdoData;
	TextView textViewHourOdoUnit;
	
	ImageButton imgbtnHourOdo;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ClickFlag;
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
		mRoot = inflater.inflate(R.layout.leftdown_main_b_hourodometer, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
		setClickEnable(ClickFlag);
	}
	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewHourOdoTitle = (TextView)mRoot.findViewById(R.id.textView_leftdown_hourodometer_title);
		textViewHourOdoData = (TextView)mRoot.findViewById(R.id.textView_leftdown_hourodometer_data);
		textViewHourOdoUnit = (TextView)mRoot.findViewById(R.id.textView_leftdown_hourodometer_unit);
		
		
		imgbtnHourOdo = (ImageButton)mRoot.findViewById(R.id.imageButton_leftdown_hourodometer);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnHourOdo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickHourOdo();
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
	
	public void ClickHourOdo(){
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftDownAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterHourOdometerFragment);
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftDownHourOdometerSelectFragment);
		
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnHourOdo.setClickable(ClickFlag);
	}
}