package com.example.deliciousfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by chaoyang805 on 2017/4/4.
 */

public class DetailActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private ImageButton mBtnBack;

    private ImageView mImageFood;
    private TextView mTvName;
    private TextView mTvPrice;
    private TextView mTvDesc;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initViews();
        Bundle bundle = getIntent().getExtras();
        loadView(parseFoodFromBundle(bundle));
    }

    private void initViews() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mImageFood = (ImageView) findViewById(R.id.image_food);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvDesc = (TextView) findViewById(R.id.tv_description);
        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);
    }

    private void loadView(Food food) {
        mImageFood.setImageResource(food.getImgResId());
        mTvTitle.setText(food.getName());
        mTvPrice.setText(String.format("价格:  %d", food.getPrice()));
        mTvName.setText(String.format("名称:  %s", food.getName()));
        mTvDesc.setText(food.getDescription());
        mRatingBar.setRating(food.getRating());
    }

    private Food parseFoodFromBundle(Bundle bundle) {
        String name = bundle.getString("name");
        int imgResId = bundle.getInt("imgResId");
        String desc = bundle.getString("description");
        int price = bundle.getInt("price");
        int type = bundle.getInt("type");
        float rating = bundle.getFloat("rating");
        boolean isSpicy = bundle.getBoolean("isSpicy");

        return new Food(name, imgResId, price, type, isSpicy, rating, desc);
    }

    public void closeActivity(View view) {
        onBackPressed();
    }
}
