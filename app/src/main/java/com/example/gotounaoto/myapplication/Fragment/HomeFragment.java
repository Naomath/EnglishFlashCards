package com.example.gotounaoto.myapplication.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogCheckFragment;
import com.example.gotounaoto.myapplication.ExtendSugar.Word;
import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.classes.GettingPieData;
import com.example.gotounaoto.myapplication.classes.MakeString;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Setter;

public class HomeFragment extends Fragment implements View.OnClickListener {

    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        settingListener();
        settingPager();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            switch (view.getId()) {
                case R.id.check_button:
                    CustomDialogCheckFragment dialogCheckFragment = new CustomDialogCheckFragment();
                    dialogCheckFragment.show(getFragmentManager(), "check");
                    break;

            }
        }

    }

    public void settingListener() {
        //listenerの設定
        view.findViewById(R.id.check_button).setOnClickListener(this);
    }

    public void settingPager() {
        //pagerの設定
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.container);
        PieChartPagerAdapter pieChartPagerAdapter = new PieChartPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pieChartPagerAdapter);
    }


    public static class PieChartFragment extends Fragment {

        //viewpagerで切り替えするところのfragment
        View rootView;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PieChartFragment() {
        }

        public static PieChartFragment newInstance(int sectionNumber) {
            PieChartFragment fragment = new PieChartFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragement_piechart, container, false);
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                //1から始まる
                case 1:
                    settingPieChart1();
                    break;
            }
            return rootView;
        }

        public void settingPieChart1() {
            //正答率を分けるグラフ
            //最初はtextviewの設定
            TextView text_explain = (TextView) rootView.findViewById(R.id.text_explain);
            text_explain.setText(R.string.textview_explain_chart1);
            //piechartの設定
            PieChart pieChart = (PieChart) rootView.findViewById(R.id.pie_chart);
            List<Word> all_words = Word.listAll(Word.class);
            List<String> messages = Arrays.asList(String.valueOf(all_words.size()), "問");
            pieChart.setCenterText(MakeString.makeString(messages));
            pieChart.setCenterTextSize(20f);
            PieData pieData = GettingPieData.gettingPieData1();
            pieChart.setData(pieData);
        }

    }

    public class PieChartPagerAdapter extends FragmentPagerAdapter {

        public PieChartPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PieChartFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
