package com.example.downloaddata;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 */
public class DownLoadAsyncTask extends AsyncTask<String,Void,String>{

    private TextView tv;

    public DownLoadAsyncTask(TextView tv){
        this.tv=tv;
    }

    @Override
    protected String doInBackground(String... params) {
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        try{
            URL url=new URL(params[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int responseCode=connection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream=connection.getInputStream();
                int temp=0;
                byte[] buff=new byte[1024];
                while((temp=inputStream.read(buff))!=-1){
                    outputStream.write(buff,0,temp);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return outputStream.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        if(!"".equals(s) && s!=null){
            tv.setText(s);
        }else{
            Log.i("Tag","下载异常！");
        }
    }
}








