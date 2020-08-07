package com.uw.alice.ui.navigationB.hot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uw.alice.R;

import static com.google.android.material.tabs.TabLayout.*;

public class HotMovieListActivity extends AppCompatActivity {

    private static final String TAG = "HotMovieListActivity";
    private Context mContext;
    private LinearLayout llBack;
    private TabLayout tabs;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_movie_list);

        initView();
        initBindViewId();
        initData();
        initOnClickListener();

    }


    /**
     * 初始化界面
     */
    private void initView() {
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //设置系统状态黑色字体
    }


    /**
     * 绑定视图
     */
    public void initBindViewId() {
        llBack = findViewById(R.id.ll_back);
        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager2);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mContext = HotMovieListActivity.this;
        /*Tab tab1 = tabs.newTab().setText("影院热映");
        Tab tab2 = tabs.newTab().setText("即将上映");
        tabs.addTab(tab1);
        tabs.addTab(tab2);*/
        pagerAdapter = new HotMovieTypePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        //viewPager.setOverScrollMode(OVER_SCROLL_NEVER);

        /**
         * 将TabLayout与ViewPager2链接的介体。
         * 当一个选项卡被选中时，介体会将ViewPager2的位置与所选选项卡同步，而当用户拖动ViewPager2时，该控件将使TabLayout的滚动位置同步。
         * 当ViewPager2移动时，TabLayoutMediator将收听ViewPager2的OnPageChangeCallback来调整选项卡。
         * 当选项卡移动时，TabLayoutMediator会侦听TabLayout的OnTabSelectedListener来调整VP2。
         * 当数据集更改时，TabLayoutMediator会侦听RecyclerView的AdapterDataObserver来重新创建选项卡内容。
         *
         * 通过创建此类的实例来建立链接，确保ViewPager2具有适配器，然后对其进行调用attach()。
         * 实例化TabLayoutMediator只会创建介体对象，attach()将TabLayout和ViewPager2链接在一起。
         * 创建此类的实例时，必须提供一个实现TabLayoutMediator.TabConfigurationStrategy，您可以在其中设置选项卡的文本和/或对所需的选项卡进行任何样式设置。
         * 更改ViewPager2的适配器将需要detach()后跟 attach()调用。更改ViewPager2或TabLayout将需要新实例化TabLayoutMediator。
         */
        TabLayoutMediator mediator = new TabLayoutMediator(tabs, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("影院热映");
                        //Log.i(TAG, "执行点位:  tab 1");
                        break;
                    case 1:
                        tab.setText("即将上映");
                        //Log.i(TAG, "执行点位:  tab 2");
                        break;
                }
            }
        });
        mediator.attach();


    }

    /**
     * 监听点击事件
     */
    private void initOnClickListener() {
        llBack.setOnClickListener(v -> finish());
    }



}
