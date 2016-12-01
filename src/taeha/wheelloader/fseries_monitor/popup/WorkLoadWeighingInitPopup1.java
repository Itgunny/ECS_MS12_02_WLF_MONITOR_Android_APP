package taeha.wheelloader.fseries_monitor.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;
import taeha.wheelloader.fseries_monitor.main.TextFitTextView;

public class WorkLoadWeighingInitPopup1 extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextFitTextView textViewTitle;
	TextFitTextView textViewCurrent;
	TextFitTextView textViewTotalA;
	TextFitTextView textViewTotalB;
	TextFitTextView textViewTotalC;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public WorkLoadWeighingInitPopup1(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "WorkLoadWeighingInitPopup1";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_workload_init, null);
		this.addContentView(mRoot,  new LayoutParams(448,288));
		this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		Log.d(TAG,"show");
		InitResource();
		InitButtonListener();
		InitValuable();
		
		if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT1;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD_WEIGHING_INIT1;
		else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD)
			ParentActivity.ScreenIndex = Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD_WEIGHING_INIT1;
			Log.d(TAG,"WorkLoadInit1 ScreenIndex : "+ Integer.toHexString(ParentActivity.ScreenIndex) +"WorkLoadInit1 OldScreenIndex : "+ Integer.toHexString(ParentActivity.OldScreenIndex));
		
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		if(ParentActivity.ScreenIndex != Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT2)
		{
			ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
			if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_TOP){
				try {
					ParentActivity._MenuBaseFragment._WorkLoadFragment.CursurDisplay(10);
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException dismiss");
				}		
			}else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_A_KEY_WORKLOAD){
				try {
					ParentActivity._MainABaseFragment._MainAKeyWorkLoadFragment.CursurDisplay(5);
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException dismiss");
				}
			}else if(ParentActivity.OldScreenIndex == Home.SCREEN_STATE_MAIN_B_KEY_WORKLOAD){
				try {
					ParentActivity._MainBBaseFragment._MainBKeyWorkLoadFragment.CursurDisplay(5);
				} catch (NullPointerException e) {
					// TODO: handle exception
					Log.e(TAG,"NullPointerException dismiss");
				}
			}
		}
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		CursurIndex = 1;
		CursurDisplay(CursurIndex);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewTitle = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_workload_init_title);
		textViewTitle.setText(getString(ParentActivity.getResources().getString(string.Initialization), 30));
		textViewCurrent = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_workload_init_current);
		textViewCurrent.setText(getString(ParentActivity.getResources().getString(string.Current), 263));
		textViewTotalA = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_workload_init_totala);
		textViewTotalA.setText(getString(ParentActivity.getResources().getString(string.Total_A), 174));
		textViewTotalB = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_workload_init_totalb);
		textViewTotalB.setText(getString(ParentActivity.getResources().getString(string.Total_B), 175));
		textViewTotalC = (TextFitTextView)mRoot.findViewById(R.id.textView_popup_workload_init_totalc);
		textViewTotalC.setText(getString(ParentActivity.getResources().getString(string.Total_C), 176));
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewCurrent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickCurrent();
			}
		});
		textViewTotalA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalA();
			}
		});
		textViewTotalB.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalB();
			}
		});
		textViewTotalC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickTotalC();
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
	public void ClickCurrent(){
		this.dismiss();
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(3);
		ParentActivity.showWorkLoadWeighingInit2();
	}	
	public void ClickTotalA(){
		this.dismiss();
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A);
		ParentActivity.showWorkLoadWeighingInit2();
	}	
	public void ClickTotalB(){
		this.dismiss();
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B);
		ParentActivity.showWorkLoadWeighingInit2();
	}	
	public void ClickTotalC(){
		this.dismiss();
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C);
		ParentActivity.showWorkLoadWeighingInit2();
	}	
	
	
	
	/////////////////////////////////////////////////////////////////////////////////
	public void KeyButtonClick(int key){
		switch (key) {
		case CAN1CommManager.LEFT:
			ClickLeft();
			break;
		case CAN1CommManager.RIGHT:
			ClickRight();
			break;
		case CAN1CommManager.ESC:
			ClickESC();
			break;
		case CAN1CommManager.ENTER:
			ClickEnter();
			break;
		default:
			break;
		}
	}
	
	public void ClickLeft(){
		switch (CursurIndex) {
		case 1:
			CursurIndex = 4;
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
		case 4:
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
			CursurIndex++;
			CursurDisplay(CursurIndex);
			break;
		case 4:
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
			ClickCurrent();
			break;
		case 2:
			ClickTotalA();
			break;
		case 3:
			ClickTotalB();
			break;
		case 4:
			ClickTotalC();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewCurrent.setPressed(true);
			textViewTotalA.setPressed(false);
			textViewTotalB.setPressed(false);
			textViewTotalC.setPressed(false);
			break;
		case 2:
			textViewCurrent.setPressed(false);
			textViewTotalA.setPressed(true);
			textViewTotalB.setPressed(false);
			textViewTotalC.setPressed(false);
			break;
		case 3:
			textViewCurrent.setPressed(false);
			textViewTotalA.setPressed(false);
			textViewTotalB.setPressed(true);
			textViewTotalC.setPressed(false);
			break;
		case 4:
			textViewCurrent.setPressed(false);
			textViewTotalA.setPressed(false);
			textViewTotalB.setPressed(false);
			textViewTotalC.setPressed(true);
			break;
		default:
			break;
		}
	}
}
