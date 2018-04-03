package com.example.gotounaoto.myapplication.DownloadFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.InformationAdapter;
import com.example.gotounaoto.myapplication.classes.BundleProcessing;
import com.example.gotounaoto.myapplication.classes.FirebaseProcessing;
import com.example.gotounaoto.myapplication.extendSugar.Book;

public class DlBookInformationFragment extends Fragment implements FirebaseProcessing.OnDlBookInformationListener, View.OnClickListener {

    View view;
    InformationAdapter adapter;

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
        settingListener();
        settingListView();
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view !=null){
            switch (view.getId()){
                case R.id.button_download:
                    download();
                    break;
            }
        }
    }

    public void download(){
        //実際にbookをダウンロードする処理
    }

    public void gettingBook(String book_path) {
        //押されたbookをゲットする
        FirebaseProcessing  firebaseProcessing = new FirebaseProcessing(this);
        firebaseProcessing.startSearchPath(book_path);
    }

    public void settingListener(){
        //リスナーの設定
        view.findViewById(R.id.button_download).setOnClickListener(this);
    }

    public void settingListView(){
        //リストビューの設定
        ListView listView = view.findViewById(R.id.list_view);
        
    }

}
