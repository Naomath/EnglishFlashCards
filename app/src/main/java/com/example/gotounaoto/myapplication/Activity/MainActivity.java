package com.example.gotounaoto.myapplication.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gotounaoto.myapplication.Fragment.HomeFragment;
import com.example.gotounaoto.myapplication.Fragment.ListsFragment;
import com.example.gotounaoto.myapplication.Fragment.SettngsFragment;
import com.example.gotounaoto.myapplication.Fragment.ShareFragment;
import com.example.gotounaoto.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    settingFragment();
                    break;
                case R.id.navigation_cards:
                    fragment = new ListsFragment();
                    settingFragment();
                    break;
                case R.id.navigation_share:
                    fragment = new ShareFragment();
                    break;
                case R.id.navigation_settings:
                    fragment = new SettngsFragment();
                    settingFragment();
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new HomeFragment();
        settingFragment();
    }

    public void settingFragment() {
        //フラグメントの一括設定
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative, fragment);
        transaction.commit();
    }
}
