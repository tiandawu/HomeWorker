package com.zbj.myapp.tools;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import java.io.File;


/**
 * Created by tiandawu on 2016/7/13.
 */
public class StorageUtil {

    public static final String EXTERNAL_STORAGE = Environment.getExternalStorageDirectory().getPath()+"/com.zbj";

    public static final String IMAGE_DIR = EXTERNAL_STORAGE + "/image";

    public static final int DIR_TYPE_IMAGE = 0;


    /**
     * 根据类型返回文件路径
     *
     * @param type 文件类型
     * @return 文件路径；返回为null时表示失败
     */
    public static String getDirByType(int type) {

        String dir = "";
        String filePath = "";

        switch (type) {
            case DIR_TYPE_IMAGE:
                filePath = IMAGE_DIR;
                break;
        }
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }

        if (file.exists()) {
            if (file.isDirectory()) {
                dir = file.getPath();
            }
        } else {
            // 文件没创建成功，可能是sd卡不存在,返回null
            dir = null;
        }
        return dir;
    }


    /**
     * 检查外部存储是否可用
     *
     * @return
     */
    public static boolean isSDCardExist() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 检查SD卡剩余空间是否可用
     *
     * @param needSize 需要的空间大小
     * @param context  上下文
     * @return true表示空间足够
     */
    public static boolean checkExternalSpace(float needSize, Context context) {
        boolean flag = false;
        if (isSDCardExist()) {
            StatFs statFs = new StatFs(EXTERNAL_STORAGE);
            long blockSize = statFs.getBlockSize();
            long availCount = statFs.getBlockCount();
            long resetSize = blockSize * availCount;
            if (resetSize > needSize) {
                flag = true;
            } else {
                Toast.makeText(context, "SD卡剩余空间不足！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "SD卡不存在！", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

}
