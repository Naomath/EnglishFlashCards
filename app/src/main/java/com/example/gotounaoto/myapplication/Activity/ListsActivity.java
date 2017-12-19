package com.example.gotounaoto.myapplication.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gotounaoto.myapplication.R;

public class ListsActivity extends AppCompatActivity implements View.OnClickListener {

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");

    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_add:
                    break;
                case R.id.button_decide:
                    break;
            }
        }
    }
}
