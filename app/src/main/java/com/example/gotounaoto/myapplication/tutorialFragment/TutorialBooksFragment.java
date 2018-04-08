package com.example.gotounaoto.myapplication.tutorialFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.BooksAdapter;
import com.example.gotounaoto.myapplication.dialogFragment.CustomDialogAddBookFragment;
import com.example.gotounaoto.myapplication.extendSugar.Book;
import com.example.gotounaoto.myapplication.interfaces.OnIntentWordsListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.List;

public class TutorialBooksFragment extends Fragment {

    View view;
    ListView listView;
    BooksAdapter adapter;
    ShowcaseView showcaseView;
    FloatingActionButton floatingActionButton;

    public TutorialBooksFragment() {
        // Required empty public constructor
    }

    public static TutorialBooksFragment newInstance() {
        TutorialBooksFragment fragment = new TutorialBooksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingThisView(inflater, container);
        settingFab();
        settingListView();
        addItems();
        return view;
    }

    public void addItems() {
        List<Book> items = Book.listAll(Book.class);
        adapter.addAll(items);
    }

    public void settingThisView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_tutorial_books, container, false);
    }

    public void settingFab() {
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcaseView.hide();
                CustomDialogAddBookFragment dialogAddFragment = new CustomDialogAddBookFragment();
                dialogAddFragment.show(getFragmentManager(), "add");
            }
        });
    }


    public void settingListView() {
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new BooksAdapter(getActivity(), R.layout.adapter_books);
        listView.setAdapter(adapter);
    }

    public void settingShowcaseView() {
        showcaseView = new ShowcaseView.Builder(getActivity())
                .setTarget(new ViewTarget(floatingActionButton))
                .setContentTitle("単語帳の登録")
                .setContentText("まずはこのプラスのボタンを押してください。そして単語帳のタイトルを入力して、ボタンを押してください。")
                .withMaterialShowcase()
                .doNotBlockTouches() //ShowcaseView下のボタンを触れるように。
                .build();
        showcaseView.hideButton();
    }
}
