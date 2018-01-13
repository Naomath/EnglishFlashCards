package com.example.gotounaoto.myapplication.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogAddFragment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogSortFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.BooksAdapter;
import com.example.gotounaoto.myapplication.interfaces.OnIntentWordsListener;
import com.example.gotounaoto.myapplication.interfaces.OnSendSortListener;
import com.orm.SugarRecord;

import java.util.ArrayList;
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
        setHasOptionsMenu(true);
        //ここでmenuを持つことを宣言
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                //新しい順に
                sortBooks(true, null);
                return;
            case 1:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                //古い順に
                sortBooks(false, null);
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_books, menu);
        menu.findItem(R.id.action_sort).setVisible(true);
        menu.findItem(R.id.action_search).setVisible(true);
        settingSearchView(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                CustomDialogSortFragment dialogSortFragment = new CustomDialogSortFragment();
                dialogSortFragment.show(getFragmentManager(), "sort");
                break;
            case R.id.action_search:
                booksSearch();
                break;
        }
        return true;
    }

    public void makeDialog() {
        CustomDialogAddFragment dialogAddFragment = new CustomDialogAddFragment();
        dialogAddFragment.show(getFragmentManager(), "add");
    }

    public void settingFab() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
    }


    public void settingListView() {
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new BooksAdapter(getActivity(), R.layout.books_adapter);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BooksWords item = (BooksWords) adapter.getItem(i);
                long id = item.getId();
                OnIntentWordsListener listener = (OnIntentWordsListener) getActivity();
                listener.moveToWords(id);
            }
        });
        sortBooks(true, null);
    }

    public void settingSearchView(Menu menu) {
        //toolbarのsearchviewを設定
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                sortBooks(true, s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.clear();
                return false;
            }
        });
    }

    public void sortBooks(boolean which, String source) {
        //リストをソートするの
        adapter.clear();
        List<BooksWords> books = BooksWords.listAll(BooksWords.class);
        if (source.equals(null)) {
            if (which) {
                Collections.reverse(books);
                //これだと新しい順
            }
            for (BooksWords item : books) {
                adapter.add(item);
            }
        } else {
            List<BooksWords> booksShowed = new ArrayList<>();
            for (BooksWords item : books) {
                if (item.getTitle().contains(source)) {
                    booksShowed.add(item);
                }
            }
            for (BooksWords item:booksShowed){
                adapter.add(item);
            }
        }
    }

    public void booksSearch() {
        //bookを名前で検索するときの処理
    }
}