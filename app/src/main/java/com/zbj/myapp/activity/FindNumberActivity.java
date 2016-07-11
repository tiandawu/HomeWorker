package com.zbj.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zbj.myapp.R;
import com.zbj.myapp.adapter.MyRecyclerViewAdapter;

/**
 * Created by tiandawu on 2016/7/8.
 */
public class FindNumberActivity extends Activity implements View.OnClickListener {

    private Button findNumber;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;

    private int[] allNumbers;
    private int[] results;

    private final int FIND_NUMBER_FINISH = 0;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FIND_NUMBER_FINISH:
                    adapter = null;//将之前的对象清除，防止过多的对象
                    adapter = new MyRecyclerViewAdapter(FindNumberActivity.this, results);
                    recyclerView.setAdapter(adapter);
                    findNumber.setText("所有奇数已找到");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_number);

        initView();
        initData();
    }

    private void initView() {
        findNumber = (Button) findViewById(R.id.find_number);
        recyclerView = (RecyclerView) findViewById(R.id.rececler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        findNumber.setOnClickListener(this);

        /**
         * 初始化1-100的数
         */
        allNumbers = new int[100];
        for (int i = 0; i < 100; i++) {
            allNumbers[i] = i + 1;
        }

        adapter = new MyRecyclerViewAdapter(this, allNumbers);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_number:
                /**
                 * 开启子线程，在子线程中寻找1-100中所有的基数
                 */

                results = new int[50];
                new Thread() {
                    @Override
                    public void run() {
                        int i = 0;
                        for (int num : allNumbers) {
                            if (!(num % 2 == 0)) {
                                results[i] = num;
                                i++;
                            }
                        }
                        /**
                         * for循环结束表示，奇数全部找出，下一步通过Handler发消息到主线程，显示数据
                         */

                        mHandler.sendEmptyMessage(FIND_NUMBER_FINISH);
                    }
                }.start();
                break;
        }
    }

}
