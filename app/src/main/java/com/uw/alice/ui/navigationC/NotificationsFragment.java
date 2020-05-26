package com.uw.alice.ui.navigationC;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.util.Util;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private Context mContext;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<String> tab_title_list = new ArrayList<>();//存放标签页标题
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        //设置系统状态栏颜色
        Function.setSystemStatusBarColor(requireActivity(),R.color.colorNavigationC);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        tabLayout = root.findViewById(R.id.tabs);
        tabLayout.setElevation(10);
        viewPager = root.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);



        final LinearLayout llMore = root.findViewById(R.id.ll_more);
        llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "有点懒，不想写这块代码", Toast.LENGTH_SHORT).show();
            }
        });


        //初始化Tablayout+ViewPage+Fragment
        initTab();

        return root;
    }


    private void initTab() {

        tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_c_tab_title_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_c_tab_title_2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_c_tab_title_3));

        fragment_list.add(DynamicGifFragment.newInstance());
        fragment_list.add(PictureJokeFragment.newInstance());
        fragment_list.add(TextJokeFragment.newInstance());

        NCSectionsPagerAdapter adapter = new NCSectionsPagerAdapter(getChildFragmentManager(), mContext, fragment_list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }



}