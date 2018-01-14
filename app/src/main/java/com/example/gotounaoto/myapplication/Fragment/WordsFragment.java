package com.example.gotounaoto.myapplication.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogDeleteFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.WordsAdapter;
import com.example.gotounaoto.myapplication.interfaces.OnInputListener;

import java.util.ArrayList;
import java.util.List;

public class WordsFragment extends Fragment{

    View view;
    BooksWords book;
    SwipeMenuListView listView;
    WordsAdapter adapter;
    long book_id;

    public WordsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_words, container, false);
        setHasOptionsMenu(true);
        gettingBookId();
        settingListView();
        settingSwipeMenu();
        sortWords();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_words, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_delete:
                CustomDialogDeleteFragment customDialogDeleteFragment = new CustomDialogDeleteFragment();
                customDialogDeleteFragment.show(getFragmentManager(), "delete");
                break;
        }
        return true;
    }

    public void settingListView() {
        listView = (SwipeMenuListView) view.findViewById(R.id.list_view);
        adapter = new WordsAdapter(getActivity(), R.layout.adapter_words);
        listView.setAdapter(adapter);
    }

    public void settingSwipeMenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            //横に出るやつ
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xd3, 0x2f, 0x2f)));
                deleteItem.setIcon(R.drawable.ic_delete_white_48dp);
                deleteItem.setWidth(300);
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Words item = (Words) adapter.getItem(position);
                        item.delete();
                        adapter.remove(item);
                        break;
                }
                return false;
            }
        });
    }

    public void gettingBookId() {
        OnInputListener listener = (OnInputListener) getActivity();
        book_id = listener.sendLong();
    }

    public void sortWords() {
        book = BooksWords.findById(BooksWords.class, book_id);
        List<Words> words = new ArrayList<>();
        long first_id = book.getFirstId();
        long last_id = book.getLastId();
        for (long i = first_id; i <= last_id; i++) {
            Words item = Words.findById(Words.class, i);
            //ここでnullチェック
            if(item!=null){
                words.add(item);
            }
        }
        //おそらく今後リストを使うのでここで二度手間にする
        for(Words item:words){
           adapter.add(item);
        }
    }
}
