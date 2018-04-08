package com.example.gotounaoto.myapplication.tutorialActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.mainFragment.SettingsUserFragment;
import com.example.gotounaoto.myapplication.processings.CallSharedPreference;
import com.example.gotounaoto.myapplication.processings.IntentProcessing;
import com.example.gotounaoto.myapplication.tutorialFragment.TutorialBooksFragment;
import com.example.gotounaoto.myapplication.tutorialFragment.TutorialHomeFragment;
import com.example.gotounaoto.myapplication.tutorialFragment.TutorialShareFragment;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class TutorialMainActivity extends AppCompatActivity {

    int number_step;
    int which_fragment;
    Toolbar toolbar;
    BottomNavigationView navigation;
    ShowcaseView showcaseView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    clickHome();
                    break;
                case R.id.navigation_cards:
                    clickCards();
                    break;
                case R.id.navigation_share:
                    clickShare();
                    break;
                case R.id.navigation_settings:
                    clickSettings();
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_main);
        gettingStep();
        settingBottomNavigation();
        settingToolbar();
        judgmentWhichFragment();
        settingShowcaseView();
    }

    public void clickHome() {
        switch (number_step) {
            case 1:
                break;
            case 2:
                which_fragment = 0;
                decorateToolbar();
                replaceFragment();
                break;
            case 3:
                pushBottomNavigation();
                break;
            case 4:
                pushBottomNavigation();
                break;
        }
    }

    public void clickCards() {
        switch (number_step) {
            case 1:
                showcaseView.hide();
                which_fragment = 1;
                decorateToolbar();
                replaceFragment();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                pushBottomNavigation();
                break;
        }
    }

    public void clickShare() {
        switch (number_step) {
            case 1:
                pushBottomNavigation();
                break;
            case 2:
                pushBottomNavigation();
                break;
            case 3:
                pushBottomNavigation();
                break;
            case 4:
                break;
        }
    }

    public void clickSettings() {
        pushBottomNavigation();
    }

    public void decorateToolbar() {
        switch (which_fragment) {
            case 0:
                setTitle("Home");
                toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);
                break;
            case 1:
                setTitle("Books");
                toolbar.setNavigationIcon(R.drawable.flashcards_icon_white);
                break;
            case 2:
                setTitle("Share");
                toolbar.setNavigationIcon(R.drawable.ic_arrow_downward_white_24dp);
                break;
            case 3:
                setTitle("Settings");
                toolbar.setNavigationIcon(R.drawable.ic_settings_white_24dp);
                break;
        }
    }

    public void gettingStep() {
        //このmainactivityが呼び出されている時どのstepなのか取得する
        number_step = CallSharedPreference.callTutorialMainStep(this);
    }

    public void judgmentWhichFragment() {
        switch (number_step) {
            case 1:
                which_fragment = 0;
                break;
            case 2:
                which_fragment = 1;
                break;
            case 3:
                which_fragment = 1;
                break;
            case 4:
                which_fragment = 2;
                break;
        }
    }

    public void pushBottomNavigation() {
        switch (which_fragment) {
            case 0:
                navigation.setSelectedItemId(R.id.navigation_home);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.navigation_cards);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.navigation_share);
                break;
            case 3:
                navigation.setSelectedItemId(R.id.navigation_settings);
                break;
        }
    }

    public void settingBottomNavigation() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void settingToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
    }

    public void settingShowcaseView() {
        showcaseView = new ShowcaseView.Builder(this)
                .setTarget(new ViewTarget(navigation))
                .setContentTitle("ようこそ")
                .setContentText("早速、単語帳を登録していきます。下の単語帳のアイコンをタップしてください.")
                .setStyle(R.style.CustomShowcaseTheme)
                .withMaterialShowcase()
                .doNotBlockTouches() //ShowcaseView下のボタンを触れるように。
                .build();
        showcaseView.hideButton();
    }

    public void replaceFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (which_fragment) {
            case 0:
                transaction.replace(R.id.relative, TutorialHomeFragment.newInstance());
                break;
            case 1:
                transaction.replace(R.id.relative, TutorialBooksFragment.newInstance());
                break;
            case 2:
                transaction.replace(R.id.relative, TutorialShareFragment.newInstance());
                break;
            case 3:
                transaction.replace(R.id.relative, SettingsUserFragment.newInstance());
                break;
        }
        transaction.commit();
    }
}
