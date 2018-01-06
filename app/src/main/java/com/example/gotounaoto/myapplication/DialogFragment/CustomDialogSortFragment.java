package com.example.gotounaoto.myapplication.DialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.interfaces.OnSendSortListener;

public class CustomDialogSortFragment extends DialogFragment implements View.OnClickListener {

    Dialog dialog;
    OnSendSortListener onSendSortListener;

    public CustomDialogSortFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        settingDialog();
        settingListener();
        return dialog;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()){
                case R.id.button_faster:
                    submitToTaretFragment(0);
                    break;
                case R.id.button_older:
                    submitToTaretFragment(1);
                    break;
            }
        }
    }

    public void settingDialog() {
        //ダイアログの詳細設定
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_custom_dialog_sort);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void settingListener() {
        //リスナーの設定
        dialog.findViewById(R.id.button_faster).setOnClickListener(this);
        dialog.findViewById(R.id.button_older).setOnClickListener(this);
    }

    public void submitToTaretFragment(int which){
        Fragment target = getTargetFragment();
        target.onActivityResult(which, Activity.RESULT_OK,null);
    }

}
