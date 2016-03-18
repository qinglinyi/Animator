package com.qinglinyi.animator;

import android.animation.ArgbEvaluator;
import android.animation.Keyframe;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.qinglinyi.animator.view.SingleLineView;

/**
 * 1. 使用PropertyValuesHolder来设置ValueAnimator
 * 2. 使用KeyFrame来设置PropertyValuesHolder
 */
public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = "ValueAnimatorActivity";

    private ValueAnimator mPropertyValuesHolderValueAnimator;
    private ValueAnimator mPropertyValuesHolderValueAnimator1;
    private ValueAnimator mPropertyValuesHolderValueAnimator2;
    private ValueAnimator mKeyFrameValueAnimator;
    private ValueAnimator mKeyFrameValueAnimator1;
    private ValueAnimator mKeyFrameValueAnimator2;
    private SingleLineView mPropertyValuesHolderLine;
    private SingleLineView mPropertyValuesHolderLine1;
    private SingleLineView mPropertyValuesHolderLine2;
    private SingleLineView mKeyFrameCodeLine;
    private SingleLineView mKeyFrameCodeLine1;
    private SingleLineView mKeyFrameCodeLine2;

    private static final int RED = 0xffFF8080;
    private static final int CYAN = 0xff80ffff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

        mPropertyValuesHolderLine = (SingleLineView) findViewById(R.id.propertyValuesHolderCodeLine);
        mKeyFrameCodeLine = (SingleLineView) findViewById(R.id.keyFrameCodeLine);
        mPropertyValuesHolderLine1 = (SingleLineView) findViewById(R.id.propertyValuesHolderCodeLine1);
        mKeyFrameCodeLine1 = (SingleLineView) findViewById(R.id.keyFrameCodeLine1);
        mPropertyValuesHolderLine2 = (SingleLineView) findViewById(R.id.propertyValuesHolderCodeLine2);
        mKeyFrameCodeLine2 = (SingleLineView) findViewById(R.id.keyFrameCodeLine2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.begin_0_btn:
                beginPropertyValuesHolderValueAnimator();
                break;
            case R.id.begin_1_btn:
                beginKeyFrameValueAnimator();
                break;
            case R.id.begin_2_btn:
                beginBothValueAnimator();
                break;

            default:

                break;
        }
    }


    private void beginPropertyValuesHolderValueAnimator() {

        if (mPropertyValuesHolderValueAnimator == null) {
            // 使用PropertyValuesHolder 来完成初始化 ValueAnimator
            // 这里使用两个PropertyValuesHolder，一个变化x,另外一个变化color
            // 第一个参数应该是propertyName，不过我们只要对应设置和获取就行（如A-A）
            // ，ValueAnimator会有一个Map存放propertyName-PropertyValuesHolder。
            // 其实mCodeValueAnimator = ValueAnimator.ofFloat(0f, 800f); 和
            //ValueAnimator.ofPropertyValuesHolder(PropertyValuesHolder.ofFloat("", 0f, 800f))是等价的
            PropertyValuesHolder holder0 = PropertyValuesHolder.ofFloat("A", 0f, 800f, 600f);
            PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("B", RED, CYAN);
            holder1.setEvaluator(new ArgbEvaluator());
            mPropertyValuesHolderValueAnimator = ValueAnimator.ofPropertyValuesHolder(holder0, holder1);
            mPropertyValuesHolderValueAnimator.setDuration(1000);
            mPropertyValuesHolderValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue("A");
                    int color = (int) animation.getAnimatedValue("B");
                    mPropertyValuesHolderLine.updateView(x, color);
                }
            });
        } else {
            mPropertyValuesHolderValueAnimator.end();
        }
        mPropertyValuesHolderValueAnimator.start();

    }

    private void beginKeyFrameValueAnimator() {

        if (mKeyFrameValueAnimator == null) {

            Keyframe keyframe0 = Keyframe.ofFloat(0f, 0f);
            Keyframe keyframe1 = Keyframe.ofFloat(.5f, 800f);
            Keyframe keyframe2 = Keyframe.ofFloat(1f, 600f);

            PropertyValuesHolder holder0 = PropertyValuesHolder.ofKeyframe("A", keyframe0, keyframe1,
                    keyframe2);
            PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("B", RED, CYAN);
            holder1.setEvaluator(new ArgbEvaluator());
            mKeyFrameValueAnimator = ValueAnimator.ofPropertyValuesHolder(holder0, holder1);
            mKeyFrameValueAnimator.setDuration(1000);
            mKeyFrameValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue("A");
                    int color = (int) animation.getAnimatedValue("B");
                    mKeyFrameCodeLine.updateView(x, color);
                }
            });
        } else {
            mKeyFrameValueAnimator.end();
        }
        mKeyFrameValueAnimator.start();
    }

    private void beginBothValueAnimator() {

        final int duration = 10000;
        final float point0 = 0f;
        final float point1 = 600f;
        final float point2 = 800f;

        if (mPropertyValuesHolderValueAnimator1 == null) {
            PropertyValuesHolder holder0 = PropertyValuesHolder.ofFloat("A", point0, point1, point2);
            PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("B", RED, CYAN);
            holder1.setEvaluator(new ArgbEvaluator());
            mPropertyValuesHolderValueAnimator1 = ValueAnimator.ofPropertyValuesHolder(holder0, holder1);
            mPropertyValuesHolderValueAnimator1.setDuration(duration);
            mPropertyValuesHolderValueAnimator1.setInterpolator(new AccelerateInterpolator());
            mPropertyValuesHolderValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue("A");
                    int color = (int) animation.getAnimatedValue("B");
                    Log.e(TAG, "PropertyValuesHolder--Fraction:" + animation.getAnimatedFraction()
                            + ",x:" + x);
                    mPropertyValuesHolderLine1.setTime((int) animation.getCurrentPlayTime());
                    mPropertyValuesHolderLine1.updateView(x, color);
                }
            });
        } else {
            mPropertyValuesHolderValueAnimator1.end();
        }
        mPropertyValuesHolderValueAnimator1.start();

        if (mPropertyValuesHolderValueAnimator2 == null) {
            PropertyValuesHolder holder0 = PropertyValuesHolder.ofFloat("A", point0, point1, point2);
            PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("B", RED, CYAN);
            holder1.setEvaluator(new ArgbEvaluator());
            mPropertyValuesHolderValueAnimator2 = ValueAnimator.ofPropertyValuesHolder(holder0, holder1);
            mPropertyValuesHolderValueAnimator2.setDuration(duration);
            mPropertyValuesHolderValueAnimator2.setInterpolator(new LinearInterpolator());
            mPropertyValuesHolderValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue("A");
                    int color = (int) animation.getAnimatedValue("B");
                    Log.e(TAG, "PropertyValuesHolder--Fraction:" + animation.getAnimatedFraction()
                            + ",x:" + x);
                    mPropertyValuesHolderLine2.updateView(x, color);
                    mPropertyValuesHolderLine2.setTime((int) animation.getCurrentPlayTime());
                }
            });
        } else {
            mPropertyValuesHolderValueAnimator2.end();
        }
        mPropertyValuesHolderValueAnimator2.start();


        if (mKeyFrameValueAnimator1 == null) {

            Keyframe keyframe0 = Keyframe.ofFloat(0f, point0);
            Keyframe keyframe1 = Keyframe.ofFloat(.8f, point1);
            Keyframe keyframe2 = Keyframe.ofFloat(1f, point2);
            PropertyValuesHolder holder0 = PropertyValuesHolder.ofKeyframe("A", keyframe0, keyframe1,
                    keyframe2);
            PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("B", RED, CYAN);
            holder1.setEvaluator(new ArgbEvaluator());
            mKeyFrameValueAnimator1 = ValueAnimator.ofPropertyValuesHolder(holder0, holder1);
            mKeyFrameValueAnimator1.setDuration(duration);
            mKeyFrameValueAnimator1.setInterpolator(new AccelerateInterpolator());
            mKeyFrameValueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue("A");
                    int color = (int) animation.getAnimatedValue("B");
                    Log.e(TAG, "KeyframeValue--Fraction:" + animation.getAnimatedFraction() + ",x:" + x);
                    mKeyFrameCodeLine1.updateView(x, color);
                    mKeyFrameCodeLine1.setTime((int) animation.getCurrentPlayTime());
                }
            });
        } else {
            mKeyFrameValueAnimator1.end();
        }
        mKeyFrameValueAnimator1.start();

        if (mKeyFrameValueAnimator2 == null) {

            Keyframe keyframe0 = Keyframe.ofFloat(0f, point0);
            Keyframe keyframe1 = Keyframe.ofFloat(.8f, point1);
            Keyframe keyframe2 = Keyframe.ofFloat(1f, point2);
            PropertyValuesHolder holder0 = PropertyValuesHolder.ofKeyframe("A", keyframe0, keyframe1,
                    keyframe2);
            PropertyValuesHolder holder1 = PropertyValuesHolder.ofInt("B", RED, CYAN);
            holder1.setEvaluator(new ArgbEvaluator());
            mKeyFrameValueAnimator2 = ValueAnimator.ofPropertyValuesHolder(holder0, holder1);
            mKeyFrameValueAnimator2.setDuration(duration);
            mKeyFrameValueAnimator2.setInterpolator(new LinearInterpolator());
            mKeyFrameValueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float x = (float) animation.getAnimatedValue("A");
                    int color = (int) animation.getAnimatedValue("B");

                    Log.e(TAG, "KeyframeValue--Fraction:" + animation.getAnimatedFraction() + ",x:" + x);
                    mKeyFrameCodeLine2.updateView(x, color);
                    mKeyFrameCodeLine2.setTime((int) animation.getCurrentPlayTime());
                }
            });
        } else {
            mKeyFrameValueAnimator2.end();
        }
        mKeyFrameValueAnimator2.start();
    }
}
