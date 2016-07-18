package com.zbj.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.zbj.myapp.R;
import com.zbj.myapp.adapter.WaterfallRecyclerAdapter;
import com.zbj.myapp.tools.HttpUtil;
import com.zbj.myapp.weight.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/16.
 */
public class WaterfallActivity extends Activity {
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<String> imgeUrls;
    private WaterfallRecyclerAdapter adapter;
    private String baseUrl = "http://gank.io/api/data/福利/";
    private int pageSize = 10;
    private int page = 1;
    private String url = "";


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    adapter.notifyDataSetChanged();
                    mRefreshLayout.setRefreshing(false);
                    Log.e("tt", "_++++++++++");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_waterfall);
        initView();
        initData();
    }

    private void initView() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRefreshLayout.setRefreshing(true);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration itemDecoration = new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                pageSize = 6;
                ++page;
                loadMore();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        imgeUrls = new ArrayList<>();
        loadMore();
        adapter = new WaterfallRecyclerAdapter(this, imgeUrls);
        mRecyclerView.setAdapter(adapter);
        Log.e("tt", "==========");
    }


    /**
     * 加载更多
     */
    private void loadMore() {
        url = baseUrl + String.valueOf(pageSize) + "/" + String.valueOf(page);
        Log.e("tt", "url = " + url.toString());
        new Thread() {
            @Override
            public void run() {
                try {
                    imgeUrls.addAll(0,HttpUtil.getImageUrls(url));
                    mHandler.sendEmptyMessage(0);
                    Log.e("tt", "---------");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
