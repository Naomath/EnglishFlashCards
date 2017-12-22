package com.example.gotounaoto.myapplication.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/22.
 */

public class prepareWords {

    @Getter
    private String original;

    @Getter
    private String translated;

    @Getter
    private String part;

    public prepareWords(){}//普通のコンストラクタ

    public prepareWords(String original, String translated, String part){
        this.original = original;
        this.translated = translated;
        this.part = part;
    }
}
