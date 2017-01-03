package com.zbj.myapp.weight;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tiandawu on 2017/1/3.
 */

public class ViewDragHelperLayout extends LinearLayout {
    private ViewDragHelper mDragHelper;
    private TextView mContentChild;
    private TextView mMenuChild;

    public ViewDragHelperLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDragHelper = ViewDragHelper.create(this, new MyCallback());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentChild = (TextView) getChildAt(0);
        mMenuChild = (TextView) getChildAt(1);
    }

    private class MyCallback extends ViewDragHelper.Callback {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mContentChild || child == mMenuChild;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (releasedChild == mContentChild) {
                if (mContentChild.getLeft() <= -mMenuChild.getWidth() / 2) {
                    openMenu();
                } else {
                    closeMenu();
                }
            }

            if (releasedChild == mMenuChild) {
                if (mMenuChild.getLeft() <= mContentChild.getWidth() - mMenuChild.getWidth() / 2) {
                    openMenu();
                } else {
                    closeMenu();
                }
            }

        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return mMenuChild.getWidth();
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if (child == mContentChild) {
                if (left < (-mMenuChild.getWidth())) {
                    left = -mMenuChild.getWidth();
                }
                if (left > 0) {
                    left = 0;
                }
            }
            if (child == mMenuChild) {
                if (left < mContentChild.getWidth() - mMenuChild.getWidth()) {
                    left = mContentChild.getWidth() - mMenuChild.getWidth();
                }
                if (left > mContentChild.getWidth()) {
                    left = mContentChild.getWidth();
                }
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            if (changedView == mContentChild) {
                mMenuChild.layout(mMenuChild.getLeft() + dx, mMenuChild.getTop(),
                        mMenuChild.getRight() + dx, mMenuChild.getBottom());
            }
            if (changedView == mMenuChild) {
                mContentChild.layout(mContentChild.getLeft() + dx, mContentChild.getTop(),
                        mContentChild.getRight() + dx, mContentChild.getBottom());
            }
        }

        private void openMenu() {
            mDragHelper.smoothSlideViewTo(mMenuChild, mContentChild.getWidth() - mMenuChild.getWidth(), mMenuChild.getTop());
            mDragHelper.smoothSlideViewTo(mContentChild, -mMenuChild.getWidth(), mContentChild.getTop());
            ViewCompat.postInvalidateOnAnimation(ViewDragHelperLayout.this);
        }

        private void closeMenu() {
            mDragHelper.smoothSlideViewTo(mMenuChild, mContentChild.getWidth() - mContentChild.getRight(), mMenuChild.getTop());
            mDragHelper.smoothSlideViewTo(mContentChild, 0, mContentChild.getTop());
            ViewCompat.postInvalidateOnAnimation(ViewDragHelperLayout.this);
        }
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(ViewDragHelperLayout.this);
        }
    }
}

