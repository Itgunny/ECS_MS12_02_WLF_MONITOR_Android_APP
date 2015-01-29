package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class LoggedFaultDeletePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
//	private static final int REQ_ERR_MACHINE_ACTIVE		= 0;
	private static final int REQ_ERR_MACHINE_LOGGED		= 1;
	
//	private static final int REQ_ERR_ENGINE_ACTIVE		= 2;
	private static final int REQ_ERR_ENGINE_LOGGED		= 3;
	
//	private static final int REQ_ERR_TM_ACTIVE			= 4;
	private static final int REQ_ERR_TM_LOGGED			= 5;
	
//	private static final int REQ_ERR_EHCU_ACTIVE		= 6;
	private static final int REQ_ERR_EHCU_LOGGED		= 7;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView textViewTitle;
	TextView textViewOK;
	TextView textViewCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectMode;
	
	private Timer mPopupOffTimer = null;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public LoggedFaultDeletePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "LoggedFaultDeletePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_loggedfault_delete, null);
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
		
		setTitle(SelectMode);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_DELETE;
	}
	
	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MONITORING_FAULTHISTORY_LOGGED_TOP;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_loggedfault_delete_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_loggedfault_delete_cancel);
	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_loggedfault_delete_title);

		textViewOK = (TextView)mRoot.findViewById(R.id.textView_popup_loggedfault_delete_ok);
		textViewCancel = (TextView)mRoot.findViewById(R.id.textView_popup_loggedfault_delete_cancel);
		
		imgbtnOK.setVisibility(View.VISIBLE);
		imgbtnCancel.setVisibility(View.VISIBLE);
		textViewOK.setVisibility(View.VISIBLE);
		textViewCancel.setVisibility(View.VISIBLE);
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
		CAN1Comm.Set_DTCInformationRequest_1515_PGN61184_11(10);
		CAN1Comm.Set_DTCType_1510_PGN61184_11(SelectMode);
		CAN1Comm.TxCANToMCU(11);
		CAN1Comm.Set_DTCInformationRequest_1515_PGN61184_11(0xF);
		CAN1Comm.Set_DTCType_1510_PGN61184_11(0xF);
		
		StartPopupOffTimer();
		imgbtnOK.setVisibility(View.INVISIBLE);
		imgbtnCancel.setVisibility(View.INVISIBLE);
		textViewOK.setVisibility(View.INVISIBLE);
		textViewCancel.setVisibility(View.INVISIBLE);
		textViewTitle.setText(ParentActivity.getResources().getString(string.Waiting_for_initialization));
	}	
	public void ClickCancel(){
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////

	public void setMode(int Index){
		SelectMode = Index;
		
	}
	public void setTitle(int Index){
		switch (Index) {
		case REQ_ERR_MACHINE_LOGGED:
			textViewTitle.setText(ParentActivity.getResources().getString(string.Delete_Logged_Fault) 
					+ "(" +ParentActivity.getResources().getString(string.Machine) +")");
			break;
		case REQ_ERR_ENGINE_LOGGED:
			textViewTitle.setText(ParentActivity.getResources().getString(string.Delete_Logged_Fault) 
					+ "(" +ParentActivity.getResources().getString(string.Engine) +")");
			break;
		case REQ_ERR_TM_LOGGED:
			textViewTitle.setText(ParentActivity.getResources().getString(string.Delete_Logged_Fault) 
					+ "(" +ParentActivity.getResources().getString(string.Transmission) +")");
			break;
		case REQ_ERR_EHCU_LOGGED:
			textViewTitle.setText(ParentActivity.getResources().getString(string.Delete_Logged_Fault) 
					+ "(" +ParentActivity.getResources().getString(string.EHCU) +")");
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
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
	////////////////////////////////////////////////////////////////////////////////
	public class PopupOffTimerClass extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ParentActivity._MenuBaseFragment._FaultHistoryLoggedFragment.RequestErrorCode(SelectMode,1,1);
			ParentActivity._MenuBaseFragment._FaultHistoryLoggedFragment.SelectedMode = SelectMode;
			ClickCancel();
		}
				
	}
	
	public void StartPopupOffTimer(){
		CancelPopupOffTimer();
		mPopupOffTimer = new Timer();
		mPopupOffTimer.schedule(new PopupOffTimerClass(),1000);	
	}
	
	public void CancelPopupOffTimer(){
		if(mPopupOffTimer != null){
			mPopupOffTimer.cancel();
			mPopupOffTimer.purge();
			mPopupOffTimer = null;
		}
		
	}
}
