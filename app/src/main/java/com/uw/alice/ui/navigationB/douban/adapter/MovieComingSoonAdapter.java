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

import java.util.List;

public class MovieComingSoonAdapter extends RecyclerView.Adapter <MovieComingSoonAdapter.ViewHolder> {

    private Context mContext;
    private List<Movie.SubjectsBean> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieComingSoonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_coming_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieComingSoonAdapter.ViewHolder holder, int position)
    {
        Movie.SubjectsBean listBean = mlistBeans.get(position);
        Glide.with(mContext).load(listBean.getImages().getLarge()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePoster);
        holder.tvMovieName.setText(listBean.getTitle());
        //holder.tvMovieScore.setText(String.valueOf(listBean.getRating().getAverage()));
        holder.tvDate.setText(listBean.getMainland_pubdate().substring(5));

        // 如果设置了回调，则设置点击事件
        if (onItemClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        /*
          返回两个{@code int}值中较小的一个。也就是说，参数的结果更接近{@link Integer#MIN_value}的值。如果参数具有相同的值，则结果是相同的值。
          (a <= b) ? a : b
         */
        return Math.min(mlistBeans.size(), 6);
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivMoviePoster;
        TextView tvMovieName ;
        TextView tvMovieScore;
        TextView tvDate;

        ViewHolder(View view)
        {
            super(view);
            ivMoviePoster = view.findViewById(R.id.iv_movie_poster);
            tvMovieName = view.findViewById(R.id.tv_movie_name);
            //tvMovieScore = view.findViewById(R.id.tv_movie_score);
            tvDate = view.findViewById(R.id.tv_date);
        }
    }

    public MovieComingSoonAdapter(List<Movie.SubjectsBean> listBeans){
        mlistBeans = listBeans;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }







}
