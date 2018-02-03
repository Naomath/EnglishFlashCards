package com.example.gotounaoto.myapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogOneTextFragment;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.SettingUserAdapter;
import com.example.gotounaoto.myapplication.classes.TwoText;

import java.util.ArrayList;
import java.util.List;

public class SettingsUserFragment extends Fragment {

    View view;
    ListView listView;
    SettingUserAdapter adapter;

    public SettingsUserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting_user, container, false);
        settingListView();
        return view;
    }

    public void settingListView() {
        //listviewの設定
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new SettingUserAdapter(getActivity(), R.layout.adapter_setting_user);
        listView.setAdapter(adapter);
        addItemList();
        //上のメソッドでadapterにitemsをセットする
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TwoText item = (TwoText) adapter.getItem(i);
                int item_id = item.getId();
                switch (item_id) {
                    case 0:
                        //ここでユーザー名を変える
                        CustomDialogOneTextFragment dialogOneTextFragment = new CustomDialogOneTextFragment();
                        Bundle savedInstanceState = new Bundle();
                        savedInstanceState.putString("title", "User Name");
                        dialogOneTextFragment.setArguments(savedInstanceState);
                        dialogOneTextFragment.show(getFragmentManager(),"user_name");
                        //この上のtagでどれにするか判別する
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    public void addItemList() {
        //listviewに要素を追加する
        List<TwoText> list_item = new ArrayList<>();
        SharedPreferences preferences_user = getActivity().getSharedPreferences("user"
                , Context.MODE_PRIVATE);
        TwoText item1 = new TwoText("ユーザー名", preferences_user.getString("name", null), 0);
        list_item.add(item1);
        TwoText item2 = new TwoText("間違えやすい問題になる誤答率"
                , String.valueOf(preferences_user.getFloat("percentage", 30f)) + "%", 1);
        list_item.add(item2);
        adapter.addAll(list_item);
    }

}
