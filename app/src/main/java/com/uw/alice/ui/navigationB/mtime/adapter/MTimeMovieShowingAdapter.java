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
import com.uw.alice.data.model.MTimeInTheatersMovie;
import com.uw.alice.data.model.Movie;
import com.uw.alice.interfaces.OnItemClickListener;
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

public class MTimeMovieShowingAdapter extends RecyclerView.Adapter<MTimeMovieShowingAdapter.ViewHolder> {

    private Context mContext;
    private List<MTimeInTheatersMovie.MsBean> mList;
    private OnItemClickListener onItemClickListener;


    public MTimeMovieShowingAdapter(List<MTimeInTheatersMovie.MsBean> listBeans) {
        mList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MTimeMovieShowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_movie_showing_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MTimeMovieShowingAdapter.ViewHolder holder, int position) {
        MTimeInTheatersMovie.MsBean result = mList.get(position);
        Glide.with(mContext).load(result.getImg()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(result.getTCn());
        if (result.getR() > 0){
            holder.tvMovieScore.setText(String.valueOf(result.getR()));
            holder.ratingBar.setRating((float) result.getR() / 2);
        }else {
            holder.tvMovieScore.setText(String.valueOf(0));
            holder.ratingBar.setRating((float) 0);
        }


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
