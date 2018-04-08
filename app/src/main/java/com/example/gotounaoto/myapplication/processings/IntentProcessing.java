package com.example.gotounaoto.myapplication.processings;

import android.app.Activity;
import android.content.Intent;

import com.example.gotounaoto.myapplication.activities.DLBookInformationActivity;
import com.example.gotounaoto.myapplication.activities.MainActivity;
import com.example.gotounaoto.myapplication.activities.ULBookInformationActivity;
import com.example.gotounaoto.myapplication.classes.TextsAndNumbers;
import com.example.gotounaoto.myapplication.extendSugar.Book;
import com.example.gotounaoto.myapplication.tutorialActivity.TutorialAddWordsActivity;
import com.example.gotounaoto.myapplication.tutorialActivity.TutorialMainActivity;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gotounaoto on 2018/03/18.
 */

public class IntentProcessing {

    public static void backToMain(Activity activity, int which_fragment) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("which_fragment", which_fragment);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void backToMainWithMessage(Activity activity, String message, int which_fragment) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("which_fragment", which_fragment);
        intent.putExtra("please_toast", true);
        intent.putExtra("message", message);
        activity.startActivity(intent);
        activity.finish();
    }
    public static void backToTutorialMain(Activity activity) {
        Intent intent = new Intent(activity, TutorialMainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public static int inTutorialMain(Activity activity){
        Intent intent = activity.getIntent();
        return intent.getIntExtra("which_fragment", 0);
    }

    public static void backToTutorialMainWithMessage(Activity activity, String message, int which_fragment) {
        Intent intent = new Intent(activity, TutorialMainActivity.class);
        intent.putExtra("which_fragment", which_fragment);
        intent.putExtra("please_toast", true);
        intent.putExtra("message", message);
        activity.startActivity(intent);
        activity.finish();
    }

    public static void fromMainToDownload(Activity activity, String book_path) {
        //MainActivityからDownloadActivityに遷移する
        Intent intent = new Intent(activity, DLBookInformationActivity.class);
        intent.putExtra("book_path", book_path);
        activity.startActivity(intent);
        activity.finish();
    }

    public static String fromMainInDownload(Activity activity) {
        //MainActivityからDownloadActivityに送られて来たものをゲットしてリターンする
        Intent intent = activity.getIntent();
        String book_path = intent.getStringExtra("book_path");
        return book_path;
    }

    public static Intent fromInputMessageDialog(String s) {
        //MessageDialogから呼び出し元のところまでreturnする
        Intent intent = new Intent();
        intent.putExtra("message", s);
        return intent;
    }

    public static String fromInputMessageDialogInAnyFragment(Intent data) {
        //MessageDialogから送られてきたもの
        return data.getStringExtra("message");
    }

    public static void fromAboutMeToUlInformation(Activity activity, Book item) {
        //ABoutMeFragmentからULBookInformationに遷移する時の処理
        Intent intent = new Intent(activity, ULBookInformationActivity.class);
        intent.putExtra("message", item.getMessage());
        intent.putExtra("title", item.getTitle());
        intent.putExtra("download_time", item.getDownload_time());
        intent.putExtra("id", String.valueOf(item.getId()));
        activity.startActivity(intent);
        activity.finish();
    }

    public static TextsAndNumbers fromAboutMeInUlInformation(Activity activity) {
        //ULBookInformationでAboutMeFragmentから送られてきた時の処理
        Intent intent = activity.getIntent();
        List<String> texts = Arrays.asList(intent.getStringExtra("title"),intent.getStringExtra("message")
                , intent.getStringExtra("id"));
        List<Integer> numbers = Arrays.asList(intent.getIntExtra("download_time", 0));
        TextsAndNumbers item = new TextsAndNumbers(texts, numbers);
        return item;
    }

    public static void toTutorialAddWords(Activity activity, String title){
        Intent intent = new Intent(activity, TutorialAddWordsActivity.class);
        intent.putExtra("title", title);
        activity.startActivity(intent);
        activity.finish();
    }

    public static String inTutorialAddWords(Activity activity){
        Intent intent = activity.getIntent();
        return intent.getStringExtra("title");
    }

}
