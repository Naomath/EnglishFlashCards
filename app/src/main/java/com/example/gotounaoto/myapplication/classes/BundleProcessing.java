package com.example.gotounaoto.myapplication.classes;

import android.os.Bundle;

import com.example.gotounaoto.myapplication.downloadFragment.DlBookInformationFragment;

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
}
