package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarRecord;

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
    private long firstId;

    @Getter
    @Setter
    private long lastId;

    @Getter
    @Setter
    private String date;

    public BooksWords() {
    }//普通のコンストラクタ

    public BooksWords(String title, long firstId, long lastId) {
        this.title = title;
        this.firstId = firstId;
        this.lastId = lastId;
    }
}
