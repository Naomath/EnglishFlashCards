package com.example.gotounaoto.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.classes.TwoText;

import org.w3c.dom.Text;

/**
 * Created by gotounaoto on 2018/02/02.
 */

public class SettingUserAdapter extends ArrayAdapter<TwoText> {
    Context context;
    LayoutInflater layoutInflater;
    int resource;

    public SettingUserAdapter(@NonNull Context context, int resource) {
        //コンストラクタ
        super(context, resource);
        this.context = context;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SettingUserAdapter.ViewSetUp view_set_up;
        if (convertView == null) {
            convertView = layoutInflater.inflate(resource, null);
            view_set_up = new SettingUserAdapter.ViewSetUp(convertView);
            convertView.setTag(view_set_up);
        } else {
            view_set_up = ((SettingUserAdapter.ViewSetUp) convertView.getTag());
            TwoText item = getItem(position);
            view_set_up.text_title.setText(item.getFirst_text());
            view_set_up.text_sub_title.setText(item.getSecond_text());
        }

        return convertView;
    }

    private class ViewSetUp {

        TextView text_title;
        TextView text_sub_title;

        public ViewSetUp(View view) {
            text_title = (TextView)view.findViewById(R.id.text_title);
            text_sub_title = (TextView)view.findViewById(R.id.text_sub_title);
        }
    }
}
