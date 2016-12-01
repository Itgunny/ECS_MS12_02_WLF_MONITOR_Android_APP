package taeha.wheelloader.fseries_monitor.popup;

import taeha.wheelloader.fseries_monitor.main.R;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WeighingErrorToast extends Toast{
	Context mContext;

	public WeighingErrorToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	public void showToast(String Body, int duration){
		LayoutInflater inflater;       
		View v;       
		if(false){            
			Activity act = (Activity)mContext;            
			inflater = act.getLayoutInflater();            
			v = inflater.inflate(R.layout.popup_weighing_error, null);        
			}else{  // same            
				inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);            
				v = inflater.inflate(R.layout.popup_weighing_error, null);        
			}        
			TextView text = (TextView) v.findViewById(R.id.textView_popup_weighing_error_title);        
			text.setText(Body);         
			show(this,v,duration);
		
	}

	private void show(Toast toast, View v, int duration){        
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);        
		toast.setDuration(duration);        
		toast.setView(v);        
		toast.show();    
	}

}
