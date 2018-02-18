package com.example.multiselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheckBoxSamsung;
    private CheckBox mCheckBoxApple;
    private CheckBox mCheckBoxXiaomi;
    private CheckBox mCheckBoxHuawei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private Set<Phone> mPhones = new HashSet<>();

    private void initData() {
        mPhones.add(new Phone("苹果", "iPhone 7"));
        mPhones.add(new Phone("苹果", "iPhone 6"));
        mPhones.add(new Phone("苹果", "iPhone 6s"));
        mPhones.add(new Phone("苹果", "iPhone 7 plus"));

        mPhones.add(new Phone("三星", "Galaxy S6"));
        mPhones.add(new Phone("三星", "Galaxy S7"));
        mPhones.add(new Phone("三星", "Galaxy S7 edge"));
        mPhones.add(new Phone("三星", "Galaxy Note 5"));

        mPhones.add(new Phone("小米", "小米5"));
        mPhones.add(new Phone("小米", "小米4"));
        mPhones.add(new Phone("小米", "小米3"));
        mPhones.add(new Phone("小米", "小米 Note"));

        mPhones.add(new Phone("华为", "荣耀7"));
        mPhones.add(new Phone("华为", "P7"));
        mPhones.add(new Phone("华为", "P8"));
        mPhones.add(new Phone("华为", "荣耀8"));
    }

    private void initViews() {
        mCheckBoxSamsung = (CheckBox) findViewById(R.id.checkbox_samsung);
        mCheckBoxApple = (CheckBox) findViewById(R.id.checkbox_apple);
        mCheckBoxXiaomi = (CheckBox) findViewById(R.id.checkbox_xiaomi);
        mCheckBoxHuawei = (CheckBox) findViewById(R.id.checkbox_huawei);

        mCheckBoxApple.setOnCheckedChangeListener(this);
        mCheckBoxSamsung.setOnCheckedChangeListener(this);
        mCheckBoxHuawei.setOnCheckedChangeListener(this);
        mCheckBoxXiaomi.setOnCheckedChangeListener(this);

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Phone> results = getFilteredPhones();
                for (Phone phone : results) {
                    System.out.println("Phone:" + phone.getBrand() + " " + phone.getModel());
                }
            }
        });
    }

    private Set<Phone> getFilteredPhones() {
        Set<Phone> results = new HashSet<>();
        if (mCheckedBoxResIds.size() == 0) {
            return results;
        }
        for (Phone phone : mPhones) {
            String brand = phone.getBrand();
            int resId = -1;
            switch (brand) {
                case "苹果":
                    resId = R.id.checkbox_apple;
                    break;
                case "三星":
                    resId = R.id.checkbox_samsung;
                    break;
                case "小米":
                    resId = R.id.checkbox_xiaomi;
                    break;
                case "华为":
                    resId = R.id.checkbox_huawei;
                    break;
                default:
                    resId = -1;
                    break;
            }
            if (mCheckedBoxResIds.contains(resId)) {
                results.add(phone);
            }
        }
        return results;
    }

    private Set<Integer> mCheckedBoxResIds = new HashSet<>();

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("MainActivity", "Checked " + isChecked + " " + buttonView.getId());
        if (isChecked) {
            mCheckedBoxResIds.add(buttonView.getId());
        } else {
            mCheckedBoxResIds.remove(buttonView.getId());
        }
    }
}
