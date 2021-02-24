package com.uw.alice.ui.navigationB.mtime;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.common.Constant;
import com.uw.alice.ui.fragment.ShareDialogFragment;
import com.willy.ratingbar.BaseRatingBar;


public class MTimeMovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MovieDetailsActivity";
    private Context mContext;
    private CoordinatorLayout rootView;
    private AppBarLayout appBarLayout;
    private NestedScrollView nestedScrollView;
    private ImageView ivMoviePoster;
    private TextView tvTitle,tvMovieTitle, tvMovieOriginalTitle, tvMovieInformation, tvMovieScore,
            tvMovieScoreCount, tvMovieWatchCount, tvMovieWishCount, tvMovieIntroduction, tvMovieShortCommentCount;
    private BaseRatingBar baseRatingBar;
    private LinearLayout llOpen;
    private RelativeLayout rlMovieStill;
    private RecyclerView labelRecyclerView, castRecyclerView, stillRecyclerView, commentRecyclerView;

    private int ThemeColor;
    private String genres, mainlandPubDate, durations;
    private int movieId; //电影ID
    private String movieTitle; //电影名称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_movie_details);
        mContext = MTimeMovieDetailsActivity.this;

        initBingViewId();
        
        if (getIntent() != null) {
           /* String id = getIntent().getStringExtra("MovieId");
            Log.d(TAG, "测试参数 收id: "+ id);*/
            movieId = getIntent().getIntExtra(Constant.ARG_MovieId, 10734);
            Glide.with(mContext).load(getIntent().getStringExtra(Constant.ARG_MoviePoster)).into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    //设置图片电影海报
                    ivMoviePoster.setImageDrawable(resource);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                    //获取图片主色调
                    ThemeColor = Function.fetchPaletteColor(bitmapDrawable.getBitmap());
                    //设置状态栏颜色
                    getWindow().setStatusBarColor(ThemeColor);
                    //设置页面背景色
                    rootView.setBackgroundColor(ThemeColor);
                    //设置AppBar背景色
                    appBarLayout.setBackgroundColor(ThemeColor);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });
        }
    }


    /**
     * 绑定视图ID
     */
    private void initBingViewId() {
        LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(this);
        llOpen = findViewById(R.id.ll_open);
        llOpen.setOnClickListener(this);
        rlMovieStill = findViewById(R.id.rl_movie_still);
        rlMovieStill.setOnClickListener(this);
        rootView = findViewById(R.id.rootView);
        appBarLayout = findViewById(R.id.appbar);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        tvTitle = findViewById(R.id.tv_title);
        labelRecyclerView = findViewById(R.id.list_label);
        castRecyclerView = findViewById(R.id.list_cast);
        stillRecyclerView = findViewById(R.id.list_still);
        commentRecyclerView = findViewById(R.id.list_short_comment);
        ivMoviePoster = findViewById(R.id.iv_movie_poster);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieOriginalTitle = findViewById(R.id.tv_movie_original_title);
        tvMovieInformation = findViewById(R.id.tv_movie_information);
        tvMovieScore = findViewById(R.id.tv_movie_score);
        tvMovieScoreCount = findViewById(R.id.tv_score_count);
        tvMovieWatchCount = findViewById(R.id.tv_watch_count);
        tvMovieWishCount = findViewById(R.id.tv_wish_count);
        tvMovieIntroduction = findViewById(R.id.tv_introduction);
        tvMovieShortCommentCount = findViewById(R.id.tv_short_comment_count);
        baseRatingBar = findViewById(R.id.baseRatingBar);

        LinearLayout llShare = findViewById(R.id.ll_share);
        llShare.setOnClickListener(this);
        
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.ll_back: //返回
                finish();
                break;

            case R.id.ll_share: //分享
                //Toast.makeText(mContext, "分享", Toast.LENGTH_SHORT).show();
                ShareDialogFragment.newInstance().show(getSupportFragmentManager(),"ShareMovie");
                break;

            case R.id.ll_open: //
                tvMovieIntroduction.setMaxLines(20);
                llOpen.setVisibility(View.GONE);
                break;

            case R.id.rl_movie_still: //返回
                Toast.makeText(mContext, "全部剧照", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
