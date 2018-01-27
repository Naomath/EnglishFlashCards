package com.example.gotounaoto.myapplication.classes;

import java.util.List;

/**
 * Created by gotounaoto on 2018/01/27.
 */

public class MakeString {

    public static String makeString(List<String> items){
        //受け取った文字列を連結して返す
        StringBuffer stringBuffer = new StringBuffer();
        for(String item:items){
            stringBuffer.append(item);
        }
        String returned = stringBuffer.toString();
        return returned;
    }
}
