package com.uw.alice.ui.navigationB.mtime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.entity.MovieEntity;
import com.uw.alice.interfaces.OnItemClickListener;
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

public class MovieShowingAdapter extends RecyclerView.Adapter<MovieShowingAdapter.ViewHolder> {

    private Context mContext;
    private final List<MovieEntity> mList;
    private OnItemClickListener onItemClickListener;


    public MovieShowingAdapter(List<MovieEntity> listBeans) {
        mList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MovieShowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_movie_showing_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieShowingAdapter.ViewHolder holder, int position) {
        MovieEntity result = mList.get(position);
        Glide.with(mContext).load(result.getMoviePosterUrl()).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(result.getMovieName());
        holder.tvMovieScore.setText(String.valueOf(result.getMovieRating()));
        holder.ratingBar.setRating((float) result.getMovieRating() / 2);

        if (onItemClickListener != null) {
            holder.ivMoviePoster.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }

    }

    @Override
    public int getItemCount() {
        return Math.min(mList.size(), 6);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMoviePoster;
        TextView tvMovieName;
        TextView tvMovieScore;
        BaseRatingBar ratingBar;

        ViewHolder(View view) {
            super(view);
            ivMoviePoster = view.findViewById(R.id.iv_movie_poster);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            tvMovieScore = view.findViewById(R.id.tv_movie_score);
            ratingBar = view.findViewById(R.id.simpleRatingBar);
        }
    }

}
