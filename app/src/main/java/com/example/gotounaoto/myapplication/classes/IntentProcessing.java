package com.example.gotounaoto.myapplication.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.gotounaoto.myapplication.activities.DownloadActivity;
import com.example.gotounaoto.myapplication.activities.MainActivity;

/**
 * Created by gotounaoto on 2018/03/18.
 */

public class IntentProcessing {

    public static void backToMain(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void fromMainToDownload(Activity activity, String book_path){
        //MainActivityからDownloadActivityに遷移する
        Intent intent = new Intent(activity, DownloadActivity.class);
        intent.putExtra("book_path", book_path);
        activity.startActivity(intent);
        activity.finish();
    }
    public static String fromMainInDownload(Activity activity){
        //MainActivityからDownloadActivityに送られて来たものをゲットしてリターンする
        Intent intent = activity.getIntent();
        String book_path = intent.getStringExtra("book_path");
        return book_path;
    }
}
