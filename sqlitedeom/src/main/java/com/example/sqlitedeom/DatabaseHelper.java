package com.example.sqlitedeom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Kong on 2018/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COURSE_NAME = "name";
    public static final String COURSE_TEACHER = "teacher";
    public static final String COURSE_PRICE = "price";

    private static final String CREATE_COURSE = "create table Course(" +
            "_id integer primary key autoincrement," +
            COURSE_NAME + " text," +
            COURSE_TEACHER + " text," +
            COURSE_PRICE + " real)";

    private static final String CREATE_TEACHER = "create table Teacher(" +
            "_id integer primary key autoincrement," +
            "name text," +
            "age integer," +
            "desc text)";

    private Context context;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COURSE);
        db.execSQL(CREATE_TEACHER); // database version 2
        Toast.makeText(context, "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // database version 2
        db.execSQL("drop table if exists Course");
        db.execSQL("drop table if exists Teacher");
        onCreate(db);
    }

}
