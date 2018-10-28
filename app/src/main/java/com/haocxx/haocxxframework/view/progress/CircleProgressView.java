package com.haocxx.haocxxframework.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Haocxx 
 * on 2018/10/28
 */
public class CircleProgressView extends View {
    public static final int DEFAULT_COLOR = Color.parseColor("#B3000000");
    public static final int DEFAULT_RADIUS = 50;

    private Paint mPaint;
    private int mProgress = 0;
    private int mRadius = DEFAULT_RADIUS;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(2 * mRadius, 2 * mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int angle = (int) (mProgress * 3.6f);
        canvas.drawArc(0, 0, 2 * mRadius, 2 * mRadius, -90, angle, true, mPaint);
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public void setColor(@ColorInt int color) {
        mPaint.setColor(color);
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }
}
