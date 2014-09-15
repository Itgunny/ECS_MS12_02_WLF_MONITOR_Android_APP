package taeha.wheelloader.fseries_monitor.main;

import java.lang.ref.WeakReference;

import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupLocking2;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking1;
import taeha.wheelloader.fseries_monitor.popup.QuickCouplerPopupUnlocking2;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class Home extends Activity {
	//CONSTANT//////////////////////////////////////////
	// TAG
	private static final String TAG = "Home";
	
	public static final int SCREEN_STATE_MAIN_B_TOP 									= 0x00000000;
	
	public static final int SCREEN_STATE_MAIN_B_RIGHTUP_TOP								= 0x01000000;
	public static final int SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_MODE						= 0x01100000;
	public static final int SCREEN_STATE_MAIN_B_RIGHTUP_ENGINE_WARMINGUP				= 0x01200000;
	
	public static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_TOP							= 0x02000000;
	public static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_CCOMODE						= 0x02100000;
	public static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_SHIFTMODE						= 0x02200000;
	public static final int SCREEN_STATE_MAIN_B_RIGHTDOWN_TCLOCKUP						= 0x02300000;
	
	
	public static final int SCREEN_STATE_MAIN_B_LEFTUP_TOP								= 0x03000000;
	public static final int SCREEN_STATE_MAIN_B_LEFTUP_MACHINESTATUS					= 0x03100000;
	
	public static final int SCREEN_STATE_MAIN_B_LEFTDOWN_TOP							= 0x04000000;
	public static final int SCREEN_STATE_MAIN_B_LEFTDOWN_HOURODOMETER					= 0x04100000;
	
	public static final int SCREEN_STATE_MAIN_B_QUICK_TOP								= 0x05000000;
	
	public static final int SCREEN_STATE_MAIN_B_KEY_TOP									= 0x06000000;
	public static final int SCREEN_STATE_MAIN_B_KEY_MAINLIGHT							= 0x06100000;
	public static final int SCREEN_STATE_MAIN_B_KEY_WORKLIGHT							= 0x06200000;
	public static final int SCREEN_STATE_MAIN_B_KEY_AUTOGREASE							= 0x06300000;
	public static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER						= 0x06400000;
	public static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING1			= 0x06410000;
	public static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2			= 0x06420000;
	public static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING1		= 0x06430000;
	public static final int SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_UNLOCKING2		= 0x06440000;
	public static final int SCREEN_STATE_MAIN_B_KEY_RIDECONTROL							= 0x06500000;
	public static final int SCREEN_STATE_MAIN_B_KEY_RIDECONTROL_SPEED					= 0x06510000;
	public static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD							= 0x06600000;
	public static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ACCUMULATION				= 0x06610000;
	public static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_DISPLAY					= 0x06620000;
	public static final int SCREEN_STATE_MAIN_B_KEY_WORKLOAD_ERRORDETECT				= 0x06630000;
	
	public static final int SCREEN_STATE_MAIN_B_KEY_BEACONLAMP							= 0x06700000;
	public static final int SCREEN_STATE_MAIN_B_KEY_REARWIPER							= 0x06800000;
	public static final int SCREEN_STATE_MAIN_B_KEY_MIRRORHEAT							= 0x06900000;
	public static final int SCREEN_STATE_MAIN_B_KEY_DETENT								= 0x06A00000;
	public static final int SCREEN_STATE_MAIN_B_KEY_FINEMODULATION						= 0x06B00000;
	public static final int SCREEN_STATE_MAIN_B_KEY_FN									= 0x06C00000;

	
	
	////////////////////////////////////////////////////
	
	//Resource//////////////////////////////////////////
	FrameLayout framelayoutMain;
	////////////////////////////////////////////////////
	//Valuable//////////////////////////////////////////
	public int ScreenIndex;
	
	// CAN1CommManager
	private static CAN1CommManager CAN1Comm = null;	
	
	// Thread
	private Thread threadRead = null;
	
	// Dialog
	Dialog HomeDialog;
	QuickCouplerPopupLocking1 _QuickCouplerPopupLocking1;
	QuickCouplerPopupUnlocking1 _QuickCouplerPopupUnlocking1;
	QuickCouplerPopupLocking2 _QuickCouplerPopupLocking2;
	QuickCouplerPopupUnlocking2 _QuickCouplerPopupUnlocking2;
	////////////////////////////////////////////////////
	
	//Fragment//////////////////////////////////////////
	public MainBBaseFragment _MainBBaseFragment;
	////////////////////////////////////////////////////

	//Lift Cycle Function///////////////////////////////
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		InitResource();
		InitFragment();
		InitPopup();
		InitValuable();
		InitAnimation();
		
		showMainBFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	
	/////////////////////////////////////////////////////


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		StartCommService();
		threadRead = new Thread(new ReadThread(this));
		try {
			//KeyButton = CAN1Comm.GetReqPopup();
			//Log.d(TAG,"Key : " + Integer.toString(KeyButton));
			//KeyButtonClick(KeyButton);
			CAN1Comm.SetScreenTopFlag(true);
		} catch (RuntimeException e) {
			Log.e(TAG,"CAN1Comm Instance Error");
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.v(TAG,"onPause");
		try {
			CAN1Comm.SetScreenTopFlag(false);
			
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		
	}
	//Initialization/////////////////////////////////////
	public void InitResource(){
		framelayoutMain = (FrameLayout) findViewById(R.id.FrameLayout_main);
	}
	public void InitValuable(){
		ScreenIndex = SCREEN_STATE_MAIN_B_TOP;
	}
	public void InitFragment(){
		_MainBBaseFragment = new MainBBaseFragment();
	}
	public void InitPopup(){
		HomeDialog = null;
		_QuickCouplerPopupLocking1 = new QuickCouplerPopupLocking1(this);
		_QuickCouplerPopupUnlocking1 = new QuickCouplerPopupUnlocking1(this);
		_QuickCouplerPopupLocking2 = new QuickCouplerPopupLocking2(this);
		_QuickCouplerPopupUnlocking2 = new QuickCouplerPopupUnlocking2(this);
		
	}
	public void InitAnimation(){
		
	}
	/////////////////////////////////////////////////////
	
	//Main Screen Fragment///////////////////////////////
	public void showMainBFragment(){
		android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.FrameLayout_main, _MainBBaseFragment);
		//transaction.addToBackStack("Main_Upper");
		transaction.commit();
	}
	/////////////////////////////////////////////////////
	//Communication//////////////////////////////////////
	// Communication Service Start
	private void StartCommService() {
		Log.v(TAG,"Start Comm Service");
		Intent intent = new Intent(Home.this,CommService.class);
		// Loacal Service
		startService(intent);
		// Remote Service
		bindService(new Intent(CommService.class.getName()),serConn,Context.BIND_AUTO_CREATE);
	}
	
	// Communication Service Stop
	private void stopCommService(){
		Log.v(TAG,"Stop Comm Service");
		unbindService(serConn);
		if(stopService(new Intent(Home.this,CommService.class))){
			Log.v(TAG,"stopService was successful");
		}
		else{
			Log.v(TAG,"stopService was unsuccessful");
		}
		try {
			CAN1Comm.unregisterCallback(mCallback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadRead.interrupt();		
	}
	
	// Service Connection
	private ServiceConnection serConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.v(TAG,"onServiceDisconnected() called");
			
			StartCommService();
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			boolean Success;
			Log.v(TAG,"onServiceConnected() called");
			CAN1Comm = CAN1CommManager.getInstance();

			try {
				Success = CAN1Comm.registerCallback(mCallback);
				Log.d(TAG,"CallBack Success : " + Boolean.toString(Success));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			threadRead.start();
			CAN1Comm.SetScreenTopFlag(true);
			
			CAN1Comm.TxCMDToMCU(CAN1CommManager.CMD_STARTCAN);
			
			
			
		}
	};
	
	
	// Service Callback
	ICAN1CommManagerCallback mCallback = new ICAN1CommManagerCallback.Stub() {
		
		@Override
		public void KeyButtonCallBack(int Data) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG,"KeyButton Callback : 0x" + Integer.toHexString(Data));
			if(CAN1Comm.GetScreenTopFlag() == true){
				KeyButtonClick(Data);
			}
				
		}
		
		@Override
		public void CallbackFunc(int Data) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG,"Callback");
		}

		@Override
		public void CIDCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "CIDCallBack");
			int ComponentCode;
			int ManufacturerCode;
			byte[] ComponentBasicInformation;
			
			ComponentBasicInformation = new byte[37];
			
			ComponentCode = CAN1Comm.Get_ComponentCode_1699_PGN65330_MONITOR();
			ManufacturerCode = CAN1Comm.Get_ManufacturerCode_1700_PGN65330_MONITOR();
			ComponentBasicInformation = CAN1Comm.Get_ComponentBasicInformation_1698_PGN65330_MONITOR();
			
		//	SaveCID(ComponentCode,ManufacturerCode,ComponentBasicInformation);
		}

		@Override
		public void ASCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "ASCallBack");
		//	SaveASPhoneNumber();
		}

		@Override
		public void StopCommServiceCallBack() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "StopCommServiceCallBack");
			stopCommService();
		}

	};
	// Thread Class
	public static class ReadThread implements Runnable {
		private WeakReference<Home> activityRef = null;
		public Message msg = null;
		public ReadThread(Home activity){
			this.activityRef = new WeakReference<Home>(activity);
			msg = new Message();
		}

		
		@Override
		public void run() {
			try{
				//while(!Thread.currentThread().isInterrupted())
				while(!activityRef.get().threadRead.currentThread().isInterrupted())
				{
				//	activityRef.get().SaveSatellitePhoneNum();
					activityRef.get().GetDataFromNative();
					activityRef.get().UpdateUI();
				//	activityRef.get().Test();
					Thread.sleep(200);
				}
			}
			catch(InterruptedException ie){
				Log.e(TAG,"InterruptedException");
			}		
			catch(RuntimeException ee){
				Log.e(TAG,"RuntimeException");
			}
		}
	
	}
	
	public void GetDataFromNative(){
		
		
	}
	public void UpdateUI() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			
			}
		});
	
	}
	
	/////////////////////////////////////////////////////
	
	//Key Button/////////////////////////////////////////
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		//Log.d(TAG,"dispatchKeyEvent");
		if(event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
			{
				Log.d(TAG,"KEYCODE_BACK");
			}
			if(event.getKeyCode() == KeyEvent.KEYCODE_MENU)
			{			
				Log.d(TAG,"KEYCODE_MENU");
			}
		}
		//return super.dispatchKeyEvent(event);
		return true;
	}
	
	public void KeyButtonClick(final int Data){
		Log.d(TAG,"KeyButtonClick");
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_MainBBaseFragment.KeyButtonClick(Data);
			}
		});
		
	}
	/////////////////////////////////////////////////////
	//Popup//////////////////////////////////////////////
	public void showQuickCouplerPopupLocking1(){
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupLocking1;
		HomeDialog.show();
	}
	public void showQuickCouplerPopupUnlocking1(){
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupUnlocking1;
		HomeDialog.show();
	}
	public void showQuickCouplerPopupLocking2(){
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupLocking2;
		HomeDialog.show();
	}
	public void showQuickCouplerPopupUnlocking2(){
		if(HomeDialog != null){
			HomeDialog.dismiss();
			HomeDialog = null;
		}
		
		HomeDialog = _QuickCouplerPopupUnlocking2;
		HomeDialog.show();
	}
	/////////////////////////////////////////////////////
}
