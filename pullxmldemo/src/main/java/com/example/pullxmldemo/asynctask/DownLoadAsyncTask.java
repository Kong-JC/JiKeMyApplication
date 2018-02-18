package com.example.pullxmldemo.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.pullxmldemo.adapter.DownLoadAdapter;
import com.example.pullxmldemo.bean.NewsInfo;
import com.example.pullxmldemo.util.HttpUtils;
import com.example.pullxmldemo.util.PullXmlUtils;

import java.util.List;

/**
 * 下载网络数据的异步任务
 */
public class DownLoadAsyncTask extends AsyncTask<String,Void,List<NewsInfo>>{
    private Context context;
    private ListView lv;

    public DownLoadAsyncTask(Context context,ListView lv){
        this.context=context;
        this.lv=lv;
    }
    @Override
    protected List<NewsInfo> doInBackground(String... params) {
        List<NewsInfo> list=null;
        //1.根据网络地址获取文本数据
        byte[] buff= HttpUtils.getHttpResult(params[0]);
//        String xmlStr=null;
//        if(buff!=null && buff.length!=0){
//            xmlStr=new String(buff,0,buff.length);
//        }
        //2.根据文本数据特点解析文本封装到List集合
        list= PullXmlUtils.PullXmlToList(buff);
        return list;
    }

    @Override
    protected void onPostExecute(List<NewsInfo> newsInfos) {
        if(newsInfos!=null && newsInfos.size()!=0){
            //将数据展示到Listiew
            DownLoadAdapter adapter=new DownLoadAdapter(newsInfos,context);
            lv.setAdapter(adapter);
        }else{
            Log.i("tag","数据加载异常!!");
        }
    }
}


















