package com.uw.alice.ui.navigationB.douban.hot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uw.alice.R;
import com.uw.alice.common.Constant;


public class HotMovieListActivity extends AppCompatActivity {

    private static final String TAG = "HotMovieListActivity";
    private Context mContext;
    private LinearLayout llBack;
    private TabLayout tabs;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    private int type;

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
        if (getIntent() != null){
            type = getIntent().getIntExtra(Constant.ARG_MovieType,0);
            //Log.i(TAG, "数据点位： type: " + type);
        }
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
        pagerAdapter = new HotMovieTypePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

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
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
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
        /**
         * 将TabLayout和ViewPager2链接在一起。 ViewPager2设置了适配器后必须调用。 在TabLayoutMediator的新实例上调用，或者在ViewPager2的适配器更改时调用。
         */
        mediator.attach();

        if (type == 2){
            /**
             * 设置当前选定的页面。
             * 如果ViewPager已经使用当前适配器完成了它的第一个布局，那么当前项和指定项之间将有一个平滑的动画过渡。
             * 如果适配器未设置或为空，则自动忽略。将项夹到适配器的边界。.
             *
             * @param item 要选择的项目索引
             * @param smoothScroll 如果为True，则平滑滚动到新项目，如果为false，则立即转换
             */
            viewPager.setCurrentItem(2,true);
        }



    }

    /**
     * 监听点击事件
     */
    private void initOnClickListener() {
        llBack.setOnClickListener(v -> finish());
    }



}
