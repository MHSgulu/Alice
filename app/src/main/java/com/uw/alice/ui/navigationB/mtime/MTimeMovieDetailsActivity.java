package com.uw.alice.ui.navigationB.mtime;


import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.appbar.AppBarLayout;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.MTimeMovieDetail;
import com.uw.alice.data.util.Util;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.uw.alice.ui.navigationB.mtime.adapter.MTimeMovieCastAdapter;
import com.uw.alice.ui.navigationB.mtime.adapter.MTimeMovieLabelAdapter;
import com.uw.alice.ui.navigationB.mtime.adapter.MTimeMovieStillAdapter;
import com.uw.alice.ui.share.ShareDialogFragment;
import com.willy.ratingbar.BaseRatingBar;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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
    private MTimeMovieCastAdapter mTimeMovieCastAdapter;

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
            movieId = getIntent().getIntExtra(Util.ARG_MovieId, 10734);
            Glide.with(mContext).load(getIntent().getStringExtra(Util.ARG_MoviePoster)).into(new CustomTarget<Drawable>() {
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
            //获取电影条目信息
            fetchMovieData();
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


    /**
     * 请求数据
     */
    private void fetchMovieData() {
        Observer<MTimeMovieDetail> mTimeMovieDetailObserver = new Observer<MTimeMovieDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MTimeMovieDetail t) {
                if (t.getCode().equals("1") || t.getMsg().equals("成功")) {
                    movieTitle = t.getData().getBasic().getName();
                    tvMovieTitle.setText(movieTitle);

                    if (TextUtils.equals(t.getData().getBasic().getName(), t.getData().getBasic().getNameEn())) {
                        tvMovieOriginalTitle.setText(String.format("(%s)", t.getData().getBasic().getYear()));
                    } else {
                        tvMovieOriginalTitle.setText(t.getData().getBasic().getNameEn());
                    }


                    genres = String.join(" / ", t.getData().getBasic().getType());
                    mainlandPubDate = t.getData().getBasic().getReleaseDate();
                    StringBuilder sb = new StringBuilder(mainlandPubDate);
                    sb.insert(4,"-");
                    sb.insert(7,"-");

                    durations = "";
                    if (!t.getData().getBasic().getMins().isEmpty()) {
                        durations = t.getData().getBasic().getMins();
                    } else {
                        durations = "未知";
                    }

                    tvMovieInformation.setText(String.format("%s / 上映时间: %s(中国大陆) / 片长: %s", genres, sb, durations));

                    tvMovieScore.setText(String.valueOf(t.getData().getBasic().getOverallRating()));
                    baseRatingBar.setRating((float) t.getData().getBasic().getOverallRating() / 2);
                    tvMovieScoreCount.setText(String.format("%s人评分", t.getData().getBasic().getRatingCountShow()));
                    tvMovieWatchCount.setText(String.format("%s人看过", t.getData().getBasic().getHasSeenCountShow()));
                    tvMovieWishCount.setText(String.format("%s人想看", t.getData().getBasic().getWantToSeeCountShow()));
                    //tvMovieShortCommentCount.setText(String.valueOf(movieDetails.getComments_count()));
                }


                if (!t.getData().getBasic().getType().isEmpty()) {
                    labelRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
                    labelRecyclerView.setAdapter(new MTimeMovieLabelAdapter(t.getData().getBasic().getType()));
                }

                tvMovieIntroduction.setText(t.getData().getBasic().getStory());
                //一般情况下等不到TextView绘制成功，所以有空指针错误,判断方法放在子线程执行
                tvMovieIntroduction.post(() -> {
                    //若果超出给定的行数限制，返回要省去的字符数。大于0代表超出文本内容限制
                    int num = tvMovieIntroduction.getLayout().getEllipsisCount(tvMovieIntroduction.getLineCount() - 1);
                    //Log.d(TAG,"num:  "+num);
                    if (num > 0) {
                        runOnUiThread(() -> llOpen.setVisibility(View.VISIBLE));
                    }
                });


                //获取演职员数据
                if (t.getData().getBasic().getDirector() != null && ! t.getData().getBasic().getActors().isEmpty()){
                    castRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    castRecyclerView.setAdapter(new MTimeMovieCastAdapter(t.getData().getBasic().getDirector(), t.getData().getBasic().getActors()));
                }


                //获取预告片剧照栏数据
                if (!t.getData().getBasic().getStageImg().getList().isEmpty()){
                    stillRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    stillRecyclerView.setAdapter(new MTimeMovieStillAdapter(t.getData().getBasic().getVideo(), t.getData().getBasic().getStageImg().getList()));
                }

                //获取随机4条热门短评
                /*if (!movieDetails.getPopular_comments().isEmpty()){
                    commentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    commentRecyclerView.setAdapter(new MovieShortCommentAdapter(movieDetails.getPopular_comments()));
                }*/


                /**
                 * 注册此视图的滚动X或Y位置更改时要调用的回调。
                 * 侦听器在滚动X或Y位置更改时通知
                 */
                /*nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        //X 水平方向  Y垂直方向
                        Log.i(TAG, "点位： scrollY: " + scrollY);
                        Log.i(TAG, "点位： oldScrollY: " + oldScrollY);

                        if (scrollY == 0){
                            tvTitle.setVisibility(View.VISIBLE);
                            //tvTitle.setText("电影");
                        }else if (scrollY == 250){
                            tvTitle.setVisibility(View.GONE);
                            //tvTitle.setText(movieTitle);
                        }

                    }
                });*/


            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "onError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().fetchMTimeMovieDetail(mTimeMovieDetailObserver, Util.LocationId, movieId);
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
