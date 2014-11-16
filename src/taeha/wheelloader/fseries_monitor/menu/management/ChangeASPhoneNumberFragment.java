package taeha.wheelloader.fseries_monitor.menu.management;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.content.SharedPreferences;
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

public class ChangeASPhoneNumberFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	static final int MAX_DATA_PW_LENGTH = 21;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;

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
	
	ImageButton imgbtnBack;
	TextView textViewNext;
	
	TextView textViewPhoneNum;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	String strASNum;
	String strASNumDash;
	byte[] ASPhoneNumber;
	int DataBufIndex;
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
		 TAG = "ChangeASPhoneNumberFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_changephone, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Change_AS_Phone_Number));
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_changephone_low_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_changephone_low_cancel);
		
		textViewNum1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_1);
		textViewNum2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_2);
		textViewNum3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_3);
		textViewNum4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_4);
		textViewNum5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_5);
		textViewNum6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_6);
		textViewNum7 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_7);
		textViewNum8 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_8);
		textViewNum9 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_9);
		textViewNum0 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_0);
		
		imgbtnBack = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_management_changephone_num_back);
		textViewNext = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_num_next);
		
		textViewPhoneNum = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_changephone_phonenum);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		ASPhoneNumber = new byte[MAX_DATA_PW_LENGTH];
		for(int i = 0; i < MAX_DATA_PW_LENGTH; i++){
			ASPhoneNumber[i] = (byte)0xFF;
		}
		DataBufIndex = 0;
		LoadPrefAS();
		ASDisplay(strASNum);
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
			}
		});
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});	
		
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
//		textViewNext.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ClickNumNext();
//			}
//		});	
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
	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyManagementAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);

		ParentActivity.strASNumDash = strASNumDash;
		
		SetChangeASNum(ASPhoneNumber,DataBufIndex);
		SavePrefAS();
	}
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyManagementAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
	}
	public void ClickDefault(){
			
	}
	public void ClickNum1(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x31;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}
	public void ClickNum2(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x32;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}

	}

	public void ClickNum3(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x33;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}

	public void ClickNum4(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x34;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}

	public void ClickNum5(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x35;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
	
	}

	public void ClickNum6(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x36;
			DataBufIndex++;
		}
		
	}

	public void ClickNum7(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x37;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}

	public void ClickNum8(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x38;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}

	public void ClickNum9(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x39;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}

	public void ClickNum0(){
		if(DataBufIndex < MAX_DATA_PW_LENGTH-1){
			ASPhoneNumber[DataBufIndex] = 0x30;
			DataBufIndex++;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
		
	}

	public void ClickBack(){
		if(DataBufIndex > 0){
			DataBufIndex--;
			ASDisplay(ASPhoneNumber,DataBufIndex);
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	public void ASDisplay(String str){
		int Length;
		byte ASNum[];
		Length = str.length();
		ASNum = new byte[Length];
		ASNum = str.getBytes();
		
		for(int i = 0; i < Length; i++){
			ASPhoneNumber[i] = ASNum[i]; 
			ASNum[i] -= 0x30;
		}
		strASNumDash = ParentActivity.GetASPhoneNum(ASNum);
		textViewPhoneNum.setText(strASNumDash);
	}
	public void ASDisplay(byte[] _data, int length){
		String str = "";
		for(int i = 0 ; i < length; i++){
			str+= Integer.toString(_data[i] - 0x30);
		}
		Log.d(TAG,"AS : " + str);
		ASDisplay(str);
		strASNum = str;
	}
	/////////////////////////////////////////////////////////////////////
	public void SetChangeASNum(byte[] NumDataBuf, int length){
		byte[] ASNum;
		ASNum = new byte[MAX_DATA_PW_LENGTH];
		for(int i = 0; i < MAX_DATA_PW_LENGTH; i++){
			ASNum[i] = (byte)0xFF;
		}
		
		for(int i = 0; i < length; i++){
			//if((NumDataBuf[i] < 0 ) || (NumDataBuf[i] > 9 ))
			//	break;
			ASNum[i] = (byte)NumDataBuf[i];
			Log.d(TAG,"AS : " + ASNum[i]);
		}
		ASNum[length] = 0x2A;
		CAN1Comm.Set_ASPhoneNumber_1095_PGN65425(ASNum);
		CAN1Comm.TxCANToMCU(145);		
	}
	
	public void LoadPrefAS(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		
		strASNum = SharePref.getString("strASNum", "");
		strASNumDash = SharePref.getString("strASNumDash", "");
		
		Log.d(TAG,"LoadPrefAS : " + strASNum);
	}
	public void SavePrefAS(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();

		edit.putString("strASNum", strASNum);
		edit.putString("strASNumDash", strASNumDash);
		
		edit.commit();
		Log.d(TAG,"SavePrefAS : " + strASNum);
	}
	/////////////////////////////////////////////////////////////////////
	
}
