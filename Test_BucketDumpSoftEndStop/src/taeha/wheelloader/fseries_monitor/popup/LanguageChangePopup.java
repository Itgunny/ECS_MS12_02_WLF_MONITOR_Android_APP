
package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TextView;

public class LanguageChangePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////

	ImageButton imgbtnOK;
	TextFitTextView textViewOK;

	TextView textViewTitle;

	//////////////////////////////////////////////////

	//VALUABLE////////////////////////////////////////
	//////////////////////////////////////////////////

	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////

	//TEST////////////////////////////////////////////

	//////////////////////////////////////////////////
	public LanguageChangePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "LanguageChangePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_software_update_error, null);
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

		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_LANG_CHANGE_POPUP;
	}

	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
		textViewTitle.setTextSize(25);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Language_Warning), 429));
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_softwareupdate_error_ok);
		
		textViewOK	  = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_softwareupdate_error_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_softwareupdate_error_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.Language_Warning), 429));
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
	///////////////////////////////////////////////////////////////////////////////
	public void ClickOK(){
		if(ParentActivity.OldScreenIndex == ParentActivity.SCREEN_STATE_MENU_PREFERENCE_DISPLAYTYPELANG_TOP)
		{
			if(ParentActivity.AnimationRunningFlag == true)
				return;
			else
				ParentActivity.StartAnimationRunningTimer();
			
			ParentActivity._MenuBaseFragment.showDisplayTypeLangAnimation();
		}
		else
		{
			ParentActivity.showMainScreen();
			ParentActivity.OldScreenIndex = 0;			
		}		

		this.dismiss();
	}	
	////////////////////////////////////////////////////////////////////////////////
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

