package com.yit.circlebutton;

import java.util.Map;

import com.yit.rippeffect.R;
import com.yit.rippeffect.SampleActivity;

import jp.co.cyberagent.android.gpuimage.GPUImageView;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by David on 2014/8/18.
 */
public class CircleButton extends ImageView {

    private static final int PRESSED_COLOR_LIGHTUP = 0; // 用于提高亮度
    private static final int PRESSED_RING_ALPHA = 40; // 用于处理透明度
    private static final int DEFAULT_PRESSED_RING_WIDTH_DIP = 5; // 默认的宽度
    private static final int ANIMATION_TIME_ID = android.R.integer.config_shortAnimTime; // 动画响应时间
    // x,y坐标，内环圆半径，外环圆半径
    private int x;
    private int y;
    private int outerRadius;
    private int pressedRingRadius;
    // 内环圆画笔，外环圆画笔，文字画笔
    private Paint circlePaint;
    private Paint focusPaint;
    // 动画单位时间内散开大小
    private float animationProgress;
    // 动画散开大小
    private int pressedRingWidth;
    // 默认的底色
    private int defaultColor = Color.BLACK;
    // 默认文字属性
    private String text = "";
    private int textColor = Color.BLACK;
    private Typeface defaultTypeface = Typeface.DEFAULT;
    private int textSize = 12;
    // 按压后散开的颜色
    private int pressedColor;
    // 图片信息
    private Drawable drawable;
    // 用来处理格式转换
    private Context context;
    // 用来处理动画
    private ObjectAnimator pressedanimator;
    
    private Handler handler = new Handler();
    
    private Thread thread;

    public CircleButton(Context context) {
        super(context);
        init(context, null);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

//    // 定义按压后的动画效果
//    @Override
    public void setPressed(boolean pressed) {
	
	
        super.setPressed(pressed);
        
        SampleActivity sa = (SampleActivity)this.context;
        Map<View,  SparseArray<View> > views = sa.getAllViewMap();
        final SparseArray<View> v = views.get(this);
        final GPUImageView gpuImageView =  sa.getGpuView();
        
        
	handler.post(new Runnable() {

	    @Override
	    public void run() {

		gpuImageView.removeView(v.get(1));
		
		for (int i = 0; i <= v.size(); i++) {
		    View view = v.get(i);
		    if (view instanceof TextView) {
			gpuImageView.removeView(view);
		    }

		}

		handler.post(new Runnable() {
		    @Override
		    public void run() {

			try {
			    Thread.sleep(200l);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}

			
			
			gpuImageView.removeView(v.get(5));
			gpuImageView.removeView(v.get(7));
			gpuImageView.removeView(v.get(9));
			
			handler.post(new Runnable() {
			    
			    @Override
			    public void run() {
				try {
				    Thread.sleep(100l);
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
				
				gpuImageView.removeView(v.get(3));
				gpuImageView.removeView(v.get(2));
				
				gpuImageView.removeView(v.get(4));
				

				handler.post(new Runnable() {
				    @Override
				    public void run() {
					try {
					    Thread.sleep(50l);
					} catch (InterruptedException e) {
					    e.printStackTrace();
					}
					
					hidePressedRing();

					handler.post(new Runnable() {
					    @Override
					    public void run() {
						try {
						    Thread.sleep(10l);
						} catch (InterruptedException e) {
						    e.printStackTrace();
						}
						
						gpuImageView.removeView(CircleButton.this);
					    }
					});

				    }
				});
				
			    }
			});


		    }

		});

	    }
	});

    }

    // 按钮绘制
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        pressedRingRadius = outerRadius - pressedRingWidth - pressedRingWidth / 2;
        focusPaint.setStrokeWidth(pressedRingWidth);
        canvas.drawCircle(x, y, pressedRingRadius + animationProgress, focusPaint);
        canvas.drawCircle(x, y, outerRadius - pressedRingWidth, circlePaint);
        super.onDraw(canvas);
    }

    // 设置按钮的中圆的属性
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w / 2;
        y = h / 2;
        outerRadius = Math.min(w, h) / 2;
        pressedRingRadius = outerRadius - pressedRingWidth - pressedRingWidth / 2;
    }

    public float getAnimationProgress() {
        return animationProgress;
    }

    public void setAnimationProgress(float animationProgress) {
        this.animationProgress = animationProgress;
        this.invalidate();
    }

    public void set_bg_color(int color) {
        this.defaultColor = color;
        this.pressedColor = getHighlightColor(color, PRESSED_COLOR_LIGHTUP);

        circlePaint.setColor(Color.RED);
        focusPaint.setColor(defaultColor);
        focusPaint.setAlpha(PRESSED_RING_ALPHA);
        circlePaint.setStrokeWidth(10);
        this.invalidate();
    }

    public void set_text(String string) {
        this.text = string;

        this.invalidate();
    }



    public void set_typeface(Typeface typeFace) {
        this.defaultTypeface = typeFace;


        this.invalidate();
    }



    public void set_fg_drawable(Drawable drawable) {
        this.setImageDrawable(drawable);

        this.invalidate();
    }

    public void set_pressed_ring_width(int dp) {
        //转化成dp格式
        this.pressedRingWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources()
                        .getDisplayMetrics());
        this.invalidate();
    }

    @SuppressLint("NewApi")
    private void init(Context context, AttributeSet attrs) {
        this.setFocusable(true);
        this.setScaleType(ScaleType.CENTER_INSIDE);
        this.context = context;
        setClickable(true);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Style.FILL);

        focusPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        focusPaint.setStyle(Paint.Style.STROKE);


        pressedRingWidth = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, DEFAULT_PRESSED_RING_WIDTH_DIP,
                getResources().getDisplayMetrics());

        int color = Color.BLACK;
        // 获取自定义xml中的属性
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CircleButton);
            color = a.getColor(R.styleable.CircleButton_bg_color, color);
            pressedRingWidth = (int) a.getDimension(
                    R.styleable.CircleButton_pressed_ring_width,
                    pressedRingWidth);
            text = a.getString(R.styleable.CircleButton_text);
            textSize = (int) a.getDimension(
                    R.styleable.CircleButton_text_size_dp, textSize);
            textColor = a.getColor(R.styleable.CircleButton_text_color,
                    textColor);
            a.recycle();
        }


        // Paint.setTextSize处理得是px格式而需要使用的是dp格式
        final float scale = context.getResources().getDisplayMetrics().density;
        Log.e("UNDERSTAND", "textSize:"+textSize+" scale:"+scale);
        set_bg_color(color);

        focusPaint.setStrokeWidth(pressedRingWidth);
        final int pressedAnimationTime = getResources().getInteger(ANIMATION_TIME_ID);
        pressedanimator = ObjectAnimator.ofFloat(this, "animationProgress", 0f,0f);
        pressedanimator.setDuration(pressedAnimationTime);
        showPressedRing();
    }



    // 获取高亮配色
    private int getHighlightColor(int color, int amount) {
        return Color.BLACK;
    }

    // 显示散开动画
    @SuppressLint("NewApi")
    private void showPressedRing() {
        pressedanimator.setFloatValues(animationProgress, pressedRingWidth);
        pressedanimator.start();
    }

    // 隐藏散开动画
    @SuppressLint("NewApi")
    private void hidePressedRing() {
        pressedanimator.setFloatValues(pressedRingWidth, 0f);
        pressedanimator.start();
    }

}
