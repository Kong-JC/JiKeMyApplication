package com.example.sqlitedeom.manager;

import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitedeom.DatabaseHelper;
import com.example.sqlitedeom.MyApplication;

/**
 * Created by Kong on 2018/2/17.
 */

public class DBManager {
    private static DatabaseHelper mHelper;
    private DBManager() {
    }
    public static SQLiteDatabase getInstance() {
        if (mHelper == null) {
            synchronized (DBManager.class) {
                if (mHelper == null) {
                    mHelper = new DatabaseHelper(MyApplication.getContext(),"CourseStore",null,3);
                }
            }
        }
        return mHelper.getWritableDatabase();
    }
}
