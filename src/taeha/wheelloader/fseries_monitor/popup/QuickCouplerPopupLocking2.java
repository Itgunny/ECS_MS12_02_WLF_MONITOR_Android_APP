package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;

public class QuickCouplerPopupLocking2 extends ParentPopup{
	//CONSTANT////////////////////////////////////////
	// TAG
	private static final String TAG = "QuickCouplerPopupLocking2";
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	ImageButton imgbtnCancel;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public QuickCouplerPopupLocking2(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_key_quickcoupler_locking_2, null);
		this.addContentView(mRoot,  new LayoutParams(500,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		InitResource();
		InitButtonListener();
		InitValuable();
		
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING2;
	}
	
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub

		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_popup_key_quickcoupler_locking_2);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_locking_2_cancel);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
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

	public void ClickCancel(){
		this.dismiss();
	}

}
