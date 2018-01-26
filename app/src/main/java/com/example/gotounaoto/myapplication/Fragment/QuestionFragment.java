package com.example.gotounaoto.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnSendWordListener;

public class QuestionFragment extends Fragment implements View.OnClickListener {

    OnSendWordListener onSendWordListener;
    OnSendChangeListener onSendChangeListener;
    Word word;
    View view;

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
        view = inflater.inflate(R.layout.fragment_question, container, false);
        settingListener();
        settingTextView();
        return view;
    }


    public void gettingWord() {
        //出題するワードを出す
        word = onSendWordListener.onSendWord();
    }

    public void settingListener() {
        //リスナーの設定
        onSendWordListener = (OnSendWordListener) getActivity();
        gettingWord();
        onSendChangeListener = (OnSendChangeListener) getActivity();
    }

    public void settingTextView() {
        //textviewの設定
        TextView text_question = (TextView) view.findViewById(R.id.text_question);
        String string_question = word.getOriginal();
        text_question.setText(string_question);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_next:
                    onSendChangeListener.sendChange(1);
                    //ここでフラグメント変更を通知
                    break;
            }
        }
    }

    public interface OnSendChangeListener {
        //fragmentを変更するのを教えるために使うローカルインターフェイス。AnswerFragmentも持っている
        public void sendChange(int which);
    }
}

