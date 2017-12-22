package com.example.gotounaoto.myapplication.Activity;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gotounaoto.myapplication.Fragment.AddWordsFragment;
import com.example.gotounaoto.myapplication.R;

public class AddWordActivity extends AppCompatActivity {

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        settingFragment();
    }

    public void settingFragment() {
        AddWordsFragment fragment = new AddWordsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_background, fragment);
        transaction.commit();
    }
}
