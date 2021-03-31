package com.uw.alice.ui.others.wallpaper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VerticalSlidePagerAdapter extends FragmentStateAdapter {


    VerticalSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return WallpaperSlideFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 8;
    }


}
