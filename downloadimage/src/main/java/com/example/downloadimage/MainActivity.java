package com.example.downloadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    private String imagePath="http://e.jikexueyuan.com/headerandfooter/images/logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv= (ImageView) findViewById(R.id.iv);
    }
    //点击按钮执行图片下载
    public void downLoad(View view){
        new DownLoadAsyncTask(iv).execute(imagePath);
    }
}
