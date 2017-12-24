package com.example.gotounaoto.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.Fragment.AddWordsFragment;
import com.example.gotounaoto.myapplication.R;

/**
 * Created by gotounaoto on 2017/12/23.
 */

public class WordsAdapetr extends ArrayAdapter<Words> {

    Context context;
    LayoutInflater layoutInflater;
    int resource;
    float BUTTON_WIDTH_DP = 70f;
    int margin;


    public WordsAdapetr(@NonNull Context context, int resource) {
        //コンストラクタ
        super(context, resource);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.resource = resource;
        //widthを取得して引いてそのぶんずらしている
        float density = getContext().getResources().getDisplayMetrics().density;
        int buttonWidthPX = (int) (BUTTON_WIDTH_DP * density + 0.5f);
        WindowManager wm = (WindowManager)getContext().getSystemService(getContext().WINDOW_SERVICE);
        Display dp = wm.getDefaultDisplay();
        margin = dp.getWidth() - buttonWidthPX;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WordsAdapetr.ViewSetUp view_set_up;
        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, null);
            view_set_up = new WordsAdapetr.ViewSetUp(convertView);
            convertView.setTag(view_set_up);
        }
        //viewpagerの設定
        ViewPager viewPager = (ViewPager)convertView.findViewById(R.id.viewpager);
        viewPager.setPageMargin(-margin);
        MyPagerAdapter adapter = new MyPagerAdapter(getContext(),getItem(position));
        viewPager.setAdapter(adapter);
        return convertView;
    }

    private class ViewSetUp {

        TextView part;
        TextView original;
        TextView translated;

        public ViewSetUp(View view) {
            part = (TextView) view.findViewById(R.id.part);
            original = (TextView) view.findViewById(R.id.text_original);
            translated = (TextView) view.findViewById(R.id.text_translated);
        }
    }
}
