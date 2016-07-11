package com.zbj.myapp.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zbj.myapp.R;

/**
 * Created by tiandawu on 2016/7/11.
 */
public class PropertyAnimActivity extends Activity implements View.OnClickListener {
    private ImageView mAnimImg;
    private Button mAnimBtn;
    private int imgWidth;
    private int imgHeight;
    private boolean imgIsGone = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        initView();
    }

    private void initView() {
        mAnimBtn = (Button) findViewById(R.id.anim_btn);
        mAnimImg = (ImageView) findViewById(R.id.anim_img);
    }

    private void initListener() {
        mAnimBtn.setOnClickListener(this);
    }


    /**
     * 隐藏图片
     */
    private void hideImage(int imgWidth, int imgHeight) {
        Log.e("tt", "width = " + imgWidth);
        Log.e("tt", "height = " + imgHeight);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mAnimImg.getLayoutParams();
        params.setMargins(0, -imgHeight, imgWidth, 0);
        mAnimImg.setLayoutParams(params);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        imgWidth = mAnimImg.getWidth();
        imgHeight = mAnimImg.getHeight();
        hideImage(imgWidth, imgHeight);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anim_btn:
                doAnimation(imgIsGone);
                break;
        }
    }

    /**
     * img做动画
     */
    private void doAnimation(boolean flag) {
        if (flag) {
            Log.e("tt", "tttttttttttt");
            ValueAnimator animator = ValueAnimator.ofInt(-imgHeight, 0);
            animator.setDuration(3000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    Log.e("tt", "value = " + value);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mAnimImg.getLayoutParams();
                    params.setMargins(0,value,imgWidth,imgHeight+value);
                    mAnimImg.setLayoutParams(params);
                }
            });
            animator.start();
            imgIsGone = false;
        } else {
            imgIsGone = true;
        }
    }
}

