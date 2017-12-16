package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.R;

public class HomeFragment extends Fragment {

    public HomeFragment() {
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
        Button checkButton = (Button)getActivity().findViewById(R.id.checkButton);
        settingFont(checkButton);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void settingFont(TextView view){
      //  Typeface kurochan = Typeface.createFromAsset(getAssets(),"kurochan.ttf");
    }
}
