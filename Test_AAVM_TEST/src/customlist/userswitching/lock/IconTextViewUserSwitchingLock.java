package customlist.userswitching.lock;


import taeha.wheelloader.fseries_monitor.main.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
// View Holder
public class IconTextViewUserSwitchingLock extends LinearLayout {

	/*
	 * Background
	 */
	private ImageView mBackground;

	/**
	 * Icon
	 */
	public CheckBox mCheckBox;
	
	/**
	 * TextView 01
	 */
	private TextView mTextFirst;
	
	/**
	 * TextView 02
	 */
	private TextView mTextSecond;


	IconTextItemUserSwitchingLock aItem;
	
	public IconTextViewUserSwitchingLock(Context context, IconTextItemUserSwitchingLock aItem) {
		super(context);

		// Layout Inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		View mRoot = inflater.inflate(R.layout.list_item_userswitching_lock, this, true);
		//GlobalFont.setGlobalFont(context, mRoot);
		
		// Set Backgound
		mBackground = (ImageView)findViewById(R.id.imageView_list_background_userswitching_lock);
		mBackground.setImageDrawable(aItem.getBackground());
		//mBackground.setImageResource(R.drawable._selector_menu_list_item);
		
		
		// Set Icon
		mCheckBox = (CheckBox) findViewById(R.id.imageViewInfoIcon_userswitching_lock);
		mCheckBox.setChecked(aItem.isSelected());
			
		// Set Text 01
		mTextFirst = (TextView) findViewById(R.id.textViewFirst_userswitching_lock);
		mTextFirst.setText(aItem.getData(0));
		mTextFirst.setTextColor(aItem.getFirstTextColor());
	
		// Set Text 02
		mTextSecond = (TextView) findViewById(R.id.textViewSecond_userswitching_lock);
		mTextSecond.setText(aItem.getData(1));
		
				
		this.aItem = aItem;
	}

	/**
	 * set Text
	 * 
	 * @param index
	 * @param data
	 */
	public void setText(int index, String data) {
		if (index == 0) {
			mTextFirst.setText(data);
		} else if (index == 1) {
			mTextSecond.setText(data);
		} else {
			//throw new IllegalArgumentException();
		}
	}
	
	public TextView getFirst()
	{
		return mTextFirst;
	}
	
	public TextView getSecond()
	{
		return mTextSecond;
	}
	
	public IconTextItemUserSwitchingLock getIconTextItem()
	{
		return aItem;
	}

	/*
	 * set Backgound
	 */
	public void setBackground(Drawable background){
		mBackground.setImageDrawable(background);
	}
	
	/**
	 *  set Text Corlor(FirstText)
	 */
	public void setFirstTextColor(int color){
		mTextFirst.setTextColor(color);
	}
	
	
	public void setChecked(boolean flag){
		mCheckBox.setChecked(flag);
	}
}
