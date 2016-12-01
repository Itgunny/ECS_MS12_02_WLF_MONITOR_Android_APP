package taeha.wheelloader.fseries_monitor.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.BufferType;

public class RadioButtonTextView extends RadioButton{
	static final String TAG = "RadioButtonTextView";
    boolean fit = false;
    boolean finish = false;
	float size2 = 0.0f;
    public RadioButtonTextView(Context context) {
        super(context);
        
    }

    public RadioButtonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioButtonTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFitTextToBox( Boolean fit ) {
        this.fit = fit;
    }

    protected void onDraw (Canvas canvas) {
    	
        if (fit) _shrinkToFit();
        if(finish)super.onDraw(canvas);

    }
    @Override
	public void setText(CharSequence text, BufferType type) {
		// TODO Auto-generated method stub
    	setFitTextToBox(true);
    	super.setText(text, type);
	}
    protected void _shrinkToFit() {
    	
        int height = this.getLineHeight();
        //Log.d(TAG, "height : " + this.getLineHeight());
        int lines = this.getLineCount();
        //Log.d(TAG, "lines : " + this.getLineCount());
        Rect r = new Rect();
        int y1 = this.getLineBounds(0, r);
        int y2 = this.getLineBounds(lines-1, r);
        Log.d(TAG, "y1 : " + y1 + "y2 : " + y2);
        float size = this.getTextSize();
        Log.d(TAG, "size : " + size);
        if (y2 > height && size >= 8.0f) {
            if(lines >= 2){
            	size2 = size - 1.0f;
            	this.setTextSize(size2);
            	//_shrinkToFit();
            }else if(lines == 1){
            	finish = true;
            }
        }else{
        	finish = true;
        }

    }
    public float getSize(int size){
    	return size2;
    }
}
