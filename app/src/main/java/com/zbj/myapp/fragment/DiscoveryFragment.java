package com.zbj.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zbj.myapp.R;
import com.zbj.myapp.activity.FindNumberActivity;
import com.zbj.myapp.activity.MyViewActivity;
import com.zbj.myapp.activity.PorterDuffXfermodeActivity;
import com.zbj.myapp.activity.PropertyAnimActivity;
import com.zbj.myapp.activity.WaterfallActivity;
import com.zbj.myapp.manager.ActivityManager;

/**
 * Created by tiandawu on 2016/7/5.
 */

/**
 * 社区
 */
public class DiscoveryFragment extends Fragment implements View.OnClickListener {

    private Button mStartFindNumAct;//打开找出100以内奇数的页面
    private Button mStartAnimAct;//打开属性动画的Activity页面
    private Button mStartMyViewAct;//打开MyView页面
    private Button mStartWaterfallAct;//打开RecyclerViewAct页面
    private Button mStartPorterDuffXfermodeAct;//打开PorterDuffXfermode页面

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_discovery, container, false);
        initView(view);
        initListener();
        return view;
    }

    private void initView(View view) {
        mStartFindNumAct = (Button) view.findViewById(R.id.start_find_number_act);
        mStartAnimAct = (Button) view.findViewById(R.id.start_animation_act);
        mStartMyViewAct = (Button) view.findViewById(R.id.start_myview_act);
        mStartWaterfallAct = (Button) view.findViewById(R.id.start_waterfall_act);
        mStartPorterDuffXfermodeAct = (Button) view.findViewById(R.id.start_PorterDuffXfermode_act);
    }

    private void initListener() {
        mStartFindNumAct.setOnClickListener(this);
        mStartAnimAct.setOnClickListener(this);
        mStartMyViewAct.setOnClickListener(this);
        mStartWaterfallAct.setOnClickListener(this);
        mStartPorterDuffXfermodeAct.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_find_number_act:
                ActivityManager.startActivity(getActivity(), FindNumberActivity.class);
                break;
            case R.id.start_animation_act:
                ActivityManager.startActivity(getActivity(), PropertyAnimActivity.class);
                break;
            case R.id.start_myview_act:
                ActivityManager.startActivity(getActivity(), MyViewActivity.class);
                break;
            case R.id.start_waterfall_act:
                ActivityManager.startActivity(getActivity(), WaterfallActivity.class);
                break;
            case R.id.start_PorterDuffXfermode_act:
                ActivityManager.startActivity(getActivity(), PorterDuffXfermodeActivity.class);
                break;
        }
    }
}
