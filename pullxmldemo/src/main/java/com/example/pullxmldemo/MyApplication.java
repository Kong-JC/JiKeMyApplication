package com.example.pullxmldemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Kong on 2018/2/15.
 */

public class MyApplication extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
