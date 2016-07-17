package com.zbj.myapp.tools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

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


    public static InputStream getImageStream(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inputStream = conn.getInputStream();
        return inputStream;
    }

    public static ArrayList<String> getImageUrls(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(5 * 1000);
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("请求url失败");
        }
        InputStream inputStream = connection.getInputStream();
        StringBuilder builder = new StringBuilder();
        byte[] result = StreamTools.read(inputStream);
        builder.append(new String(result));
        JSONObject jsonObject = new JSONObject(builder.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        ArrayList<String> imgUrls = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            String imgUrl = object.getString("url");
            imgUrls.add(imgUrl);
            Log.e("tt", "imgURl = " + imgUrl);
        }
        return imgUrls;
    }
}
