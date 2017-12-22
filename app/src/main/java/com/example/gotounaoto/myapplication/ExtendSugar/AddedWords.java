package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarRecord;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/22.
 */

public class AddedWords extends SugarRecord {

    @Getter
    @Setter
    private String original;

    @Getter
    @Setter
    private String translated;

    @Getter
    @Setter
    private String part;

    @Getter
    @Setter
    private long groupId;

    public AddedWords(){}//普通のコンストラクタ

    public AddedWords(String original, String translated, String part, long groupId){
        this.original = original;
        this.translated = translated;
        this.part = part;
        this.groupId = groupId;
    }
}
