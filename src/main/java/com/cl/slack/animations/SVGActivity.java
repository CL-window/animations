package com.cl.slack.animations;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * android绘制SVG  https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html
 * cool : https://gist.github.com/nickbutcher/b3962f0d14913e9746f2
 * Created by chenling on 2016/5/10.
 * 在线 SVG : http://editor.method.ac/
 * view->source 查看 SVG 源码
 */
public class SVGActivity extends Activity {


    private ImageView iv;
    private TextView text;
    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration;
    private boolean expanded = false;// true:只显示搜索图形 false:只显示输入框（初试状态除外）

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
//        setContentView(R.layout.activity_svg2);
        iv = (ImageView) findViewById(R.id.search);
        text = (TextView) findViewById(R.id.text);
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);
        interp = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
        duration = 2000;
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        iv.setTranslationX(offset);
        text.setAlpha(1f);// 文字不透明，透明就看不见了

        // 只显示输入框 居中显示（覆盖住输入框）
        iv.setImageDrawable(searchToBar);
        searchToBar.start();
        iv.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
        expanded = !expanded;

    }

    public void animate(View view) {
//        // 输入框为空时，就显示输入框
//        if(text.getText().length() == 0)
//            expanded = false;

        if (!expanded) {
            if(text.getText().length() > 0){
                Toast.makeText(this,"搜索："+text.getText(),Toast.LENGTH_SHORT).show();
            } else {
                // 只显示输入框
                iv.setImageDrawable(searchToBar);
                searchToBar.start();
                //让图形一直保持居中
                iv.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
//            text.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
            }

        } else {

                // 只显示搜索图形
                iv.setImageDrawable(barToSearch);
                barToSearch.start();
                //让图形一直保持居中
//            iv.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
//            text.setAlpha(0f);
            }

        
        expanded = !expanded;
    }
}
