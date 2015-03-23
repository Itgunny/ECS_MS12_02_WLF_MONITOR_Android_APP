package taeha.wheelloader.fseries_monitor.main.a;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainACenterEngineFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutTop;
	
	ImageView EngineIcon;
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
		TAG = "MainACenterEngineFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.center_main_a_engine, null);
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
		LayoutTop = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_a_engine);
		
		EngineIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_engine_icon);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		LayoutTop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBG();
				
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
		ShowEngineIcon();
	}
	/////////////////////////////////////////////////////////////////////	
	public void ShowEngineIcon(){
		try {
			if(ParentActivity.ScreenIndex == ParentActivity.SCREEN_STATE_MAIN_A_RIGHTUP_HOURODMETER)
				EngineIcon.setVisibility(View.INVISIBLE);
			else
				EngineIcon.setVisibility(View.VISIBLE);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		
	}
	
	public void ClickBG(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		ParentActivity._MainABaseFragment.showRightUptoDefaultScreenAnimation();
	}
}