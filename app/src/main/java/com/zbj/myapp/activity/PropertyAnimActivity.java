package com.zbj.myapp.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zbj.myapp.R;
import com.zbj.myapp.tools.HttpUtil;
import com.zbj.myapp.tools.StorageUtil;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by tiandawu on 2016/7/11.
 */
public class PropertyAnimActivity extends Activity implements View.OnClickListener {
    private ImageView mAnimImg;
    private Button mAnimBtn;
    private int imgWidth;
    private int imgHeight;
    private int bitmapWidth;
    private int bitmapHeight;
    private boolean imgIsGone = true;//图片是否消失

    private int startX, startY;

    private final int SAVE_IMG_FINISHED = 0;//图片从网络获取保存到SDcard完成

    private String imgPth = "http://rocko-blog.qiniudn.com/android-dev-bookmarks.jpg";//图片路径
    private String localImgPath = "";//保存到本地后图片的本地路径


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_IMG_FINISHED:
                    Log.e("tt", "图片保存成功");
                    loadImgFromSDCard();
                    hideImage(bitmapWidth, bitmapHeight);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        initView();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 从网络获取图片并保存到路径：/storage/emulated/0/com.zbj/image/  下
         */
        saveImgFromNetworker(imgPth);
    }

    /**
     * 从网络中加载图片
     *
     * @param path
     */
    private void saveImgFromNetworker(final String path) {
        new Thread() {
            @Override
            public void run() {
                try {
                    byte[] bytes = HttpUtil.getImage(path);
                    String filePath = StorageUtil.getDirByType(StorageUtil.DIR_TYPE_IMAGE);
                    String fileName = "test.png";
                    if (filePath != null) {
                        File file = new File(filePath + "/" + fileName);
                        localImgPath = file.getPath();
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(bytes, 0, bytes.length);
                        fos.close();
                        handler.sendEmptyMessage(SAVE_IMG_FINISHED);
                    } else {
                        Log.e("tt", "文件路径创建失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }.start();
    }

    /**
     * 将保存在SDCard的图片加载到ImageView
     */
    private void loadImgFromSDCard() {
        /**
         * 图片太大，先进行缩放在加载，避免OOM
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Log.e("tt", "localImgPath = " + localImgPath);
        BitmapFactory.decodeFile(localImgPath, options);
        int realWidth = options.outWidth;
        int realHeight = options.outHeight;

        Log.e("tt", "realWidth = " + realWidth);
        Log.e("tt", "realHeight = " + realHeight);

        /*计算缩放比例*/
        int scale = (realWidth > realHeight ? realWidth : realHeight) / 900;

        options.inJustDecodeBounds = false;//为false图片会加载到内存
        options.inSampleSize = scale;
        Log.e("tt", "scale = " + scale);
        Bitmap bitmap = BitmapFactory.decodeFile(localImgPath, options);
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        Log.e("tt", "bitmapWidth = " + bitmapWidth);
        Log.e("tt", "bitmapHeight = " + bitmapHeight);
        mAnimImg.setImageBitmap(bitmap);
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
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) (event.getY() - startY);
                ((View) mAnimImg.getParent()).scrollBy(0, mAnimImg.getTop() - deltaY);
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }


//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        imgWidth = mAnimImg.getWidth();
//        imgHeight = mAnimImg.getHeight();
//        hideImage(imgWidth, imgHeight);
//    }

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
            downAnimator.setDuration(2600);
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
            upAnimator.setDuration(2650);
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

