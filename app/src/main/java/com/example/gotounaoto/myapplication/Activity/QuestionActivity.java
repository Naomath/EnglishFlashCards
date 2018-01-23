package com.example.gotounaoto.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.classes.MakeDateString;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    List<Words> presented_items;
    //出す単語たちを全て入れるリスト

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        gettingIntent();
        //流れ的には
        //gettingIntent()-->settingPresentedToday() or settingPresentedWeak()-->settingFragment
    }

    public void gettingIntent(){
        Intent intent = getIntent();
        int which_course = intent.getIntExtra("which_course", 0);
        switch (which_course){
            case 0:
                settingPresentedItemsToday();
                break;
            case 1:
                settingPresentedItemsWeak();
                break;
        }
    }

    public List<BooksWords> gettingBooks(String date_string){
        //今日の問題の単語たちを取得するときに使う
        List<BooksWords> books = BooksWords.listAll(BooksWords.class);
        List <BooksWords> returned_books = new ArrayList<>();
        for(BooksWords item:books){
            if(item.getDate().equals(date_string)){
                //もし日にちが同じものをここで選定している
                returned_books.add(item);
            }
        }
        return returned_books;
    }

    public void settingPresentedItemsToday(){
        //今日の問題のセッティングをやる
        presented_items = new ArrayList<>();
        List<String> string_dates = new ArrayList<>();
        string_dates.add(MakeDateString.makeDateNow());
        string_dates.add(MakeDateString.makeDateYesterday());
        string_dates.add(MakeDateString.makeDateOneWeekBefore());
        string_dates.add(MakeDateString.makeDateOneMonthBefore());
        for(String item:string_dates){
            //その日にちの単語帳を取得してそれから単語隊を取得してそっからwordsというlistに入れる
            List<BooksWords> booksWord = gettingBooks(item);
            for(BooksWords item2: booksWord){
                List<Words> items = item2.returnWords();
                for(Words item3:items){
                    presented_items.add(item3);
                }
            }
        }
        settingFragment();
    }


    public void settingPresentedItemsWeak(){
        //苦手な問題のセッティングをやる
        settingFragment();
    }

    public void settingFragment(){
    }

}
