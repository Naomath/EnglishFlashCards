package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogAddFagment;
import com.example.gotounaoto.myapplication.R;

public class ListsFragment extends Fragment implements View.OnClickListener {

    public ListsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        view.findViewById(R.id.add_button_top).setOnClickListener(this);
        view.findViewById(R.id.add_button_bottom).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.add_button_top:
                    makeDialog();
                    break;
                case R.id.add_button_bottom:
                    makeDialog();
                    break;
            }
        }
    }

    public void makeDialog() {
        CustomDialogAddFagment dialogAddFagment = new CustomDialogAddFagment();
        dialogAddFagment.show(getFragmentManager(), "add");
    }
}
