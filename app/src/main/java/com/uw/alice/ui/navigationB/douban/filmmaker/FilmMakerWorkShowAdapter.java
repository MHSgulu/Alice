package com.uw.alice.ui.navigationB.douban.filmmaker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.FilmMaker;
import com.uw.alice.data.util.Util;
import com.uw.alice.ui.navigationB.douban.movie.MovieDetailsActivity;
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

public class FilmMakerWorkShowAdapter extends RecyclerView.Adapter <FilmMakerWorkShowAdapter.ViewHolder> {

    private Context mContext;
    private List<FilmMaker.WorksBean> mDataList;


    @NonNull
    @Override
    public FilmMakerWorkShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filmmaker_work_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmMakerWorkShowAdapter.ViewHolder holder, int position) {
        FilmMaker.WorksBean worksBean = mDataList.get(position);
        Glide.with(mContext).load(worksBean.getSubject().getImages().getSmall()).into(holder.ivMovieCover);
        holder.tvFilmmakerName.setText(worksBean.getSubject().getTitle());
        holder.tvMovieScore.setText(String.valueOf(worksBean.getSubject().getRating().getAverage()));

        holder.ratingBar.setRating((float)worksBean.getSubject().getRating().getAverage() / 2); //设置评分
        holder.ratingBar.setIsIndicator(true); //作为指示器(展示用) 默认false
        //holder.ratingBar.setClickable(false);//禁止点击
        //holder.ratingBar.setClearRatingEnabled(false); //禁止可以清除评分
        //holder.ratingBar.setScrollable(false);//禁止手势滑动评分

        holder.ivMovieCover.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra(Util.ARG_MovieId,worksBean.getSubject().getId());
            intent.putExtra(Util.ARG_MoviePoster,worksBean.getSubject().getImages().getLarge());
            mContext.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovieCover;
        TextView tvFilmmakerName,tvMovieScore;
        BaseRatingBar ratingBar;

        ViewHolder(View view) {
            super(view);
            ivMovieCover= view.findViewById(R.id.iv_movie_poster);
            tvFilmmakerName = view.findViewById(R.id.tv_filmmaker_name);
            ratingBar = view.findViewById(R.id.simpleRatingBar);
            tvMovieScore = view.findViewById(R.id.tv_movie_score);
        }
    }

    public FilmMakerWorkShowAdapter(List<FilmMaker.WorksBean> data){
        mDataList = data;
    }




}
