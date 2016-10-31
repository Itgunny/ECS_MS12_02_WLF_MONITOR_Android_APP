package customlist.userswitching.lock;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
public class IconTextListAdapterUserSwitchingLock extends BaseAdapter {

	private Context mContext;
	int checkableId;

	private List<IconTextItemUserSwitchingLock> mItems = new ArrayList<IconTextItemUserSwitchingLock>();
	
	Animation animation;
	
	IconTextViewUserSwitchingLock itemView;
	
	public IconTextListAdapterUserSwitchingLock(Context context) {
		mContext = context;	
	}
	

	public void addItem(IconTextItemUserSwitchingLock it) {
		mItems.add(it);
	}
	
	public void clearItem(){
		mItems.clear();
	}

	public void setListItems(List<IconTextItemUserSwitchingLock> lit) {
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
	
	public View getView(final int position, View convertView, ViewGroup parent) {
	

		//convertView가 한번만 생성됨으로 성능상으로 좋은 소스 ~
		if (convertView == null) 
		{
			itemView = new IconTextViewUserSwitchingLock(mContext, mItems.get(position));
			itemView.mCheckBox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CheckBox cb = (CheckBox) v;
					mItems.get(position).setSelected(cb.isChecked());
					Log.d("TAG", "position : " + position + "selected : " + mItems.get(position).isSelected()) ;
				}
			});
		} 
		else 
		{
			itemView = (IconTextViewUserSwitchingLock) convertView;
			itemView.setText(0, mItems.get(position).getData(0));
			itemView.setText(1, mItems.get(position).getData(1));
			itemView.setBackground(mItems.get(position).getBackground());
			itemView.setFirstTextColor(mItems.get(position).getFirstTextColor());
			itemView.mCheckBox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CheckBox cb = (CheckBox) v;
					mItems.get(position).setSelected(cb.isChecked());
					Log.d("TAG", "position : " + position + "selected : " + mItems.get(position).isSelected()) ;
				}
			});
			itemView.setChecked(mItems.get(position).isSelected());
			//itemView.mCheckBox.setChecked(mItems.get(position).isSelected());
		}
		
		return itemView;
	}
	
	public void UpdateFirst(int position, String str){
		mItems.get(position).setDataFirst(str);
	}
	
	public void UpdateSecond(int position,String str){
		mItems.get(position).setDataSecond(str);
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
	
	public boolean isChecked(int position){
		return mItems.get(position).isSelected();
	}
	
	public void UpdateCheckBox(int position, boolean flag){
		mItems.get(position).setSelected(flag);
		
	}
	
	
}




