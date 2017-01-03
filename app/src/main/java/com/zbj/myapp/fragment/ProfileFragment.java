package com.zbj.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zbj.myapp.R;
import com.zbj.myapp.activity.ScrollerActivity;
import com.zbj.myapp.activity.SharderActivity;
import com.zbj.myapp.activity.TextViewLineActivity;

/**
 * Created by tiandawu on 2016/7/5.
 */

/**
 * 我的
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private Button mStartScrollerAct;
    private Button mStartTextViewLineAct;
    private Button mSharderAct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_profile, container, false);
        initView(view);
        initListener();
        return view;
    }


    private void initView(View view) {
        mStartScrollerAct = (Button) view.findViewById(R.id.start_scroller_activity);
        mStartTextViewLineAct = (Button) view.findViewById(R.id.start_text_line_activity);
        mSharderAct = (Button) view.findViewById(R.id.start_Sharder_activity);
    }

    private void initListener() {
        mStartScrollerAct.setOnClickListener(this);
        mStartTextViewLineAct.setOnClickListener(this);
        mSharderAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_scroller_activity:
                Intent intent = new Intent(getActivity(), ScrollerActivity.class);
                startActivity(intent);
                break;
            case R.id.start_text_line_activity:
                Intent intent1 = new Intent(getActivity(), TextViewLineActivity.class);
                startActivity(intent1);
                break;
            case R.id.start_Sharder_activity:
                Intent intent2 = new Intent(getActivity(), SharderActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
