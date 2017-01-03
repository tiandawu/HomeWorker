package com.zbj.myapp.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.zbj.myapp.R;

/**
 * Created by tiandawu on 2017/1/3.
 */

public class BitmapShaderView extends View {


    private Paint mPaint;
    private Bitmap mBitmap;

    public BitmapShaderView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        float scale = getMeasuredWidth() / mBitmap.getWidth() * 1.0f;
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);//缩放图片
        mPaint.setShader(bitmapShader);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredWidth() / 2, getMeasuredWidth() / 2, mPaint);
    }
}
