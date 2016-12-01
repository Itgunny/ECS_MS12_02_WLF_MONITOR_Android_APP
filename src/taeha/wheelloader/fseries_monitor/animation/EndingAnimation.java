package taeha.wheelloader.fseries_monitor.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class EndingAnimation extends ImageView{
	final static private String TAG = "EndingAnimation";
	/**
	 *	BitmapFactory�� ����ϴ� Option��ü
	 */
	private final Options mOptions = new Options();
	/**
	 * res�� �ִ� �̹����� ������ �ִ� int array
	 */
	private int[] sourcesId;
	/**
	 * res�� ���� ������ Bitmap�� �����ϴ� BitmapArray, recycle�� ���
	 */
	private Bitmap[] bitmapArray;
	/**
	 * ���� ȭ�鿡 �ѷ��� bitmap;
	 */
	private Bitmap bitmap;
	/**
	 * BitmapArray�� index
	 */
	private int index;
	/**
	 * PurgeableImageView ������
	 * 
	 * @param context
	 */
	
	public EndingAnimation(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setFocusable(true);
		this.mOptions.inPurgeable = true;
		this.index = 0;
	}
	/**
	 * PurgeableImageView ������
	 * 
	 * @param context
	 * @param attrs
	 */
	public EndingAnimation(Context context, AttributeSet attrs){
		super(context, attrs);
		this.setFocusable(true);
		this.mOptions.inPurgeable = true;
		this.index = 0;
	}
	/**
	 * PurgeableImageView ������
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public EndingAnimation(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		this.setFocusable(true);
		this.mOptions.inPurgeable = true;
		this.index = 0;
	}
	/**
	 * 
	 * @param sourcesId
	 */
	public void setImage(Context context, RelativeLayout animlayoutView, int[] sourcesId){
		//id �޾ƿ´�.
		this.sourcesId = sourcesId;
		//bitmapArray�� ���� �����Ѵ�.
		this.bitmapArray = new Bitmap[this.sourcesId.length];
		//�Է¹��� id�� ���� Bitmap�� �����ϰ� ImageView�� �ѷ��ش�.
		update(animlayoutView);
		invalidate();

	}
	/**
	 * ImageView ������Ʈ�Ѵ�.
	 */
	public void update(RelativeLayout animlayoutView) {
		// TODO Auto-generated method stub
		try{
			// index�� �ּҰ� 0
			if(index < 0){
				index = 0;
			}
			// �迭�� ���̸� �ʰ��Ѱ��, ó������ ���ư���.
			else if(index >= bitmapArray.length){
				// ++, 150326 cjg
				//index = 0;
				return;
				// --, 150326 cjg
			}
			// BitmapFactory�� ���� bitmap�� �����Ѵ�.
			bitmapArray[index] = BitmapFactory.decodeResource(getResources(), sourcesId[index], mOptions);
			// ������ bitmap�� ȭ�鿡 �ѷ��ش�.
			bitmap = bitmapArray[index];
			BitmapDrawable bd = new BitmapDrawable(getResources(), bitmap);
			animlayoutView.setBackgroundDrawable(bd);
		} catch(OutOfMemoryError e){
			Log.e(TAG, e.getMessage());
			// �޸𸮰� ������ ��� BitmapArray�� recycle�Ѵ�.
			recycle();
		}Log.d("TAG",""+index);
		if(getIndex() < 49){
			index = index + 3;
		}
		else{
			index = index + 1;
		}
		
	}
	/**
	 * bitmapArray�� recycle�Ѵ�.
	 */
	private void recycle() {
		// TODO Auto-generated method stub
		for(int i = 0; i < bitmapArray.length; i++){
			bitmapArray[i].recycle();
		}
	}
	public int getIndex(){
		return index;
	}
}
