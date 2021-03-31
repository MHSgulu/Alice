package com.uw.alice.ui.modular.bing.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.uw.alice.ui.modular.bing.fragment.WallpaperSlideFragment;

public class WallpaperSlidePagerAdapter extends FragmentStatePagerAdapter {


    public WallpaperSlidePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return WallpaperSlideFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 8;
    }


}
