package com.zbj.myapp.tools;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by tiandawu on 2016/7/18.
 */
public class ScreenUtils {

    /**
     * 获取屏幕的宽高
     *
     * @param context
     * @return
     */
    public static int[] getScreenHW(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int[] HW = new int[]{width, height};
        return HW;
    }


    /**
     * 获取屏幕宽
     *
     * @param context
     * @return
     */
    public static int getScreenW(Context context) {
        return getScreenHW(context)[0];
    }


    /**
     * 获取屏幕高
     *
     * @param context
     * @return
     */
    public static int getScreenH(Context context) {
        return getScreenHW(context)[1];
    }
}
