package taeha.wheelloader.fseries_monitor.main.b;

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

public class MainBLeftUpMachineStatusFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "MainBLeftUpMachineStatusFragment";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutNormalUpper;
	RelativeLayout LayoutNormalLower;
	RelativeLayout LayoutWeighingUpper;
	RelativeLayout LayoutWeighingLower;
	
	TextView textViewNormalUpperData;
	TextView textViewNormalUpperUnit;
	TextView textViewNormalLowerData;
	TextView textViewNormalLowerUnit;
	TextView textViewWeighingUpperData;
	TextView textViewWeighingUpperUnit;
	TextView textViewWeighingLowerData;
	TextView textViewWeighingLowerUnit;
	
	ImageView imgViewNormalUpperIcon;
	ImageView imgViewNormalLowerIcon;
	ImageView imgViewWeighingUpperIcon;
	ImageView imgViewWeighingLowerIcon;
	
	ImageButton imgbtnMachineStatus;
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
		mRoot = inflater.inflate(R.layout.leftup_main_b_machinestatus, null);
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
		
		LayoutNormalUpper = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_normal_upper);
		LayoutNormalLower = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_normal_lower);
		LayoutWeighingUpper = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_weighing_upper);
		LayoutWeighingLower = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftup_main_b_machinestatus_weighing_lower);
		
		textViewNormalUpperData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_data);
		textViewNormalUpperUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_upper_unit);
		textViewNormalLowerData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_data);
		textViewNormalLowerUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_normal_lower_unit);
		textViewWeighingUpperData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_data);
		textViewWeighingUpperUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_upper_unit);
		textViewWeighingLowerData = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_data);
		textViewWeighingLowerUnit = (TextView)mRoot.findViewById(R.id.textView_leftup_machinestatus_weighing_lower_unit);
		
		imgViewNormalUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_upper_icon);
		imgViewNormalLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_normal_lower_icon);
		imgViewWeighingUpperIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_upper_icon);
		imgViewWeighingLowerIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftup_machinestatus_weighing_lower_icon);
		
		imgbtnMachineStatus = (ImageButton)mRoot.findViewById(R.id.imageButton_leftup_main_b_machinestatus);

	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnMachineStatus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  	if(ClickFlag == true)
			  		ClickMachineStatus();
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
	
	public void ClickMachineStatus(){
		ParentActivity._MainBBaseFragment._MainBodyShiftAnimation.StartShiftLeftUpAnimation();
		ParentActivity._MainBBaseFragment.CenterAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBCenterMachineStatusFragment);
		ParentActivity._MainBBaseFragment.LeftUpChangeAnimation.StartChangeAnimation(ParentActivity._MainBBaseFragment._MainBLeftUpMachineStatusSelectFragment);
		
		ParentActivity._MainBBaseFragment.RightUpChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.RightDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.LeftDownChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.VirtualKeyChangeAnimation.StartDisappearAnimation();
		
		ParentActivity._MainBBaseFragment.KeyTitleChangeAnimation.StartDisappearAnimation();
		ParentActivity._MainBBaseFragment.KeyBodyChangeAnimation.StartDisappearAnimation();
	}
	
	public void setClickEnable(boolean flag){
		ClickFlag = flag;
		imgbtnMachineStatus.setClickable(ClickFlag);
	}
}