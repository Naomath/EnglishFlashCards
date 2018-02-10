package com.example.gotounaoto.myapplication.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.example.gotounaoto.myapplication.Fragment.WordsFragment;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnDeleteListener;
import com.example.gotounaoto.myapplication.interfaces.OnInputListener;

import java.util.ArrayList;
import java.util.List;

public class WordsActivity extends AppCompatActivity implements OnInputListener,
        OnDeleteListener, WordsFragment.OnWordsListener{

    long book_id;
    BooksWords book;
    List<Word> words;

    @Override
    public String sendText() {
        return null;
    }

    @Override
    public Long sendLong() {
        return book_id;
    }

    @Override
    public void deleteBook() {
        for (Word item : words) {
            item.delete();
        }
        book.delete();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("which_fragment", 1);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        gettingIntent();
        gettingWords();
        settingFragment();
        settingToolBar();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            backActivity();
            return true;
        } else {
            return false;
        }
    }


    public void settingFragment() {
        //フラグメントの設定
        Fragment fragment = new WordsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative, fragment);
        transaction.commit();
    }

    public void settingToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        setTitle(book.getTitle());
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backActivity();
            }
        });
        //menuはfragmentの方でやる
    }


    public void gettingIntent() {
        //インテントの取得と設定
        Intent intent = getIntent();
        book_id = intent.getLongExtra("id", 0);
        book = BooksWords.findById(BooksWords.class, book_id);
    }

    public void gettingWords() {
        //ここでグループのワードを取得する
        words = new ArrayList<>();
        long first_id = book.getFirst_id();
        long last_id = book.getLast_id();
        for (long i = first_id; i <= last_id; i++) {
            Word item = Word.findById(Word.class, i);
            if (item != null) {
                words.add(item);
            }
        }
    }

    public void backActivity() {
        //homeに戻るメソッド
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("which_fragment", 1);
        startActivity(intent);
    }

    @Override
    public void startStudy(long id) {
        //ここで表示している単語帳をテストできるようにQuestionActivityに送る処理
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("book_id", book_id);
        startActivity(intent);
    }
}
