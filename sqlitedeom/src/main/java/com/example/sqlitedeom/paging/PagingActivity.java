package com.example.sqlitedeom.paging;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.sqlitedeom.R;
import com.example.sqlitedeom.cursoradapter.Course;
import com.example.sqlitedeom.manager.DBManager;

import java.util.ArrayList;
import java.util.List;

public class PagingActivity extends AppCompatActivity {

    private static final String TAG = "PagingActivity";

    private ListView listView;
    private MyAdapter myAdapter;
    private List<Course> courseList;
//    private DatabaseHelper helper;

    private int mSizePerPage = 10;
    private int mCurrentPage = 0;
    private boolean mHasMore = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);

        initView();
//        initDB();
        initAdapter();
        configListView();
        listView.setAdapter(myAdapter);
        getDataFromDB();

    }

    private void configListView() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(TAG, "onScrollStateChanged: " +
                        "\ngetLastVisiblePosition:" + view.getLastVisiblePosition() +
                        "\ngetCount" + view.getCount());
                if (view.getLastVisiblePosition() == view.getCount() - 1 && scrollState == SCROLL_STATE_IDLE) {
                    if (mHasMore) {
                        mCurrentPage++;
                        getDataFromDB();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void getDataFromDB() {
        int startIndex = mCurrentPage * mSizePerPage; // 0 * 10, 1 * 10, 从哪一条开始加载
        int offset = mSizePerPage; // 每页加载的数据
        SQLiteDatabase db = DBManager.getInstance();
        Cursor courses = db.rawQuery("select * from Course limit " + startIndex + "," + offset, null); // 从第0条加载到第10条
        if (courses.moveToFirst()) {
            do {
                courseList.add(new Course(
                        courses.getString(courses.getColumnIndex("name")),
                        courses.getString(courses.getColumnIndex("teacher")),
                        courses.getDouble(courses.getColumnIndex("price")) + ""
                ));
            } while (courses.moveToNext());
        }
        if (courses.getCount()<mSizePerPage){
            mHasMore = false;
        }
        courses.close();
        myAdapter.notifyDataSetChanged();
    }

    private void initAdapter() {
        courseList = new ArrayList<>();
        myAdapter = new MyAdapter(courseList);
    }

//    private void initDB() {
//        helper = DatabaseHelper.getHelper();
//    }

    private void initView() {
        listView = findViewById(R.id.listView);
    }

}
