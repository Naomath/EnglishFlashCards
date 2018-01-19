package com.example.gotounaoto.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gotounaoto.myapplication.R;

public class SetUserActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    SharedPreferences exist_preference;
    Button button_decide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);
        gettingUser();
        settingListener();
    }

    public void gettingUser() {
        //ユーザーが初めてかどうか確認する
        exist_preference = getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean user_exist = exist_preference.getBoolean("exist", false);
        //まだ登録していなかったらfalseにする
        if (user_exist) {
            intentToMain();
            //もしすでにあるとわかったらmainactivityにいく
        }
    }

    public void settingUser() {
        //ユーザーの設定
        SharedPreferences user_preference = getSharedPreferences("user_name", Context.MODE_PRIVATE);
        
    }

    public void settingListener() {
        AppCompatEditText edit_ueser_name = (AppCompatEditText) findViewById(R.id.edit_user_name);
        edit_ueser_name.addTextChangedListener(this);
        button_decide = (Button) findViewById(R.id.button_decide);
        button_decide.setOnClickListener(this);
    }

    public void settingButtonEnable(boolean which) {
        //whichがtrueの時にボタンが有効になる
        RelativeLayout relative_error = (RelativeLayout) findViewById(R.id.relative_error);
        if (which) {
            relative_error.setVisibility(View.INVISIBLE);
            button_decide.setEnabled(true);
        } else {
            relative_error.setVisibility(View.VISIBLE);
            button_decide.setEnabled(false);
        }
    }

    public void intentToMain() {
        //mainactivityに行く
        Intent intent = new Intent(SetUserActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //ここには何も書かない
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() != 0) {
            //何か書いてある場合
            settingButtonEnable(true);
        } else {
            settingButtonEnable(false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        //ここにも何も書かない
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()){
                case R.id.button_decide:
                    settingUser();
                    break;
            }
        }
    }
}

