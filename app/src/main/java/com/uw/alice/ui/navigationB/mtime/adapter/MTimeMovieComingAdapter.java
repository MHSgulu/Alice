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
import com.uw.alice.data.model.MTimeComingMovie;
import com.uw.alice.data.model.Movie;
import com.uw.alice.interfaces.OnItemClickListener;

import java.util.List;

public class MTimeMovieComingAdapter extends RecyclerView.Adapter<MTimeMovieComingAdapter.ViewHolder> {

    private Context mContext;
    private List<MTimeComingMovie.MoviecomingsBean> mList;
    private OnItemClickListener onItemClickListener;

    public MTimeMovieComingAdapter(List<MTimeComingMovie.MoviecomingsBean> listBeans) {
        mList = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public MTimeMovieComingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_movie_coming_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MTimeMovieComingAdapter.ViewHolder holder, int position) {
        MTimeComingMovie.MoviecomingsBean result = mList.get(position);
        Glide.with(mContext).load(result.getImage()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(result.getTitle());
        holder.tvDate.setText(result.getReleaseDate());


        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition()));
        }


    }

    @Override
    public int getItemCount() {
        /*
          返回两个{@code int}值中较小的一个。也就是说，参数的结果更接近{@link Integer#MIN_value}的值。如果参数具有相同的值，则结果是相同的值。
          (a <= b) ? a : b
         */
        return Math.min(mList.size(), 6);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMoviePoster;
        TextView tvMovieName;
        TextView tvDate;

        ViewHolder(View view) {
            super(view);
            ivMoviePoster = view.findViewById(R.id.iv_movie_poster);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            tvDate = view.findViewById(R.id.tv_date);
        }
    }


}
