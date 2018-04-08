package com.example.gotounaoto.myapplication.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.example.gotounaoto.myapplication.downloadFragment.DLBookInformationFragment;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.processings.BundleProcessing;
import com.example.gotounaoto.myapplication.processings.IntentProcessing;

public class DLBookInformationActivity extends AppCompatActivity implements DLBookInformationFragment.OnFinishListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        String book_path = gettingIntent();
        settingFragment(book_path);
        settingToolbar();
    }

    @Override
    public void finishActivity(String message) {
        backActivity();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            backActivity();
            return true;
        } else {
            return false;
        }
    }

    public String gettingIntent() {
        //前のactivityから伝えられたbook_pathをゲットしてリターンする
        return IntentProcessing.fromMainInDownload(this);
    }

    public void settingFragment(String book_path) {
        DLBookInformationFragment fragment = new DLBookInformationFragment();
        fragment.setArguments(BundleProcessing.toDlBookInformationFrInDownload(book_path));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative, fragment);
        transaction.commit();
    }

    public void settingToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backActivity();
            }
        });
        setTitle("Download");
    }

    public void backActivity() {
        IntentProcessing.backToMain(this, 2);
    }

    public void backActivityWithMessage(String message) {
        IntentProcessing.backToMainWithMessage(this, message, 2);
    }

}
