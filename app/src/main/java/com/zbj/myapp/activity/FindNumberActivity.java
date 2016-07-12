package com.zbj.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zbj.myapp.R;
import com.zbj.myapp.adapter.MyRecyclerViewAdapter;
import com.zbj.myapp.bean.OddNumber;
import com.zbj.myapp.db.NumberDao;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/8.
 */
public class FindNumberActivity extends Activity implements View.OnClickListener {

    private Button findNumber;//找出所有奇数
    private Button findCondNumber;//找出所有能被3整除的数
    private Button clearDatabase;//清除数据库
    private Button resetNumber;//重置100个数
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private NumberDao numberDao;
    private ArrayList<Integer> allNumbers;

    private final int FIND_NUMBER_FINISH = 0;
    private final int FIND_COND_NUMBER_FINISH = 1;
    private final int NO_DATA = 2;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FIND_NUMBER_FINISH:
                    /**
                     * 通知数据改变
                     */
                    adapter.notifyDataSetChanged();
                    findNumber.setText("所有奇数已找到,并保存到数据库");
                    Toast.makeText(FindNumberActivity.this, "全部找出，并保存完毕", Toast.LENGTH_SHORT).show();
                    break;
                /**
                 * 从数据库中找出所有能被3整除的数完毕
                 */
                case FIND_COND_NUMBER_FINISH:
                    adapter.notifyDataSetChanged();
                    Toast.makeText(FindNumberActivity.this, "全部找出", Toast.LENGTH_SHORT).show();
                    break;
                case NO_DATA:
                    Toast.makeText(FindNumberActivity.this, "数据库中没有数据", Toast.LENGTH_SHORT).show();
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
        findCondNumber = (Button) findViewById(R.id.find_cond_number);
        clearDatabase = (Button) findViewById(R.id.clear_database);
        resetNumber = (Button) findViewById(R.id.reset_number);
        recyclerView = (RecyclerView) findViewById(R.id.rececler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {

        findNumber.setOnClickListener(this);
        findCondNumber.setOnClickListener(this);
        clearDatabase.setOnClickListener(this);
        resetNumber.setOnClickListener(this);
        numberDao = new NumberDao(this);
        /**
         * 初始化1-100的数
         */
        allNumbers = new ArrayList<>();
        initNumber();

        adapter = new MyRecyclerViewAdapter(this, allNumbers);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化1-100的数
     */
    private void initNumber() {
        for (int i = 0; i < 100; i++) {
            allNumbers.add(i + 1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_number:
                doFindWorker();
                break;
            /**
             * 找出能被3整除的数
             */
            case R.id.find_cond_number:
                doFindCondNumberWorker();
                break;
            case R.id.clear_database:
                if (numberDao.deleteDatabase()) {
                    Toast.makeText(this, "数据已经全部删除", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "数据不存在", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.reset_number:
                allNumbers.clear();
                initNumber();
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "初始化成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 从数据库找出所有能被3整除的数
     */
    private void doFindCondNumberWorker() {
        new Thread() {
            @Override
            public void run() {
                ArrayList<OddNumber> list = numberDao.queryAll();
                if (list.size() > 0) {
                    allNumbers.clear();
                } else {
                    mHandler.sendEmptyMessage(NO_DATA);
                    return;
                }
                for (OddNumber oddNumber : list) {
                    int number = Integer.parseInt(oddNumber.getOddNumber());
                    if (number % 3 == 0) {
                        allNumbers.add(number);
                    }
                }

                mHandler.sendEmptyMessage(FIND_COND_NUMBER_FINISH);
            }
        }.start();
    }


    /**
     * 从1-100找出所有奇数并存储数据库
     */
    private void doFindWorker() {
        /**
         * 开启子线程，在子线程中寻找1-100中所有的基数
         */

        final ArrayList<Integer> result = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                OddNumber oddNumber = null;
                ArrayList<OddNumber> listData = new ArrayList<OddNumber>();
                int i = 0;
                for (int num : allNumbers) {
                    if (!(num % 2 == 0)) {
                        oddNumber = new OddNumber();
                        oddNumber.setId(String.valueOf(i++));
                        oddNumber.setOddNumber(String.valueOf(num));
                        listData.add(oddNumber);
                        result.add(num);
                    }
                }

                allNumbers.clear();
                /**
                 * 插入数据到数据库
                 */
                numberDao.insertListData(listData);

                allNumbers.addAll(result);
                /**
                 * for循环结束表示，奇数全部找出，下一步通过Handler发消息到主线程，显示数据
                 */
                mHandler.sendEmptyMessage(FIND_NUMBER_FINISH);
            }
        }.start();
    }

}
