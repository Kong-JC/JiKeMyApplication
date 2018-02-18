package com.example.deliciousfood;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaoyang805 on 2017/4/4.
 */

public class FoodAPI {

    public static List<Food> getDemoFood(Context context) {
        List<Food> foods = new ArrayList<>();
        foods = new ArrayList<>();
        foods.add(new Food("提拉米苏", R.drawable.tiramisu, 80, Food.DESSERT_FOOD, false, 4.5f, context.getString(R.string.tiramisu)));
        foods.add(new Food("舒芙蕾", R.drawable.souffle, 65, Food.DESSERT_FOOD, false, 4f, context.getString(R.string.souffle)));
        foods.add(new Food("欧培拉", R.drawable.opera, 48, Food.DESSERT_FOOD, false, 3.5f, context.getString(R.string.opera)));
        foods.add(new Food("汉堡包", R.drawable.hamburger, 15, Food.FAST_FOOD, false, 4.0f, context.getString(R.string.hamburger)));
        foods.add(new Food("三明治", R.drawable.sanwich, 8, Food.FAST_FOOD, false, 4.5f, context.getString(R.string.sandwich)));
        foods.add(new Food("麦乐鸡", R.drawable.malejikuai, 10, Food.FAST_FOOD, false, 3.0f, context.getString(R.string.maleji)));
        foods.add(new Food("宫保鸡丁", R.drawable.gongbaojiding, 20, Food.CHINESE_FOOD, true, 5.0f, context.getString(R.string.gongbaojiding)));
        foods.add(new Food("鱼香肉丝", R.drawable.yuxiangrousi, 24, Food.CHINESE_FOOD, false, 4.0f, context.getString(R.string.yuxiangrousi)));
        foods.add(new Food("水煮肉片", R.drawable.shuizhurou, 32, Food.CHINESE_FOOD, true, 4.5f, context.getString(R.string.shuizhurou)));
        foods.add(new Food("红烧肉", R.drawable.hongshaorou, 38, Food.CHINESE_FOOD, false, 5.0f, context.getString(R.string.hongshaorou)));
        return foods;
    }
}
