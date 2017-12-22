package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
        FloatingActionButton floatingActionButton = (FloatingActionButton)view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.fab:
                    makeDialog();
            }
        }
    }

    public void makeDialog() {
        CustomDialogAddFagment dialogAddFragment = new CustomDialogAddFagment();
        dialogAddFragment.show(getFragmentManager(), "add");
    }
}
