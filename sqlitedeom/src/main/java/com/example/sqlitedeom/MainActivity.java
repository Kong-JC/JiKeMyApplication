package com.example.sqlitedeom;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sqlitedeom.cursoradapter.CursorAdapterActivity;
import com.example.sqlitedeom.dao.CourseDao;
import com.example.sqlitedeom.manager.DBManager;
import com.example.sqlitedeom.paging.PagingActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btnCreate;
    private Button btnAddSql, btnDelSql, btnUpdateSql, btnQuerySql;
    private Button btnAddApi, btnDelApi, btnUpdateApi, btnQueryApi;
    private Button btnGoActivity;
    private Button btnTransaction;
    private Button btnGoPagingActivity;

//    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initDB();
    }

//    private void initDB() {
//        helper = DatabaseHelper.getHelper();
//    }

    private void initView() {
        btnCreate = findViewById(R.id.btnCreate);
        btnAddSql = findViewById(R.id.btnAddSql);
        btnDelSql = findViewById(R.id.btnDelSql);
        btnUpdateSql = findViewById(R.id.btnUpdateSql);
        btnQuerySql = findViewById(R.id.btnQuerySql);
        btnAddApi = findViewById(R.id.btnAddApi);
        btnDelApi = findViewById(R.id.btnDelApi);
        btnUpdateApi = findViewById(R.id.btnUpdateApi);
        btnQueryApi = findViewById(R.id.btnQueryApi);
        btnGoActivity = findViewById(R.id.btnGoActivity);
        btnTransaction = findViewById(R.id.btnTransaction);
        btnGoPagingActivity = findViewById(R.id.btnGoPagingActivity);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnAddSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBySql();
            }
        });
        btnDelSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delBySql();
            }
        });
        btnUpdateSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBySql();
            }
        });
        btnQuerySql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryBySql();
            }
        });
        btnAddApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addByApi();
            }
        });
        btnDelApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delByApi();
            }
        });
        btnUpdateApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateByApi();
            }
        });
        btnQueryApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryByApi();
            }
        });
        btnGoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity();
            }
        });
        btnTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTransaction();
            }
        });
        btnGoPagingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoPageActivity();
            }
        });

    }

    private void GoPageActivity() {
        startActivity(new Intent(this, PagingActivity.class));
    }

    private void doTransaction() {
        SQLiteDatabase db = DBManager.getInstance();
        db.beginTransaction();
        try {
            db.delete("Course", null, null);
//            if (true) {
//                throw new NullPointerException();
//            }
            addByApi();
            db.setTransactionSuccessful();
        } catch (NullPointerException e) {
//            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private void goActivity() {
        startActivity(new Intent(this, CursorAdapterActivity.class));
    }

    private void queryByApi() {
        Cursor cursor = DBManager.getInstance().query("Course", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String teacher = cursor.getString(cursor.getColumnIndex("teacher"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d(TAG, "queryByApi: \nname:" + name + "\nteacher:" + teacher + "\nprice" + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void updateByApi() {
        ContentValues values = new ContentValues();
        values.put("teacher", "Jikexueyuan");
        values.put("price", "5.12");
        DBManager.getInstance().update("Course", values, "name = ?", new String[]{"Sqlite"});
    }

    private void delByApi() {
        DBManager.getInstance().delete("Course", "teacher = ?", new String[]{"Jike"});
    }

    private void addByApi() {
        ContentValues values = new ContentValues();
        values.put("name", "Database");
        values.put("teacher", "Jike");
        values.put("price", "20.48");
        DBManager.getInstance().insert("Course", null, values);
    }

    private void queryBySql() {
        SQLiteDatabase db = DBManager.getInstance();
        Cursor cursor = db.rawQuery("select * from Course", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String teacher = cursor.getString(cursor.getColumnIndex("teacher"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d(TAG, "queryBySql: \nname:" + name + "\nteacher:" + teacher + "\nprice" + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void updateBySql() {
        SQLiteDatabase db = DBManager.getInstance();
        db.execSQL(getString(R.string.updateSql), new String[]{"5.12", "Sqlite"});
    }

    private void delBySql() {
        SQLiteDatabase db = DBManager.getInstance();
        db.execSQL(getString(R.string.deleteSql), new String[]{"icodeyou"});
    }

    private void addBySql() {
//        SQLiteDatabase db = DBManager.getInstance();
//        db.execSQL(getString(R.string.insertSql), new String[]{"Sqlite", "icodeyou", "10.24"});
        CourseDao.getInstance().insert("Sqlite", "icodeyou", "10.24");
    }

}
