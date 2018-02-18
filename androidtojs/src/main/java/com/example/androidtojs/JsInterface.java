package com.example.androidtojs;

import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * 伪接口 主要为了方便理解以及做抽象处理
 */
public class JsInterface {
    private WebView mWebView;
    public JsInterface(WebView webView){
        this.mWebView=webView;
    }

    //提供函数Android调用JS
    public void java_call_js(int param){
        mWebView.loadUrl(String.format("javascript:java_call_js("+param+")"));
    }

    // 提供给js调用android的方法
    @JavascriptInterface
    public void js_call_java(){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //主线程更新ui
                Toast.makeText(mWebView.getContext(),"in java Text",Toast.LENGTH_LONG).show();
            }
        });
    }
}
