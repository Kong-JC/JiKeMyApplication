package com.example.neterrorview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout mErrorView;
    FrameLayout mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorView = (FrameLayout) findViewById(R.id.error_view);
        mContentView = (FrameLayout) findViewById(R.id.container);

        mErrorView.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // loading...

//        mErrorView.setVisibility(View.VISIBLE);
    }
}
