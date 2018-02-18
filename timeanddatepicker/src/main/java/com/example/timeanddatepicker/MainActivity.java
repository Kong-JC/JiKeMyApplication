package com.example.timeanddatepicker;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimePicker = (TimePicker) findViewById(R.id.time_picker);

        mTimePicker.setIs24HourView(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(21);
            mTimePicker.setMinute(45);
        } else {
            mTimePicker.setCurrentHour(21);
            mTimePicker.setCurrentMinute(45);
        }

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, hourOfDay + ":" + minute);
            }
        });



        // DatePicker
        mDatePicker = (DatePicker) findViewById(R.id.date_picker);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse("2015-12-21");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            calendar.setTime(date);
        }
        mDatePicker.init(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Log.d(TAG, year + "-" + monthOfYear + "-" + dayOfMonth);
                }
            });

    }
}
