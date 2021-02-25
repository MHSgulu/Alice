package com.uw.alice.ui.navigationB.douban.movie;

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
import com.willy.ratingbar.BaseRatingBar;

import java.util.List;

public class MovieShortCommentAdapter extends RecyclerView.Adapter <MovieShortCommentAdapter.ViewHolder> {

    private Context mContext;
    //private List<MovieDetails.PopularCommentsBean> popularCommentsBeanList;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public MovieShortCommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_short_comment_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieShortCommentAdapter.ViewHolder holder, int position)
    {
        /*MovieDetails.PopularCommentsBean popularCommentsBean = popularCommentsBeanList.get(position);
        Glide.with(mContext).load(popularCommentsBean.getAuthor().getAvatar()).into(holder.ivAvatar);
        holder.tvNickname.setText(popularCommentsBean.getAuthor().getName());
        holder.tvCommentTime.setText(popularCommentsBean.getCreated_at().substring(0,10));
        holder.tvCommentContent.setText(popularCommentsBean.getContent());
        holder.tvCommentCount.setText(String.valueOf(popularCommentsBean.getUseful_count()));

        holder.baseRatingBar.setRating((float)popularCommentsBean.getRating().getValue());*/

        /*if (position == popularCommentsBeanList.size()-1){
            holder.divider.setVisibility(View.GONE);
        }*/


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
        return /*popularCommentsBeanList.size()*/4;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvNickname;
        TextView tvCommentTime;
        TextView tvCommentContent;
        TextView tvCommentCount;
        View divider;
        BaseRatingBar baseRatingBar;

        ViewHolder(View view) {
            super(view);
            ivAvatar = view.findViewById(R.id.iv_avatar);
            tvNickname = view.findViewById(R.id.tv_nickname);
            tvCommentTime = view.findViewById(R.id.tv_comment_time);
            tvCommentContent = view.findViewById(R.id.tv_comment_content);
            tvCommentCount = view.findViewById(R.id.tv_comment_count);
            divider = view.findViewById(R.id.divider);
            baseRatingBar = view.findViewById(R.id.commentRatingBar);
            baseRatingBar.setIsIndicator(true);
        }
    }

    /*MovieShortCommentAdapter(List<MovieDetails.PopularCommentsBean> list){
        popularCommentsBeanList = list;
    }*/

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }







}
