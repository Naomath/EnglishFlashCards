package com.example.gotounaoto.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gotounaoto.myapplication.R;

public class SetUserActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);
        gettingUser();
    }

    public void gettingUser(){
        //ユーザーが初めてかどうか確認する
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean user_exist = sharedPreferences.getBoolean("exist", false);
        //まだ登録していなかったらfalseにする
        if (!(user_exist)){
            settingUser();
        }else{
            intentMain();
        }
    }

    public void settingUser(){
        //ユーザーの設定
        
    }

    public void intentMain(){
        //mainactivityに行く
        Intent intent = new Intent(SetUserActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

