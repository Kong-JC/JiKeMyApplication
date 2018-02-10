package com.example.mydetailedlist.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.mydetailedlist.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Kong on 2018/2/10.
 */

public class SPHelper {

    //SharedPerference存放的路径
    private static String FILE_NAME = "myList";

    //键值对的key
    private static String KEY = "myList";

    /**
     * 保存待办事项数组
     *
     * @param context
     * @param itemList
     */
    public static void save(Context context, List<Item> itemList) {

        // 将数组转化为字符串
        String jsonStr = new Gson().toJson(itemList);

        // 保存字符串到SP中

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Activity.MODE_PRIVATE);

        SharedPreferences.Editor edit = sp.edit();

        edit.putString(KEY, jsonStr);

        edit.commit();
    }

    /**
     * 获取待办事项数组
     */
    public static List<Item> getItemList(Context context) {

        // 获得json字符串
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Activity.MODE_PRIVATE);
        String jsonStr = sp.getString(KEY, "[]");


        // 将字符串转化为数组
        List<Item> itemList = new Gson().fromJson(jsonStr, new TypeToken<List<Item>>() {
        }.getType());

        return itemList;
    }


    /**
     * 添加待办事项
     */
    public static void addNode(Context context, Item data) {

        // 获得数组
        List<Item> list = getItemList(context);

        // 添加数据
        list.add(data);

        //保存
        save(context, list); //保存
    }


    /**
     * 删除待办事项
     *
     * @param context
     * @param item
     */
    public static void delNode(Context context, Item item) {

        // 获得数组
        List<Item> list = getItemList(context);


        //删除
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getId().equals(item.getId())) {
                list.remove(i);
                break;
            }
        }

        //保存
        save(context, list); //保存
    }


    /**
     * 修改待办事项
     *
     * @param context
     * @param item
     */
    public static void modify(Context context, Item item) {

        // 获得数组
        List<Item> list = getItemList(context);

        //修改
        for (int i = 0; i < list.size(); i++) {


            if (list.get(i).getId().equals(item.getId())) {

                list.get(i).setName(item.getName());

                break;
            }
        }

        //保存
        save(context, list); //保存

    }

}
