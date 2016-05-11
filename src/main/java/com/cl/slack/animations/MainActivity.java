package com.cl.slack.animations;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    //视图动画
    private Button alpha, rotate, translate, scale, set;
    //属性动画
    private Button object,property, value,animatorSet,animate;
    //XML
    private Button fromXML;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initButton();

    }

    private void initButton() {
        // 布局动画
        findViewById(R.id.second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        //SVG
        findViewById(R.id.svg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SVGActivity.class));
            }
        });

        // 下拉
        findViewById(R.id.xiala).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AnimaActivity.class));
            }
        });

    }

    private void initView() {
        alpha = (Button) findViewById(R.id.alpha);
        alpha.setOnClickListener(this);

        rotate = (Button) findViewById(R.id.rotate);
        rotate.setOnClickListener(this);

        translate = (Button) findViewById(R.id.translate);
        translate.setOnClickListener(this);

        scale = (Button) findViewById(R.id.scale);
        scale.setOnClickListener(this);

        set = (Button) findViewById(R.id.set);
        set.setOnClickListener(this);

        object = (Button) findViewById(R.id.object);
        object.setOnClickListener(this);

        property = (Button) findViewById(R.id.property);
        property.setOnClickListener(this);

        value = (Button) findViewById(R.id.value);
        value.setOnClickListener(this);

        animatorSet = (Button) findViewById(R.id.animatorSet);
        animatorSet.setOnClickListener(this);

        animate = (Button) findViewById(R.id.animate);
        animate.setOnClickListener(this);

        fromXML = (Button) findViewById(R.id.fromXML);
        fromXML.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:// 透明动画
                AlphaAnimation al = new AlphaAnimation(0, 1);
                al.setDuration(2000);
                alpha.startAnimation(al);
                // 动画监听
                al.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i("slack", "onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.i("slack", "onAnimationEnd");
                        Toast.makeText(MainActivity.this, "动画结束", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.i("slack", "onAnimationRepeat");
                    }
                });

                break;
            case R.id.rotate:// 旋转动画
                //  RotateAnimation (float fromDegrees, float toDegrees, float pivotX, float pivotY)
                RotateAnimation ro = new RotateAnimation(0, 180, 100, 100);
                ro.setDuration(2000);
//                rotate.setAnimation(ro);
                rotate.startAnimation(ro);

                break;
            case R.id.translate: // 平移动画
                // TranslateAnimation (float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
                TranslateAnimation tr = new TranslateAnimation(0, 200, 0, 300);
                tr.setDuration(2000);
//                translate.setAnimation(tr);
                // want the animation to play immediately
                translate.startAnimation(tr);
                tr.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // 再移回来
                        TranslateAnimation tr = new TranslateAnimation(200, 0, 300, 0);
                        tr.setDuration(2000);
                        translate.startAnimation(tr);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.scale:// 缩放动画
                // ScaleAnimation (float fromX, float toX, float fromY, float toY)
                ScaleAnimation sc = new ScaleAnimation(0, 2, 0, 2);
                sc.setDuration(2000);
                scale.startAnimation(sc);
                break;
            case R.id.set:// AnimationSet 动画集合
                AnimationSet setAnimation = new AnimationSet(true);
                setAnimation.setDuration(2000);

                AlphaAnimation als = new AlphaAnimation(0, 1);
                als.setDuration(2000);
                setAnimation.addAnimation(als);

                RotateAnimation ros = new RotateAnimation(0, 360, 100, 100);
                ros.setDuration(2000);
                setAnimation.addAnimation(ros);

                ScaleAnimation scs = new ScaleAnimation(0, 1, 0, 1);
                scs.setDuration(2000);
                setAnimation.addAnimation(scs);

                set.startAnimation(setAnimation);

                break;
            case R.id.object:// 使用ObjectAnimator进行更精细化的控制，操作单个属性
                /**
                 * translationX和 translationY:这两个属性作为一种增量来控制着View对象从它布局容器的左上角坐标偏移的位置。
                 * rotation、rotationX和rotationY:这三个属性控制View对象围绕支点进行2D和3D旋转
                 * scaleX和scaleY. 这两个属性控制着View对象围绕它的支点进行2D缩放。
                 * pivotX和pivotY:这两个属性控制着view对象的支点位置,围绕这个支点进行旋转和缩放变换处理，
                 默认情况下,该支点的位置就是View对象的中心点。
                 * x和y这是两个简单实用的属性，它描述了View对象在它的容器中的最终位置，
                 它是最初的左上角坐标和 translationX和 translationY值的累计和
                 * alpha:它表示View对象的alpha透明度，默认值是1(不透明),0代表完全透明(不可见)
                 * 需要注意的是，这里操作的属性要有 set，get的方法，如果自己自定义一个动画类，需要提供属性的get/set方法
                 * */
                ObjectAnimator ob = ObjectAnimator.ofFloat(object, "translationX", 300);

                ob.setDuration(2000);
                ob.start();
                ob.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // 还原
                        ObjectAnimator.ofFloat(object, "translationX", 0).setDuration(2000).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                break;
            case R.id.property:// 类似视图动画中的AnimationSet，就是把动画给组合起来

                PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("rotation",30);// 30°
                PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX",1f,0,1.2f);
                PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY",1f,1,1f);// 属性，开始变化的大小，是否变化到最小（1 no, 0 yes），结束变化后的大小
                ObjectAnimator.ofPropertyValuesHolder(property,pvh1,pvh2,pvh3).setDuration(2000).start();
//                ObjectAnimator.ofPropertyValuesHolder(property,pvh3).setDuration(2000).start();
                break;
            case R.id.value:// ObjectAnimator也是继承自ValueAnimator
                //ValueAnimator本身不提供任何动画，他更像是一个数值发生器，
                // 用来产生一定具有规律的数字，从而让调用者控制动画的整个过程
                ValueAnimator va = ValueAnimator.ofFloat(0, 50);
                va.setTarget(value);
                va.setDuration(2000).start();
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float values = (float) animation.getAnimatedValue();
                        Log.i("slack", "values:"+values );
                    }
                });
                //只监听动画结束
                va.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Log.i("slack", "done..." );
                    }
                });

                break;

            case R.id.animatorSet:// 对于一个属性同时作用在一个view上，跟PropertyValuesHolder一样，但是AnimatorSet不仅能实现，而且能更精准的控制顺序

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animatorSet, "rotation", 30);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(animatorSet, "scaleX", 1f, 0, 2f);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(animatorSet, "scaleY", 1f, 0, 1f);
                AnimatorSet set = new AnimatorSet();
                set.setDuration(2000);
//                set.playTogether(animator1, animator2, animator3);// 通过playTogether等方法控制多个动画协同工作，从而控制播放顺序
                set.playSequentially(animator1, animator2, animator3);// 依次动画
                set.start();
                break;

            case R.id.animate:// animate方法直接来驱动属性动画
                animate.animate().alpha(0).y(300).x(100).setDuration(2000).withStartAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).withEndAction(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();

                break;
            case R.id.fromXML:// 属性动画同样的可以定义在xml

                Animator anim = AnimatorInflater
                        .loadAnimator(this, R.animator.animator);
                anim.setTarget(fromXML);
                anim.start();

//                MyAnimation my = new MyAnimation(this);
//                my.start();
                break;

        }
    }
}
