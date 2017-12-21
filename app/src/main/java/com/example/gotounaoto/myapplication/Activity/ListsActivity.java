package com.example.gotounaoto.myapplication.Activity;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gotounaoto.myapplication.R;

public class ListsActivity extends AppCompatActivity {

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        settingFragment();
    }

    public void settingFragment() {
        ListFragment fragment = new ListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_background, fragment);
        transaction.commit();
    }
}
