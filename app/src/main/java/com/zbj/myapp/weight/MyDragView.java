package com.zbj.myapp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by tiandawu on 2016/7/11.
 */
public class MyDragView extends ViewGroup {
    public MyDragView(Context context) {
        this(context, null);
    }

    public MyDragView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
