package com.uw.alice.ui.navigationD.joke;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.uw.alice.R;

public class GraphicJokesActivity extends AppCompatActivity {

    private static final String TAG = "NotificationsFragment";
    private Context mContext;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "生命周期点位：  GraphicJokesActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_jokes);
        mContext = GraphicJokesActivity.this;

        getWindow().setStatusBarColor(getColor(R.color.colorNavigationC)); //设置系统状态栏颜色

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setElevation(5);
        viewPager = findViewById(R.id.view_pager2);
        JokesTypePagerAdapter pagerAdapter = new JokesTypePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> finish());

        final LinearLayout llMore = findViewById(R.id.ll_more);
        llMore.setOnClickListener(v -> Toast.makeText(mContext, "有点懒，不想写这块代码", Toast.LENGTH_SHORT).show());

        initTab();


    }


    private void initTab() {

       new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
           switch (position) {
               case 0:
                   tab.setText(R.string.activity_graphic_joke_tab_label_1);
                   break;
               case 1:
                   tab.setText(R.string.activity_graphic_joke_tab_label_2);
                   break;
               case 2:
                   tab.setText(R.string.activity_graphic_joke_tab_label_3);
                   break;
           }
       }).attach();

    }



}
