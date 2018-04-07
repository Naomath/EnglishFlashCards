package com.example.gotounaoto.myapplication.processings;

import android.app.Activity;

import com.example.gotounaoto.myapplication.extendSugar.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gotounaoto on 2018/03/12.
 */

public class FirebaseProcessing {
    //firebaseに関する処理をここに書く

    OnAddItemListener onAddItemListener;

    OnReturnItemListener onReturnItemListener;

    String keyword;
    //searchに使うキーワード

    String book_path;
    //searchに使うfirebaseでもpath

    public FirebaseProcessing() {
        //empty constructor
    }

    public FirebaseProcessing(OnAddItemListener onAddItemListener) {
        this.onAddItemListener = onAddItemListener;
    }

    public FirebaseProcessing(OnReturnItemListener onReturnItemListener) {
        this.onReturnItemListener = onReturnItemListener;
    }

    public static void deleteBook(String book_path) {
        gettingBookReference().child(book_path).removeValue();
    }

    public static DatabaseReference gettingUserReference() {
        //"users"のリファレンスを取得する
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        return reference;
    }

    public static DatabaseReference gettingBookReference() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("books");
        return reference;
    }

    public static void gettingULBook(final Book book, final OnReturnItemListener onReturnItemListener) {
        //このメソッドでアプリないで管理できてない、ダウンロードの回数を取得してきてセットする
        final List<String> message = new ArrayList<>(1);
        final List<Integer> download_time = new ArrayList<>(1);
        gettingBookReference().child(book.getBook_path()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Book item = dataSnapshot.getValue(Book.class);
                message.add(item.getMessage());
                download_time.add(item.getDownload_time());
                book.setDownload_time(download_time.get(0));
                onReturnItemListener.returnItem(book);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void searchBooksHigherDownloaded() {
        //ダウンロード数が多いTop20を取得する
        gettingBookReference().orderByChild("download_time").limitToLast(20).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book item = snapshot.getValue(Book.class);
                    item.setBook_path(snapshot.getKey());
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
        gettingBookReference().orderByChild("title").startAt(keyword).endAt(keyword + "\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book item = snapshot.getValue(Book.class);
                    item.setBook_path(snapshot.getKey());
                    onAddItemListener.addItem(item);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void searchBookByBookPath() {
        //book_pathで探す
        gettingBookReference().child(book_path).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Book item = dataSnapshot.getValue(Book.class);
                onReturnItemListener.returnItem(item);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void gettingDownloadedTime() {
        //ダウンロードされた回数を取得して流す
        gettingBookReference().child(book_path).child("download_time").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int time = dataSnapshot.getValue(Integer.class);
                addDownloadTime(time);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addDownloadTime(int time) {
        //ダウンロードした回数を追加する
        gettingBookReference().child(book_path).child("download_time").setValue(time + 1);
    }

    public static void updateMessage(String book_path, String message) {
        gettingUserReference().child(book_path).child("message").setValue(message);
    }

    public void startSearchHigher() {
        //Highersearchをする時に呼ぶメソッド
        searchBooksHigherDownloaded();
    }

    public void startSearchKeyword(String keyword) {
        this.keyword = keyword;
        searchBooksByKeyword();
    }

    public void startSearchPath(String book_path) {
        this.book_path = book_path;
        searchBookByBookPath();
    }

    public void startAddDownloadedTime(String book_path) {
        this.book_path = book_path;
        gettingDownloadedTime();
        //流れ的にはgettingDownloadTime()->addDownloadTime()
    }

    public interface OnAddItemListener {
        //adapterにitemを入れるためのインターフェイス
        void addItem(Book item);
    }

    public interface OnReturnItemListener {
        void returnItem(Book item);
    }

}

