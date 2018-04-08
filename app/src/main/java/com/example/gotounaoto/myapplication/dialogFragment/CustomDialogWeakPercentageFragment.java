package com.example.gotounaoto.myapplication.dialogFragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.processings.CallSharedPreference;

public class CustomDialogWeakPercentageFragment extends DialogFragment {

    View view;
    NumberPicker numberPicker;

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
        settingThisView(inflater, container);
        settingDecideButton();
        settingNumberPicker();
        return view;
    }

    public void settingThisView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_custom_dialog_weak_percentage, container, false);
    }

    public void settingDecideButton() {
        view.findViewById(R.id.button_decide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float percentage = (float) numberPicker.getValue();
                CallSharedPreference.saveWeakPercentage(getActivity(), percentage);
                finishThis();
            }
        });
    }

    public void settingNumberPicker() {
        numberPicker = (NumberPicker) view.findViewById(R.id.number_picker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(1);
        numberPicker.setValue((int) CallSharedPreference.callWeakPercentage(getActivity()));
    }

    public void finishThis() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
