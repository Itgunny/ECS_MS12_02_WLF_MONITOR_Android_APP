package taeha.wheelloader.fseries_monitor.menu.management;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.menu.PasswordFragment;

public class MachineSecurityPasswordChangeFragment extends PasswordFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		 TAG = "MachineSecurityPasswordChangeFragment";
		Log.d(TAG, "onCreateView");		
		
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.setBackButtonEnable(true);
		ParentActivity.ScreenIndex = ParentActivity.SCREEN_STATE_MENU_MANAGEMENT_MACHINESECURITY_PWCHANGE;
		ParentActivity._MenuBaseFragment._MenuListTitleFragment.SetTitleText(ParentActivity.getResources().getString(R.string.Change_Password));
		return mRoot;
	}
	@Override
	public void ClickEnter(){
		showServicePasswordNextScreen();
	}
	
	@Override
	public void showServicePasswordNextScreen() {
		// TODO Auto-generated method stub
		ParentActivity._MenuBaseFragment.showBodyMachineSecurityList();
	}

	@Override
	public void showUserPasswordNextScreen() {
		// TODO Auto-generated method stub
		ParentActivity._MenuBaseFragment.showBodyMachineSecurityList();
	}

}


