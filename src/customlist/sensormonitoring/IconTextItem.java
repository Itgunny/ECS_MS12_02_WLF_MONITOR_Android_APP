package customlist.sensormonitoring;

import android.R.drawable;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class IconTextItem {
	
	/**
	 * Backgraound
	 */
	private Drawable mBackground;
	
	/**
	 * Icon
	 */
	private Drawable mIcon;
	
	/**
	 * Data array
	 */
	private String[] mData;
	
	/**
	 *  First Text Color
	 */
	private int mFirstTextColor;

	/**
	 * True if this item is selectable
	 */
	private boolean mSelectable = true;

	/**
	 * Initialize with icon and data array
	 * 
	 * @param icon
	 * @param obj
	 */
	public IconTextItem(Drawable icon, String[] obj) {
		mIcon = icon;
		mData = obj;
	}

	/**
	 * Initialize with icon and strings
	 * 
	 * @param icon
	 * @param obj01
	 * @param obj02
	 * @param obj03
	 */
	
	//일반 구간 
	public IconTextItem(Drawable background, Drawable icon, String obj01, String obj02, String obj03) {

		mFirstTextColor = Color.rgb(0xff, 0xff, 0xff);
		mBackground = background;
		mIcon = icon;
		
		mData = new String[3];
		mData[0] = obj01;
		mData[1] = obj02;
		mData[2] = obj03;
				
	}
	
	//일반 구간 
	public IconTextItem(Drawable icon, String obj01, String obj02, String obj03) {
		mIcon = icon;
		
		mData = new String[3];
		mData[0] = obj01;
		mData[1] = obj02;
		mData[2] = obj03;
				
	}
	public IconTextItem(String obj01, String obj02, String obj03) {

		
		mData = new String[3];
		mData[0] = obj01;
		mData[1] = obj02;
		mData[2] = obj03;
				
	}
	//조별 구간을 나누기 위한 생성자 조건
	public IconTextItem(String string) {
		// TODO Auto-generated constructor stub
		mIcon = null;
		
		mData = new String[3];
		mData[0] = null;
		mData[1] = string;
		mData[2] = null;
	}

	/**
	 * True if this item is selectable
	 */
	public boolean isSelectable() {
		return mSelectable;
	}

	/**
	 * Set selectable flag
	 */
	public void setSelectable(boolean selectable) {
		mSelectable = selectable;
	}

	/**
	 * Get data array
	 * 
	 * @return
	 */
	public String[] getData() {
		return mData;
	}

	/**
	 * Get data
	 */
	public String getData(int index) {
		if (mData == null || index >= mData.length) {
			return null;
		}
		
		return mData[index];
	}
	
	/**
	 * Set data array
	 * 
	 * @param obj
	 */
	public void setData(String[] obj) {
		mData = obj;
	}
	public void setDataFirst(String obj) {
		mData[0] = obj;
	}
	public void setDataSecond(String obj) {
		mData[1] = obj;
	}
	public void setDataThird(String obj) {
		mData[2] = obj;
	}
	/*
	 * Set Background
	 */
	
	public void setBackground(Drawable background){
		mBackground = background;
	}
	/*
	 * Get background
	 */
	public Drawable getBackground(){
		return mBackground;
	}
	
	/**
	 *  Set First Text Color
	 */
	public void setFirstTextColor(int color){
		mFirstTextColor = color;
	}
	
	/**
	 * Get First Text Color
	 */
	public int getFirstTextColor(){
		return mFirstTextColor;
	}

	/**
	 * Set icon
	 * 
	 * @param icon
	 */
	public void setIcon(Drawable icon) {
		mIcon = icon;
	}

	/**
	 * Get icon
	 * 
	 * @return
	 */
	public Drawable getIcon() {
		return mIcon;
	}
	

	/**
	 * Compare with the input object
	 * 
	 * @param other
	 * @return
	 */
	public int compareTo(IconTextItem other) {
		if (mData != null) {
			String[] otherData = other.getData();
			if (mData.length == otherData.length) {
				for (int i = 0; i < mData.length; i++) {
					if (!mData[i].equals(otherData[i])) {
						return -1;
					}
				}
			} else {
				return -1;
			}
		} else {
			throw new IllegalArgumentException();
		}
		
		return 0;
	}

}

