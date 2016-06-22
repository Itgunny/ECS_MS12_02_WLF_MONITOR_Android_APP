package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class FanSelectModePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewMode1;
	TextFitTextView textViewMode2;
	TextFitTextView textViewMode3;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int KickDown;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public FanSelectModePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "FanSelectModePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_fan_adjust, null);
		this.addContentView(mRoot,  new LayoutParams(448,348));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		ParentActivity.OldScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN;
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_POPUP;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}	
	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_fanmode_title);
		textViewTitle.setText("Select Mode");
		textViewMode1 = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_fanmode_1);
		textViewMode1.setText("Adjust Mode");
		textViewMode2 = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_fanmode_2);
		textViewMode2.setText("Fan Speed Max Mode");
		textViewMode3 = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_fanmode_3);
		textViewMode3.setText("Fan Reverse Mode");
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewMode1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMode1();
			}
		});
		textViewMode2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMode2();
			}
		});
		textViewMode3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMode3();
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
	public void ClickMode1(){
		PopupClose(1);
	}	
	public void ClickMode2(){
		PopupClose(2);
	}
	public void ClickMode3(){
		PopupClose(3);
	}
	public void PopupClose(int index){
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_SENSORMONITORING_HIDDEN;
		try {
			ParentActivity._MenuBaseFragment._ServiceMenuSensorMonitoringHiddenFragment.SetSelectMode(index);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
		this.dismiss();
	}		
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
		case 3:
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
		case 2:
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 3:
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
		switch (CursurIndex) {
		case 1:
			ClickMode1();
			break;
		case 2:
			ClickMode2();
			break;
		case 3:
			ClickMode3();
			break;
		default:
			
			break;
		}
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickMode1();
			break;
		case 2:
			ClickMode2();
			break;
		case 3:
			ClickMode3();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		textViewMode1.setPressed(false);
		textViewMode2.setPressed(false);
		textViewMode3.setPressed(false);
		switch (Index) {
		case 1:
			textViewMode1.setPressed(true);
			break;
		case 2:
			textViewMode2.setPressed(true);
			break;
		case 3:
			textViewMode3.setPressed(true);
			break;
		default:
			break;
		}
	}
}
