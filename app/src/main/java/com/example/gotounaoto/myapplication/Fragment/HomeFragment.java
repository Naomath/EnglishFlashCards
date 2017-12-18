package com.example.gotounaoto.myapplication.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogCheckFragment;
import com.example.gotounaoto.myapplication.R;

public class HomeFragment extends Fragment implements View.OnClickListener{

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        view.findViewById(R.id.check_button).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view){
        if(view != null){
            switch (view.getId()){
                case R.id.check_button:
                    CustomDialogCheckFragment dialogCheckFragment = new CustomDialogCheckFragment();
                    dialogCheckFragment.show(getFragmentManager(),"check");
                break;

            }
        }

    }



    public void settingFont(TextView view){
       //Typeface kurochan = Typeface.createFromAsset(getActivity(),"kurochan.ttf");
    }

}
