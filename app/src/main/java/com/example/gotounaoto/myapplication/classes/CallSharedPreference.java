package com.example.gotounaoto.myapplication.classes;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gotounaoto on 2018/03/16.
 */

public class CallSharedPreference {


    public static SharedPreferences callUserPreference(Context context){
        //userというプリファレンスを呼び出して返す
        SharedPreferences user_preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return user_preferences;
    }

    public static String callUserId(Context context){
        //user_idを返すメソッド
        String user_id = callUserPreference(context).getString("id", null);
        return user_id;
    }

    public static String callUserName(Context context){
        //user_nameを返すメソッド
        String user_name = callUserPreference(context).getString("name", null);
        return user_name;
    }
}
