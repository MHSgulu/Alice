package com.uw.alice.ui.navigationB.hot;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.assist.MoviePosterAdapter;
import com.uw.alice.data.model.Movie;
import com.uw.alice.data.model.MovieDetails;
import com.uw.alice.data.util.Util;
import com.uw.alice.interfaces.OnItemClickListener;
import com.uw.alice.network.retrofit.SingletonRetrofit;
import com.willy.ratingbar.BaseRatingBar;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;


import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class HotMovieListAdapter extends RecyclerView.Adapter<HotMovieListAdapter.ViewHolder> {

    private static final String TAG = "HotMovieListAdapter";
    private Context mContext;
    private final List<Movie.SubjectsBean> mValues;

    private OnItemClickListener onItemClickListener;

    HotMovieListAdapter(List<Movie.SubjectsBean> items) {
        mValues = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, int position) {
        Movie.SubjectsBean movie = mValues.get(position);
        //Log.i(TAG, "数据点位: movieId： " + movie.getId());
        Glide.with(mContext).load(movie.getImages().getSmall()).into(holder.ivCover);
        holder.tvMovieName.setText(movie.getTitle());
        holder.tvYear.setText(String.format("(%s)",movie.getYear()));
        holder.baseRatingBar.setRating((float)movie.getRating().getAverage() / 2);
        holder.tvMovieScore.setText(String.valueOf(movie.getRating().getAverage()));

        if (!movie.getGenres().isEmpty()){
            holder.labelRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            holder.labelRecyclerView.setAdapter(new HotMovieLabelAdapter(movie.getGenres()));
        }

        Observer<MovieDetails> movieDetailsObserver = new Observer<MovieDetails>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieDetails movieDetails) {
                holder.tvMovieIntroduction.setText(movieDetails.getSummary());
                List<String> moviePosterList;
                if (movieDetails.getPhotos().size() > 4){
                    moviePosterList = movieDetails.getPhotos().stream().map(MovieDetails.PhotosBean::getImage).collect(Collectors.toList()).subList(0,4);
                }else {
                    moviePosterList = movieDetails.getPhotos().stream().map(MovieDetails.PhotosBean::getImage).collect(Collectors.toList());
                }
                holder.banner.setAdapter(new MoviePosterAdapter(moviePosterList))
                        .setIndicator(new CircleIndicator(mContext))
                        .setIndicatorSelectedColorRes(R.color.colorNavigationB)
                        .setIndicatorGravity(IndicatorConfig.Direction.LEFT)
                        .isAutoLoop(false);

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        SingletonRetrofit.getInstance().requestFetchMovieDetails(movieDetailsObserver,movie.getId(),Util.DOUBAN_APIKEY);


        if (onItemClickListener != null){
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView,holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView ivCover;
        final Banner banner;
        final TextView tvMovieName,tvYear,tvMovieScore,tvMovieIntroduction;
        final BaseRatingBar baseRatingBar;
        final RecyclerView labelRecyclerView;

        public ViewHolder(View view) {
            super(view);
            ivCover = view.findViewById(R.id.iv_cover);
            banner = view.findViewById(R.id.banner);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            tvYear = view.findViewById(R.id.tv_year);
            baseRatingBar = view.findViewById(R.id.baseRatingBar);
            tvMovieScore = view.findViewById(R.id.tv_movie_score);
            tvMovieIntroduction = view.findViewById(R.id.tv_movie_introduction);
            labelRecyclerView = view.findViewById(R.id.list_label);
        }

    }

}
