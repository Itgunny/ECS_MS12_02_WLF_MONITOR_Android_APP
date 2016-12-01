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
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup.PopupOffTimerClass;

public class WorkLoadWeighingInitPopup2 extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView textViewTitle;
	TextFitTextView textViewOK;
	TextFitTextView textViewCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SelectMode;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public WorkLoadWeighingInitPopup2(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "WorkLoadWeighingInitPopup2";
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
		Log.d(TAG,"show");
		InitResource();
		InitButtonListener();
		InitValuable();
		
		setTitle(SelectMode);

		
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT2;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT2;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT2;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT1)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT2;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT1)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT2;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT1)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT2;
		
		
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
		Log.d(TAG,"dismiss");
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP){
			try {
				ParentActivity._MenuBaseFragment._WorkLoadFragment.CursurDisplay(10);
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"NullPointerException dismiss");
			}		
		}else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD
				|| ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT1){
			try {
				ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment.CursurDisplay(5);
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"NullPointerException dismiss");
			}
		}else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD
				|| ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT1){
			try {
				ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment.CursurDisplay(5);
			} catch (NullPointerException e) {
				// TODO: handle exception
				Log.e(TAG,"NullPointerException dismiss");
			}
		}			
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_loggedfault_delete_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_loggedfault_delete_cancel);
	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_loggedfault_delete_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialization), 30));
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_loggedfault_delete_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_loggedfault_delete_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Cancel), 16));
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
		switch (SelectMode) {
		case 0:
			CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(SelectMode);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
			break;
		case 1:
			CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(SelectMode);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
			break;
		case 2:
			CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(SelectMode);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestTotalWorkWeightReset_PGN61184_62(15);
			break;
		case 3:
			CAN1Comm.Set_RequestReweighing_PGN61184_62(1);
			CAN1Comm.TxCANToMCU(62);
			CAN1Comm.Set_RequestReweighing_PGN61184_62(3);
			break;
		default:
			break;
		}
		this.dismiss();
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
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "(" +getString(ParentActivity.getResources().getString(string.Total_A), 174) +")");
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "(" +getString(ParentActivity.getResources().getString(string.Total_B), 175) +")");
			break;
		case CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51) 
					+ "(" +getString(ParentActivity.getResources().getString(string.Total_C), 176) +")");
			break;
		case 3:
			textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialize_), 51)  
					+ "(" +getString(ParentActivity.getResources().getString(string.Current), 263) +")");
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(int key){
		switch (key) {
		case CAN1CommManager.LEFT:
			ClickLeft();
			break;
		case CAN1CommManager.RIGHT:
			ClickRight();
			break;
		case CAN1CommManager.ESC:
			ClickESC();
			break;
		case CAN1CommManager.ENTER:
			ClickEnter();
			break;
		default:
			break;
		}
	}
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
