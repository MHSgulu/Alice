package com.uw.alice.ui.navigationB;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.uw.alice.R;
import com.uw.alice.common.Function;
import com.uw.alice.data.model.MovieDetails;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivityItemNewsDetailBinding;
import com.uw.alice.network.retrofit.SingletonRetrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cc.shinichi.library.tool.ui.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MovieDetailsActivity";
    private Context mContext;
    private ConstraintLayout rootView;
    private ImageView ivMoviePoster;
    private TextView tvMovieTitle,tvMovieOriginalTitle,tvMovieInformation,tvMovieScore,
            tvMovieScoreCount,tvMovieWatchCount,tvMovieWishCount,tvMovieIntroduction,tvMovieShortCommentCount;
    private LinearLayout layoutFiveStarts;
    private LinearLayout layoutFourStarts;
    private LinearLayout layoutThreeStarts;
    private LinearLayout layoutTwoStarts;
    private LinearLayout layoutOneStart;
    private LinearLayout layoutNoStart;
    private LinearLayout layoutFourAndHalfStarts;
    private LinearLayout layoutThreeAndHalfStarts;
    private LinearLayout layoutTwoAndHalfStarts;
    private LinearLayout layoutOneAndHalfStart;
    private ProgressBar progressBar1,progressBar2,progressBar3,progressBar4,progressBar5;


    private LinearLayout llOpen;
    private RelativeLayout rlMovieStill;

    private RecyclerView labelRecyclerView,castRecyclerView,stillRecyclerView,commentRecyclerView;

    private int ThemeColor;
    private final OkHttpClient client = new OkHttpClient();
    private String countries,genres,mainlandPubDate,durations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        mContext = MovieDetailsActivity.this;

        LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(this);

        llOpen = findViewById(R.id.ll_open);
        llOpen.setOnClickListener(this);
        rlMovieStill = findViewById(R.id.rl_movie_still);
        rlMovieStill.setOnClickListener(this);

        rootView = findViewById(R.id.rootView);

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



        //五星小部件
        layoutFiveStarts = findViewById(R.id.layout_five_starts);
        layoutFourStarts = findViewById(R.id.layout_four_starts);
        layoutThreeStarts = findViewById(R.id.layout_three_starts);
        layoutTwoStarts = findViewById(R.id.layout_two_starts);
        layoutOneStart = findViewById(R.id.layout_one_start);
        layoutNoStart = findViewById(R.id.layout_no_start);

        layoutFourAndHalfStarts = findViewById(R.id.layout_four_and_half_starts);
        layoutThreeAndHalfStarts = findViewById(R.id.layout_three_and_half_starts);
        layoutTwoAndHalfStarts = findViewById(R.id.layout_two_and_half_starts);
        layoutOneAndHalfStart = findViewById(R.id.layout_one_and_half_starts);


        //占比进度条
        progressBar1 = findViewById(R.id.progress_bar_1);
        progressBar2 = findViewById(R.id.progress_bar_2);
        progressBar3 = findViewById(R.id.progress_bar_3);
        progressBar4 = findViewById(R.id.progress_bar_4);
        progressBar5 = findViewById(R.id.progress_bar_5);



        if (getIntent()!=null){
           /* String id = getIntent().getStringExtra("MovieId");
            Log.d(TAG, "测试参数 收id: "+ id);*/

            Glide.with(mContext).load(getIntent().getStringExtra("MoviePoster")).into(new CustomTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    //设置图片电影海报
                    ivMoviePoster.setImageDrawable(resource);
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) resource;
                    //获取图片主色调
                    ThemeColor =  Function.fetchPaletteColor(bitmapDrawable.getBitmap());
                    //设置状态栏颜色
                    getWindow().setStatusBarColor(ThemeColor);
                    //设置页面背景色
                    rootView.setBackgroundColor(ThemeColor);
                }

                @Override
                public void onLoadCleared(@Nullable Drawable placeholder) {

                }
            });

            //获取电影条目信息
            fetchMovieData();

        }




    }





    private void fetchMovieData() {

        Observer<MovieDetails> movieDetailsObserver = new Observer<MovieDetails>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieDetails movieDetails) {
                //Toast.makeText(mContext, "S!", Toast.LENGTH_SHORT).show();
                tvMovieTitle.setText(movieDetails.getTitle());

                if (TextUtils.equals(movieDetails.getTitle(),movieDetails.getOriginal_title())){
                    tvMovieOriginalTitle.setText(String.format("(%s)", movieDetails.getYear()));
                }else {
                    tvMovieOriginalTitle.setText(movieDetails.getOriginal_title());
                }

                countries = String.join(" / ",movieDetails.getCountries());
                genres = String.join("  ",movieDetails.getGenres());
                mainlandPubDate = movieDetails.getMainland_pubdate();
                durations = "";
                if (!movieDetails.getDurations().isEmpty()){
                     durations = String.join(" / ",movieDetails.getDurations());
                }else{
                    durations = "未知";
                }
                tvMovieInformation.setText(String.format("%s / %s / 上映时间: %s(中国大陆) / 片长: %s", countries, genres, mainlandPubDate, durations));

                tvMovieScore.setText(String.valueOf(movieDetails.getRating().getAverage()));
                tvMovieScoreCount.setText(String.format("%s人评分", movieDetails.getRatings_count()));
                tvMovieWatchCount.setText(String.format("%s万人看过",String.valueOf(movieDetails.getCollect_count() / 10000.0).substring(0,3)));
                tvMovieWishCount.setText(String.format("%s万人想看",String.valueOf(movieDetails.getWish_count() / 10000.0).substring(0,3)));
                tvMovieShortCommentCount.setText(String.valueOf(movieDetails.getComments_count()));

                //五星小部件
                if (movieDetails.getRating().getAverage() >= 9.5 && movieDetails.getRating().getAverage() <= 10){
                     layoutFiveStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 8.5 && movieDetails.getRating().getAverage() <= 9.4){
                     layoutFourAndHalfStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 7.5 && movieDetails.getRating().getAverage() <= 8.4){
                     layoutFourStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 6.5 && movieDetails.getRating().getAverage() <= 7.4){
                     layoutThreeAndHalfStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 5.5 && movieDetails.getRating().getAverage() <= 6.4){
                     layoutThreeStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 4.5 && movieDetails.getRating().getAverage() <= 5.4){
                     layoutTwoAndHalfStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 3.5 && movieDetails.getRating().getAverage() <= 4.4){
                     layoutTwoStarts.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 2.5 && movieDetails.getRating().getAverage() <= 3.4){
                     layoutOneAndHalfStart.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 1.5 && movieDetails.getRating().getAverage() <= 2.4){
                     layoutOneStart.setVisibility(View.VISIBLE);
                }else if (movieDetails.getRating().getAverage() >= 0.5 && movieDetails.getRating().getAverage() <= 1.4){
                     layoutOneStart.setVisibility(View.VISIBLE);
                }else {
                     layoutNoStart.setVisibility(View.VISIBLE);
                }


                double totalCount = movieDetails.getRating().getDetails().get_$1() + movieDetails.getRating().getDetails().get_$2() +
                        movieDetails.getRating().getDetails().get_$3() + movieDetails.getRating().getDetails().get_$4() + movieDetails.getRating().getDetails().get_$5();
                //Log.d(TAG, "totalCount: "+ totalCount);
                //double rate5 =  (movieDetails.getRating().getDetails().get_$5() / totalCount);
                //Log.d(TAG, "rate5: "+ rate5);
                progressBar1.setProgress((int) ((movieDetails.getRating().getDetails().get_$5() / totalCount)*100)); //强转后自动保留小数点后两位
                progressBar2.setProgress((int) ((movieDetails.getRating().getDetails().get_$4() / totalCount)*100));
                progressBar3.setProgress((int) ((movieDetails.getRating().getDetails().get_$3() / totalCount)*100));
                progressBar4.setProgress((int) ((movieDetails.getRating().getDetails().get_$2() / totalCount)*100));
                progressBar5.setProgress((int) ((movieDetails.getRating().getDetails().get_$1() / totalCount)*100));

                if (!movieDetails.getTags().isEmpty()){
                    //movieLabelAdapter = new MovieLabelAdapter(movieDetails.getTags());
                    labelRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    labelRecyclerView.setAdapter(new MovieLabelAdapter(movieDetails.getTags()));
                }

                tvMovieIntroduction.setText(movieDetails.getSummary());
                //一般情况下等不到TextView绘制成功，所以有空指针错误,判断方法放在子线程执行
                tvMovieIntroduction.post(new Runnable() {
                    @Override
                    public void run() {
                        //若果超出给定的行数限制，返回要省去的字符数。大于0代表超出文本内容限制
                        int num = tvMovieIntroduction.getLayout().getEllipsisCount(tvMovieIntroduction.getLineCount()-1);
                        //Log.d(TAG,"num:  "+num);
                        if (num > 0 ){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    llOpen.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    }
                });



                //获取演职员数据
                if (!movieDetails.getCasts().isEmpty() && !movieDetails.getDirectors().isEmpty()){
                    castRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    castRecyclerView.setAdapter(new MovieCastAdapter(movieDetails.getDirectors(),movieDetails.getCasts()));
                }

                //获取预告片剧照栏数据
                if (!movieDetails.getPhotos().isEmpty()){
                    stillRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
                    stillRecyclerView.setAdapter(new MovieStillAdapter(movieDetails.getTrailers(),movieDetails.getPhotos()));
                }

                //获取随机4条热门短评
                if (!movieDetails.getPopular_comments().isEmpty()){
                    commentRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    commentRecyclerView.setAdapter(new MovieShortCommentAdapter(movieDetails.getPopular_comments()));
                }


            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mContext, "F!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().requestFetchMovieDetails(movieDetailsObserver,getIntent().getStringExtra("MovieId"),Util.DOUBAN_APIKEY);


    }







    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.ll_back: //返回
                finish();
                break;

            case R.id.ll_open: //
                tvMovieIntroduction.setMaxLines(20);
                llOpen.setVisibility(View.GONE);
                break;

            case R.id.rl_movie_still: //返回
                Toast.makeText(mContext, "全部剧照", Toast.LENGTH_SHORT).show();;
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



    public void runOkHttp(String id) throws Exception {
        Request request = new Request.Builder()
                .url("http://api.douban.com/v2/movie/subject/"+id+"?apikey=0b2bdeda43b5688921839c8ecb20399b")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        Log.d(TAG, "responseHeaders:  " + responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    if (responseBody != null) {
                        Log.d(TAG, "responseBody.string():" + responseBody.string());
                    }
                }
            }
        });
    }



}
