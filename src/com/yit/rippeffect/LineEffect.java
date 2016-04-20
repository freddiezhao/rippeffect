package com.yit.rippeffect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class LineEffect extends View  implements Runnable{
    
    private Paint paint;
    
    private int x;
    
    private int y ;
    

    public LineEffect(Context context,int x,int y) {
	
	super(context);
	
	this.x= x;
	this.y=y;
	
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth((float) 3.0);
    }

    
    
    @Override
    public void onDraw(Canvas canvas) {
	canvas.drawLine(this.x, this.y, this.x+200, this.y+200, paint);
    }



    @Override
    public void run() {

	
    }
}
