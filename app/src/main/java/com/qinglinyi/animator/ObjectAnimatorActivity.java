package com.qinglinyi.animator;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.qinglinyi.animator.view.SingleLineView;

public class ObjectAnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private SingleLineView codeLine, xmlLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        codeLine = (SingleLineView) findViewById(R.id.codeLine);
        xmlLine = (SingleLineView) findViewById(R.id.xmlLine);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.begin_0_btn:
                codeBegin();
                break;
            case R.id.begin_1_btn:
                xmlBegin();
                break;
            default:

                break;
        }

    }

    private void codeBegin() {
        ObjectAnimator objectAnimator;
        if (codeLine.getTag() != null && codeLine.getTag() instanceof ObjectAnimator) {
            objectAnimator = (ObjectAnimator) codeLine.getTag();
        } else {
//            objectAnimator = ObjectAnimator.ofFloat(codeLine, "myX", 0f, 800f);
            objectAnimator = ObjectAnimator.ofFloat(codeLine, "myX", 800f);// 使用这个方式的时候，注意需要同时具有set和get方法
            objectAnimator.setDuration(4000);
            objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            codeLine.setTag(objectAnimator);
        }
        objectAnimator.end();
        objectAnimator.start();
    }

    private void xmlBegin() {
        ObjectAnimator objectAnimator;
        if (xmlLine.getTag() != null && xmlLine.getTag() instanceof ObjectAnimator) {
            objectAnimator = (ObjectAnimator) xmlLine.getTag();
        } else {
            objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator
                    .object_animator);
            objectAnimator.setTarget(xmlLine);
            xmlLine.setTag(objectAnimator);
        }
        objectAnimator.end();
        objectAnimator.start();
    }
}
