package com.example.gotounaoto.myapplication.ExtendSugar;

import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by gotounaoto on 2017/12/19.
 */

public class SugarOrmControl extends SugarApp {
    @Override
    public void onCreate(){
        super.onCreate();
        SugarContext.init(this);
    }
}