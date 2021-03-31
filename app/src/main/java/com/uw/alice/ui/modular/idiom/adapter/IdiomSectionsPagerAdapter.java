package com.uw.alice.ui.modular.idiom.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.uw.alice.R;

import java.util.ArrayList;

public class IdiomSectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private ArrayList<Fragment> fragment_list;//存放ViewPager下的Fragment
    private static final int[] TAB_TITLES = new int[]{R.string.navigation_d_idiom_tab_title_1,R.string.navigation_d_idiom_tab_title_2};

    public IdiomSectionsPagerAdapter(@NonNull FragmentManager fm, Context context, ArrayList<Fragment> fragment_list) {
        super(fm);
        mContext = context;
        this.fragment_list = fragment_list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }


}
