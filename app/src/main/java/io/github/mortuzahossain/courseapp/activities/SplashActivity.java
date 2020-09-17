package io.github.mortuzahossain.courseapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import io.github.mortuzahossain.courseapp.R;
import io.github.mortuzahossain.courseapp.utils.Navigator;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Timer timer = new Timer();
        TimerTask showSplash = new TimerTask() {
            @Override
            public void run() {
                Navigator.goToActivityForwardFinish(SplashActivity.this, CourseListActivity.class);
            }
        };
        timer.schedule(showSplash, 500);
    }
}