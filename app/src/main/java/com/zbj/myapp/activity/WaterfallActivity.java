package com.zbj.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.zbj.myapp.R;

/**
 * Created by tiandawu on 2016/7/16.
 */
public class WaterfallActivity extends Activity {
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.rececler_view);
    }

}
