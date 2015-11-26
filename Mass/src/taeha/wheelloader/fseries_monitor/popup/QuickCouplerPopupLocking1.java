package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
public class QuickCouplerPopupLocking1 extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	ImageButton imgbtnOK;
	ImageButton imgbtnCancel;
	
	TextView	textViewTitle;
	TextFitTextView 	textViewOK;
	TextFitTextView	textViewCancel;
	
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public QuickCouplerPopupLocking1(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "QuickCouplerPopupLocking1";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_key_quickcoupler_locking_1, null);
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
		
		// ++, 150314 bwk
		//ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING1;
		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER_POPUP_LOCKING1;
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER_POPUP_LOCKING1;
		}
		// --, 150314 bwk
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();

		// ++, 150314 bwk
		//ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		if(ParentActivity.DisplayType == ParentActivity.DISPLAY_TYPE_A){
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_QUICKCOUPLER;
		}else{
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_QUICKCOUPLER;
		}
		// --, 150314 bwk
		
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		
		
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_popup_key_quickcoupler_locking_1);
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_locking_1_ok);
		imgbtnCancel = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_key_quickcoupler_locking_1_cancel);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_key_quickcoupler_locking_1_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Excute_Locking_Attachment), 450));
		
		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_key_quickcoupler_locking_1_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(string.OK), 15));

		textViewCancel = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_key_quickcoupler_locking_1_cancel);
		textViewCancel.setText(getString(ParentActivity.getResources().getString(string.Cancel), 16));
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
		ParentActivity.showQuickCouplerPopupLocking2();
		this.dismiss();
	}	
	public void ClickCancel(){
		this.dismiss();
	}

}
