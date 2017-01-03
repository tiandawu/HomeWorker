package com.zbj.myapp.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tiandawu on 2017/1/3.
 */

public class MyTextViewLine extends View {

    private Paint mPaint;
    private String myText = "abcdefghijklmn";

    public MyTextViewLine(Context context) {
        super(context);
        init();
    }

    public MyTextViewLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(150);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float[] measuredWidth = new float[1];
        /**
         * 此方法用于测量指定宽度能显示的文本字符个数
         */
        int breakText = mPaint.breakText(myText, 0, myText.length(), true, getMeasuredWidth(), measuredWidth);
        Log.e("tt", "breakText = " + breakText + "    measuredWidth = " + measuredWidth[0] + "   strLength = " + myText.length());

        float measureText = mPaint.measureText(myText, 0, myText.length());
        Log.e("tt", "measureText = " + measureText);


        float baseLineX = getMeasuredWidth() / 24;
        float baseLineY = getMeasuredHeight() * 2 / 3;

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        Log.e("tt", "top = " + fontMetrics.top);
        canvas.drawLine(0, baseLineY + fontMetrics.top, getMeasuredWidth(), baseLineY + fontMetrics.top, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawLine(0, baseLineY + fontMetrics.ascent, getMeasuredWidth(), baseLineY + fontMetrics.ascent, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, baseLineY + fontMetrics.descent, getMeasuredWidth(), +baseLineY + fontMetrics.descent, mPaint);
        mPaint.setColor(Color.DKGRAY);
        canvas.drawLine(0, baseLineY + fontMetrics.bottom, getMeasuredWidth(), baseLineY + fontMetrics.bottom, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, baseLineY + fontMetrics.leading, getMeasuredWidth(), baseLineY + fontMetrics.leading, mPaint);


        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        canvas.drawText(myText, baseLineX, baseLineY, mPaint);
    }
}
