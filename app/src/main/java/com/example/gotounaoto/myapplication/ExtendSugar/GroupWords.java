package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarRecord;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/22.
 */

public class GroupWords extends SugarRecord {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private long firstId;

    @Getter
    @Setter
    private long lastId;

    public GroupWords() {
    }//普通のコンストラクタ

    public GroupWords(String title, int firstId, int lastId) {
        this.title = title;
        this.firstId = firstId;
        this.lastId = lastId;
    }

}
