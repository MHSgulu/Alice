package com.uw.alice.ui.modular.bing.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uw.alice.R;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.common.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.modular.bing.adapter.BingWallpaperListAdapter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BingWallpaperListActivity extends AppCompatActivity {

    private static final String TAG = "BingLatelyWallpaperActivity";
    private Context mContext;
    private RecyclerView recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_lately_wallpaper);
        mContext = BingWallpaperListActivity.this;

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(v -> finish());

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
                    recycleList.setAdapter(new BingWallpaperListAdapter(bingWallpaper.getImages()));
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
