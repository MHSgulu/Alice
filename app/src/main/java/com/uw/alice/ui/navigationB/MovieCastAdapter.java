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
import com.uw.alice.data.model.MovieDetails;

import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter <MovieCastAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieDetails.DirectorsBean> directorsBeanList;
    private List<MovieDetails.CastsBean> castsBeanList;
    private OnItemClickListener onItemClickListener;


    @NonNull
    @Override
    public MovieCastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_cast_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieCastAdapter.ViewHolder holder, int position)
    {
        if (position <= directorsBeanList.size() - 1){
            MovieDetails.DirectorsBean directorsBean = directorsBeanList.get(position);
            Glide.with(mContext).load(directorsBean.getAvatars().getLarge()).into(holder.ivCastAvatar);
            holder.tvCastName.setText(directorsBean.getName());
            holder.tvCastPosition.setText("导演");
        }else{
            MovieDetails.CastsBean castsBean = castsBeanList.get(position - directorsBeanList.size());
            Glide.with(mContext).load(castsBean.getAvatars().getLarge()).into(holder.ivCastAvatar);
            holder.tvCastName.setText(castsBean.getName());
            holder.tvCastPosition.setText("演员");
        }








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
        return castsBeanList.size() + directorsBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivCastAvatar;
        TextView tvCastName;
        TextView tvCastPosition;

        ViewHolder(View view)
        {
            super(view);
            ivCastAvatar = view.findViewById(R.id.iv_cast_avatar);
            tvCastName = view.findViewById(R.id.tv_cast_name);
            tvCastPosition = view.findViewById(R.id.tv_cast_position);


        }
    }

    MovieCastAdapter(List<MovieDetails.DirectorsBean> list1,List<MovieDetails.CastsBean> list2){
        directorsBeanList = list1;
        castsBeanList = list2;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }







}
