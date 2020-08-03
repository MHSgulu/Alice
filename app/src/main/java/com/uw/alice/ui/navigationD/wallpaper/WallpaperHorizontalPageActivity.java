package com.uw.alice.ui.navigationD.wallpaper;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.uw.alice.R;
import com.uw.alice.assist.viewpager.DepthPageTransformer;
import com.uw.alice.assist.viewpager.ZoomOutPageTransformer;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;


public class WallpaperHorizontalPageActivity extends AppCompatActivity {

    private static final String TAG = "BingDailyWallpaperActivity";
    private Context mContext;

    private ViewPager mPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_daily_wallpaper);
        mContext = WallpaperHorizontalPageActivity.this;

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> finish());

       /* final LinearLayout llMore = findViewById(R.id.ll_more);
        llMore.setOnClickListener(v -> showMenu(llMore));*/

        mPager = findViewById(R.id.view_pager);
        pagerAdapter = new WallpaperSlidePagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mPager.setAdapter(pagerAdapter);

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
                    mPager.setPageTransformer(true, new ZoomOutPageTransformer());
                    break;

                case 2:
                    mPager.setPageTransformer(true, new DepthPageTransformer());
                    break;
            }
        });
        builder.create().show();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.slide_style_menu, menu);
        return true;
    }


    public void showMenu(View anchor) {
        PopupMenu popup = new PopupMenu(this, anchor);
        popup.getMenuInflater().inflate(R.menu.slide_style_menu, popup.getMenu());
        popup.show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.slide_style_1:
                Toast.makeText(mContext, "1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.slide_style_2:
                Toast.makeText(mContext, "2", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


}
