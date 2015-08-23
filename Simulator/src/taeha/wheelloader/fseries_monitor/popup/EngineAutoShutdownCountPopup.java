package taeha.wheelloader.fseries_monitor.popup;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.KeyEvent;
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

public class EngineAutoShutdownCountPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	private static final int STATE_COTROL_AUTO_CANCEL 	= 1;
	private static final int STATE_COTROL_DELAY_CANCEL 	= 2;
	private static final int STATE_COTROL_AUTO_SKIP 	= 9;
	private static final int STATE_COTROL_DELAY_SKIP 	= 10;
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnSkip;
	ImageButton imgbtnCancel;
	
	TextView textViewTime;

	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int nCount;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public EngineAutoShutdownCountPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "EngineAutoShutdownCountPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_engineautoshutdown_count, null);
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_ENGINEAUTOSHUTDOWNCOUNT_TOP;
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
	
		//return super.onTouchEvent(event);
		return false;
	
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		//return super.dispatchKeyEvent(event);
		return false;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 2;
		CursurDisplay(CursurIndex);
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
	//	Log.d(TAG, "ScreenIndex="+Integer.toHexString(ParentActivity.OldScreenIndex));
	//	ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnSkip = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_engineautoshutdown_count_skip);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_engineautoshutdown_count_cancel);
	
		textViewTime = (TextView)mRoot.findViewById(R.id.textView_popup_engineautoshutdown_count_time);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnSkip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickSkip();
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
		nCount = CAN1Comm.Get_RemainingTimeforAutomaticEngineShutdown_PGN61184_122();
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		CountDisplay(nCount);
	}
	///////////////////////////////////////////////////////////////////////////////
	public void ClickSkip(){
		CAN1Comm.Set_EngineShutdownCotrolByte_PGN61184_121(STATE_COTROL_AUTO_SKIP);
		CAN1Comm.TxCANToMCU(121);
		CAN1Comm.Set_EngineShutdownCotrolByte_PGN61184_121(15);
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
		this.dismiss();
	}	
	public void ClickCancel(){
		CAN1Comm.Set_AutomaticEngineShutdown_363_PGN61184_121(CAN1CommManager.DATA_STATE_AUTOSHUTDOWN_OFF);
		CAN1Comm.Set_EngineShutdownCotrolByte_PGN61184_121(STATE_COTROL_AUTO_CANCEL);
		CAN1Comm.TxCANToMCU(121);
		CAN1Comm.Set_EngineShutdownCotrolByte_PGN61184_121(15);
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
		this.dismiss();
	}
	////////////////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(int key){
		switch (key) {
		case CAN1CommManager.LEFT:
			ClickLeft();
			break;
		case CAN1CommManager.RIGHT:
			ClickRight();
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
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickSkip();
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
			imgbtnSkip.setPressed(true);
			imgbtnCancel.setPressed(false);
			break;
		case 2:
			imgbtnSkip.setPressed(false);
			imgbtnCancel.setPressed(true);
			break;
		default:
			break;
		}
	}	
	public void CountDisplay(int _data){
		textViewTime.setText(Integer.toString(_data) + ParentActivity.getResources().getString(string.Sec));
	}
	////////////////////////////////////////////////////////////////////////////////
}
