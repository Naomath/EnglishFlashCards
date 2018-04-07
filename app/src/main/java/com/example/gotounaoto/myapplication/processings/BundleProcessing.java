package com.example.gotounaoto.myapplication.processings;

import android.os.Bundle;

import com.example.gotounaoto.myapplication.classes.TextsAndNumbers;
import com.example.gotounaoto.myapplication.dialogFragment.CustomDialogInputMessageFragment;
import com.example.gotounaoto.myapplication.dialogFragment.CustomDialogOneButtonFragment;
import com.example.gotounaoto.myapplication.downloadFragment.DLBookInformationFragment;
import com.example.gotounaoto.myapplication.extendSugar.Book;
import com.example.gotounaoto.myapplication.shareFragment.ULBookInformationFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gotounaoto on 2018/03/18.
 */

public class BundleProcessing {

    public static Bundle toDlBookInformationFrInDownload(String book_path) {
        //DownloadActivityでDlBookInformationFragmentを生成する時に渡すbundle
        Bundle bundle = new Bundle();
        bundle.putString("book_path", book_path);
        return bundle;
    }

    public static String inDlBookInformationFrInDownload(DLBookInformationFragment fragment) {
        //DlBookInformationFrで渡されて来たbundleを取得する
        Bundle bundle = fragment.getArguments();
        return bundle.getString("book_path");
    }

    public static Bundle toMessageDialog(Book item, int requestCode) {
        //MessageDialogにWordsFragmentから
        Bundle bundle = new Bundle();
        bundle.putString("message", item.getMessage());
        bundle.putInt("requestCode", requestCode);
        return bundle;
    }

    public static TextsAndNumbers inMessageDialog(CustomDialogInputMessageFragment fragment) {
        //MessageDialogに送られてきたのを取得する
        Bundle bundle = fragment.getArguments();
        List<String> texts = Arrays.asList(bundle.getString("message"));
        List<Integer> numbers = Arrays.asList(bundle.getInt("requestCode"));
        TextsAndNumbers item = new TextsAndNumbers(texts, numbers);
        return item;
    }

    public static Bundle toOneBtnDialog(String title, String message, int requestCode) {
        //OneBtnDialogを作るときに渡すbundle
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", message);
        bundle.putInt("requestCode", requestCode);
        return bundle;
    }

    public static TextsAndNumbers inOneBtnDialog(CustomDialogOneButtonFragment fragment) {
        //OneBtnDilogで送られてきたbundleを取得する
        Bundle bundle = fragment.getArguments();
        List<String> texts = Arrays.asList(bundle.getString("title"), bundle.getString("message"));
        List<Integer> numbers = Arrays.asList(bundle.getInt("requestCode"));
        TextsAndNumbers item = new TextsAndNumbers(texts, numbers);
        return item;
    }

    public static Bundle toULBookInformationFr(TextsAndNumbers data) {
        //ULBookInformationFragmentに対するbundle
        Bundle bundle = new Bundle();
        bundle.putString("message", data.getTexts().get(1));
        //上の要素が1になっているところに注意!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        bundle.putString("id", data.getTexts().get(2));
        bundle.putInt("download_time", data.getNumbers().get(0));
        return bundle;
    }

    public static TextsAndNumbers inULBookInformationFr(ULBookInformationFragment fragment) {
        Bundle bundle = fragment.getArguments();
        List<String> texts = Arrays.asList(bundle.getString("message"), bundle.getString("id"));
        List<Integer> numbers = Arrays.asList(bundle.getInt("download_time"));
        TextsAndNumbers data = new TextsAndNumbers(texts, numbers);
        return data;
    }

}
