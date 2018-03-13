package com.example.gotounaoto.myapplication.MainFragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogSortFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.Book;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.BooksAdapter;
import com.example.gotounaoto.myapplication.classes.GettingDataBaseReference;
import com.example.gotounaoto.myapplication.classes.SimpleBook;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DownloadFragment extends Fragment {

    View view;
    SearchView searchView;
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
        setHasOptionsMenu(true);
        settingListView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_books, menu);
        menu.findItem(R.id.action_search).setVisible(true);
        settingSearchView(menu);
        searchBooks(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                actionSearch();
                break;
        }
        return true;
    }

    public void actionSearch(){
        //searchviewのiconが押された時の処理
        adapter.clear();
        //ここでadapterをクリアにする
    }

    public void settingSearchView(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchBooks(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //ここにも処理を書いて、調べる語句が変わったたびに
                //調べるようにするとネットワークの時間がかかるし
                //大変なので何も書かずにnullにしておく
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                return false;
            }
        });

    }

    public void settingListView() {
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new BooksAdapter(getActivity(), R.layout.adapter_download_books);
        listView.setAdapter(adapter);
    }

    public void searchBooks(String keyword){
        //bookをサーチする
        final ProgressDialog progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(true);
        progressDialog.show();
        if(keyword != null){
            //つまりこの時が検索する時
            GettingDataBaseReference.gettingUserReference().orderByChild("title").equalTo(keyword).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Book item = snapshot.getValue(Book.class);
                        adapter.add(item);
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else {
            //この時が最初に上位のものを読み込む時
            GettingDataBaseReference.gettingUserReference().orderByChild("download_time").limitToLast(20).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Book item = snapshot.getValue(Book.class);
                        adapter.add(item);
                        progressDialog.dismiss();
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}