package taeha.wheelloader.fseries_monitor.menu;

import taeha.wheelloader.fseries_monitor.animation.AppearAnimation;
import taeha.wheelloader.fseries_monitor.animation.ChangeFragmentAnimation;
import taeha.wheelloader.fseries_monitor.animation.DisappearAnimation;
import taeha.wheelloader.fseries_monitor.animation.MainBodyShiftAnimation;
import taeha.wheelloader.fseries_monitor.animation.LeftRightShiftAnimation;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class MenuBodyList_ParentFragment extends ParentFragment{
	//CONSTANT////////////////////////////////////////
	
	//////////////////////////////////////////////////
	//RESOURCE////////////////////////////////////////
	RelativeLayout LayoutList1;
	RelativeLayout LayoutList2;
	RelativeLayout LayoutList3;
	RelativeLayout LayoutList4;
	RelativeLayout LayoutList5;
	RelativeLayout LayoutList6;
	
	protected ImageButton[] imgbtnList;
//	ImageButton imgbtnList2;
//	ImageButton imgbtnList3;
//	ImageButton imgbtnList4;
//	ImageButton imgbtnList5;
//	ImageButton imgbtnList6;
	
	ImageView imgViewLine1;
	ImageView imgViewLine2;
	ImageView imgViewLine3;
	ImageView imgViewLine4;
	ImageView imgViewLine5;
	ImageView imgViewLine6;
	
	TextView textViewTitle1;
	TextView textViewTitle2;
	TextView textViewTitle3;
	TextView textViewTitle4;
	TextView textViewTitle5;
	TextView textViewTitle6;
	
	TextView textViewData1;
	TextView textViewData2;
	TextView textViewData3;
	TextView textViewData4;
	TextView textViewData5;
	TextView textViewData6;
	//////////////////////////////////////////////////
	
	//VALUABLE////////////////////////////////////////

	//////////////////////////////////////////////////
	
	//Fragment////////////////////////////////////////
	
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
		 TAG = "MenuBodyList_ParentFragment";
		Log.d(TAG, "onCreateView");
		mRoot = inflater.inflate(R.layout.menu_body_list, null);
		InitResource();
		InitValuables();
		InitButtonListener();
		
		setClickableList1(false);
		setClickableList2(false);
		setClickableList3(false);
		setClickableList4(false);
		setClickableList5(false);
		setClickableList6(false);
		
		return mRoot;
	}
	
	////////////////////////////////////////////////
	
	

	//Common Function//////////////////////////////
	@Override
	protected void InitResource() {
		// TODO Auto-generated method stub
		imgbtnList = new ImageButton[6];
		
		LayoutList1 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_list_1);
		LayoutList2 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_list_2);
		LayoutList3 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_list_3);
		LayoutList4 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_list_4);
		LayoutList5 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_list_5);
		LayoutList6 = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_menu_body_list_6);

		imgbtnList[0] = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_list_1);
		imgbtnList[1] = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_list_2);
		imgbtnList[2] = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_list_3);
		imgbtnList[3] = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_list_4);
		imgbtnList[4] = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_list_5);
		imgbtnList[5] = (ImageButton)mRoot.findViewById(R.id.imageButton_menu_body_list_6);
		

		imgViewLine1 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_list_line_1);
		imgViewLine2 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_list_line_2);
		imgViewLine3 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_list_line_3);
		imgViewLine4 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_list_line_4);
		imgViewLine5 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_list_line_5);
		imgViewLine6 = (ImageView)mRoot.findViewById(R.id.imageView_menu_body_list_line_6);
		
		textViewTitle1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_1_title);
		textViewTitle2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_2_title);
		textViewTitle3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_3_title);
		textViewTitle4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_4_title);
		textViewTitle5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_5_title);
		textViewTitle6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_6_title);
		
		textViewData1 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_1_data);
		textViewData2 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_2_data);
		textViewData3 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_3_data);
		textViewData4 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_4_data);
		textViewData5 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_5_data);
		textViewData6 = (TextView)mRoot.findViewById(R.id.textView_menu_body_list_6_data);
	}

	protected void InitValuables() {
		// TODO Auto-generated method stub
		super.InitValuables();

	}
	abstract protected void InitList();
	@Override
	protected void InitButtonListener() {
		// TODO Auto-generated method stub
		imgbtnList[0].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickList1();
			}
		});
		imgbtnList[1].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickList2();
			}
		});
		imgbtnList[2].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickList3();
			}
		});
		imgbtnList[3].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickList4();
			}
		});
		imgbtnList[4].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickList5();
			}
		});
		imgbtnList[5].setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ClickList6();
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
	abstract public void ClickList1();
	abstract public void ClickList2();
	abstract public void ClickList3();
	abstract public void ClickList4();
	abstract public void ClickList5();
	abstract public void ClickList6();

	/////////////////////////////////////////////////////////////////////
	public void setClickableList1(boolean flag){
		if(flag == true){
			LayoutList1.setVisibility(View.VISIBLE);
		}else{
			LayoutList1.setVisibility(View.GONE);
		}
	}
	public void setClickableList2(boolean flag){
		if(flag == true){
			LayoutList2.setVisibility(View.VISIBLE);
		}else{
			LayoutList2.setVisibility(View.GONE);
		}
	}
	public void setClickableList3(boolean flag){
		if(flag == true){
			LayoutList3.setVisibility(View.VISIBLE);
		}else{
			LayoutList3.setVisibility(View.GONE);
		}
	}
	public void setClickableList4(boolean flag){
		if(flag == true){
			LayoutList4.setVisibility(View.VISIBLE);
		}else{
			LayoutList4.setVisibility(View.GONE);
		}
	}
	public void setClickableList5(boolean flag){
		if(flag == true){
			LayoutList5.setVisibility(View.VISIBLE);
		}else{
			LayoutList5.setVisibility(View.GONE);
		}
	}
	public void setClickableList6(boolean flag){
		if(flag == true){
			LayoutList6.setVisibility(View.VISIBLE);
		}else{
			LayoutList6.setVisibility(View.GONE);
		}
	}
	public void setListTitle1(String str){
		textViewTitle1.setText(str);
	}
	public void setListTitle2(String str){
		textViewTitle2.setText(str);
	}
	public void setListTitle3(String str){
		textViewTitle3.setText(str);
	}
	public void setListTitle4(String str){
		textViewTitle4.setText(str);
	}
	public void setListTitle5(String str){
		textViewTitle5.setText(str);
	}
	public void setListTitle6(String str){
		textViewTitle6.setText(str);
	}
	public void setListData1(String str){
		textViewData1.setText(str);
	}
	public void setListData2(String str){
		textViewData2.setText(str);
	}
	public void setListData3(String str){
		textViewData3.setText(str);
	}
	public void setListData4(String str){
		textViewData4.setText(str);
	}
	public void setListData5(String str){
		textViewData5.setText(str);
	}
	public void setListData6(String str){
		textViewData6.setText(str);
	}
	public void setListColor(int Index, int Color){
		switch(Index){
		case 1:	textViewTitle1.setTextColor(Color); break;
		case 2:	textViewTitle2.setTextColor(Color); break;
		case 3:	textViewTitle3.setTextColor(Color); break;
		case 4:	textViewTitle4.setTextColor(Color); break;
		case 5:	textViewTitle5.setTextColor(Color); break;
		}
	}
	
	public void setListFocus(int Index){
		switch (Index) {
		case 0:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		case 1:
			imgbtnList[0].setBackgroundResource(R.drawable.menu_list_selected);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		case 2:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable.menu_list_selected);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		case 3:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable.menu_list_selected);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		case 4:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable.menu_list_selected);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		case 5:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable.menu_list_selected);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		case 6:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable.menu_list_selected);
			break;
		default:
			imgbtnList[0].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[1].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[2].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[3].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[4].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			imgbtnList[5].setBackgroundResource(R.drawable._selector_menu_body_list_btn);
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////
	
}
