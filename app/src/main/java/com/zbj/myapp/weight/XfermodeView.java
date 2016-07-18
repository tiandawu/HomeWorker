package com.zbj.myapp.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zbj.myapp.tools.ScreenUtils;

/**
 * Created by tiandawu on 2016/7/18.
 */
public class XfermodeView extends View {

    private PorterDuffXfermode pdXfermod;
    private static PorterDuff.Mode PD_MODE = PorterDuff.Mode.ADD;
    private int scrennWidth, scrennHeight;
    private Bitmap srcBitmap, dstBitmap;

    private Paint mPaint;

    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        pdXfermod = new PorterDuffXfermode(PD_MODE);
        scrennWidth = ScreenUtils.getScreenW(context);
        scrennHeight = ScreenUtils.getScreenH(context);

        srcBitmap = makeSrc(300, 300);
        dstBitmap = makeDst(300, 300);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFilterBitmap(false);
    }

    private Bitmap makeDst(int width, int height) {
        Bitmap bimap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas cancas = new Canvas(bimap);
        Paint mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setColor(0xFF26AAD1);
        cancas.drawOval(new RectF(0, 0, width * 3 / 4, height * 3 / 4), mDstPaint);
        return bimap;
    }

    private Bitmap makeSrc(int width, int height) {
        Bitmap bimap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bimap);
        Paint mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setColor(0xFFFFCE43);
        canvas.drawRect(width / 3, height / 3, width * 19 / 20, height * 19 / 20, mSrcPaint);
        return bimap;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
