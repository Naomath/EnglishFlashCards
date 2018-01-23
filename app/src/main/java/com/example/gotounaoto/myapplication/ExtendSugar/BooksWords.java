package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/22.
 */

public class BooksWords extends SugarRecord {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private long first_id;

    @Getter
    @Setter
    private long last_id;

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private boolean done_upload;


    public BooksWords() {
    }//普通のコンストラクタ

    public BooksWords(String title, long first_id, long last_id, String date){
        this.title = title;
        this.first_id = first_id;
        this.last_id = last_id;
        this.date = date;
    }

    public List<Words> returnWords(){
        //このbookの単語を返すメソッド
        List<Words> words = new ArrayList<>();
        for (long i = this.first_id; i <= this.last_id; i++) {
            Words item = Words.findById(Words.class, i);
            //ここでnullチェック
            if(item!=null){
                words.add(item);
            }
        }
        List <AddedWord> addedWordList = SugarRecord.listAll(AddedWord.class);
        //後の方がitemを入れる箱
        for(AddedWord item:addedWordList){
            if(item.getId_group() == this.getId()){
                long word_id = item.getId_word();
                Words itemWord = Words.findById(Words.class, word_id);
                words.add(itemWord);
            }
        }
        return words;
    }
}
