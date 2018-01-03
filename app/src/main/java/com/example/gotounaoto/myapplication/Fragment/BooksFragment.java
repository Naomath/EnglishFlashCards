package com.example.gotounaoto.myapplication.Fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogAddFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.BooksAdapter;
import com.orm.SugarRecord;

import java.util.Collections;
import java.util.List;

public class BooksFragment extends Fragment implements View.OnClickListener {

    View view;
    ListView listView;
    BooksAdapter adapter;

    public BooksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_books, container, false);
        settingFab();
        settingListView();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.fab:
                    makeDialog();
            }
        }
    }

    public void makeDialog() {
        CustomDialogAddFragment dialogAddFragment = new CustomDialogAddFragment();
        dialogAddFragment.show(getFragmentManager(), "add");
    }

    public void settingFab(){
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
    }

    public void settingListView(){
        listView = (ListView)view.findViewById(R.id.list_view);
        adapter = new BooksAdapter(getActivity(), R.layout.books_adapter);
        listView.setAdapter(adapter);
        sortBooks(true);
    }

    public void sortBooks(boolean which){
        //リストをソートするの
        List<BooksWords> books = SugarRecord.listAll(BooksWords.class);
        if(which){
            Collections.reverse(books);
            //これだと新しい順
        }
        for(BooksWords item:books){
            adapter.add(item);
        }
    }
}
