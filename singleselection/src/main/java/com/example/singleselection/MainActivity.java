package com.example.singleselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainAcitvity";
    private RadioGroup mRadioGroup;
    private RadioButton mRadioExtremelySpicy;
    private RadioButton mRadioSpicy;
    private RadioButton mRadioLittleSpicy;
    private RadioButton mRadioNoSpicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioExtremelySpicy = (RadioButton) findViewById(R.id.radio_extremely_spicy);
        mRadioSpicy = (RadioButton) findViewById(R.id.radio_spicy);
        mRadioLittleSpicy = (RadioButton) findViewById(R.id.radio_little_spicy);
        mRadioNoSpicy = (RadioButton) findViewById(R.id.radio_no_spicy);

        int checkedId = mRadioGroup.getCheckedRadioButtonId();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                outputMessageForCheckId(checkedId);
            }
        });
//        mRadioGroup.check(R.id.radio_extremely_spicy);
        mRadioExtremelySpicy.setChecked(true);
    }

    private void outputMessageForCheckId(int checkedId) {
        String text = "";
        switch (checkedId) {
            case R.id.radio_extremely_spicy:
                text = "变态辣";
                break;
            case R.id.radio_spicy:
                text = "要辣";
                break;
            case R.id.radio_little_spicy:
                text = "微辣";
                break;
            case R.id.radio_no_spicy:
                text = "不要辣";
                break;
            default:
                break;
        }
        Log.d(TAG, text);
    }

    public void confirm(View view) {
        int checkedId = mRadioGroup.getCheckedRadioButtonId();
        outputMessageForCheckId(checkedId);
    }
}
