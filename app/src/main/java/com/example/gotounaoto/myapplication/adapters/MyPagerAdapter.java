package com.example.gotounaoto.myapplication.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.ExtendSugar.Words;
import com.example.gotounaoto.myapplication.R;

/**
 * Created by gotounaoto on 2017/12/23.
 */

public class MyPagerAdapter extends PagerAdapter {

    LayoutInflater inflater;
    int PAGE_NUM = 2;
    Words word;

    public MyPagerAdapter(Context context, Words word) {
        super();
        inflater = LayoutInflater.from(context);
        this.word = word;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout layout = null;
        if(position == 0){
            layout = (LinearLayout)inflater.inflate(R.layout.words_add_page1, null);
            TextView part = (TextView) layout.findViewById(R.id.text_part);
            TextView original = (TextView) layout.findViewById(R.id.text_original);
            TextView translated = (TextView) layout.findViewById(R.id.text_translated);
            String str_part = word.getPart().substring(0,1);
            String str_original = word.getOriginal();
            String str_translated = word.getTranslated();
            part.setText(str_part);
            original.setText(str_original);
            translated.setText(str_translated);
        }else{
            layout = (LinearLayout)inflater.inflate(R.layout.words_add_page2, null);
        }
        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {

        return PAGE_NUM;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

}
