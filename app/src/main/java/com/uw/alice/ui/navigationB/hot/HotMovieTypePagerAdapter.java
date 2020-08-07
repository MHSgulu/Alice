package com.uw.alice.ui.navigationB.hot;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HotMovieTypePagerAdapter extends FragmentStateAdapter {

    HotMovieTypePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return HotMovieFragment.newInstance(1);
        }else {
            return HotMovieFragment.newInstance(2);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
