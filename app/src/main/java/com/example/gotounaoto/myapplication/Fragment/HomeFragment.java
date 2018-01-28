package com.example.gotounaoto.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gotounaoto.myapplication.DialogFragment.CustomDialogCheckFragment;
import com.example.gotounaoto.myapplication.R;

public class HomeFragment extends Fragment implements View.OnClickListener{

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
    public void onClick(View view){
        if(view != null){
            switch (view.getId()){
                case R.id.check_button:
                    CustomDialogCheckFragment dialogCheckFragment = new CustomDialogCheckFragment();
                    dialogCheckFragment.show(getFragmentManager(),"check");
                break;

            }
        }

    }

    public void settingListener(){
        //listenerの設定
        view.findViewById(R.id.check_button).setOnClickListener(this);
    }

    public void settingPager(){
        //pagerの設定
        ViewPager viewPager = (ViewPager)view.findViewById(R.id.container);
        PieChartPagerAdapter pieChartPagerAdapter = new PieChartPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pieChartPagerAdapter);
    }


    public static class PieChartFragment extends Fragment {

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
            View rootView = inflater.inflate(R.layout.fragement_piechart, container, false);
            switch (getArguments().getInt(ARG_SECTION_NUMBER)){

            }
            return rootView;
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
            // Show 3 total pages.
            return 6;
        }
    }
}
