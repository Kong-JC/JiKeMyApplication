package com.example.jsondemo.jsondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.jsondemo.R;
import com.example.jsondemo.asynctask.DownLoadAsynctask;

/**
 * 需求:根据网络地址加载获取字符串 解析后展示到ListView中
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private String path="http://weather.xcyh.org/json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.lv);
        new DownLoadAsynctask(this,lv).execute(path);
    }
}
