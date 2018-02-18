package com.example.deliciousfood;

import android.support.annotation.DrawableRes;

/**
 * Created by chaoyang805 on 2017/4/4.
 */

public class Food {

    public static final int CHINESE_FOOD = 1;

    public static final int FAST_FOOD = 2;

    public static final int DESSERT_FOOD = 3;
    /**
     * 美食的名字
     */
    private String name;

    /**
     * 美食图片资源id
     */
    @DrawableRes
    private int imgResId;

    /**
     * 美食的价格
     */
    private int price;
    /**
     * 美食的类型,接受的类型:
     * CHINESE_FOOD
     * FAST_FOOD
     * DESSERT_FOOD
     */
    private int type;

    /**
     * 是否辣
     */
    private boolean isSpicy;

    /**
     * 美食的评分
     */
    private float rating;

    /**
     * 简介
     */
    private String description;

    public Food(String name, int imgResId, int price, int type, boolean isSpicy, float rating, String description) {
        this.name = name;
        this.imgResId = imgResId;
        this.price = price;
        this.type = type;
        this.isSpicy = isSpicy;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
