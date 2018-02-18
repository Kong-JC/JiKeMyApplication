package com.example.jsondemo.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络访问工具类
 */
public class HttpUtils {

    private static final String TAG = "HttpUtils";

    /**
     * 根据网络地址获取数据
     *
     * @param path 网址
     * @return 网络数据字节数组
     */
    public static byte[] getHttpResult(String path) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
//            URL url = new URL(path);
            URL url = new URL("");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                int temp = 0;
                byte[] buff = new byte[1024];
                while ((temp = inputStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, temp);
                }
            }
        } catch (Exception e) {
            try {
                Log.d(TAG, "getHttpResult: 使用本地缓存！");
                outputStream.write(data.getBytes());
            } catch (IOException e1) {
                Log.e(TAG, "getHttpResult: 本地缓存失效！", e1);
            }
            Log.e(TAG, "getHttpResult: 请求失败！", e);
        }
        return outputStream.toByteArray();
    }

    private static final String data = "{\"cityname\":\"\\u6210\\u90fd\",\"citycode\":\"101270101\",\"citydesc\":\"\\u56db\\u5ddd \\u6210\\u90fd\",\"publishtime\":\"2018\\u5e7402\\u670814\\u65e523:00\",\"lastupdate\":\"2018-02-14 23:34:56\",\"data\":[{\"date\":\"2018-02-14\",\"icon\":\"d01|n01\",\"weather\":\"\\u591a\\u4e91\",\"temperature\":\"11\\/6\\u2103\",\"winddirect\":\"\"},{\"date\":\"2018-02-15\",\"icon\":\"d01|n00\",\"weather\":\"\\u591a\\u4e91\\u8f6c\\u6674\",\"temperature\":\"17\\u2103\\/6\\u2103\",\"winddirect\":\"\"},{\"date\":\"2018-02-16\",\"icon\":\"d01|n01\",\"weather\":\"\\u591a\\u4e91\",\"temperature\":\"17\\u2103\\/7\\u2103\",\"winddirect\":\"\"},{\"date\":\"2018-02-17\",\"icon\":\"d02|n02\",\"weather\":\"\\u9634\",\"temperature\":\"17\\u2103\\/9\\u2103\",\"winddirect\":\"\"},{\"date\":\"2018-02-18\",\"icon\":\"d01|n01\",\"weather\":\"\\u591a\\u4e91\",\"temperature\":\"14\\u2103\\/7\\u2103\",\"winddirect\":\"\"},{\"date\":\"2018-02-19\",\"icon\":\"d01|n02\",\"weather\":\"\\u591a\\u4e91\\u8f6c\\u9634\",\"temperature\":\"15\\u2103\\/7\\u2103\",\"winddirect\":\"\"},{\"date\":\"2018-02-20\",\"icon\":\"d07|n07\",\"weather\":\"\\u5c0f\\u96e8\",\"temperature\":\"11\\u2103\\/5\\u2103\",\"winddirect\":\"\"}],\"live\":{\"updatetime\":\"17:00\",\"temperature\":\"21\\u2103\",\"humidity\":\"12%\",\"winddirect\":\"\\u897f\\u5357\\u98ce2\\u7ea7\"}}";

}













