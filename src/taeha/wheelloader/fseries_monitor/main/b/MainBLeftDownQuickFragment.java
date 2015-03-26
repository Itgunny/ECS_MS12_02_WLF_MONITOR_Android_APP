package taeha.wheelloader.fseries_monitor.main.b;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;

public class MainBLeftDownQuickFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutBG;
	
	ImageView imgViewIcon;
	
	TextView textViewTitle;
	
	ImageButton imgbtnMirror;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//ANIMATION///////////////////////////////////////

	///////////////////////////////////////////////////
	
	//TEST////////////////////////////////////////////
	
	//////////////////////////////////////////////////

	//Life Cycle Function/////////////////////////////
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		TAG = "MainBLeftDownQuickFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.leftdown_main_b_quick, null);
		InitResource();
		InitValuables();
		InitButtonListener();

		return mRoot;
	}

	////////////////////////////////////////////////
	
	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		LayoutBG = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_leftdown_main_b_quick);
		
		imgViewIcon = (ImageView)mRoot.findViewById(R.id.imageView_leftdown_main_b_quick_icon);

		textViewTitle = (TextView)mRoot.findViewById(R.id.textView_leftdown_main_b_quick_title);
		
		imgbtnMirror = (ImageButton)mRoot.findViewById(R.id.imageButton_leftdown_main_b_quick_mirror);
	}
	
	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();
		
	}
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		LayoutBG.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickHelp();
			}
		});
		imgbtnMirror.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickMirror();
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
	/////////////////////////////////////////////////////////////////////	
	
	public void ClickHelp(){
		Intent intent;
		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("org.ebookdroid");
		if(intent != null)
		{
			startActivity(intent);
			// ++, 150326 bwk
			CAN1Comm.SetMiracastFlag(false);
			CAN1Comm.SetMultimediaFlag(false);
			// --, 150326 bwk
			ParentActivity.StartAlwaysOntopService(); // ++, --, 150325 cjg
		}
	}
	public void ClickMirror(){
		// ++, 150320 cjg
//		ParentActivity.KillApps("com.mxtech.videoplayer.ad");
//		Intent intent;
//		intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.example.wfdsink");
//		if(intent != null)
//			startActivity(intent);
		if(ParentActivity.CheckRunningApp("com.mxtech.videoplayer.ad")){
			ParentActivity.OldScreenIndex = ParentActivity.SCREEN_STATE_MAIN_B_QUICK_TOP;
			ParentActivity._MultimediaClosePopup.show();
		}else{
			Intent intent;
			intent = ParentActivity.getPackageManager().getLaunchIntentForPackage("com.example.wfdsink");
			if(intent != null){
				// ++, 150323 bwk
				CAN1Comm.SetMultimediaFlag(false);
				CAN1Comm.SetMiracastFlag(true);
				// --, 150323 bwk				
				startActivity(intent);
				ParentActivity.StartAlwaysOntopService(); // ++, --, 150324 cjg
			}
		}
		// --, 150320 cjg
	}
	
	public void Clickable(boolean flag){
		LayoutBG.setClickable(flag);
		imgbtnMirror.setClickable(flag);
	}
}