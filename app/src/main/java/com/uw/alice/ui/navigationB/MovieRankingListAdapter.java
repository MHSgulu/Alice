package com.uw.alice.ui.navigationB;

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
import com.uw.alice.data.model.MovieRankingList;

import java.util.List;

public class MovieRankingListAdapter extends RecyclerView.Adapter <MovieRankingListAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieRankingList> mlistBeans;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieRankingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new MovieRankingListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_ranking_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieRankingListAdapter.ViewHolder holder, int position)
    {
        MovieRankingList listBean = mlistBeans.get(position);
        Glide.with(mContext).load(listBean.getMoviePoster()).placeholder(R.mipmap.icon_placeholder).into(holder.ivMoviePosterCover);
        holder.tvRankListName.setText(listBean.getMovieRankingName());
        holder.tvTips.setText(String.valueOf(listBean.getTips()));

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
    public int getItemCount()
    {
        return mlistBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivMoviePosterCover;
        TextView tvRankListName ;
        TextView tvTips;

        ViewHolder(View view)
        {
            super(view);
            ivMoviePosterCover = view.findViewById(R.id.iv_movie_poster_cover);
            tvRankListName = view.findViewById(R.id.tv_rankList_name);
            tvTips = view.findViewById(R.id.tv_tips);
        }
    }

    MovieRankingListAdapter(List<MovieRankingList> listBeans){
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
