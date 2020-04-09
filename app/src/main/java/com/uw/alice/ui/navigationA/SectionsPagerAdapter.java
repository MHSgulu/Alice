package com.uw.alice.ui.navigationA;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.uw.alice.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 * 一个[FragmentPagerAdapter]返回一个对应于某个节/选项卡/页的片段
 * 对于较大的页面集，请考虑{@link FragmentStatePagerAdapter}.
 */
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "SectionsPagerAdapter";
    private final Context mContext;
    private static final int[] TAB_TITLES = new int[]
            {R.string.tab_title_1,R.string.tab_title_2,R.string.tab_title_3,R.string.tab_title_4,R.string.tab_title_5,R.string.tab_title_6,
                    R.string.tab_title_7,R.string.tab_title_8,R.string.tab_title_9,R.string.tab_title_10,R.string.tab_title_11,R.string.tab_title_12,
                    R.string.tab_title_13,R.string.tab_title_14,R.string.tab_title_15,R.string.tab_title_16,R.string.tab_title_17};

    public SectionsPagerAdapter(Context context, @NonNull FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    //Return the Fragment associated with a specified position.
    // 返回与指定位置关联的片段。
    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        //调用getItem来实例化给定页的片段。
        // Return a PlaceholderFragment (defined as a static inner class below).
        //返回一个占位符片段（定义为下面的静态内部类）。
        return PlaceholderFragment.newInstance(position + 1);
    }


    @Override
    public CharSequence getPageTitle(int positon){
        return mContext.getResources().getString(TAB_TITLES[positon]);
    }



    // Return the number of views available.
    // 返回可用的视图数。
    @Override
    public int getCount() {
        // Show  total pages.
        //Log.d(TAG, "getCount:"+TAB_TITLES.length);
        //return 2;
        return TAB_TITLES.length;

    }
}
