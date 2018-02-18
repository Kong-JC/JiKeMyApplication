package com.example.jsondemo.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.jsondemo.adapter.DownLoadAdapter;
import com.example.jsondemo.bean.WeatherInfo;
import com.example.jsondemo.util.HttpUtils;
import com.example.jsondemo.util.ParserJsonUtils;

import java.util.List;


/**
 *
 */
public class DownLoadAsynctask extends AsyncTask<String,Void,List<WeatherInfo>>{
    private Context context;
    private ListView lv;

    public DownLoadAsynctask(Context context,ListView lv){
        this.context=context;
        this.lv=lv;
    }

    @Override
    protected List<WeatherInfo> doInBackground(String... params) {
        byte[] buff= HttpUtils.getHttpResult(params[0]);
        String jsonString=null;
        if(buff!=null && buff.length!=0){
            jsonString=new String(buff,0,buff.length);
        }
        //解析json字符串  List bean
        List<WeatherInfo> list= ParserJsonUtils.parserJsonToList(jsonString);
        return list;
    }

    @Override
    protected void onPostExecute(List<WeatherInfo> weatherInfos) {
        if(weatherInfos!=null && weatherInfos.size()!=0){
            DownLoadAdapter adapter=new DownLoadAdapter(weatherInfos,context);
            lv.setAdapter(adapter);
        }else{
            Log.i("Tag","下载异常!!");
        }
    }
}
















