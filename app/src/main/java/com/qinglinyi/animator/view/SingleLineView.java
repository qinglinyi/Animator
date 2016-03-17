package com.qinglinyi.animator.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.qinglinyi.animator.R;

public class SingleLineView extends View {

    private Paint mPaint;
    private float mX = 0;

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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float mY = getHeight() / 2;
        canvas.drawRect(0, mY, mX, mY + 10, mPaint);
    }

    public void setmX(float mX) {
        this.mX = mX;
        // 刷新
        invalidate();
    }
}
