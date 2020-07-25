package com.uw.alice.data.adapter.filmmaker;

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
import com.uw.alice.ui.navigationB.MovieDetailsActivity;

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


        holder.itemView.setOnClickListener(v -> {
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
        TextView tvFilmmakerName;

        ViewHolder(View view) {
            super(view);
            ivMovieCover= view.findViewById(R.id.iv_movie_poster);
            tvFilmmakerName = view.findViewById(R.id.tv_filmmaker_name);
        }
    }

    public FilmMakerWorkShowAdapter(List<FilmMaker.WorksBean> data){
        mDataList = data;
    }




}
