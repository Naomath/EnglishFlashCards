package com.example.gotounaoto.myapplication.dialogFragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.R;

public class CustomDialogWeakPercentageFragment extends DialogFragment {

    View view;

    public CustomDialogWeakPercentageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_custom_dialog_weak_percentage, container, false);
        return view;
    }
}
