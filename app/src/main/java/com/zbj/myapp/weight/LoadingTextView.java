package com.zbj.myapp.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.zbj.myapp.R;
import com.zbj.myapp.tools.ScreenUtils;

/**
 * Created by tiandawu on 2016/7/20.
 */
public class LoadingTextView extends View {

    private PorterDuffXfermode pdXfermode;
    private Paint mPaint;
    private Bitmap mBackBitmap;
    private int bimapW, bimapH;
    private int mCurW, mCurH, mCurTop;
    private Rect mDynamicRect;

    public LoadingTextView(Context context) {
        this(context, null);
    }

    public LoadingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mCurW = ScreenUtils.getScreenW(context);
        mCurH = ScreenUtils.getScreenH(context);

        pdXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.RED);
        mPaint.setFilterBitmap(true);

        mBackBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img_coder);
        bimapW = mBackBitmap.getWidth();
        bimapH = mBackBitmap.getHeight();

        mCurTop = bimapH;
        mDynamicRect = new Rect(0, mCurTop, bimapW, bimapH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveLayerCount = canvas.saveLayer(0, 0, mCurW, mCurH, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBackBitmap, 0, 0, mPaint);
        mPaint.setXfermode(pdXfermode);
        canvas.drawRect(mDynamicRect, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayerCount);
        mCurTop -= 2;
        if (mCurTop <= 0) {
            mCurTop = bimapH;
        }
        mDynamicRect.top = mCurTop;
        invalidate();
    }
}
