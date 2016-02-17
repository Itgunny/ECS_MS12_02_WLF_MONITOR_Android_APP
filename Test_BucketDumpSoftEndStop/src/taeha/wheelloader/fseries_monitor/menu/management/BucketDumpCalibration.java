package taeha.wheelloader.fseries_monitor.menu.management;

import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.main.R.string;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BucketDumpCalibration extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	private static final String TAG = "BucketDumpCalibration";
	private static final int 	NORMAL_STATUS			= 0;
	private static final int 	PROCESS_CAL1			= 1;
	private static final int 	CAL1_RETRY				= 2;
	private static final int	CAL1_SUCCESS			= 3;
	private static final int 	PROCESS_CAL2			= 4;
	private static final int 	CAL2_RETRY				= 5;
	private static final int	CAL2_SUCCESS			= 6;
	
	private static final int	TIMER_OVER_ERROR 		= 9;
	private static final int 	BOOM_POSITION_ERROR 	= 10;
	private static final int	BUCKET_POSITION_ERROR 	= 11;
	private static final int 	HYD_OIL_TEMP_ERROR 		= 12;
	private static final int 	ENGINE_RPM_ERROR		= 13;
	private static final int 	LEVEL_POSITION_ERROR	= 14;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnCancel;
	TextView textViewCancel;
	
	TextView	textViewTitle;
	TextView	textViewStart;
	
	TextView 	textViewDesText1;
	TextView 	textViewDesText2;
	TextView 	textViewDesText3;
	TextView 	textViewDesText4;
	TextView 	textViewOrder5;
	TextView 	textViewDesText5;
	
	TextView	textViewBoomPositionTitle;
	TextView	textViewBucketPositionTitle;
	TextView	textViewHydOilTempTitle;
	TextView	textViewBoomPositionData;
	TextView	textViewBucketPositionData;
	TextView	textViewHydOilTempData;
	
	ImageView	imgViewStep1;
	ImageView	imgViewStep2;
	
	ProgressBar progressBucketDump;
	//////////////////////////////////////////////////	
	//VALUABLE////////////////////////////////////////
	int BoomPosition;
	int BucketPosition;
	int rpmData;
	
	// Timer	
	private Timer mCheckTimer = null;
	private Timer mEnableButtonTimer = null;
	
	
	private int StatusCNT;
	
	int Order;	
	int CursurIndex;
	
	Handler HandleCursurDisplay;
	//////////////////////////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_management_calibration_bucket_dump, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		CursurDisplay(CursurIndex);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_CALIBRATION_BUCKET_TOP;
		ParentActivity._MenuBaseFragment._MenuInterTitleFragment.SetTitleText(ParentActivity.getString(string.Bucket_Dump_Speed_Calibration), 473);
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestAEB_PGN61184_201(0);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(0);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(0);
		CAN1Comm.TxCANToMCU(201);
		
		StartEnableButtonTimer();
		
		HandleCursurDisplay = new Handler(){
			@Override
			public void handleMessage(Message msg){
				CursurDisplay(msg.what);
			}
		};
		

		
		return mRoot;
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		CancelCheckTimer();
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		CAN1Comm.Set_RequestAEB_PGN61184_201(3);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
		CAN1Comm.TxCANToMCU(201);
		CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
		CAN1Comm.Set_RequestAEB_PGN61184_201(3);
		CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
		CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
		
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.ImageButton_menu_body_management_calibration_bucket_dump_low_cancel);
		textViewCancel = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_low_cancel);
		textViewCancel.setText(getString(ParentActivity.getString(string.Cancel), 16));
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.First_Calibration), 353));
		
		
		textViewStart = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_start);
		textViewStart.setText(getString(ParentActivity.getResources().getString(R.string.Start), 352));
		
		textViewDesText1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_step_1);
		textViewDesText2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_step_2);
		textViewDesText3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_step_3);
		textViewDesText4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_step_4);
		textViewOrder5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_order_5);
		textViewDesText5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_step_5);
		
		textViewBoomPositionTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_boom_position);
		textViewBoomPositionTitle.setText("* " + getString(ParentActivity.getResources().getString(R.string.Boom_Position), 473) + " : ");
		textViewBucketPositionTitle = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_bucket_position);
		textViewBucketPositionTitle.setText("* " + getString(ParentActivity.getResources().getString(R.string.Bucket_Position), 474) + " : ");
		
		textViewBoomPositionData = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_boom_position_value);
		textViewBucketPositionData = (TextView)mRoot.findViewById(R.id.textView_menu_body_management_calibration_bucket_dump_main_bucket_position_value);
		
		imgViewStep1 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_bucket_dump_step_1);
		imgViewStep2 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_management_calibration_bucket_dump_step_2);

		progressBucketDump = (ProgressBar)mRoot.findViewById(R.id.progressBar_menu_body_management_calibration_bucket_dump);
		progressBucketDump.setMax(115);	// 23Sec.
		progressBucketDump.setProgress(0);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
		StatusCNT = 0;
		Order = 0;
		CursurIndex = 1;
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCancel();
			}
		});
		textViewStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickStart();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		rpmData = CAN1Comm.Get_EngineSpeed_310_PGN65431();
		BoomPosition = CAN1Comm.Get_BoomPositionCalibrationError_1946_PGN61184_202();
		BucketPosition = CAN1Comm.Get_BucketPositionCalibrationError_1947_PGN61184_202();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		BoomPosition(textViewBoomPositionData, BoomPosition);
		BucketPosition(textViewBucketPositionData, BucketPosition);
		BucketDumpProgressDisplay(StatusCNT);
	}

	
	/////////////////////////////////////////////////////////////////////	
	public void ClickCancel(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();

			ParentActivity._MenuBaseFragment.showServiceMenuSoftAndStopCalbrationMenuListAnimation();
	}
	public void ClickStart(){
		if(Order == 0){
			CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(1);
			CAN1Comm.TxCANToMCU(201);
			CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
			StatusCNT = 0;
			CancelCheckTimer();
			StartCheckTimer();
		}else if(Order == 1){
			CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(2);
			CAN1Comm.TxCANToMCU(201);
			CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
			progressBucketDump.setMax(90);	// 18Sec.
			StatusCNT = 0;
			CancelCheckTimer();
			StartCheckTimer();
		}
		StartButtonOnOff(false);
	}
	/////////////////////////////////////////////////////////////////////
	public void Order2Display(){
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Second_Calibration), 354));
		textViewDesText2.setText("시작 버튼을 누르고, 액셀 페달을 최대로 밟은 상태\n에서 버켓 레버를 최대로 조작해 덤프를 실시합니다.");
		textViewDesText5.setVisibility(View.VISIBLE);
		textViewOrder5.setVisibility(View.VISIBLE);
		imgViewStep1.setImageResource(R.drawable.menu_management_boom_pressure_step_off);
		imgViewStep2.setImageResource(R.drawable.menu_management_boom_pressure_step_on);
		StatusCNT = 0;
	}
	public void BucketDumpProgressDisplay(int progress){
		progressBucketDump.setProgress(progress);
	}
	/////////////////////////////////////////////////////////////////////	
	private void BoomPosition(TextView textData, int Data) {
		if(Data == 1){
			textData.setText(getString(ParentActivity.getResources().getString(string.Boom_Up), 236));
			textData.setTextColor(Color.BLACK);
			textData.setBackgroundColor(Color.RED);
		}else if(Data == 0){
			textData.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
			textData.setTextColor(Color.BLACK);
			textData.setBackgroundColor(Color.GREEN);
		}else{
			textData.setText("-");
			textData.setTextColor(Color.BLACK);
			textData.setBackgroundColor(Color.RED);			
		}
	}
	private void BucketPosition(TextView textData, int Data) {
		if(Data == 1){
			textData.setText("롤백");
			textData.setTextColor(Color.BLACK);
			textData.setBackgroundColor(Color.RED);
		}else if(Data == 0){
			textData.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
			textData.setTextColor(Color.BLACK);
			textData.setBackgroundColor(Color.GREEN);
		}else{
			textData.setText("-");
			textData.setTextColor(Color.BLACK);
			textData.setBackgroundColor(Color.RED);
		}
	}
	/////////////////////////////////////////////////////////////////////
	public void StartButtonOnOff(boolean flag){
		if(flag == true){
			textViewStart.setClickable(true);
			textViewStart.setAlpha((float)1.0);
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
		}else{
			textViewStart.setClickable(false);
			textViewStart.setAlpha((float)0.5);
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
		}
		
	}
	public void CheckBucketDumpProgressStatus(int StatusCnt){
		int Result;
		Result = CAN1Comm.Get_RequestBucketDumpSpeedCalibrationStatus_1945_PGN61184_202();
		Log.d(TAG,"Result : " + Integer.toString(Result)+" StatusCnt:"+Integer.toString(StatusCnt));
		//if((StatusCnt >= 25) && (StatusCnt <= 90)) // 5 Sec ~ 15 Sec
		if((StatusCnt >= 15)) // 5 Sec ~ 
		{
			if( (Result == CAL1_SUCCESS) ||  (Result == CAL2_SUCCESS) 
				|| (Result == CAL1_RETRY)|| (Result == CAL2_RETRY)
				|| (Result == TIMER_OVER_ERROR) || (Result == BOOM_POSITION_ERROR)
				|| (Result == BUCKET_POSITION_ERROR) || (Result == HYD_OIL_TEMP_ERROR)
				|| (Result == ENGINE_RPM_ERROR)	|| (Result == LEVEL_POSITION_ERROR)){
				CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(0);
				CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(0);
				CAN1Comm.Set_RequestAEB_PGN61184_201(0);
				CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(0);
				CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(0);
				CAN1Comm.TxCANToMCU(201);
				CAN1Comm.Set_RequestBoomPressureCalibration_PGN61184_201(3);
				CAN1Comm.Set_RequestBoomBucketAngleSensorCalibration_PGN61184_201(15);
				CAN1Comm.Set_RequestAEB_PGN61184_201(3);
				CAN1Comm.Set_RequestBrakePedalPositionSensorCalibration_PGN61184_201(3);
				CAN1Comm.Set_RequestBucketDumpCalibration_PGN61184_201(3);
				if(Order == 0){
					StatusCNT = 115;
				}else if(Order == 1){
					StatusCNT = 90;
				}
				switch (Result) {
				case NORMAL_STATUS:
					break;
				case CAL1_RETRY:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("보정값을 찾기 전입니다.\n1st Calibration을 재시도 해주세요.");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity._BucketDumpCalibrationPopup.setRetry(CAL1_RETRY);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"CAL2_SUCCESS");
					break;
				case CAL1_SUCCESS:
					Order = 1;
					Order2Display();
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("1nd Calibration 완료");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"CAL1_SUCCESS");
					break;
				case CAL2_RETRY:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("보정값을 찾기 전입니다.\n2nd Calibration을 재시도 해주세요.");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity._BucketDumpCalibrationPopup.setRetry(CAL2_RETRY);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"CAL2_SUCCESS");
					break;
				case CAL2_SUCCESS:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("2nd Calibration 완료");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(true);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"CAL2_SUCCESS");
					break;
				case TIMER_OVER_ERROR:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("Calibration을 실패하였습니다.\n경과시간 초과");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"TIMER_OVER_ERROR");
					break;
				case BOOM_POSITION_ERROR:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("Calibration을 실패하였습니다.\n붐 위치 오류");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"BOOM_POSITION_ERROR");
					break;
				case BUCKET_POSITION_ERROR:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("Calibration을 실패하였습니다.\n버켓 위치 오류");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"BUCKET_POSITION_ERROR");
					break;
				case HYD_OIL_TEMP_ERROR:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("Calibration을 실패하였습니다.\n작동유 온도 오류");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"HYD_OIL_TEMP_ERROR");
					break;	
				case ENGINE_RPM_ERROR:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("Calibration을 실패하였습니다.\n엔진 회전수 오류");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"ENGINE_RPM_ERROR");
					break;
				case LEVEL_POSITION_ERROR:
					ParentActivity._BucketDumpCalibrationPopup.setTextTitle("Calibration을 실패하였습니다.\n최대 레버 조작 오류");
					ParentActivity._BucketDumpCalibrationPopup.setExitFlag(false);
					ParentActivity.showBuckDumpCalibrationResult();
					Log.d(TAG,"LEVEL_POSITION_ERROR");
					break;
				}
				StartButtonOnOff(true);
				CancelCheckTimer();
			}
		}
	}
	public class CheckTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					StatusCNT++;
					CheckBucketDumpProgressStatus(StatusCNT);
					UpdateUI();
				}
			});
			
		}
		
	}

	public void StartCheckTimer(){
		CancelCheckTimer();
		mCheckTimer = new Timer();
		mCheckTimer.schedule(new CheckTimerClass(),0,200);	
	}
	
	public void CancelCheckTimer(){
		if(mCheckTimer != null){
			mCheckTimer.cancel();
			mCheckTimer.purge();
			mCheckTimer = null;
		}
		
	}	
	/////////////////////////////////////////////////////////////////////
	public class EnableButtonTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(ParentActivity.AnimationRunningFlag == false)
					{
						CancelEnableButtonTimer();
						
						ParentActivity.showBucketDumpInitCalibrationPopup();
					}
				}
			});

		}

	}

	public void StartEnableButtonTimer(){
		CancelEnableButtonTimer();
		mEnableButtonTimer = new Timer();
		mEnableButtonTimer.schedule(new EnableButtonTimerClass(),1,50);	
	}

	public void CancelEnableButtonTimer(){
		if(mEnableButtonTimer != null){
			mEnableButtonTimer.cancel();
			mEnableButtonTimer.purge();
			mEnableButtonTimer = null;
		}

	}
	/////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			if(textViewStart.isClickable() == true){
				CursurIndex--;
				CursurDisplay(CursurIndex);
			}
			break;
		default:
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
			if(textViewStart.isClickable() == true){
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
			}
			break;
		default:
			break;
		}
	}
	public void ClickESC(){
		ClickCancel();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickStart();
			break;
		case 2:
			ClickCancel();
			break;
		default:
			break;
		}
	}
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewStart.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			textViewStart.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}
	public String GetTemp(int _Temp, int Unit){
		String strTemp;
		int nTemp;
		long long_Temp;
		
		long_Temp = (_Temp  & 0xFFFFFFFFL);
		if(long_Temp >= 0xFFL){
			return "0";
		}else
		{
			nTemp = GetTemp(_Temp);
			if(Unit == ParentActivity.UNIT_TEMP_F){
				nTemp *= 18;
				nTemp = nTemp / 10;
				nTemp += 32;
				strTemp = ParentActivity.GetNumberString(nTemp);
			}else{
				strTemp = ParentActivity.GetNumberString(nTemp);
			}
		}
		
		return strTemp;
		
	}
	public int GetTemp(int _Temp){
		long long_Temp;
		int nTemp;
		
		long_Temp = (_Temp  & 0xFFFFFFFFL);
		if(long_Temp >= 0xFFL){
			long_Temp = 0;
		}
		nTemp = (int) (long_Temp - 40);
		
		return nTemp;
	}
}
