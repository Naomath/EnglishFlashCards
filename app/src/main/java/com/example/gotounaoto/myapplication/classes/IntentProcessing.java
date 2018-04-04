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

    public static void backToMain(Activity activity, int which_fragment){
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("which_fragment", which_fragment);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void backToMainWithMessage(Activity activity, String message, int which_fragment){
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("which_fragment", which_fragment);
        intent.putExtra("please_toast", true);
        intent.putExtra("message", message);
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

    public static Intent fromMessageDialogToWords(String s){
        //MessageDialogから呼び出し元のところまでreturnする
        Intent intent = new Intent();
        intent.putExtra("message", s);
        return intent;
    }

    public static String fromMessageDialogInWords(Intent data){
        //MessageDialogから送られてきたもの
        //WordsFragmentに対して
        return data.getStringExtra("message");
    }

}
