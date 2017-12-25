package com.example.gotounaoto.myapplication.Activity;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogFinishFragment;
import com.example.gotounaoto.myapplication.Fragment.AddWordsFragment;
import com.example.gotounaoto.myapplication.R;

public class AddWordActivity extends AppCompatActivity {

    AddWordsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        Intent intent = getIntent();
        //title取得
        String title = intent.getStringExtra("title");
        settingToolbar(title);
        settingFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        boolean result = true;
        switch (id) {
            case android.R.id.home:
                finishActivity();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
            finishActivity();
            return true;
        } else {
            return false;
        }
    }

    public void finishActivity() {
        //戻るに関する全ての処理
        fragment.showDialog();
    }

    public void settingFragment() {
        fragment = new AddWordsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_background, fragment);
        transaction.commit();
    }

    public void settingToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
