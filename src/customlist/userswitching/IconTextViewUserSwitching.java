package customlist.userswitching;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import taeha.wheelloader.fseries_monitor.main.R;
import taeha.wheelloader.fseries_monitor.main.R.drawable;
public class IconTextViewUserSwitching extends LinearLayout {

	/*
	 * Background
	 */
	private ImageView mBackground;

	/**
	 * Icon
	 */
	private ImageView mIcon;
	private ImageView mIcon2;
	/**
	 * TextView 01
	 */
	private TextView mTextFirst;
	
	/**
	 * TextView 02
	 */
	private TextView mTextSecond;
	
	/**
	 * TextView 03
	 */
	private TextView mTextThird;
	
	IconTextItemUserSwitching aItem;
	
	public IconTextViewUserSwitching(Context context, IconTextItemUserSwitching aItem) {
		super(context);

		// Layout Inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		View mRoot = inflater.inflate(R.layout.list_item_userswitching, this, true);
		//GlobalFont.setGlobalFont(context, mRoot);
		
		// Set Backgound
		mBackground = (ImageView)findViewById(R.id.imageView_list_background_userswitching);
		mBackground.setImageDrawable(aItem.getBackground());
		//mBackground.setImageResource(R.drawable._selector_menu_list_item);
		
		
		// Set Icon
		mIcon = (ImageView) findViewById(R.id.imageViewInfoIcon_userswitching);
		mIcon.setImageDrawable(aItem.getIcon());
		
		mIcon2 = (ImageView)findViewById(R.id.imageViewInfoIcon_userswitching_locking);
		mIcon2.setImageDrawable(aItem.getIcon2());
		
		
		// Set Text 01
		mTextFirst = (TextView) findViewById(R.id.textViewFirst_userswitching);
		mTextFirst.setText(aItem.getData(0));
		mTextFirst.setTextColor(aItem.getFirstTextColor());
	
		// Set Text 02
		mTextSecond = (TextView) findViewById(R.id.textViewSecond_userswitching);
		mTextSecond.setText(aItem.getData(1));
		
		
		// Set Text 03
		mTextThird = (TextView) findViewById(R.id.textViewThrid_userswitching);
		mTextThird.setText(aItem.getData(2));
		
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
		} else if (index == 2) {
			mTextThird.setText(data);
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
	
	public IconTextItemUserSwitching getIconTextItem()
	{
		return aItem;
	}
	

	/**
	 * set Icon
	 * 
	 * @param icon
	 */
	public void setIcon(Drawable icon) {
		mIcon.setImageDrawable(icon);
	}
	public void setIcon2(Drawable icon) {
		mIcon2.setImageDrawable(icon);
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
}
