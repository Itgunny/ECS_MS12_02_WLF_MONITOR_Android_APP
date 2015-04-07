package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;

public class FineModulationPouup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	ImageButton imgbtnOK;
	TextView textViewTitle;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	String strTitle;
	boolean bExitPage;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public FineModulationPouup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "FineModulationPouup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_anglecalibration_result, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		bExitPage = false;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		InitResource();
		InitButtonListener();
		InitValuable();
		
		if(ParentActivity.DisplayType == Home.DISPLAY_TYPE_A)
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_KEY_FINEMODULATION_POPUP;
		else
			ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_KEY_FINEMODULATION_POPUP;
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
		
		ParentActivity.setScreenIndex();		
	}	
	
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_anglecalibration_result_ok);
		
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_anglecalibration_result_title);
		// ++, 150407 bwk 문구 변경 요청
		//textViewTitle.setText("EH SYSTEM is NOT equiped.");
		textViewTitle.setText("This machine does not support this feature.");
		// --, 150407 bwk
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
	}

	@Override
	protected void GetDataFromNative() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void UpdateUI() {
		// TODO Auto-generated method stub
		
	}
	
	public void ClickOK(){
		this.dismiss();
	}		
	//////////////////////////////////////////////////////////////////////
	public void ClickLeft(){
		
	}
	public void ClickRight(){
		
	}
	public void ClickESC(){
		ClickOK();
	}
	public void ClickEnter(){
		ClickOK();
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		imgbtnOK.setPressed(true);
	}
}
