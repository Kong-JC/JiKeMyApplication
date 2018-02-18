package com.example.deliciousfood;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int STATE_FILTER = 1;
    private static final int STATE_NORMAL = 2;
    private int mState = STATE_NORMAL;

    private LinearLayout mCheckBoxContainer;
    private CheckBox mCheckBoxChineseFood;
    private CheckBox mCheckBoxFastFood;
    private CheckBox mCheckBoxDessertFood;

    private RadioGroup mRadioGroup;
    private RadioButton mRadioYes;
    private RadioButton mRadioNo;

    private TextView mTvPrice;
    private SeekBar mSeekBarPrice;

    private Button mBtnReset;
    private Button mBtnSearch;

    private ImageView mImageFood;
    private Button mBtnDetail;

    private Button mBtnPrev;
    private Button mBtnNext;

    private List<Food> mFoodList;
    private List<Food> mFilteredList;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        List<Food> foods = FoodAPI.getDemoFood(this);
    }

    private void initViews() {
        mBtnDetail = (Button) findViewById(R.id.btn_detail);
        Paint paint = mBtnDetail.getPaint();
        paint.setFlags(paint.getFlags() | Paint.UNDERLINE_TEXT_FLAG);

        mCheckBoxContainer = (LinearLayout) findViewById(R.id.checkbox_container);
        mCheckBoxChineseFood = (CheckBox) findViewById(R.id.checkbox_chinesefood);
        mCheckBoxFastFood = (CheckBox) findViewById(R.id.checkbox_fastfood);
        mCheckBoxDessertFood = (CheckBox) findViewById(R.id.checkbox_dessert);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioYes = (RadioButton) findViewById(R.id.radio_yes);
        mRadioNo = (RadioButton) findViewById(R.id.radio_no);

        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mSeekBarPrice = (SeekBar) findViewById(R.id.seekbar_price);
        mSeekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvPrice.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mImageFood = (ImageView) findViewById(R.id.image_food);
        mFoodList = initData();
        // 翻页按钮
        mBtnPrev = (Button) findViewById(R.id.btn_prev);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnPrev.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        // 搜索与重置
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnReset.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);

        loadInitialView();
    }

    private List<Food> initData() {
        return FoodAPI.getDemoFood(this);
    }

    /**
     * 选中mCheckBoxContainer 中所有的CheckBox
     */
    private void checkAll() {
        int checkBoxCount = mCheckBoxContainer.getChildCount();
        for (int i = 0; i < checkBoxCount; i++) {
            View checkBox = mCheckBoxContainer.getChildAt(i);
            if (checkBox instanceof CheckBox) {
                ((CheckBox) checkBox).setChecked(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prev:
                showPrevPage();
                break;
            case R.id.btn_next:
                showNextPage();
                break;
            case R.id.btn_reset:
                mState = STATE_NORMAL;
                loadInitialView();
                break;
            case R.id.btn_search:
                mState = STATE_FILTER;
                showFilteredFoods();
                break;
            default:
                break;
        }
    }

    // 搜索与重置
    private void loadInitialView() {
        mBtnPrev.setEnabled(true);
        mBtnNext.setEnabled(true);
        mBtnDetail.setEnabled(true);
        checkAll();
        mSeekBarPrice.setProgress(100);
        mRadioGroup.check(R.id.radio_no);
        showPageAtIndex(0);
    }

    private void showFilteredFoods() {
        mFilteredList = filteredFoods();
        boolean enablePageButton = (mFilteredList.size() > 1);
        mBtnNext.setEnabled(enablePageButton);
        mBtnPrev.setEnabled(enablePageButton);

        mBtnDetail.setEnabled(!mFilteredList.isEmpty());
        if (mFilteredList.isEmpty()) {
            mImageFood.setImageResource(R.drawable.nodata);
        } else {
            showPageAtIndex(0);
        }

    }

    private List<Food> filteredFoods() {
        // 1. 价格
        int maxPrice = mSeekBarPrice.getProgress();
        // 2. 是否要辣
        boolean isSpicy = (mRadioGroup.getCheckedRadioButtonId() == R.id.radio_no);
        // 3. 美食类型
        List<Integer> selectedFoodTypes = new ArrayList<>();
        if (mCheckBoxChineseFood.isChecked()) {
            selectedFoodTypes.add(Food.CHINESE_FOOD);
        }
        if (mCheckBoxFastFood.isChecked()) {
            selectedFoodTypes.add(Food.FAST_FOOD);
        }
        if (mCheckBoxDessertFood.isChecked()) {
            selectedFoodTypes.add(Food.DESSERT_FOOD);
        }
        List<Food> results = new ArrayList<>();
        for (Food food : mFoodList) {
            if (food.getPrice() < maxPrice &&
                selectedFoodTypes.contains(food.getType())) {

                if (isSpicy || !food.isSpicy()) {
                    results.add(food);
                }
            }
        }
        Log.d(TAG, "filteredFoods:" + results);
        return results;
    }

    private List<Food> currentShowingFoods() {
        switch (mState) {
            case STATE_FILTER:
                return mFilteredList;
            case STATE_NORMAL:
                return mFoodList;
            default:
                return mFoodList;
        }
    }

    // 翻页功能
    private void showPrevPage() {
        int prevPage = (mCurrentPage - 1 + currentShowingFoods().size()) % currentShowingFoods().size();
        showPageAtIndex(prevPage);
    }

    private void showNextPage() {
        int nextPage = (mCurrentPage + 1) % currentShowingFoods().size();
        showPageAtIndex(nextPage);
    }

    private void showPageAtIndex(int index) {
        Log.d(TAG, "showPageAtIndex:" + index);
        Food food = currentShowingFoods().get(index);
        mImageFood.setImageResource(food.getImgResId());
        mCurrentPage = index;
    }

    public void openDetailActivity(View view) {

        Intent intent = new Intent(this, DetailActivity.class);

        Bundle bundle = new Bundle();
        Food food = currentShowingFoods().get(mCurrentPage);
        bundle.putString("name", food.getName());
        bundle.putInt("imgResId", food.getImgResId());
        bundle.putFloat("rating", food.getRating());
        bundle.putInt("price", food.getPrice());
        bundle.putString("description", food.getDescription());
        bundle.putBoolean("isSpicy", food.isSpicy());
        bundle.putInt("type", food.getType());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
