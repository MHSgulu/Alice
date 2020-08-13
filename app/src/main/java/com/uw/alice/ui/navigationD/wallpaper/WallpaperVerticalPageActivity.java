package com.uw.alice.ui.navigationD.wallpaper;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uw.alice.R;
import com.uw.alice.assist.viewpager2.DepthPageTransformer;
import com.uw.alice.assist.viewpager2.ZoomOutPageTransformer;


public class WallpaperVerticalPageActivity extends AppCompatActivity {

    private static final String TAG = "WallpaperVerticalPageActivity";
    private Context mContext;

    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_vertical);
        mContext = WallpaperVerticalPageActivity.this;

        viewPager = findViewById(R.id.view_pager2);
        pagerAdapter = new VerticalSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);


        //选择动画效果
        initSelectStyle();

    }

    private void initSelectStyle() {
        CharSequence[] items = {"默认动画","缩小页面","深度页面"};
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(mContext);
        builder.setTitle("请选择滑动动画效果");
        builder.setIcon(R.mipmap.icon_snowflake); //设置要在标题中使用的资源ID。
        builder.setItems(items, (dialog, which) -> {
            switch (which) {
                case 0:
                    break;

                case 1:
                    viewPager.setPageTransformer(new ZoomOutPageTransformer());
                    break;

                case 2:
                    viewPager.setPageTransformer(new DepthPageTransformer());
                    break;
            }
        });
        builder.create().show();
    }


}