package com.example.assetsdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetActivity extends AppCompatActivity {

    private static final String TAG = "AssetActivity";

    private Button btnReadTxtAssets, btnReadHtmlAssets;
    private TextView tvContent;
    private WebView wvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnReadTxtAssets = findViewById(R.id.btnReadTxtAssets);
        btnReadHtmlAssets = findViewById(R.id.btnReadHtmlAssets);
        tvContent = findViewById(R.id.tvContent);
        wvContent = findViewById(R.id.wvContent);
        btnReadTxtAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = readAssetsContent("asset.txt");
                tvContent.setText(result);
            }
        });
        btnReadHtmlAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                WebSettings settings = wvContent.getSettings();
//                //设置自适应屏幕，两者合用
//                settings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
//                settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//                // 缩放操作
//                settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//                settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//                settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
                wvContent.loadUrl("file:///android_asset/Use_WebView_JianShu.htm");
            }
        });
    }

    /**
     * 读取 Assets 指定文件的内容
     */
    @Nullable // null 注解 代表函数有可能返回 null，提醒调用该函数的上层开发人员
    private String readAssetsContent(String fileName) {
        InputStream fis = null;
        BufferedReader reader = null;
        StringBuilder builder;
        try {
            fis = getAssets().open(fileName);
            reader = new BufferedReader(new InputStreamReader(fis));
            builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            try {
                if (fis != null) fis.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
