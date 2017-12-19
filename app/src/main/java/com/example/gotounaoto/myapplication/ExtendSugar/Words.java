package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarRecord;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/19.
 */

public class Words extends SugarRecord {

    @Setter
    @Getter
    private String original;

    @Setter
    @Getter
    private String translated;


    public Words() {//普通のコンストラクタ　
    }


}
