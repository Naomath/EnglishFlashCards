package com.example.gotounaoto.myapplication.tutorialActivity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.processings.IntentProcessing;
import com.example.gotounaoto.myapplication.tutorialFragment.TutorialAddWordsFragment;
import com.github.amlcurran.showcaseview.ShowcaseView;

public class TutorialAddWordsActivity extends AppCompatActivity implements TutorialAddWordsFragment.OnFinishListener
        , TutorialAddWordsFragment.OnSendTextListener {

    Toolbar toolbar;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_add_words);
        gettingIntent();
        settingToolbar();
        settingFragment();
    }

    @Override
    public void finishActivity(String message) {
        IntentProcessing.backToTutorialMainWithMessage(this, message, 0);
    }

    @Override
    public String sendText(){
        return title;
    }

    public void gettingIntent() {
        title = IntentProcessing.inTutorialAddWords(this);
    }

    public void settingToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setTitle(title);
    }

    public void settingFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.relative_background, TutorialAddWordsFragment.newInstance());
        transaction.commit();
    }
}
