package com.example.gotounaoto.myapplication.classes;

import lombok.Getter;
import lombok.Setter;

public class TwoText {

    //SettingUserAdapterで使う

    @Getter
    @Setter
    private String first_text;

    @Getter
    @Setter
    private String second_text;

    @Getter
    @Setter
    private int id;
    //adapterから取得したときのidを示すためのやつ

    public TwoText(String first_text, String second_text) {
        this.first_text = first_text;
        this.second_text = second_text;
    }

    public TwoText(String first_text, String second_text, int id){
        this.first_text = first_text;
        this.second_text = second_text;
        this.id = id;
    }

}
