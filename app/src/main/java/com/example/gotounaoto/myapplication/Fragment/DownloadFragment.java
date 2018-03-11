package com.example.gotounaoto.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.ExtendSugar.Book;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.BooksAdapter;
import com.example.gotounaoto.myapplication.adapters.SimpleBooksAdapter;
import com.example.gotounaoto.myapplication.classes.SimpleBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DownloadFragment extends Fragment {

    View view;
    SimpleBooksAdapter adapter;
    List<SimpleBook> simple_books;

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
        gettingUploadedSimpleBooks();
        return view;
    }

    public void settingListView() {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new SimpleBooksAdapter(getActivity(), R.layout.adapter_simple_books);
        listView.setAdapter(adapter);
        sortBooks();
    }

    public void sortBooks() {
        //リストをソートするの
        adapter.clear();
        //上のlistにitemをいれる
    }

    public void serachSortBooks(){
        //検索してからbookをadapterに並べるmethod
    }

    public void gettingUploadedSimpleBooks(){
        //アップロードされてたbookを取得する
        //from fire base
        simple_books = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("simple_books");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //変更された時
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SimpleBook item = snapshot.getValue(SimpleBook.class);
                    simple_books.add(item);
                    adapter.add(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //キャンセルされた時
            }
        });
    }
}