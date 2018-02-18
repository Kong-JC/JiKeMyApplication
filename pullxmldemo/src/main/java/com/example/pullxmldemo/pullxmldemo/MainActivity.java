package com.example.pullxmldemo.pullxmldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.pullxmldemo.R;
import com.example.pullxmldemo.asynctask.DownLoadAsyncTask;

/**
 * 需求：根据指定的网络地址加载相应的信息 解析后展示到ListView中
 */
public class MainActivity extends AppCompatActivity {
    private String path="http://m.junshi81.com/data/rss/15q.xml";
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.lv);
        new DownLoadAsyncTask(this,lv).execute(path);
    }
}
