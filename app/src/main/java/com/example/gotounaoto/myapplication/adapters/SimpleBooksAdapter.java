package com.example.gotounaoto.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.ExtendSugar.Book;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.classes.SimpleBook;

/**
 * Created by gotounaoto on 2018/02/13.
 */

public class SimpleBooksAdapter extends ArrayAdapter<SimpleBook>{

    Context context;
    LayoutInflater layoutInflater;
    int resource;

    public SimpleBooksAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleBooksAdapter.ViewSetUp view_set_up;
        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, null);
            view_set_up = new SimpleBooksAdapter.ViewSetUp(convertView);
            convertView.setTag(view_set_up);
        } else {
            view_set_up = ((SimpleBooksAdapter.ViewSetUp) convertView.getTag());
        }
        SimpleBook item = getItem(position);
        if (item != null) {
            //ここにlayoutの処理をかく
            view_set_up.view_title.setText(item.getTitle());
            view_set_up.view_calendar.setText(item.getDate());
            view_set_up.view_user.setText(item.getUser_name());
        }
        return convertView;
    }

    private class ViewSetUp {

        TextView view_title;
        TextView view_calendar;
        TextView view_user;

        public ViewSetUp(View view) {
            view_title = (TextView)view.findViewById(R.id.title_text);
            view_calendar = (TextView)view.findViewById(R.id.calendar_text);
            view_user = (TextView)view.findViewById(R.id.user_text);
        }
    }
}
