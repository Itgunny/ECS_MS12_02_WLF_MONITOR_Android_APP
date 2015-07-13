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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.Home.SeatBeltTimerClass;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.popup.SpeedometerInitPopup.PopupOffTimerClass;

public class CoolingFanManualPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageView imgViewCoolingFanIcon;
	ImageView imgViewExcuteBoader;
	
	ImageButton imgbtnExcute;
	ImageButton imgbtnOK;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	boolean ManualPress;
	
	int ReverseFan;
	
	int CursurIndex = 1;
	
	Handler HandleCursurDisplay;	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public CoolingFanManualPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "FuelInitalPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_coolingfan_manual, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		HandleCursurDisplay = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				CursurDisplay(msg.what);
			}
		};
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_MANUAL;
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
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
		CAN1Comm.TxCANToMCU(61);
		CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ETC_COOLINGFAN_TOP;
		try {
			ParentActivity._MenuBaseFragment._CoolingFanReverseModeFragment.CursurDisplay(5);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgViewCoolingFanIcon = (ImageView)mRoot.findViewById(R.id.imageView_popup_coolingfan_manual);
		imgViewExcuteBoader = (ImageView)mRoot.findViewById(R.id.imageView_popup_coolingfan_manual_excute_boarder);
		
		imgbtnExcute = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_coolingfan_manual_excute);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_coolingfan_manual_ok);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CursurIndex = 2;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickOK();
			}
		});
		imgbtnExcute.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Log.d(TAG,"imgbtnExcute.isPressed()="+imgbtnExcute.isPressed());
//				Log.d(TAG,"CursurIndex="+CursurIndex);
				CursurIndex = 1;
				HandleCursurDisplay.sendMessage(HandleCursurDisplay.obtainMessage(CursurIndex));
				ClickExcute();
			}
		});
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		CheckCoolingFanManualButton();
		ReverseFan = CAN1Comm.Get_CoolingFandrivingStatus_180_PGN65428();
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		ReverseFanDisplay(ReverseFan);
		
	}
	/////////////////////////////////////////////////////////////////////
	public void ClickExcute(){
		CheckCoolingFanManualButton();
	}
	public void CheckCoolingFanManualButton(){
		if(imgbtnExcute.isPressed() == true){
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(1);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = true;
		}
		
		if(ManualPress == true && imgbtnExcute.isPressed() == false){
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(0);
			CAN1Comm.TxCANToMCU(61);
			CAN1Comm.Set_CoolingFanReverseManual_PGN61184_61(3);
			ManualPress = false;
		}
	}
	///////////////////////////////////////////////////////////////////////////////
	public void ReverseFanDisplay(int Data){
		switch (Data) {
		case CAN1CommManager.DATA_STATE_COOLINGFAN_OFF:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);

			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_FORWARD:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_gray_popup);

			break;
		case CAN1CommManager.DATA_STATE_COOLINGFAN_REVERSE:
			imgViewCoolingFanIcon.setImageResource(R.drawable.menu_mode_fan_manual_icon_green_popup);

			break;
		default:
			break;
		}
	}
	public void ClickOK(){
		this.dismiss();
	}	
	////////////////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		if(imgbtnExcute.isPressed() == true)
			return;
		switch(CursurIndex){
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
		if(imgbtnExcute.isPressed() == true)
			return;
		switch(CursurIndex){
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
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		switch(CursurIndex){
			case 1:
				CursurIndex = 3;
				CursurDisplay(CursurIndex);
				break;
			case 2:
				ClickOK();
				break;
			case 3:
				CursurIndex = 1;
				CursurDisplay(CursurIndex);
				break;
			default:
				break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(false);
		imgbtnExcute.setPressed(false);
		imgViewExcuteBoader.setVisibility(View.INVISIBLE);
		
		switch(CursurIndex){
			case 1:
				imgbtnExcute.setPressed(false);
				imgViewExcuteBoader.setVisibility(View.VISIBLE);
				break;
			case 2:
				imgbtnOK.setPressed(true);
				break;
			case 3:
				imgbtnExcute.setPressed(true);
				imgViewExcuteBoader.setVisibility(View.VISIBLE);
				break;
			default:
				break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
}
