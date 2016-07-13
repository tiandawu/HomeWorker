package com.zbj.myapp.tools;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tiandawu on 2016/7/13.
 */
public class HttpUtil {

    /**
     * 获取网络图片数据
     *
     * @param path url路径
     * @return 字节数组
     * @throws Exception
     */
    public static byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inputStream = conn.getInputStream();
        byte[] result = StreamTools.read(inputStream);
        inputStream.close();
        conn.disconnect();
        return result;
    }
}
