package com.example.gotounaoto.myapplication.classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by gotounaoto on 2018/03/12.
 */

public class GettingDataBaseReference {
    public static DatabaseReference gettingUserReference() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("simple_books");
        return reference;
    }
}
