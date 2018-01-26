package com.example.gotounaoto.myapplication.ExtendSugar;

import android.os.Parcelable;

import com.orm.SugarRecord;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2017/12/19.
 */

public class Word extends SugarRecord{

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
    private float number_question;
    //この単語の出題数

    @Getter
    @Setter
    private float number_mistake;
    //この単語を間違えた回数

    @Getter
    @Setter
    private float proportion;
    //上の三つの変数は割合を計算するためにあえてfloatに
    //してある。実験済み
    //単語の誤答率

    @Getter
    @Setter
    private boolean boolean_weak;
    //間違えやすい問題に登録されているか
    //trueで登録されている


    public Word() {//普通のコンストラクタ　
    }

    public Word(String original, String translated, String part){
        this.original = original;
        this.translated = translated;
        this.part = part;
    }

    public void calculateProportion(){
        //誤答率を計算する
        proportion = number_mistake/number_question*100;
    }
}
