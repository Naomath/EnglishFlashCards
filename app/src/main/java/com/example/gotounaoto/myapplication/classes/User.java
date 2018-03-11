package com.example.gotounaoto.myapplication.classes;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2018/02/25.
 */

public class User {

    @Getter
    @Setter
    private String user_id;
    //ユーザid

    @Getter
    @Setter
    private String user_name;
    //ユーザーname

    public User(){
        //empty constructor
    }

    public User(String user_id, String user_name){
        this.user_id = user_id;
        this.user_name = user_name;
    }
}
