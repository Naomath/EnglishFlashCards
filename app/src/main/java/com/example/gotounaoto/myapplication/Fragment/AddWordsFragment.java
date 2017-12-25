package com.example.gotounaoto.myapplication.Fragment;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.gotounaoto.myapplication.Activity.AddWordActivity;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogFinishFragment;
import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogWordAddFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.WordsAdapetr;

public class AddWordsFragment extends Fragment implements View.OnClickListener {

    SwipeMenuListView listView;
    WordsAdapetr adapetr;
    CustomDialogWordAddFragment dialog;
    AddWordActivity source;

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
        listView = (SwipeMenuListView) view.findViewById(R.id.list_view);
        adapetr = new WordsAdapetr(getActivity(), R.layout.words_add_adapter);
        listView.setAdapter(adapetr);
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
                        Words item = (Words)adapetr.getItem(position);
                        adapetr.remove(item);
                        break;
                }
                return false;
            }
        });
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
                dialog.dismiss();
                return;
            case 2:
                if(resultCode != Activity.RESULT_OK){
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
                    break;
            }
        }
    }

    public void showDialog(){
        CustomDialogFinishFragment fragment = new CustomDialogFinishFragment();
        fragment.show(getFragmentManager(),"finish");
    }

    @Override
    public void onAttach(Activity activity) {
        source = (AddWordActivity) activity;
        super.onAttach(activity);
    }
}
