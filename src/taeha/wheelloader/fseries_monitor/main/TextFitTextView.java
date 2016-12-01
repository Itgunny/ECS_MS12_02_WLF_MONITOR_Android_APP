package taeha.wheelloader.fseries_monitor.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TextFitTextView extends TextView{
	static final String TAG = "TextFitTextView";
    boolean fit = false;
    boolean finish = false;
	float orignSize;
	int count = 0;
	String oldText;
    public TextFitTextView(Context context) {
        super(context);
    }

    public TextFitTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextFitTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFitTextToBox( Boolean fit ) {
        this.fit = fit;
    }

    protected void onDraw (Canvas canvas) {
    	//super.onDraw(canvas);
    	
        if (fit){ 
        	_shrinkToFit();
        }
        if(finish)super.onDraw(canvas);       
    }

    @Override
	public void setText(CharSequence text, BufferType type) {
		// TODO Auto-generated method stub

    	super.setText(text, type);
		if(finish == true){
			if(!oldText.equals(this.getText().toString())){
				this.setTextSize(orignSize);
				finish = false;
				setFitTextToBox(true);
			}else{
				setFitTextToBox(true);	
			}
		}else{
			setFitTextToBox(true);	
		}
		
	}

	protected void _shrinkToFit() {
		
		int height = this.getLineHeight();
        int lines = this.getLineCount();
        Rect r = new Rect();
        int y1 = this.getLineBounds(0, r);
        int y2 = this.getLineBounds(lines-1, r);
        float size = this.getTextSize();
        if(count == 0){
        	orignSize = this.getTextSize();
        	count = 1; 
        }
        oldText = this.getText().toString(); 
        if (y2 > height && size >= 8.0f) {
            if(lines >= 2){
            	size = size - 2.0f;
            	this.setTextSize(size);
            }
            if(lines == 1){
            	finish = true;
            }  	
        }else{
        	finish = true;
        }
    }
    public float getSize(int size){
    	return size;
    }
}
