// ++, 150320 cjg
package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.CommService;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;

public class MultimediaClosePopup extends ParentPopup {
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
	
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public MultimediaClosePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "MultimediaClosePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_multimedia_close, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		InitResource();
		InitButtonListener();
		InitValuable();
		super.show();

		if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_A)
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_QUICK_MULTICLOSE;
		else if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_B)
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_MULTICLOSE;

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
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_multimedia_close_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_multimedia_close_cancel);
	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_multimedia_close_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.CloseMulti), 142));

		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_multimedia_close_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_multimedia_close_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(R.string.Cancel), 16));
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
		this.dismiss();
		ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_QUICK_TOP;
		//++, 150715 cjg
		Runtime runtime = Runtime.getRuntime();
		Process process;
		try{
			String cmd = "am force-stop com.mxtech.videoplayer.ad";
			process = runtime.exec(cmd);
			Log.d(TAG, "am force-stop com.mxtech.videoplayer.ad");
		}catch(Exception e){
			e.fillInStackTrace();
		}
		//--, 150715 cjg
		CAN1Comm.SetMultimediaFlag(false);
		String service = Context.WIFI_SERVICE;
		final WifiManager wifi = (WifiManager)ParentActivity.getSystemService(service);
		if(wifi.isWifiEnabled()){
			wifi.setWifiEnabled(false);
			synchronized (wifi) {
				try {
					wifi.wait(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.powerone.wfd.sink");
		if(intent != null){
			// ++, 150323 bwk
			//CAN1Comm.SetMiracastFlag(true);
			// --, 150323 bwk
			ParentActivity.StartAlwaysOntopService();
			ParentActivity.startActivity(intent);
			if(CommService.pi != null){
				if(!CommService.pi.versionName.equals("1.0.5BF")){
					
					CAN1Comm.setRunningCheckMiracast(true);
					
				}					
			}
			ParentActivity.StartCheckSmartTerminalTimer();
			
			
		}
		
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
//--, 150320 cjg