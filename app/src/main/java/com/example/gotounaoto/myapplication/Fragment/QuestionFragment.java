package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnSendListListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionFragment extends Fragment {

    OnSendListListener onSendListListener;
    List<Words> presented_words;

    public QuestionFragment() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        settingListener();
        return view;
    }

    public void gettingWordList() {
        //ここでlistを取得する
        List<Words> source = onSendListListener.sendArrayList();
        presented_words = new ArrayList<>();
        for (Words item : source) {
            presented_words.add(item);
            //ここでディープコピー
        }
        Collections.shuffle(presented_words);
    }


    public void settingListener() {
        //リスナーの設定
        onSendListListener = (OnSendListListener) getActivity();
        gettingWordList();
    }

    public interface OnSendChangeListener {
        public void sendChangeListener();
    }

}

