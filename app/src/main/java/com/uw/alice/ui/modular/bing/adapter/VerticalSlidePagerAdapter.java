package com.uw.alice.ui.modular.bing.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uw.alice.ui.modular.bing.fragment.WallpaperSlideFragment;

public class VerticalSlidePagerAdapter extends FragmentStateAdapter {


    public VerticalSlidePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
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
