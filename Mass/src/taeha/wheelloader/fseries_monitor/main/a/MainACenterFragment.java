package taeha.wheelloader.fseries_monitor.main.a;


import java.util.Timer;
import java.util.TimerTask;

import taeha.wheelloader.fseries_monitor.main.CAN1CommManager;
import taeha.wheelloader.fseries_monitor.main.ParentFragment;
import taeha.wheelloader.fseries_monitor.main.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainACenterFragment extends ParentFragment{
   //CONSTANT////////////////////////////////////////
   
   //////////////////////////////////////////////////
   //RESOURCE////////////////////////////////////////
   RelativeLayout Layoutcenter;
   TextView textViewRPMData;
   ImageButton imgbtnOption;
   ImageView imgVeiwrpmGuage;
   ImageView imgViewSmkIcon;      // ++, --, 150326 bwk
   ImageView imgViewOptionSelect;
   //////////////////////////////////////////////////
   
   //VALUABLE////////////////////////////////////////
   boolean ClickFlag;
   protected int RPM;
   
   int Maint;
   int FaultCode;
   
   ////// rpm Guage//////++, 151112cjg
   private float fDegree;
   private int nDegree;
   private int nRPM;
   private int nCurrentPosition;
   private int nEndPosition;
   private boolean InitFlag = false;
   
   ////// Animation Speed////
   final static private int PointSpeed = 15;
   //////////////////////////////////////////////////
   
   //ANIMATION///////////////////////////////////////
   //GaugerpmAnimation rpmAnimation;
   ///////////////////////////////////////////////////
   
   //TEST////////////////////////////////////////////
   
   //////////////////////////////////////////////////
   
   // Timer ++, 151112cjg
   private Timer viewrpmPointerTimer = null;
   //Life Cycle Function/////////////////////////////
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
      // TODO Auto-generated method stub
      TAG = "MainACenterFragment";
      Log.d(TAG, "onCreateView");
      mRoot = inflater.inflate(R.layout.center_main_a, null);
      InitResource();
      InitValuables();
      InitButtonListener();
      ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
      
      if(ParentActivity.rpmRunningFlag == true)
      {
         Log.d(TAG, "rpmRunningFlagtrue");
         nCurrentPosition = 0;
         ParentActivity.rpmRunningFlag = false;
      }
      else
      {
         Log.d(TAG, "rpmRunningFlag : false");
         nCurrentPosition = GetDegree();
         Log.d(TAG,"" + GetDegree());
      }
      StartPointerTimer();
      return mRoot;
   }
   @Override
   public void onResume() {
      // TODO Auto-generated method stub
      super.onResume();
      Log.d(TAG,"onResume");
      ClickFlag = true;
      setClickEnable(ClickFlag);
//      ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MAIN_A_TOP;
      ParentActivity.CheckAttachmentUnlock();
      
      if(ParentActivity.AxleWarningFlag != true)
      {
         Log.d(TAG, "AxleInit ");
         ParentActivity.FrontAxleWarningFlag = false;
         ParentActivity.RearAxleWarningFlag = false;
      }   
      
   }

   @Override
   public void onPause() {
      // TODO Auto-generated method stub
      super.onPause();
      Log.d(TAG, "onPause");
      CancelviewrpmPointerTimer();
   }
   ////////////////////////////////////////////////

   //Common Function//////////////////////////////
   @Override
   protected void InitResource() {
      // TODO Auto-generated method stub
      Layoutcenter = (RelativeLayout)mRoot.findViewById(R.id.RelativeLayout_center_main_a_bg);
      imgVeiwrpmGuage = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_gauge);
      
      textViewRPMData = (TextView)mRoot.findViewById(R.id.textView_center_main_a_rpm);
      //textViewRPMDataUnit = (TextView)mRoot.findViewById(R.id.textView_center_main_a_rpm);
      //textViewRPMDataUnit.setText(getString(ParentActivity.getResources().getString(string.rpm), 34));
      
      imgbtnOption = (ImageButton)mRoot.findViewById(R.id.imageButton_center_main_a_option);
      
      imgViewSmkIcon = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_smkicon);   // ++, --, 150326 bwk
      imgViewOptionSelect = (ImageView)mRoot.findViewById(R.id.imageView_center_main_a_option_select);
      
      Layoutcenter.setClickable(false);
   }
   
   protected void InitValuables() {
      // TODO Auto-generated method stub
      super.InitValuables();
      RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
      Setrpm(RPM);
      CursurDisplayDetail(ParentActivity._MainABaseFragment.CursurIndex);
   }
   
   boolean Temp = false;
   @Override
   protected void InitButtonListener() {
      // TODO Auto-generated method stub
      Layoutcenter.setOnTouchListener(new View.OnTouchListener() {
         
         @Override
         public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            return true;
         }
      });
      
      imgbtnOption.setOnClickListener(new View.OnClickListener() {
         
         @Override
         public void onClick(View v) {
            // TODO Auto-generated method stub
            if(ClickFlag == true)
               ClickBG();
            
         
         }
      });
   }

   @Override
   protected void GetDataFromNative() {
      // TODO Auto-generated method stub
      RPM = CAN1Comm.Get_EngineSpeed_310_PGN65431();
      Maint = CAN1Comm.Get_MaintenanceAlarmLamp_1099_PGN65428();
      FaultCode = CAN1Comm.Get_DTCAlarmLamp_1509_PGN65427();
   }

   @Override
   protected void UpdateUI() {
      // TODO Auto-generated method stub
      RPMDisplay(RPM);
      IconDisplay(FaultCode,Maint);
      SmkIconDisplay(ParentActivity.SmartIconDisplay);   // ++, --, 150326 bwk
   }
   /////////////////////////////////////////////////////////////////////
   public void RPMDisplay(int Data){
      if(Data == 0xFFFF)
         Data = 0;
      else if(Data > 9999)
         Data = 9999;
      try {
         textViewRPMData.setText(Integer.toString(Data));
         Setrpm(Data);
         imgVeiwrpmGuage.setPivotX(20);
         imgVeiwrpmGuage.setPivotY(120);
         imgVeiwrpmGuage.setRotation(nCurrentPosition);
      } catch (IllegalStateException e) {
         // TODO: handle exception
         Log.e(TAG,"IllegalStateException");
      }
   }
   
   public void ClickBG(){
      if(ParentActivity.AnimationRunningFlag == true)
         return;
      else
         ParentActivity.StartAnimationRunningTimer();
      ParentActivity._MainABaseFragment.showQuickScreenAnimation();
      Log.d(TAG,"ClickOption");
   }
   
   public void setClickEnable(boolean flag){
      ClickFlag = flag;
      imgbtnOption.setClickable(ClickFlag);
   }
   //////////////////////////////////////////////////////////////////////
   public void IconDisplay(int _fault, int _maint){
      if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON && _maint == CAN1CommManager.DATA_STATE_LAMP_ON){
         imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_faultmaint_btn);
      }else if(_fault == CAN1CommManager.DATA_STATE_LAMP_ON){
         imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_fault_btn);
      }else if(_maint == CAN1CommManager.DATA_STATE_LAMP_ON){
         imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_maint_btn);
      }else{
         imgbtnOption.setBackgroundResource(R.drawable._selector_center_main_a_quick_btn);
      }
   }
   //////////////////////////////////////////////////////////////////////
   // ++, 150326 bwk
   public void SmkIconDisplay(boolean flag){
      try{
      if(flag == false)
         imgViewSmkIcon.setVisibility(View.INVISIBLE);
      else
         imgViewSmkIcon.setVisibility(View.VISIBLE);
      } catch (NullPointerException e) {
         // TODO: handle exception
         Log.e(TAG,"NullPointerException");
      }
   }
   // --, 150326 bwk
   public void CursurDisplayDetail(int index){
      if(index == 4)
         imgViewOptionSelect.setVisibility(View.VISIBLE);
      else
         imgViewOptionSelect.setVisibility(View.GONE);
   }
   
   public void StartPointerTimer(){
      CancelviewrpmPointerTimer();
      Log.d(TAG,"rpm Animation »ý¼º");
      viewrpmPointerTimer = new Timer();
      viewrpmPointerTimer.schedule(new viewrpmPointerTimerClass(),1,PointSpeed);   
   }

   public void CancelviewrpmPointerTimer(){
      if(viewrpmPointerTimer != null){
         viewrpmPointerTimer.cancel();
         viewrpmPointerTimer.purge();
         viewrpmPointerTimer = null;
      }
   }

   public class viewrpmPointerTimerClass extends TimerTask{
      @Override
      public void run() {      
         ParentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
               nEndPosition = GetDegree();
               if(nCurrentPosition > nEndPosition){      // Increase
                  nCurrentPosition -= 1;
                  imgVeiwrpmGuage.setPivotX(20);
                  imgVeiwrpmGuage.setPivotY(120);
                  imgVeiwrpmGuage.setRotation(nCurrentPosition);
               }else if(nCurrentPosition < nEndPosition){   // Decrease
                  nCurrentPosition += 1;
                  imgVeiwrpmGuage.setPivotX(20);
                  imgVeiwrpmGuage.setPivotY(120);
                  imgVeiwrpmGuage.setRotation(nCurrentPosition);
               }else{
                  if(InitFlag == false){
                     imgVeiwrpmGuage.setPivotX(20);
                     imgVeiwrpmGuage.setPivotY(120);
                     imgVeiwrpmGuage.setRotation(nCurrentPosition);
                     InitFlag = true;
                  }
               }

            }
         });
      }
   }
   public int GetDegree(){
      return nDegree;
   }
   public int Getrpm(){
      return nRPM;
   }
   public void Setrpm(int rpm){
      if(rpm == 0xFFFF)
         rpm = 0;
      else if(rpm > 9999)
         rpm = 9999;

      SetrpmGuage(rpm);
   }
   private float CalRPM(int rpm){
      float result;

      final int MAX = 4000;
      final int MIN = 0;

      if(rpm > MAX){
         rpm = MAX;
      }
      else if(rpm < MIN){
         rpm = MIN;
      }

      result = (rpm - 2000) / 15.99f;      //result = (rpm - 2000) / 16.66f;

      return result;

   }

   private void SetrpmGuage(int rpm){
      nRPM = rpm;
      rpm = rpm / 70;
      rpm = rpm * 70;
      fDegree = CalRPM(rpm);
      nDegree = (int)fDegree + 2;   //nDegree = (int)fDegree;
   }
}