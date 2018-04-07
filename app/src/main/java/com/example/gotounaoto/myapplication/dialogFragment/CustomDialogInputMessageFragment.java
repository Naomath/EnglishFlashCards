package com.example.gotounaoto.myapplication.dialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.classes.TextsAndNumbers;
import com.example.gotounaoto.myapplication.processings.BundleProcessing;
import com.example.gotounaoto.myapplication.processings.IntentProcessing;

public class CustomDialogInputMessageFragment extends DialogFragment implements View.OnClickListener {
    //一つのテキストを入力するダイアログ

    Dialog dialog;
    Button button;
    RelativeLayout error_layout;
    EditText edit_text;
    TextsAndNumbers item;

    public CustomDialogInputMessageFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gettingBundle();
        settingDialog();
        settingButton();
        settingEditText();
        settingRelativeLayout();
        return dialog;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.decide_button:
                    decide();
                    break;
            }
        }
    }

    public void decide() {
        //buttonが押された時の処理
        Fragment target = getTargetFragment();
        target.onActivityResult(item.getNumbers().get(0), Activity.RESULT_OK
                , IntentProcessing.fromMessageDialog(edit_text.getText().toString()));
    }

    public void gettingBundle() {
        //送られてきたbundleを取得する
        item = BundleProcessing.inMessageDialog(this);
    }

    public void settingButton() {
        button = (Button) dialog.findViewById(R.id.decide_button);
        button.setOnClickListener(this);
        disableButton();
    }

    public void settingDialog() {
        dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.fragment_custom_dialog_input_message);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void settingEditText() {
        edit_text = (EditText) dialog.findViewById(R.id.edit_message);
        String message = item.getTexts().get(0);
        if (message != null) {
            edit_text.setText(message);
            enableButton();
        }
        edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    enableButton();
                } else {
                    disableButton();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void settingRelativeLayout() {
        error_layout = (RelativeLayout) dialog.findViewById(R.id.relative_error);
    }

    public void enableButton() {
        //ボタンの有効化
        button.setEnabled(true);
        error_layout.setVisibility(View.INVISIBLE);
    }

    public void disableButton() {
        //ボタンの無効化
        button.setEnabled(false);
        error_layout.setVisibility(View.VISIBLE);
    }
}
