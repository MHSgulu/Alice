package com.uw.alice.ui.navigationA;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.uw.alice.data.model.NewsChannel;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.FragmentHomeBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private HomeViewModel homeViewModel;
    private  Context mContext;

    private ArrayList<String> tab_title_list = new ArrayList<>();//存放标签页标题
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment

    private SectionsNewsPagerAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = root.getContext();
        //在此处以下三种上下文是一致的内容   com.uw.alice.MainActivity@12aefd4
        //Log.d(TAG, "Context1:"+mContext+"Context2:"+getActivity()+"Context3:"+getContext());
        viewPager = root.findViewById(R.id.view_pager);
        tabLayout = root.findViewById(R.id.tabs);
        //设置浮动高度
        tabLayout.setElevation(10);

        /*（默认导航会拦截返回按钮，回到首页，再次点击返回，才进入后台）
         将此方法放在onCreateView周期里， 底部导航栏点击系统返回不在截下返回事件，会直接进入后台。
         */
        //初始化Tab
        initTab();

        LinearLayout llSearchBox = root.findViewById(R.id.ll_search_box);
        llSearchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,SearchNewsActivity.class));
            }
        });
        LinearLayout llHotdotRank = root.findViewById(R.id.ll_hotdotRank);
        llHotdotRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,RealTimeHotSpotRankingActivity.class));
            }
        });

        //设置Tablayout与ViewPage
       // SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(mContext,getChildFragmentManager());
        //有多少页将在空闲状态下保留在屏幕外。默认值为1   0不起作用
        // viewPager.setOffscreenPageLimit(0);
        return root;
    }

    private void initTab() {

        io.reactivex.Observer<NewsChannel> observer = new io.reactivex.Observer<NewsChannel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsChannel newsChannel) {
                if (newsChannel.getCode().equals(Util.QUERY_SUCCESS_CODE)){
                    if (newsChannel.getResult().getResult() != null){
                        //tab_title_list.add(newsChannel.getResult().getResult());
                        tab_title_list = (ArrayList<String>) newsChannel.getResult().getResult();
                        //Log.d(TAG, "onNext:"+tab_title_list);
                        for (int i = 0;i<tab_title_list.size();i++){
                            tabLayout.addTab(tabLayout.newTab().setText(tab_title_list.get(i)));
                            //Log.d(TAG, "onNext:"+i);
                            fragment_list.add(PlaceholderFragment.newInstance(i+1));
                            adapter = new SectionsNewsPagerAdapter(getChildFragmentManager(),tab_title_list,fragment_list);
                            viewPager.setAdapter(adapter);
                            tabLayout.setupWithViewPager(viewPager);
                            //Log.d(TAG, "fragment_list:"+fragment_list);
                        }
                        //Log.d(TAG, "size:"+tab_title_list.size());
                    }
                }else if (newsChannel.getCode().equals(Util.ERROR_CODE_LIMIT)){
                    Toast.makeText(mContext, "新闻频道分类数据的调用次数超过每天限量1000次/天，请明天继续", Toast.LENGTH_SHORT).show();
                }

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
        SingletonRetrofit.getInstance().getNewsChannel(observer,Util.JDAPI_KEY);

    }


}