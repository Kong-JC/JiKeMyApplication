package com.example.progressbars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ProgressBar mProgressBar;
    private SeekBar mSeekBar;
    private TextView mTextView;
    private RatingBar mRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setProgress(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {

                    mProgressBar.post(new Runnable() {
                        @Override
                        public void run() {
                            int newProgress = mProgressBar.getProgress()+5;
                            mProgressBar.setProgress(newProgress);
                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (mProgressBar.getProgress() == 100) {
                        mProgressBar.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_LONG).show();
                            }
                        });
                        break;
                    }

                }
            }
        }).start();

        mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        mTextView = (TextView) findViewById(R.id.tv_progress_update);
        mTextView.setText(mSeekBar.getProgress() + "");
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "progress update " + progress + " from user ? " + fromUser);
                mTextView.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch");
            }
        });

        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);
        mRatingBar.setRating(3.5f);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.d(TAG, "ratingbar changed " + rating + " from user ? " + fromUser);
            }
        });

    }
}
