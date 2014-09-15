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

public class MainBRightUpEngineFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBRightUpEngineFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewModeTitle;
	TextView textViewModeData;
	TextView textViewWarmingUpTitle;
	TextView textViewWarmingUpData;
	
	ImageButton imgbtnMode;
	ImageButton imgbtnWarmingUp;
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
		mRoot = inflater.inflate(R.layout.rightup_main_b_engine, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ClickFlag = true;
	}

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewModeTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_mode_title);
		textViewModeData = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_mode_data);
		textViewWarmingUpTitle = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_warmingup_title);
		textViewWarmingUpData = (TextView)mRoot.findViewById(R.id.textView_rightup_main_b_engine_warmingup_data);
		
		imgbtnMode = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_b_engine_mode);
		imgbtnWarmingUp = (ImageButton)mRoot.findViewById(R.id.imageButton_rightup_main_b_engine_warmingup);

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnMode.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickMode();
			}
		});
		imgbtnWarmingUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ClickFlag == true)
					ClickWarmingUp();
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
	
	public void ClickMode(){
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpEngineModeFragment);
		
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
		
	}
	public void ClickWarmingUp(){
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpEngineWarmingUpFragment);
		
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMode.setClickable(ClickFlag);
		imgbtnWarmingUp.setClickable(ClickFlag);
	}
}