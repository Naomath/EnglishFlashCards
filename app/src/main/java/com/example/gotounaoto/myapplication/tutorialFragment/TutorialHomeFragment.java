package com.example.gotounaoto.myapplication.tutorialFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.R;

public class TutorialHomeFragment extends Fragment {

    View view;

    public TutorialHomeFragment() {
        // Required empty public constructor
    }

    public static TutorialHomeFragment newInstance() {
        TutorialHomeFragment fragment = new TutorialHomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingThisView(inflater, container);
        return view;
    }

    public void settingThisView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_tutorial_home, container, false);
    }

}
