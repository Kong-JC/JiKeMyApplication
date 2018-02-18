package com.example.shardpreferencedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferencesUtil.getInstance().put(this,"key","value");
        SharedPreferencesUtil.getInstance().get(this,"key","defValue");
    }
}
