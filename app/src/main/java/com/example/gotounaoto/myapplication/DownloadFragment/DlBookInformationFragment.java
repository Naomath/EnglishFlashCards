package com.example.gotounaoto.myapplication.DownloadFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.classes.BundleProcessing;
import com.example.gotounaoto.myapplication.classes.FirebaseProcessing;
import com.example.gotounaoto.myapplication.extendSugar.Book;

public class DlBookInformationFragment extends Fragment implements FirebaseProcessing.OnDlBookInformationListener {

    View view;

    public DlBookInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void showItem(Book item) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dl_book_information, container, false);
        gettingBook(BundleProcessing.inDlBookInformationFrInDownload(this));
        return view;
    }

    public void gettingBook(String book_path) {
        //押されたbookをゲットする
        FirebaseProcessing firebaseProcessing = new FirebaseProcessing(this);
        firebaseProcessing.startSearchPath(book_path);
    }

}
