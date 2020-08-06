package com.uw.alice.ui.navigationD.wallpaper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.BingWallpaper;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import cc.shinichi.library.ImagePreview;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class WallpaperSlideFragment extends Fragment {

    private static final String TAG = "WallpaperSlideFragment";

    private Context mContext;
    private TextView tvCopyright;
    private ImageView ivWallpaper;

    private int position;

    public static WallpaperSlideFragment newInstance(int index) {
        Bundle args = new Bundle();
        WallpaperSlideFragment fragment = new WallpaperSlideFragment();
        args.putInt(Util.ARG_Position,index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_wallpaper_slide, container, false);
        mContext = getContext();
        tvCopyright = rootView.findViewById(R.id.tv_copyright);
        ivWallpaper = rootView.findViewById(R.id.iv_wallpaper);
        if (getArguments() != null){
            position = getArguments().getInt(Util.ARG_Position,0);
        }

        //请求必应壁纸数据
        initRequestData();

        return rootView;
    }


    private void initRequestData() {

        Observer<BingWallpaper> bingWallpaperObserver = new Observer<BingWallpaper>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final BingWallpaper bingWallpaper) {
                if (bingWallpaper.getImages()!= null){
                    tvCopyright.setText(bingWallpaper.getImages().get(position).getCopyright());
                    Glide.with(mContext).load(Util.BING_API_URL + bingWallpaper.getImages().get(position).getUrl()).into(ivWallpaper);

                    ivWallpaper.setOnClickListener(v -> ImagePreview.getInstance()
                            .setContext(mContext)
                            .setIndex(0)
                            .setImage(Util.BING_API_URL + bingWallpaper.getImages().get(position).getUrl()).start());

                    tvCopyright.setOnClickListener(v -> Function.openWebPage(mContext,bingWallpaper.getImages().get(position).getCopyrightlink()));

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
