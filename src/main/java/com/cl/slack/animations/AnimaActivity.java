package com.cl.slack.animations;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 下拉展开动画
 */
public class AnimaActivity extends AppCompatActivity {

    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewMeasuredHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima);

        mHiddenView = (LinearLayout) findViewById(R.id.hidden_view);
        //获取像素密度
        mDensity = getResources().getDisplayMetrics().density;
        //获取布局的高度
        mHiddenViewMeasuredHeight = (int) (mDensity*40+0.5);
    }

    public  void llClick(View view){
        if (mHiddenView.getVisibility() == View.GONE){
            animOpen(mHiddenView);
        }else{
            animClose(mHiddenView);
        }
    }

    private void animOpen(final  View view){
        view.setVisibility(View.VISIBLE);
        ValueAnimator va = createDropAnim(view,0,mHiddenViewMeasuredHeight);
        va.start();
    }


    private void animClose(final  View view){
        final int origHeight = view.getHeight();
        ValueAnimator va = createDropAnim(view,origHeight,0);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int value = (int) animation.getAnimatedValue();//  用来产生一定具有规律的数字
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height =  value;
                view.setLayoutParams(layoutParams);
                if(value == 0){
                    view.setVisibility(View.GONE);
                }
            }
        });
        va.start();
    }


    private ValueAnimator createDropAnim(final  View view,int start,int end) {
        // ValueAnimator
        ValueAnimator va = ValueAnimator.ofInt(start, end);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();//  用来产生一定具有规律的数字
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return  va;
    }

}
