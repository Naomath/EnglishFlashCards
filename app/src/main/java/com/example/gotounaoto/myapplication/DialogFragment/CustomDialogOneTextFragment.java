package com.example.gotounaoto.myapplication.DialogFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.R;

public class CustomDialogOneTextFragment extends DialogFragment {
    //一つのテキストを入力するダイアログ

    View view;
    Bundle savedInstaceState;

    public CustomDialogOneTextFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstaceState = savedInstanceState;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_custom_dialog_one_text, container, false);
        return view;
    }

}
