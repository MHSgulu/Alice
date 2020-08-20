package com.uw.alice.ui.navigationD.joke;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uw.alice.ui.navigationD.joke.fragment.DynamicGifFragment;
import com.uw.alice.ui.navigationD.joke.fragment.PictureJokeFragment;
import com.uw.alice.ui.navigationD.joke.fragment.TextJokeFragment;

public class JokesTypePagerAdapter extends FragmentStateAdapter {

    JokesTypePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return DynamicGifFragment.newInstance();
        }else if (position == 1){
            return PictureJokeFragment.newInstance();
        }else {
            return TextJokeFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
