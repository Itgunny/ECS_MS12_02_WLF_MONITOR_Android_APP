package taeha.wheelloader.fseries_monitor.main.b;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.animation.TextViewXAxisFlipAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class MainBRightUpEngineFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
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
	int EngineMode;
	int EngineWarmingUp;
	boolean ClickFlag;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////
	TextViewXAxisFlipAnimation EngineModeDataAnimation;
	TextViewXAxisFlipAnimation EngineWarmingUpDataAnimation;
	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBRightUpEngineFragment";
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
		
		EngineModeDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		EngineWarmingUpDataAnimation = new TextViewXAxisFlipAnimation(ParentActivity);
		
	
				
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
		EngineMode = CAN1Comm.Get_EnginePowerMode_347_PGN65350();
		EngineWarmingUp = CAN1Comm.Get_EngineAlternateLowIdelSwitch_348_PGN65350();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		EngineModeDisplay(EngineMode);
		EngineWarmingUpDisplay(EngineWarmingUp);
	}
	/////////////////////////////////////////////////////////////////////	
	
	public void EngineModeDisplay(int _EngineMode){
		try {
			switch (_EngineMode) {
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_PWR:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.POWER));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_STD:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.STANDARD));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_MODE_ECONO:
				EngineModeDataAnimation.FlipAnimation(textViewModeData,getResources().getString(string.ECONO));
				break;
			default:
				break;
			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
		
	}
	public void EngineWarmingUpDisplay(int _EngineWarmingUp){
		try {
			switch (_EngineWarmingUp) {
			case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_OFF:
				EngineWarmingUpDataAnimation.FlipAnimation(textViewWarmingUpData,getResources().getString(string.OFF));
				break;
			case CAN1CommManager.DATA_STATE_ENGINE_WARMINGUP_ON:
				EngineWarmingUpDataAnimation.FlipAnimation(textViewWarmingUpData,getResources().getString(string.ON));
				break;
			default:
				break;

			}
		} catch (IllegalStateException e) {
			// TODO: handle exception
			Log.e(TAG,"IllegalStateException");
		}
		
	}
	
	public void ClickMode(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterEngineFragment = new MainBCenterEngineFragment();
		ParentActivity._MainBBaseFragment._MainBRightUpEngineModeFragment = new MainBRightUpEngineModeFragment();
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpEngineModeFragment);
		
		ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();
		

		
	}
	public void ClickWarmingUp(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MainBBaseFragment._MainBCenterEngineFragment = new MainBCenterEngineFragment();
		ParentActivity._MainBBaseFragment._MainBRightUpEngineWarmingUpFragment = new MainBRightUpEngineWarmingUpFragment();
		ParentActivity._MainBBaseFragment._MainBCenterFragment.setClickEnable(false);
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftRightUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterEngineFragment);
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBRightUpEngineWarmingUpFragment);
		
		ParentActivity._MainBBaseFragment._CenterBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._RightDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftUpBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._LeftDownBGDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._VirtualKeyDisappearAnimation.StartAnimation();
		
		ParentActivity._MainBBaseFragment._KeyTitleDisappearAnimation.StartAnimation();
		ParentActivity._MainBBaseFragment._KeyBodyDisappearAnimation.StartAnimation();

	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMode.setClickable(ClickFlag);
		imgbtnWarmingUp.setClickable(ClickFlag);
	}
}