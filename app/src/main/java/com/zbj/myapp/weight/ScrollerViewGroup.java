package com.zbj.myapp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * Created by tiandawu on 2017/1/3.
 */

public class ScrollerViewGroup extends LinearLayout {

    private TextView mContentText;
    private TextView mMenuText;
    private Scroller mScroller;
    private int downX;

    public ScrollerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        mScroller = new Scroller(context, null, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentText = (TextView) getChildAt(0);
        mMenuText = (TextView) getChildAt(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                super.onTouchEvent(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                int delX = (int) (downX - event.getX());
                if (getScrollX() + delX >= mMenuText.getWidth() || getScrollX() + delX < 0) {
                    downX = (int) event.getX();
                    return true;
                }
                scrollBy(delX, 0);
                downX = (int) event.getX();
                return true;
            case MotionEvent.ACTION_UP:
                int offset = getScrollX() / (float) mMenuText.getWidth() > 0.5f ?
                        mMenuText.getWidth() - getScrollX() : -getScrollX();
                mScroller.startScroll(getScrollX(), getScrollY(),
                        offset, 0);
                postInvalidate();
                downX = 0;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
