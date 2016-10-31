// ++, 150320 cjg
package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class AAVMWarningPopup extends ParentPopup {
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////


	ImageButton imgbtnOK;
	
	TextView textViewTitle;
	TextFitTextView textViewOK;
	
	CheckBox checkBoxLockPopup;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int lockAAVMPopup;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	
	public AAVMWarningPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "AAVMWarningPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_aavm_warning, null);
		this.addContentView(mRoot,  new LayoutParams(650,330));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		InitResource();
		InitButtonListener();
		InitValuable();
		super.show();

		ParentActivity.OldScreenIndex = ParentActivity.ScreenIndex;
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_AAVM_POPUP;
		
		Log.d(TAG,
				"KeyButtonClick : ScreenIndex"
						+ Integer.toHexString(ParentActivity.ScreenIndex));
		Log.d(TAG, "OldScreenIndex : " + ParentActivity.OldScreenIndex);

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
		try {
			ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
			Log.d(TAG, "" + ParentActivity.OldScreenIndex);
		} catch (NullPointerException e) {
			// TODO: handle exception
			Log.e(TAG,"NullPointerException dismiss");
		}
	}

	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnOK = (ImageButton)mRoot.findViewById(R.id.imageButton_popup_aavm_warning_ok);
	
		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_popup_aavm_warning_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(R.string.AAVM_Warning_Popup), 501));

		textViewOK = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_aavm_warning_ok);
		textViewOK.setText(getString(ParentActivity.getResources().getString(R.string.OK), 15));
		
		checkBoxLockPopup = (CheckBox)mRoot.findViewById(R.id.checkBox_popup_aavm_checkbox);
		checkBoxLockPopup.setText(getString(ParentActivity.getResources().getString(R.string.Do_not_show_again), 502));
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnOK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOK();
				CursurIndex = 1;
				
			}
		});
		checkBoxLockPopup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCheckBox();
				CursurIndex = 2;
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
		ParentActivity.ExcuteCamActivitybyKey();
		ParentActivity.LockAAVMWarningPopup = lockAAVMPopup;
		Log.d(TAG, "ParentActivity.LockAAVMWarningPopup" + ParentActivity.LockAAVMWarningPopup);
	}
	public void ClickCheckBox(){
		if(checkBoxLockPopup.isChecked() == true){
			lockAAVMPopup = ParentActivity.STATE_AAVM_POPUP_LOCK;
		}else {
			lockAAVMPopup = ParentActivity.STATE_AAVM_POPUP_UNLOCK;
		}
	}
	public void ClickCancel(){
		this.dismiss();
	}
	public void WarningCheckBoxDisplay(int _data){
		switch (_data) {
		case Home.STATE_AAVM_POPUP_LOCK:
			checkBoxLockPopup.setChecked(true);
			break;
		case Home.STATE_AAVM_POPUP_UNLOCK:
			checkBoxLockPopup.setChecked(false);
			break;
		default:
			break;
		}
		
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
			if(lockAAVMPopup == Home.STATE_AAVM_POPUP_LOCK)
				lockAAVMPopup = Home.STATE_AAVM_POPUP_UNLOCK;
			else
				lockAAVMPopup = Home.STATE_AAVM_POPUP_LOCK;
			WarningCheckBoxDisplay(lockAAVMPopup);
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
			checkBoxLockPopup.setPressed(false);
			break;
		case 2:
			imgbtnOK.setPressed(false);
			checkBoxLockPopup.setPressed(true);
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	
	public void KeyButtonClick(final int key){
		switch (key) {
		case CAN1CommManager.ESC:
			ClickESC();
			break;
		case CAN1CommManager.ENTER:
			ClickEnter();
			break;
		case CAN1CommManager.LEFT:
			ClickLeft();
			break;
		case CAN1CommManager.RIGHT:
			ClickRight();
			break;
		default:
			break;
		}
	}
}
//--, 150320 cjg