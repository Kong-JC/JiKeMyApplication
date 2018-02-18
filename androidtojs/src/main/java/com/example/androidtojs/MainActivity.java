package com.example.androidtojs;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;
    private WebView mWebView;
    private JsInterface jsInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText= (EditText) findViewById(R.id.text_edit);
        mButton= (Button) findViewById(R.id.btn_java);
        mWebView= (WebView) findViewById(R.id.webview);
        jsInterface=new JsInterface(mWebView);
        initWebSetttings();//初始化webview
        mWebView.loadUrl("file:///android_asset/index.html");
    }


    @SuppressLint("JavascriptInterface")
    private void initWebSetttings(){
        //允许与js交互
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置js的接口
        mWebView.addJavascriptInterface(jsInterface,"jsInterface");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int param=Integer.parseInt(mEditText.getText().toString());
                        jsInterface.java_call_js(param);
                        param=0;
                    }
                });
            }
        });
    }
}















