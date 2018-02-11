package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.BooksAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DownloadFragment extends Fragment {

    View view;
    BooksAdapter adapter;

    public DownloadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_down_load, container, false);
        settingListView();
        return view;
    }

    public void settingListView() {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new BooksAdapter(getActivity(), R.layout.adapter_books);
        listView.setAdapter(adapter);
        sortBooks("");
    }

    public void sortBooks(String source) {
        //リストをソートするの
        adapter.clear();
        final List<BooksWords> books = new ArrayList<>(gettingUploadedBooks());
        //上のlistにitemをいれる
        if (!source.equals("")) {
            //これはsearchviewなどで検索したときに使う
            List<BooksWords> booksShowed = new ArrayList<>();
            for (BooksWords item : books) {
                if (item.getTitle().contains(source)) {
                    booksShowed.add(item);
                }
            }
            for (BooksWords item : booksShowed) {
                adapter.add(item);
            }
        }else {
            //検索ではない時の場合
            for (BooksWords item:books){
                adapter.add(item);
            }
        }
    }

    public List<BooksWords> gettingUploadedBooks(){
        //アップロードされてたbookを取得する
        //from fire base
        final List<BooksWords> books = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("books");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //変更された時
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BooksWords item = snapshot.getValue(BooksWords.class);
                    books.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //キャンセルされた時
            }
        });
        return books;
    }
}