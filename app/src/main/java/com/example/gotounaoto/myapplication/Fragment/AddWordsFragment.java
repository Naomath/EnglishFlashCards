package com.example.gotounaoto.myapplication.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogWordAddFragment;
import com.example.gotounaoto.myapplication.R;

public class AddWordsFragment extends Fragment implements View.OnClickListener {

    public AddWordsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_words, container, false);
        view.findViewById(R.id.button_add).setOnClickListener(this);
        view.findViewById(R.id.button_decide).setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                String original = data.getStringExtra("original");
                String translated = data.getStringExtra("translated");
                String part = data.getStringExtra("part");
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_add:
                    CustomDialogWordAddFragment dialog = new CustomDialogWordAddFragment();
                    dialog.show(getFragmentManager(), "add_words");
                    break;
                case R.id.button_decide:
                    break;
            }
        }
    }
}
