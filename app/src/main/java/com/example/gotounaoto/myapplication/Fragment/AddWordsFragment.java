package com.example.gotounaoto.myapplication.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.gotounaoto.myapplication.Activity.AddWordActivity;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogFinishFragment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogWordAddFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.BooksWords;
import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.WordsAdapter;
import com.example.gotounaoto.myapplication.classes.MakeDateString;
import com.example.gotounaoto.myapplication.interfaces.OnFinishListener;
import com.example.gotounaoto.myapplication.interfaces.OnInputListener;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class AddWordsFragment extends Fragment implements View.OnClickListener {

    View view;
    SwipeMenuListView listView;
    WordsAdapter adapetr;
    CustomDialogWordAddFragment dialog;
    AddWordActivity source;
    OnInputListener onInputListener;
    OnFinishListener onFinishListener;
    String title;
    Button decide_button;
    RelativeLayout error_layout;
    int number_items;

    public AddWordsFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        source = (AddWordActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_words, container, false);
        gettingViews();
        settingListener();
        settingListView();
        settingSwipeMenu();
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
                Words words = new Words(original, translated, part);
                adapetr.add(words);
                number_items++;
                enableButton();
                //アイテム数をプラス1しとく
                dialog.dismiss();
                return;
            case 2:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                source.goHome();
                //activityに遷移させる
                //ダイアログからきてこのfragmentを中継してactivityへ!!!
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_add:
                    dialog = new CustomDialogWordAddFragment();
                    dialog.setTargetFragment(this, 1);
                    dialog.show(getFragmentManager(), "add_words");
                    break;
                case R.id.button_decide:
                    List<Words> wordsList = new ArrayList<Words>();
                    for (int i = 0, length = adapetr.getCount(); i < length; i++) {
                        Words item = adapetr.getItem(i);
                        wordsList.add(item);
                    }
                    SugarRecord.saveInTx(wordsList);
                    Words firstWord = wordsList.get(0);
                    Words lastWord = wordsList.get(adapetr.getCount() - 1);
                    BooksWords group = new BooksWords(title, firstWord.getId(), lastWord.getId());
                    group.setDate(MakeDateString.makeDate());
                    group.save();
                    onFinishListener.sendFinish(true);
                    break;
            }
        }
    }

    public void gettingViews() {
        //ボタンのどのviewを取得
        decide_button = (Button) view.findViewById(R.id.button_decide);
        error_layout = (RelativeLayout) view.findViewById(R.id.relative_error);
    }

    public void settingListener() {
        view.findViewById(R.id.button_add).setOnClickListener(this);
        view.findViewById(R.id.button_decide).setOnClickListener(this);
        onInputListener = (OnInputListener) getActivity();
        onFinishListener = (OnFinishListener) getActivity();
        //ここでタイトル取得も
        title = onInputListener.sendText();
    }

    public void settingListView() {
        listView = (SwipeMenuListView) view.findViewById(R.id.list_view);
        adapetr = new WordsAdapter(getActivity(), R.layout.words_adapter);
        listView.setAdapter(adapetr);
        disableButton();
        //最初は無効化しとく
        number_items = 0;
        //アイテムの数をゼロに最初はしとく
    }

    public void settingSwipeMenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            //横に出るやつ
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xd3, 0x2f, 0x2f)));
                deleteItem.setIcon(R.drawable.ic_delete_white_48dp);
                deleteItem.setWidth(300);
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Words item = (Words) adapetr.getItem(position);
                        adapetr.remove(item);
                        number_items--;
                        if (number_items == 0) {
                            disableButton();
                        }
                        break;
                }
                return false;
            }
        });
    }

    public void showDialog() {
        CustomDialogFinishFragment fragment = new CustomDialogFinishFragment();
        fragment.show(getFragmentManager(), "finish");
    }

    public void enableButton() {
        //ボタンの有効化
        decide_button.setEnabled(true);
        error_layout.setVisibility(View.INVISIBLE);
    }

    public void disableButton() {
        //ボタンの無効化
        decide_button.setEnabled(false);
        error_layout.setVisibility(View.VISIBLE);
    }


}
