package com.example.gotounaoto.myapplication.downloadFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.adapters.InformationAdapter;
import com.example.gotounaoto.myapplication.processings.BundleProcessing;
import com.example.gotounaoto.myapplication.processings.CallSharedPreference;
import com.example.gotounaoto.myapplication.processings.FirebaseProcessing;
import com.example.gotounaoto.myapplication.classes.InformationText;
import com.example.gotounaoto.myapplication.extendSugar.Book;
import com.example.gotounaoto.myapplication.extendSugar.Word;
import com.example.gotounaoto.myapplication.processings.MakeString;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class DLBookInformationFragment extends Fragment implements FirebaseProcessing.OnReturnItemListener, View.OnClickListener {

    View view;
    InformationAdapter adapter;
    Book item;
    DLBookInformationFragment.OnFinishListener onFinishListener;
    String book_path;
    ShowcaseView showcaseView;

    public DLBookInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void returnItem(Book item) {
        this.item = item;
        addInformation();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dl_book_information, container, false);
        settingListener();
        settingListView();
        gettingBook(BundleProcessing.inDlBookInformationFrInDownload(this));
        //この順番は非同期などを考えうまくできているのでいじらないで
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.button_download:
                    download();
                    break;
            }
        }
    }

    public void addDownloadedTime() {
        //ダウンロードされた回数を追加する
        FirebaseProcessing firebaseProcessing = new FirebaseProcessing();
        firebaseProcessing.startAddDownloadedTime(book_path);
    }

    public void addInformation() {
        //アイテムを追加する
        List<InformationText> information = new ArrayList<>();
        information.add(new InformationText("タイトル", item.getTitle(), 25f));
        information.add(new InformationText("作成者", item.getUser_name(), 25f));
        information.add(new InformationText("作成日", item.getDate(), 25f));
        information.add(new InformationText("ダウンロード回数", String.valueOf(item.getDownload_time()), 25f));
        information.add(new InformationText("説明", item.getMessage(), 18f));
        information.add(new InformationText("単語数", String.valueOf(item.getList_words().size()), 25f));
        information.add(new InformationText("単語例", MakeString.makeStringWithComma(item.returnListOriginal(1), 5), 20f));
        adapter.addAll(information);
    }

    public void download() {
        //実際にbookをダウンロードする処理
        showcaseView.hide();
        List<Word> list_words = item.getList_words();
        SugarRecord.saveInTx(list_words);
        item.setFirst_id(list_words.get(0).getId());
        item.setLast_id(list_words.get(list_words.size() - 1).getId());
        item.setDone_upload(2);
        item.save();
        if (CallSharedPreference.callTutorialMainStep(getActivity())==4){
            CallSharedPreference.addTutorialMainStep(getActivity());
            onFinishListener.finishActivity("ダウンロードを終了しました。これでチュートリアルを終了します。");
        }else {
            onFinishListener.finishActivity("ダウンロードが終了しました。");
        }
        addDownloadedTime();
    }

    public void gettingBook(String book_path) {
        //押されたbookをゲットする
        this.book_path = book_path;
        FirebaseProcessing firebaseProcessing = new FirebaseProcessing(this);
        firebaseProcessing.startSearchPath(book_path);
    }


    public void settingListener() {
        //リスナーの設定
        view.findViewById(R.id.button_download).setOnClickListener(this);
        onFinishListener = (OnFinishListener) getActivity();
    }

    public void settingListView() {
        //リストビューの設定
        ListView listView = view.findViewById(R.id.list_view);
        adapter = new InformationAdapter(getActivity(), R.layout.adapter_information);
        listView.setAdapter(adapter);
    }

    public void settingShowcaseView(){
        if(CallSharedPreference.callTutorialMainStep(getActivity())==4){
            showcaseView = new ShowcaseView.Builder(getActivity())
                    .setTarget(new ViewTarget(view.findViewById(R.id.button_download)))
                    .setContentTitle("単語帳のダウンロード")
                    .setContentText("下のダウンロードボタンを押してダウンロードしてください。")
                    .setStyle(R.style.CustomShowcaseTheme)
                    .withMaterialShowcase()
                    .doNotBlockTouches() //ShowcaseView下のボタンを触れるように。
                    .build();
            showcaseView.hideButton();
        }
    }

    public interface OnFinishListener {
        void finishActivity(String message);
    }


}
