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
import android.widget.RelativeLayout;

import com.example.gotounaoto.myapplication.R;

import org.apache.commons.lang3.RandomStringUtils;

public class SetUpUserActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener {

    SharedPreferences exist_preference;
    Button button_decide;
    AppCompatEditText edit_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_user);
        gettingUser();
        settingListener();
        settingOtherInitial();
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
        String user_id = RandomStringUtils.randomAlphabetic(12);
        String user_name = edit_user_name.getText().toString();
        SharedPreferences user_preference = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_user = user_preference.edit();
        editor_user.putString("id", user_id);
        editor_user.putString("name", user_name);
        editor_user.putBoolean("exist", true);
        editor_user.commit();
        intentToMain();
    }

    public void settingOtherInitial(){
        //残りのやるべき設定の初期値を設定する
        SharedPreferences weak_preference = getSharedPreferences("weak_percentage", Context.MODE_PRIVATE);
        SharedPreferences.Editor weak_editor = weak_preference.edit();
        weak_editor.putFloat("percentage",30f);
        weak_editor.commit();
    }

    public void settingListener() {
        edit_user_name = (AppCompatEditText) findViewById(R.id.edit_user_name);
        edit_user_name.addTextChangedListener(this);
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
        Intent intent = new Intent(SetUpUserActivity.this, MainActivity.class);
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
            switch (view.getId()) {
                case R.id.button_decide:
                    settingUser();
                    break;
            }
        }
    }
}

