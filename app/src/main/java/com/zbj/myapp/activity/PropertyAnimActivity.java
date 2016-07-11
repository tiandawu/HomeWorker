package com.zbj.myapp.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

    private int startX, startY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        initView();
        initListener();
    }

    private void initView() {
        mAnimBtn = (Button) findViewById(R.id.anim_btn);
        mAnimImg = (ImageView) findViewById(R.id.anim_img);
    }

    private void initListener() {
        mAnimBtn.setOnClickListener(this);
        mAnimImg.setOnClickListener(this);
    }


    /**
     * 隐藏图片
     */
    private void hideImage(int imgWidth, int imgHeight) {
        Log.e("tt", "width = " + imgWidth);
        Log.e("tt", "height = " + imgHeight);
        ((View) mAnimImg.getParent()).scrollTo(0, imgHeight);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Log.e("tt", "---------");
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("tt", "111111111");
                int deltaY = (int) (event.getY() - startY);
                if (deltaY > 10) {
                    ((View) mAnimImg.getParent()).scrollBy(0, mAnimImg.getTop()-deltaY);
                }

                break;
            case MotionEvent.ACTION_UP:
//                Log.e("tt", "222222");
                break;
        }
        return true;
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
            case R.id.anim_img:
                Toast.makeText(this, "我是图片", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * img做动画
     */
    private void doAnimation(boolean flag) {
        /**
         * 防止图片在移动过程中被点击
         */
        mAnimImg.setClickable(false);

        if (flag) {
            /**
             * 图片向下移动
             */
            ValueAnimator downAnimator = ValueAnimator.ofInt(8, 0);
            downAnimator.setDuration(2500);
            downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    Log.e("tt", "value = " + value);
                    ((View) mAnimImg.getParent()).scrollBy(0, mAnimImg.getTop() - value);
                    if (value == 0) {
                        mAnimBtn.setText("上");
                        /**
                         * 图片停止移动，设置可以点击
                         */
                        mAnimImg.setClickable(true);
                    }
                }
            });
            downAnimator.start();
            imgIsGone = false;
        } else {
            /**
             * 图片向上移动
             */
            ValueAnimator upAnimator = ValueAnimator.ofInt(0, 10);
            upAnimator.setDuration(2500);
            upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();
                    Log.e("tt", "value = " + value);
                    ((View) mAnimImg.getParent()).scrollBy(0, mAnimImg.getTop() + value);
                    if (value == 10) {
                        mAnimBtn.setText("下");
                        /**
                         * 图片停止移动，设置可以点击
                         */
                        mAnimImg.setClickable(true);
                    }
                }
            });
            upAnimator.start();
            imgIsGone = true;
        }
    }
}

