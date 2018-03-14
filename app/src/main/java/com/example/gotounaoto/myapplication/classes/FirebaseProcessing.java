package com.example.gotounaoto.myapplication.classes;

import com.example.gotounaoto.myapplication.ExtendSugar.Book;
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

    public static DatabaseReference gettingUserReference() {
        //"users"のリファレンスを取得する
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");
        return reference;
    }

    public static List<String> gettingUserIds() {
        //firebase常にuserのidを取得してくる
        final List<String> user_ids = new ArrayList<>();
        //これはreturnするidの箱
        gettingUserReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String user_id = snapshot.getKey();
                    //useridはkeyの言葉になっているから
                    user_ids.add(user_id);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return user_ids;
    }

    public static List<Book> searchBooksByKeyword(String keyword) {
        //キーワードありで探す
        final List<Book> books = new ArrayList<>();
        for (String user_id : gettingUserIds()) {
            gettingUserReference().child(user_id).child("books").child("title").equalTo(keyword).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Book item = snapshot.getValue(Book.class);
                        books.add(item);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return books;
    }

    public static List<Book> searchBooksHigherDownloaded() {
        //ダウンロード数が多いTop20を取得する
        final List<Book> books = new ArrayList<>();
        for (String user_id : gettingUserIds()) {
            gettingUserReference().child(user_id).child("books").child("download_time").limitToLast(20).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Book item = snapshot.getValue(Book.class);
                        books.add(item);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        return books;
    }
}
