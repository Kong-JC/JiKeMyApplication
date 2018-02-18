package com.example.downloaddata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private String path="http://openapi.db.39.net/app/GetDrugCompany?sign=9DFAAD5404FCB6168EA6840DCDFF39E5&app_key=app";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv);
    }

    public void downLoad(View view){
        new DownLoadAsyncTask(tv).execute(path);
    }
}
