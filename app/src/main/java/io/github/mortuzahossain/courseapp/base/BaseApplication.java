package io.github.mortuzahossain.courseapp.base;
/*
 * Created by mortuza on 17/9/20 | 12:08 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    public static Context APP_CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        APP_CONTEXT = getApplicationContext();
    }
}
