package com.zbj.myapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zbj.myapp.R;
import com.zbj.myapp.fragment.DiscoveryFragment;
import com.zbj.myapp.fragment.LiveFragment;
import com.zbj.myapp.fragment.MomentFragment;
import com.zbj.myapp.fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int LIVE_FRAG = 0;
    private static final int MOMENT_FRAG = 1;
    private static final int DISCOVERY_FRAG = 2;
    private static final int PROFILE_FRAG = 3;

    private Fragment liveFragment;
    private Fragment momentFrgment;
    private Fragment discoveryFragment;
    private Fragment profileFragment;

    private ImageView mLiveImg, mMomentImg, mDiscoveryImg, mProfileImg;
    private TextView mLiveTv, mMomentTv, mDiscoveryTv, mProfileTv, mTitleTv;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private LinearLayout mLiveTab, mMomentTab, mDiscoveryTab, mProfileTab;
    private ImageView mRecoderTab;


    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        initData();
    }

    private void initView() {

        mLiveTab = (LinearLayout) findViewById(R.id.live);
        mMomentTab = (LinearLayout) findViewById(R.id.moment);
        mDiscoveryTab = (LinearLayout) findViewById(R.id.dicovery);
        mProfileTab = (LinearLayout) findViewById(R.id.profile);
        mRecoderTab = (ImageView) findViewById(R.id.recorder);

        mLiveImg = (ImageView) findViewById(R.id.img_live);
        mMomentImg = (ImageView) findViewById(R.id.img_moment);
        mDiscoveryImg = (ImageView) findViewById(R.id.img_dicovery);
        mProfileImg = (ImageView) findViewById(R.id.img_profile);

        mLiveTv = (TextView) findViewById(R.id.tv_live);
        mMomentTv = (TextView) findViewById(R.id.tv_moment);
        mDiscoveryTv = (TextView) findViewById(R.id.tv_dicovery);
        mProfileTv = (TextView) findViewById(R.id.tv_profile);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();

        mLiveTab.setOnClickListener(this);
        mMomentTab.setOnClickListener(this);
        mDiscoveryTab.setOnClickListener(this);
        mProfileTab.setOnClickListener(this);
        mRecoderTab.setOnClickListener(this);

        mTitleTv.setText("首页");
        setSelectFragment(LIVE_FRAG);
        setTabSelected(LIVE_FRAG);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.live:
                mTitleTv.setText("首页");
                setSelectFragment(LIVE_FRAG);
                setTabSelected(LIVE_FRAG);
                Log.e("tt", "live");
                break;
            case R.id.moment:
                mTitleTv.setText("关注");
                setSelectFragment(MOMENT_FRAG);
                setTabSelected(MOMENT_FRAG);
                Log.e("tt", "moment");
                break;
            case R.id.dicovery:
                mTitleTv.setText("社区");
                setSelectFragment(DISCOVERY_FRAG);
                setTabSelected(DISCOVERY_FRAG);
                Log.e("tt", "dicovery");
                break;
            case R.id.profile:
                mTitleTv.setText("我的");
                setSelectFragment(PROFILE_FRAG);
                setTabSelected(PROFILE_FRAG);
                Log.e("tt", "profile");
                break;
            case R.id.recorder:
                Toast.makeText(this, "开始录音", Toast.LENGTH_SHORT).show();
                Log.e("tt", "recorder");
                break;
        }

    }


    /**
     * 设置选中Fragment
     *
     * @param fragFlag
     */
    private void setSelectFragment(int fragFlag) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (fragFlag) {
            case LIVE_FRAG:
                if (null == liveFragment) {
                    liveFragment = new LiveFragment();
                    fragmentTransaction.add(R.id.frame_layout, liveFragment);
                }
                fragmentTransaction.show(liveFragment);
                Log.e("tt", "LIVE_FRAG");

                break;
            case MOMENT_FRAG:
                if (null == momentFrgment) {
                    momentFrgment = new MomentFragment();
                    fragmentTransaction.add(R.id.frame_layout, momentFrgment);
                }
                fragmentTransaction.show(momentFrgment);

                Log.e("tt", "MOMENT_FRAG");

                break;
            case DISCOVERY_FRAG:
                if (null == discoveryFragment) {
                    discoveryFragment = new DiscoveryFragment();
                    fragmentTransaction.add(R.id.frame_layout, discoveryFragment);
                }
                fragmentTransaction.show(discoveryFragment);
                Log.e("tt", "DISCOVERY_FRAG");
                break;
            case PROFILE_FRAG:
                if (null == profileFragment) {
                    profileFragment = new ProfileFragment();
                    fragmentTransaction.add(R.id.frame_layout, profileFragment);
                }
                fragmentTransaction.show(profileFragment);
                Log.e("tt", "PROFILE_FRAG");
                break;
        }
        fragmentTransaction.commit();
    }


    /**
     * 设置选中的TabBar
     *
     * @param fragFlag
     */
    private void setTabSelected(int fragFlag) {
        switch (fragFlag) {
            case LIVE_FRAG:
                clearTabColor();
                mLiveImg.setSelected(true);
                mLiveTv.setTextColor(getResources().getColor(R.color.tab_color));
                break;
            case MOMENT_FRAG:
                clearTabColor();
                mMomentImg.setSelected(true);
                mMomentTv.setTextColor(getResources().getColor(R.color.tab_color));
                break;
            case DISCOVERY_FRAG:
                clearTabColor();
                mDiscoveryImg.setSelected(true);
                mDiscoveryTv.setTextColor(getResources().getColor(R.color.tab_color));
                break;
            case PROFILE_FRAG:
                clearTabColor();
                mProfileImg.setSelected(true);
                mProfileTv.setTextColor(getResources().getColor(R.color.tab_color));
                break;
        }
    }


    /**
     * 隐藏所有Fragment
     *
     * @param fragmentTransaction
     */
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {

        if (null != liveFragment) {
            fragmentTransaction.hide(liveFragment);
        }

        if (null != momentFrgment) {
            fragmentTransaction.hide(momentFrgment);
        }

        if (null != discoveryFragment) {
            fragmentTransaction.hide(discoveryFragment);
        }

        if (null != profileFragment) {
            fragmentTransaction.hide(profileFragment);
        }
    }

    /**
     * 清出tab中图片和字的颜色
     */
    private void clearTabColor() {
        mLiveImg.setSelected(false);
        mMomentImg.setSelected(false);
        mDiscoveryImg.setSelected(false);
        mProfileImg.setSelected(false);

        mLiveTv.setTextColor(getResources().getColor(R.color.text_color));
        mMomentTv.setTextColor(getResources().getColor(R.color.text_color));
        mDiscoveryTv.setTextColor(getResources().getColor(R.color.text_color));
        mProfileTv.setTextColor(getResources().getColor(R.color.text_color));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
        }

        return super.onKeyDown(keyCode, event);
    }
}
