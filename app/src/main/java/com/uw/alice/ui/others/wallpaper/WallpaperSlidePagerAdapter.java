package com.uw.alice.ui.others.wallpaper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class WallpaperSlidePagerAdapter extends FragmentStatePagerAdapter {


    WallpaperSlidePagerAdapter(@NonNull FragmentManager fm, int behavior) {
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
