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
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.R;

public class CustomDialogWordAddFragment extends DialogFragment implements View.OnClickListener {

    Dialog dialog;
    boolean bl_original;
    boolean bl_translated;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_add);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.decide_button).setOnClickListener(this);
        dialog.findViewById(R.id.decide_button).setEnabled(false);
        settingSpinner();
        watchState();
        return dialog;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.decide_button:
                    break;
            }
        }
    }

    public void settingSpinner() {
        //spinnerの設定
        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                TextView shownPart = (TextView) dialog.findViewById(R.id.part);
                shownPart.setText(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void watchState() {
        //監視するものの登録
        EditText edit_original = (EditText) dialog.findViewById(R.id.original_text);
        edit_original.addTextChangedListener(new GenericTextWatcher(edit_original));
        EditText edit_translated = (EditText) dialog.findViewById(R.id.translated_text);
        edit_translated.addTextChangedListener(new GenericTextWatcher(edit_translated));
    }

    public class GenericTextWatcher implements TextWatcher {
        //edittextの監視のクラス
        View view;

        public GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()) {
                case R.id.original_text:
                    if (charSequence.length() != 0) {
                        bl_original = true;
                    } else {
                        bl_original = false;
                    }
                    break;
                case R.id.translated_text:
                    if (charSequence.length() != 0) {
                        bl_translated = true;
                    } else {
                        bl_translated = false;
                    }
                    break;
            }
            if (bl_original && bl_translated) {
                enableButton();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    public void enableButton() {
        //ボタンの有効化
        dialog.findViewById(R.id.decide_button).setEnabled(true);
        dialog.findViewById(R.id.relative_error).setBackgroundColor(Color.parseColor("#00000000"));
    }
}
