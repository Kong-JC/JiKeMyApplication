package com.example.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 演示图片下载的异步任务
 */
public class DownLoadAsyncTask extends AsyncTask<String,Void,byte[]>{
    private ImageView iv;

    public DownLoadAsyncTask(ImageView iv){
        this.iv=iv;
    }

    @Override
    protected byte[] doInBackground(String... params) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        try{
            //1.将url网络地址字符串封装成url对象
            URL url=new URL(params[0]);
            //2.打开url对象的网络链接  获取网络链接对象
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            //3.进行网络链接的设置
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);

            //4.获取响应状态码
            int responseCode=conn.getResponseCode();
            //5.判断状态码  如果为200说明成功则进行读取操作
            if(responseCode==200){
                InputStream inputStream=conn.getInputStream();
                int temp=0;
                byte[] buff=new byte[1024];
                while((temp=inputStream.read(buff))!=-1){
                    outputStream.write(buff,0,temp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        if(bytes!=null && bytes.length!=0){
            //将字节数组转换成bitmap对象展示
            Bitmap bm= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            iv.setImageBitmap(bm);
        }else{
            Log.i("Tag","下载异常！！");
        }
    }
}










