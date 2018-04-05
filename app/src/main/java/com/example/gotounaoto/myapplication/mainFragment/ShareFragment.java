package com.example.gotounaoto.myapplication.mainFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gotounaoto.myapplication.R;
import com.example.gotounaoto.myapplication.shareFragment.AboutMeFragment;
import com.example.gotounaoto.myapplication.shareFragment.DownloadFragment;

import java.util.ArrayList;
import java.util.List;

public class ShareFragment extends Fragment {

    View view;
    ViewPager viewPager;

    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        settingThisView(inflater, container);
        settingViewPagerAndAdapter();
        settingTabLayout();
        return view;
    }

    public void settingThisView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_share, container, false);
    }

    public void settingViewPagerAndAdapter() {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), getActivity());
        viewPagerAdapter.addFragment(AboutMeFragment.newInstance());
        viewPagerAdapter.addFragment(DownloadFragment.newInstance());
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void settingTabLayout() {
        String[] nav_label = {"ユーザーの活動", "ダウンロード"};
        int[] nav_icon = {R.drawable.ic_person_black_24dp, R.drawable.ic_arrow_downward_black_24dp};
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            LinearLayout tab = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.nav_tab, null);
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);
            tab_label.setText(nav_label[i]);
            tab_icon.setImageResource(nav_icon[i]);
            tabLayout.getTabAt(i).setCustomView(tab);
        }
    }

    public static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> fragments = new ArrayList<>();
        Context context;

        public ViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
    }

}
