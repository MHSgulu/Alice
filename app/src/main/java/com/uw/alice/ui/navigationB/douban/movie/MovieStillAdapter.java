package com.uw.alice.ui.navigationB.douban.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uw.alice.R;
import com.uw.alice.data.model.MovieDetails;

import java.util.List;

public class MovieStillAdapter extends RecyclerView.Adapter<MovieStillAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieDetails.TrailersBean> trailersBeanList;
    private List<MovieDetails.PhotosBean> photosBeanList;


    @NonNull
    @Override
    public MovieStillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_still_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieStillAdapter.ViewHolder holder, int position) {
        if (!trailersBeanList.isEmpty()) {
            if (position == 0) {
                MovieDetails.TrailersBean trailersBean = trailersBeanList.get(position);
                Glide.with(mContext).load(trailersBean.getMedium()).into(holder.ivMovieStill);
                holder.tvFlag.setVisibility(View.VISIBLE);
                holder.rlPlay.setVisibility(View.VISIBLE);
            } else {
                MovieDetails.PhotosBean photosBean = photosBeanList.get(position - 1);
                Glide.with(mContext).load(photosBean.getImage()).into(holder.ivMovieStill);
                holder.tvFlag.setVisibility(View.GONE);
                holder.rlPlay.setVisibility(View.GONE);
            }
        } else {
            MovieDetails.PhotosBean photosBean = photosBeanList.get(position);
            Glide.with(mContext).load(photosBean.getImage()).into(holder.ivMovieStill);
            holder.tvFlag.setVisibility(View.GONE);
            holder.rlPlay.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        if (!trailersBeanList.isEmpty()) {
            return photosBeanList.size() + 1;
        } else {
            return photosBeanList.size();
        }

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovieStill;
        TextView tvFlag;
        RelativeLayout rlPlay;

        ViewHolder(View view) {
            super(view);
            ivMovieStill = view.findViewById(R.id.iv_movie_still);
            tvFlag = view.findViewById(R.id.tv_flag);
            rlPlay = view.findViewById(R.id.rl_play);

        }
    }

    MovieStillAdapter(List<MovieDetails.TrailersBean> list1, List<MovieDetails.PhotosBean> list2) {
        trailersBeanList = list1;
        photosBeanList = list2;
    }


}
