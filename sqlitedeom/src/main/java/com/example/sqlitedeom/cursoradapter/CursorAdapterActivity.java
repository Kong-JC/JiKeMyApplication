package com.example.sqlitedeom.cursoradapter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.sqlitedeom.R;
import com.example.sqlitedeom.manager.DBManager;

public class CursorAdapterActivity extends AppCompatActivity {

    private ListView listView;
    private MyCursorAdapter adapter;
//    private DatabaseHelper helper;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor_adapter);
        initView();
        initDB();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new MyCursorAdapter(this,cursor,false);
        listView.setAdapter(adapter);
    }

    private void initDB() {
//        helper = DBManager.getInstance();
        cursor = DBManager.getInstance().query("Course", null, null, null, null, null, null);
    }

    private void initView() {
        listView = findViewById(R.id.listView);
    }
}
