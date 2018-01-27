package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FinishQuestionFragment extends Fragment {

    View view;
    OnSendAllNumberListener onSendAllNumberListener;
    OnSendMistakeNumberListener onSendMistakeNumberListener;
    int number_all;
    //問題数
    int number_mistake;
    //間違えた回数

    public FinishQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_finish_question, container, false);
        settingListener();
        settingTextView();
        return view;
    }

    public void gettingNumbers(){
        //問題数と間違えた回数を取得する
        number_all = onSendAllNumberListener.sendAllNumber();
        number_mistake = onSendMistakeNumberListener.sendMistakeNumber();
    }

    public void settingListener(){
        onSendAllNumberListener = (OnSendAllNumberListener)getActivity();
        onSendMistakeNumberListener = (OnSendMistakeNumberListener)getActivity();
        gettingNumbers();
    }

    public void settingTextView(){
        //textviewの設定をする
        TextView text_first = (TextView)view.findViewById(R.id.text_first);
        SharedPreferences user_preference = this.getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String user_name = user_preference.getString("name",null);
        List<String> first_items = new ArrayList<>();
        first_items.add(user_name);
        first_items.add("さんは");
        first_items.add(String.valueOf(number_all));
        
    }

    public interface OnSendAllNumberListener{
        //問題数を送るinterface
        int sendAllNumber();
    }

    public interface  OnSendMistakeNumberListener{
        //間違えた数を送るinterface
        int sendMistakeNumber();
    }

}
