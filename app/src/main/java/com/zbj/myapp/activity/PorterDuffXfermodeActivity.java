package com.zbj.myapp.activity;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zbj.myapp.R;
import com.zbj.myapp.weight.XfermodeView;

/**
 * Created by tiandawu on 2016/7/18.
 */
public class PorterDuffXfermodeActivity extends Activity implements View.OnClickListener {
    private static OnChangeListener listener;

    private Button mClear, mSrc, mDst, mSrcOver,
            mDstOver, mSrcIn, mDstIn, mSrcOut,
            mDstOut, mSrcATop, mDstATop, mXor,
            mDarken, mLighten, mMutiply, mScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porter_duff);
        initView();
        initListener();
    }

    private void initView() {
        mClear = (Button) findViewById(R.id.clear);
        mSrc = (Button) findViewById(R.id.src);
        mDst = (Button) findViewById(R.id.dst);
        mSrcOver = (Button) findViewById(R.id.src_over);
        mDstOver = (Button) findViewById(R.id.dst_over);
        mSrcIn = (Button) findViewById(R.id.src_in);
        mDstIn = (Button) findViewById(R.id.dest_in);
        mSrcOut = (Button) findViewById(R.id.src_out);
        mDstOut = (Button) findViewById(R.id.dst_out);
        mSrcATop = (Button) findViewById(R.id.srca_top);
        mDstATop = (Button) findViewById(R.id.dsta_top);
        mXor = (Button) findViewById(R.id.xor);
        mDarken = (Button) findViewById(R.id.darken);
        mLighten = (Button) findViewById(R.id.lithten);
        mMutiply = (Button) findViewById(R.id.multiply);
        mScreen = (Button) findViewById(R.id.screen);
    }

    private void initListener() {
        mClear.setOnClickListener(this);
        mSrc.setOnClickListener(this);
        mDst.setOnClickListener(this);
        mSrcOver.setOnClickListener(this);
        mDstOver.setOnClickListener(this);
        mSrcIn.setOnClickListener(this);
        mDstIn.setOnClickListener(this);
        mSrcOut.setOnClickListener(this);
        mDstOut.setOnClickListener(this);
        mSrcATop.setOnClickListener(this);
        mDstATop.setOnClickListener(this);
        mXor.setOnClickListener(this);
        mDarken.setOnClickListener(this);
        mLighten.setOnClickListener(this);
        mMutiply.setOnClickListener(this);
        mScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                XfermodeView.setPdMode(PorterDuff.Mode.CLEAR);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.src:
                XfermodeView.setPdMode(PorterDuff.Mode.SRC);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.dst:
                XfermodeView.setPdMode(PorterDuff.Mode.DST);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.src_over:
                XfermodeView.setPdMode(PorterDuff.Mode.SRC_OVER);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.dst_over:
                XfermodeView.setPdMode(PorterDuff.Mode.DST_OVER);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.src_in:
                XfermodeView.setPdMode(PorterDuff.Mode.SRC_IN);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.dest_in:
                XfermodeView.setPdMode(PorterDuff.Mode.DST_IN);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.src_out:
                XfermodeView.setPdMode(PorterDuff.Mode.SRC_OUT);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.dst_out:
                XfermodeView.setPdMode(PorterDuff.Mode.DST_OUT);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.srca_top:
                XfermodeView.setPdMode(PorterDuff.Mode.SRC_ATOP);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.dsta_top:
                XfermodeView.setPdMode(PorterDuff.Mode.DST_ATOP);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.xor:
                XfermodeView.setPdMode(PorterDuff.Mode.XOR);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.darken:
                XfermodeView.setPdMode(PorterDuff.Mode.DARKEN);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.lithten:
                XfermodeView.setPdMode(PorterDuff.Mode.LIGHTEN);
                if (listener != null) {
                    listener.changeState();
                }
                break;
            case R.id.multiply:
                XfermodeView.setPdMode(PorterDuff.Mode.MULTIPLY);
                if (listener != null) {
                    listener.changeState();
                }
                break;

            case R.id.screen:
                XfermodeView.setPdMode(PorterDuff.Mode.SCREEN);
                if (listener != null) {
                    listener.changeState();
                }
                break;
        }
    }

    public interface OnChangeListener {
        void changeState();
    }

    public static void setOnChangeListener(OnChangeListener listener) {
        PorterDuffXfermodeActivity.listener = listener;
    }
}
