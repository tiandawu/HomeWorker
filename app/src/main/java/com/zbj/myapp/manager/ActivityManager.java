package com.zbj.myapp.manager;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by tiandawu on 2016/7/8.
 */
public class ActivityManager {

    /**
     * 打开一个Activity不关闭上一个Activity
     *
     * @param activity 当前Activity
     * @param cls      跳转d到的Activity
     */
    public static void startActivity(Activity activity, Class cls) {
        startActivity(activity, cls, false);
    }

    /**
     * 打开一个Activity,并关闭当前Activity
     *
     * @param activity   当前Activity
     * @param cls        跳转d到的Activity
     * @param isCloseAct 是否关闭当前Activity
     */
    public static void startActivity(Activity activity, Class cls, boolean isCloseAct) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        if (isCloseAct) {
            activity.finish();
        }
    }


}
