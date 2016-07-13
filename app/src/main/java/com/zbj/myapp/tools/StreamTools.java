package com.zbj.myapp.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by tiandawu on 2016/7/13.
 */
public class StreamTools {

    /**
     * 从一个输入流中读取数据
     *
     * @param inputStream 输入流
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] read(InputStream inputStream) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        inputStream.close();
        bos.close();
        return bos.toByteArray();
    }
}
