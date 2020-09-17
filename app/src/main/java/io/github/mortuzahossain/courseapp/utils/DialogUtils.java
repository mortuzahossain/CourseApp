package io.github.mortuzahossain.courseapp.utils;
/*
 * Created by mortuza on 17/9/20 | 2:35 PM for CourseApp
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import io.github.mortuzahossain.courseapp.R;

public class DialogUtils {
    public static Dialog showLoadingDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.loader_layout);
        return dialog;
    }
}
