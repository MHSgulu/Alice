package com.uw.alice.ui.navigationD;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BingLatelyWallpaperActivity extends AppCompatActivity {

    private static final String TAG = "BingLatelyWallpaperActi";
    private Context mContext;
    private RecyclerView recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_lately_wallpaper);
        mContext = BingLatelyWallpaperActivity.this;

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycleList = findViewById(R.id.recycle_list);

        //请求官方必应壁纸数据
        Observer<BingWallpaper> bingWallpaperObserver = new Observer<BingWallpaper>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BingWallpaper bingWallpaper) {
                if (bingWallpaper.getImages()!= null){
                    recycleList.setLayoutManager(new LinearLayoutManager(mContext));
                    recycleList.setAdapter(new BingLatelyWallpaperAdapter(bingWallpaper.getImages()));
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
        SingletonRetrofit.getInstance().queryOfficialBingWallpaper(bingWallpaperObserver,"js","8");






    }

}
