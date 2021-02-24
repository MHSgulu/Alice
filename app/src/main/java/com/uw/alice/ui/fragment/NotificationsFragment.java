package com.uw.alice.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.uw.alice.R;

import java.util.ArrayList;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class NotificationsFragment extends Fragment {

    private static final String TAG = "NotificationsFragment";
    private Context mContext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "生命周期点位：  NotificationsFragment onCreate");
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  NotificationsFragment onCreateView");
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        mContext = getContext();
        requireActivity().getWindow().setStatusBarColor(requireActivity().getColor(R.color.colorNavigationC)); //设置系统状态栏颜色

        return root;
    }






}