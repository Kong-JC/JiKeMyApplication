package com.example.mycontentprovider;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ListView listView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mContentList = new ArrayList<>();

    private static final int REQ_CODE_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkContactPermission();
        initView();
        initAdapter();
    }

    private void checkContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // 未获取到相关权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQ_CODE_CONTACT);
        } else {
            query();
        }
    }

    private void query() {
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        Log.d(TAG, "query: uri:" + ContactsContract.CommonDataKinds.Phone.CONTENT_URI.toString());
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                mContentList.add("name:" + name + "\nnumber:" + number);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void initAdapter() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContentList);
        listView.setAdapter(mAdapter);
    }

    private void initView() {
        listView = findViewById(R.id.listView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQ_CODE_CONTACT && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            query();
        } else {
            Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
        }
    }
}
