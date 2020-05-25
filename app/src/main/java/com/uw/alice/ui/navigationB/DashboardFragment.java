package com.uw.alice.ui.navigationB;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
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
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class DashboardFragment extends Fragment {

    private static final String TAG = "DashboardFragment";
    private DashboardViewModel dashboardViewModel;
    private Context mContext;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        //设置系统状态栏颜色
        Util.setSystemStatusBarColor(requireActivity(),R.color.colorNavigationB);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        tabLayout = root.findViewById(R.id.tabs);
        tabLayout.setElevation(10);
        viewPager = root.findViewById(R.id.view_pager);

        final LinearLayout llSearchBox = root.findViewById(R.id.ll_search_box);
        llSearchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "电影搜索", Toast.LENGTH_SHORT).show();
            }
        });
        final LinearLayout llBoxOffice = root.findViewById(R.id.ll_box_office);
        llBoxOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "实时票房", Toast.LENGTH_SHORT).show();
            }
        });


        //查询豆瓣电影Top250
        //queryMovieTop250();

        //初始化Tablayout
        initTab();


        return root;
    }

    private void initTab() {

        tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_b_tab_title_1));
        //tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_b_tab_title_2));
        //tabLayout.addTab(tabLayout.newTab().setText(R.string.navigation_b_tab_title_3));

        fragment_list.add(MovieFragment.newInstance());
        //fragment_list.add(BookFragment.newInstance());
        //fragment_list.add(BookFragment.newInstance());

        NBSectionsPagerAdapter adapter  = new NBSectionsPagerAdapter(getChildFragmentManager(),mContext,fragment_list);
        viewPager.setAdapter(adapter);
        //viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void queryMovieTop250() {

        io.reactivex.Observer<Movie> movieObserver = new io.reactivex.Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Movie movie) {
                Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "movie:"+movie.getSubjects().get(0).getTitle());
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:"+e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().queryTop250Movie(movieObserver, Util.DOUBAN_APIKEY,0,1);


    }




}