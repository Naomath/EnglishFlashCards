package com.example.gotounaoto.myapplication.classes;

import com.example.gotounaoto.myapplication.extendSugar.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by gotounaoto on 2018/03/12.
 */

public class FirebaseProcessing {
    //firebaseに関する処理をここに書く

    OnAddItemListener onAddItemListener;

    String keyword;
    //searchに使うキーワード

    public FirebaseProcessing() {
        //empty constructor
    }

    public FirebaseProcessing(OnAddItemListener onAddItemListener) {
        this.onAddItemListener = onAddItemListener;
    }

    public static DatabaseReference gettingUserReference() {
        //"users"のリファレンスを取得する
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        return reference;
    }

    public static DatabaseReference gettingBookReference(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("books");
        return reference;
    }

    public void judgmentSearch(int which) {
        //どっちのsearchをするのか判断する
        switch (which) {
            case 0:
                searchBooksHigherDownloaded();
                break;
            case 1:
                searchBooksByKeyword();
                break;
        }
    }


    public void searchBooksHigherDownloaded() {
        //ダウンロード数が多いTop20を取得する
        gettingBookReference().orderByChild("download_time").limitToLast(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book item = snapshot.getValue(Book.class);
                    onAddItemListener.addItem(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void searchBooksByKeyword() {
        //キーワードありで探す
            gettingBookReference().orderByChild("title").equalTo(keyword).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Book item = snapshot.getValue(Book.class);
                        onAddItemListener.addItem(item);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    public void startSearchHigher() {
        //Highersearchをする時に呼ぶメソッド
        judgmentSearch(0);
    }

    public void startSearchKeyword(String keyword) {
        judgmentSearch(1);
        this.keyword = keyword;
    }

    public interface OnAddItemListener {
        //adapterにitemを入れるためのインターフェイス
        void addItem(Book item);
    }
}

