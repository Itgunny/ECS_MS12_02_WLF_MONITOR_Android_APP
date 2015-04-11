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
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.Home;
import taeha.wheelloader.fseries_monitor.main.ParentPopup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.string;

public class KickDownPopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewMode1;
	TextView textViewMode2;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int KickDown;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public KickDownPopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "KickDownPopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_kickdown, null);
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
		
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_KICKDOWN;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MODE_ENGINETM_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		KickDown = CAN1Comm.Get_KickDownShiftMode_547_PGN65434();
		KickDownDisplay(KickDown);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewMode1 = (TextView)mRoot.findViewById(R.id.textView_popup_kickdown_mode1);
		textViewMode2 = (TextView)mRoot.findViewById(R.id.textView_popup_kickdown_mode2);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewMode1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMode1();
			}
		});
		textViewMode2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMode2();
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
	public void ClickMode1(){
		CAN1Comm.Set_KickDownShiftMode_547_PGN61184_104(CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickMode2(){
		CAN1Comm.Set_KickDownShiftMode_547_PGN61184_104(CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
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
		this.dismiss();
	}
	public void ClickEnter(){
		switch (CursurIndex) {
		case 1:
			ClickMode1();
			break;
		case 2:
			ClickMode2();
			break;
		default:
			
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewMode1.setPressed(true);
			textViewMode2.setPressed(false);
			break;
		case 2:
			textViewMode1.setPressed(false);
			textViewMode2.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void KickDownDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_KICKDOWN_UPDOWN:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_KICKDOWN_DOWNONLY:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;

		default:
			break;
		}
	}
}
