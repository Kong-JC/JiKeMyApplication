package com.example.shardpreferencedemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * Created by Kong on 2018/2/16.
 */

public class SharedPreferencesUtil {

    private static final String FILE_NAME = "SP_File";

    private static SharedPreferencesUtil mInstance;

    public SharedPreferencesUtil() {
    }

    public static SharedPreferencesUtil getInstance() {
        if (mInstance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferencesUtil();
                }
            }
        }
        return mInstance;
    }

    public void put(Context context, String key, Object value) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        String type = value.getClass().getSimpleName();
        switch (type) {
            case "Integer":
                editor.putInt(key, (Integer) value);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) value);
                break;
            case "String":
                editor.putString(key, (String) value);
                break;
            case "Float":
                editor.putFloat(key, (Float) value);
                break;
            case "Long":
                editor.putLong(key, (Long) value);
                break;
        }
        editor.apply();
    }

    @Nullable
    public Object get(Context context, String key, Object defValue) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        String type = defValue.getClass().getSimpleName();
        switch (type) {
            case "Integer":
                return sp.getInt(key, (Integer) defValue);
            case "Boolean":
                return sp.getBoolean(key, (Boolean) defValue);
            case "String":
                return sp.getString(key, (String) defValue);
            case "Float":
                return sp.getFloat(key, (Float) defValue);
            case "Long":
                return sp.getLong(key, (Long) defValue);
        }
        return null;
    }

}
