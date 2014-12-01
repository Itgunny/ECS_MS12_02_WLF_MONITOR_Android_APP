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

public class CCOModePopup extends ParentPopup{
	//CONSTANT////////////////////////////////////////

	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	TextView textViewOff;
	TextView textViewL;
	TextView textViewM;
	TextView textViewH;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////
	int CCOMode;
	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////
	public CCOModePopup(Context _context) {
		super(_context);
		// TODO Auto-generated constructor stub
		TAG = "CCOModePopup";
		ParentActivity = (Home)_context;
		inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mRoot = inflater.inflate(R.layout.popup_ccomode, null);
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
		
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_CCOMODE;
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		ParentActivity.ScreenIndex = Home.SCREEN_STATE_MENU_MODE_ENGINETM_TOP;
	}
	@Override
	public void InitValuable(){
		super.InitValuable();
		CCOMode = CAN1Comm.Get_ClutchCutoffMode_544_PGN65434();
		CCOModeDisplay(CCOMode);
	}
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		textViewOff = (TextView)mRoot.findViewById(R.id.textView_popup_ccomode_off);
		textViewL = (TextView)mRoot.findViewById(R.id.textView_popup_ccomode_L);
		textViewM = (TextView)mRoot.findViewById(R.id.textView_popup_ccomode_M);
		textViewH = (TextView)mRoot.findViewById(R.id.textView_popup_ccomode_H);
	}

	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		textViewOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickOff();
			}
		});
		textViewL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickL();
			}
		});
		textViewM.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickM();
			}
		});
		textViewH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickH();
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
	public void ClickOff(){
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickL(){
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickM(){
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
	}	
	public void ClickH(){
		CAN1Comm.Set_ClutchCutoffMode_544_PGN61184_104(CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H);
		CAN1Comm.TxCANToMCU(104);
		this.dismiss();
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
			ClickOff();
			break;
		case 2:
			ClickL();
			break;
		case 3:
			ClickM();
			break;
		case 4:
			ClickH();
			break;
		default:
			break;
		}
	}
	////////////////////////////////////////////////////////////////////////////////
	public void CursurDisplay(int Index){
		switch (Index) {
		case 1:
			textViewOff.setPressed(true);
			textViewL.setPressed(false);
			textViewM.setPressed(false);
			textViewH.setPressed(false);
			break;
		case 2:
			textViewOff.setPressed(false);
			textViewL.setPressed(true);
			textViewM.setPressed(false);
			textViewH.setPressed(false);
			break;
		case 3:
			textViewOff.setPressed(false);
			textViewL.setPressed(false);
			textViewM.setPressed(true);
			textViewH.setPressed(false);
			break;
		case 4:
			textViewOff.setPressed(false);
			textViewL.setPressed(false);
			textViewM.setPressed(false);
			textViewH.setPressed(true);
			break;
		default:
			break;
		}
	}
	public void CCOModeDisplay(int data){
		switch (data) {
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_OFF:
			CursurIndex = 1;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_L:
			CursurIndex = 2;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_M:
			CursurIndex = 3;
			CursurDisplay(CursurIndex);
			break;
		case CAN1CommManager.DATA_STATE_TM_CLUTCHCUTOFF_H:
			CursurIndex = 4;
			CursurDisplay(CursurIndex);
			break;
		default:
			break;
		}
	}
}
