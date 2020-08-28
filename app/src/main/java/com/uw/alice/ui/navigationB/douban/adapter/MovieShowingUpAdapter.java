package com.uw.alice.ui.navigationB.douban.adapter;

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
import com.uw.alice.data.model.Movie;
import com.uw.alice.interfaces.OnItemClickListener;
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

public class MovieShowingUpAdapter extends RecyclerView.Adapter <MovieShowingUpAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie.SubjectsBean> subjectsBeanList;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieShowingUpAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_showing_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieShowingUpAdapter.ViewHolder holder, int position) {
        Movie.SubjectsBean listBean = subjectsBeanList.get(position);
        Glide.with(mContext).load(listBean.getImages().getLarge()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(listBean.getTitle());
        holder.tvMovieScore.setText(String.valueOf(listBean.getRating().getAverage()));

        holder.ratingBar.setRating((float)listBean.getRating().getAverage() / 2);
        holder.ratingBar.setIsIndicator(true);

        if (onItemClickListener != null) {
            holder.ivMoviePoster.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }

    }

    @Override
    public int getItemCount()
    {
        return subjectsBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMoviePoster;
        TextView tvMovieName ;
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

    public MovieShowingUpAdapter(List<Movie.SubjectsBean> listBeans){
        subjectsBeanList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }








}
