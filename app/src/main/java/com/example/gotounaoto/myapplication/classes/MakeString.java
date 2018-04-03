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

    public static String makeStringWithComma(List<String> items, int number){
        //受け取ったのを文字列にする
        //リストの中からいくつとって文字列にするのかは、引数のnumberの数だけにする
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i<number;i++){
            stringBuffer.append(items.get(i));
            if(!(--i==number)){
                stringBuffer.append(",");
                //ここで最後の単語の後にはカンマが入らないようにしている
            }
        }
        return stringBuffer.toString();
    }
}
