package com.zbj.myapp.weight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zbj.myapp.R;
import com.zbj.myapp.tools.ScreenUtils;

/**
 * Created by tiandawu on 2016/7/19.
 */
public class StripMeizi extends View {

    private Paint mPaint;
    private Bitmap mBeforeBitmap;
    private Bitmap mBackBitmap;
    private PorterDuffXfermode pdXfermode;
    private Path mPath;
    private Canvas mCanvas;

    private int mScreenW;
    private int mScreenH;

    private int mLastX, mLastY;

    public StripMeizi(Context context) {
        this(context, null);
    }

    public StripMeizi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StripMeizi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        pdXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(80);

        mScreenW = ScreenUtils.getScreenW(context);
        mScreenH = ScreenUtils.getScreenH(context);
        mBackBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.meizi_back);
        mBackBitmap = Bitmap.createScaledBitmap(mBackBitmap, mScreenW, mScreenH, false);

        mBeforeBitmap = Bitmap.createBitmap(mScreenW, mScreenH, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBeforeBitmap);
        mCanvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.meizi_before), null,
                new RectF(0, 0, mScreenW, mScreenH), mPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBackBitmap, 0, 0, null);
        drawpath();
        canvas.drawBitmap(mBeforeBitmap, 0, 0, null);
    }

    private void drawpath() {
        mPaint.setXfermode(pdXfermode);
        mCanvas.drawPath(mPath, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs((int) (event.getX() - mLastX));
                int dy = Math.abs((int) (event.getY() - mLastY));
                if (dx > 3 || dy > 3) {
                    mPath.lineTo(event.getX(), event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
        }
        invalidate();
        return true;
    }
}
