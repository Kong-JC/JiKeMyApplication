package com.example.sqlitedeom.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitedeom.MyApplication;
import com.example.sqlitedeom.R;
import com.example.sqlitedeom.manager.DBManager;

/**
 * Created by Kong on 2018/2/17.
 */
public class CourseDao implements IDao{

    private static CourseDao mInstance;

    private static Context context = MyApplication.getContext();

    private CourseDao(){}

    public static CourseDao getInstance(){
        if (mInstance == null) {
            synchronized (CourseDao.class){
                if (mInstance==null) {
                    mInstance = new CourseDao();
                }
            }
        }
        return mInstance;
    }

    public void insert(String name, String teacher, String price) {
        SQLiteDatabase db = DBManager.getInstance();
        db.execSQL(context.getString(R.string.insertSql), new String[]{name,teacher,price});
    }

    @Override
    public void clear() {
        SQLiteDatabase db = DBManager.getInstance();
        db.delete("course",null,null);
    }

}
