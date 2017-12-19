package com.example.gotounaoto.myapplication.DialogFragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gotounaoto.myapplication.Activity.MainActivity;
import com.example.gotounaoto.myapplication.R;

public class CustomDialogAddFagment extends DialogFragment implements View.OnClickListener {

    Dialog dialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_add);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.decide_button).setOnClickListener(this);
        dialog.findViewById(R.id.decide_button).setEnabled(false);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.decide_button:
                    EditText editText = (EditText) dialog.findViewById(R.id.edit_title);
                    String title = editText.getText().toString();
                    MainActivity activity = (MainActivity)getActivity();
                    activity.intentList(title);
                    break;
            }
        }
    }

    public void watchEdit(){
        EditText title = (EditText)dialog.findViewById(R.id.edit_title);
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0){
                    enableButton();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void enableButton(){
        dialog.findViewById(R.id.decide_button).setEnabled(true);
        dialog.findViewById(R.id.relative_error).setBackgroundColor(Color.parseColor("#00000000"));
    }

}
