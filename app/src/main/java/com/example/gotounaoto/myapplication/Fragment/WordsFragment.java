package com.example.gotounaoto.myapplication.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.gotounaoto.myapplication.Activity.WordsActivity;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogDeleteFragment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogWordAddFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.AddedWord;
import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.WordsAdapter;
import com.example.gotounaoto.myapplication.interfaces.OnInputListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class WordsFragment extends Fragment implements View.OnClickListener{

    View view;
    BooksWords book;
    SwipeMenuListView listView;
    WordsAdapter adapter;
    long book_id;
    CustomDialogWordAddFragment dialog;

    public WordsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                String original = data.getStringExtra("original");
                String translated = data.getStringExtra("translated");
                String part = data.getStringExtra("part");
                Words words = new Words(original, translated, part);
                words.save();
                long id_word = words.getId();
                AddedWord addedWord = new AddedWord(original, translated, part, book_id, id_word);
                addedWord.save();
                dialog.dismiss();
                sortWords();
                return;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_words, container, false);
        setHasOptionsMenu(true);
        gettingBookId();
        settingListView();
        settingSwipeMenu();
        settingListener();
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


    public void gettingBookId() {
        OnInputListener listener = (OnInputListener) getActivity();
        book_id = listener.sendLong();
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

    public void settingListener() {
        //リスナーの設定
        view.findViewById(R.id.button_upload).setOnClickListener(this);
        view.findViewById(R.id.button_study).setOnClickListener(this);
        view.findViewById(R.id.button_add).setOnClickListener(this);
    }

    public void sortWords() {
        book = BooksWords.findById(BooksWords.class, book_id);
        List<Words> source = book.returnWords();
        List<Words> words = new ArrayList<>();
        for(Words item:source){
            words.add(item);
        }
        //そして最後に全部ぶっこむ
       for(Words item:words){
            adapter.add(item);
       }
    }

    @Override
    public void onClick(View view) {
        if(view != null){
            switch(view.getId()){
                case R.id.button_upload:
                    uploadBook();
                    break;
                case R.id.button_study:
                        break;
                case R.id.button_add:
                    addWord();
                    break;

            }
        }
    }

    public void uploadBook(){
        SharedPreferences user_preferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_id=  user_preferences.getString("id", "");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users_database = database.getReference("users");
        DatabaseReference books_database = database.getReference("books");
        books_database.push().setValue(book);
        users_database.child(user_id).push().setValue(book);
        book.setDone_upload(true);
        book.save();
        //ここでbookがuploadされているというというboolean型を残す
        makeToast("アップロードが完了しました");
    }

    public void makeToast(String message){
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void addWord(){
        //wordを追加するメソッド
        dialog = new CustomDialogWordAddFragment();
        dialog.show(getFragmentManager(),"add_word");
    }
}
