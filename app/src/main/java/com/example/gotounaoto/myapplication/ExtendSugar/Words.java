package com.example.gotounaoto.myapplication.ExtendSugar;

import android.os.Parcelable;

import com.orm.SugarRecord;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/19.
 */

public class Words extends SugarRecord{

    @Getter
    @Setter
    private String original;

    @Getter
    @Setter
    private String translated;

    @Getter
    @Setter
    private String part;


    public Words() {//普通のコンストラクタ　
    }

    public Words(String original, String translated, String part){
        this.original = original;
        this.translated = translated;
        this.part = part;
    }
}
