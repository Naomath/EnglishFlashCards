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
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogDeleteFragment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogWordAddFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.AddedWord;
import com.example.gotounaoto.myapplication.ExtendSugar.Book;
import com.example.gotounaoto.myapplication.ExtendSugar.WeakWord;
import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.WordsAdapter;
import com.example.gotounaoto.myapplication.classes.MakeString;
import com.example.gotounaoto.myapplication.classes.SimplenessBook;
import com.example.gotounaoto.myapplication.interfaces.OnInputListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsFragment extends Fragment implements View.OnClickListener {

    View view;
    Book book;
    SwipeMenuListView listView;
    WordsAdapter adapter;
    long book_id;
    CustomDialogWordAddFragment dialog;
    OnWordsListener onWordsListener;

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
                Word word = new Word(original, translated, part);
                word.save();
                long id_word = word.getId();
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
    public boolean onOptionsItemSelected(MenuItem item) {
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
                        Word item = (Word) adapter.getItem(position);
                        if (item.getExist_weak() == 1) {
                            //もし間違い問題として登録されている場合の処理
                            WeakWord weakWord = WeakWord.findById(WeakWord.class, item.getWeak_id());
                            weakWord.delete();
                        }
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
        onWordsListener = (OnWordsListener) getActivity();
    }

    public void sortWords() {
        book = Book.findById(Book.class, book_id);
        List<Word> source = book.returnWords();
        List<Word> words = new ArrayList<>();
        for (Word item : source) {
            words.add(item);
        }
        //そして最後に全部ぶっこむ
        for (Word item : words) {
            adapter.add(item);
        }
    }

    public void study() {
        //この単語帳を解く処理
        onWordsListener.startStudy(book_id);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_upload:
                    upload();
                    break;
                case R.id.button_study:
                    study();
                    break;
                case R.id.button_add:
                    addWord();
                    break;

            }
        }
    }

    public void upload() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        SimplenessBook simplenessBook = returnSimplenessBooks();
        uploadUser(database, simplenessBook);
        uploadBook(database);
        //流れとしてはsimplenessBookをgetしてユーザーをアップロードしてその後にbookをアップロードする
        makeToast("アップロードが完了しました");
    }

    public void uploadUser(FirebaseDatabase database, SimplenessBook simplenessBook) {
        //userにsimplenessBookのアップロード
        //usersという枝に対して
        SharedPreferences user_preferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_id = user_preferences.getString("id", "");
        DatabaseReference user_database = database.getReference("users");
        user_database.child(user_id).push().setValue(simplenessBook);
    }

    public void uploadBook(FirebaseDatabase database) {
        //bookのupload
        //booksという枝に対して
        DatabaseReference books_database = database.getReference("books");
        book.setDone_upload(1);
        book.save();
        book.setList_words(book.returnWords());
        //ここでbookがuploadされているというというboolean型を残す
        books_database.push().setValue(book);
    }

    public SimplenessBook returnSimplenessBooks() {
        SharedPreferences user_preferences = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_name = user_preferences.getString("name", null);
        String book_id_fireBase = MakeString.makeString(Arrays.asList(user_preferences.getString("id", null), String.valueOf(book.getId())));
        //上の式で式でユーザーidの後ろにbookのidをつけた世界でただ一つのidが完成する
        SimplenessBook simplenessBook = new SimplenessBook(book.getTitle(), user_name, book.getDate(),book_id_fireBase);
        return simplenessBook;
    }

    public void makeToast(String message) {
        Toast.makeText(this.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void addWord() {
        //wordを追加するメソッド
        dialog = new CustomDialogWordAddFragment();
        dialog.show(getFragmentManager(), "add_word");
    }

    public interface OnWordsListener {
        void startStudy(long id);
    }
}
