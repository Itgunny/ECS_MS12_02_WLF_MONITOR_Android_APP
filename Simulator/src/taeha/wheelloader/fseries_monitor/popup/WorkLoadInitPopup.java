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

public class WorkLoadInitPopup extends ParentPopup{
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


	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public WorkLoadInitPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "WorkLoadInitPopup";
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
		
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_INIT;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_INIT;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_INIT;
		
	}

	@Override
	public void InitValuable(){
		super.InitValuable();
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
		
		// ++, 150210 bwk
		/*
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP;
		try {
			ParentActivity._MenuBaseFragment._WorkLoadFragment.CursurDisplay(11);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
		*/
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
		try {
			if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP)
				ParentActivity._MenuBaseFragment._WorkLoadFragment.CursurDisplay(11);
			else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD)
				ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment.CursurDisplay(6);
			else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD)
				ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment.CursurDisplay(6);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
		// --, 150210 bwk		
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
		// ++, 150210 bwk
		//ParentActivity._MenuBaseFragment._WorkLoadFragment.SetDefault();
		if(ParentActivity.OldScreenIndex == ParentActivity.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP)
			ParentActivity._MenuBaseFragment._WorkLoadFragment.SetDefault();
		else if(ParentActivity.OldScreenIndex == ParentActivity.SCREEN_STATE_MAIN_B_KEY_WORKLOAD)
			ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment.SetDefault();
		// ++, 150314 bwk
		else if(ParentActivity.OldScreenIndex == ParentActivity.SCREEN_STATE_MAIN_A_KEY_WORKLOAD)
			ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment.SetDefault();
		// --, 150314 bwk
		// --, 150210 bwk
		this.dismiss();
	}	
	public void ClickCancel(){
		this.dismiss();
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
