package com.example.gotounaoto.myapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gotounaoto.myapplication.Fragment.HomeFragment;
import com.example.gotounaoto.myapplication.Fragment.BooksFragment;
import com.example.gotounaoto.myapplication.Fragment.SettngsFragment;
import com.example.gotounaoto.myapplication.Fragment.DownLoadFragment;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnIntentWordsListener;

public class MainActivity extends AppCompatActivity implements OnIntentWordsListener{

    private Fragment fragment;
    private Toolbar toolbar;

    @Override
    public void moveToWords(long id) {
        //インターフェイスの
        Intent intent = new Intent(this, WordsActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int which = 0;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    which = 0;
                    break;
                case R.id.navigation_cards:
                    which = 1;
                    break;
                case R.id.navigation_share:
                    which = 2;
                    break;
                case R.id.navigation_settings:
                    which = 3;
                    break;

            }
            judgmentFragment(which);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int which_fragment = gettingIntent();
        setContentView(R.layout.activity_main);
        settingBottomNavigation();
        settingToolBarFirst(0);
        judgmentFragment(which_fragment);

    }

    public int gettingIntent() {
        //インテントの取得
        Intent intent = getIntent();
        int which_fragment = intent.getIntExtra("which_fragment", 0);
        boolean which_toast = intent.getBooleanExtra("please_toast", false);
        String message_toast = intent.getStringExtra("message_toast");
        makeToast(which_toast, message_toast);
        return which_fragment;
    }


    public void settingBottomNavigation() {
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
    }

    public void settingFragment() {
        //フラグメントの一括設定
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative, fragment);
        transaction.commit();
    }

    public void settingToolBarFirst(int which) {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        settingToolBarSecond(which);
    }

    public void settingToolBarSecond(int which) {
        switch (which) {
            case 0:
                setTitle("Home");
                toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);
                break;
            case 1:
                setTitle("Books");
                toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);
                break;
            case 2:
                setTitle("Share");
                toolbar.setNavigationIcon(R.drawable.ic_share_white_24dp);
                break;
            case 3:
                setTitle("Settings");
                toolbar.setNavigationIcon(R.drawable.ic_settings_white_24dp);
                break;
        }
    }

    public void intentBooks(String title) {
        //ダイアログの後にintentする処理
        Intent intent = new Intent(this, AddWordActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void judgmentFragment(int which) {
        //どのフラグメントを最初に出すかの設定
        switch (which) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new BooksFragment();
                break;
            case 2:
                fragment = new DownLoadFragment();
                break;
            case 3:
                fragment = new SettngsFragment();
                break;
        }
        settingToolBarSecond(which);
        settingFragment();
    }

    public void makeToast(boolean which, String message) {
        //トーストを作るメソッド
        if (which) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT);
        }
    }

}