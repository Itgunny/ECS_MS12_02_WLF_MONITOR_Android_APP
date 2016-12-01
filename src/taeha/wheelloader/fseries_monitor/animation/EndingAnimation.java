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
	 *	BitmapFactory에 사용하는 Option객체
	 */
	private final Options mOptions = new Options();
	/**
	 * res에 있는 이미지를 가지고 있는 int array
	 */
	private int[] sourcesId;
	/**
	 * res를 통해 생성한 Bitmap을 보관하는 BitmapArray, recycle에 사용
	 */
	private Bitmap[] bitmapArray;
	/**
	 * 실제 화면에 뿌려질 bitmap;
	 */
	private Bitmap bitmap;
	/**
	 * BitmapArray의 index
	 */
	private int index;
	/**
	 * PurgeableImageView 생성자
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
	 * PurgeableImageView 생성자
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
	 * PurgeableImageView 생성자
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
		//id 받아온다.
		this.sourcesId = sourcesId;
		//bitmapArray를 새로 생성한다.
		this.bitmapArray = new Bitmap[this.sourcesId.length];
		//입력받은 id를 통해 Bitmap을 생성하고 ImageView에 뿌려준다.
		update(animlayoutView);
		invalidate();

	}
	/**
	 * ImageView 업데이트한다.
	 */
	public void update(RelativeLayout animlayoutView) {
		// TODO Auto-generated method stub
		try{
			// index의 최소값 0
			if(index < 0){
				index = 0;
			}
			// 배열의 길이를 초과한경우, 처음으로 돌아간다.
			else if(index >= bitmapArray.length){
				// ++, 150326 cjg
				//index = 0;
				return;
				// --, 150326 cjg
			}
			// BitmapFactory를 통해 bitmap을 생성한다.
			bitmapArray[index] = BitmapFactory.decodeResource(getResources(), sourcesId[index], mOptions);
			// 생성한 bitmap을 화면에 뿌려준다.
			bitmap = bitmapArray[index];
			BitmapDrawable bd = new BitmapDrawable(getResources(), bitmap);
			animlayoutView.setBackgroundDrawable(bd);
		} catch(OutOfMemoryError e){
			Log.e(TAG, e.getMessage());
			// 메모리가 부족한 경우 BitmapArray를 recycle한다.
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
	 * bitmapArray를 recycle한다.
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
