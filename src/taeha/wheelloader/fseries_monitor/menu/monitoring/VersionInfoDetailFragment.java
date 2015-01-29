package taeha.wheelloader.fseries_monitor.menu.monitoring;

import customlist.versiondetail.IconTextListAdapterVersion;
import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VersionInfoDetailFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	protected static final int LENGTH_COMPONENTBASICINFORMATION	= 37;
	protected static final int LENGTH_SOFTWAREIDENTIFICATION = 200;
	
//	protected static final int STATE_MANUFACTUREDAY					 		= 0;
//	protected static final int STATE_SERIALNUMBER					 		= 1;
//	protected static final int STATE_MAKER					 				= 2;
//	protected static final int STATE_VERSION					 			= 3;
	
	protected static final int STATE_SERIALNUMBER					 		= 0;
	protected static final int STATE_MAKER					 				= 1;
	protected static final int STATE_VERSION					 			= 2;
	
	
	protected static final int STATE_COMPONENTCODE_MCU							= 1;
	protected static final int STATE_COMPONENTCODE_ECM							= 5;	
	protected static final int STATE_COMPONENTCODE_TCU 							= 8;
	protected static final int STATE_COMPONENTCODE_MONITOR 						= 11;
	protected static final int STATE_COMPONENTCODE_SCU							= 15;
	protected static final int STATE_COMPONENTCODE_HAPTIC						= 16;
	protected static final int STATE_COMPONENTCODE_CLUSTER						= 21;
	protected static final int STATE_COMPONENTCODE_RCU							= 25;
	protected static final int STATE_COMPONENTCODE_RMCU							= 31;
	protected static final int STATE_COMPONENTCODE_HCE_DT						= 41;
	protected static final int STATE_COMPONENTCODE_HCU							= 51;
	protected static final int STATE_COMPONENTCODE_EPCU							= 53;
	protected static final int STATE_COMPONENTCODE_UCC 							= 55;
	protected static final int STATE_COMPONENTCODE_APTC							= 61;
	protected static final int STATE_COMPONENTCODE_AIRCONCONTROLLER				= 62;
	protected static final int STATE_COMPONENTCODE_JOYSTICKSTEERINGCONTROLLER	= 71;
	protected static final int STATE_COMPONENTCODE_ELECTRICJOYSTIC_STEERING		= 72;
	protected static final int STATE_COMPONENTCODE_ELECTRICJOYSTIC_ATTACHMENT	= 73;
	protected static final int STATE_COMPONENTCODE_SMK							= 81;
	protected static final int STATE_COMPONENTCODE_ALLAROUNDVIEWCONTROLLER		= 85;
	protected static final int STATE_COMPONENTCODE_EHCU							= 91;
	protected static final int STATE_COMPONENTCODE_ENGINE						= 101;
	
	protected static final int STATE_MANUFACTURERCODE_TAEHA						= 1;
	protected static final int STATE_MANUFACTURERCODE_FREEMS					= 10;
	protected static final int STATE_MANUFACTURERCODE_KYUNGWOO					= 20;
	protected static final int STATE_MANUFACTURERCODE_DONHWAN					= 21;
	protected static final int STATE_MANUFACTURERCODE_CONTINENTAL				= 22;
	protected static final int STATE_MANUFACTURERCODE_ZF						= 41;
	protected static final int STATE_MANUFACTURERCODE_SAUNERDANFOSS				= 51;
	protected static final int STATE_MANUFACTURERCODE_FIND						= 101;
	protected static final int STATE_MANUFACTURERCODE_GIMIS						= 102;
	protected static final int STATE_MANUFACTURERCODE_HMC						= 111;
	protected static final int STATE_MANUFACTURERCODE_CUMMINS					= 112;
	protected static final int STATE_MANUFACTURERCODE_MITSUBISHI				= 113;
	protected static final int STATE_MANUFACTURERCODE_YANMAR					= 114;
	protected static final int STATE_MANUFACTURERCODE_PERKINS					= 115;
	protected static final int STATE_MANUFACTURERCODE_SCANIA					= 116;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	protected ImageButton imgbtnOK;

	protected TextView textViewModelData;
	protected TextView textViewDateData;
	
	// ListView
	protected ListView listView;
	// ListItem
	protected IconTextListAdapterVersion adapter;

	protected RelativeLayout layoutModel;
	protected RelativeLayout layoutDate;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	protected int ComponentCode;
	protected int ManufacturerCode;
	protected byte[] ComponentBasicInformation;
	
	protected byte[] SoftwareIdentification;
	
	protected int ProgramSubVersion;
	
	protected boolean ManufactureDayDisplayFlag;
	
	protected int ListCursurIndex;
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
		 TAG = "VersionInfoDetailFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_monitoring_version_detail, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		ShowManufactureDay(ManufactureDayDisplayFlag);
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_monitoring_version_detail_low_ok);

		textViewModelData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_detail_model_data);
		textViewDateData = (TextView)mRoot.findViewById(R.id.textView_menu_body_monitoring_version_detail_date_data);
		
		layoutModel = (RelativeLayout)mRoot.findViewById(R.id.Relativelayout_menu_body_monitoring_version_detail_model);
		layoutDate = (RelativeLayout)mRoot.findViewById(R.id.Relativelayout_menu_body_monitoring_version_detail_date);
		
		listView = (ListView)mRoot.findViewById(R.id.listView_menu_body_monitoring_version_detail);
		
		adapter = new IconTextListAdapterVersion(ParentActivity);
		adapter.clearItem();

	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		try {
			ComponentBasicInformation = new byte[LENGTH_COMPONENTBASICINFORMATION];
			SoftwareIdentification = new byte[LENGTH_SOFTWAREIDENTIFICATION];
			
			ProgramSubVersion = 0xFF;
			ComponentCode = 0;
			ManufacturerCode = 0;
			ManufactureDayDisplayFlag = false;
			ListCursurIndex = 0;
			for(int i = 0; i < LENGTH_COMPONENTBASICINFORMATION; i++){
				ComponentBasicInformation[i] = 0;
			}
			for(int i = 0; i < LENGTH_SOFTWAREIDENTIFICATION; i++){
				SoftwareIdentification[i] = 0;
		}
	} catch (java.lang.ArrayIndexOutOfBoundsException e) {
		// TODO: handle exception
		Log.e(TAG,"ArrayIndexOutOfBoundsException");
	}
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
		ParentActivity._MenuBaseFragment.showBodyVersionInfo();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MONITORING_VERSIONINFO_TOP);
	}
	
	/////////////////////////////////////////////////////////////////////
	public void ShowManufactureDay(final boolean flag){
		ManufactureDayDisplayFlag = flag;
		
		ParentActivity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(flag == true){
					layoutModel.setVisibility(View.VISIBLE);
					layoutDate.setVisibility(View.VISIBLE);
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)listView.getLayoutParams();
					params.addRule(RelativeLayout.ALIGN_PARENT_TOP,0);
					listView.setLayoutParams(params); //causes layout update
				}
					
				else{
					layoutModel.setVisibility(View.INVISIBLE);
					layoutDate.setVisibility(View.INVISIBLE);
					RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)listView.getLayoutParams();
					params.addRule(RelativeLayout.ALIGN_PARENT_TOP,1);
					listView.setLayoutParams(params); //causes layout update
				}
			}
		});
	}
	public int SoftwareIDDisplay(byte[] softwareID, int StartIndex, int MaxLength, int LineIndex)throws NullPointerException{
		int Index = 0;
		boolean bAsterisk = false;
		try {
			for(int i = StartIndex; i < StartIndex + MaxLength + 1; i++){
				if(softwareID[i] == 0x2A){
					bAsterisk = true;
					break;
				}else{
					if(StartIndex + MaxLength != i)
						Index++;
				}
			}
			if(Index > 1 && bAsterisk == true){
				char[] cString;
				String strString;
				cString = new char[Index];
				for(int i = 0; i < Index; i++){
					cString[i] = (char) softwareID[StartIndex + i];
				}
				strString = new String(cString,0,cString.length);
				adapter.UpdateSecond(LineIndex, strString);
			}else{
				adapter.UpdateSecond(LineIndex, "-");
			
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			Log.e(TAG,"ArrayIndexOutOfBoundsException");
		}

		return StartIndex + Index + 1;
		
	}
	public void SerialNumberDisplay(byte[] BasicInfo)throws NullPointerException{
		boolean DataCheckFlag = true;
		String strSerial;
		int Index = 0;
		for(int i = 4; i < 20; i++){
			if(BasicInfo[i] != 0x2A)
			{
				Index++;
			}
			else{
				
				break;
			}
		}
		
		char[] Serial;
		Serial = new char [Index];
		int[] Temp;
		Temp = new int[Index];
		
		for(int i = 0; i < Index; i++){
			Serial[i] = (char) BasicInfo[i+4];
			Temp[i] = (int)(BasicInfo[i+4] & 0xFF);
			if(Temp[i] > 254){
				DataCheckFlag = false;
			}
		}
		strSerial = new String(Serial,0,Serial.length);
		if(DataCheckFlag == false){
			adapter.UpdateSecond(STATE_SERIALNUMBER, "-");
		}else{
			adapter.UpdateSecond(STATE_SERIALNUMBER, strSerial);
		}
		
	}
	public void ManufactureDayDisplay(byte[] BasicInfo)throws NullPointerException{
//		int Year, Month, Day;
//		Year = (BasicInfo[0] & 0xFF) + 2000;
//		Month = (BasicInfo[1] & 0xFF);
//		Day = (BasicInfo[2] & 0xFF);
//		if(Year > 2250 || Month > 12 || Day > 31){
//			adapter.UpdateSecond(STATE_MANUFACTUREDAY, "-");
//		}else{
//			adapter.UpdateSecond(STATE_MANUFACTUREDAY, Integer.toString(Year) + "-" + Integer.toString(Month) + "-" + Integer.toString(Day));
//		}
		
		
		if(ManufactureDayDisplayFlag == true){
			int Year, Month, Day;
			Year = (BasicInfo[0] & 0xFF) + 2000;
			Month = (BasicInfo[1] & 0xFF);
			Day = (BasicInfo[2] & 0xFF);
			if(Year > 2250 || Month > 12 || Day > 31){
				textViewDateData.setText("-");
			}else{
				textViewDateData.setText( Integer.toString(Year) + "-" + Integer.toString(Month) + "-" + Integer.toString(Day));
			}
		}
	}
	public void ManufacturerDisplay(int Code){
		switch (Code) {
		case STATE_MANUFACTURERCODE_TAEHA:
			adapter.UpdateSecond(STATE_MAKER, "Taeha");
			break;
		case STATE_MANUFACTURERCODE_FREEMS:
			adapter.UpdateSecond(STATE_MAKER, "FreeMs Corp.");
			break;
		case STATE_MANUFACTURERCODE_KYUNGWOO:
			adapter.UpdateSecond(STATE_MAKER, "KYUNGWOO");
			break;
		case STATE_MANUFACTURERCODE_DONHWAN:
			adapter.UpdateSecond(STATE_MAKER, "DongHwan");
			break;
		case STATE_MANUFACTURERCODE_CONTINENTAL:
			adapter.UpdateSecond(STATE_MAKER, "Continental");
			break;
		case STATE_MANUFACTURERCODE_ZF:
			adapter.UpdateSecond(STATE_MAKER, "ZF");
			break;
		case STATE_MANUFACTURERCODE_SAUNERDANFOSS:
			adapter.UpdateSecond(STATE_MAKER, "Danfoss Power Solution");
			break;
		case STATE_MANUFACTURERCODE_FIND:
			adapter.UpdateSecond(STATE_MAKER, "FIND");
			break;
		case STATE_MANUFACTURERCODE_GIMIS:
			adapter.UpdateSecond(STATE_MAKER, "GIMIS");
			break;
		case STATE_MANUFACTURERCODE_HMC:
			adapter.UpdateSecond(STATE_MAKER, "HMC");
			break;
		case STATE_MANUFACTURERCODE_CUMMINS:
			adapter.UpdateSecond(STATE_MAKER, "Cummins");
			break;
		case STATE_MANUFACTURERCODE_MITSUBISHI:
			adapter.UpdateSecond(STATE_MAKER, "Mitsubishi");
			break;
		case STATE_MANUFACTURERCODE_YANMAR:
			adapter.UpdateSecond(STATE_MAKER, "Yanmar");
			break;
		case STATE_MANUFACTURERCODE_PERKINS:
			adapter.UpdateSecond(STATE_MAKER, "Perkins");
			break;
		case STATE_MANUFACTURERCODE_SCANIA:
			adapter.UpdateSecond(STATE_MAKER, "Scania");
			break;
		case 0xFF:
			adapter.UpdateSecond(STATE_MAKER, "-");
			break;
		default:
			adapter.UpdateSecond(STATE_MAKER, "-");
			break;
		}
	}
	public void ModelDisplay(byte[] _data){
		textViewModelData.setText(ParentActivity.GetModelNameString(_data));
	}
	public void VersionDisplay(byte[] _data, int _subinfo){
		adapter.UpdateSecond(STATE_VERSION, ParentActivity.GetVersionString(_data, _subinfo));
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		int TotalItem;
		
		TotalItem = listView.getCount();
		
		ListCursurIndex -= 5;
		
		if(ListCursurIndex <= 0){
			ListCursurIndex = 0;
		}
		listView.setSelectionFromTop(ListCursurIndex,0);
		Log.d(TAG,"ListCursurIndex : " + Integer.toString(ListCursurIndex));
	}
	public void ClickRight(){	
		int TotalItem;
		
		TotalItem = listView.getCount();
		
		ListCursurIndex += 5;
		
		if(ListCursurIndex >= TotalItem){
			ListCursurIndex = TotalItem - 5;
		}
		listView.setSelectionFromTop(ListCursurIndex,0);
		Log.d(TAG,"ListCursurIndex : " + Integer.toString(ListCursurIndex));
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		ClickOK();
	}
}
