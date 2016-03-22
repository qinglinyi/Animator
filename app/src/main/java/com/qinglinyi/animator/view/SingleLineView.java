package com.qinglinyi.animator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.qinglinyi.animator.R;

/**
 * 一个简单的画线View
 */
public class SingleLineView extends View {

    private Paint mPaint;
    private Paint mPaintV;
    private float myX;
    private int mColor;
    private int time;

    public SingleLineView(Context context) {
        super(context);
        init(context);
    }

    public SingleLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SingleLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(context.getResources().getColor(R.color.red));
        mPaintV = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintV.setTextSize(30);
        mPaintV.setColor(context.getResources().getColor(R.color.red));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mY = getHeight() / 2;
        if (mColor != 0) {
            mPaint.setColor(mColor);
        }
        String timeStr = time + "";
        canvas.drawRect(600, 0, 610, getHeight(), mPaintV);
        canvas.drawRect(800, 0, 810, getHeight(), mPaintV);
        if (time!=0){
            canvas.drawText(timeStr, 0, timeStr.length(), 10, 50, mPaintV);
        }
        canvas.drawRect(0, mY, myX, mY + 10, mPaint);
    }

    public float getMyX(){
        return myX;
    }

    public void setMyX(float myX) {
        this.myX = myX;
        // 刷新
        invalidate();
    }

    public void setColor(int color) {
        this.mColor = color;
        invalidate();
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void updateView(float x, int color) {
        this.mColor = color;
        setMyX(x);
    }
}
