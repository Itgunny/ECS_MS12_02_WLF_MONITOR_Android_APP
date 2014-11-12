package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;

public class SoundOutputPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewInternal;
	TextView textViewExternal;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int SoundState;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public SoundOutputPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "SoundOutputPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_soundoutput, null);
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_SOUNDOUTPUT_TOP;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_PREFERENCE_TOP;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewInternal = (TextView)mRoot.findViewById(R.id.textView_popup_soundoutput_internal);
		textViewExternal = (TextView)mRoot.findViewById(R.id.textView_popup_soundoutput_aux);
		
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewInternal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickInternal();
			}
		});
		textViewExternal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickExternal();
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
	public void ClickInternal(){
		SoundState = Home.STATE_INTERNAL_SPK;
		ParentActivity.SoundState = SoundState;
		SavePref();
		try {
			CAN1Comm.LineOutfromJNI(SoundState);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG,"Load Library Error");
		}
		this.dismiss();
	}	
	public void ClickExternal(){
		SoundState = Home.STATE_EXTERNAL_AUX;
		ParentActivity.SoundState = SoundState;
		SavePref();
		try {
			CAN1Comm.LineOutfromJNI(SoundState);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException");
		}
		catch (Throwable t) {
			// TODO: handle exception
			Log.e(TAG,"Load Library Error");
		}
		this.dismiss();
	}	
	////////////////////////////////////////////////////////////////////////////////
	public void SavePref(){
		SharedPreferences SharePref = ParentActivity.getSharedPreferences("Home", 0);
		SharedPreferences.Editor edit = SharePref.edit();
		edit.putInt("SoundState", SoundState);
		edit.commit();
		Log.d(TAG,"SavePref");
	}
}
