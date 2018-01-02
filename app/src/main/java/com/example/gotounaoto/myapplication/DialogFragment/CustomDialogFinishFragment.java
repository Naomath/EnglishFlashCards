package com.example.gotounaoto.myapplication.DialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnFinishListener;

public class CustomDialogFinishFragment extends DialogFragment implements View.OnClickListener {

    public OnFinishListener onFinishListener;

    Dialog dialog;

    public static CustomDialogFinishFragment newInstance(Fragment target, int requestCode) {
        CustomDialogFinishFragment fragment = new CustomDialogFinishFragment();
        fragment.setTargetFragment(target, requestCode);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public CustomDialogFinishFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_finish);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.button_yes).setOnClickListener(this);
        dialog.findViewById(R.id.button_no).setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onFinishListener = (OnFinishListener) getActivity();
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_yes:
                    //fragmentを変更する
                    onFinishListener.sendFinish();
                    getDialog().dismiss();
                    break;
                case R.id.button_no:
                    dialog.dismiss();
                    break;
            }
        }
    }

}
