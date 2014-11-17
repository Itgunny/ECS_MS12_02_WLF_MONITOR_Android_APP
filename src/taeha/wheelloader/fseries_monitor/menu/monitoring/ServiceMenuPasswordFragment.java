package taeha.wheelloader.fseries_monitor.menu.monitoring;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class ServiceMenuPasswordFragment extends PasswordFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "ServiceMenuPasswordFragment";
		Log.d(TAG, "onCreateView");		
		
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_SERVICE_PW;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Service_Menu));
		SetTextIndicatorTitle(2);
		return mRoot;
	}

	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		if(ParentActivity.AnimationRunningFlag == true)
			return;
		else
			ParentActivity.StartAnimationRunningTimer();
		ParentActivity._MenuBaseFragment.showBodyServiceMenuList();
	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		DataBufIndex = 0;
		PasswordIndicatorDisplay();
		
		SetTextIndicatorTitle(3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CancelPasswordCheckTimer();
		SetTextIndicatorTitle(2);
	}

}
