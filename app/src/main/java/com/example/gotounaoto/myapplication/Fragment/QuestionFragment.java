package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnSendListListener;

public class QuestionFragment extends Fragment {

    OnSendListListener onSendListListener;

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

    

    public void settingListener(){
        onSendListListener = (OnSendListListener)getActivity();

    }

}
