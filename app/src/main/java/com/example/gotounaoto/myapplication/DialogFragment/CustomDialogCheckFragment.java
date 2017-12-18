package com.example.gotounaoto.myapplication.DialogFragment;

import android.app.Dialog;
import android.content.Context;
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

import com.example.gotounaoto.myapplication.R;

public class CustomDialogCheckFragment extends DialogFragment implements View.OnClickListener{

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_check);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.today_button).setOnClickListener(this);
        dialog.findViewById(R.id.weak_button).setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        if(view != null){
            switch (view.getId()){
                case R.id.today_button:
                    dismiss();
                    //まだ決定してない
                    break;
                case R.id.weak_button:
                    dismiss();
                    //まだ決定してない
                    break;
            }
        }
    }
}
