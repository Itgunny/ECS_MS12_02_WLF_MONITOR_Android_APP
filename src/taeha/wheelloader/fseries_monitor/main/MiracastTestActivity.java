package taeha.wheelloader.fseries_monitor.main;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MiracastTestActivity extends Activity{
	private static final String TAG ="MiracastTestActivity";
	
	Button multiMedia;
	Button miraCast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.miracast_test_activity);
		
		multiMedia = (Button)findViewById(R.id.multimedia);
		miraCast = (Button)findViewById(R.id.miracast);
		CAN1Comm.getInstance();
		
		multiMedia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "MultiMedia Button Click");
				Intent intent;
				intent = getPackageManager().getLaunchIntentForPackage("com.mxtech.videoplayer.ad");
				if(intent != null){
					startActivity(intent);
					CAN1Comm.SetMiracastFlag(false);
				}
			}
		});
		
		miraCast.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "Miracast Button Click");
				Intent intent;
				intent = getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
				if(intent != null){
					startActivity(intent);
					CAN1Comm.SetMultimediaFlag(false);
				}
			}
		});
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CAN1CommManager
private CAN1CommManager CAN1Comm = null;	

@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	try {
		StartCommService();
		CAN1Comm.SetScreenTopFlag(true);
	} catch (RuntimeException e) {
	} 
}

@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	try {
		CAN1Comm.SetScreenTopFlag(false);

	} catch (NullPointerException e) {
		// TODO: handle exception
	}

}

//Communication//////////////////////////////////////
// Communication Service Start
private void StartCommService() {
	Intent intent = new Intent(MiracastTestActivity.this,CommService.class);
	// Local Service
	startService(intent);
	// Remote Service
	bindService(new Intent(CommService.class.getName()),serConn,Context.BIND_AUTO_CREATE);
}

// Communication Service Stop
private void stopCommService(){
	unbindService(serConn);
	if(stopService(new Intent(MiracastTestActivity.this,CommService.class))){
	}
	else{
	}
	try {
		CAN1Comm.unregisterCallback(mCallback);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

// Service Connection
private ServiceConnection serConn = new ServiceConnection() {

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		StartCommService();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		// TODO Auto-generated method stub
		boolean Success;
		CAN1Comm = CAN1CommManager.getInstance();

		try {
			Success = CAN1Comm.registerCallback(mCallback);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CAN1Comm.SetScreenTopFlag(true);

	}
};


// Service Callback
ICAN1CommManagerCallback mCallback = new ICAN1CommManagerCallback.Stub() {

	@Override
	public void KeyButtonCallBack(int Data) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void CallbackFunc(int Data) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void CIDCallBack() throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void ASCallBack() throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void StopCommServiceCallBack() throws RemoteException {
		// TODO Auto-generated method stub
		stopCommService();
	}

	@Override
	public void EEPRomCallBack(int Data) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void FlashCallBack(int Data) throws RemoteException {
		// TODO Auto-generated method stub
	}

};

@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	stopCommService();
}
}