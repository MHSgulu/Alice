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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.uw.alice.R;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.model.Wallpaper;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class BingDailyWallpaperActivity extends AppCompatActivity {

    private static final String TAG = "BingDailyWallpaperActiv";
    private Context mContext;
    private String bingWallpaperUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_daily_wallpaper);
        mContext = BingDailyWallpaperActivity.this;

        final LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final LinearLayout llMore = findViewById(R.id.ll_more);
        llMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, BingLatelyWallpaperActivity.class));
            }
        });

        final TextView tvCopyright = findViewById(R.id.tv_copyright);
        final ImageView ivWallpaper = findViewById(R.id.iv_wallpaper);


        //请求官方必应壁纸数据
        Observer<BingWallpaper> bingWallpaperObserver = new Observer<BingWallpaper>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BingWallpaper bingWallpaper) {
                if (bingWallpaper.getImages()!= null){
                    bingWallpaperUrl= Util.BING_API_URL + bingWallpaper.getImages().get(0).getUrl();
                    tvCopyright.setText(bingWallpaper.getImages().get(0).getCopyright());
                    Glide.with(mContext).load(bingWallpaperUrl).into(ivWallpaper);

                    ivWallpaper.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImagePreview.getInstance()
                                    .setContext(mContext)
                                    .setIndex(0)
                                    .setImage(bingWallpaperUrl).start();
                        }
                    });
                    
                    tvCopyright.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //如需打开网页，请使用 ACTION_VIEW 操作，并在 Intent 数据中指定网址。
                            Uri webpage = Uri.parse(bingWallpaper.getImages().get(0).getCopyrightlink());
                            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    });

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
        SingletonRetrofit.getInstance().queryOfficialBingWallpaper(bingWallpaperObserver,"js","1");






    }




}
