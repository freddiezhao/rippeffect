package com.yit.rippeffect;

import java.util.HashMap;
import java.util.Map;

import com.yit.circlebutton.CircleButton;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;
import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;


public class SampleActivity extends Activity {

    private float x = -1;
    
    private float y = -1;
    
    private GPUImageView gpuView;
    
    public GPUImageView getGpuView() {
        return gpuView;
    }

    public void setGpuView(GPUImageView gpuView) {
        this.gpuView = gpuView;
    }

    private Thread thread;
    
    private Map<View, SparseArray<View> > allViewMap = new HashMap<View, SparseArray<View> >();

    
    public Map<View,  SparseArray<View> > getAllViewMap() {
        return allViewMap;
    }

    public void setAllViewMap(Map<View,  SparseArray<View> > allViewMap) {
        this.allViewMap = allViewMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	
	gpuView = new GPUImageView(this);
	Uri imageUri = Uri.parse("http://ci.xiaohongshu.com/feed7560-0662-4b5a-a183-4c1b9648a49b@r_1280w_1280h.jpg");
	gpuView.setImage(imageUri);
	gpuView.setFilter(new GPUImageFilter());
	gpuView.setOnTouchListener(new OnTouchListener() {

	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
		return touchEvent(v,event);
	    }
	});
	
	setContentView(gpuView);
    }

    public boolean touchEvent(View v,MotionEvent event) {
	
	final SparseArray<View> sparseArray = new SparseArray<View>();
	
	thread = new Thread(new Runnable() {
	    
	    @Override
	    public void run() {
		try {
		    Thread.sleep(250L);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

	
		runOnUiThread(new Runnable() {
		    
		    @Override
		    public void run() {
			
			FrameLayout.LayoutParams lineFL1 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lineFL1.leftMargin = (int) SampleActivity.this.x+70;
			lineFL1.topMargin = (int) SampleActivity.this.y+70;
			lineFL1.width = 230;
			lineFL1.height = 3;
			
			View view_line1 = new View(SampleActivity.this);
			view_line1.setBackgroundColor(Color.WHITE);
			view_line1.setLayoutParams(lineFL1);
			gpuView.addView(view_line1, lineFL1);
			view_line1.setTag("lineOut2");
			sparseArray.put(5, view_line1);
			
			FrameLayout.LayoutParams lineFLTextView1 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lineFLTextView1.leftMargin = (int) SampleActivity.this.x+70;
			lineFLTextView1.topMargin = (int) SampleActivity.this.y+70-60;
			
			TextView tv1 = new TextView(SampleActivity.this);
			tv1.setText("金百合婚纱");
			tv1.setTextColor(Color.WHITE);
			tv1.setTextSize(18);
			gpuView.addView(tv1, lineFLTextView1);
			
			sparseArray.put(6, tv1);
			//--------------------------------------------------------
			FrameLayout.LayoutParams lineFL3 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lineFL3.leftMargin = (int)SampleActivity. this.x+70;
			lineFL3.topMargin = (int) SampleActivity.this.y-70;
			lineFL3.width = 250;
			lineFL3.height = 4;
			
			View view_line3 = new View(SampleActivity.this);
			view_line3.setBackgroundColor(Color.WHITE);
			view_line3.setLayoutParams(lineFL3);
			view_line3.setTag("linaOut3");
			gpuView.addView(view_line3, lineFL3);
			
			sparseArray.put(7, view_line3);
			
			FrameLayout.LayoutParams lineFLTextView2 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lineFLTextView2.leftMargin = (int) SampleActivity.this.x+70;
			lineFLTextView2.topMargin = (int) SampleActivity.this.y-70-60;
			
			TextView tv2 = new TextView(SampleActivity.this);
			tv2.setText("人民币3000￥");
			tv2.setTextColor(Color.WHITE);
			tv2.setTextSize(18);
			gpuView.addView(tv2, lineFLTextView2);
			sparseArray.put(8, tv2);
			
                        //-----------------------------------------------------
			FrameLayout.LayoutParams lineFL5 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lineFL5.leftMargin = (int) SampleActivity.this.x-339;
			lineFL5.topMargin = (int) SampleActivity.this.y+69;
			lineFL5.width = 270;
			lineFL5.height = 4;
			
			View view_line5 = new View(SampleActivity.this);
			view_line5.setBackgroundColor(Color.WHITE);
			view_line5.setLayoutParams(lineFL5);
			view_line5.setTag("linaOut1");
			
			gpuView.addView(view_line5, lineFL5);
			sparseArray.put(9, view_line5);
			
			FrameLayout.LayoutParams lineFLTextView3 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			lineFLTextView3.leftMargin = (int) SampleActivity.this.x-270;
			lineFLTextView3.topMargin = (int) SampleActivity.this.y+66-60;
			
			TextView tv3 = new TextView(SampleActivity.this);
			tv3.setText("中国.苏州");
			tv3.setTextColor(Color.WHITE);
			tv3.setTextSize(18);
			
			gpuView.addView(tv3, lineFLTextView3);
			sparseArray.put(10, tv3);
			
//			thread = null;
		    
		    }
		});
	    }
	});

	this.x = event.getX();
	this.y = event.getY();

	
	
	//-------------------------涟漪效果布局参数------------------------
	FrameLayout.LayoutParams rleFL = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	rleFL.leftMargin = (int) this.x - 50;
	rleFL.topMargin = (int) this.y - 50;

	rleFL.width = 100;
	rleFL.height = 100;
	RippleEffect re = new RippleEffect(this);//涟漪效果
	gpuView.addView(re, rleFL);
	
	sparseArray.put(1, re);
	//---------------------------右下45°方向的直线---------------------
	FrameLayout.LayoutParams lineFL = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	lineFL.leftMargin = (int) this.x;
	lineFL.topMargin = (int) this.y;
	lineFL.width = 100;
	lineFL.height = 4;
	
	View view_line = new View(this);
	view_line.setBackgroundColor(Color.WHITE);
	view_line.setLayoutParams(lineFL);
	view_line.setPivotX( 0);
	view_line.setPivotY( 0);
	view_line.setRotation(45);
	gpuView.addView(view_line, lineFL);
	
	sparseArray.put(2, view_line);
	//---------------------------右上45°的直线---------------------
	FrameLayout.LayoutParams lineFL2 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	lineFL2.leftMargin = (int) this.x;
	lineFL2.topMargin = (int) this.y;
	lineFL2.width = 100;
	lineFL2.height = 4;
	
	View view_line2 = new View(this);
	view_line2.setBackgroundColor(Color.WHITE);
	view_line2.setLayoutParams(lineFL);
	view_line2.setPivotX( 0);
	view_line2.setPivotY( 0);
	view_line2.setRotation(315);
	gpuView.addView(view_line2, lineFL2);
	
	sparseArray.put(3, view_line2);
	//----------------------------左下45°的直线--------------------------
	FrameLayout.LayoutParams lineFL4 = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	lineFL4.leftMargin = (int) this.x;
	lineFL4.topMargin = (int) this.y+4;
	lineFL4.width = 100;
	lineFL4.height = 4;
	
	View view_line4 = new View(this);
	view_line4.setBackgroundColor(Color.WHITE);
	view_line4.setLayoutParams(lineFL);
	view_line4.setPivotX( 0);
	view_line4.setPivotY( 0);
	view_line4.setRotation(135);
	gpuView.addView(view_line4, lineFL4);
	
	sparseArray.put(4, view_line4);
	
	//------------------------按钮-----------------------------
	CircleButton circleButton = new CircleButton(this);
	circleButton.set_pressed_ring_width(6);
	circleButton.set_bg_color(Color.BLACK);

	        //-------------------------按钮的布局参数----------------------------
	FrameLayout.LayoutParams btnFL = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	btnFL.leftMargin = (int) this.x - 25;
	btnFL.topMargin = (int) this.y - 25;

	btnFL.width = 50;
	btnFL.height = 50;
	gpuView.addView(circleButton, btnFL);
	
	allViewMap.put(circleButton, sparseArray);//按钮对应的所有view
	thread.start();

	return super.onTouchEvent(event);

    }
}
