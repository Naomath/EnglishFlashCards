package com.example.gotounaoto.myapplication.classes;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;

import com.example.gotounaoto.myapplication.dialogFragment.CustomDialogInputMessageFragment;
import com.example.gotounaoto.myapplication.downloadFragment.DlBookInformationFragment;
import com.example.gotounaoto.myapplication.extendSugar.Book;

/**
 * Created by gotounaoto on 2018/03/18.
 */

public class BundleProcessing {

    public static Bundle toDlBookInformationFrInDownload(String book_path){
        //DownloadActivityでDlBookInformationFragmentを生成する時に渡すbundle
        Bundle bundle = new Bundle();
        bundle.putString("book_path", book_path);
        return bundle;
    }

    public static String inDlBookInformationFrInDownload(DlBookInformationFragment fragment){
        //DlBookInformationFrで渡されて来たbundleを取得する
        Bundle bundle = fragment.getArguments();
        return bundle.getString("book_path");
    }

    public static Bundle toMessageDialog(Book item){
        //MessageDialogにWordsFragmentから
        Bundle bundle = new Bundle();
        bundle.putString("message", item.getMessage());
        return bundle;
    }

    public static String inMessageDialog(CustomDialogInputMessageFragment fragment){
        //MessageDialogに送られてきたのを取得する
        Bundle bundle = fragment.getArguments();
        return bundle.getString("message");
    }
}
