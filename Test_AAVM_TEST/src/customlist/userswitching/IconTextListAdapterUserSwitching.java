package customlist.userswitching;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import taeha.wheelloader.fseries_monitor.main.R;
public class IconTextListAdapterUserSwitching extends BaseAdapter {

	private Context mContext;
	int checkableId;

	private List<IconTextItemUserSwitching> mItems = new ArrayList<IconTextItemUserSwitching>();
	
	Animation animation;

	public IconTextListAdapterUserSwitching(Context context) {
		mContext = context;	
	}
	

	public void addItem(IconTextItemUserSwitching it) {
		mItems.add(it);
	}
	
	public void clearItem(){
		mItems.clear();
	}

	public void setListItems(List<IconTextItemUserSwitching> lit) {
		mItems = lit;
	}
	public int getCount() {
		return mItems.size();
	}

	public Object getItem(int position) {
		return mItems.get(position);
	}

	public boolean areAllItemsSelectable() {
		return false;
	}

	public boolean isSelectable(int position) {
		try {
			return mItems.get(position).isSelectable();
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		IconTextViewUserSwitching itemView;
		
		//convertView�� �ѹ��� ���������� ���ɻ����� ���� �ҽ� ~
		if (convertView == null) 
		{
			itemView = new IconTextViewUserSwitching(mContext, mItems.get(position));
		} 
		else 
		{
			itemView = (IconTextViewUserSwitching) convertView;
			
			itemView.setIcon(mItems.get(position).getIcon());
			itemView.setIcon2(mItems.get(position).getIcon2());
			itemView.setText(0, mItems.get(position).getData(0));
			itemView.setText(1, mItems.get(position).getData(1));
			itemView.setText(2, mItems.get(position).getData(2));	
			itemView.setBackground(mItems.get(position).getBackground());
			itemView.setFirstTextColor(mItems.get(position).getFirstTextColor());
		}

		return itemView;
	}
	
	public void UpdateFirst(int position, String str){
		mItems.get(position).setDataFirst(str);
	}
	
	public void UpdateSecond(int position,String str){
		mItems.get(position).setDataSecond(str);
	}
	
	public void UpdateThird(int position,String str){
		mItems.get(position).setDataThird(str);
	}
	
	public void UpdateIcon(int position, Drawable icon){
		mItems.get(position).setIcon(icon);
	}
	
	public void UpdateBackground(int position, Drawable background){
		mItems.get(position).setBackground(background);
	}
	
	public void UpdateFirstColor(int position, int color){
		mItems.get(position).setFirstTextColor(color);
	}
	
	public void UpdateLockIcon(int position, Drawable icon){
		mItems.get(position).setIcon2(icon);
	}
 
}




