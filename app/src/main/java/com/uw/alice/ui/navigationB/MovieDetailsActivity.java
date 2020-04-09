package com.uw.alice.ui.navigationB;


import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.util.Util;
import com.uw.alice.databinding.ActivityItemNewsDetailBinding;

import java.util.Objects;


public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ItemNewsDetailActivity";
    //private ActivityItemNewsDetailBinding mBinding;
    private Context mContext;

    private TextView tvMovieTitle;
    private ImageView ivMoviePoster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        mContext = MovieDetailsActivity.this;
        //mBinding = DataBindingUtil.setContentView(this,R.layout.activity_item_news_detail);
        //mBinding.setOnClickListener(this);

        LinearLayout llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(this);

        TextView tvMovieTitle = findViewById(R.id.tv_movie_title);
        TextView tvMovieOriginalTitle = findViewById(R.id.tv_movie_original_title);
        TextView tvMovieDirector = findViewById(R.id.tv_movie_director);
        TextView tvMovieGenren = findViewById(R.id.tv_movie_genren);
        TextView tvMovieLength = findViewById(R.id.tv_movie_length);
        TextView tvMovieReleaseDate = findViewById(R.id.tv_movie_release_date);
        TextView tvMovieRating = findViewById(R.id.tv_movie_rating);



        ivMoviePoster = findViewById(R.id.iv_movie_poster);

        if (getIntent()!=null){
            tvMovieTitle.setText(getIntent().getStringExtra("MovieTitle"));
            tvMovieOriginalTitle.setText(String.format("原名：%s", getIntent().getStringExtra("MovieOriginalTitle")));
            tvMovieDirector.setText(String.format("导演：%s", getIntent().getStringExtra("MovieDirector")));
            //ArrayList转化String
            tvMovieGenren.setText(String.format("类型：%s", String.join("/", Objects.requireNonNull(getIntent().getStringArrayListExtra("MovieGenren")))));
            tvMovieLength.setText(String.format("片长：%s", String.join("", Objects.requireNonNull(getIntent().getStringArrayListExtra("MovieLength")))));

            tvMovieReleaseDate.setText(String.format("上映时间：%s", getIntent().getStringExtra("MovieReleaseDate")));
            tvMovieRating.setText(String.format("豆瓣评分：%s", getIntent().getDoubleExtra("MovieRating", 0)));

            Glide.with(mContext).load(getIntent().getStringExtra("MoviePoster")).into(ivMoviePoster);
        }



    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.ll_back: //返回
                finish();
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
