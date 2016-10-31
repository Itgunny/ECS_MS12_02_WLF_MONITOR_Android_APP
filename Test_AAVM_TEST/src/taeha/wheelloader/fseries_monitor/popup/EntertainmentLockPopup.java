package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.TextView;

public class EntertainmentLockPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewSmartTerminal;
	TextFitTextView textViewMultimedia;
	TextFitTextView textViewOK;
	
	
	CheckBox checkBoxSmartTerminal;
	CheckBox checkBoxMultimedia;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int LockSmartTerminal;
	int LockMultiMedia;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public EntertainmentLockPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "EntertainmentLockPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_entertainment_lock, null);
		this.addContentView(mRoot,  new LayoutParams(448,365));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_ENTERTAINMENT_LOCK_POPUP;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MANAGEMENT_ASPHONE_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		LockSmartTerminal = ParentActivity.LockSmartTerminal;
		LockMultiMedia = ParentActivity.LockMultiMedia;
		
		DisplayLockingSmartTerminal(LockSmartTerminal);
		DisplayLockingMultimedia(LockMultiMedia);
		
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_entertainment_lock_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Entertainment_limit), 503));
		
		textViewMultimedia = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_entertainment_lock_multimedia);
		textViewMultimedia.setText(getString(ParentActivity.getResources().getString(R.string.Multimedia), 199));
		
		checkBoxMultimedia = (CheckBox) mRoot.findViewById(R.id.checkBox_popup_entertainment_lock_multimedia);
		checkBoxMultimedia.setEnabled(false);
		
		textViewSmartTerminal = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_entertainment_lock_smartterminal);
		textViewSmartTerminal.setText(getString(ParentActivity.getResources().getString(R.string.Smart_Terminal), 431));
		
		checkBoxSmartTerminal = (CheckBox) mRoot.findViewById(R.id.checkBox_popup_entertainment_lock_smartterminal);
		checkBoxSmartTerminal.setEnabled(false);
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_entertainment_lock_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		


	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewSmartTerminal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ClickSmartTerminal();
			}
		});
		textViewMultimedia.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMultimeida();
			}
		});
		
		textViewOK.setOnClickListener(new View.OnClickListener() {
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
	///////////////////////////////////////////////////////////////////////////////
	public void ClickMultimeida(){
		if(checkBoxMultimedia.isChecked() == true){
			checkBoxMultimedia.setChecked(false);
			LockMultiMedia = Home.STATE_ENTERTAINMENT_MULTIMEDIA_UNLOCK;
		} else {
			checkBoxMultimedia.setChecked(true);
			LockMultiMedia = Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK;
		}
	}	
	
	public void ClickSmartTerminal(){
		if(checkBoxSmartTerminal.isChecked() == true){
			checkBoxSmartTerminal.setChecked(false);
			LockSmartTerminal = Home.STATE_ENTERTAINMENT_SMARTTERMINAL_UNLOCK;
		} else {
			checkBoxSmartTerminal.setChecked(true);
			LockSmartTerminal = Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK;
		}
	}	

	public void ClickOK(){
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyManagementAnimation();
		ParentActivity._MenuBaseFragment._MenuModeFragment.setFirstScreen(Home.SCREEN_STATE_MENU_MANAGEMENT_TOP);
		
		ParentActivity.LockMultiMedia = LockMultiMedia;
		ParentActivity.LockSmartTerminal = LockSmartTerminal;
		
		Log.d(TAG, "ParentActivity.LockMulti : " + ParentActivity.LockMultiMedia);
		Log.d(TAG, "ParentActivity.LockSmart : " + ParentActivity.LockSmartTerminal);
		
		if(LockMultiMedia == 1){
			
			Runtime runtime = Runtime.getRuntime();
			Process process;
			try{
				String cmd = "am force-stop com.mxtech.videoplayer.ad";
				process = runtime.exec(cmd);
				Log.d(TAG, "am force-stop com.mxtech.videoplayer.ad");
			}catch(Exception e){
				e.fillInStackTrace();
			}
			CAN1Comm.SetMultimediaFlag(false);
			CAN1Comm.CheckMultimedia();
			
		}
		if(LockSmartTerminal == 1){
			String service = Context.WIFI_SERVICE;
			final WifiManager wifi = (WifiManager)ParentActivity.getSystemService(service);
			wifi.setWifiEnabled(false);
			Runtime runtime = Runtime.getRuntime();
			Process process;
			try{
				String cmd = "am force-stop com.powerone.wfd.sink";
				process = runtime.exec(cmd);
				Log.d(TAG, "am force-stop com.powerone.wfd.sink");
			}catch(Exception e){
				e.fillInStackTrace();
			}
			//--, 150715 cjg
		}
		ParentActivity.SavePref();
		this.dismiss();
	}
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case 2:
			CursurIndex--;
			CursurDisplay(CursurIndex);
			break;
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
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
		this.dismiss();
	}
	
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickMultimeida();
			break;
		case 2:
			ClickSmartTerminal();
			break;
		case 3:
			ClickOK();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewMultimedia.setPressed(true);
			textViewSmartTerminal.setPressed(false);
			textViewOK.setPressed(false);
			break;
			
		case 2:
			textViewMultimedia.setPressed(false);
			textViewSmartTerminal.setPressed(true);
			textViewOK.setPressed(false);
			break;
			
		case 3:
			textViewSmartTerminal.setPressed(false);
			textViewMultimedia.setPressed(false);
			textViewOK.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void DisplayLockingSmartTerminal(int data){
		switch (data) {
		case Home.STATE_ENTERTAINMENT_SMARTTERMINAL_LOCK:
			checkBoxSmartTerminal.setChecked(true);
			break;
		case Home.STATE_ENTERTAINMENT_SMARTTERMINAL_UNLOCK:
			checkBoxSmartTerminal.setChecked(false);
			break;
		default:
			break;
		}
	}
	public void DisplayLockingMultimedia(int data){
		switch (data) {
		case Home.STATE_ENTERTAINMENT_MULTIMEDIA_LOCK:
			checkBoxMultimedia.setChecked(true);
			break;
		case Home.STATE_ENTERTAINMENT_MULTIMEDIA_UNLOCK:
			checkBoxMultimedia.setChecked(false);
			break;
		default:
			break;
		}
	}
}
