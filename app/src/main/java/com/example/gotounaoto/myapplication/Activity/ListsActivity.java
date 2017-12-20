package com.example.gotounaoto.myapplication.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogAddFagment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogCheckFragment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogWordAddFragment;
import com.example.gotounaoto.myapplication.R;

public class ListsActivity extends AppCompatActivity {

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");

    }
}
