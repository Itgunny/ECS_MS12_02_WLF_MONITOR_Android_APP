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

public class WorkLoadWeighingInitPopup1 extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewCurrent;
	TextView textViewTotalA;
	TextView textViewTotalB;
	TextView textViewTotalC;
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_HYD_WORKLOAD_WEIGHING_INIT1;
	}
	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		Log.d(TAG,"dismiss");
		ParentActivity.ScreenIndex = ParentActivity.OldScreenIndex;
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
		textViewCurrent = (TextView)mRoot.findViewById(R.id.textView_popup_workload_init_current);
		textViewTotalA = (TextView)mRoot.findViewById(R.id.textView_popup_workload_init_totala);
		textViewTotalB = (TextView)mRoot.findViewById(R.id.textView_popup_workload_init_totalb);
		textViewTotalC = (TextView)mRoot.findViewById(R.id.textView_popup_workload_init_totalc);
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
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(3);
		ParentActivity.showWorkLoadWeighingInit2();
		this.dismiss();
	}	
	public void ClickTotalA(){
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_A);
		ParentActivity.showWorkLoadWeighingInit2();
		this.dismiss();
	}	
	public void ClickTotalB(){
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_B);
		ParentActivity.showWorkLoadWeighingInit2();
		this.dismiss();
	}	
	public void ClickTotalC(){
		ParentActivity._WorkLoadWeighingInitPopup2.setMode(CAN1CommManager.DATA_STATE_WEIGHINGDISPLAY_TOTAL_C);
		ParentActivity.showWorkLoadWeighingInit2();
		this.dismiss();
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
