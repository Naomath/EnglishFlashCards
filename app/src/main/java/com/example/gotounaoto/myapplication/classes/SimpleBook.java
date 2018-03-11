package com.example.gotounaoto.myapplication.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2018/02/12.
 */

public class SimpleBook {
    //this class for Book in Fire Base
    //this class is simple Book

    @Getter
    @Setter
    private String title;
    //bool's title

    @Getter
    @Setter
    private String user_name;
    //user's name who made this book

    @Getter
    @Setter
    private String date;
    //date that user made it on

    @Getter
    @Setter
    private String fireBase_id;
    //this simpleness book's book path

    public SimpleBook() {
        //empty constructor
    }

    public SimpleBook(String title, String user_name, String date, String fireBase_id) {
        this.title = title;
        this.user_name = user_name;
        this.date = date;
        this.fireBase_id = fireBase_id;
    }

}
