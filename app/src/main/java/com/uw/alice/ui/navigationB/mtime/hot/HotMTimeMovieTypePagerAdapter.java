package com.uw.alice.ui.navigationB.mtime.hot;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uw.alice.ui.navigationB.douban.hot.HotMovieFragment;

public class HotMTimeMovieTypePagerAdapter extends FragmentStateAdapter {

    public HotMTimeMovieTypePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return HotMTimeMovieFragment.newInstance(1);
        }else {
            return HotMTimeMovieFragment.newInstance(2);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
