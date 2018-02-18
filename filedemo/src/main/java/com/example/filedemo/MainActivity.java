package com.example.filedemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInteriorFile, btnExternalFile;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
    }

    private void initView() {
        btnInteriorFile = findViewById(R.id.btnInteriorFile);
        btnExternalFile = findViewById(R.id.btnExternalFile);
        btnInteriorFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InteriorFileActivity.class));
            }
        });
        btnExternalFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ExternalFileActivity.class));
            }
        });
    }

}
