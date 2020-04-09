package com.uw.alice.ui.navigationB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.uw.alice.R;

import java.util.ArrayList;

public class NBSectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private ArrayList<String> tab_title_list;//存放标签页标题
    private ArrayList<Fragment> fragment_list;//存放ViewPager下的Fragment

    private static final int[] TAB_TITLES = new int[]{R.string.navigation_b_tab_title_1/*,R.string.navigation_b_tab_title_2,R.string.navigation_b_tab_title_3 */};

    public NBSectionsPagerAdapter(@NonNull FragmentManager fm,/* ArrayList<String> tab_title_list, */Context context, ArrayList<Fragment> fragment_list) {
        super(fm);
        /*this.tab_title_list = tab_title_list;*/
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
