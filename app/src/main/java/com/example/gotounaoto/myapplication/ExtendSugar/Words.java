package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarRecord;

/**
 * Created by gotounaoto on 2017/12/19.
 */

public class Words extends SugarRecord {

    private String original;

    private String translated;

    private String part;


    public Words() {//普通のコンストラクタ　
    }

    public Words(String original, String translated, String part){
        this.original = original;
        this.translated = translated;
        this.part = part;
    }

    public String getOriginal() {
        return original;
    }

    public String getTranslated() {
        return translated;
    }

    public String getPart() {
        return part;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }

    public void setPart(String part) {
        this.part = part;
    }
}
