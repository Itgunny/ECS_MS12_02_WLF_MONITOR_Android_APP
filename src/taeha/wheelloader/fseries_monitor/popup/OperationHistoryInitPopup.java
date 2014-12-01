package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home.SeatBeltTimerClass;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup.PopupOffTimerClass;

public class OperationHistoryInitPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView textViewTitle;
	TextView textViewOK;
	TextView textViewCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	private Timer mPopupOffTimer = null;
	boolean bClickOKFlag;
	int TimerIndex;
	
	Handler HandleCancel;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public OperationHistoryInitPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "OperationHistoryInitPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_operationhistory_init, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_INIT;
		HandleCancel = new Handler() {
			@Override
			public void handleMessage(Message msg) {

				ClickCancel();
			}
		};
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(bClickOKFlag == false)
			return super.onTouchEvent(event);
		else
			return false;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		bClickOKFlag = false;
		TimerIndex = 0;
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		imgbtnOK.setVisibility(View.VISIBLE);
		imgbtnCancel.setVisibility(View.VISIBLE);
		textViewOK.setVisibility(View.VISIBLE);
		textViewCancel.setVisibility(View.VISIBLE);
		textViewTitle.setText(ParentActivity.getResources().getString(string.Initialize_));
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_OPERATIONHISTORY_TOP;
		try {
			ParentActivity._MenuBaseFragment._OperationHistoryFragment.CursurDisplay(6);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_operationhistory_init_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_operationhistory_init_cancel);
	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_operationhistory_init_title);

		textViewOK = (TextView)mRoot.findViewById(R.id.textView_popup_operationhistory_init_ok);
		textViewCancel = (TextView)mRoot.findViewById(R.id.textView_popup_operationhistory_init_cancel);
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
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	///////////////////////////////////////////////////////////////////////////////
	public void ClickOK(){

		StartPopupOffTimer();
		imgbtnOK.setVisibility(View.INVISIBLE);
		imgbtnCancel.setVisibility(View.INVISIBLE);
		textViewOK.setVisibility(View.INVISIBLE);
		textViewCancel.setVisibility(View.INVISIBLE);
		textViewTitle.setText(ParentActivity.getResources().getString(string.Waiting_for_initialization));
		bClickOKFlag = true;
		//this.dismiss();
	}	
	public void ClickCancel(){
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////
	public class PopupOffTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(TimerIndex == 0){
				if(ParentActivity._MenuBaseFragment._OperationHistoryFragment.checkWorkTotalA.isChecked() == true){
					CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A);
					CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
					CAN1Comm.Set_SuddenChangeError_PGN61184_62(CAN1Comm.Get_SuddenChangeError_PGN65450());
					CAN1Comm.Set_BucketFullInError_PGN61184_62(CAN1Comm.Get_BucketFullInError_PGN65450());
					CAN1Comm.TxCANToMCU(62);
					CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
				}
					
				TimerIndex++;
			}else if(TimerIndex == 1){
				if(ParentActivity._MenuBaseFragment._OperationHistoryFragment.checkWorkTotalB.isChecked() == true){
					CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B);
					CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
					CAN1Comm.Set_SuddenChangeError_PGN61184_62(CAN1Comm.Get_SuddenChangeError_PGN65450());
					CAN1Comm.Set_BucketFullInError_PGN61184_62(CAN1Comm.Get_BucketFullInError_PGN65450());
					CAN1Comm.TxCANToMCU(62);
					CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
				}
				TimerIndex++;
			}else if (TimerIndex == 2){
				if(ParentActivity._MenuBaseFragment._OperationHistoryFragment.checkWorkTotalC.isChecked() == true){
					CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C);
					CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
					CAN1Comm.Set_SuddenChangeError_PGN61184_62(CAN1Comm.Get_SuddenChangeError_PGN65450());
					CAN1Comm.Set_BucketFullInError_PGN61184_62(CAN1Comm.Get_BucketFullInError_PGN65450());
					CAN1Comm.TxCANToMCU(62);
					CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
				}
				TimerIndex++;
			}else if (TimerIndex == 3){
				if(ParentActivity._MenuBaseFragment._OperationHistoryFragment.checkHourLatest.isChecked() == true){
					CAN1Comm.Set_RequestTripDataReset_PGN61184_109(CAN1CommManager.DATA_STATE_TRIPDATA_RESET_TIME);
					CAN1Comm.TxCANToMCU(109);
					CAN1Comm.Set_RequestTripDataReset_PGN61184_109(3);
				}
				
				TimerIndex++;
			}else if (TimerIndex == 4){
				if(ParentActivity._MenuBaseFragment._OperationHistoryFragment.checkHourLatest.isChecked() == true){
					CAN1Comm.Set_RequestTripDataReset_PGN61184_109(CAN1CommManager.DATA_STATE_TRIPDATA_RESET_DISTANCE);
					CAN1Comm.TxCANToMCU(109);
					CAN1Comm.Set_RequestTripDataReset_PGN61184_109(3);
				}
				TimerIndex++;
			}else{
				CancelPopupOffTimer();
			//	ClickCancel();
				HandleCancel.sendMessage(HandleCancel.obtainMessage(CursurIndex));
			}
			
		}
				
	}
	
	public void StartPopupOffTimer(){
		CancelPopupOffTimer();
		mPopupOffTimer = new Timer();
		mPopupOffTimer.schedule(new PopupOffTimerClass(),1,100);	
	}
	
	public void CancelPopupOffTimer(){
		if(mPopupOffTimer != null){
			mPopupOffTimer.cancel();
			mPopupOffTimer.purge();
			mPopupOffTimer = null;
		}
		
	}
	//////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case 2:
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
			ClickOK();
			break;
		case 2:
			ClickCancel();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			imgbtnOK.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			imgbtnOK.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
}
