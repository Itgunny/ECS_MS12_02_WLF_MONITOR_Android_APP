package taeha.wheelloader.fseries_monitor.menu.mode;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CheckModel;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.RadioButton;
import android.widget.TextView;

public class SoftStopFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	ImageButton imgbtnDefault;
	
	RadioButton radioBoomUpOn;
	RadioButton radioBoomUpOff;
	RadioButton radioBoomDownOn;
	RadioButton radioBoomDownOff;
	RadioButton radioBucketInOn;
	RadioButton radioBucketInOff;
	RadioButton radioBucketOutOn;
	RadioButton radioBucketOutOff;
	
	TextView textBoomUpTitle;
	TextView textBoomDownTitle;
	TextView textBucketInTitle;
	TextView textBucketDumpTitle;
	
	ImageView imgViewLine1;
	ImageView imgViewLine2;
	ImageView imgViewLine3;
	ImageView imgViewDot1;
	ImageView imgViewDot2;
	ImageView imgViewDot3;
	ImageView imgViewDot4;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int BoomUp;
	int BoomDown;
	int BucketIn;
	int BucketOut;
	
	Handler HandleCursurDisplay;
	int CursurIndex;
	
	boolean CheckTM;
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
		 TAG = "SoftStopFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_mode_softstop, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_HYD_SOFTSTOP_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Soft_End_Stop));
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
			
				CursurDisplay(msg.what);
			}
		};
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_softstop_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_softstop_low_cancel);
		imgbtnDefault = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_mode_softstop_low_default);
		
		radioBoomUpOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomup_on);
		radioBoomUpOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomup_off);
		radioBoomDownOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomdown_on);
		radioBoomDownOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_boomdown_off);
		radioBucketInOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketin_on);
		radioBucketInOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketin_off);
		radioBucketOutOn = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketout_on);
		radioBucketOutOff = (RadioButton)mRoot.findViewById(R.id.radioButton_menu_body_mode_softstop_bucketout_off);
		
		textBoomUpTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_softstop_boomup);
		textBoomDownTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_softstop_boomdown);
		textBucketInTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_softstop_bucketin);
		textBucketDumpTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_mode_softstop_bucketout);
		
		imgViewLine1 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_line1);
		imgViewLine2 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_line2);
		imgViewLine3 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_line3);
		
		imgViewDot1 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_boomup);
		imgViewDot2 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_boomdown);
		imgViewDot3 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_bucketin);
		imgViewDot4 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_mode_softstop_bucketout);

	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		BoomUp = CAN1Comm.Get_SoftStopBoomUp_2337_PGN65524();
		BoomDown = CAN1Comm.Get_SoftStopBoomDown_2338_PGN65524();
		BucketIn = CAN1Comm.Get_SoftStopBucketIn_2339_PGN65524();
		BucketOut = CAN1Comm.Get_SoftStopBucketOut_2340_PGN65524();
		
		BoomUpDisplay(BoomUp);
		BoomDownDisplay(BoomDown);
		BucketInDisplay(BucketIn);
		BucketOutDisplay(BucketOut);
		
		/*if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935TM
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940TM
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_955TM
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_960TM
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965TM	// ++, --, 150329 bwk
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970TM
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_975TM		
		|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980TM)*/
		String strModelOption = ParentActivity._CheckModel.GetMCUModelOption(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330());
		if(strModelOption.equals("TM")){
			
			CheckTM = true;
			
			textBoomDownTitle.setVisibility(View.INVISIBLE);
			textBucketInTitle.setVisibility(View.INVISIBLE);
			textBucketDumpTitle.setVisibility(View.INVISIBLE);
			imgViewLine2.setVisibility(View.INVISIBLE);
			imgViewLine3.setVisibility(View.INVISIBLE);
			
			imgViewDot2.setVisibility(View.INVISIBLE);
			imgViewDot3.setVisibility(View.INVISIBLE);
			imgViewDot4.setVisibility(View.INVISIBLE);
			
			radioBoomDownOn.setVisibility(View.INVISIBLE);
			radioBoomDownOff.setVisibility(View.INVISIBLE);
			radioBucketInOn.setVisibility(View.INVISIBLE);
			radioBucketInOff.setVisibility(View.INVISIBLE);
			radioBucketOutOn.setVisibility(View.INVISIBLE);
			radioBucketOutOff.setVisibility(View.INVISIBLE);		
			
			
			
		}else{
			CheckTM = false;
			
			textBoomDownTitle.setVisibility(View.VISIBLE);
			textBucketInTitle.setVisibility(View.VISIBLE);
			textBucketDumpTitle.setVisibility(View.VISIBLE);
			imgViewLine2.setVisibility(View.VISIBLE);
			imgViewLine3.setVisibility(View.VISIBLE);
			
			imgViewDot2.setVisibility(View.VISIBLE);
			imgViewDot3.setVisibility(View.VISIBLE);
			imgViewDot4.setVisibility(View.VISIBLE);
			
			radioBoomDownOn.setVisibility(View.VISIBLE);
			radioBoomDownOff.setVisibility(View.VISIBLE);
			radioBucketInOn.setVisibility(View.VISIBLE);
			radioBucketInOff.setVisibility(View.VISIBLE);
			radioBucketOutOn.setVisibility(View.VISIBLE);
			radioBucketOutOff.setVisibility(View.VISIBLE);		
		}
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
				CursurIndex = 11;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
				CursurIndex = 10;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		imgbtnDefault.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickDefault();
				CursurIndex = 9;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBoomUpOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomUpOn();
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBoomUpOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomUpOff();
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBoomDownOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomDownOn();
				CursurIndex = 3;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBoomDownOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBoomDownOff();
				CursurIndex = 4;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBucketInOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketInOn();
				CursurIndex = 5;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBucketInOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketInOff();
				CursurIndex = 6;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBucketOutOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketOutOn();
				CursurIndex = 7;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
			}
		});
		radioBucketOutOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickBucketOutOff();
				CursurIndex = 8;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
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
	public void SetDefault(){
		BoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON;
		BoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON;
		BucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF;
		BucketOut = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON;
		
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(BoomUp);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(BoomDown);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(BucketIn);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(BucketOut);
		CAN1Comm.TxCANToMCU(203);
		CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
		CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		
		BoomUpDisplay(BoomUp);
		BoomDownDisplay(BoomDown);
		BucketInDisplay(BucketIn);
		BucketOutDisplay(BucketOut);
	}
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		
		/*if(ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_935TM
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_940TM
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_955TM
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_960TM
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_965TM	// ++, --, 150329 bwk
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_970TM
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_975TM				
				|| ParentActivity._CheckModel.GetMCUVersion(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330()) == CheckModel.MODEL_980TM){
				*/
		String strModelOption = ParentActivity._CheckModel.GetMCUModelOption(CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330());
		if(strModelOption.equals("TM")){
			CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(BoomUp);
			CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		}else{
			CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(BoomUp);
			CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(BoomDown);
			CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(BucketIn);
			CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(BucketOut);
			CAN1Comm.TxCANToMCU(203);
			CAN1Comm.Set_SoftStopBoomUp_2337_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBoomDown_2338_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBucketIn_2339_PGN61184_203(3);
			CAN1Comm.Set_SoftStopBucketOut_2340_PGN61184_203(3);
		}
		

		
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyModeAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MODE_HYD_TOP);
	}
	public void ClickDefault(){
		ParentActivity.showSoftStopInit();
		
	}
	public void ClickBoomUpOn(){
		radioBoomUpOn.setChecked(true);
		radioBoomUpOff.setChecked(false);
		BoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON;
	}
	public void ClickBoomUpOff(){
		radioBoomUpOn.setChecked(false);
		radioBoomUpOff.setChecked(true);
		BoomUp = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF;
	}
	public void ClickBoomDownOn(){
		radioBoomDownOn.setChecked(true);
		radioBoomDownOff.setChecked(false);
		BoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON;
	}
	public void ClickBoomDownOff(){
		radioBoomDownOn.setChecked(false);
		radioBoomDownOff.setChecked(true);
		BoomDown = CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF;
	}
	public void ClickBucketInOn(){
		radioBucketInOn.setChecked(true);
		radioBucketInOff.setChecked(false);
		BucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON;
	}
	public void ClickBucketInOff(){
		radioBucketInOn.setChecked(false);
		radioBucketInOff.setChecked(true);
		BucketIn = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF;
	}
	public void ClickBucketOutOn(){
		radioBucketOutOn.setChecked(true);
		radioBucketOutOff.setChecked(false);
		BucketOut = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON;
	}
	public void ClickBucketOutOff(){
		radioBucketOutOn.setChecked(false);
		radioBucketOutOff.setChecked(true);
		BucketOut = CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF;
	}
	/////////////////////////////////////////////////////////////////////
	public void BoomUpDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			radioBoomUpOn.setChecked(false);
			radioBoomUpOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
			radioBoomUpOn.setChecked(true);
			radioBoomUpOff.setChecked(false);
			break;
		
		default:
			break;
		}
	}
	public void BoomDownDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_OFF:
			radioBoomDownOn.setChecked(false);
			radioBoomDownOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMDOWN_ON:
			radioBoomDownOn.setChecked(true);
			radioBoomDownOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	public void BucketInDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_OFF:
			radioBucketInOn.setChecked(false);
			radioBucketInOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETIN_ON:
			radioBucketInOn.setChecked(true);
			radioBucketInOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	public void BucketOutDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_OFF:
			radioBucketOutOn.setChecked(false);
			radioBucketOutOff.setChecked(true);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BUCKETOUT_ON:
			radioBucketOutOn.setChecked(true);
			radioBucketOutOff.setChecked(false);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 11;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 3:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 4:
			if(CheckTM == true){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 5:
			if(CheckTM == true){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 6:
			if(CheckTM == true){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 7:
			if(CheckTM == true){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 8:
			if(CheckTM == true){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 9:
			if(CheckTM == true){
				CursurIndex = 2;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		case 10:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
		
	}
	public void ClickRight(){
		switch (CursurIndex) {
		case 1:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 2:			
			if(CheckTM){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 3:		
			if(CheckTM){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 4:		
			if(CheckTM){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 5:			
			if(CheckTM){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 6:		
			if(CheckTM){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 7:		
			if(CheckTM){
				CursurIndex = 9;
				CursurDisplay(CursurIndex);
			}else{
				CursurIndex++;
				CursurDisplay(CursurIndex);
			}
			break;
		case 8:		
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 9:		
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 10:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 11:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		default:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickBoomUpOn();
			break;
		case 2:
			ClickBoomUpOff();
			break;
		case 3:
			ClickBoomDownOn();
			break;
		case 4:
			ClickBoomDownOff();
			break;
		case 5:
			ClickBucketInOn();
			break;
		case 6:
			ClickBucketInOff();
			break;
		case 7:
			ClickBucketOutOn();
			break;
		case 8:
			ClickBucketOutOff();
			break;
		case 9:
			ClickDefault();
			break;
		case 10:
			ClickCancel();
			break;
		case 11:
			ClickOK();
			break;
	
		default:
			break;
		}
	}
	public void CursurFirstDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_ON:
		default:
			CursurIndex = 1;
			CursurDisplay(1);
			break;
		case CAN1CommManager.DATA_STATE_SOFTSTOP_BOOMUP_OFF:
			CursurIndex = 2;
			CursurDisplay(2);
			break;
	
		}
	}
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnCancel.setPressed(false);
		imgbtnDefault.setPressed(false);
		radioBoomUpOn.setPressed(false);
		radioBoomUpOff.setPressed(false);
		radioBoomDownOn.setPressed(false);
		radioBoomDownOff.setPressed(false);
		radioBucketInOn.setPressed(false);
		radioBucketInOff.setPressed(false);
		radioBucketOutOn.setPressed(false);
		radioBucketOutOff.setPressed(false);
		switch (Index) {
		case 1:
			radioBoomUpOn.setPressed(true);
			break;
		case 2:
			radioBoomUpOff.setPressed(true);
			break;
		case 3:
			radioBoomDownOn.setPressed(true);
			break;
		case 4:
			radioBoomDownOff.setPressed(true);
			break;
		case 5:
			radioBucketInOn.setPressed(true);
			break;
		case 6:
			radioBucketInOff.setPressed(true);
			break;
		case 7:
			radioBucketOutOn.setPressed(true);
			break;
		case 8:
			radioBucketOutOff.setPressed(true);
			break;
		case 9:
			imgbtnDefault.setPressed(true);
			break;
		case 10:
			imgbtnCancel.setPressed(true);
			break;
		case 11:
			imgbtnOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
