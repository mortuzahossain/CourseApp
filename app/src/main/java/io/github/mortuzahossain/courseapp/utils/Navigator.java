package io.github.mortuzahossain.courseapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import io.github.mortuzahossain.courseapp.R;


public class Navigator {

    public static void goToActivityForward(Context context, final Class<? extends Activity> targetActivity) {
        Intent loginIntent = new Intent(context, targetActivity);
        context.startActivity(loginIntent);
        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }

    public static void goToActivityForwardFinish(Context context, final Class<? extends Activity> targetActivity) {
        Intent loginIntent = new Intent(context, targetActivity);
        context.startActivity(loginIntent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }

    public static void goToActivityBackward(Context context, final Class<? extends Activity> targetActivity) {
        Intent loginIntent = new Intent(context, targetActivity);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(loginIntent);
        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    public static void activityFinish(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

//    public static void activitySessionTimeOut(Context context) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
//        ((Activity) context).finish();
//    }

    public static void clearAllTopActivities(Context context, final Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        context.startActivity(intent);
        ((Activity) context).finish();
//        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        ((Activity) context).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
    }

    public static void fragmentForwardNoBackStack(Context context, int frameId, Fragment fragment, boolean setTransition) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        if (setTransition) {
            transaction.setCustomAnimations(R.anim.anim_fade_in, R.anim.anim_fade_out, R.anim.anim_fade_in, R.anim.anim_fade_out);
        }
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void fragmentForwardWithBackStack(Context context, int frameId, Fragment fragment, String stack, boolean setTransition) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        if (setTransition) {
            transaction.setCustomAnimations(R.anim.anim_fade_in, R.anim.anim_fade_out, R.anim.anim_fade_in, R.anim.anim_fade_out);
        }
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(stack);
        transaction.commit();
    }

    public static void killApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }

}
