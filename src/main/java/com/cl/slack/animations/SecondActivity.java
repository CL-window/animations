package com.cl.slack.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {

    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ll = (LinearLayout) findViewById(R.id.ll);
        //设置过渡动画
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        LayoutAnimationController lc = new LayoutAnimationController(sa, 0.5f);
        /**
         * LayoutAnimationController.ORDER_NORMAL 顺序
         * LayoutAnimationController.ORDER_RANDOM 随机
         * LayoutAnimationController.ORDER_REVEESE 反序
         * */
        lc.setOrder(LayoutAnimationController.ORDER_RANDOM);
        //设置布局动画
        ll.setLayoutAnimation(lc);
    }
}
