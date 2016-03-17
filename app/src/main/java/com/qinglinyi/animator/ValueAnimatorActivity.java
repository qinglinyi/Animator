package com.qinglinyi.animator;

import android.animation.AnimatorInflater;
import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.qinglinyi.animator.view.SingleLineView;

public class ValueAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private ValueAnimator mCodeValueAnimator;
    private ValueAnimator mXmlValueAnimator;
    private SingleLineView mCodeLine;
    private SingleLineView mXmlLine;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);

        mCodeLine = (SingleLineView) findViewById(R.id.codeLine);
        mXmlLine = (SingleLineView) findViewById(R.id.xmlLine);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.begin_0_btn:
                beginCodeValueAnimator();
                break;
            case R.id.begin_1_btn:
                beginXmlValueAnimator();
                break;
            default:

                break;
        }
    }

    private void beginCodeValueAnimator() {
        if (mCodeValueAnimator == null) {
            mCodeValueAnimator = ValueAnimator.ofFloat(0f, 800f);
            // 使用监听器监听动画属性值变化，更新到目标对象
            mCodeValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float i = (float) animation.getAnimatedValue();
                    mCodeLine.setmX(i);
                }
            });
            mCodeValueAnimator.setDuration(1000);
        } else {
            mCodeValueAnimator.end();
        }
        // 根据CheckBox设置Evaluator
        mCodeValueAnimator.setEvaluator(mCheckBox.isChecked() ? new MyEvaluator() : new FloatEvaluator());
        mCodeValueAnimator.start();
    }

    private void beginXmlValueAnimator() {
        if (mXmlValueAnimator == null) {
            mXmlValueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.anim.value_animator);
            mXmlValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float i = (float) animation.getAnimatedValue();
                    mXmlLine.setmX(i);
                }
            });

        } else {
            mXmlValueAnimator.end();
        }
        mXmlValueAnimator.start();
    }

    // 自定义Evaluator，将结果扩大1.3倍
    public class MyEvaluator implements TypeEvaluator<Float> {

        @Override
        public Float evaluate(float fraction, Float startValue, Float endValue) {
            return startValue + 1.3f * fraction * (endValue - startValue);
        }

    }

}
