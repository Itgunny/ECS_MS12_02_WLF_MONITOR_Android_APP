package customlist.versiondetail;


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
public class IconTextViewVersion extends LinearLayout {

	/*
	 * Background
	 */
	private ImageView mBackground;

	/**
	 * Icon
	 */
	private ImageView mIcon;
	
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
	
	IconTextItemVersion aItem;
	
	public IconTextViewVersion(Context context, IconTextItemVersion aItem) {
		super(context);

		// Layout Inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
		View mRoot = inflater.inflate(R.layout.list_item_version, this, true);
		//GlobalFont.setGlobalFont(context, mRoot);
		
		// Set Backgound
		mBackground = (ImageView)findViewById(R.id.imageView_list_background_version);
		mBackground.setImageDrawable(aItem.getBackground());
		//mBackground.setImageResource(R.drawable._selector_menu_list_item);
		
		
		// Set Icon
		mIcon = (ImageView) findViewById(R.id.imageViewInfoIcon_version);
		mIcon.setImageDrawable(aItem.getIcon());
		
		
		// Set Text 01
		mTextFirst = (TextView) findViewById(R.id.textViewFirst_version);
		mTextFirst.setText(aItem.getData(0));
		mTextFirst.setTextColor(aItem.getFirstTextColor());
	
		// Set Text 02
		mTextSecond = (TextView) findViewById(R.id.textViewSecond_version);
		mTextSecond.setText(aItem.getData(1));
		
		
		// Set Text 03
		mTextThird = (TextView) findViewById(R.id.textViewThrid_version);
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
	
	public IconTextItemVersion getIconTextItem()
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
