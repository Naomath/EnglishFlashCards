package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnSendWordListener;

public class AnswerFragment extends Fragment implements View.OnClickListener {

    View view;
    OnSendWordListener onSendWordListener;
    OnSendChangeListener onSendChangeListener;
    Word word;


    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_answer, container, false);
        settingListener();
        settingTextView();
        return view;
    }

    public void gettingWord() {
        //wordを取得
        word = onSendWordListener.onSendWord();
    }

    public void settingListener() {
        onSendWordListener = (OnSendWordListener) getActivity();
        gettingWord();
        //ここでwordを取得する
        onSendChangeListener = (OnSendChangeListener) getActivity();
    }

    public void settingTextView() {
        TextView text_answer = (TextView) view.findViewById(R.id.text_answer);
        String string_answer = word.getTranslated();
        text_answer.setText(string_answer);
    }

    public void settingWordProportion(float mistake) {
        //出題された後のwordの誤答率
        float number_mistake = word.getNumber_mistake();
        float number_question = word.getNumber_question();
        word.setNumber_mistake(number_mistake + mistake);
        word.setNumber_question(number_question++);
        word.calculateProportion();
        if (word.getProportion() >= 30) {
            //誤答率30%以上で間違えやすい問題になる
            word.setBoolean_weak(true);
        }
        word.save();
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_know:
                    settingWordProportion(0);
                    onSendChangeListener.sendChange(0);
                    break;
                case R.id.button_dont:
                    settingWordProportion(1);
                    onSendChangeListener.sendChange(0);
                    break;

            }
        }
    }

    public interface OnSendChangeListener {
        //fragmentを変更するのを教えるために使うローカルインターフェイス。QuestionFragmentも持っている
        void sendChange(int which);
    }
}
